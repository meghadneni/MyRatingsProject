package com.example.moviecatalog.rating;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, UniqueRating>
{
/*
    Optional<Rating> findById(UniqueRating uniqueRating);*/
}
