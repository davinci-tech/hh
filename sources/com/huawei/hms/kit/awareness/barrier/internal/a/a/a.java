package com.huawei.hms.kit.awareness.barrier.internal.a.a;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.ArraySet;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.kit.awareness.b.a.f;
import com.huawei.hms.kit.awareness.b.a.g;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.a.a.1
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
    private static final String k = "BeaconCondition";
    private static final long l = 5000;
    private Set<String> m;
    private Set<String> n;
    private Set<String> o;
    private Set<String> p;
    private long u;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 7;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 9;
    }

    public void y() {
        b(new ArraySet());
        this.u = SystemClock.elapsedRealtime();
    }

    public void x() {
        a((Set<String>) new ArraySet());
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final com.huawei.hms.kit.awareness.barrier.internal.d.b i() {
        return (com.huawei.hms.kit.awareness.barrier.internal.d.b) a(com.huawei.hms.kit.awareness.barrier.internal.d.b.class);
    }

    public boolean c(long j) {
        ArraySet arraySet = new ArraySet();
        if (SystemClock.elapsedRealtime() - this.u > j) {
            this.p.clear();
        } else {
            arraySet.addAll(this.p);
        }
        arraySet.addAll(this.o);
        if (arraySet.equals(this.m)) {
            return false;
        }
        a((Set<String>) arraySet);
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        if (!c(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        if (!c(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermissionByContext-> calling BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (g.a() && !c(context, "android.permission.BLUETOOTH_CONNECT")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermissionByContext-> calling BLUETOOTH_CONNECT denied", new Object[0]);
            return 10104;
        }
        if (d(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return !f.a(context) ? 10104 : 0;
        }
        return 10102;
    }

    public void a(String str) {
        this.o.add(str);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(com.huawei.hms.kit.awareness.barrier.internal.b.f fVar) {
        com.huawei.hms.kit.awareness.b.a.c.a(k, "BeaconCondition check result:" + s(), new Object[0]);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        if (!a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        if (!a(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermission-> calling BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (g.a() && !a(context, "android.permission.BLUETOOTH_CONNECT")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkPermission-> calling BLUETOOTH_CONNECT denied", new Object[0]);
            return 10104;
        }
        if (!b(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return 10102;
        }
        if (!g.a() && !b(context, "android.permission.BLUETOOTH")) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "checkCoreSelfBlueToothPermission()-> BLUETOOTH denied", new Object[0]);
            return 10104;
        }
        if (!g.a() || b(context, "android.permission.BLUETOOTH_CONNECT")) {
            return 0;
        }
        com.huawei.hms.kit.awareness.b.a.c.c(k, "checkCoreSelfBlueToothPermission()-> BLUETOOTH_CONNECT denied", new Object[0]);
        return 10104;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0044, code lost:
    
        if (com.huawei.hms.kit.awareness.barrier.internal.f.d.a((java.util.Set) r5.n, (java.util.Set) r5.m).isEmpty() == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0055, code lost:
    
        b(5000L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
    
        if (com.huawei.hms.kit.awareness.barrier.internal.f.d.a((java.util.Set) r5.m, (java.util.Set) r5.n).isEmpty() == false) goto L21;
     */
    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a() {
        /*
            r5 = this;
            com.huawei.hms.kit.awareness.barrier.internal.d.b r0 = r5.i()
            int r0 = r0.b()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L47
            if (r0 == r2) goto L38
            r3 = 2
            if (r0 == r3) goto L12
            goto L5b
        L12:
            java.util.Set<java.lang.String> r0 = r5.m
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L1b
            goto L30
        L1b:
            com.huawei.hms.kit.awareness.barrier.internal.a.a r0 = r5.n()
            boolean r0 = r0.l()
            if (r0 == 0) goto L2f
            com.huawei.hms.kit.awareness.barrier.internal.a.a r0 = r5.n()
            int r0 = r0.b(r5)
            r2 = r0
            goto L30
        L2f:
            r2 = r1
        L30:
            com.huawei.hms.kit.awareness.barrier.internal.a.a r0 = r5.n()
            r0.a(r5, r2)
            goto L5c
        L38:
            java.util.Set<java.lang.String> r0 = r5.n
            java.util.Set<java.lang.String> r3 = r5.m
            java.util.Set r0 = com.huawei.hms.kit.awareness.barrier.internal.f.d.a(r0, r3)
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L5b
            goto L55
        L47:
            java.util.Set<java.lang.String> r0 = r5.m
            java.util.Set<java.lang.String> r3 = r5.n
            java.util.Set r0 = com.huawei.hms.kit.awareness.barrier.internal.f.d.a(r0, r3)
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L5b
        L55:
            r3 = 5000(0x1388, double:2.4703E-320)
            r5.b(r3)
            goto L5c
        L5b:
            r2 = r1
        L5c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "BeaconCondition enter,mCurrentBeacons is:"
            r0.<init>(r3)
            java.util.Set<java.lang.String> r3 = r5.m
            r0.append(r3)
            java.lang.String r3 = ", mPreviousBeacons is:"
            r0.append(r3)
            java.util.Set<java.lang.String> r3 = r5.n
            r0.append(r3)
            java.lang.String r3 = ",beaconType is:"
            r0.append(r3)
            com.huawei.hms.kit.awareness.barrier.internal.d.b r3 = r5.i()
            int r3 = r3.b()
            r0.append(r3)
            java.lang.String r3 = ",checkResult is:"
            r0.append(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "BeaconCondition"
            com.huawei.hms.kit.awareness.b.a.c.a(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.kit.awareness.barrier.internal.a.a.a.a():int");
    }

    private void b(Set<String> set) {
        this.p = this.o;
        this.o = set;
    }

    private void a(Set<String> set) {
        this.n = this.m;
        this.m = set;
    }

    private static List<com.huawei.hms.kit.awareness.a.a.b> a(Collection<BeaconStatus.Filter> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (BeaconStatus.Filter filter : collection) {
            if (filter instanceof com.huawei.hms.kit.awareness.a.a.b) {
                com.huawei.hms.kit.awareness.a.a.b bVar = (com.huawei.hms.kit.awareness.a.a.b) filter;
                if (bVar.d()) {
                    arrayList.add(new com.huawei.hms.kit.awareness.a.a.b(bVar));
                }
            }
            com.huawei.hms.kit.awareness.b.a.c.d(k, "filter is not BeaconFilter type or the param is invalid.", new Object[0]);
            throw new IllegalArgumentException();
        }
        return arrayList;
    }

    public static a a(Collection<BeaconStatus.Filter> collection, int i) {
        List<com.huawei.hms.kit.awareness.a.a.b> a2 = a(collection);
        if (com.huawei.hms.kit.awareness.barrier.internal.f.a.a(a2) == a2.size()) {
            return new a(a2, i);
        }
        com.huawei.hms.kit.awareness.b.a.c.d(k, "convert to hash failed.", new Object[0]);
        throw new IllegalArgumentException();
    }

    private a(List<com.huawei.hms.kit.awareness.a.a.b> list, int i) {
        this.m = new ArraySet();
        this.n = new ArraySet();
        this.o = new ArraySet();
        this.p = new ArraySet();
        this.u = SystemClock.elapsedRealtime();
        a(new com.huawei.hms.kit.awareness.barrier.internal.d.b(list, i));
    }

    private a(Parcel parcel) {
        super(parcel);
        this.m = new ArraySet();
        this.n = new ArraySet();
        this.o = new ArraySet();
        this.p = new ArraySet();
        this.u = SystemClock.elapsedRealtime();
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(com.huawei.hms.kit.awareness.barrier.internal.d.b.class.getClassLoader()));
    }
}
