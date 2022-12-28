package com.banks.go4lunch.Model.jsonResponse;

import java.util.List;

public class AllRestaurantResponse {

    private List<RestaurantResponse> results;

    // GETTER & SETTER
    public List<RestaurantResponse> getResults() {
        return results;
    }

    public void setResults(List<RestaurantResponse> results) {
        this.results = results;
    }

    // CONSTRUCTOR

    public AllRestaurantResponse(List<RestaurantResponse> results) {
        this.results = results;
    }
}