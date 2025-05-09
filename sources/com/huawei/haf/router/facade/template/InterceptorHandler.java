package com.huawei.haf.router.facade.template;

import com.huawei.haf.router.Guidepost;

/* loaded from: classes.dex */
public interface InterceptorHandler extends ServiceProvider {

    public interface InterceptorCallback {
        void onContinue(Guidepost guidepost);

        void onInterrupt(Throwable th);
    }

    void process(Guidepost guidepost, InterceptorCallback interceptorCallback);
}
