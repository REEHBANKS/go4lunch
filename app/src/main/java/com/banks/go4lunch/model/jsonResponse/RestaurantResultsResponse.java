package com.banks.go4lunch.model.jsonResponse;

import com.banks.go4lunch.model.Restaurant;

public class RestaurantResultsResponse {

    private Restaurant[] results;

    // Getter & Setter
    public Restaurant[] getResults() {
        return results;
    }

    public void setResults(Restaurant[] results) {
        this.results = results;
    }

    // Constructor
    public RestaurantResultsResponse(Restaurant[] results) {
        this.results = results;
    }
}
