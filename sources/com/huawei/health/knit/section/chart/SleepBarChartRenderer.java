package com.huawei.health.knit.section.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.accessibility.AbstractTouchNode;
import com.huawei.hwcommonmodel.accessibility.ChartTouchHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthTransformer;
import com.huawei.ui.commonui.linechart.common.IHwHealthDataRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarDataSet;
import defpackage.ede;
import defpackage.eeg;
import defpackage.koq;
import defpackage.nmy;
import defpackage.nmz;
import defpackage.nnb;
import defpackage.not;
import defpackage.nrn;
import defpackage.nsf;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class SleepBarChartRenderer extends nnb implements IHwHealthDataRender {
    private Paint aa;
    private Paint ab;
    private OnSleepTimeChangedListener ac;
    private Paint ad;
    private float ae;
    private Paint af;
    private Paint ag;
    private Paint ah;
    private Paint ai;
    private float aj;
    private float ak;
    private float al;
    private boolean f;
    private HwHealthBarChart g;
    private float h;
    private int i;
    private float k;
    private boolean l;
    private float m;
    private float n;
    private boolean o;
    private float p;
    private float q;
    private boolean r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private Paint z;

    public interface OnSleepTimeChangedListener {
        void onSleepTimeChangedListener(float f, float f2);
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return false;
    }

    public SleepBarChartRenderer(HwHealthBarChart hwHealthBarChart, HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.l = true;
        this.o = true;
        this.r = false;
        this.i = 0;
        this.ad = new Paint();
        this.ab = new Paint();
        this.z = new Paint();
        this.aa = new Paint();
        this.ah = new Paint();
        this.ai = new Paint();
        this.ag = new Paint();
        this.af = new Paint();
        if (hwHealthBarChart == null || hwHealthBarDataProvider == null) {
            return;
        }
        this.g = hwHealthBarChart;
        this.e = hwHealthBarDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        this.j = new Paint(1);
        this.j.setStyle(Paint.Style.FILL);
        this.b = new Paint(1);
        this.b.setStyle(Paint.Style.STROKE);
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.knit.section.chart.SleepBarChartRenderer.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                nmz barData;
                IHwHealthBarDataSet iHwHealthBarDataSet;
                if (koq.e(objArr, 0) && (barData = SleepBarChartRenderer.this.e.getBarData()) != null && (iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(0)) != null && objArr.length >= 2 && (objArr[1] instanceof DataInfos) && ((nmy) iHwHealthBarDataSet).a() == ((DataInfos) objArr[1])) {
                    Object obj = objArr[0];
                    if (obj instanceof Integer) {
                        SleepBarChartRenderer.this.e(((Integer) obj).intValue());
                    }
                }
            }
        }, "SLEEP_WEEK_MONTH_YEAR_AVERAGE_DAILY");
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("R_Sleep_SleepBarChartRenderer", "initBuffers() barData is null!");
            return;
        }
        this.f15395a = new not[barData.getDataSetCount()];
        for (int i = 0; i < this.f15395a.length; i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            this.f15395a[i] = new not(iHwHealthBarDataSet.getEntryCount() * 4 * (iHwHealthBarDataSet.isStacked() ? iHwHealthBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iHwHealthBarDataSet.isStacked());
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (barData == null) {
            LogUtil.b("R_Sleep_SleepBarChartRenderer", "drawData() barData is null!");
            return;
        }
        ChartTouchHelper accessibilityHelper = this.g.getAccessibilityHelper();
        if (accessibilityHelper != null) {
            accessibilityHelper.e().d();
        }
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(i);
            if (iHwHealthBarDataSet.isVisible()) {
                dGJ_(canvas, iHwHealthBarDataSet, i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nnb
    public void dGJ_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, int i) {
        IHwHealthBarDataSet iHwHealthBarDataSet2;
        int i2;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        nmz barData = this.e.getBarData();
        if (canvas == null || iHwHealthBarDataSet == null || barData == null || (iHwHealthBarDataSet2 = (IHwHealthBarDataSet) barData.getDataSetByIndex(i)) == null || !iHwHealthBarDataSet2.isHighlightEnabled() || i < 0) {
            return;
        }
        this.b.setColor(iHwHealthBarDataSet.getBarBorderColor());
        this.b.setStrokeWidth(Utils.convertDpToPixel(iHwHealthBarDataSet.getBarBorderWidth()));
        if (this.f15395a == null || this.f15395a.length <= i) {
            LogUtil.h("SleepBarChartRenderer", "drawDataSet invalid parameter");
            return;
        }
        not notVar = this.f15395a[i];
        if (notVar == null) {
            return;
        }
        notVar.setPhases(this.mAnimator.getPhaseX(), this.mAnimator.getPhaseY());
        notVar.setDataSet(i);
        notVar.setInverted(false);
        notVar.setBarWidth(this.e.getBarData().d());
        notVar.b(iHwHealthBarDataSet);
        HwHealthTransformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
        transformer.pointValuesToPixel(notVar.buffer);
        boolean z = iHwHealthBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(e(iHwHealthBarDataSet));
        }
        ArrayList arrayList6 = new ArrayList(16);
        ArrayList arrayList7 = new ArrayList(16);
        ArrayList arrayList8 = new ArrayList(16);
        ArrayList arrayList9 = new ArrayList(16);
        ArrayList arrayList10 = new ArrayList(16);
        ArrayList arrayList11 = new ArrayList(16);
        nmy nmyVar = (nmy) iHwHealthBarDataSet2;
        boolean z2 = nmyVar.a() == DataInfos.CoreSleepYearDetail;
        int i3 = 0;
        while (i3 < notVar.size()) {
            if (this.mViewPortHandler.isInBoundsLeft(notVar.buffer[i3])) {
                int i4 = i3 + 2;
                if (this.mViewPortHandler.isInBoundsRight(notVar.buffer[i4])) {
                    if (!z) {
                        this.mRenderPaint.setColor(iHwHealthBarDataSet.getColor(i3 / 4));
                    }
                    RectF rectF = this.c;
                    float f = notVar.buffer[i3];
                    float f2 = notVar.buffer[i3 + 1];
                    float f3 = notVar.buffer[i4];
                    ArrayList arrayList12 = arrayList11;
                    rectF.set(f, f2, f3, notVar.buffer[i3 + 3]);
                    int i5 = i3 / 4;
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i5);
                    if (hwHealthBarEntry == null) {
                        return;
                    }
                    ede d = ((eeg) hwHealthBarEntry.acquireModel()).d();
                    i2 = i3;
                    arrayList = arrayList12;
                    arrayList2 = arrayList10;
                    arrayList3 = arrayList9;
                    arrayList4 = arrayList8;
                    arrayList5 = arrayList7;
                    b(arrayList6, arrayList7, arrayList8, arrayList, arrayList9, arrayList2, d);
                    afL_(canvas, this.c, transformer, 2, d, nmyVar.a());
                    ChartTouchHelper accessibilityHelper = this.g.getAccessibilityHelper();
                    if (accessibilityHelper != null) {
                        AbstractTouchNode a2 = accessibilityHelper.e().a(i5);
                        c(a2, hwHealthBarEntry.getX());
                        Rect rect = new Rect();
                        this.c.round(rect);
                        a2.setRect(rect);
                    }
                    i3 = i2 + 4;
                    arrayList10 = arrayList2;
                    arrayList11 = arrayList;
                    arrayList9 = arrayList3;
                    arrayList8 = arrayList4;
                    arrayList7 = arrayList5;
                }
            }
            i2 = i3;
            arrayList = arrayList11;
            arrayList2 = arrayList10;
            arrayList3 = arrayList9;
            arrayList4 = arrayList8;
            arrayList5 = arrayList7;
            i3 = i2 + 4;
            arrayList10 = arrayList2;
            arrayList11 = arrayList;
            arrayList9 = arrayList3;
            arrayList8 = arrayList4;
            arrayList7 = arrayList5;
        }
        ArrayList arrayList13 = arrayList11;
        ArrayList arrayList14 = arrayList10;
        ArrayList arrayList15 = arrayList9;
        ArrayList arrayList16 = arrayList8;
        ArrayList arrayList17 = arrayList7;
        if (this.e.getBarData().getDataSets().get(0) instanceof nmy) {
            nmy nmyVar2 = (nmy) this.e.getBarData().getDataSets().get(0);
            Object[] objArr = new Object[7];
            objArr[0] = Boolean.valueOf(arrayList6.size() > 0);
            objArr[1] = Boolean.valueOf(arrayList17.size() > 0);
            objArr[2] = Boolean.valueOf(arrayList16.size() > 0);
            objArr[3] = Boolean.valueOf(arrayList14.size() > 0);
            objArr[4] = Boolean.valueOf(arrayList15.size() > 0);
            objArr[5] = Boolean.valueOf(arrayList13.size() > 0);
            objArr[6] = nmyVar2.a();
            ObserverManagerUtil.c("SLEEP_BASE_BAR_DREAM_VISIBILITY", objArr);
        }
        if (z2) {
            return;
        }
        a(arrayList6, arrayList17, arrayList16, arrayList13);
    }

    private void c(AbstractTouchNode abstractTouchNode, float f) {
        String formattedValue;
        String b;
        IAxisValueFormatter valueFormatter = this.g.getXAxis().getValueFormatter();
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            formattedValue = ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(f, this.g.getXAxis());
        } else {
            formattedValue = valueFormatter.getFormattedValue(f, this.g.getXAxis());
        }
        String[] strArr = {nsf.h(R$string.IDS_manual_sleep_sleep_time), nsf.h(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep), nsf.h(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep), nsf.h(R$string.IDS_fitness_core_sleep_rem_sleep), nsf.h(R$string.IDS_details_sleep_sleep_latency), nsf.h(R$string.IDS_fitness_core_sleep_noontime_sleep)};
        int i = (int) this.ak;
        int i2 = (int) this.al;
        int[] iArr = {i - i2, (int) this.m, (int) this.s, (int) this.k, i2, (int) this.w};
        for (int i3 = 0; i3 < 6; i3++) {
            int i4 = iArr[i3];
            int i5 = i4 / 60;
            int i6 = i4 % 60;
            if (i5 == 0) {
                b = i6 != 0 ? nsf.b(com.huawei.health.servicesui.R$string.IDS_hw_show_set_target_sport_time_unit, Integer.valueOf(i6)) : "";
            } else if (i6 != 0) {
                b = nsf.b(com.huawei.health.servicesui.R$string.IDS_h_min_unit, Integer.valueOf(i5), Integer.valueOf(i6));
            } else {
                b = nsf.a(R.plurals._2130903223_res_0x7f0300b7, i5, UnitUtil.e(i5, 1, 0));
            }
            if (!TextUtils.isEmpty(b)) {
                formattedValue = nsf.b(com.huawei.health.servicesui.R$string.IDS_two_parts, formattedValue, nsf.b(com.huawei.health.servicesui.R$string.IDS_two_parts, strArr[i3], b));
            }
        }
        abstractTouchNode.setDescription(formattedValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (this.i != i) {
            this.i = i;
            int i2 = i / 60;
            String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0));
            int i3 = this.i % 60;
            String string = BaseApplication.getContext().getString(R$string.IDS_two_parts, quantityString, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(i3, 1, 0)));
            if (i > 0) {
                e(i, nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color), string);
            } else {
                e(i, nrn.d(BaseApplication.getContext(), R$color.colorBackground), "");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final int i2, final String str) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.section.chart.SleepBarChartRenderer.3
                @Override // java.lang.Runnable
                public void run() {
                    SleepBarChartRenderer.this.e(i, i2, str);
                }
            });
            return;
        }
        ((CoreSleepBarChartView) this.g).c(i, i2, str);
        this.g.reCalculateDynamicBoardForManualRefLine();
        this.g.refresh();
    }

    private void b(List<Float> list, List<Float> list2, List<Float> list3, List<Float> list4, List<Float> list5, List<Float> list6, ede edeVar) {
        if (edeVar == null || edeVar.j() == 30) {
            return;
        }
        if (edeVar.b() > 0.0f) {
            list.add(Float.valueOf(edeVar.b()));
        }
        if (edeVar.c() > 0.0f) {
            list2.add(Float.valueOf(edeVar.c()));
        }
        if (edeVar.d() > 0.0f) {
            list3.add(Float.valueOf(edeVar.d()));
        }
        if (edeVar.e() > 0.0f) {
            list5.add(Float.valueOf(edeVar.e()));
        }
        if (edeVar.f() > 0.0f) {
            list6.add(Float.valueOf(edeVar.f()));
        }
        if (edeVar.b() == 0.0f && edeVar.c() == 0.0f && edeVar.d() == 0.0f && edeVar.a() > 0.0f) {
            list4.add(Float.valueOf(edeVar.i()));
        }
    }

    private int a(List<Float> list, List<Float> list2, List<Float> list3, List<Float> list4) {
        int size = list.size();
        int size2 = list2.size();
        int size3 = list3.size();
        if (size <= size2) {
            size = size2;
        }
        if (size >= size3) {
            size3 = size;
        }
        int c = c(list, size3) + c(list2, size3) + c(list3, size3);
        if (koq.b(list4)) {
            return c;
        }
        Iterator<Float> it = list4.iterator();
        float f = 0.0f;
        int i = 0;
        while (it.hasNext()) {
            float floatValue = it.next().floatValue();
            if (floatValue >= 180.0f) {
                f += floatValue;
                i++;
            }
        }
        return Math.round(((c * size3) + f) / (size3 + i));
    }

    private int c(List<Float> list, int i) {
        Iterator<Float> it = list.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            f += it.next().floatValue();
        }
        return list.size() > 0 ? Math.round(f / i) : (int) f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00eb, code lost:
    
        if (r1 != false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f1, code lost:
    
        r4 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void afL_(android.graphics.Canvas r18, android.graphics.RectF r19, com.github.mikephil.charting.utils.Transformer r20, int r21, defpackage.ede r22, com.huawei.ui.commonui.linechart.common.DataInfos r23) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.knit.section.chart.SleepBarChartRenderer.afL_(android.graphics.Canvas, android.graphics.RectF, com.github.mikephil.charting.utils.Transformer, int, ede, com.huawei.ui.commonui.linechart.common.DataInfos):void");
    }

    private void afM_(Canvas canvas, RectF rectF, float f, float f2, int i, float f3) {
        LinearGradient linearGradient;
        float f4 = rectF.bottom - f3;
        if (i == 1) {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, f4, c(R$color.color_selected_year_top), c(R$color.color_selected_year_bottom), Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, f4, c(R$color.color_unselected_year_top), c(R$color.color_unselected_year_bottom), Shader.TileMode.CLAMP);
        }
        this.af.setShader(linearGradient);
        float f5 = f / 2.0f;
        canvas.drawArc(new RectF(rectF.left, (rectF.top + f2) - f5, rectF.right, rectF.top + f2 + f5), 0.0f, -180.0f, false, this.af);
        canvas.drawRect(rectF.left, f2 + rectF.top, rectF.right, f4, this.af);
    }

    private void afK_(Canvas canvas, RectF rectF, float f, float f2) {
        boolean z;
        if (this.f) {
            if (this.p > 0.0f) {
                float f3 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, (rectF.top + f2) - f3, rectF.right, rectF.top + f2 + f3), 0.0f, -180.0f, false, this.ai);
                canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, this.t, this.ai);
                if (this.x > 0.0f) {
                    canvas.drawRect(rectF.left, this.t, rectF.right, this.y, this.ag);
                    return;
                }
                return;
            }
            float f4 = f / 2.0f;
            canvas.drawArc(new RectF(rectF.left, (rectF.top + f2) - f4, rectF.right, rectF.top + f2 + f4), 0.0f, -180.0f, false, this.ai);
            canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, this.y, this.ag);
            return;
        }
        if (this.w > 0.0f) {
            float f5 = f / 2.0f;
            canvas.drawArc(new RectF(rectF.left, (rectF.top + f2) - f5, rectF.right, rectF.top + f2 + f5), 0.0f, -180.0f, false, this.ad);
            canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, this.u, this.ad);
        }
        boolean z2 = true;
        if (this.al > 0.0f) {
            float f6 = f / 2.0f;
            canvas.drawArc(new RectF(rectF.left, this.u - f6, rectF.right, this.u + f6), 0.0f, -180.0f, false, this.ab);
            canvas.drawRect(rectF.left, this.u, rectF.right, this.aj, this.ab);
            z = true;
        } else {
            z = false;
        }
        if (this.k > 0.0f) {
            if (!z) {
                float f7 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.aj - f7, rectF.right, this.aj + f7), 0.0f, -180.0f, false, this.z);
                z = true;
            }
            canvas.drawRect(rectF.left, this.aj, rectF.right, this.n, this.z);
        }
        if (this.s > 0.0f) {
            if (z) {
                z2 = z;
            } else {
                float f8 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.n - f8, rectF.right, this.n + f8), 0.0f, -180.0f, false, this.aa);
            }
            canvas.drawRect(rectF.left, this.n, rectF.right, this.q, this.aa);
            z = z2;
        }
        if (this.m > 0.0f) {
            if (!z) {
                float f9 = f / 2.0f;
                canvas.drawArc(new RectF(rectF.left, this.q - f9, rectF.right, this.q + f9), 0.0f, -180.0f, false, this.ah);
            }
            canvas.drawRect(rectF.left, this.q, rectF.right, this.h, this.ah);
        }
    }

    private void afP_(RectF rectF, float f, float f2, float f3) {
        boolean z = false;
        boolean z2 = this.m <= 0.0f && this.s <= 0.0f;
        if (this.k <= 0.0f && this.al <= 0.0f) {
            z = true;
        }
        if (this.f) {
            this.y = rectF.bottom - f2;
            this.t = ((this.p / this.ak) * f3) + rectF.top + f;
            return;
        }
        if (z2 && z) {
            this.u = rectF.bottom - f2;
        } else {
            this.u = ((this.w / this.ak) * f3) + rectF.top + f;
        }
        if (this.m <= 0.0f && this.s <= 0.0f && this.k <= 0.0f) {
            this.aj = rectF.bottom - f2;
        } else {
            this.aj = ((this.al / this.ak) * f3) + this.u;
        }
        if (this.m <= 0.0f && this.s <= 0.0f) {
            this.n = rectF.bottom - f2;
        } else {
            this.n = ((this.k / this.ak) * f3) + this.aj;
        }
        if (this.m <= 0.0f) {
            this.q = rectF.bottom - f2;
        } else {
            this.q = ((this.s / this.ak) * f3) + this.n;
        }
        this.h = rectF.bottom - f2;
    }

    private void d(ede edeVar, DataInfos dataInfos) {
        this.w = edeVar.e();
        this.al = edeVar.f();
        this.k = edeVar.d();
        this.s = edeVar.c();
        float b = edeVar.b();
        this.m = b;
        if (b + this.s + this.k + this.al == 0.0f && edeVar.a() != 0.0f) {
            this.f = true;
            this.x = edeVar.i();
            float a2 = edeVar.a();
            float f = this.x;
            float f2 = a2 - f;
            this.p = f2;
            this.ak = f + f2;
            return;
        }
        if (dataInfos == DataInfos.CoreSleepYearDetail) {
            this.f = false;
            this.x = 0.0f;
            this.p = edeVar.a();
        } else {
            this.f = false;
            this.x = 0.0f;
            this.p = 0.0f;
        }
        this.ak = this.m + this.s + this.k + this.al + this.w;
    }

    private void d(int i) {
        if (i == 1) {
            this.ad.setColor(Color.argb(255, 0, 143, 255));
            this.ab.setColor(Color.argb(255, 255, 217, 69));
            this.z.setColor(Color.argb(255, 252, OldToNewMotionPath.SPORT_TYPE_TENNIS, OldToNewMotionPath.SPORT_TYPE_TENNIS));
            this.aa.setColor(Color.argb(255, 198, 75, 228));
            this.ah.setColor(Color.argb(255, OldToNewMotionPath.SPORT_TYPE_PILATES, 43, 226));
            this.ai.setColor(c(R$color.color_selected_manual_bed));
            this.ag.setColor(c(R$color.color_selected_manual_sleep));
            return;
        }
        this.ad.setColor(c(R$color.color_unselected_noon_sleep));
        this.ab.setColor(c(R$color.color_unselected_awake));
        this.z.setColor(c(R$color.color_unselected_rem));
        this.aa.setColor(c(R$color.color_unselected_shallow));
        this.ah.setColor(c(R$color.color_unselected_deep));
        this.ai.setColor(c(R$color.color_unselected_manual_bed));
        this.ag.setColor(c(R$color.color_unselected_manual_sleep));
    }

    private int c(int i) {
        HwHealthBarChart hwHealthBarChart = this.g;
        if (hwHealthBarChart instanceof CoreSleepBarChartView) {
            return ((CoreSleepBarChartView) hwHealthBarChart).getResources().getColor(i);
        }
        return -1;
    }

    @Override // defpackage.nnb
    public void b(float f, float f2, float f3, float f4, Transformer transformer) {
        this.d.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.d, this.mAnimator.getPhaseY());
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        if (canvas == null || !isDrawingValuesAllowed(this.e) || this.e.getBarData() == null) {
            return;
        }
        List<T> dataSets = this.e.getBarData().getDataSets();
        float convertDpToPixel = Utils.convertDpToPixel(4.5f);
        boolean isDrawValueAboveBarEnabled = this.e.isDrawValueAboveBarEnabled();
        for (int i = 0; i < this.e.getBarData().getDataSetCount(); i++) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) dataSets.get(i);
            if (shouldDrawValues(iHwHealthBarDataSet)) {
                applyValueTextStyle(iHwHealthBarDataSet);
                float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
                this.ae = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                this.v = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                BarBuffer barBuffer = this.f15395a[i];
                MPPointF mPPointF = MPPointF.getInstance(iHwHealthBarDataSet.getIconsOffset());
                mPPointF.x = Utils.convertDpToPixel(mPPointF.x);
                mPPointF.y = Utils.convertDpToPixel(mPPointF.y);
                if (!iHwHealthBarDataSet.isStacked()) {
                    afO_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                } else {
                    afN_(canvas, iHwHealthBarDataSet, barBuffer, mPPointF);
                }
                MPPointF.recycleInstance(mPPointF);
            }
        }
    }

    private void afN_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        float f;
        if (barBuffer == null || barBuffer.buffer == null) {
            LogUtil.h("SleepBarChartRenderer", "setIsStacked buffer or buffer.buffer is null");
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < iHwHealthBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
            HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i);
            int i3 = i2 + 2;
            if (barBuffer.buffer.length <= i3) {
                LogUtil.h("SleepBarChartRenderer", "setIsStacked invalid parameter");
                return;
            }
            float f2 = (barBuffer.buffer[i2] + barBuffer.buffer[i3]) / 2.0f;
            int valueTextColor = iHwHealthBarDataSet.getValueTextColor(i);
            if (!this.mViewPortHandler.isInBoundsRight(f2)) {
                return;
            }
            int i4 = i2 + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i4]) && this.mViewPortHandler.isInBoundsLeft(f2)) {
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    cBv_(canvas, iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i, this.mViewPortHandler), f2, barBuffer.buffer[i4] + (hwHealthBarEntry.getY() >= 0.0f ? this.ae : this.v), valueTextColor);
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    float f3 = barBuffer.buffer[i4];
                    if (hwHealthBarEntry.getY() >= 0.0f) {
                        f = this.ae;
                    } else {
                        f = this.v;
                    }
                    Utils.drawImage(canvas, icon, (int) (f2 + mPPointF.x), (int) (f3 + f + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
                i2 += 4;
                i++;
            }
        }
    }

    private void afO_(Canvas canvas, IHwHealthBarDataSet iHwHealthBarDataSet, BarBuffer barBuffer, MPPointF mPPointF) {
        float f;
        float f2;
        float f3;
        float f4;
        for (int i = 0; i < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i += 4) {
            float f5 = (barBuffer.buffer[i] + barBuffer.buffer[i + 2]) / 2.0f;
            if (!this.mViewPortHandler.isInBoundsRight(f5)) {
                return;
            }
            int i2 = i + 1;
            if (this.mViewPortHandler.isInBoundsY(barBuffer.buffer[i2]) && this.mViewPortHandler.isInBoundsLeft(f5)) {
                int i3 = i / 4;
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) iHwHealthBarDataSet.getEntryForIndex(i3);
                float y = hwHealthBarEntry.getY();
                if (iHwHealthBarDataSet.isDrawValuesEnabled()) {
                    String formattedValue = iHwHealthBarDataSet.getValueFormatter().getFormattedValue(hwHealthBarEntry.getY(), hwHealthBarEntry, i3, this.mViewPortHandler);
                    if (y >= 0.0f) {
                        f3 = barBuffer.buffer[i2];
                        f4 = this.ae;
                    } else {
                        f3 = barBuffer.buffer[i + 3];
                        f4 = this.v;
                    }
                    cBv_(canvas, formattedValue, f5, f3 + f4, iHwHealthBarDataSet.getValueTextColor(i3));
                }
                if (hwHealthBarEntry.getIcon() != null && iHwHealthBarDataSet.isDrawIconsEnabled()) {
                    Drawable icon = hwHealthBarEntry.getIcon();
                    if (y >= 0.0f) {
                        f = barBuffer.buffer[i2];
                        f2 = this.ae;
                    } else {
                        f = barBuffer.buffer[i + 3];
                        f2 = this.v;
                    }
                    Utils.drawImage(canvas, icon, (int) (f5 + mPPointF.x), (int) (f + f2 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                }
            }
        }
    }

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        if (this.r) {
            return;
        }
        nmz barData = this.e.getBarData();
        if (canvas == null || highlightArr == null || barData == null) {
            return;
        }
        for (Highlight highlight : highlightArr) {
            IHwHealthBarDataSet iHwHealthBarDataSet = (IHwHealthBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iHwHealthBarDataSet != null && iHwHealthBarDataSet.isHighlightEnabled()) {
                this.mHighlightPaint.setColor(d(iHwHealthBarDataSet));
                List<T> entriesForXValue = iHwHealthBarDataSet.getEntriesForXValue(highlight.getX());
                if (entriesForXValue == 0 || entriesForXValue.size() == 0) {
                    return;
                }
                HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) entriesForXValue.get(0);
                if (isInBoundsX(hwHealthBarEntry, iHwHealthBarDataSet)) {
                    Transformer transformer = this.e.getTransformer(iHwHealthBarDataSet.getAxisDependencyExt());
                    eeg eegVar = (eeg) hwHealthBarEntry.acquireModel();
                    float b = eegVar.b();
                    float e = eegVar.e();
                    ede d = eegVar.d();
                    if (this.ac != null && d != null) {
                        float f = b - d.f();
                        float a2 = d.a();
                        if (a2 != 0.0f) {
                            f = d.i();
                        }
                        this.ac.onSleepTimeChangedListener(f, a2);
                        b(hwHealthBarEntry.getX(), b, e, barData.d() / 2.0f, transformer);
                        dGK_(highlight, this.d);
                        afL_(canvas, this.d, transformer, 1, d, ((nmy) iHwHealthBarDataSet).a());
                    }
                }
            }
        }
    }

    public void c(OnSleepTimeChangedListener onSleepTimeChangedListener) {
        this.ac = onSleepTimeChangedListener;
    }

    @Override // defpackage.nnb
    public void dGK_(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }

    @Override // defpackage.nnb
    public HwHealthBarDataProvider b() {
        return this.e;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public void usePaintAsBackground(boolean z) {
        this.r = z;
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean isUsePaintAsBackground() {
        return this.r;
    }

    private int e(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.r) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.getColor();
    }

    private int d(IHwHealthBarDataSet iHwHealthBarDataSet) {
        if (this.r) {
            return Color.argb(255, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE, InterfaceHiMap.POLY_LINE_MAX_SIZE);
        }
        return iHwHealthBarDataSet.acquireFocusColor();
    }

    @Override // defpackage.nnb, com.huawei.ui.commonui.linechart.common.IHwHealthDataRender
    public boolean hasData() {
        nmz barData = this.e.getBarData();
        return (barData == null || barData.getDataSets() == null || barData.getDataSets().size() == 0) ? false : true;
    }

    @Override // defpackage.nnb
    public void d(boolean z, boolean z2) {
        this.l = z;
        this.o = z2;
    }
}
