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
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.ProductCategoryAdapter;
import com.cosmetics.cosmetics.adapter.ProductsAdapter;
import com.cosmetics.cosmetics.model.ProductCategoryData;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.view.DetailsProductView;
import com.cosmetics.cosmetics.viewmodel.ProductCategoryViewModel;
import com.cosmetics.cosmetics.viewmodel.ProductsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements DetailsProductView {

    ProductsViewModel productsViewModel;

    @BindView(R.id.recycler_products)
    RecyclerView recycler_products;
    ProductsAdapter productsAdapter;

    Unbinder unbinder;

    Bundle bundle;
    ProductCategoryData productBrandData,productCategoryData;
    public String BrandId,CategoryId,TypeValue;
    String userTokenValue;
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
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
        bundle=this.getArguments();
        TypeValue=bundle.getString("type");
       // Toast.makeText(getContext(), "ty "+TypeValue, Toast.LENGTH_SHORT).show();
        if(bundle!=null&&TypeValue.equals("category"))
        {
            productCategoryData=bundle.getParcelable("ProductCategoryItem");
            CategoryId=String.valueOf(productCategoryData.getId());

            getProductsCategory();
        }else if (bundle!=null&&TypeValue.equals("brand"))
        {
            productBrandData=bundle.getParcelable("ProductBrandItem");

                BrandId = String.valueOf(productBrandData.getId());

            getProductsBrand();

        }
        //Toast.makeText(getContext(), "brand "+BrandId, Toast.LENGTH_SHORT).show();



        //check id's,language

        return view;
    }


    public void getProductsCategory() {

    productsViewModel.getProducts("", CategoryId, "en",userTokenValue, getContext()).observe(this, new Observer<List<ProductsData>>() {
        @Override
        public void onChanged(@Nullable List<ProductsData> productsData) {
            if (productsData!=null) {
                productsAdapter = new ProductsAdapter(getActivity(), productsData);
                // productCategoryAdapter.onClickItemLatestProduct(HomeFragment.this);
                productsAdapter.onClickItemProduct(ProductsFragment.this);
                recycler_products.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recycler_products.setAdapter(productsAdapter);
            }
        }
    });
}


    public void getProductsBrand() {
        if (BrandId != null) {
            productsViewModel.getProducts(BrandId, "", "en",userTokenValue, getContext()).observe(this, new Observer<List<ProductsData>>() {
                @Override
                public void onChanged(@Nullable List<ProductsData> productsData) {
                    if (productsData!=null) {
                        productsAdapter = new ProductsAdapter(getActivity(), productsData);
                        productsAdapter.onClickItemProduct(ProductsFragment.this);
                        recycler_products.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        recycler_products.setAdapter(productsAdapter);
                    }
                }
            });
        }
    }

    @Override
    public void showDetailsProduct(ProductsData productsData) {
        DetailsProductFragment detailsProductFragment=new DetailsProductFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("ProductItem",productsData);
        bundle.putString("from","productsPage");
        detailsProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.container_products,detailsProductFragment)
                .addToBackStack(null).commit();
    }
}
