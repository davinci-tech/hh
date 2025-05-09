package com.huawei.hms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;

/* loaded from: classes4.dex */
public class AGCUtils {
    private static String b(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get("com.huawei.hms.client.cpid")) == null) {
                HMSLog.i("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
                return "";
            }
            String valueOf = String.valueOf(obj);
            return valueOf.startsWith("cpid=") ? valueOf.substring(5) : valueOf;
        } catch (AndroidException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (RuntimeException e) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.", e);
            return "";
        }
    }

    private static boolean c(Context context) {
        return context.getPackageName().equals(HMSPackageManager.getInstance(context).getHMSPackageNameForMultiService());
    }

    public static String getAppId(Context context) {
        String str;
        if (context == null) {
            HMSLog.w("AGCUtils", "getAppId context is null");
            return "";
        }
        if (c(context)) {
            str = a(context, AgConnectInfo.AgConnectKey.APPLICATION_ID);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        } else {
            str = null;
        }
        try {
            AGConnectInstance aGConnectInstance = AGConnectInstance.getInstance();
            if (aGConnectInstance.getContext() != context) {
                aGConnectInstance = AGConnectInstance.buildInstance(new AGConnectOptionsBuilder().build(context));
            }
            str = aGConnectInstance.getOptions().getString(AgConnectInfo.AgConnectKey.APPLICATION_ID);
        } catch (NullPointerException unused) {
            HMSLog.e("AGCUtils", "Get appId with AGConnectServicesConfig failed");
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String a2 = a(context);
        return !TextUtils.isEmpty(a2) ? a2 : a(context, AgConnectInfo.AgConnectKey.APPLICATION_ID);
    }

    public static String getCpId(Context context) {
        String str;
        if (context == null) {
            HMSLog.w("AGCUtils", "getCpId context is null");
            return "";
        }
        if (c(context)) {
            return a(context, "client/cp_id");
        }
        try {
            AGConnectInstance aGConnectInstance = AGConnectInstance.getInstance();
            if (aGConnectInstance.getContext() != context) {
                aGConnectInstance = AGConnectInstance.buildInstance(new AGConnectOptionsBuilder().build(context));
            }
            str = aGConnectInstance.getOptions().getString("client/cp_id");
        } catch (NullPointerException unused) {
            HMSLog.e("AGCUtils", "Get cpid with AGConnectServicesConfig failed");
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String b = b(context);
        return !TextUtils.isEmpty(b) ? b : a(context, "client/cp_id");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "Get "
            java.lang.String r2 = "AGCUtils"
            r3 = 0
            com.huawei.agconnect.AGConnectOptionsBuilder r4 = new com.huawei.agconnect.AGConnectOptionsBuilder     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            r4.<init>()     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            android.content.res.Resources r5 = r7.getResources()     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            java.lang.String r6 = "agconnect-services.json"
            java.io.InputStream r3 = r5.open(r6)     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            r4.setInputStream(r3)     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            com.huawei.agconnect.AGConnectOptions r7 = r4.build(r7)     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            java.lang.String r7 = r7.getString(r8)     // Catch: java.lang.Throwable -> L26 java.lang.NullPointerException -> L28 java.io.IOException -> L41
            goto L5a
        L26:
            r7 = move-exception
            goto L7b
        L28:
            r7 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L26
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L26
            r4.append(r8)     // Catch: java.lang.Throwable -> L26
            java.lang.String r1 = " with AGConnectServicesConfig failed: "
            r4.append(r1)     // Catch: java.lang.Throwable -> L26
            r4.append(r7)     // Catch: java.lang.Throwable -> L26
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L26
            com.huawei.hms.support.log.HMSLog.e(r2, r7)     // Catch: java.lang.Throwable -> L26
            goto L59
        L41:
            r7 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L26
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L26
            r4.append(r8)     // Catch: java.lang.Throwable -> L26
            java.lang.String r1 = " failed: "
            r4.append(r1)     // Catch: java.lang.Throwable -> L26
            r4.append(r7)     // Catch: java.lang.Throwable -> L26
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L26
            com.huawei.hms.support.log.HMSLog.e(r2, r7)     // Catch: java.lang.Throwable -> L26
        L59:
            r7 = r0
        L5a:
            com.huawei.hms.utils.IOUtils.closeQuietly(r3)
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L64
            return r7
        L64:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "The "
            r7.<init>(r1)
            r7.append(r8)
            java.lang.String r8 = " is null."
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.huawei.hms.support.log.HMSLog.e(r2, r7)
            return r0
        L7b:
            com.huawei.hms.utils.IOUtils.closeQuietly(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.util.AGCUtils.a(android.content.Context, java.lang.String):java.lang.String");
    }

    private static String a(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.client.appid")) != null) {
                String valueOf = String.valueOf(obj);
                return valueOf.startsWith("appid=") ? valueOf.substring(6) : valueOf;
            }
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (AndroidException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (RuntimeException e) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.", e);
            return "";
        }
    }
}
