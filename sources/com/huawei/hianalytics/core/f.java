package com.huawei.hianalytics.core;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hianalytics.core.transport.net.TransportHandler;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/* loaded from: classes4.dex */
public class f extends TransportHandler {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.net.HttpURLConnection a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            java.net.URL r0 = new java.net.URL
            r0.<init>(r4)
            java.net.URLConnection r4 = r0.openConnection()
            java.net.URLConnection r4 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r4)
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            boolean r0 = r4 instanceof javax.net.ssl.HttpsURLConnection
            if (r0 == 0) goto L47
            r0 = r4
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0
            android.content.Context r1 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()     // Catch: java.io.IOException -> L23 java.security.GeneralSecurityException -> L26 java.security.KeyStoreException -> L29 java.security.NoSuchAlgorithmException -> L2c
            java.security.SecureRandom r2 = com.huawei.secure.android.common.encrypt.utils.EncryptUtil.genSecureRandom()     // Catch: java.io.IOException -> L23 java.security.GeneralSecurityException -> L26 java.security.KeyStoreException -> L29 java.security.NoSuchAlgorithmException -> L2c
            com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA r1 = com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA.getInstance(r1, r2)     // Catch: java.io.IOException -> L23 java.security.GeneralSecurityException -> L26 java.security.KeyStoreException -> L29 java.security.NoSuchAlgorithmException -> L2c
            goto L34
        L23:
            java.lang.String r1 = "getSocketFactory(): IO Exception!"
            goto L2e
        L26:
            java.lang.String r1 = "getSocketFactory(): General Security Exception"
            goto L2e
        L29:
            java.lang.String r1 = "getSocketFactory(): Key Store exception"
            goto L2e
        L2c:
            java.lang.String r1 = "getSocketFactory(): Algorithm Exception!"
        L2e:
            java.lang.String r2 = "HttpTransportHandler"
            com.huawei.hianalytics.core.log.HiLog.e(r2, r1)
            r1 = 0
        L34:
            if (r1 == 0) goto L3f
            r0.setSSLSocketFactory(r1)
            org.apache.http.conn.ssl.X509HostnameVerifier r1 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER
            r0.setHostnameVerifier(r1)
            goto L47
        L3f:
            com.huawei.hianalytics.core.f$a r4 = new com.huawei.hianalytics.core.f$a
            java.lang.String r5 = "No ssl socket factory set"
            r4.<init>(r5)
            throw r4
        L47:
            r4.setRequestMethod(r5)
            r0 = 15000(0x3a98, float:2.102E-41)
            r4.setConnectTimeout(r0)
            r4.setReadTimeout(r0)
            java.lang.String r0 = "GET"
            boolean r5 = r0.equals(r5)
            if (r5 != 0) goto L6a
            r5 = 1
            r4.setDoOutput(r5)
            byte[] r5 = r3.c
            int r5 = r5.length
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r0 = "Conntent-Length"
            r4.setRequestProperty(r0, r5)
        L6a:
            java.lang.String r5 = "Content-Type"
            java.lang.String r0 = "application/json; charset=UTF-8"
            r4.setRequestProperty(r5, r0)
            java.lang.String r5 = "Connection"
            java.lang.String r0 = "close"
            r4.setRequestProperty(r5, r0)
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.b
            if (r5 == 0) goto Lc0
            int r5 = r5.size()
            if (r5 <= 0) goto Lc0
            java.util.Map<java.lang.String, java.lang.String> r5 = r3.b
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L8c:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto Lc0
            java.lang.Object r0 = r5.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L8c
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto La7
            goto L8c
        La7:
            java.lang.Object r2 = r0.getValue()
            if (r2 != 0) goto Lb0
            java.lang.String r0 = ""
            goto Lb6
        Lb0:
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
        Lb6:
            java.lang.String r2 = "UTF-8"
            java.lang.String r0 = java.net.URLEncoder.encode(r0, r2)
            r4.setRequestProperty(r1, r0)
            goto L8c
        Lc0:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.core.f.a(java.lang.String, java.lang.String):java.net.HttpURLConnection");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.huawei.hianalytics.core.transport.net.Response a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = r6.f3845a     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            java.net.HttpURLConnection r1 = r6.a(r1, r7)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L60
            java.lang.String r2 = "GET"
            boolean r7 = r2.equals(r7)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L57
            if (r7 != 0) goto L2b
            java.io.OutputStream r7 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L57
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L7c
            r2.<init>(r7)     // Catch: java.lang.Exception -> L29 java.lang.Throwable -> L7c
            byte[] r0 = r6.c     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            r2.write(r0)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            r2.flush()     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L24
            r0 = r7
            goto L2c
        L22:
            r0 = move-exception
            goto L47
        L24:
            r0 = move-exception
            r5 = r2
            r2 = r7
            r7 = r0
            goto L4e
        L29:
            r2 = move-exception
            goto L64
        L2b:
            r2 = r0
        L2c:
            com.huawei.hianalytics.core.transport.net.Response r7 = new com.huawei.hianalytics.core.transport.net.Response     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4b
            int r3 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4b
            java.lang.String r4 = r6.a(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4b
            r7.<init>(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L4b
            r6.a(r2)
            r6.a(r0)
            r1.disconnect()
            return r7
        L43:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L47:
            r5 = r2
            r2 = r0
            r0 = r5
            goto L7d
        L4b:
            r7 = move-exception
            r5 = r2
            r2 = r0
        L4e:
            r0 = r5
            r5 = r2
            r2 = r7
            r7 = r5
            goto L64
        L53:
            r7 = move-exception
            r2 = r7
            r7 = r0
            goto L7d
        L57:
            r7 = move-exception
            r2 = r7
            r7 = r0
            goto L64
        L5b:
            r7 = move-exception
            r2 = r7
            r7 = r0
            r1 = r7
            goto L7d
        L60:
            r7 = move-exception
            r2 = r7
            r7 = r0
            r1 = r7
        L64:
            com.huawei.hianalytics.core.transport.Utils.handlerException(r2)     // Catch: java.lang.Throwable -> L7c
            r6.a(r0)
            r6.a(r7)
            if (r1 == 0) goto L72
            r1.disconnect()
        L72:
            com.huawei.hianalytics.core.transport.net.Response r7 = new com.huawei.hianalytics.core.transport.net.Response
            r0 = -108(0xffffffffffffff94, float:NaN)
            java.lang.String r1 = ""
            r7.<init>(r0, r1)
            return r7
        L7c:
            r2 = move-exception
        L7d:
            r6.a(r0)
            r6.a(r7)
            if (r1 == 0) goto L88
            r1.disconnect()
        L88:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.core.f.a(java.lang.String):com.huawei.hianalytics.core.transport.net.Response");
    }

    @Override // com.huawei.hianalytics.core.transport.net.TransportHandler
    public Response b() {
        return a("POST");
    }

    public final void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            HiLog.w("HttpTransportHandler", "closeQuietly(): Exception when closing the closeable!");
        }
    }

    public final String a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Closeable closeable = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                    a(byteArrayOutputStream);
                    a(inputStream);
                    return byteArrayOutputStream2;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException unused3) {
            closeable = byteArrayOutputStream;
            HiLog.e("HttpTransportHandler", HiLog.ErrorCode.NE004);
            a(closeable);
            a(inputStream);
            return "";
        } catch (Throwable th3) {
            th = th3;
            closeable = byteArrayOutputStream;
            a(closeable);
            a(inputStream);
            throw th;
        }
    }

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    @Override // com.huawei.hianalytics.core.transport.net.TransportHandler
    public Response a() {
        return a("GET");
    }

    public f(String str, Map<String, String> map, byte[] bArr) {
        super(str, map, bArr);
    }
}
