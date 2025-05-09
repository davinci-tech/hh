package defpackage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes2.dex */
public class ob {
    private static final ThreadPoolExecutor d = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void e(Runnable runnable) {
        d.execute(runnable);
    }
}
