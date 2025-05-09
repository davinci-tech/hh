package com.huawei.hwcloudjs.core.extkit;

import com.huawei.hwcloudjs.f.d;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6199a = "AuthResponseBean";
    private static Map<String, Class<? extends IExtKit>> b = new HashMap();

    public static void b(String str, Class<? extends IExtKit> cls) {
        b.put(str, cls);
    }

    public static <U, R extends Class> U a(String str, R r) {
        String str2;
        try {
            U u = (U) b.get(str).newInstance();
            if (r.isInstance(u)) {
                return u;
            }
            return null;
        } catch (IllegalAccessException unused) {
            str2 = "IllegalAccessException";
            d.b(f6199a, str2, true);
            return null;
        } catch (InstantiationException unused2) {
            str2 = "InstantiationException";
            d.b(f6199a, str2, true);
            return null;
        }
    }
}
