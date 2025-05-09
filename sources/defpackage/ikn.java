package defpackage;

import android.util.LruCache;
import com.huawei.hihealth.HiAppInfo;

/* loaded from: classes7.dex */
public class ikn {
    private static LruCache<String, HiAppInfo> d = new LruCache<>(500);
    private static LruCache<String, Integer> e = new LruCache<>(20);

    public static HiAppInfo c(String str) {
        if (str == null) {
            return null;
        }
        return d.get(str);
    }

    public static void b(String str, HiAppInfo hiAppInfo) {
        if (str == null || hiAppInfo == null) {
            return;
        }
        d.put(str, hiAppInfo);
    }

    public static void e() {
        d.evictAll();
    }

    public static LruCache<String, Integer> bBq_() {
        return e;
    }
}
