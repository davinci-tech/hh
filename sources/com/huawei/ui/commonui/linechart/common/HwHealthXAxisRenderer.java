package com.huawei.ui.commonui.linechart.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import defpackage.nng;
import defpackage.nnt;
import defpackage.nnu;
import defpackage.nor;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes6.dex */
public class HwHealthXAxisRenderer extends XAxisRenderer {
    private static final b d = new b();

    /* renamed from: a, reason: collision with root package name */
    private int f8877a;
    private HwHealthBaseBarLineChart b;
    private Context c;
    private boolean e;
    private float f;
    private float g;
    private int h;
    private boolean i;
    private Sorption j;

    public enum Sorption {
        Y_OFFSET_CEIL,
        Y_OFFSET_FLOOR
    }

    public HwHealthXAxisRenderer(Context context, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(viewPortHandler, xAxis, transformer);
        this.c = null;
        this.b = null;
        this.i = false;
        this.e = false;
        this.f = Float.MAX_VALUE;
        this.g = -3.4028235E38f;
        this.j = Sorption.Y_OFFSET_CEIL;
        this.h = 0;
        this.f8877a = -1;
        this.c = context;
        this.b = hwHealthBaseBarLineChart;
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxisValues(float f, float f2) {
        if (!this.e) {
            super.computeAxisValues(f, f2);
            return;
        }
        int i = 0;
        this.mAxis.mDecimals = 0;
        float f3 = f2 - 0.0f;
        if (f3 < 60.0f) {
            this.mAxis.mEntries = new float[]{0.0f};
            computeSize();
            return;
        }
        float f4 = f2 / 60.0f;
        if (f3 <= 300.0f) {
            double d2 = f4;
            this.mAxis.mEntryCount = ((int) Math.floor(d2)) + 1;
            this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
            while (i <= ((int) Math.floor(d2))) {
                this.mAxis.mEntries[i] = i * 60;
                i++;
            }
            computeSize();
            return;
        }
        double floor = ((int) Math.floor(f4 / 5.0f)) / 5.0f;
        int floor2 = ((int) Math.floor(floor)) * 5;
        int ceil = ((int) Math.ceil(floor)) * 5;
        if (Math.abs((floor2 == 0 ? 0 : (int) Math.ceil(Math.ceil(f4) / floor2)) - 6) > Math.abs((ceil == 0 ? 0 : (int) Math.ceil(Math.ceil(f4) / ceil)) - 6)) {
            floor2 = ceil;
        }
        int floor3 = floor2 == 0 ? 0 : (int) Math.floor(f4 / floor2);
        this.mAxis.mEntryCount = floor3 + 1;
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        while (i <= floor3) {
            this.mAxis.mEntries[i] = i * floor2 * 60;
            i++;
        }
        computeSize();
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        if ((this.mAxis instanceof nnu) && ((nnu) this.mAxis).e()) {
            return;
        }
        if (this.mAxis.mAxisMaximum - this.mAxis.mAxisMinimum < 60.0f && this.i) {
            cBD_(canvas, f, mPPointF);
        } else {
            cBI_(canvas, f, mPPointF);
        }
    }

    public void d(boolean z) {
        this.i = z;
    }

    public void e(boolean z) {
        this.e = z;
    }

    private void cBD_(Canvas canvas, float f, MPPointF mPPointF) {
        float f2 = this.mViewPortHandler.getContentRect().left;
        float f3 = this.mViewPortHandler.getContentRect().right;
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        String e2 = UnitUtil.e(0.0d, 1, 0);
        Resources resources = this.c.getResources();
        String string = resources != null ? resources.getString(R$string.IDS_hwh_motiontrack_detail_chart_less_1) : "--";
        float calcTextWidth = Utils.calcTextWidth(this.mAxisLabelPaint, e2);
        float calcTextWidth2 = Utils.calcTextWidth(this.mAxisLabelPaint, string);
        if (!nng.d(this.c)) {
            float f4 = f2 + (calcTextWidth / 2.0f);
            float f5 = f3 - (calcTextWidth2 / 2.0f);
            drawLabel(canvas, e2, f4, c(f4, f, e2), mPPointF, labelRotationAngle);
            drawLabel(canvas, string, f5, c(f5, f, string), mPPointF, labelRotationAngle);
            return;
        }
        float f6 = f3 - (calcTextWidth / 2.0f);
        float f7 = f2 + (calcTextWidth2 / 2.0f);
        drawLabel(canvas, e2, f6, c(f6, f, e2), mPPointF, labelRotationAngle);
        drawLabel(canvas, string, f7, c(f7, f, string), mPPointF, labelRotationAngle);
    }

    @Override // com.github.mikephil.charting.renderer.XAxisRenderer
    public void drawLabel(Canvas canvas, String str, float f, float f2, MPPointF mPPointF, float f3) {
        String str2;
        boolean e2 = e(str);
        float textSize = this.mXAxis.getTextSize();
        String[] d2 = d(str);
        if (e2 && (str2 = d2[0]) != null && d2[1] != null) {
            Utils.drawXAxisValue(canvas, str2, f, f2 - 38.0f, this.mAxisLabelPaint, mPPointF, f3);
            Utils.drawXAxisValue(canvas, d2[1], f, ((f2 + textSize) + 2.0f) - 38.0f, this.mAxisLabelPaint, mPPointF, f3);
        } else {
            Utils.drawXAxisValue(canvas, str, f, f2, this.mAxisLabelPaint, mPPointF, f3);
        }
    }

    private void e(e eVar) {
        int alpha = this.mAxisLabelPaint.getAlpha();
        this.mAxisLabelPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
        this.mAxisLabelPaint.setAlpha(HeartRateThresholdConfig.HEART_RATE_LIMIT);
        float textSize = this.mXAxis.getTextSize();
        boolean e2 = e(eVar.c);
        String[] d2 = d(eVar.c);
        if (e2 && d2[0] != null && d2[1] != null) {
            Utils.drawXAxisValue(eVar.d, d2[0], eVar.f8879a, eVar.i - 38.0f, this.mAxisLabelPaint, eVar.b, eVar.e);
            Utils.drawXAxisValue(eVar.d, d2[1], eVar.f8879a, ((eVar.i + textSize) + 2.0f) - 38.0f, this.mAxisLabelPaint, eVar.b, eVar.e);
        } else {
            Utils.drawXAxisValue(eVar.d, eVar.c, eVar.f8879a, eVar.i, this.mAxisLabelPaint, eVar.b, eVar.e);
        }
        this.mAxisLabelPaint.setTypeface(this.mAxisLabelPaint.getTypeface());
        this.mAxisLabelPaint.setAlpha(alpha);
    }

    private boolean e() {
        return this.c.getResources().getConfiguration().locale.getLanguage().endsWith("bo");
    }

    private boolean e(String str) {
        if (str != null && !str.equals("") && str.length() > 7) {
            this.h++;
        }
        return e() && this.h > 0;
    }

    private String[] d(String str) {
        String[] strArr = new String[2];
        if (str != null && !str.equals("") && str.length() > 7) {
            strArr[0] = str.substring(0, 4);
            strArr[1] = str.substring(4);
        }
        return strArr;
    }

    private float cBE_(float f, String str, Paint paint) {
        if (str == null || paint == null) {
            LogUtil.h("HwHealthXAxisRenderer", "text or paint is invalid");
            return 0.0f;
        }
        if (Math.abs(this.g + Float.MAX_VALUE) < 1.0E-6d) {
            return Utils.convertDpToPixel(3.0f);
        }
        paint.getTextBounds(str, 0, str.length(), new Rect());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new a(-3.4028235E38f, 0.0f));
        arrayList.add(new a(-112.0f, 6.25f));
        arrayList.add(new a(-84.0f, 18.75f));
        arrayList.add(new a(-56.0f, 33.333332f));
        arrayList.add(new a(-28.0f, 44.444443f));
        arrayList.add(new a(0.0f, 50.0f));
        arrayList.add(new a(28.0f, 44.444443f));
        arrayList.add(new a(56.0f, 33.333332f));
        arrayList.add(new a(84.0f, 18.75f));
        arrayList.add(new a(112.0f, 6.25f));
        arrayList.add(new a(140.0f, 0.0f));
        arrayList.add(new a(Float.MAX_VALUE, 0.0f));
        float width = f - (r1.width() * 0.5f);
        float width2 = f + (r1.width() * 0.5f);
        float f2 = this.g;
        float f3 = width - f2;
        if ((width2 - f2) * f3 < 0.0f) {
            return b(arrayList, 0.0f);
        }
        float b2 = b(arrayList, Math.abs(f3));
        float b3 = b(arrayList, Math.abs(width2 - this.g));
        return b2 >= b3 ? b2 : b3;
    }

