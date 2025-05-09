package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.ho;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class bk {

    /* renamed from: a, reason: collision with root package name */
    private static Class<?> f7640a;
    private static Method b;
    private static Method c;
    private static Method d;
    private static Method e;
    private static Object f;

    private static String a(Context context, Method method) {
        Object obj = f;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e2) {
            ho.d("MIdentifierManager", "invoke exception, %s", e2.getClass().getSimpleName());
            return null;
        }
    }

    public static String a(Context context) {
        return a(context, c);
    }

    static {
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            f7640a = cls;
            f = cls.newInstance();
            d = f7640a.getMethod("getUDID", Context.class);
            c = f7640a.getMethod("getOAID", Context.class);
            e = f7640a.getMethod("getVAID", Context.class);
            b = f7640a.getMethod("getAAID", Context.class);
        } catch (Exception e2) {
            ho.d("MIdentifierManager", "reflect exception, %s", e2.getClass().getSimpleName());
        }
    }
}
