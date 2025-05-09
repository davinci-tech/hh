package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class rdm {
    private static final Float e = Float.valueOf(0.5f);
    private static final Float b = Float.valueOf(12.0f);
    private static final Float d = Float.valueOf(20.0f);

    /* renamed from: a, reason: collision with root package name */
    private static final Float f16715a = Float.valueOf(24.0f);
    private static final Float h = Float.valueOf(48.0f);
    private static final Float c = Float.valueOf(0.0f);

    public static void d(Context context, d dVar) {
        if (context == null || dVar == null) {
            LogUtil.c("SearchSportAnimationUtils", "context or viewBeans is null");
            return;
        }
        HealthSearchView c2 = dVar.c();
        LinearLayout dMr_ = dVar.dMr_();
        LinearLayout dMq_ = dVar.dMq_();
        CustomTitleBar e2 = dVar.e();
        boolean dMk_ = dMk_(c2, dMr_, dMq_, e2);
        if (dMk_) {
            LogUtil.c("SearchSportAnimationUtils", "isViewNull is", Boolean.valueOf(dMk_));
            return;
        }
        int e3 = e(context);
        if (e3 == 0) {
            return;
        }
        int d2 = d(context, d.floatValue());
        int d3 = d(context, f16715a.floatValue());
        ValueAnimator dMj_ = dMj_(c2, true);
        dMj_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(c2, e3, d2, d3, dMr_, dMq_, e2) { // from class: rdm.5

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f16717a;
            final /* synthetic */ View b;
            float c;
            final /* synthetic */ View d;
            final /* synthetic */ int e;
            final /* synthetic */ int f;
            final /* synthetic */ CustomTitleBar g;
            final /* synthetic */ View i;

            {
                this.i = c2;
                this.e = e3;
                this.f = d2;
                this.f16717a = d3;
                this.d = dMr_;
                this.b = dMq_;
                this.g = e2;
                this.c = -c2.getHeight();
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator != null) {
                    float dMi_ = rdm.dMi_(valueAnimator);
                    int height = this.i.getHeight();
                    if (height > 0 && height != ((int) (-this.c))) {
                        this.c = -height;
                    }
                    rdm.dMn_(this.i, this.e * dMi_);
                    rdm.dMo_(this.i, this.f, this.f16717a, dMi_);
                    rdm.dMp_(this.d, dMi_);
                    rdm.dMm_(this.i, this.b, dMi_, this.c);
                    this.g.getViewTitle().setAlpha(1.0f - dMi_);
                    return;
                }
                LogUtil.c("SearchSportAnimationUtils", "animation is null");
            }
        });
        dMl_(dMj_);
    }

    public static void c(Context context, d dVar) {
        if (context == null || dVar == null) {
            LogUtil.c("SearchSportAnimationUtils", "context or viewBeans is null");
            return;
        }
        final HealthSearchView c2 = dVar.c();
        final LinearLayout dMr_ = dVar.dMr_();
        final LinearLayout dMq_ = dVar.dMq_();
        final CustomTitleBar e2 = dVar.e();
        boolean dMk_ = dMk_(c2, dMr_, dMq_, e2);
        if (dMk_) {
            LogUtil.c("SearchSportAnimationUtils", "isViewNull is", Boolean.valueOf(dMk_));
            return;
        }
        final int e3 = e(context);
        if (e3 == 0) {
            return;
        }
        final int d2 = d(context, d.floatValue());
        final int d3 = d(context, f16715a.floatValue());
        ValueAnimator dMj_ = dMj_(c2, false);
        final float translationY = c2.getTranslationY();
        dMj_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: rdm.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator != null) {
                    float dMi_ = rdm.dMi_(valueAnimator);
                    rdm.dMn_(c2, e3 * dMi_);
                    rdm.dMo_(c2, d2, d3, dMi_);
                    rdm.dMp_(dMr_, dMi_);
                    rdm.dMm_(c2, dMq_, dMi_, translationY);
                    e2.getViewTitle().setAlpha(1.0f - dMi_);
                    return;
                }
                LogUtil.c("SearchSportAnimationUtils", "animation is null");
            }
        });
        dMl_(dMj_);
    }

    private static boolean dMk_(View view, View view2, View view3, View view4) {
        if (view == null) {
            LogUtil.c("SearchSportAnimationUtils", "searchView is null");
            return true;
        }
        if (view2 == null) {
            LogUtil.c("SearchSportAnimationUtils", "searchMask is null");
            return true;
        }
        if (view3 == null) {
            LogUtil.c("SearchSportAnimationUtils", "searchHolder is null");
            return true;
        }
        if (view4 != null) {
            return false;
        }
        LogUtil.c("SearchSportAnimationUtils", "titleLayout is null");
        return true;
    }

    private static int e(Context context) {
        int d2 = d(context, h.floatValue()) + d(context, b.floatValue());
        if (d2 > 0) {
            return d2;
        }
        return 0;
    }

    static ValueAnimator dMj_(View view, boolean z) {
        Context context = view.getContext();
        ValueAnimator ofFloat = z ? ValueAnimator.ofFloat(c.floatValue(), 1.0f) : ValueAnimator.ofFloat(1.0f, c.floatValue());
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(context, 17563661));
        ofFloat.setDuration(300L);
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dMo_(View view, int i, int i2, float f) {
        view.setPaddingRelative((int) (((-i) * f) + i2), 0, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dMn_(View view, float f) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart((int) f);
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = new ViewGroup.MarginLayoutParams(layoutParams);
            marginLayoutParams2.setMargins((int) f, 0, 0, 0);
            marginLayoutParams = marginLayoutParams2;
        }
        view.setLayoutParams(marginLayoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float dMi_(ValueAnimator valueAnimator) {
        float floatValue = c.floatValue();
        Object animatedValue = valueAnimator.getAnimatedValue();
        return animatedValue instanceof Float ? ((Float) animatedValue).floatValue() : floatValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dMm_(View view, View view2, float f, float f2) {
        float f3 = f * f2;
        view.setTranslationY(f3);
        view2.getLayoutParams().height = view.getHeight() + ((int) f3);
        view.requestLayout();
        view2.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dMp_(View view, float f) {
        if (view != null) {
            view.setAlpha(f * 0.2f);
        }
    }

    private static void dMl_(Animator animator) {
        if (animator != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animator);
            animatorSet.start();
        }
    }

    private static int d(Context context, float f) {
        if (context == null) {
            LogUtil.c("SearchSportAnimationUtils", "dip2px,context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + e.floatValue());
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private CustomTitleBar f16718a;
        private LinearLayout b;
        private HealthSearchView c;
        private LinearLayout d;

        public CustomTitleBar e() {
            return this.f16718a;
        }

        public void c(CustomTitleBar customTitleBar) {
            this.f16718a = customTitleBar;
        }

        public HealthSearchView c() {
            return this.c;
        }

        public void c(HealthSearchView healthSearchView) {
            this.c = healthSearchView;
        }

        public LinearLayout dMr_() {
            return this.b;
        }

        public void dMt_(LinearLayout linearLayout) {
            this.b = linearLayout;
        }

        public LinearLayout dMq_() {
            return this.d;
        }

        public void dMs_(LinearLayout linearLayout) {
            this.d = linearLayout;
        }
    }
}
