package com.cosmetics.cosmetics.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cosmetics.cosmetics.NetworkConnection;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.activity.HomeActivity;
import com.cosmetics.cosmetics.activity.LoginActivity;
import com.cosmetics.cosmetics.model.LoginData;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.model.User;
import com.cosmetics.cosmetics.viewmodel.LoginViewModel;
import com.cosmetics.cosmetics.viewmodel.OrderNowViewModel;
import com.fourhcode.forhutils.FUtilsValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderNowFragment extends Fragment {

    @BindView(R.id.ET_phone)
    EditText ET_phone;
    @BindView(R.id.ET_address)
    EditText ET_address;
    @BindView(R.id.ET_country)
    EditText ET_country;
    @BindView(R.id.ET_city)
    EditText ET_city;
    @BindView(R.id.ET_street)
    EditText ET_street;
    @BindView(R.id.ET_some_notes)
    EditText ET_some_notes;
    @BindView(R.id.btn_get_location)
    Button btn_get_location;
    @BindView(R.id.btn_order_now)
    Button btn_order_now;
    
    OrderNowViewModel orderNowViewModel;
    NetworkConnection networkConnection;
    
    Unbinder unbinder;
    String userTokenValue;
    View view;
    public OrderNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_order_now, container, false);
        unbinder= ButterKnife.bind(this,view);
        userTokenValue=SharedPrefManager.getInstance(getContext()).getUserToken();
        orderNowViewModel= ViewModelProviders.of(this).get(OrderNowViewModel.class);
        networkConnection=new NetworkConnection(getContext());
        btn_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              performOrderNow();
            }
        });

        return view;
    }

    private void performOrderNow() {
        FUtilsValidation.isEmpty(ET_phone,getResources().getString(R.string.Please_enter_your_phone_number));
        FUtilsValidation.isEmpty(ET_address,getResources().getString(R.string.Please_enter_your_address));
        if (!ET_phone.getText().toString().equals("")
                && !ET_address.getText().toString().equals("")) {

   /* private void setFont() {
        customFontRegular = Typeface.createFromAsset( getApplicationContext().getAssets(), "robotoFont/Roboto-Regular.ttf" );
        forgotPasswordTxt.setTypeface( customFontRegular );
    }*/
   //check parameters
            orderNowViewModel.getOrderNow("5",ET_address.getText().toString()
                  ,ET_phone.getText().toString(),"30.3","30.1","1","1"
                   , ET_some_notes.getText().toString(),ET_city.getText().toString()
                   , ET_country.getText().toString(),ET_street.getText().toString(),"0",userTokenValue,getContext()).observe(OrderNowFragment.this, new Observer<PlusQuantityCartResponse>() {
                @Override
                public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                    if(plusQuantityCartResponse!=null)
                    Toast.makeText(getContext(), plusQuantityCartResponse.getData().toString(), Toast.LENGTH_SHORT).show();
                }
            });


        }
        }


}
