package com.huawei.devicesdk.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.blt;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.huawei.devicesdk.entity.DeviceInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: rU_, reason: merged with bridge method [inline-methods] */
        public DeviceInfo createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceName(parcel.readString());
            deviceInfo.setDeviceSn(parcel.readString());
            deviceInfo.setDeviceMac(parcel.readString());
            deviceInfo.setDeviceBtType(parcel.readInt());
            deviceInfo.setDeviceConnectState(parcel.readInt());
            deviceInfo.setBtVersion(parcel.readString());
            deviceInfo.setDeviceType(parcel.readInt());
            deviceInfo.setDeviceVersion(parcel.readString());
            deviceInfo.setDeviceSoftVersion(parcel.readString());
            deviceInfo.setDeviceMode(parcel.readString());
            deviceInfo.setCertMode(parcel.readString());
            deviceInfo.setPowerSaveMode(parcel.readInt());
            deviceInfo.setDeviceBtMode(parcel.readString());
            deviceInfo.setDeviceVersionType(parcel.readInt());
            deviceInfo.setDfxDeviceUdid(parcel.readString());
            deviceInfo.setDfxDeviceUdidParameter(parcel.readString());
            deviceInfo.setDeviceFactoryReset(parcel.readInt());
            deviceInfo.setDeviceOtaPackageName(parcel.readString());
            deviceInfo.setDeviceHilinkId(parcel.readString());
            deviceInfo.setIdToServerType(parcel.readInt());
            deviceInfo.setUdid(parcel.readString());
            deviceInfo.setPairingTime(parcel.readLong());
            deviceInfo.setLastConnectedTime(parcel.readLong());
            deviceInfo.setUsing(parcel.readByte() != 0);
            deviceInfo.setWearEngineDeviceId(parcel.readString());
            deviceInfo.setReconnect(parcel.readByte() != 0);
            deviceInfo.setCountryCode(parcel.readString());
            deviceInfo.setEmuiVersion(parcel.readString());
            deviceInfo.setAutoDetectSwitchStatus(parcel.readInt());
            deviceInfo.setFootWearPosition(parcel.readInt());
            deviceInfo.setMultiLinkBleMac(parcel.readString());
            deviceInfo.setMultiLink(parcel.readInt() == 1);
            deviceInfo.setFieldList(parcel.createStringArrayList());
            deviceInfo.setDeviceEnterpriseVersion(parcel.readString());
            deviceInfo.setDeviceSplicingProductVersion(parcel.readString());
            deviceInfo.setDeviceOStVersion(parcel.readString());
            deviceInfo.setDeviceOtaSignatureLength(parcel.readInt());
            deviceInfo.setDeviceOtaAreaType(parcel.readInt());
            deviceInfo.setSportVersion(parcel.readInt());
            deviceInfo.setSaleInfo(parcel.readString());
            deviceInfo.setPsiSignature(parcel.readString());
            deviceInfo.setDeviceSubProdId(parcel.readString());
            deviceInfo.setDemoWatch(parcel.readByte());
            deviceInfo.setSingleFrameDevice(parcel.readInt());
            return deviceInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DeviceInfo[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new DeviceInfo[i];
        }
    };
    private int autoDetectSwitchStatus;
    private String btVersion;
    private String certMode;
    private String countryCode;
    private String deviceBtMode;
    private int deviceBtType;
    private String deviceEnterpriseVersion;
    private int deviceFactoryReset;
    private String deviceHilinkId;
    private String deviceMac;
    private String deviceMode;
    private String deviceName;
    private String deviceOStVersion;
    private String deviceOtaPackageName;
    private int deviceOtaSignatureLength;
    private String deviceSn;
    private String deviceSoftVersion;
    private String deviceSplicingProductVersion;
    private String deviceSubProdId;
    private int deviceType;
    private String deviceVersion;
    private String dfxDeviceUdid;
    private String dfxDeviceUdidParameter;
    private String emuiVersion;
    private int footWearPosition;
    private int idToServerType;
    private byte isDemoWatch;
    private boolean isMultiLink;
    private boolean isUsing;
    private long lastConnectedTime;
    private String mWearEngineDeviceId;
    private String multiLinkBleMac;
    private long pairingTime;
    private int powerSaveMode;
    private String psiSignature;
    private String saleInfo;
    private int singleFrameDevice;
    private byte triggerType;
    private String udid;
    private volatile int deviceConnectState = 0;
    private int deviceVersionType = -1;
    private int sportVersion = -1;
    private int deviceOtaAreaType = -1;
    private boolean isReconnect = false;
    private List<String> fieldList = new ArrayList(16);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte getIsDemoWatch() {
        return this.isDemoWatch;
    }

    public void setDemoWatch(byte b) {
        this.isDemoWatch = b;
    }

    public int getAutoDetectSwitchStatus() {
        return this.autoDetectSwitchStatus;
    }

    public void setAutoDetectSwitchStatus(int i) {
        this.autoDetectSwitchStatus = i;
    }

    public int getFootWearPosition() {
        return this.footWearPosition;
    }

    public void setFootWearPosition(int i) {
        this.footWearPosition = i;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public void setIdToServerType(int i) {
        this.idToServerType = i;
    }

    public int getIdToServerType() {
        return this.idToServerType;
    }

    public void setDeviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("setDeviceName: ", str);
        }
        this.deviceName = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public void setDeviceBtType(int i) {
        this.deviceBtType = i;
    }

    public int getDeviceBtType() {
        return this.deviceBtType;
    }

    public int getDeviceConnectState() {
        return this.deviceConnectState;
    }

    public void setDeviceConnectState(int i) {
        if (this.deviceConnectState != i) {
            if (this.deviceConnectState != 0) {
                LogUtil.c("setDeviceConnectState: ", Integer.valueOf(this.deviceConnectState), "to ", Integer.valueOf(i));
            }
            this.deviceConnectState = i;
        }
    }

    public void setBtVersion(String str) {
        this.btVersion = str;
    }

    public String getBtVersion() {
        return this.btVersion;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceSoftVersion(String str) {
        this.deviceSoftVersion = str;
    }

    public String getDeviceSoftVersion() {
        return this.deviceSoftVersion;
    }

    public void setDeviceFactoryReset(int i) {
        this.deviceFactoryReset = i;
    }

    public int getDeviceFactoryReset() {
        return this.deviceFactoryReset;
    }

    public void setDeviceMode(String str) {
        this.deviceMode = str;
    }

    public String getDeviceMode() {
        return this.deviceMode;
    }

    public void setCertMode(String str) {
        this.certMode = str;
    }

    public String getCertMode() {
        return this.certMode;
    }

    public void setDeviceBtMode(String str) {
        this.deviceBtMode = str;
    }

    public String getDeviceBtMode() {
        return this.deviceBtMode;
    }

    public void setDfxDeviceUdid(String str) {
        this.dfxDeviceUdid = str;
    }

    public String getDfxDeviceUdid() {
        return this.dfxDeviceUdid;
    }

    public void setPowerSaveMode(int i) {
        this.powerSaveMode = i;
    }

    public int getPowerSaveMode() {
        return this.powerSaveMode;
    }

    public void setDfxDeviceUdidParameter(String str) {
        this.dfxDeviceUdidParameter = str;
    }

    public String getDfxDeviceUdidParameter() {
        return this.dfxDeviceUdidParameter;
    }

    public void setDeviceOtaPackageName(String str) {
        this.deviceOtaPackageName = str;
    }

    public String getDeviceOtaPackageName() {
        return this.deviceOtaPackageName;
    }

    public void setDeviceOtaAreaType(int i) {
        this.deviceOtaAreaType = i;
    }

    public int getDeviceOtaAreaType() {
        return this.deviceOtaAreaType;
    }

    public void setDeviceHilinkId(String str) {
        this.deviceHilinkId = str;
    }

    public String getDeviceHilinkId() {
        return this.deviceHilinkId;
    }

    public void setDeviceVersionType(int i) {
        this.deviceVersionType = i;
    }

    public int getDeviceVersionType() {
        return this.deviceVersionType;
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String str) {
        this.udid = str;
    }

    public long getPairingTime() {
        return this.pairingTime;
    }

    public void setPairingTime(long j) {
        this.pairingTime = j;
    }

    public long getLastConnectedTime() {
        return this.lastConnectedTime;
    }

    public void setLastConnectedTime(long j) {
        this.lastConnectedTime = j;
    }

    public boolean isUsing() {
        return this.isUsing;
    }

    public void setUsing(boolean z) {
        this.isUsing = z;
    }

    public String getWearEngineDeviceId() {
        return this.mWearEngineDeviceId;
    }

    public void setWearEngineDeviceId(String str) {
        this.mWearEngineDeviceId = str;
    }

    public int getSportVersion() {
        return this.sportVersion;
    }

    public void setSportVersion(int i) {
        this.sportVersion = i;
    }

    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || (str = this.deviceMac) == null || !(obj instanceof DeviceInfo)) {
            return false;
        }
        return str.equals(((DeviceInfo) obj).deviceMac);
    }

    public int hashCode() {
        String str = this.deviceMac;
        if (str == null) {
            return -1;
        }
        return str.hashCode();
    }

    public boolean isReconnect() {
        return this.isReconnect;
    }

    public void setReconnect(boolean z) {
        this.isReconnect = z;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getEmuiVersion() {
        return this.emuiVersion;
    }

    public void setEmuiVersion(String str) {
        this.emuiVersion = str;
    }

    public boolean isMultiLink() {
        return this.isMultiLink;
    }

    public void setMultiLink(boolean z) {
        this.isMultiLink = z;
    }

    public String getMultiLinkBleMac() {
        return this.multiLinkBleMac;
    }

    public void setMultiLinkBleMac(String str) {
        this.multiLinkBleMac = str;
    }

    public List<String> getFieldList() {
        return this.fieldList;
    }

    public void setFieldList(List<String> list) {
        this.fieldList = list;
    }

    public String getDeviceEnterpriseVersion() {
        return this.deviceEnterpriseVersion;
    }

    public void setDeviceEnterpriseVersion(String str) {
        this.deviceEnterpriseVersion = str;
    }

    public String getDeviceSplicingProductVersion() {
        return this.deviceSplicingProductVersion;
    }

    public void setDeviceSplicingProductVersion(String str) {
        this.deviceSplicingProductVersion = str;
    }

    public String getDeviceOStVersion() {
        return this.deviceOStVersion;
    }

    public void setDeviceOStVersion(String str) {
        this.deviceOStVersion = str;
    }

    public int getDeviceOtaSignatureLength() {
        return this.deviceOtaSignatureLength;
    }

    public void setDeviceOtaSignatureLength(int i) {
        this.deviceOtaSignatureLength = i;
    }

    public byte getTriggerType() {
        return this.triggerType;
    }

    public void setTriggerType(byte b) {
        this.triggerType = b;
    }

    public String getSaleInfo() {
        return this.saleInfo;
    }

    public void setSaleInfo(String str) {
        this.saleInfo = str;
    }

    public String getPsiSignature() {
        return this.psiSignature;
    }

    public void setPsiSignature(String str) {
        this.psiSignature = str;
    }

    public String getDeviceSubProdId() {
        return this.deviceSubProdId;
    }

    public void setDeviceSubProdId(String str) {
        this.deviceSubProdId = str;
    }

    public int getSingleFrameDevice() {
        return this.singleFrameDevice;
    }

    public void setSingleFrameDevice(int i) {
        this.singleFrameDevice = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceName);
        parcel.writeString(this.deviceSn);
        parcel.writeString(this.deviceMac);
        parcel.writeInt(this.deviceBtType);
        parcel.writeInt(this.deviceConnectState);
        parcel.writeString(this.btVersion);
        parcel.writeInt(this.deviceType);
        parcel.writeString(this.deviceVersion);
        parcel.writeString(this.deviceSoftVersion);
        parcel.writeString(this.deviceMode);
        parcel.writeString(this.certMode);
        parcel.writeInt(this.powerSaveMode);
        parcel.writeString(this.deviceBtMode);
        parcel.writeInt(this.deviceVersionType);
        parcel.writeString(this.dfxDeviceUdid);
        parcel.writeString(this.dfxDeviceUdidParameter);
        parcel.writeInt(this.deviceFactoryReset);
        parcel.writeString(this.deviceOtaPackageName);
        parcel.writeString(this.deviceHilinkId);
        parcel.writeInt(this.idToServerType);
        parcel.writeString(this.udid);
        parcel.writeLong(this.pairingTime);
        parcel.writeLong(this.lastConnectedTime);
        parcel.writeByte(this.isUsing ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mWearEngineDeviceId);
        parcel.writeByte(this.isReconnect ? (byte) 1 : (byte) 0);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.emuiVersion);
        parcel.writeInt(this.autoDetectSwitchStatus);
        parcel.writeInt(this.footWearPosition);
        parcel.writeString(this.multiLinkBleMac);
        parcel.writeInt(this.isMultiLink ? 1 : 0);
        parcel.writeStringList(this.fieldList);
        parcel.writeString(this.deviceEnterpriseVersion);
        parcel.writeString(this.deviceSplicingProductVersion);
        parcel.writeString(this.deviceOStVersion);
        parcel.writeInt(this.deviceOtaSignatureLength);
        parcel.writeInt(this.deviceOtaAreaType);
        parcel.writeInt(this.sportVersion);
        parcel.writeString(this.saleInfo);
        parcel.writeString(this.psiSignature);
        parcel.writeString(this.deviceSubProdId);
        parcel.writeByte(this.isDemoWatch);
        parcel.writeInt(this.singleFrameDevice);
    }

    public void readFromParcel(Parcel parcel) {
        readFromParcelBase(parcel);
    }

    protected void readFromParcelBase(Parcel parcel) {
        this.deviceName = parcel.readString();
        this.deviceSn = parcel.readString();
        this.deviceMac = parcel.readString();
        this.deviceBtType = parcel.readInt();
        this.deviceConnectState = parcel.readInt();
        this.btVersion = parcel.readString();
        this.deviceType = parcel.readInt();
        this.deviceVersion = parcel.readString();
        this.deviceSoftVersion = parcel.readString();
        this.deviceMode = parcel.readString();
        this.certMode = parcel.readString();
        this.powerSaveMode = parcel.readInt();
        this.deviceBtMode = parcel.readString();
        this.deviceVersionType = parcel.readInt();
        this.dfxDeviceUdid = parcel.readString();
        this.dfxDeviceUdidParameter = parcel.readString();
        this.deviceFactoryReset = parcel.readInt();
        this.deviceOtaPackageName = parcel.readString();
        this.deviceHilinkId = parcel.readString();
        this.idToServerType = parcel.readInt();
        this.udid = parcel.readString();
        this.pairingTime = parcel.readLong();
        this.lastConnectedTime = parcel.readLong();
        this.isUsing = parcel.readByte() == 1;
        this.mWearEngineDeviceId = parcel.readString();
        this.isReconnect = parcel.readByte() == 1;
        this.countryCode = parcel.readString();
        this.emuiVersion = parcel.readString();
        this.autoDetectSwitchStatus = parcel.readInt();
        this.footWearPosition = parcel.readInt();
        this.multiLinkBleMac = parcel.readString();
        this.isMultiLink = parcel.readInt() == 1;
        this.fieldList = parcel.createStringArrayList();
        this.deviceEnterpriseVersion = parcel.readString();
        this.deviceSplicingProductVersion = parcel.readString();
        this.deviceOStVersion = parcel.readString();
        this.deviceOtaSignatureLength = parcel.readInt();
        this.deviceOtaAreaType = parcel.readInt();
        this.sportVersion = parcel.readInt();
        this.saleInfo = parcel.readString();
        this.psiSignature = parcel.readString();
        this.deviceSubProdId = parcel.readString();
        this.isDemoWatch = parcel.readByte();
        this.singleFrameDevice = parcel.readInt();
    }

    public String toString() {
        return "DeviceInfo{deviceName='" + this.deviceName + "', deviceSn='" + blt.b(this.deviceSn) + "', deviceMac='" + blt.b(this.deviceMac) + "', deviceBtType=" + this.deviceBtType + ", deviceConnectState=" + this.deviceConnectState + ", btVersion='" + this.btVersion + "', deviceType=" + this.deviceType + ", deviceVersion='" + this.deviceVersion + "', deviceSoftVersion='" + this.deviceSoftVersion + "', deviceMode='" + this.deviceMode + "', certMode='" + this.certMode + "', powerSaveMode=" + this.powerSaveMode + ", deviceBtMode='" + this.deviceBtMode + "', deviceVersionType=" + this.deviceVersionType + ", dfxDeviceUdid='" + this.dfxDeviceUdid + "', dfxDeviceUdidParameter='" + this.dfxDeviceUdidParameter + "', deviceFactoryReset=" + this.deviceFactoryReset + ", deviceOtaPackageName='" + this.deviceOtaPackageName + "', deviceHilinkId='" + this.deviceHilinkId + "', idToServerType=" + this.idToServerType + ", udid='" + blt.b(this.udid) + "', pairingTime=" + this.pairingTime + ", lastConnectedTime=" + this.lastConnectedTime + ", isUsing=" + this.isUsing + ", mWearEngineDeviceId='" + this.mWearEngineDeviceId + "', isReconnect=" + this.isReconnect + ", countryCode='" + this.countryCode + "', emuiVersion='" + this.emuiVersion + "', isMultiLink=" + this.isMultiLink + ", autoDetectSwitchStatus=" + this.autoDetectSwitchStatus + ", footWearPosition=" + this.footWearPosition + ", multiLinkBleMac='" + this.multiLinkBleMac + "', fieldList=" + this.fieldList + ", deviceEnterpriseVersion='" + this.deviceEnterpriseVersion + "', deviceSplicingProductVersion='" + this.deviceSplicingProductVersion + "', deviceOStVersion='" + this.deviceOStVersion + ", deviceOtaAreaType='" + this.deviceOtaAreaType + ", sportVersion='" + this.sportVersion + '}';
    }
}
