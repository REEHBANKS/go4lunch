package com.banks.go4lunch.model;

public class Restaurant {
    private String id;
    private String restaurantName;
    private String restaurantDistant;
    private String urlPictureRestaurant;
    private String restaurantAddress;
    private String openingHours;
    private String restaurantWebSite;
    private int interestedColleague;
    private int starNumbers;
    private int restaurantNumber;



    public Restaurant() { }

    public Restaurant(String id, String restaurantName,String restaurantDistant,String urlPictureRestaurant,
                      String restaurantAddress,String openingHours,String restaurantWebSite,int interestedColleague,
                      int starNumbers,int restaurantNumber) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDistant = restaurantDistant;
        this.urlPictureRestaurant = urlPictureRestaurant;
        this.restaurantAddress = restaurantAddress;
        this.openingHours = openingHours;
        this.restaurantWebSite = restaurantWebSite;
        this.interestedColleague = interestedColleague;
        this.starNumbers = starNumbers;
        this.restaurantNumber = restaurantNumber;
    }

    // --- GETTERS ---
    public String getId() { return id;}
    public String getRestaurantName() { return restaurantName;}
    public String getRestaurantDistant() { return restaurantDistant;}
    public String getUrlPictureRestaurant() { return urlPictureRestaurant;}
    public String getRestaurantAddress() { return restaurantAddress;}
    public String getOpeningHours() { return openingHours;}
    public String getRestaurantWebSite() { return restaurantWebSite;}
    public int getInterestedColleague() {return interestedColleague;}
    public int getStarNumbers() {return starNumbers;}
    public int getRestaurantNumber() {return restaurantNumber;}

    // --- SETTERS ---
    public void setId(String id) { this.id = id;}
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName;}
    public void setRestaurantDistant(String restaurantDistant) { this.restaurantDistant = restaurantDistant;}
    public void setUrlPictureRestaurant(String urlPictureRestaurant) { this.urlPictureRestaurant = urlPictureRestaurant;}
    public void setRestaurantAddress(String restaurantAddress) { this.restaurantAddress = restaurantAddress;}
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours;}
    public void setRestaurantWebSite(String restaurantWebSite) {this.restaurantWebSite = restaurantWebSite;}
    public void setInterestedColleague(int interestedColleague) {this.interestedColleague = interestedColleague;}
    public void setStarNumbers(int starNumbers) {this.starNumbers = starNumbers;}
    public void setRestaurantNumber(int restaurantNumber) {this.restaurantNumber = restaurantNumber;}
}
