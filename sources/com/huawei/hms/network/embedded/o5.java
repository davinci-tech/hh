package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class o5 {
    public static final String b = "RequestMetricsCache";
    public static o5 c = new o5();
    public static volatile boolean d = false;
    public static volatile boolean e = false;
    public static String f = "com.huawei.hms.network.httpclient.Interceptor$Chain";

    /* renamed from: a, reason: collision with root package name */
    public LimitQueue<n5> f5397a = new LimitQueue<>(10, false);

    public void a(Interceptor.Chain chain) {
        q5.a().a(new a(chain));
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", "8.0.1.307");
            JSONArray jSONArray = new JSONArray();
            Iterator<n5> it = this.f5397a.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().i());
            }
            jSONObject.put(p5.b, jSONArray);
        } catch (JSONException unused) {
            Logger.w(b, "Get RequestMetricsCache Error");
        }
        return jSONObject.toString();
    }

    public static o5 c() {
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        if (e) {
            return d;
        }
        d = ReflectionUtils.checkCompatible(f, p5.b, new Class[0]);
        e = true;
        return d;
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Interceptor.Chain f5398a;

        @Override // java.lang.Runnable
        public void run() {
            Interceptor.Chain chain;
            if (!o5.this.b() || (chain = this.f5398a) == null || chain.requestFinishedInfo() == null) {
                return;
            }
            RequestFinishedInfo.MetricsTime metricsTime = this.f5398a.requestFinishedInfo().getMetricsTime();
            if (metricsTime.getConnectEndTime() - metricsTime.getConnectStartTime() == 0) {
                return;
            }
            o5.this.a(new n5(this.f5398a));
        }

        public a(Interceptor.Chain chain) {
            this.f5398a = chain;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(n5 n5Var) {
        this.f5397a.add(n5Var);
    }
}
