package defpackage;

import kotlin.Metadata;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a\f\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007\u001a\f\u0010\n\u001a\u00020\t*\u00020\u0004H\u0007¨\u0006\u000b"}, d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "toDurationUnit", "Ljava/util/concurrent/TimeUnit;", "toTimeUnit", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/time/DurationUnitKt")
/* renamed from: ukl, reason: from Kotlin metadata */
/* loaded from: classes10.dex */
class convertDurationUnit {
    public static final double c(double d, DurationUnit durationUnit, DurationUnit durationUnit2) {
        uhy.e((Object) durationUnit, "");
        uhy.e((Object) durationUnit2, "");
        long convert = durationUnit2.getTimeUnit().convert(1L, durationUnit.getTimeUnit());
        return convert > 0 ? d * convert : d / durationUnit.getTimeUnit().convert(1L, durationUnit2.getTimeUnit());
    }

    public static final long b(long j, DurationUnit durationUnit, DurationUnit durationUnit2) {
        uhy.e((Object) durationUnit, "");
        uhy.e((Object) durationUnit2, "");
        return durationUnit2.getTimeUnit().convert(j, durationUnit.getTimeUnit());
    }

    public static final long a(long j, DurationUnit durationUnit, DurationUnit durationUnit2) {
        uhy.e((Object) durationUnit, "");
        uhy.e((Object) durationUnit2, "");
        return durationUnit2.getTimeUnit().convert(j, durationUnit.getTimeUnit());
    }
}
