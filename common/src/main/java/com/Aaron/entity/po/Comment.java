package com.Aaron.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String commentId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

    /**
     * 父评论id
     */
    private String commentParentId;

    /**
     * 评论视频id
     */
    private String commentVideoId;

    /**
     * 评论类型 1:电影评论  2:电视剧评论 3:短视频评论
     */
    private Boolean commentVideType;

    /**
     * 状态 0:删除  1:正常
     */
    private Boolean status;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(String commentParentId) {
        this.commentParentId = commentParentId;
    }

    public String getCommentVideoId() {
        return commentVideoId;
    }

    public void setCommentVideoId(String commentVideoId) {
        this.commentVideoId = commentVideoId;
    }

    public Boolean getCommentVideType() {
        return commentVideType;
    }

    public void setCommentVideType(Boolean commentVideType) {
        this.commentVideType = commentVideType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "commentId = " + commentId +
            ", userId = " + userId +
            ", commentContent = " + commentContent +
            ", createTime = " + createTime +
            ", commentParentId = " + commentParentId +
            ", commentVideoId = " + commentVideoId +
            ", commentVideType = " + commentVideType +
            ", status = " + status +
        "}";
    }
}
