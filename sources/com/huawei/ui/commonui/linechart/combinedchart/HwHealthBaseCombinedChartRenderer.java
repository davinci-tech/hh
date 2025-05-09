package com.huawei.ui.commonui.linechart.combinedchart;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import defpackage.nnb;
import defpackage.nov;
import defpackage.npd;
import defpackage.nsn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public final class HwHealthBaseCombinedChartRenderer extends DataRenderer {

    /* renamed from: a, reason: collision with root package name */
    private DrawAction f8856a;
    protected WeakReference<Chart> b;
    private boolean c;
    protected List<DataRenderer> d;
    protected List<Highlight> e;

    interface DrawAction {
        void doDraw(DataRenderer dataRenderer, Canvas canvas);
    }

    public HwHealthBaseCombinedChartRenderer(HwHealthBaseCombinedChart hwHealthBaseCombinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.d = new ArrayList(3);
        this.e = new ArrayList(10);
        this.c = false;
        this.f8856a = new DrawAction() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChartRenderer.3
            @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChartRenderer.DrawAction
            public void doDraw(DataRenderer dataRenderer, Canvas canvas) {
                if (dataRenderer == null || canvas == null) {
                    return;
                }
                dataRenderer.drawData(canvas);
            }
        };
        this.b = new WeakReference<>(hwHealthBaseCombinedChart);
        e();
    }

    public void e() {
        HwHealthBaseCombinedChart hwHealthBaseCombinedChart;
        HwHealthBaseCombinedChart.DrawOrder[] drawOrder;
        this.d.clear();
        Chart chart = this.b.get();
        if (!(chart instanceof HwHealthBaseCombinedChart) || (drawOrder = (hwHealthBaseCombinedChart = (HwHealthBaseCombinedChart) chart).getDrawOrder()) == null || drawOrder.length == 0) {
            return;
        }
        for (HwHealthBaseCombinedChart.DrawOrder drawOrder2 : drawOrder) {
            int i = AnonymousClass1.c[drawOrder2.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    if (hwHealthBaseCombinedChart.getLineData() != null) {
                        this.d.add(new nov(hwHealthBaseCombinedChart, this.mAnimator, this.mViewPortHandler));
                    }
                } else if (i == 3 && hwHealthBaseCombinedChart.getPointData() != null) {
                    this.d.add(new npd(hwHealthBaseCombinedChart, this.mAnimator, this.mViewPortHandler, hwHealthBaseCombinedChart.getContext()));
                }
            } else if (hwHealthBaseCombinedChart.getBarData() != null) {
                this.d.add(new nnb(hwHealthBaseCombinedChart, this.mAnimator, this.mViewPortHandler));
            }
        }
    }

    /* renamed from: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChartRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HwHealthBaseCombinedChart.DrawOrder.values().length];
            c = iArr;
            try {
                iArr[HwHealthBaseCombinedChart.DrawOrder.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HwHealthBaseCombinedChart.DrawOrder.LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HwHealthBaseCombinedChart.DrawOrder.POINT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        Iterator<DataRenderer> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().initBuffers();
        }
    }

    private List<DataRenderer> b() {
        ArrayList arrayList = new ArrayList(this.d.size());
        for (Object obj : this.d) {
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
    private void cBz_(DrawAction drawAction, Canvas canvas) {
        List<DataRenderer> b = b();
        if (b.size() > 3) {
            throw new RuntimeException("combined chart not support More than 3 renders draw");
        }
        boolean z = true;
        for (DataRenderer dataRenderer : b) {
            if (z && this.c && b.size() != 1 && (dataRenderer instanceof IHwHealthDataRender)) {
                IHwHealthDataRender iHwHealthDataRender = (IHwHealthDataRender) dataRenderer;
                iHwHealthDataRender.usePaintAsBackground(true);
                drawAction.doDraw(dataRenderer, canvas);
                iHwHealthDataRender.usePaintAsBackground(false);
            } else {
                drawAction.doDraw(dataRenderer, canvas);
            }
            z = false;
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        cBz_(this.f8856a, canvas);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        Iterator<DataRenderer> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().drawValues(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        Iterator<DataRenderer> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().drawExtras(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, final Highlight[] highlightArr) {
        final Chart chart = this.b.get();
        if (chart == null) {
            return;
        }
        cBz_(new DrawAction() { // from class: com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChartRenderer.2
            @Override // com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChartRenderer.DrawAction
            public void doDraw(DataRenderer dataRenderer, Canvas canvas2) {
                if (dataRenderer == null || canvas2 == null) {
                    return;
                }
                nsn.cKW_(dataRenderer, canvas2, chart, -1, HwHealthBaseCombinedChartRenderer.this.e, highlightArr);
            }
        }, canvas);
    }
}
