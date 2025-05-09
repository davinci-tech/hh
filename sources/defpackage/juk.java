package defpackage;

import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class juk {

    /* renamed from: a, reason: collision with root package name */
    private long f14094a;
    private long b;
    private final boolean c;
    private final LinkedList<ByteBuffer> d;
    private final SocketChannel e;
    private long f;
    private final AtomicBoolean g;
    private long h;
    private final int i;
    private final String j;

    public juk(SocketChannel socketChannel, String str, int i, boolean z) {
        this.g = new AtomicBoolean(false);
        this.d = new LinkedList<>();
        this.c = z;
        this.e = socketChannel;
        this.j = str;
        this.i = i;
        this.b = -1L;
        this.f14094a = -1L;
        this.h = 0L;
        this.f = 0L;
    }

    public juk(boolean z) {
        this(null, "", -1, z);
    }

    public String i() {
        return this.j;
    }

    public int j() {
        return this.i;
    }

    public SocketChannel f() {
        return this.e;
    }

    public void m() {
        this.g.set(true);
    }

    public boolean g() {
        return this.g.get();
    }

    public boolean l() {
        return this.c;
    }

    public void e(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            LogUtil.c("DistributionNetworkTcpConduit", "appendWrite buffer is null");
            return;
        }
        synchronized (this.d) {
            this.d.add(byteBuffer);
        }
    }

    public boolean o() {
        boolean z;
        synchronized (this.d) {
            z = !this.d.isEmpty();
        }
        return z;
    }

    public int n() throws IOException {
        int i;
        synchronized (this.d) {
            i = 0;
            while (!this.d.isEmpty()) {
                ByteBuffer first = this.d.getFirst();
                i += f().write(first);
                if (first.hasRemaining()) {
                    break;
                }
                this.d.removeFirst();
            }
        }
        return i;
    }

    public long a() {
        return this.b;
    }

    public void d(long j) {
        this.b = j;
    }

    public long b() {
        return this.f14094a;
    }

    public void a(long j) {
        this.f14094a = j;
    }

    public long h() {
        return this.h;
    }

    public void c(long j) {
        this.h = j;
    }

    public long d() {
        return this.f;
    }

    public void e(long j) {
        this.f = j;
    }

    public long e() {
        if (b() < 0) {
            return 0L;
        }
        return b() + 1;
    }

    public long c() {
        if (a() < 0) {
            return 0L;
        }
        return a() + 1;
    }
}
