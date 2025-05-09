package com.huawei.health.marketing.datatype.templates;

import java.util.List;

/* loaded from: classes3.dex */
public class CourseNoticeStandardTemplate extends MarketBaseTemplate {
    private List<SingleCourseNoticeStandardContent> gridContents;

    public List<SingleCourseNoticeStandardContent> getGridContents() {
        return this.gridContents;
    }

    @Override // com.huawei.health.marketing.datatype.templates.MarketBaseTemplate
    public String toString() {
        return "CourseNoticeStandardTemplate{" + super.toString() + ", gridContents=" + this.gridContents + '}';
    }
}
