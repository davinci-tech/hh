package com.huawei.hms.kit.awareness.barrier.internal.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.b.h;
import com.huawei.hms.kit.awareness.barrier.internal.d.g;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class a extends f {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.a.1
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
    private static final String l = "DuringPeriodOfDayCondition";

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: com.huawei.hms.kit.awareness.barrier.internal.a.f.a$a, reason: collision with other inner class name */
    static final class C0127a extends g {
        public static final Parcelable.Creator<C0127a> CREATOR = new Parcelable.Creator<C0127a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.a.a.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public C0127a[] newArray(int i) {
                return new C0127a[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public C0127a createFromParcel(Parcel parcel) {
                return new C0127a(parcel);
            }
        };

        /* renamed from: a, reason: collision with root package name */
        private final long f4852a;
        private final long b;
        private TimeZone c;

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int a() {
            return 0;
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

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.f4852a);
            parcel.writeLong(this.b);
            TimeZone timeZone = this.c;
            parcel.writeString(timeZone == null ? "" : timeZone.getID());
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
        public boolean g() {
            long j = this.f4852a;
            if (j >= 0) {
                long j2 = this.b;
                if (j <= j2 && j2 <= 86400000) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long f() {
            return this.b;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public TimeZone e() {
            return this.c;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long d() {
            return this.f4852a;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public void a(TimeZone timeZone) {
            this.c = timeZone;
        }

        private C0127a(TimeZone timeZone, long j, long j2) {
            this.c = timeZone;
            this.f4852a = j;
            this.b = j2;
        }

        private C0127a(Parcel parcel) {
            this.f4852a = parcel.readLong();
            this.b = parcel.readLong();
            String readString = parcel.readString();
            this.c = com.huawei.hms.kit.awareness.barrier.internal.f.c.a(readString) ? null : TimeZone.getTimeZone(readString);
        }
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(i(), i);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.e.b
    /* renamed from: w, reason: merged with bridge method [inline-methods] */
    public final C0127a i() {
        return (C0127a) a(C0127a.class);
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.c
    public int a() {
        if (!j()) {
            com.huawei.hms.kit.awareness.b.a.c.a(l, "mStartTimeOfDayMillis is: {0}, mStopTimeOfDayMillis is: {1}", Long.valueOf(i().d()), Long.valueOf(i().f()));
            return 2;
        }
        i().a(com.huawei.hms.kit.awareness.barrier.internal.f.c.a(i().e()) ? this.k.i() : i().e());
        long a2 = h.a();
        long a3 = h.a(i().e());
        return a(a2, i().d() + a3, i().f() + a3);
    }

    public static a a(TimeZone timeZone, long j, long j2) {
        return new a(timeZone, j, j2);
    }

    private a(TimeZone timeZone, long j, long j2) {
        a(new C0127a(timeZone, j, j2));
    }

    private a(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(C0127a.class.getClassLoader()));
    }
}
