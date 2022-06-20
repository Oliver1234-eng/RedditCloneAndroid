package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.ReportPostDto;
import com.example.redditcloneandroid.model.ReportPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportPostCRUDInterface {

    @GET("reportPost")
    Call<List<ReportPost>> getAll();

    @GET("reportPost/{id}")
    Call<ReportPost> getOne(@Path("id") int id);

    @POST("reportPost")
    Call<ReportPost> create(@Body ReportPostDto dto);

    @PUT("reportPost/{id}")
    Call<ReportPost> edit(@Path("id") int id, @Body ReportPostDto dto);

    @DELETE("reportPost/{id}")
    Call<ReportPost> delete(@Path("id") int id);
}
