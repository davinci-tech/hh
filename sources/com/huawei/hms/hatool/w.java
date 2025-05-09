package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class w {
    private static String b(HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                return k1.a(inputStream);
            } catch (IOException unused) {
                v.f("hmsSdk", "When Response Content From Connection inputStream operation exception! " + httpURLConnection.getResponseCode());
                k1.a((Closeable) inputStream);
                return "";
            }
        } finally {
            k1.a((Closeable) inputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(java.net.HttpURLConnection r2) {
        /*
            boolean r0 = r2 instanceof javax.net.ssl.HttpsURLConnection
            if (r0 == 0) goto L39
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2
            android.content.Context r0 = com.huawei.hms.hatool.q0.i()     // Catch: java.lang.IllegalAccessException -> Lf java.io.IOException -> L12 java.security.GeneralSecurityException -> L15 java.security.KeyStoreException -> L18 java.security.NoSuchAlgorithmException -> L1b
            com.huawei.secure.android.common.ssl.SecureSSLSocketFactory r0 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactory.getInstance(r0)     // Catch: java.lang.IllegalAccessException -> Lf java.io.IOException -> L12 java.security.GeneralSecurityException -> L15 java.security.KeyStoreException -> L18 java.security.NoSuchAlgorithmException -> L1b
            goto L23
        Lf:
            java.lang.String r0 = "getSocketFactory(): Illegal Access Exception "
            goto L1d
        L12:
            java.lang.String r0 = "getSocketFactory(): IO Exception!"
            goto L1d
        L15:
            java.lang.String r0 = "getSocketFactory(): General Security Exception"
            goto L1d
        L18:
            java.lang.String r0 = "getSocketFactory(): Key Store exception"
            goto L1d
        L1b:
            java.lang.String r0 = "getSocketFactory(): Algorithm Exception!"
        L1d:
            java.lang.String r1 = "hmsSdk"
            com.huawei.hms.hatool.v.f(r1, r0)
            r0 = 0
        L23:
            if (r0 == 0) goto L31
            r2.setSSLSocketFactory(r0)
            com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier r0 = new com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier
            r0.<init>()
            r2.setHostnameVerifier(r0)
            goto L39
        L31:
            com.huawei.hms.hatool.w$a r2 = new com.huawei.hms.hatool.w$a
            java.lang.String r0 = "No ssl socket factory set"
            r2.<init>(r0)
            throw r2
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.w.a(java.net.HttpURLConnection):void");
    }

    private static HttpURLConnection a(String str, int i, Map<String, String> map, String str2) {
        if (TextUtils.isEmpty(str)) {
            v.b("hmsSdk", "CreateConnection: invalid urlPath.");
            return null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        a(httpURLConnection);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(com.huawei.hms.network.embedded.y.c);
        httpURLConnection.setReadTimeout(com.huawei.hms.network.embedded.y.c);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(i));
        httpURLConnection.setRequestProperty("Connection", "close");
        if (map != null && map.size() >= 1) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null && !TextUtils.isEmpty(key)) {
                    httpURLConnection.setRequestProperty(key, entry.getValue());
                }
            }
        }
        return httpURLConnection;
    }

    static class a extends Exception {
        a(String str) {
            super(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0143  */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v14, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v18, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v19, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v20, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v21, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v14, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v17, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26, types: [java.io.Closeable, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.hatool.n0 a(java.lang.String r7, byte[] r8, java.util.Map<java.lang.String, java.lang.String> r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.w.a(java.lang.String, byte[], java.util.Map, java.lang.String):com.huawei.hms.hatool.n0");
    }

    public static n0 a(String str, byte[] bArr, Map<String, String> map) {
        return a(str, bArr, map, "POST");
    }
}
