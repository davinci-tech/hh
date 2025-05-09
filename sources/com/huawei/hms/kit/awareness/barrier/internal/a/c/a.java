package com.huawei.hms.kit.awareness.barrier.internal.a.c;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.a.c;
import com.huawei.hms.kit.awareness.barrier.internal.b.e;
import com.huawei.hms.kit.awareness.barrier.internal.b.f;
import com.huawei.hms.kit.awareness.barrier.internal.d.d;
import com.huawei.hms.kit.awareness.barrier.internal.e.b;

/* loaded from: classes9.dex */
public class a extends c implements b {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.c.a.1
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
    private static final String k = "HeadsetCondition";
    private static final long l = 5000;
    private e m;
    private e n;

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
        return 0;
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
    public final d i() {
        return (d) a(d.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void b() {
        t();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(f fVar) {
        if (fVar instanceof e) {
            this.m = (e) fVar;
            com.huawei.hms.kit.awareness.b.a.c.a(k, "rootConditionCheck check result:" + s(), new Object[0]);
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (this.m.a() == -1) {
            return 2;
        }
        int b = i().b();
        if (b == 0) {
            return this.m.a() == i().a() ? 1 : 0;
        }
        if (b == 1) {
            return x();
        }
        if (b != 2) {
            return 2;
        }
        return y();
    }

    private int y() {
        int i;
        if (this.m.e() && this.n.b()) {
            b(5000L);
            i = 1;
        } else {
            i = 0;
        }
        this.n = this.m;
        return i;
    }

    private int x() {
        int i;
        if (this.m.b() && this.n.e()) {
            b(5000L);
            i = 1;
        } else {
            i = 0;
        }
        this.n = this.m;
        return i;
    }

    public static a a(int i, int i2) {
        return new a(i, i2);
    }

    public static a a(int i) {
        return new a(-1, i);
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(d.class.getClassLoader()));
        this.m = new e(-1);
        this.n = new e(-1);
    }

    private a(int i, int i2) {
        this.m = new e(-1);
        this.n = new e(-1);
        a(new d(i, i2));
    }
}
