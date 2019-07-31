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
    private final static long serialVersionUID = -7439184846455790741L;

    protected TotalResultGetListCartData(Parcel in) {
        in.readList(this.list, (GetListCartData.class.getClassLoader()));
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}