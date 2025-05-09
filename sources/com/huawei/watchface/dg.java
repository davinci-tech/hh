package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class dg {
    public static boolean a(Map<?, ?> map) {
        return map == null;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean a(Map<?, ?> map, Object obj) {
        if (isEmpty(map)) {
            return false;
        }
        return map.containsKey(obj);
    }

    public static <T> T getMapValue(Map<?, ?> map, Object obj) {
        if (isEmpty(map)) {
            return null;
        }
        try {
            return (T) map.get(obj);
        } catch (Exception e) {
            HwLog.e("MapUtils", "getMapValue Exception=" + HwLog.printException(e));
            return null;
        }
    }

    public static String a(Map<String, String> map, String str) {
        return isEmpty(map) ? "" : map.get(str);
    }

    public static String a(Map<String, String> map, String str, String str2) {
        return (!isEmpty(map) && map.containsKey(str)) ? map.get(str) : str2;
    }

    public static String b(Map<String, List<String>> map, String str, String str2) {
        return (!isEmpty(map) && map.containsKey(str)) ? (String) ArrayUtils.a(map.get(str), 0) : str2;
    }

    public static void c(Map<String, String> map, String str, String str2) {
        if (a(map) || TextUtils.isEmpty(str) || a((Map<?, ?>) map, (Object) str)) {
            return;
        }
        map.put(str, str2);
    }

    public static void b(Map<String, String> map, String str) {
        if (!isEmpty(map) && a((Map<?, ?>) map, (Object) str)) {
            map.remove(str);
        }
    }

    public static <K, T> void a(Map<K, T> map, K k, T t) {
        if (a(map)) {
            return;
        }
        map.put(k, t);
    }

    public static void b(Map<?, ?> map) {
        if (isEmpty(map)) {
            return;
        }
        map.clear();
    }
}
