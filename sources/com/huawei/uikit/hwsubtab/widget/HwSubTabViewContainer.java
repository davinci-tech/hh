package com.huawei.uikit.hwsubtab.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwhorizontalscrollview.widget.HwHorizontalScrollView;
import com.huawei.uikit.hwsubtab.widget.HwSubTabViewContainer;
import defpackage.ske;
import defpackage.sle;
import defpackage.sms;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class HwSubTabViewContainer extends HwHorizontalScrollView {

    /* renamed from: a, reason: collision with root package name */
    private ValueAnimator f10751a;
    private int b;
    private int c;
    private SlidingTabStrip d;
    private int e;
    private int f;
    private int g;
    private d h;
    private boolean i;
    private int j;
    private int n;

    public static class SlidingTabStrip extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        int f10752a;
        private int b;
        float c;
        private Drawable d;
        private int e;
        private int f;
        private int g;
        private int h;
        private ValueAnimator i;
        private final Paint j;
        private c m;

        class e extends AnimatorListenerAdapter {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f10753a;

            e(int i) {
                this.f10753a = i;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SlidingTabStrip slidingTabStrip = SlidingTabStrip.this;
                slidingTabStrip.f10752a = this.f10753a;
                slidingTabStrip.c = 0.0f;
            }
        }

        SlidingTabStrip(Context context) {
            super(context);
            this.f10752a = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            setWillNotDraw(false);
            this.j = new Paint();
            this.d = ContextCompat.getDrawable(context, R.drawable._2131429586_res_0x7f0b08d2);
        }

        private void b() {
            int i;
            int i2;
            if (this.m == null) {
                Log.e("HwSubTabViewContainer", "updateIndicatorPosition mSlidingTabStripClient is empty");
                return;
            }
            View childAt = getChildAt(this.f10752a);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                i = childAt.getLeft() + this.m.ehs_(childAt);
                i2 = childAt.getRight() - this.m.eht_(childAt);
                if (this.c > 0.0f && this.f10752a < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.f10752a + 1);
                    int left = childAt2.getLeft();
                    int ehs_ = this.m.ehs_(childAt2);
                    int right = childAt2.getRight();
                    int eht_ = this.m.eht_(childAt2);
                    float f = this.c;
                    float f2 = left + ehs_;
                    float f3 = 1.0f - f;
                    i = (int) ((f2 * f) + (i * f3));
                    i2 = (int) ((f * (right - eht_)) + (f3 * i2));
                }
            }
            d(i, i2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
            View childAt = getChildAt(this.f10752a);
            if (hasFocus() || childAt == null) {
                super.addFocusables(arrayList, i, i2);
            } else if (!childAt.isFocusable()) {
                super.addFocusables(arrayList, i, i2);
            } else if (arrayList != null) {
                arrayList.add(childAt);
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int i;
            int i2;
            int i3;
            super.draw(canvas);
            if (canvas == null) {
                Log.w("HwSubTabViewContainer", "Parameter canvas of draw should not be null.");
                return;
            }
            int i4 = this.f10752a;
            if (i4 != -1) {
                View childAt = getChildAt(i4);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    if (textView.getLayout() != null) {
                        i = textView.getTotalPaddingBottom() - this.e;
                        i2 = this.g;
                        if (i2 >= 0 || (i3 = this.h) <= i2) {
                        }
                        this.d.setBounds(0, 0, i3 - i2, this.b);
                        canvas.save();
                        canvas.translate(this.g, (getHeight() - this.b) - i);
                        this.d.draw(canvas);
                        canvas.restore();
                        return;
                    }
                }
            }
            i = 0;
            i2 = this.g;
            if (i2 >= 0) {
            }
        }

        public int getSelectedIndicatorHeight() {
            return this.b;
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.i;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                b();
                return;
            }
            this.i.cancel();
            b(this.f10752a, Math.round((1.0f - this.i.getAnimatedFraction()) * this.i.getDuration()));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
        }

        public void setSelectedIndicatorColor(int i) {
            if (this.j.getColor() != i) {
                this.d.setTint(i);
                this.j.setColor(i);
                postInvalidateOnAnimation();
            }
        }

        void setSelectedIndicatorHeight(int i) {
            if (this.b != i) {
                this.b = i;
                postInvalidateOnAnimation();
            }
        }

        void setSelectedIndicatorMargin(int i) {
            if (this.e != i) {
                this.e = i;
                postInvalidateOnAnimation();
            }
        }

        public void setSlidingTabStripClient(c cVar) {
            this.m = cVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean d() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt != null && childAt.getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(int i, float f) {
            ValueAnimator valueAnimator = this.i;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.i.cancel();
            }
            this.f10752a = i;
            this.c = f;
            b();
        }

        void b(int i, int i2) {
            ValueAnimator valueAnimator = this.i;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.i.cancel();
                this.f10752a = i;
            }
            View childAt = getChildAt(i);
            if (childAt == null) {
                b();
                return;
            }
            if (this.m == null) {
                Log.e("HwSubTabViewContainer", "animateIndicatorToPosition mSlidingTabStripClient is empty");
                return;
            }
            int left = childAt.getLeft();
            int ehs_ = this.m.ehs_(childAt);
            int right = childAt.getRight();
            int eht_ = this.m.eht_(childAt);
            int left2 = childAt.getLeft();
            int ehs_2 = this.m.ehs_(childAt);
            int right2 = childAt.getRight();
            int eht_2 = this.m.eht_(childAt);
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.i = valueAnimator2;
            valueAnimator2.setInterpolator(new ske());
            this.i.setDuration(i2);
            this.i.setFloatValues(0.0f, 1.0f);
            final int i3 = left2 + ehs_2;
            final int i4 = left + ehs_;
            final int i5 = right2 - eht_2;
            final int i6 = right - eht_;
            this.i.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: smv
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    HwSubTabViewContainer.SlidingTabStrip.this.a(i3, i4, i5, i6, valueAnimator3);
                }
            });
            this.i.addListener(new e(i));
            this.i.start();
        }

        void d(int i, int i2) {
            if (i == this.g && i2 == this.h) {
                return;
            }
            this.g = i;
            this.h = i2;
            postInvalidateOnAnimation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(int i, int i2, int i3, int i4, ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwSubTabViewContainer", "Object animator in animateIndicatorToPosition should not be null.");
            } else {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                d(d(i, i2, animatedFraction), d(i3, i4, animatedFraction));
            }
        }

        int d(int i, int i2, float f) {
            return i + Math.round(f * (i2 - i));
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwSubTabViewContainer", "Object animator in method ensureScrollAnimator should not be null.");
            } else {
                HwSubTabViewContainer.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
            }
        }
    }

    public class c {
        public c() {
        }

        public int ehs_(View view) {
            return view.getPaddingLeft();
        }

        public int eht_(View view) {
            return view.getPaddingRight();
        }
    }

    public class d {
        public d() {
        }

        public void ehq_(View view, boolean z) {
            if (z) {
                int i = HwSubTabViewContainer.this.c - HwSubTabViewContainer.this.e;
                view.setPadding(i, 0, i, 0);
            } else {
                int i2 = -HwSubTabViewContainer.this.e;
                view.setPadding(i2, 0, i2, 0);
            }
        }

        public void eju_(View view) {
            view.setPadding(HwSubTabViewContainer.this.b - HwSubTabViewContainer.this.e, 0, HwSubTabViewContainer.this.c - HwSubTabViewContainer.this.e, 0);
        }
    }

    public HwSubTabViewContainer(Context context) {
        this(context, null);
    }

    protected void a() {
        this.h = new d();
    }

    public void a(int i) {
        if (i == -1) {
            return;
        }
        if (getWindowToken() == null || this.d.d()) {
            setScrollPosition(i, 0.0f);
            return;
        }
        int scrollX = getScrollX();
        int d2 = d(i, 0.0f);
        if (scrollX != d2) {
            h();
            this.f10751a.setIntValues(scrollX, d2);
            this.f10751a.start();
        }
        this.d.b(i, 200);
    }

    public boolean e() {
        View childAt = getChildAt(0);
        ViewParent parent = getParent();
        if (childAt == null || !(parent instanceof ViewGroup)) {
            return false;
        }
        return getMeasuredWidth() < (childAt.getMeasuredWidth() + getPaddingStart()) + getPaddingEnd() || getMeasuredWidth() > ((ViewGroup) parent).getMeasuredWidth();
    }

    public int getFadingMargin() {
        return this.c;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public float getLeftFadingEdgeStrength() {
        int i = this.g;
        return (i == 0 || i == 1) ? 1.0f : 0.0f;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public float getRightFadingEdgeStrength() {
        int i = this.g;
        return (i == 0 || i == 1) ? 1.0f : 0.0f;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.j;
    }

    public int getStartOriginPadding() {
        return this.b;
    }

    public int getStartScrollPadding() {
        return this.f;
    }

    public int getSubTabItemMargin() {
        return this.e;
    }

    public SlidingTabStrip getTabStrip() {
        return this.d;
    }

    protected void j() {
        this.d.setSlidingTabStripClient(new c());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.i) {
            sle.ebA_(getParent());
        }
    }

    @Override // com.huawei.uikit.hwhorizontalscrollview.widget.HwHorizontalScrollView, android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (getOverScroller() == null || (getOverScroller().isFinished() && !b())) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        if (!isLaidOut() && f()) {
            int max = Math.max(0, childAt.getMeasuredWidth() - (((i3 - i) - getPaddingLeft()) - getPaddingRight()));
            if (getScrollX() != max || this.n == 0) {
                int i5 = this.n;
                if (i5 != -1) {
                    scrollTo(i5, getScrollY());
                } else {
                    scrollTo(max, getScrollY());
                }
            }
        }
        if (this.g == 1) {
            this.h.eju_(childAt);
            if (!e()) {
                setHorizontalFadingEdgeEnabled(false);
                return;
            } else {
                setHorizontalFadingEdgeEnabled(true);
                setFadingEdgeLength(this.c);
                return;
            }
        }
        if (!e()) {
            setHorizontalFadingEdgeEnabled(false);
            this.h.ehq_(childAt, false);
        } else {
            this.h.ehq_(childAt, true);
            setHorizontalFadingEdgeEnabled(true);
            setFadingEdgeLength(this.c);
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        this.n = i;
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (f()) {
            scrollTo(getScrollX() - (i - i3), getScrollY());
        }
    }

    @Override // com.huawei.uikit.hwhorizontalscrollview.widget.HwHorizontalScrollView, android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (e()) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    protected void setAppearance(int i) {
        this.g = i;
    }

    public void setChildPaddingClient(d dVar) {
        this.h = dVar;
    }

    public void setFadingMargin(int i) {
        this.c = i;
    }

    public void setMirrorScrollX(int i) {
        this.n = i;
    }

    public void setScrollPosition(int i, float f) {
        setScrollPosition(i, f, true);
    }

    public void setStartOriginPadding(int i) {
        this.b = i;
    }

    public void setStartScrollPadding(int i) {
        this.f = i;
    }

    public void setSubTabFaddingEdgeColor(int i) {
        this.j = i;
    }

    void setSubTabItemMargin(int i) {
        this.e = i;
        View childAt = getChildAt(0);
        if (this.g == 1) {
            int i2 = this.b;
            int i3 = this.e;
            childAt.setPadding(i2 - i3, 0, this.c - i3, 0);
        } else if (e()) {
            int i4 = this.c - this.e;
            childAt.setPadding(i4, 0, i4, 0);
        }
    }

    public HwSubTabViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = false;
        this.f = 20;
        this.g = 0;
        this.n = -1;
        e(context);
    }

    private void e(Context context) {
        setHorizontalScrollBarEnabled(false);
        setOverScrollMode(0);
        a();
        this.d = new SlidingTabStrip(context);
        j();
        super.addView(this.d, 0, new FrameLayout.LayoutParams(-2, -1));
        this.c = getResources().getDimensionPixelSize(R.dimen._2131364445_res_0x7f0a0a5d);
        this.b = getResources().getDimensionPixelSize(R.dimen._2131364464_res_0x7f0a0a70);
        if (sms.d(context) == 2) {
            this.i = true;
        }
    }

    private boolean f() {
        return getLayoutDirection() == 1;
    }

    public void setScrollPosition(int i, float f, boolean z) {
        int round = Math.round(i + f);
        if (round < 0 || round >= this.d.getChildCount()) {
            return;
        }
        if (z) {
            this.d.d(i, f);
        }
        ValueAnimator valueAnimator = this.f10751a;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.f10751a.cancel();
        }
        scrollTo(d(i, f), 0);
    }

    public HwSubTabViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = false;
        this.f = 20;
        this.g = 0;
        this.n = -1;
        e(context);
    }

    private void h() {
        if (this.f10751a == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.f10751a = valueAnimator;
            valueAnimator.setInterpolator(new ske());
            this.f10751a.setDuration(200L);
            this.f10751a.addUpdateListener(new b());
        }
    }

    private int d(int i, float f) {
        int i2;
        float f2;
        int left;
        int i3;
        View childAt = this.d.getChildAt(i);
        int i4 = i + 1;
        KeyEvent.Callback childAt2 = i4 < this.d.getChildCount() ? this.d.getChildAt(i4) : null;
        TextView textView = childAt instanceof TextView ? (TextView) childAt : null;
        TextView textView2 = childAt2 instanceof TextView ? (TextView) childAt2 : null;
        int i5 = 0;
        int width = textView != null ? textView.getWidth() : 0;
        int width2 = textView2 != null ? textView2.getWidth() : 0;
        if (textView != null) {
            if (this.g == 1) {
                if (f()) {
                    int right = textView.getRight();
                    int b2 = b(this.f);
                    int i6 = this.e;
                    left = right + b2 + i6 + i6;
                    i3 = getWidth();
                } else {
                    left = textView.getLeft() - b(this.f);
                    int i7 = this.e;
                    i3 = i7 + i7;
                }
                i5 = left - i3;
                int i8 = this.e;
                f2 = width + i8 + i8;
            } else {
                i5 = (textView.getLeft() + (width / 2)) - (getWidth() / 2);
                int i9 = this.e;
                f2 = ((width + width2) * 0.5f) + i9 + i9;
            }
            i2 = (int) (f2 * f);
        } else {
            i2 = 0;
        }
        return getLayoutDirection() == 0 ? i5 + i2 : i5 - i2;
    }

    int b(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }
}
