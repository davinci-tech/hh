package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.a.a;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes4.dex */
public final class TaskExecutors {
    public static final TaskExecutors INSTANCE = new TaskExecutors();
    public final ExecutorService mBackground = a.a();
    public final Executor mImmediate = new ImmediateExecutor();
    public final Executor mUiThread = a.b();

    public static Executor uiThread() {
        return INSTANCE.mUiThread;
    }

    public static Executor immediate() {
        return INSTANCE.mImmediate;
    }

    public static final class ImmediateExecutor implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static ExecutorService background() {
        return INSTANCE.mBackground;
    }
}
