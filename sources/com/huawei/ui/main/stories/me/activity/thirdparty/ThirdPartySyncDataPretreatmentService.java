package com.huawei.ui.main.stories.me.activity.thirdparty;

import android.content.Context;
import android.net.Uri;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.login.ui.login.LoginInit;
import com.tencent.open.SocialOperation;
import health.compact.a.AuthorizationUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes.dex */
public class ThirdPartySyncDataPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        ReleaseLogUtil.b("R_ThirdPartySyncDataPretreatmentService", "enter ThirdPartySyncData pretreatment");
        if (!AuthorizationUtils.a(context)) {
            ReleaseLogUtil.a("R_ThirdPartySyncDataPretreatmentService", "disagree to the privacy agreement, data sync is not allowed");
            return false;
        }
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            ReleaseLogUtil.a("R_ThirdPartySyncDataPretreatmentService", "basic service mode, data sync is not allowed");
            return false;
        }
        if (LoginInit.getInstance(context).isBrowseMode()) {
            ReleaseLogUtil.a("R_ThirdPartySyncDataPretreatmentService", "not login, don't intercept");
            return true;
        }
        return ((HmsAuthApi) Services.c("HealthKit", HmsAuthApi.class)).isValidSignature(d(guidepost, "appId"), d(guidepost, SocialOperation.GAME_SIGNATURE), d(guidepost, CommonPickerConstant.RequestParams.KEY_REDIRECT_URL));
    }

    private String d(Guidepost guidepost, String str) {
        Uri zN_ = guidepost.zN_();
        return zN_ != null ? zN_.getQueryParameter(str) : "";
    }
}
