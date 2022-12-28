package com.banks.go4lunch.Events;

import com.banks.go4lunch.Model.Restaurant;

public class ClickListRestaurantEvent {

    public Restaurant restaurant;

    public ClickListRestaurantEvent(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
