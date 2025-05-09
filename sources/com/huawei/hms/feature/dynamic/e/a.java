package com.huawei.hms.feature.dynamic.e;

import android.util.Log;
import dalvik.system.PathClassLoader;

/* loaded from: classes4.dex */
public final class a extends PathClassLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4515a = "a";

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!str.startsWith("java.") && !str.startsWith("android.")) {
            try {
                return findClass(str);
            } catch (ClassNotFoundException unused) {
                Log.w(f4515a, "Cannot find The class:" + str);
            }
        }
        return super.loadClass(str, z);
    }

    public a(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }
}
