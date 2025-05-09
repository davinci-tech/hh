package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ConnectionInfo implements Parcelable {
    public static final int CONNECTION_MIRROR = 0;
    public static final int CONNECTION_RESOURCE_AUDIO = 2;
    public static final int CONNECTION_RESOURCE_IMAGE = 3;
    public static final int CONNECTION_RESOURCE_VIDEO = 1;
    public static final int CONNECTION_STATUS_CONNECTED = 2;
    public static final int CONNECTION_STATUS_CONNECTING = 1;
    public static final int CONNECTION_STATUS_DISCONNECTED = 0;
    public static final int CONNECTION_STATUS_DISCONNECTING = 5;
    public static final int CONNECTION_STATUS_RTSP_CONNECTING = 3;
    public static final int CONNECTION_STATUS_WAITING_CONFIRM = 4;
    public static final Parcelable.Creator<ConnectionInfo> CREATOR = new Parcelable.Creator<ConnectionInfo>() { // from class: com.huawei.android.airsharing.api.ConnectionInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ConnectionInfo[] newArray(int i) {
            return new ConnectionInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dw_, reason: merged with bridge method [inline-methods] */
        public ConnectionInfo createFromParcel(Parcel parcel) {
            ConnectionInfo connectionInfo = new ConnectionInfo(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            connectionInfo.setProjectionDevice((ProjectionDevice) parcel.readParcelable(ProjectionDevice.class.getClassLoader()));
            return connectionInfo;
        }
    };
    private int mConnectionStatus;
    private String mDeviceName;
    private int mDeviceType;
    private String mIndication;
    private int mMediaInfoType;
    private ProjectionDevice mProjectionDevice = null;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ConnectionInfo(String str, String str2, int i, int i2, int i3) {
        this.mDeviceName = str;
        this.mIndication = str2;
        this.mMediaInfoType = i;
        this.mConnectionStatus = i2;
        this.mDeviceType = i3;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public String getIndication() {
        return this.mIndication;
    }

    public void setIndication(String str) {
        this.mIndication = str;
    }

    public int getMediaInfoType() {
        return this.mMediaInfoType;
    }

    public void setMediaInfoType(int i) {
        this.mMediaInfoType = i;
    }

    public int getConnectionStatus() {
        return this.mConnectionStatus;
    }

    public void setConnectionStatus(int i) {
        this.mConnectionStatus = i;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public ProjectionDevice getProjectionDevice() {
        return this.mProjectionDevice;
    }

    public void setProjectionDevice(ProjectionDevice projectionDevice) {
        this.mProjectionDevice = projectionDevice;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mDeviceName);
        parcel.writeString(this.mIndication);
        parcel.writeInt(this.mMediaInfoType);
        parcel.writeInt(this.mConnectionStatus);
        parcel.writeInt(this.mDeviceType);
        parcel.writeParcelable(this.mProjectionDevice, i);
    }

    public String toString() {
        return "ConnectionInfo[ mediaInfoType:" + this.mMediaInfoType + ", ConnectionStatus:" + this.mConnectionStatus + ", DeviceType: " + this.mDeviceType + ", ProjectionDevice: " + this.mProjectionDevice + " ]";
    }
}
