package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class WatchFaceDownloadQueryResp implements Parcelable {
    public static final Parcelable.Creator<WatchFaceDownloadQueryResp> CREATOR = new Parcelable.Creator<WatchFaceDownloadQueryResp>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryResp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceDownloadQueryResp createFromParcel(Parcel parcel) {
            return new WatchFaceDownloadQueryResp(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceDownloadQueryResp[] newArray(int i) {
            return new WatchFaceDownloadQueryResp[i];
        }
    };
    private String alterDownUrl;
    private String cipherVersion;
    private String downUrl;
    private String hashCode;
    private String isOrdered;
    private String licenseResp;
    private String memberStatus;
    private Integer resultcode;
    private String resultinfo;
    private Integer subscriptionStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WatchFaceDownloadQueryResp() {
    }

    public Integer getResultcode() {
        return this.resultcode;
    }

    public void setResultcode(Integer num) {
        this.resultcode = num;
    }

    public String getResultinfo() {
        return this.resultinfo;
    }

    public void setResultinfo(String str) {
        this.resultinfo = str;
    }

    public Integer getSubscriptionStatus() {
        return this.subscriptionStatus;
    }

    public void setSubscriptionStatus(Integer num) {
        this.subscriptionStatus = num;
    }

    public String getMemberStatus() {
        return this.memberStatus;
    }

    public void setMemberStatus(String str) {
        this.memberStatus = str;
    }

    public String getIsOrdered() {
        return this.isOrdered;
    }

    public void setIsOrdered(String str) {
        this.isOrdered = str;
    }

    public String getDownUrl() {
        return this.downUrl;
    }

    public void setDownUrl(String str) {
        this.downUrl = str;
    }

    public String getAlterDownUrl() {
        return this.alterDownUrl;
    }

    public void setAlterDownUrl(String str) {
        this.alterDownUrl = str;
    }

    public String getLicenseResp() {
        return this.licenseResp;
    }

    public void setLicenseResp(String str) {
        this.licenseResp = str;
    }

    public String getCipherVersion() {
        return this.cipherVersion;
    }

    public void setCipherVersion(String str) {
        this.cipherVersion = str;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(String str) {
        this.hashCode = str;
    }

    protected WatchFaceDownloadQueryResp(Parcel parcel) {
        if (parcel.readByte() == 0) {
            this.resultcode = null;
        } else {
            this.resultcode = Integer.valueOf(parcel.readInt());
        }
        this.resultinfo = parcel.readString();
        if (parcel.readByte() == 0) {
            this.subscriptionStatus = null;
        } else {
            this.subscriptionStatus = Integer.valueOf(parcel.readInt());
        }
        this.memberStatus = parcel.readString();
        this.isOrdered = parcel.readString();
        this.downUrl = parcel.readString();
        this.alterDownUrl = parcel.readString();
        this.licenseResp = parcel.readString();
        this.cipherVersion = parcel.readString();
        this.hashCode = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.resultcode == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.resultcode.intValue());
        }
        parcel.writeString(this.resultinfo);
        if (this.subscriptionStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.subscriptionStatus.intValue());
        }
        parcel.writeString(this.memberStatus);
        parcel.writeString(this.isOrdered);
        parcel.writeString(this.downUrl);
        parcel.writeString(this.alterDownUrl);
        parcel.writeString(this.licenseResp);
        parcel.writeString(this.cipherVersion);
        parcel.writeString(this.hashCode);
    }
}
