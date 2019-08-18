package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrdersDetailsResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<MyOrdersDetailsData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<MyOrdersDetailsResponse> CREATOR = new Creator<MyOrdersDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyOrdersDetailsResponse createFromParcel(Parcel in) {
            return new MyOrdersDetailsResponse(in);
        }

        public MyOrdersDetailsResponse[] newArray(int size) {
            return (new MyOrdersDetailsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7689946406003184746L;

    protected MyOrdersDetailsResponse(Parcel in) {
        in.readList(this.data, (MyOrdersDetailsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrdersDetailsResponse() {
    }

    public List<MyOrdersDetailsData> getData() {
        return data;
    }

    public void setData(List<MyOrdersDetailsData> data) {
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
