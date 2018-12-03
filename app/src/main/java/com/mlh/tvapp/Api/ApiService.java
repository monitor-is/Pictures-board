package com.mlh.tvapp.Api;

import com.mlh.tvapp.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("order/getOrders")
    Call<List<Order>> getOrders();

}
