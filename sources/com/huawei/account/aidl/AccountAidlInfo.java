package com.huawei.account.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class AccountAidlInfo implements Parcelable {
    public static final Parcelable.Creator<AccountAidlInfo> CREATOR = new Parcelable.Creator<AccountAidlInfo>() { // from class: com.huawei.account.aidl.AccountAidlInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dh_, reason: merged with bridge method [inline-methods] */
        public AccountAidlInfo createFromParcel(Parcel parcel) {
            return new AccountAidlInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public AccountAidlInfo[] newArray(int i) {
            return new AccountAidlInfo[i];
        }
    };
    private String accessToken;
    private String countryCode;
    private byte[] headPicByts;
    private String huid;
    private String photoPath;
    private String serveToken;
    private String sitId;
    private String stType;
    private String userName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected AccountAidlInfo(Parcel parcel) {
        this.userName = parcel.readString();
        this.huid = parcel.readString();
        this.serveToken = parcel.readString();
        this.accessToken = parcel.readString();
        this.photoPath = parcel.readString();
        this.sitId = parcel.readString();
        this.countryCode = parcel.readString();
        this.stType = parcel.readString();
        this.headPicByts = parcel.createByteArray();
    }

    public AccountAidlInfo() {
    }

    public AccountAidlInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, byte[] bArr) {
        this.userName = str;
        this.huid = str2;
        this.serveToken = str3;
        this.accessToken = str4;
        this.photoPath = str5;
        this.sitId = str6;
        this.countryCode = str7;
        this.stType = str8;
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        byte[] bArr2 = new byte[bArr.length];
        this.headPicByts = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public String getStType() {
        return this.stType;
    }

    public void setStType(String str) {
        this.stType = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getServeToken() {
        return this.serveToken;
    }

    public void setServeToken(String str) {
        this.serveToken = str;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public void setPhotoPath(String str) {
        this.photoPath = str;
    }

    public String getSitId() {
        return this.sitId;
    }

    public void setSitId(String str) {
        this.sitId = str;
    }

    public byte[] getHeadPicByts() {
        byte[] bArr = this.headPicByts;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public void setHeadPicByts(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        byte[] bArr2 = new byte[bArr.length];
        this.headPicByts = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.userName);
        parcel.writeString(this.huid);
        parcel.writeString(this.serveToken);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.photoPath);
        parcel.writeString(this.sitId);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.stType);
        parcel.writeByteArray(this.headPicByts);
    }

    public String toString() {
        return "AccountAidlInfo{userName='" + this.userName + "', huid='" + this.huid + "', serveToken='" + this.serveToken + "', accessToken='" + this.accessToken + "', photoPath='" + this.photoPath + "', sitId='" + this.sitId + "', countryCode='" + this.countryCode + "', stType='" + this.stType + "'}";
    }
}
