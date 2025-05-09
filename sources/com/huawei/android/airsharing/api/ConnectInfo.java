package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ConnectInfo implements Parcelable {
    public static final Parcelable.Creator<ConnectInfo> CREATOR = new Parcelable.Creator<ConnectInfo>() { // from class: com.huawei.android.airsharing.api.ConnectInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ConnectInfo[] newArray(int i) {
            return new ConnectInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dv_, reason: merged with bridge method [inline-methods] */
        public ConnectInfo createFromParcel(Parcel parcel) {
            return new ConnectInfo((ProjectionDevice) parcel.readValue(ProjectionDevice.class.getClassLoader()), EProjectionMode.valueOf(parcel.readString()), parcel.readString());
        }
    };
    private String mAppId;
    private ProjectionDevice mProjectionDevice;
    private EProjectionMode mProjectionMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ConnectInfo(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode, String str) {
        this.mProjectionDevice = null;
        this.mProjectionMode = EProjectionMode.MIRROR;
        this.mAppId = null;
        if (projectionDevice == null) {
            throw new IllegalArgumentException("projectionDevice can't be null");
        }
        if (eProjectionMode == null) {
            throw new IllegalArgumentException("projectionMode can't be null");
        }
        this.mProjectionDevice = projectionDevice;
        this.mProjectionMode = eProjectionMode;
        this.mAppId = str;
    }

    public ConnectInfo(ProjectionDevice projectionDevice) {
        this(projectionDevice, EProjectionMode.MIRROR, null);
    }

    public ConnectInfo(ProjectionDevice projectionDevice, EProjectionMode eProjectionMode) {
        this(projectionDevice, eProjectionMode, null);
    }

    public ProjectionDevice getProjectionDevice() {
        return this.mProjectionDevice;
    }

    public void setProjectionDevice(ProjectionDevice projectionDevice) {
        this.mProjectionDevice = projectionDevice;
    }

    public EProjectionMode getProjectionMode() {
        return this.mProjectionMode;
    }

    public void setProjectionMode(EProjectionMode eProjectionMode) {
        this.mProjectionMode = eProjectionMode;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public void setAppId(String str) {
        this.mAppId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeValue(this.mProjectionDevice);
        parcel.writeString(this.mProjectionMode.toString());
        parcel.writeString(this.mAppId);
    }

    public String toString() {
        return "{" + this.mProjectionDevice.toString() + "}, {mProjectionMode: " + this.mProjectionMode.toString() + "}, {AppId: ***}";
    }
}
