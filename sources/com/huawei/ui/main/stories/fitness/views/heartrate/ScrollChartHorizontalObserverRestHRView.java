package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.view.View;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;

/* loaded from: classes6.dex */
public class ScrollChartHorizontalObserverRestHRView extends ScrollChartHorizontalObserverHRView implements IFocusObserverItem {

    /* renamed from: a, reason: collision with root package name */
    private ICustomCalculator f9990a;
    private OnReferenceChange e;

    public interface OnReferenceChange {
        void onReferenceChange(float f);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverHRView, com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public IFocusObserverItem acquireFocusItem() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return null;
    }

    public ScrollChartHorizontalObserverRestHRView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context, observeredClassifiedView, str);
        this.e = null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        float calculate;
        ICustomCalculator iCustomCalculator = this.f9990a;
        if (iCustomCalculator == null) {
            calculate = hwHealthBaseScrollBarLineChart.getShowDataAverage();
        } else {
            calculate = iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        }
        OnReferenceChange onReferenceChange = this.e;
        if (onReferenceChange != null) {
            onReferenceChange.onReferenceChange(calculate);
        }
    }

    public void setAvgCalculator(ICustomCalculator iCustomCalculator) {
        this.f9990a = iCustomCalculator;
    }

    public void setOnReferenceChangeListener(OnReferenceChange onReferenceChange) {
        this.e = onReferenceChange;
    }
}
