package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class b<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a, reason: collision with root package name */
    public OnCanceledListener f4220a;
    public Executor b;
    public final Object c = new Object();

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(Task<TResult> task) {
        if (((i) task).c) {
            this.b.execute(new a());
        }
    }

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            synchronized (b.this.c) {
                if (b.this.f4220a != null) {
                    b.this.f4220a.onCanceled();
                }
            }
        }

        public a() {
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public void cancel() {
        synchronized (this.c) {
            this.f4220a = null;
        }
    }

    public b(Executor executor, OnCanceledListener onCanceledListener) {
        this.f4220a = onCanceledListener;
        this.b = executor;
    }
}
