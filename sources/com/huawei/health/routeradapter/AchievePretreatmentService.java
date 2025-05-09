package com.huawei.health.routeradapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mfg;
import defpackage.sbk;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public class AchievePretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            LogUtil.h("AchievePretreatmentService", "onPretreatment uri is null!");
            return false;
        }
        avi_(context, zN_);
        return false;
    }

    private void avi_(Context context, Uri uri) {
        String queryParameter = uri.getQueryParameter("module");
        String queryParameter2 = uri.getQueryParameter("id");
        String queryParameter3 = uri.getQueryParameter("from");
        LogUtil.a("AchievePretreatmentService", "goto achievement module= ", queryParameter);
        mcv d = mcv.d(context);
        if ("medal".equals(queryParameter)) {
            d(d, queryParameter2, queryParameter3, context);
            return;
        }
        if ("level".equals(queryParameter)) {
            if (Utils.o()) {
                return;
            }
            d.g(context);
            return;
        }
        if ("report".equals(queryParameter)) {
            d.i(context);
            return;
        }
        if ("kit".equals(queryParameter)) {
            a(context);
            return;
        }
        if ("vmall".equals(queryParameter)) {
            d.h(context);
            return;
        }
        if (ParsedFieldTag.KAKA_GET_GIFTS.equals(queryParameter)) {
            d.n(context);
            return;
        }
        if (ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE.equals(queryParameter)) {
            sbk a2 = sbk.a(context);
            a2.dUY_(new Handler());
            a2.e(context);
        } else if ("weekreport".equals(queryParameter)) {
            d.b(context, mfg.b(mct.b(context, "_weekMinReportNo")), mfg.b(mct.b(context, "_weekReportNo")), 1);
        } else {
            if ("monthreport".equals(queryParameter)) {
                d.b(context, mfg.b(mct.b(context, "_monthMinReportNo")), mfg.b(mct.b(context, "_monthReportNo")), 0);
                return;
            }
            LogUtil.h("AchievePretreatmentService", "module invalid");
        }
    }

    private void d(mcv mcvVar, String str, String str2, Context context) {
        LogUtil.a("AchievePretreatmentService", "showMeadlActivity, medalId:", str, " from: ", str2);
        if (mcvVar == null) {
            LogUtil.h("AchievePretreatmentService", "showMeadlActivity: pluginAchieve is null");
        } else {
            if (TextUtils.isEmpty(str)) {
                mcvVar.j(context);
                return;
            }
            if (mcv.d(context).getAdapter() == null) {
                mcv.d(context).setAdapter(new PluginAchieveAdapterImpl());
            }
            mcvVar.c(context, str, str2);
        }
    }

    private void a(Context context) {
        LogUtil.a("AchievePretreatmentService", "showHealthKit enter");
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity");
        context.startActivity(intent);
    }
}
