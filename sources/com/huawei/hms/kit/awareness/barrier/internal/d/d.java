package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class d extends f {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4869a;
    private final int b;

    public static boolean a(int i) {
        return i == 1 || i == 0 || i == -1;
    }

    private boolean b(int i) {
        return i == 2 || i == 1 || i == 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4869a);
        parcel.writeInt(this.b);
    }

    public int hashCode() {
        return (this.b + this.f4869a) << 2;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return a(this.f4869a) && b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return dVar.f4869a == this.f4869a && dVar.b == this.b;
    }

    public int b() {
        return this.b;
    }

    public int a() {
        return this.f4869a;
    }

    private d(Parcel parcel) {
        this.f4869a = parcel.readInt();
        this.b = parcel.readInt();
    }

    public d(int i, int i2) {
        this.f4869a = i;
        this.b = i2;
    }
}
