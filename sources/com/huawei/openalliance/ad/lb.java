package com.huawei.openalliance.ad;

import java.io.InputStream;

/* loaded from: classes5.dex */
public interface lb<R> {
    long a();

    R a(int i, InputStream inputStream, long j, ko koVar);

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final lc f7155a = new lc();

        public static <RD> lb<RD> a(Class<RD> cls) {
            if (cls == Integer.TYPE || cls == Integer.class) {
                return new ky();
            }
            if (cls == Float.TYPE || cls == Float.class) {
                return new kw();
            }
            if (cls == Double.TYPE || cls == Double.class) {
                return new kv();
            }
            if (cls == Long.TYPE || cls == Long.class) {
                return new la();
            }
            if (cls == String.class) {
                return f7155a;
            }
            if (!cls.isPrimitive()) {
                return new kz(cls);
            }
            throw new IllegalArgumentException("Response type: " + cls + " not supported!");
        }
    }
}
