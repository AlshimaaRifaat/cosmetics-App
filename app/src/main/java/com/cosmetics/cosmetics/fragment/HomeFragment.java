package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cosmetics.cosmetics.NetworkConnection;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.FeatureProductsAdapter;
import com.cosmetics.cosmetics.adapter.HomeSliderAdapter;
import com.cosmetics.cosmetics.adapter.LatestProductsAdapter;
import com.cosmetics.cosmetics.model.HomeSliderData;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.view.DetailsHomeLatestProductsView;
import com.cosmetics.cosmetics.viewmodel.LatestProductsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements DetailsHomeLatestProductsView {
    @BindView(R.id.icon_cart)
    ImageView iconCart;
    Unbinder unbinder;

    LatestProductsViewModel latestProductsViewModel;

    @BindView(R.id.recycler_latest_products)
    RecyclerView recycler_latest_products;
    LatestProductsAdapter latestProductsAdapter;

    @BindView(R.id.recycler_featured_products)
    RecyclerView recycler_featured_products;
    FeatureProductsAdapter featureProductsAdapter;

    @BindView(R.id.recycler_slider)
    RecyclerView recycler_slider;
    HomeSliderAdapter homeSliderAdapter;


    List<HomeSliderData> sliders =new ArrayList();
    final Handler handler = new Handler();
    int position = 0;
    Boolean end;
    final Runnable update = new Runnable() {
        public void run() {
            if(position == sliders.size()-1){
                end = true;
            }
            else if (position == 0) {
                end = false;
            }
            if(!end){
                position++;
            } else {
                position--;
            }
//                vp_slider.setCurrentItem(page_position, true);
            recycler_slider.smoothScrollToPosition(position);
        }
    };


    View view;
NetworkConnection networkConnection;
String userTokenValue;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        unbinder= ButterKnife.bind(this,view);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
        networkConnection=new NetworkConnection(getContext());
        getLatestProducts();
        getFeatureProducts();
        getHomeSlider();
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
        latestProductsViewModel.getlatestProducts("ar",userTokenValue,getContext()).observe(this, new Observer<List<LatestProductsData>>() {
            @Override
            public void onChanged(@Nullable List<LatestProductsData> latestProductsData) {
                if (latestProductsData!=null) {
                    if (networkConnection.isNetworkAvailable(getContext())) {
                        latestProductsAdapter = new LatestProductsAdapter(getActivity(), latestProductsData);
                        latestProductsAdapter.onClickItemLatestProduct(HomeFragment.this);
                        recycler_latest_products.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                        recycler_latest_products.setAdapter(latestProductsAdapter);
                    }else
                    {
                        Toast.makeText(getContext(), getResources().getString(R.string.Check_network_connection), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void getFeatureProducts() {
        latestProductsViewModel = ViewModelProviders.of(this).get(LatestProductsViewModel.class);
        //check "ar"
        latestProductsViewModel.getFeaturedProducts("ar",getContext()).observe(this, new Observer<List<LatestProductsData>>() {
            @Override
            public void onChanged(@Nullable List<LatestProductsData> latestProductsData) {
                if (latestProductsData!=null) {
                    if(networkConnection.isNetworkAvailable(getContext())) {
                        featureProductsAdapter = new FeatureProductsAdapter(getActivity(), latestProductsData);
                        recycler_featured_products.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        recycler_featured_products.setAdapter(featureProductsAdapter);
                    }else
                    {
                        Toast.makeText(getContext(), getResources().getString(R.string.Check_network_connection), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
public void getHomeSlider()
{
    latestProductsViewModel = ViewModelProviders.of(this).get(LatestProductsViewModel.class);
    //check "ar"
    latestProductsViewModel.getHomeSlider("ar",getContext()).observe(this, new Observer<List<HomeSliderData>>() {
        @Override
        public void onChanged(@Nullable List<HomeSliderData> homeSliderData) {
            sliders=homeSliderData;
            if (sliders!=null) {
                if (networkConnection.isNetworkAvailable(getContext())) {
                    homeSliderAdapter = new HomeSliderAdapter(getActivity(), homeSliderData);
                    recycler_slider.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    recycler_slider.setAdapter(homeSliderAdapter);
                }else
                {
                    Toast.makeText(getContext(), getResources().getString(R.string.Check_network_connection), Toast.LENGTH_SHORT).show();
                }
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(update);
                    }
                }, 2000, 2000);
            }

        }
    });
}

    @Override
    public void showDetailsHomeLatestProducts(LatestProductsData latestProductsData) {
         DetailsProductFragment detailsItemProductsFragment=new DetailsProductFragment();
         Bundle bundle=new Bundle();
         bundle.putParcelable("LatestProductsItem",latestProductsData);
         bundle.putString("from","homeLatestProductPage");
         detailsItemProductsFragment.setArguments(bundle);
         getFragmentManager().beginTransaction().replace(R.id.relative_home,detailsItemProductsFragment)
                 .addToBackStack(null).commit();
    }
}
