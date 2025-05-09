package com.huawei.profile.utils.logger;

import android.text.TextUtils;
import com.huawei.profile.utils.SensitiveUtil;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes6.dex */
public abstract class SystemPropertiesUtil {
    public static final String HEADLESS_SYTEM_USER = "ro.fw.mu.headless_system_user";
    private static final String LOG_OFF = "0";
    private static final String LOG_ON = "1";
    private static final String PROPERTY_DEBUG_ON = "persist.sys.huawei.debug.on";
    private static final String TAG = "SystemPropertiesUtil";
    public static final int USER0 = 0;

    public static String getProp(String str, String str2) {
        try {
            Class<?> cls = Class.forName("com.huawei.android.os.SystemPropertiesEx");
            Object invoke = cls.getDeclaredMethod("get", String.class, String.class).invoke(cls.newInstance(), str, str2);
            if (invoke instanceof String) {
                String str3 = (String) invoke;
                if (!TextUtils.isEmpty(str3)) {
                    return str3;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            DsLog.wt(TAG, "SystemPropertiesUtil get Error:" + SensitiveUtil.getSimpleName(e), new Object[0]);
        }
        return str2;
    }

    public static boolean getBooleanProp(String str, Boolean bool) {
        try {
            Class<?> cls = Class.forName("com.huawei.android.os.SystemPropertiesEx");
            Object invoke = cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE).invoke(cls.newInstance(), str, bool);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            DsLog.wt(TAG, "SystemPropertiesUtil get Error:" + SensitiveUtil.getSimpleName(e), new Object[0]);
        }
        return bool.booleanValue();
    }

    public static boolean isHeadlessSystemUser() {
        return getBooleanProp("ro.fw.mu.headless_system_user", false);
    }

    public static boolean isDebugOn() {
        String prop = getProp(PROPERTY_DEBUG_ON, "0");
        DsLog.it(TAG, "debugOnFlag is:" + prop, new Object[0]);
        return "1".equals(prop);
    }
}
