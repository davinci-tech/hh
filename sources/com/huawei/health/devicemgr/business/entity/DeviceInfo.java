package com.huawei.health.devicemgr.business.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import defpackage.blq;
import defpackage.bmb;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.huawei.health.devicemgr.business.entity.DeviceInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: QG_, reason: merged with bridge method [inline-methods] */
        public DeviceInfo createFromParcel(Parcel parcel) {
            return DeviceInfo.getDeviceInfo(parcel);
        }
    };
    private static final int DEFAULT_VALUE = -1;
    private String mCountryCode;
    private String mDeviceEnterpriseVersion;
    private int mDeviceFactoryReset;
    private String mDeviceOStVersion;
    private String mDeviceOtaPackageName;
    private int mDeviceOtaSignatureLength;
    private String mDeviceReportInfo;
    private String mDeviceSplicingProductVersion;
    private String mDeviceSubProdId;
    private String mEmuiVersion;
    private String mExpandCapability;
    private byte mIsDemoWatch;
    private long mLastConnectedTime;
    private String mMultiLinkBleMac;
    private int mPowerSaveModel;
    private String mPsiSignature;
    private String mSaleInfo;
    private int mSingleFrameDevice;
    private int mSupportAccountSwitch;
    private String mUdidFromDevice;
    private String mUnConvertedUdid;
    private String mWearEngineDeviceId;
    private String mDeviceName = "";
    private String mProductId = "";
    private String mDeviceIdentify = "";
    private String mDeviceId = "";
    private String mHiLinkDeviceId = "";
    private int mDeviceProtocol = -1;
    private int mProductType = -1;
    private String mDeviceModelName = "";
    private int mDeviceActiveState = 0;
    private int mDeviceConnectState = 0;
    private int mEncryptType = 0;

    @SerializedName("mDeviceBTType")
    private int mDeviceBlueToothType = -1;

    @SerializedName("UUID")
    private String mUuid = "";

    @SerializedName("mDevIdType")
    private int mDeviceIdType = 0;
    private int mAuthVersion = -1;
    private int mAutoDetectSwitchStatus = -1;
    private int mFootWearPosition = -1;

    @SerializedName("bTversion")
    private String mBluetoothVersion = "";
    private String mSoftVersion = "";
    private String mNodeId = "";
    private String mCertModel = "";
    private int mVersionType = -1;
    private String mManufacture = "";
    private String mDeviceUdid = "";
    private boolean isMultiLink = false;
    private String mRelationship = "";

    @SerializedName("fieldList")
    private List<String> mFieldList = new ArrayList(16);
    private int mDeviceOtaAreaType = -1;
    private int mSportVersion = -1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDeviceReportInfo() {
        return this.mDeviceReportInfo;
    }

    public void setDeviceReportInfo(String str) {
        this.mDeviceReportInfo = str;
    }

    public void setCountryCode(String str) {
        this.mCountryCode = str;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getEmuiVersion() {
        return this.mEmuiVersion;
    }

    public void setEmuiVersion(String str) {
        this.mEmuiVersion = str;
    }

    public int getDeviceFactoryReset() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceFactoryReset))).intValue();
    }

    public void setDeviceFactoryReset(int i) {
        this.mDeviceFactoryReset = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getmDeviceId() {
        return this.mDeviceId;
    }

    public void setmDeviceId(String str) {
        this.mDeviceId = str;
    }

    public String getBluetoothVersion() {
        return this.mBluetoothVersion;
    }

    public void setBluetoothVersion(String str) {
        this.mBluetoothVersion = str;
    }

    public String getSoftVersion() {
        return this.mSoftVersion;
    }

    public void setSoftVersion(String str) {
        this.mSoftVersion = str;
    }

    public String getUuid() {
        return (String) bmb.d(this.mUuid);
    }

    public void setUuid(String str) {
        this.mUuid = (String) bmb.d(str);
    }

    public int getDeviceIdType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceIdType))).intValue();
    }

    public void setDeviceIdType(int i) {
        this.mDeviceIdType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getHiLinkDeviceId() {
        return this.mHiLinkDeviceId;
    }

    public void setHiLinkDeviceId(String str) {
        this.mHiLinkDeviceId = str;
    }

    public String getSecurityDeviceId() {
        if (this.mDeviceIdType == 1) {
            return blq.d(this.mUuid);
        }
        if (this.mProductType >= 72) {
            return blq.d(this.mUuid);
        }
        return (String) bmb.d(this.mDeviceIdentify);
    }

    public String getSecurityUuid() {
        int i;
        int i2 = this.mDeviceIdType;
        if (i2 == 1 || (i = this.mProductType) == 10) {
            if (i2 == 1) {
                return blq.d(this.mUuid);
            }
            return this.mUuid;
        }
        if (i >= 72) {
            return blq.d(this.mUuid);
        }
        return this.mDeviceIdentify;
    }

    public void setDeviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("setDeviceName: ", str);
        }
        this.mDeviceName = (String) bmb.d(str);
    }

    public String getDeviceName() {
        return (String) bmb.d(this.mDeviceName);
    }

    public void setProductId(String str) {
        this.mProductId = (String) bmb.d(str);
    }

    public String getProductId() {
        return (String) bmb.d(this.mProductId);
    }

    public void setDeviceIdentify(String str) {
        this.mDeviceIdentify = (String) bmb.d(str);
    }

    public String getDeviceIdentify() {
        return (String) bmb.d(this.mDeviceIdentify);
    }

    public void setDeviceProtocol(int i) {
        this.mDeviceProtocol = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceProtocol() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceProtocol))).intValue();
    }

    public void setProductType(int i) {
        this.mProductType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getProductType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mProductType))).intValue();
    }

    public void setDeviceModel(String str) {
        this.mDeviceModelName = (String) bmb.d(str);
    }

    public String getDeviceModel() {
        return (String) bmb.d(this.mDeviceModelName);
    }

    public void setDeviceActiveState(int i) {
        this.mDeviceActiveState = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceActiveState() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceActiveState))).intValue();
    }

    public void setDeviceConnectState(int i) {
        this.mDeviceConnectState = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceConnectState() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceConnectState))).intValue();
    }

    public int getEncryptType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mEncryptType))).intValue();
    }

    public void setEncryptType(int i) {
        this.mEncryptType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceBluetoothType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mDeviceBlueToothType))).intValue();
    }

    public void setDeviceBluetoothType(int i) {
        this.mDeviceBlueToothType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getNodeId() {
        return (String) bmb.d(this.mNodeId);
    }

    public void setNodeId(String str) {
        this.mNodeId = (String) bmb.d(str);
    }

    public String getCertModel() {
        return (String) bmb.d(this.mCertModel);
    }

    public void setCertModel(String str) {
        this.mCertModel = (String) bmb.d(str);
    }

    public int getVersionType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mVersionType))).intValue();
    }

    public void setVersionType(int i) {
        this.mVersionType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getManufacture() {
        return (String) bmb.d(this.mManufacture);
    }

    public void setManufacture(String str) {
        this.mManufacture = (String) bmb.d(str);
    }

    public String getDeviceUdid() {
        return (String) bmb.d(this.mDeviceUdid);
    }

    public void setDeviceUdid(String str) {
        this.mDeviceUdid = (String) bmb.d(str);
    }

    public int getAuthVersion() {
        return ((Integer) bmb.d(Integer.valueOf(this.mAuthVersion))).intValue();
    }

    public void setAuthVersion(int i) {
        this.mAuthVersion = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getAutoDetectSwitchStatus() {
        return ((Integer) bmb.d(Integer.valueOf(this.mAutoDetectSwitchStatus))).intValue();
    }

    public void setAutoDetectSwitchStatus(int i) {
        this.mAutoDetectSwitchStatus = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getFootWearPosition() {
        return ((Integer) bmb.d(Integer.valueOf(this.mFootWearPosition))).intValue();
    }

    public void setFootWearPosition(int i) {
        this.mFootWearPosition = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public void setLastConnectedTime(long j) {
        this.mLastConnectedTime = j;
    }

    public long getLastConnectedTime() {
        return this.mLastConnectedTime;
    }

    public String getDeviceOtaPackageName() {
        return this.mDeviceOtaPackageName;
    }

    public void setDeviceOtaPackageName(String str) {
        this.mDeviceOtaPackageName = str;
    }

    public int getPowerSaveModel() {
        return this.mPowerSaveModel;
    }

    public void setPowerSaveModel(int i) {
        this.mPowerSaveModel = i;
    }

    public String getUdidFromDevice() {
        return this.mUdidFromDevice;
    }

    public void setUdidFromDevice(String str) {
        this.mUdidFromDevice = str;
    }

    public String getUnConvertedUdid() {
        return this.mUnConvertedUdid;
    }

    public void setUnConvertedUdid(String str) {
        this.mUnConvertedUdid = str;
    }

    public int getSupportAccountSwitch() {
        return this.mSupportAccountSwitch;
    }

    public void setSupportAccountSwitch(int i) {
        this.mSupportAccountSwitch = i;
    }

    public String getExpandCapability() {
        return this.mExpandCapability;
    }

    public void setExpandCapability(String str) {
        this.mExpandCapability = str;
    }

    public String getWearEngineDeviceId() {
        return this.mWearEngineDeviceId;
    }

    public void setWearEngineDeviceId(String str) {
        this.mWearEngineDeviceId = str;
    }

    public String getMultiLinkBleMac() {
        return this.mMultiLinkBleMac;
    }

    public void setMultiLinkBleMac(String str) {
        this.mMultiLinkBleMac = str;
    }

    public boolean isMultiLink() {
        return this.isMultiLink;
    }

    public void setMultiLink(boolean z) {
        this.isMultiLink = z;
    }

    public String getRelationship() {
        return this.mRelationship;
    }

    public void setRelationship(String str) {
        this.mRelationship = str;
    }

    public List<String> getFieldList() {
        return this.mFieldList;
    }

    public void setFieldList(List<String> list) {
        this.mFieldList = list;
    }

    public String getDeviceEnterpriseVersion() {
        return this.mDeviceEnterpriseVersion;
    }

    public void setDeviceEnterpriseVersion(String str) {
        this.mDeviceEnterpriseVersion = str;
    }

    public String getDeviceSplicingProductVersion() {
        return this.mDeviceSplicingProductVersion;
    }

    public void setDeviceSplicingProductVersion(String str) {
        this.mDeviceSplicingProductVersion = str;
    }

    public String getDeviceOStVersion() {
        return this.mDeviceOStVersion;
    }

    public void setDeviceOStVersion(String str) {
        this.mDeviceOStVersion = str;
    }

    public int getDeviceOtaSignatureLength() {
        return this.mDeviceOtaSignatureLength;
    }

    public void setDeviceOtaSignatureLength(int i) {
        this.mDeviceOtaSignatureLength = i;
    }

    public int getDeviceOtaAreaType() {
        return this.mDeviceOtaAreaType;
    }

    public void setDeviceOtaAreaType(int i) {
        this.mDeviceOtaAreaType = i;
    }

    public int getSportVersion() {
        return this.mSportVersion;
    }

    public void setSportVersion(int i) {
        this.mSportVersion = i;
    }

    public String getSaleInfo() {
        return this.mSaleInfo;
    }

    public void setSaleInfo(String str) {
        this.mSaleInfo = str;
    }

    public String getPsiSignature() {
        return this.mPsiSignature;
    }

    public void setPsiSignature(String str) {
        this.mPsiSignature = str;
    }

    public String getDeviceSubModelId() {
        return this.mDeviceSubProdId;
    }

    public void setDeviceSubModelId(String str) {
        this.mDeviceSubProdId = str;
    }

    public byte getIsDemoWatch() {
        return this.mIsDemoWatch;
    }

    public void setIsDemoWatch(byte b) {
        this.mIsDemoWatch = b;
    }

    public int getSingleFrameDevice() {
        return this.mSingleFrameDevice;
    }

    public void setSingleFrameDevice(int i) {
        this.mSingleFrameDevice = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeviceId);
        parcel.writeString(this.mDeviceName);
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mDeviceIdentify);
        parcel.writeInt(this.mDeviceProtocol);
        parcel.writeInt(this.mProductType);
        parcel.writeInt(this.mDeviceActiveState);
        parcel.writeInt(this.mDeviceConnectState);
        parcel.writeInt(this.mEncryptType);
        parcel.writeInt(this.mDeviceBlueToothType);
        parcel.writeString(this.mUuid);
        parcel.writeString(this.mDeviceModelName);
        parcel.writeInt(this.mDeviceIdType);
        parcel.writeInt(this.mAuthVersion);
        parcel.writeInt(this.mAutoDetectSwitchStatus);
        parcel.writeInt(this.mFootWearPosition);
        parcel.writeString(this.mBluetoothVersion);
        parcel.writeString(this.mSoftVersion);
        parcel.writeString(this.mNodeId);
        parcel.writeString(this.mCertModel);
        parcel.writeString(this.mManufacture);
        parcel.writeString(this.mDeviceUdid);
        parcel.writeLong(this.mLastConnectedTime);
        parcel.writeInt(this.mVersionType);
        parcel.writeString(this.mDeviceOtaPackageName);
        parcel.writeString(this.mHiLinkDeviceId);
        parcel.writeInt(this.mPowerSaveModel);
        parcel.writeString(this.mUdidFromDevice);
        parcel.writeString(this.mUnConvertedUdid);
        parcel.writeInt(this.mDeviceFactoryReset);
        parcel.writeInt(this.mSupportAccountSwitch);
        parcel.writeString(this.mExpandCapability);
        parcel.writeString(this.mWearEngineDeviceId);
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mEmuiVersion);
        parcel.writeString(this.mMultiLinkBleMac);
        parcel.writeInt(this.isMultiLink ? 1 : 0);
        parcel.writeString(this.mRelationship);
        parcel.writeStringList(this.mFieldList);
        parcel.writeString(this.mDeviceEnterpriseVersion);
        parcel.writeString(this.mDeviceSplicingProductVersion);
        parcel.writeString(this.mDeviceOStVersion);
        parcel.writeInt(this.mDeviceOtaSignatureLength);
        parcel.writeInt(this.mDeviceOtaAreaType);
        parcel.writeInt(this.mSportVersion);
        parcel.writeString(this.mSaleInfo);
        parcel.writeString(this.mPsiSignature);
        parcel.writeString(this.mDeviceReportInfo);
        parcel.writeString(this.mDeviceSubProdId);
        parcel.writeByte(this.mIsDemoWatch);
        parcel.writeInt(this.mSingleFrameDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DeviceInfo getDeviceInfo(Parcel parcel) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setmDeviceId(parcel.readString());
        deviceInfo.setDeviceName(parcel.readString());
        deviceInfo.setProductId(parcel.readString());
        deviceInfo.setDeviceIdentify(parcel.readString());
        deviceInfo.setDeviceProtocol(parcel.readInt());
        deviceInfo.setProductType(parcel.readInt());
        deviceInfo.setDeviceActiveState(parcel.readInt());
        deviceInfo.setDeviceConnectState(parcel.readInt());
        deviceInfo.setEncryptType(parcel.readInt());
        deviceInfo.setDeviceBluetoothType(parcel.readInt());
        deviceInfo.setUuid(parcel.readString());
        deviceInfo.setDeviceModel(parcel.readString());
        deviceInfo.setDeviceIdType(parcel.readInt());
        deviceInfo.setAuthVersion(parcel.readInt());
        deviceInfo.setAutoDetectSwitchStatus(parcel.readInt());
        deviceInfo.setFootWearPosition(parcel.readInt());
        deviceInfo.setBluetoothVersion(parcel.readString());
        deviceInfo.setSoftVersion(parcel.readString());
        fillDeviceInfo(parcel, deviceInfo);
        return deviceInfo;
    }

    private static void fillDeviceInfo(Parcel parcel, DeviceInfo deviceInfo) {
        deviceInfo.setNodeId(parcel.readString());
        deviceInfo.setCertModel(parcel.readString());
        deviceInfo.setManufacture(parcel.readString());
        deviceInfo.setDeviceUdid(parcel.readString());
        deviceInfo.setLastConnectedTime(parcel.readLong());
        deviceInfo.setVersionType(parcel.readInt());
        deviceInfo.setDeviceOtaPackageName(parcel.readString());
        deviceInfo.setHiLinkDeviceId(parcel.readString());
        deviceInfo.setPowerSaveModel(parcel.readInt());
        deviceInfo.setUdidFromDevice(parcel.readString());
        deviceInfo.setUnConvertedUdid(parcel.readString());
        deviceInfo.setDeviceFactoryReset(parcel.readInt());
        deviceInfo.setSupportAccountSwitch(parcel.readInt());
        deviceInfo.setExpandCapability(parcel.readString());
        deviceInfo.setWearEngineDeviceId(parcel.readString());
        partDeviceInfo(parcel, deviceInfo);
    }

    private static void partDeviceInfo(Parcel parcel, DeviceInfo deviceInfo) {
        deviceInfo.setCountryCode(parcel.readString());
        deviceInfo.setEmuiVersion(parcel.readString());
        deviceInfo.setMultiLinkBleMac(parcel.readString());
        deviceInfo.setMultiLink(parcel.readInt() == 1);
        deviceInfo.setRelationship(parcel.readString());
        deviceInfo.setFieldList(parcel.createStringArrayList());
        deviceInfo.setDeviceEnterpriseVersion(parcel.readString());
        deviceInfo.setDeviceSplicingProductVersion(parcel.readString());
        deviceInfo.setDeviceOStVersion(parcel.readString());
        deviceInfo.setDeviceOtaSignatureLength(parcel.readInt());
        deviceInfo.setDeviceOtaAreaType(parcel.readInt());
        deviceInfo.setSportVersion(parcel.readInt());
        deviceInfo.setSaleInfo(parcel.readString());
        deviceInfo.setPsiSignature(parcel.readString());
        deviceInfo.setDeviceReportInfo(parcel.readString());
        deviceInfo.setDeviceSubModelId(parcel.readString());
        deviceInfo.setIsDemoWatch(parcel.readByte());
        deviceInfo.setSingleFrameDevice(parcel.readInt());
    }

    public String toString() {
        return "[mDeviceName = " + this.mDeviceName + ",mProductType = " + this.mProductType + ",mDeviceConnectState = " + this.mDeviceConnectState + ",mRelationship = " + this.mRelationship + ",mSoftVersion = " + this.mSoftVersion + ",sportVersion = " + this.mSportVersion + "]";
    }
}