    private float cBF_(float f, String str, Paint paint) {
        return cBE_(f, str, paint) + this.b.acquireLayout().i();
    }

    private float b(List<a> list, float f) {
        ListIterator<a> listIterator = list.listIterator();
        float f2 = 0.0f;
        while (listIterator.hasNext()) {
            a next = listIterator.next();
            if (!listIterator.hasNext()) {
                break;
            }
            a aVar = list.get(listIterator.nextIndex());
            if (f > next.d && f <= aVar.d) {
                f2 = (((aVar.c - next.c) / (aVar.d - next.d)) * (f - next.d)) + next.c;
            }
        }
        return f2;
    }

    public Sorption b() {
        return this.j;
    }

    static class a {
        float c;
        float d;

        a(float f, float f2) {
            this.d = f;
            this.c = f2;
        }
    }

    public void c(Sorption sorption) {
        this.j = sorption;
    }

    public void d(int i) {
        this.f8877a = i;
    }

    protected void cBI_(Canvas canvas, float f, MPPointF mPPointF) {
        int i;
        int i2;
        float[] fArr;
        float abs;
        float f2;
        String str;
        int i3;
        String str2;
        b bVar;
        float f3;
        float labelRotationAngle = this.mXAxis.getLabelRotationAngle();
        boolean isCenterAxisLabelsEnabled = this.mXAxis.isCenterAxisLabelsEnabled();
        int i4 = this.mXAxis.mEntryCount * 2;
        float[] fArr2 = new float[i4];
        d(isCenterAxisLabelsEnabled, fArr2);
        this.mTrans.pointValuesToPixel(fArr2);
        b bVar2 = d;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        b bVar3 = bVar2;
        float f4 = 0.0f;
        String str3 = "";
        int i5 = 0;
        boolean z = true;
        float f5 = Float.MAX_VALUE;
        while (i5 < i4 && i5 < this.mXAxis.mEntries.length * 2) {
            float f6 = fArr2[i5];
            int i6 = i5 / 2;
            if (i6 < this.mXAxis.mEntries.length && this.mViewPortHandler.isInBoundsX(f6)) {
                String formattedValue = this.mXAxis.getValueFormatter().getFormattedValue(this.mXAxis.mEntries[i6], this.mXAxis);
                if (this.mXAxis.isAvoidFirstLastClippingEnabled()) {
                    f6 = d(i5, f6, formattedValue);
                }
                float f7 = f6;
                if (Math.abs(f7 - this.g) < f5 || i6 == this.f8877a) {
                    abs = Math.abs(this.g - f7);
                    f2 = f7;
                    i2 = i4;
                    str = formattedValue;
                    fArr = fArr2;
                    i3 = i6;
                    i = i5;
                    str2 = str;
                    bVar = new b(canvas, f7, f, mPPointF, z, this.mXAxis.mEntries[i6], labelRotationAngle);
                    f3 = f2;
                } else {
                    f2 = f7;
                    i = i5;
                    i2 = i4;
                    fArr = fArr2;
                    str2 = str3;
                    bVar = bVar3;
                    abs = f5;
                    str = formattedValue;
                    i3 = i6;
                    f3 = f4;
                }
                arrayList.add(new e(canvas, str, f2, c(f2, f, str), mPPointF, labelRotationAngle));
                if (i3 < this.mXAxis.mEntries.length) {
                    arrayList2.add(new b(canvas, f2, f, mPPointF, z, this.mXAxis.mEntries[i3], labelRotationAngle));
                    z = false;
                }
                f5 = abs;
                f4 = f3;
                str3 = str2;
                bVar3 = bVar;
            } else {
                i = i5;
                i2 = i4;
                fArr = fArr2;
            }
            i5 = i + 2;
            i4 = i2;
            fArr2 = fArr;
        }
        boolean z2 = (((double) Math.abs(this.g + Float.MAX_VALUE)) <= 1.0E-6d || !this.b.isMarkerViewEnable()) ? this.f8877a >= 0 : true;
        String str4 = str3;
        float f8 = f4;
        cBG_(z2, canvas, str4, f4, f, mPPointF, labelRotationAngle, bVar3);
        cBH_(canvas, f);
        c(f, f8, str4, arrayList, z2);
        e(f8, arrayList2, z2);
    }

