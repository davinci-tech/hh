package com.huawei.health.knit.section.chart;

import android.content.Context;
import com.huawei.health.knit.section.chart.TemperatureBarChart;
import com.huawei.health.knit.section.chart.TemperatureBarChartRender;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import defpackage.edm;
import defpackage.npe;

/* loaded from: classes3.dex */
public class TemperatureBarChart extends HwHealthBarChart {
    private OnDataChangedListener c;

    public interface OnDataChangedListener {
        void onDataChangedListener(edm edmVar);
    }

    public TemperatureBarChart(Context context) {
        super(context);
        d();
    }

    public void setShowDataType(String str) {
        if (this.mRenderer instanceof TemperatureBarChartRender) {
            ((TemperatureBarChartRender) this.mRenderer).d(str);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void setMarkerViewPosition(HwHealthBaseBarLineChart.a aVar) {
        this.mMarkerViewPosition = aVar;
    }

    private void d() {
        LogUtil.a("TemperatureBarChart", "initStyle");
        this.mRenderer = new TemperatureBarChartRender(this, this, this.mAnimator, this.mViewPortHandler);
        this.mAxisRendererFirstParty = new npe(getContext(), this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        enableMarkerView(true);
        enableSpacePreserveForDataOverlay(true);
        if (this.mMarker instanceof HwHealthMarkerView) {
            ((HwHealthMarkerView) this.mMarker).d(false);
        }
        if (this.mRenderer instanceof TemperatureBarChartRender) {
            ((TemperatureBarChartRender) this.mRenderer).b(new TemperatureBarChartRender.OnTemperatureTimeChangedListener() { // from class: edk
                @Override // com.huawei.health.knit.section.chart.TemperatureBarChartRender.OnTemperatureTimeChangedListener
                public final void onTemperatureTimeChangedListener(edm edmVar) {
                    TemperatureBarChart.this.b(edmVar);
                }
            });
        }
    }

    public /* synthetic */ void b(edm edmVar) {
        OnDataChangedListener onDataChangedListener = this.c;
        if (onDataChangedListener != null) {
            onDataChangedListener.onDataChangedListener(edmVar);
        }
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.c = onDataChangedListener;
    }
}
