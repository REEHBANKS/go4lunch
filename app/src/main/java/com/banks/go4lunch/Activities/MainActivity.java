package com.banks.go4lunch.Activities;

import static android.content.ContentValues.TAG;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.banks.go4lunch.BuildConfig;
import com.banks.go4lunch.Fragments.ListRestaurantFragment;
import com.banks.go4lunch.Fragments.MapFragment;
import com.banks.go4lunch.Fragments.WorkmatesFragment;
import com.banks.go4lunch.Injection.ListRestaurantViewModel;
import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    ListRestaurantViewModel mMainViewModel = new ListRestaurantViewModel();
    BottomNavigationView bottomNavigationView;
     ListRestaurantFragment mListRestaurantFragment = new ListRestaurantFragment();
     MapFragment mMapFragment = new MapFragment();
    static   WorkmatesFragment mWorkmatesFragment = new WorkmatesFragment();
    Toolbar toolbar;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int AUTOCOMPLETE_REQUEST_CODE_2 = 2;
    private static final int RESULT_OK = -1;






    // Set the fields to specify which types of place data to
    // return after the user has made a selection.




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


      //  startActivity(new Intent(MainActivity.this,ConnexionActivity.class));




        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(), BuildConfig.RR_KEY);
        }




        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mMapFragment)
                .commit();



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.maps_view:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mMapFragment )
                                .commit();
                        return true;
                    case R.id.list_view:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mListRestaurantFragment )
                                .commit();
                        return true;
                    case R.id.workmates:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,mWorkmatesFragment )
                                .commit();
                        return true;
                }

                return false;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_bar_top, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_search:

                    if (mMapFragment.isVisible()) {
                        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,
                                Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))
                                .setTypesFilter(Collections.singletonList("restaurant"))
                                .setCountry("FR")
                                .build(this);
                      startActivityForResult  ( intent,AUTOCOMPLETE_REQUEST_CODE);

                    }else
                        if (mListRestaurantFragment.isVisible()) {
                            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,
                                    Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.ICON_URL, Place.Field.OPENING_HOURS,
                                            Place.Field.RATING))
                                    .setTypesFilter(Collections.singletonList("restaurant"))
                                    .setCountry("FR")
                                    .build(this);
                            startActivityForResult  ( intent,AUTOCOMPLETE_REQUEST_CODE_2);

                        }else
                            if(mWorkmatesFragment.isVisible()){
                                Toast.makeText(this, "NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                                break;

                            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Search for map
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                Place place = Autocomplete.getPlaceFromIntent(data);
                mMapFragment.onMapReadyMarkerSearch(place.getLatLng(), place.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        // Search for list
        } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE_2){
            if (resultCode == RESULT_OK) {
                assert data != null;
                Place place = Autocomplete.getPlaceFromIntent(data);

                mMainViewModel.fetchOneRestaurantViewModel(place.getLatLng(),place.getId(), Objects.requireNonNull(place.getRating()).floatValue());


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}


// mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

//startSignInActivity();


   /* private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        //.setTheme(R.style.LoginTheme)
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                       // .setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }
*/
