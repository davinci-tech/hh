package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;

/* loaded from: classes6.dex */
public class npg extends LegendRenderer {

    /* renamed from: a, reason: collision with root package name */
    private Context f15421a;
    private HwHealthBaseBarLineChart e;

    public npg(Context context, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler, legend);
        this.f15421a = context;
        this.e = hwHealthBaseBarLineChart;
    }

    @Override // com.github.mikephil.charting.renderer.LegendRenderer
    public void renderLegend(Canvas canvas) {
        if (this.mLegend.isEnabled()) {
            d(this.mLegend);
            if (this.mLegend.getEntries().length == 3) {
                return;
            }
            Typeface typeface = this.mLegend.getTypeface();
            if (typeface != null) {
                this.mLegendLabelPaint.setTypeface(typeface);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            cDJ_(canvas, this.e.acquireChartAnchor().cBn_(), this.e.acquireChartAnchor().cBn_().top + (-this.mLegendLabelPaint.getFontMetrics().top), this.mLegend.getEntries());
        }
    }

    private void d(Legend legend) {
        if (!nng.d(this.f15421a)) {
            legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        } else {
            legend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        }
    }

    private void cDJ_(Canvas canvas, Rect rect, float f, LegendEntry[] legendEntryArr) {
        if (this.mLegend.getEntries().length == 1) {
            if (!nng.d(this.f15421a)) {
                float f2 = rect.left;
                this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
                drawLabel(canvas, f2, f, legendEntryArr[0].label);
                return;
            } else {
                float f3 = rect.right;
                this.mLegendLabelPaint.setTextAlign(Paint.Align.RIGHT);
                drawLabel(canvas, f3, f, legendEntryArr[0].label);
                return;
            }
        }
        if (this.mLegend.getEntries().length == 2) {
            float f4 = rect.left;
            float f5 = rect.right;
            if (!nng.d(this.f15421a)) {
                this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
                drawLabel(canvas, f4, f, legendEntryArr[0].label);
                this.mLegendLabelPaint.setTextAlign(Paint.Align.RIGHT);
                drawLabel(canvas, f5, f, legendEntryArr[1].label);
                return;
            }
            this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
            drawLabel(canvas, f4, f, legendEntryArr[1].label);
            this.mLegendLabelPaint.setTextAlign(Paint.Align.RIGHT);
            drawLabel(canvas, f5, f, legendEntryArr[0].label);
        }
    }
}
