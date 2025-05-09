package com.huawei.hwcommonmodel.datatypes;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class HeartDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<HeartDeviceInfo> CREATOR = new Parcelable.Creator<HeartDeviceInfo>() { // from class: com.huawei.hwcommonmodel.datatypes.HeartDeviceInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HeartDeviceInfo[] newArray(int i) {
            return new HeartDeviceInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bFh_, reason: merged with bridge method [inline-methods] */
        public HeartDeviceInfo createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            boolean readBoolean = parcel.readBoolean();
            String readString9 = parcel.readString();
            HeartDeviceInfo heartDeviceInfo = new HeartDeviceInfo();
            heartDeviceInfo.setmDeviceId(readString);
            heartDeviceInfo.setDeviceName(readString2);
            heartDeviceInfo.setProductType(readInt);
            heartDeviceInfo.setDeviceConnectState(readInt2);
            heartDeviceInfo.setUuid(readString3);
            heartDeviceInfo.setSoftVersion(readString4);
            heartDeviceInfo.setDeviceCapability(readString5);
            heartDeviceInfo.setDeviceModel(readString6);
            heartDeviceInfo.setCertModel(readString7);
            heartDeviceInfo.setPhdDeviceUdid(readString8);
            heartDeviceInfo.setIsSupportGetEcgServiceIv(readBoolean);
            heartDeviceInfo.setDeviceInfo(readString9);
            return heartDeviceInfo;
        }
    };
    private String mDeviceId = "";
    private String mDeviceName = "";
    private int mProductType = -1;
    private int mDeviceConnectState = 0;
    private String mUuid = "";
    private String mSoftVersion = "";
    private String mDeviceCapability = "";
    private String mDeviceModel = "";
    private String mCertModel = "";
    private String mPhdDeviceUdid = "";
    private boolean mIsSupportGetEcgServiceIv = false;
    private String deviceInfo = "";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDeviceCapability() {
        return this.mDeviceCapability;
    }

    public void setDeviceCapability(String str) {
        this.mDeviceCapability = str;
    }

    public String getmDeviceId() {
        return this.mDeviceId;
    }

    public void setmDeviceId(String str) {
        this.mDeviceId = str;
    }

    public String getSoftVersion() {
        return this.mSoftVersion;
    }

    public void setSoftVersion(String str) {
        this.mSoftVersion = str;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public int getProductType() {
        return this.mProductType;
    }

    public void setProductType(int i) {
        this.mProductType = i;
    }

    public int getDeviceConnectState() {
        return this.mDeviceConnectState;
    }

    public void setDeviceConnectState(int i) {
        this.mDeviceConnectState = i;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }

    public void setDeviceModel(String str) {
        this.mDeviceModel = str;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public void setCertModel(String str) {
        this.mCertModel = str;
    }

    public String getCertModel() {
        return this.mCertModel;
    }

    public void setPhdDeviceUdid(String str) {
        this.mPhdDeviceUdid = str;
    }

    public String getmPhdDeviceUdid() {
        return this.mPhdDeviceUdid;
    }

    public boolean isSupportGetEcgServiceIv() {
        return this.mIsSupportGetEcgServiceIv;
    }

    public void setIsSupportGetEcgServiceIv(boolean z) {
        this.mIsSupportGetEcgServiceIv = z;
    }

    public String getDeviceInfo() {
        return this.deviceInfo;
    }

    public void setDeviceInfo(String str) {
        this.deviceInfo = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeviceId);
        parcel.writeString(this.mDeviceName);
        parcel.writeInt(this.mProductType);
        parcel.writeInt(this.mDeviceConnectState);
        parcel.writeString(this.mUuid);
        parcel.writeString(this.mSoftVersion);
        parcel.writeString(this.mDeviceCapability);
        parcel.writeString(this.mDeviceModel);
        parcel.writeString(this.mCertModel);
        parcel.writeString(this.mPhdDeviceUdid);
        parcel.writeBoolean(this.mIsSupportGetEcgServiceIv);
        parcel.writeString(this.deviceInfo);
    }
}
