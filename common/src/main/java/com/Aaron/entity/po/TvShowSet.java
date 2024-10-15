package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电视剧集数表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("tv_show_set")
public class TvShowSet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tvShowSetId;

    /**
     * 集数名
     */
    private String tvShowSetName;

    /**
     * 播放地址
     */
    private String tvShowSetUrl;

    /**
     * 电视剧id
     */
    private String tvShowId;

    public String getTvShowSetId() {
        return tvShowSetId;
    }

    public void setTvShowSetId(String tvShowSetId) {
        this.tvShowSetId = tvShowSetId;
    }

    public String getTvShowSetName() {
        return tvShowSetName;
    }

    public void setTvShowSetName(String tvShowSetName) {
        this.tvShowSetName = tvShowSetName;
    }

    public String getTvShowSetUrl() {
        return tvShowSetUrl;
    }

    public void setTvShowSetUrl(String tvShowSetUrl) {
        this.tvShowSetUrl = tvShowSetUrl;
    }

    public String getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    @Override
    public String toString() {
        return "TvShowSet{" +
            "tvShowSetId = " + tvShowSetId +
            ", tvShowSetName = " + tvShowSetName +
            ", tvShowSetUrl = " + tvShowSetUrl +
            ", tvShowId = " + tvShowId +
        "}";
    }
}
