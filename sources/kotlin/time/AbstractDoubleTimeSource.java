package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hms.network.embedded.g4;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.MAX_MILLIS;
import defpackage.uhy;
import defpackage.uib;
import defpackage.ukf;
import defpackage.ukm;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;

@Deprecated(message = "Using AbstractDoubleTimeSource is no longer recommended, use AbstractLongTimeSource instead.")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "markNow", "Lkotlin/time/ComparableTimeMark;", "read", "", "DoubleTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractDoubleTimeSource implements TimeSource.WithComparableMarks {
    private final DurationUnit unit;

    protected abstract double read();

    public AbstractDoubleTimeSource(DurationUnit durationUnit) {
        uhy.e((Object) durationUnit, "");
        this.unit = durationUnit;
    }

    protected final DurationUnit getUnit() {
        return this.unit;
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001a\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001c"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource$DoubleTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractDoubleTimeSource;", TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlin/time/Duration;", "(DLkotlin/time/AbstractDoubleTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", RequestOptions.AD_CONTENT_CLASSIFICATION_J, "elapsedNow", "elapsedNow-UwyO8pc", "()J", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "", "minus", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class b implements ComparableTimeMark {

        /* renamed from: a, reason: collision with root package name */
        private final AbstractDoubleTimeSource f14481a;
        private final double c;
        private final long d;

        private b(double d, AbstractDoubleTimeSource abstractDoubleTimeSource, long j) {
            uhy.e((Object) abstractDoubleTimeSource, "");
            this.c = d;
            this.f14481a = abstractDoubleTimeSource;
            this.d = j;
        }

        @Override // java.lang.Comparable
        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.c.b(this, comparableTimeMark);
        }

        @Override // kotlin.time.TimeMark
        public boolean hasNotPassedNow() {
            return ComparableTimeMark.c.a(this);
        }

        @Override // kotlin.time.TimeMark
        public boolean hasPassedNow() {
            return ComparableTimeMark.c.e(this);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: minus-LRDsOJo, reason: not valid java name */
        public ComparableTimeMark mo973minusLRDsOJo(long j) {
            return ComparableTimeMark.c.b(this, j);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
        public long mo972elapsedNowUwyO8pc() {
            return ukf.b(MAX_MILLIS.c(this.f14481a.read() - this.c, this.f14481a.getUnit()), this.d);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo, reason: not valid java name */
        public ComparableTimeMark mo975plusLRDsOJo(long duration) {
            return new b(this.c, this.f14481a, ukf.a(this.d, duration), null);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc, reason: not valid java name */
        public long mo974minusUwyO8pc(ComparableTimeMark other) {
            uhy.e((Object) other, "");
            if (other instanceof b) {
                b bVar = (b) other;
                if (uhy.e(this.f14481a, bVar.f14481a)) {
                    if (ukf.c(this.d, bVar.d) && ukf.l(this.d)) {
                        return ukf.d.b();
                    }
                    long b = ukf.b(this.d, bVar.d);
                    long c = MAX_MILLIS.c(this.c - bVar.c, this.f14481a.getUnit());
                    return ukf.c(c, ukf.t(b)) ? ukf.d.b() : ukf.a(c, b);
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(Object other) {
            return (other instanceof b) && uhy.e(this.f14481a, ((b) other).f14481a) && ukf.c(mo974minusUwyO8pc((ComparableTimeMark) other), ukf.d.b());
        }

        @Override // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return ukf.n(ukf.a(MAX_MILLIS.c(this.c, this.f14481a.getUnit()), this.d));
        }

        public String toString() {
            return "DoubleTimeMark(" + this.c + ukm.c(this.f14481a.getUnit()) + " + " + ((Object) ukf.s(this.d)) + ", " + this.f14481a + g4.l;
        }

        public /* synthetic */ b(double d, AbstractDoubleTimeSource abstractDoubleTimeSource, long j, uib uibVar) {
            this(d, abstractDoubleTimeSource, j);
        }
    }

    @Override // kotlin.time.TimeSource
    public ComparableTimeMark markNow() {
        return new b(read(), this, ukf.d.b(), null);
    }
}
