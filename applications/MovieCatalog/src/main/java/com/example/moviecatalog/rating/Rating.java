package com.example.moviecatalog.rating;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rating implements Serializable {

    @EmbeddedId
    private UniqueRating uniqueRating;

    //private String movieId;
    private int rating;

    public Rating() { }

    public Rating(UniqueRating uniqueRating, int rating) {
        this.uniqueRating = uniqueRating;
        this.rating = rating;
    }

    public UniqueRating getUniqueRating() {
        return uniqueRating;
    }

    public void setUniqueRating(UniqueRating uniqueRating) {
        this.uniqueRating = uniqueRating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
