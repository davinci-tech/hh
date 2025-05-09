package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.featuremarketing.template.ITemplateGenerator;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListContent;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import com.huawei.health.marketing.datatype.templates.CourseRecommendListTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
class dnp implements ITemplateGenerator<CourseRecommendListTemplate> {
    dnp() {
    }

    @Override // com.huawei.health.featuremarketing.template.ITemplateGenerator
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public CourseRecommendListTemplate generate(ResourceBriefInfo resourceBriefInfo) {
        try {
            CourseRecommendListTemplate courseRecommendListTemplate = (CourseRecommendListTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), CourseRecommendListTemplate.class);
            if (courseRecommendListTemplate == null) {
                LogUtil.h("SeriesCourseSlidingTemplateGenerator", "CourseRecommendListTemplate is null.");
                return null;
            }
            List<SingleCourseRecommendListStandardContent> gridContents = courseRecommendListTemplate.getGridContents();
            if (koq.b(gridContents)) {
                LogUtil.h("SeriesCourseSlidingTemplateGenerator", "singleEntryContentList is null.");
                return courseRecommendListTemplate;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "singleEntryContentList rcmTabList:";
            objArr[1] = resourceBriefInfo.getRcmTabList() != null ? resourceBriefInfo.getRcmTabList().toString() : "";
            LogUtil.a("SeriesCourseSlidingTemplateGenerator", objArr);
            ArrayList arrayList = new ArrayList();
            for (SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent : gridContents) {
                List<SingleCourseRecommendListContent> d = d(singleCourseRecommendListStandardContent.getSubContents());
                if (!koq.b(d)) {
                    Object[] objArr2 = new Object[2];
                    objArr2[0] = "singleEntryContentList recommendList:";
                    objArr2[1] = singleCourseRecommendListStandardContent.getRecommendList() != null ? singleCourseRecommendListStandardContent.getRecommendList().toString() : "";
                    LogUtil.a("SeriesCourseSlidingTemplateGenerator", objArr2);
                    for (SingleCourseRecommendListContent singleCourseRecommendListContent : d) {
                        String d2 = eil.d(singleCourseRecommendListStandardContent.getRecommendList(), singleCourseRecommendListContent.getItemId());
                        if (!TextUtils.isEmpty(d2)) {
                            singleCourseRecommendListContent.setAlgId(d2);
                            singleCourseRecommendListContent.setSmartRecommend(true);
                        }
                        singleCourseRecommendListContent.setAbInfo(MarketingBiUtils.d(resourceBriefInfo));
                        singleCourseRecommendListContent.setRecommendInfoGenerated(true);
                    }
                    String d3 = eil.d(resourceBriefInfo.getRcmTabList(), singleCourseRecommendListStandardContent.getTabId());
                    if (!TextUtils.isEmpty(d3)) {
                        singleCourseRecommendListStandardContent.setAlgId(d3);
                        singleCourseRecommendListStandardContent.setSmartRecommend(true);
                    }
                    singleCourseRecommendListStandardContent.setAbInfo(MarketingBiUtils.d(resourceBriefInfo));
                    singleCourseRecommendListStandardContent.setSubContents(d);
                    arrayList.add(singleCourseRecommendListStandardContent);
                }
            }
            Collections.sort(arrayList, new Comparator<SingleCourseRecommendListStandardContent>() { // from class: dnp.2
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent2, SingleCourseRecommendListStandardContent singleCourseRecommendListStandardContent3) {
                    return singleCourseRecommendListStandardContent3.getTabPriority() - singleCourseRecommendListStandardContent2.getTabPriority();
                }
            });
            courseRecommendListTemplate.setGridContents(arrayList);
            return courseRecommendListTemplate;
        } catch (JsonSyntaxException unused) {
            LogUtil.h("SeriesCourseSlidingTemplateGenerator", "CourseRecommendListTemplate JsonSyntaxException");
            return null;
        }
    }

    private List<SingleCourseRecommendListContent> d(List<SingleCourseRecommendListContent> list) {
        if (koq.b(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (SingleCourseRecommendListContent singleCourseRecommendListContent : list) {
            if (eil.d(singleCourseRecommendListContent.getItemEffectiveTime(), singleCourseRecommendListContent.getItemExpirationTime())) {
                arrayList.add(singleCourseRecommendListContent);
            }
        }
        if (koq.b(arrayList)) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<SingleCourseRecommendListContent>() { // from class: dnp.4
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(SingleCourseRecommendListContent singleCourseRecommendListContent2, SingleCourseRecommendListContent singleCourseRecommendListContent3) {
                return singleCourseRecommendListContent3.getPriority() - singleCourseRecommendListContent2.getPriority();
            }
        });
        return arrayList;
    }
}
