package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.List;

/* loaded from: classes6.dex */
public class now extends HwHealthBaseBarLineData<IHwHealthLineDataSet> {
    public now(List<IHwHealthLineDataSet> list) {
        super(list);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData, com.github.mikephil.charting.data.ChartData
    public Entry getEntryForHighlight(Highlight highlight) {
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            if (t != null) {
                List<T> entriesForXValue = t.getEntriesForXValue(highlight.getX());
                if (!koq.b(entriesForXValue) && (t instanceof HwHealthLineDataSet)) {
                    hwEntrys.add(new HwEntrys.HwDataEntry((HwHealthBaseEntry) entriesForXValue.get(0), (HwHealthLineDataSet) t));
                }
            }
        }
        return hwEntrys;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData
    public Entry getEntryForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            if (t != null) {
                IHwHealthDatasContainer cacheDataContainer = t.cacheDataContainer(hwHealthBaseBarLineChart);
                if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                    List<HwHealthBaseEntry> searchEntryForXValue = ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(f);
                    if (t instanceof HwHealthLineDataSet) {
                        if (koq.b(searchEntryForXValue)) {
                            hwEntrys.add(new HwEntrys.HwDataEntry(null, (HwHealthLineDataSet) t));
                        } else {
                            hwEntrys.add(new HwEntrys.HwDataEntry(searchEntryForXValue.get(0), (HwHealthLineDataSet) t));
                        }
                    }
                }
            }
        }
        return hwEntrys;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData
    public float getNearestValueForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineChart.MarkerViewSlidingMode markerViewSlidingMode) {
        IHwHealthLineDatasContainer iHwHealthLineDatasContainer;
        HwHealthBaseEntry acquireNearestPoint;
        if (!koq.c(this.mDataSets) || hwHealthBaseBarLineChart == null || !(((IHwHealthLineDataSet) this.mDataSets.get(0)).cacheDataContainer(hwHealthBaseBarLineChart) instanceof IHwHealthLineDatasContainer) || (iHwHealthLineDatasContainer = (IHwHealthLineDatasContainer) ((IHwHealthLineDataSet) this.mDataSets.get(0)).cacheDataContainer(hwHealthBaseBarLineChart)) == null) {
            return f;
        }
        List<HwHealthBaseEntry> searchEntryForXValue = iHwHealthLineDatasContainer.searchEntryForXValue(f);
        if (koq.c(searchEntryForXValue)) {
            return searchEntryForXValue.get(0).getX();
        }
        return (!HwHealthBaseBarLineChart.MarkerViewSlidingMode.FORCE_ACCORDING_DATA.equals(markerViewSlidingMode) || (acquireNearestPoint = iHwHealthLineDatasContainer.acquireNearestPoint(f)) == null) ? f : acquireNearestPoint.getX();
    }
}
