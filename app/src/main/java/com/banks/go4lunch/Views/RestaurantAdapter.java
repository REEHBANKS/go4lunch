package com.banks.go4lunch.Views;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.banks.go4lunch.BuildConfig;
import com.banks.go4lunch.Events.ClickListRestaurantEvent;
import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.R;
import com.banks.go4lunch.databinding.FragmentListItemBinding;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private final List<Restaurant> restaurants;


    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentListItemBinding fragmentListItemBinding = FragmentListItemBinding.inflate(layoutInflater, parent, false);
        return new RestaurantViewHolder(fragmentListItemBinding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        // Set Name

        holder.binding.itemListRestaurantName.setText(restaurant.getRestaurantName());

        //Set Address
        holder.binding.itemListRestaurantAddress.setText(restaurant.getRestaurantAddress());

        // Set Rating
        if (restaurant.getRating() != null) {
            float resultForThreeStars = 3 * restaurant.getRating()/5;
            holder.binding.itemListRestaurantRatingBar.setRating(resultForThreeStars);
        } else {
            try {
                holder.binding.itemListRestaurantRatingBar.setRating(Float.parseFloat("Pas de note!"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Set Picture
        if (restaurant.getUrlPictureRestaurant() != null) {
            Glide.with(holder.binding.itemListRestaurantPicture.getContext())
                  .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photo_reference="
                          + restaurant.getUrlPictureRestaurant() +"&key="+ BuildConfig.RR_KEY)
                    .into(holder.binding.itemListRestaurantPicture);
        } else {
            holder.binding.itemListRestaurantPicture.setImageResource(R.drawable.picture_restaurant_with_workers);
        }

        // Set Distance
        holder.binding.itemListRestaurantDistance.setText(String.valueOf(restaurant.getDistanceKm() + " m"));

        // Set Opening
        if (restaurant.getOpeningHours() != null) {
            holder.binding.itemListRestaurantOpening.setText(String.valueOf(openOrClose(restaurant)));
        } else {
            holder.binding.itemListRestaurantOpening.setText(String.valueOf(" "));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ClickListRestaurantEvent(restaurant));
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static String statusRestaurant = "";

    public static String openOrClose(Restaurant restaurant) {
        if (restaurant.getOpeningHours()) {
            statusRestaurant = "Ouvert";
        } else {
            statusRestaurant = "Fermé";
        }
        return statusRestaurant;
    }


}
