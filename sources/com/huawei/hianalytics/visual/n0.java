package com.huawei.hianalytics.visual;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.page.IPageCustomTrace;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class n0 {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<String> f3940a = new HashSet();
    public static final LruCache<String, Class<?>> b = new LruCache<>(64);

    public static long a(long j, long j2) {
        long j3 = j2 - j;
        if (j3 < 0 || j3 > 86400000) {
            return 0L;
        }
        return j3;
    }

    public static boolean a(Object obj, String... strArr) {
        boolean z = false;
        if (strArr.length == 0) {
            return false;
        }
        for (String str : strArr) {
            try {
                LruCache<String, Class<?>> lruCache = b;
                Class<?> cls = lruCache.get(str);
                if (cls == null && !f3940a.contains(str)) {
                    cls = Class.forName(str);
                    lruCache.put(str, cls);
                }
                if (cls != null) {
                    z = cls.isInstance(obj);
                }
            } catch (Throwable unused) {
                f3940a.add(str);
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    public static Class<?> a(String[] strArr) {
        if (strArr.length == 0) {
            return null;
        }
        Class<?> cls = null;
        for (String str : strArr) {
            try {
                LruCache<String, Class<?>> lruCache = b;
                Class<?> cls2 = lruCache.get(str);
                if (cls2 == null && !f3940a.contains(str)) {
                    cls2 = Class.forName(str);
                    lruCache.put(str, cls2);
                }
                cls = cls2;
            } catch (Throwable unused) {
                f3940a.add(str);
                cls = null;
            }
            if (cls != null) {
                break;
            }
        }
        return cls;
    }

    public static Class<?> a(String str) {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            LruCache<String, Class<?>> lruCache = b;
            cls = lruCache.get(str);
            if (cls != null) {
                return cls;
            }
            try {
                if (f3940a.contains(str)) {
                    return cls;
                }
                cls2 = Class.forName(str);
                lruCache.put(str, cls2);
                return cls2;
            } catch (ClassNotFoundException unused) {
                f3940a.add(str);
                return cls;
            } catch (Throwable unused2) {
                return cls;
            }
        } catch (ClassNotFoundException unused3) {
            cls = cls2;
        } catch (Throwable unused4) {
            return cls2;
        }
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            if (method == null) {
                return null;
            }
            return (T) method.invoke(obj, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.get(next));
            }
        } catch (Exception e) {
            HiLog.w("HAUtils", "fail to merge two JSONObject, ex: " + e.getMessage());
        }
    }

    public static void a(Object obj, JSONObject jSONObject) {
        if (obj instanceof IPageCustomTrace) {
            try {
                JSONObject customProperties = ((IPageCustomTrace) obj).getCustomProperties();
                if (customProperties == null) {
                    return;
                }
                jSONObject.put("$custom_property", customProperties);
            } catch (Exception e) {
                HiLog.w("HAUtils", "fail to merge custom properties on page exit, ex: " + e.getMessage());
            }
        }
    }

    public static Class<?> a() {
        Class<?> cls;
        Class<?> cls2;
        try {
            cls = Class.forName("android.support.v7.app.AlertDialog");
        } catch (Exception unused) {
            HiLog.w("HAUtils", "can not find v7 AlertDialog class");
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.appcompat.app.AlertDialog");
        } catch (Exception unused2) {
            HiLog.w("HAUtils", "can not find appcompat AlertDialog class");
            cls2 = null;
        }
        if (cls == null && cls2 == null) {
            return null;
        }
        return cls != null ? cls : cls2;
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getApplicationInfo().processName;
        } catch (Exception e) {
            HiLog.w("HAUtils", "fail to get package name, ex: " + e.getMessage());
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T a(java.lang.Class<?> r4, java.lang.Object r5, java.lang.String... r6) {
        /*
            r0 = 0
            int r1 = r6.length     // Catch: java.lang.Exception -> L20
            if (r1 != 0) goto L5
            goto L20
        L5:
            int r1 = r6.length     // Catch: java.lang.Exception -> L20
            r2 = 0
            r3 = r0
        L8:
            if (r2 >= r1) goto L18
            r3 = r6[r2]     // Catch: java.lang.Exception -> L20
            java.lang.reflect.Field r3 = r4.getDeclaredField(r3)     // Catch: java.lang.NoSuchFieldException -> L11 java.lang.Exception -> L20
            goto L12
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L15
            goto L18
        L15:
            int r2 = r2 + 1
            goto L8
        L18:
            if (r3 != 0) goto L1b
            goto L20
        L1b:
            r4 = 1
            r3.setAccessible(r4)     // Catch: java.lang.Exception -> L20
            goto L21
        L20:
            r3 = r0
        L21:
            if (r3 != 0) goto L24
            return r0
        L24:
            java.lang.Object r4 = r3.get(r5)     // Catch: java.lang.Throwable -> L29
            return r4
        L29:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.visual.n0.a(java.lang.Class, java.lang.Object, java.lang.String[]):java.lang.Object");
    }

    public static boolean a(Object obj, String str) {
        Class<?> cls = obj.getClass();
        String a2 = v0.a().a(cls);
        while (a2 != null) {
            if (a2.equals(str)) {
                return true;
            }
            if (cls == Object.class || (cls = cls.getSuperclass()) == null) {
                return false;
            }
            a2 = v0.a().a(cls);
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (TextUtils.equals(str, str2)) {
            return true;
        }
        return str.contains("_") && TextUtils.equals(str.substring(0, str.indexOf("_")), str2);
    }
}
