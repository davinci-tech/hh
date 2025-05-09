package com.huawei.openalliance.ad.download.app;

import com.huawei.openalliance.ad.ho;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class n implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final a f6801a;
    private final CountDownLatch b = new CountDownLatch(1);
    private final String c;
    private boolean d;
    private int e;

    public interface a {
        void a(int i);

        void a(n nVar);

        void b(n nVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f6801a == null) {
            return;
        }
        ho.a("RestoreTask", "task:%s startup", this.c);
        if (this.d) {
            ho.a("RestoreTask", "task:%s is canceled", this.c);
            return;
        }
        try {
            try {
                this.f6801a.a(this);
                this.f6801a.b(this);
                d();
            } finally {
                this.f6801a.a(this.e);
            }
        } catch (Throwable unused) {
            ho.c("RestoreTask", "try restore has exception");
        }
    }

    public void c() {
        this.d = false;
    }

    public void b() {
        this.d = true;
    }

    public void a(int i) {
        ho.a("RestoreTask", "stub result code:%s", Integer.valueOf(i));
        if (i == -1 || i == 0) {
            this.e = i;
            this.b.countDown();
        }
    }

    public String a() {
        return this.c;
    }

    private void d() {
        try {
            boolean await = this.b.await(5L, TimeUnit.SECONDS);
            ho.b("RestoreTask", "wait restore success:%s", Boolean.valueOf(await));
            if (await) {
                return;
            }
            this.e = -1;
        } catch (Throwable unused) {
            ho.c("RestoreTask", "wait result failed");
        }
    }

    public n(String str, a aVar) {
        this.c = "restore_" + str;
        this.f6801a = aVar;
    }
}
