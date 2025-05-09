package com.huawei.ui.main.stories.privacy.datasourcemanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class SourceInfo implements Parcelable {
    public static final Parcelable.Creator<SourceInfo> CREATOR = new Parcelable.Creator<SourceInfo>() { // from class: com.huawei.ui.main.stories.privacy.datasourcemanager.bean.SourceInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dPE_, reason: merged with bridge method [inline-methods] */
        public SourceInfo createFromParcel(Parcel parcel) {
            return new SourceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public SourceInfo[] newArray(int i) {
            return new SourceInfo[i];
        }
    };
    private String appVertion;
    private String clientId;
    private String deviceIcon;
    private int deviceId;
    private String deviceName;
    private int deviceType;
    private String deviceUniqueCode;
    private int iconResourceId;
    private boolean isHealthKit;
    private boolean isOtherManufacturer;
    private int itemType;
    private String packageName;
    private int sourceType;
    private String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SourceInfo() {
    }

    protected SourceInfo(Parcel parcel) {
        this.sourceType = parcel.readInt();
        this.deviceId = parcel.readInt();
        this.deviceUniqueCode = parcel.readString();
        this.deviceName = parcel.readString();
        this.packageName = parcel.readString();
        this.appVertion = parcel.readString();
        this.deviceIcon = parcel.readString();
        this.deviceType = parcel.readInt();
        this.title = parcel.readString();
        this.clientId = parcel.readString();
        this.itemType = parcel.readInt();
        this.isHealthKit = parcel.readByte() != 0;
        this.isOtherManufacturer = parcel.readByte() != 0;
        this.iconResourceId = parcel.readInt();
    }

    public int getmItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(int i) {
        this.deviceId = i;
    }

    public String getDeviceUniqueCode() {
        String str = this.deviceUniqueCode;
        return str == null ? "" : str;
    }

    public void setDeviceUniqueCode(String str) {
        this.deviceUniqueCode = str;
    }

    public String getDeviceName() {
        String str = this.deviceName;
        return str == null ? "" : str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(int i) {
        this.sourceType = i;
    }

    public String getClientId() {
        String str = this.clientId;
        return str == null ? "" : str;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getDeviceIcon() {
        String str = this.deviceIcon;
        return str == null ? "" : str;
    }

    public void setDeviceIcon(String str) {
        this.deviceIcon = str;
    }

    public boolean isHealthKit() {
        return this.isHealthKit;
    }

    public void setHealthKit(boolean z) {
        this.isHealthKit = z;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setAppVertion(String str) {
        this.appVertion = str;
    }

    public boolean isOtherManufacturer() {
        return this.isOtherManufacturer;
    }

    public void setOtherManufacturer(boolean z) {
        this.isOtherManufacturer = z;
    }

    public int getIconResourceId() {
        return this.iconResourceId;
    }

    public void setIconResourceId(int i) {
        this.iconResourceId = i;
    }

    public String toString() {
        return "DeviceInfo{ deviceName=" + this.deviceName + " packageName=" + this.packageName + " appVertion=" + this.appVertion + ", deviceType=" + this.deviceType + ", title=" + this.title + ", deviceId=" + this.deviceId + ", deviceUniqueCode=" + this.deviceUniqueCode + ", sourceType=" + this.sourceType + ", itemType=" + this.itemType + ", deviceIcon=" + this.deviceIcon + ", isOtherManufacturer=" + this.isOtherManufacturer + ", iconResourceId=" + this.iconResourceId + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.sourceType);
        parcel.writeInt(this.deviceId);
        parcel.writeString(this.deviceUniqueCode);
        parcel.writeString(this.deviceName);
        parcel.writeString(this.packageName);
        parcel.writeString(this.appVertion);
        parcel.writeString(this.deviceIcon);
        parcel.writeInt(this.deviceType);
        parcel.writeString(this.title);
        parcel.writeString(this.clientId);
        parcel.writeInt(this.itemType);
        parcel.writeByte(this.isHealthKit ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isOtherManufacturer ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.iconResourceId);
    }
}
