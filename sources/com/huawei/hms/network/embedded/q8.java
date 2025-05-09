package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.SocketFactory;

/* loaded from: classes9.dex */
public class q8 extends s8 {
    public static ThreadFactory v;
    public static ExecutorService w;
    public final CopyOnWriteArrayList<InetSocketAddress> n;
    public final CopyOnWriteArrayList<InetSocketAddress> o;
    public final CopyOnWriteArrayList<b> p;
    public int q;
    public BlockingQueue<b> r;
    public b s;
    public volatile boolean t;
    public long u;

    @Override // com.huawei.hms.network.embedded.s8
    public List<InetSocketAddress> b() {
        return this.o;
    }

    @Override // com.huawei.hms.network.embedded.s8
    public /* bridge */ /* synthetic */ void a(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        super.a(inetSocketAddress, inetSocketAddress2);
    }

    @Override // com.huawei.hms.network.embedded.s8
    public void a() {
        this.t = true;
        this.r.add(b.m);
    }

    public static final class b implements Callable<b> {
        public static b m = new b();

        /* renamed from: a, reason: collision with root package name */
        public String f5442a;
        public InetSocketAddress b;
        public Proxy c;
        public SocketFactory d;
        public t6 e;
        public g7 f;
        public long g;
        public int h;
        public Socket i;
        public volatile boolean j;
        public Queue k;
        public int l;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public b call() throws Exception {
            Thread.currentThread().setName(this.f5442a + Thread.currentThread().getName());
            this.f.connectStart(this.e, this.b, this.c);
            Socket createSocket = (this.c.type() == Proxy.Type.DIRECT || this.c.type() == Proxy.Type.HTTP) ? this.d.createSocket() : new Socket(this.c);
            this.i = createSocket;
            createSocket.setTrafficClass(this.l);
            try {
                this.i.connect(this.b, this.h);
                if (this.j) {
                    f8.a(this.i);
                    return null;
                }
                if (this.i.isClosed()) {
                    return null;
                }
                this.k.add(this);
                return this;
            } catch (IOException | RuntimeException e) {
                f8.a(this.i);
                a(e);
                throw new IOException("ConnectTask call error ", e);
            }
        }

        public boolean c() {
            return TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) >= this.g;
        }

        public long b() {
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            long j = this.g;
            if (millis >= j) {
                return 0L;
            }
            return j - millis;
        }

        public void a() {
            this.j = true;
            Socket socket = this.i;
            if (socket == null || socket.isClosed()) {
                return;
            }
            f8.a(this.i);
        }

        private void a(Exception exc) {
            q8.b("address " + this.b + " connect failed", exc);
        }

        public b(InetSocketAddress inetSocketAddress, long j, int i, SocketFactory socketFactory, Proxy proxy, t6 t6Var, g7 g7Var, Queue queue) {
            this.j = false;
            this.f5442a = Thread.currentThread().getName();
            this.b = inetSocketAddress;
            this.c = proxy;
            this.d = socketFactory;
            this.e = t6Var;
            this.f = g7Var;
            this.h = (int) j;
            this.k = queue;
            this.l = i;
            this.g = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + j;
        }

