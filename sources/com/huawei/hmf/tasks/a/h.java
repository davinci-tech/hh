package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class h<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a, reason: collision with root package name */
    public OnSuccessListener<TResult> f4229a;
    public Executor b;
    public final Object c = new Object();

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(Task<TResult> task) {
        if (!task.isSuccessful() || ((i) task).c) {
            return;
        }
        this.b.execute(new a(task));
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Task f4230a;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            synchronized (h.this.c) {
                if (h.this.f4229a != null) {
                    h.this.f4229a.onSuccess(this.f4230a.getResult());
                }
            }
        }

        public a(Task task) {
            this.f4230a = task;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public void cancel() {
        synchronized (this.c) {
            this.f4229a = null;
        }
    }

    public h(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        this.f4229a = onSuccessListener;
        this.b = executor;
    }
}
