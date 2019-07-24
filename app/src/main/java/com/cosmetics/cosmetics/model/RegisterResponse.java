package com.cosmetics.cosmetics.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("data")
    @Expose
    private RegisterData data;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}