package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlusQuantityCartResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<PlusQuantityCartResponse> CREATOR = new Creator<PlusQuantityCartResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PlusQuantityCartResponse createFromParcel(Parcel in) {
            return new PlusQuantityCartResponse(in);
        }

        public PlusQuantityCartResponse[] newArray(int size) {
            return (new PlusQuantityCartResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2495435553887871730L;

    protected PlusQuantityCartResponse(Parcel in) {
        this.data = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PlusQuantityCartResponse() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
        dest.writeValue(status);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}
