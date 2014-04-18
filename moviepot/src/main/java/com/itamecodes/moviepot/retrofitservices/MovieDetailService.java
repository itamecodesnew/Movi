package com.itamecodes.moviepot.retrofitservices;


import com.itamecodes.moviepot.data.model.Movie;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by vivek on 3/8/14.
 */
public interface MovieDetailService {
    @GET("/{movieid}")
    Observable<Movie> getMovieDetails(@Path("movieid") String movieId, @Query("api_key") String apiKey,@Query("append_to_response") String appendToResponse);

}
