package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class i extends f {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.i.1
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
    private final int f4873a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4873a);
    }

    public int hashCode() {
        return this.f4873a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof i) && obj.hashCode() == hashCode();
    }

    public int a() {
        return this.f4873a;
    }

    private i(Parcel parcel) {
        this.f4873a = parcel.readInt();
    }

    public i(int i) {
        this.f4873a = i;
    }
}
