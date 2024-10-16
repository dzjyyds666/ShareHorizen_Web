package com.Aaron.admin;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@SpringBootTest
public class test {

    @Test
    public void test() {
        String videoPath = "/Users/zhijundu/Movies/Videos/鬼怪第一集.mp4"; // 视频文件路径
        String outputPath = "/Users/zhijundu/Movies/Videos"; // 输出目录
        String segmentPath = outputPath + "/1041244655-1-16"; // 分片文件夹路径

        try {
//            // 创建输出目录和分片文件夹
//            File outputDir = new File(outputPath);
//            if (!outputDir.exists()) {
//                outputDir.mkdirs();
//            }
//
//            File segmentDir = new File(segmentPath);
//            if (!segmentDir.exists()) {
//                segmentDir.mkdirs();
//            }

            // FFmpeg命令，将视频转码为HLS格式并生成M3U8文件
            String ffmpegCommand = "ffmpeg -i /Users/zhijundu/Movies/Videos/鬼怪第一集.mp4  -c:v copy -c:a copy -f hls -hls_list_size 0 -hls_time 15 -hls_segment_filename /Users/zhijundu/Movies/Videos/鬼怪第一集/output%03d.ts /Users/zhijundu/Movies/Videos/output.m3u8";

            // 打印FFmpeg命令以便调试
            System.out.println("Executing FFmpeg command: " + ffmpegCommand);

            // 执行FFmpeg命令
            Process process = Runtime.getRuntime().exec(ffmpegCommand);

            Thread outputThread = new Thread(()->{
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                    String line;
                    while ((line = reader.readLine()) != null) {}
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            outputThread.start();

            Thread errorThread = new Thread(()->{
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))){
                    String line;
                    while ((line = reader.readLine()) != null) {}
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            errorThread.start();

            // 等待FFmpeg命令执行完成
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode); // 输出进程退出状态码
            outputThread.join();
            errorThread.join();
        } catch (Exception e) {
            e.printStackTrace(); // 处理异常
        }
    }

}

