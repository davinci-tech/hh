package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.graphics.Color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBarChartRenderer;
import defpackage.qam;
import defpackage.qan;

/* loaded from: classes9.dex */
public class CoreSleepBarChartView extends HwHealthBarChart {
    private OnBarChartViewDataChangedListener e;

    public interface OnBarChartViewDataChangedListener {
        void onBarChartViewDataChangedListener(float f);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public boolean isDrawYaxisLabelsOnHighLight() {
        return true;
    }

    public CoreSleepBarChartView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.mRenderer = new SleepBarChartRenderer(this, this, this.mAnimator, this.mViewPortHandler, getContext().getResources().getString(R$string.IDS_hw_show_set_target_sport_time_unit), getContext().getResources().getString(R$string.IDS_h_min_unit));
        this.mAxisFirstParty = new qan(this, HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setAxisMinimum(0.0f);
        this.mAxisSecondParty = new qan(this, HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisThirdParty = new qan(this, HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY);
        this.mAxisRendererFirstParty = new qam(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new qam(getContext(), this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new qam(getContext(), this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
        ((qan) this.mAxisFirstParty).setTextColor(Color.argb(255, 255, 255, 255));
        this.mAxisThirdParty.setDrawGridLines(false);
        this.mAxisThirdParty.setDrawLabels(false);
        this.mAxisThirdParty.setEnabled(false);
        this.mAxisThirdParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setTextColor(Color.argb(127, 0, 0, 0));
        this.mAxisSecondParty.setTextColor(Color.argb(127, 0, 0, 0));
        this.mAxisThirdParty.setTextColor(Color.argb(127, 0, 0, 0));
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        b(true, false);
        ((SleepBarChartRenderer) this.mRenderer).b(new SleepBarChartRenderer.OnSleepTimeChangedListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepBarChartView.4
            @Override // com.huawei.ui.main.stories.fitness.views.coresleep.SleepBarChartRenderer.OnSleepTimeChangedListener
            public void onSleepTimeChangedListener(float f) {
                if (CoreSleepBarChartView.this.e != null) {
                    CoreSleepBarChartView.this.e.onBarChartViewDataChangedListener(f);
                }
            }
        });
    }

    public void setOnBarChartViewDataChangedListener(OnBarChartViewDataChangedListener onBarChartViewDataChangedListener) {
        this.e = onBarChartViewDataChangedListener;
    }

    public void d(int i, int i2, String... strArr) {
        ((qam) this.mAxisRendererFirstParty).b(i, i2, strArr);
    }
}
