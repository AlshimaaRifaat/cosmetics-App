package com.cosmetics.cosmetics.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData implements Serializable, Parcelable
{

    @SerializedName("user_token ")
    @Expose
    private String userToken;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("role_id")
    @Expose
    private int roleId;
    @SerializedName("role_name")
    @Expose
    private String roleName;
    public final static Parcelable.Creator<LoginData> CREATOR = new Creator<LoginData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        public LoginData[] newArray(int size) {
            return (new LoginData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -426764455145955682L;

    protected LoginData(Parcel in) {
        this.userToken = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.roleId = ((int) in.readValue((int.class.getClassLoader())));
        this.roleName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginData() {
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userToken);
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(phone);
        dest.writeValue(roleId);
        dest.writeValue(roleName);
    }

    public int describeContents() {
        return 0;
    }

}
