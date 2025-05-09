package com.huawei.ui.commonui.linechart.icommon;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public interface IHwHealthBarLineDataSet<T extends Entry> extends IBarLineScatterCandleBubbleDataSet<T> {
    List<T> acquireEntryVals();

    float acquireNewerPagerMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireNewerPagerMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireOlderPagerMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireOlderPagerMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    Map<Long, IStorageModel> acquireOriginalVals();

    float acquireRangePagerMaxValue(float f, float f2);

    float acquireRangePagerMinValue(float f, float f2);

    float acquireShowRangeAverageValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireShowRangeMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireShowRangeMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart);

    float acquireShowRangeRatioedValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, HwHealthBaseScrollBarLineChart.DataRatioProvider dataRatioProvider);

    IHwHealthDatasContainer cacheDataContainer(HwHealthBaseBarLineChart hwHealthBaseBarLineChart);

    float calculateLogicByShowRange(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, LogicalUnit logicalUnit);

    float computeDynamicBorderMax(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, float f2);

    float computeDynamicBorderMin(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, float f2);

    HwHealthYAxis.HwHealthAxisDependency getAxisDependencyExt();

    float[] getForcedLabels();

    int getLabelCount();

    String getShowType();

    float getXMaxForcedValue();

    float getXMinForcedValue();

    boolean isDataCalculated();

    boolean isForcedLabelsCount();

    boolean isQuerying();

    boolean isXMaxForced();

    boolean isXMinForced();

    void makeDataCalculated(boolean z);

    void setAxisDependency(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency);

    void setForcedLabels(float[] fArr);

    void setLabelCount(int i, boolean z);

    void setShowType(String str);

    void setValues(List<T> list);

    void setXMaxForcedValue(float f);

    void setXMinForcedValue(float f);
}
