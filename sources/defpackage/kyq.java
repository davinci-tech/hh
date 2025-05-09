package defpackage;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes5.dex */
public class kyq implements Runnable {
    private static final ReentrantLock c = new ReentrantLock();

    /* renamed from: a, reason: collision with root package name */
    private boolean f14706a;
    private Context b;
    private Handler d;
    private boolean e;
    private final boolean h;

    public kyq(Context context, Handler handler, boolean z, boolean z2, boolean z3) {
        this.b = context;
        this.d = handler;
        this.e = z;
        this.f14706a = z2;
        this.h = z3;
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.a("DownloadService", "DownloadService run");
        h();
        try {
            try {
                try {
                    ReentrantLock reentrantLock = c;
                    reentrantLock.lock();
                    kxu.e(-1);
                    d();
                    a();
                    reentrantLock.unlock();
                } catch (RuntimeException unused) {
                    LogUtil.b("DownloadService", "run RuntimeException e");
                }
            } catch (Exception unused2) {
                LogUtil.b("DownloadService", "run Exception");
            }
        } finally {
            c.unlock();
        }
    }

    private void a() {
        while (kxu.h() != 1) {
            if (kxu.h() == 0) {
                try {
                    LogUtil.c("DownloadService", "downloadingState DOWNLOADING_STATE_START");
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    LogUtil.b("DownloadService", "downloadingState InterruptedException e:", e.getMessage());
                }
            } else {
                if (kxu.h() == 3 && b()) {
                    return;
                }
                if (kxu.b(this.b)) {
                    e();
                    if (kxu.g()) {
                        LogUtil.a("DownloadService", "downloadingState downloadThread already running,do not start new download");
                    } else {
                        LogUtil.a("DownloadService", "downloadingState start downloadThread");
                        kxu.e(0);
                        new kyw(this.b, this.d, this.e, this.f14706a, this.h).e();
                        kxu.a(true);
                    }
                } else {
                    kxu.e(3);
                }
            }
        }
        LogUtil.a("DownloadService", "downloadingState DOWNLOADING_STATE_END");
    }

    private void e() {
        LogUtil.a("DownloadService", "downLoadDelay NetworkAvailable");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            LogUtil.b("DownloadService", "downLoadDelay Exception ", e.getMessage());
        }
    }

    private void h() {
        LogUtil.a("DownloadService", "enter setCancelDownloadFlagDelay getDownloadThreadRunningFlag:", Boolean.valueOf(kxu.g()));
        if (!kxu.g()) {
            kyw.e(false);
            return;
        }
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            LogUtil.b("DownloadService", "setCancelDownloadFlagDelay InterruptedException ", e.getMessage());
        }
        if (kxu.g()) {
            LogUtil.a("DownloadService", "setCancelDownloadFlagDelay DownloadThread not stopped in 2s");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e2) {
                LogUtil.b("DownloadService", "setCancelDownloadFlagDelay InterruptedException ", e2.getMessage());
            }
        }
        if (kxu.g()) {
            LogUtil.a("DownloadService", "setCancelDownloadFlagDelay DownloadThread not stopped in 5s");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e3) {
                LogUtil.b("DownloadService", "setCancelDownloadFlagDelay InterruptedException ", e3.getMessage());
            }
        }
        kyw.e(false);
        LogUtil.a("DownloadService", "setCancelDownloadFlagDelay leaving");
    }

    private boolean b() {
        int o = kxu.o();
        LogUtil.a("DownloadService", "processRetryState retryNum is ", Integer.valueOf(o));
        if (CommonUtil.ah(this.b)) {
            LogUtil.a("DownloadService", "processRetryState first isTypeMobile is true");
            kxu.a(11, this.e, this.f14706a, this.h);
            e(null, 11);
            kxu.e(-1);
            return true;
        }
        if (o < 3) {
            try {
                LogUtil.a("DownloadService", "processRetryState, retry in 10S,current retryNum is ", Integer.valueOf(o));
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                LogUtil.b("DownloadService", "processRetryState InterruptedException ", e.getMessage());
            }
            if (CommonUtil.ah(this.b)) {
                LogUtil.a("DownloadService", "processRetryState second isTypeMobile is true");
                kxu.a(11, this.e, this.f14706a, this.h);
                e(null, 11);
                kxu.e(-1);
                return true;
            }
            kxu.a(o + 1);
            return false;
        }
        LogUtil.a("DownloadService", "processRetryState retryOver so stop send message DOWNLOAD_FAILED_CONNECT_ERROR");
        kxu.e(1);
        kxu.a(3, this.e, this.f14706a, this.h);
        e(null, 3);
        return true;
    }

    private void d() throws IOException {
        kxl c2 = c();
        if (c2 == null) {
            LogUtil.b("DownloadService", "initPath null info");
            return;
        }
        String r = c2.r();
        if (Environment.getExternalStorageState().equals("mounted")) {
            c2.m(CommonUtil.j(this.b).getCanonicalPath() + File.separator + "download" + File.separator + r);
        } else {
            c2.m(this.b.getFilesDir() + File.separator + r);
        }
        LogUtil.c("DownloadService", "initPath apk storage path = ", c2.t(), "; mContext.getFilesDir() + File.separator =", this.b.getFilesDir(), File.separator);
        kxu.a(0);
    }

    private void e(Object obj, int i) {
        if (this.d != null) {
            Message obtain = Message.obtain();
            obtain.obj = obj;
            obtain.what = i;
            this.d.sendMessage(obtain);
        }
    }

    private kxl c() {
        if (this.e) {
            return kxu.e();
        }
        if (this.f14706a) {
            return kxu.d();
        }
        if (this.h) {
            return kxu.l();
        }
        return kxu.b();
    }
}
