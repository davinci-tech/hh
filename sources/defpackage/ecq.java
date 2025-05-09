package defpackage;

import android.content.Context;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.List;

/* loaded from: classes8.dex */
public class ecq extends HwHealthLineDataSet {
    public ecq(Context context, List<HwHealthBaseEntry> list, String str, String str2, String str3) {
        super(context, list, str, str2, str3);
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet
    public HwHealthBaseEntry constructEntry(float f, IStorageModel iStorageModel) {
        HwHealthBaseEntry constructEntry = super.constructEntry(f, iStorageModel);
        constructEntry.setData(iStorageModel);
        return constructEntry;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return Float.MAX_VALUE;
        }
        return c(Float.MAX_VALUE, hwHealthBaseScrollBarLineChart, false);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return -3.4028235E38f;
        }
        return c(-3.4028235E38f, hwHealthBaseScrollBarLineChart, true);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireRangePagerMinValue(float f, float f2) {
        float f3 = Float.MAX_VALUE;
        edq edqVar = null;
        for (T t : this.mEntries) {
            if (t != null) {
                if (t.getData() instanceof edq) {
                    edqVar = (edq) t.getData();
                }
                if (t.getX() >= f && t.getX() <= f2 && edqVar != null && edqVar.b() < f3) {
                    f3 = edqVar.b();
                }
            }
        }
        return f3;
    }

    public float c(float f, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, boolean z) {
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        edq edqVar = null;
        for (T t : this.mEntries) {
            if (t != null) {
                if (t.getData() instanceof edq) {
                    edqVar = (edq) t.getData();
                }
                if (t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                    if (z) {
                        if (edqVar != null && edqVar.c() > f) {
                            f = edqVar.c();
                        }
                    } else if (edqVar != null && edqVar.b() < f) {
                        f = edqVar.b();
                    }
                }
            }
        }
        return f;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet, com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireRangePagerMaxValue(float f, float f2) {
        float f3 = Float.MAX_VALUE;
        edq edqVar = null;
        for (T t : this.mEntries) {
            if (t != null) {
                if (t.getData() instanceof edq) {
                    edqVar = (edq) t.getData();
                }
                if (t.getX() >= f && t.getX() <= f2 && edqVar != null && edqVar.c() > f3) {
                    f3 = edqVar.c();
                }
            }
        }
        return f3;
    }
}
