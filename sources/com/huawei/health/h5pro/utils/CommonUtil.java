package com.huawei.health.h5pro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.gson.Gson;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebViewActivity;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.exception.H5ProException;
import com.huawei.health.h5pro.ext.H5ProResidentExtManager;
import com.huawei.health.h5pro.ext.hostapp.H5ProHostAppRuntimeApi;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes3.dex */
public class CommonUtil {
    public static boolean c = false;

    public static Object[] transferParamTypes(String[] strArr, Method method) {
        if (method == null) {
            throw new H5ProException("method is null");
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (strArr.length != parameterTypes.length) {
            throw new H5ProException("The number of parameters does not match the number of parameter types.");
        }
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (parameterTypes[i].equals(String.class)) {
                objArr[i] = strArr[i];
            } else {
                objArr[i] = new Gson().fromJson(strArr[i], (Class) parameterTypes[i]);
            }
        }
        return objArr;
    }

    public static void setTestVersion(boolean z) {
        c = z;
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void preProcessOption(H5ProLaunchOption h5ProLaunchOption) {
        if (h5ProLaunchOption == null || !h5ProLaunchOption.isEnableSlowWholeDocumentDraw()) {
            return;
        }
        WebView.enableSlowWholeDocumentDraw();
    }

    public static H5ProLaunchOption parseQueryOption(String str, H5ProLaunchOption h5ProLaunchOption) {
        if (TextUtils.isEmpty(str) || !str.contains("?")) {
            return h5ProLaunchOption;
        }
        H5ProLaunchOption.Builder builder = h5ProLaunchOption == null ? new H5ProLaunchOption.Builder() : new H5ProLaunchOption.Builder().copy(h5ProLaunchOption);
        for (String str2 : str.split("\\?")[1].split("&")) {
            String[] split = str2.split("=");
            if (split.length == 2) {
                buildParam(builder, split[0], split[1]);
            }
        }
        return builder.build();
    }

    public static boolean isTestVersion() {
        return c;
    }

    public static boolean isTelephonyEnable(Context context) {
        Object systemService = context.getSystemService("phone");
        return ((systemService instanceof TelephonyManager) && ((TelephonyManager) systemService).getPhoneType() == 0) ? false : true;
    }

    public static boolean isShouldSelfProtection(Context context, String str, boolean z) {
        return GeneralUtil.isShouldSelfProtection(context, str, context.getResources().getStringArray(R.array._2130968696_res_0x7f040078), z);
    }

    public static boolean isPixel() {
        return Build.MANUFACTURER.equalsIgnoreCase("Google");
    }

    public static boolean isPad(Context context) {
        if (TextUtils.equals("tablet", SystemProperties.getString("ro.build.characteristics", ""))) {
            return true;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature("com.huawei.software.features.pad") || packageManager.hasSystemFeature("com.hihonor.software.features.pad")) {
            return true;
        }
        return !isTelephonyEnable(context);
    }

    public static boolean isNumber(String str) {
        return !TextUtils.isEmpty(str) && str.matches("^[0-9]+(.[0-9]+)?$");
    }

    public static boolean isNearZero(double d) {
        return Math.abs(d) <= 1.0E-6d;
    }

    public static <K, V> boolean isMapEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static boolean isFlyme() {
        return Build.MANUFACTURER.equalsIgnoreCase("meizu");
    }

    public static String getTopActivityH5Flag() {
        Activity topActivity;
        H5ProHostAppRuntimeApi hostAppRuntimeApi = H5ProResidentExtManager.getHostAppRuntimeApi();
        return (hostAppRuntimeApi == null || (topActivity = hostAppRuntimeApi.getTopActivity()) == null || !(topActivity instanceof H5ProWebViewActivity)) ? "" : topActivity.getIntent().getStringExtra("h5Flag");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getStatusBarHeight(android.content.Context r4) {
        /*
            java.lang.String r0 = "status_bar_height"
            java.lang.String r1 = "com.android.internal.R$dimen"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L28
            java.lang.Object r2 = r1.newInstance()     // Catch: java.lang.Throwable -> L28
            java.lang.reflect.Field r1 = r1.getField(r0)     // Catch: java.lang.Throwable -> L28
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L33
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L28
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L28
            android.content.res.Resources r2 = r4.getResources()     // Catch: java.lang.Throwable -> L28
            int r1 = r2.getDimensionPixelSize(r1)     // Catch: java.lang.Throwable -> L28
            goto L34
        L28:
            java.lang.String r1 = "get StatusBar Height Exception"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            java.lang.String r2 = "H5PRO_CommonUtil"
            com.huawei.health.h5pro.utils.LogUtil.e(r2, r1)
        L33:
            r1 = 0
        L34:
            if (r1 != 0) goto L48
            android.content.res.Resources r4 = r4.getResources()
            java.lang.String r2 = "dimen"
            java.lang.String r3 = "android"
            int r0 = r4.getIdentifier(r0, r2, r3)
            if (r0 <= 0) goto L48
            int r1 = r4.getDimensionPixelSize(r0)
        L48:
            if (r1 != 0) goto L4c
            r1 = 137(0x89, float:1.92E-43)
        L4c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.utils.CommonUtil.getStatusBarHeight(android.content.Context):int");
    }

    public static String getProviderAuthority(Context context) {
        return context.getPackageName() + ".fileprovider";
    }

    public static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            LogUtil.w("H5PRO_CommonUtil", "getClass ex=", e.getMessage());
            return null;
        }
    }

    public static String getAppId(H5ProInstance h5ProInstance) {
        if (h5ProInstance != null) {
            return getAppId(h5ProInstance.getAppInfo());
        }
        LogUtil.w("H5PRO_CommonUtil", "getAppId h5ProInstance null");
        return "DEFAULT_APP_ID";
    }

    public static String getAppId(H5ProAppInfo h5ProAppInfo) {
        if (h5ProAppInfo != null) {
            return !TextUtils.isEmpty(h5ProAppInfo.getPkgName()) ? h5ProAppInfo.getPkgName() : !TextUtils.isEmpty(h5ProAppInfo.getAppId()) ? h5ProAppInfo.getAppId() : "DEFAULT_APP_ID";
        }
        LogUtil.w("H5PRO_CommonUtil", "getAppId h5ProAppInfo null");
        return "DEFAULT_APP_ID";
    }

    public static int getActivityWindowMode(Activity activity) {
        if (activity == null || !activity.isInMultiWindowMode()) {
            LogUtil.i("H5PRO_CommonUtil", "getAppWinMode: 0");
            return 0;
        }
        if (Build.VERSION.SDK_INT < 29) {
            LogUtil.i("H5PRO_CommonUtil", "getAppWinMode: 2");
            return 2;
        }
        try {
            Method method = Class.forName("com.huawei.android.app.ActivityManagerEx").getMethod("getActivityWindowMode", Activity.class);
            if (method != null) {
                int intValue = ((Integer) method.invoke(null, activity)).intValue();
                LogUtil.i("H5PRO_CommonUtil", "getAppWinMode: " + intValue);
                if (intValue == 100) {
                    return 2;
                }
                return intValue == 101 ? 3 : 1;
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("H5PRO_CommonUtil", "getActivityWindowMode exception: " + e.getMessage());
        }
        return 2;
    }

    public static int getActivityStartFlag(H5ProLaunchOption h5ProLaunchOption, String str) {
        if (h5ProLaunchOption == null) {
            return 268435456;
        }
        int startMode = h5ProLaunchOption.getStartMode();
        if (startMode == 1) {
            return AppRouterExtras.COLDSTART;
        }
        if ((startMode != 2 && startMode != 3) || !str.equals(getTopActivityH5Flag())) {
            return h5ProLaunchOption.getActivityStartFlag();
        }
        if (startMode == 2) {
            return AppRouterExtras.COLDSTART;
        }
        return 603979776;
    }

    public static String filterUrl(String str) {
        return TextUtils.isEmpty(str) ? "" : !str.contains("#") ? str : str.split("#")[0];
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LogUtil.e("H5PRO_CommonUtil", "closeStream: " + e.getMessage());
            }
        }
    }

    public static void buildParam(H5ProLaunchOption.Builder builder, String str, String str2) {
        int i;
        if (!"startActivityFlag".equals(str)) {
            if (Analyzer.isGlobalParamKeys(str)) {
                builder.addGlobalBiParam(str, str2);
            }
        } else {
            if ("SINGLE_TOP".equals(str2)) {
                i = 536870912;
            } else if (!"SINGLE_TASK".equals(str2)) {
                return;
            } else {
                i = 603979776;
            }
            builder.setActivityStartFlag(i);
        }
    }
}
