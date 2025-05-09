package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.health.knit.section.chart.BarChartRender;
import com.huawei.ui.commonui.linechart.icommon.HwHealthBarDataProvider;

/* loaded from: classes3.dex */
public class edg extends BarChartRender {
    private boolean i;

    @Override // defpackage.nnb, com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return true;
    }

    public edg(HwHealthBarDataProvider hwHealthBarDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, boolean z) {
        super(hwHealthBarDataProvider, chartAnimator, viewPortHandler);
        this.i = z;
    }

    @Override // defpackage.nnb
    protected void cBx_(Canvas canvas, RectF rectF, Paint paint, float f, float f2) {
        float f3 = f2 / 2.0f;
        int i = (int) (rectF.top + ((int) f3));
        if (f > 0.0f) {
            canvas.save();
            canvas.clipRect(rectF.left, rectF.top, rectF.right, i);
            canvas.drawRoundRect(new RectF(rectF.left, rectF.top, rectF.right, rectF.top + f2), f3, f3, paint);
            f -= f3;
            canvas.restore();
        }
        if (f > Math.pow(10.0d, -5.0d)) {
            canvas.drawRect(rectF.left, i, rectF.right, (rectF.bottom - 0.0f) - Utils.convertDpToPixel(4.0f), paint);
        }
    }

    @Override // defpackage.nnb
    protected float cBt_(RectF rectF, float f, float f2, float f3) {
        if (!this.i) {
            return super.cBt_(rectF, f, f2, f3);
        }
        if (f >= 1.0E-7f) {
            return f;
        }
        rectF.top -= f2;
        rectF.bottom -= f2 / 2.0f;
        return Math.abs(rectF.top - rectF.bottom);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005b  */
    @Override // defpackage.nnb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void cBv_(android.graphics.Canvas r11, java.lang.String r12, float r13, float r14, int r15) {
        /*
            r10 = this;
            android.graphics.Paint r13 = r10.mValuePaint
            r13.setColor(r15)
            android.graphics.Paint r13 = r10.mValuePaint
            android.graphics.Paint$Align r0 = android.graphics.Paint.Align.RIGHT
            r13.setTextAlign(r0)
            boolean r13 = android.text.TextUtils.isEmpty(r12)
            if (r13 == 0) goto L13
            return
        L13:
            java.lang.String r13 = "\\;"
            java.lang.String[] r12 = r12.split(r13)
            android.content.Context r13 = com.huawei.haf.application.BaseApplication.e()
            r0 = 1073741824(0x40000000, float:2.0)
            int r13 = defpackage.nrr.e(r13, r0)
            int r0 = r11.getWidth()
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L3f
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            boolean r0 = health.compact.a.LanguageUtil.bc(r0)
            if (r0 == 0) goto L38
            float r13 = (float) r13
            r0 = r1
            goto L41
        L38:
            int r0 = r11.getWidth()
            int r0 = r0 - r13
            float r13 = (float) r0
            goto L40
        L3f:
            r13 = 0
        L40:
            r0 = r2
        L41:
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()
            r4 = 1094713344(0x41400000, float:12.0)
            int r3 = defpackage.nrr.e(r3, r4)
            float r3 = (float) r3
            int r4 = r12.length
            if (r4 != r1) goto L5b
            r5 = r12[r2]
            r3 = r10
            r4 = r11
            r6 = r13
            r7 = r14
            r8 = r15
            r9 = r0
            r3.afU_(r4, r5, r6, r7, r8, r9)
            return
        L5b:
            int r4 = r12.length
            r5 = 2
            if (r4 != r5) goto L71
            r5 = r12[r2]
            float r7 = r14 - r3
            r3 = r10
            r4 = r11
            r6 = r13
            r8 = r15
            r9 = r0
            r3.afU_(r4, r5, r6, r7, r8, r9)
            r5 = r12[r1]
            r7 = r14
            r3.afU_(r4, r5, r6, r7, r8, r9)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.edg.cBv_(android.graphics.Canvas, java.lang.String, float, float, int):void");
    }

    public void afU_(Canvas canvas, String str, float f, float f2, int i, boolean z) {
        this.mValuePaint.setColor(i);
        if (z) {
            this.mValuePaint.setTextAlign(Paint.Align.LEFT);
        } else {
            this.mValuePaint.setTextAlign(Paint.Align.RIGHT);
        }
        canvas.drawText(str, f, f2, this.mValuePaint);
    }
}
