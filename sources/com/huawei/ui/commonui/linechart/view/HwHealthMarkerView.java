package com.huawei.ui.commonui.linechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import defpackage.nmq;
import defpackage.nng;
import defpackage.nrn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthMarkerView extends MarkerView {
    private static final SpannableString d = new SpannableString("--");

    /* renamed from: a, reason: collision with root package name */
    private HwHealthBaseBarLineChart f8899a;
    private boolean b;
    private boolean c;
    private List<a> e;
    private OnMarkViewTextNotify f;
    private HealthTextView g;
    private int h;
    private OnMarkViewGlobalPoint i;
    private float j;
    private SpannableString m;
    private HealthTextView o;

    public interface OnMarkViewGlobalPoint {
        void onTextGlobalPoint();
    }

    public interface OnMarkViewTextNotify {
        void onTextChanged(String str, List<a> list);
    }

    public HwHealthMarkerView(Context context, int i, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, i);
        this.h = -1;
        this.m = d;
        this.e = new ArrayList();
        this.j = -3.4028235E38f;
        this.f = null;
        this.i = null;
        this.f8899a = null;
        this.c = true;
        this.b = true;
        this.o = (HealthTextView) findViewById(R.id.tvTime);
        this.g = (HealthTextView) findViewById(R.id.tvContent);
        this.f8899a = hwHealthBaseBarLineChart;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    @Deprecated
    public void refreshContent(Entry entry, Highlight highlight) {
        LogUtil.b("HwHealthMarkerView", "refreshContent deprecated");
    }

    public void e(Entry entry, Highlight highlight, int i) {
        if (!(entry instanceof HwEntrys)) {
            LogUtil.h("HwHealthMarkerView", "entryArg not instanceof HwHealthLineData.HwEntrys,logic error,warning!!!");
            return;
        }
        List<HwEntrys.HwDataEntry> entries = ((HwEntrys) entry).getEntries();
        if (entries.size() == 0) {
            LogUtil.h("HwHealthMarkerView", "markview hwDataEntries size 0,return");
            return;
        }
        setTextContent(highlight);
        Drawable drawable = getResources().getDrawable(R$drawable.marker_view_background);
        if (!(drawable instanceof GradientDrawable)) {
            LogUtil.h("HwHealthMarkerView", "!(drawable instanceof GradientDrawable)");
            return;
        }
        this.e.clear();
        cCT_((GradientDrawable) drawable, entries, highlight, i);
        super.refreshContent(entry, highlight);
    }

    private void cCT_(GradientDrawable gradientDrawable, List<HwEntrys.HwDataEntry> list, Highlight highlight, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Iterator<HwEntrys.HwDataEntry> it = list.iterator();
        if (list.size() == 1 && i == 1) {
            HwEntrys.HwDataEntry next = it.next();
            if (next.getHwDataEntry() == null) {
                this.e.add(new a(next.getDataSet().acquireUnit(), (HwHealthBaseBarLineDataSet) next.getDataSet()));
                return;
            }
            IAxisValueFormatter cCS_ = cCS_(next, next.getDataSet().acquireMarkViewTextDigitalCount(), new ForegroundColorSpan(-1), spannableStringBuilder);
            if (cCS_ == null) {
                return;
            }
            e(highlight, next, cCS_);
            gradientDrawable.setColor(next.getDataSet().acquireColor());
            a();
        } else {
            while (it.hasNext()) {
                HwEntrys.HwDataEntry next2 = it.next();
                if (next2.getHwDataEntry() == null) {
                    this.e.add(new a(next2.getDataSet().acquireUnit(), (HwHealthBaseBarLineDataSet) next2.getDataSet()));
                } else {
                    IAxisValueFormatter cCS_2 = cCS_(next2, next2.getDataSet().acquireMarkViewTextDigitalCount(), new ForegroundColorSpan(next2.getDataSet().acquireMarkViewTextColor()), spannableStringBuilder);
                    if (cCS_2 == null) {
                        return;
                    }
                    this.e.add(new a(next2.getHwDataEntry(), next2.getDataSet().acquireUnit(), (HwHealthBaseBarLineDataSet) next2.getDataSet(), cCS_2));
                    if (it.hasNext()) {
                        spannableStringBuilder.append((CharSequence) new SpannableString("\n"));
                    }
                }
            }
            gradientDrawable.setColor(Color.argb(120, 0, 0, 0));
            c();
        }
        this.g.setText(spannableStringBuilder);
        setBackground(gradientDrawable);
    }

    public void b() {
        this.m = d;
        this.e.clear();
    }

    public void setOnMarkViewTextNotify(OnMarkViewTextNotify onMarkViewTextNotify) {
        this.f = onMarkViewTextNotify;
    }

    public void setOnMarkViewGlobalPoint(OnMarkViewGlobalPoint onMarkViewGlobalPoint) {
        this.i = onMarkViewGlobalPoint;
    }

    public void e(float f) {
        this.j = f;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public boolean f8900a = true;
        public HwHealthBaseEntry b;
        public IAxisValueFormatter c;
        public String d;
        public HwHealthBaseBarLineDataSet e;

        public a(String str, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
            this.d = str;
            this.e = hwHealthBaseBarLineDataSet;
        }

        public a(HwHealthBaseEntry hwHealthBaseEntry, String str, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IAxisValueFormatter iAxisValueFormatter) {
            this.b = hwHealthBaseEntry;
            this.d = str;
            this.e = hwHealthBaseBarLineDataSet;
            this.c = iAxisValueFormatter;
        }
    }

    public boolean d() {
        return this.h != -1;
    }

    public long e() {
        return this.h * 60000;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float f, float f2) {
        int save = canvas.save();
        Chart chartView = getChartView();
        if (chartView == null) {
            LogUtil.h("HwHealthMarkerView", "markview draw chart null,default draw");
            draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        if (this.f == null) {
            float f3 = (-(getWidth() / 2)) + f;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            } else if (f3 > chartView.getWidth() - getWidth()) {
                f3 = chartView.getWidth() - getWidth();
            }
            canvas.translate(f3, 0.0f);
            draw(canvas);
        } else {
            setOnMarkViewTextNotify(f);
        }
        if (this.i != null && c(f)) {
            this.i.onTextGlobalPoint();
        }
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.translate(this.j, chartView.getViewPortHandler().contentBottom() + chartView.getXAxis().getYOffset());
        canvas.scale(1.0f, -1.0f);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        paint.setAlpha(0);
        paint.setXfermode(porterDuffXfermode);
        cCV_(paint, canvas, porterDuffXfermode, new Path(), Utils.convertDpToPixel(36.0f), Utils.convertDpToPixel(13.5f));
        cCU_(canvas, paint);
        canvas.restoreToCount(save2);
    }

    private boolean c(float f) {
        return nng.d(BaseApplication.getContext()) ? f >= (this.f8899a.acquireChartAnchor().f() + Utils.convertDpToPixel(26.0f)) - 1.0f && f <= (this.f8899a.acquireChartAnchor().f() + Utils.convertDpToPixel(26.0f)) + 1.0f : f >= (this.f8899a.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f)) - 1.0f && f <= (this.f8899a.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f)) + 1.0f;
    }

    private int getMarkerViewButtonColor() {
        List dataSets = this.f8899a.getData().getDataSets();
        if (dataSets.size() == 0) {
            return Color.argb(255, 250, 101, 33);
        }
        HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = (HwHealthBaseBarLineDataSet) dataSets.get(0);
        if (hwHealthBaseBarLineDataSet instanceof HwHealthBarDataSet) {
            return ((HwHealthBarDataSet) hwHealthBaseBarLineDataSet).acquireFocusColor();
        }
        if (dataSets.size() == 1) {
            return hwHealthBaseBarLineDataSet.getColor();
        }
        return nrn.d(R$color.colorForeground);
    }

    private void cCR_(Canvas canvas, Paint paint) {
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(1.0f));
        if (getChartView() == null || !(getChartView() instanceof HwHealthBaseBarLineChart)) {
            LogUtil.h("HwHealthMarkerView", "markview draw chart null,default draw");
            return;
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = (HwHealthBaseBarLineChart) getChartView();
        Layout acquireLayout = hwHealthBaseBarLineChart.acquireLayout();
        nmq acquireChartAnchor = hwHealthBaseBarLineChart.acquireChartAnchor();
        float j = acquireLayout.j();
        float g = acquireLayout.g();
        float convertDpToPixel = Utils.convertDpToPixel(0.25f);
        float j2 = acquireLayout.j();
        float g2 = acquireLayout.g();
        float i = acquireChartAnchor.i();
        paint.setColor(nrn.d(R$color.chart_highline_color));
        canvas.drawLine(0.0f, j + g + convertDpToPixel, 0.0f, j2 + g2 + i, paint);
    }

    private void cCQ_(Canvas canvas, int[] iArr, float[] fArr, Paint paint) {
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        if (getChartView() == null || !(getChartView() instanceof HwHealthBaseBarLineChart)) {
            LogUtil.h("HwHealthMarkerView", "markview draw chart null,default draw");
            return;
        }
        HwHealthBaseBarLineChart hwHealthBaseBarLineChart = (HwHealthBaseBarLineChart) getChartView();
        Layout acquireLayout = hwHealthBaseBarLineChart.acquireLayout();
        nmq acquireChartAnchor = hwHealthBaseBarLineChart.acquireChartAnchor();
        float j = acquireLayout.j() * 2.0f;
        float j2 = acquireLayout.j() + acquireLayout.g() + acquireChartAnchor.i();
        paint.setShader(new LinearGradient(0.0f, j, 0.0f, j2, iArr, fArr, Shader.TileMode.CLAMP));
        canvas.drawLine(0.0f, j, 0.0f, j2, paint);
    }

    private String b(float f) {
        IAxisValueFormatter valueFormatter = this.f8899a.getXAxis().getValueFormatter();
        if (valueFormatter instanceof HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) {
            return ((HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter) valueFormatter).getFormattedValueForMarkerView(f, this.f8899a.getXAxis());
        }
        return this.f8899a.getXAxis().getValueFormatter().getFormattedValue(f, this.f8899a.getXAxis());
    }

    public void d(boolean z) {
        this.b = z;
    }

    private IAxisValueFormatter cCS_(HwEntrys.HwDataEntry hwDataEntry, int i, ForegroundColorSpan foregroundColorSpan, SpannableStringBuilder spannableStringBuilder) {
        SpannableString spannableString = new SpannableString(UnitUtil.e(hwDataEntry.getHwDataEntry().getY(), 1, i) + hwDataEntry.getDataSet().acquireUnit());
        spannableString.setSpan(foregroundColorSpan, 0, spannableString.length(), 34);
        spannableStringBuilder.append((CharSequence) spannableString);
        if (hwDataEntry.getDataSet() instanceof HwHealthLineDataSet) {
            return this.f8899a.getAxis(((HwHealthLineDataSet) hwDataEntry.getDataSet()).getAxisDependencyExt()).getValueFormatter();
        }
        if (hwDataEntry.getDataSet() instanceof HwHealthBarDataSet) {
            return this.f8899a.getAxis(((HwHealthBarDataSet) hwDataEntry.getDataSet()).getAxisDependencyExt()).getValueFormatter();
        }
        LogUtil.b("HwHealthMarkerView", "unrecoginzed dataSet,unsupport getAxisDependencyExt");
        return null;
    }

    private void c() {
        this.g.setSingleLine(false);
        if (this.o.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.getLayoutParams();
            if (!nng.d(getContext())) {
                layoutParams.gravity = 3;
            } else {
                layoutParams.gravity = 5;
            }
            this.o.setLayoutParams(layoutParams);
        }
    }

    private void e(Highlight highlight, HwEntrys.HwDataEntry hwDataEntry, IAxisValueFormatter iAxisValueFormatter) {
        if (this.f8899a.isRenderUsePaintAsBackground() && this.f8899a.isManualReferenceLineEnabled()) {
            this.e.add(new a(new HwHealthBaseEntry(highlight.getX(), this.f8899a.getManualReferenceLineValue()), hwDataEntry.getDataSet().acquireUnit(), (HwHealthBaseBarLineDataSet) hwDataEntry.getDataSet(), iAxisValueFormatter));
        } else {
            this.e.add(new a(hwDataEntry.getHwDataEntry(), hwDataEntry.getDataSet().acquireUnit(), (HwHealthBaseBarLineDataSet) hwDataEntry.getDataSet(), iAxisValueFormatter));
        }
    }

    private void a() {
        this.g.setSingleLine(false);
        if (this.o.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.o.getLayoutParams();
            layoutParams.gravity = 1;
            this.o.setLayoutParams(layoutParams);
        }
    }

    private void setTextContent(Highlight highlight) {
        this.h = (int) highlight.getX();
        this.m = new SpannableString(b(highlight.getX()));
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-1);
        SpannableString spannableString = this.m;
        spannableString.setSpan(foregroundColorSpan, 0, spannableString.length(), 34);
        this.o.setText(this.m);
        this.o.setSingleLine(false);
    }

    private void setOnMarkViewTextNotify(float f) {
        if (this.e.size() == 0 || f == this.f8899a.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f)) {
            return;
        }
        this.f.onTextChanged(this.m.toString(), this.e);
    }

    private void cCV_(Paint paint, Canvas canvas, PorterDuffXfermode porterDuffXfermode, Path path, float f, float f2) {
        path.moveTo(-f, 0.0f);
        float f3 = f * 3.0f;
        float f4 = f * 2.0f;
        path.cubicTo((-f3) / 6.0f, 0.0f, (-f4) / 6.0f, f2, 0.0f, f2);
        path.cubicTo(f4 / 6.0f, f2, f3 / 6.0f, 0.0f, f, 0.0f);
        canvas.drawPath(path, paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(Utils.convertDpToPixel(7.0f), 0.0f, Utils.convertDpToPixel(-3.0f), nrn.d(getContext(), R$color.health_chart_eye_shadow_color));
        paint.setColor(nrn.d(getContext(), R$color.colorBackground));
        canvas.drawCircle(0.0f, 0.0f, Utils.convertDpToPixel(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        paint.setXfermode(porterDuffXfermode);
        paint.setAlpha(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2.0f);
        canvas.drawCircle(0.0f, 0.0f, Utils.convertDpToPixel(11.0f), paint);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(0.0f, Utils.convertDpToPixel(-1.0f));
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(nrn.d(getContext(), R$color.health_chart_eye_color));
        boolean isAntiAlias = paint.isAntiAlias();
        paint.setAntiAlias(true);
        canvas.drawCircle(0.0f, 0.0f, Utils.convertDpToPixel(11.0f), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.convertDpToPixel(0.5f));
        paint.setColor(nrn.d(getContext(), R$color.health_chart_eye_shadow_color));
        paint.setAntiAlias(true);
        canvas.drawCircle(0.0f, 0.0f, Utils.convertDpToPixel(11.25f), paint);
        paint.setAntiAlias(isAntiAlias);
        paint.setStyle(Paint.Style.STROKE);
        canvas.translate(0.0f, Utils.convertDpToPixel(1.0f));
    }

    private void cCU_(Canvas canvas, Paint paint) {
        if (this.c) {
            if (this.b) {
                int markerViewButtonColor = getMarkerViewButtonColor();
                int argb = Color.argb(0, Color.red(markerViewButtonColor), Color.green(markerViewButtonColor), Color.blue(markerViewButtonColor));
                cCQ_(canvas, new int[]{argb, markerViewButtonColor, argb}, new float[]{0.0f, 0.5f, 1.0f}, paint);
                return;
            }
            cCR_(canvas, paint);
        }
    }

    public List<a> getData() {
        return this.e;
    }

    public SpannableString getTime() {
        return this.m;
    }
}
