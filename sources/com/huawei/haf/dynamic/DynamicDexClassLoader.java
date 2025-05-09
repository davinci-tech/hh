package com.huawei.haf.dynamic;

import android.text.TextUtils;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/* loaded from: classes.dex */
final class DynamicDexClassLoader extends BaseDexClassLoader {

    /* renamed from: a, reason: collision with root package name */
    private final List<String> f2108a;
    private final String c;

    DynamicDexClassLoader(String str, List<String> list, File file, String str2, List<String> list2) {
        super(list == null ? "" : TextUtils.join(File.pathSeparator, list), file, str2, DynamicDexClassLoader.class.getClassLoader());
        this.c = str;
        this.f2108a = list2 == null ? Collections.EMPTY_LIST : list2;
    }

    String e() {
        return this.c;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> a2;
        try {
            return super.findClass(str);
        } catch (ClassNotFoundException e) {
            if (this.f2108a.isEmpty() || (a2 = DynamicApplicationLoaders.a(str, this.f2108a)) == null) {
                throw e;
            }
            return a2;
        }
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        String findLibrary = super.findLibrary(str);
        if (findLibrary == null && !this.f2108a.isEmpty()) {
            findLibrary = DynamicApplicationLoaders.c(str, this.f2108a);
        }
        if (findLibrary != null) {
            return findLibrary;
        }
        ClassLoader parent = getParent();
        return parent instanceof BaseDexClassLoader ? ((BaseDexClassLoader) parent).findLibrary(str) : findLibrary;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) {
        Enumeration<URL> findResources = super.findResources(str);
        return (findResources != null || this.f2108a.isEmpty()) ? findResources : DynamicApplicationLoaders.d(str, this.f2108a);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected URL findResource(String str) {
        URL findResource = super.findResource(str);
        return (findResource != null || this.f2108a.isEmpty()) ? findResource : DynamicApplicationLoaders.b(str, this.f2108a);
    }

    URL c(String str) {
        return super.findResource(str);
    }

    Enumeration<URL> a(String str) {
        return super.findResources(str);
    }

    String e(String str) {
        return super.findLibrary(str);
    }

    Class<?> d(String str) throws ClassNotFoundException {
        Class<?> findLoadedClass = findLoadedClass(str);
        return findLoadedClass != null ? findLoadedClass : super.findClass(str);
    }
}
