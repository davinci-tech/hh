package com.huawei.hms.kit.awareness.barrier.internal.a.b;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.d;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.b.a.1
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
    public static final String k = "com.huawei.hms.permission.ACTIVITY_RECOGNITION";
    private static final String l = "BehaviorCondition";
    private static final long m = 5000;
    private final int n;
    private int o;
    private int p;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 3;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 9;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
        parcel.writeInt(this.n);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final com.huawei.hms.kit.awareness.barrier.internal.d.c i() {
        return (com.huawei.hms.kit.awareness.barrier.internal.d.c) a(com.huawei.hms.kit.awareness.barrier.internal.d.c.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean j() {
        return i().g() && com.huawei.hms.kit.awareness.barrier.internal.type.c.a(this.n);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b(f fVar) {
        if (fVar instanceof d) {
            int a2 = ((d) fVar).a();
            this.o = a2;
            this.p = a2;
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        return (Build.VERSION.SDK_INT > 28 ? !c(context, "android.permission.ACTIVITY_RECOGNITION") : !c(context, k)) ? 10103 : 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (fVar instanceof d) {
            this.o = ((d) fVar).a();
            s();
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        return (Build.VERSION.SDK_INT > 28 ? !a(context, "android.permission.ACTIVITY_RECOGNITION") : !a(context, k)) ? 10103 : 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0013, code lost:
    
        if (y() != false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        b(5000L);
        r1 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x001a, code lost:
    
        if (x() != false) goto L15;
     */
    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a() {
        /*
            r5 = this;
            int r0 = r5.n
            r1 = 2
            if (r0 == r1) goto L16
            r2 = 3
            if (r0 == r2) goto Lf
            r2 = 4
            if (r0 == r2) goto Lc
            goto L24
        Lc:
            int r1 = r5.o
            goto L24
        Lf:
            boolean r0 = r5.y()
            if (r0 == 0) goto L23
            goto L1c
        L16:
            boolean r0 = r5.x()
            if (r0 == 0) goto L23
        L1c:
            r0 = 5000(0x1388, double:2.4703E-320)
            r5.b(r0)
            r1 = 1
            goto L24
        L23:
            r1 = 0
        L24:
            int r0 = r5.n
            java.lang.String r0 = com.huawei.hms.kit.awareness.barrier.internal.type.c.b(r0)
            com.huawei.hms.kit.awareness.barrier.internal.d.c r2 = r5.i()
            java.lang.String r2 = r2.toString()
            int r3 = r5.o
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2, r3, r4}
            java.lang.String r2 = "BehaviorCondition"
            java.lang.String r3 = "TransitionType:{0}; {1}; mState:{2}; check result:{3}"
            com.huawei.hms.kit.awareness.b.a.c.a(r2, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.kit.awareness.barrier.internal.a.b.a.a():int");
    }

    private boolean y() {
        int i = this.o;
        boolean z = i != this.p && i == 0;
        this.p = i;
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0007, code lost:
    
        if (r0 == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean x() {
        /*
            r2 = this;
            int r0 = r2.o
            int r1 = r2.p
            if (r0 == r1) goto La
            r1 = 1
            if (r0 != r1) goto La
            goto Lb
        La:
            r1 = 0
        Lb:
            r2.p = r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.kit.awareness.barrier.internal.a.b.a.x():boolean");
    }

    public static a a(int i, int... iArr) {
        if (com.huawei.hms.kit.awareness.barrier.internal.d.c.a(iArr) && com.huawei.hms.kit.awareness.barrier.internal.type.c.a(i)) {
            return new a(i, iArr);
        }
        com.huawei.hms.kit.awareness.b.a.c.d(l, "Invalid parameter.", new Object[0]);
        return null;
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(com.huawei.hms.kit.awareness.barrier.internal.d.c.class.getClassLoader()));
        this.n = parcel.readInt();
        this.o = 2;
        this.p = 2;
    }

    private a(int i, int[] iArr) {
        a(new com.huawei.hms.kit.awareness.barrier.internal.d.c(iArr));
        this.n = i;
        this.o = 2;
        this.p = 2;
    }
}