    private void e(float f, List<b> list, boolean z) {
        for (b bVar : list) {
            if (!z || !nor.a(bVar.g, f)) {
                a(bVar);
            }
        }
    }

    private void c(float f, float f2, String str, List<e> list, boolean z) {
        for (e eVar : list) {
            if (!z || !nor.a(eVar.f8879a, f2) || !nor.a(eVar.i, c(f2, f, str))) {
                drawLabel(eVar.d, eVar.c, eVar.f8879a, eVar.i, eVar.b, eVar.e);
            }
        }
    }

    private void d(boolean z, float[] fArr) {
        for (int i = 0; i < fArr.length; i += 2) {
            if (z) {
                fArr[i] = this.mXAxis.mCenteredEntries[i / 2];
            } else {
                fArr[i] = this.mXAxis.mEntries[i / 2];
            }
        }
    }

    private void c(b bVar) {
        if (bVar != d) {
            Typeface typeface = this.mAxisLabelPaint.getTypeface();
            int alpha = this.mAxisLabelPaint.getAlpha();
            this.mAxisLabelPaint.setTypeface(Typeface.create("sans-serif-medium", 0));
            this.mAxisLabelPaint.setAlpha(HeartRateThresholdConfig.HEART_RATE_LIMIT);
            a(bVar);
            this.mAxisLabelPaint.setTypeface(typeface);
            this.mAxisLabelPaint.setAlpha(alpha);
        }
    }

