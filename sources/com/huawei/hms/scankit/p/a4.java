package com.huawei.hms.scankit.p;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.hatool.HmsHiAnalyticsUtils;
import com.huawei.wearengine.sensor.Sensor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes4.dex */
public class a4 {
    private static volatile a4 h = new a4();
    private static String[] i = {"AD", "AL", "AN", "AT", "AU", "AX", "BA", "BE", "BG", "BQ", "CA", "CH", "CW", "CY", "CZ", "DE", "DK", "EE", "ES", "FI", "FO", "FR", "GB", "GG", "GI", "GL", "GR", Sensor.NAME_HR, "HU", "IE", "IL", "IM", "IS", "IT", "JE", "LI", "LT", "LU", "LV", "MC", "MD", "ME", "MF", "MK", "MT", "NL", "NO", "NZ", "PL", "PM", "PT", "RO", "RS", "SE", "SI", "SJ", "SK", "SM", "SX", "TR", "UA", "UM", "US", "VA", "VC", "XK", "YK"};
    private volatile boolean c;
    private volatile long d;

    /* renamed from: a, reason: collision with root package name */
    private Timer f5731a = new Timer();
    private volatile boolean b = true;
    private volatile boolean e = false;
    private final Lock f = new ReentrantLock();
    private List<b> g = new ArrayList(5);

    class a extends Thread {
        a(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            a4.this.d();
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private String f5733a;
        private LinkedHashMap<String, String> b;

        /* synthetic */ b(a4 a4Var, String str, LinkedHashMap linkedHashMap, a aVar) {
            this(str, linkedHashMap);
        }

        private b(String str, LinkedHashMap<String, String> linkedHashMap) {
            this.f5733a = str;
            this.b = linkedHashMap;
        }
    }

    class c extends TimerTask {
        private c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                a4.this.b = true;
                HmsHiAnalyticsUtils.onReport();
            } catch (Exception e) {
                o4.b("ScanHiAnalytics", e.getMessage());
            }
        }

        /* synthetic */ c(a4 a4Var, a aVar) {
            this();
        }
    }

    private a4() {
    }

    public static a4 b() {
        return h;
    }

    private void c(String str, LinkedHashMap<String, String> linkedHashMap) {
        if (!this.e) {
            HmsHiAnalyticsUtils.onEvent(0, str, linkedHashMap);
            HmsHiAnalyticsUtils.onEvent(1, str, linkedHashMap);
        }
        if (this.b) {
            this.b = false;
            this.f5731a.schedule(new c(this, null), 3000L);
        }
        o4.d("ScanHiAnalytics", str + " " + linkedHashMap.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!this.f.tryLock() || this.c) {
            return;
        }
        try {
            Context context = DynamicModuleInitializer.getContext();
            if (context == null) {
                return;
            }
            String a2 = a(context);
            if (a2 != null && !a2.isEmpty()) {
                HmsHiAnalyticsUtils.init(context, false, false, false, a2, context.getPackageName());
                HmsHiAnalyticsUtils.enableLog();
                a();
            }
        } finally {
            this.f.unlock();
        }
    }

    public void b(String str, LinkedHashMap<String, String> linkedHashMap) {
        if (this.c) {
            c(str, linkedHashMap);
        } else {
            a(str, linkedHashMap);
            c();
        }
    }

    private void a(String str, LinkedHashMap<String, String> linkedHashMap) {
        synchronized (this) {
            if (this.c) {
                c(str, linkedHashMap);
            } else {
                if (this.g.size() >= 100) {
                    return;
                }
                this.g.add(new b(this, str, linkedHashMap, null));
            }
        }
    }

    private void c() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.d > 6000) {
            this.d = currentTimeMillis;
            new a("ScanHiAnalytics").start();
        }
    }

    private void a() {
        synchronized (this) {
            this.c = true;
            for (b bVar : this.g) {
                c(bVar.f5733a, bVar.b);
            }
            this.g = null;
        }
    }

    private String a(Context context) {
        try {
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            String a2 = new a1(context, false).a();
            Log.i("ScanHiAnalytics", "getCollectURL:localCountryCode " + a2);
            if (a2 != null && !a2.isEmpty() && !"UNKNOWN".equals(a2)) {
                grsBaseInfo.setSerCountry(a2.toUpperCase(Locale.ENGLISH));
            }
            if (Arrays.asList(i).contains(a2)) {
                this.e = true;
            }
            GrsClient grsClient = new GrsClient(context, grsBaseInfo);
            String synGetGrsUrl = grsClient.synGetGrsUrl("com.huawei.cloud.mlkithianalytics", "ROOTNEW");
            if (TextUtils.isEmpty(synGetGrsUrl)) {
                synGetGrsUrl = grsClient.synGetGrsUrl("com.huawei.cloud.mlkithianalytics", "ROOT");
            }
            Log.i("ScanHiAnalytics", "grs get url success: " + synGetGrsUrl + "  countryCode = " + grsBaseInfo.getSerCountry());
            return synGetGrsUrl;
        } catch (RuntimeException | Exception unused) {
            return null;
        }
    }
}
