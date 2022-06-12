package com.example.redditcloneandroid.retrofit;

import com.example.redditcloneandroid.model.Objava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ObjavaApi {

    @GET("/post/get-all")
    Call<List<Objava>> getAllPosts();

    @POST("/post/save")
    Call<Objava> save(@Body Objava objava);
}
