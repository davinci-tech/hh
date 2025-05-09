package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;

/* loaded from: classes4.dex */
public class m implements Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new Parcelable.Creator<m>() { // from class: com.huawei.hms.kit.awareness.a.a.m.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i) {
            return new m[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return new m(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final String f4801a = "^[a-zA-Z0-9]{2}$";
    private static final int b = 1;
    private final int c;
    private String d;
    private String e;
    private long f;
    private double g;
    private double h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeLong(this.f);
        parcel.writeDouble(this.g);
        parcel.writeDouble(this.h);
    }

    public boolean f() {
        int i = this.c;
        if (i == 0) {
            return k.a(this.g, this.h);
        }
        if (i == 1) {
            return !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.d) && this.d.matches(f4801a);
        }
        if (i != 3) {
            return true;
        }
        return a(this.f);
    }

    public double e() {
        return this.h;
    }

    public double d() {
        return this.g;
    }

    public long c() {
        return this.f;
    }

    public String b() {
        return this.d;
    }

    public int a() {
        return this.c;
    }

    private boolean a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(14, 0);
        calendar.set(calendar.get(1), 0, 1, 0, 0, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(calendar.get(1) + 1, 0, 1, 0, 0, 0);
        return timeInMillis <= j && j < calendar.getTimeInMillis();
    }

    private m(Parcel parcel) {
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readLong();
        this.g = parcel.readDouble();
        this.h = parcel.readDouble();
    }

    public m(int i, String str) {
        this.c = i;
        this.d = str;
    }

    public m(int i, long j) {
        this.c = i;
        this.f = j;
    }

    public m(int i, double d, double d2) {
        this.c = i;
        this.g = d;
        this.h = d2;
    }
}
