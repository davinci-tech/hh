package com.huawei.hms.kit.awareness.barrier.internal.a.e;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.b.g;
import com.huawei.hms.kit.awareness.barrier.internal.d.e;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.e.a.1
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
    private static final String k = "LocationCondition";
    private static final long l = 5000;
    private g m;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 1;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 8;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final e i() {
        return (e) a(e.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    public g w() {
        return this.m;
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
        if (Build.VERSION.SDK_INT >= 29 && !c(context, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        if (d(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return (Build.VERSION.SDK_INT < 29 || d(context, "android.permission.ACCESS_BACKGROUND_LOCATION")) ? 0 : 10102;
        }
        return 10102;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (fVar instanceof g) {
            this.m = (g) fVar;
            com.huawei.hms.kit.awareness.b.a.c.a(k, "rootConditionCheck check result:" + s(), new Object[0]);
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        if (!a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        if (Build.VERSION.SDK_INT >= 29 && !a(context, "android.permission.ACCESS_BACKGROUND_LOCATION")) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        if (b(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return (Build.VERSION.SDK_INT < 29 || b(context, "android.permission.ACCESS_BACKGROUND_LOCATION")) ? 0 : 10102;
        }
        return 10102;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (this.m.a() == 2) {
            return 2;
        }
        int a2 = this.m.a();
        int i = AnonymousClass2.f4851a[i().e().ordinal()];
        if ((i == 1 || i == 2) && a2 == 1) {
            b(5000L);
        }
        return a2;
    }

    public static a a(double d, double d2, double d3, com.huawei.hms.kit.awareness.barrier.internal.type.g gVar, long j) {
        return new a(d, d2, d3, gVar, j);
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(e.class.getClassLoader()));
    }

    /* renamed from: com.huawei.hms.kit.awareness.barrier.internal.a.e.a$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4851a;

        static {
            int[] iArr = new int[com.huawei.hms.kit.awareness.barrier.internal.type.g.values().length];
            f4851a = iArr;
            try {
                iArr[com.huawei.hms.kit.awareness.barrier.internal.type.g.ENTERING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4851a[com.huawei.hms.kit.awareness.barrier.internal.type.g.EXITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private a(double d, double d2, double d3, com.huawei.hms.kit.awareness.barrier.internal.type.g gVar, long j) {
        a(e.a(d2, d, d3, j, gVar));
    }
}
