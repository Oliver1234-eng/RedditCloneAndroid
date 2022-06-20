package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.FlairDto;
import com.example.redditcloneandroid.model.Flair;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FlairCRUDInterface {

    @GET("flair")
    Call<List<Flair>> getAll();

    @GET("flair/{id}")
    Call<Flair> getOne(@Path("id") int id);

    @POST("flair")
    Call<Flair> create(@Body FlairDto dto);

    @PUT("flair/{id}")
    Call<Flair> edit(@Path("id") int id, @Body FlairDto dto);

    @DELETE("flair/{id}")
    Call<Flair> delete(@Path("id") int id);
}
