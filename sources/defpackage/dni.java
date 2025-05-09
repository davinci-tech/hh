package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.HomePageRecommendTemplate;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
class dni implements ITemplateGenerator<HomePageRecommendTemplate> {
    dni() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public HomePageRecommendTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            HomePageRecommendTemplate homePageRecommendTemplate = (HomePageRecommendTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), HomePageRecommendTemplate.class);
            if (homePageRecommendTemplate == null) {
                LogUtil.h("HomePageTemplateGenerator", "getHomePageRecommendTemplate is null.");
                return null;
            }
            HomePageRecommendTemplate c = c(homePageRecommendTemplate);
            List<SingleGridContent> gridContents = c.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.h("HomePageTemplateGenerator", "getHomePageRecommendTemplate singleGridContentList is null.");
                return null;
            }
            Iterator<SingleGridContent> it = gridContents.iterator();
            while (it.hasNext()) {
                eil.c(it.next(), resourceBriefInfo);
            }
            Collections.sort(gridContents, new Comparator() { // from class: dnh
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return dni.d((SingleGridContent) obj, (SingleGridContent) obj2);
                }
            });
            return c;
        } catch (JsonSyntaxException e) {
            LogUtil.h("HomePageTemplateGenerator", "getHomePageRecommendTemplate JsonSyntaxException ", e.getMessage());
            return null;
        }
    }

    static /* synthetic */ int d(SingleGridContent singleGridContent, SingleGridContent singleGridContent2) {
        return singleGridContent2.getPriority() - singleGridContent.getPriority();
    }

    private HomePageRecommendTemplate c(HomePageRecommendTemplate homePageRecommendTemplate) {
        List<SingleGridContent> gridContents = homePageRecommendTemplate.getGridContents();
        if (!koq.b(gridContents)) {
            ArrayList arrayList = new ArrayList();
            for (SingleGridContent singleGridContent : gridContents) {
                if (eil.d(singleGridContent.getItemEffectiveTime(), singleGridContent.getItemExpirationTime())) {
                    arrayList.add(singleGridContent);
                }
            }
            homePageRecommendTemplate.setGridContents(arrayList);
        }
        return homePageRecommendTemplate;
    }
}
