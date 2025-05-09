package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.MultiFunctionTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFunctionGridContent;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnk implements ITemplateGenerator<MultiFunctionTemplate> {
    dnk() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MultiFunctionTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            MultiFunctionTemplate multiFunctionTemplate = (MultiFunctionTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), MultiFunctionTemplate.class);
            if (multiFunctionTemplate == null) {
                LogUtil.h("MultiFunctionTemplateGenerator", "gridTemplate is null.");
                return null;
            }
            MultiFunctionTemplate b = b(multiFunctionTemplate);
            List<SingleFunctionGridContent> gridContents = b.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.a("MultiFunctionTemplateGenerator", "singleGridContentList is null.");
                return null;
            }
            if (b.isIntelligentFlag()) {
                return b;
            }
            Collections.sort(gridContents, new Comparator<SingleGridContent>() { // from class: dnk.1
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
                    return singleGridContent2.getPriority() - singleGridContent.getPriority();
                }
            });
            return b;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("MultiFunctionTemplateGenerator", "gridTemplate JsonSyntaxException");
            return null;
        }
    }

    private MultiFunctionTemplate b(MultiFunctionTemplate multiFunctionTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleFunctionGridContent> gridContents = multiFunctionTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleFunctionGridContent singleFunctionGridContent : gridContents) {
                if (eil.d(singleFunctionGridContent.getItemEffectiveTime(), singleFunctionGridContent.getItemExpirationTime())) {
                    arrayList.add(singleFunctionGridContent);
                }
            }
            multiFunctionTemplate.setGridContents(arrayList);
        }
        return multiFunctionTemplate;
    }
}
