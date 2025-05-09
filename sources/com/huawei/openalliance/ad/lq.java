package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.List;

/* loaded from: classes5.dex */
public class lq {
    public static mi a(Context context, ContentRecord contentRecord, lm lmVar, boolean z) {
        mg mgVar;
        mj mjVar;
        mk mkVar;
        mk mkVar2;
        if (contentRecord == null || context == null) {
            return new lt();
        }
        if (z && (lmVar == null || lmVar.getOpenMeasureView() == null)) {
            ho.b("AdSessionAgentFactory", "MeasureView is null");
            return new lt();
        }
        if (!lp.a()) {
            return new lt();
        }
        ho.a("AdSessionAgentFactory", "AdSessionAgent is avalible");
        lp lpVar = new lp();
        List<Om> b = contentRecord.b(context);
        if (b == null) {
            ho.b("AdSessionAgentFactory", "Oms is null");
            return lpVar;
        }
        if (contentRecord.R() != null || (contentRecord.S() != null && "video/mp4".equals(contentRecord.S().a()))) {
            ho.b("AdSessionAgentFactory", "Video adsession");
            mgVar = mg.VIDEO;
            mjVar = mj.VIEWABLE;
            mkVar = mk.NATIVE;
            mkVar2 = mk.NATIVE;
        } else {
            mgVar = mg.NATIVE_DISPLAY;
            mjVar = mj.VIEWABLE;
            mkVar = mk.NATIVE;
            mkVar2 = mk.NONE;
        }
        me a2 = me.a(mgVar, mjVar, mkVar, mkVar2, false);
        if (a2 == null) {
            return lpVar;
        }
        if (lpVar instanceof lp) {
            ho.b("AdSessionAgentFactory", "init adSessionAgent");
            lpVar.a(context, b, a2);
        }
        if (z) {
            lpVar.a(lmVar.getOpenMeasureView());
        }
        return lpVar;
    }
}
