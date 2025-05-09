package com.huawei.haf.dynamic;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/* loaded from: classes.dex */
final class DynamicDelegateClassloader extends PathClassLoader {
    private final ClassLoader b;
    private ClassNotFoundInterceptor c;

    DynamicDelegateClassloader(ClassLoader classLoader) {
        super("", classLoader);
        this.b = classLoader;
    }

    void d(ClassNotFoundInterceptor classNotFoundInterceptor) {
        this.c = classNotFoundInterceptor;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> findClass;
        try {
            return this.b.loadClass(str);
        } catch (ClassNotFoundException e) {
            ClassNotFoundInterceptor classNotFoundInterceptor = this.c;
            if (classNotFoundInterceptor == null || (findClass = classNotFoundInterceptor.findClass(str)) == null) {
                throw e;
            }
            return findClass;
        }
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        return this.b.getResource(str);
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String str) throws IOException {
        return this.b.getResources(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected URL findResource(String str) {
        URL findResource = super.findResource(str);
        return findResource == null ? DynamicApplicationLoaders.b(str, null) : findResource;
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    protected Enumeration<URL> findResources(String str) {
        Enumeration<URL> findResources = super.findResources(str);
        return findResources == null ? DynamicApplicationLoaders.d(str, (List<String>) null) : findResources;
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str) throws ClassNotFoundException {
        return findClass(str);
    }

    @Override // dalvik.system.BaseDexClassLoader, java.lang.ClassLoader
    public String findLibrary(String str) {
        ClassLoader classLoader = this.b;
        String findLibrary = classLoader instanceof BaseDexClassLoader ? ((BaseDexClassLoader) classLoader).findLibrary(str) : null;
        return findLibrary == null ? DynamicApplicationLoaders.c(str, null) : findLibrary;
    }
}
