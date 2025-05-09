package com.huawei.profile.utils;

import com.huawei.profile.utils.logger.DsLog;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class ClassUtil {
    private static final String TAG = "ClassUtil";

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) {
        return obj;
    }

    private ClassUtil() {
    }

    public static <T> T cast(Object obj, Class<T> cls) {
        if (cls == null) {
            DsLog.et(TAG, "result object is null", new Object[0]);
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            DsLog.et(TAG, "failed to cast class " + cls.getName(), new Object[0]);
            return null;
        }
    }

    public static <T> List<T> castList(Object obj, Class<T> cls) {
        try {
            List<T> list = (List) List.class.cast(obj);
            return list == null ? Collections.emptyList() : list;
        } catch (ClassCastException unused) {
            DsLog.et(TAG, "failed to cast to list", new Object[0]);
            return Collections.emptyList();
        }
    }

    public static <K, V> Map<K, V> castMap(Object obj, Class<K> cls, Class<V> cls2) {
        try {
            Map<K, V> map = (Map) Map.class.cast(obj);
            return map == null ? Collections.emptyMap() : map;
        } catch (ClassCastException unused) {
            DsLog.et(TAG, "failed to cast map", new Object[0]);
            return Collections.emptyMap();
        }
    }
}
