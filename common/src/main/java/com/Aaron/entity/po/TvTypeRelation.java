package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电视剧类型关系表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("tv_type_relation")
public class TvTypeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tTRelationId;

    /**
     * 电视剧id
     */
    private String tvShowId;

    /**
     * 电视剧类型id
     */
    private String tvTypeId;

    public String gettTRelationId() {
        return tTRelationId;
    }

    public void settTRelationId(String tTRelationId) {
        this.tTRelationId = tTRelationId;
    }

    public String getTvShowId() {
        return tvShowId;
    }

    public void setTvShowId(String tvShowId) {
        this.tvShowId = tvShowId;
    }

    public String getTvTypeId() {
        return tvTypeId;
    }

    public void setTvTypeId(String tvTypeId) {
        this.tvTypeId = tvTypeId;
    }

    @Override
    public String toString() {
        return "TvTypeRelation{" +
            "tTRelationId = " + tTRelationId +
            ", tvShowId = " + tvShowId +
            ", tvTypeId = " + tvTypeId +
        "}";
    }
}
