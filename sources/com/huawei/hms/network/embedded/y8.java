package com.huawei.hms.network.embedded;

import androidx.core.location.LocationRequestCompat;
import com.huawei.hms.network.embedded.c9;
import com.huawei.hms.network.embedded.n7;
import com.huawei.hms.network.embedded.t7;
import com.huawei.hms.network.embedded.ua;
import com.huawei.hms.network.embedded.v7;
import com.huawei.hms.network.embedded.v9;
import com.huawei.openalliance.ad.constant.ErrorCode;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes9.dex */
public final class y8 extends v9.j implements y6 {
    public static final int A = 4;
    public static final long B = 1000000000;
    public static final int C = 1000;
    public static final /* synthetic */ boolean D = true;
    public static final String y = "throw with null exception";
    public static final int z = 21;
    public final z8 b;
    public x7 c;
    public Socket d;
    public Socket e;
    public i7 f;
    public r7 g;
    public v9 h;
    public db i;
    public cb j;
    public boolean k;
    public int l;
    public int m;
    public int n;
    public int o = 1;
    public final List<Reference<d9>> p = new ArrayList();
    public long q = LocationRequestCompat.PASSIVE_INTERVAL;
    public c9.a r = null;
    public s8 s = null;
    public x7 t = null;
    public long u = 0;
    public int v = 0;
    public int w = 0;
    public long x = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder("Connection{");
        sb.append(this.c.a().l().h());
        sb.append(":");
        sb.append(this.c.a().l().n());
        sb.append(", proxy=");
        sb.append(this.c.b());
        sb.append(" hostAddress=");
        sb.append(this.c.d());
        sb.append(" cipherSuite=");
        i7 i7Var = this.f;
        sb.append(i7Var != null ? i7Var.a() : "none");
        sb.append(" protocol=");
        sb.append(this.g);
        sb.append('}');
        return sb.toString();
    }

    public void h() {
        if (!D && Thread.holdsLock(this.b)) {
            throw new AssertionError();
        }
        synchronized (this.b) {
            this.k = true;
        }
    }

    public boolean g() {
        return this.h != null;
    }

    public c9.a f() {
        return this.r;
    }

    public void e() {
        s8 s8Var = this.s;
        if (s8Var != null) {
            s8Var.a();
        }
        f8.a(this.d);
    }

    @Override // com.huawei.hms.network.embedded.y6
    public r7 d() {
        return this.g;
    }

    @Override // com.huawei.hms.network.embedded.y6
    public Socket c() {
        return this.e;
    }

    @Override // com.huawei.hms.network.embedded.y6
    public x7 b() {
        return this.c;
    }

    public boolean a(boolean z2) {
        if (this.e.isClosed() || this.e.isInputShutdown() || this.e.isOutputShutdown()) {
            return false;
        }
        v9 v9Var = this.h;
        if (v9Var != null) {
            return v9Var.j(System.nanoTime());
        }
        if (z2) {
            try {
                int soTimeout = this.e.getSoTimeout();
                try {
                    this.e.setSoTimeout(1);
                    return !this.i.i();
                } finally {
                    this.e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean a(p6 p6Var, @Nullable List<x7> list) {
        if (this.p.size() >= this.o || this.k || !c8.f5203a.a(this.c.a(), p6Var)) {
            return false;
        }
        if (p6Var.l().h().equals(b().a().l().h())) {
            return true;
        }
        if (this.h == null || list == null || !a(list) || p6Var.e() != sa.f5480a || !a(p6Var.l())) {
            return false;
        }
        try {
            p6Var.a().a(p6Var.l().h(), a().d());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean a(m7 m7Var) {
        if (m7Var.n() != this.c.a().l().n()) {
            return false;
        }
        if (m7Var.h().equals(this.c.a().l().h())) {
            return true;
        }
        return this.f != null && sa.f5480a.a(m7Var.h(), (X509Certificate) this.f.d().get(0));
    }

    public void a(CopyOnWriteArrayList<InetSocketAddress> copyOnWriteArrayList, int i, int i2) {
        if (copyOnWriteArrayList != null) {
            this.s = r8.a(copyOnWriteArrayList, i, i2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        if (r5 > 1) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(@javax.annotation.Nullable java.io.IOException r5) {
        /*
            r4 = this;
            boolean r0 = com.huawei.hms.network.embedded.y8.D
            if (r0 != 0) goto L13
            com.huawei.hms.network.embedded.z8 r0 = r4.b
            boolean r0 = java.lang.Thread.holdsLock(r0)
            if (r0 != 0) goto Ld
            goto L13
        Ld:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L13:
            com.huawei.hms.network.embedded.z8 r0 = r4.b
            monitor-enter(r0)
            boolean r1 = r5 instanceof com.huawei.hms.network.embedded.da     // Catch: java.lang.Throwable -> L52
            r2 = 1
            if (r1 == 0) goto L32
            com.huawei.hms.network.embedded.da r5 = (com.huawei.hms.network.embedded.da) r5     // Catch: java.lang.Throwable -> L52
            com.huawei.hms.network.embedded.r9 r5 = r5.f5222a     // Catch: java.lang.Throwable -> L52
            com.huawei.hms.network.embedded.r9 r1 = com.huawei.hms.network.embedded.r9.REFUSED_STREAM     // Catch: java.lang.Throwable -> L52
            if (r5 != r1) goto L2d
            int r5 = r4.n     // Catch: java.lang.Throwable -> L52
            int r5 = r5 + r2
            r4.n = r5     // Catch: java.lang.Throwable -> L52
            if (r5 <= r2) goto L50
        L2a:
            r4.k = r2     // Catch: java.lang.Throwable -> L52
            goto L4b
        L2d:
            com.huawei.hms.network.embedded.r9 r1 = com.huawei.hms.network.embedded.r9.CANCEL     // Catch: java.lang.Throwable -> L52
            if (r5 == r1) goto L50
            goto L2a
        L32:
            boolean r1 = r4.g()     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L3c
            boolean r1 = r5 instanceof com.huawei.hms.network.embedded.q9     // Catch: java.lang.Throwable -> L52
            if (r1 == 0) goto L50
        L3c:
            r4.k = r2     // Catch: java.lang.Throwable -> L52
            int r1 = r4.m     // Catch: java.lang.Throwable -> L52
            if (r1 != 0) goto L50
            if (r5 == 0) goto L4b
            com.huawei.hms.network.embedded.z8 r1 = r4.b     // Catch: java.lang.Throwable -> L52
            com.huawei.hms.network.embedded.x7 r3 = r4.c     // Catch: java.lang.Throwable -> L52
            r1.a(r3, r5)     // Catch: java.lang.Throwable -> L52
        L4b:
            int r5 = r4.l     // Catch: java.lang.Throwable -> L52
            int r5 = r5 + r2
            r4.l = r5     // Catch: java.lang.Throwable -> L52
        L50:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            return
        L52:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.y8.a(java.io.IOException):void");
    }

    @Override // com.huawei.hms.network.embedded.v9.j
    public void a(y9 y9Var) throws IOException {
        y9Var.a(r9.REFUSED_STREAM, (IOException) null);
    }

    @Override // com.huawei.hms.network.embedded.v9.j
    public void a(v9 v9Var) {
        synchronized (this.b) {
            this.o = v9Var.t();
        }
    }

    public void a(c9.a aVar) {
        this.r = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ed A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r15, int r16, int r17, int r18, int r19, boolean r20, com.huawei.hms.network.embedded.t6 r21, com.huawei.hms.network.embedded.g7 r22) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.y8.a(int, int, int, int, int, boolean, com.huawei.hms.network.embedded.t6, com.huawei.hms.network.embedded.g7):void");
    }

    public ua.f a(v8 v8Var) throws SocketException {
        this.e.setSoTimeout(0);
        h();
        return new a(true, this.i, this.j, v8Var);
    }

    @Override // com.huawei.hms.network.embedded.y6
    public i7 a() {
        return this.f;
    }

    public g9 a(q7 q7Var, n7.a aVar) throws SocketException {
        v9 v9Var = this.h;
        if (v9Var != null) {
            return new w9(q7Var, this, aVar, v9Var);
        }
        this.e.setSoTimeout(aVar.c());
        this.i.timeout().timeout(aVar.c(), TimeUnit.MILLISECONDS);
        this.j.timeout().timeout(aVar.b(), TimeUnit.MILLISECONDS);
        return new p9(q7Var, this, this.i, this.j);
    }

    private t7 i() throws IOException {
        t7 a2 = new t7.a().a(this.c.a().l()).a("CONNECT", (u7) null).b("Host", f8.a(this.c.a().l(), true)).b("Proxy-Connection", "Keep-Alive").b("User-Agent", g8.a()).a();
        t7 b = this.c.a().h().b(this.c, new v7.a().a(a2).a(r7.HTTP_1_1).a(ErrorCode.ERROR_CODE_NO_SDKVERSION).a("Preemptive Authenticate").a(f8.d).b(-1L).a(-1L).b("Proxy-Authenticate", "OkHttp-Preemptive").a());
        return b != null ? b : a2;
    }

    private boolean a(List<x7> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            x7 x7Var = list.get(i);
            if (x7Var.b().type() == Proxy.Type.DIRECT && this.c.b().type() == Proxy.Type.DIRECT && this.c.d().equals(x7Var.d())) {
                return true;
            }
        }
        return false;
    }

    private void a(u8 u8Var, int i, t6 t6Var, g7 g7Var) throws IOException {
        if (this.c.a().k() != null) {
            g7Var.secureConnectStart(t6Var);
            a(u8Var);
            g7Var.secureConnectEnd(t6Var, this.f);
            if (this.g == r7.HTTP_2) {
                a(i);
                return;
            }
            return;
        }
        if (!this.c.a().f().contains(r7.H2_PRIOR_KNOWLEDGE)) {
            this.e = this.d;
            this.g = r7.HTTP_1_1;
        } else {
            this.e = this.d;
            this.g = r7.H2_PRIOR_KNOWLEDGE;
            a(i);
        }
    }

    private void a(u8 u8Var) throws IOException {
        SSLSocket sSLSocket;
        p6 a2 = this.c.a();
        try {
            try {
                sSLSocket = (SSLSocket) a2.k().createSocket(this.d, a2.l().h(), a2.l().n(), true);
            } catch (AssertionError e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
            sSLSocket = null;
        }
        try {
            String d = a2.d();
            if (d != null && d.length() != 0) {
                d = m7.f(a2.l().s() + "://" + d).h();
            }
            if (d == null || d.length() == 0) {
                d = a2.l().h();
            }
            a7 a3 = u8Var.a(sSLSocket);
            if (a3.c()) {
                ma.f().a(sSLSocket, d, a2.f());
            }
            sSLSocket.startHandshake();
            SSLSession session = sSLSocket.getSession();
            i7 a4 = i7.a(session);
            if (a2.e().verify(d, session)) {
                a2.a().a(a2.l().h(), a4.d());
                String b = a3.c() ? ma.f().b(sSLSocket) : null;
                this.e = sSLSocket;
                this.i = ob.a(ob.b(sSLSocket));
                this.j = ob.a(ob.a(this.e));
                this.f = a4;
                this.g = b != null ? r7.a(b) : r7.HTTP_1_1;
                if (sSLSocket != null) {
                    ma.f().a(sSLSocket);
                    return;
                }
                return;
            }
            List<Certificate> d2 = a4.d();
            if (d2.isEmpty()) {
                throw new SSLPeerUnverifiedException("Hostname " + a2.l().h() + " not verified (no certificates)");
            }
            X509Certificate x509Certificate = (X509Certificate) d2.get(0);
            throw new SSLPeerUnverifiedException("Hostname " + d + " not verified:\n    certificate: " + v6.a((Certificate) x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + sa.a(x509Certificate));
        } catch (AssertionError e2) {
            e = e2;
            if (!f8.a(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            if (sSLSocket != null) {
                ma.f().a(sSLSocket);
            }
            f8.a((Socket) sSLSocket);
            throw th;
        }
    }

    private void a(int i, int i2, int i3, t6 t6Var, g7 g7Var) throws IOException {
        long currentTimeMillis;
        x7 e;
        x7 x7Var = this.t;
        if (x7Var == null) {
            x7Var = this.c;
        }
        Proxy b = x7Var.b();
        p6 a2 = x7Var.a();
        if (this.s == null || this.t != null) {
            this.d = (b.type() == Proxy.Type.DIRECT || b.type() == Proxy.Type.HTTP) ? a2.j().createSocket() : new Socket(b);
            g7Var.connectStart(t6Var, this.c.d(), b);
            currentTimeMillis = System.currentTimeMillis();
            this.d.setSoTimeout(i2);
            this.d.setTrafficClass(i3);
            try {
                ma.f().a(this.d, x7Var.d(), i);
            } catch (ConnectException e2) {
                ConnectException connectException = new ConnectException("Failed to connect to " + x7Var.d());
                connectException.initCause(e2);
                throw connectException;
            }
        } else {
            currentTimeMillis = System.currentTimeMillis();
            this.d = this.s.a(i, a2.j(), b, t6Var, g7Var);
            if (this.s.l != null && (e = this.r.e()) != null) {
                this.r.b(new x7(e.a(), e.b(), this.s.l));
            }
            c9.a aVar = this.r;
            if (aVar != null) {
                aVar.a(this.s.b());
                Socket socket = this.d;
                if (socket != null) {
                    this.r.a((InetSocketAddress) socket.getRemoteSocketAddress());
                }
            }
            if (this.d == null) {
                throw new ConnectException("Failed to connect to host " + this.c.a().l().h());
            }
            x7 x7Var2 = new x7(this.c.a(), this.c.b(), (InetSocketAddress) this.d.getRemoteSocketAddress());
            this.t = x7Var2;
            this.c = x7Var2;
            this.d.setSoTimeout(i2);
            this.d.setTrafficClass(i3);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        this.u = currentTimeMillis2;
        if (i2 != 0) {
            this.w = ((int) ((currentTimeMillis2 * 4) + 1000)) / i2;
        }
        try {
            this.i = ob.a(ob.b(this.d));
            this.j = ob.a(ob.a(this.d));
        } catch (NullPointerException e3) {
            if (y.equals(e3.getMessage())) {
                throw new IOException(e3);
            }
        }
    }

    private void a(int i, int i2, int i3, int i4, t6 t6Var, g7 g7Var) throws IOException {
        t7 i5 = i();
        m7 k = i5.k();
        for (int i6 = 0; i6 < 21; i6++) {
            a(i, i2, i4, t6Var, g7Var);
            i5 = a(i2, i3, i5, k);
            if (i5 == null) {
                return;
            }
            f8.a(this.d);
            this.d = null;
            this.j = null;
            this.i = null;
            g7Var.connectEnd(t6Var, this.c.d(), this.c.b(), null);
        }
    }

    private void a(int i) throws IOException {
        this.e.setSoTimeout(0);
        v9 a2 = new v9.h(true).a(this.e, this.c.a().l().h(), this.i, this.j).a(this).a(i).a();
        this.h = a2;
        a2.w();
    }

    public static y8 a(z8 z8Var, x7 x7Var, Socket socket, long j) {
        y8 y8Var = new y8(z8Var, x7Var);
        y8Var.e = socket;
        y8Var.q = j;
        return y8Var;
    }

    public class a extends ua.f {
        public final /* synthetic */ v8 d;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.d.a(-1L, true, true, null);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(boolean z, db dbVar, cb cbVar, v8 v8Var) {
            super(z, dbVar, cbVar);
            this.d = v8Var;
        }
    }

    private t7 a(int i, int i2, t7 t7Var, m7 m7Var) throws IOException {
        String str = "CONNECT " + f8.a(m7Var, true) + " HTTP/1.1";
        while (true) {
            p9 p9Var = new p9(null, null, this.i, this.j);
            this.i.timeout().timeout(i, TimeUnit.MILLISECONDS);
            this.j.timeout().timeout(i2, TimeUnit.MILLISECONDS);
            p9Var.a(t7Var.e(), str);
            p9Var.c();
            v7 a2 = p9Var.a(false).a(t7Var).a();
            p9Var.c(a2);
            int w = a2.w();
            if (w == 200) {
                if (this.i.g().i() && this.j.a().i()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (w != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + a2.w());
            }
            t7 b = this.c.a().h().b(this.c, a2);
            if (b == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if ("close".equalsIgnoreCase(a2.b("Connection"))) {
                return b;
            }
            t7Var = b;
        }
    }

    public y8(z8 z8Var, x7 x7Var) {
        this.b = z8Var;
        this.c = x7Var;
    }
}
