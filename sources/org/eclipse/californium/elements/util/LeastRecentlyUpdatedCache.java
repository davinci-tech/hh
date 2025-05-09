package org.eclipse.californium.elements.util;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes7.dex */
public class LeastRecentlyUpdatedCache<K, V> {
    public static final long d = TimeUnit.MINUTES.toSeconds(30);

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentMap<K, c<K, V>> f15871a;
    private final List<EvictionListener<V>> b;
    private volatile int c;
    private volatile long e;
    private Collection<V> f;
    private final c<K, V> h;
    private final ReentrantReadWriteLock i;

    public interface EvictionListener<V> {
        void onEviction(V v);
    }

    public interface Predicate<V> {
        boolean accept(V v);
    }

    public LeastRecentlyUpdatedCache() {
        this(16, 150000, d, TimeUnit.SECONDS);
    }

    public LeastRecentlyUpdatedCache(int i, long j, TimeUnit timeUnit) {
        this(Math.min(i, 16), i, j, timeUnit);
    }

    public LeastRecentlyUpdatedCache(int i, int i2, long j, TimeUnit timeUnit) {
        this.i = new ReentrantReadWriteLock();
        this.h = new c<>();
        this.b = new LinkedList();
        if (i > i2) {
            throw new IllegalArgumentException("initial capacity must be <= max capacity");
        }
        this.c = i2;
        this.f15871a = new ConcurrentHashMap(i);
        b(j, timeUnit);
    }

    public final ReentrantReadWriteLock.ReadLock d() {
        return this.i.readLock();
    }

    public final ReentrantReadWriteLock.WriteLock g() {
        return this.i.writeLock();
    }

    public void b(EvictionListener<V> evictionListener) {
        if (evictionListener != null) {
            this.b.add(evictionListener);
        }
    }

    public final long b(TimeUnit timeUnit) {
        return timeUnit.convert(this.e, TimeUnit.NANOSECONDS);
    }

    public final void b(long j, TimeUnit timeUnit) {
        this.e = timeUnit.toNanos(j);
    }

    public final int e() {
        return this.c;
    }

    public final int j() {
        return this.f15871a.size();
    }

    public final int b() {
        return Math.max(0, this.c - this.f15871a.size());
    }

    public final void a() {
        this.i.writeLock().lock();
        try {
            this.f15871a.clear();
            c<K, V> cVar = this.h;
            if (cVar != ((c) cVar).b && ((c) this.h).b != null) {
                ((c) this.h).b.d = null;
            }
            c<K, V> cVar2 = this.h;
            if (cVar2 != ((c) cVar2).d && ((c) this.h).d != null) {
                ((c) this.h).d.b = null;
            }
            c<K, V> cVar3 = this.h;
            ((c) cVar3).b = ((c) cVar3).d = cVar3;
        } finally {
            this.i.writeLock().unlock();
        }
    }

