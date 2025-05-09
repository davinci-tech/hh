package com.huawei.ui.main.stories.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import defpackage.bzs;
import defpackage.gnm;
import defpackage.pwo;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;

/* loaded from: classes2.dex */
public class SleepServiceImpl implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        LogUtil.a("SleepServiceImpl", "onPretreatment:", guidepost.g(), " ", guidepost.m());
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.b("SleepServiceImpl", "uri is null.");
            return false;
        }
        LogUtil.a("SleepServiceImpl", "uri:", zN_.getQuery());
        if (dRv_(zN_) == 1) {
            new pwo(context, zN_).b();
        } else {
            dRw_(context, zN_);
        }
        return false;
    }

    public static void dRw_(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class<?>) KnitSleepDetailActivity.class);
        intent.putExtra("NTwe23AtQb8U", uri);
        gnm.aPB_(context, intent);
    }

    public static void a(String str) {
        H5proUtil.initH5pro();
        H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.sleep-management", new H5ProLaunchOption.Builder().setImmerse().showStatusBar().setNeedSoftInputAdapter().setStatusBarTextBlack(true).setForceDarkMode(1).addPath(str).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepManagementApi", ((SleepApi) Services.c("Main", SleepApi.class)).getSleepManagementH5Bridge()).addCustomizeJsModule("tradeApi", JsTradeApi.class).build());
    }

    public static int dRv_(Uri uri) {
        try {
            int h = CommonUtils.h(uri.getQueryParameter(CommonUtil.PAGE_TYPE));
            LogUtil.a("SleepServiceImpl", "type:", Integer.valueOf(h));
            return h;
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            LogUtil.b("SleepServiceImpl", "get type IllegalArgumentException:", CommonUtil.PAGE_TYPE, LogAnonymous.b(e));
            return -1;
        }
    }

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
        LogUtil.a("SleepServiceImpl", "init.");
    }
}
