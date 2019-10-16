package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.model.MovieRatingInitialList;
import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UniqueRating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource
{
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private MovieRatingInitialList movieRatingInitialList;

    @RequestMapping("/{userId}/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId,@PathVariable("userId") String userId)
    {
        Optional<Rating> r = ratingsRepository.findById(new UniqueRating(userId,movieId));
        if (r.isPresent()) {
            Rating rr = r.get();
            return rr;
        }
         else
            return null;
    }


    @RequestMapping("/setup")
    public void setupInitialRatings()
    {
        for (Rating m:movieRatingInitialList.rating) {
            ratingsRepository.save(m);
        };
    }


    @PostMapping(path="/add")
    public void addRatings(@RequestBody Rating rating)
    {
        ratingsRepository.save(rating);
    }


   @PostMapping("/delete")
    public void deleteRating(@RequestBody UniqueRating uniqueRating)
   {
       ratingsRepository.deleteById(uniqueRating);
   }


}
