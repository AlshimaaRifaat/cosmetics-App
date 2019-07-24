package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSliderData implements Serializable, Parcelable
{

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<HomeSliderData> CREATOR = new Creator<HomeSliderData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeSliderData createFromParcel(Parcel in) {
            return new HomeSliderData(in);
        }

        public HomeSliderData[] newArray(int size) {
            return (new HomeSliderData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2619141637109479987L;

    protected HomeSliderData(Parcel in) {
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HomeSliderData() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
        dest.writeValue(title);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}
