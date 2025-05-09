package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.core.Scheduler;

/* loaded from: classes7.dex */
public interface SchedulerMultiWorkerSupport {

    /* loaded from: classes10.dex */
    public interface WorkerCallback {
        void onWorker(int i, Scheduler.Worker worker);
    }

    void createWorkers(int i, WorkerCallback workerCallback);
}
