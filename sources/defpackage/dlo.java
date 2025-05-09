package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.MultiGridsSleepPageTemplate;
import com.huawei.health.marketing.views.CarouselsLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dlo implements ILayoutGenerator {
    dlo() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        LogUtil.a("", "getCarouselsPager");
        if (context == null) {
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        LogUtil.a("", "resourceBriefInfo: " + resourceBriefInfo.toString());
        if ((c instanceof MultiGridsSleepPageTemplate) && koq.b(((MultiGridsSleepPageTemplate) c).getGridContents())) {
            LogUtil.c("", "singleGridContentList is empty.");
            return null;
        }
        CarouselsLayout carouselsLayout = new CarouselsLayout(context);
        carouselsLayout.d(i, resourceBriefInfo, c);
        return carouselsLayout;
    }
}
