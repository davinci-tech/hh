package com.huawei.health.routeradapter;

import android.content.Context;
import com.huawei.haf.common.exception.HafException;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.template.InterceptorHandler;
import defpackage.fbi;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public final class HealthLoginInterceptorService implements InterceptorHandler {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.template.InterceptorHandler
    public void process(Guidepost guidepost, InterceptorHandler.InterceptorCallback interceptorCallback) {
        if (!fbi.c(guidepost)) {
            a(guidepost, interceptorCallback, false);
            return;
        }
        Context a2 = guidepost.a();
        if (fbi.a(a2) && fbi.d(a2)) {
            if (Utils.o() && fbi.b(guidepost)) {
                fbi.c(a2);
                interceptorCallback.onInterrupt(new HafException("oversea user not used"));
                return;
            } else {
                a(guidepost, interceptorCallback, true);
                return;
            }
        }
        fbi.b(guidepost, null, true);
        interceptorCallback.onInterrupt(new HafException("no login"));
    }

    private void a(Guidepost guidepost, InterceptorHandler.InterceptorCallback interceptorCallback, boolean z) {
        if (fbi.d(guidepost)) {
            fbi.b(guidepost, null, z);
            interceptorCallback.onInterrupt(new HafException("need obtainAdapterInit"));
        } else {
            interceptorCallback.onContinue(guidepost);
        }
    }
}
