package com.huawei.android.appbundle.remote;

import com.huawei.hmf.tasks.TaskCompletionSource;

/* loaded from: classes8.dex */
public abstract class RemoteTask implements Runnable {
    private final TaskCompletionSource<?> mTask;

    protected abstract void execute();

    public RemoteTask() {
        this.mTask = null;
    }

    public RemoteTask(TaskCompletionSource<?> taskCompletionSource) {
        this.mTask = taskCompletionSource;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            execute();
        } catch (Exception e) {
            if (this.mTask != null) {
                this.mTask.setException(e);
            }
        }
    }

    public final TaskCompletionSource getTask() {
        return this.mTask;
    }
}
