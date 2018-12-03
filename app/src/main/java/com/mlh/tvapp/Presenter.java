package com.mlh.tvapp;

import com.mlh.tvapp.Api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private ApiManager manager = new ApiManager();
    public interface OrdersListener {

        void onSuccess(List<Order> ordersList);

        void onError();

    }

    public void getOrders(final OrdersListener listener) {
        Call<List<Order>> call = manager.getJsonService().getOrders();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                listener.onError();
            }
        });
    }
}
