package com.Aaron.service;

import com.Aaron.entity.po.MovieInfo;
import com.Aaron.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 电影信息表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
public interface IMovieInfoService extends IService<MovieInfo> {

    String uploadBigFile(MultipartFile file, int chunkNumber, int totalChunks, String name);
}
