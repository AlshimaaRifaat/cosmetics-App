package com.cosmetics.cosmetics.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cosmetics.cosmetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    @BindView(R.id.btn_category) Button btnCategory;
    @BindView(R.id.btn_brands)Button btnBrands;
    private Unbinder unbinder;

    public ProductsFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_products, container, false);
        unbinder= ButterKnife.bind(this,view);
        chooseType();
        return view;
    }

    private void chooseType() {
        btnBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnBrands.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_pink));
                btnBrands.setTextColor(Color.rgb(240, 245, 251));
                btnCategory.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_white));
                btnCategory.setTextColor(Color.rgb(	113, 113, 113)	);
                /*TripStatus();
                clickAddTrip();*/

            }
        });
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCategory.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_pink));
                btnCategory.setTextColor(Color.rgb(240, 245, 251));
                btnBrands.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_white));
                btnBrands.setTextColor(Color.rgb(  	113, 113, 113));
                /*TripStatusMgdwla();
                clickAddTrip();*/

                // reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
    }

}
