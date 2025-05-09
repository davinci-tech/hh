package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.MultiTabHorizontalTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGrid4Tab;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class dnq implements ITemplateGenerator<MultiTabHorizontalTemplate> {
    dnq() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public MultiTabHorizontalTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            MultiTabHorizontalTemplate multiTabHorizontalTemplate = (MultiTabHorizontalTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), MultiTabHorizontalTemplate.class);
            if (multiTabHorizontalTemplate == null) {
                LogUtil.h("MultiTabHorizontalTemplateGenerator", "gridTemplate is null.");
                return null;
            }
            MultiTabHorizontalTemplate c = c(multiTabHorizontalTemplate);
            if (!koq.b(c.getGridContents())) {
                return c;
            }
            LogUtil.a("MultiTabHorizontalTemplateGenerator", "multi tab horizontal grid content is empty");
            return null;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("MultiTabHorizontalTemplateGenerator", "MultiGridsSleepPageTemplate JsonSyntaxException");
            return null;
        }
    }

    private MultiTabHorizontalTemplate c(MultiTabHorizontalTemplate multiTabHorizontalTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleGrid4Tab> gridContents = multiTabHorizontalTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleGrid4Tab singleGrid4Tab : gridContents) {
                if (eil.d(singleGrid4Tab.getItemEffectiveTime(), singleGrid4Tab.getItemExpirationTime())) {
                    arrayList.add(singleGrid4Tab);
                }
            }
            multiTabHorizontalTemplate.setGridContents(arrayList);
        }
        return multiTabHorizontalTemplate;
    }
}
