package defpackage;

import java.util.Arrays;

/* loaded from: classes.dex */
public class uhy {
    public static int a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    public static int c(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private uhy() {
    }

    public static String b(String str, Object obj) {
        return str + obj;
    }

    public static void d(Object obj) {
        if (obj == null) {
            d();
        }
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            b(str);
        }
    }

    public static void b() {
        throw ((uei) a(new uei()));
    }

    public static void d() {
        throw ((NullPointerException) a(new NullPointerException()));
    }

    public static void b(String str) {
        throw ((NullPointerException) a(new NullPointerException(str)));
    }

    public static void a(String str) {
        throw ((uer) a(new uer(str)));
    }

    public static void d(String str) {
        a("lateinit property " + str + " has not been initialized");
    }

    public static void d(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((IllegalStateException) a(new IllegalStateException(str + " must not be null")));
    }

    public static void a(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw ((NullPointerException) a(new NullPointerException(str + " must not be null")));
    }

    public static void c(Object obj, String str) {
        if (obj == null) {
            j(str);
        }
    }

    public static void e(Object obj, String str) {
        if (obj == null) {
            h(str);
        }
    }

    private static void j(String str) {
        throw ((IllegalArgumentException) a(new IllegalArgumentException(e(str))));
    }

    private static void h(String str) {
        throw ((NullPointerException) a(new NullPointerException(e(str))));
    }

    private static String e(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = uhy.class.getName();
        int i = 0;
        while (!stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        while (stackTrace[i].getClassName().equals(name)) {
            i++;
        }
        StackTraceElement stackTraceElement = stackTrace[i];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    public static boolean e(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static void a() {
        c("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void c(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static void c(int i, String str) {
        a();
    }

    private static <T extends Throwable> T a(T t) {
        return (T) d((Throwable) t, uhy.class.getName());
    }

    static <T extends Throwable> T d(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        t.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return t;
    }
}
