package com.banks.go4lunch.model.jsonResponse;

public class LocationResponse {

    private Long lat;
    private Long lng;

    public LocationResponse(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }
}
