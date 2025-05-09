package com.huawei.profile.devicelist;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class ChangeDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<ChangeDeviceInfo> CREATOR = new Parcelable.Creator<ChangeDeviceInfo>() { // from class: com.huawei.profile.devicelist.ChangeDeviceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChangeDeviceInfo createFromParcel(Parcel parcel) {
            return new ChangeDeviceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChangeDeviceInfo[] newArray(int i) {
            return new ChangeDeviceInfo[0];
        }
    };
    private Map<String, Object> changeDeviceInfoMap;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ChangeDeviceInfo(Parcel parcel) {
        this.changeDeviceInfoMap = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public ChangeDeviceInfo(Map<String, Object> map) {
        this.changeDeviceInfoMap = map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.changeDeviceInfoMap);
    }

    public Map<String, Object> getChangeDeviceInfo() {
        Map<String, Object> map = this.changeDeviceInfoMap;
        return map == null ? new HashMap() : map;
    }
}
