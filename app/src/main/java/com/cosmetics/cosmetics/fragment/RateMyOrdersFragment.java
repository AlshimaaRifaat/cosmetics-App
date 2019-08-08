package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.cosmetics.cosmetics.Language;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.viewmodel.CartViewModel;
import com.cosmetics.cosmetics.viewmodel.RateMyOrdersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateMyOrdersFragment extends Fragment {
//ET_comment
     @BindView(R.id.ET_comment)
     EditText ET_comment;
    @BindView(R.id.rating_bar_evaluate)
    RatingBar rating_bar_evaluate;

    @BindView(R.id.btn_rate)
    Button btn_rate;

    RateMyOrdersViewModel rateMyOrdersViewModel;
    String userTokenValue;
    //public  static String TotalPrice;
    Unbinder unbinder;
    public RateMyOrdersFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_rate_my_orders, container, false);
        unbinder= ButterKnife.bind(this,view);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
        rateMyOrdersViewModel = ViewModelProviders.of(this).get(RateMyOrdersViewModel.class);

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformRatingMyOrders();
            }
        });


        return view;
    }
    private void PerformRatingMyOrders() {
        //check product id & rating range
        rateMyOrdersViewModel.getRateMyOrders("1","1",ET_comment.getText().toString(),userTokenValue,getContext())
                .observe(this, new Observer<PlusQuantityCartResponse>() {
                    @Override
                    public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                        if(plusQuantityCartResponse!=null)
                            Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
