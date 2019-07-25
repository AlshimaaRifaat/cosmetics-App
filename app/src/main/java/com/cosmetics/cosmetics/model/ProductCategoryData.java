package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCategoryData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<ProductCategoryData> CREATOR = new Creator<ProductCategoryData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductCategoryData createFromParcel(Parcel in) {
            return new ProductCategoryData(in);
        }

        public ProductCategoryData[] newArray(int size) {
            return (new ProductCategoryData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2425200740569243161L;

    protected ProductCategoryData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductCategoryData() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(image);
        dest.writeValue(title);
        dest.writeValue(shortDescription);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}