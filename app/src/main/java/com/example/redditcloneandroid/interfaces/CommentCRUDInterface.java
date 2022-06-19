package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.CommentDto;
import com.example.redditcloneandroid.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentCRUDInterface {

    @GET("comment")
    Call<List<Comment>> getAll();

    @GET("comment/{id}")
    Call<Comment> getOne(@Path("id") int id);

    @POST("comment")
    Call<Comment> create(@Body CommentDto dto);

    @PUT("comment/{id}")
    Call<Comment> edit(@Path("id") int id, @Body CommentDto dto);

    @DELETE("comment/{id}")
    Call<Comment> delete(@Path("id") int id);
}
