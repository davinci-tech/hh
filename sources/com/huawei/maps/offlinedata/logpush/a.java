package com.huawei.maps.offlinedata.logpush;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.util.HiAnalyticTools;
import com.huawei.maps.offlinedata.service.cloud.d;
import com.huawei.maps.offlinedata.service.cloud.utils.f;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f6448a = false;
    private static final ThreadPoolExecutor b = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private static HiAnalyticsInstance c;

    protected abstract String c();

    protected a() {
        if (b()) {
            return;
        }
        b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.logpush.a$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                a.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        if (b() || com.huawei.maps.offlinedata.utils.a.a() == null) {
            return;
        }
        String c2 = d.a().c();
        g.a("AbstractLogPusher", "hiAnalyticsUrl--" + c2);
        if (TextUtils.isEmpty(c2)) {
            return;
        }
        HiAnalyticTools.enableLog(com.huawei.maps.offlinedata.utils.a.a());
        HiAnalyticsInstance create = new HiAnalyticsInstance.Builder(com.huawei.maps.offlinedata.utils.a.a()).setMaintConf(new HiAnalyticsConfig.Builder().setCollectURL(c2).build()).create("offlinedata");
        c = create;
        create.setAppid("com.huawei.hwid");
        g.a("AbstractLogPusher", "initHiAnalytics()--initialize success.");
        f6448a = true;
    }

    protected void a(String str, LinkedHashMap<String, String> linkedHashMap) {
        g.a("AbstractLogPusher", "onEvent--" + linkedHashMap);
        if (!e() || TextUtils.isEmpty(str)) {
            return;
        }
        g.a("AbstractLogPusher", "onEvent--start");
        c.onEvent(1, str, linkedHashMap);
    }

    protected void a() {
        g.a("AbstractLogPusher", "onReport");
        if (e()) {
            g.a("AbstractLogPusher", "onReport--start");
            c.onReport(1);
        }
    }

    private boolean e() {
        if (!b()) {
            g.a("AbstractLogPusher", "HA SDK init is false");
            return false;
        }
        if (a(com.huawei.maps.offlinedata.utils.a.a().getApplicationContext())) {
            return true;
        }
        g.a(c(), "log push is not allow");
        return false;
    }

    public static boolean b() {
        return f6448a;
    }

    protected boolean a(Context context) {
        if (!com.huawei.maps.offlinedata.logpush.utils.d.b()) {
            return true;
        }
        if (context == null) {
            return false;
        }
        return com.huawei.maps.offlinedata.logpush.utils.b.a(context);
    }

    protected LinkedHashMap<String, String> a(com.huawei.maps.offlinedata.logpush.dto.c cVar) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("package", com.huawei.maps.offlinedata.utils.a.a().getApplicationContext().getPackageName());
        linkedHashMap.put("appId", com.huawei.maps.offlinedata.service.cloud.utils.b.a(com.huawei.maps.offlinedata.utils.a.a()));
        linkedHashMap.put("version", f.b());
        linkedHashMap.put("apiLevel", String.valueOf(Build.VERSION.SDK_INT));
        linkedHashMap.put("mapProvider", Constants.LINK);
        linkedHashMap.put("callFrom", String.valueOf(!com.huawei.maps.offlinedata.logpush.utils.d.b() ? 1 : 0));
        linkedHashMap.put("service", "hmsmap");
        if (cVar != null && cVar.f() != null) {
            linkedHashMap.put("ext", cVar.f().toString());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.g())) {
            linkedHashMap.put("extra_01", cVar.g());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.h())) {
            linkedHashMap.put("extra_02", cVar.h());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.i())) {
            linkedHashMap.put("extra_03", cVar.i());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.j())) {
            linkedHashMap.put("extra_04", cVar.j());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.k())) {
            linkedHashMap.put("extra_05", cVar.k());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.l())) {
            linkedHashMap.put("extra_06", cVar.l());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.m())) {
            linkedHashMap.put("extra_07", cVar.m());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.n())) {
            linkedHashMap.put("extra_08", cVar.n());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.o())) {
            linkedHashMap.put("extra_09", cVar.o());
        }
        if (cVar != null && !TextUtils.isEmpty(cVar.p())) {
            linkedHashMap.put("extra_10", cVar.p());
        }
        return linkedHashMap;
    }
}
