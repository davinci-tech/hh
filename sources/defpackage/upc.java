package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hms.network.embedded.g4;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0016\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000eH\u0086\b¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0016\u001a\u00028\u0000H\u0086\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", ExifInterface.LONGITUDE_EAST, "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", "", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", WatchFaceConstant.HASHCODE, "", "hashCode-impl", "(Ljava/lang/Object;)I", "plus", FunctionSetBeanReader.BI_ELEMENT, "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class upc<E> {
    private final Object e;

    public static <E> Object d(Object obj) {
        return obj;
    }

    public static /* synthetic */ Object b(Object obj, int i, uib uibVar) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return d(obj);
    }

    public static final Object a(Object obj, E e) {
        if (ASSERTIONS_ENABLED.a() && !(!(e instanceof List))) {
            throw new AssertionError();
        }
        if (obj == null) {
            return d(e);
        }
        if (obj instanceof ArrayList) {
            if (obj != null) {
                ((ArrayList) obj).add(e);
                return d(obj);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>{ kotlin.collections.TypeAliasesKt.ArrayList<E of kotlinx.coroutines.internal.InlineList> }");
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(e);
        return d(arrayList);
    }

    /* renamed from: c, reason: from getter */
    public final /* synthetic */ Object getE() {
        return this.e;
    }

    public String toString() {
        return b(this.e);
    }

    public int hashCode() {
        return a(this.e);
    }

    public boolean equals(Object obj) {
        return e(this.e, obj);
    }

    public static String b(Object obj) {
        return "InlineList(holder=" + obj + g4.l;
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static boolean e(Object obj, Object obj2) {
        return (obj2 instanceof upc) && uhy.e(obj, ((upc) obj2).getE());
    }
}
