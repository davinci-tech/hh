package com.huawei.uikit.hwswiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import defpackage.bnm;
import defpackage.bno;
import defpackage.slb;
import defpackage.slc;
import defpackage.smr;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class HwSwipeRefreshLayout extends FrameLayout implements NestedScrollingParent2, NestedScrollingChild2 {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10759a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private HwLinkageViewInfoCallBack ae;
    private HwOverSwipeRefreshListener af;
    private boolean ag;
    private boolean ah;
    private HwGenericEventDetector ai;
    private boolean aj;
    private int ak;
    private int al;
    private boolean am;
    private boolean an;
    private Runnable ao;
    private ViewTreeObserver ap;
    private d aq;
    private ViewTreeObserver.OnScrollChangedListener ar;
    private HwBottomRefreshCallBack as;
    private View av;
    private HwRefreshHeadView aw;
    private final int[] ax;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private View k;
    private String l;
    private boolean m;
    private String n;
    private String o;
    private Callback p;
    private boolean q;
    private int r;
    private int s;
    private Context t;
    private AnimatorSet u;
    private NestedScrollingChildHelper v;
    private int w;
    private NestedScrollingParentHelper x;
    private AnimatorSet y;
    private int z;

    public interface Callback {
        boolean isEnabled();

        boolean needToWait();

        void onRefreshStart();

        void onScrollUp();
    }

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwSwipeRefreshLayout", "onAnimationUpdate: the input animation is null!");
            } else {
                HwSwipeRefreshLayout.this.ehE_(valueAnimator);
            }
        }
    }

    class b implements ViewTreeObserver.OnScrollChangedListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            if (HwSwipeRefreshLayout.this.g() || HwSwipeRefreshLayout.this.as == null || HwSwipeRefreshLayout.this.a(1)) {
                return;
            }
            HwSwipeRefreshLayout.this.as.onRefresh();
        }
    }

    class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            HwSwipeRefreshLayout.this.y = null;
            HwSwipeRefreshLayout hwSwipeRefreshLayout = HwSwipeRefreshLayout.this;
            hwSwipeRefreshLayout.setScrollY(-((int) hwSwipeRefreshLayout.g));
            if (!HwSwipeRefreshLayout.this.m && HwSwipeRefreshLayout.this.p != null) {
                HwSwipeRefreshLayout.this.setPullState(4444);
                HwSwipeRefreshLayout.this.p.onRefreshStart();
            }
            HwSwipeRefreshLayout.this.ah = true;
            HwSwipeRefreshLayout.this.f10759a = false;
        }
    }

    /* loaded from: classes9.dex */
    class d implements Runnable {
        private Interpolator c;
        private OverScroller d;
        private boolean e;

        d() {
            this.c = AnimationUtils.loadInterpolator(HwSwipeRefreshLayout.this.getContext(), R.interpolator._2131689478_res_0x7f0f0006);
            this.d = new OverScroller(HwSwipeRefreshLayout.this.getContext(), this.c);
        }

        private void a() {
            HwSwipeRefreshLayout.this.al = 0;
            this.e = false;
            OverScroller overScroller = this.d;
            if (overScroller == null || overScroller.isFinished()) {
                return;
            }
            this.d.abortAnimation();
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller = this.d;
            if (overScroller == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                a();
                return;
            }
            float c = HwSwipeRefreshLayout.this.c(this.d.getCurrY());
            int a2 = HwSwipeRefreshLayout.this.a(c);
            if (((int) HwSwipeRefreshLayout.this.g) <= a2) {
                HwSwipeRefreshLayout.this.d(c, a2);
                a();
                HwSwipeRefreshLayout.this.n();
            } else if (((int) HwSwipeRefreshLayout.this.i) >= a2) {
                HwSwipeRefreshLayout.this.a(c, a2);
                HwSwipeRefreshLayout.this.postOnAnimation(this);
            } else if (HwSwipeRefreshLayout.this.e) {
                Log.w("HwSwipeRefreshLayout", "GenericRefreshScroller: the state with Refreshing");
            } else {
                HwSwipeRefreshLayout.this.e(c, a2);
                HwSwipeRefreshLayout.this.postOnAnimation(this);
            }
            HwSwipeRefreshLayout.this.requestLayout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            OverScroller overScroller = this.d;
            if (overScroller == null) {
                return;
            }
            if (!overScroller.isFinished()) {
                this.d.abortAnimation();
            }
            this.e = true;
            this.d.startScroll(0, 0, 0, (int) HwSwipeRefreshLayout.this.g, 250);
            HwSwipeRefreshLayout.this.postOnAnimation(this);
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwSwipeRefreshLayout.this.al = 0;
        }
    }

    class f implements ValueAnimator.AnimatorUpdateListener {
        f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwSwipeRefreshLayout", "onAnimationUpdate: the input animation is null");
                return;
            }
            int intValue = 0 - ((Integer) valueAnimator.getAnimatedValue()).intValue();
            float f = intValue;
            HwSwipeRefreshLayout.this.r = (int) f;
            HwSwipeRefreshLayout.this.setNoRefreshBackLine(f);
            HwSwipeRefreshLayout.this.f(intValue);
            HwSwipeRefreshLayout.this.p();
        }
    }

    class g implements HwGenericEventDetector.OnScrollListener {
        private long e = 0;

        g() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnScrollListener
        public boolean onScrollBy(float f, float f2, MotionEvent motionEvent) {
            if (HwSwipeRefreshLayout.this.g()) {
                return true;
            }
            if (HwSwipeRefreshLayout.this.a(-1)) {
                this.e = SystemClock.uptimeMillis();
                HwSwipeRefreshLayout.this.al = 0;
                return false;
            }
            if (!HwSwipeRefreshLayout.this.a(-1) && Float.compare(f2, 0.0f) > 0) {
                this.e = SystemClock.uptimeMillis();
                HwSwipeRefreshLayout.this.al = 0;
                return false;
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.e < 150) {
                this.e = uptimeMillis;
                return true;
            }
            if (HwSwipeRefreshLayout.this.al == 0) {
                HwSwipeRefreshLayout.this.al++;
                HwSwipeRefreshLayout hwSwipeRefreshLayout = HwSwipeRefreshLayout.this;
                hwSwipeRefreshLayout.postDelayed(hwSwipeRefreshLayout.ao, 150L);
                return false;
            }
            HwSwipeRefreshLayout hwSwipeRefreshLayout2 = HwSwipeRefreshLayout.this;
            hwSwipeRefreshLayout2.removeCallbacks(hwSwipeRefreshLayout2.ao);
            if (HwSwipeRefreshLayout.this.aq == null) {
                return false;
            }
            HwSwipeRefreshLayout.this.aq.c();
            return true;
        }
    }

    class h extends AnimatorListenerAdapter {
        h() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            HwSwipeRefreshLayout.this.u = null;
        }
    }

    class i implements ValueAnimator.AnimatorUpdateListener {
        i() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.w("HwSwipeRefreshLayout", "onAnimationUpdate: the input animation is null");
                return;
            }
            int intValue = 0 - ((Integer) valueAnimator.getAnimatedValue()).intValue();
            float f = intValue;
            HwSwipeRefreshLayout.this.r = (int) f;
            HwSwipeRefreshLayout.this.setNoRefreshBackLine(f);
            HwSwipeRefreshLayout.this.f(intValue);
            HwSwipeRefreshLayout.this.p();
        }
    }

    class j extends AnimatorListenerAdapter {
        j() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            HwSwipeRefreshLayout.this.aw.setProgressBarVisibility(4);
            HwSwipeRefreshLayout.this.e = false;
            HwSwipeRefreshLayout.this.m = false;
            HwSwipeRefreshLayout.this.r = 0;
            HwSwipeRefreshLayout.this.d = -1;
            HwSwipeRefreshLayout.this.p();
        }
    }

    public HwSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    private int getLinkedState() {
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        if (hwLinkageViewInfoCallBack == null || !hwLinkageViewInfoCallBack.hasLinkageView()) {
            return -1;
        }
        return this.ae.getLinkageViewState();
    }

    private int getPullState() {
        return this.aa;
    }

    private void setCallBackInObserver(View view) {
        if (this.ap != null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        this.ap = viewTreeObserver;
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnScrollChangedListener(this.ar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNoRefreshBackLine(float f2) {
        int i2 = (int) f2;
        if (((int) this.i) < i2) {
            setRefreshingBarAppearLine(i2);
        } else {
            this.aw.setProgressBarVisibility(4);
        }
        if (((int) this.f) <= i2) {
            setTextViewAppearLine(i2);
        } else {
            this.aw.setTextViewVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPullState(int i2) {
        this.aa = i2;
    }

    private void setRefreshingBarAppearLine(float f2) {
        int i2 = (int) f2;
        float f3 = this.g;
        if (i2 > ((int) f3)) {
            f2 = f3;
        }
        float f4 = this.i;
        float f5 = (f2 - f4) / (f3 - f4);
        float f6 = (f5 * 0.5f) + 0.5f;
        this.aw.setProgressBarVisibility(0);
        this.aw.setProgressBarAlpha(f5);
        this.aw.setProgressBarDragFraction(f5);
        this.aw.setProgressBarScaleX(f6);
        this.aw.setProgressBarScaleY(f6);
        this.aw.setProgressBarScaleSize((int) (this.w * f6));
    }

    private void setTextViewAppearLine(float f2) {
        int i2 = (int) f2;
        float f3 = this.h;
        if (i2 > ((int) f3)) {
            f2 = f3;
        }
        float f4 = this.f;
        float f5 = (f2 - f4) / (f3 - f4);
        if (this.m) {
            this.aw.setTextViewText(this.l);
        } else {
            this.aw.setTextViewText(this.n);
        }
        if (this.ag) {
            this.aw.setTextViewVisibility(0);
        }
        this.aw.setTextViewAlpha(f5);
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            return;
        }
        Object c2 = slc.c((Object) null, b2, new Object[]{context, this, "scrollToRefreshEnabled", true});
        if (c2 instanceof Boolean) {
            setExtendScrollEnabled(((Boolean) c2).booleanValue());
        }
    }

    public void a() {
        c();
    }

    public void c() {
        if (!this.e || this.m) {
            Log.w("HwSwipeRefreshLayout", "not refreshing = " + this.e + " mIsStartBackAnimating = " + this.m);
            return;
        }
        if (this.aw == null || this.av == null) {
            return;
        }
        setPullState(5555);
        this.m = true;
        int scrollY = getScrollY();
        if (this.y != null) {
            Log.w("HwSwipeRefreshLayout", "startFinishRefreshingAnim: mStartUpAnimatorSet != null");
            this.y.cancel();
        }
        if (scrollY > 0 && this.e) {
            m();
        } else {
            this.ab = (int) getTranslationY();
            m();
        }
    }

    protected HwGenericEventDetector d() {
        return new HwGenericEventDetector(getContext());
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.aj) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        HwGenericEventDetector hwGenericEventDetector = this.ai;
        if (hwGenericEventDetector == null || !hwGenericEventDetector.eis_(motionEvent)) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        return this.v.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        return this.v.dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Boolean ehB_;
        if (this.an) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (motionEvent == null) {
            Log.w("HwSwipeRefreshLayout", "dispatchTouchEvent: the input event is null");
            return false;
        }
        if (motionEvent.getActionMasked() == 0 && this.m) {
            return true;
        }
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        boolean z = hwLinkageViewInfoCallBack != null && hwLinkageViewInfoCallBack.hasLinkageView();
        int linkageViewState = z ? this.ae.getLinkageViewState() : -1;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.am = false;
        } else if (actionMasked == 2 && (ehB_ = ehB_(motionEvent, z, linkageViewState)) != null) {
            return ehB_.booleanValue();
        }
        this.ak = linkageViewState;
        return super.dispatchTouchEvent(motionEvent);
    }

    protected HwGenericEventDetector.OnScrollListener e() {
        return new g();
    }

    public HwBottomRefreshCallBack getBottomRefreshCallBack() {
        return this.as;
    }

    public HwLinkageViewInfoCallBack getLinkageViewInfoCallBack() {
        return this.ae;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.x.getNestedScrollAxes();
    }

    public HwOverSwipeRefreshListener getOverSwipeRefreshListener() {
        return this.af;
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i2) {
        return this.v.hasNestedScrollingParent(i2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.v.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (configuration == null) {
            return;
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.an) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent == null) {
            Log.w("HwSwipeRefreshLayout", "onInterceptTouchEvent: the input event is null");
            return false;
        }
        if (this.m) {
            this.c = (int) motionEvent.getRawY();
            return false;
        }
        if (ehO_(motionEvent)) {
            return false;
        }
        if ((motionEvent.getActionMasked() == 2 && this.q) || super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        Callback callback = this.p;
        if (callback != null && !callback.isEnabled()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (ehK_(motionEvent)) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int measuredWidth = getMeasuredWidth();
        j();
        if (this.av != null && this.aw != null && this.p != null) {
            int paddingLeft = getPaddingLeft();
            this.aw.layout(paddingLeft, (0 - getPaddingTop()) - this.r, measuredWidth + paddingLeft, 0);
        } else {
            Log.w("HwSwipeRefreshLayout", "onLayout view is null mChildView = " + this.av + ", mHeaderView = " + this.aw);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        j();
        if (this.av == null || this.aw == null || this.p == null) {
            Log.w("HwSwipeRefreshLayout", "onMeasure view is null!");
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = (this.r - getPaddingTop()) - getPaddingBottom();
        if (paddingTop < 0) {
            paddingTop = 0;
        }
        this.aw.measure(View.MeasureSpec.makeMeasureSpec((measuredWidth - paddingLeft) - paddingRight, 1073741824), View.MeasureSpec.makeMeasureSpec(paddingTop, 1073741824));
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4) {
        ehI_(view, i2, i3, iArr, i4);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
        ehH_(view, i2, i3, i4, i5);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
        this.x.onNestedScrollAccepted(view, view2, i2, i3);
        startNestedScroll(i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (i2 & 2) != 0;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        return (i2 & 2) != 0;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i2) {
        stopNestedScroll(0);
        this.x.onStopNestedScroll(view, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.an) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent == null) {
            Log.w("HwSwipeRefreshLayout", "onTouchEvent(): MotionEvent can not be null!");
            return false;
        }
        if (this.aw == null || this.av == null) {
            Log.w("HwSwipeRefreshLayout", "onTouchEvent view is null mChildView = " + this.av + ", mHeaderView = " + this.aw);
            return super.onTouchEvent(motionEvent);
        }
        if (this.m || this.f10759a) {
            return false;
        }
        Callback callback = this.p;
        if (callback != null && !callback.isEnabled()) {
            Log.w("HwSwipeRefreshLayout", "onTouchEvent: don't isEnabled");
            return super.onTouchEvent(motionEvent);
        }
        int actionIndex = motionEvent.getActionIndex();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked == 5) {
                            ehC_(actionIndex, motionEvent);
                            setPullState(1111);
                        } else if (actionMasked == 6) {
                            h();
                        }
                    }
                } else if (ehM_(actionIndex, motionEvent)) {
                    return true;
                }
            }
            this.ab = (int) getTranslationY();
            h(actionIndex);
        } else {
            startNestedScroll(2, 0);
            ehC_(actionIndex, motionEvent);
            setPullState(1111);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        int i4 = this.r;
        if (this.av.canScrollVertically(1) && i3 > i4) {
            i3 = i4 - this.ac;
        }
        if (this.av.canScrollVertically(-1)) {
            Log.w("HwSwipeRefreshLayout", "scrollTo: y = " + i3);
        }
        if (i3 <= i4) {
            i4 = i3;
        }
        super.scrollTo(i2, i4);
    }

    public void setBottomRefreshCallBack(HwBottomRefreshCallBack hwBottomRefreshCallBack) {
        i();
        this.as = hwBottomRefreshCallBack;
        if (hwBottomRefreshCallBack != null) {
            b();
        }
    }

    public void setCallback(Callback callback) {
        this.p = callback;
    }

    public void setCanRefreshText(String str) {
        this.o = str;
    }

    public void setContentView(View view) {
        if (view != null) {
            this.av = view;
        } else {
            Log.w("HwSwipeRefreshLayout", "setContentView: you add a null view");
        }
    }

    public void setExtendScrollEnabled(boolean z) {
        this.aj = z;
    }

    public void setIsShowText(boolean z) {
        int dimensionPixelSize;
        this.ag = z;
        if (z) {
            this.j = getResources().getDimensionPixelSize(R.dimen._2131364474_res_0x7f0a0a7a);
            this.g = getResources().getDimensionPixelSize(R.dimen._2131364472_res_0x7f0a0a78);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131364479_res_0x7f0a0a7f);
        } else {
            this.j = getResources().getDimensionPixelSize(R.dimen._2131364475_res_0x7f0a0a7b);
            this.g = getResources().getDimensionPixelSize(R.dimen._2131364473_res_0x7f0a0a79);
            dimensionPixelSize = 0;
        }
        HwRefreshHeadView hwRefreshHeadView = this.aw;
        if (hwRefreshHeadView != null) {
            hwRefreshHeadView.d(dimensionPixelSize);
        }
    }

    public void setLinkageViewInfoCallBack(HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack) {
        this.ae = hwLinkageViewInfoCallBack;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.v.setNestedScrollingEnabled(z);
    }

    public void setOverSwipeRefreshListener(HwOverSwipeRefreshListener hwOverSwipeRefreshListener) {
        this.af = hwOverSwipeRefreshListener;
    }

    public void setProgressBarColor(int i2) {
        HwRefreshHeadView hwRefreshHeadView = this.aw;
        if (hwRefreshHeadView == null) {
            return;
        }
        hwRefreshHeadView.setProgressBarColor(i2);
    }

    public void setPullDownText(String str) {
        this.n = str;
    }

    public void setRefreshPushText(String str) {
        this.l = str;
    }

    public void setScrollView(View view) {
        this.k = view;
    }

    public void setSwipeRefreshLayoutDisabled(boolean z) {
        this.an = z;
    }

    public void setTextColor(int i2) {
        HwRefreshHeadView hwRefreshHeadView = this.aw;
        if (hwRefreshHeadView == null) {
            return;
        }
        hwRefreshHeadView.setTextViewColor(i2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        return this.v.startNestedScroll(i2, i3);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i2) {
        this.v.stopNestedScroll(i2);
    }

    public HwSwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100561_res_0x7f060391);
    }

    private void h(int i2) {
        Callback callback;
        if (i2 < 0) {
            Log.w("HwSwipeRefreshLayout", "upOrCancelTouch: index Error");
            return;
        }
        this.q = false;
        int pullState = getPullState();
        if (pullState == 6666 && (callback = this.p) != null && callback.needToWait()) {
            if (this.y != null) {
                Log.w("HwSwipeRefreshLayout", "upOrCancelTouch: mStartUpAnimatorSet != null");
                this.y.cancel();
            }
            this.e = true;
            setPullState(3333);
            t();
            this.aw.a();
        } else if (pullState != 4444 && pullState != 3333) {
            AnimatorSet animatorSet = this.u;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            o();
            this.aw.e();
        }
        Callback callback2 = this.p;
        if (callback2 != null) {
            callback2.onScrollUp();
        }
    }

    private void m() {
        if (getScrollY() == 0) {
            Log.w("HwSwipeRefreshLayout", "startBackAnim: the endPosition is in the initial position");
        }
        bno bnoVar = new bno(999.0f, 59.99f, Math.abs(r0));
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "ScrollY", 0);
        ofInt.setTarget(this);
        ofInt.setInterpolator(bnoVar);
        ofInt.setDuration((long) bnoVar.getDuration());
        AnimatorSet animatorSet = new AnimatorSet();
        ofInt.addUpdateListener(new f());
        animatorSet.addListener(new j());
        animatorSet.play(ofInt);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Callback callback;
        int pullState = getPullState();
        if (pullState == 6666 && (callback = this.p) != null && callback.needToWait()) {
            if (this.y != null) {
                Log.w("HwSwipeRefreshLayout", "upOrCancelTouch: mStartUpAnimatorSet != null");
                this.y.cancel();
            }
            this.e = true;
            setPullState(3333);
            t();
        } else if (pullState != 4444 && pullState != 3333) {
            AnimatorSet animatorSet = this.u;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            o();
        }
        Callback callback2 = this.p;
        if (callback2 != null) {
            callback2.onScrollUp();
        }
    }

    private void o() {
        bno bnoVar = new bno(250.0f, 30.0f, Math.abs(getScrollY()));
        setPullState(2222);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "ScrollY", 0);
        ofInt.setInterpolator(bnoVar);
        ofInt.setDuration((long) bnoVar.getDuration());
        ofInt.addUpdateListener(new i());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt);
        animatorSet.addListener(new h());
        this.u = animatorSet;
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        requestLayout();
    }

    private void t() {
        this.f10759a = true;
        bno bnoVar = new bno(250.0f, 30.0f, Math.abs(getScrollY()));
        this.aw.setProgressBarAlpha(1.0f);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "ScrollY", -((int) this.g));
        ofInt.setInterpolator(bnoVar);
        ofInt.setDuration((long) bnoVar.getDuration());
        ofInt.addUpdateListener(new a());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new c());
        ehD_(ofInt, animatorSet);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        ehI_(view, i2, i3, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        ehH_(view, i2, i3, i4, i5);
    }

    public HwSwipeRefreshLayout(Context context, AttributeSet attributeSet, int i2) {
        super(d(context, i2), attributeSet, i2);
        this.ax = new int[2];
        this.aa = 1111;
        this.ah = true;
        this.aj = true;
        this.an = false;
        this.am = false;
        this.ak = -1;
        this.ao = new e();
        this.aq = new d();
        this.ap = null;
        this.ar = new b();
        ehF_(getContext(), attributeSet, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        HwOverSwipeRefreshListener hwOverSwipeRefreshListener;
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        if (hwLinkageViewInfoCallBack == null || !hwLinkageViewInfoCallBack.hasLinkageView() || (hwOverSwipeRefreshListener = this.af) == null) {
            return;
        }
        hwOverSwipeRefreshListener.onTopOverSwipeRefresh(i2);
    }

    private void h() {
        this.d = -1;
        this.b = 0;
        this.c = 0;
    }

    private float i(int i2) {
        int d2 = d(i2, this.c);
        int[] iArr = this.ax;
        iArr[0] = 0;
        iArr[1] = 0;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        float a2 = a(0 - (0 - d2), Math.abs(getScrollY()), (int) (getHeight() * 0.5f));
        this.c = i2;
        return a2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.x.onNestedScrollAccepted(view, view2, i2);
        startNestedScroll(i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        stopNestedScroll(0);
        this.x.onStopNestedScroll(view);
    }

    private void f() {
        this.j = getResources().getDimensionPixelSize(R.dimen._2131364474_res_0x7f0a0a7a);
        this.g = getResources().getDimensionPixelSize(R.dimen._2131364472_res_0x7f0a0a78);
        this.i = getResources().getDimensionPixelSize(R.dimen._2131364471_res_0x7f0a0a77);
        this.f = getResources().getDimensionPixelSize(R.dimen._2131364477_res_0x7f0a0a7d);
        this.h = getResources().getDimensionPixelSize(R.dimen._2131364478_res_0x7f0a0a7e);
    }

    private boolean ehO_(MotionEvent motionEvent) {
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        return (hwLinkageViewInfoCallBack == null || !hwLinkageViewInfoCallBack.hasLinkageView() || motionEvent.getAction() == 0 || motionEvent.getAction() == 5 || this.ae.getLinkageViewState() == 0) ? false : true;
    }

    private float e(int i2) {
        return a(i2, Math.abs(getScrollY()), (int) (getHeight() * 0.5f));
    }

    private void l() {
        this.aw.setProgressBarVisibility(0);
        this.aw.setProgressBarAlpha(1.0f);
        this.aw.setProgressBarScaleX(1.0f);
        this.aw.setProgressBarScaleY(1.0f);
        this.aw.setProgressBarDragFraction(1.0f);
        this.aw.setProgressBarScaleSize((int) (this.w * 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return this.e || this.m || this.f10759a || this.aq.e;
    }

    private static Context d(Context context, int i2) {
        return smr.b(context, i2, R.style.Theme_Emui_HwSwipeRefreshLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i2) {
        Boolean e2;
        View view = this.av;
        if (view == null && this.k == null) {
            return false;
        }
        View view2 = this.k;
        if (view2 == null) {
            return view.canScrollVertically(i2);
        }
        if (view2 instanceof AbsListView) {
            if (i2 == -1) {
                e2 = e("canScrollUp");
            } else {
                e2 = i2 == 1 ? e("canScrollDown") : null;
            }
            if (e2 != null) {
                return e2.booleanValue();
            }
        }
        return this.k.canScrollVertically(i2);
    }

    private float b(int i2) {
        float i3 = i(i2);
        float f2 = this.j;
        if (f2 <= i3) {
            i3 = f2;
        }
        return 0.0f - i3;
    }

    private Boolean e(String str) {
        try {
            Object c2 = slc.c(this.k, str, null, null, Class.forName("android.widget.AbsListView"));
            if (c2 instanceof Boolean) {
                return (Boolean) c2;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            Log.e("HwSwipeRefreshLayout", "canChildScrollVertically() ClassNotFoundException");
            return null;
        } catch (IllegalArgumentException unused2) {
            Log.e("HwSwipeRefreshLayout", "canChildScrollVertically() IllegalArgumentException");
            return null;
        } catch (SecurityException unused3) {
            Log.e("HwSwipeRefreshLayout", "canChildScrollVertically() SecurityException");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(float f2, int i2) {
        scrollBy(0, (int) f2);
        this.r = i2;
        float f3 = i2;
        setRefreshingBarAppearLine(f3);
        setPullState(1111);
        this.aw.setTextViewVisibility(4);
        if (((int) this.f) <= i2) {
            setTextViewAppearLine(f3);
        }
    }

    private void i() {
        ViewTreeObserver viewTreeObserver = this.ap;
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnScrollChangedListener(this.ar);
            this.ap = null;
        }
    }

    private Boolean ehB_(MotionEvent motionEvent, boolean z, int i2) {
        if (!z || i2 != 0 || this.e || !onInterceptTouchEvent(motionEvent)) {
            return null;
        }
        if (!this.am) {
            motionEvent.setAction(3);
            this.am = true;
            return Boolean.valueOf(super.dispatchTouchEvent(motionEvent));
        }
        if (this.ak != 0) {
            this.c = (int) motionEvent.getRawY();
            this.ak = i2;
        }
        return Boolean.valueOf(onTouchEvent(motionEvent));
    }

    private void j() {
        if (this.av != null) {
            return;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && !childAt.equals(this.aw) && ((childAt instanceof AbsListView) || (childAt instanceof ScrollView) || (childAt instanceof RecyclerView))) {
                this.av = childAt;
                if (this.as != null) {
                    setCallBackInObserver(childAt);
                    return;
                }
                return;
            }
        }
    }

    private boolean ehN_(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        if (this.c == 0) {
            this.c = rawY;
        }
        int i2 = rawY - this.c;
        int abs = Math.abs(i2);
        int i3 = this.s;
        if (abs > i3 && i2 > i3 && !a(-1) && !this.m) {
            this.c = rawY;
            if (!this.e) {
                this.q = true;
                return true;
            }
            if (getScrollY() != (-((int) this.g))) {
                return false;
            }
            this.q = true;
            return true;
        }
        this.q = false;
        return false;
    }

    private void k() {
        if (this.ag) {
            this.aw.setTextViewVisibility(0);
        }
        this.aw.setTextViewAlpha(1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float c(int i2) {
        float e2 = e(i2);
        float f2 = this.j;
        if (f2 <= e2) {
            e2 = f2;
        }
        if (this.g > (-getScrollY())) {
            return -e2;
        }
        return 0.0f;
    }

    private boolean ehK_(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            startNestedScroll(2, 0);
            ehC_(actionIndex, motionEvent);
        } else if (actionMasked == 1) {
            this.d = -1;
            if (this.q) {
                return true;
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                this.d = -1;
            } else if (actionMasked == 5) {
                ehC_(actionIndex, motionEvent);
            } else if (actionMasked == 6) {
                h();
            }
        } else if (ehN_(motionEvent)) {
            return true;
        }
        return false;
    }

    private boolean ehM_(int i2, MotionEvent motionEvent) {
        if (i2 < 0) {
            return false;
        }
        int rawY = (int) motionEvent.getRawY();
        int x = (int) motionEvent.getX();
        if (this.c == 0 && this.b == 0) {
            this.c = rawY;
            this.b = x;
        }
        if (((int) this.g) > Math.abs(getScrollY())) {
            this.ah = true;
        }
        int d2 = d(rawY, this.c);
        float b2 = b(rawY);
        int b3 = b(b2);
        f(b3);
        if (c(0 - d2, getLinkedState())) {
            return true;
        }
        this.aw.e();
        if (((int) this.g) <= b3) {
            if (isHapticFeedbackEnabled() && this.ah) {
                slb.ebC_(this, 1, 0);
                this.ah = false;
            }
            d(b2, b3);
        } else if (((int) this.i) < b3) {
            if (!this.e) {
                e(b2, b3);
            } else {
                this.ah = false;
                Log.w("HwSwipeRefreshLayout", "onActionMoveTouch: the state with Refreshing");
            }
        } else {
            a(b2, b3);
        }
        requestLayout();
        return true;
    }

    private void ehC_(int i2, MotionEvent motionEvent) {
        this.d = motionEvent.getPointerId(i2);
        this.b = (int) motionEvent.getX();
        this.c = (int) motionEvent.getRawY();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2, int i2) {
        int i3 = (int) f2;
        if (getScrollY() + i3 >= 0) {
            scrollBy(0, -getScrollY());
            this.r = 0;
        } else {
            scrollBy(0, i3);
            this.r = i2;
        }
        this.aw.setProgressBarVisibility(4);
        this.aw.setTextViewVisibility(4);
        setPullState(1111);
    }

    private void ehF_(Context context, AttributeSet attributeSet, int i2) {
        this.t = context;
        this.s = ViewConfiguration.get(context).getScaledTouchSlop();
        this.w = getResources().getDimensionPixelSize(R.dimen._2131364476_res_0x7f0a0a7c);
        this.y = null;
        this.u = null;
        this.ac = 0;
        this.ab = 0;
        this.e = false;
        this.m = false;
        this.f10759a = false;
        this.q = false;
        this.d = -1;
        this.ae = null;
        this.af = null;
        this.x = new NestedScrollingParentHelper(this);
        this.v = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        h();
        f();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100389_res_0x7f0602e5, R.attr._2131100560_res_0x7f060390, R.attr._2131100562_res_0x7f060392, R.attr._2131100563_res_0x7f060393, R.attr._2131100564_res_0x7f060394, R.attr._2131100565_res_0x7f060395}, i2, R.style.Widget_Emui_HwSwipeRefreshLayout);
        this.ad = obtainStyledAttributes.getColor(2, ContextCompat.getColor(getContext(), R.color._2131297345_res_0x7f090441));
        this.z = obtainStyledAttributes.getColor(5, ContextCompat.getColor(getContext(), R.color._2131297373_res_0x7f09045d));
        this.n = obtainStyledAttributes.getString(3);
        this.l = obtainStyledAttributes.getString(4);
        this.o = obtainStyledAttributes.getString(1);
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        setIsShowText(z);
        HwGenericEventDetector d2 = d();
        this.ai = d2;
        if (d2 != null) {
            d2.eit_(this, e());
        }
        d(R.layout.hwswiperefreshlayout_headview_layout);
        setValueFromPlume(context);
    }

    private int b(float f2) {
        return 0 - ((int) (getScrollY() + f2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(float f2, int i2) {
        scrollBy(0, (int) f2);
        this.r = i2;
        l();
        this.aw.setTextViewText(this.o);
        k();
        setPullState(6666);
    }

    private boolean c(int i2, int i3) {
        if (i2 > 0 && getScrollY() == 0 && i3 != -1) {
            HwOverSwipeRefreshListener hwOverSwipeRefreshListener = this.af;
            if (hwOverSwipeRefreshListener != null) {
                hwOverSwipeRefreshListener.onTopOverSwipeRefresh(0);
            }
            startNestedScroll(2, 0);
            if (dispatchNestedPreScroll(0, i2, new int[2], new int[2], 0)) {
                return true;
            }
        }
        if (i2 < 0 && i3 != 0 && i3 != -1) {
            HwOverSwipeRefreshListener hwOverSwipeRefreshListener2 = this.af;
            if (hwOverSwipeRefreshListener2 != null) {
                hwOverSwipeRefreshListener2.onTopOverSwipeRefresh(0);
            }
            startNestedScroll(2, 0);
            if (dispatchNestedScroll(0, 0, 0, i2, new int[2], 0)) {
                return true;
            }
        }
        return false;
    }

    private void d(int i2) {
        this.r = 0;
        HwRefreshHeadView hwRefreshHeadView = (HwRefreshHeadView) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(i2, (ViewGroup) this, true).findViewById(R.id.hwswiperefreshlayout_headview);
        this.aw = hwRefreshHeadView;
        hwRefreshHeadView.a(this.t);
        setProgressBarColor(this.ad);
        setTextColor(this.z);
        if (!this.ag) {
            this.aw.d(0);
        } else {
            this.aw.d(getResources().getDimensionPixelSize(R.dimen._2131364479_res_0x7f0a0a7f));
        }
    }

    private float a(int i2, int i3, int i4) {
        return i2 * new bnm(i4).getRate(i3);
    }

    private int d(int i2, int i3) {
        int i4 = i2 - i3;
        if (this.q) {
            return i4;
        }
        int abs = Math.abs(i4);
        int i5 = this.s;
        if (abs <= i5) {
            return i4;
        }
        this.q = true;
        return i4 > 0 ? i4 - i5 : i4 + i5;
    }

    private void ehI_(View view, int i2, int i3, int[] iArr, int i4) {
        startNestedScroll(2, 0);
        int i5 = this.r;
        boolean z = i3 > 0;
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        if (hwLinkageViewInfoCallBack != null && hwLinkageViewInfoCallBack.hasLinkageView() && this.ae.getLinkageViewState() == 2 && i3 > 0) {
            ehG_(view);
        }
        if (this.f10759a) {
            iArr[0] = 0;
            iArr[1] = i3;
            return;
        }
        if (z) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            if (i5 > 0 && getScrollY() < 0) {
                int i6 = -getScrollY();
                if (getScrollY() + i3 >= 0) {
                    scrollBy(0, -getScrollY());
                    f(0);
                } else {
                    scrollBy(0, i3);
                    f(i6);
                }
                iArr[0] = 0;
                iArr[1] = i3;
                return;
            }
            if (dispatchNestedPreScroll(0, i3, iArr2, iArr3, 0)) {
                if (!this.m) {
                    this.c -= i3;
                }
                iArr[0] = 0;
                iArr[1] = i3;
            }
        }
    }

    private void ehH_(View view, int i2, int i3, int i4, int i5) {
        int i6;
        startNestedScroll(2, 0);
        boolean z = i5 < 0;
        int i7 = this.r;
        HwLinkageViewInfoCallBack hwLinkageViewInfoCallBack = this.ae;
        boolean z2 = hwLinkageViewInfoCallBack != null && hwLinkageViewInfoCallBack.hasLinkageView();
        if (z2) {
            i6 = this.ae.getLinkageViewState();
            if (i6 == 0 && i7 == 0) {
                ehG_(view);
            }
        } else {
            i6 = -1;
        }
        if (!z2 && Math.abs(getScrollY()) == i7) {
            ehG_(view);
        }
        if (z && i6 != 0 && !this.m && i6 != -1) {
            if (!dispatchNestedScroll(0, 0, 0, i5, new int[2], 0) || this.m) {
                return;
            }
            this.c -= i5;
            return;
        }
        if (z) {
            if (i6 == 0 || i6 == -1) {
                int scrollY = getScrollY();
                int i8 = -scrollY;
                f(i8);
                if (i8 == i7) {
                    ehG_(view);
                }
                if (i8 < i7) {
                    if (Math.abs(scrollY + i5) >= i7) {
                        scrollBy(0, -(i7 + scrollY));
                    } else {
                        scrollBy(0, i5);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void ehG_(View view) {
        if (view instanceof NestedScrollingChild2) {
            ((NestedScrollingChild2) view).stopNestedScroll(1);
        }
    }

    private void ehD_(ObjectAnimator objectAnimator, AnimatorSet animatorSet) {
        animatorSet.play(objectAnimator);
        this.y = animatorSet;
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ehE_(ValueAnimator valueAnimator) {
        int intValue = 0 - ((Integer) valueAnimator.getAnimatedValue()).intValue();
        float f2 = intValue;
        if (((int) Math.abs(f2 - this.g)) == 0) {
            f2 = this.g;
            if (this.y != null) {
                setScrollY(-((int) f2));
            }
        }
        f(intValue);
        this.r = (int) f2;
        l();
        this.aw.setTextViewText(this.l);
        k();
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(float f2) {
        return (int) ((-getScrollY()) - f2);
    }

    private void b() {
        View view = this.av;
        if (view == null && this.k == null) {
            Log.w("HwSwipeRefreshLayout", "addChildViewObserver: childView and scrollChildView is null");
            return;
        }
        if (this.ap != null || this.as == null) {
            return;
        }
        View view2 = this.k;
        if (view2 == null) {
            setCallBackInObserver(view);
        } else {
            setCallBackInObserver(view2);
        }
    }
}
