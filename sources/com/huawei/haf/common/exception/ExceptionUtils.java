package com.huawei.haf.common.exception;

import health.compact.a.LogConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.IdentityHashMap;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.jar.JarException;

/* loaded from: classes.dex */
public final class ExceptionUtils {
    private ExceptionUtils() {
    }

    public static void b(Throwable th, PrintStream printStream, boolean z) {
        new WrappedThrowable(th).e(printStream, z);
    }

    public static void e(Throwable th, PrintWriter printWriter, boolean z) {
        new WrappedThrowable(th).b(printWriter, z);
    }

    public static String d(Throwable th) {
        return a(th, !LogConfig.a(), !(th instanceof ClassNotFoundException));
    }

    public static String a(Throwable th, boolean z, boolean z2) {
        if (th == null) {
            return "";
        }
        Throwable cause = th.getCause();
        if (cause == null || !z2) {
            return c(th, z);
        }
        return c(th, z) + ", Caused by: " + c(cause, z);
    }

    private static String c(Throwable th, boolean z) {
        if (z) {
            return e(th);
        }
        return th.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(Throwable th) {
        if (th instanceof FileNotFoundException) {
            return "*** Exception 10001 ***";
        }
        if (c(th) || a(th)) {
            return th.getClass().getName() + "：******";
        }
        return th.toString();
    }

    private static boolean a(Throwable th) {
        return (th instanceof SocketTimeoutException) || (th instanceof ConnectException);
    }

    public static void c(Thread thread, Throwable th, PrintStream printStream) {
        if (th != null) {
            b(th, printStream, true);
        } else {
            WrappedThrowable.c(thread.getStackTrace(), new WrappedPrintStream(printStream));
        }
        printStream.flush();
    }

    public static void b(Thread thread, Throwable th, PrintWriter printWriter) {
        if (th != null) {
            e(th, printWriter, true);
        } else {
            WrappedThrowable.c(thread.getStackTrace(), new WrappedPrintWriter(printWriter));
        }
        printWriter.flush();
    }

    public static boolean c(Throwable th) {
        if (th instanceof Exception) {
            return g(th);
        }
        if (th instanceof Error) {
            return f(th);
        }
        return false;
    }

    private static boolean g(Throwable th) {
        if (th instanceof IOException) {
            return i(th);
        }
        if (th instanceof RuntimeException) {
            return j(th);
        }
        return (th instanceof NotOwnerException) || (th instanceof SQLException);
    }

    private static boolean i(Throwable th) {
        return (th instanceof FileNotFoundException) || (th instanceof JarException) || (th instanceof BindException);
    }

    private static boolean j(Throwable th) {
        return (th instanceof MissingResourceException) || (th instanceof ConcurrentModificationException);
    }

    private static boolean f(Throwable th) {
        return (th instanceof OutOfMemoryError) || (th instanceof StackOverflowError);
    }

    static class WrappedThrowable {
        private final Throwable b;
        private Set<Throwable> e;

        WrappedThrowable(Throwable th) {
            this.b = th;
        }

        void b(PrintWriter printWriter, boolean z) {
            if (z) {
                e(new WrappedPrintWriter(printWriter));
            } else {
                this.b.printStackTrace(printWriter);
            }
        }

        void e(PrintStream printStream, boolean z) {
            if (z) {
                e(new WrappedPrintStream(printStream));
            } else {
                this.b.printStackTrace(printStream);
            }
        }

        static void c(StackTraceElement[] stackTraceElementArr, PrintStreamOrWriter printStreamOrWriter) {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                printStreamOrWriter.append("\tat ").println(stackTraceElement);
            }
        }

        private void e(PrintStreamOrWriter printStreamOrWriter) {
            Set<Throwable> newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
            this.e = newSetFromMap;
            newSetFromMap.add(this.b);
            synchronized (printStreamOrWriter.lock()) {
                printStreamOrWriter.println(ExceptionUtils.e(this.b));
                StackTraceElement[] stackTrace = this.b.getStackTrace();
                c(stackTrace, printStreamOrWriter);
                for (Throwable th : this.b.getSuppressed()) {
                    d(th, printStreamOrWriter, stackTrace, "Suppressed: ", "\t");
                }
                Throwable cause = this.b.getCause();
                if (cause != null) {
                    d(cause, printStreamOrWriter, stackTrace, "Caused by: ", "");
                }
            }
        }

        private void d(Throwable th, PrintStreamOrWriter printStreamOrWriter, StackTraceElement[] stackTraceElementArr, String str, String str2) {
            if (this.e.contains(th)) {
                printStreamOrWriter.append("\t[CIRCULAR REFERENCE:").append(ExceptionUtils.e(th)).println("]");
                return;
            }
            this.e.add(th);
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            for (int length2 = stackTraceElementArr.length - 1; length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2]); length2--) {
                length--;
            }
            int length3 = (stackTrace.length - 1) - length;
            printStreamOrWriter.append(str2).append(str).println(ExceptionUtils.e(th));
            for (int i = 0; i <= length; i++) {
                printStreamOrWriter.append(str2).append("\tat ").println(stackTrace[i]);
            }
            if (length3 != 0) {
                printStreamOrWriter.append(str2).append("\t... ").append(String.valueOf(length3)).println(" more");
            }
            for (Throwable th2 : th.getSuppressed()) {
                d(th2, printStreamOrWriter, stackTrace, "Suppressed: ", str2 + "\t");
            }
            Throwable cause = th.getCause();
            if (cause != null) {
                d(cause, printStreamOrWriter, stackTrace, "Caused by: ", str2);
            }
        }
    }

    static abstract class PrintStreamOrWriter {
        abstract PrintStreamOrWriter append(String str);

        abstract Object lock();

        abstract void println(Object obj);

        private PrintStreamOrWriter() {
        }
    }

    static class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream c;

        WrappedPrintStream(PrintStream printStream) {
            super();
            this.c = printStream;
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        Object lock() {
            return this.c;
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        void println(Object obj) {
            this.c.println(obj);
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        PrintStreamOrWriter append(String str) {
            this.c.append((CharSequence) str);
            return this;
        }
    }

    static class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter d;

        WrappedPrintWriter(PrintWriter printWriter) {
            super();
            this.d = printWriter;
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        Object lock() {
            return this.d;
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        void println(Object obj) {
            this.d.println(obj);
        }

        @Override // com.huawei.haf.common.exception.ExceptionUtils.PrintStreamOrWriter
        PrintStreamOrWriter append(String str) {
            this.d.append((CharSequence) str);
            return this;
        }
    }
}
