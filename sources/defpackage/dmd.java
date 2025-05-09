package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.views.SpecialShapedPictureLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dmd implements ILayoutGenerator {
    dmd() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            LogUtil.c("SpecialShapePicLayoutGenerator", "context is null when getGridTemplate.");
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("SpecialShapePicLayoutGenerator", "getGridTemplate filterTemplate is null");
            return null;
        }
        SpecialShapedPictureLayout specialShapedPictureLayout = new SpecialShapedPictureLayout(context);
        specialShapedPictureLayout.e(i, resourceBriefInfo, c);
        return specialShapedPictureLayout;
    }
}
