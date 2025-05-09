package com.huawei.hms.kit.awareness.barrier.internal.a.g;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import com.huawei.hms.kit.awareness.b.a.f;
import com.huawei.hms.kit.awareness.b.a.g;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.d.h;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;
import com.huawei.hms.kit.awareness.barrier.internal.f.d;
import java.util.Set;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.g.a.1
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
    private static final String k = "BluetoothCondition";
    private static final long m = 5000;
    private Set<String> n;
    private Set<String> o;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 9;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 6;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public h i() {
        return (h) a(h.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    public Set<String> w() {
        return this.n;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        if (!c(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermissionByContext() -> calling BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (!f.a(context)) {
            return 10104;
        }
        if (!g.a() || c(context, "android.permission.BLUETOOTH_CONNECT")) {
            return 0;
        }
        com.huawei.hms.kit.awareness.b.a.c.c(k, " checkPermissionByContext() -> BLUETOOTH_CONNECT denied", new Object[0]);
        return 10104;
    }

    public boolean a(String str) {
        return this.n.contains(str);
    }

    public void a(Set<String> set) {
        this.o = this.n;
        this.n = set;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(com.huawei.hms.kit.awareness.barrier.internal.b.f fVar) {
        if (fVar instanceof com.huawei.hms.kit.awareness.barrier.internal.b.a) {
            com.huawei.hms.kit.awareness.barrier.internal.b.a aVar = (com.huawei.hms.kit.awareness.barrier.internal.b.a) fVar;
            if (aVar.b()) {
                a(d.a(aVar.a()));
            }
            com.huawei.hms.kit.awareness.b.a.c.a(k, "rootConditionCheck check result:" + s(), new Object[0]);
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        if (!a(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkBluetoothPermission() ->  calling BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (!g.a() && !b(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermission-> BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (g.a() && !b(context, "android.permission.BLUETOOTH_CONNECT")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermission-> BLUETOOTH_CONNECT denied", new Object[0]);
            return 10104;
        }
        if (!g.a() || a(context, "android.permission.BLUETOOTH_CONNECT")) {
            return 0;
        }
        com.huawei.hms.kit.awareness.b.a.c.c(k, " checkBluetoothPermission() ->  calling BLUETOOTH_CONNECT denied", new Object[0]);
        return 10104;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
    
        b(5000L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        if (com.huawei.hms.kit.awareness.barrier.internal.f.d.a((java.util.Set) r5.n, (java.util.Set) r5.o).isEmpty() == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        if (x().c() == 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
    
        if (x().c() == 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
    
        if (com.huawei.hms.kit.awareness.barrier.internal.f.d.a((java.util.Set) r5.o, (java.util.Set) r5.n).isEmpty() == false) goto L13;
     */
    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a() {
        /*
            r5 = this;
            com.huawei.hms.kit.awareness.barrier.internal.d.h r0 = r5.i()
            int r0 = r0.a()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L35
            if (r0 == r2) goto L21
            r3 = 2
            if (r0 == r3) goto L12
            return r1
        L12:
            java.util.Set<java.lang.String> r0 = r5.o
            java.util.Set<java.lang.String> r3 = r5.n
            java.util.Set r0 = com.huawei.hms.kit.awareness.barrier.internal.f.d.a(r0, r3)
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L5a
            goto L2f
        L21:
            java.util.Set<java.lang.String> r0 = r5.n
            java.util.Set<java.lang.String> r3 = r5.o
            java.util.Set r0 = com.huawei.hms.kit.awareness.barrier.internal.f.d.a(r0, r3)
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L5a
        L2f:
            r3 = 5000(0x1388, double:2.4703E-320)
            r5.b(r3)
            goto L5b
        L35:
            java.util.Set<java.lang.String> r0 = r5.n
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L47
            com.huawei.hms.kit.awareness.barrier.internal.d.h r0 = r5.i()
            int r0 = r0.c()
            if (r0 == r2) goto L5b
        L47:
            java.util.Set<java.lang.String> r0 = r5.n
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L5a
            com.huawei.hms.kit.awareness.barrier.internal.d.h r0 = r5.i()
            int r0 = r0.c()
            if (r0 != 0) goto L5a
            goto L5b
        L5a:
            r2 = r1
        L5b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "-----BluetoothCondition check() end,barrier Tag is: "
            r0.<init>(r3)
            java.lang.String r3 = r5.l()
            r0.append(r3)
            java.lang.String r3 = ",mCurrent bluetooth is:"
            r0.append(r3)
            java.util.Set<java.lang.String> r3 = r5.n
            r0.append(r3)
            java.lang.String r3 = ", mPrevious bluetooth is:"
            r0.append(r3)
            java.util.Set<java.lang.String> r3 = r5.o
            r0.append(r3)
            java.lang.String r3 = ",barrier Type is:"
            r0.append(r3)
            com.huawei.hms.kit.awareness.barrier.internal.d.h r3 = r5.i()
            int r3 = r3.a()
            r0.append(r3)
            java.lang.String r3 = ",checkResult is:"
            r0.append(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "BluetoothCondition"
            com.huawei.hms.kit.awareness.b.a.c.a(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.kit.awareness.barrier.internal.a.g.a.a():int");
    }

    public static a a(int i, int i2, int i3) {
        return new a(i, i2, i3);
    }

    public static a a(int i, int i2) {
        return new a(i, i2, -1);
    }

    private a(Parcel parcel) {
        super(parcel);
        this.n = new ArraySet();
        this.o = new ArraySet();
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(h.class.getClassLoader()));
    }

    private a(int i, int i2, int i3) {
        this.n = new ArraySet();
        this.o = new ArraySet();
        a(new h(i, i2, i3));
    }
}
