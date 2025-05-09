package com.huawei.health.motiontrack.api;

import android.view.View;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;

/* loaded from: classes3.dex */
public interface ViewHolderBase {
    HwHealthBaseCombinedChart acquireHeartRateChart();

    View buildView(int i, int i2);
}
