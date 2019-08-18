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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.NetworkConnection;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.DetailsMyOrdersAdapter;
import com.cosmetics.cosmetics.adapter.FeatureProductsAdapter;
import com.cosmetics.cosmetics.model.LatestProductsData;
import com.cosmetics.cosmetics.model.MyOrdersData;
import com.cosmetics.cosmetics.model.MyOrdersDetailsData;
import com.cosmetics.cosmetics.view.DetailsMyOrdersView;
import com.cosmetics.cosmetics.view.OnClickRateProductView;
import com.cosmetics.cosmetics.viewmodel.CartViewModel;
import com.cosmetics.cosmetics.viewmodel.DetailsMyOrdersViewModel;
import com.cosmetics.cosmetics.viewmodel.LatestProductsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsMyOrdersFragment extends Fragment implements OnClickRateProductView{
//T_rate_product1



    @BindView(R.id.recycler_details_list_my_orders)
    RecyclerView recycler_details_list_my_orders;

    Unbinder unbinder;
    String userTokenValue;
    DetailsMyOrdersViewModel detailsMyOrdersViewModel;
    NetworkConnection networkConnection;
    DetailsMyOrdersAdapter detailsMyOrdersAdapter;

    Bundle bundle;
    String MyOrderId;
    MyOrdersData myOrdersData;
View view;
    public DetailsMyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details_my_orders, container, false);
        unbinder= ButterKnife.bind(this,view);
        detailsMyOrdersViewModel = ViewModelProviders.of(this).get(DetailsMyOrdersViewModel.class);
        networkConnection=new NetworkConnection(getContext());
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();

        bundle = this.getArguments();
        if (bundle!= null) {
            myOrdersData = bundle.getParcelable("MyOrdersItem");
            MyOrderId=String.valueOf(myOrdersData.getOrderId());
            getListMyOrdersDetails();
            // Toast.makeText(getContext(), "PID "+productId, Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    public void getListMyOrdersDetails() {
        //check "order id"
        detailsMyOrdersViewModel.getMyOrdersDetails(MyOrderId,userTokenValue,getContext()).observe(this, new Observer<List<MyOrdersDetailsData>>() {
            @Override
            public void onChanged(@Nullable List<MyOrdersDetailsData> myOrdersDetailsData) {
                if (myOrdersDetailsData!=null) {
                    if(networkConnection.isNetworkAvailable(getContext())) {
                        detailsMyOrdersAdapter = new DetailsMyOrdersAdapter(getActivity(), myOrdersDetailsData);
                        detailsMyOrdersAdapter.onClickRateProduct(DetailsMyOrdersFragment.this);
                        recycler_details_list_my_orders.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        recycler_details_list_my_orders.setAdapter(detailsMyOrdersAdapter);
                    }else
                    {
                        Toast.makeText(getContext(), getResources().getString(R.string.Check_network_connection), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void showOnClickRateProductResult(MyOrdersDetailsData myOrdersDetailsData) {
        RateMyOrdersFragment rateMyOrdersFragment=new RateMyOrdersFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("rateProduct",myOrdersDetailsData);
        rateMyOrdersFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.container_details_my_orders,rateMyOrdersFragment)
                .addToBackStack(null).commit();
    }
}
