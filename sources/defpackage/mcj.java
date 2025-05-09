package defpackage;

import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.acl.NotOwnerException;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
import java.util.MissingResourceException;

/* loaded from: classes.dex */
public class mcj {
    private static boolean c = false;
    private static boolean e = false;

    static {
        boolean z;
        try {
            Field field = Log.class.getField("HWLog");
            Field field2 = Log.class.getField("HWModuleLog");
            e = field.getBoolean(null);
            boolean z2 = field2.getBoolean(null);
            c = z2;
            if (!e && (!z2 || !Log.isLoggable("LogUtils", 3))) {
                z = false;
                e = z;
                Log.i("LogUtils", "HwDebug:" + e + " HwModuleDebug:" + c);
            }
            z = true;
            e = z;
            Log.i("LogUtils", "HwDebug:" + e + " HwModuleDebug:" + c);
        } catch (IllegalAccessException e2) {
            Log.e("LogUtils", "error:getLogField--IllegalAccessException" + e(e2));
        } catch (IllegalArgumentException e3) {
            Log.e("LogUtils", "error:getLogField--IllegalArgumentException" + e(e3));
        } catch (NoSuchFieldException e4) {
            Log.e("LogUtils", "error:getLogField--NoSuchFieldException" + e(e4));
        }
    }

    public static void c(String str, String str2) {
        Log.i("PictureSecuritySDK", str + " info:" + str2);
    }

    public static void d(String str, String str2) {
        Log.w("PictureSecuritySDK", str + " warn: " + str2);
    }

    public static void b(String str, String str2) {
        Log.e("PictureSecuritySDK", str + " error:" + str2);
    }

    public static void d(String str, String str2, Throwable th) {
        Log.e("PictureSecuritySDK", str + " error:" + str2 + Constants.LINK + e(th));
    }

    public static String e(Throwable th) {
        if (th == null) {
            return "null Throwable";
        }
        if (th instanceof Exception) {
            if ((th instanceof IOException) || th.toString().contains("IOException") || th.toString().contains("FileNotFoundException") || (th instanceof MissingResourceException) || (th instanceof NotOwnerException) || (th instanceof ConcurrentModificationException) || (th instanceof SQLException) || th.toString().contains("InsufficientResourcesException")) {
                return th.getClass().getSimpleName();
            }
        } else if (th instanceof Error) {
            if ((th instanceof IOError) || th.toString().contains("IOException") || th.toString().contains("IOError") || th.toString().contains("FileNotFoundException") || (th instanceof OutOfMemoryError) || (th instanceof StackOverflowError)) {
                return th.getClass().getSimpleName();
            }
        } else if (th.toString().contains("IOException") || th.toString().contains("IOError") || th.toString().contains("FileNotFoundException")) {
            return th.getClass().getSimpleName();
        }
        return th.toString();
    }
}
