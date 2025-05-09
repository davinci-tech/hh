package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.MultiTabHorizontalTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.views.MultiTabHorizontalLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dlz implements ILayoutGenerator {
    dlz() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        LogUtil.a("", "getMultiTabHorizontalLayout");
        if (context == null) {
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.a("", "getMultiTabHorizontalLayout filterTemplate is null");
            return null;
        }
        if ((c instanceof MultiTabHorizontalTemplate) && koq.b(((MultiTabHorizontalTemplate) c).getGridContents())) {
            LogUtil.c("", "gridContents is empty.");
            return null;
        }
        MultiTabHorizontalLayout multiTabHorizontalLayout = new MultiTabHorizontalLayout(context);
        multiTabHorizontalLayout.e(i, resourceBriefInfo, c);
        return multiTabHorizontalLayout;
    }
}
