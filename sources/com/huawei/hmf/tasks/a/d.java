package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class d<TResult> implements ExecuteResult<TResult> {

    /* renamed from: a, reason: collision with root package name */
    public OnCompleteListener<TResult> f4223a;
    public Executor b;
    public final Object c = new Object();

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public final void onComplete(Task<TResult> task) {
        this.b.execute(new a(task));
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Task f4224a;

        @Override // java.lang.Runnable
        public void run() {
            synchronized (d.this.c) {
                if (d.this.f4223a != null) {
                    d.this.f4223a.onComplete(this.f4224a);
                }
            }
        }

        public a(Task task) {
            this.f4224a = task;
        }
    }

    @Override // com.huawei.hmf.tasks.ExecuteResult
    public void cancel() {
        synchronized (this.c) {
            this.f4223a = null;
        }
    }

    public d(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.f4223a = onCompleteListener;
        this.b = executor;
    }
}
