package health.compact.a.util;

import android.util.Log;
import com.huawei.hihealth.api.LogApi;

/* loaded from: classes.dex */
public class LogUtil {

    /* renamed from: a, reason: collision with root package name */
    private static LogApi f13147a;

    private LogUtil() {
    }

    public static void c(LogApi logApi) {
        f13147a = logApi;
    }

    public static LogApi d() {
        return f13147a;
    }

    public static void b(String str, Object... objArr) {
        LogApi logApi = f13147a;
        if (logApi == null) {
            b(str, 1, objArr);
        } else {
            logApi.d(str, objArr);
        }
    }

    public static void d(String str, Object... objArr) {
        LogApi logApi = f13147a;
        if (logApi == null) {
            b(str, 2, objArr);
        } else {
            logApi.i(str, objArr);
        }
    }

    public static void c(String str, Object... objArr) {
        LogApi logApi = f13147a;
        if (logApi == null) {
            b(str, 3, objArr);
        } else {
            logApi.w(str, objArr);
        }
    }

    public static void e(String str, Object... objArr) {
        LogApi logApi = f13147a;
        if (logApi == null) {
            b(str, 4, objArr);
        } else {
            logApi.e(str, objArr);
        }
    }

    public static boolean a() {
        LogApi logApi = f13147a;
        if (logApi == null) {
            return false;
        }
        return logApi.isBeta();
    }

    private static void b(String str, int i, Object... objArr) {
        StringBuilder sb = new StringBuilder(32);
        if (objArr != null) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        c(str, i, sb);
    }

    private static void c(String str, int i, StringBuilder sb) {
        if (i == 0) {
            Log.v(str, sb.toString());
            return;
        }
        if (i == 1) {
            Log.d(str, sb.toString());
            return;
        }
        if (i == 2) {
            Log.i(str, sb.toString());
        } else if (i == 3) {
            Log.w(str, sb.toString());
        } else {
            if (i != 4) {
                return;
            }
            Log.e(str, sb.toString());
        }
    }
}
