package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.MultiGridsSleepPageTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnn implements ITemplateGenerator<MultiGridsSleepPageTemplate> {
    dnn() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public MultiGridsSleepPageTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            MultiGridsSleepPageTemplate multiGridsSleepPageTemplate = (MultiGridsSleepPageTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), MultiGridsSleepPageTemplate.class);
            if (multiGridsSleepPageTemplate == null) {
                LogUtil.h("GridTemplateGenerator", "gridTemplate is null.");
                return null;
            }
            MultiGridsSleepPageTemplate e = e(multiGridsSleepPageTemplate);
            Collections.sort(e.getGridContents(), new Comparator<SingleGridContent>() { // from class: dnn.3
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
                    return singleGridContent2.getPriority() - singleGridContent.getPriority();
                }
            });
            return e;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("GridTemplateGenerator", "MultiGridsSleepPageTemplate JsonSyntaxException");
            return null;
        }
    }

    private MultiGridsSleepPageTemplate e(MultiGridsSleepPageTemplate multiGridsSleepPageTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleGridContent> gridContents = multiGridsSleepPageTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleGridContent singleGridContent : gridContents) {
                if (eil.d(singleGridContent.getItemEffectiveTime(), singleGridContent.getItemExpirationTime())) {
                    arrayList.add(singleGridContent);
                }
            }
            multiGridsSleepPageTemplate.setGridContents(arrayList);
        }
        return multiGridsSleepPageTemplate;
    }
}
