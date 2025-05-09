package ohos.security.deviceauth.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import defpackage.uwn;
import defpackage.uwp;
import defpackage.uwr;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class DeviceUtil {
    private static final String API_LEVEL_PROP = "ro.build.hw_emui_api_level";
    private static final String BRAND_HARMONY = "harmony";
    private static final String CLASS_NAME_BUILDEX = "com.huawei.android.os.BuildEx";
    private static final String CLASS_NAME_DAS = "com.huawei.security.deviceauth.HwDeviceGroupManager";
    private static final String CLASS_NAME_PROPERTIES = "android.os.SystemProperties";
    private static final String EMUI_VERSION_PREFIX = "EmotionUI";
    private static final String EMUI_VERSION_PROP = "ro.build.version.emui";
    private static final String GROUP_AUTH_PERMISSION = "com.huawei.permission.ACCESS_HW_GROUP_AUTH_SERVICE";
    private static final String GROUP_AUTH_SERVICE_NAME = "com.huawei.securityserver.HwDeviceGroupAuthService";
    private static final String GROUP_AUTH_SERVICE_PACKAGE_NAME = "com.huawei.securityserver";
    private static final String GROUP_MANAGE_PERMISSION = "com.huawei.permission.ACCESS_DEVICE_GROUP_MANAGER";
    private static final int HM_START_VERSION = 25;
    private static final boolean IS_BUILD_EX_SUPPORTED = isEmuiBuildExSupported();
    private static final boolean IS_HUAWEI_SYSTEM = isHuaweiSystem();
    private static final boolean IS_SYSTEM_SERVICE_SUPPORTED = isHichain3Supported();
    private static final int MAIN_USER = 0;
    private static final String MANUFACTURER_HONOR = "HONOR";
    private static final String MANUFACTURER_PROP = "ro.product.manufacturer";
    private static final String METHOD_NAME_DAS = "processRequestData";
    private static final String METHOD_NAME_GET_HC_UDID = "getHarmonyUDID";
    private static final String METHOD_NAME_GET_UDID = "getUDID";
    private static final String OS_PKG_ANDROID = "android";
    private static final String OS_PKG_HWEXT = "androidhwext";
    private static final int PER_USER_RANGE = 100000;
    private static final String TAG = "DeviceUtil";

    private DeviceUtil() {
    }

    public static boolean isSystemServiceSupported(Context context) {
        if (IS_SYSTEM_SERVICE_SUPPORTED && isExistGroupAuthService(context) && isHaveAccessPermission(context)) {
            uwn.a(TAG, "Support using the system service capabilities.");
            return true;
        }
        uwn.a(TAG, "Support using the native service capabilities.");
        return false;
    }

    public static String getUdid() {
        String udidBySn;
        if (IS_HUAWEI_SYSTEM) {
            uwn.a(TAG, "Get udid from huawei system.");
            udidBySn = getHuaweiUdid();
        } else {
            uwn.a(TAG, "Get udid from third-party system.");
            udidBySn = getUdidBySn();
        }
        if (TextUtils.isEmpty(udidBySn)) {
            Context b = uwp.b();
            if (b == null) {
                uwn.e(TAG, "getAndroidId: Global context is null.");
                return "";
            }
            udidBySn = getAndroidId(b);
        }
        return udidBySn == null ? "" : udidBySn;
    }

    private static boolean isExistGroupAuthService(Context context) {
        if (context == null) {
            uwn.e(TAG, "isExistGroupAuthService: context is null");
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName(GROUP_AUTH_SERVICE_PACKAGE_NAME, GROUP_AUTH_SERVICE_NAME);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            uwn.e(TAG, "isExistGroupAuthService: PackageManager is null");
            return false;
        }
        if (packageManager.resolveService(intent, 0) == null) {
            uwn.a(TAG, "isExistGroupAuthService: groupAuthService is not exist");
            return false;
        }
        uwn.a(TAG, "isExistGroupAuthService: groupAuthService is exist");
        return true;
    }

    private static boolean isHaveAccessPermission(Context context) {
        if (context == null) {
            uwn.e(TAG, "isHaveAccessPermission: context is null");
            return false;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            uwn.a(TAG, "isHaveAccessPermission: can not get callerPackageName");
            return false;
        }
        if (!checkCallerPermission(context, packageName, GROUP_MANAGE_PERMISSION)) {
            uwn.e(TAG, "caller not has group manage permission, callerPackageName:" + packageName);
            return false;
        }
        if (!checkCallerPermission(context, packageName, GROUP_AUTH_PERMISSION)) {
            uwn.e(TAG, "caller not has group auth permission, callerPackageName:" + packageName);
            return false;
        }
        uwn.a(TAG, "isHaveAccessPermission: caller has access permission");
        return true;
    }

    private static boolean isHichain3Supported() {
        if (!IS_HUAWEI_SYSTEM) {
            uwn.a(TAG, "Non-Huawei and non-Harmony, use the native service capabilities.");
            return false;
        }
        try {
            Class.forName(CLASS_NAME_DAS).getMethod(METHOD_NAME_DAS, Long.TYPE, byte[].class);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            uwn.a(TAG, "Old version, use the native service capabilities.");
        }
        if (Process.myUid() / 100000 == 0) {
            uwn.a(TAG, "New version in main user, use the system service capabilities.");
            return true;
        }
        uwn.a(TAG, "New version in sub user, use the native service capabilities.");
        return false;
    }

    private static boolean isHuaweiSystem() {
        return isEmuiSystem() || isHarmonySystem();
    }

    private static boolean isEmuiSystem() {
        if (isHonorSystem()) {
            uwn.a(TAG, "This is Honor system.");
            return false;
        }
        if (!IS_BUILD_EX_SUPPORTED) {
            uwn.a(TAG, "BuildEx is not supported.");
            return false;
        }
        String systemProperties = getSystemProperties(EMUI_VERSION_PROP);
        if (TextUtils.isEmpty(systemProperties)) {
            uwn.e(TAG, "Cannot get EMUI version.");
            return false;
        }
        return systemProperties.contains(EMUI_VERSION_PREFIX);
    }

    private static boolean isHonorSystem() {
        return "HONOR".equals(getSystemProperties(MANUFACTURER_PROP));
    }

    private static boolean isHarmonySystem() {
        if (isHonorSystem()) {
            uwn.a(TAG, "This is Honor system.");
            return false;
        }
        try {
            Object invoke = Class.forName("com.huawei.system.BuildEx").getDeclaredMethod("getOsBrand", new Class[0]).invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return BRAND_HARMONY.equals(invoke);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            uwn.e(TAG, "Unexpected exception.");
        }
        return false;
    }

    private static int getEmuiVersion() {
        if (!IS_BUILD_EX_SUPPORTED) {
            return 0;
        }
        String systemProperties = getSystemProperties(API_LEVEL_PROP);
        if (TextUtils.isEmpty(systemProperties)) {
            uwn.e(TAG, "Get invalid api level.");
            return 0;
        }
        try {
            return Integer.parseInt(systemProperties);
        } catch (NumberFormatException unused) {
            uwn.e(TAG, "Invalid format of version.");
            return 0;
        }
    }

    private static String getHuaweiUdid() {
        String harmonyUdid;
        String str = null;
        try {
            harmonyUdid = getHarmonyUdid();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            uwn.e(TAG, "Old Huawei platform.");
        } catch (IllegalAccessException | InvocationTargetException unused2) {
            uwn.e(TAG, "Unexpected exception for getting udid.");
            str = "";
        }
        if (!TextUtils.isEmpty(harmonyUdid)) {
            uwn.a(TAG, "getHarmonyUDID deviceUdid");
            return harmonyUdid;
        }
        str = (String) Class.forName(CLASS_NAME_BUILDEX).getMethod(METHOD_NAME_GET_UDID, new Class[0]).invoke(null, new Object[0]);
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (str == null) {
            return getImei();
        }
        return getUdidBySn();
    }

    private static String getHarmonyUdid() {
        try {
            uwn.a(TAG, "getHarmonyUdid deviceUdid");
            Object invoke = Class.forName(CLASS_NAME_BUILDEX).getMethod(METHOD_NAME_GET_HC_UDID, new Class[0]).invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            uwn.e(TAG, "getHarmonyUDID ClassNotFoundException");
            return null;
        } catch (NoSuchMethodException unused2) {
            uwn.e(TAG, "getHarmonyUDID NoSuchMethodException");
            return null;
        } catch (InvocationTargetException unused3) {
            uwn.e(TAG, "getHarmonyUDID InvocationTargetException");
            return null;
        } catch (Exception unused4) {
            uwn.e(TAG, "getHarmonyUDID others:");
            return null;
        }
    }

    private static String getImei() {
        uwn.a(TAG, "Get udid by imei");
        Context b = uwp.b();
        if (b == null) {
            uwn.e(TAG, "Global context is null.");
            return "";
        }
        if (!hasGetPermission(uwp.b(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            return "";
        }
        try {
            return ((TelephonyManager) b.getSystemService("phone")).getImei();
        } catch (SecurityException unused) {
            uwn.e(TAG, "SecurityException: not allowed to get Imei!");
            return "";
        } catch (Exception unused2) {
            uwn.e(TAG, "Other Exception!");
            return "";
        }
    }

    private static boolean isSystemExitPermission(Context context, String str) {
        PermissionInfo permissionInfo;
        try {
            permissionInfo = context.getPackageManager().getPermissionInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            uwn.b("cannot found permission: " + str, new Object[0]);
            permissionInfo = null;
        }
        if (permissionInfo == null) {
            return false;
        }
        uwn.a(TAG, "android res: " + "android".equals(permissionInfo.packageName) + " hwext res: " + OS_PKG_HWEXT.equals(permissionInfo.packageName));
        return "android".equals(permissionInfo.packageName) || OS_PKG_HWEXT.equals(permissionInfo.packageName);
    }

    private static boolean hasPermission(Context context, String str) {
        return context != null && (context.checkSelfPermission(str) == 0 || !isSystemExitPermission(context, str));
    }

    private static boolean hasGetPermission(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!hasPermission(context, str)) {
                return false;
            }
        }
        return true;
    }

    private static String getDeviceSn() {
        String str = Build.SERIAL;
        if (hasGetPermission(uwp.b(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            try {
                uwn.a(TAG, "get udid by getSerial");
                str = Build.getSerial();
            } catch (SecurityException unused) {
                uwn.e(TAG, "Get SN SecurityException");
            }
        }
        String trim = str.trim();
        return TextUtils.isEmpty(trim) ? "unknown" : trim;
    }

    private static String getAndroidId(Context context) {
        uwn.a(TAG, "get udid by androidId");
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private static String getUdidBySn() {
        uwn.a(TAG, "Get udid by sn");
        String deviceSn = getDeviceSn();
        return (TextUtils.isEmpty(deviceSn) || "unknown".equals(deviceSn)) ? "" : uwr.a(uwr.a(deviceSn));
    }

    private static String getSystemProperties(String str) {
        if (TextUtils.isEmpty(str)) {
            uwn.e(TAG, "Input parameter is empty.");
            return "";
        }
        try {
            Object invoke = Class.forName(CLASS_NAME_PROPERTIES).getDeclaredMethod("get", String.class, String.class).invoke(null, str, "");
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            uwn.e(TAG, "Get system properties failed.");
        }
        return "";
    }

    private static boolean isEmuiBuildExSupported() {
        try {
            Class.forName(CLASS_NAME_BUILDEX);
            return true;
        } catch (ClassNotFoundException unused) {
            uwn.a(TAG, "BuildEx is not supported.");
            return false;
        }
    }

    private static boolean checkCallerPermission(Context context, String str, String str2) {
        if (context == null) {
            uwn.e(TAG, "checkCallerPermission: context is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            uwn.e(TAG, "Can not get package manager");
            return false;
        }
        if (packageManager.checkPermission(str2, str) == 0) {
            return true;
        }
        uwn.e(TAG, "NO_PERMISSION : need permission " + str2);
        return false;
    }
}
