package com.huawei.ui.main.stories.fitness.views.heartrate.linechart;

import android.content.Context;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.nom;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class HeartRateHorizontalLineChart extends RestHeartRateLineChart {
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public boolean isSupportScaled() {
        return true;
    }

    public HeartRateHorizontalLineChart(Context context) {
        super(context);
        injectDataParser(new c());
    }

    static class c implements HwHealthLineChart.DataParser {
        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet) {
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public boolean supportOverlaying() {
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public boolean supportSamping() {
            return true;
        }

        private c() {
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public int onSamping(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet) {
            int ceil = (int) Math.ceil((TimeUnit.MILLISECONDS.toMinutes(nom.d()) * 1.0d) / (i * 3));
            if (ceil >= 1) {
                return ceil;
            }
            return 5;
        }
    }
}
