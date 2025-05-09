package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
class dng implements ITemplateGenerator<GridTemplate> {
    dng() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public GridTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            GridTemplate gridTemplate = (GridTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), GridTemplate.class);
            if (gridTemplate == null) {
                LogUtil.h("GridTemplateGenerator", "gridTemplate is null.");
                return null;
            }
            GridTemplate d = d(gridTemplate);
            List<SingleGridContent> gridContents = d.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.a("GridTemplateGenerator", "singleGridContentList is null.");
                return null;
            }
            Iterator<SingleGridContent> it = gridContents.iterator();
            while (it.hasNext()) {
                eil.c(it.next(), resourceBriefInfo);
            }
            if (d.isIntelligentFlag()) {
                return d;
            }
            Collections.sort(gridContents, new Comparator<SingleGridContent>() { // from class: dng.5
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
                    return singleGridContent2.getPriority() - singleGridContent.getPriority();
                }
            });
            return d;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("GridTemplateGenerator", "gridTemplate JsonSyntaxException");
            return null;
        }
    }

    private GridTemplate d(GridTemplate gridTemplate) {
        ArrayList arrayList = new ArrayList();
        List<SingleGridContent> gridContents = gridTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            for (SingleGridContent singleGridContent : gridContents) {
                if (eil.d(singleGridContent.getItemEffectiveTime(), singleGridContent.getItemExpirationTime())) {
                    arrayList.add(singleGridContent);
                }
            }
            gridTemplate.setGridContents(new dnj().e(arrayList));
        }
        return gridTemplate;
    }
}
