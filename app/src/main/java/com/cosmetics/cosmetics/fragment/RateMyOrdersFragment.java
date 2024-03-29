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
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.Language;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.model.MyOrdersData;
import com.cosmetics.cosmetics.model.MyOrdersDetailsData;
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

    @BindView(R.id.T_evaluation)
    TextView T_evaluation;

    RateMyOrdersViewModel rateMyOrdersViewModel;
    String userTokenValue;
    //public  static String TotalPrice;
    Unbinder unbinder;
    int rateCount;

    Bundle bundle;
    String ProductId;
    MyOrdersDetailsData myOrdersDetailsData;
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
        bundle = this.getArguments();
        if (bundle!= null) {
            myOrdersDetailsData = bundle.getParcelable("rateProduct");
            ProductId=String.valueOf(myOrdersDetailsData.getProductId());
            PerformRatingMyOrders();

            // Toast.makeText(getContext(), "PID "+productId, Toast.LENGTH_SHORT).show();
        }
        /*rating_bar_evaluate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Toast.makeText(getContext(), String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });*/

       /* if(rateVal==1)
        {
            T_evaluation.setText("Very Bad");
        }else if(rateVal==2)
        {
            T_evaluation.setText("Bad");
        }else if(rateVal==3)
        {
            T_evaluation.setText("Good");
        }else if(rateVal==4)
        {
            T_evaluation.setText("Very Good");
        }else if(rateVal==5)
        {
            T_evaluation.setText("Excellent");
        }*/
        checkEvaluationCase();
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformRatingMyOrders();
            }
        });


        return view;
    }
    private void PerformRatingMyOrders() {
        //check product id

      rateCount= (int) rating_bar_evaluate.getRating();


       // Toast.makeText(getContext(), String.valueOf(rateCount), Toast.LENGTH_SHORT).show();
                rateMyOrdersViewModel.getRateMyOrders(ProductId,String.valueOf(rateCount),ET_comment.getText().toString(),userTokenValue,getContext())
                        .observe(RateMyOrdersFragment.this, new Observer<PlusQuantityCartResponse>() {
                            @Override
                            public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                                if(plusQuantityCartResponse!=null)
                                    Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                            }
                        });
       checkEvaluationCase();
            }

    private void checkEvaluationCase() {
        if(rateCount==1)
        {
            T_evaluation.setVisibility(View.VISIBLE);
            T_evaluation.setText("Very Bad");
        }else if(rateCount==2)
        {
            T_evaluation.setVisibility(View.VISIBLE);
            T_evaluation.setText("Bad");
        }else if(rateCount==3)
        {
            T_evaluation.setVisibility(View.VISIBLE);
            T_evaluation.setText("Good");
        }else if(rateCount==4)
        {
            T_evaluation.setVisibility(View.VISIBLE);
            T_evaluation.setText("Very Good");
        }else if(rateCount==5)
        {
            T_evaluation.setVisibility(View.VISIBLE);
            T_evaluation.setText("Excellent");
        }

    }


}


