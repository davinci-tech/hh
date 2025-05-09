package com.huawei.updatesdk.a.a.b;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import com.huawei.updatesdk.a.a.d.d;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private HttpURLConnection f10800a = null;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f10801a;
        private int b;
        private String c;

        public String c() {
            return this.c;
        }

        public String b() {
            return this.f10801a;
        }

        public int a() {
            return this.b;
        }
    }

    public void a() {
        HttpURLConnection httpURLConnection = this.f10800a;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a a(String str, String str2, String str3, String str4, Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        a aVar = new a();
        BufferedInputStream bufferedInputStream2 = null;
        try {
            HttpURLConnection a2 = a(str, context);
            this.f10800a = a2;
            a2.setDoInput(true);
            this.f10800a.setDoOutput(true);
            this.f10800a.setUseCaches(false);
            this.f10800a.setConnectTimeout(5000);
            this.f10800a.setReadTimeout(10000);
            this.f10800a.setRequestMethod("POST");
            this.f10800a.setRequestProperty("Content-Type", "application/x-gzip");
            this.f10800a.setRequestProperty("Content-Encoding", Constants.GZIP);
            this.f10800a.setRequestProperty("Connection", "Keep-Alive");
            this.f10800a.setRequestProperty("User-Agent", str4);
            DataOutputStream dataOutputStream = new DataOutputStream(this.f10800a.getOutputStream());
            try {
                dataOutputStream.write(a(str2.getBytes(str3)));
                dataOutputStream.flush();
                a(aVar, this.f10800a);
                int responseCode = this.f10800a.getResponseCode();
                aVar.b = responseCode;
                bufferedInputStream2 = responseCode == 200 ? new BufferedInputStream(this.f10800a.getInputStream()) : new BufferedInputStream(this.f10800a.getErrorStream());
                com.huawei.updatesdk.a.a.d.b bVar = new com.huawei.updatesdk.a.a.d.b();
                byte[] a3 = com.huawei.updatesdk.a.a.b.a.b().a();
                while (true) {
                    int read = bufferedInputStream2.read(a3);
                    if (read == -1) {
                        break;
                    }
                    bVar.a(a3, read);
                }
                com.huawei.updatesdk.a.a.b.a.b().a(a3);
                aVar.f10801a = bVar.a();
                HttpURLConnection httpURLConnection = this.f10800a;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                d.a(dataOutputStream);
                d.a(bufferedInputStream2);
                return aVar;
            } catch (Throwable th2) {
                th = th2;
                BufferedInputStream bufferedInputStream3 = bufferedInputStream2;
                bufferedInputStream2 = dataOutputStream;
                bufferedInputStream = bufferedInputStream3;
                HttpURLConnection httpURLConnection2 = this.f10800a;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                d.a(bufferedInputStream2);
                d.a(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        ByteArrayOutputStream byteArrayOutputStream3;
        DataOutputStream dataOutputStream;
        ByteArrayOutputStream byteArrayOutputStream4 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                dataOutputStream = new DataOutputStream(new GZIPOutputStream(byteArrayOutputStream, bArr.length));
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
            byteArrayOutputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            dataOutputStream.write(bArr, 0, bArr.length);
            dataOutputStream.flush();
            byteArrayOutputStream3 = dataOutputStream;
        } catch (IOException e3) {
            e = e3;
            byteArrayOutputStream4 = dataOutputStream;
            byteArrayOutputStream2 = byteArrayOutputStream4;
            byteArrayOutputStream4 = byteArrayOutputStream;
            try {
                com.huawei.updatesdk.a.a.c.a.a.a.a("HttpsUtil", "gzip error!", e);
                byteArrayOutputStream3 = byteArrayOutputStream2;
                byteArrayOutputStream = byteArrayOutputStream4;
                d.a(byteArrayOutputStream3);
                d.a(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th3) {
                th = th3;
                ByteArrayOutputStream byteArrayOutputStream5 = byteArrayOutputStream2;
                byteArrayOutputStream = byteArrayOutputStream4;
                byteArrayOutputStream4 = byteArrayOutputStream5;
                d.a(byteArrayOutputStream4);
                d.a(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream4 = dataOutputStream;
            d.a(byteArrayOutputStream4);
            d.a(byteArrayOutputStream);
            throw th;
        }
        d.a(byteArrayOutputStream3);
        d.a(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void a(a aVar, HttpURLConnection httpURLConnection) {
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("HttpsUtil", "headers is null.");
            return;
        }
        List<String> list = headerFields.get("x-traceId");
        if (list == null || list.isEmpty()) {
            com.huawei.updatesdk.a.a.c.a.a.a.d("HttpsUtil", "no x-traceId.");
        } else {
            aVar.c = list.get(0);
        }
    }

    public static HttpURLConnection a(String str, Context context) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        httpsURLConnection.setSSLSocketFactory(SecureSSLSocketFactoryNew.getInstance(context));
        httpsURLConnection.setHostnameVerifier(new StrictHostnameVerifier());
        return httpsURLConnection;
    }
}
