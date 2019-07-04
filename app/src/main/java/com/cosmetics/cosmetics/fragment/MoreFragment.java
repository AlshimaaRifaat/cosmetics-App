package com.cosmetics.cosmetics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmetics.cosmetics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {
    @BindView(R.id.T_favorite)
    TextView favoriteTxt;

    @BindView(R.id.T_profile)
    TextView profileTxt;

    Unbinder unbinder;

    public MoreFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_more, container, false);

        unbinder= ButterKnife.bind(this,view);
        favoriteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.relative_more,new FavoriteFragment())
                        .addToBackStack(null).commit();
            }
        });
        profileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.relative_more,new ProfileFragment())
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }
}
