package com.huawei.agconnect.datastore.core;

import java.util.Map;

/* loaded from: classes2.dex */
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    public void remove(String str, String str2) {
        AndroidSharedPrefUtil.remove(str, str2);
    }

    public void put(String str, String str2, Class<?> cls, Object obj, Class<?> cls2) {
        synchronized (this) {
            AndroidSharedPrefUtil.put(str, str2, cls, obj, cls2);
        }
    }

    public void init(Object obj) {
        AndroidSharedPrefUtil.init(obj);
    }

    public Map<String, ?> getAll(String str) {
        return AndroidSharedPrefUtil.getAll(str);
    }

    public Object get(String str, String str2, Class<?> cls, Object obj, Class<?> cls2) {
        Object obj2;
        synchronized (this) {
            obj2 = AndroidSharedPrefUtil.get(str, str2, cls, obj, cls2);
        }
        return obj2;
    }

    private static Class getClass(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        Class cls;
        if (getClass("ohos.app.Context") != null && (cls = getClass("com.huawei.agconnect.datastore.core.HarmonyPlatform")) != null) {
            try {
                return (Platform) cls.newInstance();
            } catch (IllegalAccessException unused) {
                throw new RuntimeException("cannot create HarmonyPlatform instance: IllegalAccessException");
            } catch (InstantiationException unused2) {
                throw new RuntimeException("cannot create HarmonyPlatform instance: InstantiationException");
            }
        }
        return new Platform();
    }
}
