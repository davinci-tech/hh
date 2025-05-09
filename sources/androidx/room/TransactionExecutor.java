package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
class TransactionExecutor implements Executor {
    private Runnable mActive;
    private final Executor mExecutor;
    private final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();

    TransactionExecutor(Executor executor) {
        this.mExecutor = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        synchronized (this) {
            this.mTasks.offer(new Runnable() { // from class: androidx.room.TransactionExecutor.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        TransactionExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.mActive == null) {
                scheduleNext();
            }
        }
    }

    void scheduleNext() {
        synchronized (this) {
            Runnable poll = this.mTasks.poll();
            this.mActive = poll;
            if (poll != null) {
                this.mExecutor.execute(poll);
            }
        }
    }
}
