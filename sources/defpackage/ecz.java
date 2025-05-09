package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.renderer.YAxisRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes3.dex */
public class ecz extends YAxisRenderer {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11957a;
    private Paint b;

    public ecz(ViewPortHandler viewPortHandler, YAxis yAxis, Transformer transformer, boolean z) {
        super(viewPortHandler, yAxis, transformer);
        this.f11957a = z;
        this.b = new Paint();
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer
    public void drawYLabels(Canvas canvas, float f, float[] fArr, float f2) {
        int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
        float labelXOffset = this.mYAxis.getLabelXOffset();
        int save = canvas.save();
        this.b.set(this.mAxisLabelPaint);
        if (this.f11957a) {
            canvas.translate(canvas.getWidth(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
            canvas.translate(canvas.getWidth() - ((f + labelXOffset) * 2.0f), 0.0f);
            this.b.setTextAlign(Paint.Align.RIGHT);
        }
        for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled() ? 1 : 0; i2 < i; i2++) {
            canvas.drawText(this.mYAxis.getFormattedLabel(i2), f + labelXOffset, fArr[(i2 * 2) + 1] + f2, this.b);
        }
        canvas.restoreToCount(save);
    }
}
