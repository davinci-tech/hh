package com.huawei.ui.commonui.barlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;

/* loaded from: classes6.dex */
public class BarView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f8765a;
    private int b;
    private int c;
    private int d;
    private int e;

    public BarView(Context context) {
        super(context);
        this.f8765a = new Paint();
        this.d = 0;
        this.c = 0;
        b();
    }

    public BarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8765a = new Paint();
        this.d = 0;
        this.c = 0;
        b();
    }

    public BarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8765a = new Paint();
        this.d = 0;
        this.c = 0;
        b();
    }

    private void b() {
        this.f8765a.setStrokeCap(Paint.Cap.ROUND);
        this.f8765a.setAntiAlias(true);
        this.f8765a.setStrokeWidth(getContext().getResources().getDimensionPixelSize(R.dimen._2131362841_res_0x7f0a0419));
        this.c = ContextCompat.getColor(getContext(), R$color.health_bar_bg_color);
    }

    public void setData(int i, int i2, int i3) {
        this.e = i;
        this.b = i2;
        this.d = i3;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        float strokeWidth = this.f8765a.getStrokeWidth();
        canvas.rotate(180.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        float width = getWidth() / 2.0f;
        float height = getHeight();
        float f = strokeWidth / 2.0f;
        this.f8765a.setColor(this.c);
        canvas.drawLine(width, 0.0f, width, height - f, this.f8765a);
        float height2 = this.e != 0 ? ((getHeight() * this.e) / this.b) - f : 0.0f;
        if (height2 > 0.0f) {
            this.f8765a.setColor(this.d);
            canvas.drawLine(width, 0.0f, width, height2, this.f8765a);
        }
        canvas.restore();
    }
}
