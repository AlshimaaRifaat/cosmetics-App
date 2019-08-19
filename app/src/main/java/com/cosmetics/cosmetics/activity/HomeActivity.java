package com.cosmetics.cosmetics.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosmetics.cosmetics.Language;
import com.cosmetics.cosmetics.NetworkConnection;
import com.cosmetics.cosmetics.R;
import com.cosmetics.cosmetics.fragment.HomeFragment;
import com.cosmetics.cosmetics.fragment.MoreFragment;
import com.cosmetics.cosmetics.fragment.MyOrdersFragment;
import com.cosmetics.cosmetics.fragment.ProductsCategoryBrandFragment;
import com.cosmetics.cosmetics.fragment.RateMyOrdersFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static TabLayout tabLayout;
    private ViewPager viewPager;
   /* private TextView cartNotification;

    CardPresenter cardPresenter;
    String UserToken=SplashActivity.Login;

    public TextView textView;*/
   View view,view1,view2,view3,view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setRotationX(180);
        onSelectedTab();
        /*cardPresenter=new CardPresenter(this,this);
        cardPresenter.getCardList(UserToken);*/
        RefreshTabs();
        setupTabIcons();

        if(Language.isRTL()) {
            tabLayout.getTabAt(3).select();
        }else {
            tabLayout.getTabAt(0).select();
        }
    }

    private void RefreshTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
                Selected_Postion(tab.getPosition());


                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void Selected_Postion(int position) {
        ImageView homeIconTab=view1.findViewById(R.id.homeIconTab);
        ImageView productsIconTab=view2.findViewById(R.id.productsIconTab);
        ImageView MyOrdersIconTab=view3.findViewById(R.id.MyOrdersIconTab);
        ImageView moreIconTab=view4.findViewById(R.id.moreIconTab);
        TextView T_home=view1.findViewById(R.id.T_home);
        TextView  T_products=view2.findViewById(R.id.T_products);
        TextView  T_MyOrders=view3.findViewById(R.id.T_MyOrders);
        TextView  T_more=view4.findViewById(R.id.T_more);

        if (position == 0) {
            homeIconTab.setImageResource(R.drawable.home_icon_pink);
            T_home.setTextColor(Color.parseColor("#C6488E"));
            T_products.setTextColor(Color.parseColor("#b3b3b3"));
            T_MyOrders.setTextColor(Color.parseColor("#b3b3b3"));
            T_more.setTextColor(Color.parseColor("#b3b3b3"));
            productsIconTab.setImageResource(R.drawable.cosmetics);
            MyOrdersIconTab.setImageResource(R.drawable.padnote);
            moreIconTab.setImageResource(R.drawable.more_button_of_three_dots);
        }
        if (position == 1) {
            productsIconTab.setImageResource(R.drawable.cosmetics_pink);
            MyOrdersIconTab.setImageResource(R.drawable.padnote);
            homeIconTab.setImageResource(R.drawable.home_icon_silhouette);
            moreIconTab.setImageResource(R.drawable.more_button_of_three_dots);
            T_products.setTextColor(Color.parseColor("#C6488E"));
            T_more.setTextColor(Color.parseColor("#b3b3b3"));
            T_home.setTextColor(Color.parseColor("#b3b3b3"));
            T_MyOrders.setTextColor(Color.parseColor("#b3b3b3"));

        }
        if (position == 2) {
            MyOrdersIconTab.setImageResource(R.drawable.padnote_pink);
            homeIconTab.setImageResource(R.drawable.home_icon_silhouette);
            productsIconTab.setImageResource(R.drawable.cosmetics);
            moreIconTab.setImageResource(R.drawable.more_button_of_three_dots);
            T_MyOrders.setTextColor(Color.parseColor("#C6488E"));
            T_home.setTextColor(Color.parseColor("#b3b3b3"));
            T_more.setTextColor(Color.parseColor("#b3b3b3"));
            T_products.setTextColor(Color.parseColor("#b3b3b3"));

        }
        if (position == 3) {
            moreIconTab.setImageResource(R.drawable.more_button_pink);
            homeIconTab.setImageResource(R.drawable.home_icon_silhouette);
            productsIconTab.setImageResource(R.drawable.cosmetics);
            MyOrdersIconTab.setImageResource(R.drawable.padnote);
            T_more.setTextColor(Color.parseColor("#C6488E"));
            T_home.setTextColor(Color.parseColor("#b3b3b3"));
            T_MyOrders.setTextColor(Color.parseColor("#b3b3b3"));
            T_products.setTextColor(Color.parseColor("#b3b3b3"));
        }
    }

    private void setupTabIcons() {
      view1=getLayoutInflater().inflate( R.layout.tab_icon_home,null );

        view2=getLayoutInflater().inflate( R.layout.tab_icon_products,null );
        //cartNotification=view2.findViewById( R.id.cart_notification );

        view3=getLayoutInflater().inflate( R.layout.tab_icon_my_orders,null );
        view4=getLayoutInflater().inflate( R.layout.tab_icon_more,null );


      if(Language.isRTL()){
            tabLayout.getTabAt(0).setCustomView(view4);
            tabLayout.getTabAt(1).setCustomView(view3);
            tabLayout.getTabAt(2).setCustomView(view2);
            tabLayout.getTabAt(3).setCustomView(view1);
        }else {
            tabLayout.getTabAt( 0 ).setCustomView( view1 );
            tabLayout.getTabAt( 1 ).setCustomView( view2 );
            tabLayout.getTabAt( 2 ).setCustomView( view3 );
            tabLayout.getTabAt( 3).setCustomView( view4 );
        }
    }
    public void onSelectedTab(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
//                switch(tab.getPosition()) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());


     if(Language.isRTL()){
            viewPagerAdapter.addFrag( new MoreFragment(),"" );
            viewPagerAdapter.addFrag( new MyOrdersFragment(),"" );
            viewPagerAdapter.addFrag( new ProductsCategoryBrandFragment(),"" );
            viewPagerAdapter.addFrag( new HomeFragment(),"" );

        }else {
            viewPagerAdapter.addFrag( new HomeFragment(),"" );
            viewPagerAdapter.addFrag( new ProductsCategoryBrandFragment(),"" );
            viewPagerAdapter.addFrag( new MyOrdersFragment(),"" );
            viewPagerAdapter.addFrag( new MoreFragment(),"" );

        }
        viewPager.setAdapter( viewPagerAdapter );
    }

    private void init() {
        viewPager =findViewById(R.id.view_pager_container);
        tabLayout =findViewById(R.id.tabs);

    }

    /*@Override
    public void showCardList(List<CardData> cardDataList) {
        if(Language.isRTL()) {
            TabLayout.Tab tab = HomeActivity.tabLayout.getTabAt(2); // fourth tab
            View tabView = tab.getCustomView();
            TextView textView = tabView.findViewById(R.id.cart_notification);
            textView.setVisibility(View.VISIBLE);
            textView.setText(cardDataList.size() + "");
        }else {
            TabLayout.Tab tab = HomeActivity.tabLayout.getTabAt(1); // fourth tab
            View tabView = tab.getCustomView();
            TextView textView = tabView.findViewById(R.id.cart_notification);
            textView.setVisibility(View.VISIBLE);
            textView.setText(cardDataList.size() + "");
        }
    }

    @Override
    public void showPrice(String price) {

    }

    @Override
    public void showEmptyCard() {

    }

    @Override
    public void showError() {

    }*/

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }



    @Override
    public void onStop() {
        super.onStop();
//        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }


}