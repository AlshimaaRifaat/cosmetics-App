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
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
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
    private final static long serialVersionUID = -7654426791819621411L;

    protected ListFavoriteProductData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.unitPrice = ((String) in.readValue((String.class.getClassLoader())));
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(image);
        dest.writeValue(unitPrice);
    }

    public int describeContents() {
        return 0;
    }

}
