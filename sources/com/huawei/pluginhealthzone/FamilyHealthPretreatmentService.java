package com.huawei.pluginhealthzone;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nsn;

/* loaded from: classes.dex */
public class FamilyHealthPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h("FamilyHealthPretreatmentService", "onPretreatment uri is null!");
            return false;
        }
        if (cmS_(zN_) == 10) {
            String query = zN_.getQuery();
            if (TextUtils.isEmpty(query)) {
                AppRouter.b("/PluginHealthZone/FamilyHealthTempActivity").c(context);
            } else {
                AppRouter.zi_(Uri.parse("huaweischeme://healthapp/PluginHealthZone/FamilyHealthTempActivity?" + query)).c(context);
            }
        }
        return false;
    }

    private int cmS_(Uri uri) {
        try {
            String queryParameter = uri.getQueryParameter(CommonUtil.PARAM_HEALTH_TYPE);
            if (!nsn.c(queryParameter)) {
                return -1;
            }
            try {
                return Integer.parseInt(queryParameter);
            } catch (NumberFormatException unused) {
                return -1;
            }
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("FamilyHealthPretreatmentService", "getHealthType IllegalArgumentException:");
            return -1;
        }
    }
}
