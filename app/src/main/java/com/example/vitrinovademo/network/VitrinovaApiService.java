package com.example.vitrinovademo.network;

import com.example.vitrinovademo.model.ResponseModel;
import com.example.vitrinovademo.model.Vitrin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VitrinovaApiService {

    @GET("discover")
    Call<List<ResponseModel>> getVitrinovaProduct();
}
