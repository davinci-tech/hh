package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.s7;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class e7 extends o6 {
    public static final int h = 1;
    public static final /* synthetic */ boolean i = true;

    @Nullable
    public Runnable c;

    @Nullable
    public ExecutorService d;

    /* renamed from: a, reason: collision with root package name */
    public int f5231a = 64;
    public int b = 5;
    public final Deque<s7.a> e = new ArrayDeque();
    public final Deque<s7.a> f = new ArrayDeque();
    public final Deque<s7> g = new ArrayDeque();

    @Override // com.huawei.hms.network.embedded.o6
    public void a(String str, int i2, String str2) {
    }

    @Override // com.huawei.hms.network.embedded.o6
    public void b(String str, int i2, String str2) {
    }

    @Override // com.huawei.hms.network.embedded.o6
    public int h() {
        return 1;
    }

    public ExecutorService i() {
        ExecutorService executorService;
        synchronized (this) {
            if (this.d == null) {
                this.d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), f8.a("OkHttp Dispatcher", false));
            }
            executorService = this.d;
        }
        return executorService;
    }

    @Override // com.huawei.hms.network.embedded.b8
    public int g() {
        int size;
        synchronized (this) {
            size = this.e.size();
        }
        return size;
    }

    @Override // com.huawei.hms.network.embedded.b8
    public int f() {
        int i2;
        synchronized (this) {
            i2 = this.b;
        }
        return i2;
    }

    @Override // com.huawei.hms.network.embedded.b8
    public int e() {
        int i2;
        synchronized (this) {
            i2 = this.f5231a;
        }
        return i2;
    }

    @Override // com.huawei.hms.network.embedded.b8
    public int d() {
        int size;
        int size2;
        synchronized (this) {
            size = this.f.size();
            size2 = this.g.size();
        }
        return size + size2;
    }

    @Override // com.huawei.hms.network.embedded.b8
    public List<t6> c() {
        List<t6> unmodifiableList;
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.g);
            Iterator<s7.a> it = this.f.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().f());
            }
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    @Override // com.huawei.hms.network.embedded.o6
    public void b(s7 s7Var) {
        a(this.g, s7Var);
    }

    @Override // com.huawei.hms.network.embedded.o6
    public void b(s7.a aVar) {
        aVar.c().decrementAndGet();
        a(this.f, aVar);
    }

    @Override // com.huawei.hms.network.embedded.b8
    public void b(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("max < 1: " + i2);
        }
        synchronized (this) {
            this.f5231a = i2;
        }
        j();
    }

    @Override // com.huawei.hms.network.embedded.b8
    public void b() {
        synchronized (this) {
            Iterator<s7.a> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().f().cancel();
            }
            Iterator<s7.a> it2 = this.f.iterator();
            while (it2.hasNext()) {
                it2.next().f().cancel();
            }
            Iterator<s7> it3 = this.g.iterator();
            while (it3.hasNext()) {
                it3.next().cancel();
            }
        }
    }

    @Override // com.huawei.hms.network.embedded.b8
    public void a(@Nullable Runnable runnable) {
        synchronized (this) {
            this.c = runnable;
        }
    }

    @Override // com.huawei.hms.network.embedded.o6
    public void a(s7 s7Var) {
        synchronized (this) {
            this.g.add(s7Var);
        }
    }

    @Override // com.huawei.hms.network.embedded.o6
    public void a(s7.a aVar) {
        s7.a a2;
        synchronized (this) {
            this.e.add(aVar);
            if (!aVar.f().d && (a2 = a(aVar.g())) != null) {
                aVar.a(a2);
            }
        }
        j();
    }

    @Override // com.huawei.hms.network.embedded.b8
    public void a(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("max < 1: " + i2);
        }
        synchronized (this) {
            this.b = i2;
        }
        j();
    }

    @Override // com.huawei.hms.network.embedded.b8
    public List<t6> a() {
        List<t6> unmodifiableList;
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            Iterator<s7.a> it = this.e.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().f());
            }
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    @Nullable
    public s7.a a(String str) {
        for (s7.a aVar : this.f) {
            if (aVar.g().equals(str)) {
                return aVar;
            }
        }
        for (s7.a aVar2 : this.e) {
            if (aVar2.g().equals(str)) {
                return aVar2;
            }
        }
        return null;
    }

    private boolean j() {
        int i2;
        boolean z;
        if (!i && Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<s7.a> it = this.e.iterator();
            while (it.hasNext()) {
                s7.a next = it.next();
                if (this.f.size() >= this.f5231a) {
                    break;
                }
                if (next.c().get() < this.b) {
                    it.remove();
                    next.c().incrementAndGet();
                    arrayList.add(next);
                    this.f.add(next);
                }
            }
            z = d() > 0;
        }
        int size = arrayList.size();
        for (i2 = 0; i2 < size; i2++) {
            ((s7.a) arrayList.get(i2)).a(i());
        }
        return z;
    }

    private <T> void a(Deque<T> deque, T t) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.c;
        }
        if (j() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public e7(ExecutorService executorService) {
        this.d = executorService;
    }

    public e7() {
    }
}
