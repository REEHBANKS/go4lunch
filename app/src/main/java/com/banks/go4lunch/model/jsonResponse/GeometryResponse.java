package com.banks.go4lunch.model.jsonResponse;

import com.google.gson.annotations.SerializedName;

public class GeometryResponse {

    @SerializedName("location")
    private LocationResponse locationResponse;

    public GeometryResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }

    public LocationResponse getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(LocationResponse locationResponse) {
        this.locationResponse = locationResponse;
    }
}
