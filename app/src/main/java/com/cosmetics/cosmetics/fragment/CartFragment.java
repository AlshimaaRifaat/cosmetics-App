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
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.SharedPrefManager;
import com.cosmetics.cosmetics.adapter.CartAdapter;
import com.cosmetics.cosmetics.adapter.DetailsProductSliderAdapter;
import com.cosmetics.cosmetics.adapter.LatestProductsAdapter;
import com.cosmetics.cosmetics.model.GetListCartData;
import com.cosmetics.cosmetics.model.GetListCartResponse;
import com.cosmetics.cosmetics.model.PlusQuantityCartResponse;
import com.cosmetics.cosmetics.model.TotalResultGetListCartData;
import com.cosmetics.cosmetics.view.DeleteItemCartView;
import com.cosmetics.cosmetics.view.MinQuantityView;
import com.cosmetics.cosmetics.view.PlusQuantityCartView;
import com.cosmetics.cosmetics.viewmodel.CartViewModel;
import com.cosmetics.cosmetics.viewmodel.LatestProductsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements PlusQuantityCartView,MinQuantityView
,DeleteItemCartView{

    CartViewModel cartViewModel;

    @BindView(R.id.recycler_cart)
    RecyclerView recycler_cart;
    CartAdapter cartAdapter;
    String userTokenValue;

    @BindView(R.id.T_sub_total_price)
    TextView T_sub_total_price;

    @BindView(R.id.T_tax)
    TextView T_tax;

    @BindView(R.id.T_delivery_fees)
    TextView T_delivery_fees;

    @BindView(R.id.rel_total_result_cart)
    RelativeLayout rel_total_result_cart;

    @BindView(R.id.btn_check_out)
    Button btn_check_out;
    Unbinder unbinder;

    View view;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder= ButterKnife.bind(this,view);
        userTokenValue= SharedPrefManager.getInstance(getContext()).getUserToken();
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        performGettingListCart();
        performGettingTotalResultListCart();

        btn_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container_cart,new OrderNowFragment())
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }





    private void performGettingTotalResultListCart() {
        cartViewModel.getTotalResultListCart("en",userTokenValue,getContext()).observe(this, new Observer<TotalResultGetListCartData>() {
            @Override
            public void onChanged(@Nullable TotalResultGetListCartData totalResultGetListCartData) {
                if (totalResultGetListCartData!=null) {
                    T_sub_total_price.setText(getResources().getString(R.string.sub_total)+"  "+String.valueOf(totalResultGetListCartData.getPrice())+" $");
                    T_tax.setText(String.valueOf(getResources().getString(R.string.Tax)+"  "+totalResultGetListCartData.getTotalTax())+" $");
                    T_delivery_fees.setText(getResources().getString(R.string.Delivery_fees)+"  "+String.valueOf(totalResultGetListCartData.getTotalDeleveryFees())+" $");
                }
            }
        });
    }

    private void performGettingListCart() {

        cartViewModel.getListCart("en",userTokenValue,getContext()).observe(this, new Observer<List<GetListCartData>>() {
            @Override
            public void onChanged(@Nullable List<GetListCartData> getListCartData) {
                if(getListCartData!=null) {
                    cartAdapter = new CartAdapter(getActivity(), getListCartData);
                    cartAdapter.onClickPlusQuantityCart( CartFragment. this);
                    cartAdapter.onClickMinQuantityCart(CartFragment.this);
                    cartAdapter.onClickDeleteItemCart(CartFragment.this);
                    recycler_cart.setLayoutManager(new LinearLayoutManager(getContext()));
                    recycler_cart.setAdapter(cartAdapter);
                }else
                {
                   rel_total_result_cart.setVisibility(View.GONE);
                    Toast.makeText(getContext(), getResources().getString(R.string.Cart_is_impty), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showPlusQuantityCart(GetListCartData getListCartData) {
        //check lang,cart id
        cartViewModel.getPlusQuantityCart("en",String.valueOf(getListCartData.getCartId()),userTokenValue,getContext())
                .observe(this, new Observer<PlusQuantityCartResponse>() {
                    @Override
                    public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                        if(plusQuantityCartResponse!=null)
                            Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                        performGettingListCart();
                        performGettingTotalResultListCart();

                    }
                });
    }

    @Override
    public void showMinQuantityCart(GetListCartData getListCartData) {
        cartViewModel.getMinQuantityCart("en",String.valueOf(getListCartData.getCartId()),userTokenValue,getContext())
                .observe(this, new Observer<PlusQuantityCartResponse>() {
                    @Override
                    public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                        if(plusQuantityCartResponse!=null)
                            Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                        performGettingListCart();
                        performGettingTotalResultListCart();

                    }
                });
    }

    @Override
    public void showDeleteItemCartView(GetListCartData getListCartData, int Position) {
        cartViewModel.getDeleteItemCart("en",String.valueOf(getListCartData.getCartId()),userTokenValue,getContext())
                .observe(this, new Observer<PlusQuantityCartResponse>() {
                    @Override
                    public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                        if(plusQuantityCartResponse!=null)
                            Toast.makeText(getContext(), plusQuantityCartResponse.getData(), Toast.LENGTH_SHORT).show();
                        performGettingListCart();
                        performGettingTotalResultListCart();

                    }
                });
    }
}
