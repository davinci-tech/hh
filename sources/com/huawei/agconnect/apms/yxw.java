package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.HttpEntityWrapper;

/* loaded from: classes2.dex */
public class yxw {
    public static final AgentLog abc = AgentLogManager.getAgentLog();

    public static String abc(String str, HttpResponse httpResponse, String str2) {
        try {
            Header[] headers = httpResponse.getHeaders(str);
            if (headers != null && headers.length > 0 && headers[0].getValue() != null) {
                return headers[0].getValue();
            }
            return str2;
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static HttpResponse abc(HttpResponse httpResponse, wxy wxyVar) {
        long j;
        try {
            String str = Agent.getAgentConfiguration().def;
            if (TextUtils.isEmpty(str)) {
                str = "dl-from";
            }
            if (!TextUtils.isEmpty(str)) {
                wxyVar.cde = abc(str, httpResponse, "");
            }
            if (httpResponse.getStatusLine() != null) {
                wxyVar.bcd(httpResponse.getStatusLine().getStatusCode());
            }
            wxyVar.efg = abc("Content-Type", httpResponse, "");
            try {
                j = Long.parseLong(abc("Content-Length", httpResponse, ""));
            } catch (Throwable unused) {
                j = 0;
            }
            wxyVar.def(j);
            if (Agent.isNuwaTracerEnable()) {
                wxyVar.dcb = abc("network-in", httpResponse, "");
                wxyVar.cba = abc("network-out", httpResponse, "");
                wxyVar.f1698a = abc(HeaderType.X_NUWA_SAMPLE_STATE, httpResponse, "0");
                wxyVar.b = abc(HeaderType.X_NUWA_CALL_SEQ, httpResponse, "");
            }
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                if (entity instanceof HttpEntityWrapper) {
                    httpResponse.setEntity(new vut(httpResponse, wxyVar));
                } else {
                    httpResponse.setEntity(new wvu(httpResponse, wxyVar));
                }
                wxyVar.d();
                yza.cde.add(wxyVar);
            } else {
                wxyVar.abc(0L);
                abc(wxyVar);
            }
        } catch (Throwable th) {
            abc.warn("failed to get cdn info: " + th.getMessage());
        }
        return httpResponse;
    }

    public static void abc(wxy wxyVar, Exception exc) {
        wxyVar.bcd(0);
        if (exc != null) {
            wxyVar.abc(exc.toString());
        }
        abc(wxyVar);
    }

    public static void abc(wxy wxyVar) {
        if (wxyVar == null) {
            return;
        }
        if (wxyVar.dcb()) {
            wxyVar.qpo = r0.abc();
        }
        if (wxyVar.gfe()) {
            return;
        }
        wxyVar.d();
        vwx e = wxyVar.e();
        if (e != null) {
            HttpEvent httpEvent = new HttpEvent(e, "");
            wxyVar.abc(true);
            rst.cde.add(httpEvent);
        }
    }
}
