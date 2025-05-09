package defpackage;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jrd {
    private static boolean b = false;

    public static void b(DeviceInfo deviceInfo, String str, String str2, String str3, String str4) {
        String d = DateFormatUtil.d(System.currentTimeMillis(), null, "yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put(EventMonitorRecord.EVENT_ID, str);
        linkedHashMap.put("transType", str2);
        if (deviceInfo != null) {
            linkedHashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, deviceInfo.getSoftVersion());
        } else {
            linkedHashMap.put(HiAnalyticsConstant.HaKey.BI_KEY_BASE_VERSION, "");
            LogUtil.h("HwDeviceUpdateUtil", "uploadOtaEvent deviceInfo is null, eventId = ", str);
        }
        if (deviceInfo != null && ("050901".equals(str) || "050902".equals(str) || "050903".equals(str) || "050904".equals(str) || "050905".equals(str) || "050906".equals(str) || "050907".equals(str) || "050909".equals(str))) {
            linkedHashMap.put("targetVersion", kxz.j(BaseApplication.getContext(), deviceInfo.getDeviceIdentify()));
        } else {
            linkedHashMap.put("targetVersion", "");
        }
        linkedHashMap.put("errorCode", str3);
        linkedHashMap.put("timestamp", d);
        linkedHashMap.put(CloudParamKeys.INFO, str4);
        bls.a(new JSONObject(linkedHashMap).toString(), "", 1, deviceInfo);
    }

    public static boolean d(long j, long j2) {
        Calendar c = c(j);
        Calendar c2 = c(j2);
        return c.get(1) == c2.get(1) && c.get(2) == c2.get(2) && Math.abs(c.get(5) - c2.get(5)) < 1;
    }

    private static Calendar c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return calendar;
    }

    public static boolean e(int i, String str) {
        LogUtil.a("HwDeviceUpdateUtil", "isAlreadyCheck deviceId ", CommonUtil.l(str));
        boolean a2 = kxz.a(kxz.c(BaseApplication.getContext(), str), i);
        LogUtil.a("HwDeviceUpdateUtil", "isAlreadyCheck = ", Boolean.valueOf(a2));
        return a2;
    }

    public static boolean b() {
        Object systemService = BaseApplication.getContext().getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> runningTasks = systemService instanceof ActivityManager ? ((ActivityManager) systemService).getRunningTasks(10) : null;
        if (runningTasks == null || runningTasks.size() <= 0) {
            LogUtil.h("HwDeviceUpdateUtil", "(runningTaskInfos == null) || (runningTaskInfos.size() <= 0)");
            return false;
        }
        for (int i = 0; i < runningTasks.size(); i++) {
            if (runningTasks.get(i).topActivity != null && TextUtils.equals(runningTasks.get(i).topActivity.getPackageName(), BaseApplication.getContext().getPackageName()) && (TextUtils.equals(runningTasks.get(i).topActivity.getClassName(), "com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity") || TextUtils.equals(runningTasks.get(i).topActivity.getClassName(), "com.huawei.ui.device.activity.pairing.DevicePairGuideActivity") || TextUtils.equals(runningTasks.get(i).topActivity.getClassName(), "com.huawei.ui.device.activity.update.UpdateVersionActivity") || TextUtils.equals(runningTasks.get(i).topActivity.getClassName(), "com.huawei.ui.device.activity.update.DeviceOtaActivity") || TextUtils.equals(runningTasks.get(i).topActivity.getClassName(), ComponentInfo.PluginWearAbility_A_2))) {
                LogUtil.a("HwDeviceUpdateUtil", "band is showing ,current activity :", runningTasks.get(i).topActivity.getClassName());
                return true;
            }
        }
        return false;
    }

    public static boolean f() {
        Object systemService = BaseApplication.getContext().getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> runningTasks = systemService instanceof ActivityManager ? ((ActivityManager) systemService).getRunningTasks(10) : null;
        if (runningTasks == null || runningTasks.size() <= 0) {
            LogUtil.h("HwDeviceUpdateUtil", "(runningTaskInfos == null) || (runningTaskInfos.size() <= 0)");
            return true;
        }
        if (runningTasks.get(0).topActivity == null) {
            return true;
        }
        String packageName = runningTasks.get(0).topActivity.getPackageName();
        if (!TextUtils.equals(packageName, BaseApplication.getContext().getPackageName())) {
            return true;
        }
        if (!TextUtils.equals(packageName, "com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity") && !TextUtils.equals(packageName, "com.huawei.ui.device.activity.pairing.DevicePairGuideActivity") && !TextUtils.equals(packageName, "com.huawei.ui.device.activity.update.UpdateVersionActivity") && !TextUtils.equals(packageName, "com.huawei.ui.device.activity.update.DeviceOtaActivity") && !TextUtils.equals(packageName, ComponentInfo.PluginWearAbility_A_2)) {
            return false;
        }
        LogUtil.a("HwDeviceUpdateUtil", "band is showing ,current activity :", packageName);
        return true;
    }

    public static void b(String str, boolean z) {
        String str2 = "update_key_band_new_version_flag";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_band_new_version_flag";
        }
        LogUtil.a("HwDeviceUpdateUtil", "refreshRestoreOtaFlag key:", str2, " isNeedInit ", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str2, String.valueOf(z), (StorageParams) null);
    }

    public static boolean e(String str) {
        String str2 = "update_key_band_new_version_flag";
        if (!TextUtils.isEmpty(str)) {
            str2 = knl.a(str) + "update_key_band_new_version_flag";
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), str2);
        LogUtil.a("HwDeviceUpdateUtil", "isRestoreOtaFlag key:", str2, " isNeedInit ", b2);
        return Boolean.parseBoolean(b2);
    }

    public static boolean d(String str) {
        Date b2;
        LogUtil.a("HwDeviceUpdateUtil", "isCheckInterval : lastTime = ", str);
        if (TextUtils.isEmpty(str) || (b2 = kxz.b(str)) == null) {
            return true;
        }
        return Math.abs(System.currentTimeMillis() - b2.getTime()) > 43200000;
    }

    public static String b(String str) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), str);
        LogUtil.a("HwDeviceUpdateUtil", "getLastStartedTime, lastTime ", b2);
        return b2;
    }

    public static void c(String str) {
        String c = kxz.c();
        LogUtil.a("HwDeviceUpdateUtil", "setStartedTime, currentTimeMinutes ", c);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), str, c, (StorageParams) null);
    }

    public static boolean d() {
        return b;
    }

    public static void d(boolean z) {
        b = z;
    }

    public static void c(String str, boolean z) {
        LogUtil.a("HwDeviceUpdateUtil", "setUpdateBand deviceId ", CommonUtil.l(str), " isUpdate ", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1003), "is_auto_update_band" + knl.d(str), String.valueOf(z), (StorageParams) null);
    }

    public static boolean a(String str) {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), "is_auto_update_band" + knl.d(str));
        LogUtil.a("HwDeviceUpdateUtil", "getUpdateBand deviceId ", CommonUtil.l(str), " updateResult ", b2);
        return Boolean.parseBoolean(b2);
    }

    public static boolean a() {
        Object systemService = BaseApplication.getContext().getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> runningTasks = systemService instanceof ActivityManager ? ((ActivityManager) systemService).getRunningTasks(10) : null;
        if (runningTasks == null || runningTasks.size() <= 0) {
            LogUtil.h("HwDeviceUpdateUtil", "isBandTransferActivity (runningTaskInfos == null) || (runningTaskInfos.size() <= 0)");
            return false;
        }
        for (int i = 0; i < runningTasks.size(); i++) {
            if (runningTasks.get(i).topActivity != null && TextUtils.equals(runningTasks.get(i).topActivity.getPackageName(), BaseApplication.getContext().getPackageName()) && TextUtils.equals(runningTasks.get(0).topActivity.getClassName(), "com.huawei.ui.device.activity.update.DeviceOtaActivity")) {
                LogUtil.a("HwDeviceUpdateUtil", "isBandTransferActivity band is showing ,current activity :", runningTasks.get(0).topActivity.getClassName());
                return true;
            }
        }
        return false;
    }

    public static boolean e() {
        boolean g = g();
        LogUtil.a("HwDeviceUpdateUtil", "isMobileTraffic isWifiConnected = ", Boolean.valueOf(g));
        if (CommonUtil.bh()) {
            int c = c();
            LogUtil.a("HwDeviceUpdateUtil", "isMobileTraffic wifiType = ", Integer.valueOf(c));
            if (g && c == 1) {
                return false;
            }
        } else if (g) {
            return false;
        }
        return true;
    }

    public static boolean g() {
        NetworkInfo activeNetworkInfo;
        LogUtil.a("HwDeviceUpdateUtil", "isWifiConnected");
        Object systemService = BaseApplication.getContext().getSystemService("connectivity");
        return (systemService instanceof ConnectivityManager) && (activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1;
    }

    public static int c() {
        try {
            Class<?> cls = Class.forName("com.huawei.android.net.wifi.WifiManagerCommonEx");
            Object invoke = cls.getMethod("getHwMeteredHint", Context.class).invoke(cls.newInstance(), BaseApplication.getContext());
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue() ? 2 : 1;
            }
            return 2;
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi ClassNotFoundException");
            return 0;
        } catch (IllegalAccessException unused2) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi IllegalAccessException");
            return 0;
        } catch (IllegalArgumentException unused3) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi IllegalArgumentException");
            return 0;
        } catch (InstantiationException unused4) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi InstantiationException");
            return 0;
        } catch (NoSuchMethodException unused5) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi NoSuchMethodException");
            return 0;
        } catch (InvocationTargetException unused6) {
            LogUtil.b("HwDeviceUpdateUtil", "isMeteredWifi InvocationTargetException");
            return 0;
        }
    }
}
