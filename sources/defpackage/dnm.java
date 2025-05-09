package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleHotSeriesRecommendContent;
import com.huawei.health.marketing.datatype.templates.HotSeriesRecommendTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnm implements ITemplateGenerator<HotSeriesRecommendTemplate> {
    dnm() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public HotSeriesRecommendTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            HotSeriesRecommendTemplate hotSeriesRecommendTemplate = (HotSeriesRecommendTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), HotSeriesRecommendTemplate.class);
            if (hotSeriesRecommendTemplate == null) {
                LogUtil.h("HotSeriesRecommendTemplateGenerator", "HotSeriesRecommendTemplate is null.");
                return null;
            }
            HotSeriesRecommendTemplate e = e(hotSeriesRecommendTemplate);
            Collections.sort(e.getGridContents(), new Comparator() { // from class: dno
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return dnm.b((SingleHotSeriesRecommendContent) obj, (SingleHotSeriesRecommendContent) obj2);
                }
            });
            return e;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("HotSeriesRecommendTemplateGenerator", "HotSeriesRecommendTemplate JsonSyntaxException");
            return null;
        }
    }

    static /* synthetic */ int b(SingleHotSeriesRecommendContent singleHotSeriesRecommendContent, SingleHotSeriesRecommendContent singleHotSeriesRecommendContent2) {
        return singleHotSeriesRecommendContent2.getPriority() - singleHotSeriesRecommendContent.getPriority();
    }

    private HotSeriesRecommendTemplate e(HotSeriesRecommendTemplate hotSeriesRecommendTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleHotSeriesRecommendContent> gridContents = hotSeriesRecommendTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleHotSeriesRecommendContent singleHotSeriesRecommendContent : gridContents) {
                if (eil.d(singleHotSeriesRecommendContent.getItemEffectiveTime(), singleHotSeriesRecommendContent.getItemExpirationTime())) {
                    arrayList.add(singleHotSeriesRecommendContent);
                }
            }
            hotSeriesRecommendTemplate.setGridContents(arrayList);
        }
        return hotSeriesRecommendTemplate;
    }
}
