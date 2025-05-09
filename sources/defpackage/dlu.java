package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.views.DietAnalysisSeriesSidingLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dlu implements ILayoutGenerator {
    dlu() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("", "getSportBanner filterTemplate is null");
            return null;
        }
        DietAnalysisSeriesSidingLayout dietAnalysisSeriesSidingLayout = new DietAnalysisSeriesSidingLayout(context);
        dietAnalysisSeriesSidingLayout.initData(i, resourceBriefInfo, c);
        return dietAnalysisSeriesSidingLayout;
    }
}
