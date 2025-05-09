package defpackage;

import androidx.core.location.LocationRequestCompat;
import defpackage.ukf;
import kotlin.Metadata;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a*\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a(\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a(\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a(\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0001H\u0080\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"checkInfiniteSumDefined", "", "value", "duration", "Lkotlin/time/Duration;", "durationInUnit", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "infinityOfSign", "(J)J", "saturatingAdd", "unit", "Lkotlin/time/DurationUnit;", "saturatingAdd-NuflL3o", "(JLkotlin/time/DurationUnit;J)J", "saturatingAddInHalves", "saturatingAddInHalves-NuflL3o", "saturatingDiff", "valueNs", "origin", "(JJLkotlin/time/DurationUnit;)J", "saturatingFiniteDiff", "value1", "value2", "saturatingOriginsDiff", "origin1", "origin2", "isSaturated", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: ukq, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
public final class checkInfiniteSumDefined {
    public static final long a(long j, DurationUnit durationUnit, long j2) {
        uhy.e((Object) durationUnit, "");
        long e = ukf.e(j2, durationUnit);
        if (((j - 1) | 1) == LocationRequestCompat.PASSIVE_INTERVAL) {
            return e(j, j2, e);
        }
        if ((1 | (e - 1)) == LocationRequestCompat.PASSIVE_INTERVAL) {
            return d(j, durationUnit, j2);
        }
        long j3 = j + e;
        if (((j ^ j3) & (e ^ j3)) >= 0) {
            return j3;
        }
        if (j < 0) {
            return Long.MIN_VALUE;
        }
        return LocationRequestCompat.PASSIVE_INTERVAL;
    }

    private static final long e(long j, long j2, long j3) {
        if (!ukf.l(j2) || (j ^ j3) >= 0) {
            return j;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    private static final long d(long j, DurationUnit durationUnit, long j2) {
        long d = ukf.d(j2, 2);
        long e = ukf.e(d, durationUnit);
        return (1 | (e - 1)) == LocationRequestCompat.PASSIVE_INTERVAL ? e : a(a(j, durationUnit, d), durationUnit, ukf.b(j2, d));
    }

    private static final long d(long j) {
        return j < 0 ? ukf.d.c() : ukf.d.d();
    }

    public static final long e(long j, long j2, DurationUnit durationUnit) {
        uhy.e((Object) durationUnit, "");
        if (((j2 - 1) | 1) == LocationRequestCompat.PASSIVE_INTERVAL) {
            if (j == j2) {
                return ukf.d.b();
            }
            return ukf.t(d(j2));
        }
        if ((1 | (j - 1)) == LocationRequestCompat.PASSIVE_INTERVAL) {
            return d(j);
        }
        return a(j, j2, durationUnit);
    }

    private static final long a(long j, long j2, DurationUnit durationUnit) {
        long j3 = j - j2;
        if (((j3 ^ j) & (~(j3 ^ j2))) < 0) {
            if (durationUnit.compareTo(DurationUnit.MILLISECONDS) < 0) {
                long a2 = ukm.a(1L, DurationUnit.MILLISECONDS, durationUnit);
                ukf.a aVar = ukf.d;
                return ukf.a(MAX_MILLIS.d((j / a2) - (j2 / a2), DurationUnit.MILLISECONDS), MAX_MILLIS.d((j % a2) - (j2 % a2), durationUnit));
            }
            return ukf.t(d(j3));
        }
        return MAX_MILLIS.d(j3, durationUnit);
    }
}
