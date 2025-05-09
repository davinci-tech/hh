package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.view.View;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class SportCardView extends HeartRateCardView implements IFocusObserverItem {

    /* renamed from: a, reason: collision with root package name */
    private ICustomCalculator f10000a;
    private ICustomCalculator b;

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return null;
    }

    public SportCardView(Context context, ObserveredClassifiedView observeredClassifiedView) {
        super(context, observeredClassifiedView, context.getString(R$string.IDS_hw_health_show_healthdata_heartrate_range), context.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.b = null;
        this.f10000a = null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        float f;
        float f2;
        ICustomCalculator iCustomCalculator = this.b;
        if (iCustomCalculator == null || this.f10000a == null) {
            float[] showMinMaxRangeByRatio = hwHealthBaseScrollBarLineChart.getShowMinMaxRangeByRatio();
            if (showMinMaxRangeByRatio == null || showMinMaxRangeByRatio.length != 2) {
                f = Float.MIN_VALUE;
                f2 = Float.MAX_VALUE;
            } else {
                float f3 = showMinMaxRangeByRatio[0];
                f = showMinMaxRangeByRatio[1];
                f2 = f3;
            }
        } else {
            f = iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
            f2 = this.f10000a.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        }
        if (f >= f2 && f2 > 0.0f) {
            setContentText(UnitUtil.e(f2, 1, 0) + Constants.LINK + UnitUtil.e(f, 1, 0));
            return;
        }
        setContentText("--");
    }

    public void c(ICustomCalculator iCustomCalculator) {
        this.b = iCustomCalculator;
    }

    public void b(ICustomCalculator iCustomCalculator) {
        this.f10000a = iCustomCalculator;
    }
}
