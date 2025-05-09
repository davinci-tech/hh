package com.huawei.hwcloudjs.d.b;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hwcloudjs.f.d;
import com.huawei.hwcloudjs.f.h;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public static final String f6209a = "POST";
    private static final String b = "BaseRequest";
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 10;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.huawei.hwcloudjs.d.b.a] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.StringBuilder] */
    public <T extends com.huawei.hwcloudjs.d.b.a.c> T a(com.huawei.hwcloudjs.d.b.a.b<T> bVar, Context context) {
        DataOutputStream dataOutputStream;
        String str;
        HttpURLConnection a2;
        ?? d2 = bVar.d();
        d.c(b, "doRequest begin", true);
        d.c(b, "doRequest urlStr:" + d2, false);
        T c2 = bVar.c();
        InputStream inputStream = null;
        try {
            try {
                a2 = a(d2, bVar.f(), bVar.b(), context);
            } catch (Throwable th) {
                th = th;
                h.a(null);
                h.a(d2);
                throw th;
            }
        } catch (IOException unused) {
            dataOutputStream = null;
        } catch (RuntimeException e2) {
            e = e2;
            dataOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            d2 = 0;
            h.a(null);
            h.a(d2);
            throw th;
        }
        if (a2 == null) {
            c2.b(10);
            h.a(null);
            h.a(null);
            return c2;
        }
        a2.connect();
        String a3 = bVar.a();
        d.c(b, "genBody:" + a3, false);
        if (TextUtils.isEmpty(a3)) {
            dataOutputStream = null;
        } else {
            dataOutputStream = new DataOutputStream(a2.getOutputStream());
            try {
                dataOutputStream.write(a3.getBytes("UTF-8"));
                dataOutputStream.flush();
            } catch (IOException unused2) {
                c2.b(3);
                str = "geturl failed IOException";
                d.b(b, str, true);
                h.a(inputStream);
                h.a(dataOutputStream);
                return c2;
            } catch (RuntimeException e3) {
                e = e3;
                c2.b(10);
                str = "doRequest RuntimeException:" + e.getClass().getSimpleName();
                d.b(b, str, true);
                h.a(inputStream);
                h.a(dataOutputStream);
                return c2;
            }
        }
        int responseCode = a2.getResponseCode();
        if (responseCode == 200) {
            d.c(b, "urlConnection getResponseCode:" + responseCode, false);
            inputStream = a2.getInputStream();
            String a4 = a(inputStream);
            c2.b(1);
            c2.a(responseCode);
            HashMap hashMap = new HashMap();
            String[] a5 = c2.a();
            if (a5.length > 0) {
                for (String str2 : a5) {
                    hashMap.put(str2, a2.getHeaderField(str2));
                }
            }
            d.c(b, "readStream res:" + a4, false);
            c2.a(a4, hashMap);
        } else {
            d.b(b, "geturl failed" + responseCode, true);
            c2.b(2);
            c2.a(responseCode);
        }
        h.a(inputStream);
        h.a(dataOutputStream);
        return c2;
    }

    private HttpURLConnection a(String str, String str2, String str3, Context context) {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = a(str, context);
        } catch (IOException unused) {
            httpURLConnection = null;
        }
        try {
        } catch (IOException unused2) {
            d.b(b, "getURLConnection IOException", true);
            return httpURLConnection;
        }
        if (httpURLConnection == null) {
            d.c(b, "urlConnection is null", true);
            return httpURLConnection;
        }
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setDoInput(true);
        if (str2.equalsIgnoreCase("POST")) {
            httpURLConnection.setDoOutput(true);
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("Connection", w9.j);
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        if (str3 != null) {
            httpURLConnection.setRequestProperty("Content-Type", str3);
        }
        return httpURLConnection;
    }

    private HttpURLConnection a(String str, Context context) {
        String str2;
        HttpsURLConnection httpsURLConnection = null;
        try {
            d.c(b, "getUrlConnection urlString:" + str, false);
            URLConnection openConnection = URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
            if (str.startsWith("http://")) {
                if (openConnection instanceof HttpURLConnection) {
                    return (HttpURLConnection) openConnection;
                }
                d.b(b, "getURLConnection url.openConnection() is error", true);
                return null;
            }
            if (openConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) openConnection;
                try {
                    c.a(httpsURLConnection2, context);
                    return httpsURLConnection2;
                } catch (MalformedURLException unused) {
                    httpsURLConnection = httpsURLConnection2;
                    str2 = "getUrlConnection MalformedURLException";
                    HttpsURLConnection httpsURLConnection3 = httpsURLConnection;
                    d.b(b, str2, true);
                    return httpsURLConnection3;
                } catch (IOException unused2) {
                    httpsURLConnection = httpsURLConnection2;
                    str2 = "getUrlConnection IOException";
                    HttpsURLConnection httpsURLConnection32 = httpsURLConnection;
                    d.b(b, str2, true);
                    return httpsURLConnection32;
                }
            }
            d.b(b, "getURLConnection url.openConnection() is error", true);
            return null;
        } catch (MalformedURLException unused3) {
        } catch (IOException unused4) {
        }
    }

    private String a(InputStream inputStream) {
        StringBuilder sb;
        d.c(b, "readStream begin", true);
        d.c(b, "readStream inStream:" + inputStream, false);
        String str = "";
        if (inputStream == null) {
            d.b(b, "readStream inStream is null", true);
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e2) {
                    d.b(b, "readStream IOException:" + e2.getClass().getSimpleName(), true);
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        sb = new StringBuilder("outStream IOException:");
                        sb.append(e.getClass().getSimpleName());
                        d.b(b, sb.toString(), true);
                        d.c(b, "readStream response:" + str, false);
                        return str;
                    }
                }
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                    d.b(b, "outStream IOException:" + e4.getClass().getSimpleName(), true);
                }
                throw th;
            }
        }
        str = byteArrayOutputStream.toString("UTF-8");
        try {
            byteArrayOutputStream.close();
        } catch (IOException e5) {
            e = e5;
            sb = new StringBuilder("outStream IOException:");
            sb.append(e.getClass().getSimpleName());
            d.b(b, sb.toString(), true);
            d.c(b, "readStream response:" + str, false);
            return str;
        }
        d.c(b, "readStream response:" + str, false);
        return str;
    }
}
