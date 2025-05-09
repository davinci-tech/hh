package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class i0 implements Callable<m0> {
    public static final String c = "DnsCallable";

    /* renamed from: a, reason: collision with root package name */
    public final String f5299a;
    public final ExecutorService b;

    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final String f5300a;
        public final h0 b;
        public final BlockingQueue c;

        @Override // java.lang.Runnable
        public void run() {
            Logger.v(i0.c, (this.b == h0.b ? "LocalDNS" : "HttpDNS").concat(" do look up"));
            m0 lookup = this.b.lookup(this.f5300a);
            if (lookup.j()) {
                if (this.b != h0.b) {
                    Logger.w(i0.c, "HttpDNS do look up result is empty");
                    return;
                }
                Logger.w(i0.c, "LocalDNS do look up result is empty");
            }
            this.c.add(lookup);
        }

        public a(String str, h0 h0Var, BlockingQueue blockingQueue) {
            this.f5300a = str;
            this.b = h0Var;
            this.c = blockingQueue;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public m0 call() {
        Throwable e;
        m0 m0Var;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        m0 m0Var2 = new m0();
        try {
            if (t.m().k()) {
                this.b.execute(new a(this.f5299a, h0.d, linkedBlockingQueue));
            }
            this.b.execute(new a(this.f5299a, h0.b, linkedBlockingQueue));
            m0Var = (m0) linkedBlockingQueue.poll(y.a(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | RuntimeException e2) {
            e = e2;
            m0Var = m0Var2;
        }
        if (m0Var == null) {
            return m0Var2;
        }
        t.m().a(this.f5299a, m0Var.f());
        try {
            Logger.i(c, (m0Var.f() == 3 ? "HttpDNS" : "LocalDNS").concat(" finish loop up"));
        } catch (InterruptedException e3) {
            e = e3;
            Logger.w(c, "Dns loop Exception ", e);
            return m0Var;
        } catch (RuntimeException e4) {
            e = e4;
            Logger.w(c, "Dns loop Exception ", e);
            return m0Var;
        }
        return m0Var;
    }

    public i0(String str, ExecutorService executorService) {
        this.f5299a = str;
        this.b = executorService;
    }
}
