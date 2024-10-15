package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 短视频信息表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("short_video_info")
public class ShortVideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String shortVideoId;

    /**
     * 短视频名
     */
    private String shortVideoName;

    /**
     * 描述
     */
    private String shortVideoDescription;

    /**
     * 时长
     */
    private String shortVideoTime;

    /**
     * 上传时间
     */
    private LocalDateTime createTime;

    /**
     * 封面
     */
    private String shortVideoPicture;

    /**
     * 播放地址
     */
    private String shortVideoUrl;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 观看量
     */
    private Integer shortVideoViews;

    /**
     * 点赞量
     */
    private Integer shortVideoLikes;

    /**
     * 状态 0:下架  1:上架
     */
    private Boolean status;

    public String getShortVideoId() {
        return shortVideoId;
    }

    public void setShortVideoId(String shortVideoId) {
        this.shortVideoId = shortVideoId;
    }

    public String getShortVideoName() {
        return shortVideoName;
    }

    public void setShortVideoName(String shortVideoName) {
        this.shortVideoName = shortVideoName;
    }

    public String getShortVideoDescription() {
        return shortVideoDescription;
    }

    public void setShortVideoDescription(String shortVideoDescription) {
        this.shortVideoDescription = shortVideoDescription;
    }

    public String getShortVideoTime() {
        return shortVideoTime;
    }

    public void setShortVideoTime(String shortVideoTime) {
        this.shortVideoTime = shortVideoTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getShortVideoPicture() {
        return shortVideoPicture;
    }

    public void setShortVideoPicture(String shortVideoPicture) {
        this.shortVideoPicture = shortVideoPicture;
    }

    public String getShortVideoUrl() {
        return shortVideoUrl;
    }

    public void setShortVideoUrl(String shortVideoUrl) {
        this.shortVideoUrl = shortVideoUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getShortVideoViews() {
        return shortVideoViews;
    }

    public void setShortVideoViews(Integer shortVideoViews) {
        this.shortVideoViews = shortVideoViews;
    }

    public Integer getShortVideoLikes() {
        return shortVideoLikes;
    }

    public void setShortVideoLikes(Integer shortVideoLikes) {
        this.shortVideoLikes = shortVideoLikes;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShortVideoInfo{" +
            "shortVideoId = " + shortVideoId +
            ", shortVideoName = " + shortVideoName +
            ", shortVideoDescription = " + shortVideoDescription +
            ", shortVideoTime = " + shortVideoTime +
            ", createTime = " + createTime +
            ", shortVideoPicture = " + shortVideoPicture +
            ", shortVideoUrl = " + shortVideoUrl +
            ", userId = " + userId +
            ", shortVideoViews = " + shortVideoViews +
            ", shortVideoLikes = " + shortVideoLikes +
            ", status = " + status +
        "}";
    }
}
