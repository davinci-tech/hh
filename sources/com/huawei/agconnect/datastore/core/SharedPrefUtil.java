package com.huawei.agconnect.datastore.core;

import android.content.Context;
import java.util.Map;

/* loaded from: classes2.dex */
public class SharedPrefUtil {
    private static final String TAG = "SharedPrefUtil";
    private static SharedPrefUtil instance = new SharedPrefUtil();

    public void remove(String str, String str2) {
        Platform.get().remove(str, str2);
    }

    public void put(String str, String str2, Class<?> cls, Object obj, Class<?> cls2) {
        synchronized (this) {
            Platform.get().put(str, str2, cls, obj, cls2);
        }
    }

    public Map<String, ?> getAll(String str) {
        return Platform.get().getAll(str);
    }

    public Object get(String str, String str2, Class<?> cls, Object obj, Class<?> cls2) {
        Object obj2;
        synchronized (this) {
            obj2 = Platform.get().get(str, str2, cls, obj, cls2);
        }
        return obj2;
    }

    public static void init(Object obj) {
        synchronized (SharedPrefUtil.class) {
            Platform.get().init(obj);
        }
    }

    public static void init(Context context) {
        synchronized (SharedPrefUtil.class) {
            Platform.get().init(context);
        }
    }

    public static SharedPrefUtil getInstance() {
        return instance;
    }

    private SharedPrefUtil() {
    }
}
