package defpackage;

import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\t\u0010\u0014\u001a\u00020\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0007\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lkotlin/ranges/LongProgression;", "", "", "start", "endInclusive", "step", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "", "isEmpty", "iterator", "Lkotlin/collections/LongIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public class uiu implements Iterable<Long>, KMappedMarker {
    public static final a c = new a(null);

    /* renamed from: a, reason: collision with root package name */
    private final long f17436a;
    private final long b;
    private final long e;

    public uiu(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.b = j;
        this.e = differenceModulo.a(j, j2, j3);
        this.f17436a = j3;
    }

    /* renamed from: e, reason: from getter */
    public final long getB() {
        return this.b;
    }

    /* renamed from: b, reason: from getter */
    public final long getE() {
        return this.e;
    }

    @Override // java.lang.Iterable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public LongIterator iterator() {
        return new uis(this.b, this.e, this.f17436a);
    }

    public boolean isEmpty() {
        long j = this.f17436a;
        long j2 = this.b;
        long j3 = this.e;
        return j <= 0 ? j2 < j3 : j2 > j3;
    }

    public boolean equals(Object other) {
        if (other instanceof uiu) {
            if (!isEmpty() || !((uiu) other).isEmpty()) {
                uiu uiuVar = (uiu) other;
                if (this.b != uiuVar.b || this.e != uiuVar.e || this.f17436a != uiuVar.f17436a) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j = 31;
        long j2 = this.b;
        long j3 = this.e;
        long j4 = this.f17436a;
        return (int) ((j * (((j2 ^ (j2 >>> 32)) * j) + (j3 ^ (j3 >>> 32)))) + ((j4 >>> 32) ^ j4));
    }

    public String toString() {
        StringBuilder sb;
        long j;
        if (this.f17436a > 0) {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append("..");
            sb.append(this.e);
            sb.append(" step ");
            j = this.f17436a;
        } else {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append(" downTo ");
            sb.append(this.e);
            sb.append(" step ");
            j = -this.f17436a;
        }
        sb.append(j);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lkotlin/ranges/LongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(uib uibVar) {
            this();
        }
    }
}
