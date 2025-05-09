package com.huawei.watchface.mvp.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import com.huawei.watchface.R$dimen;
import com.huawei.watchface.de;
import com.huawei.watchface.mvp.model.datatype.ClipOptions;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class ClipImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    private int f11147a;
    private float b;
    private float c;
    private final Paint d;
    private Paint e;
    private Paint f;
    private float g;
    private ScaleGestureDetector h;
    private final Matrix i;
    private final float[] j;
    private GestureDetector k;
    private boolean l;
    private float m;
    private float n;
    private int o;
    private boolean p;
    private ClipOptions q;
    private Rect r;
    private Rect s;
    private boolean t;

    public ClipImageView(Context context) {
        this(context, null);
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 4.0f;
        this.c = 0.5f;
        this.g = 1.0f;
        this.i = new Matrix();
        this.j = new float[9];
        this.r = new Rect();
        this.s = new Rect();
        this.t = true;
        setScaleType(ImageView.ScaleType.MATRIX);
        this.k = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.watchface.mvp.ui.widget.ClipImageView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (ClipImageView.this.l) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (ClipImageView.this.getScale() < ClipImageView.this.b && ClipImageView.this.getScale() >= ClipImageView.this.g) {
                    ClipImageView clipImageView = ClipImageView.this;
                    clipImageView.postDelayed(new a(clipImageView.b, x, y), 16L);
                } else {
                    ClipImageView clipImageView2 = ClipImageView.this;
                    clipImageView2.postDelayed(new a(clipImageView2.g, x, y), 16L);
                }
                ClipImageView.this.l = true;
                return true;
            }
        });
        this.h = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        Paint paint = new Paint(1);
        this.d = paint;
        paint.setColor(-1);
        paint.setDither(true);
        if (this.t) {
            Paint paint2 = new Paint();
            this.e = paint2;
            paint2.setColor(-1);
            this.e.setStrokeWidth(getResources().getDimensionPixelSize(R$dimen.dp_1));
            this.e.setStyle(Paint.Style.STROKE);
            this.e.setAlpha(76);
            Paint paint3 = new Paint();
            this.f = paint3;
            paint3.setColor(-1);
            this.f.setStrokeWidth(getResources().getDimensionPixelSize(R$dimen.dp_2));
            this.f.setStyle(Paint.Style.STROKE);
            this.f11147a = getResources().getDimensionPixelSize(R$dimen.dp_32);
        }
    }

    public void setClipOptions(ClipOptions clipOptions) {
        this.q = clipOptions;
        requestLayout();
        invalidate();
    }

    public void setSmartClipRect(Rect rect) {
        a(rect);
    }

    private void a(Rect rect) {
        HwLog.i("ClipImageView", "SmartRectSource:" + rect.toString());
        this.s = rect;
    }

    public final float getScale() {
        this.i.getValues(this.j);
        return this.j[0];
    }

    private RectF getMatrixRectF() {
        Matrix matrix = this.i;
        RectF rectF = new RectF();
        if (getDrawable() != null) {
            rectF.set(0.0f, 0.0f, r2.getIntrinsicWidth(), r2.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    private void b() {
        if (this.q == null) {
            HwLog.i("ClipImageView", "UpdateBorder option is null");
            return;
        }
        HwLog.i("ClipImageView", "UpdateBorder begin");
        int width = getWidth();
        int height = getHeight();
        HwLog.i("ClipImageView", "width:" + width + "height:" + height);
        if (this.q == null) {
            this.q = new ClipOptions(getContext());
        }
        int i = 150;
        if (this.q.getOutputHeight() > this.q.getOutputWidth()) {
            int outputHeight = (this.q.getOutputHeight() * 150) / this.q.getOutputWidth();
            this.r.top = outputHeight;
            int outputWidth = (width - ((this.q.getOutputWidth() * (height - (outputHeight * 2))) / this.q.getOutputHeight())) / 2;
            if (outputWidth < 150) {
                this.r.top = (height - (((width - 300) * this.q.getOutputHeight()) / this.q.getOutputWidth())) / 2;
            } else {
                i = outputWidth;
            }
            this.r.left = i;
            this.r.right = width - i;
            Rect rect = this.r;
            rect.bottom = height - rect.top;
        } else {
            this.r.left = 150;
            this.r.right = width - 150;
            int outputHeight2 = ((width - 300) * this.q.getOutputHeight()) / this.q.getOutputWidth();
            this.r.top = (height - outputHeight2) / 2;
            Rect rect2 = this.r;
            rect2.bottom = rect2.top + outputHeight2;
        }
        HwLog.i("ClipImageView", "Init ClipFrame:" + this.r.toString());
    }

    private void c() {
        double d;
        double d2;
        Drawable drawable = getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        HwLog.i("ClipImageView", "drawableSize:" + intrinsicWidth + ", " + intrinsicHeight);
        int width = this.r.width();
        int height = this.r.height();
        HwLog.i("ClipImageView", "clipSize:" + width + ", " + height);
        HwLog.i("ClipImageView", "viewSize:" + getWidth() + ", " + getHeight());
        float f = (float) width;
        float f2 = (float) height;
        this.g = Math.min(f / ((float) this.s.width()), f2 / ((float) this.s.height()));
        HwLog.i("ClipImageView", "SmartInitScale = " + this.g);
        float f3 = (float) intrinsicWidth;
        float f4 = f / f3;
        float f5 = intrinsicHeight;
        float f6 = f2 / f5;
        if (intrinsicWidth > width && intrinsicHeight > height) {
            this.c = Math.max(f4, f6);
            this.b = Math.min(f3 / this.q.getOutputWidth(), f5 / this.q.getOutputHeight());
        } else {
            float max = Math.max(f4, f6);
            this.g = max;
            this.c = max;
            this.b = Math.max(f3 / this.q.getOutputWidth(), f5 / this.q.getOutputHeight());
        }
        if (this.s.width() == this.q.getOutputWidth() && this.s.height() == this.q.getOutputHeight()) {
            HwLog.i("ClipImageView", "mSmartRect.width == mClipOptions.getOutputWidth()");
            d = this.r.left;
            d2 = this.r.top;
            this.g = this.c;
        } else {
            d = this.r.left - (this.s.left * this.g);
            d2 = this.r.top - (this.s.top * this.g);
        }
        if (d2 < 0.0d) {
            d2 = this.r.top;
        }
        HwLog.i("ClipImageView", "InitDealtX:" + d + "; InitDealtY:" + d2 + "; InitScale:" + this.g + "; MinScale:" + this.c + "; MaxScale:" + this.b + "; Translate:" + d + ", " + d2);
        Matrix matrix = this.i;
        float f7 = this.g;
        matrix.setScale(f7, f7);
        this.i.postTranslate((float) ((int) Math.ceil(d)), (float) ((int) Math.ceil(d2)));
        StringBuilder sb = new StringBuilder("MScaleMatrix = ");
        sb.append(this.i.toString());
        HwLog.i("ClipImageView", sb.toString());
    }

    private void d() {
        int intrinsicWidth = getDrawable().getIntrinsicWidth();
        int intrinsicHeight = getDrawable().getIntrinsicHeight();
        HwLog.i("ClipImageView", "common drawableSize:" + intrinsicWidth + ", " + intrinsicHeight + "; common clipSize:" + this.r.width() + ", " + this.r.height() + "; common viewSize:" + getWidth() + ", " + getHeight());
        if (intrinsicWidth >= getWidth() && intrinsicHeight >= getHeight()) {
            HwLog.i("ClipImageView", "OrgBitmap bigger");
            float f = intrinsicWidth;
            float f2 = intrinsicHeight;
            this.g = Math.max(getWidth() / f, getHeight() / f2);
            this.c = Math.max(this.r.width() / f, this.r.height() / f2);
            this.b = Math.min(f / this.r.width(), f2 / this.r.height());
            HwLog.i("ClipImageView", "BigInitScale = " + this.g);
        } else if (intrinsicWidth <= this.r.width() || intrinsicHeight <= this.r.height()) {
            HwLog.i("ClipImageView", "OrgBitmap smaller than clip");
            float f3 = intrinsicWidth;
            float f4 = intrinsicHeight;
            float max = Math.max(this.r.width() / f3, this.r.height() / f4);
            this.g = max;
            this.c = max;
            this.b = Math.min(f3 / this.q.getOutputWidth(), f4 / this.q.getOutputHeight());
        } else {
            HwLog.i("ClipImageView", "OrgBitmap bigger than clip");
            this.g = 1.0f;
            float f5 = intrinsicWidth;
            float f6 = intrinsicHeight;
            this.c = Math.max(this.r.width() / f5, this.r.height() / f6);
            this.b = Math.min(f5 / this.q.getOutputWidth(), f6 / this.q.getOutputHeight());
        }
        float f7 = this.g;
        float f8 = this.c;
        if (f7 < f8) {
            this.g = f8;
        } else if (f7 <= this.b) {
            HwLog.i("ClipImageView", "right calculated initScale");
        } else {
            this.b = f7;
        }
        double width = (getWidth() - (intrinsicWidth * this.g)) / 2.0f;
        double height = (getHeight() - (intrinsicHeight * this.g)) / 2.0f;
        HwLog.i("ClipImageView", "InitScale:" + this.g + "; MinScale:" + this.c + "; MaxScale:" + this.b + "; Translate:{" + width + ", " + height + "}");
        Matrix matrix = this.i;
        float f9 = this.g;
        matrix.setScale(f9, f9);
        this.i.postTranslate((float) ((int) Math.ceil(width)), (float) ((int) Math.ceil(height)));
        StringBuilder sb = new StringBuilder("MScaleMatrix:");
        sb.append(this.i.toString());
        HwLog.i("ClipImageView", sb.toString());
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void g() {
        if (this.q == null) {
            HwLog.i("ClipImageView", "resetImageMatrix option is null");
            return;
        }
        if (getDrawable() == null) {
            return;
        }
        b();
        if (this.s.width() > 0 && this.s.height() > 0) {
            c();
        } else {
            d();
        }
        if (this.q.isSecondEdit() && this.q.getScaleX() != 0.0f && this.q.getScaleY() != 0.0f) {
            this.i.setScale(this.q.getScaleX(), this.q.getScaleY());
            this.i.postTranslate(this.q.getTransX(), this.q.getTransY());
            HwLog.d("ClipImageView", "resetImageMatrix is secondEdit");
        }
        setImageMatrix(this.i);
    }

    private void e() {
        if (getWidth() != 0) {
            g();
        } else {
            post(new Runnable() { // from class: com.huawei.watchface.mvp.ui.widget.ClipImageView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ClipImageView.this.g();
                }
            });
        }
    }

    private boolean a(float f, float f2) {
        return Math.sqrt((double) ((f * f) + (f2 * f2))) >= 0.0d;
    }

    private void a(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        paint.setColor(0);
        canvas2.drawRect(0.0f, 0.0f, canvas2.getWidth(), canvas2.getHeight(), this.d);
        paint.setXfermode(porterDuffXfermode);
        HwLog.i("ClipImageView", "draw:" + this.r.toString());
        if (this.q.getOutputWidth() == this.q.getOutputHeight() && !this.q.isClipRectangleAnyway()) {
            float width = this.r.left + (this.r.width() / 2.0f);
            float height = this.r.top + (this.r.height() / 2.0f);
            float height2 = this.r.height() / 2.0f;
            canvas2.drawCircle(width, height, height2, paint);
            if (this.t) {
                canvas2.drawCircle(width, height, height2, this.e);
            }
        } else {
            canvas2.drawRect(new RectF(this.r.left, this.r.top, this.r.right, this.r.bottom), paint);
            if (this.t) {
                Path path = new Path();
                path.moveTo(this.r.left, this.r.top + this.f11147a);
                path.lineTo(this.r.left, this.r.top);
                path.lineTo(this.r.left + this.f11147a, this.r.top);
                path.moveTo(this.r.right - this.f11147a, this.r.top);
                path.lineTo(this.r.right, this.r.top);
                path.lineTo(this.r.right, this.r.top + this.f11147a);
                path.moveTo(this.r.left + this.f11147a, this.r.bottom);
                path.lineTo(this.r.left, this.r.bottom);
                path.lineTo(this.r.left, this.r.bottom - this.f11147a);
                path.moveTo(this.r.right - this.f11147a, this.r.bottom);
                path.lineTo(this.r.right, this.r.bottom);
                path.lineTo(this.r.right, this.r.bottom - this.f11147a);
                canvas2.drawPath(path, this.f);
            }
        }
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        float f;
        if (this.q == null) {
            return;
        }
        RectF matrixRectF = getMatrixRectF();
        HwLog.iBetaLog("ClipImageView", "CheckBorderCurrent:" + matrixRectF.toString() + "; CheckBorderClipRect:" + this.q.toString());
        if (matrixRectF.width() >= this.r.width()) {
            f = matrixRectF.left > ((float) this.r.left) ? (-matrixRectF.left) + this.r.left : 0.0f;
            if (matrixRectF.right < this.r.right) {
                f = this.r.right - matrixRectF.right;
            }
        } else {
            f = 0.0f;
        }
        if (matrixRectF.height() >= this.r.height()) {
            r2 = matrixRectF.top > ((float) this.r.top) ? (-matrixRectF.top) + this.r.top : 0.0f;
            if (matrixRectF.bottom < this.r.bottom) {
                r2 = this.r.bottom - matrixRectF.bottom;
            }
        }
        this.i.postTranslate(f, r2);
    }

    public ClipOptions getResultClipOptions() {
        if (this.q == null) {
            return null;
        }
        Drawable drawable = getDrawable();
        Bitmap bitmap = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : null;
        if (bitmap == null) {
            return this.q;
        }
        float[] fArr = new float[9];
        this.i.getValues(fArr);
        float intrinsicWidth = (fArr[0] * drawable.getIntrinsicWidth()) / bitmap.getWidth();
        float intrinsicHeight = (fArr[4] * drawable.getIntrinsicHeight()) / bitmap.getHeight();
        float f = fArr[2];
        float f2 = fArr[5];
        HwLog.i("ClipImageView", "Original:{" + bitmap.getWidth() + ", " + bitmap.getHeight() + "; Matrix.scaleX:" + intrinsicWidth + "; Matrix.scaleY:" + intrinsicHeight + "; Matrix.X:" + f + "; Matrix.Y:" + f2);
        this.q.setScaleX(de.a(intrinsicWidth));
        this.q.setScaleY(de.a(intrinsicHeight));
        this.q.setTransX(de.a(f));
        this.q.setTransY(de.a(f2));
        float f3 = ((-f) + ((float) this.r.left)) / intrinsicWidth;
        float f4 = ((-f2) + ((float) this.r.top)) / intrinsicHeight;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        float width = this.r.width() / intrinsicWidth;
        float height = this.r.height() / intrinsicHeight;
        if (f3 + width > bitmap.getWidth()) {
            width = bitmap.getWidth() - f3;
        }
        if (f4 + height > bitmap.getHeight()) {
            height = bitmap.getHeight() - f4;
        }
        int i = (int) f3;
        int i2 = (int) f4;
        this.q.setClipRect(new Rect(i, i2, ((int) width) + i, ((int) height) + i2));
        HwLog.i("ClipImageView", "ResultRect:" + this.q.toString());
        return this.q;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        e();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        e();
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        e();
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        HwLog.i("ClipImageView", "onScaleBegin");
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        HwLog.i("ClipImageView", "onScaleEnd");
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        HwLog.i("ClipImageView", "onScale");
        float scale = getScale();
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        HwLog.i("ClipImageView", "scale:" + scale + "; factor:" + scaleFactor);
        if (getDrawable() == null) {
            HwLog.i("ClipImageView", "drawable is null");
            return true;
        }
        if ((scale < this.b && scaleFactor > 1.0f) || (scale > this.c && scaleFactor < 1.0f)) {
            HwLog.i("ClipImageView", "Limit scale");
            float f = this.c;
            if (scaleFactor * scale < f) {
                scaleFactor = f / scale;
            }
            float f2 = this.b;
            if (scaleFactor * scale > f2) {
                scaleFactor = f2 / scale;
            }
            this.i.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            f();
            setImageMatrix(this.i);
        }
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.q == null) {
            return false;
        }
        if (this.k.onTouchEvent(motionEvent)) {
            return true;
        }
        HwLog.i("ClipImageView", "ScaleGestureDetector onTouchEvent");
        this.h.onTouchEvent(motionEvent);
        int pointerCount = motionEvent.getPointerCount();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f += motionEvent.getX(i);
            f2 += motionEvent.getY(i);
        }
        float f3 = pointerCount;
        float f4 = f / f3;
        float f5 = f2 / f3;
        if (pointerCount != this.o) {
            this.p = false;
            this.m = f4;
            this.n = f5;
        }
        this.o = pointerCount;
        a(motionEvent, f4, f5);
        return true;
    }

    private void a(MotionEvent motionEvent, float f, float f2) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                float f3 = f - this.m;
                float f4 = f2 - this.n;
                if (!this.p) {
                    this.p = a(f3, f4);
                }
                if (this.p && getDrawable() != null) {
                    RectF matrixRectF = getMatrixRectF();
                    if (matrixRectF.width() <= this.r.width()) {
                        f3 = 0.0f;
                    }
                    if (matrixRectF.height() <= this.r.height()) {
                        f4 = 0.0f;
                    }
                    this.i.postTranslate(f3, f4);
                    f();
                    setImageMatrix(this.i);
                }
                this.m = f;
                this.n = f2;
                return;
            }
            if (action != 3) {
                HwLog.i("ClipImageView", "do no handle event");
                return;
            }
        }
        this.o = 0;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.q == null) {
            HwLog.i("ClipImageView", "OnDraw options is null");
            return;
        }
        this.d.setColor(-1577058304);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setStrokeWidth(1.0f);
        a(canvas);
    }

    /* loaded from: classes9.dex */
    class a implements Runnable {
        private float b;
        private float c;
        private float d;
        private float e;

        private a(float f, float f2, float f3) {
            this.b = f;
            this.d = f2;
            this.e = f3;
            if (ClipImageView.this.getScale() < this.b) {
                this.c = 1.07f;
            } else {
                this.c = 0.93f;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Matrix matrix = ClipImageView.this.i;
            float f = this.c;
            matrix.postScale(f, f, this.d, this.e);
            ClipImageView.this.f();
            ClipImageView clipImageView = ClipImageView.this;
            clipImageView.setImageMatrix(clipImageView.i);
            float scale = ClipImageView.this.getScale();
            float f2 = this.c;
            if ((f2 > 1.0f && scale < this.b) || (f2 < 1.0f && this.b < scale)) {
                ClipImageView.this.postDelayed(this, 16L);
                return;
            }
            float f3 = this.b / scale;
            ClipImageView.this.i.postScale(f3, f3, this.d, this.e);
            ClipImageView.this.f();
            ClipImageView clipImageView2 = ClipImageView.this;
            clipImageView2.setImageMatrix(clipImageView2.i);
            ClipImageView.this.l = false;
        }
    }
}
