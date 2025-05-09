package com.huawei.health.knit.section.chart;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import defpackage.ecx;

/* loaded from: classes3.dex */
public class LacticLineChart extends HwHealthCommonLineChart {
    public LacticLineChart(Context context) {
        super(context);
        this.mRenderer = new ecx(this, this.mAnimator, this.mViewPortHandler, context);
    }

    @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart
    public void a() {
        super.a();
        acquireLayout().l(getResources().getDimensionPixelSize(R.dimen._2131362996_res_0x7f0a04b4));
    }
}
