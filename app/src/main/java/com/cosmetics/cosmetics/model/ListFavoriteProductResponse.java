package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFavoriteProductResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<ListFavoriteProductData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<ListFavoriteProductResponse> CREATOR = new Creator<ListFavoriteProductResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListFavoriteProductResponse createFromParcel(Parcel in) {
            return new ListFavoriteProductResponse(in);
        }

        public ListFavoriteProductResponse[] newArray(int size) {
            return (new ListFavoriteProductResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8871402129879115217L;

    protected ListFavoriteProductResponse(Parcel in) {
        in.readList(this.data, (ListFavoriteProductData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ListFavoriteProductResponse() {
    }

    public List<ListFavoriteProductData> getData() {
        return data;
    }

    public void setData(List<ListFavoriteProductData> data) {
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