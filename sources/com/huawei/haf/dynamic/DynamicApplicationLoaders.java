package com.huawei.haf.dynamic;

import android.content.Context;
import health.compact.a.ReflectionUtils;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class DynamicApplicationLoaders {
    private static final Set<DynamicDexClassLoader> e = Collections.newSetFromMap(new ConcurrentHashMap());

    private DynamicApplicationLoaders() {
    }

    public static ClassLoader b(ClassLoader classLoader) {
        return new DynamicDelegateClassloader(classLoader);
    }

    public static void a(ClassLoader classLoader, Context context) throws DynamicLoaderException {
        ClassLoader b = b(classLoader);
        try {
            Object obj = ReflectionUtils.d(context, "mPackageInfo").get(context);
            if (obj != null) {
                ReflectionUtils.d(obj, "mClassLoader").set(obj, b);
                Thread.currentThread().setContextClassLoader(b);
            }
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            throw new DynamicLoaderException("reflect PackageInfo Classloader fail.", e2);
        }
    }

    public static boolean d(ClassLoader classLoader) {
        return classLoader instanceof DynamicDelegateClassloader;
    }

    public static void d(ClassLoader classLoader, ClassNotFoundInterceptor classNotFoundInterceptor) {
        if (d(classLoader)) {
            ((DynamicDelegateClassloader) classLoader).d(classNotFoundInterceptor);
        }
    }

    public static ClassLoader d(String str, List<String> list, File file, File file2, List<String> list2) {
        return new DynamicDexClassLoader(str, list, file, file2 == null ? null : file2.getAbsolutePath(), list2);
    }

    public static void a(ClassLoader classLoader) {
        if (classLoader instanceof DynamicDexClassLoader) {
            e.add((DynamicDexClassLoader) classLoader);
        }
    }

    public static void c(ClassLoader classLoader) {
        if (classLoader instanceof DynamicDexClassLoader) {
            e.remove(classLoader);
        }
    }

    public static ClassLoader e(String str) {
        for (DynamicDexClassLoader dynamicDexClassLoader : e) {
            if (str.equals(dynamicDexClassLoader.e())) {
                return dynamicDexClassLoader;
            }
        }
        return null;
    }

    static Class<?> a(String str, List<String> list) {
        for (DynamicDexClassLoader dynamicDexClassLoader : e) {
            if (list == null || list.contains(dynamicDexClassLoader.e())) {
                try {
                    return dynamicDexClassLoader.d(str);
                } catch (ClassNotFoundException unused) {
                    continue;
                }
            }
        }
        return null;
    }

    static URL b(String str, List<String> list) {
        URL url = null;
        for (DynamicDexClassLoader dynamicDexClassLoader : e) {
            if (list == null || list.contains(dynamicDexClassLoader.e())) {
                url = dynamicDexClassLoader.c(str);
                if (url != null) {
                    break;
                }
            }
        }
        return url;
    }

    static Enumeration<URL> d(String str, List<String> list) {
        Enumeration<URL> enumeration = null;
        for (DynamicDexClassLoader dynamicDexClassLoader : e) {
            if (list == null || list.contains(dynamicDexClassLoader.e())) {
                enumeration = dynamicDexClassLoader.a(str);
                if (enumeration != null) {
                    break;
                }
            }
        }
        return enumeration;
    }

    static String c(String str, List<String> list) {
        String str2 = null;
        for (DynamicDexClassLoader dynamicDexClassLoader : e) {
            if (list == null || list.contains(dynamicDexClassLoader.e())) {
                str2 = dynamicDexClassLoader.e(str);
                if (str2 != null) {
                    break;
                }
            }
        }
        return str2;
    }
}
