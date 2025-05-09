package com.huawei.ui.commonui.linechart.combinedchart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.nqk;
import defpackage.nqn;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthTrackCombinedChart extends HwHealthBaseCombinedChart {
    public HwHealthTrackCombinedChart(Context context) {
        super(context);
        d();
    }

    public HwHealthTrackCombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public HwHealthTrackCombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        d(new c());
        getLegend().setEnabled(true);
    }

    public void e(HwHealthBaseCombinedChart.DataParser dataParser) {
        d(dataParser);
    }

    /* loaded from: classes9.dex */
    static class c implements HwHealthBaseCombinedChart.DataParser {
        private int e;

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public boolean isSupportOverlaying() {
            return true;
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public boolean isSupportSampling() {
            return true;
        }

        private c() {
            this.e = 5;
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet) {
            nqn.c(list, hwHealthLineDataSet.d(), this.e);
        }

        @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart.DataParser
        public int onSampling(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet) {
            return new nqk().decresPoint(list, i);
        }
    }
}
