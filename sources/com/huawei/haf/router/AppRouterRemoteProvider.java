package com.huawei.haf.router;

import android.os.Bundle;
import com.huawei.haf.common.base.BaseContentProvider;
import com.huawei.haf.handler.HandlerExecutor;

/* loaded from: classes.dex */
public abstract class AppRouterRemoteProvider extends BaseContentProvider {
    @Override // com.huawei.haf.common.base.BaseContentProvider, android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        if (bundle == null || !"navigate".equals(str)) {
            return null;
        }
        navigate(bundle);
        return null;
    }

    protected void navigate(Bundle bundle) {
        HandlerExecutor.a(new InnerRouterRunnable(bundle));
    }

    static class InnerRouterRunnable implements Runnable {
        private final Bundle e;

        InnerRouterRunnable(Bundle bundle) {
            this.e = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppRouterResultReceiver.zl_(null, this.e);
        }
    }
}
