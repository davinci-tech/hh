package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public final class c9 {

    /* renamed from: a, reason: collision with root package name */
    public final p6 f5204a;
    public final a9 b;
    public final t6 c;
    public final g7 d;
    public int f;
    public boolean i;
    public List<InetAddress> j;
    public List<Proxy> e = Collections.emptyList();
    public List<InetSocketAddress> g = Collections.emptyList();
    public final List<x7> h = new ArrayList();

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final CopyOnWriteArrayList<x7> f5205a;
        public int b;
        public x7 c;
        public int d;
        public int e;
        public final a9 f;
        public boolean g;
        public InetSocketAddress h;

        public void h() {
            if (this.g) {
                Iterator<x7> it = this.f5205a.iterator();
                while (it.hasNext()) {
                    x7 next = it.next();
                    if (this.c != null && next.d().equals(this.c.d())) {
                        this.f5205a.remove(next);
                        return;
                    }
                }
            }
        }

        public x7 g() {
            if (!f()) {
                throw new NoSuchElementException();
            }
            if (this.g) {
                x7 x7Var = this.f5205a.get(0);
                this.c = x7Var;
                this.d++;
                return x7Var;
            }
            CopyOnWriteArrayList<x7> copyOnWriteArrayList = this.f5205a;
            int i = this.b;
            this.b = i + 1;
            return copyOnWriteArrayList.get(i);
        }

        public boolean f() {
            return this.g ? this.f5205a.size() > 0 && this.d < this.e : this.b < this.f5205a.size();
        }

        public x7 e() {
            return this.c;
        }

        public List<x7> d() {
            return new ArrayList(this.f5205a);
        }

        public void c() {
            InetSocketAddress inetSocketAddress;
            if (!this.g || (inetSocketAddress = this.h) == null) {
                return;
            }
            x7 b = b(inetSocketAddress);
            if (b != null) {
                this.f.b(b);
            }
            this.h = null;
        }

        public void b(x7 x7Var) {
            this.c = x7Var;
        }

        public CopyOnWriteArrayList<InetSocketAddress> b() {
            CopyOnWriteArrayList<InetSocketAddress> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            Iterator<x7> it = this.f5205a.iterator();
            while (it.hasNext()) {
                copyOnWriteArrayList.add(it.next().d());
            }
            return copyOnWriteArrayList;
        }

        public boolean a() {
            return this.g;
        }

        public void a(List<InetSocketAddress> list) {
            if (this.g) {
                Iterator<InetSocketAddress> it = list.iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
            }
        }

        public void a(InetSocketAddress inetSocketAddress) {
            if (this.g) {
                this.h = inetSocketAddress;
            }
        }

        public void a(x7 x7Var) {
            if (!this.g) {
                this.f.a(x7Var);
            } else {
                if (this.h == null) {
                    return;
                }
                this.f.a(x7Var);
                this.h = null;
            }
        }

        private x7 b(InetSocketAddress inetSocketAddress) {
            Iterator<x7> it = this.f5205a.iterator();
            while (it.hasNext()) {
                x7 next = it.next();
                if (next.d().equals(inetSocketAddress)) {
                    this.f5205a.remove(next);
                    return next;
                }
            }
            return null;
        }

        public a(CopyOnWriteArrayList<x7> copyOnWriteArrayList, a9 a9Var, boolean z) {
            this.b = 0;
            this.d = 0;
            this.e = 0;
            this.h = null;
            this.f5205a = copyOnWriteArrayList;
            if (copyOnWriteArrayList != null) {
                this.e = copyOnWriteArrayList.size();
            }
            this.f = a9Var;
            this.g = z;
        }

        public a(CopyOnWriteArrayList<x7> copyOnWriteArrayList) {
            this.b = 0;
            this.d = 0;
            this.e = 0;
            this.h = null;
            this.f5205a = copyOnWriteArrayList;
            this.g = false;
            this.f = null;
        }
    }

    public a b() throws IOException {
        if (!a()) {
            throw new NoSuchElementException();
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        while (c()) {
            Proxy d = d();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                x7 x7Var = new x7(this.f5204a, d, this.g.get(i));
                if (this.b.c(x7Var)) {
                    this.h.add(x7Var);
                } else {
                    copyOnWriteArrayList.add(x7Var);
                }
            }
            if (!copyOnWriteArrayList.isEmpty()) {
                break;
            }
        }
        if (this.i || copyOnWriteArrayList.isEmpty()) {
            copyOnWriteArrayList.addAll(this.h);
            this.h.clear();
        }
        return new a(copyOnWriteArrayList, this.b, this.i);
    }

    public boolean a() {
        return c() || !this.h.isEmpty();
    }

    public c9 a(t7 t7Var) {
        this.i = t7Var.d();
        this.j = t7Var.a();
        if (this.i && (this.e.size() > 1 || (this.e.size() == 1 && this.e.get(0).type() != Proxy.Type.DIRECT))) {
            this.i = false;
        }
        return this;
    }

    private Proxy d() throws IOException {
        if (!c()) {
            throw new SocketException("No route to " + this.f5204a.l().h() + "; exhausted proxy configurations: " + this.e);
        }
        List<Proxy> list = this.e;
        int i = this.f;
        this.f = i + 1;
        Proxy proxy = list.get(i);
        a(proxy);
        return proxy;
    }

    private boolean c() {
        return this.f < this.e.size();
    }

    private void a(Proxy proxy) throws IOException {
        String h;
        int n;
        this.g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            h = this.f5204a.l().h();
            n = this.f5204a.l().n();
        } else {
            SocketAddress address = proxy.address();
            if (!(address instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
            h = a(inetSocketAddress);
            n = inetSocketAddress.getPort();
        }
        if (n < 1 || n > 65535) {
            throw new SocketException("No route to " + h + ":" + n + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(h, n));
        } else if (!this.i || this.j.isEmpty()) {
            this.d.dnsStart(this.c, h);
            List<InetAddress> lookup = this.f5204a.c().lookup(h);
            if (lookup.isEmpty()) {
                throw new UnknownHostException(this.f5204a.c() + " returned no addresses for " + h);
            }
            this.d.dnsEnd(this.c, h, lookup);
            int size = lookup.size();
            for (int i = 0; i < size; i++) {
                this.g.add(new InetSocketAddress(lookup.get(i), n));
            }
        }
        a(n);
    }

    private void a(m7 m7Var, Proxy proxy) {
        List<Proxy> a2;
        if (proxy != null) {
            a2 = Collections.singletonList(proxy);
        } else {
            List<Proxy> select = this.f5204a.i().select(m7Var.u());
            a2 = (select == null || select.isEmpty()) ? f8.a(Proxy.NO_PROXY) : f8.a(select);
        }
        this.e = a2;
        this.f = 0;
    }

    private void a(int i) {
        if (this.i) {
            List<InetAddress> list = this.j;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(this.j.get(size), i);
                    if (this.g.contains(inetSocketAddress)) {
                        this.g.remove(inetSocketAddress);
                    }
                    this.g.add(0, inetSocketAddress);
                }
            }
            if (this.g.size() == 1) {
                this.i = false;
            }
        }
    }

    public static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        return address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
    }

    public c9(p6 p6Var, a9 a9Var, t6 t6Var, g7 g7Var) {
        this.f5204a = p6Var;
        this.b = a9Var;
        this.c = t6Var;
        this.d = g7Var;
        a(p6Var.l(), p6Var.g());
    }
}
