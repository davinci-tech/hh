package defpackage;

import java.io.FileNotFoundException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.MissingResourceException;
import java.util.jar.JarException;

/* loaded from: classes3.dex */
public class bll {
    public static String a(Throwable th) {
        return a(th, true, true);
    }

    private static String a(Throwable th, boolean z, boolean z2) {
        if (th == null) {
            return "";
        }
        Throwable cause = th.getCause();
        if (cause == null || !z2) {
            return b(th, z);
        }
        return b(th, z) + ", Caused by: " + b(cause, z);
    }

    private static String b(Throwable th, boolean z) {
        if (z) {
            return d(th);
        }
        return th.toString();
    }

    private static String d(Throwable th) {
        if (th instanceof FileNotFoundException) {
            return "*** Exception 10001 ***";
        }
        if (c(th) || b(th)) {
            return th.getClass().getName() + "：******";
        }
        return th.toString();
    }

    public static boolean c(Throwable th) {
        if (th instanceof Error) {
            return e(th);
        }
        if (th instanceof Exception) {
            return f(th);
        }
        return false;
    }

    private static boolean e(Throwable th) {
        return (th instanceof OutOfMemoryError) || (th instanceof StackOverflowError);
    }

    private static boolean f(Throwable th) {
        return (th instanceof FileNotFoundException) || (th instanceof JarException) || (th instanceof BindException) || (th instanceof MissingResourceException) || (th instanceof ConcurrentModificationException) || (th instanceof NotOwnerException) || (th instanceof SQLException);
    }

    private static boolean b(Throwable th) {
        return (th instanceof SocketTimeoutException) || (th instanceof ConnectException);
    }
}
