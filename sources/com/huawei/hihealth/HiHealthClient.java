package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiHealthClient implements Parcelable {
    public static final Parcelable.Creator<HiHealthClient> CREATOR = new Parcelable.Creator<HiHealthClient>() { // from class: com.huawei.hihealth.HiHealthClient.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buG_, reason: merged with bridge method [inline-methods] */
        public HiHealthClient createFromParcel(Parcel parcel) {
            return new HiHealthClient(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiHealthClient[] newArray(int i) {
            return new HiHealthClient[i];
        }
    };
    private int clientId;
    private HiAppInfo hiAppInfo;
    private HiDeviceInfo hiDeviceInfo;
    private HiUserInfo hiUserInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHealthClient() {
    }

    protected HiHealthClient(Parcel parcel) {
        this.hiAppInfo = (HiAppInfo) parcel.readParcelable(HiAppInfo.class.getClassLoader());
        this.hiUserInfo = (HiUserInfo) parcel.readParcelable(HiUserInfo.class.getClassLoader());
        this.hiDeviceInfo = (HiDeviceInfo) parcel.readParcelable(HiDeviceInfo.class.getClassLoader());
        this.clientId = parcel.readInt();
    }

    public String getDeviceUuid() {
        HiDeviceInfo hiDeviceInfo = this.hiDeviceInfo;
        if (hiDeviceInfo != null) {
            return hiDeviceInfo.getDeviceUniqueCode();
        }
        return null;
    }

    public void setDeviceUuid(String str) {
        if (this.hiDeviceInfo == null) {
            this.hiDeviceInfo = new HiDeviceInfo(1);
        }
        this.hiDeviceInfo.setDeviceUniqueCode(str);
    }

    public int getOwnerId() {
        HiUserInfo hiUserInfo = this.hiUserInfo;
        if (hiUserInfo != null) {
            return hiUserInfo.getOwnerId();
        }
        return 0;
    }

    public void setOwnerId(int i) {
        if (this.hiUserInfo == null) {
            this.hiUserInfo = new HiUserInfo();
        }
        this.hiUserInfo.setOwnerId(i);
    }

    public String getPackageName() {
        HiAppInfo hiAppInfo = this.hiAppInfo;
        if (hiAppInfo != null) {
            return hiAppInfo.getPackageName();
        }
        return null;
    }

    public void setPackageName(String str) {
        if (this.hiAppInfo == null) {
            this.hiAppInfo = new HiAppInfo();
        }
        this.hiAppInfo.setPackageName(str);
    }

    public int getAppId() {
        HiAppInfo hiAppInfo = this.hiAppInfo;
        if (hiAppInfo != null) {
            return hiAppInfo.getAppId();
        }
        return 0;
    }

    public void setAppId(int i) {
        if (this.hiAppInfo == null) {
            this.hiAppInfo = new HiAppInfo();
        }
        this.hiAppInfo.setAppId(i);
    }

    public String getHuid() {
        HiUserInfo hiUserInfo = this.hiUserInfo;
        if (hiUserInfo != null) {
            return hiUserInfo.getHuid();
        }
        return null;
    }

    public void setHuid(String str) {
        if (this.hiUserInfo == null) {
            this.hiUserInfo = new HiUserInfo();
        }
        this.hiUserInfo.setHuid(str);
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int i) {
        this.clientId = i;
    }

    public HiAppInfo getHiAppInfo() {
        return this.hiAppInfo;
    }

    public void setHiAppInfo(HiAppInfo hiAppInfo) {
        this.hiAppInfo = hiAppInfo;
    }

    public HiUserInfo getHiUserInfo() {
        return this.hiUserInfo;
    }

    public void setHiUserInfo(HiUserInfo hiUserInfo) {
        this.hiUserInfo = hiUserInfo;
    }

    public HiDeviceInfo getHiDeviceInfo() {
        return this.hiDeviceInfo;
    }

    public void setHiDeviceInfo(HiDeviceInfo hiDeviceInfo) {
        this.hiDeviceInfo = hiDeviceInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.hiAppInfo, i);
        parcel.writeParcelable(this.hiUserInfo, i);
        parcel.writeParcelable(this.hiDeviceInfo, i);
        parcel.writeInt(this.clientId);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiHealthClient{clientId=");
        stringBuffer.append(this.clientId);
        stringBuffer.append(", hiAppInfo=").append(this.hiAppInfo);
        stringBuffer.append(", hiDeviceInfo=").append(this.hiDeviceInfo);
        stringBuffer.append(", hiUserInfo=").append(this.hiUserInfo);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
