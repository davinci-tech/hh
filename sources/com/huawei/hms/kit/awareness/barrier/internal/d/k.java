package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class k extends f {
    public static final Parcelable.Creator<k> CREATOR = new Parcelable.Creator<k>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.k.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k[] newArray(int i) {
            return new k[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4875a;
    private final int b;

    public static boolean a(int i) {
        return i == 2 || i == 0 || i == 1 || i == -1;
    }

    private boolean b(int i) {
        return i == 0 || i == 2 || i == 1 || i == 3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4875a);
        parcel.writeInt(this.b);
    }

    public int hashCode() {
        return this.b + this.f4875a;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return a(this.f4875a) && b(this.b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.f4875a == kVar.f4875a && this.b == kVar.b;
    }

    public int b() {
        return this.b;
    }

    public int a() {
        return this.f4875a;
    }

    private k(Parcel parcel) {
        this.f4875a = parcel.readInt();
        this.b = parcel.readInt();
    }

    public k(int i, int i2) {
        this.f4875a = i;
        this.b = i2;
    }
}
