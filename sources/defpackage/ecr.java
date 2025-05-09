package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.icommon.HwHealthLineDataProvider;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import defpackage.npa;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ecr extends npa {

    /* renamed from: a, reason: collision with root package name */
    protected float[] f11954a;
    protected float[] b;
    protected float[] c;
    protected float[] e;
    private edq g;
    private float k;
    private HwHealthBaseEntry l;
    private final int m;
    private float n;
    private final int o;
    private Paint q;
    private Paint r;
    private int s;

    private boolean b(boolean z, boolean z2) {
        return (z || z2) ? false : true;
    }

    public ecr(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.m = nrn.d(R$color.emui_color_fg_2);
        this.o = nrn.d(R$color.emuiColor10);
        this.b = new float[4];
        this.f11954a = new float[4];
        this.c = new float[4];
        this.e = new float[4];
        this.s = 1;
        this.q = new Paint();
        this.r = new Paint();
        this.k = Utils.convertDpToPixel(2.0f);
        this.n = Utils.convertDpToPixel(4.0f);
        b();
    }

    @Override // defpackage.npa
    protected void cDb_(float[] fArr, IHwHealthLineDataSet iHwHealthLineDataSet, Canvas canvas, MPPointF mPPointF, int i) {
        super.cDb_(fArr, iHwHealthLineDataSet, canvas, mPPointF, i);
    }

    private void b() {
        if (this.q == null) {
            this.q = new Paint();
        }
        if (this.r == null) {
            this.r = new Paint();
        }
        this.q.setAntiAlias(true);
        this.q.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.q.setStyle(Paint.Style.STROKE);
        this.r.setAntiAlias(true);
        this.r.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.r.setStyle(Paint.Style.STROKE);
        this.q.setColor(this.m);
        this.r.setColor(this.o);
    }

    @Override // defpackage.npa, defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        if (iHwHealthLineDataSet.isDataCalculated()) {
            int entryCount = iHwHealthLineDataSet.getEntryCount();
            int i = iHwHealthLineDataSet.isDrawSteppedEnabled() ? 4 : 2;
            HwHealthTransformer transformer = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt());
            this.mRenderPaint.setStyle(Paint.Style.STROKE);
            Canvas canvas2 = this.mBitmapCanvas;
            this.mXBounds.set(this.mChart, iHwHealthLineDataSet);
            c(entryCount, i);
            cCN_(iHwHealthLineDataSet, i, transformer, canvas2);
            this.mRenderPaint.setPathEffect(null);
        }
    }

    @Override // defpackage.nox
    protected void c(int i, int i2) {
        int i3 = i * i2;
        int max = Math.max(i3, i2) * 2;
        int max2 = Math.max(i3, i2) * 4;
        if (this.b.length < max) {
            this.b = new float[max2];
            this.f11954a = new float[max2];
        }
        if (this.c.length < max) {
            this.c = new float[max2];
            this.e = new float[max2];
        }
    }

    @Override // defpackage.nox
    protected void cCN_(IHwHealthLineDataSet iHwHealthLineDataSet, int i, Transformer transformer, Canvas canvas) {
        int i2;
        edq d;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (iHwHealthLineDataSet.getEntryForIndex(this.mXBounds.min) != 0) {
            float phaseY = this.mAnimator.getPhaseY();
            int i3 = this.mXBounds.min;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            while (i3 <= this.mXBounds.range + this.mXBounds.min) {
                Entry d2 = d(iHwHealthLineDataSet, i3);
                Entry entryForIndex = iHwHealthLineDataSet.getEntryForIndex(i3);
                edq d3 = d(entryForIndex);
                if (d3 == null) {
                    i2 = i3;
                } else {
                    Entry a2 = a(iHwHealthLineDataSet, i3);
                    i2 = i3;
                    int i10 = i6;
                    int i11 = i5;
                    arrayList.add(new PointF(entryForIndex.getX(), d3.b() * phaseY));
                    arrayList2.add(new PointF(entryForIndex.getX(), d3.c() * phaseY));
                    boolean b = b(iHwHealthLineDataSet, d2, entryForIndex);
                    if (b(b, a(iHwHealthLineDataSet, entryForIndex, a2))) {
                        this.c[i7] = entryForIndex.getX();
                        this.c[i7 + 1] = d3.b() * phaseY;
                        int i12 = i7 + 3;
                        this.c[i7 + 2] = entryForIndex.getX();
                        i7 += 4;
                        this.c[i12] = d3.b() * phaseY;
                        this.e[i8] = entryForIndex.getX();
                        this.e[i8 + 1] = d3.c() * phaseY;
                        int i13 = i8 + 3;
                        this.e[i8 + 2] = entryForIndex.getX();
                        i8 += 4;
                        this.e[i13] = d3.c() * phaseY;
                        i9++;
                    }
                    if (d2 == null || !b || (d = d(d2)) == null) {
                        i6 = i10;
                        i5 = i11;
                    } else {
                        this.b[i4] = d2.getX();
                        this.b[i4 + 1] = d.b() * phaseY;
                        this.b[i4 + 2] = entryForIndex.getX();
                        this.b[i4 + 3] = d3.b() * phaseY;
                        this.f11954a[i11] = d2.getX();
                        this.f11954a[i11 + 1] = d.c() * phaseY;
                        this.f11954a[i11 + 2] = entryForIndex.getX();
                        this.f11954a[i11 + 3] = d3.c() * phaseY;
                        i4 += 4;
                        i5 = i11 + 4;
                        i6 = i10 + 1;
                    }
                }
                i3 = i2 + 1;
            }
            afl_(i4, i5, i6, i, transformer, canvas, iHwHealthLineDataSet, i7, i8, i9, arrayList, arrayList2);
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry] */
    private Entry d(IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        if (i != 0) {
            return iHwHealthLineDataSet.getEntryForIndex(i - 1);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.github.mikephil.charting.data.Entry] */
    private Entry a(IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        if (i != this.mXBounds.range + this.mXBounds.min) {
            return iHwHealthLineDataSet.getEntryForIndex(i + 1);
        }
        return null;
    }

    private edq d(Entry entry) {
        edq edqVar;
        if (entry instanceof HwHealthBaseEntry) {
            HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) entry;
            if (!(hwHealthBaseEntry.getData() instanceof edq) || (edqVar = (edq) hwHealthBaseEntry.getData()) == null) {
                return null;
            }
            return edqVar;
        }
        return null;
    }

    private boolean b(IHwHealthLineDataSet iHwHealthLineDataSet, Entry entry, Entry entry2) {
        if (entry == null || !(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            return false;
        }
        return ((HwHealthLineDataSet) iHwHealthLineDataSet).b((int) entry.getX(), (int) entry2.getX());
    }

    private boolean a(IHwHealthLineDataSet iHwHealthLineDataSet, Entry entry, Entry entry2) {
        if (entry2 == null || !(iHwHealthLineDataSet instanceof HwHealthLineDataSet)) {
            return false;
        }
        return ((HwHealthLineDataSet) iHwHealthLineDataSet).b((int) entry.getX(), (int) entry2.getX());
    }

    private void afi_(int i, Transformer transformer, Canvas canvas, int[] iArr) {
        float[] fArr;
        Paint paint;
        int i2 = 0;
        int i3 = iArr[0];
        int i4 = 1;
        int i5 = iArr[1];
        if (iArr[2] == 1) {
            fArr = this.b;
            paint = this.q;
        } else {
            fArr = this.f11954a;
            paint = this.r;
        }
        if (i3 <= 0) {
            return;
        }
        transformer.pointValuesToPixel(fArr);
        int max = Math.max(i5 * i, i);
        Path path = new Path();
        path.moveTo(fArr[0], fArr[1]);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = f;
        while (true) {
            int i6 = (i2 * 2) + i4;
            if (i6 < max) {
                int i7 = i2 * 4;
                float f4 = fArr[i7];
                float f5 = fArr[i7 + i4];
                int i8 = i6 * 2;
                float f6 = fArr[i8];
                float f7 = fArr[i8 + i4];
                if (e(f3, f4) && e(f2, f5)) {
                    path.lineTo(f6, f7);
                } else {
                    int save = canvas.save();
                    LogUtil.c("SectionLineChartRender", "save is ：", Integer.valueOf(save));
                    canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f3, (int) this.mViewPortHandler.contentBottom());
                    canvas.drawPath(path, paint);
                    canvas.restoreToCount(save);
                    path.reset();
                    path.moveTo(f4, f5);
                    path.lineTo(f6, f7);
                    f = f4;
                }
                i2++;
                f2 = f7;
                f3 = f6;
                i4 = 1;
            } else {
                afk_(canvas, f, f3, path, paint);
                return;
            }
        }
    }

    protected void afn_(Canvas canvas, List<PointF> list, IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        npa.d dVar = new npa.d();
        dVar.d(i);
        super.cDa_(canvas, new Paint(), list, iHwHealthLineDataSet, dVar);
    }

    @Override // defpackage.npa
    protected void cDd_(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet, npa.d dVar) {
        for (int i = 0; i < dVar.a().length; i += 2) {
            dVar.cDf_().setAntiAlias(true);
            dVar.cDf_().setStyle(Paint.Style.FILL);
            cDe_(canvas, dVar, iHwHealthLineDataSet, i);
        }
    }

    @Override // defpackage.npa, defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        List<T> entriesForXValue;
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null || canvas == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = lineData.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0 && dataSets.size() == 1 && (entriesForXValue = ((IHwHealthLineDataSet) dataSets.get(0)).getEntriesForXValue(highlight.getX())) != 0 && entriesForXValue.size() != 0) {
                Entry entry = (Entry) entriesForXValue.get(0);
                if (entry instanceof HwHealthBaseEntry) {
                    HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) entry;
                    this.l = hwHealthBaseEntry;
                    if (hwHealthBaseEntry.getData() instanceof edq) {
                        this.g = (edq) this.l.getData();
                    }
                }
                float[] fArr = new float[3];
                if (this.g != null) {
                    fArr[0] = entry.getX();
                    fArr[1] = this.g.c();
                    fArr[2] = this.g.b();
                }
                afj_(canvas, fArr, (IHwHealthLineDataSet) dataSets.get(0), true, true);
            }
        }
    }

    private void afk_(Canvas canvas, float f, float f2, Path path, Paint paint) {
        int save = canvas.save();
        LogUtil.c("SectionLineChartRender", "save is ：", Integer.valueOf(save));
        canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
        canvas.drawPath(path, paint);
        canvas.restoreToCount(save);
        path.reset();
    }

    private void afl_(int i, int i2, int i3, int i4, Transformer transformer, Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet, int i5, int i6, int i7, List<PointF> list, List<PointF> list2) {
        afi_(i4, transformer, canvas, new int[]{i, i3, 1});
        afi_(i4, transformer, canvas, new int[]{i2, i3, 2});
        cCG_(canvas, transformer, iHwHealthLineDataSet, new int[]{i5, i7}, null);
        cCG_(canvas, transformer, iHwHealthLineDataSet, new int[]{i6, i7}, null);
        if (list.size() > 0) {
            afn_(canvas, list, iHwHealthLineDataSet, 1);
            afn_(canvas, list2, iHwHealthLineDataSet, 2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void afj_(android.graphics.Canvas r13, float[] r14, com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet r15, boolean r16, boolean r17) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ecr.afj_(android.graphics.Canvas, float[], com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet, boolean, boolean):void");
    }

    @Override // defpackage.npa
    protected void cDe_(Canvas canvas, npa.d dVar, IHwHealthLineDataSet iHwHealthLineDataSet, int i) {
        if (i < 0) {
            return;
        }
        float f = dVar.a()[i];
        float f2 = dVar.a()[i + 1];
        dVar.cDf_().setColor(nrn.d(R$color.chart_background_color));
        canvas.drawCircle(f, f2, this.n, dVar.cDf_());
        if (dVar.b() == 1) {
            dVar.cDf_().setColor(this.m);
        } else {
            dVar.cDf_().setColor(this.o);
        }
        canvas.drawCircle(f, f2, this.k, dVar.cDf_());
    }

    private void afm_(MPPointD mPPointD, MPPointD mPPointD2, Canvas canvas, float f, float f2, float f3) {
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(nrn.d(R$color.chart_background_color));
        float f4 = (float) mPPointD.y;
        float f5 = (float) mPPointD2.y;
        this.mHighlightPaint.setShadowLayer(8.0f, 0.0f, 6.0f, nrn.d(R$color.chart_shadow_color));
        canvas.drawCircle(f, f4, f2, this.mHighlightPaint);
        canvas.drawCircle(f, f5, f2, this.mHighlightPaint);
        this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.o);
        canvas.drawCircle(f, f4, f3, this.mHighlightPaint);
        this.mHighlightPaint.setColor(this.m);
        canvas.drawCircle(f, f5, f3, this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }
}
