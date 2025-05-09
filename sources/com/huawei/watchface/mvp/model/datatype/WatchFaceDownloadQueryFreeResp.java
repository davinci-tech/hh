package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class WatchFaceDownloadQueryFreeResp implements Parcelable {
    public static final Parcelable.Creator<WatchFaceDownloadQueryFreeResp> CREATOR = new Parcelable.Creator<WatchFaceDownloadQueryFreeResp>() { // from class: com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryFreeResp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceDownloadQueryFreeResp createFromParcel(Parcel parcel) {
            return new WatchFaceDownloadQueryFreeResp(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchFaceDownloadQueryFreeResp[] newArray(int i) {
            return new WatchFaceDownloadQueryFreeResp[i];
        }
    };
    private String cipherVersion;
    private String downUrl;
    private String hashCode;
    private String licenseResp;
    private Integer resultcode;
    private String resultinfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public String getDownUrl() {
        return this.downUrl;
    }

    public void setDownUrl(String str) {
        this.downUrl = str;
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

    protected WatchFaceDownloadQueryFreeResp(Parcel parcel) {
        if (parcel.readByte() == 0) {
            this.resultcode = null;
        } else {
            this.resultcode = Integer.valueOf(parcel.readInt());
        }
        this.resultinfo = parcel.readString();
        this.downUrl = parcel.readString();
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
        parcel.writeString(this.downUrl);
        parcel.writeString(this.licenseResp);
        parcel.writeString(this.cipherVersion);
        parcel.writeString(this.hashCode);
    }
}
