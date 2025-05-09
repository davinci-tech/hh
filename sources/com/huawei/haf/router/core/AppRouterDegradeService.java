package com.huawei.haf.router.core;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.facade.service.DegradeService;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
final class AppRouterDegradeService implements NaviCallback {
    private final DegradeService c = (DegradeService) AppRouterHelper.e(DegradeService.class);

    AppRouterDegradeService() {
    }

    void d(Context context, Guidepost guidepost) {
        Uri zT_;
        DegradeService degradeService = this.c;
        if ((degradeService == null || !degradeService.onLost(context, guidepost)) && (zT_ = AppRouterHelper.a().zT_()) != null) {
            LogUtil.c("HAF_Degrade", "route degrade uri:", zT_);
            AppRouter.zi_(zT_).b(context, this);
        }
    }

    @Override // com.huawei.haf.router.NaviCallback
    public void onArrival(Guidepost guidepost) {
        LogUtil.c("HAF_Degrade", "arrival ", "route degrade uri:", guidepost.zN_());
    }
}
