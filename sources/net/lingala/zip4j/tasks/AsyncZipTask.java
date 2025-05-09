package net.lingala.zip4j.tasks;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* loaded from: classes7.dex */
public abstract class AsyncZipTask<T> {
    private final ExecutorService executorService;
    private final ProgressMonitor progressMonitor;
    private final boolean runInThread;

    protected abstract long calculateTotalWork(T t) throws ZipException;

    protected abstract void executeTask(T t, ProgressMonitor progressMonitor) throws IOException;

    protected abstract ProgressMonitor.Task getTask();

    public AsyncZipTask(c cVar) {
        this.progressMonitor = cVar.b;
        this.runInThread = cVar.d;
        this.executorService = cVar.f15273a;
    }

    public void execute(final T t) throws ZipException {
        if (this.runInThread && ProgressMonitor.State.BUSY.equals(this.progressMonitor.e())) {
            throw new ZipException("invalid operation - Zip4j is in busy state");
        }
        initProgressMonitor();
        if (this.runInThread) {
            this.progressMonitor.c(calculateTotalWork(t));
            this.executorService.execute(new Runnable() { // from class: net.lingala.zip4j.tasks.AsyncZipTask.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AsyncZipTask asyncZipTask = AsyncZipTask.this;
                        asyncZipTask.performTaskWithErrorHandling(t, asyncZipTask.progressMonitor);
                    } catch (ZipException unused) {
                    } catch (Throwable th) {
                        AsyncZipTask.this.executorService.shutdown();
                        throw th;
                    }
                    AsyncZipTask.this.executorService.shutdown();
                }
            });
            return;
        }
        performTaskWithErrorHandling(t, this.progressMonitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performTaskWithErrorHandling(T t, ProgressMonitor progressMonitor) throws ZipException {
        try {
            executeTask(t, progressMonitor);
            progressMonitor.a();
        } catch (ZipException e) {
            progressMonitor.a(e);
            throw e;
        } catch (Exception e2) {
            progressMonitor.a(e2);
            throw new ZipException(e2);
        }
    }

    protected void verifyIfTaskIsCancelled() throws ZipException {
        if (this.progressMonitor.d()) {
            this.progressMonitor.b(ProgressMonitor.Result.CANCELLED);
            this.progressMonitor.b(ProgressMonitor.State.READY);
            throw new ZipException("Task cancelled", ZipException.Type.TASK_CANCELLED_EXCEPTION);
        }
    }

    private void initProgressMonitor() {
        this.progressMonitor.b();
        this.progressMonitor.b(ProgressMonitor.State.BUSY);
        this.progressMonitor.c(getTask());
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final ExecutorService f15273a;
        private final ProgressMonitor b;
        private final boolean d;

        public c(ExecutorService executorService, boolean z, ProgressMonitor progressMonitor) {
            this.f15273a = executorService;
            this.d = z;
            this.b = progressMonitor;
        }
    }
}
