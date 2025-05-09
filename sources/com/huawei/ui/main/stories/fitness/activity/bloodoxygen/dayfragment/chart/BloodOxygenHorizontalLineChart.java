package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.chart;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;

/* loaded from: classes9.dex */
public class BloodOxygenHorizontalLineChart extends com.huawei.health.knit.section.chart.BloodOxygenLineChart {
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public boolean isSupportScaled() {
        return true;
    }

    public BloodOxygenHorizontalLineChart(Context context, DataInfos dataInfos) {
        super(context, dataInfos);
        setScaletMaxima(8.0f);
    }

    @Override // com.huawei.health.knit.section.chart.BloodOxygenLineChart, com.huawei.health.knit.section.chart.InteractorLineChart
    public void initCursorMode() {
        Layout acquireLayout = acquireLayout();
        acquireLayout.l(Utils.convertDpToPixel(15.0f));
        acquireLayout.c(Utils.convertDpToPixel(20.0f));
        setBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131296657_res_0x7f090191));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
    }
}
