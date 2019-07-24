package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.adapter.LatestProductsAdapter;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.viewmodel.LatestProductsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.icon_cart)
    ImageView iconCart;
    Unbinder unbinder;

    @BindView(R.id.recycler_latest_products)
    RecyclerView recycler_latest_products;

    LatestProductsViewModel latestProductsViewModel;
    LatestProductsAdapter latestProductsAdapter;

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        unbinder= ButterKnife.bind(this,view);
        getLatestProducts();
        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.relative_home,new CartFragment())
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }

    public void getLatestProducts() {
        latestProductsViewModel = ViewModelProviders.of(this).get(LatestProductsViewModel.class);
        //check "ar"
        latestProductsViewModel.getLatestProducts("ar",getContext()).observe(this, new Observer<List<LatestProductsData>>() {
            @Override
            public void onChanged(@Nullable List<LatestProductsData> latestProductsData) {
                latestProductsAdapter = new LatestProductsAdapter(getActivity(),latestProductsData);
                recycler_latest_products.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recycler_latest_products.setAdapter(latestProductsAdapter);
            }
        });
    }
}
