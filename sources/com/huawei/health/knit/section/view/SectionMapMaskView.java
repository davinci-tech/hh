package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;

/* loaded from: classes3.dex */
public class SectionMapMaskView extends AppCompatImageView {

    /* renamed from: a, reason: collision with root package name */
    private RectF f2699a;
    private Paint b;
    private PorterDuffXfermode c;
    private int d;
    private float e;

    public SectionMapMaskView(Context context) {
        super(context);
        this.f2699a = new RectF();
        this.b = new Paint();
        this.c = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.d = -1;
        d(context);
    }

    public SectionMapMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2699a = new RectF();
        this.b = new Paint();
        this.c = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.d = -1;
        d(context);
    }

    public SectionMapMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2699a = new RectF();
        this.b = new Paint();
        this.c = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        this.d = -1;
        d(context);
    }

    private void d(Context context) {
        this.e = context.getResources().getDimension(R.dimen._2131362922_res_0x7f0a046a);
        this.d = context.getResources().getColor(R$color.colorSubBackground);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        float f = width;
        float height = getHeight();
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, f, height, null, 31);
        this.b.setStyle(Paint.Style.FILL_AND_STROKE);
        this.b.setStrokeWidth(0.0f);
        this.b.setAntiAlias(true);
        this.b.setColor(this.d);
        canvas.drawRect(0.0f, 0.0f, f, height, this.b);
        this.b.setXfermode(this.c);
        this.b.setColor(-16777216);
        this.f2699a.left = 2.0f;
        this.f2699a.right = width - 4;
        this.f2699a.top = 2.0f;
        this.f2699a.bottom = r1 - 4;
        RectF rectF = this.f2699a;
        float f2 = this.e;
        canvas.drawRoundRect(rectF, f2, f2, this.b);
        this.b.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }
}
