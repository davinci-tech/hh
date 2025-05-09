package com.huawei.hms.kit.awareness.barrier.internal.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.b.h;
import com.huawei.hms.kit.awareness.barrier.internal.d.g;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class d extends f {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }
    };
    private static final String l = "InSunriseOrSunsetPeriodCondition";

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static final class a extends g {
        public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.d.a.1
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

        /* renamed from: a, reason: collision with root package name */
        private final int f4855a;
        private final long b;
        private final long c;

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int a() {
            return 0;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public void a(TimeZone timeZone) {
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int b() {
            return 0;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public TimeZone e() {
            return null;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f4855a);
            parcel.writeLong(this.b);
            parcel.writeLong(this.c);
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
        public boolean g() {
            int i = this.f4855a;
            if (i == 0 || i == 1) {
                return Math.abs(this.b) <= 86400000 && Math.abs(this.c) <= 86400000 && this.b < this.c;
            }
            return false;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long f() {
            return this.c;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long d() {
            return this.b;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int c() {
            return this.f4855a;
        }

        private a(Parcel parcel) {
            this.f4855a = parcel.readInt();
            this.b = parcel.readLong();
            this.c = parcel.readLong();
        }

        private a(int i, long j, long j2) {
            this.f4855a = i;
            this.b = j;
            this.c = j2;
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final a i() {
        return (a) a(a.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.e.b
    public void a(com.huawei.hms.kit.awareness.barrier.internal.b.f fVar) {
        if (fVar instanceof h) {
            h hVar = (h) fVar;
            if (hVar.b()) {
                this.k = hVar;
            }
        }
        com.huawei.hms.kit.awareness.b.a.c.a(l, "rootConditionCheck check result:" + s(), new Object[0]);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        long c = this.k.c(i().c());
        if (c != 0 && j()) {
            return a(h.a(), c + i().d(), c + i().f());
        }
        com.huawei.hms.kit.awareness.b.a.c.a(l, "The parameters are illegal! ", new Object[0]);
        return 2;
    }

    public static d a(int i, long j, long j2) {
        return new d(i, j, j2);
    }

    private d(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(a.class.getClassLoader()));
    }

    private d(int i, long j, long j2) {
        a(new a(i, j, j2));
    }
}