    private float d(int i, float f, String str) {
        if (i != (this.mXAxis.mEntryCount * 2) - 2 || this.mXAxis.mEntryCount <= 1) {
            return i == 0 ? f + (Utils.calcTextWidth(this.mAxisLabelPaint, str) / 2.0f) : f;
        }
        float calcTextWidth = Utils.calcTextWidth(this.mAxisLabelPaint, str);
        return (calcTextWidth <= this.mViewPortHandler.offsetRight() * 2.0f || f + calcTextWidth <= this.mViewPortHandler.getChartWidth()) ? f : f - (calcTextWidth / 2.0f);
    }

    private void cBG_(boolean z, Canvas canvas, String str, float f, float f2, MPPointF mPPointF, float f3, b bVar) {
        if (z) {
            e(new e(canvas, str, f, c(f, f2, str), mPPointF, f3));
            c(bVar);
        }
    }

    private void e(boolean z, b bVar, IAxisValueFormatter iAxisValueFormatter, float f) {
        if (z || !bVar.b) {
            return;
        }
        HwHealthBaseScrollBarLineChart.h hVar = (HwHealthBaseScrollBarLineChart.h) iAxisValueFormatter;
        String e2 = hVar.e(bVar.e, this.mXAxis, true);
        float calcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, e2) + Utils.convertDpToPixel(6.0f);
        float f2 = 720;
        this.mTrans.pointValuesToPixel(new float[]{bVar.e - f2, 0.0f, bVar.e + f2, 0.0f});
        if (hVar.a(bVar.e)) {
            if (!b(bVar.g)) {
                drawLabel(bVar.c, e2, this.f, c(bVar.g, f, e2) - calcTextHeight, bVar.d, bVar.f8878a);
                return;
            } else {
                drawLabel(bVar.c, e2, bVar.g, c(bVar.g, f, e2) - calcTextHeight, bVar.d, bVar.f8878a);
                return;
            }
        }
        drawLabel(bVar.c, e2, this.f, c(bVar.g, f, e2) - calcTextHeight, bVar.d, bVar.f8878a);
    }

    private void c(boolean z, b bVar, HwHealthBaseScrollBarLineChart.g gVar) {
        if (z || !bVar.b) {
            return;
        }
        String a2 = gVar.a(bVar.e, this.mXAxis, true);
        float calcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, a2) + Utils.convertDpToPixel(6.0f);
        float f = 720;
        this.mTrans.pointValuesToPixel(new float[]{bVar.e - f, 0.0f, bVar.e + f, 0.0f});
        if (gVar.c(bVar.e)) {
            if (!b(bVar.g)) {
                drawLabel(bVar.c, a2, this.f, c(bVar.g, bVar.f, a2) - calcTextHeight, bVar.d, bVar.f8878a);
                return;
            } else {
                drawLabel(bVar.c, a2, bVar.g, c(bVar.g, bVar.f, a2) - calcTextHeight, bVar.d, bVar.f8878a);
                return;
            }
        }
        drawLabel(bVar.c, a2, this.f, c(bVar.g, bVar.f, a2) - calcTextHeight, bVar.d, bVar.f8878a);
    }

    private void cBH_(Canvas canvas, float f) {
        if (this.b.getGlobalPointFlag()) {
            Paint paint = new Paint(1);
            paint.setColor(this.b.getGlobalPointColor());
            paint.setAntiAlias(true);
            if (!nng.d(this.c)) {
                float j = this.b.acquireChartAnchor().j();
                float convertDpToPixel = Utils.convertDpToPixel(26.0f);
                canvas.drawCircle(j - convertDpToPixel, c(this.b.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f), f, "") + Utils.convertDpToPixel(5.0f), Utils.convertDpToPixel(2.0f), paint);
                return;
            }
            float f2 = this.b.acquireChartAnchor().f();
            float convertDpToPixel2 = Utils.convertDpToPixel(26.0f);
            canvas.drawCircle(f2 + convertDpToPixel2, c(this.b.acquireChartAnchor().f() + Utils.convertDpToPixel(26.0f), f, "") + Utils.convertDpToPixel(5.0f), Utils.convertDpToPixel(2.0f), paint);
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        float f8879a;
        MPPointF b;
        String c;
        Canvas d;
        float e;
        float i;

        e(Canvas canvas, String str, float f, float f2, MPPointF mPPointF, float f3) {
            this.d = canvas;
            this.c = str;
            this.f8879a = f;
            this.i = f2;
            this.b = mPPointF;
            this.e = f3;
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        float f8878a;
        boolean b;
        Canvas c;
        MPPointF d;
        float e;
        float f;
        float g;

        b() {
        }

        b(Canvas canvas, float f, float f2, MPPointF mPPointF, boolean z, float f3, float f4) {
            this.c = canvas;
            this.g = f;
            this.f = f2;
            this.d = mPPointF;
            this.b = z;
            this.e = f3;
            this.f8878a = f4;
        }
    }

    private void a(b bVar) {
        IAxisValueFormatter valueFormatter = this.mXAxis.getValueFormatter();
        if (this instanceof nnt) {
            if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.h) {
                d(bVar);
            } else if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.g) {
                b(bVar);
            }
        }
    }

    private void d(b bVar) {
        float f;
        IAxisValueFormatter valueFormatter = this.mXAxis.getValueFormatter();
        if (Math.abs(this.f - bVar.g) > 1.0E-6d) {
            this.f = bVar.g;
        }
        if (!(valueFormatter instanceof HwHealthBaseScrollBarLineChart.h)) {
            LogUtil.h("HwHealthXAxisRenderer", "drawUpperLabelsForWeek valueFormatter not instanceof UnixWeekAxisValueFormatter");
            return;
        }
        boolean z = false;
        String e2 = ((HwHealthBaseScrollBarLineChart.h) valueFormatter).e(bVar.e, this.mXAxis, false);
        if (e() && this.h > 0) {
            f = bVar.f - 38.0f;
        } else {
            f = bVar.f;
        }
        if (!TextUtils.isEmpty(e2)) {
            float calcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, e2) + Utils.convertDpToPixel(6.0f);
            if (!TextUtils.isEmpty(e2)) {
                if (b(bVar.g)) {
                    drawLabel(bVar.c, e2, this.f, c(bVar.g, f, e2) - calcTextHeight, bVar.d, bVar.f8878a);
                } else {
                    drawLabel(bVar.c, e2, bVar.g, c(bVar.g, f, e2) - calcTextHeight, bVar.d, bVar.f8878a);
                }
                z = true;
            }
        }
        e(z, bVar, valueFormatter, f);
    }

    private void b(b bVar) {
        if (!(this.mXAxis.getValueFormatter() instanceof HwHealthBaseScrollBarLineChart.g)) {
            LogUtil.h("HwHealthXAxisRenderer", "drawUpperLabelsForYear getValueFormatter not instanceof UnixYearAxisValueFormatter");
            return;
        }
        HwHealthBaseScrollBarLineChart.g gVar = (HwHealthBaseScrollBarLineChart.g) this.mXAxis.getValueFormatter();
        if (Math.abs(this.f - bVar.g) > 1.0E-6d) {
            this.f = bVar.g;
        }
        boolean z = false;
        String a2 = gVar.a(bVar.e, this.mXAxis, false);
        if (!TextUtils.isEmpty(a2)) {
            float calcTextHeight = Utils.calcTextHeight(this.mAxisLabelPaint, a2) + Utils.convertDpToPixel(6.0f);
            if (!TextUtils.isEmpty(a2)) {
                if (b(bVar.g)) {
                    drawLabel(bVar.c, a2, this.f, c(bVar.g, bVar.f, a2) - calcTextHeight, bVar.d, bVar.f8878a);
                } else {
                    drawLabel(bVar.c, a2, bVar.g, c(bVar.g, bVar.f, a2) - calcTextHeight, bVar.d, bVar.f8878a);
                }
                z = true;
            }
        }
        c(z, bVar, gVar);
    }

    private boolean b(float f) {
        return !nng.d(this.c) ? f < this.f : f > this.f;
    }

    private float c(float f, float f2, String str) {
        if (this.j == Sorption.Y_OFFSET_CEIL) {
            return f2 + this.b.acquireLayout().f();
        }
        if (this.j != Sorption.Y_OFFSET_FLOOR) {
            return f2;
        }
        return ((f2 - cBF_(f, str, this.mAxisLabelPaint)) - this.mXAxis.mLabelHeight) - Utils.convertDpToPixel(2.5f);
    }

    public void d(float f) {
        this.g = f;
    }
}
