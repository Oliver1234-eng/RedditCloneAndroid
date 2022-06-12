package com.example.redditcloneandroid.retrofit;

import com.example.redditcloneandroid.model.Korisnik;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KorisnikApi {

    @GET("/user/get-all")
    Call<List<Korisnik>> getAllUsers();

    @POST("/user/save")
    Call<Korisnik> save(@Body Korisnik korisnik);
}
