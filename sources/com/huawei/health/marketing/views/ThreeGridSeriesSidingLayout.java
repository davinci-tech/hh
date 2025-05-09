package com.huawei.health.marketing.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.marketing.datatype.SingleHorizontalGridStandardContent;
import defpackage.eks;

/* loaded from: classes3.dex */
public class ThreeGridSeriesSidingLayout extends BaseSeriesSidingLayout<SingleHorizontalGridStandardContent> {
    public ThreeGridSeriesSidingLayout(Context context) {
        super(context);
    }

    public ThreeGridSeriesSidingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ThreeGridSeriesSidingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.marketing.views.BaseSeriesSidingLayout
    protected BaseSeriesSidingAdapter<SingleHorizontalGridStandardContent> getAdapter() {
        return new eks(this.mContext, this.mPositionId, getResourceBriefInfo(), this.mTemplateTheme);
    }
}
