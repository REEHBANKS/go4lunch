package com.banks.go4lunch.Model.jsonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotosResponse  {

    @SerializedName("photo_reference")
    @Expose
    String photoReference;

    public PhotosResponse(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}
