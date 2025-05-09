package com.huawei.health.routeradapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.feedback.ui.ProblemSuggestActivity;
import defpackage.gnm;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class FaPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.d("FaPretreatmentService", "uri == null");
            return false;
        }
        LogUtil.d("FaPretreatmentService", zN_.toString());
        if (TextUtils.isEmpty(zN_.getQuery())) {
            return false;
        }
        b(context, zN_.getQueryParameter("target"));
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void b(Context context, String str) {
        char c;
        switch (str.hashCode()) {
            case -2129213109:
                if (str.equals("startWalk")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1335157162:
                if (str.equals("device")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1060521098:
                if (str.equals("myData")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -795510179:
                if (str.equals("myTarget")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -266803431:
                if (str.equals("userInfo")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 0:
                if (str.equals("")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 435676133:
                if (str.equals("problemSuggest")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            d(context);
            return;
        }
        if (c == 1) {
            j(context);
            return;
        }
        if (c == 2) {
            e(context);
            return;
        }
        if (c == 3) {
            a(context);
        } else if (c == 4) {
            c(context);
        } else {
            if (c != 5) {
                return;
            }
            b(context);
        }
    }

    private void d(Context context) {
        AppRouter.b("/Main/HealthDatasActivity").b(AppRouterExtras.COLDSTART).c(context);
    }

    private void j(Context context) {
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").b(AppRouterExtras.COLDSTART).c(context);
    }

    private void e(Context context) {
        Intent intent = new Intent(context, (Class<?>) ProblemSuggestActivity.class);
        intent.setFlags(AppRouterExtras.COLDSTART);
        gnm.aPB_(context, intent);
    }

    private void a(Context context) {
        AppRouter.b("/home/device").e(Constants.HOME_TAB_NAME, "DEVICE").b(AppRouterExtras.COLDSTART).c(context);
    }

    private void c(Context context) {
        AppRouter.b("/PluginMotionTrack/TrackMainMapActivity").b(AppRouterExtras.COLDSTART).c(context);
    }

    private void b(Context context) {
        AppRouter.b("/HWUserProfileMgr/MyTargetActivity").b(AppRouterExtras.COLDSTART).c(context);
    }
}
