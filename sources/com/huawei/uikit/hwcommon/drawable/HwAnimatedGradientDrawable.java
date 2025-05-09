package com.huawei.uikit.hwcommon.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;

/* loaded from: classes9.dex */
public class HwAnimatedGradientDrawable extends GradientDrawable {

    /* renamed from: a, reason: collision with root package name */
    private float f10625a;
    private Animator b;
    private boolean c;
    private Animator d;
    private TimeInterpolator e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;

    public HwAnimatedGradientDrawable(int i, float f, float f2) {
        this.e = new HwCubicBezierInterpolator(0.2f, 0.0f, 0.4f, 1.0f);
        c(i, f, f2);
    }

    private void c() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rectAlpha", this.f10625a);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(this.e);
        if (this.h > 0.0f || this.k) {
            ObjectAnimator ofFloat2 = a() < 1.0E-7f ? ObjectAnimator.ofFloat(this, "rectScale", this.g, this.i) : ObjectAnimator.ofFloat(this, "rectScale", this.i);
            ofFloat2.setDuration(100L);
            ofFloat2.setInterpolator(this.e);
            animatorSet.playTogether(ofFloat, ofFloat2);
        } else {
            c(1.0f);
            animatorSet.play(ofFloat);
        }
        this.b = animatorSet;
        animatorSet.start();
    }

    private void c(int i, float f, float f2) {
        setShape(0);
        setColor(i);
        setCornerRadius(f2);
        this.h = f2;
        this.c = false;
        this.f10625a = f;
        this.f = 0.0f;
        this.i = 1.0f;
        this.g = 0.9f;
        this.k = false;
    }

    private void d() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rectAlpha", 0.0f);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(this.e);
        animatorSet.playTogether(ofFloat);
        this.d = animatorSet;
        animatorSet.start();
    }

    public float a() {
        return this.f;
    }

    public void c(float f) {
        if (f < 0.0f || f > 1.0f) {
            return;
        }
        this.j = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f = this.f;
        if (f < 1.0E-7f) {
            return;
        }
        float f2 = this.j;
        setAlpha((int) (f * 255.0f));
        canvas.save();
        canvas.scale(f2, f2, canvas.getWidth() * 0.5f, canvas.getHeight() * 0.5f);
        super.draw(canvas);
        canvas.restore();
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
                Log.i("HwAnimatedGradientDrawable", "State = " + i);
            }
        }
        if (z2 && z3) {
            z = true;
        }
        b(z);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            b();
        } else if (!visible) {
            Log.i("HwAnimatedGradientDrawable", "isChanged = " + visible + ".");
        } else if (this.c) {
            this.f = this.f10625a;
            this.j = this.i;
        } else {
            this.f = 0.0f;
        }
        return visible;
    }

    private void b(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (z) {
                Animator animator = this.b;
                if (animator == null || !animator.isRunning()) {
                    Animator animator2 = this.d;
                    if (animator2 != null && animator2.isRunning()) {
                        this.d.cancel();
                    }
                    c();
                    return;
                }
                return;
            }
            Animator animator3 = this.d;
            if (animator3 == null || !animator3.isRunning()) {
                Animator animator4 = this.b;
                if (animator4 != null && animator4.isRunning()) {
                    this.b.cancel();
                }
                d();
            }
        }
    }

    public HwAnimatedGradientDrawable() {
        this(201326592, 1.0f, 12.0f);
    }

    public HwAnimatedGradientDrawable(Context context) {
        this(201326592, 1.0f, context);
    }

    public HwAnimatedGradientDrawable(int i, float f, Context context) {
        this(i, f, 4.0f, context);
    }

    public HwAnimatedGradientDrawable(int i, float f, float f2, Context context) {
        this.e = new HwCubicBezierInterpolator(0.2f, 0.0f, 0.4f, 1.0f);
        if (context != null) {
            c(i, f, f2 * context.getResources().getDisplayMetrics().density);
        } else {
            c(i, f, 12.0f);
        }
    }

    private void b() {
        Animator animator = this.b;
        if (animator != null && animator.isRunning()) {
            this.b.end();
            Log.i("HwAnimatedGradientDrawable", "clearEffect: mEnterAnim is not null.");
        }
        Animator animator2 = this.d;
        if (animator2 != null && animator2.isRunning()) {
            this.d.end();
            Log.i("HwAnimatedGradientDrawable", "clearEffect: mExitAnim is not null.");
        }
        this.b = null;
        this.d = null;
        this.c = false;
        this.f = 0.0f;
        invalidateSelf();
    }
}
