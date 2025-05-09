package com.huawei.openalliance.ad.download;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.inner.DownloadBlockInfo;
import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.g;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ae;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cy;
import com.huawei.openalliance.ad.utils.dl;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes5.dex */
public class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private Context f6809a;
    private b b;
    private DownloadTask c;
    private WeakReference<c> d;
    private int h;
    private boolean e = false;
    private boolean f = false;
    private final byte[] g = new byte[0];
    private boolean i = true;

    public String toString() {
        return "DownloadWorker";
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        ho.b("DownloadWorker", "[%s] running...", this);
        this.c = null;
        boolean z = false;
        while (!d()) {
            try {
                synchronized (this) {
                    while (this.b.c() > 0 && !bv.e(this.b.f6803a)) {
                        wait(1000L);
                    }
                }
                this.h = 0;
                this.i = true;
                DownloadTask b = this.b.b();
                this.c = b;
                if (b != null) {
                    z = false;
                    do {
                        synchronized (this) {
                            if (z) {
                                long pow = (long) (Math.pow(2.0d, this.h - 1) * 500.0d);
                                ho.d("DownloadWorker", "retry, interval:" + pow + ", count:" + this.h);
                                wait(pow);
                            }
                        }
                        z = b(this.c);
                        if (!z) {
                            break;
                        }
                        i = this.h;
                        this.h = i + 1;
                    } while (i < 3);
                }
            } finally {
                try {
                } catch (Throwable th) {
                }
            }
            if (this.c != null) {
                a(z);
            }
        }
    }

    public void a(DownloadTask downloadTask) {
        if (downloadTask == null || !downloadTask.equals(this.c) || b()) {
            return;
        }
        b(true);
        if (ho.a()) {
            ho.a("DownloadWorker", "cancelCurrentTask, taskId:%s", downloadTask.n());
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.download.h.1
            @Override // java.lang.Runnable
            public void run() {
                cy.a(h.this.c());
            }
        });
    }

    private File e(DownloadTask downloadTask) {
        long j;
        File file = new File(downloadTask.e());
        if (file.exists()) {
            j = file.length();
        } else {
            File parentFile = file.getParentFile();
            if ((!parentFile.exists() || !parentFile.isDirectory()) && !ae.g(parentFile)) {
                ho.c("DownloadWorker", "failed to create dirs");
                throw new IOException("fail to create dirs");
            }
            if (!file.createNewFile()) {
                ho.c("DownloadWorker", "failed to create new temp file");
                throw new IOException("fail to create new temp file");
            }
            j = 0;
        }
        downloadTask.b(j);
        return file;
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x0140, code lost:
    
        com.huawei.openalliance.ad.ho.b("DownloadWorker", "download canceled");
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0196, code lost:
    
        com.huawei.openalliance.ad.ho.c("DownloadWorker", "downloading, check file size failed");
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x019b, code lost:
    
        r1 = r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x019d, code lost:
    
        r0 = r1.c(r38, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01a1, code lost:
    
        if (r0 != false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01a3, code lost:
    
        com.huawei.openalliance.ad.utils.ae.e(r10);
        r38.a(com.huawei.openalliance.ad.download.DownloadTask.b.FILE_SIZE_ERROR);
        r1.b.j(r38);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01b0, code lost:
    
        com.huawei.openalliance.ad.utils.cy.a((java.io.Closeable) r32);
        com.huawei.openalliance.ad.utils.cy.a(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01b6, code lost:
    
        if (r11 == null) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01b8, code lost:
    
        com.huawei.openalliance.ad.utils.cy.a(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01bb, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01be, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01bc, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(com.huawei.openalliance.ad.download.DownloadTask r38) {
        /*
            Method dump skipped, instructions count: 753
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.download.h.d(com.huawei.openalliance.ad.download.DownloadTask):boolean");
    }

    private boolean d() {
        boolean z;
        synchronized (this.g) {
            z = this.f;
        }
        return z;
    }

    private boolean c(DownloadTask downloadTask, File file) {
        if (downloadTask.r() || TextUtils.isEmpty(downloadTask.b()) || !bv.c(this.f6809a)) {
            return false;
        }
        downloadTask.c(true);
        downloadTask.b(0L);
        downloadTask.c(0L);
        downloadTask.f(null);
        downloadTask.c(0);
        ae.e(file);
        this.b.i(downloadTask);
        return true;
    }

    private boolean c(DownloadTask downloadTask) {
        DownloadTask.b bVar;
        if (!bv.e(this.b.f6803a)) {
            bVar = DownloadTask.b.NO_NETWORK;
        } else {
            if (downloadTask.o() || bv.c(this.b.f6803a)) {
                return true;
            }
            bVar = DownloadTask.b.MOBILE_NETWORK;
        }
        downloadTask.a(bVar);
        this.b.j(downloadTask);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c c() {
        c cVar;
        synchronized (this) {
            WeakReference<c> weakReference = this.d;
            cVar = weakReference != null ? weakReference.get() : null;
        }
        return cVar;
    }

    private boolean b(DownloadTask downloadTask, File file) {
        ho.b("DownloadWorker", "download complete");
        if (b()) {
            if (!downloadTask.q()) {
                return false;
            }
            ho.b("DownloadWorker", "onDownloadCompleted - task is cancelled");
            ae.e(file);
            downloadTask.b(0L);
            return false;
        }
        if (!downloadTask.z() || ae.a(downloadTask.c(), file)) {
            if (!ae.a(this.f6809a, file, downloadTask.d(), downloadTask.A(), "normal")) {
                this.b.j(downloadTask);
                return false;
            }
            ho.b("DownloadWorker", "download success");
            this.b.a((b) downloadTask, 100);
            this.b.h(downloadTask);
            return false;
        }
        ho.c("DownloadWorker", "onDownloadCompleted, check file sha256 failed");
        boolean c = c(downloadTask, file);
        if (!c) {
            ae.e(file);
            downloadTask.a(DownloadTask.b.FILE_SHA256_ERROR);
            this.b.j(downloadTask);
        }
        return c;
    }

    private boolean b(DownloadTask downloadTask) {
        try {
            ho.a("DownloadWorker", "takeTask, taskId:%s, priority:%d, seqNum:%d", downloadTask.n(), Integer.valueOf(downloadTask.k()), Long.valueOf(downloadTask.m()));
            downloadTask.a((DownloadBlockInfo) null);
            if (!c(downloadTask)) {
                ho.a("DownloadWorker", "executeTask, network error, taskId:%s", downloadTask.n());
                return false;
            }
            downloadTask.a(this);
            downloadTask.a(2);
            if (!d(downloadTask)) {
                return false;
            }
            d(downloadTask);
            return false;
        } catch (Throwable th) {
            ho.d("DownloadWorker", "executeTask Exception, taskId:" + dl.a(downloadTask.n()));
            ho.a(5, th);
            return a();
        }
    }

    private boolean b() {
        boolean z;
        synchronized (this) {
            z = this.e;
        }
        return z;
    }

    private void b(boolean z) {
        synchronized (this) {
            this.e = z;
        }
    }

    private c b(c cVar, DownloadTask downloadTask, File file) {
        ho.b("DownloadWorker", "checkConn - try Safe Url");
        if (downloadTask.r() || TextUtils.isEmpty(downloadTask.b()) || !ae.f(file)) {
            ho.b("DownloadWorker", "checkConn - fail to switch to safe url, stop downloading");
            downloadTask.a(DownloadTask.b.FILE_SIZE_ERROR);
            if (file.length() <= 0) {
                ae.e(file);
            }
            cy.a(cVar);
            return null;
        }
        ho.b("DownloadWorker", "checkConn - switch to safe url ok");
        cy.a(cVar);
        downloadTask.b(0L);
        downloadTask.c(0L);
        downloadTask.c(true);
        downloadTask.f(null);
        downloadTask.c(0);
        return a(downloadTask, file);
    }

    private static boolean a(c cVar, DownloadTask downloadTask) {
        return downloadTask.g() <= 0 || cVar.b() == 206;
    }

    private boolean a() {
        if (!b()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
                ho.d("DownloadWorker", "exception occur when wait for sync cancel");
            }
        }
        return !b();
    }

    private void a(boolean z) {
        if (z) {
            try {
                this.b.j(this.c);
            } catch (Throwable unused) {
                ho.d("DownloadWorker", "call manager.onDownloadFail Exception");
            }
        }
        try {
            if (b() && this.c.p() == DownloadTask.c.USER_CLICK) {
                this.c.a(0);
            }
            this.c.a((h) null);
            this.b.a((b) this.c);
            this.c = null;
        } catch (Throwable unused2) {
            ho.d("DownloadWorker", "run Exception");
        }
    }

    private void a(c cVar) {
        synchronized (this) {
            this.d = new WeakReference<>(cVar);
        }
    }

    private void a(DownloadTask downloadTask, long j, long j2, DownloadBlockInfo downloadBlockInfo) {
        if (j == 0) {
            ho.c("DownloadWorker", "speed log - no start time");
            return;
        }
        long c = ao.c();
        long j3 = c - j;
        if (j3 > 0) {
            if (!downloadTask.q()) {
                if (downloadBlockInfo == null) {
                    downloadBlockInfo = new DownloadBlockInfo();
                }
                downloadBlockInfo.a(j);
                downloadBlockInfo.b(c);
                downloadBlockInfo.c(j2);
                downloadTask.a(downloadBlockInfo);
                downloadTask.y();
            }
            ho.b("DownloadWorker", "download complete - total time: %d(ms) total download size: %d(bytes) avg. speed: %d(bytes/s)", Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(((100000 * j2) / j3) / 100));
            return;
        }
        ho.c("DownloadWorker", "speed log - duration <= 0");
    }

    private c a(c cVar, DownloadTask downloadTask, File file) {
        ho.b("DownloadWorker", "checkConn start");
        try {
            long a2 = g.a(cVar);
            if (downloadTask.f() > 0 && a2 > 0 && downloadTask.f() != a2) {
                ho.a("DownloadWorker", "task size:%d, header size:%d", Long.valueOf(downloadTask.f()), Long.valueOf(a2));
                ho.b("DownloadWorker", "checkConn - may be hijacked, switch to safe url");
                cVar = b(cVar, downloadTask, file);
            }
            ho.b("DownloadWorker", "checkConn end");
            return cVar;
        } catch (g.a e) {
            downloadTask.c(downloadTask.t() + 1);
            downloadTask.f(e.a());
            int e2 = this.b.e();
            ho.c("DownloadWorker", "checkConn - url is redirected [count: %d, max: %d]", Integer.valueOf(downloadTask.t()), Integer.valueOf(e2));
            if (TextUtils.isEmpty(downloadTask.s()) || downloadTask.t() > e2) {
                return b(cVar, downloadTask, file);
            }
            ho.b("DownloadWorker", "checkConn - connect with redirected url");
            cy.a(cVar);
            return a(downloadTask, file);
        }
    }

    private c a(DownloadTask downloadTask, File file) {
        String a2;
        c cVar = null;
        try {
            if (!TextUtils.isEmpty(downloadTask.s()) && downloadTask.t() < this.b.e()) {
                ho.b("DownloadWorker", "create connection with redirected url");
                a2 = downloadTask.s();
            } else if (!downloadTask.r() || TextUtils.isEmpty(downloadTask.b())) {
                ho.b("DownloadWorker", "create connection with normal url");
                a2 = downloadTask.a();
            } else {
                ho.b("DownloadWorker", "create connection with safe url");
                a2 = downloadTask.b();
                downloadTask.f(null);
                downloadTask.c(0);
            }
            if (ho.a()) {
                ho.a("DownloadWorker", "url: %s", dl.a(a2));
            }
            cVar = c.a(this.f6809a, a2, downloadTask.g());
            return a(cVar, downloadTask, file);
        } catch (com.huawei.openalliance.ad.exception.d e) {
            cy.a(cVar);
            throw e;
        } catch (IOException e2) {
            downloadTask.a(DownloadTask.b.CONN_FAILED);
            cy.a(cVar);
            throw e2;
        } catch (KeyStoreException e3) {
            cy.a(cVar);
            throw e3;
        } catch (NoSuchAlgorithmException e4) {
            cy.a(cVar);
            throw e4;
        }
    }

    public h(b bVar) {
        this.b = bVar;
        this.f6809a = bVar.f6803a;
    }
}
