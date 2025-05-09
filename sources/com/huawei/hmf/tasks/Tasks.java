package com.huawei.hmf.tasks;

import android.os.Looper;
import com.huawei.hmf.tasks.a.i;
import com.huawei.hmf.tasks.a.j;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class Tasks {
    public static j IMPL = new j();

    public static <TResult> Task<TResult> fromCanceled() {
        i iVar = new i();
        iVar.a();
        return iVar;
    }

    public static <TResult> Task<TResult> fromException(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(exc);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> fromResult(TResult tresult) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    public static <TResult> TResult await(Task<TResult> task) throws ExecutionException, InterruptedException {
        j jVar = IMPL;
        jVar.getClass();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("await must not be called on the UI thread");
        }
        if (task.isComplete()) {
            return (TResult) jVar.a(task);
        }
        j.d dVar = new j.d();
        ((i) task).addOnSuccessListener(TaskExecutors.uiThread(), dVar).addOnFailureListener(dVar);
        dVar.f4242a.await();
        return (TResult) jVar.a(task);
    }

    public static <TResult> TResult await(Task<TResult> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        j jVar = IMPL;
        jVar.getClass();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            if (!task.isComplete()) {
                j.d dVar = new j.d();
                ((i) task).addOnSuccessListener(TaskExecutors.uiThread(), dVar).addOnFailureListener(dVar);
                if (!dVar.f4242a.await(j, timeUnit)) {
                    throw new TimeoutException("Timed out waiting for Task");
                }
            }
            return (TResult) jVar.a(task);
        }
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    public static <TResult> Task<List<TResult>> successOf(Task<?>... taskArr) {
        return j.c(Arrays.asList(taskArr));
    }

    public static <TResult> Task<List<TResult>> successOf(Collection<? extends Task<TResult>> collection) {
        return j.c(collection);
    }

    public static Task<Void> join(Task<?>... taskArr) {
        return j.b(Arrays.asList(taskArr));
    }

    public static Task<Void> join(Collection<? extends Task<?>> collection) {
        return j.b(collection);
    }

    public static <TResult> Task<TResult> callInBackground(Executor executor, Callable<TResult> callable) {
        return IMPL.a(executor, callable);
    }

    public static <TResult> Task<TResult> callInBackground(Callable<TResult> callable) {
        return IMPL.a(TaskExecutors.background(), callable);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return IMPL.a(TaskExecutors.immediate(), callable);
    }

    public static Task<List<Task<?>>> allOf(Task<?>... taskArr) {
        return j.a(Arrays.asList(taskArr));
    }

    public static Task<List<Task<?>>> allOf(Collection<? extends Task<?>> collection) {
        return j.a(collection);
    }
}
