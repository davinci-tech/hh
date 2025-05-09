package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.openalliance.ad.analysis.BaseAnalysisInfo;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class bo extends com.huawei.openalliance.ad.analysis.c {
    public void a(String str, Bundle bundle, ContentRecord contentRecord, qq qqVar) {
        if (TextUtils.isEmpty(str) || bundle == null || contentRecord == null || qqVar == null) {
            ho.b("EngineAnalysisUtil", "param or ad or analysisType is null");
            return;
        }
        gk gkVar = new gk(bundle);
        String d = gkVar.d("analysis_info");
        boolean a2 = gkVar.a("is_report_now", false);
        boolean a3 = gkVar.a("is_check_discard", false);
        BaseAnalysisInfo baseAnalysisInfo = (BaseAnalysisInfo) com.huawei.openalliance.ad.utils.be.b(d, BaseAnalysisInfo.class, new Class[0]);
        if (baseAnalysisInfo == null) {
            ho.b("EngineAnalysisUtil", "engineInfo is null");
            return;
        }
        baseAnalysisInfo.aj(str);
        com.huawei.openalliance.ad.analysis.b a4 = a(contentRecord, baseAnalysisInfo);
        if (a4 == null) {
            ho.c("EngineAnalysisUtil", "onAnalysis, info is null.");
            return;
        }
        if (ho.a()) {
            ho.a("EngineAnalysisUtil", "onAnalysis, info: %s", com.huawei.openalliance.ad.utils.be.b(a4));
        }
        ho.b("EngineAnalysisUtil", "start report analysis, analysisType: %s", str);
        qqVar.b(a4, a2, a3);
    }

    public void a(Bundle bundle, ContentRecord contentRecord, qq qqVar) {
        if (bundle == null || contentRecord == null) {
            ho.b("EngineAnalysisUtil", "param or ad is null");
        } else {
            a(new gk(bundle).d("analysisType"), bundle, contentRecord, qqVar);
        }
    }

    public com.huawei.openalliance.ad.analysis.b a(ContentRecord contentRecord, BaseAnalysisInfo baseAnalysisInfo) {
        if (baseAnalysisInfo == null || contentRecord == null) {
            return null;
        }
        com.huawei.openalliance.ad.analysis.b e = e(contentRecord);
        a(e, baseAnalysisInfo);
        return e;
    }

    public com.huawei.openalliance.ad.analysis.b a(BaseAnalysisInfo baseAnalysisInfo) {
        if (baseAnalysisInfo == null) {
            return null;
        }
        com.huawei.openalliance.ad.analysis.b a2 = a(true);
        a(a2, baseAnalysisInfo);
        return a2;
    }

    public bo(Context context) {
        super(context);
    }
}
