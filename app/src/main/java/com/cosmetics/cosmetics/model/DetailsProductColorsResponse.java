package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsProductColorsResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<DetailsProductColorsData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<DetailsProductColorsResponse> CREATOR = new Creator<DetailsProductColorsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetailsProductColorsResponse createFromParcel(Parcel in) {
            return new DetailsProductColorsResponse(in);
        }

        public DetailsProductColorsResponse[] newArray(int size) {
            return (new DetailsProductColorsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -9078661477162413338L;

    protected DetailsProductColorsResponse(Parcel in) {
        in.readList(this.data, (DetailsProductColorsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetailsProductColorsResponse() {
    }

    public List<DetailsProductColorsData> getData() {
        return data;
    }

    public void setData(List<DetailsProductColorsData> data) {
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
