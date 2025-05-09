package com.huawei.hms.mlsdk.asr.o;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.httpclient.websocket.WebSocketListener;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes4.dex */
public class f implements com.huawei.hms.mlsdk.asr.o.d {
    private static HandlerThread u;

    /* renamed from: a, reason: collision with root package name */
    private String f5096a;
    private List<String> b;
    private e d;
    private HttpClient f;
    private Request g;
    private SSLSocketFactory i;
    private X509TrustManager j;
    private Map<String, String> k;
    private Handler l;
    private Lock n;
    private long q;
    private boolean r;
    private int c = 0;
    private WebSocket e = null;
    private volatile int h = -1;
    private volatile boolean m = false;
    private boolean o = true;
    private Runnable p = new a();
    private Runnable s = new b();
    private WebSocketListener t = new c();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (f.this.d != null) {
                f.this.d.a();
            }
            f.this.f();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (f.this.h == 1) {
                f.this.e.send("{\"command\":\"HEART\"}");
                Log.d("WebSocketManager", "mHeartMessage: {\"command\":\"HEART\"}");
                f.this.l.postDelayed(this, f.this.q);
            }
        }
    }

    public static final class d {
        private HttpClient d;
        private Request e;
        private SSLSocketFactory f;
        private X509TrustManager g;
        private Map<String, String> h;
        private boolean m;

        /* renamed from: a, reason: collision with root package name */
        private String f5100a = "";
        private List<String> b = new ArrayList();
        private boolean c = true;
        private int i = Integer.MAX_VALUE;
        private boolean j = true;
        private String k = "{\"command\":\"HEART\"}";
        private long l = 5000;

        public d(Context context) {
        }

        public d a(List<String> list) {
            this.b.clear();
            this.b.addAll(list);
            return this;
        }

        public d b(boolean z) {
            this.m = z;
            return this;
        }

        public d a(boolean z) {
            this.c = z;
            return this;
        }

        public d a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            this.f = sSLSocketFactory;
            this.g = x509TrustManager;
            return this;
        }

        public d a(Map<String, String> map) {
            this.h = map;
            return this;
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("dispatch Message");
        u = handlerThread;
        handlerThread.start();
    }

    public f(d dVar) {
        this.f5096a = "";
        this.b = null;
        this.q = 5000L;
        this.f = dVar.d;
        this.g = dVar.e;
        this.f5096a = dVar.f5100a;
        if (!dVar.b.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            this.b = arrayList;
            arrayList.addAll(dVar.b);
        }
        boolean unused = dVar.c;
        this.i = dVar.f;
        this.j = dVar.g;
        this.k = dVar.h;
        int unused2 = dVar.i;
        this.n = new ReentrantLock();
        this.l = new Handler(u.getLooper());
        boolean unused3 = dVar.j;
        String unused4 = dVar.k;
        this.q = dVar.l;
        this.r = dVar.m;
    }

    static /* synthetic */ int f(f fVar) {
        int i = fVar.c;
        fVar.c = i + 1;
        return i;
    }

    private void g() {
        X509TrustManager x509TrustManager;
        if (this.f == null) {
            Log.d("WebSocketManager", "createWebSocket()");
            HttpClient.Builder builder = new HttpClient.Builder();
            SSLSocketFactory sSLSocketFactory = this.i;
            if (sSLSocketFactory == null || (x509TrustManager = this.j) == null) {
                Log.d("WebSocketManager", "SslSocketFactory or TrustManger is null");
            } else {
                builder.sslSocketFactory(sSLSocketFactory, x509TrustManager);
                builder.connectTimeout(20000).callTimeout(20000).readTimeout(20000).hostnameVerifier((HostnameVerifier) SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                NetworkKit.getInstance().initConnectionPool(5, 5L, TimeUnit.SECONDS);
                this.f = builder.build();
            }
        }
        if (this.b == null && this.f5096a.isEmpty()) {
            Log.d("WebSocketManager", "not config url");
        } else {
            try {
                Map<String, String> map = this.k;
                Request.Builder newRequest = this.f.newRequest();
                String str = this.f5096a.isEmpty() ? this.b.get(0) : this.f5096a;
                this.f5096a = str;
                Request.Builder url = newRequest.url(str);
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        url.addHeader(entry.getKey(), entry.getValue());
                    }
                }
                this.g = url.build();
            } catch (IllegalArgumentException e) {
                StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("createRequest(): IllegalArgumentException");
                a2.append(e.getMessage());
                Log.e("WebSocketManager", a2.toString());
            } catch (Exception e2) {
                StringBuilder a3 = com.huawei.hms.mlsdk.asr.o.a.a("createRequest(): Exception: ");
                a3.append(e2.getMessage());
                Log.e("WebSocketManager", a3.toString());
            }
        }
        if (this.f == null || this.g == null) {
            return;
        }
        WebSocket webSocket = this.e;
        if (webSocket != null) {
            webSocket.cancel();
        }
        try {
            this.n.lockInterruptibly();
            try {
                this.f.newWebSocket(this.g, this.t);
                this.n.unlock();
            } catch (Throwable th) {
                this.n.unlock();
                throw th;
            }
        } catch (InterruptedException unused) {
            Log.d("WebSocketManager", "createWebSocket() InterruptedException");
        }
    }

    static /* synthetic */ void k(f fVar) {
        fVar.l.removeCallbacks(fVar.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        synchronized (this) {
            int a2 = a();
            if (a2 != 0 && a2 != 1) {
                a(0);
                g();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        e eVar;
        synchronized (this) {
            if (this.h == -1) {
                Log.d("WebSocketManager", "webSocket is already disconnected");
                return;
            }
            this.l.removeCallbacks(this.p);
            this.l.removeCallbacks(this.s);
            WebSocket webSocket = this.e;
            if (webSocket != null) {
                webSocket.cancel();
            }
            WebSocket webSocket2 = this.e;
            if (webSocket2 != null) {
                boolean close = webSocket2.close(1000, "NORMAL CLOSE");
                Log.d("WebSocketManager", "disconnect(), isClosed is " + close);
                this.e = null;
                if (!close && (eVar = this.d) != null) {
                    eVar.b(1001, "ABNORMAL CLOSE");
                }
            }
            a(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        SmartLogger.i("WebSocketManager", "using other url to connect server");
        a(2);
        this.l.post(this.p);
    }

    public boolean b() {
        return this.m;
    }

    public boolean c() {
        return this.h == 1;
    }

    public void d() {
        this.m = false;
        f();
    }

    public void e() {
        this.m = true;
        h();
    }

    class c extends WebSocketListener {
        c() {
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            f.k(f.this);
            Log.d("WebSocketManager", "onClosed: code: " + i + "reason: " + str);
            if (f.this.d != null) {
                f.this.d.b(i, str);
            }
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onClosing(WebSocket webSocket, int i, String str) {
            f.this.a(3);
            SmartLogger.i("WebSocketManager", "onClosing: code: " + i + ", reason: " + str);
            f.k(f.this);
            if (f.this.d != null) {
                f.this.d.a(i, str);
            }
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            f.this.a(-1);
            SmartLogger.i("WebSocketManager", "onFailure: " + th.getMessage());
            if (f.this.r) {
                f.a(f.this, th, response);
                return;
            }
            if (!f.this.o || f.this.b == null) {
                f.a(f.this, th, response);
                return;
            }
            f.f(f.this);
            if (f.this.c >= f.this.b.size()) {
                f.a(f.this, th, response);
                return;
            }
            f fVar = f.this;
            fVar.f5096a = (String) fVar.b.get(f.this.c);
            f.this.i();
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            SmartLogger.d("WebSocketManager", "onMessage: " + str);
            if (f.this.d != null) {
                f.this.d.a(str);
            }
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            f.this.a(1);
            SmartLogger.i("WebSocketManager", "onOpen()");
            f.this.o = false;
            f.this.c = 0;
            f.this.e = webSocket;
            f.g(f.this);
            if (f.this.d != null) {
                f.this.d.a(response);
            }
            f.this.l.postDelayed(f.this.s, f.this.q);
            if (f.this.m) {
                f.this.h();
            }
        }

        @Override // com.huawei.hms.network.httpclient.websocket.WebSocketListener
        public void onMessage(WebSocket webSocket, byte[] bArr) {
            if (f.this.d != null) {
                f.this.d.a(bArr);
            }
        }
    }

    public void a(e eVar) {
        this.d = eVar;
    }

    public int a() {
        int i;
        synchronized (this) {
            i = this.h;
        }
        return i;
    }

    public void a(int i) {
        synchronized (this) {
            this.h = i;
        }
    }

    public boolean a(String str) {
        boolean send = (this.e == null || this.h != 1) ? false : this.e.send(str);
        if (!send) {
            a(0);
            g();
        }
        return send;
    }

    public boolean a(byte[] bArr) {
        if (this.e == null || this.h != 1) {
            return false;
        }
        return this.e.send(bArr);
    }

    static /* synthetic */ void a(f fVar, Throwable th, Response response) {
        e eVar = fVar.d;
        if (eVar != null) {
            eVar.a(th, response);
        }
    }

    static /* synthetic */ void g(f fVar) {
        fVar.l.removeCallbacks(fVar.p);
    }
}
