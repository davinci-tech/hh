package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/* loaded from: classes10.dex */
public class c extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    final String f11294a;
    public boolean b;
    private Matrix c;
    private Matrix d;
    private int e;
    private float f;
    private float g;
    private Bitmap h;
    private boolean i;
    private float j;
    private float k;
    private PointF l;
    private PointF m;
    private float n;
    private float o;
    private Rect p;

    private void a() {
    }

    public c(Context context) {
        super(context);
        this.c = new Matrix();
        this.d = new Matrix();
        this.e = 0;
        this.f = 1.0f;
        this.g = 1.0f;
        this.i = false;
        this.f11294a = "TouchView";
        this.l = new PointF();
        this.m = new PointF();
        this.n = 1.0f;
        this.o = 0.0f;
        this.b = false;
        Rect rect = new Rect();
        this.p = rect;
        getDrawingRect(rect);
        a();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.h = bitmap;
        if (bitmap != null) {
            this.h = bitmap;
        }
    }

    private float a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() < 2) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void a(Rect rect) {
        this.p = rect;
        if (this.h != null) {
            c();
        }
    }

    private void a(PointF pointF) {
        if (this.h == null) {
            return;
        }
        float[] fArr = new float[9];
        this.c.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fArr[0];
        float width = this.h.getWidth();
        float height = this.h.getHeight();
        float f4 = this.p.left - f;
        if (f4 <= 1.0f) {
            f4 = 1.0f;
        }
        float f5 = (f + (width * f3)) - this.p.right;
        if (f5 <= 1.0f) {
            f5 = 1.0f;
        }
        float width2 = (this.p.width() * f4) / (f5 + f4);
        float f6 = this.p.left;
        float f7 = this.p.top - f2;
        float f8 = (f2 + (height * f3)) - this.p.bottom;
        if (f7 <= 1.0f) {
            f7 = 1.0f;
        }
        pointF.set(width2 + f6, ((this.p.height() * f7) / ((f8 > 1.0f ? f8 : 1.0f) + f7)) + this.p.top);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0019, code lost:
    
        if (r0 != 6) goto L28;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.i
            r1 = 1
            if (r0 == 0) goto L6
            return r1
        L6:
            int r0 = r6.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 == 0) goto L92
            if (r0 == r1) goto L8b
            r2 = 1092616192(0x41200000, float:10.0)
            r3 = 2
            if (r0 == r3) goto L37
            r4 = 5
            if (r0 == r4) goto L1d
            r6 = 6
            if (r0 == r6) goto L8b
            goto Lb1
        L1d:
            float r6 = r5.a(r6)
            r5.n = r6
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 <= 0) goto Lb1
            android.graphics.Matrix r6 = r5.d
            android.graphics.Matrix r0 = r5.c
            r6.set(r0)
            android.graphics.PointF r6 = r5.m
            r5.a(r6)
            r5.e = r3
            goto Lb1
        L37:
            int r0 = r5.e
            if (r0 != r1) goto L5f
            android.graphics.Matrix r0 = r5.c
            android.graphics.Matrix r2 = r5.d
            r0.set(r2)
            float r0 = r6.getX()
            android.graphics.PointF r2 = r5.l
            float r2 = r2.x
            float r6 = r6.getY()
            android.graphics.PointF r3 = r5.l
            float r3 = r3.y
            android.graphics.Matrix r4 = r5.c
            float r0 = r0 - r2
            float r6 = r6 - r3
            r4.postTranslate(r0, r6)
            android.graphics.Matrix r6 = r5.c
            r5.setImageMatrix(r6)
            goto Lb1
        L5f:
            if (r0 != r3) goto Lb1
            android.graphics.Matrix r0 = r5.c
            r0.set(r0)
            float r6 = r5.a(r6)
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 <= 0) goto L85
            android.graphics.Matrix r0 = r5.c
            android.graphics.Matrix r2 = r5.d
            r0.set(r2)
            float r0 = r5.n
            float r6 = r6 / r0
            android.graphics.Matrix r0 = r5.c
            android.graphics.PointF r2 = r5.m
            float r2 = r2.x
            android.graphics.PointF r3 = r5.m
            float r3 = r3.y
            r0.postScale(r6, r6, r2, r3)
        L85:
            android.graphics.Matrix r6 = r5.c
            r5.setImageMatrix(r6)
            goto Lb1
        L8b:
            r5.b()
            r6 = 0
            r5.e = r6
            goto Lb1
        L92:
            android.graphics.Matrix r0 = r5.c
            android.graphics.Matrix r2 = r5.getImageMatrix()
            r0.set(r2)
            android.graphics.Matrix r0 = r5.d
            android.graphics.Matrix r2 = r5.c
            r0.set(r2)
            android.graphics.PointF r0 = r5.l
            float r2 = r6.getX()
            float r6 = r6.getY()
            r0.set(r2, r6)
            r5.e = r1
        Lb1:
            r5.b = r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.avatar.c.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Animation animation;
        if (this.h == null) {
            return;
        }
        float width = this.p.width();
        float height = this.p.height();
        float[] fArr = new float[9];
        this.c.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        boolean z = false;
        float f3 = fArr[0];
        float f4 = this.f;
        if (f3 > f4) {
            float f5 = f4 / f3;
            this.o = f5;
            this.c.postScale(f5, f5, this.m.x, this.m.y);
            setImageMatrix(this.c);
            float f6 = 1.0f / this.o;
            animation = new ScaleAnimation(f6, 1.0f, f6, 1.0f, this.m.x, this.m.y);
        } else {
            float f7 = this.g;
            if (f3 < f7) {
                float f8 = f7 / f3;
                this.o = f8;
                this.c.postScale(f8, f8, this.m.x, this.m.y);
                float f9 = this.o;
                animation = new ScaleAnimation(1.0f, f9, 1.0f, f9, this.m.x, this.m.y);
            } else {
                float width2 = this.h.getWidth() * f3;
                float height2 = this.h.getHeight() * f3;
                float f10 = this.p.left - f;
                float f11 = this.p.top - f2;
                if (f10 < 0.0f) {
                    f = this.p.left;
                    z = true;
                }
                if (f11 < 0.0f) {
                    f2 = this.p.top;
                    z = true;
                }
                if (width2 - f10 < width) {
                    f = this.p.left - (width2 - width);
                    z = true;
                }
                if (height2 - f11 < height) {
                    f2 = this.p.top - (height2 - height);
                    z = true;
                }
                if (z) {
                    float f12 = fArr[2];
                    float f13 = fArr[5];
                    fArr[2] = f;
                    fArr[5] = f2;
                    this.c.setValues(fArr);
                    setImageMatrix(this.c);
                    animation = new TranslateAnimation(f12 - f, 0.0f, f13 - f2, 0.0f);
                } else {
                    setImageMatrix(this.c);
                    animation = null;
                }
            }
        }
        if (animation != null) {
            this.i = true;
            animation.setDuration(300L);
            startAnimation(animation);
            new Thread(new Runnable() { // from class: com.tencent.connect.avatar.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    c.this.post(new Runnable() { // from class: com.tencent.connect.avatar.c.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            c.this.clearAnimation();
                            c.this.b();
                        }
                    });
                    c.this.i = false;
                }
            }).start();
        }
    }

    private void c() {
        if (this.h == null) {
            return;
        }
        float[] fArr = {r1, 0.0f, this.j, 0.0f, r1, r2, 0.0f, 0.0f, 0.0f};
        this.c.getValues(fArr);
        float max = Math.max(this.p.width() / this.h.getWidth(), this.p.height() / this.h.getHeight());
        this.j = this.p.left - (((this.h.getWidth() * max) - this.p.width()) / 2.0f);
        float height = this.p.top - (((this.h.getHeight() * max) - this.p.height()) / 2.0f);
        this.k = height;
        this.c.setValues(fArr);
        float min = Math.min(2048.0f / this.h.getWidth(), 2048.0f / this.h.getHeight());
        this.f = min;
        this.g = max;
        if (min < max) {
            this.f = max;
        }
        setImageMatrix(this.c);
    }
}
