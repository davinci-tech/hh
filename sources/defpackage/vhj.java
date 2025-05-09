package defpackage;

/* loaded from: classes7.dex */
public final class vhj {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f17714a = false;
    private static e e;

    private vhj() {
    }

    public static String e(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null input");
        }
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static boolean b(String str) {
        String e2 = e(str);
        if (e2 == null) {
            return false;
        }
        return e2.equalsIgnoreCase("true");
    }

    static final class e extends SecurityManager {
        private e() {
        }

        @Override // java.lang.SecurityManager
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    private static e b() {
        e eVar = e;
        if (eVar != null) {
            return eVar;
        }
        if (f17714a) {
            return null;
        }
        e d = d();
        e = d;
        f17714a = true;
        return d;
    }

    private static e d() {
        try {
            return new e();
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static Class<?> a() {
        int i;
        e b = b();
        if (b == null) {
            return null;
        }
        Class<?>[] classContext = b.getClassContext();
        String name = vhj.class.getName();
        int i2 = 0;
        while (i2 < classContext.length && !name.equals(classContext[i2].getName())) {
            i2++;
        }
        if (i2 >= classContext.length || (i = i2 + 2) >= classContext.length) {
            throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
        }
        return classContext[i];
    }

    public static final void a(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void d(String str) {
        System.err.println("SLF4J: " + str);
    }
}
