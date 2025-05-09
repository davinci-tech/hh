package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class car {
    public static boolean a(Context context) {
        boolean a2 = a(context, Integer.toString(20002), "course_end_config_type", true);
        LogUtil.a("SportSettingStorageUtils", "acquireCourseEndConfig isKeepMoving: ", Boolean.valueOf(a2));
        return a2;
    }

    public static void d(Context context, boolean z) {
        LogUtil.a("SportSettingStorageUtils", "saveCourseEndConfig isKeepMoving: ", Boolean.valueOf(z));
        e(context, z, Integer.toString(20002), "course_end_config_type");
    }

    public static boolean d(Context context) {
        return a(context, Integer.toString(20002), "course_end_config_dialog_shown", false);
    }

    public static void b(Context context, boolean z) {
        e(context, z, Integer.toString(20002), "course_end_config_dialog_shown");
    }

    private static boolean a(Context context, String str, String str2, boolean z) {
        if (context == null) {
            LogUtil.b("SportSettingStorageUtils", "acquireBoolean context is null");
            return z;
        }
        String b = SharedPreferenceManager.b(context, str, str2);
        if (!TextUtils.isEmpty(b)) {
            try {
                return Boolean.parseBoolean(b);
            } catch (NumberFormatException e) {
                LogUtil.b("SportSettingStorageUtils", "acquireBoolean NumberFormatException", LogAnonymous.b((Throwable) e));
            }
        }
        return z;
    }

    private static void e(Context context, boolean z, String str, String str2) {
        if (context == null) {
            LogUtil.b("SportSettingStorageUtils", "saveBoolean context is null");
        } else {
            SharedPreferenceManager.e(context, str, str2, String.valueOf(z), new StorageParams());
        }
    }
}
