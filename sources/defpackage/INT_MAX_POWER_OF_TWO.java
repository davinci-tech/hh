package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Collections;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a4\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007H\u0001\u001aQ\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001a\u00020\u00012#\u0010\n\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0081\bø\u0001\u0000\u001aI\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052#\u0010\n\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0081\bø\u0001\u0000\u001a \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0001\u001a(\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0001\u001a2\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013\u001aa\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u00040\u00172*\u0010\u0018\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00130\u0019\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013H\u0007¢\u0006\u0002\u0010\u001a\u001aY\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u001b\"\u0004\b\u0001\u0010\u00052*\u0010\u0018\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00130\u0019\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013¢\u0006\u0002\u0010\u001c\u001aC\u0010\u001d\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u001e2\u0006\u0010\u001f\u001a\u0002H\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00050!H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u0019\u0010#\u001a\u00020$*\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%0\u0003H\u0087\b\u001a2\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0000\u001a1\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0081\b\u001a:\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u001b\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\u001a@\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0000\u0012\u0002H\u00040\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"INT_MAX_POWER_OF_TWO", "", "build", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "builder", "", "buildMapInternal", "capacity", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "createMapBuilder", "mapCapacity", "expectedSize", "mapOf", "pair", "Lkotlin/Pair;", "sortedMapOf", "Ljava/util/SortedMap;", "comparator", "Ljava/util/Comparator;", "pairs", "", "(Ljava/util/Comparator;[Lkotlin/Pair;)Ljava/util/SortedMap;", "", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", MedalConstants.EVENT_KEY, "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "toSingletonMap", "toSingletonMapOrSelf", "toSortedMap", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
/* renamed from: uft, reason: from Kotlin metadata */
/* loaded from: classes7.dex */
public class INT_MAX_POWER_OF_TWO extends getOrImplicitDefault {
    public static final int b(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final <K, V> Map<K, V> c(ueo<? extends K, ? extends V> ueoVar) {
        uhy.e((Object) ueoVar, "");
        Map<K, V> singletonMap = Collections.singletonMap(ueoVar.e(), ueoVar.c());
        uhy.a(singletonMap, "");
        return singletonMap;
    }

    public static final <K, V> Map<K, V> d(int i) {
        return new ugd(i);
    }

    public static final <K, V> Map<K, V> c(Map<K, V> map) {
        uhy.e((Object) map, "");
        return ((ugd) map).e();
    }

    public static final <K, V> Map<K, V> a(Map<? extends K, ? extends V> map) {
        uhy.e((Object) map, "");
        Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
        Map<K, V> singletonMap = Collections.singletonMap(next.getKey(), next.getValue());
        uhy.a(singletonMap, "");
        return singletonMap;
    }
}
