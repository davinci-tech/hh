package com.huawei.hwbtsdk.btdatatype.datatype;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class BluetoothDeviceNode implements Parcelable {
    public static final Parcelable.Creator<BluetoothDeviceNode> CREATOR = new Parcelable.Creator<BluetoothDeviceNode>() { // from class: com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bDt_, reason: merged with bridge method [inline-methods] */
        public BluetoothDeviceNode createFromParcel(Parcel parcel) {
            return new BluetoothDeviceNode(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public BluetoothDeviceNode[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new BluetoothDeviceNode[i];
        }
    };
    private BluetoothDevice btDevice;
    private int deviceType;
    private String displayName;
    private String nodeId;
    private String recordName;
    private int rssi;
    private long timeStamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected BluetoothDeviceNode(Parcel parcel) {
        this.nodeId = "";
        this.displayName = "";
        this.recordName = "";
        this.deviceType = 0;
        this.nodeId = parcel.readString();
        this.displayName = parcel.readString();
        this.btDevice = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.rssi = parcel.readInt();
        this.timeStamp = parcel.readLong();
        this.recordName = parcel.readString();
        this.deviceType = parcel.readInt();
    }

    public BluetoothDeviceNode() {
        this.nodeId = "";
        this.displayName = "";
        this.recordName = "";
        this.deviceType = 0;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String str) {
        this.nodeId = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public BluetoothDevice getBtDevice() {
        return this.btDevice;
    }

    public void setBtDevice(BluetoothDevice bluetoothDevice) {
        this.btDevice = bluetoothDevice;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public String getRecordName() {
        return this.recordName;
    }

    public void setRecordName(String str) {
        this.recordName = str;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nodeId);
        parcel.writeString(this.displayName);
        parcel.writeParcelable(this.btDevice, i);
        parcel.writeInt(this.rssi);
        parcel.writeLong(this.timeStamp);
        parcel.writeString(this.recordName);
        parcel.writeInt(this.deviceType);
    }
}
