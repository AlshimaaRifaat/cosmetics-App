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
import com.cosmetics.cosmetics.model.ProductCategoryData;
import com.cosmetics.cosmetics.view.DetailsProductBrandView;
import com.cosmetics.cosmetics.view.DetailsProductCategoryView;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    Context context;
     List<ProductCategoryData> productCategoryDataList;

    DetailsProductCategoryView detailsProductCategoryView;


    public ProductCategoryAdapter(Context context, List<ProductCategoryData> productCategoryDataList) {
        this.context = context;
        this.productCategoryDataList = productCategoryDataList;
    }

    @NonNull
    @Override
    public ProductCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products_category_brand, parent, false);
        return new ProductCategoryAdapter.ViewHolder(view);
    }

   public void onClickItemProductCategory(DetailsProductCategoryView detailsProductCategoryView)
    {
        this.detailsProductCategoryView=detailsProductCategoryView;
    }
    @Override
    public void onBindViewHolder(@NonNull ProductCategoryAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+productCategoryDataList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(productCategoryDataList.get(position).getTitle());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsProductCategoryView.showDetailsProductCategory(productCategoryDataList.get(position));
            }
        });

    }

    public int getItemCount() {
        return productCategoryDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product);
            textView = itemView.findViewById(R.id.T_title);
        }
    }
}