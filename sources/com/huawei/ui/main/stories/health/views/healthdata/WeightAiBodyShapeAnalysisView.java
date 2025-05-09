package com.huawei.ui.main.stories.health.views.healthdata;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.rag;

/* loaded from: classes9.dex */
public class WeightAiBodyShapeAnalysisView extends MarketingView {
    public WeightAiBodyShapeAnalysisView(Context context) {
        super(context);
    }

    public WeightAiBodyShapeAnalysisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WeightAiBodyShapeAnalysisView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.main.stories.health.views.healthdata.MarketingView
    public void e(int i) {
        LogUtil.a("WeightAiBodyShapeAnalysisView", "init AI Body Shape Analysis layout");
        if (rag.e()) {
            super.e(i);
        }
    }
}
