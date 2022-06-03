package com.banks.go4lunch.model;

import java.util.Date;

public class SelectedRestaurant {

    private String restaurantId;
    private String userId;
    private Date dateSelected;



    public SelectedRestaurant() { }

    public SelectedRestaurant (String restaurantId,String userId,Date dateSelected) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.dateSelected = dateSelected;
    }

    // --- GETTERS ---
    public String getRestaurantId() {return restaurantId;}
    public String getUserId() {return userId;}

    // --- SETTERS ---
    public void setRestaurantId(String restaurantId) {this.restaurantId = restaurantId;}
    public void setUserId(String userId) {this.userId = userId;}
}
