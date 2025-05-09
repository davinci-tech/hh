package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes9.dex */
public class pux extends nox {

    /* renamed from: a, reason: collision with root package name */
    private int f16271a;
    private int b;
    private int c;
    private int e;
    private int g;
    private int h;
    private int k;
    private int l;
    private DataInfos m;
    private Path n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private int t;
    private int u;
    private int v;
    private int w;
    private Paint x;
    private int y;

    private int e(float f) {
        if (f >= 1.0f && f <= 29.0f) {
            return 1;
        }
        if (f <= 29.0f || f >= 60.0f) {
            return (f < 60.0f || f >= 80.0f) ? 4 : 3;
        }
        return 2;
    }

    public pux(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.p = 1;
        this.n = new Path();
        this.s = false;
        this.x = new Paint();
        this.q = BaseApplication.getContext().getResources().getColor(R.color._2131296850_res_0x7f090252);
        this.t = BaseApplication.getContext().getResources().getColor(R.color._2131296863_res_0x7f09025f);
        this.k = BaseApplication.getContext().getResources().getColor(R.color._2131296829_res_0x7f09023d);
        this.g = BaseApplication.getContext().getResources().getColor(R.color._2131296826_res_0x7f09023a);
        this.e = BaseApplication.getContext().getResources().getColor(R.color._2131296772_res_0x7f090204);
        this.c = BaseApplication.getContext().getResources().getColor(R.color._2131296773_res_0x7f090205);
        this.f16271a = BaseApplication.getContext().getResources().getColor(R.color._2131296771_res_0x7f090203);
        this.b = BaseApplication.getContext().getResources().getColor(R.color._2131296770_res_0x7f090202);
        this.w = BaseApplication.getContext().getResources().getColor(R.color._2131296842_res_0x7f09024a);
        this.y = BaseApplication.getContext().getResources().getColor(R.color._2131299385_res_0x7f090c39);
        this.v = BaseApplication.getContext().getResources().getColor(R.color._2131296828_res_0x7f09023c);
        this.u = BaseApplication.getContext().getResources().getColor(R.color._2131296825_res_0x7f090239);
        this.h = BaseApplication.getContext().getResources().getColor(R.color._2131297865_res_0x7f090649);
        this.l = BaseApplication.getContext().getResources().getColor(R.color._2131296759_res_0x7f0901f7);
        this.r = 0;
        this.o = 0;
        a();
        this.m = dataInfos;
    }

