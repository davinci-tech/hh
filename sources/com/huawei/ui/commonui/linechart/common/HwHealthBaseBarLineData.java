package com.huawei.ui.commonui.linechart.common;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseBarLineData<T extends IHwHealthBarLineDataSet<? extends HwHealthBaseEntry>> extends BarLineScatterCandleBubbleData<T> {
    private static final float MIN_ACCURACY_FROM_ZERO = 1.0E-7f;
    private static final String TAG = "HwHealthBaseBarLineData";
    protected float mFirstPartyAxisMax;
    protected float mFirstPartyAxisMin;
    protected float mSecondPartyAxisMax;
    protected float mSecondPartyAxisMin;
    protected float mThirdPartyAxisMax;
    protected float mThirdPartyAxisMin;

    @Override // com.github.mikephil.charting.data.ChartData
    public abstract Entry getEntryForHighlight(Highlight highlight);

    public abstract Entry getEntryForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart);

    public float getNearestValueForXValue(float f, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, HwHealthBaseBarLineChart.MarkerViewSlidingMode markerViewSlidingMode) {
        return f;
    }

    public HwHealthBaseBarLineData() {
        this.mFirstPartyAxisMax = -3.4028235E38f;
        this.mFirstPartyAxisMin = Float.MAX_VALUE;
        this.mSecondPartyAxisMax = -3.4028235E38f;
        this.mSecondPartyAxisMin = Float.MAX_VALUE;
        this.mThirdPartyAxisMax = -3.4028235E38f;
        this.mThirdPartyAxisMin = Float.MAX_VALUE;
    }

    public HwHealthBaseBarLineData(T... tArr) {
        super(tArr);
        this.mFirstPartyAxisMax = -3.4028235E38f;
        this.mFirstPartyAxisMin = Float.MAX_VALUE;
        this.mSecondPartyAxisMax = -3.4028235E38f;
        this.mSecondPartyAxisMin = Float.MAX_VALUE;
        this.mThirdPartyAxisMax = -3.4028235E38f;
        this.mThirdPartyAxisMin = Float.MAX_VALUE;
    }

    public HwHealthBaseBarLineData(List<T> list) {
        super(list);
        this.mFirstPartyAxisMax = -3.4028235E38f;
        this.mFirstPartyAxisMin = Float.MAX_VALUE;
        this.mSecondPartyAxisMax = -3.4028235E38f;
        this.mSecondPartyAxisMin = Float.MAX_VALUE;
        this.mThirdPartyAxisMax = -3.4028235E38f;
        this.mThirdPartyAxisMin = Float.MAX_VALUE;
    }

    public float getYMin(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            if (Math.abs(this.mFirstPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mFirstPartyAxisMin;
            }
            if (Math.abs(this.mSecondPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mSecondPartyAxisMin;
            }
            return this.mThirdPartyAxisMin;
        }
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            if (Math.abs(this.mSecondPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mSecondPartyAxisMin;
            }
            if (Math.abs(this.mFirstPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mFirstPartyAxisMin;
            }
            return this.mThirdPartyAxisMin;
        }
        if (Math.abs(this.mThirdPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
            return this.mThirdPartyAxisMin;
        }
        if (Math.abs(this.mFirstPartyAxisMin - Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
            return this.mFirstPartyAxisMin;
        }
        return this.mSecondPartyAxisMin;
    }

    public float getYMax(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            if (Math.abs(this.mFirstPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mFirstPartyAxisMax;
            }
            if (Math.abs(this.mSecondPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mSecondPartyAxisMax;
            }
            return this.mThirdPartyAxisMax;
        }
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            if (Math.abs(this.mSecondPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mSecondPartyAxisMax;
            }
            if (Math.abs(this.mFirstPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
                return this.mFirstPartyAxisMax;
            }
            return this.mThirdPartyAxisMax;
        }
        if (Math.abs(this.mThirdPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
            return this.mThirdPartyAxisMax;
        }
        if (Math.abs(this.mFirstPartyAxisMax + Float.MAX_VALUE) > MIN_ACCURACY_FROM_ZERO) {
            return this.mFirstPartyAxisMax;
        }
        return this.mSecondPartyAxisMax;
    }

    @Override // com.github.mikephil.charting.data.ChartData
    @Deprecated
    public void calcMinMax(Entry entry, YAxis.AxisDependency axisDependency) {
        throw new RuntimeException("HwHealthLineData calcMinMax Deprecated");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.data.ChartData
    public void calcMinMax() {
        if (this.mDataSets == null) {
            return;
        }
        this.mYMax = -3.4028235E38f;
        this.mYMin = Float.MAX_VALUE;
        this.mXMax = -3.4028235E38f;
        this.mXMin = Float.MAX_VALUE;
        Iterator it = this.mDataSets.iterator();
        while (it.hasNext()) {
            calcMinMax((HwHealthBaseBarLineData<T>) it.next());
        }
        this.mFirstPartyAxisMax = -3.4028235E38f;
        this.mFirstPartyAxisMin = Float.MAX_VALUE;
        this.mSecondPartyAxisMax = -3.4028235E38f;
        this.mSecondPartyAxisMin = Float.MAX_VALUE;
        IHwHealthBarLineDataSet firstParty = getFirstParty(this.mDataSets);
        if (firstParty != null) {
            this.mFirstPartyAxisMax = firstParty.getYMax();
            this.mFirstPartyAxisMin = firstParty.getYMin();
            for (T t : this.mDataSets) {
                if (t != null) {
                    getFirstPartyAxis(t);
                }
            }
        }
        IHwHealthBarLineDataSet secondParty = getSecondParty(this.mDataSets);
        if (secondParty != null) {
            this.mSecondPartyAxisMax = secondParty.getYMax();
            this.mSecondPartyAxisMin = secondParty.getYMin();
            for (T t2 : this.mDataSets) {
                if (t2 != null) {
                    getSecondPartyAxis(t2);
                }
            }
        }
        IHwHealthBarLineDataSet thirdParty = getThirdParty(this.mDataSets);
        if (thirdParty != null) {
            this.mThirdPartyAxisMax = thirdParty.getYMax();
            this.mThirdPartyAxisMin = thirdParty.getYMin();
            for (T t3 : this.mDataSets) {
                if (t3 != null) {
                    getThirdPartyAxis(t3);
                }
            }
        }
    }

    private void calcMinMax(Entry entry, HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        if (this.mYMax < entry.getY()) {
            this.mYMax = entry.getY();
        }
        if (this.mYMin > entry.getY()) {
            this.mYMin = entry.getY();
        }
        if (this.mXMax < entry.getX()) {
            this.mXMax = entry.getX();
        }
        if (this.mXMin > entry.getX()) {
            this.mXMin = entry.getX();
        }
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            if (this.mFirstPartyAxisMax < entry.getY()) {
                this.mFirstPartyAxisMax = entry.getY();
            }
            if (this.mFirstPartyAxisMin > entry.getY()) {
                this.mFirstPartyAxisMin = entry.getY();
                return;
            }
            return;
        }
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            if (this.mSecondPartyAxisMax < entry.getY()) {
                this.mSecondPartyAxisMax = entry.getY();
            }
            if (this.mSecondPartyAxisMin > entry.getY()) {
                this.mSecondPartyAxisMin = entry.getY();
                return;
            }
            return;
        }
        if (this.mThirdPartyAxisMax < entry.getY()) {
            this.mThirdPartyAxisMax = entry.getY();
        }
        if (this.mThirdPartyAxisMin > entry.getY()) {
            this.mThirdPartyAxisMin = entry.getY();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.data.ChartData
    public void calcMinMax(T t) {
        if (t == null) {
            LogUtil.b(TAG, "calcMinMax data == null");
            return;
        }
        if (this.mYMax < t.getYMax()) {
            this.mYMax = t.getYMax();
        }
        if (this.mYMin > t.getYMin()) {
            this.mYMin = t.getYMin();
        }
        if (this.mXMax < t.getXMax()) {
            this.mXMax = t.getXMax();
        }
        if (this.mXMin > t.getXMin()) {
            this.mXMin = t.getXMin();
        }
        if (t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            if (this.mFirstPartyAxisMax < t.getYMax()) {
                this.mFirstPartyAxisMax = t.getYMax();
            }
            if (this.mFirstPartyAxisMin > t.getYMin()) {
                this.mFirstPartyAxisMin = t.getYMin();
                return;
            }
            return;
        }
        if (t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            if (this.mSecondPartyAxisMax < t.getYMax()) {
                this.mSecondPartyAxisMax = t.getYMax();
            }
            if (this.mSecondPartyAxisMin > t.getYMin()) {
                this.mSecondPartyAxisMin = t.getYMin();
                return;
            }
            return;
        }
        if (this.mThirdPartyAxisMax < t.getYMax()) {
            this.mThirdPartyAxisMax = t.getYMax();
        }
        if (this.mThirdPartyAxisMin > t.getYMin()) {
            this.mThirdPartyAxisMin = t.getYMin();
        }
    }

    private void getFirstPartyAxis(T t) {
        if (t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            if (t.getYMin() < this.mFirstPartyAxisMin) {
                this.mFirstPartyAxisMin = t.getYMin();
            }
            if (t.getYMax() > this.mFirstPartyAxisMax) {
                this.mFirstPartyAxisMax = t.getYMax();
            }
        }
    }

    private void getSecondPartyAxis(T t) {
        if (t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            if (t.getYMin() < this.mSecondPartyAxisMin) {
                this.mSecondPartyAxisMin = t.getYMin();
            }
            if (t.getYMax() > this.mSecondPartyAxisMax) {
                this.mSecondPartyAxisMax = t.getYMax();
            }
        }
    }

    private void getThirdPartyAxis(T t) {
        if (t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
            if (t.getYMin() < this.mThirdPartyAxisMin) {
                this.mThirdPartyAxisMin = t.getYMin();
            }
            if (t.getYMax() > this.mThirdPartyAxisMax) {
                this.mThirdPartyAxisMax = t.getYMax();
            }
        }
    }

    public float acquireFirstPartyAxisMax() {
        return getYMax(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
    }

    public float acquireFirstPartyAxisMin() {
        return getYMin(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
    }

    public float acquireSecondPartyAxisMax() {
        return getYMax(HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
    }

    public float acquireSecondPartyAxisMin() {
        return getYMin(HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY);
    }

    public float acquireThirdPartyAxisMax() {
        return getYMax(HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY);
    }

    public float acquireThirdPartyAxisMin() {
        return getYMin(HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY);
    }

    private T getThirdParty(List<T> list) {
        for (T t : list) {
            if (t != null && t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.THIRD_PARTY) {
                return t;
            }
        }
        return null;
    }

    private T getFirstParty(List<T> list) {
        for (T t : list) {
            if (t != null && t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
                return t;
            }
        }
        return null;
    }

    private T getSecondParty(List<T> list) {
        for (T t : list) {
            if (t != null && t.getAxisDependencyExt() == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
                return t;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T getDataSet(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            return (T) getFirstParty(this.mDataSets);
        }
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            return (T) getSecondParty(this.mDataSets);
        }
        return (T) getThirdParty(this.mDataSets);
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public void addEntry(Entry entry, int i) {
        if (this.mDataSets.size() > i && i >= 0) {
            IDataSet iDataSet = (IDataSet) this.mDataSets.get(i);
            if (iDataSet.addEntry(entry) && (iDataSet instanceof IHwHealthBarLineDataSet)) {
                calcMinMax(entry, ((IHwHealthBarLineDataSet) iDataSet).getAxisDependencyExt());
                return;
            }
            return;
        }
        LogUtil.b("addEntry", "Cannot add Entry because dataSetIndex too high or too low.");
    }

    @Override // com.github.mikephil.charting.data.ChartData
    public float getXMax() {
        if (this.mDataSets == null || this.mDataSets.isEmpty()) {
            return this.mXMax;
        }
        float f = this.mXMax;
        for (T t : this.mDataSets) {
            if (t != null && t.isXMaxForced() && t.getXMaxForcedValue() > f) {
                f = t.getXMaxForcedValue();
            }
        }
        return f;
    }
}
