package com.huawei.ucd.cloveranimation;

import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import defpackage.njq;
import defpackage.njr;

/* loaded from: classes9.dex */
public class CloverLoadingView extends View {

    /* renamed from: a, reason: collision with root package name */
    private AddFrameListener f8729a;
    private boolean b;
    private Runnable c;
    private AddFrameListener d;
    private boolean e;
    private float f;
    private float g;
    private RectF h;
    private float i;
    private njr j;
    private Bitmap k;
    private Paint l;
    private Paint m;
    private float n;
    private Bitmap o;
    private Bitmap p;
    private float s;
    private float t;

    public CloverLoadingView(Context context) {
        this(context, null);
    }

    public CloverLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CloverLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 1.0f;
        this.f = 0.74f;
        this.i = 0.74f;
        this.g = 0.74f;
        this.k = null;
        this.p = null;
        this.o = null;
        this.e = false;
        this.c = new Runnable() { // from class: com.huawei.ucd.cloveranimation.CloverLoadingView.5
            @Override // java.lang.Runnable
            public void run() {
                CloverLoadingView cloverLoadingView = CloverLoadingView.this;
                cloverLoadingView.k = cloverLoadingView.cvF_(R.drawable._2131428435_res_0x7f0b0453);
                CloverLoadingView cloverLoadingView2 = CloverLoadingView.this;
                cloverLoadingView2.p = cloverLoadingView2.cvF_(R.drawable._2131428433_res_0x7f0b0451);
                CloverLoadingView cloverLoadingView3 = CloverLoadingView.this;
                cloverLoadingView3.o = cloverLoadingView3.cvF_(R.drawable._2131428434_res_0x7f0b0452);
                CloverLoadingView.this.e = true;
                CloverLoadingView.this.postInvalidate();
                if (CloverLoadingView.this.d != null) {
                    CloverLoadingView.this.d.onFinished();
                }
            }
        };
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap cvF_(int i) {
        Bitmap cvD_ = cvD_(i);
        Matrix matrix = new Matrix();
        float f = this.n;
        matrix.preScale(f, f);
        Bitmap createBitmap = Bitmap.createBitmap(cvD_, 0, 0, cvD_.getWidth(), cvD_.getHeight(), matrix, true);
        if (cvD_.isRecycled()) {
            cvD_.recycle();
        }
        return createBitmap;
    }

    private Bitmap cvD_(int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(getResources().openRawResource(i), null, options);
    }

    public void setAddFrameListener(AddFrameListener addFrameListener) {
        this.d = addFrameListener;
    }

    public void setLoadingEndListener(AddFrameListener addFrameListener) {
        this.f8729a = addFrameListener;
    }

    public void setCustom(boolean z) {
        this.b = z;
    }

    public njr getCloverData() {
        return this.j;
    }

    private void a() {
        this.j = new njr();
        this.m = new Paint(1);
        this.h = new RectF();
        this.l = new Paint(1);
    }

    private void c() {
        new Thread(this.c).start();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        float f = i;
        float f2 = i2;
        this.h.set(0.0f, 0.0f, f, f2);
        this.s = (f / 2.0f) - (this.j.i() / 2.0f);
        this.t = (f2 * 0.56f) - this.j.h();
        this.n = Math.min(i, i2) / (Math.max(this.j.i(), this.j.h()) * 2.0f);
        c();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.m.setShader(null);
        this.m.setAlpha(255);
        cvA_(canvas);
        cvy_(canvas);
    }

    private void cvC_(Canvas canvas, int i, int i2, float f) {
        canvas.save();
        this.l.setAlpha(255);
        if (this.k == null) {
            return;
        }
        float f2 = this.f;
        canvas.scale(f2, f2, getWidth() / 2.0f, getHeight() * 0.56f);
        canvas.drawBitmap(this.k, (getWidth() - i) / 2.0f, ((getHeight() * 0.56f) - i2) - f, this.l);
        canvas.restore();
    }

