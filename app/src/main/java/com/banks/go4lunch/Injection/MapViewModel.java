package com.banks.go4lunch.Injection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.banks.go4lunch.Model.Restaurant;
import com.banks.go4lunch.Repository.RestaurantRepository;

import java.util.List;

public class MapViewModel extends ViewModel {

    RestaurantRepository restaurantRepository =  RestaurantRepository.getInstance();


    public LiveData<List<Restaurant>> getMapLiveData(){
        return restaurantRepository.getRestaurantLiveData();
    }

    public void fetchMapViewModel(Double latitude, Double longitude){
        restaurantRepository.fetchRestaurant(latitude,longitude);
    }

}
