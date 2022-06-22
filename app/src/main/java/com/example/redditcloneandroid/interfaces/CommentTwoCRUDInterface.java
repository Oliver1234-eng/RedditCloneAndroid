package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.CommentTwoDto;
import com.example.redditcloneandroid.model.CommentTwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentTwoCRUDInterface {

    @GET("commentTwo")
    Call<List<CommentTwo>> getAll();

    @GET("commentTwo/{id}")
    Call<CommentTwo> getOne(@Path("id") int id);

    @POST("commentTwo")
    Call<CommentTwo> create(@Body CommentTwoDto dto);

    @PUT("commentTwo/{id}")
    Call<CommentTwo> edit(@Path("id") int id, @Body CommentTwoDto dto);

    @DELETE("commentTwo/{id}")
    Call<CommentTwo> delete(@Path("id") int id);
}
