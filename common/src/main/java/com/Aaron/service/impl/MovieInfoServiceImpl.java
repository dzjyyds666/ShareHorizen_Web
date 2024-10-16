package com.Aaron.service.impl;

import com.Aaron.Exection.MyException;
import com.Aaron.entity.po.MovieInfo;
import com.Aaron.mapper.MovieInfoMapper;
import com.Aaron.service.IMovieInfoService;
import com.Aaron.utils.ResultCodeEnum;
import com.Aaron.utils.minio.MinioProperties;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.*;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 电影信息表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@Service
public class MovieInfoServiceImpl extends ServiceImpl<MovieInfoMapper, MovieInfo> implements IMovieInfoService {

    // 临时文件存储路径
    private static final String TEMP_PATH = "/tmp/video_chunks/";

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MovieInfoMapper movieInfoMapper;

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String uploadBigFile(MultipartFile file, int chunkNumber, int totalChunks, String name) {
        try{
            // 接收文件分片并保存到临时目录中
            // 创建临时目录
            name = name.substring(0,name.lastIndexOf("."));
            String tempDir = TEMP_PATH+name+"/";
            Files.createDirectories(Paths.get(tempDir));
            // 保存分片到临时目录
            String chunkFileName = name + "_chunk_" + chunkNumber;
            String chunkFilePath = tempDir + chunkFileName;
            // 将文件写入磁盘
            file.transferTo(new File(chunkFilePath));

            boolean allChunksUploaded = true;
            for(int i = 1; i <= totalChunks; i++){
                String chunkPath = tempDir + name + "_chunk_" + i;
                if(!Files.exists(Paths.get(chunkPath))){
                    allChunksUploaded = false;
                    break;
                }
            }

            if(allChunksUploaded){
                // 合并所有切片
                mergeChunks(name,totalChunks);
                // 文件改为m3u8存储
                convertToM3u8(name);

                final String finalName = name;
                threadPoolExecutor.execute(()-> {
                    List<String> filepaths = new ArrayList<>();
                    // 遍历文件夹下所有文件
                    File dir = new File(tempDir);
                    if(dir.exists() && dir.isDirectory()){
                        File[] files = dir.listFiles();
                        for (File tmpfile: files){
                            String url = uploadFileToMinio("Movie", tmpfile.getAbsolutePath(),finalName);
                            filepaths.add(url);
                        }
                        Path m3u8Path = Paths.get(TEMP_PATH +finalName+"/" + finalName + ".m3u8");
                        try {
                            byte[] bytes = Files.readAllBytes(m3u8Path);
                            String m3u8file = new String(bytes);
                            for (String url: filepaths){
                                String name1 = url.substring(url.lastIndexOf("/")+1);
                                if(m3u8file.contains(name1)){
                                    m3u8file = m3u8file.replace(name1,url);
                                }
                            }
                            Path tmp = Paths.get(TEMP_PATH+ finalName+ "/" + finalName+".m3u8");
                            Files.write(tmp, m3u8file.getBytes());
                            uploadFileToMinio("Movie",TEMP_PATH+finalName+"/"+finalName+".m3u8",finalName);
                            File delete = new File(TEMP_PATH + finalName);
                            FileUtils.deleteDirectory(delete);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("上传完成");
                    }
                });
                System.out.println(String.join("/",minioProperties.getEndpoint(),minioProperties.getBucketName(),"Movie/" + name +"/" + name + ".m3u8"));
                return String.join("/",minioProperties.getEndpoint(),minioProperties.getBucketName(),"Movie/" + name +"/" + name + ".m3u8");
            }
            return "上传第"+ chunkNumber +"片";
        }catch (Exception e){
            throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }

    private void convertToM3u8(String name) {
        String outputFilePath = TEMP_PATH + name + "/" + name + ".m3u8";
        String inputFilePath = TEMP_PATH + name + "/" + name + ".mp4";
        String segmentPath = TEMP_PATH + name ;
        try{
            // 创建分片目录
            File segmentDir = new File(segmentPath);
            if (!segmentDir.exists()) {
                segmentDir.mkdirs();
            }

            // FFmpeg命令，将视频转码为HLS格式并生成M3U8文件
            String ffmpegCommand = "ffmpeg -i "+ inputFilePath +" -c:v copy -c:a copy -f hls -hls_list_size 0 -hls_time 15 -hls_segment_filename "+ segmentPath +"/"+ name +"%03d.ts "+ outputFilePath;
            //System.out.println(ffmpegCommand);
            Process process = Runtime.getRuntime().exec(ffmpegCommand);

            Thread outputThread = new Thread(() -> {
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                    while ((reader.readLine()) != null) {
                    }
                }catch (Exception e){
                    throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
                }
            });
            outputThread.start();

            Thread errorThread = new Thread(()->{
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){
                    while ((reader.readLine()) != null){
                    }
                }catch (Exception e){
                    throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
                }
            });
            errorThread.start();

            int code = process.waitFor();
            outputThread.join();
            errorThread.join();
            Files.delete(Paths.get(inputFilePath));
            if (code != 0) {
                throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
            }
        }catch (Exception e){
            throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }

    public void mergeChunks(String name,int totalChunks){
        try {
            // 创建输出文件
            String outputFilePath = TEMP_PATH + name + "/" + name + ".mp4";
            File outputFile = new File(outputFilePath);
            boolean newFile = outputFile.createNewFile();
            if (!newFile) {
                throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
            }

            // 初始化输出文件
            try (FileOutputStream writer = new FileOutputStream(outputFile,true)) {
                for (int i = 1; i <= totalChunks; i++) {
                    String chunkPath = TEMP_PATH + name + "/" + name + "_chunk_" + i;
                    File chunkFile = new File(chunkPath);

                    try (FileInputStream reader = new FileInputStream(chunkFile)) {
                        byte[] buffer = new byte[8192]; // 固定缓冲区大小
                        int n;
                        while ((n = reader.read(buffer)) != -1) {
                            writer.write(buffer, 0, n);
                        }
                    }
                    Files.delete(Paths.get(chunkPath));
                }
            }
        }catch (Exception e){
            throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }

    public String uploadFileToMinio(String type,String filePath,String name){
        try{

            Path path = Paths.get(filePath);

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if(!bucketExists){
                // 创建存储桶
                minioClient.makeBucket(MakeBucketArgs
                        .builder()
                        .bucket(minioProperties.getBucketName())
                        .build());

                String bucketPolicy = """
                        {
                          "Statement" : [ {
                            "Action" : "s3:GetObject",
                            "Effect" : "Allow",
                            "Principal" : "*",
                            "Resource" : "arn:aws:s3:::%s/*"
                          } ],
                          "Version" : "2012-10-17"
                        }""".formatted(minioProperties.getBucketName());
                // 设置存储桶权限
                minioClient.setBucketPolicy(SetBucketPolicyArgs
                        .builder()
                        .bucket(minioProperties.getBucketName())
                        .config(bucketPolicy)
                        .build());
            }
            // 类型监测工具
            Tika tika = new Tika();
            String filename = type+"/" + name +"/"+path.getFileName();
            System.out.println(filename);
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(Files.newInputStream(path), Files.size(path), -1)
                    .contentType(tika.detect(path))
                    .object(filename)
                    .build());
            return String.join("/",minioProperties.getEndpoint(),minioProperties.getBucketName(),filename);
        }catch (Exception e){
            throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }
}
