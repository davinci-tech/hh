package com.huawei.hms.kit.awareness.barrier.internal.a.f;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.b.h;
import com.huawei.hms.kit.awareness.barrier.internal.d.g;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class c extends f {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }
    };
    private static final String l = "DuringTimePeriodCondition";

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int b(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static final class a extends g {
        public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.c.a.1
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
        private final long f4854a;
        private final long b;

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

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int c() {
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
            parcel.writeLong(this.f4854a);
            parcel.writeLong(this.b);
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
        public boolean g() {
            long j = this.f4854a;
            return j >= 0 && j <= this.b;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long f() {
            return this.b;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long d() {
            return this.f4854a;
        }

        private a(Parcel parcel) {
            this.f4854a = parcel.readLong();
            this.b = parcel.readLong();
        }

        private a(long j, long j2) {
            this.f4854a = j;
            this.b = j2;
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

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (j()) {
            return a(h.a(), i().d(), i().f());
        }
        com.huawei.hms.kit.awareness.b.a.c.a(l, "mStartTimeMillis is: {0}, mStopTimeMillis is: {1}", Long.valueOf(i().d()), Long.valueOf(i().f()));
        return 2;
    }

    public static c a(long j, long j2) {
        return new c(j, j2);
    }

    private c(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(a.class.getClassLoader()));
    }

    private c(long j, long j2) {
        a(new a(j, j2));
    }
}
