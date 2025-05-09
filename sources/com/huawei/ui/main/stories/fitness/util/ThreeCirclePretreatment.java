package com.huawei.ui.main.stories.fitness.util;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.threeCircle.remind.ThreeCircleNotificationReceiver;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class ThreeCirclePretreatment implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        LogUtil.a("SCUI_ThreeCirclePretreatment", "onPretreatment:", guidepost.g(), " ", guidepost.m());
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            ReleaseLogUtil.c("SCUI_ThreeCirclePretreatment", "uri is null.");
            return false;
        }
        String queryParameter = zN_.getQueryParameter("name");
        String queryParameter2 = zN_.getQueryParameter("remindType");
        String queryParameter3 = zN_.getQueryParameter("subRemindType");
        ReleaseLogUtil.e("SCUI_ThreeCirclePretreatment", "onPretreatment, title: ", queryParameter, "remindType: ", queryParameter2, "subRemindType: ", queryParameter3);
        ThreeCircleNotificationReceiver.e(queryParameter, queryParameter2, queryParameter3);
        return false;
    }
}
