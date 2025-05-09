package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackPaceColorGradientView extends View {

    /* renamed from: a, reason: collision with root package name */
    private Paint f3806a;
    private float b;
    private int[] c;
    private boolean d;
    private LinearGradient e;
    private RectF h;

    public TrackPaceColorGradientView(Context context) {
        this(context, null);
    }

    public TrackPaceColorGradientView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TrackPaceColorGradientView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.b = context.getResources().getDimension(R.dimen._2131362610_res_0x7f0a0332);
        this.h = new RectF();
    }

    public void setColors(List<Integer> list) {
        if (koq.b(list)) {
            return;
        }
        this.c = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.c[i] = list.get(i).intValue();
        }
    }

    public void setRadius(float f) {
        this.b = f;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b();
        bjE_(canvas);
    }

    private void bjE_(Canvas canvas) {
        if (this.f3806a == null) {
            return;
        }
        this.h.left = 0.0f;
        this.h.top = 0.0f;
        this.h.right = getWidth();
        this.h.bottom = getHeight();
        RectF rectF = this.h;
        float f = this.b;
        canvas.drawRoundRect(rectF, f, f, this.f3806a);
    }

    private void b() {
        int[] iArr = this.c;
        if (iArr == null || iArr.length == 0 || this.d) {
            return;
        }
        this.d = true;
        Paint paint = new Paint();
        this.f3806a = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f3806a.setPathEffect(new CornerPathEffect(this.b));
        this.f3806a.setAntiAlias(true);
        int[] iArr2 = this.c;
        if (iArr2.length > 1) {
            LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, getWidth(), getHeight(), this.c, (float[]) null, Shader.TileMode.MIRROR);
            this.e = linearGradient;
            this.f3806a.setShader(linearGradient);
            return;
        }
        this.f3806a.setColor(iArr2[0]);
    }
}
