package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.network.embedded.w;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.openalliance.ad.ca;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.bv;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class k extends b {
    private static HttpClient b;
    private static HttpClient c;
    private static final byte[] d = new byte[0];

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0237, code lost:
    
        if (r13 <= 0) goto L125;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0267 A[Catch: all -> 0x0281, TryCatch #1 {all -> 0x0281, blocks: (B:58:0x0219, B:60:0x0239, B:61:0x0293, B:45:0x0242, B:47:0x0267, B:48:0x026e, B:50:0x0274, B:51:0x027d, B:87:0x0286), top: B:26:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0274 A[Catch: all -> 0x0281, TryCatch #1 {all -> 0x0281, blocks: (B:58:0x0219, B:60:0x0239, B:61:0x0293, B:45:0x0242, B:47:0x0267, B:48:0x026e, B:50:0x0274, B:51:0x027d, B:87:0x0286), top: B:26:0x00a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0292  */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.huawei.hms.network.httpclient.HttpClient, java.io.Closeable] */
    @Override // com.huawei.openalliance.ad.net.http.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.openalliance.ad.net.http.Response b(com.huawei.openalliance.ad.net.http.e r25, com.huawei.openalliance.ad.net.http.a r26) {
        /*
            Method dump skipped, instructions count: 683
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.k.b(com.huawei.openalliance.ad.net.http.e, com.huawei.openalliance.ad.net.http.a):com.huawei.openalliance.ad.net.http.Response");
    }

    public void a(e eVar) {
        Log.i("NetworkKitCaller", "preCreateHttpClient.");
        try {
            b(eVar);
        } catch (Throwable th) {
            ho.c("NetworkKitCaller", "preCreateHttpClient error: %s", th.getClass().getSimpleName());
        }
    }

    public void a(Submit submit) {
        RequestFinishedInfo requestFinishedInfo;
        RequestFinishedInfo.Metrics metrics;
        if (submit == null || (requestFinishedInfo = submit.getRequestFinishedInfo()) == null || (metrics = requestFinishedInfo.getMetrics()) == null) {
            return;
        }
        ho.b("NetworkKitCaller", "DnsType: %s", metrics.getDnsType());
        if (w.l.equalsIgnoreCase(metrics.getDnsType()) || "HTTPDNS".equalsIgnoreCase(metrics.getDnsType())) {
            ho.b("NetworkKitCaller", "grs forceExpire");
            ca.a(this.f7301a).b();
        }
    }

    private HttpClient b(e eVar) {
        synchronized (d) {
            if (b == null || c == null) {
                HttpClient.Builder enableQuic = bv.a(this.f7301a, eVar.j).readTimeout(eVar.c).connectTimeout(eVar.b).enableQuic(eVar.j);
                HttpsConfig.a(enableQuic, false, eVar.i);
                b = enableQuic.build();
                HttpClient.Builder enableQuic2 = bv.a(this.f7301a, eVar.j).readTimeout(eVar.c).connectTimeout(eVar.b).enableQuic(eVar.j);
                HttpsConfig.a(enableQuic2, true, false);
                c = enableQuic2.build();
            }
        }
        return eVar.g ? c : b;
    }

    private boolean a(com.huawei.hms.network.httpclient.Response<ResponseBody> response) {
        Map<String, List<String>> headers = response.getHeaders();
        if (headers == null || headers.size() <= 0) {
            return false;
        }
        List<String> list = headers.get("Content-Encoding");
        if (list == null) {
            list = headers.get("Content-Encoding".toLowerCase(Locale.ENGLISH));
        }
        if (list == null) {
            return false;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (Constants.GZIP.equalsIgnoreCase(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.hms.network.httpclient.Request.Builder r5, com.huawei.openalliance.ad.net.http.a r6, boolean r7) {
        /*
            r4 = this;
            java.lang.String r0 = "Accept-Encoding"
            java.lang.String r1 = "gzip"
            r5.addHeader(r0, r1)
            boolean r0 = r4.a(r6)
            if (r0 == 0) goto L16
            java.lang.String r0 = "hw-request-type"
            java.lang.String r2 = r4.b(r6)
            r5.addHeader(r0, r2)
        L16:
            int r0 = r6.h
            r2 = 1
            if (r0 != r2) goto L30
            android.content.Context r0 = r4.f7301a
            java.lang.String r0 = com.huawei.openalliance.ad.utils.d.k(r0)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L30
            java.lang.String r2 = "User-Agent"
            com.huawei.hms.network.httpclient.Request$Builder r3 = r5.removeHeader(r2)
            r3.addHeader(r2, r0)
        L30:
            com.huawei.openalliance.ad.net.http.c r0 = r6.f
            if (r0 == 0) goto L5e
            com.huawei.openalliance.ad.net.http.c r0 = r6.f
            java.util.Map r0 = r0.a()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L42:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L5e
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r5.addHeader(r3, r2)
            goto L42
        L5e:
            if (r7 == 0) goto La2
            boolean r7 = r6.k
            if (r7 == 0) goto L69
            java.lang.String r7 = "Content-Encoding"
            r5.addHeader(r7, r1)
        L69:
            boolean r7 = r4.a(r6)
            java.lang.String r0 = "Content-Type"
            java.lang.String r1 = "NetworkKitCaller"
            if (r7 == 0) goto L7e
            java.lang.String r7 = "content type stream."
            com.huawei.openalliance.ad.ho.a(r1, r7)
            r5.removeHeader(r0)
            java.lang.String r7 = "application/octet-stream"
            goto L8f
        L7e:
            java.lang.String r7 = r6.g
            if (r7 == 0) goto L92
            java.lang.String r7 = r6.g
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r2 = "content type: %s"
            com.huawei.openalliance.ad.ho.a(r1, r2, r7)
            java.lang.String r7 = r6.g
        L8f:
            r5.addHeader(r0, r7)
        L92:
            byte[] r7 = r6.j
            if (r7 == 0) goto La2
            byte[] r6 = r6.j
            int r6 = r6.length
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = "Content-Length"
            r5.addHeader(r7, r6)
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.k.a(com.huawei.hms.network.httpclient.Request$Builder, com.huawei.openalliance.ad.net.http.a, boolean):void");
    }

    public k(Context context) {
        super(context);
    }
}
