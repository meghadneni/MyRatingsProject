package com.example.moviecatalog.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RatingController
{
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRatingInitialList movieRatingInitialList;

 /*   @Autowired
    private RestTemplate restTemplate;*/

   /* public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
*/
    @GetMapping("/ratings")
    public String allPodcasts(Map<String, Object> model)
    {
        model.put("rating", ratingRepository.findAll() );
        return "podcasts";
    }

/*    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getUniqueRating().getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());
    }*/

 /*   @RequestMapping("/setup")
    public String setupRatingsDb()
    {
        for (Rating m:movieRatingInitialList.rating)
        {
            ratingRepository.save(m);
        }
        return "setup";
    }*/


}
