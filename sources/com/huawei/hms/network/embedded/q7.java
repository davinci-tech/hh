package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.g7;
import com.huawei.hms.network.embedded.j7;
import com.huawei.hms.network.embedded.t6;
import com.huawei.hms.network.embedded.v7;
import com.huawei.hms.network.embedded.z6;
import com.huawei.hms.network.embedded.z7;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public class q7 implements Cloneable, t6.a, z7.a {
    public static final List<r7> F = f8.a(r7.HTTP_2, r7.HTTP_1_1);
    public static final List<a7> G = f8.a(a7.h, a7.j);
    public static final int H = 100;
    public static final int I = 2000;
    public static final int J = 200;
    public final int A;
    public final int B;
    public final int C;
    public final int D;
    public final d E;

    /* renamed from: a, reason: collision with root package name */
    public final e7 f5437a;

    @Nullable
    public final Proxy b;
    public final List<r7> c;
    public final List<a7> d;
    public final List<n7> e;
    public final List<n7> f;
    public final g7.b g;
    public final ProxySelector h;
    public final c7 i;

    @Nullable
    public final r6 j;

    @Nullable
    public final n8 k;
    public final SocketFactory l;
    public final SSLSocketFactory m;
    public final qa n;
    public final HostnameVerifier o;
    public final v6 p;
    public final q6 q;
    public final q6 r;
    public final z6 s;
    public final f7 t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final int x;
    public final int y;
    public final int z;

    public static final class c {
        public int A;
        public int B;
        public int C;
        public int D;

        /* renamed from: a, reason: collision with root package name */
        public e7 f5439a;

        @Nullable
        public Proxy b;
        public List<r7> c;
        public List<a7> d;
        public final List<n7> e;
        public final List<n7> f;
        public g7.b g;
        public ProxySelector h;
        public c7 i;

        @Nullable
        public r6 j;

        @Nullable
        public n8 k;
        public SocketFactory l;

        @Nullable
        public SSLSocketFactory m;

        @Nullable
        public qa n;
        public HostnameVerifier o;
        public v6 p;
        public q6 q;
        public q6 r;
        public z6 s;
        public f7 t;
        public boolean u;
        public boolean v;
        public boolean w;
        public int x;
        public int y;
        public int z;

        public c f(long j, TimeUnit timeUnit) {
            this.A = f8.a("timeout", j, timeUnit);
            return this;
        }

        public c e(Duration duration) {
            this.A = f8.a("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public c e(long j, TimeUnit timeUnit) {
            this.z = f8.a("timeout", j, timeUnit);
            return this;
        }

        public c d(Duration duration) {
            this.z = f8.a("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public c d(long j, TimeUnit timeUnit) {
            this.B = f8.a("interval", j, timeUnit);
            return this;
        }

        public List<n7> c() {
            return this.f;
        }

        public c c(boolean z) {
            this.w = z;
            return this;
        }

        public c c(Duration duration) {
            this.B = f8.a("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public c c(long j, TimeUnit timeUnit) {
            int a2 = f8.a("connectionAttemptDelay", j, timeUnit);
            this.C = a2;
            if (a2 < 100 || a2 > 2000) {
                String str = "Connection Attempt Delay " + this.C + " ms is out of range (100ms ~ 2000ms).";
                this.C = 200;
                throw new IllegalArgumentException(str);
            }
            if (a2 < this.y) {
                return this;
            }
            String str2 = "Connection Attempt Delay " + this.C + " ms is out of range (100ms ~ 2000ms).";
            this.C = 200;
            throw new IllegalArgumentException(str2);
        }

        public List<n7> b() {
            return this.e;
        }

        public c b(boolean z) {
            this.u = z;
            return this;
        }

        public c b(List<r7> list) {
            ArrayList arrayList = new ArrayList(list);
            if (!arrayList.contains(r7.H2_PRIOR_KNOWLEDGE) && !arrayList.contains(r7.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols must contain h2_prior_knowledge or http/1.1: " + arrayList);
            }
            if (arrayList.contains(r7.H2_PRIOR_KNOWLEDGE) && arrayList.size() > 1) {
                throw new IllegalArgumentException("protocols containing h2_prior_knowledge cannot use other protocols: " + arrayList);
            }
            if (arrayList.contains(r7.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + arrayList);
            }
            if (arrayList.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            arrayList.remove(r7.SPDY_3);
            this.c = Collections.unmodifiableList(arrayList);
            return this;
        }

        public c b(Duration duration) {
            int i = this.y;
            int a2 = f8.a("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            this.y = a2;
            if (this.C < a2) {
                return this;
            }
            String str = "Connection Attempt Delay (" + this.C + " ms) is greater than or equal to Connect Timeout (" + this.y + " ms)";
            this.y = i;
            throw new IllegalArgumentException(str);
        }

        public c b(q6 q6Var) {
            if (q6Var == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.q = q6Var;
            return this;
        }

        public c b(n7 n7Var) {
            if (n7Var == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f.add(n7Var);
            return this;
        }

        public c b(long j, TimeUnit timeUnit) {
            this.y = f8.a("timeout", j, timeUnit);
            return this;
        }

        public q7 a() {
            return new q7(this);
        }

        public c a(boolean z) {
            this.v = z;
            return this;
        }

        public c a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            if (x509TrustManager == null) {
                throw new NullPointerException("trustManager == null");
            }
            this.m = sSLSocketFactory;
            this.n = qa.a(x509TrustManager);
            return this;
        }

        public c a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.m = sSLSocketFactory;
            this.n = ma.f().a(sSLSocketFactory);
            return this;
        }

        public c a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.o = hostnameVerifier;
            return this;
        }

        public c a(SocketFactory socketFactory) {
            if (socketFactory == null) {
                throw new NullPointerException("socketFactory == null");
            }
            if (socketFactory instanceof SSLSocketFactory) {
                throw new IllegalArgumentException("socketFactory instanceof SSLSocketFactory");
            }
            this.l = socketFactory;
            return this;
        }

        public c a(List<a7> list) {
            this.d = f8.a(list);
            return this;
        }

        public c a(Duration duration) {
            this.x = f8.a("timeout", duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public c a(ProxySelector proxySelector) {
            if (proxySelector == null) {
                throw new NullPointerException("proxySelector == null");
            }
            this.h = proxySelector;
            return this;
        }

        public c a(@Nullable Proxy proxy) {
            this.b = proxy;
            return this;
        }

        public c a(z6 z6Var) {
            if (z6Var == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.s = z6Var;
            return this;
        }

        public c a(v6 v6Var) {
            if (v6Var == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.p = v6Var;
            return this;
        }

        public c a(@Nullable r6 r6Var) {
            this.j = r6Var;
            this.k = null;
            return this;
        }

        public c a(q6 q6Var) {
            if (q6Var == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.r = q6Var;
            return this;
        }

        public c a(n7 n7Var) {
            if (n7Var == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.e.add(n7Var);
            return this;
        }

        public c a(g7 g7Var) {
            if (g7Var == null) {
                throw new NullPointerException("eventListener == null");
            }
            this.g = g7.a(g7Var);
            return this;
        }

        public c a(g7.b bVar) {
            if (bVar == null) {
                throw new NullPointerException("eventListenerFactory == null");
            }
            this.g = bVar;
            return this;
        }

        public c a(f7 f7Var) {
            if (f7Var == null) {
                throw new NullPointerException("dns == null");
            }
            this.t = f7Var;
            return this;
        }

        public c a(e7 e7Var) {
            if (e7Var == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.f5439a = e7Var;
            return this;
        }

        public c a(c7 c7Var) {
            if (c7Var == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.i = c7Var;
            return this;
        }

        public c a(long j, TimeUnit timeUnit) {
            this.x = f8.a("timeout", j, timeUnit);
            return this;
        }

        public c a(int i) {
            if (i < 0 || i > 255) {
                ma.f().a(5, "The trafficClass must be in the range 0 <= tc <= 255", (Throwable) null);
                return this;
            }
            this.D = i;
            return this;
        }

        public e7 a(r7 r7Var) {
            int i = b.f5438a[r7Var.ordinal()];
            if (i == 1) {
                return new k7();
            }
            if (i == 2 || i == 3 || i == 4) {
                return new e7();
            }
            throw new IllegalArgumentException("there is no dispatcher fit for the protocol " + r7Var.toString());
        }

        public c(q7 q7Var) {
            ArrayList arrayList = new ArrayList();
            this.e = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.f = arrayList2;
            this.f5439a = q7Var.f5437a;
            this.b = q7Var.b;
            this.c = q7Var.c;
            this.d = q7Var.d;
            arrayList.addAll(q7Var.e);
            arrayList2.addAll(q7Var.f);
            this.g = q7Var.g;
            this.h = q7Var.h;
            this.i = q7Var.i;
            this.k = q7Var.k;
            this.j = q7Var.j;
            this.l = q7Var.l;
            this.m = q7Var.m;
            this.n = q7Var.n;
            this.o = q7Var.o;
            this.p = q7Var.p;
            this.q = q7Var.q;
            this.r = q7Var.r;
            this.s = q7Var.s;
            this.t = q7Var.t;
            this.u = q7Var.u;
            this.v = q7Var.v;
            this.w = q7Var.w;
            this.x = q7Var.x;
            this.y = q7Var.y;
            this.z = q7Var.z;
            this.A = q7Var.A;
            this.B = q7Var.B;
            this.C = q7Var.C;
            this.D = q7Var.D;
        }

        public c() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.f5439a = new e7();
            this.c = q7.F;
            this.d = q7.G;
            this.g = g7.a(g7.NONE);
            ProxySelector proxySelector = ProxySelector.getDefault();
            this.h = proxySelector;
            if (proxySelector == null) {
                this.h = new na();
            }
            this.i = c7.f5202a;
            this.l = SocketFactory.getDefault();
            this.o = sa.f5480a;
            this.p = v6.c;
            q6 q6Var = q6.f5436a;
            this.q = q6Var;
            this.r = q6Var;
            this.s = new z6();
            this.t = f7.f5250a;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = 0;
            this.y = 10000;
            this.z = 10000;
            this.A = 10000;
            this.B = 0;
            this.D = 0;
            this.C = 200;
        }
    }

    public int z() {
        return this.z;
    }

    public ProxySelector y() {
        return this.h;
    }

    public q6 x() {
        return this.q;
    }

    @Nullable
    public Proxy w() {
        return this.b;
    }

    public List<r7> v() {
        return this.c;
    }

    public int u() {
        return this.B;
    }

    public c t() {
        return new c(this);
    }

    public List<n7> s() {
        return this.f;
    }

    @Nullable
    public n8 r() {
        r6 r6Var = this.j;
        return r6Var != null ? r6Var.f5454a : this.k;
    }

    public List<n7> q() {
        return this.e;
    }

    public HostnameVerifier p() {
        return this.o;
    }

    public int o() {
        return this.D;
    }

    public boolean n() {
        return this.u;
    }

    public boolean m() {
        return this.v;
    }

    public g7.b l() {
        return this.g;
    }

    public f7 k() {
        return this.t;
    }

    public e7 j() {
        return this.f5437a;
    }

    public c7 i() {
        return this.i;
    }

    public List<a7> h() {
        return this.d;
    }

    public z6 g() {
        return this.s;
    }

    public int f() {
        return this.C;
    }

    public int e() {
        return this.y;
    }

    public v6 d() {
        return this.p;
    }

    public boolean c(String str, int i, String str2) {
        return this.s.b(str, i, str2);
    }

    public int c() {
        return this.x;
    }

    @Nullable
    public r6 b() {
        return this.j;
    }

    public int b(String str, int i, String str2) {
        return this.s.a(str, i, str2);
    }

    public void a(String str, int i, String str2) {
        this.f5437a.a(str, i, str2);
    }

    @Override // com.huawei.hms.network.embedded.z7.a
    public z7 a(t7 t7Var, a8 a8Var) {
        ua uaVar = new ua(t7Var, a8Var, new Random(), this.B);
        uaVar.a(this);
        return uaVar;
    }

    public class a extends c8 {
        @Override // com.huawei.hms.network.embedded.c8
        public boolean a(p6 p6Var, p6 p6Var2) {
            return p6Var.a(p6Var2);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public void a(v7.a aVar, v8 v8Var) {
            aVar.a(v8Var);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public void a(j7.a aVar, String str, String str2) {
            aVar.b(str, str2);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public void a(j7.a aVar, String str) {
            aVar.b(str);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public void a(a7 a7Var, SSLSocket sSLSocket, boolean z) {
            a7Var.a(sSLSocket, z);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public z8 a(z6 z6Var) {
            return z6Var.delegate;
        }

        @Override // com.huawei.hms.network.embedded.c8
        @Nullable
        public v8 a(v7 v7Var) {
            return v7Var.m;
        }

        @Override // com.huawei.hms.network.embedded.c8
        public t6 a(q7 q7Var, t7 t7Var) {
            return s7.a(q7Var, t7Var, true);
        }

        @Override // com.huawei.hms.network.embedded.c8
        public int a(v7.a aVar) {
            return aVar.c;
        }
    }

    @Override // com.huawei.hms.network.embedded.t6.a
    public t6 a(t7 t7Var) {
        return s7.a(this, t7Var, false);
    }

    public q6 a() {
        return this.r;
    }

    public int D() {
        return this.A;
    }

    public SSLSocketFactory C() {
        return this.m;
    }

    public SocketFactory B() {
        return this.l;
    }

    public boolean A() {
        return this.w;
    }

    public static SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext b2 = ma.f().b();
            b2.init(null, new TrustManager[]{x509TrustManager}, null);
            return b2.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError("No System TLS", e);
        }
    }

    public class d implements z6.a {
        @Override // com.huawei.hms.network.embedded.z6.a
        public void a(String str, int i, String str2) {
            q7.this.f5437a.b(str, i, str2);
        }

        public /* synthetic */ d(q7 q7Var, a aVar) {
            this();
        }

        public d() {
        }
    }

    public static String E() {
        return g8.a();
    }

    public q7(c cVar) {
        boolean z;
        qa qaVar;
        this.E = new d(this, null);
        this.f5437a = cVar.f5439a;
        this.b = cVar.b;
        this.c = cVar.c;
        List<a7> list = cVar.d;
        this.d = list;
        this.e = f8.a(cVar.e);
        this.f = f8.a(cVar.f);
        this.g = cVar.g;
        this.h = cVar.h;
        this.i = cVar.i;
        this.j = cVar.j;
        this.k = cVar.k;
        this.l = cVar.l;
        Iterator<a7> it = list.iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z = z || it.next().b();
            }
        }
        if (cVar.m == null && z) {
            X509TrustManager a2 = f8.a();
            this.m = a(a2);
            qaVar = qa.a(a2);
        } else {
            this.m = cVar.m;
            qaVar = cVar.n;
        }
        this.n = qaVar;
        if (this.m != null) {
            ma.f().b(this.m);
        }
        this.o = cVar.o;
        this.p = cVar.p.a(qaVar);
        this.q = cVar.q;
        this.r = cVar.r;
        z6 z6Var = cVar.s;
        this.s = z6Var;
        this.t = cVar.t;
        this.u = cVar.u;
        this.v = cVar.v;
        this.w = cVar.w;
        this.x = cVar.x;
        this.y = cVar.y;
        this.z = cVar.z;
        this.A = cVar.A;
        this.B = cVar.B;
        this.D = cVar.D;
        if (this.e.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.e);
        }
        if (this.f.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.f);
        }
        this.C = cVar.C;
        z6Var.a(this.E);
    }

    public static /* synthetic */ class b {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5438a;

        static {
            int[] iArr = new int[r7.values().length];
            f5438a = iArr;
            try {
                iArr[r7.HTTP_2.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5438a[r7.HTTP_1_0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5438a[r7.HTTP_1_1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5438a[r7.SPDY_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public q7() {
        this(new c());
    }

    static {
        c8.f5203a = new a();
    }
}
