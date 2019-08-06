package com.cosmetics.cosmetics.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.activity.LoginActivity;

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

    @BindView(R.id.T_log_out)
    TextView T_log_out;

    @BindView(R.id.T_login)
    TextView T_login;


    Unbinder unbinder;
String userTokenValue;
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
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
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
        logOut();
        return view;
    }

    private void logOut() {
        if (userTokenValue==null)
        {
            T_log_out.setVisibility(View.GONE);
            T_login.setVisibility(View.VISIBLE);


        }
        T_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).saveUserToken(null);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        T_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();

            }
        });
    }
}
