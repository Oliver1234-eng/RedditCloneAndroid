package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.UserDto;
import com.example.redditcloneandroid.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserCRUDInterface {

    @GET("user")
    Call<List<User>> getAll();

    @GET("user/{id}")
    Call<User> getOne(@Path("id") int id);

    @POST("user")
    Call<User> create(@Body UserDto dto);

    @PUT("user/{id}")
    Call<User> edit(@Path("id") int id, @Body UserDto dto);

    @DELETE("user/{id}")
    Call<User> delete(@Path("id") int id);
}
