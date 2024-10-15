package com.Aaron.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 电影类型关系表
 * </p>
 *
 * @author Aaron
 * @since 2024-10-15
 */
@TableName("movie_type_relation")
public class MovieTypeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mTRelationId;

    /**
     * 电影id
     */
    private String movieId;

    /**
     * 电影类型id
     */
    private String movieTypeId;

    public String getmTRelationId() {
        return mTRelationId;
    }

    public void setmTRelationId(String mTRelationId) {
        this.mTRelationId = mTRelationId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(String movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    @Override
    public String toString() {
        return "MovieTypeRelation{" +
            "mTRelationId = " + mTRelationId +
            ", movieId = " + movieId +
            ", movieTypeId = " + movieTypeId +
        "}";
    }
}
