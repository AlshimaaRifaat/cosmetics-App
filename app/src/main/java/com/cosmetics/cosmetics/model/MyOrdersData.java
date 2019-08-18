package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrdersData implements Serializable, Parcelable
{

    @SerializedName("order_id")
    @Expose
    private int orderId;
    @SerializedName("order_total_price")
    @Expose
    private String orderTotalPrice;
    @SerializedName("tax")
    @Expose
    private double tax;
    @SerializedName("delevery_fees")
    @Expose
    private String deleveryFees;
    @SerializedName("order_stat")
    @Expose
    private String orderStat;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_city")
    @Expose
    private String customerCity;
    @SerializedName("customer_country")
    @Expose
    private String customerCountry;
    @SerializedName("customer_street")
    @Expose
    private String customerStreet;
    @SerializedName("customer_comments_extra")
    @Expose
    private String customerCommentsExtra;
    @SerializedName("langtude")
    @Expose
    private String langtude;
    @SerializedName("lattude")
    @Expose
    private String lattude;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Parcelable.Creator<MyOrdersData> CREATOR = new Creator<MyOrdersData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyOrdersData createFromParcel(Parcel in) {
            return new MyOrdersData(in);
        }

        public MyOrdersData[] newArray(int size) {
            return (new MyOrdersData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2474133698874755219L;

    protected MyOrdersData(Parcel in) {
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.orderTotalPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.tax = ((double) in.readValue((double.class.getClassLoader())));
        this.deleveryFees = ((String) in.readValue((String.class.getClassLoader())));
        this.orderStat = ((String) in.readValue((String.class.getClassLoader())));
        this.customerAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCity = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.customerStreet = ((String) in.readValue((String.class.getClassLoader())));
        this.customerCommentsExtra = ((String) in.readValue((String.class.getClassLoader())));
        this.langtude = ((String) in.readValue((String.class.getClassLoader())));
        this.lattude = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentMethod = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.customerPhone = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrdersData() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getDeleveryFees() {
        return deleveryFees;
    }

    public void setDeleveryFees(String deleveryFees) {
        this.deleveryFees = deleveryFees;
    }

    public String getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(String orderStat) {
        this.orderStat = orderStat;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerCommentsExtra() {
        return customerCommentsExtra;
    }

    public void setCustomerCommentsExtra(String customerCommentsExtra) {
        this.customerCommentsExtra = customerCommentsExtra;
    }

    public String getLangtude() {
        return langtude;
    }

    public void setLangtude(String langtude) {
        this.langtude = langtude;
    }

    public String getLattude() {
        return lattude;
    }

    public void setLattude(String lattude) {
        this.lattude = lattude;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(orderTotalPrice);
        dest.writeValue(tax);
        dest.writeValue(deleveryFees);
        dest.writeValue(orderStat);
        dest.writeValue(customerAddress);
        dest.writeValue(customerCity);
        dest.writeValue(customerCountry);
        dest.writeValue(customerStreet);
        dest.writeValue(customerCommentsExtra);
        dest.writeValue(langtude);
        dest.writeValue(lattude);
        dest.writeValue(paymentMethod);
        dest.writeValue(paymentStatus);
        dest.writeValue(customerPhone);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return 0;
    }

}