package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.view.View;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;

/* loaded from: classes6.dex */
public class ScrollChartHorizontalObserverSportHRView extends ScrollChartHorizontalObserverHRView implements IFocusObserverItem {
    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
    }

    public ScrollChartHorizontalObserverSportHRView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context, observeredClassifiedView, str);
    }
}