    private final void a(V v) {
        if (v == null || this.b.isEmpty()) {
            return;
        }
        Iterator<EvictionListener<V>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().onEviction(v);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean d(K k, V v) {
        Object obj;
        if (v == null) {
            return false;
        }
        this.i.writeLock().lock();
        try {
            c<K, V> cVar = this.f15871a.get(k);
            if (cVar == null) {
                if (this.f15871a.size() < this.c) {
                    e((LeastRecentlyUpdatedCache<K, V>) k, (K) v);
                } else {
                    c cVar2 = ((c) this.h).b;
                    if (cVar2.a(this.e)) {
                        cVar2.e();
                        this.f15871a.remove(cVar2.c());
                        e((LeastRecentlyUpdatedCache<K, V>) k, (K) v);
                        obj = cVar2.d();
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        return false;
                    }
                    a((LeastRecentlyUpdatedCache<K, V>) obj);
                    return true;
                }
            } else {
                cVar.e();
                e((LeastRecentlyUpdatedCache<K, V>) k, (K) v);
            }
            return true;
        } finally {
            this.i.writeLock().unlock();
        }
    }

    private final void e(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f15871a.put(k, cVar);
        cVar.n(this.h);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean b(K k, V v, long j) {
        Object obj;
        if (v == null) {
            return false;
        }
        this.i.writeLock().lock();
        try {
            c<K, V> cVar = this.f15871a.get(k);
            if (cVar == null) {
                if (this.f15871a.size() < this.c) {
                    d(k, v, j);
                } else {
                    c cVar2 = ((c) this.h).b;
                    if (!cVar2.a(this.e) || j - cVar2.e < 0) {
                        obj = null;
                    } else {
                        cVar2.e();
                        this.f15871a.remove(cVar2.c());
                        d(k, v, j);
                        obj = cVar2.d();
                    }
                    if (obj == null) {
                        return false;
                    }
                    a((LeastRecentlyUpdatedCache<K, V>) obj);
                    return true;
                }
            } else {
                cVar.e();
                d(k, v, j);
            }
            return true;
        } finally {
            this.i.writeLock().unlock();
        }
    }

    private final void d(K k, V v, long j) {
        c<K, V> cVar = new c<>(k, v, j);
        this.f15871a.put(k, cVar);
        c<K, V> cVar2 = ((c) this.h).d;
        c<K, V> cVar3 = this.h;
        if (cVar2 != cVar3) {
            while (j - ((c) cVar3).d.e < 0 && (cVar3 = ((c) cVar3).d) != this.h) {
            }
            cVar.n(cVar3);
            return;
        }
        cVar.n(cVar3);
    }

    private final c<K, V> d(K k) {
        if (k == null) {
            return null;
        }
        return this.f15871a.get(k);
    }

    public final boolean b(K k) {
        c<K, V> d2 = d((LeastRecentlyUpdatedCache<K, V>) k);
        if (d2 == null) {
            return false;
        }
        return d2.a(this.e);
    }

    public final V c(K k) {
        c<K, V> d2 = d((LeastRecentlyUpdatedCache<K, V>) k);
        if (d2 == null) {
            return null;
        }
        return (V) d2.d();
    }

    public final V e(K k) {
        if (k == null) {
            return null;
        }
        this.i.writeLock().lock();
        try {
            c<K, V> cVar = this.f15871a.get(k);
            if (cVar == null) {
                return null;
            }
            cVar.m(this.h);
            return (V) cVar.d();
        } finally {
            this.i.writeLock().unlock();
        }
    }

    public final V a(K k, V v) {
        if (k == null) {
            return null;
        }
        this.i.writeLock().lock();
        try {
            c<K, V> cVar = this.f15871a.get(k);
            if (cVar == null || cVar.d() != v) {
                return null;
            }
            this.f15871a.remove(k);
            cVar.e();
            return v;
        } finally {
            this.i.writeLock().unlock();
        }
    }

    public final V b(Predicate<V> predicate) {
        if (predicate == null) {
            return null;
        }
        Iterator<c<K, V>> it = this.f15871a.values().iterator();
        while (it.hasNext()) {
            V v = (V) it.next().d();
            if (predicate.accept(v)) {
                return v;
            }
        }
        return null;
    }

    public final Iterator<V> i() {
        return new Iterator<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.4

            /* renamed from: a, reason: collision with root package name */
            private final Iterator<c<K, V>> f15874a;
            private volatile c<K, V> c;
            private volatile boolean e;

            {
                this.f15874a = LeastRecentlyUpdatedCache.this.f15871a.values().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.e) {
                    this.c = null;
                    if (this.f15874a.hasNext()) {
                        this.c = this.f15874a.next();
                    }
                    this.e = true;
                }
                return this.c != null;
            }

            @Override // java.util.Iterator
            public V next() {
                hasNext();
                this.e = false;
                if (this.c == null) {
                    throw new NoSuchElementException();
                }
                return (V) ((c) this.c).f;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.c != null && !this.e) {
                    try {
                        LeastRecentlyUpdatedCache.this.i.writeLock().lock();
                        this.f15874a.remove();
                        this.c.e();
                        LeastRecentlyUpdatedCache.this.i.writeLock().unlock();
                        this.c = null;
                        return;
                    } catch (Throwable th) {
                        LeastRecentlyUpdatedCache.this.i.writeLock().unlock();
                        throw th;
                    }
                }
                throw new IllegalStateException("next() must be called before remove()!");
            }
        };
    }

    public final Collection<V> f() {
        Collection<V> collection = this.f;
        if (collection != null) {
            return collection;
        }
        AbstractCollection<V> abstractCollection = new AbstractCollection<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.3
            @Override // java.util.AbstractCollection, java.util.Collection
            public final int size() {
                return LeastRecentlyUpdatedCache.this.f15871a.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public final boolean contains(final Object obj) {
                return LeastRecentlyUpdatedCache.this.b((Predicate) new Predicate<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.3.2
                    @Override // org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.Predicate
                    public boolean accept(V v) {
                        return v.equals(obj);
                    }
                }) != null;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public final Iterator<V> iterator() {
                return LeastRecentlyUpdatedCache.this.i();
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

    public final Iterator<V> c() {
        return new Iterator<V>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.2

            /* renamed from: a, reason: collision with root package name */
            final Iterator<c<K, V>> f15873a;

            {
                this.f15873a = new b();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f15873a.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((c) this.f15873a.next()).f;
            }

            @Override // java.util.Iterator
            public void remove() {
                this.f15873a.remove();
            }
        };
    }

    public final Iterator<a<V>> h() {
        return new Iterator<a<V>>() { // from class: org.eclipse.californium.elements.util.LeastRecentlyUpdatedCache.1
            final Iterator<c<K, V>> c;

            {
                this.c = new b();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.c.hasNext();
            }

            @Override // java.util.Iterator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public a<V> next() {
                return this.c.next().a();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.c.remove();
            }
        };
    }

    class b implements Iterator<c<K, V>> {

        /* renamed from: a, reason: collision with root package name */
        c<K, V> f15875a;
        c<K, V> e;

        private b() {
            this.f15875a = null;
            this.e = a(LeastRecentlyUpdatedCache.this.h);
        }

        public c<K, V> a(c<K, V> cVar) {
            return LeastRecentlyUpdatedCache.this.d((c) cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            c<K, V> cVar;
            while (this.e != LeastRecentlyUpdatedCache.this.h && (cVar = this.e) != null && cVar.b()) {
                this.e = a(this.e);
            }
            c<K, V> cVar2 = this.e;
            return (cVar2 == null || cVar2.b()) ? false : true;
        }

        @Override // java.util.Iterator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public c<K, V> next() {
            this.f15875a = this.e;
            if (hasNext()) {
                this.f15875a = this.e;
            } else {
                c<K, V> cVar = this.f15875a;
                if (cVar == null || cVar == LeastRecentlyUpdatedCache.this.h) {
                    throw new NoSuchElementException();
                }
            }
            this.e = a(this.e);
            hasNext();
            return this.f15875a;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Iterator
        public void remove() {
            c<K, V> cVar = this.f15875a;
            if (cVar == null) {
                throw new IllegalStateException("next() must be called before remove()!");
            }
            LeastRecentlyUpdatedCache.this.a(((c) cVar).f15876a, ((c) this.f15875a).f);
            this.f15875a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c<K, V> d(c<K, V> cVar) {
        try {
            this.i.readLock().lock();
            return ((c) cVar).b;
        } finally {
            this.i.readLock().unlock();
        }
    }

    static class c<K, V> {
        private static long c = -1;

        /* renamed from: a, reason: collision with root package name */
        private final K f15876a;
        private c<K, V> b;
        private c<K, V> d;
        private volatile long e;
        private final V f;

        private c() {
            this.f15876a = null;
            this.f = null;
            this.e = c;
            this.b = this;
            this.d = this;
        }

        private c(K k, V v) {
            this(k, v, ClockUtil.d());
        }

        private c(K k, V v, long j) {
            this.f15876a = k;
            this.f = v;
            this.e = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final a<V> a() {
            return new a<>(this.f, this.e);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final K c() {
            return this.f15876a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final V d() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean a(long j) {
            return ClockUtil.d() - this.e >= j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean m(c<K, V> cVar) {
            if (!e()) {
                return false;
            }
            this.e = ClockUtil.d();
            n(cVar);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void n(c<K, V> cVar) {
            this.b = cVar;
            c<K, V> cVar2 = cVar.d;
            this.d = cVar2;
            cVar2.b = this;
            this.b.d = this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean e() {
            if (this.d == null || this.b == null) {
                return false;
            }
            this.e = c;
            c<K, V> cVar = this.d;
            cVar.b = this.b;
            this.b.d = cVar;
            this.d = null;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean b() {
            return this.e == c;
        }

        public String toString() {
            return "CacheEntry [key: " + this.f15876a + ", last access: " + this.e + "]";
        }
    }

    /* loaded from: classes10.dex */
    public static final class a<V> {
        private final V c;
        private final long e;

        public a(V v, long j) {
            this.c = v;
            this.e = j;
        }

        public final V b() {
            return this.c;
        }

        public final long d() {
            return this.e;
        }

        public int hashCode() {
            long j = this.e;
            int i = (int) (j ^ (j >>> 32));
            V v = this.c;
            return v != null ? i + v.hashCode() : i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.e != aVar.e) {
                return false;
            }
            V v = this.c;
            if (v == null) {
                return aVar.c == null;
            }
            return v.equals(aVar.c);
        }

        public String toString() {
            return this.e + ": " + this.c;
        }
    }
}
