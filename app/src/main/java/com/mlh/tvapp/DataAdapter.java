package com.mlh.tvapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    public List<Order> orders;
    private Context context;

    DataAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
//        Picasso.get()
//                .load("http://square.github.io/picasso/static/sample.png")
//                .into(holder.imageView);
        new DownloadImageTask(holder.imageView).execute(order.image);
        holder.tvId.setText(order.number);
        if (order.status == 1)
            holder.tvId.setTextColor(Color.parseColor("#006600"));
        holder.tvTime.setText(order.timestamp);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvId, tvTime;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            tvId = view.findViewById(R.id.tv_id);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }
}
