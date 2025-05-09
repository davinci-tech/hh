package kotlin.time;

import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.uhy;
import defpackage.ukf;
import kotlin.Metadata;
import kotlin.time.TimeMark;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\bH¦\u0002J\b\u0010\t\u001a\u00020\u0004H&J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0000H¦\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bH\u0096\u0002ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bH¦\u0002ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0010\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlin/time/ComparableTimeMark;", "Lkotlin/time/TimeMark;", "", "compareTo", "", "other", "equals", "", "", WatchFaceConstant.HASHCODE, "minus", "Lkotlin/time/Duration;", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "duration", "minus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ComparableTimeMark extends TimeMark, Comparable<ComparableTimeMark> {
    int compareTo(ComparableTimeMark other);

    boolean equals(Object other);

    int hashCode();

    @Override // kotlin.time.TimeMark
    /* renamed from: minus-LRDsOJo */
    ComparableTimeMark mo973minusLRDsOJo(long duration);

    /* renamed from: minus-UwyO8pc */
    long mo974minusUwyO8pc(ComparableTimeMark other);

    @Override // kotlin.time.TimeMark
    /* renamed from: plus-LRDsOJo */
    ComparableTimeMark mo975plusLRDsOJo(long duration);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class c {
        public static boolean a(ComparableTimeMark comparableTimeMark) {
            return TimeMark.e.e(comparableTimeMark);
        }

        public static boolean e(ComparableTimeMark comparableTimeMark) {
            return TimeMark.e.d(comparableTimeMark);
        }

        public static ComparableTimeMark b(ComparableTimeMark comparableTimeMark, long j) {
            return comparableTimeMark.mo975plusLRDsOJo(ukf.t(j));
        }

        public static int b(ComparableTimeMark comparableTimeMark, ComparableTimeMark comparableTimeMark2) {
            uhy.e((Object) comparableTimeMark2, "");
            return ukf.e(comparableTimeMark.mo974minusUwyO8pc(comparableTimeMark2), ukf.d.b());
        }
    }
}
