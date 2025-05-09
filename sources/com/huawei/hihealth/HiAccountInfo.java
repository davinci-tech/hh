package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiAccountInfo implements Parcelable {
    public static final Parcelable.Creator<HiAccountInfo> CREATOR = new Parcelable.Creator<HiAccountInfo>() { // from class: com.huawei.hihealth.HiAccountInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bul_, reason: merged with bridge method [inline-methods] */
        public HiAccountInfo createFromParcel(Parcel parcel) {
            return new HiAccountInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiAccountInfo[] newArray(int i) {
            return new HiAccountInfo[i];
        }
    };
    private String accessToken;
    private int appId;
    private long expiresIn;
    private String huid;
    private int isLogin;
    private String serviceToken;
    private int siteId;
    private String thirdOpenId;
    private String thirdToken;
    private int type;
    private String userName;

    public static final class LoginStatus {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAccountInfo() {
        this.isLogin = 0;
    }

    protected HiAccountInfo(Parcel parcel) {
        this.isLogin = 0;
        this.huid = parcel.readString();
        this.userName = parcel.readString();
        this.accessToken = parcel.readString();
        this.serviceToken = parcel.readString();
        this.thirdOpenId = parcel.readString();
        this.thirdToken = parcel.readString();
        this.siteId = parcel.readInt();
        this.expiresIn = parcel.readLong();
        this.isLogin = parcel.readInt();
        this.type = parcel.readInt();
        this.appId = parcel.readInt();
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int i) {
        this.appId = i;
    }

    public String getThirdOpenId() {
        return this.thirdOpenId;
    }

    public void setThirdOpenId(String str) {
        this.thirdOpenId = str;
    }

    public String getThirdToken() {
        return this.thirdToken;
    }

    public void setThirdToken(String str) {
        this.thirdToken = str;
    }

    public int getSiteId() {
        return this.siteId;
    }

    public void setSiteId(int i) {
        this.siteId = i;
    }

    public String getServiceToken() {
        return this.serviceToken;
    }

    public void setServiceToken(String str) {
        this.serviceToken = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(long j) {
        this.expiresIn = j;
    }

    public int getIsLogin() {
        return this.isLogin;
    }

    public void setLogin(int i) {
        this.isLogin = i;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiAccountInfo{, isLogin=");
        stringBuffer.append(this.isLogin);
        stringBuffer.append(", appId=").append(this.appId);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.huid);
        parcel.writeString(this.userName);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.serviceToken);
        parcel.writeString(this.thirdOpenId);
        parcel.writeString(this.thirdToken);
        parcel.writeInt(this.siteId);
        parcel.writeLong(this.expiresIn);
        parcel.writeInt(this.isLogin);
        parcel.writeInt(this.type);
        parcel.writeInt(this.appId);
    }
}
