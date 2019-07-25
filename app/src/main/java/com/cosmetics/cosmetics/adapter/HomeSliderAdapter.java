package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.model.LatestProductsData;

import java.util.ArrayList;
import java.util.List;

public class HomeSliderAdapter  extends RecyclerView.Adapter<HomeSliderAdapter.ViewHolder> {

    Context context;
    private List<HomeSliderData> homeSliderDataList=new ArrayList<>();

    public HomeSliderAdapter(Context context, List<HomeSliderData> homeSliderDataList) {
        this.context = context;
        this.homeSliderDataList = homeSliderDataList;
    }

    @NonNull
    @Override
    public HomeSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home_slider, parent, false);
        return new HomeSliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSliderAdapter.ViewHolder holder, int position) {
        Glide.with(context).load("http://style-cosmetics.com"+homeSliderDataList.get(position).getImage()).into(holder.imageView);

    }

    public int getItemCount() {

            return homeSliderDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_slider);
        }
    }
}