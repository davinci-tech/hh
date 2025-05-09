package com.huawei.hmf.services.internal;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public class ApplicationContext {
    private static volatile Context context;

    public static void setContext(Context context2) {
        if (context == null) {
            context = context2.getApplicationContext();
        }
    }

    public static Context getContext() {
        if (context == null) {
            synchronized (ApplicationContext.class) {
                if (context == null) {
                    context = obtainContext();
                }
            }
        }
        return context;
    }

    /* renamed from: com.huawei.hmf.services.internal.ApplicationContext$1, reason: invalid class name */
    static final class AnonymousClass1 implements Runnable {
        final /* synthetic */ CountDownLatch val$mutex;

        AnonymousClass1(CountDownLatch countDownLatch) {
            this.val$mutex = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            Context unused = ApplicationContext.context = ApplicationContext.obtainContext();
            this.val$mutex.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Context obtainContext() {
        try {
            try {
                return ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0])).getApplicationContext();
            } catch (Exception unused) {
                return ((Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, null)).getApplicationContext();
            }
        } catch (Exception unused2) {
            return null;
        }
    }
}
