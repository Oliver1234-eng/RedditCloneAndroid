package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.CommentThreeDto;
import com.example.redditcloneandroid.model.CommentThree;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentThreeCRUDInterface {

    @GET("commentThree")
    Call<List<CommentThree>> getAll();

    @GET("commentThree/{id}")
    Call<CommentThree> getOne(@Path("id") int id);

    @POST("commentThree")
    Call<CommentThree> create(@Body CommentThreeDto dto);

    @PUT("commentThree/{id}")
    Call<CommentThree> edit(@Path("id") int id, @Body CommentThreeDto dto);

    @DELETE("commentThree/{id}")
    Call<CommentThree> delete(@Path("id") int id);
}
