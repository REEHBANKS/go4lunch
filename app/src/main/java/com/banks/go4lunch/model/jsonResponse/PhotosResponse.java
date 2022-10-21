package com.banks.go4lunch.model.jsonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotosResponse {

    @SerializedName("html_attributions")
    @Expose
    private String urlPicture;
}
