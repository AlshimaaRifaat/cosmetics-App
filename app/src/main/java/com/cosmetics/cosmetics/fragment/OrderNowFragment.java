package com.cosmetics.cosmetics.fragment;


import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cosmetics.cosmetics.GPSTracker;
import com.cosmetics.cosmetics.Gbs;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderNowFragment extends Fragment implements OnMapReadyCallback, com.google.android.gms.location.LocationListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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

    GoogleApiClient mGoogleApiClient;
    final int REQUEST_LOCATION_CODE = 99;
    LocationRequest locationReques;
    private GoogleMap googleMap;
    double latitude,longitude;
    List<Address> addresses;
    String address,Country,City,Street;
   // EditText ET_address,E_City,E_State;
    Gbs e;
    GPSTracker gbs;
    Unbinder unbinder;
    String userTokenValue;
    boolean statues=true;
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
        gbs=new GPSTracker(getContext());
        btn_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              performOrderNow();
            }
        });

        e=new Gbs();
        //get_Intent();
       GetLocation();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        checkLocationPermission();
        return view;
    }

    private void performOrderNow() {
        FUtilsValidation.isEmpty(ET_phone, getResources().getString(R.string.Please_enter_your_phone_number));
        FUtilsValidation.isEmpty(ET_address, getResources().getString(R.string.Please_enter_your_address));
        if (networkConnection.isNetworkAvailable(getContext())) {
            if (userTokenValue == null) {
                Toast.makeText(getContext(), getResources().getString(R.string.Pleaseloginfirst), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
            if (!ET_phone.getText().toString().equals("")
                    && !ET_address.getText().toString().equals("")) {

   /* private void setFont() {
        customFontRegular = Typeface.createFromAsset( getApplicationContext().getAssets(), "robotoFont/Roboto-Regular.ttf" );
        forgotPasswordTxt.setTypeface( customFontRegular );
    }*/
                //check parameters
                //Toast.makeText(getContext(), CartFragment.TotalPrice, Toast.LENGTH_SHORT).show();
                orderNowViewModel.getOrderNow(CartFragment.TotalPrice, ET_address.getText().toString()
                        , ET_phone.getText().toString(), String.valueOf(longitude), String.valueOf(latitude), "1", "1"
                        , ET_some_notes.getText().toString(), ET_city.getText().toString()
                        , ET_country.getText().toString(), ET_street.getText().toString(), "0", userTokenValue, getContext()).observe(OrderNowFragment.this, new Observer<PlusQuantityCartResponse>() {
                    @Override
                    public void onChanged(@Nullable PlusQuantityCartResponse plusQuantityCartResponse) {
                        if (plusQuantityCartResponse != null)
                            Toast.makeText(getContext(), plusQuantityCartResponse.getData().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
    }

    private synchronized void buildGoogleapiclient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);

            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
            task.addOnFailureListener((getActivity()), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        if(!statues) {
            try {
                Geocoder geocoder = new Geocoder(getContext());
                addresses = geocoder.getFromLocation(latitude, longitude, 1);

                address = addresses.get(0).getAddressLine(0);
                Street = addresses.get(0).getFeatureName();
                City = addresses.get(0).getAdminArea();
                Country = addresses.get(0).getCountryName();

                ET_address.setText(address + "");
                ET_street.setText(Street);
                ET_city.setText(City);
                ET_country.setText(Country);

                // check the rest

            } catch (IOException d) {
                d.printStackTrace();
            }
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
    public void GetLocation(){

        btn_get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statues=false;
                buildGoogleapiclient();
                checkLocationPermission();
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclient();
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        gbs.RemoveCallback();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        buildGoogleapiclient();
    }
}
