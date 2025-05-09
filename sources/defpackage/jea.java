package defpackage;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Outline;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.animation.EnterAnimationCallback;
import com.huawei.hwcommonmodel.utils.animation.ExitAnimationCallback;
import com.huawei.hwcommonmodel.utils.animation.ExitAnimationStartCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jea {

    /* renamed from: a, reason: collision with root package name */
    private ValueAnimator f13759a;
    private ViewGroup c;
    private ObjectAnimator d;
    private View e;
    private ValueAnimator f;
    private ExitAnimationStartCallback g;
    private ExitAnimationCallback i;
    private EnterAnimationCallback j;
    private float k;
    private float m;
    private Rect n;
    private int o;
    private int p;
    private Rect q = new Rect();
    private boolean h = false;
    private boolean l = false;
    private List<View> s = new ArrayList(10);
    private final Property<View, Rect> b = new Property<View, Rect>(Rect.class, "bounds") { // from class: jea.3
        @Override // android.util.Property
        /* renamed from: bGq_, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            if (view == null) {
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
            if (rect != null) {
                marginLayoutParams.setMarginStart(rect.left);
                marginLayoutParams.topMargin = rect.top;
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginLayoutParams);
                layoutParams.height = rect.height();
                layoutParams.width = rect.width();
                view.setLayoutParams(layoutParams);
            }
        }

        @Override // android.util.Property
        /* renamed from: bGp_, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    };

    public jea(Rect rect, ViewGroup viewGroup) {
        this.n = rect;
        this.c = viewGroup;
    }

    public void bGo_(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public void d(ExitAnimationCallback exitAnimationCallback) {
        this.i = exitAnimationCallback;
    }

    public void b(ExitAnimationStartCallback exitAnimationStartCallback) {
        this.g = exitAnimationStartCallback;
    }

    public void e(EnterAnimationCallback enterAnimationCallback) {
        this.j = enterAnimationCallback;
    }

    public void b() {
        Rect rect = this.n;
        if (rect == null || this.c == null) {
            LogUtil.h("ActivityAnimationHelper", "mSourceRect or mBootLayout is null");
            return;
        }
        this.p = rect.right - this.n.left;
        this.o = this.n.bottom - this.n.top;
        this.c.post(new Runnable() { // from class: jea.4
            @Override // java.lang.Runnable
            public void run() {
                jea.this.c.getGlobalVisibleRect(jea.this.q);
                int i = jea.this.q.right - jea.this.q.left;
                int i2 = jea.this.q.bottom - jea.this.q.top;
                if (i == 0 || i2 == 0) {
                    LogUtil.h("ActivityAnimationHelper", "targetWidth or targetHeight is 0");
                    jea.this.c.setVisibility(0);
                    return;
                }
                jea.this.k = r3.p / i;
                jea.this.m = r0.o / i2;
                if (jea.this.c.getChildCount() > 0) {
                    jea jeaVar = jea.this;
                    jeaVar.e = jeaVar.c.getChildAt(0);
                }
                if (jea.this.e == null) {
                    jea.this.c.setVisibility(0);
                    return;
                }
                jea.this.j();
                jea.this.i();
                jea.this.c.postDelayed(new Runnable() { // from class: jea.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        jea.this.e();
                    }
                }, 300L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.c.setOutlineProvider(new ViewOutlineProvider() { // from class: jea.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline != null) {
                    outline.setRoundRect(jea.this.q.left, jea.this.q.top, jea.this.q.right, jea.this.q.bottom, 90.0f);
                }
            }
        });
        this.c.setClipToOutline(true);
        this.e.setOutlineProvider(new ViewOutlineProvider() { // from class: jea.10
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline != null) {
                    outline.setRoundRect(jea.this.q.left, jea.this.q.top, jea.this.q.right, jea.this.q.bottom, 90.0f);
                }
            }
        });
        this.e.setClipToOutline(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        c();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f13759a = ofFloat;
        ofFloat.setDuration(300L);
        this.f13759a.setInterpolator(new DecelerateInterpolator());
        this.f13759a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: jea.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator == null || valueAnimator.getAnimatedValue() == null) {
                    return;
                }
                jea.this.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.c.setVisibility(0);
        this.f13759a.start();
        EnterAnimationCallback enterAnimationCallback = this.j;
        if (enterAnimationCallback != null) {
            enterAnimationCallback.onEnterAnimationStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        float f2 = this.k;
        float f3 = this.m;
        float f4 = ((1.0f - f3) * f) + f3;
        this.c.setScaleX(((1.0f - f2) * f) + f2);
        this.c.setScaleY(f4);
        float f5 = 1.0f - f;
        this.c.setTranslationX(((this.n.left + ((this.n.right - this.n.left) / 2.0f)) - (this.q.left + ((this.q.right - this.q.left) / 2.0f))) * f5);
        this.c.setTranslationY(((this.n.top + ((this.n.bottom - this.n.top) / 2.0f)) - (this.q.top + ((this.q.bottom - this.q.top) / 2.0f))) * f5);
        View view = this.e;
        if (view != null) {
            if (f4 > 0.0f) {
                float f6 = this.k;
                view.setScaleY((((1.0f - f6) * f) + f6) * (1.0f / f4));
            }
            if (f < 0.01f) {
                this.e.setAlpha(0.0f);
            } else if (f <= 0.5f) {
                this.e.setAlpha((0.4f * f) + 0.8f);
            } else {
                LogUtil.c("ActivityAnimationHelper", "mChildView");
                this.e.setAlpha(1.0f);
            }
        }
        Iterator<View> it = this.s.iterator();
        while (it.hasNext()) {
            it.next().setAlpha(f);
        }
    }

    private void c() {
        this.s.clear();
        View view = this.e;
        if (view == null || !(view instanceof ViewGroup)) {
            return;
        }
        for (int i = 0; i < ((ViewGroup) this.e).getChildCount(); i++) {
            View childAt = ((ViewGroup) this.e).getChildAt(i);
            if (childAt != null && childAt.getVisibility() == 0) {
                this.s.add(childAt);
            }
        }
    }

    private void f() {
        this.c.setBackgroundColor(0);
        c();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.f = ofFloat;
        ofFloat.setDuration(300L);
        this.f.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: jea.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator == null || valueAnimator.getAnimatedValue() == null) {
                    return;
                }
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float f = jea.this.k;
                float f2 = jea.this.m;
                jea.this.c.setScaleX(((f - 1.0f) * floatValue) + 1.0f);
                jea.this.c.setScaleY(((f2 - 1.0f) * floatValue) + 1.0f);
                jea.this.c.setTranslationX(((jea.this.n.left + ((jea.this.n.right - jea.this.n.left) / 2.0f)) - (jea.this.q.left + ((jea.this.q.right - jea.this.q.left) / 2.0f))) * floatValue);
                jea.this.c.setTranslationY(((jea.this.n.top + ((jea.this.n.bottom - jea.this.n.top) / 2.0f)) - (jea.this.q.top + ((jea.this.q.bottom - jea.this.q.top) / 2.0f))) * floatValue);
                if (jea.this.e != null) {
                    float f3 = ((jea.this.m - 1.0f) * floatValue) + 1.0f;
                    if (f3 > 0.0f) {
                        jea.this.e.setScaleY((((jea.this.k - 1.0f) * floatValue) + 1.0f) * (1.0f / f3));
                    }
                }
                if (floatValue > 0.99f) {
                    jea.this.e.setAlpha(0.0f);
                } else if (floatValue >= 0.5f) {
                    jea.this.e.setAlpha(1.0f - ((floatValue - 0.5f) * 0.4f));
                } else {
                    LogUtil.c("ActivityAnimationHelper", "no care");
                }
                Iterator it = jea.this.s.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setAlpha(1.0f - floatValue);
                }
            }
        });
        this.f.addListener(new Animator.AnimatorListener() { // from class: jea.9
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                jea.this.e.clearAnimation();
                jea.this.c.clearAnimation();
            }
        });
        this.f.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ValueAnimator valueAnimator = this.f13759a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.c.setAlpha(1.0f);
        this.c.setTranslationX(0.0f);
        this.c.setTranslationY(0.0f);
        this.c.setScaleX(1.0f);
        this.c.setScaleY(1.0f);
        this.c.setBackgroundColor(nrn.d(R$color.colorBackground));
        this.c.setClipToOutline(false);
        View view = this.e;
        if (view != null) {
            view.setAlpha(1.0f);
            this.e.setTranslationX(0.0f);
            this.e.setTranslationY(0.0f);
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
            this.e.setClipToOutline(false);
        }
        EnterAnimationCallback enterAnimationCallback = this.j;
        if (enterAnimationCallback != null) {
            enterAnimationCallback.onEnterAnimationEnd();
        }
    }

    public void a() {
        ViewGroup viewGroup;
        if (this.n == null || (viewGroup = this.c) == null || this.e == null) {
            LogUtil.h("ActivityAnimationHelper", "startExit mSourceRect,mBootLayout or mChildView is null");
            ExitAnimationCallback exitAnimationCallback = this.i;
            if (exitAnimationCallback != null) {
                exitAnimationCallback.onExitAnimationEnd();
                return;
            }
            return;
        }
        if (this.h) {
            LogUtil.h("ActivityAnimationHelper", "startExit is doing!");
            return;
        }
        this.h = true;
        viewGroup.setBackgroundColor(nrn.d(R$color.common_transparent));
        ExitAnimationStartCallback exitAnimationStartCallback = this.g;
        if (exitAnimationStartCallback != null) {
            exitAnimationStartCallback.onExitAnimationStart();
        }
        this.e.setOutlineProvider(new ViewOutlineProvider() { // from class: jea.8
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline == null || view == null) {
                    return;
                }
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), jea.c(30.0f));
            }
        });
        this.e.setClipToOutline(true);
        this.c.setOutlineProvider(new ViewOutlineProvider() { // from class: jea.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                if (outline == null || view == null) {
                    return;
                }
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), jea.c(30.0f));
            }
        });
        this.c.setClipToOutline(true);
        f();
        this.c.postDelayed(new Runnable() { // from class: jea.5
            @Override // java.lang.Runnable
            public void run() {
                jea.this.d();
            }
        }, 300L);
    }

    private boolean g() {
        return this.l && !CommonUtil.bh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.c.setVisibility(4);
        ValueAnimator valueAnimator = this.f13759a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.f;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.c.setAlpha(1.0f);
        this.c.setTranslationX(0.0f);
        this.c.setTranslationY(0.0f);
        this.c.setScaleX(1.0f);
        this.c.setScaleY(1.0f);
        this.c.setClipToOutline(false);
        View view = this.e;
        if (view != null) {
            view.setAlpha(1.0f);
            this.e.setTranslationX(0.0f);
            this.e.setTranslationY(0.0f);
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
            this.e.setClipToOutline(false);
            if (!g()) {
                this.b.set(this.e, this.q);
            }
        }
        ExitAnimationCallback exitAnimationCallback = this.i;
        if (exitAnimationCallback != null) {
            exitAnimationCallback.onExitAnimationEnd();
        }
        this.h = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float c(float f) {
        return f * BaseApplication.getContext().getResources().getDisplayMetrics().density;
    }
}
