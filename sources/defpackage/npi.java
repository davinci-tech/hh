package defpackage;

import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class npi implements IHwHealthLineDatasContainer {

    /* renamed from: a, reason: collision with root package name */
    protected List<npf> f15422a = new ArrayList();
    protected d b = new d();
    protected HwHealthBaseBarLineChart c;
    protected HwHealthLineDataSet d;

    protected boolean d(float f, float f2, float f3) {
        return f3 < f && f3 > f2;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public List<npf> queryNewNodes() {
        return null;
    }

    public npi(HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        this.c = hwHealthBaseBarLineChart;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public HwHealthBaseBarLineChart acquireChart() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public void reset() {
        this.f15422a.clear();
        this.d = null;
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r0v24, types: [com.github.mikephil.charting.data.Entry] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public void load(HwHealthLineDataSet hwHealthLineDataSet) {
        ?? entryForIndex;
        this.b.b(this.c, hwHealthLineDataSet);
        this.d = hwHealthLineDataSet;
        this.f15422a.clear();
        if (this.b.e() - 1 >= 0 && this.b.e() - 1 < hwHealthLineDataSet.getEntryCount()) {
            this.f15422a.add(new npf(hwHealthLineDataSet.getEntryForIndex(this.b.e() - 1)));
        }
        for (int e = this.b.e(); this.b.d() != 0 && e < this.b.b(); e++) {
            npf npfVar = new npf(hwHealthLineDataSet.getEntryForIndex(e));
            if (e == this.b.e() && hwHealthLineDataSet.isDrawStartEndNode()) {
                npfVar.e(true);
            }
            if (e == this.b.b() - 1 && hwHealthLineDataSet.isDrawStartEndNode()) {
                npfVar.a(true);
            }
            this.f15422a.add(npfVar);
        }
        if (this.b.b() >= 0 && this.b.b() < hwHealthLineDataSet.getEntryCount() && (entryForIndex = hwHealthLineDataSet.getEntryForIndex(this.b.b())) != 0) {
            this.f15422a.add(new npf(entryForIndex));
        }
        e(hwHealthLineDataSet, this.f15422a);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public List<npf> queryNodes() {
        return this.f15422a;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public List<HwHealthBaseEntry> searchEntryForXValue(float f) {
        return b(this.f15422a, f);
    }

    protected List<HwHealthBaseEntry> b(List<npf> list, float f) {
        int i;
        ArrayList arrayList = new ArrayList();
        int size = list.size() - 1;
        int i2 = 0;
        while (true) {
            if (i2 > size) {
                i = size;
                break;
            }
            int i3 = (size + i2) / 2;
            npf npfVar = list.get(i3);
            if (Math.abs(f - npfVar.b()) <= 0.001f) {
                int i4 = i3;
                while (i4 > 0 && Math.abs(list.get(i4 - 1).b() - f) <= 0.001f) {
                    i4--;
                }
                i = list.size();
                d(list, f, arrayList, i, i4);
            } else if (f > npfVar.b()) {
                i2 = i3 + 1;
            } else {
                size = i3 - 1;
            }
        }
        return i2 > i ? b(list, arrayList, i2, i, f) : arrayList;
    }

    private List<HwHealthBaseEntry> b(List<npf> list, List<HwHealthBaseEntry> list2, int i, int i2, float f) {
        boolean z;
        boolean f2 = this.d.f();
        boolean z2 = false;
        boolean z3 = (i < 0 || i > list.size() - 1) || (i2 < 0 || i2 > list.size() - 1);
        if (f2) {
            HwHealthBaseEntry e = e(list, i, i2, f);
            if (e == null) {
                return list2;
            }
            list2.add(e);
            return list2;
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.c;
        if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
            boolean z4 = ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE;
            z = ((HwHealthBaseScrollBarLineChart) this.c).acquireChartShowMode() == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_SCALE_MODE;
            z2 = z4;
        } else {
            z = false;
        }
        if (z2 || z) {
            HwHealthBaseEntry c = c(list, i, i2, f);
            if (c == null) {
                return list2;
            }
            list2.add(c);
            return list2;
        }
        if (z3) {
            return a(list, list2, f);
        }
        HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) list.get(i2).c();
        HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) list.get(i).c();
        if (Math.abs(hwHealthBaseEntry.getX() - f) <= Math.abs(hwHealthBaseEntry2.getX() - f)) {
            list2.add(hwHealthBaseEntry);
        } else {
            list2.add(hwHealthBaseEntry2);
        }
        return list2;
    }

    private List<HwHealthBaseEntry> a(List<npf> list, List<HwHealthBaseEntry> list2, float f) {
        if (koq.b(list2) && !koq.b(list)) {
            float d2 = d();
            if (d2 < 0.001f) {
                return list2;
            }
            float f2 = d2 / 2.0f;
            if (Math.abs(f - list.get(list.size() - 1).b()) <= f2) {
                Entry c = list.get(list.size() - 1).c();
                if (c instanceof HwHealthBaseEntry) {
                    list2.add((HwHealthBaseEntry) c);
                }
                return list2;
            }
            if (Math.abs(f - list.get(0).b()) <= f2) {
                Entry c2 = list.get(0).c();
                if (c2 instanceof HwHealthBaseEntry) {
                    list2.add((HwHealthBaseEntry) c2);
                }
            }
        }
        return list2;
    }

    private float d() {
        if (this.d != null) {
            return r0.d();
        }
        return 0.0f;
    }

    private void d(List<npf> list, float f, List<HwHealthBaseEntry> list2, int i, int i2) {
        while (i2 < i) {
            npf npfVar = list.get(i2);
            if (npfVar == null || Math.abs(npfVar.b() - f) > 0.001f || !(npfVar.c() instanceof HwHealthBaseEntry)) {
                return;
            }
            list2.add((HwHealthBaseEntry) npfVar.c());
            i2++;
        }
    }

    private HwHealthBaseEntry e(List<npf> list, int i, int i2, float f) {
        return e(list, i, i2, f, this.d.a() / 2.0f);
    }

    private HwHealthBaseEntry a(List<npf> list, int i, int i2, float f, float f2) {
        boolean z = true;
        boolean z2 = i < 0 || i > list.size() - 1;
        if (i2 >= 0 && i2 <= list.size() - 1) {
            z = false;
        }
        if (z2 && !z && (list.get(i2).c() instanceof HwHealthBaseEntry)) {
            HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) list.get(i2).c();
            if (Math.abs(hwHealthBaseEntry.getX() - f) >= f2) {
                return null;
            }
            return hwHealthBaseEntry;
        }
        if (z2 || !z || !(list.get(i).c() instanceof HwHealthBaseEntry)) {
            return null;
        }
        HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) list.get(i).c();
        if (Math.abs(hwHealthBaseEntry2.getX() - f) >= f2) {
            return null;
        }
        return hwHealthBaseEntry2;
    }

    private HwHealthBaseEntry e(List<npf> list, int i, int i2, float f, float f2) {
        boolean z = false;
        boolean z2 = i < 0 || i > list.size() - 1;
        boolean z3 = i2 < 0 || i2 > list.size() - 1;
        if (z2 || z3) {
            return a(list, i, i2, f, f2);
        }
        Entry c = list.get(i).c();
        Entry c2 = list.get(i2).c();
        int x = (int) c.getX();
        int x2 = (int) c2.getX();
        float abs = Math.abs(c2.getX() - f);
        float abs2 = Math.abs(c.getX() - f);
        int min = Math.min(i, i2);
        int max = Math.max(i, i2);
        if (list.get(min) == null || list.get(max) == null) {
            LogUtil.b("HwHealthLineDataContainer", "dataList.get(minPoint) is null or dataList.get(maxPoint) is null");
            return null;
        }
        if (list.get(min).g() && list.get(max).i()) {
            z = true;
        }
        if (x - x2 > f2 && abs >= f2 && abs2 >= f2 && !z) {
            return null;
        }
        if (!(list.get(i2).c() instanceof HwHealthBaseEntry) || !(list.get(i).c() instanceof HwHealthBaseEntry)) {
            LogUtil.b("HwHealthLineDataContainer", "!(dataList.get(high).getHookEntry() instanceof HwHealthBaseEntry)");
            return null;
        }
        HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) list.get(i2).c();
        HwHealthBaseEntry hwHealthBaseEntry2 = (HwHealthBaseEntry) list.get(i).c();
        return Math.abs(hwHealthBaseEntry.getX() - f) <= Math.abs(hwHealthBaseEntry2.getX() - f) ? hwHealthBaseEntry : hwHealthBaseEntry2;
    }

    private HwHealthBaseEntry b(List<npf> list, int i, int i2, float f) {
        HwHealthTransformer transformer = this.c.getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        float[] fArr = {0.0f, 0.0f, Utils.convertDpToPixel(6.0f), 0.0f};
        transformer.pixelsToValue(fArr);
        return e(list, i, i2, f, Math.abs(fArr[2] - fArr[0]));
    }

    private HwHealthBaseEntry c(List<npf> list, int i, int i2, float f) {
        HwHealthBaseEntry hwHealthBaseEntry;
        boolean z = true;
        boolean z2 = i < 0 || i > list.size() - 1;
        boolean z3 = i2 < 0 || i2 > list.size() - 1;
        if (!z2 && !z3) {
            z = false;
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.c;
        if (!(hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart)) {
            LogUtil.h("HwHealthLineDataContainer", "!(mChart instanceof HwHealthBaseScrollBarLineChart)");
            return null;
        }
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = ((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart).acquireScrollAdapter();
        ChartData data = this.c.getData();
        if ((acquireScrollAdapter instanceof nob) && data != null && data.getDataSets() != null && data.getDataSets().size() != 0 && (data.getDataSets().get(0) instanceof HwHealthBaseLineDataSet)) {
            return b(list, i, i2, f);
        }
        int i3 = (int) f;
        int acquireValuePresentRangeMin = acquireScrollAdapter.acquireValuePresentRangeMin(i3);
        int acquireValuePresentRangeMax = acquireScrollAdapter.acquireValuePresentRangeMax(i3);
        if (z) {
            if (!z2 && (list.get(i).c() instanceof HwHealthBaseEntry)) {
                hwHealthBaseEntry = (HwHealthBaseEntry) list.get(i).c();
            } else {
                hwHealthBaseEntry = (z3 || !(list.get(i2).c() instanceof HwHealthBaseEntry)) ? null : (HwHealthBaseEntry) list.get(i2).c();
            }
            if (hwHealthBaseEntry == null || hwHealthBaseEntry.getX() < acquireValuePresentRangeMin || hwHealthBaseEntry.getX() > acquireValuePresentRangeMax) {
                return null;
            }
            return hwHealthBaseEntry;
        }
        HwHealthBaseEntry hwHealthBaseEntry2 = list.get(i).c() instanceof HwHealthBaseEntry ? (HwHealthBaseEntry) list.get(i).c() : null;
        HwHealthBaseEntry hwHealthBaseEntry3 = list.get(i2).c() instanceof HwHealthBaseEntry ? (HwHealthBaseEntry) list.get(i2).c() : null;
        if (c(hwHealthBaseEntry2, acquireValuePresentRangeMin, acquireValuePresentRangeMax)) {
            return hwHealthBaseEntry2;
        }
        if (c(hwHealthBaseEntry3, acquireValuePresentRangeMin, acquireValuePresentRangeMax)) {
            return hwHealthBaseEntry3;
        }
        return null;
    }

    private boolean c(HwHealthBaseEntry hwHealthBaseEntry, int i, int i2) {
        return hwHealthBaseEntry != null && hwHealthBaseEntry.getX() >= ((float) i) && hwHealthBaseEntry.getX() <= ((float) i2);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer
    public HwHealthBaseEntry acquireNearestPoint(float f) {
        float highestVisibleX = this.c.getHighestVisibleX();
        float lowestVisibleX = this.c.getLowestVisibleX();
        float f2 = Float.MAX_VALUE;
        HwHealthBaseEntry hwHealthBaseEntry = null;
        for (npf npfVar : this.f15422a) {
            if (!(npfVar.c() instanceof HwHealthBaseEntry)) {
                return null;
            }
            float x = ((HwHealthBaseEntry) npfVar.c()).getX();
            if (d(highestVisibleX, lowestVisibleX, x)) {
                float f3 = x - f;
                if (Math.abs(f3) < f2) {
                    f2 = Math.abs(f3);
                    hwHealthBaseEntry = (HwHealthBaseEntry) npfVar.c();
                }
            }
        }
        return hwHealthBaseEntry;
    }

    protected void e(HwHealthLineDataSet hwHealthLineDataSet, List<npf> list) {
        int i = 0;
        while (i < list.size()) {
            int i2 = i - 1;
            npf npfVar = null;
            npf npfVar2 = (i2 < 0 || i2 >= list.size()) ? null : list.get(i2);
            int i3 = i + 1;
            if (i3 >= 0 && i3 < list.size()) {
                npfVar = list.get(i3);
            }
            npf npfVar3 = list.get(i);
            if (npfVar2 != null && npfVar3 != null) {
                npfVar3.d(hwHealthLineDataSet.b((int) npfVar2.b(), (int) npfVar3.b()));
            }
            if (npfVar != null && npfVar3 != null) {
                npfVar3.b(hwHealthLineDataSet.b((int) npfVar3.b(), (int) npfVar.b()));
            }
            i = i3;
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f15423a;
        private int c;
        private int e;

        public void b(BarLineScatterCandleBubbleDataProvider barLineScatterCandleBubbleDataProvider, HwHealthLineDataSet hwHealthLineDataSet) {
            if (hwHealthLineDataSet.getValues().size() == 0) {
                this.c = 0;
                this.f15423a = 0;
                this.e = 0;
            } else {
                this.c = 0;
                int size = hwHealthLineDataSet.getValues().size();
                this.f15423a = size;
                this.e = size - this.c;
            }
        }

        public int e() {
            return this.c;
        }

        public int b() {
            return this.f15423a;
        }

        public int d() {
            return this.e;
        }
    }
}
