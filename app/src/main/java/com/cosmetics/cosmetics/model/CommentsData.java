package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsData implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("rate_range")
    @Expose
    private String rateRange;
    @SerializedName("rate_comment")
    @Expose
    private String rateComment;
    @SerializedName("rate_reply")
    @Expose
    private String rateReply;
    public final static Parcelable.Creator<CommentsData> CREATOR = new Creator<CommentsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CommentsData createFromParcel(Parcel in) {
            return new CommentsData(in);
        }

        public CommentsData[] newArray(int size) {
            return (new CommentsData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1153759023061135933L;

    protected CommentsData(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.rateRange = ((String) in.readValue((String.class.getClassLoader())));
        this.rateComment = ((String) in.readValue((String.class.getClassLoader())));
        this.rateReply = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CommentsData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRateRange() {
        return rateRange;
    }

    public void setRateRange(String rateRange) {
        this.rateRange = rateRange;
    }

    public String getRateComment() {
        return rateComment;
    }

    public void setRateComment(String rateComment) {
        this.rateComment = rateComment;
    }

    public String getRateReply() {
        return rateReply;
    }

    public void setRateReply(String rateReply) {
        this.rateReply = rateReply;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userName);
        dest.writeValue(rateRange);
        dest.writeValue(rateComment);
        dest.writeValue(rateReply);
    }

    public int describeContents() {
        return 0;
    }

}