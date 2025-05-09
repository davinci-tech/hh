package com.huawei.agconnect.apms;

import android.content.ComponentCallbacks2;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes2.dex */
public class o implements ComponentCallbacks2 {
    public ScheduledExecutorService abc = Executors.newSingleThreadScheduledExecutor(new j0("UIBackgroundListener"));

    public static class abc implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            n abc = n.abc();
            abc.bcd.execute(new k(abc));
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 20) {
            this.abc.submit(new abc());
        }
    }
}
