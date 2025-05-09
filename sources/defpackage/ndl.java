package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes9.dex */
public class ndl extends Drawable implements Animatable, Drawable.Callback, Animator.AnimatorListener {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f15267a;
    private Animator.AnimatorListener b;
    private ObjectAnimator c;
    private int d;
    private boolean e;
    private Drawable f;
    private View h;
    private int j;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    private void e() {
        setBounds(0, 0, this.j, this.d);
        this.f.setBounds(0, 0, this.j, this.d);
        this.f15267a.setBounds(0, 0, this.j, this.d);
    }

    private void c() {
        View view = this.h;
        boolean z = true;
        boolean z2 = false;
        if (view != null) {
            if (view.getWidth() != 0 && this.h.getWidth() != this.j) {
                this.j = this.h.getWidth();
                z2 = true;
            }
            if (this.h.getHeight() != 0 && this.h.getHeight() != this.d) {
                this.d = this.h.getHeight();
                z2 = true;
            }
        }
        Drawable drawable = this.f15267a;
        if (drawable != null) {
            if (this.j <= 0) {
                this.j = drawable.getIntrinsicWidth();
            } else {
                z = z2;
            }
            if (this.d <= 0) {
                this.d = this.f15267a.getIntrinsicHeight();
                e();
            }
            z2 = z;
        }
        if (!z2) {
            return;
        }
        e();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        View view = this.h;
        if (view != null && !view.getGlobalVisibleRect(new Rect())) {
            this.e = true;
        } else {
            this.c.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.c.end();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        ObjectAnimator objectAnimator = this.c;
        return objectAnimator != null && objectAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.e) {
            this.f.draw(canvas);
        }
        this.f15267a.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (255 <= i) {
            this.e = true;
        }
        if (this.e) {
            this.f15267a.setAlpha(255);
        } else {
            this.f15267a.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        c();
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        c();
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        invalidateSelf();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Animator.AnimatorListener animatorListener = this.b;
        if (animatorListener != null) {
            animatorListener.onAnimationStart(animator);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Animator.AnimatorListener animatorListener = this.b;
        if (animatorListener != null) {
            animatorListener.onAnimationEnd(animator);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Animator.AnimatorListener animatorListener = this.b;
        if (animatorListener != null) {
            animatorListener.onAnimationCancel(animator);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        Animator.AnimatorListener animatorListener = this.b;
        if (animatorListener != null) {
            animatorListener.onAnimationRepeat(animator);
        }
    }
}
