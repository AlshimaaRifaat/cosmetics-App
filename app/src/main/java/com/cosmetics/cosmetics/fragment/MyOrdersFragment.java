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
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.CommentsAdapter;
import com.cosmetics.cosmetics.adapter.MyOrdersAdapter;
import com.cosmetics.cosmetics.model.CommentsData;
import com.cosmetics.cosmetics.model.MyOrdersData;
import com.cosmetics.cosmetics.view.DetailsMyOrdersView;
import com.cosmetics.cosmetics.viewmodel.DetailsProductViewModel;
import com.cosmetics.cosmetics.viewmodel.MyOrdersViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment implements DetailsMyOrdersView{

    @BindView(R.id.recycler_my_orders)
    RecyclerView recycler_my_orders;
    MyOrdersAdapter myOrdersAdapter;
    MyOrdersViewModel myOrdersViewModel;
    String userTokenValue;
    Unbinder unbinder;
    public MyOrdersFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_orders, container, false);
        unbinder = ButterKnife.bind(this, view);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
        myOrdersViewModel = ViewModelProviders.of(this).get(MyOrdersViewModel.class);
        getListMyOrders();
        return view;
    }

    public void getListMyOrders() {
       //check Lang
        myOrdersViewModel.getMyOrders("en",userTokenValue, getContext()).observe(this, new Observer<List<MyOrdersData>>() {
            @Override
            public void onChanged(@Nullable List<MyOrdersData> myOrdersData) {
                if(myOrdersData!=null) {
                    myOrdersAdapter = new MyOrdersAdapter(getActivity(), myOrdersData);
                    myOrdersAdapter.onClickMyOrdersItem(MyOrdersFragment.this);
                    recycler_my_orders.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler_my_orders.setAdapter(myOrdersAdapter);
                }
            }
        });


    }

    @Override
    public void showDetailsMyOrders(MyOrdersData myOrdersData) {
        DetailsMyOrdersFragment detailsMyOrdersFragment=new DetailsMyOrdersFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("MyOrdersItem",myOrdersData);
        detailsMyOrdersFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.container_my_orders,detailsMyOrdersFragment)
                .addToBackStack(null).commit();
    }
}
