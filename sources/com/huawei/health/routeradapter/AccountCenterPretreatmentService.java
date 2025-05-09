package com.huawei.health.routeradapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.HealthApplication;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class AccountCenterPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        if (CommonUtil.z(context)) {
            ThirdPartyLoginManager.getInstance().openAccountManager(HealthApplication.wa_());
            return false;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwid://com.huawei.hwid/AccountCenter"));
        try {
            intent.setPackage(HMSPackageManager.getInstance(BaseApplication.getContext()).getHMSPackageName());
            if (HuaweiLoginManager.checkIsInstallHuaweiAccount(BaseApplication.getContext())) {
                HealthApplication.wa_().startActivity(intent);
            } else {
                ReleaseLogUtil.e("AccountCenterPretreatmentService", "jumpToHwIdAccountCenter not install hmscore");
            }
        } catch (ActivityNotFoundException | SecurityException e) {
            ReleaseLogUtil.e("AccountCenterPretreatmentService", "jumpToHwIdAccountCenter ", e.getClass().getName());
        }
        return false;
    }
}
