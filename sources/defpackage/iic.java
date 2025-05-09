package defpackage;

import android.util.SparseArray;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class iic {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Integer> f13385a;
    private static final SparseArray<Integer> b;
    private static final SparseArray<Integer> c;
    private static final Map<String, Integer> d;
    private static final Map<String, Integer> e;
    private static final SparseArray<Integer> f;
    private static final SparseArray<Integer> g;
    private static final Map<String, Integer> i;
    private static final Map<String, Integer> j;

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        HashMap hashMap2 = new HashMap();
        j = hashMap2;
        HashMap hashMap3 = new HashMap();
        f13385a = hashMap3;
        HashMap hashMap4 = new HashMap();
        e = hashMap4;
        HashMap hashMap5 = new HashMap();
        i = hashMap5;
        SparseArray<Integer> sparseArray = new SparseArray<>();
        g = sparseArray;
        sparseArray.put(20006, 10);
        sparseArray.put(20002, 20);
        sparseArray.put(20008, 30);
        sparseArray.put(20003, 40);
        sparseArray.put(20005, 50);
        sparseArray.put(20004, 60);
        sparseArray.put(20007, 70);
        SparseArray<Integer> sparseArray2 = new SparseArray<>();
        f = sparseArray2;
        sparseArray2.put(22003, 10);
        sparseArray2.put(22002, 20);
        sparseArray2.put(22001, 30);
        SparseArray<Integer> sparseArray3 = new SparseArray<>();
        c = sparseArray3;
        sparseArray3.put(22106, 10);
        sparseArray3.put(22107, 20);
        sparseArray3.put(22104, 30);
        sparseArray3.put(22105, 40);
        sparseArray3.put(22101, 50);
        sparseArray3.put(22102, 60);
        sparseArray3.put(22103, 70);
        SparseArray<Integer> sparseArray4 = new SparseArray<>();
        b = sparseArray4;
        sparseArray4.put(32, 10);
        sparseArray4.put(30, 10);
        sparseArray4.put(23, 30);
        sparseArray4.put(1, 100);
        hashMap.put("08F", 10);
        hashMap.put("082", 20);
        hashMap.put("06E", 30);
        hashMap.put("06D", 40);
        hashMap.put("025", 50);
        hashMap.put("088", 60);
        hashMap.put("02B", 70);
        hashMap.put("14C", 80);
        hashMap2.put("00E", 10);
        hashMap2.put("A12", 15);
        hashMap2.put("06E", 20);
        hashMap2.put("06D", 30);
        hashMap2.put("08F", 40);
        hashMap3.put("08F", 10);
        hashMap3.put("06E", 15);
        hashMap3.put("06D", 20);
        hashMap4.put("08F", 10);
        hashMap4.put("06E", 15);
        hashMap4.put("06D", 20);
        hashMap5.put("06E", 10);
        hashMap5.put("06D", 20);
        hashMap5.put("A12", 30);
    }

    public static Map<String, Integer> e() {
        return Collections.unmodifiableMap(d);
    }

    public static Map<String, Integer> c() {
        return Collections.unmodifiableMap(j);
    }

    public static Map<String, Integer> b() {
        return Collections.unmodifiableMap(f13385a);
    }

    public static Map<String, Integer> a() {
        return Collections.unmodifiableMap(e);
    }

    public static Map<String, Integer> d() {
        return Collections.unmodifiableMap(i);
    }

    public static Integer b(int i2) {
        Integer num = b.get(i2);
        if (num == null) {
            return 50;
        }
        return num;
    }

    public static int d(int i2, int i3) {
        SparseArray<Integer> sparseArray = g;
        Integer num = sparseArray.get(i2);
        Integer num2 = sparseArray.get(i3);
        if (num == null || num2 == null) {
            return 0;
        }
        return num.compareTo(num2);
    }

    public static int e(int i2, int i3) {
        SparseArray<Integer> sparseArray = f;
        Integer num = sparseArray.get(i2);
        Integer num2 = sparseArray.get(i3);
        if (num == null || num2 == null) {
            return 0;
        }
        return num.compareTo(num2);
    }

    public static int c(int i2, int i3) {
        SparseArray<Integer> sparseArray = c;
        Integer num = sparseArray.get(i2);
        Integer num2 = sparseArray.get(i3);
        if (num == null || num2 == null) {
            return 0;
        }
        return num.compareTo(num2);
    }
}
