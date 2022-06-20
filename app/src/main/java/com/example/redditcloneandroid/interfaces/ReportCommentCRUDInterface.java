package com.example.redditcloneandroid.interfaces;

import com.example.redditcloneandroid.dto.ReportCommentDto;
import com.example.redditcloneandroid.model.ReportComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportCommentCRUDInterface {

    @GET("reportComment")
    Call<List<ReportComment>> getAll();

    @GET("reportComment/{id}")
    Call<ReportComment> getOne(@Path("id") int id);

    @POST("reportComment")
    Call<ReportComment> create(@Body ReportCommentDto dto);

    @PUT("reportComment/{id}")
    Call<ReportComment> edit(@Path("id") int id, @Body ReportCommentDto dto);

    @DELETE("reportComment/{id}")
    Call<ReportComment> delete(@Path("id") int id);
}
