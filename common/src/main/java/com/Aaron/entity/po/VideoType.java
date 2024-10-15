package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电影类型表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("video_type")
public class VideoType implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieTypeId;

    /**
     * 电影类型名
     */
    private String movieTypeName;

    /**
     * 状态 0:禁用  1:status
     */
    private Boolean staus;

    public String getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(String movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }

    public Boolean getStaus() {
        return staus;
    }

    public void setStaus(Boolean staus) {
        this.staus = staus;
    }

    @Override
    public String toString() {
        return "VideoType{" +
            "movieTypeId = " + movieTypeId +
            ", movieTypeName = " + movieTypeName +
            ", staus = " + staus +
        "}";
    }
}
