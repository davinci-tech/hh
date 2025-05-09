package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import java.util.concurrent.ExecutionException;

/* loaded from: classes9.dex */
public class e<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {

    /* renamed from: a, reason: collision with root package name */
    public final Object f4225a = new Object();
    public final int b;
    public final i<Void> c;
    public int d;
    public Exception e;
    public boolean f;

    @Override // com.huawei.hmf.tasks.OnSuccessListener
    public void onSuccess(TResult tresult) {
        synchronized (this.f4225a) {
            this.d++;
            a();
        }
    }

    @Override // com.huawei.hmf.tasks.OnFailureListener
    public void onFailure(Exception exc) {
        synchronized (this.f4225a) {
            this.d++;
            this.e = exc;
            a();
        }
    }

    @Override // com.huawei.hmf.tasks.OnCanceledListener
    public void onCanceled() {
        synchronized (this.f4225a) {
            this.d++;
            this.f = true;
            a();
        }
    }

    public final void a() {
        if (this.d >= this.b) {
            if (this.e != null) {
                this.c.a(new ExecutionException("a task failed", this.e));
            } else if (this.f) {
                this.c.a();
            } else {
                this.c.a((i<Void>) null);
            }
        }
    }

    public e(int i, i<Void> iVar) {
        this.b = i;
        this.c = iVar;
    }
}
