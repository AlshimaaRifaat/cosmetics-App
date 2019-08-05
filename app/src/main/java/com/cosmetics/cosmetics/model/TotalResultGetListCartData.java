package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalResultGetListCartData implements Serializable, Parcelable
{

    @SerializedName("list")
    @Expose
    private java.util.List<GetListCartData> list = null;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("total_delevery_fees")
    @Expose
    private String totalDeleveryFees;
    @SerializedName("price")
    @Expose
    private int price;
    public final static Parcelable.Creator<TotalResultGetListCartData> CREATOR = new Creator<TotalResultGetListCartData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TotalResultGetListCartData createFromParcel(Parcel in) {
            return new TotalResultGetListCartData(in);
        }

        public TotalResultGetListCartData[] newArray(int size) {
            return (new TotalResultGetListCartData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5750979157125216081L;

    protected TotalResultGetListCartData(Parcel in) {
        in.readList(this.list, (GetListCartData.class.getClassLoader()));
        this.totalTax = ((String) in.readValue((String.class.getClassLoader())));
        this.totalDeleveryFees = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((int) in.readValue((int.class.getClassLoader())));
    }

    public TotalResultGetListCartData() {
    }

    public java.util.List<GetListCartData> getList() {
        return list;
    }

    public void setList(java.util.List<GetListCartData> list) {
        this.list = list;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getTotalDeleveryFees() {
        return totalDeleveryFees;
    }

    public void setTotalDeleveryFees(String totalDeleveryFees) {
        this.totalDeleveryFees = totalDeleveryFees;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
        dest.writeValue(totalTax);
        dest.writeValue(totalDeleveryFees);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}