package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class s8 {

    /* renamed from: a, reason: collision with root package name */
    public final CopyOnWriteArrayList<InetSocketAddress> f5477a;
    public final int d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public Selector i;
    public InetSocketAddress l;
    public int m;
    public final CopyOnWriteArrayList<InetSocketAddress> b = new CopyOnWriteArrayList<>();
    public final CopyOnWriteArrayList<b> c = new CopyOnWriteArrayList<>();
    public Object j = new Object();
    public volatile boolean k = false;

    public List<InetSocketAddress> b() {
        return this.b;
    }

    public void a(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        this.h = inetSocketAddress2 == null || inetSocketAddress.getAddress().getAddress().length == inetSocketAddress2.getAddress().getAddress().length;
    }

    public void a() {
        String str;
        if (this.i != null) {
            synchronized (this.j) {
                try {
                    try {
                        this.k = true;
                        Selector selector = this.i;
                        if (selector != null) {
                            selector.close();
                        }
                    } finally {
                        this.i = null;
                    }
                } catch (IOException unused) {
                    str = "Selector close error";
                    a(str, (Throwable) null);
                } catch (Exception unused2) {
                    str = "Selector close exception";
                    a(str, (Throwable) null);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.net.Socket a(long r7, javax.net.SocketFactory r9, java.net.Proxy r10, com.huawei.hms.network.embedded.t6 r11, com.huawei.hms.network.embedded.g7 r12) {
        /*
            r6 = this;
            r9 = 0
            java.nio.channels.Selector r0 = java.nio.channels.Selector.open()     // Catch: java.io.IOException -> L38
            r6.i = r0     // Catch: java.io.IOException -> L38
            r0 = r6
            r1 = r7
            r3 = r10
            r4 = r11
            r5 = r12
            java.nio.channels.SocketChannel r7 = r0.a(r1, r3, r4, r5)     // Catch: java.nio.channels.ClosedSelectorException -> L27
            if (r7 == 0) goto L2f
            r8 = 1
            r7.configureBlocking(r8)     // Catch: java.nio.channels.ClosedSelectorException -> L17 java.io.IOException -> L19
            goto L2f
        L17:
            r8 = move-exception
            goto L2a
        L19:
            r7.close()     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L1f
            goto L24
        L1d:
            r7 = move-exception
            goto L26
        L1f:
            java.lang.String r7 = "Socket channel close error"
            r6.a(r7, r9)     // Catch: java.lang.Throwable -> L1d
        L24:
            r7 = r9
            goto L2f
        L26:
            throw r7     // Catch: java.nio.channels.ClosedSelectorException -> L27
        L27:
            r7 = move-exception
            r8 = r7
            r7 = r9
        L2a:
            java.lang.String r10 = "Selector is already closed"
            r6.a(r10, r8)
        L2f:
            r6.c()
            if (r7 == 0) goto L38
            java.net.Socket r9 = r7.socket()
        L38:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.s8.a(long, javax.net.SocketFactory, java.net.Proxy, com.huawei.hms.network.embedded.t6, com.huawei.hms.network.embedded.g7):java.net.Socket");
    }

    private SocketChannel d() {
        Iterator<SelectionKey> it = this.i.selectedKeys().iterator();
        SocketChannel socketChannel = null;
        while (it.hasNext()) {
            SelectionKey next = it.next();
            it.remove();
            if (next.isConnectable() && next.attachment() != null) {
                b bVar = (b) next.attachment();
                try {
                    SocketChannel socketChannel2 = bVar.f5478a;
                    if (socketChannel2.finishConnect()) {
                        next.cancel();
                        try {
                            this.c.remove(bVar);
                            this.l = bVar.b;
                            return socketChannel2;
                        } catch (IOException unused) {
                            socketChannel = socketChannel2;
                            next.cancel();
                            a(bVar);
                        }
                    } else {
                        next.cancel();
                        a(bVar);
                    }
                } catch (IOException unused2) {
                }
            }
        }
        return socketChannel;
    }

    private void c() {
        Iterator<b> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.c.clear();
        Selector selector = this.i;
        if (selector != null) {
            try {
                try {
                    try {
                        selector.close();
                    } catch (Exception e) {
                        a("Selector close error", e);
                    }
                } catch (IOException unused) {
                    a("Selector close error", (Throwable) null);
                }
            } finally {
                this.i = null;
            }
        }
    }

    public final class b {

        /* renamed from: a, reason: collision with root package name */
        public SocketChannel f5478a;
        public InetSocketAddress b;
        public long c;

        public boolean c() {
            return TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) >= this.c;
        }

        public long b() {
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            long j = this.c;
            if (millis >= j) {
                return 0L;
            }
            return j - millis;
        }

        public void a(InetSocketAddress inetSocketAddress, long j, int i, Proxy proxy, t6 t6Var, g7 g7Var) throws IOException {
            this.b = inetSocketAddress;
            this.c = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + j;
            g7Var.connectStart(t6Var, inetSocketAddress, proxy);
            SocketChannel open = SocketChannel.open();
            this.f5478a = open;
            open.configureBlocking(false);
            this.f5478a.socket().setTrafficClass(i);
            this.f5478a.connect(inetSocketAddress);
        }

        public void a() {
            SocketChannel socketChannel = this.f5478a;
            if (socketChannel == null) {
                return;
            }
            try {
                try {
                    socketChannel.close();
                } catch (IOException unused) {
                    s8.this.a("Socket channel close error", (Throwable) null);
                }
            } finally {
                this.f5478a = null;
            }
        }

        public b() {
            this.f5478a = null;
            this.b = null;
            this.c = 0L;
        }
    }

    private boolean a(InetSocketAddress inetSocketAddress, long j, Proxy proxy, t6 t6Var, g7 g7Var) {
        b bVar = new b();
        try {
            bVar.a(inetSocketAddress, j, this.m, proxy, t6Var, g7Var);
            synchronized (this.j) {
                if (this.i == null) {
                    return false;
                }
                bVar.f5478a.register(this.i, 8).attach(bVar);
                this.c.add(bVar);
                return true;
            }
        } catch (IOException unused) {
            a("Failed to parepare socket channel for " + inetSocketAddress.toString(), (Throwable) null);
            this.b.add(inetSocketAddress);
            bVar.a();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Throwable th) {
        ma.f().a(4, str, th);
    }

    private void a(b bVar) {
        this.b.add(bVar.b);
        this.c.remove(bVar);
        bVar.a();
    }

    private void a(long j) {
        while (this.c.size() > 0) {
            b bVar = this.c.get(0);
            if (!bVar.c()) {
                return;
            } else {
                a(bVar);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e4, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.nio.channels.SocketChannel a(long r17, java.net.Proxy r19, com.huawei.hms.network.embedded.t6 r20, com.huawei.hms.network.embedded.g7 r21) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.s8.a(long, java.net.Proxy, com.huawei.hms.network.embedded.t6, com.huawei.hms.network.embedded.g7):java.nio.channels.SocketChannel");
    }

    public s8(CopyOnWriteArrayList<InetSocketAddress> copyOnWriteArrayList, int i, int i2) {
        this.f5477a = new CopyOnWriteArrayList<>(copyOnWriteArrayList);
        this.e = i;
        this.f = i;
        this.g = i / 2;
        this.m = i2;
        this.d = copyOnWriteArrayList.size();
    }
}
