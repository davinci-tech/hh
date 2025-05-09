package com.huawei.openalliance.ad.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.os;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ParticleRelativeLayout extends AutoScaleSizeRelativeLayout implements com.huawei.openalliance.ad.views.interfaces.e {

    /* renamed from: a, reason: collision with root package name */
    private static final int f7991a = 2131428552;
    private static final int b = 2131428554;
    private int c;
    private Bitmap d;
    private Paint e;
    private Paint f;
    private ValueAnimator g;
    private List<x> h;
    private PorterDuffXfermode i;
    private View j;
    private AdLandingPageData k;
    private boolean l;
    private boolean m;
    private Handler n;

    public void setRadius(int i) {
        this.c = i;
        setRectCornerRadius(ao.a(getContext(), i));
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void setAutoRepeat(boolean z) {
        this.l = z;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ho.b("ParticleRelativeLayout", "onSizeChanged");
        d();
        if (!this.m && this.l && this.k != null) {
            k();
            f();
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
            ValueAnimator valueAnimator = this.g;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.g.cancel();
            }
            this.k = null;
            this.j = null;
            k();
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "onDetachedFromWindow exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.d == null) {
            return;
        }
        try {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), this.f, 31);
            a(canvas);
            this.f.setXfermode(this.i);
            canvas.drawBitmap(this.d, 0.0f, 0.0f, this.f);
            this.f.setXfermode(null);
            canvas.restoreToCount(saveLayer);
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "dispatchDraw exception: %s", th.getClass().getSimpleName());
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public boolean b() {
        ValueAnimator valueAnimator = this.g;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void a(View view, AdLandingPageData adLandingPageData) {
        if (view == null || adLandingPageData == null) {
            ho.b("ParticleRelativeLayout", "view or adLandingPageData is null");
            return;
        }
        ho.b("ParticleRelativeLayout", "start");
        this.j = view;
        this.k = adLandingPageData;
        post(new Runnable() { // from class: com.huawei.openalliance.ad.views.ParticleRelativeLayout.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (ParticleRelativeLayout.this.g == null) {
                        ParticleRelativeLayout.this.e();
                    } else if (ParticleRelativeLayout.this.g.isRunning()) {
                        ParticleRelativeLayout.this.g.cancel();
                    }
                    ParticleRelativeLayout.this.g.start();
                } catch (Throwable th) {
                    ho.c("ParticleRelativeLayout", "start animator exception: %s", th.getClass().getSimpleName());
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.e
    public void a() {
        ho.b("ParticleRelativeLayout", "stop");
        try {
            ValueAnimator valueAnimator = this.g;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.g.cancel();
            }
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "cancel animator exception: %s", th.getClass().getSimpleName());
        }
        postInvalidate();
    }

    private void k() {
        if (bg.a(this.h)) {
            return;
        }
        this.h.clear();
    }

    private void j() {
        ValueAnimator ofPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofKeyframe(View.SCALE_X, Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(0.2f, 0.95f), Keyframe.ofFloat(0.4f, 1.0f), Keyframe.ofFloat(1.0f, 1.0f)), PropertyValuesHolder.ofKeyframe(View.SCALE_Y, Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(0.2f, 0.95f), Keyframe.ofFloat(0.4f, 1.0f), Keyframe.ofFloat(1.0f, 1.0f)));
        this.g = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(ProfileExtendConstants.TIME_OUT);
        this.g.setTarget(this.j);
        if (this.l) {
            this.g.setRepeatCount(-1);
        }
        this.g.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.openalliance.ad.views.ParticleRelativeLayout.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (ParticleRelativeLayout.this.j == null || ParticleRelativeLayout.this.g == null) {
                    return;
                }
                ParticleRelativeLayout.this.j.setScaleY(((Float) ParticleRelativeLayout.this.g.getAnimatedValue()).floatValue());
                ParticleRelativeLayout.this.j.setScaleX(((Float) ParticleRelativeLayout.this.g.getAnimatedValue()).floatValue());
            }
        });
        this.g.addListener(new AnimatorListenerAdapter() { // from class: com.huawei.openalliance.ad.views.ParticleRelativeLayout.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator, boolean z) {
                if (ParticleRelativeLayout.this.n != null) {
                    ParticleRelativeLayout.this.n.sendEmptyMessageDelayed(1002, 300L);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                if (ParticleRelativeLayout.this.n != null) {
                    ParticleRelativeLayout.this.n.sendEmptyMessageDelayed(1002, 300L);
                }
            }
        });
    }

    private void i() {
        int i = f7991a;
        a(new int[]{50, 250}, new float[][]{new float[]{0.668f, 0.74f}, new float[]{0.363f, -0.131f}, new float[]{0.0f, 1.0f}}, i);
        a(new int[]{100, 300}, new float[][]{new float[]{0.535f, 0.582f}, new float[]{0.797f, 1.12f}, new float[]{0.0f, 1.0f}}, i);
        a(new int[]{0, 250}, new float[][]{new float[]{0.488f, 0.465f}, new float[]{0.268f, -0.107f}, new float[]{0.0f, 0.9f}}, i);
        a(new int[]{50, 250}, new float[][]{new float[]{0.171f, 0.073f}, new float[]{0.429f, -0.107f}, new float[]{0.0f, 0.6f}}, i);
        a(new int[]{150, 200}, new float[][]{new float[]{0.299f, 0.253f}, new float[]{0.268f, -0.071f}, new float[]{0.0f, 0.4f}}, i);
        a(new int[]{0, 250}, new float[][]{new float[]{0.124f, 0.017f}, new float[]{0.369f, -0.155f}, new float[]{0.0f, 1.0f}}, i);
        a(new int[]{100, 200}, new float[][]{new float[]{0.204f, 0.168f}, new float[]{0.762f, 1.054f}, new float[]{0.0f, 0.4f}}, i);
        a(new int[]{0, 250}, new float[][]{new float[]{0.15f, 0.11f}, new float[]{0.702f, 1.071f}, new float[]{0.0f, 0.5f}}, i);
        a(new int[]{50, 350}, new float[][]{new float[]{0.58f, 0.654f}, new float[]{0.446f, -0.065f}, new float[]{0.0f, 0.45f}}, i);
        a(new int[]{0, 250}, new float[][]{new float[]{0.655f, 0.685f}, new float[]{0.774f, 1.065f}, new float[]{0.0f, 0.4f}}, i);
        a(new int[]{50, 200}, new float[][]{new float[]{0.884f, 1.01f}, new float[]{0.506f, -0.077f}, new float[]{0.0f, 0.8f}}, i);
        int i2 = b;
        a(new int[]{0, 250}, new float[][]{new float[]{0.465f, 0.431f}, new float[]{0.786f, 1.071f}, new float[]{0.0f, 0.5f}}, i2);
        a(new int[]{50, 250}, new float[][]{new float[]{0.261f, 0.203f}, new float[]{0.357f, -0.077f}, new float[]{0.0f, 0.6f}}, i2);
        a(new int[]{0, 250}, new float[][]{new float[]{0.778f, 0.854f}, new float[]{0.357f, -0.101f}, new float[]{0.0f, 0.8f}}, i2);
        a(new int[]{50, 250}, new float[][]{new float[]{0.711f, 0.794f}, new float[]{0.429f, -0.054f}, new float[]{0.0f, 0.4f}}, i2);
        a(new int[]{100, 300}, new float[][]{new float[]{0.809f, 0.885f}, new float[]{0.72f, 1.125f}, new float[]{0.0f, 1.0f}}, i2);
    }

    private boolean h() {
        return os.f(this.k.f()) == 2 || ao.n(getContext());
    }

    private void g() {
        int i = b;
        a(new int[]{0, 300}, new float[][]{new float[]{0.375f, 0.239f}, new float[]{0.75f, 1.119f}, new float[]{0.0f, 0.9f}}, i);
        a(new int[]{50, 300}, new float[][]{new float[]{0.314f, 0.083f}, new float[]{0.369f, -0.065f}, new float[]{0.0f, 0.5f}}, i);
        int i2 = f7991a;
        a(new int[]{0, 300}, new float[][]{new float[]{0.683f, 0.825f}, new float[]{0.315f, -0.155f}, new float[]{0.0f, 1.0f}}, i2);
        a(new int[]{0, 250}, new float[][]{new float[]{0.436f, 0.369f}, new float[]{0.298f, -0.107f}, new float[]{0.0f, 0.7f}}, i2);
        a(new int[]{50, 300}, new float[][]{new float[]{0.244f, 0.05f}, new float[]{0.667f, 1.054f}, new float[]{0.0f, 0.4f}}, i2);
        a(new int[]{0, 300}, new float[][]{new float[]{0.692f, 0.953f}, new float[]{0.708f, 1.089f}, new float[]{0.0f, 0.7f}}, i2);
    }

    private void f() {
        try {
            if (getHeight() == 0 || getWidth() == 0) {
                ho.b("ParticleRelativeLayout", "not support particle animator");
            } else if (h()) {
                i();
            } else {
                g();
            }
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "initParticleAnimator error: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            j();
            f();
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "init animator exception: %s", th.getClass().getSimpleName());
        }
    }

    private void d() {
        if (getHeight() <= 0 || getWidth() <= 0) {
            return;
        }
        try {
            this.d = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            new Canvas(this.d).drawRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), ao.a(getContext(), this.c), ao.a(getContext(), this.c), this.e);
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "createBitMapException: %s", th.getClass().getSimpleName());
        }
    }

    private void c() {
        ho.b("ParticleRelativeLayout", "init");
        try {
            this.h = new ArrayList();
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
            this.i = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "init exception: %s", th.getClass().getSimpleName());
        }
    }

    private void a(int[] iArr, float[][] fArr, int i) {
        if (this.h != null) {
            this.h.add(new x(fArr, iArr, a(i), this));
        }
    }

    private void a(Canvas canvas) {
        try {
            for (x xVar : this.h) {
                canvas.drawBitmap(xVar.f(), (Rect) null, new RectF(xVar.b(), xVar.c(), xVar.d(), xVar.e()), this.f);
            }
        } catch (Throwable th) {
            ho.c("ParticleRelativeLayout", "drawBitmaps: %s", th.getClass().getSimpleName());
        }
    }

    private Bitmap a(int i) {
        Drawable drawable = getResources().getDrawable(i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public ParticleRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = false;
        this.m = true;
        this.n = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.ParticleRelativeLayout.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (bg.a(ParticleRelativeLayout.this.h)) {
                    return true;
                }
                for (x xVar : ParticleRelativeLayout.this.h) {
                    if (xVar.a() != null) {
                        xVar.a().start();
                    }
                }
                return true;
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100708_res_0x7f060424, R.attr._2131100709_res_0x7f060425});
        this.c = obtainStyledAttributes.getDimensionPixelOffset(0, 36);
        obtainStyledAttributes.recycle();
        c();
    }

    public ParticleRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ParticleRelativeLayout(Context context) {
        super(context);
        this.l = false;
        this.m = true;
        this.n = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.huawei.openalliance.ad.views.ParticleRelativeLayout.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (bg.a(ParticleRelativeLayout.this.h)) {
                    return true;
                }
                for (x xVar : ParticleRelativeLayout.this.h) {
                    if (xVar.a() != null) {
                        xVar.a().start();
                    }
                }
                return true;
            }
        });
        c();
    }
}
