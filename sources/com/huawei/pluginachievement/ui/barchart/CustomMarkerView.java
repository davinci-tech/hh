package com.huawei.pluginachievement.ui.barchart;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public class CustomMarkerView extends MarkerView {

    /* renamed from: a, reason: collision with root package name */
    private MPPointF f8434a;
    private boolean b;
    private Context e;

    public CustomMarkerView(Context context, int i) {
        super(context, i);
        this.f8434a = new MPPointF();
        this.e = context;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void refreshContent(Entry entry, Highlight highlight) {
        super.refreshContent(entry, highlight);
    }

    @Override // com.github.mikephil.charting.components.MarkerView
    public void setOffset(float f, float f2) {
        this.f8434a.x = f;
        this.f8434a.y = f2;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public MPPointF getOffset() {
        return this.f8434a;
    }

    public void setIsRun(boolean z) {
        this.b = z;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public MPPointF getOffsetForDrawingAtPoint(float f, float f2) {
        MPPointF offset = getOffset();
        Chart chartView = getChartView();
        float width = getWidth();
        offset.y = -getHeight();
        if (chartView != null) {
            if (f > chartView.getWidth() - width) {
                if (this.b) {
                    a(this.e, R.drawable.pic_tip_run_right_bottom);
                } else {
                    a(this.e, R.drawable.pic_tip_step_right_bottom);
                }
                offset.x = -(width - 44.0f);
                LogUtil.a("CustomMarkerView", "getOffsetForDrawingAtPoint() offset.x right = ", Float.valueOf(offset.x));
            } else {
                offset.x = 0.0f;
                float f3 = width / 2.0f;
                if (f > f3) {
                    if (this.b) {
                        a(this.e, R.drawable.pic_tip_run_middle_bottom);
                    } else {
                        a(this.e, R.drawable.pic_tip_step_middle_bottom);
                    }
                    offset.x = -f3;
                    LogUtil.a("CustomMarkerView", "getOffsetForDrawingAtPoint() offset.x middle = ", Float.valueOf(offset.x));
                } else {
                    if (this.b) {
                        a(this.e, R.drawable.pic_tip_run_left_bottom);
                    } else {
                        a(this.e, R.drawable.pic_tip_step_left_bottom);
                    }
                    offset.x = -44.0f;
                    LogUtil.a("CustomMarkerView", "getOffsetForDrawingAtPoint() offset.x left = ", Float.valueOf(offset.x));
                }
            }
        }
        return offset;
    }

    @Override // com.github.mikephil.charting.components.MarkerView, com.github.mikephil.charting.components.IMarker
    public void draw(Canvas canvas, float f, float f2) {
        MPPointF offsetForDrawingAtPoint = getOffsetForDrawingAtPoint(f, f2);
        int save = canvas.save();
        canvas.translate(f + offsetForDrawingAtPoint.x, f2 + offsetForDrawingAtPoint.y);
        draw(canvas);
        canvas.restoreToCount(save);
    }

    private void a(Context context, int i) {
        ((ImageView) findViewById(R.id.img_here)).setBackgroundResource(i);
    }
}
