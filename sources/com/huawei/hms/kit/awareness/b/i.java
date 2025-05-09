package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class i implements Parcelable {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.huawei.hms.kit.awareness.b.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final String f4839a = "BluetoothRequest";
    private final int b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
    }

    public boolean b() {
        if (com.huawei.hms.kit.awareness.barrier.internal.d.h.b(this.b)) {
            return true;
        }
        com.huawei.hms.kit.awareness.b.a.c.d(f4839a, "request object is invalid", new Object[0]);
        return false;
    }

    public int a() {
        return this.b;
    }

    private i(Parcel parcel) {
        this.b = parcel.readInt();
    }

    public i(int i) {
        this.b = i;
    }
}