    private void a() {
        this.x.setColor(this.h);
        this.x.setAntiAlias(true);
        this.x.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.x.setStyle(Paint.Style.STROKE);
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
                    if (DataInfos.PressureDayDetail == this.m && entryForIndex2 != 0) {
                        arrayList2.add(new Entry(entryForIndex2.getX(), entryForIndex2.getY()));
                    }
                    if (entryForIndex2 != 0 && this.p != 0) {
                        arrayList.add(new PointF(entryForIndex2.getX(), entryForIndex2.getY() * phaseY));
                    }
                }
                dtM_(i, transformer, canvas2, c(iHwHealthLineDataSet, phaseY, arrayList2));
                if (this.p == 0 || arrayList.size() <= 0) {
                    return;
                }
                if (DataInfos.PressureDayDetail == this.m) {
                    dtL_(canvas2, arrayList, iHwHealthLineDataSet);
                } else {
                    dtK_(canvas2, arrayList, iHwHealthLineDataSet);
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
    private int c(com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet r9, float r10, java.util.List<com.github.mikephil.charting.data.Entry> r11) {
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
            com.huawei.ui.commonui.linechart.common.DataInfos r5 = r8.m
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
            com.huawei.ui.commonui.linechart.common.DataInfos r6 = r8.m
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
            com.huawei.ui.commonui.linechart.common.DataInfos r6 = r8.m
            if (r5 != r6) goto L66
            float r5 = r4.getX()
            float r6 = r3.getX()
            boolean r5 = r8.c(r5, r6)
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pux.c(com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet, float, java.util.List):int");
    }

    private void dtM_(int i, Transformer transformer, Canvas canvas, int i2) {
        if (i2 <= 0) {
            return;
        }
        transformer.pointValuesToPixel(this.i);
        int max = Math.max(i2 * i, i);
        if (DataInfos.PressureDayDetail == this.m) {
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
                if (d(f3, f5) && d(f4, f6)) {
                    path.lineTo(f7, f8);
                } else {
                    canvas.save();
                    canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                    this.x.setShadowLayer(10.0f, 0.0f, 5.0f, this.l);
                    canvas.drawPath(path, this.x);
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
                this.x.setShadowLayer(10.0f, 0.0f, 5.0f, this.l);
                canvas.drawPath(path, this.x);
                canvas.restore();
                path.reset();
                return;
            }
        }
    }

    private boolean d(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-7f;
    }

    private void dtL_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
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
            fArr2[i2] = e(pointF.y);
            i2++;
        }
        this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
        int i4 = 0;
        int i5 = 0;
        while (i4 < size) {
            float f = fArr[i4];
            float f2 = fArr[i4 + 1];
            int[] dtN_ = dtN_(paint, fArr2[i5]);
            float e = nrr.e(BaseApplication.getContext(), 0.75f);
            canvas.drawCircle(f, f2, e, paint);
            float f3 = f - e;
            float f4 = f + e;
            paint2.setShader(new LinearGradient(f3, f2, f4, this.mViewPortHandler.contentBottom(), dtN_, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
            canvas.drawRect(f3, f2, f4, this.mViewPortHandler.contentBottom(), paint2);
            i4 += 2;
            i5++;
        }
    }

    private int[] dtN_(Paint paint, float f) {
        int[] iArr = new int[2];
        if (f == 1.0f) {
            int i = this.g;
            iArr[0] = i;
            iArr[1] = this.b;
            paint.setColor(i);
        } else if (f == 2.0f) {
            int i2 = this.k;
            iArr[0] = i2;
            iArr[1] = this.f16271a;
            paint.setColor(i2);
        } else if (f == 3.0f) {
            int i3 = this.t;
            iArr[0] = i3;
            iArr[1] = this.c;
            paint.setColor(i3);
        } else if (f == 4.0f) {
            int i4 = this.q;
            iArr[0] = i4;
            iArr[1] = this.e;
            paint.setColor(i4);
        } else {
            LogUtil.a("PressureLineChartRender", "grade no match");
        }
        return iArr;
    }

    private void dtK_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float convertDpToPixel = Utils.convertDpToPixel(4.0f);
        if (this.p == 1) {
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
                fArr2[i3] = e(pointF.y);
                i3++;
            }
            this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
            int i5 = 0;
            while (i < size) {
                float f = fArr2[i5];
                if (f == 1.0f) {
                    paint.setColor(this.g);
                } else if (f == 2.0f) {
                    paint.setColor(this.k);
                } else if (f == 3.0f) {
                    paint.setColor(this.t);
                } else if (f == 4.0f) {
                    paint.setColor(this.q);
                } else {
                    LogUtil.a("PressureLineChartRender", "no grade match!");
                }
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                float f2 = fArr[i];
                float f3 = fArr[i + 1];
                canvas.drawCircle(f2, f3, convertDpToPixel, paint);
                paint.setColor(-1);
                canvas.drawCircle(f2, f3, convertDpToPixel / 2.0f, paint);
                i += 2;
                i5++;
            }
        }
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.n.reset();
            this.n.moveTo(f, ((MarkerView) ((Chart) this.mChart).getMarker()).getHeight());
            this.n.lineTo(f, this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
            canvas.drawPath(this.n, this.mHighlightPaint);
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
        this.mHighlightPaint.setColor(this.o);
        float e = nrr.e(BaseApplication.getContext(), 0.75f);
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
                        e(entry);
                        if (this.s && ((HwHealthBaseBarLineChart) this.mChart).isManualReferenceLineEnabled()) {
                            entry = new HwHealthBaseEntry(highlight.getX(), ((HwHealthBaseBarLineChart) this.mChart).getManualReferenceLineValue());
                        }
                        MPPointD pixelForValues = this.mChart.getTransformer(((IHwHealthLineDataSet) dataSets.get(0)).getAxisDependencyExt()).getPixelForValues(entry.getX(), entry.getY() * this.mAnimator.getPhaseY());
                        highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                        if (DataInfos.PressureDayDetail == this.m) {
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
                            if (DataInfos.PressureDayDetail == this.m) {
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

    private void e(Entry entry) {
        int e = e(entry.getY());
        if (e == 1) {
            this.r = this.g;
            this.o = this.u;
            return;
        }
        if (e == 2) {
            this.r = this.k;
            this.o = this.v;
        } else if (e == 3) {
            this.r = this.t;
            this.o = this.y;
        } else if (e == 4) {
            this.r = this.q;
            this.o = this.w;
        } else {
            LogUtil.a("PressureLineChartRender", "high light grade no match");
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        this.s = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.s;
    }

    private boolean c(float f, float f2) {
        Date date = new Date(((long) f) * 60000);
        Date date2 = new Date(((long) f2) * 60000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(6);
        calendar.setTime(date2);
        return calendar.get(6) == i;
    }
}
