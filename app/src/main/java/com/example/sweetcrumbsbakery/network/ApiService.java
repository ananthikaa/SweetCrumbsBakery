package com.example.sweetcrumbsbakery.network;

import com.example.sweetcrumbsbakery.model.BillResponse;
import com.example.sweetcrumbsbakery.model.CartItem;
import com.example.sweetcrumbsbakery.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("products")
    Call<List<Product>> getProducts();

    @POST("calculate-bill")
    Call<BillResponse> calculateBill(@Body Map<String, List<CartItem>> request);
}