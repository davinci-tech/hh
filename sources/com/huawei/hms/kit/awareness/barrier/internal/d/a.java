package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class a extends f {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.a.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }
    };
    private static final float d = 0.1f;

    /* renamed from: a, reason: collision with root package name */
    private final float f4866a;
    private final float b;
    private final int c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4866a);
        parcel.writeFloat(this.b);
        parcel.writeInt(this.c);
    }

    public int hashCode() {
        return Math.round(this.f4866a + this.b);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        float f = this.f4866a;
        return 0.0d <= ((double) f) && f < this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return Math.abs(aVar.f4866a - this.f4866a) < 0.1f && Math.abs(aVar.b - this.b) < 0.1f;
    }

    public int c() {
        return this.c;
    }

    public float b() {
        return this.b;
    }

    public float a() {
        return this.f4866a;
    }

    private a(Parcel parcel) {
        this.f4866a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readInt();
    }

    public a(float f, float f2, int i) {
        this.f4866a = f;
        this.b = f2;
        this.c = i;
    }
}
