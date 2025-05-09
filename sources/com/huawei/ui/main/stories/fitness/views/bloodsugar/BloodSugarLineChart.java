package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import com.github.mikephil.charting.components.XAxis;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChart;
import defpackage.nox;
import defpackage.nrr;
import defpackage.pyd;
import defpackage.pzd;
import defpackage.pzi;
import defpackage.pzw;

/* loaded from: classes6.dex */
public class BloodSugarLineChart extends InteractorLineChart {
    private String b;
    private final ViewTreeObserver.OnGlobalLayoutListener c;
    private int d;
    private int e;

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public pyd getHealthLineDataSet(HwHealthBaseBarLineData hwHealthBaseBarLineData) {
        return null;
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChart$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass4() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            BloodSugarLineChart.this.post(new Runnable() { // from class: pzh
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarLineChart.AnonymousClass4.this.c();
                }
            });
        }

        public /* synthetic */ void c() {
            if (BloodSugarLineChart.this.getWidth() > 0) {
                BloodSugarLineChart bloodSugarLineChart = BloodSugarLineChart.this;
                bloodSugarLineChart.e = bloodSugarLineChart.getWidth();
            }
            LogUtil.c("BloodSugarLineChart", "Blood sugar ViewTreeObserver width:", Integer.valueOf(BloodSugarLineChart.this.e));
            float f = BloodSugarLineChart.this.mContext.getResources().getDisplayMetrics().density;
            BloodSugarLineChart.this.d = (int) (r1.e / (f * 3.0f));
            BloodSugarLineChart bloodSugarLineChart2 = BloodSugarLineChart.this;
            bloodSugarLineChart2.reCalculate(bloodSugarLineChart2.d);
            BloodSugarLineChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(BloodSugarLineChart.this.c);
        }
    }

    public BloodSugarLineChart(Context context, DataInfos dataInfos) {
        super(context, dataInfos);
        this.d = 0;
        this.e = 0;
        this.c = new AnonymousClass4();
        LogUtil.c("BloodSugarLineChart", "construct chart");
        injectDataContainer(new HwHealthBaseBarLineChart.HwHealthDataContainerGenerator() { // from class: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChart.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            public boolean typeOf(Class cls) {
                Class<pzd> cls2 = pzd.class;
                while (true) {
                    if (cls2 == null) {
                        return false;
                    }
                    if (cls2.equals(cls)) {
                        return true;
                    }
                    for (Class<?> cls3 : cls2.getInterfaces()) {
                        if (cls3.equals(cls)) {
                            return true;
                        }
                    }
                    cls2 = cls2.getSuperclass();
                }
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart.HwHealthDataContainerGenerator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public pzd newDataContainer() {
                return new pzd(BloodSugarLineChart.this);
            }
        });
    }

    public BloodSugarLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.e = 0;
        this.c = new AnonymousClass4();
    }

    public BloodSugarLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        this.e = 0;
        this.c = new AnonymousClass4();
    }

    public void setShowDataType(String str) {
        if (this.mRenderer instanceof pzi) {
            ((pzi) this.mRenderer).a(str);
        }
        if (this.mAxisRendererFirstParty instanceof pzw) {
            ((pzw) this.mAxisRendererFirstParty).e(str);
        }
        if (this.mAxisRendererSecondParty instanceof pzw) {
            ((pzw) this.mAxisRendererSecondParty).e(str);
        }
        if (this.mAxisRendererThirdParty instanceof pzw) {
            ((pzw) this.mAxisRendererThirdParty).e(str);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        int width = getWidth();
        this.e = width;
        if (width == 0) {
            setVisibility(0);
            getViewTreeObserver().addOnGlobalLayoutListener(this.c);
            return;
        }
        int i = (int) (this.e / (this.mContext.getResources().getDisplayMetrics().density * 3.0f));
        this.d = i;
        if (i == 0) {
            LogUtil.h("BloodSugarLineChart", "mOneScreenShowCounts null,width not null");
        } else {
            reCalculate(i);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public float computeTipsCenterHeightPx() {
        super.computeTipsCenterHeightPx();
        if (this.mAxisFirstParty.mEntries.length > 2) {
            return (this.mAxisFirstParty.mEntries[1] + this.mAxisFirstParty.mEntries[2]) / 2.0f;
        }
        return 0.0f;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public nox getLineChartRender(Context context, DataInfos dataInfos) {
        return new pzi(this, this.mAnimator, this.mViewPortHandler, context, dataInfos);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initXAxis() {
        XAxis xAxis = getXAxis();
        xAxis.setValueFormatter(new InteractorLineChart.d());
        xAxis.enableGridDashedLine(nrr.e(this.mContext, 2.0f), nrr.e(this.mContext, 1.0f), 0.0f);
        xAxis.setAxisMinimum(0.0f);
        xAxis.setAxisMaximum(1440.0f);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initYRender() {
        this.mAxisRendererFirstParty = new pzw(this.mContext, this.mViewPortHandler, this.mAxisFirstParty, this.mFirstAxisTransformer, this);
        this.mAxisRendererSecondParty = new pzw(this.mContext, this.mViewPortHandler, this.mAxisSecondParty, this.mSecondAxisTransformer, this);
        this.mAxisRendererThirdParty = new pzw(this.mContext, this.mViewPortHandler, this.mAxisThirdParty, this.mThirdPartyAxisTransformer, this);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart
    public void initCursorMode() {
        setBackgroundColor(this.mContext.getResources().getColor(R.color._2131296853_res_0x7f090255));
        enableMarkerView(true);
        setMarkerSlidingMode(HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA);
        enableSpacePreserveForDataOverlay(true);
    }

    public void c() {
        if (this.mRenderer == null || !(this.mRenderer instanceof pzi)) {
            return;
        }
        ((pzi) this.mRenderer).a();
    }

    public void setBloodSugarLineChartInterface(BloodSugarLineChartInterface bloodSugarLineChartInterface) {
        if (this.mRenderer == null || !(this.mRenderer instanceof pzi)) {
            return;
        }
        ((pzi) this.mRenderer).b(bloodSugarLineChartInterface);
    }

    public void a() {
        if (this.mRenderer == null || !(this.mRenderer instanceof pzi)) {
            return;
        }
        ((pzi) this.mRenderer).b((BloodSugarLineChartInterface) null);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.InteractorLineChart, com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void setMarkerViewPosition(HwHealthBaseBarLineChart.a aVar) {
        if (aVar == null && (this.mRenderer instanceof pzi)) {
            float e = ((pzi) this.mRenderer).e();
            if (e >= 0.0f) {
                highlightValue(e, false);
                return;
            }
        }
        super.setMarkerViewPosition(aVar);
    }

    public void e() {
        if (this.mRenderer instanceof pzi) {
            ((pzi) this.mRenderer).b();
        }
    }

    public String getDataType() {
        return this.b;
    }

    public void setDataType(String str) {
        this.b = str;
    }
}
