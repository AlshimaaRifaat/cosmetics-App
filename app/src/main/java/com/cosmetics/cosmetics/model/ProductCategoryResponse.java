package com.cosmetics.cosmetics.model;


import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCategoryResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<ProductCategoryData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<ProductCategoryResponse> CREATOR = new Creator<ProductCategoryResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductCategoryResponse createFromParcel(Parcel in) {
            return new ProductCategoryResponse(in);
        }

        public ProductCategoryResponse[] newArray(int size) {
            return (new ProductCategoryResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8466519218794783310L;

    protected ProductCategoryResponse(Parcel in) {
        in.readList(this.data, (ProductCategoryData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductCategoryResponse() {
    }

    public List<ProductCategoryData> getData() {
        return data;
    }

    public void setData(List<ProductCategoryData> data) {
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