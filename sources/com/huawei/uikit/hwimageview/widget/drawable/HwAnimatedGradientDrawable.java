package com.huawei.uikit.hwimageview.widget.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.animation.PathInterpolator;

/* loaded from: classes9.dex */
public class HwAnimatedGradientDrawable extends GradientDrawable {
    private static final TimeInterpolator d = new PathInterpolator(0.2f, 0.0f, 0.4f, 1.0f);

    /* renamed from: a, reason: collision with root package name */
    private float f10677a;
    private boolean b;
    private Animator c;
    private Animator e;
    private float f;
    private float g;
    private float h;
    private boolean i;
    private float j;

    public HwAnimatedGradientDrawable() {
        this(201326592, 1.0f, 12.0f);
    }

    private void b() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rectAlpha", 0.0f);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(d);
        animatorSet.playTogether(ofFloat);
        this.c = animatorSet;
        animatorSet.start();
    }

    private void c() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rectAlpha", this.f10677a);
        ofFloat.setDuration(100L);
        TimeInterpolator timeInterpolator = d;
        ofFloat.setInterpolator(timeInterpolator);
        if (getCornerRadius() > 0.0f || this.i) {
            ObjectAnimator ofFloat2 = a() < 1.0E-7f ? ObjectAnimator.ofFloat(this, "rectScale", this.f, this.j) : ObjectAnimator.ofFloat(this, "rectScale", this.j);
            ofFloat2.setDuration(100L);
            ofFloat2.setInterpolator(timeInterpolator);
            animatorSet.playTogether(ofFloat, ofFloat2);
        } else {
            e(1.0f);
            animatorSet.play(ofFloat);
        }
        this.e = animatorSet;
        animatorSet.start();
    }

    private void e(int i, float f, float f2) {
        setShape(0);
        setColor(i);
        setCornerRadius(f2);
        this.b = false;
        this.f10677a = f;
        this.g = 0.0f;
        this.j = 1.0f;
        this.f = 0.9f;
        this.i = false;
    }

    public float a() {
        return this.g;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f = this.g;
        if (f < 1.0E-7f) {
            return;
        }
        setAlpha((int) (f * 255.0f));
        canvas.save();
        float f2 = this.h;
        canvas.scale(f2, f2, canvas.getWidth() / 2.0f, canvas.getHeight() / 2.0f);
        super.draw(canvas);
        canvas.restore();
    }

    public void e(float f) {
        if (f >= 0.0f && f <= 1.0f) {
            this.h = f;
            invalidateSelf();
        } else {
            Log.w("HwAnimatedGradientDraw", "illegal params: rectScale = " + f);
        }
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRect(getBounds());
        outline.setAlpha(0.0f);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z2 = true;
            } else if (i == 16842919) {
                z3 = true;
            } else {
                Log.w("HwAnimatedGradientDraw", "onStateChange: wrong state");
            }
        }
        if (z2 && z3) {
            z = true;
        }
        a(z);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            d();
        } else if (!visible) {
            Log.w("HwAnimatedGradientDraw", "setVisible: unexpected visibility state");
        } else if (this.b) {
            this.g = this.f10677a;
            this.h = this.j;
        } else {
            this.g = 0.0f;
        }
        return visible;
    }

    public HwAnimatedGradientDrawable(int i, float f, float f2) {
        e(i, f, f2);
    }

    public HwAnimatedGradientDrawable(Context context) {
        this(201326592, 1.0f, context);
    }

    public HwAnimatedGradientDrawable(int i, float f, Context context) {
        this(i, f, 4.0f, context);
    }

    public HwAnimatedGradientDrawable(int i, float f, float f2, Context context) {
        if (context != null) {
            e(i, f, f2 * context.getResources().getDisplayMetrics().density);
        } else {
            e(i, f, 12.0f);
        }
    }

    private void a(boolean z) {
        if (this.b != z) {
            this.b = z;
            if (z) {
                Animator animator = this.e;
                if (animator == null || !animator.isRunning()) {
                    Animator animator2 = this.c;
                    if (animator2 != null && animator2.isRunning()) {
                        this.c.cancel();
                    }
                    c();
                    return;
                }
                return;
            }
            Animator animator3 = this.c;
            if (animator3 == null || !animator3.isRunning()) {
                Animator animator4 = this.e;
                if (animator4 != null && animator4.isRunning()) {
                    this.e.cancel();
                }
                b();
            }
        }
    }

    private void d() {
        Animator animator = this.e;
        if (animator != null && animator.isRunning()) {
            this.e.end();
        }
        Animator animator2 = this.c;
        if (animator2 != null && animator2.isRunning()) {
            this.c.end();
        }
        this.e = null;
        this.c = null;
        this.b = false;
        this.g = 0.0f;
        invalidateSelf();
    }
}
