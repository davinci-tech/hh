package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.views.SportBannerLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dmf implements ILayoutGenerator {
    dmf() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("", "getSportBanner filterTemplate is null");
            return null;
        }
        if ((c instanceof GridTemplate) && koq.b(((GridTemplate) c).getGridContents())) {
            LogUtil.c("", "singleGridContentList is empty.");
            return null;
        }
        SportBannerLayout sportBannerLayout = new SportBannerLayout(context);
        sportBannerLayout.d(i, resourceBriefInfo, c);
        return sportBannerLayout;
    }
}
