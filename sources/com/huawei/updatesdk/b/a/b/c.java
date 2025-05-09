package com.huawei.updatesdk.b.a.b;

import android.util.ArrayMap;
import android.util.ArraySet;
import java.io.File;
import java.security.PublicKey;

/* loaded from: classes7.dex */
class c {
    private static Object b(File file) {
        try {
            Class<?> cls = Class.forName("android.content.pm.PackageParser");
            return cls.getDeclaredMethod("parsePackage", File.class, Integer.TYPE).invoke(cls.newInstance(), file, 0);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.c("KeySetsUtils", "can not get PackageParser: " + th.getMessage());
            return null;
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        ArrayMap<String, ArraySet<PublicKey>> f10826a = null;
        ArraySet<String> b = null;

        a() {
        }
    }

    private static Object a(Object obj, String str) {
        try {
            return Class.forName("android.content.pm.PackageParser$Package").getDeclaredField(str).get(obj);
        } catch (Throwable th) {
            com.huawei.updatesdk.a.a.c.a.a.a.c("KeySetsUtils", "can not find class: " + th.getMessage());
            return null;
        }
    }

    static a a(File file) {
        a aVar = new a();
        Object b = b(file);
        if (b != null) {
            Object a2 = a(b, "mKeySetMapping");
            if (a2 instanceof ArrayMap) {
                aVar.f10826a = (ArrayMap) a2;
            }
            Object a3 = a(b, "mUpgradeKeySets");
            if (a3 instanceof ArraySet) {
                aVar.b = (ArraySet) a3;
            }
        }
        return aVar;
    }
}
