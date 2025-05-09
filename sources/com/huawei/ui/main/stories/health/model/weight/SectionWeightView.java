package com.huawei.ui.main.stories.health.model.weight;

import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.knit.section.view.GoodThingsBaseSection;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.views.ResourceBriefInfoOwnable;
import health.compact.a.util.LogUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class SectionWeightView extends GoodThingsBaseSection implements ResourceBriefInfoOwnable {
    private ResourceBriefInfo b;
    private Observer d;

    @Override // com.huawei.health.knit.section.view.GoodThingsBaseSection, com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        super.bindParamsToView(hashMap);
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        boolean z = (resourceBriefInfo == null || this.b == null || !resourceBriefInfo.getResourceId().equals(this.b.getResourceId())) ? false : true;
        LogUtil.d("SectionWeightView", "isOwnedByBriefInfo: " + z);
        return z;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.b;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d("SectionWeightView", "onDetachedFromWindow, unregisterObserver");
        ObserverManagerUtil.e(this.d, "MARKETING_GOOD_THING_CLOSE" + this.b.getResourceId());
    }
}
