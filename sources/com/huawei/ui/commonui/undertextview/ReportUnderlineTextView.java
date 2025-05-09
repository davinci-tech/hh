package com.huawei.ui.commonui.undertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes8.dex */
public class ReportUnderlineTextView extends HealthTextView {
    private int c;
    private final Paint d;
    private int e;

    public ReportUnderlineTextView(Context context) {
        this(context, null);
    }

    public ReportUnderlineTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReportUnderlineTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Paint();
        this.c = 0;
        cHg_(context, attributeSet, i);
    }

    private void cHg_(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ReportUnderlineTextView);
        this.e = obtainStyledAttributes.getColor(R$styleable.ReportUnderlineTextView_underline_color, 0);
        this.c = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ReportUnderlineTextView_underline_height, 5);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4 + this.c);
    }

    @Override // com.huawei.ui.commonui.healthtextview.HealthTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        this.d.setColor(this.e);
        this.d.setStrokeWidth(this.c);
        int lineCount = getLineCount();
        if (lineCount <= 0) {
            LogUtil.h("ReportUnderlineTextView", "onDraw lineCount = ", Integer.valueOf(lineCount));
            return;
        }
        int height = getHeight() / lineCount;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        if (this.c > height) {
            this.c = height;
        }
        int gravity = getGravity();
        int i3 = 0;
        while (i3 < lineCount) {
            float lineWidth = getLayout().getLineWidth(i3);
            i3++;
            int i4 = this.c;
            int[] a2 = a(gravity, lineWidth);
            if (a2.length == 2) {
                i2 = a2[0];
                i = a2[1];
            } else {
                i = 0;
                i2 = 0;
            }
            float f = ((paddingTop + paddingBottom) + (height * i3)) - i4;
            canvas.drawLine(i2, f, i, f, this.d);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0097, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int[] a(int r5, float r6) {
        /*
            r4 = this;
            r0 = 2
            int[] r0 = new int[r0]
            r1 = 1
            r2 = 0
            switch(r5) {
                case 17: goto L74;
                case 49: goto L74;
                case 81: goto L74;
                case 8388627: goto L3f;
                case 8388629: goto La;
                case 8388659: goto L3f;
                case 8388661: goto La;
                case 8388691: goto L3f;
                case 8388693: goto La;
                default: goto L8;
            }
        L8:
            goto L97
        La:
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r5 = health.compact.a.LanguageUtil.bc(r5)
            if (r5 == 0) goto L24
            int r5 = r4.getPaddingStart()
            r0[r2] = r5
            int r5 = r4.getPaddingEnd()
            float r5 = (float) r5
            float r6 = r6 + r5
            int r5 = (int) r6
            r0[r1] = r5
            goto L97
        L24:
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r5 = r5 - r6
            int r6 = r4.getPaddingStart()
            float r6 = (float) r6
            float r5 = r5 - r6
            int r5 = (int) r5
            r0[r2] = r5
            int r5 = r4.getWidth()
            int r6 = r4.getPaddingEnd()
            int r5 = r5 - r6
            r0[r1] = r5
            goto L97
        L3f:
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r5 = health.compact.a.LanguageUtil.bc(r5)
            if (r5 == 0) goto L64
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r5 = r5 - r6
            int r6 = r4.getPaddingStart()
            float r6 = (float) r6
            float r5 = r5 - r6
            int r5 = (int) r5
            r0[r2] = r5
            int r5 = r4.getWidth()
            int r6 = r4.getPaddingEnd()
            int r5 = r5 - r6
            r0[r1] = r5
            goto L97
        L64:
            int r5 = r4.getPaddingStart()
            r0[r2] = r5
            int r5 = r4.getPaddingEnd()
            float r5 = (float) r5
            float r6 = r6 + r5
            int r5 = (int) r6
            r0[r1] = r5
            goto L97
        L74:
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r5 = r5 - r6
            int r3 = r4.getPaddingStart()
            float r3 = (float) r3
            float r5 = r5 - r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r5 = r5 / r3
            int r5 = (int) r5
            r0[r2] = r5
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r5 = r5 - r6
            int r2 = r4.getPaddingStart()
            float r2 = (float) r2
            float r5 = r5 - r2
            float r5 = r5 / r3
            float r5 = r5 + r6
            int r5 = (int) r5
            r0[r1] = r5
        L97:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.undertextview.ReportUnderlineTextView.a(int, float):int[]");
    }
}
