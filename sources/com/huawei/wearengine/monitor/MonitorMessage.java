package com.huawei.wearengine.monitor;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class MonitorMessage implements Parcelable {
    public static final Parcelable.Creator<MonitorMessage> CREATOR = new Parcelable.Creator<MonitorMessage>() { // from class: com.huawei.wearengine.monitor.MonitorMessage.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdy_, reason: merged with bridge method [inline-methods] */
        public MonitorMessage createFromParcel(Parcel parcel) {
            return new MonitorMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MonitorMessage[] newArray(int i) {
            return (i > 65535 || i < 0) ? new MonitorMessage[0] : new MonitorMessage[i];
        }
    };
    private static final int MAX_SIZE = 65535;
    private boolean mBooleanData;
    private String mDeviceId;
    private int mIntData;
    private HashMap<String, MonitorMessage> mMapData;
    private String mMonitorItemType;
    private int mProductType;
    private String mStringData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MonitorMessage() {
    }

    public MonitorMessage(String str, String str2, boolean z) {
        this.mMonitorItemType = str;
        this.mDeviceId = str2;
        this.mBooleanData = z;
    }

    public MonitorMessage(String str, String str2, int i) {
        this.mMonitorItemType = str;
        this.mDeviceId = str2;
        this.mIntData = i;
    }

    public MonitorMessage(String str, String str2, String str3) {
        this.mMonitorItemType = str;
        this.mDeviceId = str2;
        this.mStringData = str3;
    }

    public MonitorMessage(String str, String str2, HashMap<String, MonitorMessage> hashMap) {
        this.mMonitorItemType = str;
        this.mDeviceId = str2;
        this.mMapData = hashMap;
    }

    private MonitorMessage(Parcel parcel) {
        this.mMonitorItemType = parcel.readString();
        this.mDeviceId = parcel.readString();
        this.mBooleanData = parcel.readInt() != 0;
        this.mIntData = parcel.readInt();
        this.mStringData = parcel.readString();
        this.mMapData = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mProductType = parcel.readInt();
    }

    public String getMonitorItemType() {
        return this.mMonitorItemType;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public int getProductType() {
        return this.mProductType;
    }

    public boolean isBooleanData() {
        return this.mBooleanData;
    }

    public int getIntData() {
        return this.mIntData;
    }

    public String getStringData() {
        return this.mStringData;
    }

    public HashMap<String, MonitorMessage> getMapData() {
        return this.mMapData;
    }

    public MonitorMessage setMonitorItemType(String str) {
        this.mMonitorItemType = str;
        return this;
    }

    public MonitorMessage setDeviceId(String str) {
        this.mDeviceId = str;
        return this;
    }

    public MonitorMessage setProductType(int i) {
        this.mProductType = i;
        return this;
    }

    public MonitorMessage setBooleanData(boolean z) {
        this.mBooleanData = z;
        return this;
    }

    public MonitorMessage setIntData(int i) {
        this.mIntData = i;
        return this;
    }

    public MonitorMessage setStringData(String str) {
        this.mStringData = str;
        return this;
    }

    public MonitorMessage setMapData(HashMap<String, MonitorMessage> hashMap) {
        this.mMapData = hashMap;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mMonitorItemType);
        parcel.writeString(this.mDeviceId);
        parcel.writeInt(this.mBooleanData ? 1 : 0);
        parcel.writeInt(this.mIntData);
        parcel.writeString(this.mStringData);
        parcel.writeMap(this.mMapData);
        parcel.writeInt(this.mProductType);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mMonitorItemType = parcel.readString();
        this.mDeviceId = parcel.readString();
        this.mBooleanData = parcel.readInt() != 0;
        this.mIntData = parcel.readInt();
        this.mStringData = parcel.readString();
        this.mMapData = parcel.readHashMap(HashMap.class.getClassLoader());
        this.mProductType = parcel.readInt();
    }
}
