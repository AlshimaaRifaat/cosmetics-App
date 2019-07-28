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
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.view.DetailsHomeLatestProductsView;

import java.util.List;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.ViewHolder> {

    Context context;
    private List<LatestProductsData> latestProductsDataList;
    DetailsHomeLatestProductsView detailsHomeLatestProductsView;

    public LatestProductsAdapter(Context context, List<LatestProductsData> latestProductsDataList) {
        this.context = context;
        this.latestProductsDataList = latestProductsDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home_latest_products, parent, false);
        return new ViewHolder(view);
    }
    public void onClickItemLatestProduct(DetailsHomeLatestProductsView detailsHomeLatestProductsView)
    {
        this.detailsHomeLatestProductsView=detailsHomeLatestProductsView;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+latestProductsDataList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(latestProductsDataList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsHomeLatestProductsView.showDetailsHomeLatestProducts(latestProductsDataList.get(position));
            }
        });
    }

    public int getItemCount() {
        return latestProductsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageView;
       private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.T_title);
        }
    }
}