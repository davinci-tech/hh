package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;
import java.util.List;

/* loaded from: classes3.dex */
public class CourseRecommendListTemplate extends BaseSidingTemplate<SingleCourseRecommendListStandardContent> {
    private List<SingleCourseRecommendListStandardContent> gridContents;

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public List<SingleCourseRecommendListStandardContent> getGridContents() {
        return this.gridContents;
    }

    @Override // com.huawei.health.marketing.datatype.templates.BaseSidingTemplate
    public void setGridContents(List<SingleCourseRecommendListStandardContent> list) {
        this.gridContents = list;
    }
}
