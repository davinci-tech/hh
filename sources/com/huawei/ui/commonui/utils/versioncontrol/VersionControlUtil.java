package com.huawei.ui.commonui.utils.versioncontrol;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.ReflectionUtils;
import health.compact.a.Utils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class VersionControlUtil {
    private static final int REFLECT_METHOD_PARAM_LENGTH = 2;
    public static final boolean SLEEP_MANAGER_DEFAULT_STRATEGY = true;
    private static final String TAG = "CommonUI_VersionControlUtil";

    public static boolean isSupportSleepManagement() {
        return true;
    }

    public static boolean isShowSmartSleepImprovement() {
        return (Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true;
    }

    public static boolean isShownOversea() {
        return Utils.o();
    }

    public static boolean isVersioned(String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String[] split = str.split("#");
        int length = split.length;
        if (length != 2) {
            LogUtil.b(TAG, "version control method format wrong, current length is ", Integer.valueOf(length));
            return true;
        }
        String str2 = split[0];
        String str3 = split[1];
        if (str2.startsWith("!")) {
            str2 = str2.substring(1);
        } else {
            z = false;
        }
        return reflectVersionControlMethod(str2, str3, z);
    }

    private static boolean reflectVersionControlMethod(String str, String str2, boolean z) {
        try {
            Object invoke = ReflectionUtils.b(ReflectionUtils.d(str), str2, new Class[0]).invoke(null, new Object[0]);
            if (invoke instanceof Boolean) {
                return z != ((Boolean) invoke).booleanValue();
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.h(TAG, "reflect version control method fail");
        }
        return true;
    }
}
