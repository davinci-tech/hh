package com.huawei.openalliance.ad;

import android.content.Context;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ks<P> {
    String a();

    String a(P p, ko koVar);

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final Map<Type, ks> f7152a = new HashMap();
        private static kt b;
        private static kq c;
        private static kr d;

        private static void a(Context context) {
            synchronized (a.class) {
                if (b == null) {
                    kt ktVar = new kt(context);
                    b = ktVar;
                    f7152a.put(String.class, ktVar);
                }
                if (c == null) {
                    c = new kq(context);
                    Map<Type, ks> map = f7152a;
                    map.put(Integer.TYPE, c);
                    map.put(Integer.class, c);
                    map.put(Float.TYPE, c);
                    map.put(Float.class, c);
                    map.put(Long.TYPE, c);
                    map.put(Long.class, c);
                    map.put(Double.TYPE, c);
                    map.put(Double.class, c);
                    map.put(Short.TYPE, c);
                    map.put(Short.class, c);
                    map.put(Byte.TYPE, c);
                    map.put(Byte.class, c);
                    map.put(Character.TYPE, c);
                    map.put(Character.class, c);
                    map.put(Boolean.TYPE, c);
                    map.put(Boolean.class, c);
                }
                if (d == null) {
                    d = new kr(context);
                }
            }
        }

        public static ks a(Context context, Class cls) {
            a(context);
            ks ksVar = f7152a.get(cls);
            return ksVar == null ? d : ksVar;
        }
    }
}
