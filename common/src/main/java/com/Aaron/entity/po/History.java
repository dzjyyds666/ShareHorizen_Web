package com.Aaron.entity.po;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 观看记录表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    private String historyId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 视频类型 1:电影 2:电视剧 3:短视频
     */
    private Boolean videoType;

    /**
     * 观看时间
     */
    private LocalDateTime createTime;

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Boolean getVideoType() {
        return videoType;
    }

    public void setVideoType(Boolean videoType) {
        this.videoType = videoType;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "History{" +
            "historyId = " + historyId +
            ", userId = " + userId +
            ", videoId = " + videoId +
            ", videoType = " + videoType +
            ", createTime = " + createTime +
        "}";
    }
}
