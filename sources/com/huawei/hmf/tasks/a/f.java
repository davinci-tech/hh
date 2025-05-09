package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class f<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a, reason: collision with root package name */
    public OnFailureListener f4226a;
    public Executor b;
    public final Object c = new Object();

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(Task<TResult> task) {
        if (task.isSuccessful() || ((i) task).c) {
            return;
        }
        this.b.execute(new a(task));
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Task f4227a;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (f.this.c) {
                if (f.this.f4226a != null) {
                    f.this.f4226a.onFailure(this.f4227a.getException());
                }
            }
        }

        public a(Task task) {
            this.f4227a = task;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public void cancel() {
        synchronized (this.c) {
            this.f4226a = null;
        }
    }

    public f(Executor executor, OnFailureListener onFailureListener) {
        this.f4226a = onFailureListener;
        this.b = executor;
    }
}
