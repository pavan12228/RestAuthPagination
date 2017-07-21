package com.example.ravinderreddy.pagination;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by Ravinder Reddy on 21-07-2017.
 */

public interface ApiServiceCall
{
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/products")
    public abstract void pagination(@Query("items_per_page") int items_per_page,
                                    @Query("page") int page,
                                    @Query("product") String product,
                                    @Query("q") String q,
                                    @Query("status ") String status,
                                    Callback<JsonObject> callback);
}
