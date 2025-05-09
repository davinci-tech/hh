package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleHorizontalGridStandardContent;
import com.huawei.health.marketing.datatype.templates.HorizontalThreeGridTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnl implements ITemplateGenerator<HorizontalThreeGridTemplate> {
    dnl() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public HorizontalThreeGridTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            HorizontalThreeGridTemplate horizontalThreeGridTemplate = (HorizontalThreeGridTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), HorizontalThreeGridTemplate.class);
            if (horizontalThreeGridTemplate == null) {
                LogUtil.h("HorizontalThreeGridTemplateGenerator", "CrossedCardOneCardTemplate is null.");
                return null;
            }
            List<SingleHorizontalGridStandardContent> gridContents = horizontalThreeGridTemplate.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.h("HorizontalThreeGridTemplateGenerator", "CrossedCardOneCardContent is null.");
                return horizontalThreeGridTemplate;
            }
            ArrayList arrayList = new ArrayList();
            for (SingleHorizontalGridStandardContent singleHorizontalGridStandardContent : gridContents) {
                if (eil.d(singleHorizontalGridStandardContent.getItemEffectiveTime(), singleHorizontalGridStandardContent.getItemExpirationTime())) {
                    arrayList.add(singleHorizontalGridStandardContent);
                }
            }
            if (koq.b(arrayList)) {
                return null;
            }
            Collections.sort(arrayList, new Comparator<SingleHorizontalGridStandardContent>() { // from class: dnl.3
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(SingleHorizontalGridStandardContent singleHorizontalGridStandardContent2, SingleHorizontalGridStandardContent singleHorizontalGridStandardContent3) {
                    return singleHorizontalGridStandardContent3.getPriority() - singleHorizontalGridStandardContent2.getPriority();
                }
            });
            horizontalThreeGridTemplate.setGridContents(arrayList);
            return horizontalThreeGridTemplate;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("HorizontalThreeGridTemplateGenerator", "CrossedCardOneCardTemplate JsonSyntaxException");
            return null;
        }
    }
}
