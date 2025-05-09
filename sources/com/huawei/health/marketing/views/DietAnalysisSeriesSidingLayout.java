package com.huawei.health.marketing.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.marketing.datatype.CrossedCardOneCardContent;

/* loaded from: classes3.dex */
public class DietAnalysisSeriesSidingLayout extends BaseSeriesSidingLayout<CrossedCardOneCardContent> {
    public DietAnalysisSeriesSidingLayout(Context context) {
        this(context, null);
    }

    public DietAnalysisSeriesSidingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DietAnalysisSeriesSidingLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DietAnalysisSeriesSidingLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingLayout
    protected BaseSeriesSidingAdapter<CrossedCardOneCardContent> getAdapter() {
        return new DietAnalysisSeriesSidingAdapter(this.mContext, this.mPositionId, this.mTemplateTheme, getResourceBriefInfo());
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingLayout
    protected String getLayoutTag() {
        return "DietAnalysisSeriesSidingLayout";
    }
}
