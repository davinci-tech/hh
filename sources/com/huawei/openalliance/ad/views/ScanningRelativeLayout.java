package com.huawei.openalliance.ad.views;

import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.gq;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class ScanningRelativeLayout extends AutoScaleSizeRelativeLayout implements com.huawei.openalliance.ad.views.interfaces.e {

    /* renamed from: a, reason: collision with root package name */
    private int f8010a;
    private int b;
    private Bitmap c;
    private Bitmap d;
    private Paint e;
    private Paint f;
    private float g;
    private float h;
    private float i;
    private ValueAnimator j;
    private PorterDuffXfermode k;
    private boolean l;
    private boolean m;

    public void setRadius(int i) {
        this.b = i;
        setRectCornerRadius(ao.a(getContext(), i));
    }

    public void setLeft(float f) {
        this.g = f;
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void setAutoRepeat(boolean z) {
        this.l = z;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        ValueAnimator valueAnimator;
        super.onSizeChanged(i, i2, i3, i4);
        ho.b("ScanningRelativeLayout", "onSizeChanged");
        f();
        this.i = i;
        if (!this.m && this.l && (valueAnimator = this.j) != null) {
            boolean isRunning = valueAnimator.isRunning();
            if (isRunning) {
                this.j.cancel();
            }
            this.j = null;
            g();
            ValueAnimator valueAnimator2 = this.j;
            if (valueAnimator2 != null && isRunning) {
                valueAnimator2.start();
            }
        }
        if (i == 0 || i2 == 0) {
            return;
        }
        this.m = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return;
            }
            this.j.cancel();
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "animator cancel exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.c == null) {
            return;
        }
        try {
            canvas.drawBitmap(this.d, this.g, 0.0f, this.f);
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.f, 31);
            this.f.setXfermode(this.k);
            canvas.drawBitmap(this.c, 0.0f, 0.0f, this.f);
            this.f.setXfermode(null);
            canvas.restoreToCount(saveLayer);
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "dispatchDraw exception: %s", th.getClass().getSimpleName());
        }
    }

    public void d() {
        ho.b("ScanningRelativeLayout", "start");
        post(new Runnable() { // from class: com.huawei.openalliance.ad.views.ScanningRelativeLayout.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ScanningRelativeLayout.this.j == null) {
                        ScanningRelativeLayout.this.g();
                    } else if (ScanningRelativeLayout.this.j.isRunning()) {
                        ScanningRelativeLayout.this.j.cancel();
                    }
                    ScanningRelativeLayout.this.j.start();
                } catch (Throwable th) {
                    ho.c("ScanningRelativeLayout", "start scan exception: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    public void c() {
        ho.b("ScanningRelativeLayout", ParamConstants.CallbackMethod.ON_PREPARE);
        try {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null) {
                g();
            } else if (valueAnimator.isRunning()) {
                this.j.cancel();
            }
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "prepare scan exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public boolean b() {
        ValueAnimator valueAnimator = this.j;
        if (valueAnimator == null) {
            return false;
        }
        return valueAnimator.isRunning();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void a(View view, AdLandingPageData adLandingPageData) {
        d();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void a() {
        ho.b("ScanningRelativeLayout", "stop");
        try {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "cancel animation exception: %s", th.getClass().getSimpleName());
        }
        this.g = this.h;
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(dd.c() ? PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, Keyframe.ofFloat(0.0f, this.i), Keyframe.ofFloat(1.0f, this.h)) : PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, Keyframe.ofFloat(0.0f, this.h), Keyframe.ofFloat(1.0f, this.i)));
            this.j = ofPropertyValuesHolder;
            ofPropertyValuesHolder.setInterpolator(new gq(0.2f, 0.0f, 0.2f, 1.0f));
            this.j.setDuration(ProfileExtendConstants.TIME_OUT);
            if (this.l) {
                this.j.setRepeatCount(-1);
            }
            this.j.addUpdateListener(new a(this));
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "init animator exception: %s", th.getClass().getSimpleName());
        }
    }

    private void f() {
        if (getHeight() <= 0 || getWidth() <= 0) {
            return;
        }
        try {
            this.c = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            new Canvas(this.c).drawRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), ao.a(getContext(), this.b), ao.a(getContext(), this.b), this.e);
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "createBitMapException: %s", th.getClass().getSimpleName());
        }
    }

    private void e() {
        ho.b("ScanningRelativeLayout", "init");
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), this.f8010a);
            this.d = decodeResource;
            if (decodeResource != null && dd.c()) {
                Matrix matrix = new Matrix();
                matrix.postScale(-1.0f, 1.0f);
                Bitmap bitmap = this.d;
                this.d = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.d.getHeight(), matrix, false);
            }
            float f = -this.d.getWidth();
            this.h = f;
            this.g = f;
            Paint paint = new Paint(1);
            this.f = paint;
            paint.setDither(true);
            this.f.setFilterBitmap(true);
            Paint paint2 = new Paint(1);
            this.e = paint2;
            paint2.setDither(true);
            this.e.setStyle(Paint.Style.FILL);
            this.e.setColor(-1);
            this.e.setFilterBitmap(true);
            this.k = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        } catch (Throwable th) {
            ho.c("ScanningRelativeLayout", "init exception: %s", th.getClass().getSimpleName());
        }
    }

    static class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ScanningRelativeLayout> f8012a;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ScanningRelativeLayout scanningRelativeLayout = this.f8012a.get();
            if (scanningRelativeLayout == null || valueAnimator == null) {
                return;
            }
            scanningRelativeLayout.setLeft(((Float) valueAnimator.getAnimatedValue()).floatValue());
            scanningRelativeLayout.postInvalidate();
        }

        public a(ScanningRelativeLayout scanningRelativeLayout) {
            this.f8012a = new WeakReference<>(scanningRelativeLayout);
        }
    }

    public ScanningRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = false;
        this.m = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100708_res_0x7f060424, R.attr._2131100709_res_0x7f060425});
        this.f8010a = obtainStyledAttributes.getResourceId(1, R.drawable._2131428581_res_0x7f0b04e5);
        this.b = obtainStyledAttributes.getDimensionPixelOffset(0, 36);
        obtainStyledAttributes.recycle();
        e();
    }

    public ScanningRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScanningRelativeLayout(Context context) {
        super(context);
        this.l = false;
        this.m = true;
        e();
    }
}
