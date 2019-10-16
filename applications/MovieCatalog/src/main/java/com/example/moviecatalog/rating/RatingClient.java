package com.example.moviecatalog.rating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class RatingClient
{

 @Value("${ratings.url}")
 private String ratingsURL;

 @Autowired
 private RestTemplate restTemplate;
 private static final Logger log = LoggerFactory.getLogger(RatingClient.class);


 public void addRating(Rating rating)
 {
  restTemplate.postForEntity(ratingsURL+"/add",rating,Rating.class);
 }

}
