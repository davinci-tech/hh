package defpackage;

import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class nnd extends HwHealthBaseBarLineData<HwHealthBaseBarLineDataSet<HwHealthBaseEntry>> {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthBaseCombinedChart.DrawOrder[] f15397a = {HwHealthBaseCombinedChart.DrawOrder.BAR, HwHealthBaseCombinedChart.DrawOrder.LINE};
    private List<IHwHealthBarDataSet> b = new ArrayList();
    private now c;
    private nmz d;
    private noz e;

    public void d(now nowVar) {
        this.c = nowVar;
        notifyDataChanged();
    }

    public void b(noz nozVar) {
        this.e = nozVar;
        notifyDataChanged();
    }

    public void c(nmz nmzVar) {
        this.d = nmzVar;
        notifyDataChanged();
    }

    public HwHealthBaseCombinedChart.DrawOrder[] i() {
        return (HwHealthBaseCombinedChart.DrawOrder[]) this.f15397a.clone();
    }

    public void e(HwHealthBaseCombinedChart.DrawOrder[] drawOrderArr) {
        if (drawOrderArr == null || drawOrderArr.length == 0) {
            return;
        }
        this.f15397a = (HwHealthBaseCombinedChart.DrawOrder[]) drawOrderArr.clone();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData, com.github.mikephil.charting.data.ChartData
    public void calcMinMax() {
        if (this.mDataSets == null) {
            this.mDataSets = new ArrayList(10);
        }
        this.mDataSets.clear();
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        this.mFirstPartyAxisMax = -3.4028235E38f;
        this.mFirstPartyAxisMin = Float.MAX_VALUE;
        this.mSecondPartyAxisMax = -3.4028235E38f;
        this.mSecondPartyAxisMin = Float.MAX_VALUE;
        this.mThirdPartyAxisMax = -3.4028235E38f;
        this.mThirdPartyAxisMin = Float.MAX_VALUE;
        b();
    }

    public void c() {
        List<HwHealthBaseBarLineData> d = d();
        this.mDataSets.clear();
        for (HwHealthBaseBarLineData hwHealthBaseBarLineData : d) {
            if (hwHealthBaseBarLineData != null) {
                this.mDataSets.addAll(hwHealthBaseBarLineData.getDataSets());
            }
        }
    }

    public now f() {
        return this.c;
    }

    public now h() {
        return this.e;
    }

    public void a() {
        this.c.getDataSets().clear();
        notifyDataChanged();
    }

    public void e() {
        this.e.getDataSets().clear();
        notifyDataChanged();
    }

    public nmz j() {
        return this.d;
    }

    public void g() {
        this.b.addAll(this.d.getDataSets());
        this.d.getDataSets().clear();
        notifyDataChanged();
    }

    public void l() {
        List<IHwHealthBarDataSet> list = this.b;
        if (list == null || list.size() == 0) {
            return;
        }
        this.d.getDataSets().addAll(this.b);
        notifyDataChanged();
    }

    public void a(HwHealthBarDataSet hwHealthBarDataSet) {
        this.d.getDataSets().remove(hwHealthBarDataSet);
        notifyDataChanged();
    }

    public List<HwHealthBaseBarLineData> d() {
        noz nozVar;
        ArrayList arrayList = new ArrayList(2);
        for (HwHealthBaseCombinedChart.DrawOrder drawOrder : this.f15397a) {
            int i = AnonymousClass1.d[drawOrder.ordinal()];
            if (i == 1) {
                nmz nmzVar = this.d;
                if (nmzVar != null) {
                    arrayList.add(nmzVar);
                }
            } else if (i == 2) {
                now nowVar = this.c;
                if (nowVar != null) {
                    arrayList.add(nowVar);
                }
            } else if (i == 3 && (nozVar = this.e) != null) {
                arrayList.add(nozVar);
            }
        }
        return arrayList;
    }

    /* renamed from: nnd$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HwHealthBaseCombinedChart.DrawOrder.values().length];
            d = iArr;
            try {
                iArr[HwHealthBaseCombinedChart.DrawOrder.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HwHealthBaseCombinedChart.DrawOrder.LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HwHealthBaseCombinedChart.DrawOrder.POINT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public void notifyDataChanged() {
        now nowVar = this.c;
        if (nowVar != null) {
            nowVar.notifyDataChanged();
        }
        nmz nmzVar = this.d;
        if (nmzVar != null) {
            nmzVar.notifyDataChanged();
        }
        noz nozVar = this.e;
        if (nozVar != null) {
            nozVar.notifyDataChanged();
        }
        calcMinMax();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData, com.github.mikephil.charting.data.ChartData
    public Entry getEntryForHighlight(Highlight highlight) {
        BarLineScatterCandleBubbleData d;
        if (highlight.getDataIndex() >= d().size() || (d = d(highlight.getDataIndex())) == null || highlight.getDataSetIndex() >= d.getDataSetCount()) {
            return null;
        }
        List<Entry> entriesForXValue = d.getDataSetByIndex(highlight.getDataSetIndex()).getEntriesForXValue(highlight.getX());
        if (entriesForXValue == null || entriesForXValue.isEmpty()) {
            LogUtil.b("HealthChart_HwHealthCombinedData", "getEntryForHighlight() entries is null!");
            return null;
        }
        for (Entry entry : entriesForXValue) {
            if (entry.getY() == highlight.getY() || Float.isNaN(highlight.getY())) {
                return entry;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData
    public Entry getEntryForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        List entriesForXValue;
        HwEntrys hwEntrys = new HwEntrys();
        for (T t : this.mDataSets) {
            if (t instanceof IHwHealthLineDataSet) {
                IHwHealthDatasContainer cacheDataContainer = t.cacheDataContainer(hwHealthBaseBarLineChart);
                entriesForXValue = cacheDataContainer instanceof IHwHealthLineDatasContainer ? ((IHwHealthLineDatasContainer) cacheDataContainer).searchEntryForXValue(f) : null;
            } else if (t instanceof IHwHealthBarDataSet) {
                if (hwHealthBaseBarLineChart instanceof HwHealthBarDataProvider) {
                    entriesForXValue = new ArrayList(10);
                    entriesForXValue.addAll(((IHwHealthBarDataSet) t).getEntriesForXValue(f, (HwHealthBarDataProvider) hwHealthBaseBarLineChart));
                } else {
                    entriesForXValue = t.getEntriesForXValue(f);
                }
            } else {
                entriesForXValue = t.getEntriesForXValue(f);
            }
            if (entriesForXValue == null || entriesForXValue.size() == 0) {
                if (t instanceof HwHealthBarDataSet) {
                    hwEntrys.add(new HwEntrys.HwDataEntry(null, (HwHealthBarDataSet) t));
                } else if (t instanceof HwHealthLineDataSet) {
                    hwEntrys.add(new HwEntrys.HwDataEntry(null, (HwHealthLineDataSet) t));
                }
            } else if (t instanceof HwHealthBarDataSet) {
                hwEntrys.add(new HwEntrys.HwDataEntry((HwHealthBaseEntry) entriesForXValue.get(0), (HwHealthBarDataSet) t));
            } else if (t instanceof HwHealthLineDataSet) {
                hwEntrys.add(new HwEntrys.HwDataEntry((HwHealthBaseEntry) entriesForXValue.get(0), (HwHealthLineDataSet) t));
            }
        }
        return hwEntrys;
    }

    public BarLineScatterCandleBubbleData d(int i) {
        List<HwHealthBaseBarLineData> d = d();
        if (i < 0 || i >= d.size()) {
            return null;
        }
        return d.get(i);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public boolean removeDataSet(HwHealthBaseBarLineDataSet<HwHealthBaseEntry> hwHealthBaseBarLineDataSet) {
        Iterator<HwHealthBaseBarLineData> it = d().iterator();
        boolean z = false;
        while (it.hasNext() && !(z = it.next().removeDataSet((HwHealthBaseBarLineData) hwHealthBaseBarLineDataSet))) {
        }
        notifyDataChanged();
        return z;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeDataSet(int i) {
        LogUtil.b("HealthChart_HwHealthCombinedData", "removeDataSet(int index) not supported for CombinedData");
        return false;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeEntry(Entry entry, int i) {
        LogUtil.b("HealthChart_HwHealthCombinedData", "removeEntry(...) not supported for CombinedData");
        return false;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public boolean removeEntry(float f, int i) {
        LogUtil.b("HealthChart_HwHealthCombinedData", "removeEntry(...) not supported for CombinedData");
        return false;
    }

    public void b() {
        for (HwHealthBaseBarLineData hwHealthBaseBarLineData : d()) {
            hwHealthBaseBarLineData.calcMinMax();
            this.mDataSets.addAll(hwHealthBaseBarLineData.getDataSets());
            if (hwHealthBaseBarLineData.getYMax() > this.mYMax) {
                this.mYMax = hwHealthBaseBarLineData.getYMax();
            }
            if (hwHealthBaseBarLineData.getYMin() < this.mYMin) {
                this.mYMin = hwHealthBaseBarLineData.getYMin();
            }
            if (hwHealthBaseBarLineData.getXMax() > this.mXMax) {
                this.mXMax = hwHealthBaseBarLineData.getXMax();
            }
            if (hwHealthBaseBarLineData.getXMin() < this.mXMin) {
                this.mXMin = hwHealthBaseBarLineData.getXMin();
            }
            if (hwHealthBaseBarLineData.acquireFirstPartyAxisMax() > this.mFirstPartyAxisMax) {
                this.mFirstPartyAxisMax = hwHealthBaseBarLineData.acquireFirstPartyAxisMax();
            }
            if (hwHealthBaseBarLineData.acquireFirstPartyAxisMin() < this.mFirstPartyAxisMin) {
                this.mFirstPartyAxisMin = hwHealthBaseBarLineData.acquireFirstPartyAxisMin();
            }
            if (hwHealthBaseBarLineData.acquireSecondPartyAxisMax() > this.mSecondPartyAxisMax) {
                this.mSecondPartyAxisMax = hwHealthBaseBarLineData.acquireSecondPartyAxisMax();
            }
            if (hwHealthBaseBarLineData.acquireSecondPartyAxisMin() < this.mSecondPartyAxisMin) {
                this.mSecondPartyAxisMin = hwHealthBaseBarLineData.acquireSecondPartyAxisMin();
            }
            if (hwHealthBaseBarLineData.acquireThirdPartyAxisMax() > this.mThirdPartyAxisMax) {
                this.mThirdPartyAxisMax = hwHealthBaseBarLineData.acquireThirdPartyAxisMax();
            }
            if (hwHealthBaseBarLineData.acquireThirdPartyAxisMin() < this.mThirdPartyAxisMin) {
                this.mThirdPartyAxisMin = hwHealthBaseBarLineData.acquireThirdPartyAxisMin();
            }
        }
    }
}
