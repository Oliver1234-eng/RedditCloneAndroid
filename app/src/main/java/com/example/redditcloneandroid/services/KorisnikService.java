package com.example.redditcloneandroid.services;

import com.example.redditcloneandroid.model.Korisnik;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface KorisnikService {

    @POST("login")
    Call<Korisnik> login(@Body Korisnik korisnik);

    @GET("users/{username}")
    Call<Korisnik> findByUsername(@Path("username") String username);

    @POST("registerUser")
    Call<Korisnik> register(@Body Korisnik korisnik);

    @GET("users")
    Call<List<Korisnik>> getUsers();
}
