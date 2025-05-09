package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.HiDateUtil;
import health.compact.a.util.LogUtil;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiDeviceInfo implements Parcelable {
    public static final String MODEL_KEY = "model";
    private static final String TAG = "HiDeviceInfo";
    private int deviceId;
    private String deviceMac;
    private String deviceName;
    private String deviceSerialNumber;
    private int deviceType;
    private String deviceUdid;
    private String deviceUniqueCode;
    private String firmwareVersion;
    private String hardwareVersion;
    private String manufacturer;
    private String model;
    private long modifyTime;
    private int priority;
    private String prodId;
    private String softwareVersion;
    private String subProdId;
    public static final Parcelable.Creator<HiDeviceInfo> CREATOR = new Parcelable.Creator<HiDeviceInfo>() { // from class: com.huawei.hihealth.HiDeviceInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buD_, reason: merged with bridge method [inline-methods] */
        public HiDeviceInfo createFromParcel(Parcel parcel) {
            return new HiDeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiDeviceInfo[] newArray(int i) {
            return new HiDeviceInfo[i];
        }
    };
    private static final Integer[] WATCH_PRODUCT = {36, 79, 80, 41, 88};
    private static final Integer[] BAND_PRODUCT = {21, 22, 23, 35, 42, 61, 62, 77, 81, 83, 90, 91, 74, 76, 44};
    private static final Integer[] EARPHONE_PRODUCT = {43, 52, 75};
    private static final Integer[] BODY_FAT_PRODUCT = {31, 27, 48, 56, 57, 73, 82};
    private static final Integer[] BLOOD_SUGAR_PRODUCT = {28, 34, 71, 72};

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiDeviceInfo(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("constructions deviceType illegal");
        }
        this.deviceType = i;
    }

    protected HiDeviceInfo(Parcel parcel) {
        this.deviceId = parcel.readInt();
        this.deviceUniqueCode = parcel.readString();
        this.deviceName = parcel.readString();
        this.deviceType = parcel.readInt();
        this.firmwareVersion = parcel.readString();
        this.hardwareVersion = parcel.readString();
        this.softwareVersion = parcel.readString();
        this.manufacturer = parcel.readString();
        this.model = parcel.readString();
        this.deviceSerialNumber = parcel.readString();
        this.deviceMac = parcel.readString();
        this.priority = parcel.readInt();
        this.modifyTime = parcel.readLong();
        this.prodId = parcel.readString();
        this.subProdId = parcel.readString();
        this.deviceUdid = parcel.readString();
    }

    public int getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(int i) {
        this.deviceId = i;
    }

    public String getDeviceUniqueCode() {
        return this.deviceUniqueCode;
    }

    public void setDeviceUniqueCode(String str) {
        this.deviceUniqueCode = (String) ResultUtils.a(str);
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = (String) ResultUtils.a(str);
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("setDeviceType illegal");
        }
        this.deviceType = i;
    }

    public int getDeviceTypeMapping() {
        if (Arrays.asList(WATCH_PRODUCT).contains(Integer.valueOf(this.deviceType))) {
            return 0;
        }
        if (Arrays.asList(BAND_PRODUCT).contains(Integer.valueOf(this.deviceType))) {
            return 1;
        }
        if (Arrays.asList(EARPHONE_PRODUCT).contains(Integer.valueOf(this.deviceType))) {
            return 2;
        }
        if (Arrays.asList(BODY_FAT_PRODUCT).contains(Integer.valueOf(this.deviceType))) {
            return 3;
        }
        return Arrays.asList(BLOOD_SUGAR_PRODUCT).contains(Integer.valueOf(this.deviceType)) ? 4 : -1;
    }

    public String getDeviceInfoToODMF() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DeviceName", this.deviceName);
            jSONObject.put("DeviceID", this.deviceUniqueCode);
            jSONObject.put("DeviceType", this.deviceType);
            jSONObject.put("DeviceActiveTime", HiDateUtil.m(System.currentTimeMillis()));
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.e(TAG, "getDeviceInfoToODMF JSONException");
            return null;
        }
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
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

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getDeviceSN() {
        return this.deviceSerialNumber;
    }

    public void setDeviceSN(String str) {
        this.deviceSerialNumber = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long j) {
        this.modifyTime = j;
    }

    public String getProdId() {
        return this.prodId;
    }

    public void setProdId(String str) {
        this.prodId = str;
    }

    public void setSubProdId(String str) {
        this.subProdId = str;
    }

    public String getSubProdId() {
        return this.subProdId;
    }

    public String getDeviceUdid() {
        return this.deviceUdid;
    }

    public void setDeviceUdid(String str) {
        this.deviceUdid = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDeviceInfo{, deviceName='");
        stringBuffer.append(this.deviceName).append("', deviceType=");
        stringBuffer.append(this.deviceType);
        stringBuffer.append(", priority=").append(this.priority);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.deviceId);
        parcel.writeString(this.deviceUniqueCode);
        parcel.writeString(this.deviceName);
        parcel.writeInt(this.deviceType);
        parcel.writeString(this.firmwareVersion);
        parcel.writeString(this.hardwareVersion);
        parcel.writeString(this.softwareVersion);
        parcel.writeString(this.manufacturer);
        parcel.writeString(this.model);
        parcel.writeString(this.deviceSerialNumber);
        parcel.writeString(this.deviceMac);
        parcel.writeInt(this.priority);
        parcel.writeLong(this.modifyTime);
        parcel.writeString(this.prodId);
        parcel.writeString(this.subProdId);
        parcel.writeString(this.deviceUdid);
    }
}
