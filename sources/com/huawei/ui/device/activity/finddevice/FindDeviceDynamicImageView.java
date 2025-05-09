package com.huawei.ui.device.activity.finddevice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;
import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public class FindDeviceDynamicImageView extends View {
    private static final float[] b = {1.0f, 0.73f, 0.47f};
    private static final float[][] c = {new float[]{10.0f, 0.0f}, new float[]{6.0f, 0.0f}, new float[]{0.0f, 10.0f}};
    private static final float[][] e = {new float[]{15.2f, 0.0f}, new float[]{20.4f, 10.2f}, new float[]{5.2f, 10.2f}};

    /* renamed from: a, reason: collision with root package name */
    private Paint f9094a;
    private Paint d;
    private int f;
    private Runnable g;
    private int h;
    private float[][] i;
    private boolean j;
    private int l;

    public FindDeviceDynamicImageView(Context context) {
        super(context);
        this.i = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 4, 2);
        this.l = 0;
        this.h = 150;
        this.g = new Runnable() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceDynamicImageView.2
            @Override // java.lang.Runnable
            public void run() {
                FindDeviceDynamicImageView.this.invalidate();
                FindDeviceDynamicImageView.this.c();
            }
        };
        a();
    }

    public FindDeviceDynamicImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = (float[][]) Array.newInstance((Class<?>) Float.TYPE, 4, 2);
        this.l = 0;
        this.h = 150;
        this.g = new Runnable() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceDynamicImageView.2
            @Override // java.lang.Runnable
            public void run() {
                FindDeviceDynamicImageView.this.invalidate();
                FindDeviceDynamicImageView.this.c();
            }
        };
        a();
    }

    private void a() {
        Paint paint = new Paint();
        this.d = paint;
        paint.setAntiAlias(true);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        d();
        e();
    }

    private void d() {
        if (getContext() == null) {
            this.i = c;
        } else if (nsn.v(BaseApplication.getContext())) {
            this.i = e;
        } else {
            this.i = c;
        }
    }

    private void e() {
        Paint paint = new Paint();
        this.f9094a = paint;
        paint.setAntiAlias(true);
        this.f9094a.setStyle(Paint.Style.FILL_AND_STROKE);
        if (getContext() == null) {
            this.f9094a.setColor(-301790);
        } else if (nsn.v(BaseApplication.getContext())) {
            this.f9094a.setColor(-1);
        } else {
            this.f9094a.setColor(-301790);
        }
    }

    public void b() {
        c(this.h);
    }

    public void c(int i) {
        this.h = i;
        if (this.j) {
            return;
        }
        this.j = true;
        this.l = 0;
        d(i);
        post(this.g);
    }

    private void d(int i) {
        Context context = getContext();
        if (context != null) {
            this.f = e(context, i);
        }
    }

    private static int e(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void f() {
        int i = this.l + 1;
        this.l = i;
        this.l = i % 200;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        postDelayed(this.g, 10L);
    }

    private void cPS_(Canvas canvas, int i) {
        int length = b.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.f9094a.setAlpha((int) e(i2, i));
            float f = this.f;
            canvas.drawCircle(f, f, c(i2, i), this.f9094a);
        }
    }

    private float c(int i, int i2) {
        float f = this.f;
        float[] fArr = b;
        float f2 = fArr[i] * f;
        return f2 + (i >= 1 ? (((f * fArr[i - 1]) - f2) / 200.0f) * i2 : 0.0f);
    }

    private float e(int i, int i2) {
        if (i < 0) {
            return 0.0f;
        }
        float[][] fArr = this.i;
        if (i >= fArr.length) {
            return 0.0f;
        }
        float[] fArr2 = fArr[i];
        float f = fArr2[0];
        return f + (((fArr2[1] - f) / 200.0f) * i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        super.onDraw(canvas);
        f();
        cPS_(canvas, this.l);
        canvas.save();
        canvas.restore();
    }
}
