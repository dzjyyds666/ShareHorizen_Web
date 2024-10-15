package com.Aaron.admin.controller;

import com.Aaron.service.IMovieInfoService;
import com.Aaron.utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 电影信息表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@RestController
@RequestMapping("/movieInfo")
@Tag(name = "电影信息")
public class MovieInfoController {

    @Autowired
    private IMovieInfoService movieInfoService;



    @PostMapping("/upload-chunk")
    private Result<String> uploadBigFile(@RequestParam MultipartFile file,@RequestParam int chunkNumber,@RequestParam int totalChunks,@RequestParam String name){
        return Result.ok(movieInfoService.uploadBigFile(file,chunkNumber,totalChunks,name));
    }

}
