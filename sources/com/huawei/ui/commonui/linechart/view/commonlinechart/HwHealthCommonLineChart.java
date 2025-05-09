package com.huawei.ui.commonui.linechart.view.commonlinechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import defpackage.nmv;
import defpackage.now;
import defpackage.npa;
import defpackage.npe;
import java.text.DecimalFormat;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthCommonLineChart extends HwHealthLineChart {

    /* renamed from: a, reason: collision with root package name */
    protected Context f8901a;

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public int getAnimateTime() {
        return 100;
    }

    public HwHealthCommonLineChart(Context context) {
        super(context);
        this.f8901a = null;
        LogUtil.c("HwHealthCommonLineChart", "construct chart");
        this.f8901a = context;
        this.mRenderer = new npa(this, this.mAnimator, this.mViewPortHandler, context);
        c();
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void clipDataArea(Canvas canvas) {
        RectF rectF = new RectF(this.mViewPortHandler.getContentRect());
        rectF.top -= this.mXAxis.getYOffset();
        rectF.bottom += this.mXAxis.getYOffset();
        rectF.left -= Utils.convertDpToPixel(5.0f);
        rectF.right += Utils.convertDpToPixel(5.0f);
        canvas.clipRect(rectF);
    }

    public void a() {
        acquireLayout().l(Utils.convertDpToPixel(20.0f)).a(Utils.convertDpToPixel(36.0f)).j(Utils.convertDpToPixel(8.0f));
    }

    private void c() {
        this.mLegend.setEnabled(false);
        getDescription().setEnabled(false);
        a();
        XAxis xAxis = getXAxis();
        xAxis.setValueFormatter(new c());
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(1440.0f);
        getXAxis().setAvoidFirstLastClipping(false);
        getAxisFirstParty().setValueFormatter(new nmv());
        b();
        setBackgroundColor(Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
        if (this.mMarker instanceof HwHealthMarkerView) {
            ((HwHealthMarkerView) this.mMarker).d(false);
        }
    }

    protected void b() {
        this.mAxisRendererFirstParty = new npe(this.f8901a, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new npe(this.f8901a, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new npe(this.f8901a, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart
    public void resetYaxisAnimateValue() {
        this.mAxisRendererFirstParty.i();
    }

    public static class c implements IAxisValueFormatter {
        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            return decimalFormat.format(((int) f) / 60) + ":" + decimalFormat.format(r5 - (r0 * 60));
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void setMarkerViewPosition(HwHealthBaseBarLineChart.a aVar) {
        this.mMarkerViewPosition = aVar;
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        LogUtil.c("HwHealthCommonLineChart", "refresh chart");
        super.refresh();
    }

    private boolean d() {
        List<T> dataSets = ((now) this.mData).getDataSets();
        if (dataSets == 0 || dataSets.size() == 0) {
            return true;
        }
        LogUtil.a("HwHealthCommonLineChart", "fillOriginalData mLineData size = ", Integer.valueOf(dataSets.size()));
        return false;
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart
    public void customAxisShow() {
        if (!d()) {
            super.customAxisShow();
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(false);
        this.mAxisThirdParty.setEnabled(false);
        this.mAxisFirstParty.setDrawLabels(true);
        this.mAxisSecondParty.setDrawLabels(false);
        this.mAxisThirdParty.setDrawLabels(false);
    }
}
