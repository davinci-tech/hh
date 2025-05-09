package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes9.dex */
public class g4 {
    public static final String c = "AllLinkDelayAnalysisHelper";
    public static volatile g4 d = null;
    public static final String e = "network-in";
    public static final String f = "network-out";
    public static final String g = "network-vendor";
    public static final String h = "net-msg-id";
    public static final String i = "NWK";
    public static final char j = ';';
    public static final char k = '(';
    public static final char l = ')';
    public static final String m = "time";
    public static final char n = '/';

    /* renamed from: a, reason: collision with root package name */
    public boolean f5263a = false;
    public boolean b = false;

    public boolean addQuicTrace() {
        return false;
    }

    public void userSet(boolean z) {
        this.b = z;
    }

    public void traceResponseNetworkKitOutEvent(z2 z2Var) {
        if (this.f5263a) {
            z2Var.traceResponseNetworkKitOutEvent(b());
        } else {
            Logger.v(c, "isEnableAllLinkDelayAnalysis false");
        }
    }

    public void traceResponseNetworkKitInEvent(z2 z2Var) {
        if (this.f5263a) {
            z2Var.traceResponseNetworkKitInEvent(b(), "NWK");
        } else {
            Logger.v(c, "isEnableAllLinkDelayAnalysis false");
        }
    }

    public h1.d traceRequestNetworkKitOutEvent(h1.d dVar) {
        if (!this.f5263a) {
            Logger.v(c, "isEnableAllLinkDelayAnalysis false");
            return dVar;
        }
        Logger.v(c, "[traceRequestNetworkKitOutEvent]before update:" + Headers.of(dVar.getHeaders()).toString());
        Request build = dVar.newBuilder().removeHeader("network-out").addHeader("network-out", a(dVar.getHeaders(), "network-out", b())).build();
        Logger.v(c, "[traceRequestNetworkKitOutEvent]after update:" + Headers.of(build.getHeaders()).toString());
        return new h1.d(build);
    }

    public h1.d traceRequestNetworkKitInEvent(h1.d dVar) {
        if (!this.f5263a) {
            Logger.v(c, "isEnableAllLinkDelayAnalysis false");
            return dVar;
        }
        Logger.v(c, "[traceRequestNetworkKitInEvent]before update:" + Headers.of(dVar.getHeaders()).toString());
        Map<String, List<String>> headers = dVar.getHeaders();
        Request.Builder addHeader = dVar.newBuilder().removeHeader("network-in").addHeader("network-in", a(headers, "network-in", b())).removeHeader(g).addHeader(g, a(headers, g, "NWK"));
        addUUIDWithoutDash(addHeader, headers);
        Request build = addHeader.build();
        Logger.v(c, "[traceRequestNetworkKitInEvent]after update:" + Headers.of(build.getHeaders()).toString());
        return new h1.d(build);
    }

    public String getNetMsgIdFromRequest(h1.d dVar) {
        return (dVar.getHeaders().get("net-msg-id") == null || dVar.getHeaders().get("net-msg-id").size() <= 0) ? "" : dVar.getHeaders().get("net-msg-id").get(0).toString();
    }

    public void enableAllLinkDelayAnalysis(boolean z) {
        this.f5263a = z;
    }

    public void addUUIDWithoutDash(Request.Builder builder, Map<String, List<String>> map) {
        if (map.containsKey("net-msg-id")) {
            return;
        }
        builder.addHeader("net-msg-id", UUID.randomUUID().toString().replaceAll(Constants.LINK, ""));
    }

    public static g4 getInstance() {
        if (d == null) {
            synchronized (g4.class) {
                if (d == null) {
                    d = new g4();
                }
            }
        }
        return d;
    }

    private String b() {
        return "NWK(time/" + a() + l;
    }

    private String a(Map<String, List<String>> map, String str, String str2) {
        StringBuilder sb;
        Iterator<String> it = map.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (next != null && StringUtils.strEquals(next, str)) {
                if (TextUtils.isEmpty(map.get(str).toString())) {
                    String obj = map.get(str).toString();
                    if (obj.indexOf(59) == obj.length() - 1) {
                        sb = new StringBuilder();
                        sb.append(obj);
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(obj);
                        sb2.append(';');
                        sb = sb2;
                    }
                    sb.append(str2);
                    return sb.toString();
                }
            }
        }
        return str2;
    }

    private String a() {
        return u1.parseCurrentUTCTime();
    }
}
