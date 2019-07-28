package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsProductSliderData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Parcelable.Creator<DetailsProductSliderData> CREATOR = new Creator<DetailsProductSliderData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetailsProductSliderData createFromParcel(Parcel in) {
            return new DetailsProductSliderData(in);
        }

        public DetailsProductSliderData[] newArray(int size) {
            return (new DetailsProductSliderData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6339915094467647129L;

    protected DetailsProductSliderData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetailsProductSliderData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}
