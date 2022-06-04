package com.example.redditcloneandroid.services;

import com.example.redditcloneandroid.model.Objava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ObjavaService {

    @GET("posts/{id}")
    Call<Objava> getPost(@Path("id") Long id);

    @GET("posts")
    Call<List<Objava>> getPosts();

    @POST("posts")
    Call<Objava> addPost(@Body Objava objava);

    @GET("posts/user/{id}")
    Call<List<Objava>> getUsersPosts(@Path("id") Long id);

    @DELETE("posts/{id}")
    Call<Objava> deletePost(@Path("id") Long id);

    @PUT("posts/{id}")
    Call<Objava> updatePost(@Path("id") Long id, @Body Objava objava);
}
