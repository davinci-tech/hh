package com.huawei.hms.ads.dynamicloader;

import com.huawei.hms.ads.uiengineloader.af;
import dalvik.system.PathClassLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/* loaded from: classes4.dex */
public class d extends PathClassLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4309a = "d";

    @Override // java.lang.ClassLoader
    protected final Class loadClass(String str, boolean z) throws ClassNotFoundException {
        if (str.startsWith("java.") || str.startsWith("com.huawei.hms.ads.uiengine.common")) {
            try {
                return super.loadClass(str, z);
            } catch (ClassNotFoundException unused) {
                af.c(f4309a, "Load class failed.");
            }
        }
        try {
            return findClass(str);
        } catch (ClassNotFoundException unused2) {
            return super.loadClass(str, z);
        }
    }

    @Override // java.lang.ClassLoader
    public final Enumeration getResources(String str) {
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        ClassLoader systemClassLoader = getSystemClassLoader();
        if (systemClassLoader != null) {
            try {
                a(arrayList, systemClassLoader.getResources(str));
            } catch (IOException unused) {
                str2 = f4309a;
                str3 = "Add Enumeration failed.";
            }
            a(arrayList, findResources(str));
            return Collections.enumeration(arrayList);
        }
        str2 = f4309a;
        str3 = "Failed to get SystemClassLoader";
        af.c(str2, str3);
        a(arrayList, findResources(str));
        return Collections.enumeration(arrayList);
    }

    @Override // java.lang.ClassLoader
    public final URL getResource(String str) {
        URL resource;
        ClassLoader systemClassLoader = getSystemClassLoader();
        if (systemClassLoader == null) {
            af.c("DelegateLastPathClssLdr", "Failed to get SystemClassLoader");
            resource = null;
        } else {
            resource = systemClassLoader.getResource(str);
        }
        return resource == null ? findResource(str) : resource;
    }

    private static void a(List list, Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            list.add(enumeration.nextElement());
        }
    }

    public d(String str, String str2, ClassLoader classLoader) {
        super(str, str2, classLoader);
    }

    public d(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }
}
