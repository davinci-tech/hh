package com.huawei.hms.kit.awareness.barrier.internal.a.k;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.BarrierStatus;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.b.o;
import com.huawei.hms.kit.awareness.barrier.internal.d;
import com.huawei.hms.kit.awareness.barrier.internal.d.l;

/* loaded from: classes9.dex */
public class a extends c {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.k.a.1
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
    private static final String k = "WifiCondition";
    private static final long l = 5000;
    private o m;
    private o n;
    private l o;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 12;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 10;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public l i() {
        return this.o;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        if (c(context, "android.permission.ACCESS_WIFI_STATE") && c(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return (d(context, "android.permission.ACCESS_WIFI_STATE") && d(context, "android.permission.ACCESS_NETWORK_STATE")) ? 0 : 10106;
        }
        return 10105;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (fVar instanceof o) {
            this.m = (o) fVar;
            if (!y()) {
                int s = s();
                this.n = this.m;
                com.huawei.hms.kit.awareness.b.a.c.a(k, "rootConditionCheck check result:" + s, new Object[0]);
                return;
            }
            com.huawei.hms.kit.awareness.b.a.c.b(k, "is same status", new Object[0]);
            BarrierStatus m = m();
            if (m instanceof d) {
                d dVar = (d) m;
                dVar.a(dVar.getPresentStatus());
                dVar.a(System.currentTimeMillis());
            }
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        if (a(context, "android.permission.ACCESS_WIFI_STATE") && a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return (b(context, "android.permission.ACCESS_WIFI_STATE") && b(context, "android.permission.ACCESS_NETWORK_STATE")) ? 0 : 10106;
        }
        return 10105;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (this.m.c == -1 && this.m.b == -1) {
            return 2;
        }
        int i = this.o.b;
        if (i == 0) {
            return x();
        }
        boolean z = false;
        if (i == 1) {
            if (this.m.a() && this.n.b()) {
                z = true;
            }
            return a(z);
        }
        if (i == 2) {
            if (this.m.b() && this.n.a()) {
                z = true;
            }
            return a(z);
        }
        if (i == 3) {
            if (this.m.e() && this.n.f() && C() == 1) {
                z = true;
            }
            return a(z);
        }
        if (i != 4) {
            return 2;
        }
        if (this.m.f() && this.n.e() && C() == 2 && ((com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.o.b()) || com.huawei.hms.kit.awareness.b.a.d.a(this.o.b(), this.n.e)) && (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.o.a()) || com.huawei.hms.kit.awareness.b.a.d.a(this.o.a(), this.n.d)))) {
            z = true;
        }
        return a(z);
    }

    private boolean y() {
        if ((this.o.f4876a == 3 || this.o.f4876a == 4 || this.o.b == 1 || this.o.b == 2) && this.m.b == this.n.b) {
            return true;
        }
        return ((this.o.f4876a == 1 || this.o.f4876a == 2 || this.o.b == 3 || this.o.b == 4) && this.m.c == this.n.c) ? com.huawei.hms.kit.awareness.b.a.d.a(this.m.d, this.n.d) && com.huawei.hms.kit.awareness.b.a.d.a(this.m.e, this.n.e) : this.o.b == 4 && m().getPresentStatus() == 1 && !(this.o.b() == null && this.o.a() == null) && ((this.o.b() == null || !this.o.b().equals(this.m.e)) && (this.o.a() == null || !this.o.a().equals(this.m.d)));
    }

    private int x() {
        int i = this.o.f4876a;
        if (i == 1 || i == 2) {
            return C() == this.o.f4876a ? 1 : 0;
        }
        if (i == 3 || i == 4) {
            return D() == this.o.f4876a ? 1 : 0;
        }
        return 2;
    }

    public static a a(int i, int i2, String str, String str2) {
        return new a(i, i2, str, str2);
    }

    private int a(boolean z) {
        if (!z) {
            return 0;
        }
        b(5000L);
        return 1;
    }

    private int D() {
        if (this.m.b == 3 || this.m.b == 4) {
            return this.m.b;
        }
        return -1;
    }

    private int C() {
        boolean a2 = com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.o.b());
        boolean z = !a2 && this.o.b().equals(this.m.e);
        boolean a3 = com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.o.a());
        boolean z2 = !a3 && this.o.a().equals(this.m.d);
        com.huawei.hms.kit.awareness.b.a.c.a(k, "barrier tag: " + l() + ", info's ssid: " + this.m.e + ", param's ssid: " + this.o.b() + ", info's bssid: " + this.m.d + ", param's bssid: " + this.o.a(), new Object[0]);
        return (a3 && a2) ? (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.m.d) && com.huawei.hms.kit.awareness.barrier.internal.f.c.a(this.m.e)) ? 2 : 1 : ((a2 || z) && (a3 || z2)) ? 1 : 2;
    }

    private a(Parcel parcel) {
        super(parcel);
        this.m = new o(-1, -1, null, null);
        this.n = new o(-1, -1, null, null);
        this.o = (l) parcel.readParcelable(l.class.getClassLoader());
    }

    private a(int i, int i2, String str, String str2) {
        this.m = new o(-1, -1, null, null);
        this.n = new o(-1, -1, null, null);
        this.o = new l(i, i2, str, str2);
    }
}
