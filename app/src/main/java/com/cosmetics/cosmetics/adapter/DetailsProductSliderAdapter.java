package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.HomeSliderData;

import java.util.ArrayList;
import java.util.List;

public class DetailsProductSliderAdapter extends RecyclerView.Adapter<DetailsProductSliderAdapter.ViewHolder> {

    Context context;
    private List<DetailsProductSliderData> detailsProductSliderData=new ArrayList<>();

    public DetailsProductSliderAdapter(Context context, List<DetailsProductSliderData> detailsProductSliderData) {
        this.context = context;
        this.detailsProductSliderData = detailsProductSliderData;
    }

    @NonNull
    @Override
    public DetailsProductSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home_slider, parent, false);
        return new DetailsProductSliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsProductSliderAdapter.ViewHolder holder, int position) {
        Glide.with(context).load("http://titco-industry.com"+detailsProductSliderData.get(position).getImage()).into(holder.imageView);

    }

    public int getItemCount() {

        return detailsProductSliderData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_slider);
        }
    }
}