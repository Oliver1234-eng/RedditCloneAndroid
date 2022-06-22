package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.PostOneDto;
import com.example.redditcloneandroid.model.PostOne;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostOneCRUDInterface {

    @GET("postOne")
    Call<List<PostOne>> getAll();

    @GET("postOne/{id}")
    Call<PostOne> getOne(@Path("id") int id);

    @POST("postOne")
    Call<PostOne> create(@Body PostOneDto dto);

    @PUT("postOne/{id}")
    Call<PostOne> edit(@Path("id") int id, @Body PostOneDto dto);

    @DELETE("postOne/{id}")
    Call<PostOne> delete(@Path("id") int id);
}
