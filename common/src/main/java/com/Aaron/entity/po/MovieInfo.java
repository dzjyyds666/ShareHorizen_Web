package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电影信息表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("movie_info")
public class MovieInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieId;

    /**
     * 电影名
     */
    private String movieName;

    /**
     * 电影时长
     */
    private String movieTime;

    /**
     * 上映时间
     */
    private String movieReleaseTime;

    /**
     * 评分
     */
    private String movieScore;

    /**
     * 国家
     */
    private String movieCountry;

    /**
     * 语言
     */
    private String movieLanguage;

    /**
     * 海报
     */
    private String moviePicture;

    /**
     * 简介
     */
    private String movieIntroduction;

    /**
     * 主演
     */
    private String movieStarring;

    /**
     * 导演
     */
    private String movieDirector;

    /**
     * 播放地址
     */
    private String movieUrl;

    /**
     * 状态 0:下架  1:上架
     */
    private Boolean status;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public String getMovieReleaseTime() {
        return movieReleaseTime;
    }

    public void setMovieReleaseTime(String movieReleaseTime) {
        this.movieReleaseTime = movieReleaseTime;
    }

    public String getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(String movieScore) {
        this.movieScore = movieScore;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMoviePicture() {
        return moviePicture;
    }

    public void setMoviePicture(String moviePicture) {
        this.moviePicture = moviePicture;
    }

    public String getMovieIntroduction() {
        return movieIntroduction;
    }

    public void setMovieIntroduction(String movieIntroduction) {
        this.movieIntroduction = movieIntroduction;
    }

    public String getMovieStarring() {
        return movieStarring;
    }

    public void setMovieStarring(String movieStarring) {
        this.movieStarring = movieStarring;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
            "movieId = " + movieId +
            ", movieName = " + movieName +
            ", movieTime = " + movieTime +
            ", movieReleaseTime = " + movieReleaseTime +
            ", movieScore = " + movieScore +
            ", movieCountry = " + movieCountry +
            ", movieLanguage = " + movieLanguage +
            ", moviePicture = " + moviePicture +
            ", movieIntroduction = " + movieIntroduction +
            ", movieStarring = " + movieStarring +
            ", movieDirector = " + movieDirector +
            ", movieUrl = " + movieUrl +
            ", status = " + status +
        "}";
    }
}
