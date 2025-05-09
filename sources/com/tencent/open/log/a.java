package com.tencent.open.log;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.model.RecordAction;
import com.tencent.tauth.Tencent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes7.dex */
public class a extends Tracer implements Handler.Callback {

    /* renamed from: a, reason: collision with root package name */
    private b f11357a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile f e;
    private volatile f f;
    private volatile f g;
    private volatile f h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    public a(b bVar) {
        this(c.b, true, g.f11363a, bVar);
    }

    public a(int i, boolean z, g gVar, b bVar) {
        super(i, z, gVar);
        this.i = false;
        a(bVar);
        this.e = new f();
        this.f = new f();
        this.g = this.e;
        this.h = this.f;
        this.d = new char[bVar.c()];
        HandlerThread handlerThread = new HandlerThread(bVar.b(), bVar.d());
        this.j = handlerThread;
        handlerThread.start();
        if (!this.j.isAlive() || this.j.getLooper() == null) {
            return;
        }
        this.k = new Handler(this.j.getLooper(), this);
    }

    public void a() {
        if (this.k.hasMessages(1024)) {
            this.k.removeMessages(1024);
        }
        this.k.sendEmptyMessage(1024);
    }

    public void b() {
        h();
        this.j.quit();
    }

    @Override // com.tencent.open.log.Tracer
    protected void doTrace(int i, Thread thread, long j, String str, String str2, Throwable th) {
        a(e().a(i, thread, j, str, str2, th));
    }

    private void a(String str) {
        this.g.a(str);
        if (this.g.a() >= c().c()) {
            a();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1024) {
            return true;
        }
        f();
        return true;
    }

    private void f() {
        if (Thread.currentThread() == this.j && !this.i) {
            this.i = true;
            i();
            try {
                try {
                    this.h.a(g(), this.d);
                } catch (IOException e) {
                    SLog.e("FileTracer", "flushBuffer exception", e);
                }
                this.h.b();
                this.i = false;
            } catch (Throwable th) {
                this.h.b();
                throw th;
            }
        }
    }

    private Writer g() {
        File a2 = c().a();
        if (a2 != null && ((a2 != null && !a2.equals(this.c)) || (this.b == null && a2 != null))) {
            this.c = a2;
            h();
            try {
                this.b = new FileWriter(this.c, true);
            } catch (IOException unused) {
                this.b = null;
                SLog.e(SLog.TAG, "-->obtainFileWriter() app specific file permission denied");
            }
            a(a2);
        }
        return this.b;
    }

    private void a(File file) {
        File[] listFiles;
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists() || !parentFile.isDirectory() || (listFiles = parentFile.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (b(file2)) {
                String name = file2.getName();
                if (b.a(System.currentTimeMillis() - (Tencent.USE_ONE_HOUR ? 3600000L : 259200000L)).compareTo(name.substring(32, 43)) > 0) {
                    SLog.d("FileTracer", "delete name=" + name + ", success=" + file2.delete());
                }
            }
        }
    }

    private boolean b(File file) {
        if (file == null) {
            return false;
        }
        String name = file.getName();
        SLog.d("FileTracer", RecordAction.ACT_NAME_TAG + name);
        return !TextUtils.isEmpty(name) && name.length() == 47 && name.startsWith("com.tencent.mobileqq_connectSdk.") && name.endsWith(".log");
    }

    private void h() {
        try {
            FileWriter fileWriter = this.b;
            if (fileWriter != null) {
                fileWriter.flush();
                this.b.close();
            }
        } catch (IOException e) {
            SLog.e(SLog.TAG, "-->closeAppSpecificFileWriter() exception:", e);
        }
    }

    private void i() {
        synchronized (this) {
            if (this.g == this.e) {
                this.g = this.f;
                this.h = this.e;
            } else {
                this.g = this.e;
                this.h = this.f;
            }
        }
    }

    public b c() {
        return this.f11357a;
    }

    public void a(b bVar) {
        this.f11357a = bVar;
    }
}
