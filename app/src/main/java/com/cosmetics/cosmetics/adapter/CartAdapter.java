package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.view.DetailsHomeLatestProductsView;
import com.cosmetics.cosmetics.view.PlusQuantityCartView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    private List<GetListCartData> getListCartDataList;
   // DetailsHomeLatestProductsView detailsHomeLatestProductsView;

    PlusQuantityCartView plusQuantityCartView;
    public CartAdapter(Context context, List<GetListCartData> getListCartDataList) {
        this.context = context;
        this.getListCartDataList = getListCartDataList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_cart, parent, false);
        return new CartAdapter.ViewHolder(view);
    }
   public void onClickPlusQuantityCart(PlusQuantityCartView plusQuantityCartView)
    {
        this.plusQuantityCartView=plusQuantityCartView;
    }
    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load("http://titco-industry.com"+getListCartDataList.get(position).getImage()).into(holder.img);
        holder.T_title.setText(getListCartDataList.get(position).getProductName());
        holder.T_quantity.setText("Qty "+String.valueOf(getListCartDataList.get(position).getQuantity()));
        holder.T_unit_price.setText("Price $"+String.valueOf(getListCartDataList.get(position).getUnitPrice()));
        holder.T_total_unit_price.setText("$"+String.valueOf(getListCartDataList.get(position).getTotalUnitPrice()));
        holder.T_changed_quantity.setText(String.valueOf(getListCartDataList.get(position).getQuantity()));
        holder.Btn_plus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusQuantityCartView.showPlusQuantityCart(getListCartDataList.get(position),holder);
            }
        });
    }

    public int getItemCount() {
        return getListCartDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView T_title;
        private TextView T_quantity;
        private TextView T_unit_price;
        private TextView T_total_unit_price;
        private TextView T_changed_quantity;
        private Button Btn_plus_quantity;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            T_title = itemView.findViewById(R.id.T_title);
            T_quantity= itemView.findViewById(R.id.T_product_quantity);
            T_unit_price=itemView.findViewById(R.id.T_unit_price);
            T_total_unit_price=itemView.findViewById(R.id.T_total_unit_price);
            T_changed_quantity= itemView.findViewById(R.id.T_changed_quantity);
            Btn_plus_quantity=itemView.findViewById(R.id.btn_plus);
        }
    }
}