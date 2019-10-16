package io.sairam.movieinfoservice.resources;

import io.sairam.movieinfoservice.models.Movie;
import io.sairam.movieinfoservice.models.MovieRepository;
import io.sairam.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId)
    {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }


    @PostMapping("/add/{movieId}")
    public Movie addMovie(@PathVariable("movieId") String movieId)
    {
        Movie movie=  getMovieInfo(movieId);
        movieRepository.save(movie);
        return movie;
    }



}
