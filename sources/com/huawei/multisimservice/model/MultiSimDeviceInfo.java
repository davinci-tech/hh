package com.huawei.multisimservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MultiSimDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<MultiSimDeviceInfo> CREATOR = new Parcelable.Creator<MultiSimDeviceInfo>() { // from class: com.huawei.multisimservice.model.MultiSimDeviceInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bZi_, reason: merged with bridge method [inline-methods] */
        public MultiSimDeviceInfo createFromParcel(Parcel parcel) {
            return new MultiSimDeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MultiSimDeviceInfo[] newArray(int i) {
            return new MultiSimDeviceInfo[i];
        }
    };
    public static final int DEVICE_TYPE_ESIM = 2;
    public static final int DEVICE_TYPE_INVALID = 0;
    public static final int DEVICE_TYPE_NO_MODEM = 3;
    public static final int DEVICE_TYPE_SIM = 1;
    public static final int RESULT_CODE_NOT_CONNECT = -2;
    public static final int RESULT_CODE_NOT_SUPPORT = -3;
    public static final int RESULT_CODE_SUCCESS = 1;
    public static final int RESULT_CODE_UNKNOW = 0;
    public static final int RESULT_CODE_USER_REJECT = -1;
    private String mAPIVersion;
    private String mDeviceIMEI;
    private String mDeviceSerialNumber;
    private int mDeviceType;
    private String mEID;
    private String mProductName;
    private int mResultCode;
    private String mSign;
    private List<SimInfo> mSimInfoList;
    private String mTechVersion;
    private long mTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MultiSimDeviceInfo() {
        this.mDeviceType = 0;
        this.mTime = 0L;
        this.mDeviceIMEI = "";
        this.mDeviceSerialNumber = "";
        this.mProductName = "";
        this.mEID = "";
        this.mAPIVersion = "";
        this.mTechVersion = "";
        this.mSign = "";
        this.mSimInfoList = new ArrayList();
    }

    public MultiSimDeviceInfo(Parcel parcel) {
        this.mDeviceType = 0;
        this.mTime = 0L;
        this.mDeviceIMEI = "";
        this.mDeviceSerialNumber = "";
        this.mProductName = "";
        this.mEID = "";
        this.mAPIVersion = "";
        this.mTechVersion = "";
        this.mSign = "";
        this.mSimInfoList = new ArrayList();
        this.mResultCode = parcel.readInt();
        this.mDeviceType = parcel.readInt();
        this.mDeviceIMEI = parcel.readString();
        this.mDeviceSerialNumber = parcel.readString();
        this.mProductName = parcel.readString();
        this.mEID = parcel.readString();
        this.mAPIVersion = parcel.readString();
        this.mTechVersion = parcel.readString();
        this.mTime = parcel.readLong();
        this.mSign = parcel.readString();
        if (this.mSimInfoList == null) {
            this.mSimInfoList = new ArrayList();
        }
        parcel.readTypedList(this.mSimInfoList, SimInfo.CREATOR);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public String getDeviceIMEI() {
        return this.mDeviceIMEI;
    }

    public void setDeviceIMEI(String str) {
        this.mDeviceIMEI = str;
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public void setDeviceSerialNumber(String str) {
        this.mDeviceSerialNumber = str;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public void setProductName(String str) {
        this.mProductName = str;
    }

    public String getEID() {
        return this.mEID;
    }

    public void setEID(String str) {
        this.mEID = str;
    }

    public String getApiVersion() {
        return this.mAPIVersion;
    }

    public void setApiVersion(String str) {
        this.mAPIVersion = str;
    }

    public String getTechVersion() {
        return this.mTechVersion;
    }

    public void setTechVersion(String str) {
        this.mTechVersion = str;
    }

    public String getSign() {
        return this.mSign;
    }

    public void setSign(String str) {
        this.mSign = str;
    }

    public List<SimInfo> getSimInfoList() {
        return this.mSimInfoList;
    }

    public void setSimInfoList(List<SimInfo> list) {
        this.mSimInfoList = list;
    }

    public String getDeviceID() {
        int i = this.mDeviceType;
        if (2 == i || 1 == i) {
            return this.mDeviceIMEI;
        }
        if (3 == i) {
            return this.mDeviceSerialNumber;
        }
        return null;
    }

    public SimInfo getActiveSimProfileInfo() {
        List<SimInfo> list = this.mSimInfoList;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                SimInfo simInfo = this.mSimInfoList.get(i);
                if (simInfo != null && simInfo.isActive()) {
                    return simInfo;
                }
            }
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResultCode);
        parcel.writeInt(this.mDeviceType);
        parcel.writeString(this.mDeviceIMEI);
        parcel.writeString(this.mDeviceSerialNumber);
        parcel.writeString(this.mProductName);
        parcel.writeString(this.mEID);
        parcel.writeString(this.mAPIVersion);
        parcel.writeString(this.mTechVersion);
        parcel.writeLong(this.mTime);
        parcel.writeString(this.mSign);
        parcel.writeTypedList(this.mSimInfoList);
    }
}
