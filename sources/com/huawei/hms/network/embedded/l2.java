package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class l2 implements Executor {
    public static final String i = "MessageLoop";
    public static final long j = -1;
    public static final /* synthetic */ boolean k = true;
    public boolean b;
    public boolean c;
    public InterruptedIOException d;
    public RuntimeException e;
    public long f = -1;
    public boolean g = false;
    public long h = 0;

    /* renamed from: a, reason: collision with root package name */
    public final BlockingQueue<Runnable> f5356a = new LinkedBlockingQueue();

    public void updateLoopTimer(long j2) {
        this.g = true;
        long currentTimeMillis = j2 + System.currentTimeMillis();
        this.h = currentTimeMillis;
        Logger.d(i, "updateWriteTimer : %d", Long.valueOf(currentTimeMillis));
    }

    public void quit() {
        if (!k && !a()) {
            throw new AssertionError();
        }
        this.b = false;
    }

    public void loop(int i2) throws IOException {
        Runnable a2;
        if (!k && !a()) {
            throw new AssertionError();
        }
        long nanoTime = System.nanoTime();
        long convert = TimeUnit.NANOSECONDS.convert(i2, TimeUnit.MILLISECONDS);
        if (this.c) {
            InterruptedIOException interruptedIOException = this.d;
            if (interruptedIOException == null) {
                throw this.e;
            }
            throw interruptedIOException;
        }
        if (this.b) {
            throw new IllegalStateException("Cannot run loop when it is already running.");
        }
        this.b = true;
        while (this.b) {
            if (i2 == 0) {
                try {
                    a2 = a(false, 0L);
                } catch (InterruptedIOException e) {
                    Logger.d(i, e.getMessage());
                    if (this.g) {
                        long convert2 = TimeUnit.NANOSECONDS.convert(this.h - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                        Logger.d(i, "loop updateWriteTimer : %d", Long.valueOf(convert2));
                        if (convert2 > 0) {
                            nanoTime = System.nanoTime();
                            this.g = false;
                            convert = convert2;
                        }
                    }
                    this.b = false;
                    this.c = true;
                    this.d = e;
                    throw e;
                } catch (RuntimeException e2) {
                    this.b = false;
                    this.c = true;
                    this.e = e2;
                    throw e2;
                }
            } else {
                a2 = a(true, (convert - System.nanoTime()) + nanoTime);
            }
            a2.run();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) throws RejectedExecutionException {
        if (runnable == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.f5356a.put(runnable);
        } catch (InterruptedException e) {
            throw new RejectedExecutionException(e);
        }
    }

    private boolean a() {
        long j2 = this.f;
        if (j2 != -1) {
            return j2 == Thread.currentThread().getId();
        }
        this.f = Thread.currentThread().getId();
        return true;
    }

    private Runnable a(boolean z, long j2) throws InterruptedIOException {
        try {
            Runnable take = !z ? this.f5356a.take() : this.f5356a.poll(j2, TimeUnit.NANOSECONDS);
            if (take != null) {
                return take;
            }
            throw new SocketTimeoutException("loop timeout");
        } catch (InterruptedException e) {
            InterruptedIOException interruptedIOException = new InterruptedIOException();
            interruptedIOException.initCause(e);
            throw interruptedIOException;
        }
    }
}
