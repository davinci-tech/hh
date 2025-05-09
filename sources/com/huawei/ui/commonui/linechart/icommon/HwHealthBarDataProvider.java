package com.huawei.ui.commonui.linechart.icommon;

import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import defpackage.nmz;
import defpackage.nnj;

/* loaded from: classes6.dex */
public interface HwHealthBarDataProvider extends BarLineScatterCandleBubbleDataProvider {
    nnj getAxisDataRenderArg(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    nmz getBarData();

    HwHealthTransformer getTransformer(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    boolean isDrawBarShadowEnabled();

    boolean isDrawValueAboveBarEnabled();

    boolean isHighlightFullBarEnabled();
}
