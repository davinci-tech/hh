package com.huawei.ui.commonui.linechart.combinedchart;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import defpackage.nnb;
import defpackage.nov;
import defpackage.nsn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public final class HwHealthCombinedChartRenderer extends DataRenderer {

    /* renamed from: a, reason: collision with root package name */
    protected WeakReference<Chart> f8858a;
    protected List<DataRenderer> b;
    protected List<Highlight> d;
    private DrawAction e;

    interface DrawAction {
        void doDraw(DataRenderer dataRenderer, Canvas canvas);
    }

    public HwHealthCombinedChartRenderer(HwHealthCombinedChart hwHealthCombinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.b = new ArrayList(5);
        this.d = new ArrayList();
        this.e = new DrawAction() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChartRenderer.2
            @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChartRenderer.DrawAction
            public void doDraw(DataRenderer dataRenderer, Canvas canvas) {
                dataRenderer.drawData(canvas);
            }
        };
        this.f8858a = new WeakReference<>(hwHealthCombinedChart);
        c();
    }

    public void c() {
        HwHealthCombinedChart hwHealthCombinedChart;
        HwHealthCombinedChart.DrawOrder[] drawOrder;
        this.b.clear();
        Chart chart = this.f8858a.get();
        if (!(chart instanceof HwHealthCombinedChart) || (drawOrder = (hwHealthCombinedChart = (HwHealthCombinedChart) chart).getDrawOrder()) == null || drawOrder.length == 0) {
            return;
        }
        for (HwHealthCombinedChart.DrawOrder drawOrder2 : drawOrder) {
            int i = AnonymousClass5.e[drawOrder2.ordinal()];
            if (i == 1) {
                if (hwHealthCombinedChart.getBarData() != null) {
                    this.b.add(new nnb(hwHealthCombinedChart, this.mAnimator, this.mViewPortHandler));
                }
            } else if (i == 2 && hwHealthCombinedChart.getLineData() != null) {
                nov novVar = new nov(hwHealthCombinedChart, this.mAnimator, this.mViewPortHandler);
                novVar.a(1);
                this.b.add(novVar);
            }
        }
    }

    /* renamed from: com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChartRenderer$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HwHealthCombinedChart.DrawOrder.values().length];
            e = iArr;
            try {
                iArr[HwHealthCombinedChart.DrawOrder.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HwHealthCombinedChart.DrawOrder.LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        for (DataRenderer dataRenderer : this.b) {
            if (dataRenderer != null) {
                dataRenderer.initBuffers();
            }
        }
    }

    private List<DataRenderer> a() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.b) {
            if (!(obj instanceof IHwHealthDataRender)) {
                throw new RuntimeException("only support huawei chart render,interface specified!!!");
            }
            if (((IHwHealthDataRender) obj).hasData()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cBA_(DrawAction drawAction, Canvas canvas) {
        List<DataRenderer> a2 = a();
        if (a2.size() == 0) {
            return;
        }
        if (a2.size() == 1) {
            drawAction.doDraw(a2.get(0), canvas);
            return;
        }
        if (a2.size() > 2) {
            throw new RuntimeException("combined chart not support More than 2 renders draw");
        }
        DataRenderer dataRenderer = a2.get(0);
        if (!(dataRenderer instanceof IHwHealthDataRender)) {
            LogUtil.b("HealthChart_HwHealthCombinedChartRenderer", "Can not transform to IHwHealthDataRender.");
            return;
        }
        IHwHealthDataRender iHwHealthDataRender = (IHwHealthDataRender) dataRenderer;
        iHwHealthDataRender.usePaintAsBackground(true);
        drawAction.doDraw(dataRenderer, canvas);
        iHwHealthDataRender.usePaintAsBackground(false);
        Iterator<DataRenderer> it = this.b.iterator();
        while (it.hasNext()) {
            drawAction.doDraw(it.next(), canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        cBA_(this.e, canvas);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        Iterator<DataRenderer> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().drawValues(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        Iterator<DataRenderer> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().drawExtras(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, final Highlight[] highlightArr) {
        final Chart chart = this.f8858a.get();
        if (chart == null) {
            return;
        }
        cBA_(new DrawAction() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChartRenderer.3
            @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChartRenderer.DrawAction
            public void doDraw(DataRenderer dataRenderer, Canvas canvas2) {
                nsn.cKW_(dataRenderer, canvas2, chart, -1, HwHealthCombinedChartRenderer.this.d, highlightArr);
            }
        }, canvas);
    }
}
