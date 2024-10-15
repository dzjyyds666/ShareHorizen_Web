package com.Aaron.service.impl;

import com.Aaron.entity.po.VideoType;
import com.Aaron.mapper.VideoTypeMapper;
import com.Aaron.service.IVideoTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影类型表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@Service
public class VideoTypeServiceImpl extends ServiceImpl<VideoTypeMapper, VideoType> implements IVideoTypeService {

}
