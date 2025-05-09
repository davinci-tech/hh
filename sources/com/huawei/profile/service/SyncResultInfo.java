package com.huawei.profile.service;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class SyncResultInfo implements Parcelable {
    public static final Parcelable.Creator<SyncResultInfo> CREATOR = new Parcelable.Creator<SyncResultInfo>() { // from class: com.huawei.profile.service.SyncResultInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncResultInfo createFromParcel(Parcel parcel) {
            return new SyncResultInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncResultInfo[] newArray(int i) {
            return new SyncResultInfo[i];
        }
    };
    private Map<String, Object> syncResultInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SyncResultInfo(Map<String, SyncResult> map) {
        this.syncResultInfo = new HashMap();
        for (Map.Entry<String, SyncResult> entry : map.entrySet()) {
            this.syncResultInfo.put(entry.getKey(), entry.getValue());
        }
    }

    protected SyncResultInfo(Parcel parcel) {
        this.syncResultInfo = new HashMap();
        if (parcel == null) {
            return;
        }
        this.syncResultInfo = parcel.readHashMap(SyncResultInfo.class.getClassLoader());
    }

    public Map<String, Object> getSyncResultInfo() {
        return this.syncResultInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.syncResultInfo);
    }
}
