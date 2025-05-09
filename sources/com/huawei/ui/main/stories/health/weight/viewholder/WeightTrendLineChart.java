package com.huawei.ui.main.stories.health.weight.viewholder;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class WeightTrendLineChart extends HwHealthLineChart {
    public WeightTrendLineChart(Context context) {
        super(context);
    }

    public WeightTrendLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WeightTrendLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public float correctScrollMoveXValueToContentArea(float f) {
        boolean bc = LanguageUtil.bc(BaseApplication.getContext());
        if (f <= this.mViewPortHandler.contentRight()) {
            return f < this.mViewPortHandler.contentLeft() ? this.mViewPortHandler.contentLeft() + 100.0f : f;
        }
        if (!bc) {
            return this.mViewPortHandler.contentRight() - 60.0f;
        }
        return this.mViewPortHandler.contentRight();
    }
}
