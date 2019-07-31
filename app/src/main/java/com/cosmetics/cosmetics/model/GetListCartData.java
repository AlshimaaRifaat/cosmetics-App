package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListCartData implements Serializable, Parcelable
{

    @SerializedName("cart_id")
    @Expose
    private int cartId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
    @SerializedName("total_unit_price")
    @Expose
    private int totalUnitPrice;
    public final static Parcelable.Creator<GetListCartData> CREATOR = new Creator<GetListCartData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetListCartData createFromParcel(Parcel in) {
            return new GetListCartData(in);
        }

        public GetListCartData[] newArray(int size) {
            return (new GetListCartData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7955953508879332605L;

    protected GetListCartData(Parcel in) {
        this.cartId = ((int) in.readValue((int.class.getClassLoader())));
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.unitPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.totalUnitPrice = ((int) in.readValue((int.class.getClassLoader())));
    }

    public GetListCartData() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public int getTotalUnitPrice() {
        return totalUnitPrice;
    }

    public void setTotalUnitPrice(int totalUnitPrice) {
        this.totalUnitPrice = totalUnitPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cartId);
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(quantity);
        dest.writeValue(image);
        dest.writeValue(unitPrice);
        dest.writeValue(totalUnitPrice);
    }

    public int describeContents() {
        return 0;
    }

}
