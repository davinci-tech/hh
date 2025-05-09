package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.knit.section.chart.HwHealthPressureBarChart;
import com.huawei.health.knit.section.view.SectionLineChart;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class edc extends nox {

    /* renamed from: a, reason: collision with root package name */
    private int f11958a;
    private int b;
    private int c;
    private int e;
    private int g;
    private int h;
    private Path k;
    private int l;
    private int m;
    private DataInfos n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private Paint u;
    private int v;
    private int w;
    private int x;
    private int y;

    private int a(float f) {
        if (f >= 1.0f && f <= 29.0f) {
            return 1;
        }
        if (f <= 29.0f || f >= 60.0f) {
            return (f < 60.0f || f >= 80.0f) ? 4 : 3;
        }
        return 2;
    }

    public edc(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.s = 1;
        this.k = new Path();
        this.t = false;
        this.u = new Paint();
        this.p = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_4);
        this.q = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_3);
        this.o = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_2);
        this.g = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_1);
        this.e = BaseApplication.getContext().getResources().getColor(R$color.color_4de67e17);
        this.b = BaseApplication.getContext().getResources().getColor(R$color.color_4dffdf80);
        this.c = BaseApplication.getContext().getResources().getColor(R$color.color_4d67dbe6);
        this.f11958a = BaseApplication.getContext().getResources().getColor(R$color.color_4d3db6f2);
        this.v = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_4);
        this.w = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_3);
        this.x = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_2);
        this.y = BaseApplication.getContext().getResources().getColor(R$color.color_pressure_1);
        this.h = BaseApplication.getContext().getResources().getColor(R$color.health_pressureline_color);
        this.l = BaseApplication.getContext().getResources().getColor(R$color.color_26000000);
        this.r = 0;
        this.m = 0;
        b();
        this.n = dataInfos;
    }

    private void b() {
        this.u.setColor(this.h);
        this.u.setAntiAlias(true);
        this.u.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.u.setStyle(Paint.Style.STROKE);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.github.mikephil.charting.data.Entry] */
    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            int entryCount = iHwHealthLineDataSet.getEntryCount();
            int i = iHwHealthLineDataSet.isDrawSteppedEnabled() ? 4 : 2;
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            float phaseY = this.mAnimator.getPhaseY();
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            int i2 = entryCount * i;
            if (this.i.length < Math.max(i2, i) * 2) {
                this.i = new float[Math.max(i2, i) * 4];
            }
            if (this.f.length < Math.max(i2, i) * 2) {
                this.f = new float[Math.max(i2, i) * 4];
            }
            T entryForIndex = iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min);
            ArrayList arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            if (entryForIndex != 0) {
                for (int i3 = this.mXBounds.min; i3 <= this.mXBounds.range + this.mXBounds.min; i3++) {
                    ?? entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i3);
                    if (DataInfos.PressureDayDetail == this.n && entryForIndex2 != 0) {
                        arrayList2.add(new Entry(entryForIndex2.getX(), entryForIndex2.getY()));
                    }
                    if (entryForIndex2 != 0 && this.s != 0) {
                        arrayList.add(new PointF(entryForIndex2.getX(), entryForIndex2.getY() * phaseY));
                    }
                }
                afH_(i, transformer, canvas2, a(iHwHealthLineDataSet, phaseY, arrayList2));
                if (this.s == 0 || arrayList.size() <= 0) {
                    return;
                }
                if (DataInfos.PressureDayDetail == this.n) {
                    afG_(canvas2, arrayList, iHwHealthLineDataSet);
                } else {
                    afF_(canvas2, arrayList, iHwHealthLineDataSet);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet r9, float r10, java.util.List<com.github.mikephil.charting.data.Entry> r11) {
        /*
            r8 = this;
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r0 = r8.mXBounds
            int r0 = r0.min
            r1 = 0
            r2 = r1
        L6:
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r3 = r8.mXBounds
            int r3 = r3.range
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r4 = r8.mXBounds
            int r4 = r4.min
            int r3 = r3 + r4
            if (r0 > r3) goto L96
            r3 = 0
            if (r0 == 0) goto L32
            com.huawei.ui.commonui.linechart.common.DataInfos r4 = com.huawei.ui.commonui.linechart.common.DataInfos.PressureDayDetail
            com.huawei.ui.commonui.linechart.common.DataInfos r5 = r8.n
            if (r4 != r5) goto L2b
            int r4 = r11.size()
            int r5 = r0 + (-1)
            int r4 = r4 + (-1)
            if (r4 < r5) goto L32
            java.lang.Object r4 = r11.get(r5)
            com.github.mikephil.charting.data.Entry r4 = (com.github.mikephil.charting.data.Entry) r4
            goto L33
        L2b:
            int r4 = r0 + (-1)
            com.github.mikephil.charting.data.Entry r4 = r9.getEntryForIndex(r4)
            goto L33
        L32:
            r4 = r3
        L33:
            com.huawei.ui.commonui.linechart.common.DataInfos r5 = com.huawei.ui.commonui.linechart.common.DataInfos.PressureDayDetail
            com.huawei.ui.commonui.linechart.common.DataInfos r6 = r8.n
            if (r5 != r6) goto L48
            int r5 = r11.size()
            int r5 = r5 + (-1)
            if (r5 < r0) goto L4c
            java.lang.Object r3 = r11.get(r0)
            com.github.mikephil.charting.data.Entry r3 = (com.github.mikephil.charting.data.Entry) r3
            goto L4c
        L48:
            com.github.mikephil.charting.data.Entry r3 = r9.getEntryForIndex(r0)
        L4c:
            if (r3 != 0) goto L4f
            goto L92
        L4f:
            if (r4 != 0) goto L52
            goto L92
        L52:
            com.huawei.ui.commonui.linechart.common.DataInfos r5 = com.huawei.ui.commonui.linechart.common.DataInfos.PressureDayDetail
            com.huawei.ui.commonui.linechart.common.DataInfos r6 = r8.n
            if (r5 != r6) goto L66
            float r5 = r4.getX()
            float r6 = r3.getX()
            boolean r5 = r8.d(r5, r6)
            if (r5 == 0) goto L92
        L66:
            float[] r5 = r8.i
            int r6 = r2 + 1
            float r7 = r4.getX()
            r5[r2] = r7
            float[] r5 = r8.i
            int r7 = r2 + 2
            float r4 = r4.getY()
            float r4 = r4 * r10
            r5[r6] = r4
            float[] r4 = r8.i
            int r5 = r2 + 3
            float r6 = r3.getX()
            r4[r7] = r6
            float[] r4 = r8.i
            float r3 = r3.getY()
            float r3 = r3 * r10
            r4[r5] = r3
            int r1 = r1 + 1
            int r2 = r2 + 4
        L92:
            int r0 = r0 + 1
            goto L6
        L96:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.edc.a(com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet, float, java.util.List):int");
    }

    private void afH_(int i, Transformer transformer, Canvas canvas, int i2) {
        if (i2 <= 0) {
            return;
        }
        transformer.pointValuesToPixel(this.i);
        int max = Math.max(i2 * i, i);
        if (DataInfos.PressureDayDetail == this.n) {
            return;
        }
        Path path = new Path();
        int i3 = 0;
        path.moveTo(this.i[0], this.i[1]);
        float f = this.i[0];
        float f2 = this.i[0];
        float f3 = this.i[0];
        float f4 = this.i[1];
        while (true) {
            int i4 = (i3 * 2) + 1;
            if (i4 < max) {
                int i5 = i3 * 4;
                float f5 = this.i[i5];
                float f6 = this.i[i5 + 1];
                int i6 = i4 * 2;
                float f7 = this.i[i6];
                float f8 = this.i[i6 + 1];
                if (e(f3, f5) && e(f4, f6)) {
                    path.lineTo(f7, f8);
                } else {
                    canvas.save();
                    canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                    this.u.setShadowLayer(10.0f, 0.0f, 5.0f, this.l);
                    canvas.drawPath(path, this.u);
                    canvas.restore();
                    path.reset();
                    path.moveTo(f5, f6);
                    path.lineTo(f7, f8);
                    f = f5;
                }
                i3++;
                f4 = f8;
                f2 = f7;
                f3 = f2;
            } else {
                canvas.save();
                canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                this.u.setShadowLayer(10.0f, 0.0f, 5.0f, this.l);
                canvas.drawPath(path, this.u);
                canvas.restore();
                path.reset();
                return;
            }
        }
    }

    private boolean e(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-7f;
    }

    private void afG_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        int size = list.size() * 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[list.size()];
        int i = 0;
        int i2 = 0;
        for (PointF pointF : list) {
            int i3 = i + 1;
            fArr[i] = pointF.x;
            i += 2;
            fArr[i3] = pointF.y;
            fArr2[i2] = a(pointF.y);
            i2++;
        }
        this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
        ChartTouchHelper accessibilityHelper = ((HwHealthBaseBarLineChart) this.mChart).getAccessibilityHelper();
        if (accessibilityHelper != null) {
            accessibilityHelper.e().d();
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < size) {
            float f = fArr[i4];
            float f2 = fArr[i4 + 1];
            int e = nrr.e(BaseApplication.getContext(), 2.0f);
            afI_(paint, fArr2[i5]);
            paint.setAlpha(153);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            float f3 = e;
            canvas.drawCircle(f, f2, f3, paint);
            canvas.drawRect(f - f3, f2, f + f3, this.mViewPortHandler.contentBottom(), paint);
            if (accessibilityHelper != null) {
                AbstractTouchNode a2 = accessibilityHelper.e().a(i5);
                int i6 = (int) f;
                a2.setRect(new Rect(i6 - e, (int) f2, i6 + e, (int) this.mViewPortHandler.contentBottom()));
                a2.setDescription(a(iHwHealthLineDataSet, list.get(i5).x));
            }
            i4 += 2;
            i5++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String a(IHwHealthLineDataSet iHwHealthLineDataSet, float f) {
        String formattedValue;
        HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) iHwHealthLineDataSet.getEntryForXValue(f, 0.0f);
        SectionLineChart section = ((HwHealthPressureBarChart) this.mChart).getSection();
        if (section == null || hwHealthBaseEntry == null) {
            LogUtil.h("PressureLineChartRender", "section ", section, " entry", hwHealthBaseEntry);
            return "";
        }
        IAxisValueFormatter valueFormatter = ((HwHealthBaseScrollBarLineChart) this.mChart).getXAxis().getValueFormatter();
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            formattedValue = ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(hwHealthBaseEntry.getX(), this.mChart.getXAxis());
        } else {
            formattedValue = valueFormatter.getFormattedValue(hwHealthBaseEntry.getX(), this.mChart.getXAxis());
        }
        String[] strArr = {section.getThirdLayerText(), section.d(hwHealthBaseEntry), section.c(hwHealthBaseEntry)};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            if (!TextUtils.isEmpty(str)) {
                formattedValue = nsf.b(R$string.IDS_two_parts, formattedValue, str);
            }
        }
        return formattedValue;
    }

    private void afI_(Paint paint, float f) {
        if (f == 1.0f) {
            paint.setColor(this.g);
            return;
        }
        if (f == 2.0f) {
            paint.setColor(this.o);
            return;
        }
        if (f == 3.0f) {
            paint.setColor(this.q);
        } else if (f == 4.0f) {
            paint.setColor(this.p);
        } else {
            LogUtil.a("PressureLineChartRender", "grade no match");
        }
    }

    private void afF_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Paint paint = new Paint();
        boolean z = true;
        paint.setAntiAlias(true);
        float f = 4.0f;
        float convertDpToPixel = Utils.convertDpToPixel(4.0f);
        if (this.s == 1) {
            paint.setStyle(Paint.Style.STROKE);
            int size = list.size() * 2;
            float[] fArr = new float[size];
            float[] fArr2 = new float[list.size()];
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (PointF pointF : list) {
                int i4 = i2 + 1;
                fArr[i2] = pointF.x;
                i2 += 2;
                fArr[i4] = pointF.y;
                fArr2[i3] = a(pointF.y);
                i3++;
            }
            this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
            ChartTouchHelper accessibilityHelper = ((HwHealthBaseBarLineChart) this.mChart).getAccessibilityHelper();
            if (accessibilityHelper != null) {
                accessibilityHelper.e().d();
            }
            int i5 = 0;
            while (i < size) {
                float f2 = fArr2[i5];
                if (f2 == 1.0f) {
                    paint.setColor(this.g);
                } else if (f2 == 2.0f) {
                    paint.setColor(this.o);
                } else if (f2 == 3.0f) {
                    paint.setColor(this.q);
                } else if (f2 == f) {
                    paint.setColor(this.p);
                } else {
                    LogUtil.a("PressureLineChartRender", "no grade match!");
                }
                paint.setAntiAlias(z);
                paint.setStyle(Paint.Style.FILL);
                float f3 = fArr[i];
                float f4 = fArr[i + 1];
                canvas.drawCircle(f3, f4, convertDpToPixel, paint);
                paint.setColor(-1);
                canvas.drawCircle(f3, f4, convertDpToPixel / 2.0f, paint);
                if (iHwHealthLineDataSet.isVisible() && accessibilityHelper != null) {
                    AbstractTouchNode a2 = accessibilityHelper.e().a(i5);
                    a2.setRect(new Rect((int) (f3 - convertDpToPixel), (int) (f4 - convertDpToPixel), (int) (f3 + convertDpToPixel), (int) (f4 + convertDpToPixel)));
                    a2.setDescription(a(iHwHealthLineDataSet, list.get(i5).x));
                }
                i += 2;
                i5++;
                z = true;
                f = 4.0f;
            }
        }
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.k.reset();
            this.k.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.k.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.k, this.mHighlightPaint);
        }
        if (z2) {
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStrokeWidth(1.0f);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(6.0f), this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(this.r);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(4.0f), this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.m);
        float e = nrr.e(BaseApplication.getContext(), 2.0f);
        canvas.drawCircle(f, f2, e, this.mHighlightPaint);
        canvas.drawRect(f - e, f2, f + e, this.mViewPortHandler.contentBottom(), this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = lineData.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0) {
                int argb = Color.argb(0, 0, 0, 0);
                if (dataSets.size() == 1) {
                    List<T> entriesForXValue = ((IHwHealthLineDataSet) dataSets.get(0)).getEntriesForXValue(highlight.getX());
                    if (entriesForXValue != 0 && entriesForXValue.size() != 0) {
                        Entry entry = (Entry) entriesForXValue.get(0);
                        d(entry);
                        if (this.t && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                            entry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                        }
                        MPPointD pixelForValues = this.mChart.getTransformer(((IHwHealthLineDataSet) dataSets.get(0)).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                        highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                        if (DataInfos.PressureDayDetail == this.n) {
                            dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, (ILineScatterCandleRadarDataSet) dataSets.get(0), true, false, argb);
                        } else {
                            dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, (ILineScatterCandleRadarDataSet) dataSets.get(0), true, true, argb);
                        }
                    }
                } else {
                    for (T t : dataSets) {
                        List<T> entriesForXValue2 = t.getEntriesForXValue(highlight.getX());
                        if (entriesForXValue2 != 0 && entriesForXValue2.size() != 0) {
                            Entry entry2 = (Entry) entriesForXValue2.get(0);
                            MPPointD pixelForValues2 = this.mChart.getTransformer(t.getAxisDependencyExt()).getPixelForValues(entry2.getX(), entry2.getY() * this.mAnimator.getPhaseY());
                            highlight.setDraw((float) pixelForValues2.x, (float) pixelForValues2.y);
                            if (DataInfos.PressureDayDetail == this.n) {
                                dwf_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, t, false, false, argb);
                            } else {
                                dwf_(canvas, (float) pixelForValues2.x, (float) pixelForValues2.y, t, false, true, argb);
                            }
                        }
                    }
                }
            }
        }
    }

    private void d(Entry entry) {
        int a2 = a(entry.getY());
        if (a2 == 1) {
            this.r = this.g;
            this.m = this.y;
            return;
        }
        if (a2 == 2) {
            this.r = this.o;
            this.m = this.x;
        } else if (a2 == 3) {
            this.r = this.q;
            this.m = this.w;
        } else if (a2 == 4) {
            this.r = this.p;
            this.m = this.v;
        } else {
            LogUtil.a("PressureLineChartRender", "high light grade no match");
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        this.t = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.t;
    }

    private boolean d(float f, float f2) {
        Date date = new Date(((long) f) * 60000);
        Date date2 = new Date(((long) f2) * 60000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(6);
        calendar.setTime(date2);
        return calendar.get(6) == i;
    }
}
