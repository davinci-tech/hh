package com.huawei.ui.commonui.linechart.barchart;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import defpackage.koq;
import defpackage.nmz;
import defpackage.nna;
import defpackage.nnb;

/* loaded from: classes6.dex */
public class HwHealthBarChartBase extends HwHealthBaseScrollBarLineChart<nmz> implements HwHealthBarDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8853a;
    protected boolean b;
    private boolean c;
    private boolean d;

    public HwHealthBarChartBase(Context context) {
        super(context);
        this.b = false;
        this.d = true;
        this.c = false;
        this.f8853a = false;
    }

    public HwHealthBarChartBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.d = true;
        this.c = false;
        this.f8853a = false;
    }

    public HwHealthBarChartBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.d = true;
        this.c = false;
        this.f8853a = false;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.mRenderer = new nnb(this, this.mAnimator, this.mViewPortHandler);
        setHighlighter(new nna(this));
        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart
    public void calcMinMax() {
        if (this.f8853a) {
            this.mXAxis.calculate(((nmz) this.mData).getXMin() - (((nmz) this.mData).d() / 2.0f), ((nmz) this.mData).getXMax() + (((nmz) this.mData).d() / 2.0f));
        } else {
            this.mXAxis.calculate(((nmz) this.mData).getXMin(), ((nmz) this.mData).getXMax());
        }
        super.calcMinMax();
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawValueAboveBarEnabled() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isDrawBarShadowEnabled() {
        return this.c;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public boolean isHighlightFullBarEnabled() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider
    public nmz getBarData() {
        return (nmz) this.mData;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        for (T t : ((nmz) this.mData).getDataSets()) {
            if (t != null) {
                t.setValues(t.acquireEntryVals());
                t.makeDataCalculated(true);
            }
        }
        notifyDataSetChanged();
        invalidate();
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
    }

    public void b(boolean z, boolean z2) {
        if (this.mRenderer instanceof nnb) {
            ((nnb) this.mRenderer).d(z, z2);
        } else {
            LogUtil.h("HwHealthBarChartBase", "mRenderer is not HwHealthBarChartRenderer");
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public long queryMarkerViewTimeRangeMin() {
        long queryMarkerViewTimeMills = queryMarkerViewTimeMills();
        return koq.b(((nmz) this.mData).getDataSets()) ? queryMarkerViewTimeMills : ((HwHealthBaseBarDataSet) ((IHwHealthBarDataSet) r2.get(0))).acquireValuePresentRangeMin((int) (queryMarkerViewTimeMills / 60000)) * 60000;
    }
}
