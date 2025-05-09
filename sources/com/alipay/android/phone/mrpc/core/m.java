package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* loaded from: classes7.dex */
public final class m extends FutureTask<u> {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ q f828a;
    public final /* synthetic */ l b;

    @Override // java.util.concurrent.FutureTask
    public final void done() {
        o a2 = this.f828a.a();
        if (a2.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a2.h()) {
                a2.g();
                if (isCancelled() && isDone()) {
                    return;
                }
                cancel(false);
            }
        } catch (InterruptedException e) {
            e.toString();
        } catch (CancellationException unused) {
            a2.g();
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                e2.toString();
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(l lVar, Callable callable, q qVar) {
        super(callable);
        this.b = lVar;
        this.f828a = qVar;
    }
}
