package com.huawei.android.airsharing.api;

import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.wz;
import java.util.Objects;

/* loaded from: classes2.dex */
public class ProjectionDevice implements Parcelable {
    public static final Parcelable.Creator<ProjectionDevice> CREATOR = new Parcelable.Creator<ProjectionDevice>() { // from class: com.huawei.android.airsharing.api.ProjectionDevice.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ProjectionDevice[] newArray(int i) {
            return new ProjectionDevice[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dR_, reason: merged with bridge method [inline-methods] */
        public ProjectionDevice createFromParcel(Parcel parcel) {
            ProjectionDevice projectionDevice = new ProjectionDevice(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
            projectionDevice.setLocalIpAddress(parcel.readString());
            projectionDevice.setRemoteIpAddress(parcel.readString());
            projectionDevice.setConnectType(parcel.readInt());
            if (wz.c().b(Binder.getCallingPid(), 12)) {
                int readInt = parcel.readInt();
                if (readInt >= 16 && readInt <= 1024) {
                    byte[] bArr = new byte[readInt];
                    parcel.readByteArray(bArr);
                    projectionDevice.setSessionKey(bArr);
                }
            } else {
                projectionDevice.setSessionKey(parcel.createByteArray());
            }
            projectionDevice.setProjectionScene(parcel.readInt());
            projectionDevice.setDeviceSubtype(parcel.readInt());
            if (wz.c().b(Binder.getCallingPid(), 9)) {
                projectionDevice.setProductName(parcel.readString());
            }
            if (wz.c().b(Binder.getCallingPid(), 10)) {
                projectionDevice.setNetworkId(parcel.readString());
                projectionDevice.setTransferMode(parcel.readInt());
            }
            if (wz.c().b(Binder.getCallingPid(), 13)) {
                projectionDevice.setSupportedMediaTypes(parcel.readInt());
            }
            return projectionDevice;
        }
    };
    private static final int SESSION_KEY_MAX_LEN = 1024;
    private static final int SESSION_KEY_MIN_LEN = 16;
    private int mCapability;
    private int mConnectType;
    private String mDeviceName;
    private int mDeviceSubtype;
    private int mDeviceType;
    private String mIndication;
    private String mLocalIpAddress;
    private String mNetworkId;
    private int mPriority;
    private String mProductName;
    private int mProjectionScene;
    private String mRemoteIpAddress;
    private int mSdkClientPid;
    private byte[] mSessionKeys;
    private int mSupportedMediaTypes;
    private int mTransferMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProjectionDevice(String str, String str2, int i, int i2) {
        this(str, str2, -1, i, i2, 1);
    }

    public ProjectionDevice(String str, String str2, int i, int i2, int i3) {
        this(str, str2, i, i2, i3, 1);
    }

    public ProjectionDevice(String str, String str2, int i, int i2, int i3, int i4) {
        this.mSdkClientPid = Binder.getCallingPid();
        this.mTransferMode = 0;
        this.mSessionKeys = new byte[0];
        this.mSupportedMediaTypes = 0;
        this.mDeviceName = str;
        this.mIndication = str2;
        this.mPriority = i;
        this.mCapability = i2;
        this.mDeviceType = i3;
        this.mProjectionScene = i4;
        this.mConnectType = 0;
        this.mDeviceSubtype = 0;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public void setProductName(String str) {
        this.mProductName = str;
    }

    public String getIndication() {
        return this.mIndication;
    }

    public void setIndication(String str) {
        this.mIndication = str;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int i) {
        this.mCapability = i;
    }

    public void addCapability(int i) {
        this.mCapability = i | this.mCapability;
    }

    public void removeCapability(int i) {
        this.mCapability = (~i) & this.mCapability;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public int getDeviceSubtype() {
        return this.mDeviceSubtype;
    }

    public void setDeviceSubtype(int i) {
        this.mDeviceSubtype = i;
    }

    @Deprecated
    public boolean isHuaweiTv() {
        return this.mDeviceType == 2;
    }

    public boolean isCapabilityMixedDevice() {
        return this.mDeviceType == 2;
    }

    public void setLocalIpAddress(String str) {
        this.mLocalIpAddress = str;
    }

    public String getLocalIpAddress() {
        return this.mLocalIpAddress;
    }

    public void setRemoteIpAddress(String str) {
        this.mRemoteIpAddress = str;
    }

    public String getRemoteIpAddress() {
        return this.mRemoteIpAddress;
    }

    public void setConnectType(int i) {
        if (i == 1 && (this.mCapability & 32) != 0) {
            this.mConnectType = i;
        } else {
            this.mConnectType = 0;
        }
    }

    public int getConnectType() {
        return this.mConnectType;
    }

    public int getProjectionScene() {
        return this.mProjectionScene;
    }

    public void setProjectionScene(int i) {
        this.mProjectionScene = i;
    }

    public void setSdkClientPid(int i) {
        this.mSdkClientPid = i;
    }

    public boolean setSessionKey(byte[] bArr) {
        if (bArr == null || bArr.length < 16) {
            return false;
        }
        byte[] bArr2 = this.mSessionKeys;
        if (bArr2 != null && bArr2.length > 0) {
            System.arraycopy(new byte[bArr2.length], 0, bArr2, 0, bArr2.length);
        }
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        this.mSessionKeys = bArr3;
        System.arraycopy(bArr, 0, bArr3, 0, length);
        return true;
    }

    public byte[] getSessionKey() {
        return (byte[]) this.mSessionKeys.clone();
    }

    public void setNetworkId(String str) {
        this.mNetworkId = str;
    }

    public void setTransferMode(int i) {
        this.mTransferMode = i;
    }

    public String getNetworkId() {
        return this.mNetworkId;
    }

    public int getTransferMode() {
        return this.mTransferMode;
    }

    public void setSupportedMediaTypes(int i) {
        this.mSupportedMediaTypes = i;
    }

    public int getSupportedMediaTypes() {
        return this.mSupportedMediaTypes;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mDeviceName);
        parcel.writeString(this.mIndication);
        parcel.writeInt(this.mPriority);
        parcel.writeInt(this.mCapability);
        parcel.writeInt(this.mDeviceType);
        parcel.writeString(this.mLocalIpAddress);
        parcel.writeString(this.mRemoteIpAddress);
        parcel.writeInt(this.mConnectType);
        if (wz.c().b(this.mSdkClientPid, 12)) {
            parcel.writeInt(this.mSessionKeys.length);
            byte[] bArr = this.mSessionKeys;
            if (bArr.length >= 16 && bArr.length <= 1024) {
                parcel.writeByteArray(bArr);
            }
        } else {
            parcel.writeByteArray(this.mSessionKeys);
        }
        parcel.writeInt(this.mProjectionScene);
        parcel.writeInt(this.mDeviceSubtype);
        if (wz.c().b(this.mSdkClientPid, 9)) {
            parcel.writeString(this.mProductName);
        }
        if (wz.c().b(this.mSdkClientPid, 10)) {
            parcel.writeString(this.mNetworkId);
            parcel.writeInt(this.mTransferMode);
        }
        if (wz.c().b(this.mSdkClientPid, 13)) {
            parcel.writeInt(this.mSupportedMediaTypes);
        }
    }

    public String toString() {
        return "ProjectionDevice{priority:" + this.mPriority + ", transferMode:" + this.mTransferMode + ", capability:" + this.mCapability + ", deviceType:" + this.mDeviceType + ", subtype:" + this.mDeviceSubtype + ", connectType: " + this.mConnectType + '}';
    }

    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof ProjectionDevice)) {
            return false;
        }
        ProjectionDevice projectionDevice = (ProjectionDevice) obj;
        String str2 = this.mDeviceName;
        return str2 != null && str2.equals(projectionDevice.getDeviceName()) && (str = this.mIndication) != null && str.equals(projectionDevice.getIndication()) && this.mDeviceType == projectionDevice.getDeviceType() && this.mDeviceSubtype == projectionDevice.getDeviceSubtype();
    }

    public int hashCode() {
        return Objects.hash(this.mDeviceName, this.mIndication, Integer.valueOf(this.mDeviceType), Integer.valueOf(this.mDeviceSubtype));
    }
}
