package io.reactivex.rxjava3.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.Callable;

/* loaded from: classes7.dex */
public final class AndroidSchedulers {
    private static final Scheduler MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler(new Callable() { // from class: io.reactivex.rxjava3.android.schedulers.AndroidSchedulers$$ExternalSyntheticLambda0
        @Override // java.util.concurrent.Callable
        public final Object call() {
            Scheduler scheduler;
            scheduler = AndroidSchedulers.MainHolder.DEFAULT;
            return scheduler;
        }
    });

    static final class MainHolder {
        static final Scheduler DEFAULT = AndroidSchedulers.internalFrom(Looper.getMainLooper(), true);

        private MainHolder() {
        }
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    public static Scheduler from(Looper looper) {
        return from(looper, true);
    }

    public static Scheduler from(Looper looper, boolean z) {
        if (looper == null) {
            throw new NullPointerException("looper == null");
        }
        return internalFrom(looper, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Scheduler internalFrom(Looper looper, boolean z) {
        return new HandlerScheduler(new Handler(looper), z);
    }

    private AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }
}
