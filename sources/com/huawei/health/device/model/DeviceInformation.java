package com.huawei.health.device.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class DeviceInformation extends FitnessData implements Serializable {
    public static final Parcelable.Creator<DeviceInformation> CREATOR = new Parcelable.Creator<DeviceInformation>() { // from class: com.huawei.health.device.model.DeviceInformation.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: Gx_, reason: merged with bridge method [inline-methods] */
        public DeviceInformation createFromParcel(Parcel parcel) {
            return new DeviceInformation(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DeviceInformation[] newArray(int i) {
            return new DeviceInformation[i];
        }
    };
    private static final long serialVersionUID = 3749276696388994604L;

    public DeviceInformation(Parcel parcel) {
        super(parcel);
    }

    public DeviceInformation() {
        clearData();
        setFitnessDataType(5);
    }

    public String getManufacturerString() {
        return (String) this.mFitnessData.get(20006);
    }

    public void setManufacturerString(String str) {
        this.mFitnessData.put(20006, str);
    }

    public String getModelString() {
        return (String) this.mFitnessData.get(20007);
    }

    public void setModelString(String str) {
        this.mFitnessData.put(20007, str);
    }

    public int getDeviceType() {
        if (this.mFitnessData.containsKey(20005) && (this.mFitnessData.get(20005) instanceof Integer)) {
            return ((Integer) this.mFitnessData.get(20005)).intValue();
        }
        return 0;
    }

    public void setDeviceType(int i) {
        this.mFitnessData.put(20005, Integer.valueOf(i));
    }

    public String getSerialNumber() {
        return String.valueOf(this.mFitnessData.get(20010));
    }

    public void setSerialNumber(String str) {
        this.mFitnessData.put(20010, str);
    }

    public String getHardwareVersion() {
        return String.valueOf(this.mFitnessData.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI)));
    }

    public void setHardwareVersion(String str) {
        this.mFitnessData.put(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
    }

    public String getFirmwareVersion() {
        return String.valueOf(this.mFitnessData.get(20015));
    }

    public void setFirmwareVersion(String str) {
        this.mFitnessData.put(20015, str);
    }

    public String getSoftwareVersion() {
        return String.valueOf(this.mFitnessData.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION)));
    }

    public void setSoftwareVersion(String str) {
        this.mFitnessData.put(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION), str);
    }

    public String getSystemId() {
        return String.valueOf(this.mFitnessData.get(20016));
    }

    public void setSystemId(String str) {
        this.mFitnessData.put(20016, str);
    }

    public String getBleDeviceName() {
        return String.valueOf(this.mFitnessData.get(20014));
    }

    public void setBleDeviceName(String str) {
        this.mFitnessData.put(20014, str);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
