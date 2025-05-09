package com.huawei.health.knit.section.chart;

import android.content.Context;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import defpackage.ecp;
import defpackage.ecr;

/* loaded from: classes3.dex */
public class HwHealthBloodPressureLineChart extends HwHealthCommonLineChart {
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public boolean isMoveToLastDataStamp() {
        return true;
    }

    public HwHealthBloodPressureLineChart(Context context) {
        super(context);
        this.mRenderer = new ecr(this, this.mAnimator, this.mViewPortHandler, context);
    }

    @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart
    public void b() {
        this.mAxisRendererFirstParty = new ecp(this.f8901a, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new ecp(this.f8901a, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new ecp(this.f8901a, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
    }
}
