package com.huawei.wearengine.monitor;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class MonitorData implements Parcelable {
    public static final Parcelable.Creator<MonitorData> CREATOR = new Parcelable.Creator<MonitorData>() { // from class: com.huawei.wearengine.monitor.MonitorData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: fds_, reason: merged with bridge method [inline-methods] */
        public MonitorData createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            return new MonitorData(parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readHashMap(HashMap.class.getClassLoader()));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MonitorData[] newArray(int i) {
            return (i > 65535 || i < 0) ? new MonitorData[0] : new MonitorData[i];
        }
    };
    private boolean mBooleanData;
    private int mIntData;
    private HashMap<String, MonitorData> mMapData;
    private String mStringData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MonitorData(boolean z, int i, String str, HashMap<String, MonitorData> hashMap) {
        this.mBooleanData = z;
        this.mIntData = i;
        this.mStringData = str;
        this.mMapData = hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mBooleanData ? 1 : 0);
        parcel.writeInt(this.mIntData);
        parcel.writeString(this.mStringData);
        parcel.writeMap(this.mMapData);
    }

    public final void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mBooleanData = parcel.readInt() != 0;
        this.mIntData = parcel.readInt();
        this.mStringData = parcel.readString();
        this.mMapData = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public boolean asBool() {
        return this.mBooleanData;
    }

    public int asInt() {
        return this.mIntData;
    }

    public String asString() {
        return this.mStringData;
    }

    public HashMap<String, MonitorData> asMap() {
        return this.mMapData;
    }
}
