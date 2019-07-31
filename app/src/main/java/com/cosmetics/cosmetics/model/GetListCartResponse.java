package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListCartResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private TotalResultGetListCartData data;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<GetListCartResponse> CREATOR = new Creator<GetListCartResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetListCartResponse createFromParcel(Parcel in) {
            return new GetListCartResponse(in);
        }

        public GetListCartResponse[] newArray(int size) {
            return (new GetListCartResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -798648006061056954L;

    protected GetListCartResponse(Parcel in) {
        this.data = ((TotalResultGetListCartData) in.readValue((TotalResultGetListCartData.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetListCartResponse() {
    }

    public TotalResultGetListCartData getData() {
        return data;
    }

    public void setData(TotalResultGetListCartData data) {
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
