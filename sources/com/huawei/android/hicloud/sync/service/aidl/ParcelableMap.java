package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Map;

/* loaded from: classes8.dex */
public class ParcelableMap<k, t> implements Parcelable {
    public static final Parcelable.Creator<ParcelableMap> CREATOR = new c();
    private static final String TAG = "ParcelableMap";
    private Map<k, t> map;

    static final class c implements Parcelable.Creator<ParcelableMap> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ParcelableMap[] newArray(int i) {
            return new ParcelableMap[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fe_, reason: merged with bridge method [inline-methods] */
        public ParcelableMap createFromParcel(Parcel parcel) {
            return new ParcelableMap(parcel, null);
        }
    }

    /* synthetic */ ParcelableMap(Parcel parcel, c cVar) {
        this(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        this.map = parcel.readHashMap(ParcelableMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Map<k, t> getMap() {
        return this.map;
    }

    public void setMap(Map<k, t> map) {
        this.map = map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.map);
    }

    private ParcelableMap(Parcel parcel) {
        readFromParcel(parcel);
    }

    public ParcelableMap() {
    }
}
