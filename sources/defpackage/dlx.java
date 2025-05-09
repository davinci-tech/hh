package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.views.MultiFunctionLayout;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class dlx implements ILayoutGenerator {
    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            LogUtil.a("MultiFunctionLayoutGenerator", "context is null when getGridTemplate.");
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.a("MultiFunctionLayoutGenerator", "getGridTemplate filterTemplate is null");
            return null;
        }
        if ((c instanceof GridTemplate) && koq.b(((GridTemplate) c).getGridContents())) {
            LogUtil.a("MultiFunctionLayoutGenerator", "singleGridContentList is empty.");
            return null;
        }
        MultiFunctionLayout multiFunctionLayout = new MultiFunctionLayout(BaseApplication.e());
        multiFunctionLayout.e(i, resourceBriefInfo, c);
        return multiFunctionLayout;
    }
}
