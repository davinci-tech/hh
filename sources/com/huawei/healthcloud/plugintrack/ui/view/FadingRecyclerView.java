package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;

/* loaded from: classes4.dex */
public class FadingRecyclerView extends HealthRecycleView {

    /* renamed from: a, reason: collision with root package name */
    private int f3780a;
    private int b;
    private int c;
    private Paint e;

    public FadingRecyclerView(Context context) {
        super(context);
        this.c = 100;
        e();
    }

    public FadingRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 100;
        e();
    }

    public FadingRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 100;
        e();
    }

    private void e() {
        Paint paint = new Paint();
        this.e = paint;
        paint.setAntiAlias(true);
        this.e.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    public void setSpanPixel(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b = i2;
        this.f3780a = i;
        this.e.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, i2 / 2.0f, new int[]{0, -16777216, -16777216}, new float[]{0.0f, this.c / (i2 / 2.0f), 1.0f}, Shader.TileMode.MIRROR));
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        canvas.saveLayer(0.0f, 0.0f, this.f3780a, this.b, null, 31);
        super.draw(canvas);
        canvas.drawRect(0.0f, 0.0f, this.f3780a, this.b, this.e);
        canvas.restore();
    }
}
