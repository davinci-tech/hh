package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.util.Date;

/* loaded from: classes5.dex */
public class kxy {
    public static String b(Context context, String str) {
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_auto_check_time" + str);
        LogUtil.a("Scale_ScaleUpdateUtil", "getScaleAutoCheckTime,mAutoCheckTime ", b);
        return b;
    }

    public static void d(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setScaleAutoCheckTime,time ", str);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_auto_check_time" + str2, str, storageParams);
    }

    public static boolean d(String str) {
        Date a2;
        LogUtil.a("Scale_ScaleUpdateUtil", "isAlreadyUpdated isAlreadyUpdatedOfBand: strLastTime = ", str);
        if (TextUtils.isEmpty(str) || (a2 = kxz.a(str)) == null) {
            return false;
        }
        long time = a2.getTime();
        LogUtil.a("Scale_ScaleUpdateUtil", "isAlreadyUpdated last = ", Long.valueOf(time));
        return Math.abs(System.currentTimeMillis() - time) <= 259200000;
    }

    public static void e(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setScaleCheckNewVersion,version ", str);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_new_version" + str2, str, storageParams);
    }

    public static String a(Context context, String str) {
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_new_version" + str);
        LogUtil.a("Scale_ScaleUpdateUtil", "getScaleCheckNewVersion,mVersion ", b);
        return b;
    }

    public static void a(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setScaleLastVersionCode, lastVersionCode ", str);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_last_version_code" + str2, str, storageParams);
    }

    public static String c(Context context, String str) {
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_store_path" + str);
        LogUtil.a("Scale_ScaleUpdateUtil", "getScaleStorePath,mStorePath ", b);
        return b;
    }

    public static void h(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setScaleStorePath, storePath ", str);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_store_path" + str2, str, storageParams);
    }

    public static void c(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setScaleDeviceVersion,Version ", str);
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_device_version" + str2, str, storageParams);
    }

    public static void d(Context context, String str) {
        String c = c(context, str);
        LogUtil.a("Scale_ScaleUpdateUtil", "deleteScaleUpdateDfu: path = ", c);
        if (TextUtils.isEmpty(c)) {
            return;
        }
        File file = new File(c);
        if (!file.exists() || file.delete()) {
            return;
        }
        LogUtil.b("Scale_ScaleUpdateUtil", "deleteScaleUpdateDfu: path = ", c, " failed!");
    }

    public static void b(Context context, boolean z, String str) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setHaveNewScaleVersion,isHaveResult ", Boolean.valueOf(z));
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_new_version_tip" + str, "" + z, storageParams);
    }

    public static boolean h(Context context, String str) {
        String str2 = "is_new_honor" + knl.d(str);
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), str2);
        boolean parseBoolean = Boolean.parseBoolean(b);
        LogUtil.a("Scale_ScaleUpdateUtil", "isNewWeightHonor,isNewHonor ", b, " newHonorKey ", str2);
        return parseBoolean;
    }

    public static boolean j(Context context, String str) {
        String e = e(context, str);
        LogUtil.a("Scale_ScaleUpdateUtil", "haveWeightDeviceNewVersion currentVersion: ", e);
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        String i = i(context, str);
        LogUtil.a("Scale_ScaleUpdateUtil", "haveWeightDeviceNewVersion newVersion: ", i);
        if (TextUtils.isEmpty(i)) {
            return false;
        }
        return !e.equals(i);
    }

    public static void b(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setDeviceCurrentVersion,version：", str);
        if (context == null) {
            LogUtil.h("Scale_ScaleUpdateUtil", "setDeviceCurrentVersion context is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_device_current_version" + str2, str, storageParams);
    }

    public static String e(Context context, String str) {
        if (context == null) {
            LogUtil.h("Scale_ScaleUpdateUtil", "getDeviceCurrentVersion context is null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_device_current_version" + str);
        LogUtil.a("Scale_ScaleUpdateUtil", "getDeviceCurrentVersion,version :", b);
        return b;
    }

    public static void j(String str, Context context, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "setWeightDeviceNewVersion,version：", str);
        if (context == null) {
            LogUtil.h("Scale_ScaleUpdateUtil", "setWeightDeviceNewVersion context is null");
            return;
        }
        StorageParams storageParams = new StorageParams();
        storageParams.d(0);
        SharedPreferenceManager.e(context, String.valueOf(1003), "update_key_scale_device_new_version" + str2, str, storageParams);
    }

    public static String i(Context context, String str) {
        if (context == null) {
            LogUtil.h("Scale_ScaleUpdateUtil", "getWeightDeviceNewVersion context is null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(1003), "update_key_scale_device_new_version" + str);
        LogUtil.a("Scale_ScaleUpdateUtil", "getWeightDeviceNewVersion,version :", b);
        return b;
    }

    public static void d(Context context, String str, String str2) {
        LogUtil.a("Scale_ScaleUpdateUtil", "resetScaleUpdateInfo");
        b(context, false, str);
        d("", context, str);
        e("", context, str);
        c("", context, str);
        a("", context, str);
        j("", context, str2);
    }
}
