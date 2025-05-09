package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.wa;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class ua implements z7, wa.a {
    public static final List<r7> G = Collections.singletonList(r7.HTTP_1_1);
    public static final long H = 16777216;
    public static final long I = 60000;
    public static final int J = 1000;
    public static final int K = 1200000;
    public static final String L = "Sec-WebSocket-Extensions";
    public static final String M = "permessage-deflate";
    public static final String N = "client_no_context_takeover";
    public static final String O = "server_no_context_takeover";
    public static final int P = 4;
    public static final /* synthetic */ boolean Q = true;
    public boolean B;
    public bb C;
    public bb D;
    public Deflater E;
    public fb F;

    /* renamed from: a, reason: collision with root package name */
    public final t7 f5524a;
    public final a8 b;
    public final Random c;
    public long d;
    public final String e;
    public ScheduledFuture<?> f;
    public t6 g;
    public final Runnable h;
    public wa i;
    public xa j;
    public ScheduledExecutorService k;
    public f l;
    public long o;
    public boolean p;
    public ScheduledFuture<?> q;
    public String s;
    public boolean t;
    public int u;
    public int v;
    public int w;
    public boolean x;
    public long y;
    public final ArrayDeque<eb> m = new ArrayDeque<>();
    public final ArrayDeque<Object> n = new ArrayDeque<>();
    public int r = -1;
    public LinkedList<Long> z = new LinkedList<>();
    public boolean A = false;

    @Override // com.huawei.hms.network.embedded.z7
    public t7 request() {
        return this.f5524a;
    }

    public void l() {
        synchronized (this) {
            if (this.t) {
                return;
            }
            xa xaVar = this.j;
            int i = this.x ? this.u : -1;
            this.u++;
            this.x = true;
            if (i == -1) {
                try {
                    xaVar.a(eb.f);
                    this.y = System.currentTimeMillis();
                    return;
                } catch (IOException e2) {
                    a(e2, (v7) null);
                    return;
                }
            }
            a(new SocketTimeoutException("sent ping but didn't receive pong within " + this.d + "ms (after " + (i - 1) + " successful ping/pongs)"), (v7) null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v8 */
    public boolean k() throws IOException {
        f fVar;
        String str;
        synchronized (this) {
            if (this.t) {
                return false;
            }
            xa xaVar = this.j;
            eb poll = this.m.poll();
            int i = -1;
            d dVar = 0;
            r4 = null;
            f fVar2 = null;
            if (poll == null) {
                Object poll2 = this.n.poll();
                if (poll2 instanceof c) {
                    int i2 = this.r;
                    str = this.s;
                    if (i2 != -1) {
                        f fVar3 = this.l;
                        this.l = null;
                        this.k.shutdown();
                        fVar2 = fVar3;
                    } else {
                        this.q = this.k.schedule(new b(), ((c) poll2).c, TimeUnit.MILLISECONDS);
                    }
                    i = i2;
                } else {
                    if (poll2 == null) {
                        return false;
                    }
                    str = null;
                }
                fVar = fVar2;
                dVar = poll2;
            } else {
                fVar = null;
                str = null;
            }
            try {
                if (poll != null) {
                    xaVar.b(poll);
                } else if (dVar instanceof d) {
                    eb ebVar = dVar.b;
                    cb a2 = ob.a(xaVar.a(dVar.f5528a, ebVar.j()));
                    a2.a(ebVar);
                    a2.close();
                    synchronized (this) {
                        this.o -= ebVar.j();
                    }
                } else {
                    if (!(dVar instanceof c)) {
                        throw new AssertionError();
                    }
                    c cVar = (c) dVar;
                    xaVar.a(cVar.f5527a, cVar.b);
                    if (fVar != null) {
                        this.b.onClosed(this, i, str);
                    }
                }
                f8.a(fVar);
                return true;
            } catch (Throwable th) {
                f8.a(fVar);
                throw th;
            }
        }
    }

    public void j() throws InterruptedException {
        ScheduledFuture<?> scheduledFuture = this.q;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.k.shutdown();
        this.k.awaitTermination(10L, TimeUnit.SECONDS);
    }

    public int i() {
        int i;
        synchronized (this) {
            i = this.u;
        }
        return i;
    }

    public int h() {
        int i;
        synchronized (this) {
            i = this.w;
        }
        return i;
    }

    public int g() {
        int i;
        synchronized (this) {
            i = this.v;
        }
        return i;
    }

    public boolean f() throws IOException {
        try {
            this.i.a();
            return this.r == -1;
        } catch (Exception e2) {
            a(e2, (v7) null);
            return false;
        }
    }

    public boolean e(eb ebVar) {
        synchronized (this) {
            if (!this.t && (!this.p || !this.n.isEmpty())) {
                this.m.add(ebVar);
                o();
                return true;
            }
            return false;
        }
    }

    public void e() throws IOException {
        while (this.r == -1) {
            this.i.a();
        }
    }

    @Override // com.huawei.hms.network.embedded.wa.a
    public void d(eb ebVar) throws IOException {
        this.b.onMessage(this, ebVar);
    }

    public LinkedList<Long> d() {
        return this.z;
    }

    @Override // com.huawei.hms.network.embedded.z7
    public void cancel() {
        this.g.cancel();
    }

    @Override // com.huawei.hms.network.embedded.wa.a
    public void c(eb ebVar) {
        synchronized (this) {
            if (!this.t && (!this.p || !this.n.isEmpty())) {
                this.m.add(ebVar);
                o();
                this.v++;
            }
        }
    }

    public t6 c() {
        return this.g;
    }

    @Override // com.huawei.hms.network.embedded.z7
    public boolean b(String str) {
        if (str != null) {
            return a(eb.d(str), 1);
        }
        throw new NullPointerException("text == null");
    }

    @Override // com.huawei.hms.network.embedded.z7
    public boolean b(eb ebVar) {
        if (ebVar != null) {
            return a(ebVar, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    @Override // com.huawei.hms.network.embedded.wa.a
    public void b(int i, String str) {
        f fVar;
        if (i == -1) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            if (this.r != -1) {
                throw new IllegalStateException("already closed");
            }
            this.r = i;
            this.s = str;
            fVar = null;
            if (this.p && this.n.isEmpty()) {
                f fVar2 = this.l;
                this.l = null;
                ScheduledFuture<?> scheduledFuture = this.q;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                this.k.shutdown();
                fVar = fVar2;
            }
        }
        try {
            this.b.onClosing(this, i, str);
            if (fVar != null) {
                this.b.onClosed(this, i, str);
            }
        } finally {
            m();
            f8.a(fVar);
        }
    }

    public void b() {
        this.f.cancel(true);
        ma.f().a(4, "After sentPingCount = " + this.u + " receivedPongCount = " + this.w + " reset the ping interver to " + this.d, (Throwable) null);
        this.u = 0;
        this.w = 0;
        this.v = 0;
    }

    public boolean a(int i, String str, long j) {
        eb ebVar;
        synchronized (this) {
            va.b(i);
            if (str != null) {
                ebVar = eb.d(str);
                if (ebVar.j() > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: " + str);
                }
            } else {
                ebVar = null;
            }
            if (!this.t && !this.p) {
                this.p = true;
                this.n.add(new c(i, ebVar, j));
                o();
                return true;
            }
            return false;
        }
    }

    @Override // com.huawei.hms.network.embedded.z7
    public boolean a(int i, String str) {
        return a(i, str, 60000L);
    }

    public void a(String str, f fVar) throws IOException {
        synchronized (this) {
            this.l = fVar;
            this.j = new xa(fVar.f5530a, fVar.c, this.c, this.A);
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, f8.a(str, false));
            this.k = scheduledThreadPoolExecutor;
            if (this.d != 0) {
                e eVar = new e();
                long j = this.d;
                this.f = scheduledThreadPoolExecutor.scheduleAtFixedRate(eVar, j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.n.isEmpty()) {
                o();
            }
        }
        this.i = new wa(fVar.f5530a, fVar.b, this, this.A, this.B);
    }

    @Override // com.huawei.hms.network.embedded.wa.a
    public void a(String str) throws IOException {
        this.b.onMessage(this, str);
    }

    public void a(Exception exc, @Nullable v7 v7Var) {
        synchronized (this) {
            if (this.t) {
                return;
            }
            this.t = true;
            f fVar = this.l;
            this.l = null;
            ScheduledFuture<?> scheduledFuture = this.q;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.k;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
            }
            try {
                ma.f().a(5, "failWebSocket", exc);
                this.b.onFailure(this, exc, v7Var);
            } finally {
                f8.a(fVar);
                m();
            }
        }
    }

    public void a(v7 v7Var, @Nullable v8 v8Var) throws IOException {
        if (v7Var.w() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + v7Var.w() + " " + v7Var.B() + "'");
        }
        String b2 = v7Var.b("Connection");
        if (!"Upgrade".equalsIgnoreCase(b2)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + b2 + "'");
        }
        String b3 = v7Var.b("Upgrade");
        if (!"websocket".equalsIgnoreCase(b3)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + b3 + "'");
        }
        String b4 = v7Var.b("Sec-WebSocket-Accept");
        String b5 = eb.d(this.e + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").g().b();
        if (b5.equals(b4)) {
            if (v8Var == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + b5 + "' but was '" + b4 + "'");
    }

    public void a(v7 v7Var) {
        if (v7Var.b(L) != null && v7Var.b(L).contains(M)) {
            this.A = true;
            if (v7Var.b(L).contains(N) && v7Var.b(L).contains(O)) {
                this.B = true;
            }
        }
        if (this.A) {
            this.C = new bb();
            this.D = new bb();
            Deflater deflater = new Deflater(-1, true);
            this.E = deflater;
            this.F = new fb((cb) this.D, deflater);
        }
    }

    public void a(q7 q7Var) {
        q7 a2 = q7Var.t().b(G).a();
        t7 a3 = this.f5524a.i().b("Upgrade", "websocket").b("Connection", "Upgrade").b("Sec-WebSocket-Key", this.e).b("Sec-WebSocket-Version", "13").a();
        t6 a4 = c8.f5203a.a(a2, a3);
        this.g = a4;
        a4.enqueue(new a(a3));
    }

    @Override // com.huawei.hms.network.embedded.wa.a
    public void a(eb ebVar) {
        synchronized (this) {
            this.w++;
            this.x = false;
            if (this.y != 0) {
                this.z.add(Long.valueOf(System.currentTimeMillis() - this.y));
                if (this.z.size() > 5) {
                    this.z.remove(0);
                }
            }
            this.b.onReadPong(this.d, this.z);
        }
    }

    public void a(long j) {
        if (j < 1000 || j > 1200000 || this.f == null) {
            ma.f().a(5, "WebSocket resetPingInterval param " + j + " error. The interval ranges are [1000,1200000]ms", (Throwable) null);
            return;
        }
        this.d = j;
        try {
            b();
            ScheduledExecutorService scheduledExecutorService = this.k;
            e eVar = new e();
            long j2 = this.d;
            this.f = scheduledExecutorService.scheduleAtFixedRate(eVar, j2, j2, TimeUnit.MILLISECONDS);
        } catch (RuntimeException e2) {
            ma.f().a(4, "Start new websocket interval ping error", e2);
        }
    }

    public void a(int i, TimeUnit timeUnit) throws InterruptedException {
        this.k.awaitTermination(i, timeUnit);
    }

    @Override // com.huawei.hms.network.embedded.z7
    public long a() {
        long j;
        synchronized (this) {
            j = this.o;
        }
        return j;
    }

    private void o() {
        if (!Q && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        ScheduledExecutorService scheduledExecutorService = this.k;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.execute(this.h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        do {
            try {
            } catch (IOException e2) {
                a(e2, (v7) null);
                return;
            }
        } while (k());
    }

    private void m() {
        synchronized (this) {
            if (this.A) {
                f8.a(this.C);
                f8.a(this.D);
                f8.a(this.F);
                this.A = false;
            }
        }
    }

    private eb f(eb ebVar) throws IOException {
        if (this.B) {
            this.E.reset();
        }
        this.C.a(ebVar);
        fb fbVar = this.F;
        bb bbVar = this.C;
        fbVar.write(bbVar, bbVar.B());
        this.F.flush();
        return this.D.r().a(0, r4.j() - 4);
    }

    public class a implements u6 {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t7 f5525a;

        @Override // com.huawei.hms.network.embedded.u6
        public void onResponse(t6 t6Var, v7 v7Var) {
            v8 a2 = c8.f5203a.a(v7Var);
            try {
                ua.this.a(v7Var, a2);
                f g = a2.g();
                ua.this.a(v7Var);
                try {
                    ua.this.a("OkHttp WebSocket " + this.f5525a.k().r(), g);
                    ua.this.b.onOpen(ua.this, v7Var);
                    ua.this.e();
                } catch (Exception e) {
                    ua.this.a(e, (v7) null);
                }
            } catch (IOException e2) {
                if (a2 != null) {
                    a2.m();
                }
                ua.this.a(e2, v7Var);
                f8.a(v7Var);
            }
        }

        @Override // com.huawei.hms.network.embedded.u6
        public void onFailure(t6 t6Var, IOException iOException) {
            ua.this.a(iOException, (v7) null);
        }

        public a(t7 t7Var) {
            this.f5525a = t7Var;
        }
    }

    private boolean a(eb ebVar, int i) {
        synchronized (this) {
            if (!this.t && !this.p) {
                if (this.A) {
                    try {
                        ebVar = f(ebVar);
                    } catch (Exception e2) {
                        ma.f().a(5, e2.getMessage(), e2);
                        a(e2, (v7) null);
                    }
                }
                if (ebVar == null) {
                    return false;
                }
                if (this.o + ebVar.j() > H) {
                    a(1001, (String) null);
                    return false;
                }
                this.o += ebVar.j();
                this.n.add(new d(i, ebVar));
                o();
                return true;
            }
            return false;
        }
    }

    public final class b implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ua.this.cancel();
        }

        public b() {
        }
    }

    public final class e implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ua.this.l();
        }

        public e() {
        }
    }

    public ua(t7 t7Var, a8 a8Var, Random random, long j) {
        if (!"GET".equals(t7Var.h())) {
            throw new IllegalArgumentException("Request must be GET: " + t7Var.h());
        }
        this.f5524a = t7Var;
        this.b = a8Var;
        this.c = random;
        this.d = j;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.e = eb.e(bArr).b();
        this.h = new Runnable() { // from class: com.huawei.hms.network.embedded.ua$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ua.this.n();
            }
        };
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public final int f5527a;
        public final eb b;
        public final long c;

        public c(int i, eb ebVar, long j) {
            this.f5527a = i;
            this.b = ebVar;
            this.c = j;
        }
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public final int f5528a;
        public final eb b;

        public d(int i, eb ebVar) {
            this.f5528a = i;
            this.b = ebVar;
        }
    }

    public static abstract class f implements Closeable {

        /* renamed from: a, reason: collision with root package name */
        public final boolean f5530a;
        public final db b;
        public final cb c;

        public f(boolean z, db dbVar, cb cbVar) {
            this.f5530a = z;
            this.b = dbVar;
            this.c = cbVar;
        }
    }
}
