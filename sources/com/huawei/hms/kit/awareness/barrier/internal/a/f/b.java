package com.huawei.hms.kit.awareness.barrier.internal.a.f;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.internal.b.h;
import com.huawei.hms.kit.awareness.barrier.internal.d.g;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class b extends f {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }
    };
    private static final String l = "DuringPeriodOfWeekCondition";

    @Override // com.huawei.hms.kit.awareness.barrier.internal.a.f.f, com.huawei.hms.kit.awareness.barrier.internal.a.c, com.huawei.hms.kit.awareness.barrier.AwarenessBarrier, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static final class a extends g {
        public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.a.f.b.a.1
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
        private static final int f4853a = 1;
        private static final int b = 7;
        private final int c;
        private final long d;
        private final long e;
        private TimeZone f;

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
            parcel.writeInt(this.c);
            parcel.writeLong(this.d);
            parcel.writeLong(this.e);
            TimeZone timeZone = this.f;
            parcel.writeString(timeZone == null ? "" : timeZone.getID());
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
        public boolean g() {
            int i = this.c;
            if (i >= 1 && i <= 7) {
                long j = this.d;
                if (j >= 0) {
                    long j2 = this.e;
                    if (j <= j2 && j2 <= 86400000) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long f() {
            return this.e;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public TimeZone e() {
            return this.f;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public long d() {
            return this.d;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public void a(TimeZone timeZone) {
            this.f = timeZone;
        }

        @Override // com.huawei.hms.kit.awareness.barrier.internal.d.g
        public int a() {
            return this.c;
        }

        private a(Parcel parcel) {
            this.c = parcel.readInt();
            this.d = parcel.readLong();
            this.e = parcel.readLong();
            String readString = parcel.readString();
            this.f = com.huawei.hms.kit.awareness.barrier.internal.f.c.a(readString) ? null : TimeZone.getTimeZone(readString);
        }

        private a(int i, TimeZone timeZone, long j, long j2) {
            this.c = i;
            this.f = timeZone;
            this.d = j;
            this.e = j2;
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
        if (!j()) {
            com.huawei.hms.kit.awareness.b.a.c.a(l, "mStartTimeOfDayMillis is: {0}, mStopTimeOfDayMillis is: {1}", Long.valueOf(i().d()), Long.valueOf(i().f()));
            return 2;
        }
        i().a(com.huawei.hms.kit.awareness.barrier.internal.f.c.a(i().e()) ? this.k.i() : i().e());
        long a2 = h.a();
        long a3 = h.a(i().e());
        int a4 = a(i().e());
        return a(a2, ((i().a() - a4) * 86400000) + a3 + i().d(), i().f() + a3 + ((i().a() - a4) * 86400000));
    }

    public static b a(int i, TimeZone timeZone, long j, long j2) {
        return new b(i, timeZone, j, j2);
    }

    private b(Parcel parcel) {
        super(parcel);
        a((com.huawei.hms.kit.awareness.barrier.internal.d.f) parcel.readParcelable(a.class.getClassLoader()));
    }

    private b(int i, TimeZone timeZone, long j, long j2) {
        a(new a(i, timeZone, j, j2));
    }
}
