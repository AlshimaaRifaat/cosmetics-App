package com.cosmetics.cosmetics.fragment;


import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.activity.LoginActivity;
import com.cosmetics.cosmetics.adapter.DetailsProductColorsAdapter;
import com.cosmetics.cosmetics.adapter.DetailsProductSliderAdapter;
import com.cosmetics.cosmetics.adapter.FeatureProductsAdapter;
import com.cosmetics.cosmetics.adapter.HomeSliderAdapter;
import com.cosmetics.cosmetics.adapter.LatestProductsAdapter;
import com.cosmetics.cosmetics.model.DetailsProductAddCartResponse;
import com.cosmetics.cosmetics.model.DetailsProductColorsData;
import com.cosmetics.cosmetics.model.DetailsProductSliderData;
import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.model.ProductsData;
import com.cosmetics.cosmetics.view.OnClickProductColorView;
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
public class DetailsProductFragment extends Fragment implements OnClickProductColorView {
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

    @BindView(R.id.recycler_colors)
    RecyclerView recycler_colors;
    DetailsProductColorsAdapter detailsProductColorsAdapter;

    @BindView(R.id.btn_add_to_cart)
    Button btn_add_to_cart;

    @BindView(R.id.img_favorite_pink)
    ImageView img_favorite_pink;

    @BindView(R.id.img_favorite_black)
    ImageView img_favorite_black;
    Unbinder unbinder;

    LatestProductsData latestProductsData;
    DetailsProductViewModel detailsProductViewModel;
    DetailsProductSliderAdapter detailsProductSliderAdapter;

    Bundle bundle,bundleProducts;
    ProductsData productsData;
    String productId,fromValue,userTokenValue;
    public String productColorId;

    public DetailsProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_product, container, false);
        unbinder = ButterKnife.bind(this, view);
        detailsProductViewModel = ViewModelProviders.of(this).get(DetailsProductViewModel.class);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
       // Toast.makeText(getContext(),userTokenValue, Toast.LENGTH_SHORT).show();

        bundle = this.getArguments();
        bundleProducts=this.getArguments();
        fromValue=bundle.getString("from");
       // Toast.makeText(getContext(), fromValue, Toast.LENGTH_SHORT).show();
        if (bundleProducts!= null&&fromValue.equals("productsPage")) {
            productsData = bundleProducts.getParcelable("ProductItem");
            productId=String.valueOf(productsData.getId());
            getDetailsProductSlider();
            getDetailsProductColors();
        }else if (bundle != null&&fromValue.equals("homeLatestProductPage")) {
            latestProductsData = bundle.getParcelable("LatestProductsItem");
            productId=String.valueOf(latestProductsData.getId());
            T_title.setText(latestProductsData.getTitle());
            T_price.setText("$" + String.valueOf(latestProductsData.getPriceGeneral()));
            T_description.setText(latestProductsData.getDescription());
            T_label_title.setText(latestProductsData.getTitle());
            getDetailsProductSlider();
            getDetailsProductColors();
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               // Toast.makeText(getContext(), String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });
        img_favorite_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFavoriteProduct();
            }
        });
       // Toast.makeText(getContext(), productId, Toast.LENGTH_SHORT).show();
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAddingToCard();
            }
        });
        return view;
    }

    private void performFavoriteProduct() {

        if (userTokenValue==null)
        {
            Toast.makeText(getContext(), getResources().getString(R.string.Pleaseloginfirst), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0,0);
            getActivity().finish();
        }else
        {
            detailsProductViewModel.getFavoriteProduct("en",productId,userTokenValue, getContext()).observe(this, new Observer<PlusQuantityCartResponse>() {
                @Override
                public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                    if(plusQuantityCartResponse!=null)
                    {
                        Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                        img_favorite_black.setVisibility(View.GONE);
                        img_favorite_pink.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }

    private void performAddingToCard() {
        if (userTokenValue==null)
        {
            Toast.makeText(getContext(), getResources().getString(R.string.Pleaseloginfirst), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0,0);
            getActivity().finish();
        }else
        {
          //  Toast.makeText(getContext(), productColorId, Toast.LENGTH_SHORT).show();
            detailsProductViewModel.getDetailsProductAddCart(productId,"1", productColorId,userTokenValue, getContext()).observe(this,
                    new Observer<DetailsProductAddCartResponse>() {
                        @Override
                        public void onChanged(@Nullable DetailsProductAddCartResponse detailsProductAddCartResponse) {
                         if(detailsProductAddCartResponse!=null)
                         {
                             Toast.makeText(getContext(), detailsProductAddCartResponse.getData(), Toast.LENGTH_SHORT).show();
                         }
                        }
                    });

        }

    }

    public void getDetailsProductSlider() {
        //check "ar"
        detailsProductViewModel.getDetailsProductSlider(productId, getContext()).observe(this, new Observer<List<DetailsProductSliderData>>() {
            @Override
            public void onChanged(@Nullable List<DetailsProductSliderData> detailsProductSliderData) {
                if(detailsProductSliderData!=null) {
                    detailsProductSliderAdapter = new DetailsProductSliderAdapter(getActivity(), detailsProductSliderData);
                    recycler_slider.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recycler_slider.setAdapter(detailsProductSliderAdapter);
                }
            }
        });

    }

    public void getDetailsProductColors() {
        detailsProductViewModel.getDetailsProductColor(productId, getContext()).observe(this, new Observer<List<DetailsProductColorsData>>() {
            @Override
            public void onChanged(@Nullable List<DetailsProductColorsData> detailsProductColorsDataList) {
                if(detailsProductColorsDataList!=null) {
                    detailsProductColorsAdapter = new DetailsProductColorsAdapter(getActivity(), detailsProductColorsDataList);
                    detailsProductColorsAdapter.onClickProductColor(DetailsProductFragment.this);
                    recycler_colors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recycler_colors.setAdapter(detailsProductColorsAdapter);
                }
            }
        });

    }

    @Override
    public void showOnClickProductColorResult(DetailsProductColorsData detailsProductColorsData) {

        productColorId=String.valueOf(detailsProductColorsData.getId());
        this.productColorId=productColorId;
    }
}