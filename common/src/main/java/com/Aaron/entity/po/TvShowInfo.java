package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电视剧信息表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("tv_show_info")
public class TvShowInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tvShowId;

    /**
     * 电视剧名
     */
    private String tvShowName;

    /**
     * 电视剧时长
     */
    private String tvShowTime;

    /**
     * 上映时间
     */
    private String tvShowReleaseTime;

    /**
     * 评分
     */
    private String tvShowScore;

    /**
     * 国家
     */
    private String tvShowCountry;

    /**
     * 语言
     */
    private String tvShowLanguage;

    /**
     * 海报
     */
    private String tvShowPicture;

    /**
     * 简介
     */
    private String tvShowIntroduction;

    /**
     * 主演
     */
    private String tvShowStarring;

    /**
     * 导演
     */
    private String tvShowDirector;

    /**
     * 状态 0:下架  1:上架
     */
    private Boolean status;

    public String getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    public String getTvShowName() {
        return tvShowName;
    }

    public void setTvShowName(String tvShowName) {
        this.tvShowName = tvShowName;
    }

    public String getTvShowTime() {
        return tvShowTime;
    }

    public void setTvShowTime(String tvShowTime) {
        this.tvShowTime = tvShowTime;
    }

    public String getTvShowReleaseTime() {
        return tvShowReleaseTime;
    }

    public void setTvShowReleaseTime(String tvShowReleaseTime) {
        this.tvShowReleaseTime = tvShowReleaseTime;
    }

    public String getTvShowScore() {
        return tvShowScore;
    }

    public void setTvShowScore(String tvShowScore) {
        this.tvShowScore = tvShowScore;
    }

    public String getTvShowCountry() {
        return tvShowCountry;
    }

    public void setTvShowCountry(String tvShowCountry) {
        this.tvShowCountry = tvShowCountry;
    }

    public String getTvShowLanguage() {
        return tvShowLanguage;
    }

    public void setTvShowLanguage(String tvShowLanguage) {
        this.tvShowLanguage = tvShowLanguage;
    }

    public String getTvShowPicture() {
        return tvShowPicture;
    }

    public void setTvShowPicture(String tvShowPicture) {
        this.tvShowPicture = tvShowPicture;
    }

    public String getTvShowIntroduction() {
        return tvShowIntroduction;
    }

    public void setTvShowIntroduction(String tvShowIntroduction) {
        this.tvShowIntroduction = tvShowIntroduction;
    }

    public String getTvShowStarring() {
        return tvShowStarring;
    }

    public void setTvShowStarring(String tvShowStarring) {
        this.tvShowStarring = tvShowStarring;
    }

    public String getTvShowDirector() {
        return tvShowDirector;
    }

    public void setTvShowDirector(String tvShowDirector) {
        this.tvShowDirector = tvShowDirector;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TvShowInfo{" +
            "tvShowId = " + tvShowId +
            ", tvShowName = " + tvShowName +
            ", tvShowTime = " + tvShowTime +
            ", tvShowReleaseTime = " + tvShowReleaseTime +
            ", tvShowScore = " + tvShowScore +
            ", tvShowCountry = " + tvShowCountry +
            ", tvShowLanguage = " + tvShowLanguage +
            ", tvShowPicture = " + tvShowPicture +
            ", tvShowIntroduction = " + tvShowIntroduction +
            ", tvShowStarring = " + tvShowStarring +
            ", tvShowDirector = " + tvShowDirector +
            ", status = " + status +
        "}";
    }
}
