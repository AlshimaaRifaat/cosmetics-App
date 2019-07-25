package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.adapter.ProductCategoryAdapter;
import com.cosmetics.cosmetics.adapter.ProductsAdapter;
import com.cosmetics.cosmetics.model.ProductCategoryData;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.viewmodel.ProductCategoryViewModel;
import com.cosmetics.cosmetics.viewmodel.ProductsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    ProductsViewModel productsViewModel;

    @BindView(R.id.recycler_products)
    RecyclerView recycler_products;
    ProductsAdapter productsAdapter;

    Unbinder unbinder;

    Bundle bundle;
    ProductCategoryData productCategoryData;
    String BrandId;
    public ProductsFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_products, container, false);
        unbinder = ButterKnife.bind(this, view);
        bundle=this.getArguments();
        if(bundle!=null)
        {
            productCategoryData=bundle.getParcelable("ProductBrandItem");
            BrandId=String.valueOf(productCategoryData.getId());
        }
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        //check id's
        productsViewModel.getProducts(BrandId,"","en", getContext()).observe(this, new Observer<List<ProductsData>>() {
            @Override
            public void onChanged(@Nullable List<ProductsData> productsData) {
                productsAdapter = new ProductsAdapter(getActivity(), productsData);
                // productCategoryAdapter.onClickItemLatestProduct(HomeFragment.this);
                recycler_products.setLayoutManager(new GridLayoutManager(getContext(),2));
                recycler_products.setAdapter(productsAdapter);
            }
        });
        return view;
    }

}
