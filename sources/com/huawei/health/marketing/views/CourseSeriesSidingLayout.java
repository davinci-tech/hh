package com.huawei.health.marketing.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.marketing.datatype.SingleCourseRecommendListStandardContent;

/* loaded from: classes3.dex */
public class CourseSeriesSidingLayout extends BaseSeriesSidingLayout<SingleCourseRecommendListStandardContent> {
    public CourseSeriesSidingLayout(Context context) {
        this(context, null);
    }

    public CourseSeriesSidingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CourseSeriesSidingLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CourseSeriesSidingLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingLayout
    protected BaseSeriesSidingAdapter<SingleCourseRecommendListStandardContent> getAdapter() {
        return new CourseSeriesSidingAdapter(this.mContext, this.mPositionId, getResourceBriefInfo(), this.mTemplateTheme);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingLayout
    protected String getLayoutTag() {
        return "CourseSeriesSidingLayout";
    }
}
