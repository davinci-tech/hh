package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import java.util.List;

/* loaded from: classes6.dex */
public class pyp extends nmy {
    public pyp(List<HwHealthBarEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(list, str, str2, str3, dataInfos);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireRangePagerMinValue(float f, float f2) {
        float e = e(f, f2);
        LogUtil.a("BloodOxygenHealthBarDataSet acquireRangePagerMinValue ", Float.valueOf(e));
        return e;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return Float.MAX_VALUE;
        }
        float e = e(hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum(), hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum());
        LogUtil.a("BloodOxygenHealthBarDataSet acquireShowRangeMinValue ", Float.valueOf(e));
        return e;
    }

    private float e(float f, float f2) {
        float f3 = Float.MAX_VALUE;
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= f && t.getX() <= f2) {
                eck a2 = t.acquireModel() instanceof ech ? ((ech) t.acquireModel()).a() : null;
                if (a2 != null && a2.a() < f3) {
                    f3 = a2.a();
                }
            }
        }
        return f3;
    }
}
