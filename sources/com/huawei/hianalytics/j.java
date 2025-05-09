package com.huawei.hianalytics;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Binder;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.hihonor.android.os.Build;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DaoManager;
import com.huawei.hianalytics.core.transport.TransportHandlerFactory;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.secure.android.common.util.SafeBase64;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static String f3877a;

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0052, code lost:
    
        if (android.text.TextUtils.equals("uuid_metric_info", r8) != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject a(com.huawei.hianalytics.x r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "MRUtils"
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = r5.e     // Catch: org.json.JSONException -> L65
            java.lang.String r3 = "mc_duration"
            r1.put(r3, r2)     // Catch: org.json.JSONException -> L65
            long r2 = r5.f101a     // Catch: org.json.JSONException -> L65
            java.lang.String r4 = "play_start_time"
            r1.put(r4, r2)     // Catch: org.json.JSONException -> L65
            long r2 = r5.b     // Catch: org.json.JSONException -> L65
            java.lang.String r4 = "play_duration"
            r1.put(r4, r2)     // Catch: org.json.JSONException -> L65
            int r2 = r5.f3960a     // Catch: org.json.JSONException -> L65
            java.lang.String r3 = "favorite_state"
            r1.put(r3, r2)     // Catch: org.json.JSONException -> L65
            java.lang.String r5 = r5.g     // Catch: org.json.JSONException -> L65
            java.lang.String r2 = "package_name"
            r1.put(r2, r5)     // Catch: org.json.JSONException -> L65
            r1.put(r6, r7)     // Catch: org.json.JSONException -> L65
            com.huawei.hianalytics.w r5 = com.huawei.hianalytics.w.a()     // Catch: org.json.JSONException -> L65
            int r5 = r5.f90a     // Catch: org.json.JSONException -> L65
            r6 = 1
            if (r5 == r6) goto L5b
            r6 = 2
            java.lang.String r7 = "uuid_metric_info"
            if (r5 == r6) goto L4e
            r6 = 3
            if (r5 == r6) goto L47
            r6 = 4
            if (r5 == r6) goto L54
            java.lang.String r5 = "error idFlag"
            com.huawei.hianalytics.core.log.HiLog.w(r0, r5)     // Catch: org.json.JSONException -> L65
            goto L6a
        L47:
            boolean r5 = android.text.TextUtils.equals(r7, r8)     // Catch: org.json.JSONException -> L65
            if (r5 != 0) goto L5b
            goto L54
        L4e:
            boolean r5 = android.text.TextUtils.equals(r7, r8)     // Catch: org.json.JSONException -> L65
            if (r5 == 0) goto L5b
        L54:
            java.lang.String r5 = f()     // Catch: org.json.JSONException -> L65
            java.lang.String r6 = "udid"
            goto L61
        L5b:
            java.lang.String r5 = m555a(r8)     // Catch: org.json.JSONException -> L65
            java.lang.String r6 = "uuid"
        L61:
            r1.put(r6, r5)     // Catch: org.json.JSONException -> L65
            goto L6a
        L65:
            java.lang.String r5 = "BMID JSONException"
            com.huawei.hianalytics.core.log.HiLog.w(r0, r5)
        L6a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.j.a(com.huawei.hianalytics.x, java.lang.String, java.lang.String, java.lang.String):org.json.JSONObject");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032 A[Catch: all -> 0x004e, TryCatch #0 {all -> 0x004e, blocks: (B:3:0x0002, B:5:0x000d, B:8:0x0014, B:11:0x001e, B:13:0x0024, B:14:0x002c, B:16:0x0032, B:18:0x004a), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b() {
        /*
            java.lang.String r0 = ""
            com.huawei.hianalytics.i0 r1 = com.huawei.hianalytics.k0.a()     // Catch: java.lang.Throwable -> L4e
            r2 = 1
            java.util.List r1 = r1.a(r2)     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L2b
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L4e
            if (r2 == 0) goto L14
            goto L2b
        L14:
            r2 = 0
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L4e
            android.app.ActivityManager$RunningTaskInfo r1 = (android.app.ActivityManager.RunningTaskInfo) r1     // Catch: java.lang.Throwable -> L4e
            if (r1 != 0) goto L1e
            goto L2b
        L1e:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L4e
            r3 = 29
            if (r2 < r3) goto L2b
            android.app.ActivityManager$TaskDescription r1 = r1.taskDescription     // Catch: java.lang.Throwable -> L4e
            java.lang.String r1 = r1.getLabel()     // Catch: java.lang.Throwable -> L4e
            goto L2c
        L2b:
            r1 = r0
        L2c:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L4e
            if (r2 == 0) goto L4c
            com.huawei.hianalytics.i0 r1 = com.huawei.hianalytics.k0.a()     // Catch: java.lang.Throwable -> L4e
            android.content.pm.ActivityInfo r1 = r1.a()     // Catch: java.lang.Throwable -> L4e
            android.content.Context r2 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()     // Catch: java.lang.Throwable -> L4e
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch: java.lang.Throwable -> L4e
            java.lang.CharSequence r1 = r1.loadLabel(r2)     // Catch: java.lang.Throwable -> L4e
            boolean r2 = r1 instanceof java.lang.String     // Catch: java.lang.Throwable -> L4e
            if (r2 == 0) goto L4d
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L4e
        L4c:
            r0 = r1
        L4d:
            return r0
        L4e:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getLabel ex: "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "DcUtil"
            com.huawei.hianalytics.core.log.HiLog.i(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.j.b():java.lang.String");
    }

    public static String c() {
        return i.a().m550a().f;
    }

    public static String d() {
        String str = i.a().m550a().o;
        return str == null ? "" : str;
    }

    public static String e() {
        return i.a().m550a().t;
    }

    /* renamed from: f, reason: collision with other method in class */
    public static boolean m573f() {
        l m550a = i.a().m550a();
        if (TextUtils.isEmpty(m550a.u)) {
            m550a.u = c("hw_sc.build.platform.version", "");
        }
        return (TextUtils.isEmpty(m550a.u) ^ true) || m569c();
    }

    public static boolean g() {
        return System.currentTimeMillis() - a("global_v2", "lastMetricReportTime", 0L) < w.a().f91a;
    }

    public static String c(Context context) {
        synchronized (j.class) {
            String str = i.a().m550a().j;
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            String str2 = "";
            if (context == null) {
                HiLog.e("SharedPreUtils", "context is null");
                str2 = "";
            } else {
                if (!TextUtils.isEmpty("uuid") && !TextUtils.isEmpty("global_v2")) {
                    SharedPreferences m551a = m551a(context, m554a(context, "global_v2"));
                    if (m551a != null) {
                        str2 = m551a.getString("uuid", "");
                    }
                }
                HiLog.e("SharedPreUtils", "spName or spKey is empty");
            }
            if (!TextUtils.isEmpty(str2) && str2.length() > 32) {
                String decrypt = AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Uuid_Sp_Key", str2);
                if (!TextUtils.isEmpty(decrypt)) {
                    a(context, "global_v2", "uuid", decrypt);
                    str2 = decrypt;
                }
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = UUID.randomUUID().toString().replace(Constants.LINK, "");
                i.a().m550a().j = str2;
                a(context, "global_v2", "uuid", str2);
            } else {
                i.a().m550a().j = str2;
            }
            return str2;
        }
    }

    /* renamed from: d, reason: collision with other method in class */
    public static boolean m571d() {
        l m550a = i.a().m550a();
        if (TextUtils.isEmpty(m550a.v)) {
            m550a.v = c("ro.build.hw_emui_api_level", "");
        }
        try {
        } catch (Exception unused) {
            HiLog.w("DeviceUtil", "Exception");
        }
        return Integer.parseInt(m550a.v) >= 29;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(java.lang.String r4, java.lang.String r5) {
        /*
            com.huawei.hianalytics.a1 r0 = a(r4, r5)
            java.lang.String r1 = ""
            if (r0 == 0) goto L5a
            boolean r0 = r0.f15c
            if (r0 != 0) goto Ld
            goto L5a
        Ld:
            com.huawei.hianalytics.a1 r0 = a(r4, r5)
            if (r0 == 0) goto L16
            java.lang.String r0 = r0.f7a
            goto L17
        L16:
            r0 = r1
        L17:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L4b
            java.lang.String r0 = "global_v2"
            java.lang.String r1 = a(r0, r4, r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L4a
            int r2 = r1.length()
            r3 = 32
            if (r2 <= r3) goto L41
            java.lang.String r2 = "HiAnalytics_Sdk_Uuid_Sp_Key"
            java.lang.String r2 = com.huawei.hianalytics.core.common.AesGcmKsWrapper.decrypt(r2, r1)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L41
            m559a(r0, r4, r2)
            r1 = r2
        L41:
            com.huawei.hianalytics.a1 r4 = a(r4, r5)
            if (r4 == 0) goto L4c
            r4.f7a = r1
            goto L4c
        L4a:
            r0 = r1
        L4b:
            r1 = r0
        L4c:
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L5a
            android.content.Context r4 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()
            java.lang.String r1 = c(r4)
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.j.b(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: c, reason: collision with other method in class */
    public static boolean m569c() {
        l m550a = i.a().m550a();
        if (TextUtils.isEmpty(m550a.f49a)) {
            m550a.f49a = c("ro.build.version.emui", "");
        }
        return !TextUtils.isEmpty(m550a.f49a);
    }

    public static String b(String str, String str2, String str3) {
        Object obj = null;
        try {
            obj = Class.forName(str).getMethod("get", String.class, String.class).invoke(null, str2, str3);
        } catch (Throwable unused) {
            HiLog.e("DeviceUtil", "invokeStaticFun fail");
        }
        return obj != null ? (String) obj : str3;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static void m565b() {
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_metric_mc");
        if (instanceByTag == null) {
            HiLog.w("MRUtils", "metric instance is null");
            return;
        }
        String a2 = a("global_v2", "sdkReportUrl", "");
        if (!TextUtils.isEmpty(a2)) {
            i.a().a("ha_metric_mc").b.f14c = a2;
        }
        instanceByTag.onReport(0);
        m558a("global_v2", "lastMetricReportTime", System.currentTimeMillis());
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m560a() {
        String str = i.a().m550a().r;
        if (TextUtils.isEmpty(str)) {
            str = a("Privacy_MY", "public_key_time_interval", "");
            i.a().m550a().r = str;
        }
        String str2 = i.a().m550a().s;
        if (TextUtils.isEmpty(str2)) {
            str2 = a("Privacy_MY", "public_key_time_last", "");
            i.a().m550a().s = str2;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (System.currentTimeMillis() - Long.parseLong(str2) <= Integer.parseInt(str)) {
                    return false;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return true;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m568b(String str, String str2, String str3) {
        JSONArray optJSONArray;
        try {
            if (TextUtils.isEmpty(str3)) {
                HiLog.i("RUtils", "url is null");
                return false;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JsbMapKeyNames.H5_USER_ID, "");
            jSONObject.put("userAttribute", new JSONObject());
            Response execute = TransportHandlerFactory.create(str3, null, jSONObject.toString().getBytes(StandardCharsets.UTF_8), 1).execute();
            if (execute.getHttpCode() != 200) {
                return false;
            }
            try {
                JSONObject optJSONObject = new JSONObject(execute.getContent()).optJSONObject("result");
                if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("featureConfigValues")) != null && optJSONArray.length() > 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null && TextUtils.equals(optJSONObject2.optString("paramKey"), str2)) {
                            String optString = optJSONObject2.optString("paramValue");
                            if (TextUtils.isEmpty(optString)) {
                                return false;
                            }
                            m559a("global_v2", str, optString);
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        } catch (Throwable th) {
            HiLog.i("RUtils", "requestConfig fail: " + th.getMessage());
            return false;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m562a(Context context, String str) {
        if (context == null) {
            return false;
        }
        int callingPid = Binder.getCallingPid();
        int callingUid = Binder.getCallingUid();
        if (context.checkPermission(str, callingPid, callingUid) == -1) {
            return false;
        }
        String permissionToOp = AppOpsManager.permissionToOp(str);
        if (permissionToOp != null) {
            String packageName = context.getPackageName();
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(callingUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return false;
                }
                packageName = packagesForUid[0];
            }
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
            if (appOpsManager == null || appOpsManager.noteProxyOpNoThrow(permissionToOp, packageName) != 0) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m557a() {
        d1 a2;
        d1 a3 = k.a().a("ha_metric_mc");
        if (a3 == null) {
            a2 = new d1("ha_metric_mc");
            if (a2.f27a == null) {
                HiLog.e("HACU", "metric instance is null, create failed");
                return;
            }
            HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("Analytics_Kit_Tag");
            String collectUrl = instanceByTag == null ? "" : instanceByTag.getCollectUrl();
            if (TextUtils.isEmpty(collectUrl)) {
                return;
            }
            a2.b(new HiAnalyticsConfig.Builder().setCollectURL(collectUrl).setAutoReportThresholdSize(100).build());
            d1 a4 = k.a().a("ha_metric_mc", a2);
            if (a4 != null) {
                a2 = a4;
            }
        } else {
            a2 = k.a().a("ha_metric_mc", a3);
        }
        if (a2 == null) {
            HiLog.e("HACU", "metricInstance is null");
        } else {
            a2.setAppid("com.huawei.hianalytics");
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m556a(String str, String str2) {
        a1 a2 = a(str, str2);
        return a2 != null ? a2.f16d : "";
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m554a(Context context, String str) {
        String str2 = i.a().m550a().n;
        if (TextUtils.isEmpty(str2)) {
            str2 = context.getPackageName();
        }
        return "hianalytics_" + str + "_" + str2;
    }

    public static long a(Context context, String str) {
        String str2 = m554a(context, str) + WatchFaceConstant.XML_SUFFIX;
        return new File(context.getFilesDir(), "../shared_prefs/" + str2).length();
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m563a(String str) {
        if (!s.a().f80c || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Iterator<Pattern> it = s.a().f73a.iterator();
            while (it.hasNext()) {
                if (it.next().matcher(str).matches()) {
                    return true;
                }
            }
        } catch (Throwable th) {
            HiLog.i("DcUtil", "isLiteAppPage fail: " + th.getMessage());
        }
        return false;
    }

    public static void a(String str, HiAnalyticsConfig hiAnalyticsConfig) {
        d1 a2 = k.a().a("ha_metric_mc");
        if (a2 == null) {
            return;
        }
        a2.f27a.clearCacheDataByTag();
        DaoManager.getInstance().deleteAllInfo();
        a2.b(new HiAnalyticsConfig.Builder().setCollectURL(str).setHttpHeader(hiAnalyticsConfig.f3894a.f8a).setAutoReportThresholdSize(100).build());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (r3.length() > 256) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r3, java.lang.String r4, java.lang.String r5) {
        /*
            com.huawei.hianalytics.a1 r0 = a(r4, r5)
            java.lang.String r1 = ""
            if (r0 == 0) goto Lb
            java.lang.String r0 = r0.f11b
            goto Lc
        Lb:
            r0 = r1
        Lc:
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L1b
            com.huawei.hianalytics.a1 r3 = a(r4, r5)
            if (r3 == 0) goto L1a
            java.lang.String r1 = r3.f11b
        L1a:
            return r1
        L1b:
            com.huawei.hianalytics.i r4 = com.huawei.hianalytics.i.a()
            com.huawei.hianalytics.l r4 = r4.m550a()
            java.lang.String r5 = r4.m
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L6b
            r5 = 256(0x100, float:3.59E-43)
            android.content.pm.PackageManager r0 = r3.getPackageManager()     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Throwable -> L56
            r2 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo(r3, r2)     // Catch: java.lang.Throwable -> L56
            if (r3 == 0) goto L5d
            android.os.Bundle r0 = r3.metaData     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L5d
            android.os.Bundle r3 = r3.metaData     // Catch: java.lang.Throwable -> L56
            java.lang.String r0 = "CHANNEL"
            java.lang.Object r3 = r3.get(r0)     // Catch: java.lang.Throwable -> L56
            if (r3 == 0) goto L5d
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L56
            int r0 = r3.length()     // Catch: java.lang.Throwable -> L56
            if (r0 <= r5) goto L5f
            goto L5d
        L56:
            java.lang.String r3 = "DeviceIdUtils"
            java.lang.String r0 = "get channel error"
            com.huawei.hianalytics.core.log.HiLog.e(r3, r0)
        L5d:
            java.lang.String r3 = "Unknown"
        L5f:
            java.lang.String r0 = "channel"
            boolean r5 = a(r0, r3, r5)
            if (r5 != 0) goto L68
            goto L69
        L68:
            r1 = r3
        L69:
            r4.m = r1
        L6b:
            java.lang.String r3 = r4.m
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.j.a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static void a(int i, final HiAnalyticsConfig hiAnalyticsConfig) {
        if (hiAnalyticsConfig == null || hiAnalyticsConfig.f3894a == null || i != 0) {
            return;
        }
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("Analytics_Kit_Tag");
        final String collectUrl = instanceByTag == null ? "" : instanceByTag.getCollectUrl();
        if (TextUtils.isEmpty(collectUrl)) {
            return;
        }
        TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.j$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                j.a(collectUrl, hiAnalyticsConfig);
            }
        });
    }

    public static List<String> a(List<String> list) {
        String str;
        String str2 = w.a().f92a;
        if (TextUtils.isEmpty(str2)) {
            return new ArrayList();
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(it.next()));
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Music", jSONArray);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("flowId", "");
            jSONObject2.put("bId", "");
            jSONObject2.put("uuId", UUID.randomUUID().toString());
            jSONObject2.put("dataFormat", "1");
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("scenarioId", "music101");
            jSONObject3.put("content", jSONArray2);
            String b = b(SafeBase64.encodeToString(jSONObject3.toString().getBytes(StandardCharsets.UTF_8), 0));
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("data", b);
            jSONObject4.put("version", "1.0");
            jSONObject4.put("meta", jSONObject2);
            str = jSONObject4.toString();
        } catch (JSONException e) {
            HiLog.e("RUtils", "JsonException" + e.getMessage());
            str = "";
        }
        Response execute = TransportHandlerFactory.create(str2 + "/tag/v1/service", null, str.getBytes(StandardCharsets.UTF_8), 1).execute();
        ArrayList arrayList = new ArrayList();
        if (execute.getHttpCode() == 200) {
            try {
                JSONObject optJSONObject = new JSONObject(execute.getContent()).optJSONObject("result");
                if (optJSONObject == null) {
                    HiLog.e("RUtils", "result is null!");
                } else if (optJSONObject.optInt("code", -1) != 0) {
                    HiLog.i("RUtils", "request failed");
                } else {
                    String optString = optJSONObject.optString("content");
                    if (TextUtils.isEmpty(optString)) {
                        HiLog.d("RUtils", "content is null");
                    } else {
                        JSONArray optJSONArray = new JSONArray(new String(SafeBase64.decode(b(optString), 0), StandardCharsets.UTF_8)).optJSONObject(0).optJSONArray("Music");
                        if (optJSONArray != null && optJSONArray.length() != 0) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject jSONObject5 = optJSONArray.getJSONObject(i);
                                JSONObject jSONObject6 = new JSONObject();
                                jSONObject6.put("item_id", jSONObject5.optString("item_id", ""));
                                jSONObject6.put("content_code", jSONObject5.optString("content_code", ""));
                                jSONObject6.put("metric_tag", new JSONObject(jSONObject5.optString("tag_info", "")).optString("tag", ""));
                                arrayList.add(jSONObject6.toString());
                            }
                        }
                    }
                }
            } catch (JSONException unused) {
                HiLog.w("RUtils", "HR JsonException");
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.database.sqlite.SQLiteDatabase r16) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.j.a(android.database.sqlite.SQLiteDatabase):void");
    }

    public static boolean h() {
        String c = c("ro.build.characteristics", "");
        return "default".equals(c) || "tablet".equals(c);
    }

    public static String f() {
        try {
            return (String) (m572e() ? Class.forName("com.hihonor.android.os.Build").getMethod("getUDID", new Class[0]) : Class.forName("com.huawei.android.os.BuildEx").getMethod("getUDID", new Class[0])).invoke(null, new Object[0]);
        } catch (Exception unused) {
            HiLog.e("DeviceIdUtils", "getUDID failed");
            return "";
        }
    }

    /* renamed from: e, reason: collision with other method in class */
    public static boolean m572e() {
        try {
            if (Build.MANUFACTURER.equalsIgnoreCase("HONOR") && Build.VERSION.SDK_INT >= 31) {
                if (Build.VERSION.MAGIC_SDK_INT >= 33) {
                    return true;
                }
            }
        } catch (Throwable th) {
            HiLog.e("DeviceUtil", "isHonor6UpPhone error:" + th.getClass());
        }
        return false;
    }

    /* renamed from: c, reason: collision with other method in class */
    public static boolean m570c(Context context) {
        PowerManager powerManager;
        if (context == null || (powerManager = (PowerManager) context.getSystemService("power")) == null) {
            return true;
        }
        try {
            return powerManager.isInteractive();
        } catch (Exception unused) {
            HiLog.w("DeviceUtil", "isScreenInteractive has exception");
            return true;
        }
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String b = b("android.os.SystemProperties", str, str2);
        if (TextUtils.isEmpty(b)) {
            return b(m572e() ? "com.hihonor.android.os.SystemPropertiesEx" : "com.huawei.android.os.SystemPropertiesEx", str, str2);
        }
        return b;
    }

    public static boolean b(Context context, String str) {
        File file = new File(context.getFilesDir(), "../shared_prefs/" + m554a(context, str) + WatchFaceConstant.XML_SUFFIX);
        if (file.exists() && file.delete()) {
            HiLog.w("SharedPreUtils", "delete sp file");
        }
        return file.exists() && file.delete();
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m567b(Context context) {
        NetworkCapabilities networkCapabilities;
        if (!m562a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            HiLog.sw("DeviceUtil", "no network permission!");
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) != null) {
                if (!networkCapabilities.hasTransport(0) && !networkCapabilities.hasTransport(1) && !networkCapabilities.hasTransport(3) && !networkCapabilities.hasTransport(7) && !networkCapabilities.hasTransport(4)) {
                    if (!networkCapabilities.hasCapability(16)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Throwable unused) {
            HiLog.e("DeviceUtil", "cannot get network state, ensure permission in the manifest");
        }
        return false;
    }

    /* renamed from: b, reason: collision with other method in class */
    public static boolean m566b() {
        String b = b("com.huawei.android.os.SystemPropertiesEx", "ro.product.locale.region", "");
        if (!TextUtils.isEmpty(b)) {
            return "cn".equalsIgnoreCase(b);
        }
        String b2 = b("com.huawei.android.os.SystemPropertiesEx", SystemPropertyValues.PROP_LOCALE_KEY, "");
        if (!TextUtils.isEmpty(b2)) {
            return b2.toLowerCase(Locale.ENGLISH).contains("cn");
        }
        String country = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(country)) {
            return false;
        }
        return "cn".equalsIgnoreCase(country);
    }

    public static String b(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v8 */
    public static String b(Context context) {
        if (context == 0) {
            return "";
        }
        if (!TextUtils.isEmpty(d())) {
            return d();
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                context = Application.getProcessName();
            } else {
                try {
                    String str = (String) Class.forName("android.app.ActivityThread").getMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
                    if (TextUtils.isEmpty(str)) {
                        context = context.getPackageName();
                        context = context;
                    } else {
                        context = str;
                    }
                } catch (Throwable unused) {
                    context = context.getPackageName();
                }
            }
        } catch (Exception unused2) {
            HiLog.e("HAUtils", "get process name error");
            context = context.getPackageName();
        }
        return TextUtils.isEmpty(context) ? "" : context;
    }

    public static boolean a(Map<String, String> map) {
        String str;
        if (map == null || map.isEmpty()) {
            str = "onEvent size empty";
        } else if (map.size() != 1 || (map.get("constants") == null && map.get("_constants") == null)) {
            try {
                if (map.size() <= 2048 && map.toString().length() <= 204800) {
                    return true;
                }
                HiLog.w("ParamCheckUtils", "map data is too big. size: " + map.size() + ", length: " + map.toString().length());
                return false;
            } catch (Exception unused) {
                str = "checkMap failed";
            }
        } else {
            str = "the key can't be constants or _constants";
        }
        HiLog.w("ParamCheckUtils", str);
        return false;
    }

    public static boolean a(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            HiLog.e("SharedPreUtils", "spName or spKey is empty");
            return z;
        }
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getBoolean(str2, z) : z;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m564a(String str, String str2, String str3) {
        StringBuilder sb;
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder("parameter is null. parameter:");
        } else {
            if (Pattern.compile(str3).matcher(str2).matches()) {
                return true;
            }
            sb = new StringBuilder("check parameter failure! parameter:");
        }
        sb.append(str);
        HiLog.w("ParamCheckUtils", sb.toString());
        return false;
    }

    public static boolean a(String str, String str2, int i) {
        StringBuilder sb;
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder("parameter is empty: ");
        } else {
            if (str2.length() <= i) {
                return true;
            }
            sb = new StringBuilder("parameter length error: ");
        }
        sb.append(str);
        HiLog.w("ParamCheckUtils", sb.toString());
        return false;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m561a(Context context) {
        if (context == null) {
            return false;
        }
        return TextUtils.equals(context.getPackageName(), System.getProperty("debug.huawei.hianalytics.app"));
    }

    public static boolean a(long j) {
        return System.currentTimeMillis() - j >= TimeUnit.DAYS.toMillis(7L);
    }

    public static void a(List<String> list, List<String> list2) {
        if (!list.isEmpty()) {
            n.a(EnvUtils.getAppContext()).deleteMcInfo(list);
        }
        if (list2.isEmpty()) {
            return;
        }
        n.a(EnvUtils.getAppContext()).deleteMcTag(list2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m559a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            HiLog.e("SharedPreUtils", "spName or spKey is empty");
            return;
        }
        SharedPreferences a2 = a(str);
        if (a2 != null) {
            SharedPreferences.Editor edit = a2.edit();
            edit.putString(str2, str3);
            edit.commit();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m558a(String str, String str2, long j) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            HiLog.e("SharedPreUtils", "spName or spKey is empty");
            return;
        }
        SharedPreferences a2 = a(str);
        if (a2 != null) {
            SharedPreferences.Editor edit = a2.edit();
            edit.putLong(str2, j);
            edit.commit();
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        String str4;
        if (context == null) {
            str4 = "context is null";
        } else {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                SharedPreferences m551a = m551a(context, m554a(context, str));
                if (m551a != null) {
                    SharedPreferences.Editor edit = m551a.edit();
                    edit.putString(str2, str3);
                    edit.commit();
                    return;
                }
                return;
            }
            str4 = "spName or spKey is empty";
        }
        HiLog.e("SharedPreUtils", str4);
    }

    public static LinkedHashMap<String, String> a(Map<String, String> map, int i, long j, long j2, String str) {
        String str2;
        String str3;
        if (map == null || map.isEmpty()) {
            str2 = "headers is empty";
        } else {
            if (map.size() <= i) {
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    if (TextUtils.isEmpty(key) || key.length() > j) {
                        str3 = "key check failure";
                    } else if (TextUtils.isEmpty(str) || key.startsWith(str)) {
                        if (TextUtils.isEmpty(entry.getValue()) || r3.length() > j2) {
                            str3 = "value check failure";
                        } else {
                            linkedHashMap.put(key, entry.getValue());
                        }
                    } else {
                        str3 = "key is not starts with x_";
                    }
                    HiLog.w("ParamCheckUtils", str3);
                }
                return linkedHashMap;
            }
            str2 = "map size is too big";
        }
        HiLog.w("ParamCheckUtils", str2);
        return null;
    }

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            HiLog.e("SharedPreUtils", "spName or spKey is empty");
            return str3;
        }
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getString(str2, str3) : str3;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m555a(String str) {
        String a2 = a("global_v2", str, "");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String replace = UUID.randomUUID().toString().replace(Constants.LINK, "");
        m559a("global_v2", str, replace);
        return replace;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static String m553a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (Throwable unused) {
            HiLog.e("DeviceUtil", "get app version error");
            return "";
        }
    }

    public static String a() {
        String property = System.getProperty("debug.huawei.hianalytics.app.url");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        HiLog.i("DeviceUtil", "hianalytics debugMode url is empty");
        return "";
    }

    /* renamed from: a, reason: collision with other method in class */
    public static b1 m552a(String str, String str2) {
        a1 a2;
        e1 a3 = i.a().a(str);
        if (a3 == null || (a2 = a3.a(str2)) == null) {
            return null;
        }
        return a2.m544a();
    }

    public static a1 a(String str, String str2) {
        e1 a2 = i.a().a(str);
        if (a2 != null) {
            return a2.a(str2);
        }
        return null;
    }

    public static Pair<String, Boolean> a(Context context) {
        if (context == null) {
            return new Pair<>("", Boolean.FALSE);
        }
        ContentResolver contentResolver = context.getContentResolver();
        return new Pair<>(Settings.Global.getString(contentResolver, "pps_oaid"), Boolean.valueOf(Boolean.parseBoolean(Settings.Global.getString(contentResolver, "pps_track_limit"))));
    }

    public static SharedPreferences a(String str) {
        Context appContext = EnvUtils.getAppContext();
        if (appContext != null) {
            return m551a(appContext, m554a(appContext, str));
        }
        HiLog.e("SharedPreUtils", "context is null");
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static SharedPreferences m551a(Context context, String str) {
        try {
            return context.getSharedPreferences(str, 0);
        } catch (Exception unused) {
            HiLog.e("SharedPreUtils", "getSharedPreferences Exception");
            return null;
        }
    }

    public static long a(String str, String str2, long j) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            HiLog.e("SharedPreUtils", "spName or spKey is empty");
            return j;
        }
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getLong(str2, j) : j;
    }
}
