package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.CrossedCardOneCardContent;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.CrossedCardOneCardTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dns implements ITemplateGenerator<CrossedCardOneCardTemplate> {
    dns() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CrossedCardOneCardTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            CrossedCardOneCardTemplate crossedCardOneCardTemplate = (CrossedCardOneCardTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), CrossedCardOneCardTemplate.class);
            if (crossedCardOneCardTemplate == null) {
                LogUtil.h("SeriesDietAnalysisSidingTemplateGenerator", "CrossedCardOneCardTemplate is null.");
                return null;
            }
            List<CrossedCardOneCardContent> gridContents = crossedCardOneCardTemplate.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.h("SeriesDietAnalysisSidingTemplateGenerator", "CrossedCardOneCardContent is null.");
                return crossedCardOneCardTemplate;
            }
            ArrayList arrayList = new ArrayList();
            for (CrossedCardOneCardContent crossedCardOneCardContent : gridContents) {
                if (eil.d(crossedCardOneCardContent.getItemEffectiveTime(), crossedCardOneCardContent.getItemExpirationTime())) {
                    arrayList.add(crossedCardOneCardContent);
                }
            }
            if (koq.b(arrayList)) {
                return null;
            }
            Collections.sort(arrayList, new Comparator<CrossedCardOneCardContent>() { // from class: dns.2
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(CrossedCardOneCardContent crossedCardOneCardContent2, CrossedCardOneCardContent crossedCardOneCardContent3) {
                    return crossedCardOneCardContent3.getPriority() - crossedCardOneCardContent2.getPriority();
                }
            });
            crossedCardOneCardTemplate.setGridContents(arrayList);
            return crossedCardOneCardTemplate;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("SeriesDietAnalysisSidingTemplateGenerator", "CrossedCardOneCardTemplate JsonSyntaxException");
            return null;
        }
    }
}
