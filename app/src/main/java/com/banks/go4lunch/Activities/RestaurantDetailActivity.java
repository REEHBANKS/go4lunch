package com.banks.go4lunch.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.banks.go4lunch.BuildConfig;
import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.R;
import com.banks.go4lunch.databinding.ActivityRestaurantDetailBinding;
import com.bumptech.glide.Glide;

public class RestaurantDetailActivity extends AppCompatActivity {

    public static String RESTAURANT_KEY = "RESTAURANT_KEY";
    private Restaurant restaurant;
    private ActivityRestaurantDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        restaurant = (Restaurant) getIntent().getSerializableExtra(RESTAURANT_KEY);
        updateUi();

    }

    public void updateUi() {

        //-----------
        // Set View
        //----------
        if (restaurant.getUrlPictureRestaurant() != null) {
            Glide.with(binding.pictureRestaurantDetail.getContext())
                    .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photo_reference="
                            + restaurant.getUrlPictureRestaurant() + "&key=" + BuildConfig.RR_KEY)
                    .into(binding.pictureRestaurantDetail);
        } else {
            binding.pictureRestaurantDetail.setImageResource(R.drawable.picture_restaurant_with_workers);
        }

        binding.nameRestaurantDetail.setText(restaurant.getRestaurantName());
        binding.addressRestaurantDetail.setText(restaurant.getRestaurantAddress());
        if (restaurant.getRating() != null) {
            float resultForThreeStars = 3 * restaurant.getRating() / 5;
            binding.itemListRestaurantRatingBar.setRating(resultForThreeStars);
        } else {
            try {
                binding.itemListRestaurantRatingBar.setRating(Float.parseFloat("Pas de note!"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        //-----------
        // Set Button
        //----------

        binding.buttonCallRestaurantDetail.setOnClickListener(v -> Toast.makeText(getApplicationContext()
                , "Sorry call unavailable !", Toast.LENGTH_SHORT).show());

        binding.buttonWebsiteRestaurantDetail.setOnClickListener(v -> Toast.makeText(getApplicationContext()
                , "Sorry website unavailable !", Toast.LENGTH_SHORT).show());
    }


}