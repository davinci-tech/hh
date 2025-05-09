package com.huawei.wearengine.auth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class HiAppInfo implements Parcelable {
    public static final Parcelable.Creator<HiAppInfo> CREATOR = new Parcelable.Creator<HiAppInfo>() { // from class: com.huawei.wearengine.auth.HiAppInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: fcU_, reason: merged with bridge method [inline-methods] */
        public HiAppInfo createFromParcel(Parcel parcel) {
            return new HiAppInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiAppInfo[] newArray(int i) {
            return (i > 65535 || i < 0) ? new HiAppInfo[0] : new HiAppInfo[i];
        }
    };
    private int appId;
    private String appName;
    private int appUid;
    private byte[] byteDraw;
    private long cloudCode;
    private String key;
    private String packageName;
    private String signature;
    private String userId;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAppInfo() {
    }

    protected HiAppInfo(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.key = parcel.readString();
        this.appUid = parcel.readInt();
        this.userId = parcel.readString();
        this.appId = parcel.readInt();
        this.packageName = parcel.readString();
        this.appName = parcel.readString();
        this.byteDraw = parcel.createByteArray();
        this.version = parcel.readString();
        this.signature = parcel.readString();
        this.cloudCode = parcel.readLong();
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public int getAppUid() {
        return this.appUid;
    }

    public void setAppUid(int i) {
        this.appUid = i;
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int i) {
        this.appId = i;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public byte[] getByteDraw() {
        byte[] bArr = this.byteDraw;
        return bArr == null ? new byte[0] : (byte[]) bArr.clone();
    }

    public void setByteDraw(byte[] bArr) {
        this.byteDraw = bArr == null ? null : (byte[]) bArr.clone();
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public long getCloudCode() {
        return this.cloudCode;
    }

    public void setCloudCode(long j) {
        this.cloudCode = j;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiAppInfo{key=");
        stringBuffer.append(this.key);
        stringBuffer.append(", appUid=").append(this.appUid);
        stringBuffer.append(", userId=").append(this.userId);
        stringBuffer.append(", appId=").append(this.appId);
        stringBuffer.append(", packageName='").append(this.packageName).append("', appName='");
        stringBuffer.append(this.appName).append("', byteDraw='");
        stringBuffer.append(Arrays.toString(this.byteDraw)).append("', version='");
        stringBuffer.append(this.version).append("', cloudCode=");
        stringBuffer.append(this.cloudCode);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.key);
        parcel.writeInt(this.appUid);
        parcel.writeString(this.userId);
        parcel.writeInt(this.appId);
        parcel.writeString(this.packageName);
        parcel.writeString(this.appName);
        parcel.writeByteArray(this.byteDraw);
        parcel.writeString(this.version);
        parcel.writeString(this.signature);
        parcel.writeLong(this.cloudCode);
    }
}
