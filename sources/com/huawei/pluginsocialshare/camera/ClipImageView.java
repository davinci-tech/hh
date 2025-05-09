package com.huawei.pluginsocialshare.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import java.io.ByteArrayOutputStream;

/* loaded from: classes6.dex */
public class ClipImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    private int f8522a;
    private GestureDetector b;
    private boolean c;
    private ScaleGestureDetector d;
    private int e;
    private int f;
    private boolean g;
    private float h;
    private float i;
    private boolean j;
    private float k;
    private float l;
    private float m;
    private final Matrix n;
    private final float[] o;

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public ClipImageView(Context context) {
        this(context, null);
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = new float[9];
        this.n = new Matrix();
        this.l = 4.0f;
        this.k = 2.0f;
        this.m = 1.0f;
        this.g = true;
        this.d = null;
        setScaleType(ImageView.ScaleType.MATRIX);
        this.b = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.pluginsocialshare.camera.ClipImageView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (ClipImageView.this.c) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (ClipImageView.this.getScale() < ClipImageView.this.k) {
                    ClipImageView clipImageView = ClipImageView.this;
                    clipImageView.postDelayed(clipImageView.new d(clipImageView.k, x, y), 16L);
                    ClipImageView.this.c = true;
                } else {
                    ClipImageView clipImageView2 = ClipImageView.this;
                    clipImageView2.postDelayed(clipImageView2.new d(clipImageView2.m, x, y), 16L);
                    ClipImageView.this.c = true;
                }
                return true;
            }
        });
        this.d = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scale = getScale();
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (getDrawable() == null) {
            return true;
        }
        float f = this.l;
        boolean z = false;
        boolean z2 = scale < f && scaleFactor > 1.0f;
        float f2 = this.m;
        if (scale > f2 && scaleFactor < 1.0f) {
            z = true;
        }
        if (z2 || z) {
            if (scaleFactor * scale < f2) {
                scaleFactor = f2 / scale;
            }
            if (scaleFactor * scale > f) {
                scaleFactor = f / scale;
            }
            this.n.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            if (a()) {
                setImageMatrix(this.n);
            }
        }
        return true;
    }

    private RectF getMatrixRectF() {
        RectF rectF = new RectF();
        if (getDrawable() != null) {
            rectF.set(0.0f, 0.0f, r1.getIntrinsicWidth(), r1.getIntrinsicHeight());
            this.n.mapRect(rectF);
        }
        return rectF;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
    
        if (r7 != 3) goto L20;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            android.view.GestureDetector r7 = r6.b
            boolean r7 = r7.onTouchEvent(r8)
            r0 = 1
            if (r7 == 0) goto La
            return r0
        La:
            android.view.ScaleGestureDetector r7 = r6.d
            r7.onTouchEvent(r8)
            int r7 = r8.getPointerCount()
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = r2
        L17:
            if (r4 >= r7) goto L26
            float r5 = r8.getX(r4)
            float r1 = r1 + r5
            float r5 = r8.getY(r4)
            float r3 = r3 + r5
            int r4 = r4 + 1
            goto L17
        L26:
            float r4 = (float) r7
            float r1 = r1 / r4
            float r3 = r3 / r4
            int r4 = r6.f
            if (r7 == r4) goto L33
            r6.j = r2
            r6.h = r1
            r6.i = r3
        L33:
            r6.f = r7
            int r7 = r8.getAction()
            if (r7 == r0) goto L46
            r8 = 2
            if (r7 == r8) goto L42
            r8 = 3
            if (r7 == r8) goto L46
            goto L48
        L42:
            r6.a(r1, r3)
            goto L48
        L46:
            r6.f = r2
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginsocialshare.camera.ClipImageView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    private void a(float f, float f2) {
        float f3 = f - this.h;
        float f4 = f2 - this.i;
        if (!this.j) {
            this.j = c(f3, f4);
        } else if (getDrawable() != null) {
            RectF matrixRectF = getMatrixRectF();
            if (matrixRectF.left + f3 >= this.f8522a || matrixRectF.right + f3 <= getWidth() - this.f8522a) {
                f3 = 0.0f;
            }
            if (matrixRectF.top + f4 >= getVerticalPadding() || matrixRectF.bottom + f4 <= getHeight() - getVerticalPadding()) {
                f4 = 0.0f;
            }
            this.n.postTranslate(f3, f4);
            setImageMatrix(this.n);
        }
        this.h = f;
        this.i = f2;
    }

    public final float getScale() {
        this.n.getValues(this.o);
        return this.o[0];
    }

    private float getTransX() {
        this.n.getValues(this.o);
        return this.o[2];
    }

    private float getTransY() {
        this.n.getValues(this.o);
        return this.o[5];
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Drawable drawable;
        if (!this.g || (drawable = getDrawable()) == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int width = getWidth() - (this.f8522a * 2);
        this.e = width;
        float f = width * 1.0f;
        float max = Math.max(f / intrinsicWidth, f / intrinsicHeight);
        this.m = max;
        this.k = max * 2.0f;
        this.l = max * 4.0f;
        this.n.postTranslate((getWidth() - intrinsicWidth) / 2.0f, (getHeight() - intrinsicHeight) / 2.0f);
        Matrix matrix = this.n;
        float f2 = this.m;
        matrix.postScale(f2, f2, getWidth() / 2.0f, getHeight() / 2.0f);
        setImageMatrix(this.n);
        this.g = false;
    }

    public Bitmap cpx_() {
        try {
            return cpw_();
        } catch (IllegalArgumentException unused) {
            LogUtil.h("Share_ClipImageView", "clip:IllegalArgumentException");
            return null;
        } catch (IllegalStateException unused2) {
            LogUtil.h("Share_ClipImageView", "clip:IllegalStateException");
            return null;
        } catch (OutOfMemoryError unused3) {
            LogUtil.h("Share_ClipImageView", "clip:OutOfMemoryError");
            return null;
        }
    }

    private Bitmap cpw_() {
        if (!(getDrawable() instanceof BitmapDrawable) || !(getDrawable() instanceof BitmapDrawable)) {
            return null;
        }
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        int transX = (int) ((this.f8522a - getTransX()) / getScale());
        int verticalPadding = (int) ((getVerticalPadding() - getTransY()) / getScale());
        int scale = (int) (this.e / getScale());
        int scale2 = (int) (this.e / getScale());
        if (transX < 0) {
            transX = 0;
        }
        if (verticalPadding < 0) {
            verticalPadding = 0;
        }
        if (transX + scale > bitmap.getWidth()) {
            transX = bitmap.getWidth() - scale;
        }
        if (verticalPadding + scale2 > bitmap.getHeight()) {
            verticalPadding = bitmap.getHeight() - scale2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, transX, verticalPadding, (int) (this.e / getScale()), (int) (this.e / getScale()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            LogUtil.a("Share_ClipImageView", "after rawClip bitmap size:", Double.valueOf((byteArrayOutputStream.toByteArray().length / 1024.0d) / 1024.0d), "M");
            return createBitmap;
        } finally {
            IoUtils.e(byteArrayOutputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        RectF matrixRectF = getMatrixRectF();
        int width = getWidth();
        int height = getHeight();
        if (matrixRectF.width() < this.e) {
            return false;
        }
        float f = matrixRectF.left > ((float) this.f8522a) ? (-matrixRectF.left) + this.f8522a : 0.0f;
        float f2 = matrixRectF.right;
        float f3 = width - this.f8522a;
        if (f2 < f3) {
            f = f3 - matrixRectF.right;
        }
        if (matrixRectF.height() < this.e) {
            return false;
        }
        float verticalPadding = matrixRectF.top > ((float) getVerticalPadding()) ? getVerticalPadding() + (-matrixRectF.top) : 0.0f;
        if (matrixRectF.bottom < height - getVerticalPadding()) {
            verticalPadding = (height - getVerticalPadding()) - matrixRectF.bottom;
        }
        this.n.postTranslate(f, verticalPadding);
        return true;
    }

    private boolean c(float f, float f2) {
        return Math.sqrt((double) ((f * f) + (f2 * f2))) >= 0.0d;
    }

    public void setHorizontalPadding(int i) {
        this.f8522a = i;
    }

    private int getVerticalPadding() {
        return (getHeight() - this.e) / 2;
    }

    /* loaded from: classes9.dex */
    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private float f8523a;
        private float b;
        private float c;
        private float d;

        d(float f, float f2, float f3) {
            this.c = f;
            this.f8523a = f2;
            this.d = f3;
            if (ClipImageView.this.getScale() < this.c) {
                this.b = 1.07f;
            } else {
                this.b = 0.93f;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Matrix matrix = ClipImageView.this.n;
            float f = this.b;
            matrix.postScale(f, f, this.f8523a, this.d);
            if (ClipImageView.this.a()) {
                ClipImageView clipImageView = ClipImageView.this;
                clipImageView.setImageMatrix(clipImageView.n);
            }
            float scale = ClipImageView.this.getScale();
            float f2 = this.b;
            boolean z = f2 > 1.0f && scale < this.c;
            boolean z2 = f2 < 1.0f && this.c < scale;
            if (z || z2) {
                ClipImageView.this.postDelayed(this, 16L);
                return;
            }
            float f3 = this.c / scale;
            ClipImageView.this.n.postScale(f3, f3, this.f8523a, this.d);
            if (ClipImageView.this.a()) {
                ClipImageView clipImageView2 = ClipImageView.this;
                clipImageView2.setImageMatrix(clipImageView2.n);
                ClipImageView.this.c = false;
            }
        }
    }
}
