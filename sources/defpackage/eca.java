package defpackage;

import com.github.mikephil.charting.data.Entry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class eca extends npi {
    private List<npf> e;
    private List<npf> g;
    private List<npf> h;

    public eca(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(hwHealthBaseBarLineChart);
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.e = new ArrayList();
    }

    @Override // defpackage.npi, com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public void load(HwHealthLineDataSet hwHealthLineDataSet) {
        Entry entryForIndex;
        this.b.b(this.c, hwHealthLineDataSet);
        this.d = hwHealthLineDataSet;
        a();
        if (this.b.e() - 1 >= 0 && this.b.e() - 1 < hwHealthLineDataSet.getEntryCount()) {
            a(hwHealthLineDataSet.getEntryForIndex(this.b.e() - 1));
        }
        for (int e = this.b.e(); this.b.d() != 0 && e < this.b.d() + this.b.e(); e++) {
            a(hwHealthLineDataSet.getEntryForIndex(e));
        }
        if (this.b.b() >= 0 && this.b.b() < hwHealthLineDataSet.getEntryCount() && (entryForIndex = hwHealthLineDataSet.getEntryForIndex(this.b.b())) != null) {
            a(entryForIndex);
        }
        d(this.g);
        e(hwHealthLineDataSet, this.g);
    }

    private void d(List<npf> list) {
        this.e.clear();
        for (npf npfVar : list) {
            float b = npfVar.b();
            float f = b % 30.0f;
            int i = (int) (b - f);
            int i2 = (int) ((b + 30.0f) - f);
            if (npfVar.d() != 0.0f) {
                this.e.add(npfVar);
            } else {
                Iterator<npf> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        npf next = it.next();
                        float b2 = next.b();
                        if (b2 < i || b2 > i2 || next.d() == 0.0f) {
                        }
                    } else {
                        this.e.add(npfVar);
                        break;
                    }
                }
            }
        }
    }

    @Override // defpackage.npi
    protected void e(HwHealthLineDataSet hwHealthLineDataSet, List<npf> list) {
        if (koq.c(list)) {
            Iterator<npf> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().a() == null) {
                    it.remove();
                }
            }
            Iterator<npf> it2 = list.iterator();
            int i = Integer.MIN_VALUE;
            int i2 = Integer.MAX_VALUE;
            while (it2.hasNext()) {
                int intValue = ((Integer) it2.next().a()).intValue();
                if (intValue > i) {
                    i = intValue;
                }
                if (intValue < i2) {
                    i2 = intValue;
                }
            }
            float d = (d(i) - r0) / 20.5f;
            float e = e(i2);
            Iterator<npf> it3 = list.iterator();
            while (it3.hasNext()) {
                it3.next().b((((Integer) r3.a()).intValue() - (e - (80.5f * d))) / d);
            }
        }
        if (this.c.getRenderer() instanceof eci) {
            e(((eci) this.c.getRenderer()).e == DataInfos.BloodOxygenAltitudeDayHorizontal, hwHealthLineDataSet, list);
        }
    }

    @Override // defpackage.npi, com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public List<npf> queryNewNodes() {
        return this.g;
    }

    @Override // defpackage.npi, com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public List<HwHealthBaseEntry> searchEntryForXValue(float f) {
        if (!(this.c.getRenderer() instanceof eci)) {
            return b(this.f15422a, f);
        }
        return b(this.e, f);
    }

    public List<HwHealthBaseEntry> c(float f) {
        List<HwHealthBaseEntry> b = b(this.f15422a, f);
        if (koq.c(b)) {
            return Math.abs(b.get(0).getX() - f) >= 1.0f ? new ArrayList() : b;
        }
        return new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<HwHealthBaseEntry> a(float f) {
        ArrayList arrayList = new ArrayList();
        float f2 = f % 30.0f;
        int i = (int) (f - f2);
        int i2 = (int) ((30.0f + f) - f2);
        HwHealthBaseEntry hwHealthBaseEntry = null;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < this.d.getEntryCount(); i4++) {
            HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) this.d.getEntryForIndex(i4);
            if (hwHealthBaseEntry2.getX() >= i && hwHealthBaseEntry2.getX() <= i2) {
                int abs = (int) Math.abs(f - hwHealthBaseEntry2.getX());
                if ((hwHealthBaseEntry2.getData() instanceof ecm) && ((ecm) hwHealthBaseEntry2.getData()).d() != Integer.MAX_VALUE && abs < i3) {
                    hwHealthBaseEntry = hwHealthBaseEntry2;
                    i3 = abs;
                }
            }
        }
        if (hwHealthBaseEntry != null) {
            arrayList.add(hwHealthBaseEntry);
        }
        return arrayList;
    }

    private void a() {
        this.f15422a.clear();
        this.g.clear();
        this.h.clear();
    }

    private void a(Entry entry) {
        if (entry.getData() instanceof ecm) {
            int d = ((ecm) entry.getData()).d();
            npf npfVar = new npf(entry);
            if (d != Integer.MAX_VALUE) {
                npfVar.b(Integer.valueOf(d));
            } else {
                this.h.add(npfVar);
            }
            this.f15422a.add(npfVar);
            this.g.add(npfVar);
        }
    }

    private int d(int i) {
        int i2 = i % 50;
        int i3 = i + 50;
        return i2 == 0 ? i3 : i3 - i2;
    }

    private int e(int i) {
        int i2 = i % 50;
        return i2 == 0 ? i - 50 : i - i2;
    }

    private void e(boolean z, HwHealthLineDataSet hwHealthLineDataSet, List<npf> list) {
        int i = z ? 20 : 24;
        int i2 = 0;
        while (i2 < list.size()) {
            int i3 = i2 - 1;
            npf npfVar = null;
            npf npfVar2 = (i3 < 0 || i3 >= list.size()) ? null : list.get(i3);
            int i4 = i2 + 1;
            if (i4 >= 0 && i4 < list.size()) {
                npfVar = list.get(i4);
            }
            npf npfVar3 = list.get(i2);
            if (npfVar2 != null && npfVar3 != null && Math.abs(npfVar2.b() - npfVar3.b()) <= i) {
                npfVar3.d(hwHealthLineDataSet.b((int) npfVar2.b(), (int) npfVar3.b()));
            }
            if (npfVar != null && npfVar3 != null && Math.abs(npfVar.b() - npfVar3.b()) <= i) {
                npfVar3.b(hwHealthLineDataSet.b((int) npfVar3.b(), (int) npfVar.b()));
            }
            i2 = i4;
        }
    }
}
