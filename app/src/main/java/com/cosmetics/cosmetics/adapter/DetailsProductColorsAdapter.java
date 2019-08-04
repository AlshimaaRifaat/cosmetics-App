package com.cosmetics.cosmetics.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.DetailsProductColorsData;
import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.view.OnClickProductColorView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsProductColorsAdapter  extends RecyclerView.Adapter<DetailsProductColorsAdapter.ViewHolder> {

    Context context;
    private List<DetailsProductColorsData> detailsProductColorsDataList=new ArrayList<>();
    int row_index=0;
   OnClickProductColorView onClickProductColorView;
    public DetailsProductColorsAdapter(Context context, List<DetailsProductColorsData> detailsProductColorsDataList) {
        this.context = context;
        this.detailsProductColorsDataList = detailsProductColorsDataList;
    }

    @NonNull
    @Override
    public DetailsProductColorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_details_product_colors, parent, false);
        return new DetailsProductColorsAdapter.ViewHolder(view);
    }
    public void onClickProductColor(OnClickProductColorView onClickProductColorView)
    {
        this.onClickProductColorView=onClickProductColorView;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsProductColorsAdapter.ViewHolder holder, final int position) {
       // holder.circleImageView.setCircleBackgroundColor(Color.parseColor(detailsProductColorsDataList.get(position).getHashColor()));
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{
                200, 200, 200, 200,
                200, 200, 200, 200}, null, null);

        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(Color.parseColor(detailsProductColorsDataList.get(position).getHashColor()));
        holder.circleImageView.setBackground(shapeDrawable);

        if(row_index==position)
        {
            holder.relativeLayout.setBackground(context.getResources().getDrawable(R.drawable.circle_img));
        }else
        {
            holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                onClickProductColorView.showOnClickProductColorResult(detailsProductColorsDataList.get(position));
                notifyDataSetChanged();
            }
        });


    }


    public int getItemCount() {
        return detailsProductColorsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circle_img);
            relativeLayout=itemView.findViewById(R.id.rel_around_circle);
        }
    }

}
