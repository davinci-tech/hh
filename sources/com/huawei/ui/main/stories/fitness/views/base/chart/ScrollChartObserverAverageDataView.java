package com.huawei.ui.main.stories.fitness.views.base.chart;

import android.content.Context;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;

/* loaded from: classes6.dex */
public class ScrollChartObserverAverageDataView extends ScrollChartObserverView {
    private ICustomCalculator c;

    public ScrollChartObserverAverageDataView(Context context, ObserveredClassifiedView observeredClassifiedView, String str, String str2) {
        super(context, observeredClassifiedView, str, str2);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        float showDataAverage;
        ICustomCalculator iCustomCalculator = this.c;
        if (iCustomCalculator != null) {
            showDataAverage = iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        } else {
            showDataAverage = hwHealthBaseScrollBarLineChart.getShowDataAverage();
        }
        setContentText(this.mHost.convertFloat2TextShow(showDataAverage));
    }

    public void setCustomCalculator(ICustomCalculator iCustomCalculator) {
        this.c = iCustomCalculator;
    }
}