    private void cvz_(Canvas canvas, int i, int i2, float f) {
        if (this.o == null) {
            return;
        }
        canvas.save();
        this.l.setAlpha(255);
        float f2 = this.i;
        canvas.scale(f2, f2, getWidth() / 2.0f, getHeight() * 0.56f);
        canvas.drawBitmap(this.o, ((getWidth() / 2.0f) - i) - f, ((getHeight() * 0.56f) - (i2 * 0.211f)) + f, this.l);
        canvas.restore();
    }

    private void cvB_(Canvas canvas, int i, float f) {
        if (this.p == null) {
            return;
        }
        canvas.save();
        this.l.setAlpha(255);
        float f2 = this.g;
        canvas.scale(f2, f2, getWidth() / 2.0f, getHeight() * 0.56f);
        canvas.drawBitmap(this.p, (getWidth() / 2.0f) + f, ((getHeight() * 0.56f) - (i * 0.211f)) + f, this.l);
        canvas.restore();
    }

    private void cvy_(Canvas canvas) {
        Bitmap bitmap;
        if (!this.e || (bitmap = this.k) == null || this.o == null || this.p == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = this.k.getHeight();
        float height2 = getHeight() * 0.03f;
        cvC_(canvas, width, height, height2);
        float f = height2 * 0.707f;
        cvB_(canvas, height, f);
        cvz_(canvas, width, height, f);
    }

    private void setPetalBackgroundColor(LinearGradient linearGradient) {
        if (this.b) {
            this.m.setShader(linearGradient);
        } else {
            this.m.setShader(this.j.cvl_());
        }
    }

    private void cvA_(Canvas canvas) {
        canvas.save();
        float f = this.i * this.n;
        canvas.scale(f, f, getWidth() / 2.0f, getHeight() * 0.56f);
        setPetalBackgroundColor(this.j.cvn_());
        canvas.translate(this.s, this.t);
        canvas.rotate(-120.0f, this.j.cvr_().x, this.j.cvr_().y);
        canvas.drawPath(this.j.cvq_(), this.m);
        canvas.restore();
        canvas.save();
        float f2 = this.g * this.n;
        canvas.scale(f2, f2, getWidth() / 2.0f, getHeight() * 0.56f);
        setPetalBackgroundColor(this.j.cvt_());
        canvas.translate(this.s, this.t);
        canvas.rotate(120.0f, this.j.cvr_().x, this.j.cvr_().y);
        canvas.drawPath(this.j.cvq_(), this.m);
        canvas.restore();
        canvas.save();
        float f3 = this.f * this.n;
        canvas.scale(f3, f3, getWidth() / 2.0f, getHeight() * 0.56f);
        setPetalBackgroundColor(this.j.cvp_());
        canvas.translate(this.s, this.t);
        canvas.drawPath(this.j.cvq_(), this.m);
        canvas.restore();
    }

    private PropertyValuesHolder cvE_(njq njqVar) {
        Keyframe ofFloat = Keyframe.ofFloat(0.0f, 0.74f);
        Keyframe ofFloat2 = Keyframe.ofFloat(0.1875f, 1.0f);
        ofFloat2.setInterpolator(njqVar);
        Keyframe ofFloat3 = Keyframe.ofFloat(0.375f, 0.74f);
        ofFloat3.setInterpolator(njqVar);
        Keyframe ofFloat4 = Keyframe.ofFloat(0.5f, 1.0f);
        ofFloat4.setInterpolator(njqVar);
        Keyframe ofFloat5 = Keyframe.ofFloat(0.625f, 0.74f);
        ofFloat5.setInterpolator(njqVar);
        Keyframe ofFloat6 = Keyframe.ofFloat(0.8125f, 1.0f);
        ofFloat6.setInterpolator(njqVar);
        Keyframe ofFloat7 = Keyframe.ofFloat(1.0f, 0.74f);
        ofFloat7.setInterpolator(njqVar);
        return PropertyValuesHolder.ofKeyframe("scale", ofFloat, ofFloat2, ofFloat3, ofFloat4, ofFloat5, ofFloat6, ofFloat7);
    }

    private ValueAnimator getScaleValueAnimator() {
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(cvE_(new njq()));
        ofPropertyValuesHolder.setInterpolator(new njq());
        ofPropertyValuesHolder.setDuration(2667L);
        ofPropertyValuesHolder.setRepeatCount(-1);
        return ofPropertyValuesHolder;
    }
}
