package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.PostDto;
import com.example.redditcloneandroid.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostCRUDInterface {

    @GET("post")
    Call<List<Post>> getAll();

    @GET("post/{id}")
    Call<Post> getOne(@Path("id") int id);

    @POST("post")
    Call<Post> create(@Body PostDto dto);

    @PUT("post/{id}")
    Call<Post> edit(@Path("id") int id, @Body PostDto dto);

    @DELETE("post/{id}")
    Call<Post> delete(@Path("id") int id);
}
