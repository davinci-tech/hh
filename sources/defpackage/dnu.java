package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleTextContent;
import com.huawei.health.marketing.datatype.TextLinkTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class dnu implements ITemplateGenerator<TextLinkTemplate> {
    dnu() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TextLinkTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            TextLinkTemplate textLinkTemplate = (TextLinkTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), TextLinkTemplate.class);
            if (textLinkTemplate == null) {
                LogUtil.h("GridTemplateGenerator", "gridTemplate is null.");
                return null;
            }
            TextLinkTemplate e = e(textLinkTemplate);
            if (!koq.b(e.getGridContents())) {
                return e;
            }
            LogUtil.a("GridTemplateGenerator", "multi tab horizontal grid content is empty");
            return null;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("GridTemplateGenerator", "MultiGridsSleepPageTemplate JsonSyntaxException");
            return null;
        }
    }

    private TextLinkTemplate e(TextLinkTemplate textLinkTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleTextContent> gridContents = textLinkTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleTextContent singleTextContent : gridContents) {
                if (eil.d(singleTextContent.getItemEffectiveTime(), singleTextContent.getItemExpirationTime())) {
                    arrayList.add(singleTextContent);
                }
            }
            textLinkTemplate.setGridContents(arrayList);
        }
        return textLinkTemplate;
    }
}
