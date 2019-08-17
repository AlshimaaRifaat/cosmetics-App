package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<CommentsData> data = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<CommentsResponse> CREATOR = new Creator<CommentsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CommentsResponse createFromParcel(Parcel in) {
            return new CommentsResponse(in);
        }

        public CommentsResponse[] newArray(int size) {
            return (new CommentsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1383679214225784675L;

    protected CommentsResponse(Parcel in) {
        in.readList(this.data, (CommentsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CommentsResponse() {
    }

    public List<CommentsData> getData() {
        return data;
    }

    public void setData(List<CommentsData> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(status);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}