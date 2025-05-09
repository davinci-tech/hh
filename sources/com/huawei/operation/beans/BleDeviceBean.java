package com.huawei.operation.beans;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes5.dex */
public class BleDeviceBean implements Parcelable {
    public static final Parcelable.Creator<BleDeviceBean> CREATOR = new Parcelable.Creator<BleDeviceBean>() { // from class: com.huawei.operation.beans.BleDeviceBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDeviceBean createFromParcel(Parcel parcel) {
            return new BleDeviceBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDeviceBean[] newArray(int i) {
            return new BleDeviceBean[i];
        }
    };
    private String advertisData;
    private List<ParcelUuid> advertisServiceUUIDs;
    private String deviceId;
    private String localName;
    private String manufacturerData;
    private int rssi;
    private String serviceData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BleDeviceBean() {
    }

    protected BleDeviceBean(Parcel parcel) {
        this.deviceId = parcel.readString();
        this.rssi = parcel.readInt();
        this.advertisData = parcel.readString();
        this.manufacturerData = parcel.readString();
        this.advertisServiceUUIDs = parcel.createTypedArrayList(ParcelUuid.CREATOR);
        this.localName = parcel.readString();
        this.serviceData = parcel.readString();
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setAdvertisData(String str) {
        this.advertisData = str;
    }

    public void setAdvertisServiceUUIDs(List<ParcelUuid> list) {
        this.advertisServiceUUIDs = list;
    }

    public void setLocalName(String str) {
        this.localName = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeInt(this.rssi);
        parcel.writeString(this.advertisData);
        parcel.writeString(this.manufacturerData);
        parcel.writeTypedList(this.advertisServiceUUIDs);
        parcel.writeString(this.localName);
        parcel.writeString(this.serviceData);
    }
}
