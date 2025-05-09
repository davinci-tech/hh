package com.huawei.health.suggestion.service;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.ftb;
import defpackage.gnm;
import health.compact.a.AuthorizationUtils;
import health.compact.a.Services;

/* loaded from: classes.dex */
public class FitnessPretreatmentServiceImpl implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(final Context context, final Guidepost guidepost) {
        LogUtil.a("Suggestion_FitnessPretreatmentServiceImpl", "onPretreatment:", guidepost.g(), " ", guidepost.m());
        final Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.b("Suggestion_FitnessPretreatmentServiceImpl", "uri is null.");
            return false;
        }
        LoginInit loginInit = LoginInit.getInstance(context);
        if ((loginInit.getAccountInfo(1011) == null && loginInit.isBrowseMode()) || !AuthorizationUtils.a(context)) {
            LogUtil.a("Suggestion_FitnessPretreatmentServiceImpl", "StartHealth to MainActivity");
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), "com.huawei.health.MainActivity");
            intent.putExtra("schemeDataType", 2);
            intent.putExtra(" schemeParamUri", zN_);
            intent.putExtra("needLogin", true);
            gnm.aPB_(context, intent);
            return false;
        }
        LogUtil.a("Suggestion_FitnessPretreatmentServiceImpl", "uri:", zN_.getQuery());
        if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) == null) {
            LogUtil.h("Suggestion_FitnessPretreatmentServiceImpl", "plugin suggestion not install.");
            Services.a("PluginFitnessAdvice", PluginSuggestion.class, context, new Consumer<PluginSuggestion>() { // from class: com.huawei.health.suggestion.service.FitnessPretreatmentServiceImpl.5
                @Override // com.huawei.framework.servicemgr.Consumer
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void accept(PluginSuggestion pluginSuggestion) {
                    LogUtil.a("Suggestion_FitnessPretreatmentServiceImpl", "PluginSuggestion installed.");
                    new ftb(context, zN_, guidepost.zB_()).b();
                }
            }, true);
        } else {
            new ftb(context, zN_, guidepost.zB_()).b();
        }
        return false;
    }

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
        LogUtil.a("Suggestion_FitnessPretreatmentServiceImpl", "init.");
    }
}
