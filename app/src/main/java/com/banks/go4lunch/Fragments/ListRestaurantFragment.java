package com.banks.go4lunch.Fragments;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.banks.go4lunch.Activities.RestaurantDetailActivity;
import com.banks.go4lunch.Events.ClickListRestaurantEvent;
import com.banks.go4lunch.Injection.ListRestaurantViewModel;
import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.R;
import com.banks.go4lunch.Views.RestaurantAdapter;
import com.banks.go4lunch.databinding.FragmentListRestaurantBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListRestaurantFragment extends Fragment {

    ListRestaurantViewModel mMainViewModel = new ListRestaurantViewModel();
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;


    //DESIGN
    private FragmentListRestaurantBinding binding;

    //DATA
    private List<Restaurant> restaurants;
    private RestaurantAdapter adapter;
    private static final  String TAG = "Info";




    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListRestaurantBinding.inflate(getLayoutInflater(), container, false);
        this.configureRecyclerView();
        checkAccessRestaurant();
        observeRestaurantLiveData();
        observeOneRestaurantLiveData();


        return binding.getRoot();

    }



    // Get Current List Restaurant

    public void observeRestaurantLiveData() {
       // mMainViewModel.getRestaurantLiveData().observe(getViewLifecycleOwner(), this::updateUI);
        mMainViewModel.getRestaurantLiveData().observe(getViewLifecycleOwner(), new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {

                updateUI(restaurants);

            }
        });
    }

    // Get Current One Restaurant

    public void observeOneRestaurantLiveData() {
        mMainViewModel.getOneRestaurantLiveData().observe(getViewLifecycleOwner(), new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                updateRestaurantSearch(restaurant);


            }
        });
    }
    

    // -----------------
    // CONFIGURATION RECYCLERVIEW
    // -----------------
    private void configureRecyclerView() {
        this.restaurants = new ArrayList<>();
        this.adapter = new RestaurantAdapter(this.restaurants);
        binding.fragmentMainRecyclerView.setAdapter(this.adapter);
        this.binding.fragmentMainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // UPDATE UI
    // -------------------
    public void updateUI(List<Restaurant> theRestaurants) {
        restaurants.addAll(theRestaurants);
        adapter.notifyDataSetChanged();
    }

    public void updateRestaurantSearch(Restaurant theRestaurant)  {
        restaurants.clear();
        restaurants.add(theRestaurant);
        adapter.notifyDataSetChanged();

        Toast.makeText(getContext(), "je marche", Toast.LENGTH_SHORT).show();

    }

    // -------------------
    // EventBus
    // -------------------
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onClickListRestaurant(ClickListRestaurantEvent event){
        launchRestaurantDetailActivity(event.restaurant);
    }

    // -------------------
    // Method Launching restaurant detail activity with a element restaurant
    // -------------------

    private void launchRestaurantDetailActivity(Restaurant restaurant){
        Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
       intent.putExtra(RestaurantDetailActivity.RESTAURANT_KEY,restaurant);
        startActivity(intent);
    }



    // -------------------
    // Checking Permission use current location of user
    // -------------------
    private void checkAccessRestaurant() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();

        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();


            } else {
                Toast.makeText(requireContext(), "Permission died ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(requireContext())
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(requireContext())
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();



                            mMainViewModel.fetchRestaurantViewModel(latitude,longitude);
                        }
                    }
                }, Looper.getMainLooper());

    }


}
