package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;

/* loaded from: classes9.dex */
public class put extends nnr {
    public put(Context context, ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        super(context, viewPortHandler, yAxis, transformer, hwHealthBaseBarLineChart);
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxis(float f, float f2, boolean z) {
        this.mAxis.mEntryCount = 5;
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        this.mAxis.mEntries[0] = 0.0f;
        this.mAxis.mEntries[1] = 29.0f;
        this.mAxis.mEntries[2] = 59.0f;
        this.mAxis.mEntries[3] = 79.0f;
        this.mAxis.mEntries[4] = 99.0f;
    }

    @Override // defpackage.nnr, com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderGridLines(Canvas canvas) {
        int d;
        if (this.mYAxis.isEnabled()) {
            if (this.mYAxis.isDrawGridLinesEnabled()) {
                canvas.clipRect(new RectF(0.0f, 0.0f, this.mViewPortHandler.getChartWidth(), this.mViewPortHandler.getChartHeight()));
                this.mGridPaint.setColor(this.mYAxis.getGridColor());
                this.mGridPaint.setStrokeWidth(nrr.e(this.b, 0.5f));
                Path path = this.mRenderGridLinesPath;
                path.reset();
                float[] transformedPositions = getTransformedPositions();
                for (int i = 0; i < transformedPositions.length; i += 2) {
                    int gridColor = this.mYAxis.getGridColor();
                    if (((HwHealthYAxis) this.mYAxis).c()) {
                        d = Color.argb(13, Color.red(gridColor), Color.green(gridColor), Color.blue(gridColor));
                    } else {
                        d = ((HwHealthYAxis) this.mYAxis).d();
                    }
                    this.mGridPaint.setColor(d);
                    canvas.drawPath(linePath(path, i, transformedPositions), this.mGridPaint);
                    path.reset();
                    this.mGridPaint.setColor(gridColor);
                }
            }
            if (this.mYAxis.isDrawZeroLineEnabled()) {
                drawZeroLine(canvas);
            }
        }
    }
}
