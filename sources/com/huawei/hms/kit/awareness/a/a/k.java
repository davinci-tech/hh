package com.huawei.hms.kit.awareness.a.a;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.capture.WeatherPosition;

/* loaded from: classes4.dex */
public class k implements com.huawei.hms.kit.awareness.barrier.internal.g {
    public static final Parcelable.Creator<k> CREATOR = new Parcelable.Creator<k>() { // from class: com.huawei.hms.kit.awareness.a.a.k.1
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
    private static final double f4799a = -180.0d;
    private static final double b = -360.0d;
    private static final double c = 180.0d;
    private static final double d = -180.0d;
    private static final double e = 90.0d;
    private static final double f = -90.0d;
    private static final double g = 1.0E-7d;
    private static final String l = "^[^%'<>()\"]{0,32}$";
    private static final String m = "^[a-z]{2,5}_[a-zA-Z_]{2,10}$";
    private double h;
    private double i;
    private String j;
    private final int k;
    private WeatherPosition n;
    private String o;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.h);
        parcel.writeDouble(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        if (this.k == 3) {
            parcel.writeParcelable(this.n, i);
        }
    }

    public WeatherPosition f() {
        return this.n;
    }

    public boolean e() {
        int i = this.k;
        if (i == 1) {
            return a(this.h, this.i);
        }
        if (i != 3) {
            return true;
        }
        return !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.n) && g();
    }

    public int d() {
        return this.k;
    }

    public String c() {
        return this.j;
    }

    public double b() {
        return this.i;
    }

    public void a(String str) {
        this.o = str;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.g
    public int a(Context context) {
        int i = this.k;
        if (i == 0) {
            return com.huawei.hms.kit.awareness.b.a.f.a(context, this.o);
        }
        if (i == 2) {
            return com.huawei.hms.kit.awareness.b.a.f.b(context, this.o);
        }
        return 0;
    }

    public double a() {
        return this.h;
    }

    private boolean g() {
        if (this.n.getCountry() != null && !this.n.getCountry().matches(l)) {
            return false;
        }
        if ((this.n.getProvince() != null && !this.n.getProvince().matches(l)) || com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.n.getCity()) || !this.n.getCity().matches(l)) {
            return false;
        }
        if (this.n.getDistrict() == null || this.n.getDistrict().matches(l)) {
            return (this.n.getCounty() == null || this.n.getCounty().matches(l)) && this.n.getLocale() != null && this.n.getLocale().matches(m);
        }
        return false;
    }

    static boolean a(double d2, double d3) {
        return (-180.0d < d3 || Math.abs(d3 + c) < g) && (d3 < c || Math.abs(c - d3) < g) && ((f < d2 || Math.abs(d2 + e) < g) && (d2 < e || Math.abs(e - d2) < g));
    }

    public k(String str, int i) {
        this.h = -180.0d;
        this.i = -360.0d;
        this.j = str;
        this.k = i;
    }

    public k(WeatherPosition weatherPosition, int i) {
        this.k = i;
        this.n = weatherPosition;
    }

    private k(Parcel parcel) {
        this.h = parcel.readDouble();
        this.i = parcel.readDouble();
        this.j = parcel.readString();
        int readInt = parcel.readInt();
        this.k = readInt;
        if (readInt == 3) {
            this.n = (WeatherPosition) parcel.readParcelable(WeatherPosition.class.getClassLoader());
        }
    }

    public k(int i) {
        this.k = i;
    }
}
