package com.huawei.agconnect.apms;

import android.content.Context;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes2.dex */
public class a0 {
    public static final AgentLog abc = AgentLogManager.getAgentLog();

    public static Display abc(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            if (windowManager != null) {
                return windowManager.getDefaultDisplay();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x011d A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String bcd() {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.a0.bcd():java.lang.String");
    }

    public static Object abc(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return abc(Class.forName(str), str2, clsArr, objArr);
        } catch (Throwable th) {
            abc.warn("failed to get method: " + th.getMessage());
            return null;
        }
    }

    public static Object abc(Class<?> cls, String str, Class[] clsArr, Object[] objArr) {
        if (clsArr == null) {
            if (objArr != null) {
                return null;
            }
        } else if (objArr == null || clsArr.length != objArr.length) {
            return null;
        }
        try {
        } catch (Throwable th) {
            abc.warn("exception occurred when invoking method: " + th.getMessage());
        }
        try {
            return cls.getMethod(str, clsArr).invoke(null, objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            abc.warn("failed to invoke method: " + e.getMessage());
            return null;
        }
    }

    public static boolean abc() {
        return Build.MANUFACTURER.equals("HONOR") && Build.VERSION.SDK_INT >= 31;
    }
}
