package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChart;

/* loaded from: classes6.dex */
public class pzd extends npi {
    public pzd(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(hwHealthBaseBarLineChart);
    }

    @Override // defpackage.npi, com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public void load(HwHealthLineDataSet hwHealthLineDataSet) {
        Entry entryForIndex;
        this.b.b(this.c, hwHealthLineDataSet);
        this.d = hwHealthLineDataSet;
        this.f15422a.clear();
        if (this.b.e() - 1 >= 0 && this.b.e() - 1 < hwHealthLineDataSet.getEntryCount()) {
            c(hwHealthLineDataSet.getEntryForIndex(this.b.e() - 1));
        }
        for (int e = this.b.e(); this.b.d() != 0 && e < this.b.d() + this.b.e(); e++) {
            c(hwHealthLineDataSet.getEntryForIndex(e));
        }
        if (this.b.b() < 0 || this.b.b() >= hwHealthLineDataSet.getEntryCount() || (entryForIndex = hwHealthLineDataSet.getEntryForIndex(this.b.b())) == null) {
            return;
        }
        c(entryForIndex);
    }

    private void c(Entry entry) {
        if (e(entry)) {
            this.f15422a.add(new npf(entry));
        }
    }

    private boolean e(Entry entry) {
        if ((entry.getData() instanceof pzy) && (this.c instanceof BloodSugarLineChart)) {
            pzy pzyVar = (pzy) entry.getData();
            BloodSugarLineChart bloodSugarLineChart = (BloodSugarLineChart) this.c;
            if (!pzyVar.f() && pzyVar.c().equals(bloodSugarLineChart.getDataType())) {
                return true;
            }
        }
        return false;
    }
}
