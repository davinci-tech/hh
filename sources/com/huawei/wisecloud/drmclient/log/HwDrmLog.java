package com.huawei.wisecloud.drmclient.log;

import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOError;
import java.io.IOException;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.MissingResourceException;

/* loaded from: classes7.dex */
public class HwDrmLog {
    private static final String FILENOTFOUND = "FileNotFoundException";
    private static final String INSUFFICIENTRESOURCESEXCEPTION = "InsufficientResourcesException";
    private static final String IOE = "IOException";
    private static final String IOERROR = "IOError";
    private static final String MODULE_TAG = "HWDRM";
    private static boolean sHwDebug = false;

    public static boolean isDebuggable() {
        return sHwDebug;
    }

    public static void v(String str, String str2) {
        if (sHwDebug) {
            Log.v(MODULE_TAG, str + " - verbose:" + str2);
        }
    }

    public static void d(String str, String str2) {
        if (sHwDebug) {
            Log.d(MODULE_TAG, str + " - debug:" + str2);
        }
    }

    public static void i(String str, String str2) {
        Log.i(MODULE_TAG, str + " - info: " + str2);
    }

    public static void w(String str, String str2) {
        Log.w(MODULE_TAG, str + " - warn: " + str2);
    }

    public static void w(String str, String str2, Throwable th) {
        Log.w(MODULE_TAG, str + " - warn:" + str2 + Constants.LINK + printException(th));
    }

    public static void e(String str, String str2) {
        Log.e(MODULE_TAG, str + " - error:" + str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(MODULE_TAG, str + " - error:" + str2 + Constants.LINK + printException(th));
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
