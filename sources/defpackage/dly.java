package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.views.MemberBannerCardLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dly implements ILayoutGenerator {
    dly() {
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
        MemberBannerCardLayout memberBannerCardLayout = new MemberBannerCardLayout(context);
        memberBannerCardLayout.d(i, resourceBriefInfo, c);
        return memberBannerCardLayout;
    }
}
