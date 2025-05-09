package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.Continuation;
import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hmf.tasks.a.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public class j {
    public static Task<Void> b(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setResult(null);
            return taskCompletionSource.getTask();
        }
        Iterator<? extends Task<?>> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException("task can not is null");
            }
        }
        i iVar = new i();
        e eVar = new e(collection.size(), iVar);
        for (Task<?> task : collection) {
            task.addOnSuccessListener(TaskExecutors.immediate(), eVar);
            i iVar2 = (i) task;
            iVar2.a((ExecuteResult) new f(TaskExecutors.immediate(), eVar));
            iVar2.a((ExecuteResult) new com.huawei.hmf.tasks.a.b(TaskExecutors.immediate(), eVar));
        }
        return iVar;
    }

    public static Task<List<Task<?>>> a(Collection<? extends Task<?>> collection) {
        Task<Void> b2 = b(collection);
        b bVar = new b(collection);
        i iVar = (i) b2;
        iVar.getClass();
        Executor uiThread = TaskExecutors.uiThread();
        i iVar2 = new i();
        iVar.addOnCompleteListener(uiThread, new i.e(iVar, iVar2, bVar));
        return iVar2;
    }

    public static <TResult> Task<List<TResult>> c(Collection<? extends Task<?>> collection) {
        Task<Void> b2 = b(collection);
        c cVar = new c(collection);
        i iVar = (i) b2;
        iVar.getClass();
        Executor uiThread = TaskExecutors.uiThread();
        i iVar2 = new i();
        iVar.addOnCompleteListener(uiThread, new i.e(iVar, iVar2, cVar));
        return iVar2;
    }

    public static class d<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {

        /* renamed from: a, reason: collision with root package name */
        public final CountDownLatch f4242a = new CountDownLatch(1);

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        public final void onSuccess(TResult tresult) {
            this.f4242a.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public final void onFailure(Exception exc) {
            this.f4242a.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnCanceledListener
        public void onCanceled() {
            this.f4242a.countDown();
        }
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TaskCompletionSource f4239a;
        public final /* synthetic */ Callable b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4239a.setResult(this.b.call());
            } catch (Exception e) {
                this.f4239a.setException(e);
            }
        }

        public a(j jVar, TaskCompletionSource taskCompletionSource, Callable callable) {
            this.f4239a = taskCompletionSource;
            this.b = callable;
        }
    }

    public final <TResult> TResult a(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    public static final class b implements Continuation<Void, List<Task<?>>> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Collection f4240a;

        @Override // com.huawei.hmf.tasks.Continuation
        public List<Task<?>> then(Task<Void> task) throws Exception {
            ArrayList arrayList = new ArrayList(this.f4240a.size());
            arrayList.addAll(this.f4240a);
            return arrayList;
        }

        public b(Collection collection) {
            this.f4240a = collection;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [TResult] */
    public static final class c<TResult> implements Continuation<Void, List<TResult>> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Collection f4241a;

        @Override // com.huawei.hmf.tasks.Continuation
        public Object then(Task<Void> task) throws Exception {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.f4241a.iterator();
            while (it.hasNext()) {
                arrayList.add(((Task) it.next()).getResult());
            }
            return arrayList;
        }

        public c(Collection collection) {
            this.f4241a = collection;
        }
    }

    public <TResult> Task<TResult> a(Executor executor, Callable<TResult> callable) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new a(this, taskCompletionSource, callable));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
        return taskCompletionSource.getTask();
    }
}
