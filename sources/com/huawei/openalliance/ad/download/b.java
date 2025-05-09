package com.huawei.openalliance.ad.download;

import android.content.Context;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ae;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes5.dex */
public class b<T extends DownloadTask> {

    /* renamed from: a, reason: collision with root package name */
    public Context f6803a;
    public String b;
    protected com.huawei.openalliance.ad.download.a<T> c;
    protected h d;
    protected d<T> e;
    private ExecutorService f;
    private Integer g;

    protected int d() {
        return 256000;
    }

    protected void j(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadFail, taskId:%s", t.n());
        }
        if (ae.b(t.e()) || ae.b(this.f6803a, t.d())) {
            b(t);
        } else {
            t.b(0);
        }
        t.a(e.FAILED);
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.onDownloadFail(t);
        }
    }

    protected void i(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadSwitchSafeUrl, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.b(t);
        }
    }

    protected void h(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadSuccess, taskId:%s", t.n());
        }
        this.e.b(t);
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.onDownloadSuccess(t);
        }
    }

    protected void g(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadStart, taskId:%s", t.n());
        }
        t.a(e.DOWNLOADING);
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.onDownloadStart(t);
        }
    }

    public int g() {
        Integer num = this.g;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public boolean f() {
        Integer num = this.g;
        return num != null && num.intValue() > 0;
    }

    protected void f(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadWaiting, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.onDownloadWaiting(t);
        }
    }

    public void e(T t) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadWaitingForWifi, taskId:%s", t.n());
        }
        t.a(e.WAITING_FOR_WIFI);
        t.a(DownloadTask.c.WAITING_WIFI_DOWNLOAD);
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.a(t);
        }
    }

    public int e() {
        Context context = this.f6803a;
        if (context != null) {
            return fh.b(context).bj();
        }
        return 5;
    }

    protected boolean d(T t) {
        if (t == null) {
            return true;
        }
        boolean d = this.e.d(t);
        if (!ho.a()) {
            return true;
        }
        ho.a("DownloadManager", "deleteTask, succ:%s, taskId:%s, priority:%d", Boolean.valueOf(d), t.n(), Integer.valueOf(t.k()));
        return true;
    }

    protected void d(T t, boolean z) {
        if (t == null) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadDeleted, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.a(t, z);
        }
    }

    public boolean c(T t) {
        e i = t.i();
        boolean q = t.q();
        t.a(e.WAITING);
        t.b(false);
        boolean a2 = this.e.a((d<T>) t);
        if (ho.a()) {
            ho.a("DownloadManager", "addTask, added:%s, task:%s, priority:%d", Boolean.valueOf(a2), t.n(), Integer.valueOf(t.k()));
        }
        if (a2) {
            f(t);
        } else {
            t.a(i);
            t.b(q);
        }
        return a2;
    }

    protected void c(T t, boolean z) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadResumed, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.c(t, z);
        }
    }

    protected int c() {
        return this.e.a();
    }

    public boolean b(T t) {
        return this.e.b(t);
    }

    protected void b(T t, boolean z) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a()) {
            ho.a("DownloadManager", "onDownloadPaused, taskId:%s", t.n());
        }
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.b(t, z);
        }
    }

    protected T b() {
        return this.e.b();
    }

    protected boolean a(T t, boolean z, boolean z2, boolean z3) {
        if (t == null) {
            return false;
        }
        if (z) {
            t.b(true);
        }
        ho.b("DownloadManager", "removeTask, succ:" + this.e.g(t) + ", fromUser:" + z);
        if (z2) {
            com.huawei.openalliance.ad.utils.m.f(new a(t, this.f6803a));
        }
        if (z3) {
            d(t, z);
        }
        return true;
    }

    public boolean a(T t, boolean z) {
        if (t == null) {
            return false;
        }
        if (t.l() >= 100) {
            t.b(0);
        }
        boolean q = t.q();
        t.b(false);
        boolean f = this.e.f(t);
        if (ho.a()) {
            ho.a("DownloadManager", "resumeTask, succ:%s, taskId:%s", Boolean.valueOf(f), t.n());
        }
        if (!f) {
            t.b(q);
            return false;
        }
        t.a(e.WAITING);
        c(t, z);
        return true;
    }

    public void a(Integer num) {
        this.g = num;
    }

    public void a(com.huawei.openalliance.ad.download.a<T> aVar) {
        this.c = aVar;
    }

    public void a(T t, DownloadTask.c cVar) {
        if (t == null) {
            return;
        }
        boolean e = this.e.e(t);
        if (ho.a()) {
            ho.a("DownloadManager", "pauseTask, succ:%s, taskId:%s", Boolean.valueOf(e), t.n());
        }
        if (e) {
            ho.b("DownloadManager", "reason:" + cVar);
            t.a(cVar);
            t.a(e.IDLE);
            b(t, DownloadTask.c.USER_CLICK == cVar);
        }
    }

    protected void a(T t, int i) {
        if (t == null || t.q()) {
            return;
        }
        if (ho.a() && i % 10 == 0) {
            ho.a("DownloadManager", "onDownloadProgress, progress:%d, taskId:%s", Integer.valueOf(i), t.n());
        }
        t.b(i);
        com.huawei.openalliance.ad.download.a<T> aVar = this.c;
        if (aVar != null) {
            aVar.onDownloadProgress(t);
        }
    }

    protected void a(T t) {
        if (t != null) {
            if (ho.a()) {
                ho.a("DownloadManager", "onDownloadCompleted, taskId:%s, priority:%d", t.n(), Integer.valueOf(t.k()));
            }
            this.e.c(t);
        }
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final DownloadTask f6804a;
        private final Context b;

        @Override // java.lang.Runnable
        public void run() {
            DownloadTask downloadTask = this.f6804a;
            if (downloadTask == null) {
                return;
            }
            ae.a(this.b, downloadTask.e());
            ae.a(this.b, this.f6804a.d());
        }

        a(DownloadTask downloadTask, Context context) {
            this.f6804a = downloadTask;
            this.b = context;
        }
    }

    public void a() {
        if (this.e == null) {
            this.e = new d<>();
        }
        this.f = Executors.newFixedThreadPool(1, new f());
        h hVar = new h(this);
        this.d = hVar;
        this.f.execute(hVar);
    }

    public T a(String str) {
        return this.e.a(str);
    }

    public b(Context context) {
        this.f6803a = context.getApplicationContext();
    }
}