        public b() {
            this.j = false;
        }
    }

    @Override // com.huawei.hms.network.embedded.s8
    public Socket a(long j, SocketFactory socketFactory, Proxy proxy, t6 t6Var, g7 g7Var) {
        b bVar;
        int size = this.n.isEmpty() ? 0 : this.n.size();
        while (!this.n.isEmpty() && !f()) {
            if (this.t) {
                c();
                return null;
            }
            this.u = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            b a2 = a(t6Var, j, socketFactory, proxy, g7Var, this.r);
            if (a2 != null) {
                try {
                    w.submit(a2);
                    this.p.add(a2);
                } catch (RejectedExecutionException e) {
                    b(e.getMessage(), e);
                }
                try {
                    if (this.t) {
                        c();
                        return null;
                    }
                    if (f()) {
                        c();
                        return e();
                    }
                    int i = this.h ? this.f : this.g;
                    this.q = i;
                    b poll = this.r.poll(i - (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) - this.u), TimeUnit.MILLISECONDS);
                    if (this.t) {
                        c();
                        return null;
                    }
                    if (poll != null) {
                        a(poll);
                    }
                } catch (InterruptedException e2) {
                    b(e2.getMessage(), e2);
                }
            }
        }
        ArrayList arrayList = new ArrayList(this.p);
        if (!this.t && !f() && d() != size && !arrayList.isEmpty()) {
            try {
                bVar = (b) arrayList.get(arrayList.size() - 1);
            } catch (IndexOutOfBoundsException e3) {
                b(e3.getMessage(), e3);
                bVar = null;
            }
            if (bVar != null) {
                try {
                    b poll2 = this.r.poll(bVar.b(), TimeUnit.MILLISECONDS);
                    if (!this.t) {
                        if (poll2 == null) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                a(((b) it.next()).b);
                            }
                        } else {
                            a(poll2);
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                b bVar2 = (b) it2.next();
                                if (bVar2 != this.s && bVar2.c()) {
                                    a(bVar2.b);
                                }
                            }
                        }
                    }
                } catch (InterruptedException e4) {
                    b(e4.getMessage(), e4);
                }
            }
        }
        b bVar3 = this.s;
        if (bVar3 != null) {
            this.l = bVar3.b;
        }
        try {
            c();
        } catch (RuntimeException unused) {
            b("ClearResource with RuntimeException error", null);
        }
        return e();
    }

    private boolean f() {
        boolean z;
        synchronized (this) {
            z = this.s != null;
        }
        return z;
    }

    private Socket e() {
        b bVar = this.s;
        if (bVar != null) {
            return bVar.i;
        }
        return null;
    }

    private int d() {
        int size;
        synchronized (this.o) {
            size = this.o.size();
        }
        return size;
    }

    private void c() {
        b bVar;
        if (!this.t && (bVar = this.s) != null) {
            this.p.remove(bVar);
        }
        Iterator<b> it = this.p.iterator();
        while (it.hasNext()) {
            b next = it.next();
            try {
                if (next != this.s && next.c()) {
                    a(next.b);
                }
                next.a();
            } catch (ConcurrentModificationException e) {
                b(e.getMessage(), e);
            }
        }
        this.p.clear();
    }

    public static void b(String str, Throwable th) {
        ma.f().a(4, str, th);
    }

    private void a(InetSocketAddress inetSocketAddress) {
        synchronized (this.o) {
            if (!this.o.contains(inetSocketAddress)) {
                this.o.add(inetSocketAddress);
            }
        }
    }

    private void a(b bVar) {
        synchronized (this) {
            if (this.s != null) {
                return;
            }
            this.s = bVar;
        }
    }

    public class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        public final AtomicInteger f5441a = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, " OkHttp_" + this.f5441a.getAndIncrement() + " concurrentConnect");
        }
    }

    private b a(t6 t6Var, long j, SocketFactory socketFactory, Proxy proxy, g7 g7Var, Queue queue) {
        if (this.n.isEmpty()) {
            return null;
        }
        InetSocketAddress remove = this.n.remove(0);
        if (this.n.isEmpty()) {
            a(remove, (InetSocketAddress) null);
        } else {
            a(remove, this.n.get(0));
        }
        return new b(remove, j, this.m, socketFactory, proxy, t6Var, g7Var, queue);
    }

    public q8(CopyOnWriteArrayList<InetSocketAddress> copyOnWriteArrayList, int i, int i2) {
        super(copyOnWriteArrayList, i, i2);
        this.o = new CopyOnWriteArrayList<>();
        this.p = new CopyOnWriteArrayList<>();
        this.r = new LinkedBlockingQueue();
        this.n = copyOnWriteArrayList;
        this.f = i;
        this.g = i / 2;
    }

    static {
        a aVar = new a();
        v = aVar;
        w = Executors.newCachedThreadPool(aVar);
    }
}
