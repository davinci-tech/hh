package com.huawei.hms.feature.dynamic.e;

import android.util.Log;
import dalvik.system.DexClassLoader;

/* loaded from: classes9.dex */
public final class b extends DexClassLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4516a = "b";

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!str.startsWith("java.") && !str.startsWith("android.")) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
                Log.w(f4516a, "Cannot find The class:" + str);
            }
        }
        return super.loadClass(str, z);
    }

    public b(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, str3, classLoader);
    }
}
