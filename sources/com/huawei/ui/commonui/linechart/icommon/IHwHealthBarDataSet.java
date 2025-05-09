package com.huawei.ui.commonui.linechart.icommon;

import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import java.util.List;

/* loaded from: classes6.dex */
public interface IHwHealthBarDataSet extends IBarLineScatterCandleBubbleDataSet<HwHealthBarEntry>, IHwHealthBarLineDataSet<HwHealthBarEntry> {

    public enum BarWidthMode {
        DEFAULT_WIDTH,
        DATA_SET_X_AXIS_WIDTH,
        DATA_SET_DP_WIDTH
    }

    int acquireFocusColor();

    int acquireFocusColor(HwHealthBarEntry hwHealthBarEntry);

    int getBarBorderColor();

    float getBarBorderWidth();

    float getBarDrawWidth();

    float getBarDrawWidth(Transformer transformer);

    BarWidthMode getBarDrawWidthMode();

    int getBarShadowColor();

    HwHealthBarDataSet.DrawColorMode getDrawColorMode();

    List<HwHealthBarEntry> getEntriesForXValue(float f, HwHealthBarDataProvider hwHealthBarDataProvider);

    int getHighLightAlpha();

    String[] getStackLabels();

    int getStackSize();

    boolean isSearchByBarWidth();

    boolean isStacked();

    void setBarDrawWidth(float f);

    void setBarDrawWidthDp(float f);

    void setSearchByBarWidth(boolean z);
}
