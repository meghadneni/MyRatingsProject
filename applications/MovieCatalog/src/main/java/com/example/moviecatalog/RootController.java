package com.example.moviecatalog;

import com.example.moviecatalog.movies.Movie;
import com.example.moviecatalog.movies.MoviesBean;
import com.example.moviecatalog.rating.MovieRatingInitialList;
import com.example.moviecatalog.rating.Rating;
import com.example.moviecatalog.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class RootController
{
    private MoviesBean moviesBean;

    private RatingRepository ratingRepository;

    @Value("${ratings.url}")
    private String ratingsURL;

    @Value("${movies.url}")
    private String moviesURL;

    @Autowired
    private RestTemplate restTemplate;

    private MovieRatingInitialList movieRatingInitialList;

    public RootController(MoviesBean moviesBean, RatingRepository ratingRepository) {
        this.moviesBean = moviesBean;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model)
    {
        restTemplate.getForObject(ratingsURL+"/setup",Rating.class);

        List<Rating> all ;

        if(ratingRepository != null)
        for (Rating r: ratingRepository.findAll())
        {
           restTemplate.postForObject(moviesURL+"/add/"+r.getUniqueRating().getMovieId(),null,Movie.class);
        }

        model.put("rating", ratingRepository.findAll());
        model.put("movies", moviesBean.getMovies());


        return "setup";
    }

}
