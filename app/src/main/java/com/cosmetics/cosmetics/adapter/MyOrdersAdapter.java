package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.MyOrdersData;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    Context context;
    private List<MyOrdersData> myOrdersDataList;
    // DetailsHomeLatestProductsView detailsHomeLatestProductsView;

    //PlusQuantityCartView plusQuantityCartView;


    public MyOrdersAdapter(Context context, List<MyOrdersData> myOrdersDataList) {
        this.context = context;
        this.myOrdersDataList = myOrdersDataList;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_my_orders, parent, false);
        return new MyOrdersAdapter.ViewHolder(view);
    }
    /*public void onClickPlusQuantityCart(PlusQuantityCartView plusQuantityCartView)
    {
        this.plusQuantityCartView=plusQuantityCartView;
    }*/


    @Override
    public void onBindViewHolder(@NonNull final MyOrdersAdapter.ViewHolder holder, final int position) {

        holder.T_order_id.setText("#"+String.valueOf(myOrdersDataList.get(position).getOrderId()));
        String orderStatusValue=String.valueOf(myOrdersDataList.get(position).getOrderStat());

        if (orderStatusValue.equals("1"))
        {
            holder.T_order_status.setText(context.getResources().getString(R.string.InProgress));
        }else if (orderStatusValue.equals("2"))
        {
            holder.T_order_status.setText(context.getResources().getString(R.string.Delivered));
        }else if (orderStatusValue.equals("3"))
        {
            holder.T_order_status.setText(context.getResources().getString(R.string.Canceled));
        }

        holder.T_date.setText(String.valueOf(myOrdersDataList.get(position).getCreatedAt()));
        holder.T_price.setText("$ "+String.valueOf(myOrdersDataList.get(position).getOrderTotalPrice()));

        /*holder.Btn_plus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*int countt=Integer.parseInt(holder.T_changed_quantity.getText().toString());
                countt++;
                holder.T_changed_quantity.setText(String.valueOf(countt));
                holder.T_total_unit_price.setText(Double.toString(Double.parseDouble(holder.T_quantity.getText().toString())*Double.parseDouble(holder.T_unit_price.getText().toString())));*//*
                plusQuantityCartView.showPlusQuantityCart(getListCartDataList.get(position));
            }
        });*/


    }

    public int getItemCount() {
        return myOrdersDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView T_order_id;
        private TextView T_price;
        private TextView T_order_status;
        private TextView T_date;
        public ViewHolder(View itemView) {
            super(itemView);
            T_order_id = itemView.findViewById(R.id.T_order_id);
            T_price= itemView.findViewById(R.id.T_price);
            T_order_status= itemView.findViewById(R.id.T_order_status);
            T_date= itemView.findViewById(R.id.T_date);

        }
    }
}