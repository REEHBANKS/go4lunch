package com.banks.go4lunch.model;

public class Restaurant {
    private String id;
    private String restaurantName;
    private Long longitudeLatitude;
    private String urlPictureRestaurant;
    private String restaurantAddress;
    private String openingHours;
    private int rating;




    public Restaurant() { }

    public Restaurant(String id, String restaurantName, Long longitudeLatitude, String urlPictureRestaurant,
                      String restaurantAddress,String openingHours,
                      int rating) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.longitudeLatitude = longitudeLatitude;
        this.urlPictureRestaurant = urlPictureRestaurant;
        this.restaurantAddress = restaurantAddress;
        this.openingHours = openingHours;
        this.rating = rating;

    }

    // --- GETTERS ---
    public String getId() { return id;}
    public String getRestaurantName() { return restaurantName;}
    public Long longitudeLatitude() { return longitudeLatitude;}
    public String getUrlPictureRestaurant() { return urlPictureRestaurant;}
    public String getRestaurantAddress() { return restaurantAddress;}
    public String getOpeningHours() { return openingHours;}
    public int getRating() {return rating;}


    // --- SETTERS ---
    public void setId(String id) { this.id = id;}
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName;}
    public void setLongitudeLatitude(Long longitudeLatitude) { this.longitudeLatitude = longitudeLatitude;}
    public void setUrlPictureRestaurant(String urlPictureRestaurant) { this.urlPictureRestaurant = urlPictureRestaurant;}
    public void setRestaurantAddress(String restaurantAddress) { this.restaurantAddress = restaurantAddress;}
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours;}
    public void setRating(int rating) {this.rating = rating;}

}
