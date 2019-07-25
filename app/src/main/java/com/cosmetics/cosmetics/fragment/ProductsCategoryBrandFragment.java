package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.adapter.ProductBrandAdapter;
import com.cosmetics.cosmetics.adapter.ProductCategoryAdapter;
import com.cosmetics.cosmetics.model.ProductCategoryData;
import com.cosmetics.cosmetics.view.DetailsProductBrandView;
import com.cosmetics.cosmetics.viewmodel.ProductCategoryViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsCategoryBrandFragment extends Fragment implements DetailsProductBrandView{
    @BindView(R.id.btn_category)
    Button btnCategory;
    @BindView(R.id.btn_brands)
    Button btnBrands;


    ProductCategoryViewModel productCategoryViewModel;

    @BindView(R.id.recycler_products)
    RecyclerView recycler_products;
    ProductCategoryAdapter productCategoryAdapter;


    ProductBrandAdapter productBrandAdapter;
    Unbinder unbinder;

    public ProductsCategoryBrandFragment() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_products_category_brand, container, false);
        unbinder = ButterKnife.bind(this, view);
        productCategoryViewModel = ViewModelProviders.of(this).get(ProductCategoryViewModel.class);
        productCategoryViewModel.getProductCategory("en", getContext()).observe(this, new Observer<List<ProductCategoryData>>() {
            @Override
            public void onChanged(@Nullable List<ProductCategoryData> productCategoryData) {
                productCategoryAdapter = new ProductCategoryAdapter(getActivity(), productCategoryData);
                recycler_products.setLayoutManager(new GridLayoutManager(getContext(),2));
                recycler_products.setAdapter(productCategoryAdapter);
            }
        });
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
                btnCategory.setTextColor(Color.rgb(113, 113, 113));
                getProductBrand();
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
                btnBrands.setTextColor(Color.rgb(113, 113, 113));
                getProductCategory();
                /*TripStatusMgdwla();
                clickAddTrip();*/

                // reservation_presenter.reservation(String.valueOf(PAGE),Clintid,Lang,Type);
            }
        });
    }

    public void getProductCategory() {

        //check "en"
        productCategoryViewModel.getProductCategory("en", getContext()).observe(this, new Observer<List<ProductCategoryData>>() {
            @Override
            public void onChanged(@Nullable List<ProductCategoryData> productCategoryData) {
                productCategoryAdapter = new ProductCategoryAdapter(getActivity(), productCategoryData);
               // productCategoryAdapter.onClickItemLatestProduct(HomeFragment.this);
                recycler_products.setLayoutManager(new GridLayoutManager(getContext(),2));
                recycler_products.setAdapter(productCategoryAdapter);
            }
        });
    }

    public void getProductBrand() {
        //check "en"
        productCategoryViewModel.getProductBrand("en", getContext()).observe(this, new Observer<List<ProductCategoryData>>() {
            @Override
            public void onChanged(@Nullable List<ProductCategoryData> productCategoryData) {
                productBrandAdapter = new ProductBrandAdapter(getActivity(), productCategoryData);
                // productCategoryAdapter.onClickItemLatestProduct(HomeFragment.this);
                productBrandAdapter.onClickItemProductBrand(ProductsCategoryBrandFragment.this);
                recycler_products.setLayoutManager(new GridLayoutManager(getContext(),2));
                recycler_products.setAdapter(productBrandAdapter);
            }
        });
    }

    @Override
    public void showDetailsProductBrand(ProductCategoryData productCategoryData) {
        ProductsFragment productsFragment=new ProductsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("ProductBrandItem",productCategoryData);
        productsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.relative_product_category_brand,productsFragment)
                .addToBackStack(null).commit();
    }
}