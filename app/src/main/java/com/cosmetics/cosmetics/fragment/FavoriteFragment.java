package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.CartAdapter;
import com.cosmetics.cosmetics.adapter.FavoriteAdapter;
import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.ListFavoriteProductData;
import com.cosmetics.cosmetics.viewmodel.CartViewModel;
import com.cosmetics.cosmetics.viewmodel.ListFavoriteProductViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    ListFavoriteProductViewModel listFavoriteProductViewModel;

    @BindView(R.id.recycler_favorite)
    RecyclerView recycler_favorite;
    FavoriteAdapter favoriteAdapter;
    String userTokenValue;

    Unbinder unbinder;
    View view;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        unbinder = ButterKnife.bind(this, view);
        userTokenValue = SharedPrefManager.getInstance(getContext()).getUserToken();
        listFavoriteProductViewModel = ViewModelProviders.of(this).get(ListFavoriteProductViewModel.class);
        performGettingListFavoriteProduct();

        return view;
    }

    private void performGettingListFavoriteProduct() {
        //lang en
        listFavoriteProductViewModel.getFavoriteProduct("en", userTokenValue, getContext()).observe(this, new Observer<List<ListFavoriteProductData>>() {
            @Override
            public void onChanged(@Nullable List<ListFavoriteProductData> listFavoriteProductData) {
                if (listFavoriteProductData != null) {
                    favoriteAdapter = new FavoriteAdapter(getActivity(), listFavoriteProductData);
                    // cartAdapter.onClickPlusQuantityCart( CartFragment. this);
                    recycler_favorite.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recycler_favorite.setAdapter(favoriteAdapter);
                }
            }
        });
    }
}
