package com.huawei.openalliance.ad;

/* loaded from: classes5.dex */
public abstract class mv {
    /* JADX WARN: Multi-variable type inference failed */
    static <T> T a(String str, Class<T> cls) {
        if (cls == null || cls == String.class) {
            return str;
        }
        if (!cls.isPrimitive()) {
            return (T) com.huawei.openalliance.ad.utils.be.b(str, cls, new Class[0]);
        }
        String str2 = "Response type: " + cls + " not supported!";
        ho.c("RemoteCallUtil", str2);
        throw new IllegalArgumentException(str2);
    }
}
