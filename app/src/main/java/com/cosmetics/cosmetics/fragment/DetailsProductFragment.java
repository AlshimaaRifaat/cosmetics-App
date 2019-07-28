package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.adapter.DetailsProductSliderAdapter;
import com.cosmetics.cosmetics.adapter.FeatureProductsAdapter;
import com.cosmetics.cosmetics.adapter.HomeSliderAdapter;
import com.cosmetics.cosmetics.adapter.LatestProductsAdapter;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.viewmodel.DetailsProductViewModel;
import com.cosmetics.cosmetics.viewmodel.LatestProductsViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsProductFragment extends Fragment {
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

    @BindView(R.id.recycler_slider)
    RecyclerView recycler_slider;

    Unbinder unbinder;

    LatestProductsData latestProductsData;
    DetailsProductViewModel detailsProductViewModel;
    DetailsProductSliderAdapter detailsProductSliderAdapter;

    Bundle bundle;

    public DetailsProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_product, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDetailsProductSlider();
        bundle = this.getArguments();
        if (bundle != null) {
            latestProductsData = bundle.getParcelable("LatestProductsItem");
            T_title.setText(latestProductsData.getTitle());
            T_price.setText("$" + String.valueOf(latestProductsData.getPriceGeneral()));
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

    public void getDetailsProductSlider() {
        detailsProductViewModel = ViewModelProviders.of(this).get(DetailsProductViewModel.class);
        //check "ar"
        detailsProductViewModel.getDetailsProductSlider("1", getContext()).observe(this, new Observer<List<DetailsProductSliderData>>() {
            @Override
            public void onChanged(@Nullable List<DetailsProductSliderData> detailsProductSliderData) {
                detailsProductSliderAdapter = new DetailsProductSliderAdapter(getActivity(),detailsProductSliderData);
                recycler_slider.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recycler_slider.setAdapter(detailsProductSliderAdapter);
            }
        });

    }
}