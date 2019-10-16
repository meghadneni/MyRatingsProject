package com.example.moviecatalog.rating;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UniqueRating implements Serializable
{
    private String userId;

    private String movieId;

    public UniqueRating()
    {}

    public UniqueRating(String userId, String movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

}
