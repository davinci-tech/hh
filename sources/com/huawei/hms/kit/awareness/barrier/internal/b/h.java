package com.huawei.hms.kit.awareness.barrier.internal.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class h extends f implements Parcelable {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.b.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i) {
            return new h[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }
    };
    private static final int b = 0;
    private static final int c = 2;
    private static final int d = 2;
    private static final String e = "TimeInfo";
    private static final long j = -1;
    private int f;
    private int g;
    private long h;
    private List<Long> i;
    private long k;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeLong(this.h);
        parcel.writeList(this.i);
    }

    public String toString() {
        return "TimeInfo{mIsHoliday=" + this.f + ", mIsWeekend=" + this.g + ", mSunTimeState=" + this.i + ", mTimeOfGetTimeInfoFromCloud=" + this.h + ", mTimeDifferenceStamp=" + this.k + '}';
    }

    public boolean m() {
        return a(this.h, a(), i());
    }

    public TimeZone l() {
        long j2 = this.k;
        return j2 == -1 ? TimeZone.getDefault() : c(j2);
    }

    public int j() {
        return this.f;
    }

    public TimeZone i() {
        return this.i.size() != 3 ? TimeZone.getDefault() : c(e());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f), Integer.valueOf(this.g), Long.valueOf(this.h), this.i);
    }

    public int h() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return a(this.h, hVar.h, i()) && Objects.equals(this.i, hVar.i) && this.f == hVar.f && this.g == hVar.g;
    }

    public long e() {
        if (b()) {
            return this.i.get(2).longValue();
        }
        return 0L;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.TIME;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public final boolean c() {
        int i;
        int i2;
        return this.i.size() == 3 && (i = this.f) >= 0 && i <= 2 && (i2 = this.g) >= 0 && i2 <= 2;
    }

    public long c(int i) {
        if (!b()) {
            return 0L;
        }
        if (i == 0 || i == 1) {
            return this.i.get(i).longValue();
        }
        return 0L;
    }

    public boolean b() {
        return this.i.size() == 3;
    }

    public void b(List<Long> list) {
        if (list == null || list.size() != 3) {
            return;
        }
        this.i = list;
    }

    public void b(long j2) {
        this.k = j2;
    }

    public void b(int i) {
        this.g = i;
    }

    public void a(long j2) {
        this.h = j2;
    }

    public void a(int i) {
        this.f = i;
    }

    public int a(int i, TimeZone timeZone) {
        long currentTimeMillis = System.currentTimeMillis();
        if (i == 6) {
            if (this.g == 2 || !a(this.h, currentTimeMillis, timeZone)) {
                return 2;
            }
            return this.g == 0 ? 1 : 0;
        }
        if (i == 7 && this.g != 2 && a(this.h, currentTimeMillis, timeZone)) {
            return this.g;
        }
        return 2;
    }

    public static long k() {
        return SystemClock.elapsedRealtime();
    }

    public static TimeZone c(long j2) {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        try {
            String[] availableIDs = TimeZone.getAvailableIDs((int) j2);
            return (com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Object[]) availableIDs) || (str = availableIDs[0]) == null) ? timeZone : TimeZone.getTimeZone(str);
        } catch (Exception unused) {
            com.huawei.hms.kit.awareness.b.a.c.d(e, "timezone getavailableids throw Exception", new Object[0]);
            return timeZone;
        }
    }

    private static boolean a(long j2, long j3, TimeZone timeZone) {
        return Math.abs(j2 - j3) < 86400000 && (((long) timeZone.getOffset(j2)) + j2) / 86400000 == (((long) timeZone.getOffset(j3)) + j3) / 86400000;
    }

    public static long a(TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    private h(Parcel parcel) {
        this.f = 2;
        this.g = 2;
        this.i = new ArrayList();
        this.k = -1L;
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readLong();
        parcel.readList(this.i, Long.class.getClassLoader());
    }

    public h() {
        this.f = 2;
        this.g = 2;
        this.i = new ArrayList();
        this.k = -1L;
    }
}
