package com.huawei.hms.framework.network.restclient.dnkeeper;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b extends g implements Callable {
    private static final String h = "DNKeeperBatchCallable";
    private HashSet<RequestHost> d;
    private HashMap<String, h> e;
    private PLSharedPreferences f;
    private e g;

    @Override // java.util.concurrent.Callable
    public HashMap<String, DnsResult> call() {
        RequestFinishedInfo.Metrics metrics;
        HttpClient httpClient = DNKeeperManager.getInstance().getHttpClient();
        HashMap<String, DnsResult> hashMap = new HashMap<>();
        String str = "https://" + this.b + d.h;
        String a2 = f.a(this.d);
        String uuid = UUID.randomUUID().toString();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metrics_data", new JSONObject().put("trace_id", uuid));
        } catch (JSONException e) {
            Logger.w(h, "recordMap fail to put:", e);
        }
        Request build = httpClient.newRequest().options(jSONObject.toString()).requestBody(RequestBodyProviders.create("application/json", StringUtils.str2Byte(a2))).url(str).method("POST").build();
        Logger.v(h, "DNKeeperCallable call : " + build);
        this.g.put("request_domain", this.d.toString());
        this.g.put("trace_id", uuid);
        Submit<ResponseBody> newSubmit = httpClient.newSubmit(build);
        try {
            Response<ResponseBody> execute = newSubmit.execute();
            a(newSubmit);
            RequestFinishedInfo requestFinishedInfo = newSubmit.getRequestFinishedInfo();
            if (requestFinishedInfo != null && (metrics = requestFinishedInfo.getMetrics()) != null) {
                this.f4571a = metrics.getConnectIps();
            }
            if (execute.isOK()) {
                Logger.i(h, "response from dnkeeper server success");
                this.g.put("error_code", 10020000L);
                a(execute);
            } else {
                Logger.w(h, "response status code:" + execute.getCode());
                this.g.put("error_code", (long) execute.getCode());
                a();
            }
            e.a();
            if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
                Logger.v(h, "dnkeeper report data to aiops is: %s", new JSONObject(this.g.get()));
                HianalyticsHelper.getInstance().executeReportHa(this.g, "dns_request");
            }
            for (Map.Entry<String, h> entry : this.e.entrySet()) {
                h value = entry.getValue();
                if (!f.a(value.a())) {
                    Logger.d(h, "queryIps from dnkeeper service success");
                    value.a(false);
                    hashMap.put(entry.getKey(), value.a());
                }
            }
            return hashMap;
        } catch (IOException e2) {
            Logger.w(h, "IOException: ", e2);
            this.g.put("error_code", ExceptionCode.getErrorCodeFromException(e2));
            a(newSubmit);
            a();
            e.a();
            if (HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getAppContext())) {
                Logger.v(h, "dnkeeper report data to aiops is: %s", new JSONObject(this.g.get()));
                HianalyticsHelper.getInstance().executeReportHa(this.g, "dns_request");
            }
            return hashMap;
        }
    }

    private void a(Submit submit) {
        Iterator<h> it = this.e.values().iterator();
        while (it.hasNext()) {
            it.next().a((Future) null);
        }
        RequestFinishedInfo requestFinishedInfo = submit.getRequestFinishedInfo();
        if (requestFinishedInfo != null) {
            RequestFinishedInfo.Metrics metrics = requestFinishedInfo.getMetrics();
            this.f4571a = metrics.getConnectIps();
            this.g.put("dns_server_ips", Arrays.toString(metrics.getConnectIps().toArray()));
            this.g.put(e.h, requestFinishedInfo.getMetricsTime().getTotalTime());
            this.g.put("protocol_impl", requestFinishedInfo.getNetworkSdkType());
        }
    }

    private void a(Response<ResponseBody> response) {
        try {
            String byte2Str = StringUtils.byte2Str(IoUtils.toByteArray(response.getBody().getInputStream()));
            Logger.v(h, "response = " + byte2Str);
            JSONObject jSONObject = new JSONObject(byte2Str);
            JSONArray jSONArray = jSONObject.getJSONArray(d.o);
            JSONArray optJSONArray = jSONObject.optJSONArray(d.n);
            StringBuilder sb = new StringBuilder();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    if (i > 0) {
                        sb.append("&");
                    }
                    sb.append(optJSONArray.opt(i));
                }
            }
            PLSharedPreferences pLSharedPreferences = this.f;
            if (pLSharedPreferences != null) {
                pLSharedPreferences.putString(d.e, "https://" + this.b);
                Logger.d(h, "whiteDomainRecords persist " + optJSONArray);
                this.f.putString(d.n, sb.toString());
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string = jSONObject2.getString("domainName");
                h hVar = this.e.get(string);
                if (hVar == null) {
                    hVar = new h();
                    this.e.put(string, hVar);
                }
                if (jSONObject2.getInt(DnsResult.KEY_ATN_CODE) == 0) {
                    jSONObject2.put("createTime", System.currentTimeMillis());
                    DnsResult a2 = f.a(jSONObject2.toString());
                    hVar.a(a2);
                    a(a2, string);
                } else {
                    this.g.put("error_code", 10020001L);
                    a(hVar);
                }
            }
            a(null, null);
        } catch (IOException | JSONException e) {
            a();
            this.g.put("error_code", 10020001L);
            Logger.w(h, e.getClass().getSimpleName());
        }
    }

    private void a(DnsResult dnsResult, String str) {
        if (dnsResult == null) {
            a(this.f);
        } else {
            a(dnsResult, str, this.f);
        }
    }

    private void a(h hVar) {
        hVar.a(System.currentTimeMillis());
    }

    private void a() {
        Iterator<h> it = this.e.values().iterator();
        while (it.hasNext()) {
            it.next().a(System.currentTimeMillis());
        }
    }

    public b(HashSet<RequestHost> hashSet, String str, HashMap<String, h> hashMap, PLSharedPreferences pLSharedPreferences, e eVar) {
        HashSet<RequestHost> hashSet2 = new HashSet<>();
        this.d = hashSet2;
        hashSet2.addAll(hashSet);
        this.b = str;
        this.e = hashMap;
        this.f = pLSharedPreferences;
        this.g = eVar;
    }
}
