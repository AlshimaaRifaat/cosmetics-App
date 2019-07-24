package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestProductsData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_general")
    @Expose
    private int priceGeneral;
    @SerializedName("sale_price")
    @Expose
    private int salePrice;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Parcelable.Creator<LatestProductsData> CREATOR = new Creator<LatestProductsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LatestProductsData createFromParcel(Parcel in) {
            return new LatestProductsData(in);
        }

        public LatestProductsData[] newArray(int size) {
            return (new LatestProductsData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7517626684767119324L;

    protected LatestProductsData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.priceGeneral = ((int) in.readValue((int.class.getClassLoader())));
        this.salePrice = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LatestProductsData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPriceGeneral() {
        return priceGeneral;
    }

    public void setPriceGeneral(int priceGeneral) {
        this.priceGeneral = priceGeneral;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(shortDescription);
        dest.writeValue(description);
        dest.writeValue(priceGeneral);
        dest.writeValue(salePrice);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}