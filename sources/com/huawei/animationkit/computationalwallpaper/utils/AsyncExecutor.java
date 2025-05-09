package com.huawei.animationkit.computationalwallpaper.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import defpackage.abv;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes8.dex */
public abstract class AsyncExecutor<T, R> {
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    protected abstract void finishOnUiThread(R r, abv abvVar);

    protected abstract R runInBackground(T t) throws abv;

    public void execute(T t) {
        this.mExecutorService.submit(new e(t));
    }

    class e implements Runnable {
        private final T b;

        private e(T t) {
            this.b = t;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = null;
            try {
                e = null;
                obj = AsyncExecutor.this.runInBackground(this.b);
            } catch (abv e) {
                e = e;
                Log.e("ExecuteRunnable", "run generate task failed");
            }
            AsyncExecutor.this.mHandler.post(new b(obj, e));
        }
    }

    class b implements Runnable {
        private final abv b;
        private final R e;

        public b(R r, abv abvVar) {
            this.e = r;
            this.b = abvVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncExecutor.this.finishOnUiThread(this.e, this.b);
        }
    }
}
