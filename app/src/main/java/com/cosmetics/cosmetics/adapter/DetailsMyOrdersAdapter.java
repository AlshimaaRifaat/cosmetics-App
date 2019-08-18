package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.CommentsData;
import com.cosmetics.cosmetics.model.MyOrdersDetailsData;
import com.cosmetics.cosmetics.view.DetailsProductView;
import com.cosmetics.cosmetics.view.OnClickRateProductView;

import java.util.List;

public class DetailsMyOrdersAdapter extends RecyclerView.Adapter<DetailsMyOrdersAdapter.ViewHolder> {

    Context context;
    private List<MyOrdersDetailsData> myOrdersDetailsDataList;

    OnClickRateProductView onClickRateProductView;


    public DetailsMyOrdersAdapter(Context context, List<MyOrdersDetailsData> myOrdersDetailsDataList) {
        this.context = context;
        this.myOrdersDetailsDataList = myOrdersDetailsDataList;
    }

    @NonNull
    @Override
    public DetailsMyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_details_my_orders_item, parent, false);
        return new DetailsMyOrdersAdapter.ViewHolder(view);
    }
    public void onClickRateProduct(OnClickRateProductView onClickRateProductView)
     {
         this.onClickRateProductView=onClickRateProductView;
     }
    @Override
    public void onBindViewHolder(@NonNull DetailsMyOrdersAdapter.ViewHolder holder, final int position) {

        holder.T_product_quantity.setText(myOrdersDetailsDataList.get(position).getProductQuantity()+" "+myOrdersDetailsDataList.get(position).getProductName());
        holder.T_price.setText(String.valueOf(myOrdersDetailsDataList.get(position).getProductPrice()));

     holder.T_rate_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRateProductView.showOnClickRateProductResult(myOrdersDetailsDataList.get(position));
            }
        });
    }

    public int getItemCount() {
        return myOrdersDetailsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView T_product_quantity;
        private TextView T_price;
        private TextView T_rate_product;



        public ViewHolder(View itemView) {
            super(itemView);

            T_product_quantity=itemView.findViewById(R.id.T_product_quantity);
            T_price= itemView.findViewById(R.id.T_price);
            T_rate_product= itemView.findViewById(R.id.T_rate_product);

        }
    }
}

