package com.huawei.hms.kit.awareness.barrier.internal.a.h;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.b.l;
import com.huawei.hms.kit.awareness.barrier.internal.d.i;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.h.a.1
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
    private static final String k = "ZenModeCondition";
    private int l;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public boolean c() {
        return true;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int d() {
        return 10;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int o() {
        return 7;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final i i() {
        return (i) a(i.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (fVar instanceof l) {
            this.l = ((l) fVar).a();
            com.huawei.hms.kit.awareness.b.a.c.a(k, "zenModeCondition Check check result:" + s(), new Object[0]);
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (this.l == -1) {
            return 2;
        }
        int a2 = i().a();
        if (a2 == 0) {
            return this.l == 1 ? 1 : 0;
        }
        if (a2 != 1) {
            return 2;
        }
        return this.l == 0 ? 1 : 0;
    }

    public static a a(int i) {
        return new a(i);
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(i.class.getClassLoader()));
    }

    private a(int i) {
        this.l = -1;
        a(new i(i));
    }
}
