package com.mlh.tvapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter.OrdersListener {

    List<Order> readyOrders = new ArrayList<>();
    List<Order> notReadyOrders = new ArrayList<>();
    Presenter presenter;
    DataAdapter notReadyAdapter;
    DataAdapter readyAdapter;
    RecyclerView rvNotReady;
    RecyclerView rvReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter();
        presenter.getOrders(this);
        rvNotReady = findViewById(R.id.rv_not_ready);
        rvNotReady.setLayoutManager(new GridLayoutManager(this, 3));
        notReadyAdapter = new DataAdapter(this, notReadyOrders);
        rvNotReady.setAdapter(notReadyAdapter);

        rvReady = findViewById(R.id.rv_ready);
        rvReady.setLayoutManager(new GridLayoutManager(this, 3));
        readyAdapter = new DataAdapter(this, readyOrders);
        rvReady.setAdapter(readyAdapter);

        CountDownTimer timer = new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                presenter.getOrders(MainActivity.this);
                this.start();
            }
        };
        timer.start();
    }

    @Override
    public void onSuccess(List<Order> ordersList) {
        readyOrders.clear();
        notReadyOrders.clear();

        for (Order order: ordersList) {
            if (order.status == 0) {
                notReadyOrders.add(order);
            }
            if (order.status == 1) {
                readyOrders.add(order);
            }
        }

        notReadyAdapter.orders = notReadyOrders;
        notReadyAdapter.notifyDataSetChanged();

        readyAdapter.orders = readyOrders;
        readyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        //Snackbar.make(rvNotReady, "Ошибка сети", Snackbar.LENGTH_LONG).show();
    }
}
