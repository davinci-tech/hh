package com.huawei.health.devicemgr.business.entity;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class HwGetDevicesParameter implements Parcelable {
    public static final Parcelable.Creator<HwGetDevicesParameter> CREATOR = new Parcelable.Creator<HwGetDevicesParameter>() { // from class: com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HwGetDevicesParameter[] newArray(int i) {
            return new HwGetDevicesParameter[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: QI_, reason: merged with bridge method [inline-methods] */
        public HwGetDevicesParameter createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
            hwGetDevicesParameter.setDeviceIdentify(readString);
            return hwGetDevicesParameter;
        }
    };
    private static final int MAX_LENGTH = 6;
    private String mDeviceIdentify = "";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setDeviceIdentify(String str) {
        if (str != null) {
            String replaceAll = str.replaceAll(":", "");
            if (replaceAll.length() >= 6) {
                this.mDeviceIdentify = replaceAll.substring(replaceAll.length() - 6);
            } else {
                this.mDeviceIdentify = replaceAll;
            }
        }
    }

    public String getDeviceIdentify() {
        return this.mDeviceIdentify;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeviceIdentify);
    }

    public String toString() {
        return "[HwGetDevicesParameter:mDeviceIdentify = " + CommonUtil.l(this.mDeviceIdentify) + "]";
    }
}
