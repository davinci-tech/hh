package com.huawei.ui.main.stories.privacy.template.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class SourceInfoBean implements Parcelable {
    public static final Parcelable.Creator<SourceInfoBean> CREATOR = new Parcelable.Creator<SourceInfoBean>() { // from class: com.huawei.ui.main.stories.privacy.template.model.bean.SourceInfoBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dQB_, reason: merged with bridge method [inline-methods] */
        public SourceInfoBean createFromParcel(Parcel parcel) {
            return new SourceInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SourceInfoBean[] newArray(int i) {
            return new SourceInfoBean[i];
        }
    };
    private String deviceName;
    private String hardwareVersion;
    private boolean isDevice;
    private String manufacturer;
    private String produceType;
    private String softwareVersion;
    private String source;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SourceInfoBean(boolean z, String str) {
        this.isDevice = z;
        this.source = str;
    }

    protected SourceInfoBean(Parcel parcel) {
        this(parcel.readByte() != 0, parcel.readString());
        this.deviceName = parcel.readString();
        this.manufacturer = parcel.readString();
        this.produceType = parcel.readString();
        this.hardwareVersion = parcel.readString();
        this.softwareVersion = parcel.readString();
    }

    public boolean isDevice() {
        return this.isDevice;
    }

    public String getSource() {
        return this.source;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getProduceType() {
        return this.produceType;
    }

    public void setProduceType(String str) {
        this.produceType = str;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isDevice ? (byte) 1 : (byte) 0);
        parcel.writeString(this.source);
        parcel.writeString(this.deviceName);
        parcel.writeString(this.manufacturer);
        parcel.writeString(this.produceType);
        parcel.writeString(this.hardwareVersion);
        parcel.writeString(this.softwareVersion);
    }
}
