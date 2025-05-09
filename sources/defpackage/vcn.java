package defpackage;

import defpackage.vab;
import defpackage.vbt;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.eclipse.californium.scandium.dtls.DTLSConnectionState;
import org.slf4j.Logger;

/* loaded from: classes7.dex */
public final class vcn implements Destroyable {

    /* renamed from: a, reason: collision with root package name */
    private int f17669a;
    private SecretKey b;
    private SecretKey d;
    private boolean f;
    private final long g;
    private int h;
    private volatile long i;
    private vcp j;
    private volatile long k;
    private long l;
    private DTLSConnectionState m;
    private int n;
    private volatile long o;
    private volatile long p;
    private boolean q;
    private long[] r;
    private vcp s;
    private final vct t;
    private int u;
    private DTLSConnectionState v;
    private static final Logger e = vha.a((Class<?>) vcn.class);
    private static final vbt.a c = new vbt.a(3, 2);

    public vcn(long j) {
        this.s = null;
        this.j = null;
        this.m = DTLSConnectionState.NULL;
        this.v = DTLSConnectionState.NULL;
        this.b = null;
        this.d = null;
        this.h = 0;
        this.u = 0;
        this.r = new long[2];
        this.o = -1L;
        this.k = 0L;
        this.p = 0L;
        this.i = 0L;
        if (j < 0 || j > 281474976710655L) {
            throw new IllegalArgumentException("Initial sequence number must be greater than 0 and less than 2^48");
        }
        this.t = new vct();
        this.g = System.currentTimeMillis();
        this.r[0] = j;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() throws DestroyFailedException {
        vfh.a(this.t);
        vfh.e(this.b);
        this.b = null;
        vfh.e(this.d);
        this.d = null;
        if (this.m != DTLSConnectionState.NULL) {
            this.m.destroy();
            this.m = DTLSConnectionState.NULL;
        }
        if (this.v != DTLSConnectionState.NULL) {
            this.v.destroy();
            this.v = DTLSConnectionState.NULL;
        }
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return vfh.d(this.t) && vfh.d(this.m) && vfh.d(this.v) && vfh.b(this.d) && vfh.b(this.b);
    }

    public vct a() {
        return this.t;
    }

    public boolean m() {
        return this.q;
    }

    void e(boolean z) {
        this.q = z;
    }

    public vcp j() {
        return this.s;
    }

    void d(vcp vcpVar) {
        this.s = vcpVar;
    }

    public vcp d() {
        return this.j;
    }

    void a(vcp vcpVar) {
        this.j = vcpVar;
    }

    public void a(SecretKey secretKey, SecretKey secretKey2) {
        this.b = vfh.a(secretKey);
        this.d = vfh.a(secretKey2);
    }

    public void e(vab.d dVar) {
        b(dVar, this.u);
    }

    public void a(vab.d dVar) {
        b(dVar, this.h);
    }

    private void b(vab.d dVar, int i) {
        this.t.d(dVar);
        dVar.d(uzz.g, Integer.valueOf(i));
        dVar.d(uzz.o, Long.valueOf(this.g));
        if (this.s != null && this.j != null) {
            dVar.d(uzz.l, this.j);
            dVar.d(uzz.r, this.s);
        }
        if (this.f17669a > 0) {
            dVar.d(uzz.n, Integer.valueOf(this.f17669a));
        }
    }

    public int h() {
        return this.u;
    }

    public int b() {
        return this.h;
    }

    void g() {
        n();
        this.h++;
    }

    private void k() {
        int i = this.u + 1;
        this.u = i;
        this.r[i] = 0;
    }

    public long c() {
        return e(this.u);
    }

    public long e(int i) {
        long[] jArr = this.r;
        long j = jArr[i];
        if (j <= 281474976710655L) {
            jArr[i] = 1 + j;
            return j;
        }
        throw new IllegalStateException("Maximum sequence number for epoch has been reached");
    }

    public DTLSConnectionState e() {
        return this.m;
    }

    public void e(SecretKey secretKey, vff vffVar, SecretKey secretKey2) {
        DTLSConnectionState create = DTLSConnectionState.create(this.t.e(), this.t.c(), secretKey, vffVar, secretKey2);
        vfh.a(this.m);
        this.m = create;
        g();
        e.trace("Setting current read state to{}{}", vcb.a(), create);
    }

    public DTLSConnectionState i() {
        return c(this.u);
    }

    DTLSConnectionState c(int i) {
        if (i == 0) {
            return DTLSConnectionState.NULL;
        }
        return this.v;
    }

    public void a(SecretKey secretKey, vff vffVar, SecretKey secretKey2) {
        DTLSConnectionState create = DTLSConnectionState.create(this.t.e(), this.t.c(), secretKey, vffVar, secretKey2);
        vfh.a(this.v);
        this.v = create;
        k();
        e.trace("Setting current write state to{}{}", vcb.a(), create);
    }

    public void b(int i) {
        this.f17669a = i;
    }

    public boolean d(int i, long j, int i2) {
        int b = b();
        if (i != b) {
            throw new IllegalArgumentException("wrong epoch! " + i + " != " + b);
        }
        if (j < this.k) {
            return i2 < 0 || j > this.k - ((long) i2);
        }
        if (this.f) {
            int i3 = this.n;
            if (i > i3) {
                return false;
            }
            if (i == i3 && j >= this.l) {
                return false;
            }
        }
        return !a(j);
    }

    boolean a(long j) {
        if (j > this.o) {
            return false;
        }
        long j2 = 1 << ((int) (j - this.k));
        Logger logger = e;
        if (logger.isDebugEnabled()) {
            logger.debug("Checking sequence no [{}] using bit mask [{}] against received records [{}] with lower boundary [{}]", Long.valueOf(j), Long.toBinaryString(j2), Long.toBinaryString(this.p), Long.valueOf(this.k));
        }
        return (this.p & j2) == j2;
    }

    public boolean e(int i, long j) {
        int b = b();
        if (i != b) {
            throw new IllegalArgumentException("wrong epoch! " + i + " != " + b);
        }
        boolean z = j > this.o;
        if (z) {
            this.o = j;
            long max = Math.max(0L, j - 63);
            long j2 = max - this.k;
            if (j2 > 0) {
                this.p >>>= (int) j2;
                this.k = max;
            }
        }
        this.p = (1 << ((int) (j - this.k))) | this.p;
        Logger logger = e;
        if (logger.isDebugEnabled()) {
            logger.debug("Updated receive window with sequence number [{}]: new upper boundary [{}], new bit vector [{}]", Long.valueOf(j), Long.valueOf(this.o), Long.toBinaryString(this.p));
        }
        return z;
    }

    public boolean l() {
        return this.f;
    }

    public void b(int i, long j) {
        this.f = true;
        this.n = i;
        this.l = j;
    }

    public int hashCode() {
        int i;
        long j;
        long j2 = this.g;
        int i2 = ((int) (j2 ^ (j2 >>> 32))) + 31;
        if (this.f) {
            i = ((i2 * 31) + this.n) * 31;
            j = this.l;
        } else {
            i = ((i2 * 31) + this.h) * 31;
            j = this.o;
        }
        int i3 = (int) j;
        int i4 = this.u;
        int i5 = (int) this.r[i4];
        int i6 = (int) this.k;
        int i7 = (int) (this.p ^ (this.p >>> 32));
        vcp vcpVar = this.j;
        int hashCode = vcpVar == null ? 0 : vcpVar.hashCode();
        vcp vcpVar2 = this.s;
        return ((((((((((((((((((i + i3) * 31) + i4) * 31) + i5) * 31) + i6) * 31) + i7) * 31) + hashCode) * 31) + (vcpVar2 != null ? vcpVar2.hashCode() : 0)) * 31) + (this.q ? 1 : 0)) * 31) + this.f17669a) * 31) + this.t.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z;
        int i;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        vcn vcnVar = (vcn) obj;
        if (this.t.equals(vcnVar.t) && this.g == vcnVar.g && (z = this.f) == vcnVar.f) {
            return (!z || (this.n == vcnVar.n && this.l == vcnVar.l)) && vbj.d(this.j, vcnVar.j) && vbj.d(this.s, vcnVar.s) && this.h == vcnVar.h && this.k == vcnVar.k && this.o == vcnVar.o && this.p == vcnVar.p && (i = this.u) == vcnVar.u && this.r[i] == vcnVar.r[i] && this.q == vcnVar.q && this.f17669a == vcnVar.f17669a;
        }
        return false;
    }

    private void n() {
        this.p = 0L;
        this.o = -1L;
        this.k = 0L;
    }

    public void f() {
        this.i++;
    }

    public boolean e(vbo vboVar) {
        if (this.f) {
            return false;
        }
        int c2 = vbt.c(vboVar, 3, 16);
        vboVar.c(this.g, 64);
        this.t.d(vboVar);
        vboVar.b(this.h, 8);
        if (this.h > 0) {
            e().writeTo(vboVar);
        }
        vboVar.b(this.u, 8);
        if (this.u > 0) {
            i().writeTo(vboVar);
        }
        vboVar.b(this.s, 8);
        a(vboVar);
        vboVar.d(this.q ? (byte) 1 : (byte) 0);
        vboVar.b(this.f17669a, 16);
        vbt.d(vboVar, c2, 16);
        return true;
    }

    public static vcn a(vbn vbnVar) {
        vbt.e d = c.d();
        int a2 = vbt.a(vbnVar, d, 16);
        if (a2 <= 0) {
            return null;
        }
        return new vcn(d.a(), vbnVar.b(a2));
    }

    private vcn(int i, vbn vbnVar) {
        this.s = null;
        this.j = null;
        this.m = DTLSConnectionState.NULL;
        this.v = DTLSConnectionState.NULL;
        this.b = null;
        this.d = null;
        this.h = 0;
        this.u = 0;
        this.r = new long[2];
        this.o = -1L;
        this.k = 0L;
        this.p = 0L;
        this.i = 0L;
        this.g = vbnVar.d(64);
        vct b = vct.b(vbnVar);
        this.t = b;
        if (b == null) {
            throw new IllegalArgumentException("read session must not be null!");
        }
        int c2 = vbnVar.c(8);
        this.h = c2;
        if (c2 > 0) {
            this.m = DTLSConnectionState.fromReader(b.e(), b.c(), vbnVar);
        }
        int c3 = vbnVar.c(8);
        this.u = c3;
        if (c3 == 1) {
            this.v = DTLSConnectionState.fromReader(b.e(), b.c(), vbnVar);
        } else if (c3 > 1) {
            throw new IllegalArgumentException("write epoch must be 1!");
        }
        byte[] h = vbnVar.h(8);
        if (h != null) {
            this.s = new vcp(h);
        }
        d(vbnVar);
        if (i == 2) {
            this.q = true;
            this.f17669a = 0;
        } else if (i == 3) {
            this.q = vbnVar.c() == 1;
            this.f17669a = vbnVar.c(16);
        }
        vbnVar.b("dtls-context");
    }

    public void a(vbo vboVar) {
        int c2 = vbt.c(vboVar, 1, 8);
        vboVar.c(this.r[this.u], 48);
        vboVar.c(this.k, 48);
        vboVar.c(this.p, 64);
        vboVar.c(this.i, 64);
        vbt.d(vboVar, c2, 8);
    }

    public void d(vbn vbnVar) {
        int a2 = vbt.a(vbnVar, 1, 8);
        if (a2 > 0) {
            vbn b = vbnVar.b(a2);
            long d = b.d(48);
            long d2 = b.d(48);
            long d3 = b.d(64);
            long d4 = b.d(64);
            b.b("dtls-context-sequence-numbers");
            int numberOfLeadingZeros = Long.numberOfLeadingZeros(d3);
            this.r[this.u] = d;
            this.k = d2;
            this.p = d3;
            this.o = ((d2 + 64) - numberOfLeadingZeros) - 1;
            this.i = d4;
        }
    }
}
