package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hms.network.embedded.g4;
import com.huawei.openalliance.ad.beans.parameter.RequestOptions;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.MAX_MILLIS;
import defpackage.checkInfiniteSumDefined;
import defpackage.ueh;
import defpackage.uhy;
import defpackage.uib;
import defpackage.uij;
import defpackage.ukf;
import defpackage.ukm;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\bH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "zero", "", "getZero", "()J", "zero$delegate", "Lkotlin/Lazy;", "adjustedRead", "markNow", "Lkotlin/time/ComparableTimeMark;", "read", "LongTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {
    private final DurationUnit unit;

    /* renamed from: zero$delegate, reason: from kotlin metadata */
    private final Lazy zero;

    protected abstract long read();

    public AbstractLongTimeSource(DurationUnit durationUnit) {
        uhy.e((Object) durationUnit, "");
        this.unit = durationUnit;
        this.zero = ueh.a(new e());
    }

    protected final DurationUnit getUnit() {
        return this.unit;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", TrackConstants$Opers.INVOKE, "()Ljava/lang/Long;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class e extends Lambda implements Function0<Long> {
        @Override // kotlin.jvm.functions.Function0
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Long invoke() {
            return Long.valueOf(AbstractLongTimeSource.this.read());
        }

        e() {
            super(0);
        }
    }

    private final long getZero() {
        return ((Number) this.zero.getValue()).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long adjustedRead() {
        return read() - getZero();
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001a\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0001H\u0096\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0007H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0006\u001a\u00020\u0007X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001c"}, d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractLongTimeSource;", TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlin/time/Duration;", "(JLkotlin/time/AbstractLongTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", RequestOptions.AD_CONTENT_CLASSIFICATION_J, "elapsedNow", "elapsedNow-UwyO8pc", "()J", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "", "minus", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class b implements ComparableTimeMark {

        /* renamed from: a, reason: collision with root package name */
        private final AbstractLongTimeSource f14482a;
        private final long c;
        private final long d;

        private b(long j, AbstractLongTimeSource abstractLongTimeSource, long j2) {
            uhy.e((Object) abstractLongTimeSource, "");
            this.c = j;
            this.f14482a = abstractLongTimeSource;
            this.d = j2;
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
        /* renamed from: minus-LRDsOJo */
        public ComparableTimeMark mo973minusLRDsOJo(long j) {
            return ComparableTimeMark.c.b(this, j);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: elapsedNow-UwyO8pc */
        public long mo972elapsedNowUwyO8pc() {
            return ukf.b(checkInfiniteSumDefined.e(this.f14482a.adjustedRead(), this.c, this.f14482a.getUnit()), this.d);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo */
        public ComparableTimeMark mo975plusLRDsOJo(long duration) {
            DurationUnit unit = this.f14482a.getUnit();
            if (ukf.l(duration)) {
                return new b(checkInfiniteSumDefined.a(this.c, unit, duration), this.f14482a, ukf.d.b(), null);
            }
            long a2 = ukf.a(duration, unit);
            long a3 = ukf.a(ukf.b(duration, a2), this.d);
            long a4 = checkInfiniteSumDefined.a(this.c, unit, a2);
            long a5 = ukf.a(a3, unit);
            long a6 = checkInfiniteSumDefined.a(a4, unit, a5);
            long b = ukf.b(a3, a5);
            long f = ukf.f(b);
            if (a6 != 0 && f != 0 && (a6 ^ f) < 0) {
                long a7 = MAX_MILLIS.a(uij.d(f), unit);
                a6 = checkInfiniteSumDefined.a(a6, unit, a7);
                b = ukf.b(b, a7);
            }
            if ((1 | (a6 - 1)) == LocationRequestCompat.PASSIVE_INTERVAL) {
                b = ukf.d.b();
            }
            return new b(a6, this.f14482a, b, null);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc */
        public long mo974minusUwyO8pc(ComparableTimeMark other) {
            uhy.e((Object) other, "");
            if (other instanceof b) {
                b bVar = (b) other;
                if (uhy.e(this.f14482a, bVar.f14482a)) {
                    return ukf.a(checkInfiniteSumDefined.e(this.c, bVar.c, this.f14482a.getUnit()), ukf.b(this.d, bVar.d));
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(Object other) {
            return (other instanceof b) && uhy.e(this.f14482a, ((b) other).f14482a) && ukf.c(mo974minusUwyO8pc((ComparableTimeMark) other), ukf.d.b());
        }

        @Override // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return (ukf.n(this.d) * 37) + Long.hashCode(this.c);
        }

        public String toString() {
            return "LongTimeMark(" + this.c + ukm.c(this.f14482a.getUnit()) + " + " + ((Object) ukf.s(this.d)) + ", " + this.f14482a + g4.l;
        }

        public /* synthetic */ b(long j, AbstractLongTimeSource abstractLongTimeSource, long j2, uib uibVar) {
            this(j, abstractLongTimeSource, j2);
        }
    }

    @Override // kotlin.time.TimeSource
    public ComparableTimeMark markNow() {
        return new b(adjustedRead(), this, ukf.d.b(), null);
    }
}
