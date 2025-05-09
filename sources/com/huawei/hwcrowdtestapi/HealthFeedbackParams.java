package com.huawei.hwcrowdtestapi;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class HealthFeedbackParams implements Parcelable {
    public static final Parcelable.Creator<HealthFeedbackParams> CREATOR = new Parcelable.Creator<HealthFeedbackParams>() { // from class: com.huawei.hwcrowdtestapi.HealthFeedbackParams.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bGB_, reason: merged with bridge method [inline-methods] */
        public HealthFeedbackParams createFromParcel(Parcel parcel) {
            return new HealthFeedbackParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HealthFeedbackParams[] newArray(int i) {
            return new HealthFeedbackParams[i];
        }
    };
    private String deviceId;
    private String deviceModel;
    private String deviceSn;
    private String hardwareVersion;
    private boolean isHonorDevice = false;
    private String productName;
    private int productType;
    private String productVersion;
    private String romVersion;
    private String routerBrand;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HealthFeedbackParams() {
    }

    public HealthFeedbackParams(Parcel parcel) {
        if (parcel != null) {
            this.deviceId = parcel.readString();
            this.productName = parcel.readString();
            this.productVersion = parcel.readString();
            this.routerBrand = parcel.readString();
            this.hardwareVersion = parcel.readString();
            this.romVersion = parcel.readString();
            this.deviceModel = parcel.readString();
            this.deviceSn = parcel.readString();
            this.productType = parcel.readInt();
        }
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public String getRomVersion() {
        return this.romVersion;
    }

    public void setRomVersion(String str) {
        this.romVersion = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductVersion() {
        return this.productVersion;
    }

    public String getRouterBrand() {
        return this.routerBrand;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setProductVersion(String str) {
        this.productVersion = str;
    }

    public void setRouterBrand(String str) {
        this.routerBrand = str;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public boolean isHonorDevice() {
        return this.isHonorDevice;
    }

    public void setIsHonorDevice(boolean z) {
        this.isHonorDevice = z;
    }

    public int getProductType() {
        return this.productType;
    }

    public void setProductType(int i) {
        this.productType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeString(this.productName);
        parcel.writeString(this.productVersion);
        parcel.writeString(this.routerBrand);
        parcel.writeString(this.hardwareVersion);
        parcel.writeString(this.romVersion);
        parcel.writeString(this.deviceModel);
        parcel.writeString(this.deviceSn);
        parcel.writeInt(this.productType);
    }
}
