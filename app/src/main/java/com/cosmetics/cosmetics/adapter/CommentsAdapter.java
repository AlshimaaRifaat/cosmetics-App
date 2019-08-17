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
import com.cosmetics.cosmetics.model.CommentsData;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.view.DetailsProductView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    Context context;
    private List<CommentsData> commentsDataList;

   // DetailsProductView detailsProductView;


    public CommentsAdapter(Context context, List<CommentsData> commentsDataList) {
        this.context = context;
        this.commentsDataList = commentsDataList;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }
   /* public void onClickItemProduct(DetailsProductView detailsProductView)
    {
        this.detailsProductView=detailsProductView;
    }*/
    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, final int position) {

        holder.T_user_name.setText(commentsDataList.get(position).getUserName());
        holder.T_description.setText(String.valueOf(commentsDataList.get(position).getRateComment()));

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsProductView.showDetailsProduct(productsDataList.get(position));
            }
        });*/
    }

    public int getItemCount() {
        return commentsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView T_user_name;
        private TextView T_description;



        public ViewHolder(View itemView) {
            super(itemView);

            T_user_name = itemView.findViewById(R.id.T_user_name);
            T_description=itemView.findViewById(R.id.T_description);

        }
    }
}
