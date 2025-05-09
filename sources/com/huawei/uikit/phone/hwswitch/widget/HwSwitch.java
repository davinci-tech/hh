package com.huawei.uikit.phone.hwswitch.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.huawei.health.R;

/* loaded from: classes7.dex */
public class HwSwitch extends com.huawei.uikit.hwswitch.widget.HwSwitch {

    /* renamed from: a, reason: collision with root package name */
    private int f10792a;
    private Paint b;
    private int c;
    private int e;

    public HwSwitch(Context context) {
        this(context, null);
    }

    @Override // com.huawei.uikit.hwswitch.widget.HwSwitch, android.widget.Switch, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        if (isFocused() && this.d != null && hasWindowFocus()) {
            Rect bounds = this.d.getBounds();
            this.b.setStrokeWidth(this.e);
            int i = bounds.left;
            int i2 = this.c;
            int i3 = bounds.top;
            int i4 = this.f10792a;
            RectF rectF = new RectF(i - i2, i3 - i4, bounds.right + i2, bounds.bottom + i4);
            float f = ((bounds.bottom - bounds.top) / 2) + this.f10792a;
            canvas.drawRoundRect(rectF, f, f, this.b);
        }
    }

    public HwSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100566_res_0x7f060396);
    }

    private void a() {
        setDefaultFocusHighlightEnabled(false);
        Paint paint = new Paint();
        this.b = paint;
        paint.setAntiAlias(true);
        this.b.setStyle(Paint.Style.STROKE);
        this.b.setColor(getFocusedPathColor());
        this.f10792a = (int) getResources().getDimension(R.dimen._2131364482_res_0x7f0a0a82);
        this.e = (int) getResources().getDimension(R.dimen._2131364481_res_0x7f0a0a81);
        c();
    }

    protected void c() {
        this.c = (int) (getResources().getDimension(R.dimen._2131364480_res_0x7f0a0a80) - (getResources().getDimension(R.dimen._2131364486_res_0x7f0a0a86) / 24.0f));
    }

    public HwSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        a();
    }
}
