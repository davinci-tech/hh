package com.huawei.hms.network.embedded;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class q0 {
    public static final String d = "HttpDnsClient";
    public static final String e = ",";
    public static final String f = "Retry-After";
    public static final String g = "metrics_data";
    public static final String h = "type";
    public static final String i = "ttl";
    public static final String j = "values";
    public static final String k = "values_v6";
    public static final String l = "domain";
    public static final String m = "dns";
    public static final String n = "error_code";
    public static final int o = 1000;
    public static final int p = 5;
    public static final int q = 86400;
    public static final int r = 600;
    public static final int s = 60;
    public static final int t = 503;

    /* renamed from: a, reason: collision with root package name */
    public final String f5425a;
    public final HttpClient b = new HttpClient.Builder().retryTimeOnConnectionFailure(0).build();
    public a c;

    public void a(a aVar) {
        this.c = aVar;
    }

    public ArrayList<m0> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (String str : list) {
            if (i2 >= 5) {
                break;
            }
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
                i2++;
            }
        }
        p0 p0Var = new p0();
        p0Var.put("trigger_type", "dns_init");
        ArrayList<m0> a2 = a(arrayList, p0Var);
        p0.a();
        if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
            Logger.v("HttpDnsClient", "httpdns report data to aiops is: %s", new JSONObject(p0Var.get()));
            HianalyticsHelper.getInstance().executeReportHa(p0Var, "dns_request");
        }
        return a2;
    }

    public a a() {
        return this.c;
    }

    public m0 a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        p0 p0Var = new p0();
        ArrayList<m0> a2 = a(arrayList, p0Var);
        p0.a();
        if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
            Logger.v("HttpDnsClient", "httpdns report data to aiops is: %s", new JSONObject(p0Var.get()));
            HianalyticsHelper.getInstance().executeReportHa(p0Var, "dns_request");
        }
        Iterator<m0> it = a2.iterator();
        return it.hasNext() ? it.next() : new m0();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x017e A[LOOP:1: B:35:0x0178->B:37:0x017e, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.ArrayList<com.huawei.hms.network.embedded.m0> a(java.util.List<java.lang.String> r9, com.huawei.hms.network.embedded.p0 r10) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.q0.a(java.util.List, com.huawei.hms.network.embedded.p0):java.util.ArrayList");
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final long f5426a;
        public final long b;

        public boolean a() {
            return SystemClock.elapsedRealtime() - this.b <= this.f5426a;
        }

        public a(long j, long j2) {
            this.f5426a = j;
            this.b = j2;
        }
    }

    private ArrayList<m0> a(Response<ResponseBody> response) {
        String str;
        ArrayList<m0> arrayList = new ArrayList<>();
        try {
            String byte2Str = StringUtils.byte2Str(IoUtils.toByteArray(response.getBody().getInputStream()));
            Logger.v("HttpDnsClient", "BODY:" + byte2Str);
            JSONArray jSONArray = new JSONObject(byte2Str).getJSONArray("dns");
            arrayList.ensureCapacity(jSONArray.length());
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                m0 a2 = a(jSONArray.getJSONObject(i2));
                a2.a(0);
                arrayList.add(a2);
            }
        } catch (IOException e2) {
            e = e2;
            str = "IOException";
            Logger.w("HttpDnsClient", str, e);
        } catch (JSONException e3) {
            Logger.w("HttpDnsClient", "JSONException", e3.getClass().getSimpleName());
        } catch (Throwable th) {
            e = th;
            str = "parseResponse error";
            Logger.w("HttpDnsClient", str, e);
        }
        return arrayList;
    }

    private m0 a(JSONObject jSONObject) {
        boolean z;
        m0 m0Var = new m0();
        m0Var.b(3);
        if (TextUtils.isEmpty(jSONObject.optString("error_code"))) {
            int optInt = jSONObject.optInt("ttl");
            if (optInt > 86400) {
                optInt = 86400;
            } else if (optInt < 0) {
                optInt = 600;
            }
            m0Var.b(optInt * 1000);
            m0Var.b(jSONObject.optString("domain"));
            JSONArray optJSONArray = jSONObject.optJSONArray(j);
            int i2 = 0;
            boolean z2 = false;
            if (optJSONArray == null) {
                Logger.e("HttpDnsClient", "response return empty ipv4 values");
                z = false;
            } else {
                int i3 = 0;
                z = false;
                while (i3 < optJSONArray.length()) {
                    m0Var.a((String) optJSONArray.opt(i3));
                    i3++;
                    z = true;
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray(k);
            if (optJSONArray2 == null) {
                Logger.w("HttpDnsClient", "response return empty ipv6 values");
            } else {
                boolean z3 = false;
                while (i2 < optJSONArray2.length()) {
                    m0Var.a((String) optJSONArray2.opt(i2));
                    i2++;
                    z3 = true;
                }
                z2 = z3;
            }
            y.a(m0Var, z, z2);
        }
        return m0Var;
    }

    public q0(String str) {
        this.f5425a = str;
    }
}
