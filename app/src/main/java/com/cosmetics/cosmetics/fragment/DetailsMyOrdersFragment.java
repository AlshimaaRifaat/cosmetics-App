package com.cosmetics.cosmetics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosmetics.cosmetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsMyOrdersFragment extends Fragment {
//T_rate_product1

    @BindView(R.id.T_rate_product1)
    TextView rateProduct1Txt;
    Unbinder unbinder;
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
        rateProduct1Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.relative_details,new RateMyOrdersFragment())
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }

}
