package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UniqueRating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface RatingsRepository extends CrudRepository<Rating, UniqueRating>
{
    Optional<Rating> findById(UniqueRating id);
    /*
    Optional<List<Rating>> findByUserId(String userId);*/

}
