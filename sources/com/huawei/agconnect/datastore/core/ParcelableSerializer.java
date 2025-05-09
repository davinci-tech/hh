package com.huawei.agconnect.datastore.core;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
class ParcelableSerializer {
    private static final String TAG = "ParcelableSerializer";

    static <T extends Parcelable> String serializeToString(T t) {
        return SafeBase64.encodeToString(serializeToBytes(t), 0);
    }

    private static <T extends Parcelable> byte[] serializeToBytes(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    static <T extends Parcelable> T deserializeFromString(String str, Parcelable.Creator<T> creator) {
        return (T) deserializeFromBytes(SafeBase64.decode(str, 0), creator);
    }

    private static <T extends Parcelable> T deserializeFromBytes(byte[] bArr, Parcelable.Creator<T> creator) {
        if (creator == null || bArr.length == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    ParcelableSerializer() {
    }
}
