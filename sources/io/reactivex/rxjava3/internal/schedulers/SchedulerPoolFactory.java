package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes7.dex */
public final class SchedulerPoolFactory {
    static final String PURGE_ENABLED_KEY = "rx3.purge-enabled";
    public static final boolean PURGE_ENABLED = getBooleanProperty(true, PURGE_ENABLED_KEY, true, true, new SystemPropertyAccessor());

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    static boolean getBooleanProperty(boolean z, String str, boolean z2, boolean z3, Function<String, String> function) {
        if (!z) {
            return z3;
        }
        try {
            String apply = function.apply(str);
            return apply == null ? z2 : "true".equals(apply);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return z2;
        }
    }

    static final class SystemPropertyAccessor implements Function<String, String> {
        SystemPropertyAccessor() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public String apply(String str) {
            return System.getProperty(str);
        }
    }

    public static ScheduledExecutorService create(ThreadFactory threadFactory) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, threadFactory);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(PURGE_ENABLED);
        return scheduledThreadPoolExecutor;
    }
}
