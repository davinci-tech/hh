package com.huawei.health.routeradapter;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.R;
import defpackage.gpo;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class ColumnPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ != null && avl_(context, zN_)) {
            return false;
        }
        avn_(context, zN_, guidepost);
        return false;
    }

    private boolean avk_(Context context, Uri uri) {
        if (context == null) {
            return false;
        }
        try {
            AppRouter.b("/OperationBundle/FunctionMenuActivity").c("contentType", Integer.parseInt(uri.getQueryParameter("contentType"))).c(context);
            return true;
        } catch (NumberFormatException unused) {
            LogUtil.b("Opera_ColumnPretreatmentService", "goToDetail() Exception.");
            return false;
        }
    }

    private boolean e(Context context) {
        if (context == null) {
            return false;
        }
        AppRouter.b("/OperationBundle/SocialAssessmentActivity").c(context);
        return true;
    }

    private boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (gpo.b()) {
            AppRouter.b("/OperationBundle/MemberRelayActivity").c(context);
            return true;
        }
        AppRouter.b("/home/discover").c(BaseApplication.e());
        return true;
    }

    private boolean avm_(Context context, Uri uri) {
        if (context == null) {
            return false;
        }
        AppRouter.b("/OperationBundle/MemberTypeSelectActivity").b(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031).e("memberTypeSelectUri", uri.toString()).c(context);
        return true;
    }

    private void avn_(Context context, Uri uri, Guidepost guidepost) {
        AppRouter.b("/home/main").c(context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean avl_(Context context, Uri uri) {
        char c;
        String queryParameter = uri.getQueryParameter("module");
        if (queryParameter == null) {
            return false;
        }
        queryParameter.hashCode();
        switch (queryParameter.hashCode()) {
            case -1597508329:
                if (queryParameter.equals("vipMember")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -211398313:
                if (queryParameter.equals("functionMenu")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 858523452:
                if (queryParameter.equals("evaluation")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1568587987:
                if (queryParameter.equals("vipTypeSelect")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return a(context);
        }
        if (c == 1) {
            return avk_(context, uri);
        }
        if (c == 2) {
            return e(context);
        }
        if (c != 3) {
            return false;
        }
        return avm_(context, uri);
    }
}
