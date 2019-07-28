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
import com.cosmetics.cosmetics.model.ProductsData;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    private List<ProductsData> productsDataList;


    public ProductsAdapter(Context context, List<ProductsData> productsDataList) {
        this.context = context;
        this.productsDataList = productsDataList;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products, parent, false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+productsDataList.get(position).getImage()).into(holder.imageView);
        holder.T_title.setText(productsDataList.get(position).getTitle());
        holder.T_price.setText(String.valueOf(productsDataList.get(position).getPriceGeneral()));
    }

    public int getItemCount() {
        return productsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView T_title;
        private TextView T_price;



        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            T_title = itemView.findViewById(R.id.T_title);
            T_price=itemView.findViewById(R.id.T_price);

        }
    }
}