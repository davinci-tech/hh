package com.huawei.hianalytics;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.dc.StateKeeper;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class u extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public String f3900a;

    /* renamed from: a, reason: collision with other field name */
    public WeakReference<v> f84a;

    /* renamed from: a, reason: collision with other field name */
    public final boolean f85a;
    public WeakReference<p> b;
    public WeakReference<b0> c;
    public WeakReference<a0> d;

    public final void a() {
        b0 b0Var;
        WeakReference<b0> weakReference = this.c;
        if (weakReference == null || (b0Var = weakReference.get()) == null) {
            return;
        }
        synchronized (b0Var) {
            b0Var.e = j.b();
        }
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void b(Message message) {
        v vVar;
        v vVar2;
        p pVar;
        String str;
        h1 h1Var;
        String string;
        b0 b0Var;
        v vVar3;
        p pVar2;
        WeakReference<a0> weakReference;
        a0 a0Var;
        b0 b0Var2;
        switch (message.what) {
            case 1:
                String str2 = (String) message.obj;
                WeakReference<v> weakReference2 = this.f84a;
                if (weakReference2 == null || (vVar = weakReference2.get()) == null) {
                    return;
                }
                String b = j.b();
                if (TextUtils.isEmpty(b)) {
                    vVar.b = "";
                    vVar.f3902a = 0L;
                    str2 = "";
                } else {
                    if (!TextUtils.isEmpty(vVar.b) && !TextUtils.equals(vVar.b, b)) {
                        vVar.b();
                    }
                    if (TextUtils.equals(vVar.b, b)) {
                        return;
                    }
                    vVar.b = b;
                    vVar.f3902a = System.currentTimeMillis();
                }
                vVar.c = str2;
                return;
            case 2:
                WeakReference<v> weakReference3 = this.f84a;
                if (weakReference3 == null || (vVar2 = weakReference3.get()) == null) {
                    return;
                }
                vVar2.c();
                return;
            case 3:
                WeakReference<p> weakReference4 = this.b;
                if (weakReference4 == null || (pVar = weakReference4.get()) == null) {
                    return;
                }
                pVar.b();
                return;
            case 4:
                HiLog.d("handler", "handleMessage report");
                if (System.currentTimeMillis() - j.a("global_v2", "lastReportTime", 0L) < s.a().f71a) {
                    return;
                }
                if (s.a().f74a) {
                    if (g.f3871a[0].equals(this.f3900a)) {
                        WeakReference<p> weakReference5 = this.b;
                        if (weakReference5 != null && (pVar2 = weakReference5.get()) != null) {
                            pVar2.b();
                        }
                        WeakReference<v> weakReference6 = this.f84a;
                        if (weakReference6 != null && (vVar3 = weakReference6.get()) != null) {
                            vVar3.c();
                        }
                        if (s.a().f82d) {
                            try {
                                Context appContext = EnvUtils.getAppContext();
                                Pair<Long, Long> m583a = s.a().m583a();
                                Map<String, q> a2 = new c0().a(((UsageStatsManager) appContext.getSystemService("usagestats")).queryEvents(((Long) m583a.first).longValue(), ((Long) m583a.second).longValue()));
                                JSONArray jSONArray = new JSONArray();
                                for (q qVar : ((HashMap) a2).values()) {
                                    if (qVar.f3895a != 0 && qVar.c != 0) {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("start", m583a.first);
                                        jSONObject.put("end", m583a.second);
                                        jSONObject.put("packageName", qVar.f67a);
                                        jSONObject.put("firstTimeStamp", qVar.f66a);
                                        jSONObject.put("lastTimeStamp", qVar.b);
                                        jSONObject.put("launchCount", qVar.f3895a);
                                        jSONObject.put("totalTime", qVar.c);
                                        jSONArray.put(jSONObject);
                                    }
                                }
                                HiLog.d("UC", "collect range " + m583a + ", size " + jSONArray.length());
                                if (jSONArray.length() != 0) {
                                    HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                                    if (instanceByTag != null) {
                                        JSONObject jSONObject2 = new JSONObject();
                                        jSONObject2.put("usages", jSONArray);
                                        jSONObject2.put("version", "v2");
                                        instanceByTag.onEventSync(0, "102", jSONObject2);
                                    }
                                    s.a().getClass();
                                    j.m558a("global_v2", "lastCollectTime", System.currentTimeMillis());
                                }
                            } catch (Throwable th) {
                                HiLog.sw("UC", "collect " + th.getMessage());
                            }
                        } else {
                            HiLog.i("UC", "app usage is disable");
                        }
                    }
                    WeakReference<b0> weakReference7 = this.c;
                    if (weakReference7 != null && (b0Var = weakReference7.get()) != null) {
                        b0Var.b();
                    }
                    HiAnalyticsInstance instanceByTag2 = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                    if (instanceByTag2 != null) {
                        String a3 = j.a("global_v2", "sdkReportUrl", "");
                        if (!TextUtils.isEmpty(a3)) {
                            i.a().a("ha_default_collection").b.f14c = a3;
                        }
                        if (TextUtils.isEmpty(j.f())) {
                            Pair<String, Boolean> a4 = j.a(EnvUtils.getAppContext());
                            instanceByTag2.setOAID(0, (String) a4.first);
                            instanceByTag2.setOAIDTrackingFlag(0, ((Boolean) a4.second).booleanValue());
                        }
                        instanceByTag2.onReport(0);
                        j.m558a("global_v2", "lastReportTime", System.currentTimeMillis());
                    }
                }
                s a5 = s.a();
                a5.getClass();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - j.a("global_v2", "lastReloadTime", 0L) > a5.f75b) {
                    boolean z = a5.f74a;
                    String[] strArr = g.f3871a;
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            HiAnalyticsInstance instanceByTag3 = HiAnalyticsManager.getInstanceByTag(strArr[i]);
                            if (instanceByTag3 == null) {
                                i++;
                            } else {
                                String collectUrl = instanceByTag3.getCollectUrl();
                                if (!TextUtils.isEmpty(collectUrl) && (collectUrl.contains("metrics1-drcn") || collectUrl.contains("metrics-drcn") || collectUrl.contains("metrics1.data"))) {
                                    str = j.a("global_v2", "sdkConfigUrl", "");
                                    if (TextUtils.isEmpty(str)) {
                                        synchronized (h1.b) {
                                            if (h1.f3874a == null) {
                                                h1.f3874a = new h1(EnvUtils.getAppContext());
                                            }
                                            h1Var = h1.f3874a;
                                        }
                                        synchronized (h1Var.f39a) {
                                            string = h1Var.f38a.getSharedPreferences("qoes_remote_config", 0).getString("ab_base_url", "");
                                        }
                                        if (!TextUtils.isEmpty(string)) {
                                            str = string + "/abtest/1.0/com.huawei.hianalytics/AB/config";
                                        }
                                    }
                                }
                            }
                        }
                    }
                    str = "";
                    if (j.m568b("DcCfg", "common_cfg", str)) {
                        HiLog.d("DC", "reloadConfig success");
                        a5.m584a();
                        j.m558a("global_v2", "lastReloadTime", currentTimeMillis);
                        boolean z2 = a5.f74a;
                        if (z2 != z) {
                            if (z2) {
                                for (String str3 : g.f3871a) {
                                    if (HiAnalyticsManager.getInstanceByTag(str3) != null) {
                                        t.d(str3);
                                    }
                                }
                            } else {
                                synchronized (StateKeeper.class) {
                                    t.a();
                                }
                            }
                        }
                    } else {
                        HiLog.w("DC", "reloadConfig failed");
                    }
                }
                removeMessages(4);
                long j = s.a().f71a;
                s.a().getClass();
                sendEmptyMessageDelayed(4, j + (new Random().nextInt(r2.b) * 60000));
                return;
            case 5:
                if (!j.g() && w.a().f94a && (weakReference = this.d) != null && (a0Var = weakReference.get()) != null) {
                    synchronized (a0Var) {
                        HiLog.d("MPL", "flush");
                        if (!n.a(EnvUtils.getAppContext()).checkAndClearTable()) {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            try {
                                List<x> a6 = a0Var.a((List<String>) arrayList2);
                                if (a6.isEmpty()) {
                                    HiLog.d("MPL", "no metric data");
                                } else {
                                    Map<String, y> m543a = a0Var.m543a((List<String>) arrayList);
                                    ArrayList arrayList3 = new ArrayList();
                                    a0Var.a(a6, arrayList2, arrayList3, m543a);
                                    if (arrayList3.isEmpty()) {
                                        HiLog.d("MPL", "no new metric data");
                                    } else if (a0Var.a(arrayList3, m543a)) {
                                        a0Var.a(arrayList3, null, null, m543a);
                                    }
                                    j.m565b();
                                }
                                j.a(arrayList2, arrayList);
                            } catch (Throwable unused) {
                                HiLog.d("MPL", "handle info failed");
                            }
                        }
                        a0Var.f3832a.removeMessages(5);
                        a0Var.f3832a.sendEmptyMessageDelayed(5, w.a().f91a + w.a().m594a());
                    }
                }
                removeMessages(5);
                sendEmptyMessageDelayed(5, w.a().f91a + w.a().m594a());
                return;
            case 6:
                WeakReference<b0> weakReference8 = this.c;
                if (weakReference8 == null || (b0Var2 = weakReference8.get()) == null) {
                    return;
                }
                b0Var2.b();
                return;
            case 7:
                a();
                return;
            default:
                return;
        }
    }

    @Override // android.os.Handler
    public void handleMessage(final Message message) {
        if (this.f85a) {
            TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.u$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    u.this.b(message);
                }
            });
            return;
        }
        try {
            b(message);
        } catch (Throwable th) {
            HiLog.w("handler", "handleMessage error:" + th.getMessage());
        }
    }

    public u(Looper looper, int i, String str) {
        super(looper);
        long b;
        int i2;
        this.f85a = Looper.myLooper() == Looper.getMainLooper();
        if (i != 1) {
            if (i == 2) {
                b = w.a().b() + w.a().m594a();
                i2 = 5;
            }
            this.f3900a = str;
        }
        b = s.a().b() + s.a().m582a();
        i2 = 4;
        sendEmptyMessageDelayed(i2, b);
        this.f3900a = str;
    }
}
