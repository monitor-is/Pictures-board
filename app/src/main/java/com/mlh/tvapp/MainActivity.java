package com.mlh.tvapp;

import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter.OrdersListener {

    List<Order> orders = new ArrayList<>();
    Presenter presenter;
    DataAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter();
        presenter.getOrders(this);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        adapter = new DataAdapter(this, orders);
        recyclerView.setAdapter(adapter);
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
        adapter.orders = ordersList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        //Snackbar.make(recyclerView, "Ошибка сети", Snackbar.LENGTH_LONG).show();
    }
}
