package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.CommentGuestDto;
import com.example.redditcloneandroid.model.CommentGuest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentGuestCRUDInterface {

    @GET("commentGuest")
    Call<List<CommentGuest>> getAll();

    @GET("commentGuest/{id}")
    Call<CommentGuest> getOne(@Path("id") int id);

    @POST("commentGuest")
    Call<CommentGuest> create(@Body CommentGuestDto dto);

    @PUT("commentGuest/{id}")
    Call<CommentGuest> edit(@Path("id") int id, @Body CommentGuestDto dto);

    @DELETE("commentGuest/{id}")
    Call<CommentGuest> delete(@Path("id") int id);
}
