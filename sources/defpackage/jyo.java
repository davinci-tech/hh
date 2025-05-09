package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jyo {
    public static boolean c(Context context) {
        int i;
        if (context == null) {
            return false;
        }
        String lowerCase = Build.BRAND.toLowerCase(Locale.ENGLISH);
        if (!lowerCase.contains("oppo") && !lowerCase.contains("oneplus")) {
            return false;
        }
        try {
            i = context.getPackageManager().getPackageInfo("com.coloros.calendar", 0).versionCode;
            try {
                LogUtil.a("CalendarPermissionUtils", "oppo versionCode is", Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.h("CalendarPermissionUtils", "can not find oppo new calendar app");
                return i < 7001000 ? false : false;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            i = 0;
        }
        if (i < 7001000 && !d(context)) {
            return true;
        }
    }

    private static boolean d(Context context) {
        ApplicationInfo applicationInfo;
        ApplicationInfo applicationInfo2;
        boolean z = false;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo("com.coloros.calendar", 128);
            applicationInfo2 = context.getPackageManager().getApplicationInfo("com.android.providers.calendar", 128);
        } catch (Exception unused) {
        }
        if (applicationInfo != null && applicationInfo2 != null) {
            if (applicationInfo.metaData.getBoolean("mergeVersion") && applicationInfo2.metaData.getBoolean("mergeVersion")) {
                if (Settings.System.getInt(context.getContentResolver(), "key_calendar_migration", 0) == 1) {
                    z = true;
                }
            }
            LogUtil.a("CalendarPermissionUtils", "oppo is isMigratedVersion or not", Boolean.valueOf(z));
            return z;
        }
        return false;
    }

    public static DeviceInfo e() {
        DeviceInfo deviceInfo;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "CalendarPermissionUtils");
        if (deviceList.size() == 0) {
            LogUtil.h("CalendarPermissionUtils", "getContactedDeviceMac: the object of HwDeviceMgr is null. ");
            return null;
        }
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                deviceInfo = it.next();
                if (!cvt.c(deviceInfo.getProductType())) {
                    break;
                }
            }
        }
        deviceInfo = null;
        if (deviceInfo != null) {
            return deviceInfo;
        }
        LogUtil.h("CalendarPermissionUtils", "getContactedDeviceMac: the object of DeviceInfo is null. ");
        return null;
    }

    public static boolean a() {
        return cwi.c(e(), 184);
    }

    private static boolean h() {
        return c("android.permission.WRITE_CALENDAR");
    }

    private static boolean c() {
        return c("android.permission.READ_CALENDAR");
    }

    public static boolean d() {
        return h() && c();
    }

    public static String d(String str, boolean z) {
        if (z) {
            return "calendar_sync_smart_" + kak.b(str);
        }
        return "calendar_sync_" + kak.b(str);
    }

    public static void e(boolean z) {
        LogUtil.a("CalendarPermissionUtils", "setSyncAllCalendarFlag() in isFlag = ", Boolean.valueOf(z));
        SharedPreferenceManager.e("calendar_module", "key_calendar_all", z);
    }

    public static boolean b() {
        return SharedPreferenceManager.a("calendar_module", "key_calendar_all", false);
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CalendarPermissionUtils", "isPermissionGranted: permission is null or empty.");
            return false;
        }
        Context context = BaseApplication.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.checkPermission(str, context.getPackageName()) == 0;
        }
        LogUtil.h("CalendarPermissionUtils", "isPermissionGranted: packageManager is null or empty.");
        return false;
    }
}
