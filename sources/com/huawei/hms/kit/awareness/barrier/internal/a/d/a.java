package com.huawei.hms.kit.awareness.barrier.internal.a.d;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.d.a.1
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
    private static final String k = "AmbientLightCondition";
    private com.huawei.hms.kit.awareness.barrier.internal.b.b l;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 5;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 5;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final com.huawei.hms.kit.awareness.barrier.internal.d.a i() {
        return (com.huawei.hms.kit.awareness.barrier.internal.d.a) a(com.huawei.hms.kit.awareness.barrier.internal.d.a.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (!(fVar instanceof com.huawei.hms.kit.awareness.barrier.internal.b.b)) {
            com.huawei.hms.kit.awareness.b.a.c.c(k, "The info is not instance of AmbientLightInfo", new Object[0]);
            return;
        }
        this.l = (com.huawei.hms.kit.awareness.barrier.internal.b.b) fVar;
        com.huawei.hms.kit.awareness.b.a.c.a(k, "rootConditionCheck check result:" + s(), new Object[0]);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        return a(this.l.a());
    }

    private boolean b(float f) {
        return i().a() <= f && f < i().b();
    }

    public static a a(float f, float f2, int i) {
        return new a(f, f2, i);
    }

    private int a(float f) {
        int c = i().c();
        if (c == 0 || c == 1 || c == 2) {
            return b(f) ? 1 : 0;
        }
        return 2;
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(com.huawei.hms.kit.awareness.barrier.internal.d.a.class.getClassLoader()));
    }

    private a(float f, float f2, int i) {
        a(new com.huawei.hms.kit.awareness.barrier.internal.d.a(f, f2, i));
    }
}
