package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestProductsResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<LatestProductsData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<LatestProductsResponse> CREATOR = new Creator<LatestProductsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LatestProductsResponse createFromParcel(Parcel in) {
            return new LatestProductsResponse(in);
        }

        public LatestProductsResponse[] newArray(int size) {
            return (new LatestProductsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4357881396097607756L;

    protected LatestProductsResponse(Parcel in) {
        in.readList(this.data, (LatestProductsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LatestProductsResponse() {
    }

    public List<LatestProductsData> getData() {
        return data;
    }

    public void setData(List<LatestProductsData> data) {
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
        dest.writeList(data);
        dest.writeValue(status);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}