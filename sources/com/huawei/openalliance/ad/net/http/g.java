package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/* loaded from: classes5.dex */
class g extends b {
    /* JADX WARN: Code restructure failed: missing block: B:73:0x023e, code lost:
    
        if (r11 > 0) goto L128;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0274 A[Catch: all -> 0x0282, TRY_LEAVE, TryCatch #21 {all -> 0x0282, blocks: (B:59:0x0257, B:61:0x0274), top: B:58:0x0257 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x027d  */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v50 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r25v0 */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v10 */
    /* JADX WARN: Type inference failed for: r25v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3 */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r25v6 */
    /* JADX WARN: Type inference failed for: r25v7 */
    /* JADX WARN: Type inference failed for: r25v8 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v60 */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r2v62 */
    /* JADX WARN: Type inference failed for: r2v63 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // com.huawei.openalliance.ad.net.http.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.openalliance.ad.net.http.Response b(com.huawei.openalliance.ad.net.http.e r29, com.huawei.openalliance.ad.net.http.a r30) {
        /*
            Method dump skipped, instructions count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.net.http.g.b(com.huawei.openalliance.ad.net.http.e, com.huawei.openalliance.ad.net.http.a):com.huawei.openalliance.ad.net.http.Response");
    }

    private void b(a aVar, HttpURLConnection httpURLConnection) {
        String str;
        if (a(aVar)) {
            str = FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM;
        } else if (aVar.g == null) {
            return;
        } else {
            str = aVar.g;
        }
        httpURLConnection.setRequestProperty("Content-Type", str);
    }

    private boolean a(URLConnection uRLConnection) {
        String headerField = uRLConnection.getHeaderField("Content-Encoding");
        return headerField != null && Constants.GZIP.equalsIgnoreCase(headerField);
    }

    private void a(HttpURLConnection httpURLConnection, a aVar) {
        httpURLConnection.setRequestProperty(j2.v, Constants.GZIP);
        if (a(aVar)) {
            httpURLConnection.setRequestProperty("hw-request-type", b(aVar));
        }
        if (aVar.h == 1) {
            String k = com.huawei.openalliance.ad.utils.d.k(this.f7301a);
            if (!TextUtils.isEmpty(k)) {
                httpURLConnection.setRequestProperty("User-Agent", k);
            }
        }
        if (aVar.f != null) {
            for (Map.Entry<String, String> entry : aVar.f.a().entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private void a(a aVar, HttpURLConnection httpURLConnection) {
        if (aVar.k) {
            httpURLConnection.setRequestProperty("Content-Encoding", Constants.GZIP);
        }
        if (aVar.j != null) {
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(aVar.j.length));
        }
    }

    private HttpURLConnection a(String str, a aVar, boolean z, e eVar) {
        ho.b("HttpUrlConnectionCaller", "createConnection: %s", cz.f(str));
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
        HttpsConfig.a(httpURLConnection, eVar.g, eVar.i);
        httpURLConnection.setRequestMethod(aVar.e);
        httpURLConnection.setConnectTimeout(eVar.b);
        httpURLConnection.setReadTimeout(eVar.c);
        httpURLConnection.setDoOutput(z);
        a(httpURLConnection, aVar);
        return httpURLConnection;
    }

    g(Context context) {
        super(context);
    }
}
