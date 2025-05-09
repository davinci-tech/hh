package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import java.util.List;

/* loaded from: classes6.dex */
public class nmz extends HwHealthBaseBarLineData<IHwHealthBarDataSet> {
    private float e;

    public nmz(List<IHwHealthBarDataSet> list) {
        super(list);
        this.e = 0.85f;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData, com.github.mikephil.charting.data.ChartData
    public Entry getEntryForHighlight(Highlight highlight) {
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            List<T> entriesForXValue = t.getEntriesForXValue(highlight.getX());
            if (!koq.b(entriesForXValue)) {
                hwEntrys.add(new HwEntrys.HwDataEntry((HwHealthBaseEntry) entriesForXValue.get(0), (HwHealthBarDataSet) t));
            }
        }
        return hwEntrys;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData
    public Entry getEntryForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            if (t instanceof HwHealthBarDataSet) {
                List<T> entriesForXValue = t.getEntriesForXValue(f);
                if (koq.b(entriesForXValue)) {
                    hwEntrys.add(new HwEntrys.HwDataEntry(null, (HwHealthBarDataSet) t));
                } else {
                    hwEntrys.add(new HwEntrys.HwDataEntry((HwHealthBaseEntry) entriesForXValue.get(0), (HwHealthBarDataSet) t));
                }
            }
        }
        return hwEntrys;
    }

    public void b(float f) {
        this.e = f;
    }

    public float d() {
        return this.e;
    }

    public void d(DataInfos dataInfos) {
        if (dataInfos.isWeekData()) {
            b(311.1f);
            return;
        }
        if (dataInfos.isMonthData()) {
            b(534.6f);
            return;
        }
        if (dataInfos.isYearData()) {
            b(16272.001f);
        } else if (dataInfos.isDayData()) {
            b(12.6f);
        } else if (dataInfos.isAllData()) {
            b(81256.32f);
        }
    }
}
