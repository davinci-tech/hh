package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.List;

/* loaded from: classes9.dex */
public class pki extends now {
    public pki(List<IHwHealthLineDataSet> list) {
        super(list);
    }

    @Override // defpackage.now, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData
    public Entry getEntryForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            if (t != null) {
                IHwHealthDatasContainer cacheDataContainer = t.cacheDataContainer(hwHealthBaseBarLineChart);
                if (cacheDataContainer instanceof IHwHealthLineDatasContainer) {
                    IHwHealthLineDatasContainer iHwHealthLineDatasContainer = (IHwHealthLineDatasContainer) cacheDataContainer;
                    if (iHwHealthLineDatasContainer instanceof eca) {
                        List<HwHealthBaseEntry> c = ((eca) iHwHealthLineDatasContainer).c(f);
                        if (t instanceof HwHealthLineDataSet) {
                            if (koq.b(c)) {
                                hwEntrys.add(new HwEntrys.HwDataEntry(null, (HwHealthLineDataSet) t));
                            } else {
                                hwEntrys.add(new HwEntrys.HwDataEntry(c.get(0), (HwHealthLineDataSet) t));
                            }
                        }
                    }
                }
            }
        }
        return hwEntrys;
    }
}
