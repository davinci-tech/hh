package com.huawei.featureuserprofile.feedback;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixj;
import defpackage.ixx;
import defpackage.jcc;
import defpackage.nrh;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;

/* loaded from: classes.dex */
public class FeedbackPretreatmentService implements PretreatmentService {
    private static String b = "FeedbackPretreatmentService";
    private static final String[] d = {"com.tencent.qqpimsecure", "com.qihoo360.mobilesafe", "com.mpoyit.zawcgm", "com.zxly.assist", "com.anguanjia.safe", "com.fractalist.SystemOptimizer", "com.tencent.qlauncher.lite", "com.baoruan.launcher2", "com.hola.launcher", "com.dianxinos.dxhome", "com.nd.android.pandahome2", "com.mili.launcher", "com.tencent.launcher", "com.Dean.launcher", "com.gau.go.launcherex", "com.cleanmaster.mguard_cn", "com.isyezon.kbatterydoctor", "com.mdhlkj.batterysaver", "com.ijinshan.kbatterydoctor"};

    /* renamed from: a, reason: collision with root package name */
    private String f1981a;
    private String c;
    private Context e;
    private boolean f = false;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h(b, "onPretreatment uri is null!");
            return false;
        }
        LogUtil.a(b, "onPretreatment uri:", zN_.toString());
        this.e = BaseApplication.e();
        if (ixj.b().d()) {
            nrh.b(this.e, R.string._2130844514_res_0x7f021b62);
            return false;
        }
        if (!SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "Initialized_Feedback", false)) {
            nrh.b(this.e, R.string._2130841762_res_0x7f0210a2);
            return false;
        }
        String queryParameter = zN_.getQueryParameter("isDevice");
        this.c = queryParameter;
        boolean z = "1".equals(queryParameter) || "3".equals(this.c);
        if (z || "4".equals(this.c)) {
            this.f1981a = zN_.getQueryParameter("device_id");
            this.f = "true".equals(zN_.getQueryParameter("enhancement_mode"));
        } else {
            jcc.d().c();
        }
        if (z) {
            c();
        } else if ("4".equals(this.c)) {
            b();
        } else {
            d();
        }
        return false;
    }

    private void d() {
        LogUtil.a(b, "gotoFeedback()");
        String value = AnalyticsValue.HEALTH_MINE_QA_2040026.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this.e, value, hashMap, 0);
        e(this.e);
        int bCP_ = ixj.b().bCP_(BaseApplication.wa_(), true);
        if (bCP_ == 0) {
            LogUtil.a(b, "gotoFeedback successful");
        } else {
            LogUtil.a(b, "gotoFeedback errorCode : ", Integer.valueOf(bCP_));
        }
    }

    private void c() {
        LogUtil.a(b, "gotoDeviceFeedback()");
        AppRouter.b("/Device/QuestionSuggestionActivity").e("deviceType", this.c).e("device_id", this.f1981a).e("enhancement_mode", this.f).c(this.e);
    }

    private void b() {
        LogUtil.a(b, "gotoScaleDeviceFeedback()");
        AppRouter.b("/Device/WeightQuestionSuggestionActivity").e("deviceType", this.c).e("uniqueId", this.f1981a).e("enhancement_mode", this.f).c(this.e);
    }

    private void e(Context context) {
        LogUtil.a(b, "getThreeAppInfos");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h(b, "PackageManager is null");
            return;
        }
        for (String str : d) {
            try {
                packageManager.getApplicationInfo(str, 0);
                jcc.d().b("third_app", str);
                LogUtil.a(b, "package : ", str);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtil.b(b, "writeThirdAppInfos fail ", e.getMessage());
            }
        }
    }
}
