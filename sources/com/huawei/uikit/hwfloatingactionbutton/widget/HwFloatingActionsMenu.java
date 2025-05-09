package com.huawei.uikit.hwfloatingactionbutton.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import defpackage.smr;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class HwFloatingActionsMenu extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f10672a;
    private View b;
    private float c;
    private ColorStateList d;
    private float e;
    private AnimatorSet f;
    private Context g;
    private int h;
    private AnimatorSet i;
    private OnFloatingActionsMenuUpdateListener j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private HwFloatingActionButton v;
    private int w;
    private boolean x;
    private int y;

    public interface OnFloatingActionsMenuUpdateListener {
        void onMenuHide();

        void onMenuShow();
    }

    class a implements Animator.AnimatorListener {
        a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwFloatingActionsMenu.this.e();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HwFloatingActionsMenu.this.c();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class c {

        /* renamed from: a, reason: collision with root package name */
        private AnimatorSet f10673a;
        private AnimatorSet b;
        private AnimatorSet d;
        private AnimatorSet e;

        c(Context context) {
            b(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void edB_(View view) {
            this.d.setTarget(view);
            this.b.setTarget(view);
            HwFloatingActionsMenu.this.f.play(this.d);
            HwFloatingActionsMenu.this.i.play(this.b);
        }

        private void b(Context context) {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, HwFloatingActionsMenu.f10672a ? R.animator._2131034146_res_0x7f050022 : R.anim._2130772050_res_0x7f010052);
            Animator loadAnimator2 = AnimatorInflater.loadAnimator(context, HwFloatingActionsMenu.f10672a ? R.animator._2131034145_res_0x7f050021 : R.anim._2130772049_res_0x7f010051);
            Animator loadAnimator3 = AnimatorInflater.loadAnimator(context, HwFloatingActionsMenu.f10672a ? R.animator._2131034143_res_0x7f05001f : R.anim._2130772047_res_0x7f01004f);
            Animator loadAnimator4 = AnimatorInflater.loadAnimator(context, HwFloatingActionsMenu.f10672a ? R.animator._2131034144_res_0x7f050020 : R.anim._2130772048_res_0x7f010050);
            if ((loadAnimator instanceof AnimatorSet) && (loadAnimator2 instanceof AnimatorSet) && (loadAnimator3 instanceof AnimatorSet) && (loadAnimator4 instanceof AnimatorSet)) {
                this.e = (AnimatorSet) loadAnimator;
                this.d = (AnimatorSet) loadAnimator2;
                this.f10673a = (AnimatorSet) loadAnimator3;
                this.b = (AnimatorSet) loadAnimator4;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(View view) {
            this.e.setTarget(view);
            this.f10673a.setTarget(view);
            HwFloatingActionsMenu.this.f.play(this.e);
            HwFloatingActionsMenu.this.i.play(this.f10673a);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(long j) {
            this.d.setStartDelay(j);
            this.e.setStartDelay(j);
            long j2 = 100 - j;
            this.b.setStartDelay(j2);
            this.f10673a.setStartDelay(j2);
        }
    }

    class d implements Animator.AnimatorListener {
        d() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwFloatingActionsMenu.this.j();
        }
    }

    static {
        f10672a = k() || o();
    }

    public HwFloatingActionsMenu(Context context) {
        this(context, null);
    }

    private void f() {
        Context context = this.g;
        boolean z = f10672a;
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, z ? R.animator._2131034145_res_0x7f050021 : R.anim._2130772049_res_0x7f010051);
        Animator loadAnimator2 = AnimatorInflater.loadAnimator(this.g, z ? R.animator._2131034144_res_0x7f050020 : R.anim._2130772048_res_0x7f010050);
        if ((loadAnimator instanceof AnimatorSet) && (loadAnimator2 instanceof AnimatorSet)) {
            AnimatorSet animatorSet = (AnimatorSet) loadAnimator;
            AnimatorSet animatorSet2 = (AnimatorSet) loadAnimator2;
            this.b.setAlpha(0.0f);
            animatorSet.setTarget(this.b);
            animatorSet2.setTarget(this.b);
            this.f.play(animatorSet);
            this.i.play(animatorSet2);
        }
    }

    private void g() {
        for (int i = this.w - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (childAt != this.v && (childAt instanceof HwFloatingActionButton)) {
                int i2 = this.h + 50;
                this.h = i2;
                if (i2 >= 100) {
                    break;
                }
            }
        }
        int i3 = 0;
        for (int i4 = this.w - 1; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            if (childAt2 != this.v && (childAt2 instanceof HwFloatingActionButton)) {
                c cVar = new c(this.g);
                cVar.a(childAt2);
                childAt2.setScaleX(0.0f);
                childAt2.setScaleY(0.0f);
                Object tag = childAt2.getTag(R.id.hwfab_label);
                if (tag instanceof View) {
                    View view = (View) tag;
                    view.setAlpha(0.0f);
                    cVar.edB_(view);
                }
                int i5 = this.h;
                if (i3 > i5) {
                    i3 = i5;
                }
                cVar.c(i3);
                i3 += 50;
            }
        }
    }

    private int getMaxLabelWidth() {
        int i = 0;
        for (int i2 = 0; i2 < this.w; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8 && childAt != this.b && !(childAt instanceof HwTextView)) {
                if (childAt != this.v) {
                    this.m = this.o - ((this.t - childAt.getMeasuredWidth()) / 2);
                }
                Object tag = childAt.getTag(R.id.hwfab_label);
                if (tag instanceof HwTextView) {
                    HwTextView hwTextView = (HwTextView) tag;
                    if (this.s == 0) {
                        if ((this.l - this.t) - this.m < hwTextView.getMeasuredWidth()) {
                            int i3 = (this.l - this.t) - this.m;
                            if (i <= i3) {
                                i = i3;
                            }
                            hwTextView.setMaxWidth(i - this.o);
                        } else if (i <= hwTextView.getMeasuredWidth()) {
                            i = hwTextView.getMeasuredWidth();
                        }
                    } else if (((this.l - this.t) / 2) - this.m < hwTextView.getMeasuredWidth()) {
                        int i4 = ((this.l - this.t) / 2) - this.m;
                        if (i <= i4) {
                            i = i4;
                        }
                        hwTextView.setMaxWidth(i - this.o);
                    } else if (i <= hwTextView.getMeasuredWidth()) {
                        i = hwTextView.getMeasuredWidth();
                    }
                }
            }
        }
        return i;
    }

    private void h() {
        if (!this.x || this.f.isRunning()) {
            return;
        }
        this.i.start();
        OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener = this.j;
        if (onFloatingActionsMenuUpdateListener != null) {
            onFloatingActionsMenuUpdateListener.onMenuHide();
        }
    }

    private void i() {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.p);
        for (int i = 0; i < this.w; i++) {
            View childAt = getChildAt(i);
            if (childAt != this.b && (childAt instanceof HwFloatingActionButton)) {
                HwFloatingActionButton hwFloatingActionButton = (HwFloatingActionButton) childAt;
                String title = hwFloatingActionButton.getTitle();
                if (hwFloatingActionButton != this.v && hwFloatingActionButton.getTag(R.id.hwfab_label) == null) {
                    HwTextView c2 = HwTextView.c(contextThemeWrapper);
                    if (c2 == null) {
                        return;
                    }
                    if (title != null) {
                        c2.setText(title);
                    }
                    c2.setTextAppearance(getContext(), this.p);
                    c2.setAutoTextInfo(9, 1, 2);
                    addView(c2);
                    this.w++;
                    hwFloatingActionButton.setTag(R.id.hwfab_label, c2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.x = true;
        for (int i = 0; i < this.w; i++) {
            View childAt = getChildAt(i);
            if (childAt != this.v) {
                childAt.setVisibility(0);
                Object tag = childAt.getTag(R.id.hwfab_label);
                if (tag instanceof View) {
                    ((View) tag).setVisibility(0);
                }
            }
        }
    }

    private static boolean k() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            if (declaredMethod.invoke(null, "ro.build.hw_emui_lite.enable", false) instanceof Boolean) {
                return ((Boolean) declaredMethod.invoke(null, "ro.build.hw_emui_lite.enable", false)).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException unused) {
            Log.e("HwFabMenu", "isEmuiLite ClassNotFoundException info");
            return false;
        } catch (IllegalAccessException unused2) {
            Log.e("HwFabMenu", "isEmuiLite IllegalAccessException info");
            return false;
        } catch (NoSuchMethodException unused3) {
            Log.e("HwFabMenu", "isEmuiLite NoSuchMethodException info");
            return false;
        } catch (InvocationTargetException unused4) {
            Log.e("HwFabMenu", "isEmuiLite InvocationTargetException info");
            return false;
        }
    }

    private int l() {
        int i = 0;
        for (int i2 = 0; i2 < this.w; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8 && childAt != this.b && !(childAt instanceof HwTextView)) {
                int measuredWidth = childAt.getMeasuredWidth() + this.q + childAt.getPaddingLeft();
                int i3 = this.t;
                if (i3 > measuredWidth) {
                    measuredWidth = i3;
                }
                this.t = measuredWidth;
                i += childAt.getMeasuredHeight();
            }
        }
        return i;
    }

    private void m() {
        Context context = this.g;
        boolean z = f10672a;
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, z ? R.animator._2131034147_res_0x7f050023 : R.anim._2130772051_res_0x7f010053);
        Animator loadAnimator2 = AnimatorInflater.loadAnimator(this.g, z ? R.animator._2131034142_res_0x7f05001e : R.anim._2130772046_res_0x7f01004e);
        if ((loadAnimator instanceof AnimatorSet) && (loadAnimator2 instanceof AnimatorSet)) {
            AnimatorSet animatorSet = (AnimatorSet) loadAnimator;
            AnimatorSet animatorSet2 = (AnimatorSet) loadAnimator2;
            animatorSet.setTarget(this.v);
            animatorSet2.setTarget(this.v);
            this.f.play(animatorSet);
            this.i.play(animatorSet2);
        }
    }

    private void n() {
        if (this.x || this.i.isRunning()) {
            return;
        }
        this.f.start();
        OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener = this.j;
        if (onFloatingActionsMenuUpdateListener != null) {
            onFloatingActionsMenuUpdateListener.onMenuShow();
        }
    }

    private static boolean o() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            if (declaredMethod.invoke(null, "ro.config.hw_nova_performance", false) instanceof Boolean) {
                return ((Boolean) declaredMethod.invoke(null, "ro.config.hw_nova_performance", false)).booleanValue();
            }
            return false;
        } catch (ClassNotFoundException unused) {
            Log.e("HwFabMenu", "isNovaPerformance ClassNotFoundException info");
            return false;
        } catch (IllegalAccessException unused2) {
            Log.e("HwFabMenu", "isNovaPerformance IllegalAccessException info");
            return false;
        } catch (NoSuchMethodException unused3) {
            Log.e("HwFabMenu", "isNovaPerformance NoSuchMethodException info");
            return false;
        } catch (InvocationTargetException unused4) {
            Log.e("HwFabMenu", "isNovaPerformance InvocationTargetException info");
            return false;
        }
    }

    private void setDrawableBackground(Context context) {
        this.b = new View(context);
        addView(this.b, new ViewGroup.LayoutParams(-1, -1));
        this.b.setBackground(getResources().getDrawable(R.drawable.hwfab_background));
    }

    public void c() {
        if (this.x) {
            h();
        } else {
            n();
        }
    }

    public ColorStateList getFabBackgroundColor() {
        return this.d;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        bringChildToFront(this.v);
        this.w = getChildCount();
        if (this.p != 0) {
            i();
        }
        m();
        f();
        g();
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        this.b.layout(i, i2, i5, i6);
        c(i6 - this.v.getMeasuredHeight(), this.s == 0 ? (i5 - (this.v.getMeasuredWidth() / 2)) - this.q : i5 / 2);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        this.l = getResources().getDisplayMetrics().widthPixels;
        this.n = getResources().getDisplayMetrics().heightPixels;
        this.t = 0;
        d(l(), getMaxLabelWidth());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void setFabBackgroundColor(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return;
        }
        this.d = colorStateList;
        this.v.setBackgroundTintList(colorStateList);
    }

    public void setOnFloatingActionsMenuUpdateListener(OnFloatingActionsMenuUpdateListener onFloatingActionsMenuUpdateListener) {
        this.j = onFloatingActionsMenuUpdateListener;
    }

    public void setmButtonMarginBottom(int i) {
        this.r = i;
    }

    public void setmButtonMarginRight(int i) {
        this.q = i;
    }

    public HwFloatingActionsMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100327_res_0x7f0602a7);
    }

    private void a() {
        this.i.addListener(new a());
    }

    private void d(int i, int i2) {
        int i3 = this.t;
        int i4 = i2 > 0 ? i2 + this.m : 0;
        int i5 = this.r;
        int paddingTop = this.v.getPaddingTop();
        if (this.x) {
            setMeasuredDimension(this.l, this.n);
        } else {
            setMeasuredDimension(i3 + i4, i + i5 + paddingTop);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        for (int i = 0; i < this.w; i++) {
            View childAt = getChildAt(i);
            if (childAt != this.v) {
                childAt.setVisibility(8);
                Object tag = childAt.getTag(R.id.hwfab_label);
                if (tag instanceof View) {
                    ((View) tag).setVisibility(8);
                }
            }
        }
        this.x = false;
    }

    public HwFloatingActionsMenu(Context context, AttributeSet attributeSet, int i) {
        super(c(context, i), attributeSet, i);
        this.x = false;
        this.h = -50;
        this.f = new AnimatorSet();
        this.i = new AnimatorSet();
        edy_(super.getContext(), attributeSet, i);
    }

    private static Context c(Context context, int i) {
        return smr.b(context, i, R.style.Theme_Emui_HwFloatingActionButton);
    }

    private void edy_(Context context, AttributeSet attributeSet, int i) {
        this.g = context;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364248_res_0x7f0a0998);
        this.o = dimensionPixelSize;
        this.q = getResources().getDimensionPixelSize(R.dimen._2131362634_res_0x7f0a034a);
        this.r = dimensionPixelSize;
        this.k = getResources().getDimensionPixelSize(R.dimen._2131364249_res_0x7f0a0999);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100305_res_0x7f060291, R.attr._2131100309_res_0x7f060295, R.attr._2131100310_res_0x7f060296, R.attr._2131100311_res_0x7f060297, R.attr._2131100315_res_0x7f06029b, R.attr._2131100317_res_0x7f06029d, R.attr._2131100318_res_0x7f06029e}, i, R.style.Widget_Emui_HwFloatingActionsMenu);
        this.p = obtainStyledAttributes.getResourceId(4, 0);
        this.s = obtainStyledAttributes.getInt(2, 0);
        this.d = obtainStyledAttributes.getColorStateList(0);
        this.y = obtainStyledAttributes.getColor(6, 0);
        this.u = obtainStyledAttributes.getResourceId(3, 0);
        this.e = obtainStyledAttributes.getDimension(1, 0.0f);
        this.c = obtainStyledAttributes.getDimension(5, 0.0f);
        obtainStyledAttributes.recycle();
        a(context);
        setDrawableBackground(context);
    }

    private void b() {
        this.f.addListener(new d());
        a();
    }

    private void a(Context context) {
        HwFloatingActionButton c2 = HwFloatingActionButton.c(context);
        this.v = c2;
        if (c2 == null) {
            return;
        }
        c2.setImageResource(this.u);
        this.v.setRippleColor(this.y);
        this.v.setCompatElevation(this.e);
        this.v.setCompatPressedTranslationZ(this.c);
        this.v.setId(R.id.hwfab_expand_menu_button);
        this.v.setSize(0);
        ColorStateList colorStateList = this.d;
        if (colorStateList != null) {
            this.v.setBackgroundTintList(colorStateList);
        }
        this.v.setOnClickListener(new b());
        addView(this.v, super.generateDefaultLayoutParams());
        this.w++;
    }

    private void c(int i, int i2) {
        int measuredWidth = i2 - (this.v.getMeasuredWidth() / 2);
        int i3 = i - this.r;
        boolean z = getLayoutDirection() == 1;
        if (z) {
            if (this.s == 0) {
                HwFloatingActionButton hwFloatingActionButton = this.v;
                int i4 = this.q;
                hwFloatingActionButton.layout(i4, i3, hwFloatingActionButton.getMeasuredWidth() + i4, this.v.getMeasuredHeight() + i3);
            } else {
                HwFloatingActionButton hwFloatingActionButton2 = this.v;
                hwFloatingActionButton2.layout(measuredWidth, i3, hwFloatingActionButton2.getMeasuredWidth() + measuredWidth, this.v.getMeasuredHeight() + i3);
            }
        } else {
            HwFloatingActionButton hwFloatingActionButton3 = this.v;
            hwFloatingActionButton3.layout(measuredWidth, i3, hwFloatingActionButton3.getMeasuredWidth() + measuredWidth, this.v.getMeasuredHeight() + i3);
        }
        a(i2, z, i2 - ((this.t / 2) + this.m), i);
    }

    private void a(int i, boolean z, int i2, int i3) {
        int i4 = (i3 - this.k) - this.r;
        for (int i5 = this.w - 1; i5 >= 0; i5--) {
            View childAt = getChildAt(i5);
            if (childAt != this.v && childAt.getVisibility() != 8 && childAt != this.b && !(childAt instanceof HwTextView)) {
                int measuredWidth = i - (childAt.getMeasuredWidth() / 2);
                int measuredHeight = i4 - childAt.getMeasuredHeight();
                if (z) {
                    childAt.layout((this.l - measuredWidth) - childAt.getMeasuredWidth(), measuredHeight, this.l - measuredWidth, childAt.getMeasuredHeight() + measuredHeight);
                } else {
                    childAt.layout(measuredWidth, measuredHeight, childAt.getMeasuredWidth() + measuredWidth, childAt.getMeasuredHeight() + measuredHeight);
                }
                Object tag = childAt.getTag(R.id.hwfab_label);
                if (tag instanceof HwTextView) {
                    HwTextView hwTextView = (HwTextView) tag;
                    int measuredWidth2 = i2 - hwTextView.getMeasuredWidth();
                    if (measuredWidth2 <= 0) {
                        measuredWidth2 = 0;
                    }
                    int measuredHeight2 = ((childAt.getMeasuredHeight() - hwTextView.getMeasuredHeight()) / 2) + measuredHeight;
                    if (z) {
                        hwTextView.layout((this.l - measuredWidth2) - hwTextView.getMeasuredWidth(), measuredHeight2, this.l - measuredWidth2, hwTextView.getMeasuredHeight() + measuredHeight2);
                    } else {
                        hwTextView.layout(measuredWidth2, measuredHeight2, i2, hwTextView.getMeasuredHeight() + measuredHeight2);
                    }
                }
                i4 = measuredHeight - this.k;
            }
        }
    }
}
