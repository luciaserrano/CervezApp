package com.example.myfavbeerproject.webservice;

import com.example.myfavbeerproject.models.Beer;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WebServiceClient {

    public static final String BASE_URL = "https://api.punkapi.com/v2/";

    @GET("beers")
    Call<List<Beer>> getBeers();

    // OBTENER UNA CERVEZA POR ID
    @GET("beers/{id}")
    Call<List<Beer>> selectonebeer (@Path("id") int id );

    @GET("beers")
    Call<List<Beer>> searchGetBeers(@QueryMap Map<String, String> options);

    @GET("beers/random")
    Call<List<Beer>> getBeersRandom();

    @GET("beers")
    Call<List<Beer>> getNextPage(@Query("page") int page);
}
