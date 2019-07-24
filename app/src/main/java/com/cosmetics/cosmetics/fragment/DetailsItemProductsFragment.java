package com.cosmetics.cosmetics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.model.LatestProductsData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsItemProductsFragment extends Fragment {
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.T_title)
    TextView T_title;



    @BindView(R.id.T_price)
    TextView T_price;

    @BindView(R.id.T_description)
    TextView T_description;

    @BindView(R.id.T_label_title)
    TextView T_label_title;


    Unbinder unbinder;

    LatestProductsData latestProductsData;
    Bundle bundle;
    public DetailsItemProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details_item_products, container, false);
        unbinder= ButterKnife.bind(this,view);
        bundle=this.getArguments();
        if(bundle!=null)
        {
            latestProductsData=bundle.getParcelable("LatestProductsItem");
            T_title.setText(latestProductsData.getTitle());
            T_price.setText("$"+String.valueOf(latestProductsData.getPriceGeneral()));
            T_description.setText(latestProductsData.getDescription());
            T_label_title.setText(latestProductsData.getTitle());
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getContext(), String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
