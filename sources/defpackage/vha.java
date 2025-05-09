package defpackage;

import com.huawei.openalliance.ad.constant.OsType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.StaticLoggerBinder;

/* loaded from: classes7.dex */
public final class vha {
    static volatile int d;
    static final vhi b = new vhi();
    static final vhk e = new vhk();
    static boolean c = vhj.b("slf4j.detectLoggerNameMismatch");

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f17708a = {"1.6", "1.7"};
    private static String j = "org/slf4j/impl/StaticLoggerBinder.class";

    private vha() {
    }

    private static final void i() {
        e();
        if (d == 3) {
            h();
        }
    }

    private static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("org/slf4j/impl/StaticLoggerBinder") || str.contains("org.slf4j.impl.StaticLoggerBinder");
    }

    private static final void e() {
        Set<URL> set;
        try {
            try {
                try {
                    if (j()) {
                        set = null;
                    } else {
                        set = a();
                        a(set);
                    }
                    StaticLoggerBinder.getSingleton();
                    d = 3;
                    d(set);
                } catch (NoClassDefFoundError e2) {
                    if (b(e2.getMessage())) {
                        d = 4;
                        vhj.d("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
                        vhj.d("Defaulting to no-operation (NOP) logger implementation");
                        vhj.d("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
                    } else {
                        e(e2);
                        throw e2;
                    }
                }
            } catch (Exception e3) {
                e(e3);
                throw new IllegalStateException("Unexpected initialization failure", e3);
            } catch (NoSuchMethodError e4) {
                String message = e4.getMessage();
                if (message != null && message.contains("org.slf4j.impl.StaticLoggerBinder.getSingleton()")) {
                    d = 2;
                    vhj.d("slf4j-api 1.6.x (or later) is incompatible with this binding.");
                    vhj.d("Your binding is version 1.5.5 or earlier.");
                    vhj.d("Upgrade your binding to version 1.6.x.");
                }
                throw e4;
            }
        } finally {
            f();
        }
    }

    private static void f() {
        b();
        g();
        b.d();
    }

    private static void b() {
        vhi vhiVar = b;
        synchronized (vhiVar) {
            vhiVar.a();
            for (vhh vhhVar : vhiVar.c()) {
                vhhVar.e(d(vhhVar.getName()));
            }
        }
    }

    static void e(Throwable th) {
        d = 2;
        vhj.a("Failed to instantiate SLF4J LoggerFactory", th);
    }

    private static void g() {
        LinkedBlockingQueue<vhc> b2 = b.b();
        int size = b2.size();
        ArrayList<vhc> arrayList = new ArrayList(128);
        int i = 0;
        while (b2.drainTo(arrayList, 128) != 0) {
            for (vhc vhcVar : arrayList) {
                e(vhcVar);
                if (i == 0) {
                    b(vhcVar, size);
                }
                i++;
            }
            arrayList.clear();
        }
    }

    private static void b(vhc vhcVar, int i) {
        if (vhcVar.a().b()) {
            b(i);
        } else {
            if (vhcVar.a().d()) {
                return;
            }
            c();
        }
    }

    private static void e(vhc vhcVar) {
        if (vhcVar == null) {
            return;
        }
        vhh a2 = vhcVar.a();
        String name = a2.getName();
        if (a2.a()) {
            throw new IllegalStateException("Delegate logger cannot be null at this state.");
        }
        if (a2.d()) {
            return;
        }
        if (a2.b()) {
            a2.c(vhcVar);
        } else {
            vhj.d(name);
        }
    }

    private static void c() {
        vhj.d("The following set of substitute loggers may have been accessed");
        vhj.d("during the initialization phase. Logging calls during this");
        vhj.d("phase were not honored. However, subsequent logging calls to these");
        vhj.d("loggers will work as normally expected.");
        vhj.d("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static void b(int i) {
        vhj.d("A number (" + i + ") of logging calls during the initialization phase have been intercepted and are");
        vhj.d("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        vhj.d("See also http://www.slf4j.org/codes.html#replay");
    }

    private static final void h() {
        try {
            String str = StaticLoggerBinder.REQUESTED_API_VERSION;
            boolean z = false;
            for (String str2 : f17708a) {
                if (str.startsWith(str2)) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            vhj.d("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(f17708a).toString());
            vhj.d("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        } catch (NoSuchFieldError unused) {
        } catch (Throwable th) {
            vhj.a("Unexpected problem occured during version sanity check", th);
        }
    }

    static Set<URL> a() {
        Enumeration<URL> resources;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            ClassLoader classLoader = vha.class.getClassLoader();
            if (classLoader == null) {
                resources = ClassLoader.getSystemResources(j);
            } else {
                resources = classLoader.getResources(j);
            }
            while (resources.hasMoreElements()) {
                linkedHashSet.add(resources.nextElement());
            }
        } catch (IOException e2) {
            vhj.a("Error getting resources from path", e2);
        }
        return linkedHashSet;
    }

    private static boolean e(Set<URL> set) {
        return set.size() > 1;
    }

    private static void a(Set<URL> set) {
        if (e(set)) {
            vhj.d("Class path contains multiple SLF4J bindings.");
            Iterator<URL> it = set.iterator();
            while (it.hasNext()) {
                vhj.d("Found binding in [" + it.next() + "]");
            }
            vhj.d("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }

    private static boolean j() {
        String e2 = vhj.e("java.vendor.url");
        if (e2 == null) {
            return false;
        }
        return e2.toLowerCase().contains(OsType.ANDROID);
    }

    private static void d(Set<URL> set) {
        if (set == null || !e(set)) {
            return;
        }
        vhj.d("Actual binding is of type [" + StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr() + "]");
    }

    public static Logger d(String str) {
        return d().getLogger(str);
    }

    public static Logger a(Class<?> cls) {
        Class<?> a2;
        Logger d2 = d(cls.getName());
        if (c && (a2 = vhj.a()) != null && b(cls, a2)) {
            vhj.d(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", d2.getName(), a2.getName()));
            vhj.d("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        }
        return d2;
    }

    private static boolean b(Class<?> cls, Class<?> cls2) {
        return !cls2.isAssignableFrom(cls);
    }

    public static ILoggerFactory d() {
        if (d == 0) {
            synchronized (vha.class) {
                if (d == 0) {
                    d = 1;
                    i();
                }
            }
        }
        int i = d;
        if (i == 1) {
            return b;
        }
        if (i == 2) {
            throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
        }
        if (i == 3) {
            return StaticLoggerBinder.getSingleton().getLoggerFactory();
        }
        if (i == 4) {
            return e;
        }
        throw new IllegalStateException("Unreachable code");
    }
}
