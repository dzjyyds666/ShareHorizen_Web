package com.Aaron.service.impl;

import com.Aaron.Exection.MyException;
import com.Aaron.entity.po.MovieInfo;
import com.Aaron.mapper.MovieInfoMapper;
import com.Aaron.service.IMovieInfoService;
import com.Aaron.utils.Result;
import com.Aaron.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
                return "全部上传完成";
            }

            return "上传第"+ chunkNumber +"片";
        }catch (Exception e){
            throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
        }
    }

    public void mergeChunks(String name,int totalChunks){
        try {
            // 创建输出文件
            String outputFilePath = TEMP_PATH + name + ".mp4";
            File outputFile = new File(outputFilePath);
            boolean newFile = outputFile.createNewFile();
            if (!newFile) {
                throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
            }

            // 初始化输出文件
            try (FileOutputStream writer = new FileOutputStream(outputFile)) {
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
                Files.delete(Paths.get(TEMP_PATH + name));
            }
       }catch (Exception e){
           throw new MyException(ResultCodeEnum.UPLOAD_ERROR);
       }
    }
}
