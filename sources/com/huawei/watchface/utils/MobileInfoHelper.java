package com.huawei.watchface.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.ao;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.dl;
import com.huawei.watchface.dq;
import com.huawei.watchface.dz;
import com.huawei.watchface.environment.Environment;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* loaded from: classes7.dex */
public class MobileInfoHelper {
    public static final String CODE_CN = "CN";
    private static final String DEFAULT_DEVICE_ID = "U9200";
    public static final String DEFAULT_VERSION_NAME = "EMUI1.0";
    private static final int MAGIC_6_0 = 33;
    public static final int RANDOM_SIZE = 32;
    private static final String TAG = "MobileInfoHelper";
    private static final String TYPE_IMEI = "type=0";
    private static final String TYPE_SEPARATOR = ":";
    private static final String TYPE_UDID = "type=9";
    private static final String TYPE_UUID = "type=6";
    private static Boolean isHos;
    private static volatile Boolean isUpMagic6;
    private static int magicApiLevel;
    private static String sVersionName;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final int f11195a = dq.a("ro.build.hw_emui_api_level", 0);
        public static final String b = dq.a("hw_sc.build.platform.version", "");
    }

    public static String getDeviceName() {
        String str = Build.MODEL;
        try {
            return URLEncoder.encode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            HwLog.e(TAG, HwLog.printException((Exception) e));
            return str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getVersion() {
        /*
            java.lang.String r0 = "MobileInfoHelper"
            java.lang.String r1 = com.huawei.watchface.utils.MobileInfoHelper.sVersionName
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto Ld
            java.lang.String r0 = com.huawei.watchface.utils.MobileInfoHelper.sVersionName
            goto L4a
        Ld:
            android.app.Application r1 = com.huawei.watchface.environment.Environment.getApplicationContext()     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            android.app.Application r2 = com.huawei.watchface.environment.Environment.getApplicationContext()     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            r3 = 16
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r3)     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            java.lang.String r1 = r1.versionName     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            com.huawei.watchface.utils.MobileInfoHelper.sVersionName = r1     // Catch: java.lang.RuntimeException -> L29 android.content.pm.PackageManager.NameNotFoundException -> L40
            r0 = r1
            goto L4a
        L29:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "runtimeException "
            r2.<init>(r3)
            java.lang.String r1 = com.huawei.watchface.utils.HwLog.printException(r1)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.huawei.watchface.utils.HwLog.e(r0, r1)
            goto L48
        L40:
            r1 = move-exception
            java.lang.String r1 = com.huawei.watchface.utils.HwLog.printException(r1)
            com.huawei.watchface.utils.HwLog.e(r0, r1)
        L48:
            java.lang.String r0 = "EMUI1.0"
        L4a:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L62
            java.lang.String r1 = "-"
            boolean r2 = r0.contains(r1)
            if (r2 == 0) goto L62
            java.lang.String[] r1 = r0.split(r1)
            int r2 = r1.length
            if (r2 <= 0) goto L62
            r0 = 0
            r0 = r1[r0]
        L62:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.MobileInfoHelper.getVersion():java.lang.String");
    }

    public static int getAppVersionCode(String str) {
        PackageManager packageManager = Environment.getApplicationContext().getPackageManager();
        if (packageManager == null) {
            HwLog.i(TAG, "getAppVersionCode pm is null");
            return 0;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo.applicationInfo == null) {
                HwLog.i(TAG, "getAppVersionCode applicationInfo is null");
                return 0;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            HwLog.e(TAG, "getAppVersionCode exception " + HwLog.printException((Exception) e));
            return 0;
        } catch (RuntimeException e2) {
            HwLog.e(TAG, "runtimeException " + HwLog.printException((Exception) e2));
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063 A[Catch: all -> 0x0082, TryCatch #0 {all -> 0x0082, blocks: (B:3:0x0003, B:5:0x0007, B:10:0x000d, B:11:0x005b, B:13:0x0063, B:15:0x0073, B:26:0x0029, B:19:0x0044), top: B:2:0x0003, inners: #4, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0073 A[Catch: all -> 0x0082, TRY_LEAVE, TryCatch #0 {all -> 0x0082, blocks: (B:3:0x0003, B:5:0x0007, B:10:0x000d, B:11:0x005b, B:13:0x0063, B:15:0x0073, B:26:0x0029, B:19:0x0044), top: B:2:0x0003, inners: #4, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isHarmonyOs() {
        /*
            java.lang.String r0 = "MobileInfoHelper"
            r1 = 0
            java.lang.Boolean r2 = com.huawei.watchface.utils.MobileInfoHelper.isHos     // Catch: java.lang.Throwable -> L82
            if (r2 == 0) goto Lc
            boolean r0 = r2.booleanValue()     // Catch: java.lang.Throwable -> L82
            return r0
        Lc:
            r2 = 1
            java.lang.String r3 = "com.huawei.system.BuildEx"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            java.lang.String r5 = "getOsBrand"
            java.lang.reflect.Method r3 = r3.getMethod(r5, r4)     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            r3.setAccessible(r2)     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            r5 = 0
            java.lang.Object r3 = r3.invoke(r5, r4)     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L28 java.lang.IllegalArgumentException -> L3f java.lang.IllegalAccessException -> L41 java.lang.ClassNotFoundException -> L43 java.lang.Throwable -> L82
            goto L5b
        L28:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = "isHarmonyOs exception!"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)     // Catch: java.lang.Throwable -> L82
            r4.append(r3)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L82
            com.huawei.watchface.utils.HwLog.e(r0, r3)     // Catch: java.lang.Throwable -> L82
            goto L59
        L3f:
            r3 = move-exception
            goto L44
        L41:
            r3 = move-exception
            goto L44
        L43:
            r3 = move-exception
        L44:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = "isHarmonyOs illegal exception!"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = com.huawei.watchface.utils.HwLog.printException(r3)     // Catch: java.lang.Throwable -> L82
            r4.append(r3)     // Catch: java.lang.Throwable -> L82
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L82
            com.huawei.watchface.utils.HwLog.e(r0, r3)     // Catch: java.lang.Throwable -> L82
        L59:
            java.lang.String r3 = ""
        L5b:
            java.lang.String r4 = "harmony"
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Throwable -> L82
            if (r3 == 0) goto L73
            java.lang.String r3 = "is HarmonyOs"
            com.huawei.watchface.utils.HwLog.i(r0, r3)     // Catch: java.lang.Throwable -> L82
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> L82
            com.huawei.watchface.utils.MobileInfoHelper.isHos = r2     // Catch: java.lang.Throwable -> L82
            boolean r0 = r2.booleanValue()     // Catch: java.lang.Throwable -> L82
            return r0
        L73:
            boolean r2 = isEmuiStrUp11o1()     // Catch: java.lang.Throwable -> L82
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> L82
            com.huawei.watchface.utils.MobileInfoHelper.isHos = r2     // Catch: java.lang.Throwable -> L82
            boolean r0 = r2.booleanValue()     // Catch: java.lang.Throwable -> L82
            return r0
        L82:
            java.lang.String r2 = "not HarmonyOs"
            com.huawei.watchface.utils.HwLog.i(r0, r2)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            com.huawei.watchface.utils.MobileInfoHelper.isHos = r0
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.MobileInfoHelper.isHarmonyOs():boolean");
    }

    private static boolean isEmuiStrUp11o1() {
        String a2 = dq.a("ro.build.version.emui", "");
        if (TextUtils.isEmpty(a2) || !a2.startsWith("EmotionUI_")) {
            HwLog.w(TAG, "emui is empty or value is error!");
            return false;
        }
        String[] split = SafeString.replace(a2, "EmotionUI_", "").split("\\.");
        if (split.length >= 2) {
            try {
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                if (parseInt >= 12 || (parseInt == 11 && parseInt2 >= 1)) {
                    return !TextUtils.isEmpty(a.b);
                }
                return false;
            } catch (NumberFormatException unused) {
                HwLog.w(TAG, "NumberFormatException");
            }
        }
        HwLog.w(TAG, "not emuiStrUp11o1");
        return false;
    }

    public static boolean isHarmony4Version() {
        return isHarmonyOs() && !TextUtils.isEmpty(a.b) && IntegerUtils.a(a.b.split("\\.")[0]) >= 4;
    }

    public static String getBuildNumber() {
        String buildExValue = getBuildExValue("DISPLAY");
        if (TextUtils.isEmpty(buildExValue) || "unknown".equals(buildExValue)) {
            buildExValue = Build.DISPLAY;
            if (TextUtils.isEmpty(buildExValue)) {
                HwLog.e(TAG, "Get both display failed!");
                return null;
            }
            HwLog.e(TAG, "Get huawei display failed, get original display success!");
        } else {
            HwLog.e(TAG, "Get huawei display success!");
        }
        return buildExValue.trim().replaceAll(" ", "");
    }

    private static String getBuildExValue(String str) {
        try {
            Field declaredField = Class.forName("com.huawei.system.BuildEx").getDeclaredField(str);
            declaredField.setAccessible(true);
            return (String) declaredField.get(null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
            HwLog.e(TAG, "getBuildExValue illegal exception!");
            return "";
        } catch (Exception unused2) {
            HwLog.e(TAG, "getBuildExValue exception!");
            return "";
        }
    }

    public static String generateDeviceIDWithSeparator() {
        return generateDeviceIDWithSeparator(":");
    }

    public static String generateDeviceIDWithSeparator(String str) {
        String udid = getUDID();
        if (!TextUtils.isEmpty(udid)) {
            return udid.concat(str).concat(TYPE_UDID);
        }
        return generateUUID().concat(str).concat(TYPE_UUID);
    }

    public static String getUDID() {
        String str = "";
        try {
            String c = ao.c(dz.c("phone_UDID"), "savePw");
            if (!TextUtils.isEmpty(c)) {
                HwLog.i(TAG, "get spUDID success");
                return c;
            }
            String str2 = (String) Class.forName("com.huawei.android.os.BuildEx").getMethod("getUDID", new Class[0]).invoke(null, new Object[0]);
            try {
                if (!TextUtils.isEmpty(str2)) {
                    dz.b("phone_UDID", ao.a(str2, "savePw"));
                    HwLog.i(TAG, "set UDID sp success");
                }
                HwLog.i(TAG, "getUDID success");
                return str2;
            } catch (AndroidRuntimeException e) {
                e = e;
                str = str2;
                HwLog.e(TAG, "getUDID getudid failed, RuntimeException is AndroidRuntimeException" + HwLog.printException((Exception) e));
                return str;
            } catch (ClassNotFoundException e2) {
                e = e2;
                str = str2;
                HwLog.e(TAG, "getUDID method invoke failed" + HwLog.printException((Exception) e));
                return str;
            } catch (IllegalAccessException e3) {
                e = e3;
                str = str2;
                HwLog.e(TAG, "getUDID method invoke failed : Illegal AccessException" + HwLog.printException((Exception) e));
                return str;
            } catch (IllegalArgumentException e4) {
                e = e4;
                str = str2;
                HwLog.e(TAG, "getUDID method invoke failed : Illegal ArgumentException" + HwLog.printException((Exception) e));
                return str;
            } catch (NoSuchMethodException e5) {
                e = e5;
                str = str2;
                HwLog.e(TAG, "getUDID method invoke failed : NoSuchMethodException" + HwLog.printException((Exception) e));
                return str;
            } catch (InvocationTargetException e6) {
                e = e6;
                str = str2;
                HwLog.e(TAG, "getUDID method invoke failed : InvocationTargetException" + HwLog.printException((Exception) e));
                return str;
            }
        } catch (AndroidRuntimeException e7) {
            e = e7;
        } catch (ClassNotFoundException e8) {
            e = e8;
        } catch (IllegalAccessException e9) {
            e = e9;
        } catch (IllegalArgumentException e10) {
            e = e10;
        } catch (NoSuchMethodException e11) {
            e = e11;
        } catch (InvocationTargetException e12) {
            e = e12;
        }
    }

    public static int getMagicApiLevel() {
        int i = magicApiLevel;
        if (i != 0) {
            return i;
        }
        try {
            Class<?> cls = Class.forName("com.hihonor.android.os.Build$VERSION");
            int i2 = cls.getField("MAGIC_SDK_INT").getInt(cls);
            magicApiLevel = i2;
            return i2;
        } catch (ClassNotFoundException e) {
            HwLog.w(TAG, "getMagicVersion ClassNotFoundException: " + HwLog.printException((Exception) e));
            return 0;
        } catch (IllegalAccessException e2) {
            HwLog.w(TAG, "getMagicVersion IllegalAccessException: " + HwLog.printException((Exception) e2));
            return 0;
        } catch (NoSuchFieldException e3) {
            HwLog.w(TAG, "getMagicVersion NoSuchFieldException: " + HwLog.printException((Exception) e3));
            return 0;
        }
    }

    public static String generateUUID() {
        return dl.a(32).toUpperCase(Locale.ENGLISH);
    }

    public static boolean isEmui11() {
        return a.f11195a >= 25;
    }

    public static boolean isHonorDevice() {
        return TextUtils.equals(Build.MANUFACTURER, "HONOR");
    }

    public static boolean isUpMagicUI6() {
        if (isUpMagic6 == null) {
            isUpMagic6 = Boolean.valueOf(isHonorDevice() && getMagicApiLevel() >= 33);
            HwLog.i(TAG, "isUpMagic6: " + isUpMagic6);
        }
        return isUpMagic6.booleanValue();
    }

    public static boolean isHuaweiDevice() {
        return TextUtils.equals(Build.MANUFACTURER, "HUAWEI");
    }

    public static boolean isAccessControlEleven() {
        return !HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea() && isEmui11();
    }
}
