package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.featuremarketing.layout.ILayoutGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.BaseTemplate;
import com.huawei.health.marketing.datatype.templates.HomePageRecommendTemplate;
import com.huawei.health.marketing.views.HomePageRecommendLayout;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class dlr implements ILayoutGenerator {
    dlr() {
    }

    @Override // com.huawei.health.featuremarketing.layout.ILayoutGenerator
    public View generate(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (c == null) {
            LogUtil.h("HomePageRecommendLayoutGenerator", "filterTemplate is null");
            return null;
        }
        HomePageRecommendLayout homePageRecommendLayout = new HomePageRecommendLayout(context);
        homePageRecommendLayout.b(i, resourceBriefInfo, c);
        return homePageRecommendLayout;
    }

    public List<View> a(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        BaseTemplate c = dnr.c(resourceBriefInfo);
        if (!(c instanceof HomePageRecommendTemplate)) {
            LogUtil.h("HomePageRecommendLayoutGenerator", "filterTemplate is error");
            return null;
        }
        List<SingleGridContent> gridContents = ((HomePageRecommendTemplate) c).getGridContents();
        if (koq.b(gridContents)) {
            LogUtil.h("HomePageRecommendLayoutGenerator", "gridContents is empty");
            return null;
        }
        if (koq.c(resourceBriefInfo.getRecommendList())) {
            LogUtil.a("HomePageRecommendLayoutGenerator", "resourceBriefInfo recommendList ： ", resourceBriefInfo.getRecommendList().toString());
        }
        ArrayList arrayList = new ArrayList();
        for (SingleGridContent singleGridContent : gridContents) {
            if (singleGridContent != null) {
                LogUtil.a("HomePageRecommendLayoutGenerator", "gridContent itemId ： ", singleGridContent.getItemId());
                HomePageRecommendLayout homePageRecommendLayout = new HomePageRecommendLayout(context);
                homePageRecommendLayout.b(i, resourceBriefInfo, c, singleGridContent);
                arrayList.add(homePageRecommendLayout);
            }
        }
        return arrayList;
    }
}
