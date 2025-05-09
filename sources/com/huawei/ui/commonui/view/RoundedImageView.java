package com.huawei.ui.commonui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class RoundedImageView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private RectF f8977a;
    private Context b;
    private Path d;
    private float[] e;

    public RoundedImageView(Context context) {
        super(context);
        this.d = new Path();
        this.f8977a = new RectF();
        this.e = new float[8];
        this.b = context;
        setRadius(nsn.c(context, 4.0f));
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new Path();
        this.f8977a = new RectF();
        this.e = new float[8];
        this.b = context;
        setRadius(nsn.c(context, 4.0f));
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Path();
        this.f8977a = new RectF();
        this.e = new float[8];
        this.b = context;
        setRadius(nsn.c(context, 4.0f));
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        this.f8977a.right = getWidth();
        this.f8977a.bottom = getHeight();
        this.d.addRoundRect(this.f8977a, this.e, Path.Direction.CW);
        canvas.clipPath(this.d);
        super.onDraw(canvas);
    }

    public final void setRadius(float f) {
        int i = 0;
        while (true) {
            float[] fArr = this.e;
            if (i >= fArr.length) {
                return;
            }
            fArr[i] = f;
            i++;
        }
    }
}
