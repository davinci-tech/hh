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

/* loaded from: classes6.dex */
public class obe {
    private static final Float e = Float.valueOf(0.5f);
    private static final Float c = Float.valueOf(12.0f);

    /* renamed from: a, reason: collision with root package name */
    private static final Float f15595a = Float.valueOf(20.0f);
    private static final Float d = Float.valueOf(24.0f);
    private static final Float g = Float.valueOf(48.0f);
    private static final Float b = Float.valueOf(0.0f);

    public static void c(Context context, c cVar) {
        int d2;
        if (context == null || cVar == null) {
            return;
        }
        HealthSearchView d3 = cVar.d();
        LinearLayout cUm_ = cVar.cUm_();
        LinearLayout cUl_ = cVar.cUl_();
        CustomTitleBar c2 = cVar.c();
        if (cUf_(d3, cUm_, cUl_, c2) || (d2 = d(context)) == 0) {
            return;
        }
        int b2 = b(context, f15595a.floatValue());
        int b3 = b(context, d.floatValue());
        ValueAnimator cUe_ = cUe_(d3, true);
        cUe_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(d3, d2, b2, b3, cUm_, cUl_, c2) { // from class: obe.4

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f15596a;
            final /* synthetic */ int b;
            float c;
            final /* synthetic */ View d;
            final /* synthetic */ int e;
            final /* synthetic */ int h;
            final /* synthetic */ CustomTitleBar i;
            final /* synthetic */ View j;

            {
                this.j = d3;
                this.e = d2;
                this.h = b2;
                this.b = b3;
                this.f15596a = cUm_;
                this.d = cUl_;
                this.i = c2;
                this.c = -d3.getHeight();
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator == null) {
                    return;
                }
                float cUd_ = obe.cUd_(valueAnimator);
                int height = this.j.getHeight();
                if (height > 0 && height != ((int) (-this.c))) {
                    this.c = -height;
                }
                obe.cUi_(this.j, this.e * cUd_);
                obe.cUj_(this.j, this.h, this.b, cUd_);
                obe.cUk_(this.f15596a, cUd_);
                obe.cUh_(this.j, this.d, cUd_, this.c);
                this.i.getViewTitle().setAlpha(1.0f - cUd_);
            }
        });
        cUg_(cUe_);
    }

    public static void e(Context context, c cVar) {
        final int d2;
        if (context == null || cVar == null) {
            LogUtil.c("SearchAnimationUtils", "context or viewBeans is null");
            return;
        }
        final HealthSearchView d3 = cVar.d();
        final LinearLayout cUm_ = cVar.cUm_();
        final LinearLayout cUl_ = cVar.cUl_();
        final CustomTitleBar c2 = cVar.c();
        if (cUf_(d3, cUm_, cUl_, c2) || (d2 = d(context)) == 0) {
            return;
        }
        final int b2 = b(context, f15595a.floatValue());
        final int b3 = b(context, d.floatValue());
        ValueAnimator cUe_ = cUe_(d3, false);
        final float translationY = d3.getTranslationY();
        cUe_.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: obe.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator == null) {
                    return;
                }
                float cUd_ = obe.cUd_(valueAnimator);
                obe.cUi_(d3, d2 * cUd_);
                obe.cUj_(d3, b2, b3, cUd_);
                obe.cUk_(cUm_, cUd_);
                obe.cUh_(d3, cUl_, cUd_, translationY);
                c2.getViewTitle().setAlpha(1.0f - cUd_);
            }
        });
        cUg_(cUe_);
    }

    private static boolean cUf_(View view, View view2, View view3, View view4) {
        if (view == null) {
            LogUtil.c("SearchAnimationUtils", "searchView is null");
            return true;
        }
        if (view2 == null) {
            LogUtil.c("SearchAnimationUtils", "searchMask is null");
            return true;
        }
        if (view3 == null) {
            LogUtil.c("SearchAnimationUtils", "searchHolder is null");
            return true;
        }
        if (view4 != null) {
            return false;
        }
        LogUtil.c("SearchAnimationUtils", "titleLayout is null");
        return true;
    }

    private static int d(Context context) {
        int b2 = b(context, g.floatValue()) + b(context, c.floatValue());
        if (b2 > 0) {
            return b2;
        }
        return 0;
    }

    static ValueAnimator cUe_(View view, boolean z) {
        Context context = view.getContext();
        ValueAnimator ofFloat = z ? ValueAnimator.ofFloat(b.floatValue(), 1.0f) : ValueAnimator.ofFloat(1.0f, b.floatValue());
        ofFloat.setInterpolator(AnimationUtils.loadInterpolator(context, 17563661));
        ofFloat.setDuration(300L);
        return ofFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cUj_(View view, int i, int i2, float f) {
        view.setPaddingRelative((int) (((-i) * f) + i2), 0, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cUi_(View view, float f) {
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
    public static float cUd_(ValueAnimator valueAnimator) {
        float floatValue = b.floatValue();
        Object animatedValue = valueAnimator.getAnimatedValue();
        return animatedValue instanceof Float ? ((Float) animatedValue).floatValue() : floatValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cUh_(View view, View view2, float f, float f2) {
        float f3 = f * f2;
        view.setTranslationY(f3);
        view2.getLayoutParams().height = view.getHeight() + ((int) f3);
        view.requestLayout();
        view2.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cUk_(View view, float f) {
        if (view != null) {
            view.setAlpha(f * 0.2f);
        }
    }

    private static void cUg_(Animator animator) {
        if (animator != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animator);
            animatorSet.start();
        }
    }

    public static int b(Context context, float f) {
        if (context == null) {
            LogUtil.c("SearchAnimationUtils", "dip2px,context is null");
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + e.floatValue());
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private CustomTitleBar f15598a;
        private HealthSearchView b;
        private LinearLayout c;
        private LinearLayout e;

        public CustomTitleBar c() {
            return this.f15598a;
        }

        public void a(CustomTitleBar customTitleBar) {
            this.f15598a = customTitleBar;
        }

        public HealthSearchView d() {
            return this.b;
        }

        public void a(HealthSearchView healthSearchView) {
            this.b = healthSearchView;
        }

        public LinearLayout cUm_() {
            return this.e;
        }

        public void cUo_(LinearLayout linearLayout) {
            this.e = linearLayout;
        }

        public LinearLayout cUl_() {
            return this.c;
        }

        public void cUn_(LinearLayout linearLayout) {
            this.c = linearLayout;
        }
    }
}
