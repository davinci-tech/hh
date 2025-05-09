package defpackage;

import com.huawei.openplatform.abl.log.HiAdLog;

/* loaded from: classes2.dex */
public class wi implements Runnable {
    private final Runnable c;

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.c;
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                HiAdLog.e("TaskWrapper", "exception in task run");
                HiAdLog.printSafeStackTrace(5, th);
            }
        }
    }

    public wi(Runnable runnable) {
        this.c = runnable;
    }
}
