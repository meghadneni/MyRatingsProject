package io.javabrains.ratingsdataservice.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MovieRatingInitialList {

     public List<Rating> rating = Arrays.asList(new Rating(new UniqueRating("1111", "256040"), 8),
            new Rating(new UniqueRating("1111", "350312"), 9),
            new Rating(new UniqueRating("1111", "566914"), 5),
            new Rating(new UniqueRating("1111", "176590"), 7),
            new Rating(new UniqueRating("2222", "63376"), 7),
            new Rating(new UniqueRating("2222", "611754"), 8),
            new Rating(new UniqueRating("2222", "571610"), 5),
            new Rating(new UniqueRating("2222", "592898"), 9),
            new Rating(new UniqueRating("3333", "530154"), 8),
            new Rating(new UniqueRating("3333", "456570"), 7),
            new Rating(new UniqueRating("3333", "103243"), 7),
            new Rating(new UniqueRating("3333", "256040"), 7));



}
