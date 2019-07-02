package com.cosmetics.cosmetics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmetics.cosmetics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsMyOrdersFragment extends Fragment {

View view;
    public DetailsMyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details_my_orders, container, false);
        return view;
    }

}
