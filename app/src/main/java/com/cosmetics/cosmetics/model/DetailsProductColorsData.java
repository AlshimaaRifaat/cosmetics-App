package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsProductColorsData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("hash_color")
    @Expose
    private String hashColor;
    public final static Parcelable.Creator<DetailsProductColorsData> CREATOR = new Creator<DetailsProductColorsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetailsProductColorsData createFromParcel(Parcel in) {
            return new DetailsProductColorsData(in);
        }

        public DetailsProductColorsData[] newArray(int size) {
            return (new DetailsProductColorsData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6727580477070032304L;

    protected DetailsProductColorsData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.hashColor = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetailsProductColorsData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashColor() {
        return hashColor;
    }

    public void setHashColor(String hashColor) {
        this.hashColor = hashColor;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(hashColor);
    }

    public int describeContents() {
        return 0;
    }

}
