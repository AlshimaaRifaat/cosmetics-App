package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<ProductsData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<ProductsResponse> CREATOR = new Creator<ProductsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductsResponse createFromParcel(Parcel in) {
            return new ProductsResponse(in);
        }

        public ProductsResponse[] newArray(int size) {
            return (new ProductsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6213204204082694078L;

    protected ProductsResponse(Parcel in) {
        in.readList(this.data, (ProductsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductsResponse() {
    }

    public List<ProductsData> getData() {
        return data;
    }

    public void setData(List<ProductsData> data) {
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