package com.huawei.haf.router.core;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.template.InterceptorHandler;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class AppRouterInterceptorService implements Runnable {
    AppRouterInterceptorService() {
        HandlerCenter.d().d(this, "interceptor");
    }

    void a(Guidepost guidepost, InterceptorHandler.InterceptorCallback interceptorCallback) {
        if (AppRouterStore.d.isEmpty()) {
            interceptorCallback.onContinue(guidepost);
        } else {
            HandlerCenter.d().d(new InterceptorExecutor(AppRouterStore.c.size(), guidepost, interceptorCallback), "interceptor");
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator<Map.Entry<Integer, Class<? extends InterceptorHandler>>> it = AppRouterStore.d.entrySet().iterator();
        while (it.hasNext()) {
            Class<? extends InterceptorHandler> value = it.next().getValue();
            try {
                InterceptorHandler newInstance = value.newInstance();
                newInstance.init(BaseApplication.e());
                AppRouterStore.c.add(newInstance);
            } catch (Exception e) {
                throw new AppRouterHandleException("init interceptor error! name = [" + value.getName() + "], reason = [" + e.getMessage() + "]");
            }
        }
    }

    static class InterceptorExecutor extends CountDownLatch implements Runnable {
        private final InterceptorHandler.InterceptorCallback c;
        private final Guidepost e;

        InterceptorExecutor(int i, Guidepost guidepost, InterceptorHandler.InterceptorCallback interceptorCallback) {
            super(i);
            this.e = guidepost;
            this.c = interceptorCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b(0);
                await(250L, TimeUnit.SECONDS);
                if (getCount() > 0) {
                    this.c.onInterrupt(new AppRouterHandleException("The interceptor processing timed out."));
                } else if (this.e.k() instanceof Throwable) {
                    this.c.onInterrupt((Throwable) this.e.k());
                } else {
                    this.c.onContinue(this.e);
                }
            } catch (Exception e) {
                this.c.onInterrupt(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(final int i) {
            if (i >= AppRouterStore.c.size()) {
                return;
            }
            AppRouterStore.c.get(i).process(this.e, new InterceptorHandler.InterceptorCallback() { // from class: com.huawei.haf.router.core.AppRouterInterceptorService.InterceptorExecutor.1
                @Override // com.huawei.haf.router.facade.template.InterceptorHandler.InterceptorCallback
                public void onContinue(Guidepost guidepost) {
                    InterceptorExecutor.this.countDown();
                    InterceptorExecutor.this.b(i + 1);
                }

                @Override // com.huawei.haf.router.facade.template.InterceptorHandler.InterceptorCallback
                public void onInterrupt(Throwable th) {
                    Guidepost guidepost = InterceptorExecutor.this.e;
                    if (th == null) {
                        th = new AppRouterHandleException("No message.");
                    }
                    guidepost.e(th);
                    InterceptorExecutor.this.d();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            while (getCount() > 0) {
                countDown();
            }
        }
    }
}
