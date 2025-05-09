package com.huawei.ui.commonui.linechart.icommon;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import defpackage.nnj;
import defpackage.now;

/* loaded from: classes6.dex */
public interface HwHealthLineDataProvider extends BarLineScatterCandleBubbleDataProvider {
    HwHealthYAxis getAxis(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    nnj getAxisDataRenderArg(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    now getLineData();

    default now getPointData() {
        return null;
    }

    HwHealthTransformer getTransformer(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    XAxis getXAxis();
}
