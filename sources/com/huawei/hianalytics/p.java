package com.huawei.hianalytics;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class p extends j0 {

    /* renamed from: a, reason: collision with root package name */
    public static long f3890a;

    /* renamed from: a, reason: collision with other field name */
    public final List<a> f58a;

    @Override // com.huawei.hianalytics.j0
    public void a() {
    }

    public final void a(String str, String str2) {
        synchronized (this) {
            ((j0) this).f3878a.removeMessages(3);
            this.f58a.add(new a(str, str2, System.currentTimeMillis()));
            if (this.f58a.size() >= s.a().f70a) {
                b();
            } else {
                ((j0) this).f3878a.sendEmptyMessageDelayed(3, s.a().f81d);
            }
        }
    }

    public void b() {
        synchronized (this) {
            if (this.f58a.isEmpty()) {
                return;
            }
            try {
                HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
                if (instanceByTag != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (a aVar : this.f58a) {
                        aVar.getClass();
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("from", aVar.f59a);
                        jSONObject.put("to", aVar.b);
                        jSONObject.put("time", aVar.f3891a);
                        jSONArray.put(jSONObject);
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("apps", jSONArray);
                    jSONObject2.put("version", "v2");
                    instanceByTag.onEventSync(0, "100", jSONObject2);
                }
            } catch (Throwable th) {
                HiLog.i("CC1", "flush fail: " + th.getMessage());
            }
            this.f58a.clear();
        }
    }

    @Override // com.huawei.hianalytics.j0
    public void a(final Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - f3890a > 5000) {
            f3890a = currentTimeMillis;
            if (!j.g()) {
                ((j0) this).f3878a.removeMessages(5);
                ((j0) this).f3878a.sendEmptyMessage(5);
            }
        }
        if (System.currentTimeMillis() - j.a("global_v2", "lastReportTime", 0L) >= s.a().f71a) {
            ((j0) this).f3878a.removeMessages(4);
            ((j0) this).f3878a.sendEmptyMessageDelayed(4, new Random().nextInt(30) * 1000);
        }
        if (g.f3871a[0].equals(((j0) this).f42a) && s.a().f77b) {
            TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.p$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    p.this.b(bundle);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Bundle bundle) {
        try {
            String string = bundle.getString("toPackage");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            a(bundle.getString("fromPackage"), string);
        } catch (Throwable th) {
            HiLog.i("CC1", "cache " + th.getMessage());
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final long f3891a;

        /* renamed from: a, reason: collision with other field name */
        public final String f59a;
        public final String b;

        public a(String str, String str2, long j) {
            this.f59a = str;
            this.b = str2;
            this.f3891a = j;
        }
    }

    public p(Handler handler, String str) {
        super(handler, str);
        this.f58a = new ArrayList();
    }
}
