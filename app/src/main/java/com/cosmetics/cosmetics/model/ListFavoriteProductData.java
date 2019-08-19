package com.cosmetics.cosmetics.model;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFavoriteProductData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("rates")
    @Expose
    private double rates;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_general")
    @Expose
    private String priceGeneral;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Parcelable.Creator<ListFavoriteProductData> CREATOR = new Creator<ListFavoriteProductData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListFavoriteProductData createFromParcel(Parcel in) {
            return new ListFavoriteProductData(in);
        }

        public ListFavoriteProductData[] newArray(int size) {
            return (new ListFavoriteProductData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1081405399419350903L;

    protected ListFavoriteProductData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.rates = ((double) in.readValue((double.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.priceGeneral = ((String) in.readValue((String.class.getClassLoader())));
        this.salePrice = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ListFavoriteProductData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPriceGeneral() {
        return priceGeneral;
    }

    public void setPriceGeneral(String priceGeneral) {
        this.priceGeneral = priceGeneral;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
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
        dest.writeValue(productId);
        dest.writeValue(rates);
        dest.writeValue(name);
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