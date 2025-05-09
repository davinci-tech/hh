package com.huawei.watchface.utils;

import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import java.io.IOError;
import java.io.IOException;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.MissingResourceException;

/* loaded from: classes7.dex */
public final class HwLog {
    public static final int D = 1;
    public static final int E = 4;
    private static final String FILENOTFOUND = "FileNotFoundException";
    public static final int I = 2;
    private static final String INSUFFICIENTRESOURCESEXCEPTION = "InsufficientResourcesException";
    private static final String IOE = "IOException";
    private static final String IOERROR = "IOError";
    private static final String MODULE_TAG = "WatchFaceConnetor";
    public static final String TAG = "HwThemeManager";
    public static final int V = 0;
    public static final int W = 3;

    public static void v(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printLog(MODULE_TAG, str, 0, str2);
    }

    public static void vBetaLog(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printBetaLog(MODULE_TAG, str, 0, str2);
    }

    public static void d(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printLog(MODULE_TAG, str, 2, str2);
    }

    public static void dBetaLog(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printBetaLog(MODULE_TAG, str, 2, str2);
    }

    public static void i(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printLog(MODULE_TAG, str, 2, str2);
    }

    public static void iBetaLog(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printBetaLog(MODULE_TAG, str, 2, str2);
    }

    public static void w(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printLog(MODULE_TAG, str, 4, str2);
    }

    public static void wBetaLog(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printBetaLog(MODULE_TAG, str, 4, str2);
    }

    public static void e(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printLog(MODULE_TAG, str, 4, str2);
    }

    public static void eBetaLog(String str, String str2) {
        if (Environment.getApplicationContext() == null) {
            return;
        }
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).printBetaLog(MODULE_TAG, str, 4, str2);
    }

    public static String printException(Exception exc) {
        if (exc == null) {
            return "null Exception";
        }
        if ((exc instanceof IOException) || exc.toString().contains(IOE) || exc.toString().contains(FILENOTFOUND) || (exc instanceof MissingResourceException) || (exc instanceof NotOwnerException) || (exc instanceof ConcurrentModificationException) || (exc instanceof SQLException) || exc.toString().contains(INSUFFICIENTRESOURCESEXCEPTION)) {
            return exc.getClass().getSimpleName();
        }
        return exc.toString();
    }

    public static String printException(Error error) {
        if (error == null) {
            return "null Error";
        }
        if ((error instanceof IOError) || error.toString().contains(IOE) || error.toString().contains(IOERROR) || error.toString().contains(FILENOTFOUND) || (error instanceof OutOfMemoryError) || (error instanceof StackOverflowError)) {
            return error.getClass().getSimpleName();
        }
        return error.toString();
    }

    public static String printException(Throwable th) {
        if (th == null) {
            return "null Throwable";
        }
        if (th.toString().contains(IOE) || th.toString().contains(IOERROR) || th.toString().contains(FILENOTFOUND)) {
            return th.getClass().getSimpleName();
        }
        return th.toString();
    }
}
