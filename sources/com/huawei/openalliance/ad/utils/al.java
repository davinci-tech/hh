package com.huawei.openalliance.ad.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class al {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f7583a = new byte[0];
    private final byte[] b = new byte[0];
    private final String c;
    private ak d;
    private HandlerThread e;
    private int f;

    public void b() {
        synchronized (this.f7583a) {
            if (!d()) {
                ho.b("HandlerExecAgent", "release exec agent - not working");
                return;
            }
            int i = this.f - 1;
            this.f = i;
            if (i <= 0) {
                this.f = 0;
                c();
            }
            if (ho.a()) {
                ho.a("HandlerExecAgent", "release exec agent - ref count: %d", Integer.valueOf(this.f));
            }
        }
    }

    public void a(String str) {
        if (d()) {
            ak f = f();
            if (f != null) {
                f.a(str);
            } else {
                a(new a(2, null, str, 0L));
            }
        }
    }

    public void a(Runnable runnable, String str, long j) {
        if (d()) {
            ak f = f();
            if (f != null) {
                f.a(runnable, str, j);
            } else {
                a(new a(1, runnable, str, j));
            }
        }
    }

    public void a(Runnable runnable) {
        if (d()) {
            ak f = f();
            if (f != null) {
                f.a(runnable);
            } else {
                a(new a(1, runnable, null, 0L));
            }
        }
    }

    public void a() {
        synchronized (this.f7583a) {
            this.f++;
            ak f = f();
            if (f != null) {
                f.a("handler_exec_release_task");
            }
            if (ho.a()) {
                ho.a("HandlerExecAgent", "acquire exec agent. ref count: %d", Integer.valueOf(this.f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ak f() {
        ak akVar;
        synchronized (this.f7583a) {
            akVar = this.d;
        }
        return akVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (d()) {
            synchronized (this.b) {
                if (this.e == null) {
                    ho.b("HandlerExecAgent", "init handler thread");
                    HandlerThread handlerThread = new HandlerThread(this.c);
                    handlerThread.start();
                    Looper looper = handlerThread.getLooper();
                    if (looper != null) {
                        this.e = handlerThread;
                        a(new ak(new Handler(looper)));
                    } else {
                        handlerThread.quit();
                    }
                }
            }
        }
    }

    private boolean d() {
        boolean z;
        synchronized (this.f7583a) {
            z = this.f > 0;
        }
        return z;
    }

    private void c() {
        ak f = f();
        if (f != null) {
            ho.b("HandlerExecAgent", "delay quit thread");
            f.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.al.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (al.this.b) {
                        if (al.this.e != null) {
                            al.this.e.quitSafely();
                            al.this.e = null;
                        }
                        al.this.a((ak) null);
                        ho.b("HandlerExecAgent", "quit thread and release");
                    }
                }
            }, "handler_exec_release_task", 60000L);
        }
    }

    private void a(final a aVar) {
        m.g(new Runnable() { // from class: com.huawei.openalliance.ad.utils.al.2
            @Override // java.lang.Runnable
            public void run() {
                al.this.e();
                ak f = al.this.f();
                if (f != null) {
                    if (aVar.f7586a == 1) {
                        f.a(aVar.b, aVar.c, aVar.d);
                    } else if (aVar.f7586a == 2) {
                        f.a(aVar.c);
                    }
                }
            }
        });
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f7586a;
        Runnable b;
        String c;
        long d;

        public String toString() {
            return "CacheTask{taskType=" + this.f7586a + ", id='" + this.c + "'}";
        }

        a(int i, Runnable runnable, String str, long j) {
            this.f7586a = i;
            this.b = runnable;
            this.c = str;
            this.d = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ak akVar) {
        synchronized (this.f7583a) {
            this.d = akVar;
        }
    }

    public al(String str) {
        this.c = TextUtils.isEmpty(str) ? "handler_exec_thread" : str;
    }
}
