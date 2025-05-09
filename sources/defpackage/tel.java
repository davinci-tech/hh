package defpackage;

import android.util.SparseArray;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class tel {
    private static long b;
    private static long e;
    private static SparseArray<Long> d = new SparseArray<>();

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Long> f17263a = new HashMap();

    public static boolean e(int i, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (d.indexOfKey(i) < 0) {
            c(i, currentTimeMillis);
            return false;
        }
        if (currentTimeMillis - d.get(i).longValue() <= j) {
            return true;
        }
        c(i, currentTimeMillis);
        return false;
    }

    public static boolean e(int i) {
        return e(i, 500L);
    }

    private static void c(int i, long j) {
        if ((j - e > PreConnectManager.CONNECT_INTERNAL && j - b > 500) || d.size() > 50) {
            e = j;
            d.clear();
        }
        b = j;
        d.put(i, Long.valueOf(j));
    }
}
