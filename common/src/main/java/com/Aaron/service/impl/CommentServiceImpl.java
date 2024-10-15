package com.Aaron.service.impl;

import com.Aaron.entity.po.Comment;
import com.Aaron.mapper.CommentMapper;
import com.Aaron.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
