package com.huawei.hmf.tasks.a;

import android.app.Activity;
import com.huawei.hmf.tasks.Continuation;
import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.SuccessContinuation;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class i<TResult> extends Task<TResult> {
    public boolean b;
    public volatile boolean c;
    public TResult d;
    public Exception e;

    /* renamed from: a, reason: collision with root package name */
    public final Object f4231a = new Object();
    public List<ExecuteResult<TResult>> f = new ArrayList();

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return a((ExecuteResult) new com.huawei.hmf.tasks.a.b(TaskExecutors.uiThread(), onCanceledListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        return a((ExecuteResult) new f(TaskExecutors.uiThread(), onFailureListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        Executor uiThread = TaskExecutors.uiThread();
        i iVar = new i();
        addOnCompleteListener(uiThread, new e(this, iVar, continuation));
        return iVar;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        Executor uiThread = TaskExecutors.uiThread();
        i iVar = new i();
        addOnCompleteListener(uiThread, new d(this, continuation, iVar));
        return iVar;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isSuccessful() {
        boolean z;
        synchronized (this.f4231a) {
            z = this.b && !this.c && this.e == null;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        Executor uiThread = TaskExecutors.uiThread();
        i iVar = new i();
        addOnSuccessListener(uiThread, new a(this, successContinuation, iVar));
        addOnFailureListener(new b(this, iVar));
        a((ExecuteResult) new com.huawei.hmf.tasks.a.b(TaskExecutors.uiThread(), new c(this, iVar)));
        return iVar;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        i iVar = new i();
        addOnSuccessListener(executor, new a(this, successContinuation, iVar));
        addOnFailureListener(new b(this, iVar));
        a((ExecuteResult) new com.huawei.hmf.tasks.a.b(TaskExecutors.uiThread(), new c(this, iVar)));
        return iVar;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isComplete() {
        boolean z;
        synchronized (this.f4231a) {
            z = this.b;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.Task
    public boolean isCanceled() {
        return this.c;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <E extends Throwable> TResult getResultThrowException(Class<E> cls) throws Throwable {
        TResult tresult;
        synchronized (this.f4231a) {
            if (cls != null) {
                if (cls.isInstance(this.e)) {
                    throw cls.cast(this.e);
                }
            }
            if (this.e != null) {
                throw new RuntimeException(this.e);
            }
            tresult = this.d;
        }
        return tresult;
    }

    @Override // com.huawei.hmf.tasks.Task
    public TResult getResult() {
        TResult tresult;
        synchronized (this.f4231a) {
            if (this.e != null) {
                throw new RuntimeException(this.e);
            }
            tresult = this.d;
        }
        return tresult;
    }

    @Override // com.huawei.hmf.tasks.Task
    public Exception getException() {
        Exception exc;
        synchronized (this.f4231a) {
            exc = this.e;
        }
        return exc;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        i iVar = new i();
        addOnCompleteListener(executor, new d(this, continuation, iVar));
        return iVar;
    }

    @Override // com.huawei.hmf.tasks.Task
    public <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        i iVar = new i();
        addOnCompleteListener(executor, new e(this, iVar, continuation));
        return iVar;
    }

    public final void b() {
        synchronized (this.f4231a) {
            Iterator<ExecuteResult<TResult>> it = this.f.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onComplete(this);
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
            this.f = null;
        }
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        return a((ExecuteResult) new h(executor, onSuccessListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnSuccessListener(OnSuccessListener<TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.uiThread(), onSuccessListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnSuccessListener(Activity activity, OnSuccessListener<TResult> onSuccessListener) {
        h hVar = new h(TaskExecutors.uiThread(), onSuccessListener);
        g.a(activity, hVar);
        return a((ExecuteResult) hVar);
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        return a((ExecuteResult) new f(executor, onFailureListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        f fVar = new f(TaskExecutors.uiThread(), onFailureListener);
        g.a(activity, fVar);
        return a((ExecuteResult) fVar);
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        return a((ExecuteResult) new com.huawei.hmf.tasks.a.d(executor, onCompleteListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.uiThread(), onCompleteListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCompleteListener(Activity activity, OnCompleteListener<TResult> onCompleteListener) {
        com.huawei.hmf.tasks.a.d dVar = new com.huawei.hmf.tasks.a.d(TaskExecutors.uiThread(), onCompleteListener);
        g.a(activity, dVar);
        return a((ExecuteResult) dVar);
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        return a((ExecuteResult) new com.huawei.hmf.tasks.a.b(executor, onCanceledListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public Task<TResult> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        com.huawei.hmf.tasks.a.b bVar = new com.huawei.hmf.tasks.a.b(TaskExecutors.uiThread(), onCanceledListener);
        g.a(activity, bVar);
        return a((ExecuteResult) bVar);
    }

    public final boolean a() {
        synchronized (this.f4231a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.c = true;
            this.f4231a.notifyAll();
            b();
            return true;
        }
    }

    public final void a(TResult tresult) {
        synchronized (this.f4231a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.d = tresult;
            this.f4231a.notifyAll();
            b();
        }
    }

    /* loaded from: classes9.dex */
    public class a implements OnSuccessListener<TResult> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SuccessContinuation f4232a;
        public final /* synthetic */ i b;

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public void onSuccess(TResult tresult) {
            try {
                Task then = this.f4232a.then(tresult);
                if (then == null) {
                    this.b.a((Exception) new NullPointerException("SuccessContinuation returned null"));
                } else {
                    ((i) then).addOnCompleteListener(TaskExecutors.uiThread(), new C0070a());
                }
            } catch (Exception e) {
                this.b.a(e);
            }
        }

        /* JADX INFO: Add missing generic type declarations: [TContinuationResult] */
        /* renamed from: com.huawei.hmf.tasks.a.i$a$a, reason: collision with other inner class name */
        public class C0070a<TContinuationResult> implements OnCompleteListener<TContinuationResult> {
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<TContinuationResult> task) {
                if (task.isSuccessful()) {
                    a.this.b.a((i) task.getResult());
                } else if (((i) task).c) {
                    a.this.b.a();
                } else {
                    a.this.b.a(task.getException());
                }
            }

            public C0070a() {
            }
        }

        public a(i iVar, SuccessContinuation successContinuation, i iVar2) {
            this.f4232a = successContinuation;
            this.b = iVar2;
        }
    }

    public class b implements OnFailureListener {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f4234a;

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            this.f4234a.a(exc);
        }

        public b(i iVar, i iVar2) {
            this.f4234a = iVar2;
        }
    }

    public class c implements OnCanceledListener {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f4235a;

        @Override // com.huawei.hmf.tasks.OnCanceledListener
        public void onCanceled() {
            this.f4235a.a();
        }

        public c(i iVar, i iVar2) {
            this.f4235a = iVar2;
        }
    }

    public class d implements OnCompleteListener<TResult> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Continuation f4236a;
        public final /* synthetic */ i b;

        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<TResult> task) {
            try {
                Task task2 = (Task) this.f4236a.then(task);
                if (task2 == null) {
                    this.b.a((Exception) new NullPointerException("Continuation returned null"));
                } else {
                    ((i) task2).addOnCompleteListener(TaskExecutors.uiThread(), new a());
                }
            } catch (Exception e) {
                this.b.a(e);
            }
        }

        /* JADX INFO: Add missing generic type declarations: [TContinuationResult] */
        public class a<TContinuationResult> implements OnCompleteListener<TContinuationResult> {
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<TContinuationResult> task) {
                if (task.isSuccessful()) {
                    d.this.b.a((i) task.getResult());
                } else if (((i) task).c) {
                    d.this.b.a();
                } else {
                    d.this.b.a(task.getException());
                }
            }

            public a() {
            }
        }

        public d(i iVar, Continuation continuation, i iVar2) {
            this.f4236a = continuation;
            this.b = iVar2;
        }
    }

    public final void a(Exception exc) {
        synchronized (this.f4231a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.e = exc;
            this.f4231a.notifyAll();
            b();
        }
    }

    public class e implements OnCompleteListener<TResult> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f4238a;
        public final /* synthetic */ Continuation b;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task<TResult> task) {
            if (((i) task).c) {
                this.f4238a.a();
                return;
            }
            try {
                this.f4238a.a((i) this.b.then(task));
            } catch (Exception e) {
                this.f4238a.a(e);
            }
        }

        public e(i iVar, i iVar2, Continuation continuation) {
            this.f4238a = iVar2;
            this.b = continuation;
        }
    }

    public Task<TResult> a(ExecuteResult<TResult> executeResult) {
        boolean isComplete;
        synchronized (this.f4231a) {
            isComplete = isComplete();
            if (!isComplete) {
                this.f.add(executeResult);
            }
        }
        if (isComplete) {
            executeResult.onComplete(this);
        }
        return this;
    }
}
