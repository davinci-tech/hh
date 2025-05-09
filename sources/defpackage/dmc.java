package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.views.TextLinkLayout;

/* loaded from: classes3.dex */
public class dmc implements ILayoutGenerator {
    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            return null;
        }
        TextLinkLayout textLinkLayout = new TextLinkLayout(context);
        textLinkLayout.e(i, resourceBriefInfo);
        return textLinkLayout;
    }
}
