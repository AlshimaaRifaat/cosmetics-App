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
import com.cosmetics.cosmetics.model.ListFavoriteProductData;
import com.cosmetics.cosmetics.view.DetailsHomeLatestProductsView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    Context context;
    private List<ListFavoriteProductData> listFavoriteProductDataList;
   // DetailsHomeLatestProductsView detailsHomeLatestProductsView;


    public FavoriteAdapter(Context context, List<ListFavoriteProductData> listFavoriteProductDataList) {
        this.context = context;
        this.listFavoriteProductDataList = listFavoriteProductDataList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_favorite, parent, false);
        return new FavoriteAdapter.ViewHolder(view);
    }
   /* public void onClickItemLatestProduct(DetailsHomeLatestProductsView detailsHomeLatestProductsView)
    {
        this.detailsHomeLatestProductsView=detailsHomeLatestProductsView;
    }*/
    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+listFavoriteProductDataList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(listFavoriteProductDataList.get(position).getName());
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsHomeLatestProductsView.showDetailsHomeLatestProducts(latestProductsDataList.get(position));
            }
        });*/
    }

    public int getItemCount() {
        return listFavoriteProductDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private ImageView iconFavoriteBlack;
        private ImageView iconFavoritePink;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.T_title);
            iconFavoriteBlack=itemView.findViewById(R.id.icon_favorite_black);
            iconFavoritePink=itemView.findViewById(R.id.icon_favorite_pink);

        }
    }
}