package com.huawei.healthcloud.plugintrack.ui.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class MapMaskView extends AppCompatImageView {

    /* renamed from: a, reason: collision with root package name */
    private float f3771a;
    private PorterDuffXfermode b;
    private int c;
    private RectF d;
    private Paint e;

    public MapMaskView(Context context) {
        super(context);
        this.d = new RectF();
        this.e = new Paint();
        this.b = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.c = -1;
        b(context);
    }

    public MapMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new RectF();
        this.e = new Paint();
        this.b = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.c = -1;
        b(context);
    }

    public MapMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new RectF();
        this.e = new Paint();
        this.b = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.c = -1;
        b(context);
    }

    private void b(Context context) {
        this.f3771a = context.getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6);
        this.c = context.getResources().getColor(R.color._2131296690_res_0x7f0901b2);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        float f = width;
        float height = getHeight();
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, f, height, null, 31);
        this.e.setStyle(Paint.Style.FILL_AND_STROKE);
        this.e.setStrokeWidth(0.0f);
        this.e.setAntiAlias(true);
        this.e.setColor(this.c);
        canvas.drawRect(0.0f, 0.0f, f, height, this.e);
        this.e.setXfermode(this.b);
        this.e.setColor(-16777216);
        this.d.left = 2.0f;
        this.d.right = width - 4;
        this.d.top = 2.0f;
        this.d.bottom = r1 - 4;
        RectF rectF = this.d;
        float f2 = this.f3771a;
        canvas.drawRoundRect(rectF, f2, f2, this.e);
        this.e.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }
}
