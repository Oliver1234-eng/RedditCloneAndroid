package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.CommunityDto;
import com.example.redditcloneandroid.dto.UserDto;
import com.example.redditcloneandroid.model.Community;
import com.example.redditcloneandroid.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommunityCRUDInterface {

    @GET("community")
    Call<List<Community>> getAll();

    @GET("community/{id}")
    Call<Community> getOne(@Path("id") int id);

    @POST("community")
    Call<Community> create(@Body CommunityDto dto);

    @PUT("community/{id}")
    Call<Community> edit(@Path("id") int id, @Body CommunityDto dto);

    @DELETE("community/{id}")
    Call<Community> delete(@Path("id") int id);
}
