package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class j extends f {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.j.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j[] newArray(int i) {
            return new j[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4874a;
    private final int b;
    private final String c;

    public static boolean a(int i) {
        return i == 1 || i == 0 || i == -1;
    }

    private static boolean b(int i) {
        return i == 0 || i == 1 || i == 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4874a);
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
    }

    public int hashCode() {
        return (this.b + this.f4874a) << 2;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return a(this.f4874a) && b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return jVar.f4874a == this.f4874a && jVar.b == this.b && jVar.c.equals(this.c);
    }

    public String c() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int a() {
        return this.f4874a;
    }

    private j(Parcel parcel) {
        this.f4874a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readString();
    }

    public j(int i, int i2, String str) {
        this.f4874a = i;
        this.b = i2;
        this.c = str;
    }
}
