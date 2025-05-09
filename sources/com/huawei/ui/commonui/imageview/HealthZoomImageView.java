package com.huawei.ui.commonui.imageview;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes9.dex */
public class HealthZoomImageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f8841a;
    private Context b;
    private int c;
    private GestureDetector d;
    private int e;
    private float f;
    private boolean g;
    private float h;
    private boolean i;
    private int j;
    private int k;
    private float l;
    private int m;
    private float n;
    private Matrix o;
    private float p;
    private float q;
    private float r;
    private Path s;
    private float t;
    private float u;
    private ScaleGestureDetector v;
    private int w;
    private int x;
    private int y;
    private int z;

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public HealthZoomImageView(Context context) {
        this(context, null);
    }

    public HealthZoomImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HealthZoomImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        this.s = new Path();
        this.b = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HealthZoomImageView);
        try {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.HealthZoomImageView_radius, 0);
            this.k = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.HealthZoomImageView_lefTopRadius, 0);
            this.y = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.HealthZoomImageView_rightTopRadius, 0);
            this.m = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.HealthZoomImageView_leftBottomRadius, 0);
            int dimensionPixelOffset2 = obtainStyledAttributes.getDimensionPixelOffset(R$styleable.HealthZoomImageView_rightBottomRadius, 0);
            this.x = dimensionPixelOffset2;
            if (this.k == 0) {
                this.k = dimensionPixelOffset;
            }
            if (this.y == 0) {
                this.y = dimensionPixelOffset;
            }
            if (this.m == 0) {
                this.m = dimensionPixelOffset;
            }
            if (dimensionPixelOffset2 == 0) {
                this.x = dimensionPixelOffset;
            }
            this.w = obtainStyledAttributes.getInt(R$styleable.HealthZoomImageView_customerScaleType, 0);
            this.n = obtainStyledAttributes.getFloat(R$styleable.HealthZoomImageView_maxScale, 8.0f);
            this.p = obtainStyledAttributes.getFloat(R$styleable.HealthZoomImageView_minScale, 0.5f);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthZoomImageView", "HealthZoomImageView Resources NotFoundException");
        }
        obtainStyledAttributes.recycle();
        a();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.z = getWidth();
        this.j = getHeight();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int max = Math.max(this.k, this.m);
        int max2 = Math.max(this.y, this.x);
        int max3 = Math.max(this.k, this.y);
        int max4 = Math.max(this.m, this.x);
        if (this.z >= max + max2 && this.j > max3 + max4) {
            this.s.moveTo(this.k, 0.0f);
            this.s.lineTo(this.z - this.y, 0.0f);
            Path path = this.s;
            float f = this.z;
            path.quadTo(f, 0.0f, f, this.y);
            this.s.lineTo(this.z, this.j - this.x);
            Path path2 = this.s;
            int i = this.z;
            float f2 = this.j;
            path2.quadTo(i, f2, i - this.x, f2);
            this.s.lineTo(this.m, this.j);
            this.s.quadTo(0.0f, this.j, 0.0f, r1 - this.m);
            this.s.lineTo(0.0f, this.k);
            this.s.quadTo(0.0f, 0.0f, this.k, 0.0f);
            canvas.clipPath(this.s);
        }
        try {
            super.onDraw(canvas);
        } catch (Exception unused) {
            LogUtil.b("HealthZoomImageView", "onDraw:ondraw failed");
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        setOnTouchListener(this);
        this.v = new ScaleGestureDetector(this.b, this);
        this.d = new GestureDetector(this.b, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.ui.commonui.imageview.HealthZoomImageView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (HealthZoomImageView.this.i) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (HealthZoomImageView.this.getScale() > HealthZoomImageView.this.u) {
                    HealthZoomImageView healthZoomImageView = HealthZoomImageView.this;
                    healthZoomImageView.postDelayed(healthZoomImageView.new a(healthZoomImageView.u, x, y), 10L);
                    HealthZoomImageView.this.i = true;
                } else {
                    HealthZoomImageView healthZoomImageView2 = HealthZoomImageView.this;
                    healthZoomImageView2.postDelayed(healthZoomImageView2.new a(healthZoomImageView2.l, x, y), 10L);
                    HealthZoomImageView.this.i = true;
                }
                return true;
            }
        });
        setScaleType(ImageView.ScaleType.MATRIX);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.imageview.HealthZoomImageView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HealthZoomImageView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                HealthZoomImageView healthZoomImageView = HealthZoomImageView.this;
                healthZoomImageView.z = healthZoomImageView.getWidth();
                HealthZoomImageView healthZoomImageView2 = HealthZoomImageView.this;
                healthZoomImageView2.j = healthZoomImageView2.getHeight();
                HealthZoomImageView healthZoomImageView3 = HealthZoomImageView.this;
                healthZoomImageView3.f8841a = healthZoomImageView3.getDrawable();
                if (HealthZoomImageView.this.f8841a == null) {
                    return;
                }
                HealthZoomImageView healthZoomImageView4 = HealthZoomImageView.this;
                healthZoomImageView4.e = healthZoomImageView4.f8841a.getIntrinsicWidth();
                HealthZoomImageView healthZoomImageView5 = HealthZoomImageView.this;
                healthZoomImageView5.c = healthZoomImageView5.f8841a.getIntrinsicHeight();
                HealthZoomImageView.this.b();
                HealthZoomImageView.this.g();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f8841a == null) {
            return;
        }
        int i = this.e;
        int i2 = this.z;
        if (i > i2 && this.c < this.j) {
            this.u = (i2 * 1.0f) / i;
        } else {
            int i3 = this.c;
            int i4 = this.j;
            if (i3 > i4 && i < i2) {
                this.u = (i4 * 1.0f) / i3;
            } else if (i3 > i4 && i > i2) {
                this.u = Math.min((i4 * 1.0f) / i3, (i2 * 1.0f) / i);
            } else if (i3 < i4 && i < i2) {
                this.u = Math.min((i4 * 1.0f) / i3, (i2 * 1.0f) / i);
            } else {
                this.u = 1.0f;
            }
        }
        if (this.w == 0) {
            float f = this.u;
            this.l = this.n * f;
            this.r = f * this.p;
        } else {
            this.l = this.n;
            this.r = this.p;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Matrix matrix = new Matrix();
        this.o = matrix;
        matrix.postTranslate((this.z / 2.0f) - (this.e / 2.0f), (this.j / 2.0f) - (this.c / 2.0f));
        Matrix matrix2 = this.o;
        float f = this.u;
        matrix2.postScale(f, f, this.z / 2.0f, this.j / 2.0f);
        setImageMatrix(this.o);
    }

    public float getScale() {
        float[] fArr = new float[9];
        this.o.getValues(fArr);
        return fArr[0];
    }

    private RectF cAT_(Matrix matrix) {
        RectF rectF = new RectF();
        if (this.f8841a == null) {
            return null;
        }
        rectF.set(0.0f, 0.0f, this.e, this.c);
        matrix.mapRect(rectF);
        return rectF;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (this.f8841a == null) {
            return true;
        }
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float scale = getScale();
        float f = scale * scaleFactor;
        float f2 = this.l;
        if (f >= f2 && scaleFactor > 1.0f) {
            scaleFactor = f2 / scale;
        }
        float f3 = this.r;
        if (f <= f3 && scaleFactor < 1.0f) {
            scaleFactor = f3 / scale;
        }
        this.o.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        d();
        setImageMatrix(this.o);
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        float scale = getScale();
        float f = this.u;
        if (scale < f) {
            float f2 = f / scale;
            this.o.postScale(f2, f2, this.z / 2.0f, this.j / 2.0f);
            setImageMatrix(this.o);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        float f;
        RectF cAT_ = cAT_(this.o);
        if (cAT_ == null) {
            LogUtil.h("HealthZoomImageView", "checkBorderAndCenterWhenScale RectF is null");
            return;
        }
        if (cAT_.height() >= this.j) {
            f = cAT_.top > 0.0f ? -cAT_.top : 0.0f;
            float f2 = cAT_.bottom;
            float f3 = this.j;
            if (f2 < f3) {
                f = f3 - cAT_.bottom;
            }
        } else {
            f = 0.0f;
        }
        if (cAT_.width() >= this.z) {
            r2 = cAT_.left > 0.0f ? -cAT_.left : 0.0f;
            float f4 = cAT_.right;
            float f5 = this.z;
            if (f4 < f5) {
                r2 = f5 - cAT_.right;
            }
        }
        float width = cAT_.width();
        float f6 = this.z;
        if (width < f6) {
            r2 = (cAT_.width() / 2.0f) + ((f6 / 2.0f) - cAT_.right);
        }
        float height = cAT_.height();
        float f7 = this.j;
        if (height < f7) {
            f = ((f7 / 2.0f) - cAT_.bottom) + (cAT_.height() / 2.0f);
        }
        this.o.postTranslate(r2, f);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float f;
        if (this.d.onTouchEvent(motionEvent)) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action == 2) {
                this.t = motionEvent.getX();
                float y = motionEvent.getY();
                this.q = y;
                if (!this.g) {
                    this.g = true;
                    this.h = this.t;
                    this.f = y;
                }
                RectF cAT_ = cAT_(this.o);
                float f2 = 0.0f;
                if (cAT_ != null) {
                    f = (cAT_.height() <= ((float) this.j) || !e()) ? 0.0f : this.q - this.f;
                    if (cAT_.width() > this.z && c()) {
                        f2 = this.t - this.h;
                    }
                } else {
                    f = 0.0f;
                }
                this.o.postTranslate(f2, f);
                e(f2, f);
                this.h = this.t;
                this.f = this.q;
            } else if (action != 5 && action != 6) {
                LogUtil.h("HealthZoomImageView", "onTouch default");
            }
            return this.v.onTouchEvent(motionEvent);
        }
        this.g = false;
        return this.v.onTouchEvent(motionEvent);
    }

    private boolean c() {
        RectF cAT_ = cAT_(this.o);
        return cAT_ != null && cAT_.left <= 0.0f && cAT_.right >= ((float) getWidth());
    }

    private boolean e() {
        RectF cAT_ = cAT_(this.o);
        return cAT_ != null && cAT_.top <= 0.0f && cAT_.bottom >= ((float) getHeight());
    }

    private void e(float f, float f2) {
        if (!c()) {
            this.o.postTranslate(-f, 0.0f);
        }
        if (!e()) {
            this.o.postTranslate(0.0f, -f2);
        }
        setImageMatrix(this.o);
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private float f8843a;
        private float b;
        private float d;
        private float e;

        a(float f, float f2, float f3) {
            this.e = f;
            this.d = f2;
            this.b = f3;
            if (HealthZoomImageView.this.getScale() < this.e) {
                this.f8843a = 1.1f;
            } else {
                this.f8843a = 0.9f;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Matrix matrix = HealthZoomImageView.this.o;
            float f = this.f8843a;
            matrix.postScale(f, f, this.d, this.b);
            HealthZoomImageView.this.d();
            HealthZoomImageView healthZoomImageView = HealthZoomImageView.this;
            healthZoomImageView.setImageMatrix(healthZoomImageView.o);
            float scale = HealthZoomImageView.this.getScale();
            float f2 = this.f8843a;
            if ((f2 > 1.0f && scale < this.e) || (f2 < 1.0f && this.e < scale)) {
                HealthZoomImageView.this.postDelayed(this, 10L);
                return;
            }
            float f3 = this.e / scale;
            HealthZoomImageView.this.o.postScale(f3, f3, this.d, this.b);
            HealthZoomImageView.this.d();
            HealthZoomImageView healthZoomImageView2 = HealthZoomImageView.this;
            healthZoomImageView2.setImageMatrix(healthZoomImageView2.o);
            HealthZoomImageView.this.i = false;
        }
    }
}
