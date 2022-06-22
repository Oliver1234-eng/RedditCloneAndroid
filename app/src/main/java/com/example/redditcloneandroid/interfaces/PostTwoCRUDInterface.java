package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.PostTwoDto;
import com.example.redditcloneandroid.model.PostTwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostTwoCRUDInterface {

    @GET("postTwo")
    Call<List<PostTwo>> getAll();

    @GET("postTwo/{id}")
    Call<PostTwo> getOne(@Path("id") int id);

    @POST("postTwo")
    Call<PostTwo> create(@Body PostTwoDto dto);

    @PUT("postTwo/{id}")
    Call<PostTwo> edit(@Path("id") int id, @Body PostTwoDto dto);

    @DELETE("postTwo/{id}")
    Call<PostTwo> delete(@Path("id") int id);
}
