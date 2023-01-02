package com.banks.go4lunch.Injection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.Repository.RestaurantRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class ListRestaurantViewModel extends ViewModel {

   RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();

   public LiveData<List<Restaurant>> getRestaurantLiveData(){
      return restaurantRepository.getRestaurantLiveData();
   }

   public LiveData<Restaurant> getOneRestaurantLiveData(){
      return restaurantRepository.getOneRestaurantLiveData();
   }


   public void fetchRestaurantViewModel(Double latitude, Double longitude){
      restaurantRepository.fetchRestaurant(latitude,longitude);
   }

   public void fetchOneRestaurantViewModel(LatLng latLng, String id, Float rating){
      restaurantRepository.fetchOneRestaurant(latLng,id,rating);
   }
}
