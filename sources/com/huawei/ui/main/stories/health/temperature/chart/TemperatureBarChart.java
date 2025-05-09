package com.huawei.ui.main.stories.health.temperature.chart;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.stories.BarChartRender;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureBarChart;
import defpackage.npe;
import defpackage.qor;

/* loaded from: classes6.dex */
public class TemperatureBarChart extends HwHealthBarChart {
    private OnDataChangedListener d;

    public interface OnDataChangedListener {
        void onDataChangedListener(float f, float f2);
    }

    public TemperatureBarChart(Context context) {
        super(context);
        a();
    }

    public void setShowDataType(String str) {
        if (this.mRenderer instanceof qor) {
            ((qor) this.mRenderer).a(str);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void setMarkerViewPosition(HwHealthBaseBarLineChart.a aVar) {
        this.mMarkerViewPosition = aVar;
    }

    private void a() {
        LogUtil.a("TemperatureBarChart", "initStyle");
        this.mRenderer = new qor(this, this, this.mAnimator, this.mViewPortHandler);
        this.mAxisRendererFirstParty = new npe(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        if (this.mMarker instanceof HwHealthMarkerView) {
            ((HwHealthMarkerView) this.mMarker).d(false);
        }
        if (this.mRenderer instanceof qor) {
            ((qor) this.mRenderer).b(new BarChartRender.OnTimeChangedListener() { // from class: qol
                @Override // com.huawei.ui.main.stories.BarChartRender.OnTimeChangedListener
                public final void onTimeChangedListener(float f, float f2) {
                    TemperatureBarChart.this.e(f, f2);
                }
            });
        }
    }

    public /* synthetic */ void e(float f, float f2) {
        OnDataChangedListener onDataChangedListener = this.d;
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChangedListener(f, f2);
        }
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.d = onDataChangedListener;
    }
}
