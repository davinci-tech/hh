package defpackage;

import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.TextLinkTemplate;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.views.TextLinkNestingLayout;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
class dmb implements ILayoutGenerator {

    /* renamed from: a, reason: collision with root package name */
    private FragmentManager f11711a;

    public dmb(FragmentManager fragmentManager) {
        this.f11711a = fragmentManager;
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null) {
            return null;
        }
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (!(c instanceof TextLinkTemplate)) {
            LogUtil.h("", "getTextLinkNestingLayout baseTemplate is null");
            return null;
        }
        TextLinkTemplate textLinkTemplate = (TextLinkTemplate) c;
        if (koq.b(textLinkTemplate.getGridContents())) {
            LogUtil.c("", "singleTextContentList is empty.");
            return null;
        }
        TextLinkNestingLayout textLinkNestingLayout = new TextLinkNestingLayout(context);
        textLinkNestingLayout.a(resourceBriefInfo, textLinkTemplate, this.f11711a);
        return textLinkNestingLayout;
    }
}
