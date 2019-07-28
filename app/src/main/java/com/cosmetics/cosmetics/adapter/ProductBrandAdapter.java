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

import java.util.List;

public class ProductBrandAdapter extends RecyclerView.Adapter<ProductBrandAdapter.ViewHolder> {

    Context context;
    private List<ProductCategoryData> productCategoryDataList;
    DetailsProductBrandView detailsProductBrandView;


    public ProductBrandAdapter(Context context, List<ProductCategoryData> productCategoryDataList) {
        this.context = context;
        this.productCategoryDataList = productCategoryDataList;
    }

    @NonNull
    @Override
    public ProductBrandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products_category_brand, parent, false);
        return new ProductBrandAdapter.ViewHolder(view);
    }
    public void onClickItemProductBrand(DetailsProductBrandView detailsProductBrandView)
     {
         this.detailsProductBrandView=detailsProductBrandView;
     }
    @Override
    public void onBindViewHolder(@NonNull ProductBrandAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+productCategoryDataList.get(position).getImage()).into(holder.imageView);
        holder.textView.setText(productCategoryDataList.get(position).getTitle());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsProductBrandView.showDetailsProductBrand(productCategoryDataList.get(position));
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