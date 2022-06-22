package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.PostThreeDto;
import com.example.redditcloneandroid.model.PostThree;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostThreeCRUDInterface {

    @GET("postThree")
    Call<List<PostThree>> getAll();

    @GET("postThree/{id}")
    Call<PostThree> getOne(@Path("id") int id);

    @POST("postThree")
    Call<PostThree> create(@Body PostThreeDto dto);

    @PUT("postThree/{id}")
    Call<PostThree> edit(@Path("id") int id, @Body PostThreeDto dto);

    @DELETE("postThree/{id}")
    Call<PostThree> delete(@Path("id") int id);
}
