package com.huawei.pluginachievement.manager.service;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.ixx;
import defpackage.meh;
import java.util.HashMap;

/* loaded from: classes.dex */
public class AchieveKaKaPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        meh c = meh.c(context);
        if (c.t() == null || c.t().getHuid() == null) {
            LogUtil.h("AchieveKaKaPretreatmentService", "onPretreatment userProfile or uid is null!");
            c.ad();
            if (c.t() == null || c.t().getHuid() == null) {
                LogUtil.h("AchieveKaKaPretreatmentService", "onPretreatment getUserProfile or uid is null!");
                return false;
            }
        }
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h("AchieveKaKaPretreatmentService", "onPretreatment uri is null!");
            return false;
        }
        cgm_(zN_);
        AppRouter.b("/PluginAchievement/AchieveKaKaActivity").c(context);
        return false;
    }

    private void cgm_(Uri uri) {
        if (uri == null) {
            LogUtil.h("AchieveKaKaPretreatmentService", "handleBiAnalytics getData is null");
            return;
        }
        String queryParameter = uri.getQueryParameter("from");
        HashMap hashMap = new HashMap(2);
        if (!TextUtils.isEmpty(queryParameter)) {
            hashMap.put("from", queryParameter);
        }
        Context context = BaseApplication.getContext();
        OpAnalyticsUtil.getInstance().init(context);
        ixx.d().a(LoginInit.getInstance(context).getAccountInfo(1011));
        ixx.d().e(LoginInit.getInstance(context).getAccountInfo(1010));
        ixx.d().d(context, AnalyticsValue.SUCCESSES_KAKA_1100007.value(), hashMap, 0);
    }
}
