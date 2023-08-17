package com.iti.finalproject.network;

import com.iti.finalproject.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("movie/top_rated?api_key=1bb745726fcd33471de3440c34c17091")
    public Call<Movie> getMovies();
}
