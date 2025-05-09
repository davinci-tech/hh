package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleEntryContent;
import com.huawei.health.marketing.datatype.templates.QuickEntryTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnt implements ITemplateGenerator<QuickEntryTemplate> {
    dnt() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public QuickEntryTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            QuickEntryTemplate quickEntryTemplate = (QuickEntryTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), QuickEntryTemplate.class);
            if (quickEntryTemplate == null) {
                LogUtil.h("QuickEntryTemplateGenerator", "quickEntryTemplate is null.");
                return null;
            }
            QuickEntryTemplate a2 = a(quickEntryTemplate);
            Collections.sort(a2.getGridContents(), new Comparator<SingleEntryContent>() { // from class: dnt.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(SingleEntryContent singleEntryContent, SingleEntryContent singleEntryContent2) {
                    return singleEntryContent2.getPriority() - singleEntryContent.getPriority();
                }
            });
            return a2;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("QuickEntryTemplateGenerator", "quickEntryTemplate JsonSyntaxException");
            return null;
        }
    }

    private QuickEntryTemplate a(QuickEntryTemplate quickEntryTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleEntryContent> gridContents = quickEntryTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleEntryContent singleEntryContent : gridContents) {
                if (eil.d(singleEntryContent.getItemEffectiveTime(), singleEntryContent.getItemExpirationTime())) {
                    arrayList.add(singleEntryContent);
                }
            }
            quickEntryTemplate.setGridContents(arrayList);
        }
        return quickEntryTemplate;
    }
}
