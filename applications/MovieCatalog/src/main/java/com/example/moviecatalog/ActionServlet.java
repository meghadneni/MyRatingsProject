/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.moviecatalog;

import com.example.moviecatalog.movies.Movie;
import com.example.moviecatalog.movies.MoviesBean;
import com.example.moviecatalog.rating.Rating;
import com.example.moviecatalog.rating.UniqueRating;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @version $Revision$ $Date$
 */
@Component
public class ActionServlet extends HttpServlet
{
    @Value("${ratings.url}")
    private String ratingsURL;

    @Value("${movies.url}")
    private String moviesURL;

    private static final long serialVersionUID = -5832176047021911038L;

    public static int PAGE_SIZE = 5;

    @Autowired
    private RestTemplate restTemplate;

    @EJB
    private MoviesBean moviesBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if ("Add".equals(action))
        {
            String movieId = request.getParameter("movieId");
            String userId= request.getParameter("userId");
            int userRating = Integer.parseInt(request.getParameter("userRating"));
            /*
            String title = request.getParameter("title");
            String director = request.getParameter("director");
            String genre = request.getParameter("genre");
            int year = Integer.parseInt(request.getParameter("year")); */

            Rating rating = new Rating(new UniqueRating(userId,movieId),userRating);

            restTemplate.postForObject(ratingsURL+"/add",rating,Rating.class);


            restTemplate.postForObject(moviesURL+"/add/"+rating.getUniqueRating().getMovieId(),null,Movie.class);

            response.sendRedirect("moviefun");
            return;

        }
        else if ("Remove".equals(action))
        {

            String[] ids = request.getParameterValues("id");
            for (String id : ids) {
                moviesBean.deleteMovieId(new String(id));
            }

            response.sendRedirect("moviefun");
            return;

        } else {
            String key = request.getParameter("key");
            String field = request.getParameter("field");

            int count = 0;

            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
                count = moviesBean.countAll();
                key = "";
                field = "";
            } else {
                count = moviesBean.count(field, key);
            }

            int page = 1;

            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
            }

            int pageCount = (count / PAGE_SIZE);
            if (pageCount == 0 || count % PAGE_SIZE != 0) {
                pageCount++;
            }

            if (page < 1) {
                page = 1;
            }

            if (page > pageCount) {
                page = pageCount;
            }

            int start = (page - 1) * PAGE_SIZE;
            List<Movie> range;

            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(field)) {
                range = moviesBean.findAll(start, PAGE_SIZE);
            } else {
                range = moviesBean.findRange(field, key, start, PAGE_SIZE);
            }

            int end = start + range.size();

            request.setAttribute("count", count);
            request.setAttribute("start", start + 1);
            request.setAttribute("end", end);
            request.setAttribute("page", page);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("movies", range);
            request.setAttribute("key", key);
            request.setAttribute("field", field);
        }

        request.getRequestDispatcher("WEB-INF/moviefun.jsp").forward(request, response);
    }

}