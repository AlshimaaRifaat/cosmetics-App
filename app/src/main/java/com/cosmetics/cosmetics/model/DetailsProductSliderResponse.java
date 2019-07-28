package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsProductSliderResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<DetailsProductSliderData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<DetailsProductSliderResponse> CREATOR = new Creator<DetailsProductSliderResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetailsProductSliderResponse createFromParcel(Parcel in) {
            return new DetailsProductSliderResponse(in);
        }

        public DetailsProductSliderResponse[] newArray(int size) {
            return (new DetailsProductSliderResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8243385745366372139L;

    protected DetailsProductSliderResponse(Parcel in) {
        in.readList(this.data, (DetailsProductSliderData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetailsProductSliderResponse() {
    }

    public List<DetailsProductSliderData> getData() {
        return data;
    }

    public void setData(List<DetailsProductSliderData> data) {
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
