package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.OpenEndRange;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001aB\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u001a\u0010\b\u001a\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u001b"}, d2 = {"Lkotlin/ranges/LongRange;", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/ClosedRange;", "", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "(JJ)V", "endExclusive", "getEndExclusive$annotations", "()V", "getEndExclusive", "()Ljava/lang/Long;", "getEndInclusive", "getStart", "contains", "", "value", "equals", "other", "", WatchFaceConstant.HASHCODE, "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uit extends uiu implements ClosedRange<Long>, OpenEndRange<Long> {
    public static final a e = new a(null);

    /* renamed from: a, reason: collision with root package name */
    private static final uit f17435a = new uit(1, 0);

    public uit(long j, long j2) {
        super(j, j2, 1L);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* synthetic */ boolean contains(Long l) {
        return b(l.longValue());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public Long getStart() {
        return Long.valueOf(getB());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public Long getEndInclusive() {
        return Long.valueOf(getE());
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Long getEndExclusive() {
        if (getE() == LocationRequestCompat.PASSIVE_INTERVAL) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
        }
        return Long.valueOf(getE() + 1);
    }

    public boolean b(long j) {
        return getB() <= j && j <= getE();
    }

    @Override // defpackage.uiu, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return getB() > getE();
    }

    @Override // defpackage.uiu
    public boolean equals(Object other) {
        if (other instanceof uit) {
            if (!isEmpty() || !((uit) other).isEmpty()) {
                uit uitVar = (uit) other;
                if (getB() != uitVar.getB() || getE() != uitVar.getE()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // defpackage.uiu
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (int) ((31 * (getB() ^ (getB() >>> 32))) + (getE() ^ (getE() >>> 32)));
    }

    @Override // defpackage.uiu
    public String toString() {
        return getB() + ".." + getE();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/LongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/LongRange;", "getEMPTY", "()Lkotlin/ranges/LongRange;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(uib uibVar) {
            this();
        }
    }
}
