package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes7.dex */
public final class q implements Callable<u> {
    public static final HttpRequestRetryHandler e = new ad();

    /* renamed from: a, reason: collision with root package name */
    public l f830a;
    public Context b;
    public o c;
    public String d;
    public HttpUriRequest f;
    public CookieManager i;
    public AbstractHttpEntity j;
    public HttpHost k;
    public URL l;
    public String q;
    public HttpContext g = new BasicHttpContext();
    public CookieStore h = new BasicCookieStore();
    public int m = 0;
    public boolean n = false;
    public boolean o = false;
    public String p = null;

    public final o a() {
        return this.c;
    }

    private CookieManager i() {
        CookieManager cookieManager = this.i;
        if (cookieManager != null) {
            return cookieManager;
        }
        CookieManager cookieManager2 = CookieManager.getInstance();
        this.i = cookieManager2;
        return cookieManager2;
    }

    private URL h() {
        URL url = this.l;
        if (url != null) {
            return url;
        }
        URL url2 = new URL(this.c.a());
        this.l = url2;
        return url2;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        String b = this.c.b("operationType");
        this.q = b;
        return b;
    }

    private void e() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0109 A[Catch: Exception -> 0x0232, NullPointerException -> 0x024f, IOException -> 0x026e, UnknownHostException -> 0x028e, HttpHostConnectException -> 0x02b0, NoHttpResponseException -> 0x02cf, SocketTimeoutException -> 0x02f0, ConnectTimeoutException -> 0x0311, ConnectionPoolTimeoutException -> 0x0331, SSLException -> 0x0351, SSLPeerUnverifiedException -> 0x0371, SSLHandshakeException -> 0x0391, URISyntaxException -> 0x03b1, HttpException -> 0x03be, TryCatch #3 {HttpException -> 0x03be, NullPointerException -> 0x024f, SocketTimeoutException -> 0x02f0, URISyntaxException -> 0x03b1, UnknownHostException -> 0x028e, SSLHandshakeException -> 0x0391, SSLPeerUnverifiedException -> 0x0371, SSLException -> 0x0351, NoHttpResponseException -> 0x02cf, ConnectionPoolTimeoutException -> 0x0331, ConnectTimeoutException -> 0x0311, HttpHostConnectException -> 0x02b0, IOException -> 0x026e, Exception -> 0x0232, blocks: (B:4:0x0006, B:6:0x0014, B:8:0x0018, B:10:0x001c, B:12:0x0022, B:15:0x0028, B:17:0x0030, B:19:0x0036, B:20:0x003a, B:22:0x0040, B:24:0x004e, B:26:0x00b0, B:28:0x00b6, B:30:0x00c0, B:32:0x00c9, B:34:0x00d5, B:37:0x00df, B:40:0x0101, B:42:0x0109, B:43:0x0116, B:45:0x013c, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:51:0x0153, B:54:0x015f, B:57:0x018e, B:63:0x01aa, B:68:0x01c3, B:69:0x01dc, B:71:0x01dd, B:73:0x01e5, B:75:0x01eb, B:78:0x01f7, B:80:0x01fb, B:85:0x020b, B:87:0x0213, B:89:0x021d, B:92:0x00e9, B:96:0x0221, B:100:0x0225, B:101:0x0231), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013c A[Catch: Exception -> 0x0232, NullPointerException -> 0x024f, IOException -> 0x026e, UnknownHostException -> 0x028e, HttpHostConnectException -> 0x02b0, NoHttpResponseException -> 0x02cf, SocketTimeoutException -> 0x02f0, ConnectTimeoutException -> 0x0311, ConnectionPoolTimeoutException -> 0x0331, SSLException -> 0x0351, SSLPeerUnverifiedException -> 0x0371, SSLHandshakeException -> 0x0391, URISyntaxException -> 0x03b1, HttpException -> 0x03be, TryCatch #3 {HttpException -> 0x03be, NullPointerException -> 0x024f, SocketTimeoutException -> 0x02f0, URISyntaxException -> 0x03b1, UnknownHostException -> 0x028e, SSLHandshakeException -> 0x0391, SSLPeerUnverifiedException -> 0x0371, SSLException -> 0x0351, NoHttpResponseException -> 0x02cf, ConnectionPoolTimeoutException -> 0x0331, ConnectTimeoutException -> 0x0311, HttpHostConnectException -> 0x02b0, IOException -> 0x026e, Exception -> 0x0232, blocks: (B:4:0x0006, B:6:0x0014, B:8:0x0018, B:10:0x001c, B:12:0x0022, B:15:0x0028, B:17:0x0030, B:19:0x0036, B:20:0x003a, B:22:0x0040, B:24:0x004e, B:26:0x00b0, B:28:0x00b6, B:30:0x00c0, B:32:0x00c9, B:34:0x00d5, B:37:0x00df, B:40:0x0101, B:42:0x0109, B:43:0x0116, B:45:0x013c, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:51:0x0153, B:54:0x015f, B:57:0x018e, B:63:0x01aa, B:68:0x01c3, B:69:0x01dc, B:71:0x01dd, B:73:0x01e5, B:75:0x01eb, B:78:0x01f7, B:80:0x01fb, B:85:0x020b, B:87:0x0213, B:89:0x021d, B:92:0x00e9, B:96:0x0221, B:100:0x0225, B:101:0x0231), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0149 A[Catch: Exception -> 0x0232, NullPointerException -> 0x024f, IOException -> 0x026e, UnknownHostException -> 0x028e, HttpHostConnectException -> 0x02b0, NoHttpResponseException -> 0x02cf, SocketTimeoutException -> 0x02f0, ConnectTimeoutException -> 0x0311, ConnectionPoolTimeoutException -> 0x0331, SSLException -> 0x0351, SSLPeerUnverifiedException -> 0x0371, SSLHandshakeException -> 0x0391, URISyntaxException -> 0x03b1, HttpException -> 0x03be, TryCatch #3 {HttpException -> 0x03be, NullPointerException -> 0x024f, SocketTimeoutException -> 0x02f0, URISyntaxException -> 0x03b1, UnknownHostException -> 0x028e, SSLHandshakeException -> 0x0391, SSLPeerUnverifiedException -> 0x0371, SSLException -> 0x0351, NoHttpResponseException -> 0x02cf, ConnectionPoolTimeoutException -> 0x0331, ConnectTimeoutException -> 0x0311, HttpHostConnectException -> 0x02b0, IOException -> 0x026e, Exception -> 0x0232, blocks: (B:4:0x0006, B:6:0x0014, B:8:0x0018, B:10:0x001c, B:12:0x0022, B:15:0x0028, B:17:0x0030, B:19:0x0036, B:20:0x003a, B:22:0x0040, B:24:0x004e, B:26:0x00b0, B:28:0x00b6, B:30:0x00c0, B:32:0x00c9, B:34:0x00d5, B:37:0x00df, B:40:0x0101, B:42:0x0109, B:43:0x0116, B:45:0x013c, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:51:0x0153, B:54:0x015f, B:57:0x018e, B:63:0x01aa, B:68:0x01c3, B:69:0x01dc, B:71:0x01dd, B:73:0x01e5, B:75:0x01eb, B:78:0x01f7, B:80:0x01fb, B:85:0x020b, B:87:0x0213, B:89:0x021d, B:92:0x00e9, B:96:0x0221, B:100:0x0225, B:101:0x0231), top: B:3:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00e9 A[Catch: Exception -> 0x0232, NullPointerException -> 0x024f, IOException -> 0x026e, UnknownHostException -> 0x028e, HttpHostConnectException -> 0x02b0, NoHttpResponseException -> 0x02cf, SocketTimeoutException -> 0x02f0, ConnectTimeoutException -> 0x0311, ConnectionPoolTimeoutException -> 0x0331, SSLException -> 0x0351, SSLPeerUnverifiedException -> 0x0371, SSLHandshakeException -> 0x0391, URISyntaxException -> 0x03b1, HttpException -> 0x03be, TryCatch #3 {HttpException -> 0x03be, NullPointerException -> 0x024f, SocketTimeoutException -> 0x02f0, URISyntaxException -> 0x03b1, UnknownHostException -> 0x028e, SSLHandshakeException -> 0x0391, SSLPeerUnverifiedException -> 0x0371, SSLException -> 0x0351, NoHttpResponseException -> 0x02cf, ConnectionPoolTimeoutException -> 0x0331, ConnectTimeoutException -> 0x0311, HttpHostConnectException -> 0x02b0, IOException -> 0x026e, Exception -> 0x0232, blocks: (B:4:0x0006, B:6:0x0014, B:8:0x0018, B:10:0x001c, B:12:0x0022, B:15:0x0028, B:17:0x0030, B:19:0x0036, B:20:0x003a, B:22:0x0040, B:24:0x004e, B:26:0x00b0, B:28:0x00b6, B:30:0x00c0, B:32:0x00c9, B:34:0x00d5, B:37:0x00df, B:40:0x0101, B:42:0x0109, B:43:0x0116, B:45:0x013c, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:51:0x0153, B:54:0x015f, B:57:0x018e, B:63:0x01aa, B:68:0x01c3, B:69:0x01dc, B:71:0x01dd, B:73:0x01e5, B:75:0x01eb, B:78:0x01f7, B:80:0x01fb, B:85:0x020b, B:87:0x0213, B:89:0x021d, B:92:0x00e9, B:96:0x0221, B:100:0x0225, B:101:0x0231), top: B:3:0x0006 }] */
    @Override // java.util.concurrent.Callable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alipay.android.phone.mrpc.core.u call() {
        /*
            Method dump skipped, instructions count: 980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.call():com.alipay.android.phone.mrpc.core.u");
    }

    private HttpUriRequest c() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            String b2 = this.c.b(Constants.GZIP);
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(b());
            httpPost.setEntity(abstractHttpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    private URI b() {
        String a2 = this.c.a();
        String str = this.d;
        if (str != null) {
            a2 = str;
        }
        if (a2 != null) {
            return new URI(a2);
        }
        throw new RuntimeException("url should not be null");
    }

    public static long b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 != null) {
            return b.b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0L;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a2 = b.a(httpEntity);
        httpEntity.getContentLength();
        try {
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = a2.read(bArr);
                    if (read == -1 || this.c.h()) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    this.c.f();
                }
                outputStream.flush();
            } catch (Exception e2) {
                e2.getCause();
                throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
            }
        } finally {
            r.a(a2);
        }
    }

    public static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(";")) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    private u a(HttpResponse httpResponse, int i, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str2;
        Thread.currentThread().getId();
        HttpEntity entity = httpResponse.getEntity();
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        String str3 = null;
        if (entity == null || httpResponse.getStatusLine().getStatusCode() != 200) {
            if (entity != null) {
                return null;
            }
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
        Thread.currentThread().getId();
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            a(entity, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.o = false;
            this.f830a.c(System.currentTimeMillis() - currentTimeMillis);
            this.f830a.a(byteArray.length);
            int length = byteArray.length;
            p pVar = new p(a(httpResponse), i, str, byteArray);
            long b = b(httpResponse);
            Header contentType = httpResponse.getEntity().getContentType();
            if (contentType != null) {
                HashMap<String, String> a2 = a(contentType.getValue());
                String str4 = a2.get("charset");
                str3 = a2.get("Content-Type");
                str2 = str4;
            } else {
                str2 = null;
            }
            pVar.b(str3);
            pVar.a(str2);
            pVar.a(System.currentTimeMillis());
            pVar.b(b);
            try {
                byteArrayOutputStream.close();
                return pVar;
            } catch (IOException e2) {
                throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException e3) {
                    throw new RuntimeException("ArrayOutputStream close error!", e3.getCause());
                }
            }
            throw th;
        }
    }

    public static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    public static long a(String[] strArr) {
        String str;
        for (int i = 0; i < strArr.length; i++) {
            if ("max-age".equalsIgnoreCase(strArr[i]) && (str = strArr[i + 1]) != null) {
                try {
                    return Long.parseLong(str);
                } catch (Exception unused) {
                    continue;
                }
            }
        }
        return 0L;
    }

    public q(l lVar, o oVar) {
        this.f830a = lVar;
        this.b = lVar.f827a;
        this.c = oVar;
    }
}
