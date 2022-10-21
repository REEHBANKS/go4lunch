package com.banks.go4lunch.model.jsonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningResponse {

    @SerializedName("open_now")
    @Expose
    private Boolean open;

    public OpeningResponse(Boolean open) {
        this.open = open;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
