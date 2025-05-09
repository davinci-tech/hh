package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.views.ThreeGridSeriesSidingLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dme implements ILayoutGenerator {
    dme() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("", "getSportBanner filterTemplate is null");
            return null;
        }
        ThreeGridSeriesSidingLayout threeGridSeriesSidingLayout = new ThreeGridSeriesSidingLayout(context);
        threeGridSeriesSidingLayout.initData(i, resourceBriefInfo, c);
        return threeGridSeriesSidingLayout;
    }
}
