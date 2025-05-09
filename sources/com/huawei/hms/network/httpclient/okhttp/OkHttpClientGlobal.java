package com.huawei.hms.network.httpclient.okhttp;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.ReflectionUtils;
import com.huawei.hms.network.embedded.a7;
import com.huawei.hms.network.embedded.f8;
import com.huawei.hms.network.embedded.g3;
import com.huawei.hms.network.embedded.k7;
import com.huawei.hms.network.embedded.q7;
import com.huawei.hms.network.embedded.r7;
import com.huawei.hms.network.embedded.y8;
import com.huawei.hms.network.embedded.z6;
import com.huawei.hms.network.embedded.z8;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class OkHttpClientGlobal {
    public static final int INTERVAL = 500;
    public static final String g = "OkHttpClientGlobal";
    public static OkHttpClientGlobal h = null;
    public static final int i = 200;
    public static final int j = 32;
    public static final int k = 8;
    public static final int l = 5;
    public static final TimeUnit m = TimeUnit.MINUTES;

    /* renamed from: a, reason: collision with root package name */
    public int f5643a;
    public long b;
    public TimeUnit c;
    public q7 d;
    public Deque<y8> e;
    public z8 f;

    public z6 newConnectionPool() {
        return new z6(this.f5643a, this.b, this.c);
    }

    public int getMaxIdleConnections() {
        int i2;
        synchronized (this) {
            i2 = this.f5643a;
        }
        return i2;
    }

    public List<String> getHostsInConnectionPool() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(8);
            a();
            if (this.e != null) {
                Logger.i(g, "connection pool size is: " + this.e.size());
                for (Object obj : this.e.toArray()) {
                    if (obj instanceof y8) {
                        String h2 = ((y8) obj).b().a().l().h();
                        if (!arrayList.contains(h2)) {
                            arrayList.add(h2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public TimeUnit getConnectionTimeUnit() {
        TimeUnit timeUnit;
        synchronized (this) {
            timeUnit = this.c;
        }
        return timeUnit;
    }

    public void getConnectionPool() {
        synchronized (this) {
            Object fieldObj = ReflectionUtils.getFieldObj(getInstance().getClient().g(), "delegate");
            if (fieldObj instanceof z8) {
                this.f = (z8) fieldObj;
            }
        }
    }

    public long getConnectionKeepAliveDuration() {
        long j2;
        synchronized (this) {
            j2 = this.b;
        }
        return j2;
    }

    public q7 getClient() {
        q7 q7Var;
        synchronized (this) {
            if (this.d == null) {
                k7 k7Var = new k7();
                k7Var.b(200);
                k7Var.e(32);
                this.d = new q7.c().a(new z6(this.f5643a, this.b, this.c)).a(k7Var).b(f8.a(r7.HTTP_2, r7.HTTP_1_1)).c(500L, TimeUnit.MILLISECONDS).a(g3.getFactory()).a(f8.a(a7.g, a7.j)).a();
            }
            q7Var = this.d;
        }
        return q7Var;
    }

    public void evictAll() {
        synchronized (this) {
            if (this.d != null) {
                Logger.i(g, "evictAll-start, count: " + this.d.g().a() + ", idle: " + this.d.g().c());
                this.d.g().b();
                Logger.i(g, "evictAll-end, count: " + this.d.g().a() + ", idle: " + this.d.g().c());
            }
            PreConnectManager.getInstance().clearInfo();
        }
    }

    public static void init(int i2, long j2, TimeUnit timeUnit) {
        synchronized (OkHttpClientGlobal.class) {
            if (h == null) {
                h = new OkHttpClientGlobal(i2, j2, timeUnit);
            } else if (getInstance().getMaxIdleConnections() < i2 && getInstance().getConnectionTimeUnit().toMillis(getInstance().getConnectionKeepAliveDuration()) < timeUnit.toMillis(j2)) {
                getInstance().a(i2, j2, timeUnit);
            }
        }
    }

    public static OkHttpClientGlobal getInstance() {
        OkHttpClientGlobal okHttpClientGlobal;
        synchronized (OkHttpClientGlobal.class) {
            if (h == null) {
                h = new OkHttpClientGlobal();
            }
            okHttpClientGlobal = h;
        }
        return okHttpClientGlobal;
    }

    private void a(int i2, long j2, TimeUnit timeUnit) {
        synchronized (this) {
            if (this.f == null) {
                getConnectionPool();
            }
            z8 z8Var = this.f;
            if (z8Var != null) {
                try {
                    ReflectionUtils.getField(z8Var, "maxIdleConnections").set(this.f, Integer.valueOf(i2));
                    this.f5643a = i2;
                } catch (IllegalAccessException unused) {
                    Logger.w(g, "maxIdleConnections set value failed !!!");
                }
                try {
                    ReflectionUtils.getField(this.f, "keepAliveDurationNs").set(this.f, Long.valueOf(timeUnit.toNanos(j2)));
                    this.b = j2;
                    this.c = timeUnit;
                } catch (IllegalAccessException unused2) {
                    Logger.w(g, "connectionTimeUnit and connectionKeepAliveDuration set value failed !!!");
                }
            }
        }
    }

    private void a() {
        synchronized (this) {
            if (this.f == null) {
                getConnectionPool();
            }
            z8 z8Var = this.f;
            if (z8Var != null) {
                Object fieldObj = ReflectionUtils.getFieldObj(z8Var, "connections");
                if (fieldObj instanceof ArrayDeque) {
                    this.e = ((ArrayDeque) fieldObj).clone();
                }
            }
        }
    }

    public OkHttpClientGlobal(int i2, long j2, TimeUnit timeUnit) {
        this.f5643a = 8;
        this.f5643a = i2 <= 0 ? 8 : i2;
        if (j2 <= 0) {
            this.b = 5L;
            this.c = m;
        } else {
            this.b = j2;
            this.c = timeUnit;
        }
    }

    public OkHttpClientGlobal() {
        this(8, 5L, m);
    }
}
