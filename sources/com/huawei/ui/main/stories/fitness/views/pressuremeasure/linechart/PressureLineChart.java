package com.huawei.ui.main.stories.fitness.views.pressuremeasure.linechart;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart;
import defpackage.nox;
import defpackage.put;
import defpackage.pux;
import defpackage.pyd;

/* loaded from: classes9.dex */
public class PressureLineChart extends InteractorLineChart {
    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public pyd getHealthLineDataSet(HwHealthBaseBarLineData hwHealthBaseBarLineData) {
        return null;
    }

    public PressureLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PressureLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initCursorMode() {
        setBackgroundColor(this.mContext.getResources().getColor(R.color._2131297258_res_0x7f0903ea));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initYRender() {
        this.mAxisRendererFirstParty = new put(this.mContext, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new put(this.mContext, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new put(this.mContext, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        LogUtil.c("PressureLineChart", "refresh chart");
        super.refresh();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public float computeTipsCenterHeightPx() {
        return (this.mAxisFirstParty.mEntries[1] + this.mAxisFirstParty.mEntries[2]) / 2.0f;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public nox getLineChartRender(Context context, DataInfos dataInfos) {
        return new pux(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initXAxis() {
        XAxis xAxis = getXAxis();
        xAxis.setValueFormatter(new InteractorLineChart.d());
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(1440.0f);
    }
}
