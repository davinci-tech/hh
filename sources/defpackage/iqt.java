package defpackage;

import android.content.Context;
import android.os.Binder;
import android.text.TextUtils;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.ScopeManager;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class iqt {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f13544a;
    private static Set<String> b;
    private static final Map<String, String> c;
    private static final Map<String, String> d;

    private static boolean c(int i) {
        return i == 2002 || i == 10008;
    }

    private static boolean d(int i) {
        return i == 2 || i == 40002;
    }

    static {
        HashSet hashSet = new HashSet();
        f13544a = hashSet;
        b = c();
        HashMap hashMap = new HashMap();
        hashMap.put("com.huawei.hwid", "10132067");
        hashMap.put("com.huawei.hms", "102322765");
        hashMap.put("com.huawei.health", "b92825c2bd5d6d6d1e7f39eecd17843b7d9016f611136b75441bc6f4d3f00f05");
        hashMap.put(BaseApplication.APP_PACKAGE_GOOGLE_HEALTH, "B2881B2EE3D4EFA03342AE07DAFC0466B63EDE959EC2E2F58C05A54266F45748");
        d = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("com.huawei.ohos.health", "b2881b2ee3d4efa03342ae07dafc0466b63ede959ec2e2f58c05a54266f45748");
        c = Collections.unmodifiableMap(hashMap2);
        hashSet.add("3775339A90423293F6F60055FE4484130CF90E5D4A83E9B8713EDB97F5A094B0");
        hashSet.add("B2881B2EE3D4EFA03342AE07DAFC0466B63EDE959EC2E2F58C05A54266F45748");
    }

    public static boolean b(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        String e = KeyValDbManager.b(context).e("cloud_user_privacy402");
        if (TextUtils.isEmpty(e)) {
            e = String.valueOf(!Utils.o());
        }
        ReleaseLogUtil.e("Debug_HealthKitPermissionUtil", "isAppWhite linkState = ", e);
        return "true".equals(e);
    }

    public static boolean b() {
        Context context = BaseApplication.getContext();
        String a2 = iwi.a(context);
        Map<String, String> map = c;
        if (map.containsKey(a2)) {
            return map.get(a2).toUpperCase(Locale.ROOT).equals(HsfSignValidator.e(context, a2));
        }
        return false;
    }

    public static boolean b(String str, Context context) {
        ReleaseLogUtil.e("Debug_HealthKitPermissionUtil", "isHealthKitApp packageName = ", str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Map<String, String> map = d;
        if (!map.containsKey(str)) {
            return false;
        }
        if (map.get(str).toUpperCase(Locale.ROOT).equals(HsfSignValidator.e(context, str))) {
            return true;
        }
        return a(map.get(str), str, context);
    }

    public static boolean a() {
        Context context = BaseApplication.getContext();
        String a2 = iwi.a(context);
        if ("com.huawei.ohos.health.device".equals(a2)) {
            return f13544a.contains(HsfSignValidator.e(context, a2));
        }
        return false;
    }

    public static boolean d(String str, int i) {
        boolean b2 = ima.b();
        boolean z = !b2 || b(str, i);
        ReleaseLogUtil.e("Debug_HealthKitPermissionUtil", "isScreenLocked: ", Boolean.valueOf(b2), ", isScreenLockedAllowed: ", Boolean.valueOf(z));
        return z;
    }

    private static boolean b(String str, int i) {
        if (a() || b.contains(str)) {
            return true;
        }
        if ("saveSample".equals(str) && i == 50001) {
            return true;
        }
        return e() && e(str, i);
    }

    private static boolean e() {
        Context context = BaseApplication.getContext();
        String a2 = iwi.a(context);
        if (!"com.huawei.audioaccessorymanager".equals(a2)) {
            ReleaseLogUtil.c("Debug_HealthKitPermissionUtil", "isAudioManager: false");
            return false;
        }
        return "FED014913C948337C171FFEC7A2CB3873AC05E71A62FA8C3D8BF8F6854E5A31F".equals(HsfSignValidator.e(context, a2));
    }

    private static boolean e(String str, int i) {
        if ("registerSportData".equals(str) || "getDeviceList".equals(str)) {
            return true;
        }
        if ("saveSample".equals(str) && (2002 == i || 50001 == i)) {
            return true;
        }
        if ("saveSamples".equals(str)) {
            return 2002 == i || 50001 == i;
        }
        return false;
    }

    private static boolean a(String str, String str2, Context context) {
        LogUtil.a("Debug_HealthKitPermissionUtil", "appId = ", str, " packageName = ", str2);
        ScopeManager scopeManager = new ScopeManager(context);
        scopeManager.setAppId(Binder.getCallingPid(), Binder.getCallingUid(), str);
        String str3 = GRSManager.a(context).getNoCheckUrl("domainScopeOauth", "") + "?nsp_svc=nsp.scope.app.get&nsp_fmt=json&type=2&appid=" + str;
        LogUtil.a("Debug_HealthKitPermissionUtil", "validateUrl = ", str3);
        scopeManager.setScopeServerUrl(str, str3);
        ScopeInfoResponse scope = scopeManager.getScope(str, "hihealth");
        String e = HsfSignValidator.e(context, str2);
        if (scope == null) {
            ReleaseLogUtil.c("Debug_HealthKitPermissionUtil", "scopeInfoResponse is null");
            return false;
        }
        if (e != null) {
            return e.equals(scope.getCertFingerprint()) || e.equals(scope.getCertFingerprintExtra());
        }
        return false;
    }

    public static int e(int i) {
        if (d(i)) {
            return 40002;
        }
        if (c(i)) {
            return 10008;
        }
        return i;
    }

    private static Set<String> c() {
        HashSet hashSet = new HashSet();
        hashSet.add("stopReadingHeartRate");
        hashSet.add("stopReadingAtrial");
        hashSet.add("stopReadingRRI");
        hashSet.add("stopSport");
        hashSet.add("pushMsgToWearable");
        hashSet.add("unRegisterSportData");
        hashSet.add("sendDeviceCommand");
        hashSet.add("getSwitch");
        return hashSet;
    }
}
