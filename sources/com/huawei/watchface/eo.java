package com.huawei.watchface;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.util.HiAnalyticTools;
import com.huawei.hianalytics.v2.HiAnalyticsConf;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import com.tencent.connect.common.Constants;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class eo {
    private static Executor c = Executors.newSingleThreadExecutor();
    private static Map<String, String> d;

    /* renamed from: a, reason: collision with root package name */
    public int f11011a;
    public HiAnalyticsInstance b;
    private Context e;

    /* synthetic */ eo(AnonymousClass1 anonymousClass1) {
        this();
    }

    private eo() {
        this.f11011a = 0;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static eo f11015a = new eo(null);
    }

    public static eo a() {
        return a.f11015a;
    }

    public void a(Context context) {
        a(MobileInfoHelper.getVersion(), Environment.getApplicationContext());
        this.e = context;
        c.execute(new Runnable() { // from class: com.huawei.watchface.eo$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                eo.this.d();
            }
        });
    }

    private static void a(String str, Context context) {
        HwLog.i("AnalyticsUtils", "setAppVer: " + str);
        SharedPreferences.Editor edit = context.getSharedPreferences("hianalytics_global_v2_" + context.getPackageName(), 0).edit();
        edit.putString(Constants.PARAM_APP_VER, str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(this.e);
        String commonCountryCode = hwWatchFaceApi.getCommonCountryCode();
        if (TextUtils.isEmpty(commonCountryCode)) {
            HwLog.i("AnalyticsUtils", "initConfig isHomeCountryEmpty is null");
            return;
        }
        HwLog.i("AnalyticsUtils", "homeCountry: " + commonCountryCode + " targetCountry: " + commonCountryCode);
        String a2 = !TextUtils.isEmpty(commonCountryCode) ? b.a().a(commonCountryCode, "hianalyticsV3", c.a("hianalyticsV3")) : "";
        if (HiAnalyticsManager.getInitFlag("theme_ha_normal_tag")) {
            a(0);
            a(1);
        }
        HiAnalyticsConfig build = new HiAnalyticsConfig.Builder().setCollectURL(a2).setEnableUUID(true).setEnableUDID(true).setUdid(hwWatchFaceApi.getDeviceId()).build();
        try {
            if (HiAnalyticsManager.getInitFlag("theme_ha_normal_tag")) {
                this.b = new HiAnalyticsInstance.Builder(Environment.getApplicationContext()).setMaintConf(build).refresh("theme_ha_normal_tag");
                new HiAnalyticsConf.Builder(Environment.getApplicationContext()).setCollectURL(1, a2).refresh(true);
            } else {
                this.b = new HiAnalyticsInstance.Builder(Environment.getApplicationContext()).setMaintConf(build).create("theme_ha_normal_tag");
                new HiAnalyticsConf.Builder(Environment.getApplicationContext()).setCollectURL(1, a2).create();
                a(0);
            }
        } catch (Exception e) {
            HwLog.e("AnalyticsUtils", "initConfig Exception: " + HwLog.printException(e));
        }
        f();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("projectid", commonCountryCode);
        linkedHashMap.put("is_login", String.valueOf(this.f11011a));
        linkedHashMap.put("phoneType", MobileInfoHelper.getDeviceName());
        if (this.b != null) {
            if (!hwWatchFaceApi.isOversea() && TextUtils.equals(commonCountryCode, "CN")) {
                String userId = hwWatchFaceApi.getUserId();
                this.b.setUpid(0, userId);
                this.b.setUpid(1, userId);
            }
            this.b.setAppid(FlavorConfig.SERVICE_WATCH_FACE);
            try {
                this.b.setCommonProp(0, linkedHashMap);
                this.b.setCommonProp(1, b());
            } catch (Exception e2) {
                HwLog.e("AnalyticsUtils", "setCommonProp Exception: " + HwLog.printException(e2));
            }
        }
        HiAnalyticTools.enableLog(this.e);
    }

    public static Map<String, String> b() {
        synchronized (eo.class) {
            if (d == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                d = concurrentHashMap;
                concurrentHashMap.put("is_root", String.valueOf(CommonUtils.F()));
                d.put("appVersion", CommonUtils.m());
                d.put("androidVersion", Build.VERSION.RELEASE);
                d.put("phoneType", MobileInfoHelper.getDeviceName());
                d.put("is_HarmonyOs", String.valueOf(MobileInfoHelper.isHarmonyOs()));
            }
            HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(Environment.getApplicationContext());
            if (hwWatchFaceApi == null) {
                return d;
            }
            d.put("is_login", hwWatchFaceApi.isLogin() ? "1" : "0");
            if (!hwWatchFaceApi.isOversea() && TextUtils.equals(hwWatchFaceApi.getCommonCountryCode(), "CN")) {
                d.put("user_id", hwWatchFaceApi.getUserId());
            }
            d.put("softVersion", hwWatchFaceApi.getSoftVersion());
            d.put("deviceModel", hwWatchFaceApi.getDeviceModel());
            d.put("osType", MobileInfoHelper.isHarmonyOs() ? "0" : "1");
            d.put("osVersion", Build.VERSION.RELEASE);
            d.put("siteName", hwWatchFaceApi.getWatchfaceSiteForAll());
            d.put(HiAnalyticsConstant.BI_KEY_NET_TYPE, String.valueOf(dj.c()));
            d.put("hmsVer", CommonUtils.v());
            d.put("ageGroup", String.valueOf(n.a(Environment.getApplicationContext()).b()));
            d.put("userType", ab.a().c());
            d.put("run_in_background", CommonUtils.s() ? "1" : "0");
            d.put("connectStatus", String.valueOf(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectStateForAnalytics()));
            d.put("sessionId", eq.c().a());
            d.put("sdkVersion", cv.j());
            d.put("watchType", HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchfaceModel());
            return d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
        long b = dp.b("theme_last_report_time", System.currentTimeMillis());
        if (this.b != null) {
            FlavorConfig.printAnalyticsLog(i, str, GsonHelper.toJson(linkedHashMap));
            this.b.onEvent(i, str, linkedHashMap);
            if (z) {
                b(i);
            } else if (System.currentTimeMillis() - b >= 86400000) {
                b(i);
            }
        }
    }

    /* renamed from: com.huawei.watchface.eo$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f11012a;
        final /* synthetic */ String b;
        final /* synthetic */ LinkedHashMap c;
        final /* synthetic */ eo d;

        @Override // java.lang.Runnable
        public void run() {
            if (this.d.e()) {
                this.d.a(this.f11012a, this.b, this.c, false);
            }
        }
    }

    public void a(final int i, final String str, final LinkedHashMap<String, String> linkedHashMap) {
        HwLog.i("AnalyticsUtils", "onEventReport() type: " + i + ", key: " + str);
        c.execute(new Runnable() { // from class: com.huawei.watchface.eo.2
            @Override // java.lang.Runnable
            public void run() {
                if (eo.this.e()) {
                    eo.this.a(i, str, linkedHashMap, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HwLog.i("AnalyticsUtils", "onReportAnalytics() type: " + i);
        dp.a("theme_last_report_time", System.currentTimeMillis());
        if (this.b != null) {
            HwLog.i("AnalyticsUtils", "onReportAnalytics() onReport.");
            this.b.onReport(i);
        }
    }

    public void c() {
        b(1);
    }

    public void a(final int i) {
        c.execute(new Runnable() { // from class: com.huawei.watchface.eo.3
            @Override // java.lang.Runnable
            public void run() {
                if (eo.this.e()) {
                    eo.this.b(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(Environment.getApplicationContext());
        return (hwWatchFaceApi.isRelease() || hwWatchFaceApi.isBetaVersion()) && !ActivityManager.isUserAMonkey();
    }

    private void f() {
        if (HwWatchFaceApi.getInstance(this.e).isLogin()) {
            this.f11011a = 1;
        } else {
            this.f11011a = 0;
        }
    }

    public static void a(BaseEvent baseEvent, String str) {
        if (baseEvent == null) {
            HwLog.e("AnalyticsUtils", "uploadBiEvent() event is null.");
            return;
        }
        baseEvent.b(System.currentTimeMillis());
        baseEvent.c(baseEvent.n() - baseEvent.m());
        baseEvent.k(str);
        baseEvent.a_();
    }
}
