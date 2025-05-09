package com.huawei.uikit.hwbottomsheet.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.uikit.hwbottomsheet.R$drawable;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HwDragImageView extends ImageView {

    /* renamed from: a, reason: collision with root package name */
    private String f10603a;
    private String b;
    private HwBottomSheet c;
    private String d;
    private String e;
    private Drawable f;
    private Drawable g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private float k;
    private float l;
    private Interpolator m;
    private boolean n;
    private Drawable o;
    private float p;
    private float r;
    private float s;
    private float t;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HwBottomSheet.SheetState.values().length];
            e = iArr;
            try {
                iArr[HwBottomSheet.SheetState.COLLAPSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HwBottomSheet.SheetState.ANCHORED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[HwBottomSheet.SheetState.EXPANDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    class d implements Animator.AnimatorListener {
        d() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            HwDragImageView.this.d();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwDragImageView.this.k = 0.0f;
            HwDragImageView.this.l = 0.0f;
            HwDragImageView.this.d();
            HwDragImageView.this.invalidate();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    class e implements ValueAnimator.AnimatorUpdateListener {
        e() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator != null && (valueAnimator.getAnimatedValue() instanceof Float)) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                HwDragImageView.this.k *= floatValue;
                HwDragImageView.this.l *= floatValue;
                HwDragImageView.this.invalidate();
            }
        }
    }

    public HwDragImageView(Context context) {
        super(context);
        this.e = "";
        this.b = "";
        this.d = "";
        this.f10603a = "";
        this.n = false;
        e(context);
    }

    private void b() {
        eao_(a(true), true);
        this.k = 0.0f;
        this.l = 0.0f;
    }

    private void e(Context context) {
        a(context);
        b(context);
        this.t = context.getResources().getDisplayMetrics().density * 12.0f;
        this.s = context.getResources().getDisplayMetrics().density * 5.0f;
    }

    private void f() {
        if (this.n) {
            eao_(a(false), false);
            a();
            this.n = false;
        }
    }

    void a() {
        HwBottomSheet hwBottomSheet = this.c;
        if (hwBottomSheet == null || hwBottomSheet.a()) {
            return;
        }
        if (Float.compare(this.k, 0.0f) == 0 && Float.compare(this.l, 0.0f) == 0) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(this.m);
        ofFloat.addListener(new d());
        ofFloat.addUpdateListener(new e());
        ofFloat.start();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        HwBottomSheet hwBottomSheet = this.c;
        if (hwBottomSheet == null || hwBottomSheet.a()) {
            super.draw(canvas);
        } else {
            canvas.translate(this.k, this.l);
            super.draw(canvas);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            this.p = motionEvent.getRawX();
            this.r = motionEvent.getRawY();
        } else if (action == 10) {
            this.p = -1.0f;
            this.r = -1.0f;
        }
        return onHoverEvent;
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null || this.c == null) {
            return;
        }
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        String str = this.e;
        if (str == null) {
            return;
        }
        int i = a.e[this.c.getSheetState().ordinal()];
        if (i == 1) {
            str = String.format(Locale.ENGLISH, this.e, this.b);
        } else if (i == 2) {
            str = String.format(Locale.ENGLISH, this.e, this.d);
        } else if (i == 3) {
            str = String.format(Locale.ENGLISH, this.e, this.f10603a);
        }
        if (str == null) {
            return;
        }
        setContentDescription(str);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        HwBottomSheet hwBottomSheet;
        if (motionEvent == null) {
            return false;
        }
        HwBottomSheet hwBottomSheet2 = this.c;
        if (hwBottomSheet2 == null || hwBottomSheet2.a()) {
            return super.onTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                f();
            } else if (actionMasked == 3 && (hwBottomSheet = this.c) != null && hwBottomSheet.getViewDragHelper() != null && this.c.getViewDragHelper().b() != 1) {
                f();
            }
        } else if (!this.c.e()) {
            b();
        }
        return super.onTouchEvent(motionEvent);
    }

    void e(HwBottomSheet hwBottomSheet) {
        this.c = hwBottomSheet;
    }

    private void a(Context context) {
        this.e = context.getString(R.string._2130851314_res_0x7f0235f2);
        this.d = context.getString(R.string._2130851313_res_0x7f0235f1);
        this.b = context.getString(R.string._2130851315_res_0x7f0235f3);
        this.f10603a = context.getString(R.string._2130851316_res_0x7f0235f4);
    }

    private void b(Context context) {
        this.i = context.getDrawable(R$drawable.hwbottomsheet_indicate_middle_scale_big);
        this.h = context.getDrawable(R$drawable.hwbottomsheet_indicate_middle_scale_normal);
        this.j = context.getDrawable(R$drawable.hwbottomsheet_indicate_down_scale_big);
        this.f = context.getDrawable(R$drawable.hwbottomsheet_indicate_down_scale_normal);
        this.g = context.getDrawable(R$drawable.hwbottomsheet_indicate_up_scale_big);
        this.o = context.getDrawable(R$drawable.hwbottomsheet_indicate_up_scale_normal);
        this.m = AnimationUtils.loadInterpolator(context, R.interpolator._2131689480_res_0x7f0f0008);
    }

    void c() {
        this.n = false;
    }

    public HwDragImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = "";
        this.b = "";
        this.d = "";
        this.f10603a = "";
        this.n = false;
        e(context);
    }

    private Drawable a(boolean z) {
        HwBottomSheet hwBottomSheet = this.c;
        if (hwBottomSheet != null && !hwBottomSheet.a()) {
            int i = a.e[this.c.getSheetState().ordinal()];
            if (i == 1) {
                return z ? this.g : this.o;
            }
            if (i == 2) {
                return z ? this.i : this.h;
            }
            if (i == 3) {
                return z ? this.j : this.f;
            }
        }
        return null;
    }

    void d() {
        if (Float.compare(this.p, 0.0f) <= 0 || Float.compare(this.r, 0.0f) <= 0 || !isHovered()) {
            return;
        }
        getLocationOnScreen(new int[2]);
        if (Float.compare(this.p, r0[0]) >= 0 && Float.compare(this.p, r0[0] + getWidth()) <= 0) {
            if (Float.compare(this.r, r0[1]) < 0 || Float.compare(this.r, r0[1] + getHeight()) > 0) {
                setHovered(false);
                return;
            }
            return;
        }
        setHovered(false);
    }

    Drawable ean_(HwBottomSheet.SheetState sheetState) {
        HwBottomSheet hwBottomSheet;
        if (sheetState == null || (hwBottomSheet = this.c) == null || hwBottomSheet.a()) {
            return null;
        }
        int i = a.e[sheetState.ordinal()];
        if (i == 1) {
            return this.o;
        }
        if (i == 2) {
            return this.h;
        }
        if (i != 3) {
            return null;
        }
        return this.f;
    }

    public HwDragImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = "";
        this.b = "";
        this.d = "";
        this.f10603a = "";
        this.n = false;
        e(context);
    }

    void eao_(Drawable drawable, boolean z) {
        HwBottomSheet hwBottomSheet = this.c;
        if (hwBottomSheet == null || hwBottomSheet.a() || drawable == null || !(drawable instanceof AnimatedVectorDrawable)) {
            return;
        }
        setImageDrawable(drawable);
        ((AnimatedVectorDrawable) drawable).start();
        if (z) {
            this.n = true;
        }
    }

    boolean e() {
        return this.n;
    }

    void a(float f, float f2) {
        float f3;
        float f4;
        HwBottomSheet hwBottomSheet = this.c;
        if (hwBottomSheet == null || hwBottomSheet.a() || !this.n) {
            return;
        }
        float f5 = this.k + (f * 0.1f);
        this.k = f5;
        this.l += f2 * 0.04f;
        if (Math.abs(f5) > this.t) {
            f3 = Float.compare(this.k, 0.0f) >= 0 ? this.t : -this.t;
        } else {
            f3 = this.k;
        }
        this.k = f3;
        if (Math.abs(this.l) > this.s) {
            f4 = Float.compare(this.l, 0.0f) >= 0 ? this.s : -this.s;
        } else {
            f4 = this.l;
        }
        this.l = f4;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "";
    }
}
