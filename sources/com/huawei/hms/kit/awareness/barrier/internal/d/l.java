package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class l extends f {
    public static final Parcelable.Creator<l> CREATOR = new Parcelable.Creator<l>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.l.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l[] newArray(int i) {
            return new l[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public final int f4876a;
    public final int b;
    private final m c;

    public static boolean a(int i) {
        return i == 1 || i == 2 || i == 4 || i == 3 || i == -1;
    }

    private static boolean b(int i) {
        return i == 3 || i == 4 || i == 0 || i == 1 || i == 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4876a);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public int hashCode() {
        return ((this.b * 100) + this.f4876a) << 2;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return a(this.f4876a) && b(this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return lVar.f4876a == this.f4876a && lVar.b == this.b;
    }

    public String b() {
        return this.c.b();
    }

    public String a() {
        return this.c.a();
    }

    private l(Parcel parcel) {
        this.f4876a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = (m) parcel.readParcelable(m.class.getClassLoader());
    }

    public l(int i, int i2, String str, String str2) {
        this.f4876a = i;
        this.b = i2;
        this.c = new m(str, str2);
    }
}
