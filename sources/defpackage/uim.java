package defpackage;

import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\t\u0010\u0013\u001a\u00020\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0007\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lkotlin/ranges/IntProgression;", "", "", "start", "endInclusive", "step", "(III)V", "first", "getFirst", "()I", "last", "getLast", "getStep", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "isEmpty", "iterator", "Lkotlin/collections/IntIterator;", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public class uim implements Iterable<Integer>, KMappedMarker {
    public static final b e = new b(null);

    /* renamed from: a, reason: collision with root package name */
    private final int f17433a;
    private final int b;
    private final int d;

    public uim(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i3 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.b = i;
        this.f17433a = differenceModulo.c(i, i2, i3);
        this.d = i3;
    }

    /* renamed from: d, reason: from getter */
    public final int getB() {
        return this.b;
    }

    /* renamed from: a, reason: from getter */
    public final int getF17433a() {
        return this.f17433a;
    }

    /* renamed from: e, reason: from getter */
    public final int getD() {
        return this.d;
    }

    @Override // java.lang.Iterable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public IntIterator iterator() {
        return new uir(this.b, this.f17433a, this.d);
    }

    public boolean isEmpty() {
        return this.d <= 0 ? this.b < this.f17433a : this.b > this.f17433a;
    }

    public boolean equals(Object other) {
        if (other instanceof uim) {
            if (!isEmpty() || !((uim) other).isEmpty()) {
                uim uimVar = (uim) other;
                if (this.b != uimVar.b || this.f17433a != uimVar.f17433a || this.d != uimVar.d) {
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
        return (((this.b * 31) + this.f17433a) * 31) + this.d;
    }

    public String toString() {
        StringBuilder sb;
        int i;
        if (this.d > 0) {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append("..");
            sb.append(this.f17433a);
            sb.append(" step ");
            i = this.d;
        } else {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append(" downTo ");
            sb.append(this.f17433a);
            sb.append(" step ");
            i = -this.d;
        }
        sb.append(i);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lkotlin/ranges/IntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/IntProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class b {
        private b() {
        }

        public final uim d(int i, int i2, int i3) {
            return new uim(i, i2, i3);
        }

        public /* synthetic */ b(uib uibVar) {
            this();
        }
    }
}
