package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrdersDetailsData implements Serializable, Parcelable
{

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_quantity")
    @Expose
    private String productQuantity;
    @SerializedName("product_tax")
    @Expose
    private String productTax;
    public final static Parcelable.Creator<MyOrdersDetailsData> CREATOR = new Creator<MyOrdersDetailsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyOrdersDetailsData createFromParcel(Parcel in) {
            return new MyOrdersDetailsData(in);
        }

        public MyOrdersDetailsData[] newArray(int size) {
            return (new MyOrdersDetailsData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6690687027550431867L;

    protected MyOrdersDetailsData(Parcel in) {
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.productQuantity = ((String) in.readValue((String.class.getClassLoader())));
        this.productTax = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrdersDetailsData() {
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductTax() {
        return productTax;
    }

    public void setProductTax(String productTax) {
        this.productTax = productTax;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(productPrice);
        dest.writeValue(productQuantity);
        dest.writeValue(productTax);
    }

    public int describeContents() {
        return 0;
    }

}