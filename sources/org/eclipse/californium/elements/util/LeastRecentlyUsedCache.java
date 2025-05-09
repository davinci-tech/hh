package org.eclipse.californium.elements.util;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class LeastRecentlyUsedCache<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f15877a;
    private final Map<K, c<K, V>> b;
    private volatile long c;
    private final List<EvictionListener<V>> d;
    private volatile int e;
    private Collection<V> f;
    private volatile boolean g;
    private c<K, V> i;

    public interface EvictionListener<V> {
        void onEviction(V v);
    }

    public interface Predicate<V> {
        boolean accept(V v);
    }

    public LeastRecentlyUsedCache() {
        this(16, 150000, 1800L, TimeUnit.SECONDS);
    }

    public LeastRecentlyUsedCache(int i, long j) {
        this(Math.min(i, 16), i, j, TimeUnit.SECONDS);
    }

    public LeastRecentlyUsedCache(int i, int i2, long j, TimeUnit timeUnit) {
        this.f15877a = true;
        this.g = true;
        this.d = new LinkedList();
        if (i > i2) {
            throw new IllegalArgumentException("initial capacity must be <= max capacity");
        }
        this.e = i2;
        this.b = new ConcurrentHashMap(i);
        e(j, timeUnit);
        h();
    }

    private void h() {
        c<K, V> cVar = new c<>();
        this.i = cVar;
        ((c) cVar).e = ((c) cVar).c = cVar;
    }

    public void a(EvictionListener<V> evictionListener) {
        if (evictionListener != null) {
            this.d.add(evictionListener);
        }
    }

    public void c(boolean z) {
        this.f15877a = z;
    }

    public void e(boolean z) {
        this.g = z;
    }

    public final void e(long j, TimeUnit timeUnit) {
        this.c = timeUnit.toNanos(j);
    }

    public final int b() {
        return this.e;
    }

    public final int e() {
        return this.b.size();
    }

    public final int d() {
        return Math.max(0, this.e - this.b.size());
    }

    public final void c() {
        this.b.clear();
        h();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean c(K k, V v) {
        if (v == null) {
            return false;
        }
        c<K, V> cVar = this.b.get(k);
        if (cVar == null) {
            if (this.b.size() < this.e) {
                d(k, v);
                return true;
            }
            c cVar2 = ((c) this.i).e;
            if (!cVar2.c(this.c)) {
                return false;
            }
            cVar2.d();
            this.b.remove(cVar2.b());
            d(k, v);
            b(cVar2.c());
            return true;
        }
        cVar.d();
        d(k, v);
        return true;
    }

    private void b(V v) {
        Iterator<EvictionListener<V>> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().onEviction(v);
        }
    }

    private void d(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.b.put(k, cVar);
        cVar.g(this.i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean b(K k, V v, long j) {
        if (v == null) {
            return false;
        }
        c<K, V> cVar = this.b.get(k);
        if (cVar == null) {
            if (this.b.size() < this.e) {
                a(k, v, j);
                return true;
            }
            c cVar2 = ((c) this.i).e;
            if (!cVar2.c(this.c) || j - cVar2.d < 0) {
                return false;
            }
            cVar2.d();
            this.b.remove(cVar2.b());
            a(k, v, j);
            b(cVar2.c());
            return true;
        }
        cVar.d();
        a(k, v, j);
        return true;
    }

    private void a(K k, V v, long j) {
        c<K, V> cVar = new c<>(k, v, j);
        this.b.put(k, cVar);
        c<K, V> cVar2 = ((c) this.i).c;
        c<K, V> cVar3 = this.i;
        if (cVar2 != cVar3) {
            while (j - ((c) cVar3).c.d < 0 && (cVar3 = ((c) cVar3).c) != this.i) {
            }
            cVar.g(cVar3);
            return;
        }
        cVar.g(cVar3);
    }

    public final V a(K k) {
        c<K, V> cVar;
        if (k == null || (cVar = this.b.get(k)) == null) {
            return null;
        }
        return c((c) cVar, (Iterator) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final V c(c<K, V> cVar, Iterator<c<K, V>> it) {
        if (!this.f15877a || this.c <= 0 || !cVar.c(this.c)) {
            if (this.g) {
                cVar.h(this.i);
            }
            return (V) cVar.c();
        }
        if (it != null) {
            it.remove();
        } else {
            this.b.remove(cVar.b());
        }
        cVar.d();
        b(cVar.c());
        return null;
    }

    public final boolean c(K k) {
        c<K, V> cVar;
        if (k == null || (cVar = this.b.get(k)) == null) {
            return false;
        }
        cVar.h(this.i);
        return true;
    }

    public final V e(K k) {
        c<K, V> remove;
        if (k == null || (remove = this.b.remove(k)) == null) {
            return null;
        }
        remove.d();
        return (V) remove.c();
    }

    public final V a(K k, V v) {
        c<K, V> cVar;
        if (k == null || (cVar = this.b.get(k)) == null || cVar.c() != v) {
            return null;
        }
        this.b.remove(k);
        cVar.d();
        return v;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int a(int i) {
        int i2 = 0;
        while (true) {
            if (i != 0 && i2 >= i) {
                break;
            }
            c<K, V> cVar = ((c) this.i).e;
            if (this.i == cVar || !cVar.c(this.c)) {
                break;
            }
            cVar.d();
            this.b.remove(cVar.b());
            b(cVar.c());
            i2++;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final V a(Predicate<V> predicate, boolean z) {
        if (predicate == 0) {
            return null;
        }
        Iterator<c<K, V>> it = this.b.values().iterator();
        while (it.hasNext()) {
            c<K, V> next = it.next();
            if (predicate.accept(next.c())) {
                V c2 = c((c) next, (Iterator) it);
                if (z || c2 != null) {
                    return c2;
                }
            }
        }
        return null;
    }

    public final Iterator<V> g() {
        return a(true);
    }

    public final Iterator<V> a(final boolean z) {
        final Iterator<c<K, V>> it = this.b.values().iterator();
        return new Iterator<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUsedCache.1
            private boolean b;
            private c<K, V> e;

            /* JADX WARN: Code restructure failed: missing block: B:16:0x0029, code lost:
            
                r5.e = r0;
             */
            @Override // java.util.Iterator
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean hasNext() {
                /*
                    r5 = this;
                    boolean r0 = r5.b
                    r1 = 1
                    if (r0 != 0) goto L36
                    r0 = 0
                    r5.e = r0
                L8:
                    java.util.Iterator r0 = r2
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L34
                    java.util.Iterator r0 = r2
                    java.lang.Object r0 = r0.next()
                    org.eclipse.californium.elements.util.LeastRecentlyUsedCache$c r0 = (org.eclipse.californium.elements.util.LeastRecentlyUsedCache.c) r0
                    boolean r2 = r3
                    if (r2 == 0) goto L32
                    org.eclipse.californium.elements.util.LeastRecentlyUsedCache r2 = org.eclipse.californium.elements.util.LeastRecentlyUsedCache.this
                    monitor-enter(r2)
                    org.eclipse.californium.elements.util.LeastRecentlyUsedCache r3 = org.eclipse.californium.elements.util.LeastRecentlyUsedCache.this     // Catch: java.lang.Throwable -> L2f
                    java.util.Iterator r4 = r2     // Catch: java.lang.Throwable -> L2f
                    java.lang.Object r3 = org.eclipse.californium.elements.util.LeastRecentlyUsedCache.b(r3, r0, r4)     // Catch: java.lang.Throwable -> L2f
                    if (r3 == 0) goto L2d
                    r5.e = r0     // Catch: java.lang.Throwable -> L2f
                    monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
                    goto L34
                L2d:
                    monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
                    goto L8
                L2f:
                    r0 = move-exception
                    monitor-exit(r2)     // Catch: java.lang.Throwable -> L2f
                    throw r0
                L32:
                    r5.e = r0
                L34:
                    r5.b = r1
                L36:
                    org.eclipse.californium.elements.util.LeastRecentlyUsedCache$c<K, V> r0 = r5.e
                    if (r0 == 0) goto L3b
                    goto L3c
                L3b:
                    r1 = 0
                L3c:
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: org.eclipse.californium.elements.util.LeastRecentlyUsedCache.AnonymousClass1.hasNext():boolean");
            }

            @Override // java.util.Iterator
            public V next() {
                hasNext();
                this.b = false;
                c<K, V> cVar = this.e;
                if (cVar == null) {
                    throw new NoSuchElementException();
                }
                return (V) ((c) cVar).b;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public final Collection<V> j() {
        Collection<V> collection = this.f;
        if (collection != null) {
            return collection;
        }
        AbstractCollection<V> abstractCollection = new AbstractCollection<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUsedCache.3
            @Override // java.util.AbstractCollection, java.util.Collection
            public final int size() {
                return LeastRecentlyUsedCache.this.b.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public final boolean contains(final Object obj) {
                return LeastRecentlyUsedCache.this.a((Predicate) new Predicate<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUsedCache.3.2
                    @Override // org.eclipse.californium.elements.util.LeastRecentlyUsedCache.Predicate
                    public boolean accept(V v) {
                        return v.equals(obj);
                    }
                }, false) != null;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public final Iterator<V> iterator() {
                return LeastRecentlyUsedCache.this.g();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public final boolean add(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public final boolean remove(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public final void clear() {
                throw new UnsupportedOperationException();
            }
        };
        this.f = abstractCollection;
        return abstractCollection;
    }

    public final Iterator<e<V>> a() {
        return new Iterator<e<V>>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUsedCache.2

            /* renamed from: a, reason: collision with root package name */
            final int f15879a;
            c<K, V> c;
            int d;

            {
                this.f15879a = LeastRecentlyUsedCache.this.b.size();
                this.c = LeastRecentlyUsedCache.this.i;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return ((c) this.c).e != LeastRecentlyUsedCache.this.i && this.d < this.f15879a;
            }

            @Override // java.util.Iterator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public e<V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.d++;
                c<K, V> cVar = ((c) this.c).e;
                this.c = cVar;
                return cVar.a();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    static class c<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private final K f15881a;
        private final V b;
        private c<K, V> c;
        private long d;
        private c<K, V> e;

        private c() {
            this.f15881a = null;
            this.b = null;
            this.d = -1L;
        }

        private c(K k, V v) {
            this(k, v, ClockUtil.d());
        }

        private c(K k, V v, long j) {
            this.f15881a = k;
            this.b = v;
            this.d = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public e<V> a() {
            return new e<>(this.b, this.d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public K b() {
            return this.f15881a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public V c() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c(long j) {
            return ClockUtil.d() - this.d >= j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void h(c<K, V> cVar) {
            d();
            this.d = ClockUtil.d();
            g(cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g(c<K, V> cVar) {
            this.e = cVar;
            c<K, V> cVar2 = cVar.c;
            this.c = cVar2;
            cVar2.e = this;
            this.e.c = this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            c<K, V> cVar = this.c;
            cVar.e = this.e;
            this.e.c = cVar;
        }

        public String toString() {
            return "CacheEntry [key: " + this.f15881a + ", last access: " + this.d + "]";
        }
    }

    /* loaded from: classes10.dex */
    public static final class e<V> {

        /* renamed from: a, reason: collision with root package name */
        private final long f15882a;
        private final V e;

        public e(V v, long j) {
            this.e = v;
            this.f15882a = j;
        }

        public V b() {
            return this.e;
        }

        public long a() {
            return this.f15882a;
        }

        public int hashCode() {
            long j = this.f15882a;
            int i = (int) (j ^ (j >>> 32));
            V v = this.e;
            return v != null ? i + v.hashCode() : i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            e eVar = (e) obj;
            if (this.f15882a != eVar.f15882a) {
                return false;
            }
            V v = this.e;
            if (v == null) {
                return eVar.e == null;
            }
            return v.equals(eVar.e);
        }

        public String toString() {
            return this.f15882a + ": " + this.e;
        }
    }
}
