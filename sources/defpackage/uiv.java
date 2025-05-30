package defpackage;

import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.OpenEndRange;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u0019B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\b\u001a\u00020\u00038VX\u0097\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lkotlin/ranges/IntRange;", "Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/ClosedRange;", "", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "(II)V", "endExclusive", "getEndExclusive$annotations", "()V", "getEndExclusive", "()Ljava/lang/Integer;", "getEndInclusive", "getStart", "contains", "", "value", "equals", "other", "", WatchFaceConstant.HASHCODE, "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class uiv extends uim implements ClosedRange<Integer>, OpenEndRange<Integer> {
    public static final c b = new c(null);
    private static final uiv d = new uiv(1, 0);

    public uiv(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* synthetic */ boolean contains(Integer num) {
        return b(num.intValue());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public Integer getStart() {
        return Integer.valueOf(getB());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public Integer getEndInclusive() {
        return Integer.valueOf(getF17433a());
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public Integer getEndExclusive() {
        if (getF17433a() == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
        }
        return Integer.valueOf(getF17433a() + 1);
    }

    public boolean b(int i) {
        return getB() <= i && i <= getF17433a();
    }

    @Override // defpackage.uim, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return getB() > getF17433a();
    }

    @Override // defpackage.uim
    public boolean equals(Object other) {
        if (other instanceof uiv) {
            if (!isEmpty() || !((uiv) other).isEmpty()) {
                uiv uivVar = (uiv) other;
                if (getB() != uivVar.getB() || getF17433a() != uivVar.getF17433a()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // defpackage.uim
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (getB() * 31) + getF17433a();
    }

    @Override // defpackage.uim
    public String toString() {
        return getB() + ".." + getF17433a();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/IntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/IntRange;", "getEMPTY", "()Lkotlin/ranges/IntRange;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class c {
        private c() {
        }

        public final uiv b() {
            return uiv.d;
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }
    }
}
