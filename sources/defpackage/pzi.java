package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.util.Pair;
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
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthBaseLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pzi extends nox {

    /* renamed from: a, reason: collision with root package name */
    private BloodSugarLineChartInterface f16354a;
    private long aa;
    private float ab;
    private float ac;
    private final int ad;
    private int ae;
    private Entry af;
    private final List<Pair> ag;
    private long ah;
    private final int ai;
    private final Paint aj;
    private long ak;
    private final int al;
    private final int am;
    private final int b;
    private final int c;
    private final Paint e;
    private Entry g;
    private final int h;
    private final DataInfos k;
    private final Map<Long, IStorageModel> l;
    private final int m;
    private Entry n;
    private String o;
    private Entry p;
    private Entry q;
    private final int r;
    private final int s;
    private final int t;
    private final Paint u;
    private int v;
    private boolean w;
    private final int x;
    private final Path y;
    private Entry z;

    public pzi(HwHealthLineDataProvider hwHealthLineDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Context context, DataInfos dataInfos) {
        super(hwHealthLineDataProvider, chartAnimator, viewPortHandler, context);
        this.c = BaseApplication.getContext().getResources().getColor(R.color._2131296795_res_0x7f09021b);
        this.b = BaseApplication.getContext().getResources().getColor(R.color._2131296797_res_0x7f09021d);
        this.h = BaseApplication.getContext().getResources().getColor(R.color._2131296799_res_0x7f09021f);
        this.ai = BaseApplication.getContext().getResources().getColor(R.color._2131296795_res_0x7f09021b);
        this.al = BaseApplication.getContext().getResources().getColor(R.color._2131296799_res_0x7f09021f);
        this.am = BaseApplication.getContext().getResources().getColor(R.color._2131296797_res_0x7f09021d);
        this.m = BaseApplication.getContext().getResources().getColor(R.color._2131298145_res_0x7f090761);
        this.x = BaseApplication.getContext().getResources().getColor(R.color._2131296759_res_0x7f0901f7);
        this.t = BaseApplication.getContext().getResources().getColor(R.color._2131296535_res_0x7f090117);
        this.r = Color.argb(127, 252, 49, 89);
        this.s = Color.argb(0, 252, 49, 89);
        this.ad = Color.argb(255, 252, 49, 89);
        this.u = new Paint(1);
        this.y = new Path();
        this.aj = new Paint();
        this.e = new Paint(1);
        this.ag = new ArrayList(3);
        this.ae = 0;
        this.v = 0;
        this.ak = 180000L;
        this.w = false;
        this.ab = -1.0f;
        this.ac = -1.0f;
        this.l = new HashMap(16);
        LogUtil.a("BloodSugarLineChartRender", "BloodOxygenLineChartRender");
        j();
        this.k = dataInfos;
        if (dataInfos != null) {
            if (dataInfos.isDayData()) {
                this.ak = 180000L;
                this.aa = 2100000L;
            } else if (dataInfos.isWeekData()) {
                this.ak = 600000L;
                this.aa = 7000000L;
            } else {
                this.ak = 3600000L;
                this.aa = 42000000L;
            }
        }
    }

    private void j() {
        this.aj.setColor(this.m);
        this.aj.setAntiAlias(true);
        this.aj.setStrokeWidth(Utils.convertDpToPixel(2.0f));
        this.aj.setStyle(Paint.Style.STROKE);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeCap(Paint.Cap.SQUARE);
        this.e.setStrokeJoin(Paint.Join.ROUND);
        this.e.setColor(this.ad);
        this.e.setStrokeWidth(2.0f);
        this.u.setStyle(Paint.Style.STROKE);
        this.u.setStrokeCap(Paint.Cap.ROUND);
        this.u.setColor(this.ad);
        this.u.setStrokeWidth(2.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal
    public void drawLinear(Canvas canvas, IHwHealthLineDataSet iHwHealthLineDataSet) {
        pza pzaVar;
        List list;
        Entry entry;
        int i;
        Entry entry2;
        float f;
        long k;
        pza pzaVar2 = iHwHealthLineDataSet instanceof pza ? (pza) iHwHealthLineDataSet : null;
        if (pzaVar2 == null || !pzaVar2.isDataCalculated()) {
            return;
        }
        List values = pzaVar2.getValues();
        if (koq.b(values)) {
            return;
        }
        Collections.sort(values);
        h();
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        ArrayList arrayList3 = new ArrayList(16);
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        d(pzaVar2);
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        long j2 = 0;
        Entry entry3 = pzaVar2.getEntryForIndex(this.mXBounds.min);
        Entry entry4 = pzaVar2.getEntryForIndex(this.mXBounds.range + this.mXBounds.min);
        while (true) {
            float f3 = f2;
            pzaVar = pzaVar2;
            if (i2 >= values.size()) {
                break;
            }
            HwHealthBaseEntry hwHealthBaseEntry = (HwHealthBaseEntry) values.get(i2);
            if (entry3.getX() > hwHealthBaseEntry.getX() || hwHealthBaseEntry.getX() > entry4.getX() || !(hwHealthBaseEntry.getData() instanceof pzy)) {
                list = values;
            } else {
                pzy pzyVar = (pzy) hwHealthBaseEntry.getData();
                list = values;
                if (this.o.equals("BLOOD_SUGAR_CONTINUE") || this.o.equals("BLOOD_SUGAR_FINGER_TIP") || this.o.equals(pzyVar.c())) {
                    this.l.put(Long.valueOf(pzyVar.k()), pzyVar);
                    int o = pzyVar.o();
                    if (o == 2108) {
                        if (!pzyVar.f()) {
                            npf npfVar = new npf(hwHealthBaseEntry);
                            if (j2 == 0) {
                                arrayList4.add(npfVar);
                                j2 = pzyVar.k();
                                i = i2;
                            } else {
                                i = i2;
                                if (pzyVar.k() - j2 < this.aa) {
                                    npfVar.d(true);
                                    arrayList4.add(npfVar);
                                    k = pzyVar.k();
                                } else {
                                    int size = arrayList4.size() - 1;
                                    if (!arrayList4.get(size).i()) {
                                        npf remove = arrayList4.remove(size);
                                        arrayList5.add(new PointF(remove.b(), remove.d()));
                                    }
                                    arrayList4.add(npfVar);
                                    k = pzyVar.k();
                                }
                                j2 = k;
                            }
                            entry = entry3;
                            entry2 = entry4;
                            f2 = f3;
                        }
                    } else {
                        i = i2;
                        long j3 = j;
                        if (pzyVar.k() - j > this.ak) {
                            j = pzyVar.k();
                            f = hwHealthBaseEntry.getX();
                        } else {
                            f = f3;
                            j = j3;
                        }
                        hwHealthBaseEntry.setX(f);
                        entry = entry3;
                        if (this.k != DataInfos.BloodSugarDayDetail && !"BLOOD_SUGAR_FINGER_TIP".equals(pzyVar.c())) {
                            arrayList3.add(new Entry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY()));
                        }
                        entry2 = entry4;
                        arrayList.add(new PointF(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY() * this.mAnimator.getPhaseY()));
                        arrayList2.add(Integer.valueOf(pzyVar.h()));
                        if (this.k == DataInfos.BloodSugarDayDetail) {
                            c(hwHealthBaseEntry, o);
                        }
                        f2 = f;
                    }
                    i2 = i + 1;
                    entry3 = entry;
                    pzaVar2 = pzaVar;
                    values = list;
                    entry4 = entry2;
                }
            }
            entry = entry3;
            entry2 = entry4;
            i = i2;
            f2 = f3;
            j = j;
            i2 = i + 1;
            entry3 = entry;
            pzaVar2 = pzaVar;
            values = list;
            entry4 = entry2;
        }
        if (arrayList4.size() > 0) {
            npf npfVar2 = arrayList4.get(arrayList4.size() - 1);
            this.ab = npfVar2.b();
            if (!npfVar2.i()) {
                arrayList5.add(new PointF(npfVar2.b(), npfVar2.d()));
            }
        }
        if (arrayList.size() > 0) {
            this.ac = arrayList.get(arrayList.size() - 1).x;
        }
        Canvas canvas2 = this.mBitmapCanvas;
        int contentBottom = (int) (this.mViewPortHandler.contentBottom() + this.mChart.getXAxis().getYOffset());
        HwHealthTransformer transformer = this.mChart.getTransformer(pzaVar.getAxisDependencyExt());
        if (this.o.equals("BLOOD_SUGAR_CONTINUE")) {
            if (!arrayList4.isEmpty() || !arrayList5.isEmpty()) {
                dvX_(canvas2, transformer, arrayList4, arrayList5);
            }
            if (!arrayList.isEmpty()) {
                canvas2.saveLayerAlpha(new RectF(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), contentBottom), 25);
                dvY_(canvas2, pzaVar, arrayList, arrayList2, arrayList3);
                canvas2.restore();
            }
        } else if (this.o.equals("BLOOD_SUGAR_FINGER_TIP")) {
            if (!arrayList.isEmpty()) {
                dvY_(canvas2, pzaVar, arrayList, arrayList2, arrayList3);
            }
            if (!arrayList4.isEmpty() || !arrayList5.isEmpty()) {
                canvas2.saveLayerAlpha(new RectF(this.mViewPortHandler.contentLeft(), this.mViewPortHandler.contentTop(), this.mViewPortHandler.contentRight(), contentBottom), 25);
                dvX_(canvas2, transformer, arrayList4, arrayList5);
                canvas2.restore();
            }
        } else if (!arrayList.isEmpty()) {
            dvY_(canvas2, pzaVar, arrayList, arrayList2, arrayList3);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j4 = elapsedRealtime - this.ah;
        if (j4 > 100) {
            a();
            LogUtil.a("BloodSugarLineChartRender", "diff=", Long.valueOf(j4));
        }
        this.ah = elapsedRealtime;
    }

    public void a() {
        BloodSugarLineChartInterface bloodSugarLineChartInterface = this.f16354a;
        if (bloodSugarLineChartInterface != null) {
            bloodSugarLineChartInterface.onDrawDataCallback(this.l);
        }
    }

    private void dvY_(Canvas canvas, pza pzaVar, List<PointF> list, List<Integer> list2, List<Entry> list3) {
        HwHealthTransformer transformer = this.mChart.getTransformer(pzaVar.getAxisDependencyExt());
        int i = pzaVar.getMode() == HwHealthBaseLineDataSet.Mode.STEPPED ? 4 : 2;
        float phaseY = this.mAnimator.getPhaseY();
        if (this.k != DataInfos.BloodSugarDayDetail && list3.size() > 0) {
            dwc_(i, transformer, canvas, d(pzaVar, phaseY));
        }
        dwd_(transformer, phaseY, canvas);
        if (list.size() > 0) {
            dvZ_(canvas, list2, list, transformer);
        }
    }

    private void dvX_(Canvas canvas, Transformer transformer, List<npf> list, List<PointF> list2) {
        if (!koq.b(list2)) {
            dwb_(canvas, transformer, list2);
        }
        if (koq.b(list)) {
            return;
        }
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        Path path = new Path();
        int i = 0;
        while (i < list.size() - 1) {
            npf npfVar = list.get(i);
            int i2 = i + 1;
            npf npfVar2 = list.get(i2);
            if (npfVar2.i()) {
                path.reset();
                MPPointD pixelForValues = transformer.getPixelForValues(npfVar.b(), npfVar.d());
                MPPointD pixelForValues2 = transformer.getPixelForValues(npfVar2.b(), npfVar2.d());
                int save = canvas.save();
                canvas.clipRect((int) pixelForValues.x, (int) viewPortHandler.contentTop(), (int) pixelForValues2.x, (int) viewPortHandler.contentBottom());
                path.moveTo((float) pixelForValues.x, (float) pixelForValues.y);
                path.lineTo((float) pixelForValues2.x, (float) pixelForValues2.y);
                canvas.drawPath(path, this.e);
                canvas.restoreToCount(save);
                dwa_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, (float) pixelForValues2.x, (float) pixelForValues2.y);
            }
            i = i2;
        }
    }

    private void dwb_(Canvas canvas, Transformer transformer, List<PointF> list) {
        for (PointF pointF : list) {
            MPPointD pixelForValues = transformer.getPixelForValues(pointF.x, pointF.y);
            canvas.drawLine((float) pixelForValues.x, (float) pixelForValues.y, (float) pixelForValues.x, (float) pixelForValues.y, this.u);
            dwa_(canvas, (float) (pixelForValues.x - (this.u.getStrokeWidth() / 2.0f)), (float) pixelForValues.y, (float) (pixelForValues.x + (this.u.getStrokeWidth() / 2.0f)), (float) pixelForValues.y);
        }
    }

    private void dwa_(Canvas canvas, float f, float f2, float f3, float f4) {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        int save = canvas.save();
        int contentBottom = (int) viewPortHandler.contentBottom();
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        float f5 = contentBottom;
        path.lineTo(f3, f5);
        path.lineTo(f, f5);
        canvas.clipPath(path);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{this.r, this.s});
        gradientDrawable.setBounds((int) viewPortHandler.contentLeft(), (int) viewPortHandler.contentTop(), (int) viewPortHandler.contentRight(), contentBottom);
        gradientDrawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    private void d(pza pzaVar) {
        int i = pzaVar.getMode() == HwHealthBaseLineDataSet.Mode.STEPPED ? 4 : 2;
        this.mXBounds.set(this.mChart, pzaVar);
        int max = Math.max(pzaVar.getEntryCount() * i, i);
        int i2 = max * 2;
        if (this.i.length < i2) {
            this.i = new float[max * 4];
        }
        if (this.f.length < i2) {
            this.f = new float[max * 4];
        }
    }

    private void c(Entry entry, int i) {
        switch (i) {
            case 2008:
                this.n = entry;
                break;
            case 2009:
                this.g = entry;
                break;
            case 2010:
                this.af = entry;
                break;
            case 2011:
                this.z = entry;
                break;
            case 2012:
                this.q = entry;
                break;
            case 2013:
                this.p = entry;
                break;
        }
    }

    private void h() {
        this.n = null;
        this.g = null;
        this.af = null;
        this.z = null;
        this.q = null;
        this.p = null;
        this.ab = -1.0f;
        this.ac = -1.0f;
        this.l.clear();
    }

    private void dwd_(Transformer transformer, float f, Canvas canvas) {
        if (this.k != DataInfos.BloodSugarDayDetail) {
            return;
        }
        this.ag.clear();
        Entry entry = this.n;
        if (entry != null && this.g != null && d(entry.getX(), this.g.getX())) {
            this.ag.add(new Pair(this.n, this.g));
        }
        Entry entry2 = this.af;
        if (entry2 != null && this.z != null && d(entry2.getX(), this.z.getX())) {
            this.ag.add(new Pair(this.af, this.z));
        }
        Entry entry3 = this.q;
        if (entry3 != null && this.p != null && d(entry3.getX(), this.p.getX())) {
            this.ag.add(new Pair(this.q, this.p));
        }
        if (koq.c(this.ag)) {
            this.aj.setColor(this.t);
            if (nrt.a(BaseApplication.getContext())) {
                this.aj.setAlpha(76);
            } else {
                this.aj.setAlpha(25);
            }
            this.aj.setStyle(Paint.Style.FILL_AND_STROKE);
            this.aj.setStrokeWidth(Utils.convertDpToPixel(8.0f));
            Path path = new Path();
            for (Pair pair : this.ag) {
                MPPointD pixelForValues = transformer.getPixelForValues(((Entry) pair.first).getX(), ((Entry) pair.first).getY() * f);
                MPPointD pixelForValues2 = transformer.getPixelForValues(((Entry) pair.second).getX(), ((Entry) pair.second).getY() * f);
                path.reset();
                path.moveTo((float) pixelForValues.x, (float) pixelForValues.y);
                path.lineTo((float) pixelForValues2.x, (float) pixelForValues2.y);
                canvas.drawPath(path, this.aj);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.github.mikephil.charting.data.Entry] */
    private int d(IHwHealthLineDataSet iHwHealthLineDataSet, float f) {
        LogUtil.a("BloodSugarLineChartRender", "putLineBuffer");
        int i = this.mXBounds.min;
        int i2 = 0;
        int i3 = 0;
        while (i <= this.mXBounds.range + this.mXBounds.min) {
            Entry entryForIndex = i != 0 ? iHwHealthLineDataSet.getEntryForIndex(i - 1) : null;
            ?? entryForIndex2 = iHwHealthLineDataSet.getEntryForIndex(i);
            if (entryForIndex2 != 0 && entryForIndex != null && (DataInfos.BloodSugarDayDetail != this.k || d(entryForIndex.getX(), entryForIndex2.getX()))) {
                this.i[i3] = entryForIndex.getX();
                this.i[i3 + 1] = entryForIndex.getY() * f;
                this.i[i3 + 2] = entryForIndex2.getX();
                this.i[i3 + 3] = entryForIndex2.getY() * f;
                i2++;
                i3 += 4;
            }
            i++;
        }
        return i2;
    }

    private void dwc_(int i, Transformer transformer, Canvas canvas, int i2) {
        if (i2 <= 0) {
            return;
        }
        transformer.pointValuesToPixel(this.i);
        int max = Math.max(i2 * i, i);
        if (DataInfos.BloodSugarDayDetail == this.k) {
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
                if (a(f3, f5) && a(f4, f6)) {
                    path.lineTo(f7, f8);
                } else {
                    canvas.save();
                    canvas.clipRect(f, (int) this.mViewPortHandler.contentTop(), f2, (int) this.mViewPortHandler.contentBottom());
                    this.aj.setShadowLayer(10.0f, 0.0f, 5.0f, this.x);
                    canvas.drawPath(path, this.aj);
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
                this.aj.setShadowLayer(10.0f, 0.0f, 5.0f, this.x);
                canvas.drawPath(path, this.aj);
                canvas.restore();
                path.reset();
                return;
            }
        }
    }

    private boolean a(float f, float f2) {
        LogUtil.a("BloodSugarLineChartRender", "floatNumberCompare");
        return Math.abs(f - f2) < 1.0E-7f;
    }

    private void dvZ_(Canvas canvas, List<Integer> list, List<PointF> list2, Transformer transformer) {
        LogUtil.a("BloodSugarLineChartRender", "drawDataPoints");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        int size = list2.size() * 2;
        float[] fArr = new float[size];
        int i = 0;
        for (PointF pointF : list2) {
            int i2 = i + 1;
            fArr[i] = pointF.x;
            i += 2;
            fArr[i2] = pointF.y;
        }
        transformer.pointValuesToPixel(fArr);
        for (int i3 = 0; i3 < size; i3 += 2) {
            paint.setColor(list.get(i3 / 2).intValue());
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            float f = fArr[i3];
            float f2 = fArr[i3 + 1];
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(5.0f), paint);
            paint.setColor(-1);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(5.0f) / 2.0f, paint);
        }
    }

    private float c(float f) {
        LogUtil.a("BloodSugarLineChartRender", "getColorForBloodOxygenGrade");
        if (f <= 4.4f) {
            return 4.4f;
        }
        return f <= 8.0f ? 8.0f : 10.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.view.HwHealthLineChartRenderLayerOriginal, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        List entriesForXValue;
        now lineData = this.mChart.getLineData();
        if (highlightArr == null || highlightArr.length <= 0 || lineData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            List<T> dataSets = lineData.getDataSets();
            if (dataSets != 0 && dataSets.size() != 0) {
                for (T t : dataSets) {
                    if ("BLOOD_SUGAR_CONTINUE".equals(this.o) && (this.mChart instanceof HwHealthBaseBarLineChart)) {
                        IHwHealthDatasContainer cacheDataContainer = t.cacheDataContainer((HwHealthBaseBarLineChart) this.mChart);
                        entriesForXValue = cacheDataContainer instanceof IHwHealthLineDatasContainer ? t.getEntriesForXValue(highlight.getX(), (IHwHealthLineDatasContainer) cacheDataContainer) : null;
                    } else {
                        entriesForXValue = t.getEntriesForXValue(highlight.getX());
                    }
                    if (entriesForXValue != null && !entriesForXValue.isEmpty()) {
                        dwe_(canvas, highlight, t, entriesForXValue);
                    }
                }
            }
        }
    }

    private void dwe_(Canvas canvas, Highlight highlight, IHwHealthLineDataSet iHwHealthLineDataSet, List<HwHealthBaseEntry> list) {
        if (koq.c(list)) {
            for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                pzy pzyVar = hwHealthBaseEntry.getData() instanceof pzy ? (pzy) hwHealthBaseEntry.getData() : null;
                if (pzyVar != null && this.o.equals(pzyVar.c())) {
                    e(hwHealthBaseEntry);
                    MPPointD pixelForValues = this.mChart.getTransformer(iHwHealthLineDataSet.getAxisDependencyExt()).getPixelForValues(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    dwf_(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iHwHealthLineDataSet, false, true, Color.argb(0, 0, 0, 0));
                }
            }
        }
    }

    @Override // defpackage.nox
    protected void dwf_(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet, boolean z, boolean z2, int i) {
        if (iLineScatterCandleRadarDataSet == null) {
            LogUtil.h("BloodSugarLineChartRender", "drawHighlightLines: dataSet is null");
            return;
        }
        LogUtil.a("BloodSugarLineChartRender", "set.getHighlightLineWidth() = ", Float.valueOf(iLineScatterCandleRadarDataSet.getHighlightLineWidth()));
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setColor(i);
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled() && z) {
            this.y.reset();
            if (this.mChart instanceof Chart) {
                if (((Chart) this.mChart).getMarker() instanceof MarkerView) {
                    this.y.moveTo(f, ((MarkerView) r10).getHeight());
                    this.y.lineTo(f, this.mViewPortHandler.contentTop() + this.mChart.getXAxis().getYOffset());
                    canvas.drawPath(this.y, this.mHighlightPaint);
                }
            }
        }
        if (z2) {
            this.mHighlightPaint.setAntiAlias(true);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(-1);
            this.mHighlightPaint.setStrokeWidth(0.0f);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(8.0f), this.mHighlightPaint);
            this.mHighlightPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.mHighlightPaint.setStyle(Paint.Style.FILL);
            this.mHighlightPaint.setColor(this.ae);
            canvas.drawCircle(f, f2, Utils.convertDpToPixel(5.0f), this.mHighlightPaint);
            this.mHighlightPaint.setStyle(Paint.Style.STROKE);
            return;
        }
        this.mHighlightPaint.setAntiAlias(true);
        this.mHighlightPaint.setStrokeWidth(1.0f);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(this.v);
        float e = nrr.e(BaseApplication.getContext(), 1.0f);
        canvas.drawCircle(f - e, f2, e, this.mHighlightPaint);
        canvas.drawRect(f - (r10 * 2), f2, f, this.mViewPortHandler.contentTop(), this.mHighlightPaint);
        this.mHighlightPaint.setStyle(Paint.Style.STROKE);
    }

    private void e(Entry entry) {
        LogUtil.a("BloodSugarLineChartRender", "setHighLightDotColor");
        Object data = entry.getData();
        if (data instanceof pzy) {
            pzy pzyVar = (pzy) data;
            this.ae = pzyVar.h();
            this.v = pzyVar.h();
            return;
        }
        float c = c(entry.getY());
        if (c <= 4.4f) {
            this.ae = this.b;
            this.v = this.am;
        } else if (c <= 8.0f) {
            this.ae = this.h;
            this.v = this.al;
        } else {
            this.ae = this.c;
            this.v = this.ai;
        }
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        LogUtil.a("BloodSugarLineChartRender", "usePaintAsBackground");
        this.w = z;
    }

    @Override // defpackage.nox, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        LogUtil.a("BloodSugarLineChartRender", "isUsePaintAsBackground");
        return this.w;
    }

    private boolean d(float f, float f2) {
        LogUtil.a("BloodSugarLineChartRender", "isSameDay");
        Date date = new Date((((long) f) * 60000) + 1388505600000L);
        Date date2 = new Date((((long) f2) * 60000) + 1388505600000L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(6);
        calendar.setTime(date2);
        return calendar.get(6) == i;
    }

    public void a(String str) {
        this.o = str;
    }

    public void b(BloodSugarLineChartInterface bloodSugarLineChartInterface) {
        this.f16354a = bloodSugarLineChartInterface;
    }

    public float e() {
        if ("BLOOD_SUGAR_CONTINUE".equals(this.o)) {
            return this.ab;
        }
        return this.ac;
    }

    public void b() {
        this.ab = -1.0f;
        this.ac = -1.0f;
    }
}
