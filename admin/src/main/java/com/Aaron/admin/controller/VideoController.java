package com.Aaron.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RequestMapping("/video")
public class VideoController {

    private static final String TEMP_DIR = "/tmp/video_chunks/";

    @PostMapping("/upload-chunk")
    public String handleFileChunkUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("chunkIndex") int chunkIndex,
            @RequestParam("totalChunks") int totalChunks) {

        try {
            // 创建临时目录
            Files.createDirectories(Paths.get(TEMP_DIR));

            // 保存分片到临时目录
            String chunkFileName = "chunk_" + chunkIndex;
            String chunkFilePath = TEMP_DIR + chunkFileName;
            file.transferTo(new File(chunkFilePath));

            // 检查是否所有分片都已上传
            boolean allChunksUploaded = true;
            for (int i = 0; i < totalChunks; i++) {
                if (!new File(TEMP_DIR + "chunk_" + i).exists()) {
                    allChunksUploaded = false;
                    break;
                }
            }

            if (allChunksUploaded) {
                // 合并所有分片
                mergeChunks(totalChunks);
                return "All chunks uploaded and merged successfully";
            } else {
                return "Chunk " + chunkIndex + " uploaded successfully";
            }
        } catch (IOException | InterruptedException e) {
            return "Failed to upload chunk " + chunkIndex + ": " + e.getMessage();
        }
    }

    private void mergeChunks(int totalChunks) throws IOException, InterruptedException {
        // 创建目标文件
        String outputFile = "/tmp/output_video.mp4";
        File outputFileObj = new File(outputFile);
        outputFileObj.createNewFile();

        // 合并所有分片
        for (int i = 0; i < totalChunks; i++) {
            String chunkFileName = "chunk_" + i;
            String chunkFilePath = TEMP_DIR + chunkFileName;

            //Files.copy(Paths.get(chunkFilePath), Files.newOutputStream(Paths.get(outputFileObj.toURI())), StandardCopyOption.APPEND);
            Files.delete(Paths.get(chunkFilePath));
        }

        // 转换为M3U8格式
        convertToM3U8(outputFile);
    }

    private void convertToM3U8(String originalFilePath) throws IOException, InterruptedException {
        String outputFilePath = "/tmp/output.m3u8";
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg", "-i", originalFilePath, "-c:v", "libx264", "-profile:v", "main", "-level", "3.0",
                "-c:a", "aac", "-b:a", "128k", "-ar", "44100", "-f", "hls", outputFilePath);

        Process process = processBuilder.start();
        int exitCode = process.waitFor(); // 等待转换完成

        // 删除原始文件
        Files.delete(Paths.get(originalFilePath));

        if (exitCode == 0) {
            System.out.println("Video converted successfully.");
        } else {
            throw new RuntimeException("Failed to convert video.");
        }
    }
}
