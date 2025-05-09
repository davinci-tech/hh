package com.huawei.uikit.hwviewpager.widget;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.customview.view.AbsSavedState;
import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.bnf;
import defpackage.bng;
import defpackage.bnm;
import defpackage.bno;
import defpackage.slc;
import defpackage.smr;
import defpackage.sms;
import defpackage.smz;
import defpackage.snh;
import defpackage.snl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class HwViewPager extends ViewGroup {
    private static final int A1 = 400;
    private static final float B1 = 1.0E-6f;
    private static final int C1 = -1;
    private static final int D1 = 1;
    private static final int E1 = 2;
    private static final int F1 = 0;
    private static final int G1 = 1;
    private static final int H1 = 2;
    private static final int I1 = 1000;
    private static final int J1 = 4;
    private static final boolean K1 = false;
    private static final int L1 = 2;
    private static final int M1 = -1;
    private static final float N1 = 1.4f;
    private static final float O1 = 1.4f;
    private static final float P1 = 1.0f;
    public static final int PAGE_SCROLL_HORIZONTAL = 0;
    public static final int PAGE_SCROLL_VERTICAL = 1;
    private static final float Q1 = 0.3f;
    private static final int R1 = 2;
    private static final int S1 = 300;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int T1 = 255;
    private static final int U1 = 2131298563;
    private static final int V1 = 1;
    private static final int W1 = 2;
    private static final long X1 = 700;
    private static final long Y1 = 300;
    private static final String g1 = "HwViewPager";
    private static final int h1 = 15;
    private static final int i1 = 2;
    private static final int j1 = 1;
    private static final int k1 = 10;
    private static final int l1 = 1200;
    private static final float m1 = 228.0f;
    private static final float n1 = 30.0f;
    private static final float o1 = 0.5f;
    private static final float p1 = 0.6f;
    private static final int q1 = 100;
    private static final float r1 = 0.3f;
    private static final float s1 = 2.0f;
    private static final boolean t1 = false;
    private static final boolean u1 = false;
    private static final float v1 = 0.5f;
    private static final int w1 = 2;
    private static final int x1 = 600;
    private static final int y1 = 25;
    private static final int z1 = 16;
    private int A;
    private boolean A0;
    private int B;
    private int B0;
    private boolean C;
    private ValueAnimator C0;
    private boolean D;
    private HwPagerAdapter D0;
    private boolean E;
    private smz E0;
    private boolean F;
    private boolean F0;
    private int G;
    private float G0;
    private boolean H;
    private float H0;
    private boolean I;
    private bng I0;
    private int J;
    private DynamicAnimation.OnAnimationEndListener J0;
    private int K;
    private DynamicAnimation.OnAnimationUpdateListener K0;
    private int L;
    private HwGenericEventDetector L0;
    private float M;
    private boolean M0;
    private float N;
    private boolean N0;
    private float O;
    private int O0;
    private float P;
    private ViewGroupOverlay P0;
    private float Q;
    private Drawable Q0;
    private int R;
    private int R0;
    private VelocityTracker S;
    private Interpolator S0;
    private int T;
    private int T0;
    private int U;
    private int U0;
    private int V;
    private int V0;
    private int W;
    private int W0;
    private int X0;
    private int Y0;
    private boolean Z0;

    /* renamed from: a, reason: collision with root package name */
    HwPagerAdapter f10777a;
    private boolean a0;
    private int a1;
    int b;
    private long b0;
    private e b1;
    private int c;
    private EdgeEffect c0;
    private float c1;
    private int d;
    private EdgeEffect d0;
    private int d1;
    private int e;
    private boolean e0;
    private float e1;
    private final ArrayList<e> f;
    private boolean f0;
    private final e g;
    private boolean g0;
    private final Rect h;
    private int h0;
    private final Map<OnPageChangeListener, i> i;
    private List<OnPageChangeListener> i0;
    private final Runnable j;
    private OnPageChangeListener j0;
    private int k;
    private OnPageChangeListener k0;
    private Parcelable l;
    private List<OnAdapterChangeListener> l0;
    private ClassLoader m;
    private PageTransformer m0;
    private Scroller n;
    private int n0;
    private boolean o;
    private int o0;
    private m p;
    private ArrayList<View> p0;
    private int q;
    private int q0;
    private Drawable r;
    private boolean r0;
    private int s;
    private boolean s0;
    private int t;
    private boolean t0;
    private int u;
    private boolean u0;
    private int v;
    private float v0;
    private float w;
    private float w0;
    private float x;
    private boolean x0;
    private float y;
    private boolean y0;
    private float z;
    private boolean z0;
    static final int[] f1 = {R.attr.layout_gravity};
    private static final a Z1 = new a();
    private static final Comparator<e> a2 = new l();
    private static final Interpolator b2 = new b();

    @Target({ElementType.TYPE})
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes2.dex */
    public @interface DecorView {
    }

    public interface OnAdapterChangeListener {
        void onAdapterChanged(HwViewPager hwViewPager, HwPagerAdapter hwPagerAdapter, HwPagerAdapter hwPagerAdapter2);
    }

    /* loaded from: classes2.dex */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    public interface PageTransformer {
        void transformPage(View view, float f);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new d();

        /* renamed from: a, reason: collision with root package name */
        int f10780a;
        Parcelable b;
        ClassLoader c;

        class d implements Parcelable.ClassLoaderCreator<SavedState> {
            d() {
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f10780a + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f10780a);
            parcel.writeParcelable(this.b, i);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f10780a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }
    }

    static class a implements Comparator<View> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: eiG_, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            if (!(view.getLayoutParams() instanceof LayoutParams) || !(view2.getLayoutParams() instanceof LayoutParams)) {
                Log.w(HwViewPager.g1, "compare: view compare is not instance of layout params");
                return 0;
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            boolean z = layoutParams.f;
            return z != layoutParams2.f ? z ? 1 : -1 : layoutParams.e - layoutParams2.e;
        }
    }

    class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    class c implements OnApplyWindowInsetsListener {
        private final Rect e = new Rect();

        c() {
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
            WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
            if (onApplyWindowInsets.isConsumed()) {
                return onApplyWindowInsets;
            }
            Rect rect = this.e;
            rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
            rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
            rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
            rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
            int childCount = HwViewPager.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                WindowInsetsCompat dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(HwViewPager.this.getChildAt(i), onApplyWindowInsets);
                rect.left = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
                rect.top = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
                rect.right = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
                rect.bottom = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
            }
            return onApplyWindowInsets.replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    public static class d implements OnPageChangeListener {
        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
        }
    }

    protected static class e {

        /* renamed from: a, reason: collision with root package name */
        float f10781a;
        int b;
        float c;
        Object d;
        boolean e;

        protected e() {
        }
    }

    class f implements ValueAnimator.AnimatorUpdateListener {
        f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            HwViewPager.this.Q0.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f));
            HwViewPager.this.Q0.invalidateSelf();
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwViewPager.this.setScrollState(0);
            HwViewPager.this.o();
        }
    }

    class h implements ValueAnimator.AnimatorUpdateListener {
        h() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                return;
            }
            HwViewPager.this.Q0.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f));
            HwViewPager.this.Q0.invalidateSelf();
        }
    }

    class i implements OnPageChangeListener {

        /* renamed from: a, reason: collision with root package name */
        private final OnPageChangeListener f10782a;
        private int b;
        private float c;
        private float e;
        private boolean g;

        /* synthetic */ i(HwViewPager hwViewPager, OnPageChangeListener onPageChangeListener, l lVar) {
            this(onPageChangeListener);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (HwViewPager.this.r0) {
                return;
            }
            HwPagerAdapter currentAdapter = HwViewPager.this.getCurrentAdapter();
            if (currentAdapter != null) {
                HwViewPager hwViewPager = HwViewPager.this;
                int i2 = hwViewPager.b;
                int a2 = ((currentAdapter instanceof snh) && hwViewPager.s0) ? ((snh) currentAdapter).a(i2) : i2;
                if (HwViewPager.this.s0 && i != 2 && (i2 <= HwViewPager.this.e || i2 >= currentAdapter.getCount() - HwViewPager.this.d)) {
                    HwViewPager.this.b(a2, false);
                }
            }
            this.f10782a.onPageScrollStateChanged(i);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            int i3;
            if (HwViewPager.this.r0) {
                return;
            }
            HwPagerAdapter currentAdapter = HwViewPager.this.getCurrentAdapter();
            if (currentAdapter != null) {
                if ((currentAdapter instanceof snh) && HwViewPager.this.s0) {
                    snh snhVar = (snh) currentAdapter;
                    i3 = snhVar.a(i);
                    snhVar.d();
                } else {
                    currentAdapter.getCount();
                    i3 = i;
                }
                if (this.g) {
                    return;
                }
                if (HwViewPager.this.s0 && f == 0.0f && this.c == 0.0f && (i <= HwViewPager.this.e || i >= currentAdapter.getCount() - HwViewPager.this.d)) {
                    this.g = true;
                    HwViewPager.this.b(i3, false);
                    this.g = false;
                }
                i = i3;
            }
            this.c = f;
            this.b = i;
            this.f10782a.onPageScrolled(i, f, i2);
        }

        @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (HwViewPager.this.r0) {
                return;
            }
            HwPagerAdapter currentAdapter = HwViewPager.this.getCurrentAdapter();
            if ((currentAdapter instanceof snh) && HwViewPager.this.s0) {
                i = ((snh) currentAdapter).a(i);
            }
            float f = i;
            if (this.e != f) {
                this.e = f;
                this.f10782a.onPageSelected(i);
            }
        }

        private i(OnPageChangeListener onPageChangeListener) {
            this.c = -1.0f;
            this.e = -1.0f;
            this.g = false;
            this.f10782a = onPageChangeListener;
            this.b = -1;
        }
    }

    class j extends AccessibilityDelegateCompat {
        j() {
        }

        private boolean d() {
            HwPagerAdapter hwPagerAdapter = HwViewPager.this.f10777a;
            return hwPagerAdapter != null && hwPagerAdapter.getCount() > 1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(HwViewPager.class.getName());
            accessibilityEvent.setScrollable(d());
            if (accessibilityEvent.getEventType() == 4096) {
                HwViewPager hwViewPager = HwViewPager.this;
                if (hwViewPager.f10777a != null) {
                    accessibilityEvent.setItemCount(hwViewPager.getRealCount());
                    accessibilityEvent.setFromIndex(HwViewPager.this.getCurrentItem());
                    accessibilityEvent.setToIndex(HwViewPager.this.getCurrentItem());
                }
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(HwViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(d());
            if ((HwViewPager.this.isPageScrollHorizontal() && HwViewPager.this.canScrollHorizontally(1)) || (!HwViewPager.this.isPageScrollHorizontal() && HwViewPager.this.canScrollVertically(1))) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (!(HwViewPager.this.isPageScrollHorizontal() && HwViewPager.this.canScrollHorizontally(-1)) && (HwViewPager.this.isPageScrollHorizontal() || !HwViewPager.this.canScrollVertically(-1))) {
                return;
            }
            accessibilityNodeInfoCompat.addAction(8192);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            if (i == 4096) {
                if (!(HwViewPager.this.isPageScrollHorizontal() && HwViewPager.this.canScrollHorizontally(1)) && (HwViewPager.this.isPageScrollHorizontal() || !HwViewPager.this.canScrollVertically(1))) {
                    return false;
                }
                HwViewPager.this.N0 = false;
                HwViewPager hwViewPager = HwViewPager.this;
                hwViewPager.setCurrentItem(hwViewPager.getCurrentItem() + 1);
                HwViewPager.this.N0 = true;
                return true;
            }
            if (i != 8192) {
                return false;
            }
            if (!(HwViewPager.this.isPageScrollHorizontal() && HwViewPager.this.canScrollHorizontally(-1)) && (HwViewPager.this.isPageScrollHorizontal() || !HwViewPager.this.canScrollVertically(-1))) {
                return false;
            }
            HwViewPager.this.N0 = false;
            HwViewPager hwViewPager2 = HwViewPager.this;
            hwViewPager2.setCurrentItem(hwViewPager2.getCurrentItem() - 1);
            HwViewPager.this.N0 = true;
            return true;
        }
    }

    class l implements Comparator<e> {
        l() {
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(e eVar, e eVar2) {
            return eVar.b - eVar2.b;
        }
    }

    class m extends DataSetObserver {
        m() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            HwViewPager.this.g();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            HwViewPager.this.g();
        }
    }

    public HwViewPager(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwPagerAdapter getCurrentAdapter() {
        return this.f10777a;
    }

    private float getCurrentAnimationPosition() {
        bng bngVar = this.I0;
        if (bngVar == null || !bngVar.isRunning()) {
            return 0.0f;
        }
        return this.I0.b().getPosition();
    }

    private float getEndAnimationPosition() {
        bng bngVar = this.I0;
        if (bngVar == null || !bngVar.isRunning()) {
            return 0.0f;
        }
        return this.I0.b().getEndPosition();
    }

    private int getNewAnimationScrollX() {
        if (!isPageScrollHorizontal()) {
            return getScrollX();
        }
        if (this.u0) {
            bng bngVar = this.I0;
            if (bngVar == null || !bngVar.isRunning()) {
                return getScrollX();
            }
            int currentAnimationPosition = (int) getCurrentAnimationPosition();
            cancelAnimation();
            setScrollingCacheEnabled(false);
            return currentAnimationPosition;
        }
        Scroller scroller = this.n;
        if (scroller == null || scroller.isFinished()) {
            return getScrollX();
        }
        int currX = this.o ? this.n.getCurrX() : this.n.getStartX();
        this.n.abortAnimation();
        setScrollingCacheEnabled(false);
        return currX;
    }

    private int getNewAnimationScrollY() {
        if (isPageScrollHorizontal()) {
            return getScrollY();
        }
        if (this.u0) {
            bng bngVar = this.I0;
            if (bngVar == null || !bngVar.isRunning()) {
                return getScrollY();
            }
            int currentAnimationPosition = (int) getCurrentAnimationPosition();
            cancelAnimation();
            setScrollingCacheEnabled(false);
            return currentAnimationPosition;
        }
        Scroller scroller = this.n;
        if (scroller == null || scroller.isFinished()) {
            return getScrollY();
        }
        int currY = this.o ? this.n.getCurrY() : this.n.getStartY();
        this.n.abortAnimation();
        setScrollingCacheEnabled(false);
        return currY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRealCount() {
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        return ((hwPagerAdapter instanceof snh) && this.s0) ? ((snh) hwPagerAdapter).d() : hwPagerAdapter.getCount();
    }

    private int h(int i2) {
        e e2 = e(i2);
        if (e2 != null) {
            return (int) ((isPageScrollHorizontal() ? getClientWidth() : getClientHeight()) * Math.max(this.y, Math.min(e2.f10781a, this.z)));
        }
        return 0;
    }

    private void i() {
        bng bngVar = this.I0;
        if (bngVar == null || !bngVar.isRunning()) {
            return;
        }
        s();
        this.I0.removeEndListener(this.J0);
        this.I0.removeUpdateListener(this.K0);
        this.I0.cancel();
    }

    public static HwViewPager instantiate(Context context) {
        Object e2 = sms.e(context, sms.e(context, (Class<?>) HwViewPager.class, sms.c(context, 15, 1)), (Class<?>) HwViewPager.class);
        if (e2 instanceof HwViewPager) {
            return (HwViewPager) e2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        this.z0 = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.z0 = true;
    }

    private void p() {
        int currentItem = getCurrentItem();
        if (!isPageScrollHorizontal()) {
            this.n.setFinalY(currentItem * getClientHeight());
            return;
        }
        if (isRtlLayout()) {
            currentItem = -currentItem;
        }
        this.n.setFinalX(currentItem * getClientWidth());
    }

    private void q() {
        int velocity = (int) this.I0.b().getVelocity();
        int currentItem = getCurrentItem();
        if (!isPageScrollHorizontal()) {
            this.I0.e().e(this, DynamicAnimation.SCROLL_Y, this.w0, this.v0, currentItem * getClientHeight(), velocity);
            return;
        }
        if (isRtlLayout()) {
            currentItem = -currentItem;
        }
        this.I0.e().e(this, DynamicAnimation.SCROLL_X, this.w0, this.v0, currentItem * getClientWidth(), velocity);
    }

    private void r() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).f) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void s() {
        int ceil;
        int i2;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        float endAnimationPosition = getEndAnimationPosition() - getCurrentAnimationPosition();
        if (isPageScrollHorizontal()) {
            i2 = ((int) Math.ceil(endAnimationPosition)) + scrollX;
            ceil = scrollY;
        } else {
            ceil = ((int) Math.ceil(endAnimationPosition)) + scrollY;
            i2 = scrollX;
        }
        if (scrollX != i2) {
            scrollTo(i2, ceil);
            pageScrolled(i2);
        } else if (scrollY != ceil) {
            scrollTo(i2, ceil);
            pageScrolled(ceil);
        }
    }

    private void setAdapterInner(HwPagerAdapter hwPagerAdapter) {
        HwPagerAdapter hwPagerAdapter2 = this.f10777a;
        if (hwPagerAdapter2 != null) {
            hwPagerAdapter2.a(null);
            this.f10777a.startUpdate(this);
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                e eVar = this.f.get(i2);
                this.f10777a.destroyItem(this, eVar.b, eVar.d);
            }
            this.f10777a.finishUpdate(this);
            this.f.clear();
            r();
            this.b = 0;
            scrollTo(0, 0);
        }
        HwPagerAdapter hwPagerAdapter3 = this.f10777a;
        this.f10777a = hwPagerAdapter;
        this.c = 0;
        if (hwPagerAdapter != null) {
            if (this.p == null) {
                this.p = new m();
            }
            this.f10777a.a(this.p);
            this.F = false;
            boolean z = this.e0;
            this.e0 = true;
            this.c = this.f10777a.getCount();
            if (this.k >= 0) {
                this.f10777a.restoreState(this.l, this.m);
                setCurrentItemInternal(this.k, false, true);
                this.k = -1;
                this.l = null;
                this.m = null;
            } else if (z) {
                requestLayout();
            } else {
                o();
            }
        }
        List<OnAdapterChangeListener> list = this.l0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.l0.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.l0.get(i3).onAdapterChanged(this, hwPagerAdapter3, hwPagerAdapter);
        }
    }

    private void setChildCount(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i3).getLayoutParams();
            if (layoutParams instanceof LayoutParams) {
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (!layoutParams2.f) {
                    layoutParams2.c = 0.0f;
                }
            }
        }
    }

    private void setCurrentItemWithoutNotification(int i2) {
        this.r0 = true;
        a(i2, false);
        this.r0 = false;
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.E != z) {
            this.E = z;
        }
    }

    private void setSensitivityMode(int i2) {
        if (i2 == 0) {
            this.Q = 1.4f;
        } else if (i2 == 2) {
            this.Q = 1.0f;
        } else {
            this.Q = 1.4f;
        }
    }

    private void setValueFromPlume(Context context) {
        Method b3 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b3 == null) {
            return;
        }
        Object c2 = slc.c((Object) null, b3, new Object[]{context, this, "changePageEnabled", Boolean.TRUE});
        if (c2 instanceof Boolean) {
            setExtendedChangePageEnabled(((Boolean) c2).booleanValue());
        }
    }

    private void t() {
        this.I0.addEndListener(this.J0);
        this.I0.addUpdateListener(this.K0);
    }

    private boolean u() {
        this.R = -1;
        h();
        this.c0.onRelease();
        this.d0.onRelease();
        return this.c0.isFinished() || this.d0.isFinished();
    }

    private void v() {
        if (!this.u0) {
            boolean z = !isPageScrollHorizontal() ? Math.abs(this.n.getFinalY() - this.n.getCurrY()) <= this.W : Math.abs(this.n.getFinalX() - this.n.getCurrX()) <= this.W;
            if (this.q0 != 2 || !z) {
                a(false);
                this.H = false;
                return;
            }
            this.n.abortAnimation();
            this.F = false;
            o();
            this.H = true;
            c(true);
            setScrollState(1);
            return;
        }
        float abs = Math.abs(getCurrentAnimationPosition());
        float abs2 = Math.abs(getEndAnimationPosition());
        if (this.q0 != 2 || Math.abs(abs - abs2) <= this.W) {
            a(false);
            this.H = false;
            return;
        }
        this.n.abortAnimation();
        cancelAnimation();
        this.F = false;
        o();
        this.H = true;
        c(true);
        setScrollState(1);
    }

    private void w() {
        if (this.o0 != 0) {
            ArrayList<View> arrayList = this.p0;
            if (arrayList == null) {
                this.p0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.p0.add(getChildAt(i2));
            }
            Collections.sort(this.p0, Z1);
        }
    }

    private void x() {
        this.C0.setDuration(300L);
        this.C0.addUpdateListener(new f());
        this.C0.setInterpolator(this.S0);
        this.C0.start();
    }

    private void y() {
        if (this.A0 || this.Q0 == null) {
            return;
        }
        ValueAnimator valueAnimator = this.C0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.C0.cancel();
        }
        this.C0 = ValueAnimator.ofFloat(this.w, this.x);
        x();
    }

    private void z() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.x, this.w);
        this.C0 = ofFloat;
        ofFloat.setDuration(300L);
        this.C0.addUpdateListener(new h());
        this.C0.setInterpolator(this.S0);
        this.C0.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        e c2;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (c2 = c(childAt)) != null && c2.b == this.b) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    public void addOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        if (this.l0 == null) {
            this.l0 = new ArrayList();
        }
        this.l0.add(onAdapterChangeListener);
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.s0) {
            i iVar = new i(this, onPageChangeListener, null);
            this.i.put(onPageChangeListener, iVar);
            onPageChangeListener = iVar;
        }
        if (this.i0 == null) {
            this.i0 = new ArrayList();
        }
        this.i0.add(onPageChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        e c2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (c2 = c(childAt)) != null && c2.b == this.b) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        if (!(layoutParams instanceof LayoutParams)) {
            Log.e(g1, "addView: LayoutParams lp is null or not layout params!");
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean d2 = layoutParams2.f | d(view);
        layoutParams2.f = d2;
        if (!this.C) {
            super.addView(view, i2, layoutParams);
        } else {
            if (d2) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.f10778a = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public boolean arrowScroll(int i2) {
        View findFocus = findFocus();
        if (findFocus != this) {
            if (findFocus != null) {
                for (ViewParent parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        break;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(findFocus.getClass().getSimpleName());
                for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ");
                    sb.append(parent2.getClass().getSimpleName());
                }
                Log.e(g1, "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
            }
            return b(i2, findFocus);
        }
        findFocus = null;
        return b(i2, findFocus);
    }

    public boolean beginFakeDrag() {
        if (this.H) {
            return false;
        }
        this.a0 = true;
        setScrollState(1);
        if (isPageScrollHorizontal()) {
            this.O = 0.0f;
            this.M = 0.0f;
        } else {
            this.P = 0.0f;
            this.N = 0.0f;
        }
        VelocityTracker velocityTracker = this.S;
        if (velocityTracker == null) {
            this.S = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.S.addMovement(obtain);
        obtain.recycle();
        this.b0 = uptimeMillis;
        return true;
    }

    protected boolean canScroll(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (isPageScrollHorizontal()) {
            if (z && view.canScrollHorizontally(-i2)) {
                return true;
            }
        } else if (z && view.canScrollVertically(-i2)) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.f10777a == null) {
            return false;
        }
        int currentItem = getCurrentItem();
        int count = this.f10777a.getCount();
        if (i2 < 0) {
            if (isRtlLayout()) {
                if (currentItem == count - 1) {
                    return false;
                }
            } else if (currentItem == 0) {
                return false;
            }
        } else {
            if (i2 <= 0) {
                return false;
            }
            if (isRtlLayout()) {
                if (currentItem == 0) {
                    return false;
                }
            } else if (currentItem == count - 1) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        if (this.f10777a == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        return i2 < 0 ? scrollY > ((int) (((float) clientHeight) * this.y)) : i2 > 0 && scrollY < ((int) (((float) clientHeight) * this.z));
    }

    protected void cancelAnimation() {
        bng bngVar = this.I0;
        if (bngVar == null || !bngVar.isRunning()) {
            return;
        }
        this.I0.removeEndListener(this.J0);
        this.I0.removeUpdateListener(this.K0);
        this.I0.cancel();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void clearOnPageChangeListeners() {
        if (isRtlLayout()) {
            this.i.clear();
        }
        List<OnPageChangeListener> list = this.i0;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        this.o = true;
        if (this.n.isFinished() || !this.n.computeScrollOffset()) {
            if (this.u0) {
                return;
            }
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.n.getCurrX();
        int currY = this.n.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.n.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    protected HwGenericEventDetector createGenericEventDetector() {
        return new HwGenericEventDetector(getContext());
    }

    protected HwGenericEventDetector.OnChangePageListener createOnChangePageListener() {
        return new HwGenericEventDetector.OnChangePageListener() { // from class: sng
            @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnChangePageListener
            public final boolean onChangePage(float f2, MotionEvent motionEvent) {
                boolean a3;
                a3 = HwViewPager.this.a(f2, motionEvent);
                return a3;
            }
        };
    }

    protected HwGenericEventDetector.OnScrollListener createOnScrollListener() {
        return null;
    }

    protected int determineTargetPage(int i2, float f2, int i3, int i4) {
        int a3 = isPageScrollHorizontal() ? a(i2, f2, i3, i4) : b(i2, f2, i3, i4);
        if (this.f.size() <= 0) {
            return a3;
        }
        return Math.max(this.f.get(0).b, Math.min(a3, this.f.get(r4.size() - 1).b));
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        e c2;
        if (accessibilityEvent == null) {
            return false;
        }
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (c2 = c(childAt)) != null && c2.b == this.b && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        HwPagerAdapter hwPagerAdapter;
        if (canvas == null) {
            return;
        }
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        if (overScrollMode != 0 && (overScrollMode != 1 || (hwPagerAdapter = this.f10777a) == null || hwPagerAdapter.getCount() <= 1)) {
            this.c0.finish();
            this.d0.finish();
            return;
        }
        if (this.c0.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = getWidth();
            canvas.rotate(270.0f);
            canvas.translate((-height) + getPaddingTop(), this.y * width);
            this.c0.setSize(height, width);
            z = this.c0.draw(canvas);
            canvas.restoreToCount(save);
        }
        if (!this.d0.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate(-getPaddingTop(), (-(this.z + 1.0f)) * width2);
            this.d0.setSize((height2 - paddingTop) - paddingBottom, width2);
            z |= this.d0.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.r;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public void endFakeDrag() {
        if (!this.a0) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.f10777a != null && this.R != -1) {
            VelocityTracker velocityTracker = this.S;
            velocityTracker.computeCurrentVelocity(1000, this.U);
            int xVelocity = (int) (isPageScrollHorizontal() ? velocityTracker.getXVelocity(this.R) : velocityTracker.getYVelocity(this.R));
            this.F = true;
            if (isPageScrollHorizontal()) {
                c(xVelocity);
            } else {
                d(xVelocity);
            }
        }
        h();
        this.a0 = false;
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? m() : arrowScroll(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? n() : arrowScroll(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    public void fakeDragBy(float f2) {
        if (!this.a0) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.f10777a == null || this.f.size() <= 0) {
            return;
        }
        if (isPageScrollHorizontal()) {
            this.M += f2;
        } else {
            this.N += f2;
        }
        float a3 = a((isPageScrollHorizontal() ? getScrollX() : getScrollY()) - f2, isPageScrollHorizontal() ? getClientWidth() : getClientHeight());
        if (isPageScrollHorizontal()) {
            int i2 = (int) a3;
            this.M += a3 - i2;
            scrollTo(i2, getScrollY());
        } else {
            int i3 = (int) a3;
            this.N += a3 - i3;
            scrollTo(getScrollX(), i3);
        }
        pageScrolled((int) a3);
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = isPageScrollHorizontal() ? MotionEvent.obtain(this.b0, uptimeMillis, 2, this.M, 0.0f, 0) : MotionEvent.obtain(this.b0, uptimeMillis, 2, 0.0f, this.N, 0);
        this.S.addMovement(obtain);
        obtain.recycle();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public HwPagerAdapter getAdapter() {
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        return hwPagerAdapter instanceof snh ? ((snh) hwPagerAdapter).b() : hwPagerAdapter;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.o0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        if (this.p0.size() == 0) {
            return 0;
        }
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 >= this.p0.size()) {
            i3 = this.p0.size() - 1;
        }
        ViewGroup.LayoutParams layoutParams = this.p0.get(i3).getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            return ((LayoutParams) layoutParams).d;
        }
        return 0;
    }

    protected int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    protected int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public int getCurrentItem() {
        int i2 = this.b;
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        return ((hwPagerAdapter instanceof snh) && this.s0) ? ((snh) hwPagerAdapter).a(i2) : i2;
    }

    public float getEndShadowAlpha() {
        return this.x;
    }

    public float getHwSpringDamping() {
        return this.v0;
    }

    public float getHwSpringStiffness() {
        return this.w0;
    }

    public int getOffscreenPageLimit() {
        return this.G;
    }

    public int getPageMargin() {
        return this.q;
    }

    public int getPageScrollDirection() {
        return this.O0;
    }

    protected float getPageSwitchThreshold() {
        if (this.u0) {
            return 0.5f;
        }
        return p1;
    }

    public boolean getReverseDrawingOrder() {
        return this.D;
    }

    protected float getRotaryVelocity() {
        HwGenericEventDetector hwGenericEventDetector = this.L0;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.a();
        }
        return 0.0f;
    }

    public Scroller getScroller() {
        return this.n;
    }

    public float getSensitivity() {
        HwGenericEventDetector hwGenericEventDetector = this.L0;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.b();
        }
        return 1.4f;
    }

    public int getShadowColor() {
        return this.R0;
    }

    public boolean getShadowEnable() {
        return this.F0;
    }

    public float getSpringInterpolatorEndPos() {
        return this.H0;
    }

    public float getStartShadowAlpha() {
        return this.w;
    }

    public float getVelocityRatio() {
        return this.G0;
    }

    protected e infoForCurrentScrollPosition() {
        float f2;
        int i2;
        boolean z = isPageScrollHorizontal() && isRtlLayout();
        float clientWidth = isPageScrollHorizontal() ? getClientWidth() : getClientHeight();
        float f3 = 0.0f;
        if (clientWidth > 0.0f) {
            f2 = (isPageScrollHorizontal() ? getScrollX() : getScrollY()) / clientWidth;
        } else {
            f2 = 0.0f;
        }
        if (z) {
            f2 = -f2;
        }
        float f4 = clientWidth > 0.0f ? this.q / clientWidth : 0.0f;
        int i3 = -1;
        int i4 = 0;
        boolean z2 = true;
        e eVar = null;
        float f5 = 0.0f;
        while (i4 < this.f.size()) {
            e eVar2 = this.f.get(i4);
            if (!z2 && eVar2.b != (i2 = i3 + 1)) {
                eVar2 = this.g;
                eVar2.f10781a = f3 + f5 + f4;
                eVar2.b = i2;
                eVar2.c = isPageScrollHorizontal() ? this.f10777a.getPageWidth(eVar2.b) : this.f10777a.getPageHeight(eVar2.b);
                i4--;
            }
            e eVar3 = eVar2;
            f3 = eVar3.f10781a;
            float f6 = eVar3.c;
            if (!z2 && f2 < f3) {
                return eVar;
            }
            if (f2 < f6 + f3 + f4 || i4 == this.f.size() - 1) {
                return eVar3;
            }
            i3 = eVar3.b;
            i4++;
            z2 = false;
            eVar = eVar3;
            f5 = eVar3.c;
        }
        return eVar;
    }

    public boolean isAutoRtlLayoutEnabled() {
        return this.y0;
    }

    public boolean isDynamicSpringAnimaitionEnabled() {
        return this.u0;
    }

    public boolean isExtendedChangePageEnabled() {
        return this.M0;
    }

    public boolean isFakeDragging() {
        return this.a0;
    }

    public boolean isPageScrollHorizontal() {
        return this.O0 == 0;
    }

    public boolean isRtlLayout() {
        if (!this.y0) {
            return false;
        }
        String language = Locale.getDefault().getLanguage();
        return (language.contains("ar") || language.contains("fa") || language.contains("iw")) || (language.contains("ug") || language.contains(Constants.URDU_LANG));
    }

    public boolean isSpringInterpolatorEnable() {
        return this.x0;
    }

    public boolean isSupportLoop() {
        return this.s0;
    }

    public boolean isSupportRltLayout() {
        return isRtlLayout();
    }

    void j() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.n = new Scroller(context, b2);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.L = viewConfiguration.getScaledPagingTouchSlop();
        if (this.u0) {
            this.T = 1200;
        } else {
            this.T = (int) (400.0f * f2);
        }
        this.U = viewConfiguration.getScaledMaximumFlingVelocity();
        this.c0 = new EdgeEffect(context);
        this.d0 = new EdgeEffect(context);
        this.V = (int) (25.0f * f2);
        this.W = (int) (2.0f * f2);
        this.J = (int) (f2 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new j());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new c());
    }

    boolean m() {
        if (this.z0 && this.f10777a != null) {
            int i2 = this.b;
            if (i2 > 0) {
                this.N0 = false;
                if (isPageScrollHorizontal() && isRtlLayout()) {
                    HwPagerAdapter hwPagerAdapter = this.f10777a;
                    if (hwPagerAdapter != null && this.b == hwPagerAdapter.getCount() - 1) {
                        return false;
                    }
                    b();
                    setCurrentItem(this.b + 1, true);
                } else {
                    a();
                    setCurrentItem(this.b - 1, true);
                }
                this.N0 = true;
                return true;
            }
            if (i2 == 0 && isPageScrollHorizontal() && isRtlLayout()) {
                HwPagerAdapter hwPagerAdapter2 = this.f10777a;
                if (hwPagerAdapter2 != null && hwPagerAdapter2.getCount() == 1) {
                    return false;
                }
                setCurrentItem(this.b + 1, true);
                return true;
            }
        }
        return false;
    }

    boolean n() {
        HwPagerAdapter hwPagerAdapter;
        if (this.z0 && (hwPagerAdapter = this.f10777a) != null) {
            if (this.b < hwPagerAdapter.getCount() - 1) {
                this.N0 = false;
                if (!isPageScrollHorizontal() || !isRtlLayout()) {
                    b();
                    setCurrentItem(this.b + 1, true);
                } else {
                    if (this.b == 0) {
                        return false;
                    }
                    a();
                    setCurrentItem(this.b - 1, true);
                }
                this.N0 = true;
                return true;
            }
            if (this.b == this.f10777a.getCount() - 1 && isPageScrollHorizontal() && isRtlLayout()) {
                setCurrentItem(this.b - 1, true);
                return true;
            }
        }
        return false;
    }

    public void nextPage() {
        nextPage(true);
    }

    void o() {
        populate(this.b);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e0 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.j);
        Scroller scroller = this.n;
        if (scroller != null && !scroller.isFinished()) {
            this.n.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.q <= 0 || this.r == null || this.f.size() <= 0 || this.f10777a == null) {
            return;
        }
        int scrollX = isPageScrollHorizontal() ? getScrollX() : getScrollY();
        int width = isPageScrollHorizontal() ? getWidth() : getHeight();
        float f4 = width <= 0 ? 0.0f : this.q / width;
        e eVar = this.f.get(0);
        float f5 = eVar.f10781a;
        int size = this.f.size();
        int i2 = eVar.b;
        int i3 = this.f.get(size - 1).b;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            e eVar2 = eVar;
            int i6 = i5;
            while (i4 > eVar2.b && i6 < size) {
                i6++;
                eVar2 = this.f.get(i6);
            }
            boolean z = isPageScrollHorizontal() && isRtlLayout();
            if (i4 == eVar2.b) {
                float f6 = z ? -(eVar2.f10781a + eVar2.c) : eVar2.f10781a + eVar2.c;
                f3 = width * f6;
                f2 = z ? f6 - f4 : f6 + f4;
            } else {
                float pageWidth = isPageScrollHorizontal() ? this.f10777a.getPageWidth(i4) : this.f10777a.getPageHeight(i4);
                float f7 = z ? f5 - pageWidth : f5 + pageWidth;
                float f8 = width;
                float f9 = pageWidth + f4;
                f2 = z ? f5 - f9 : f5 + f9;
                f3 = f7 * f8;
            }
            if (a(canvas, scrollX, width, f3, z)) {
                return;
            }
            i4++;
            eVar = eVar2;
            i5 = i6;
            f5 = f2;
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        HwGenericEventDetector hwGenericEventDetector;
        if (!this.M0 || (hwGenericEventDetector = this.L0) == null) {
            return super.onGenericMotionEvent(motionEvent);
        }
        if (hwGenericEventDetector.eis_(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            u();
            return false;
        }
        if (action != 0) {
            if (this.H) {
                return true;
            }
            if (this.I) {
                return false;
            }
        }
        if (c(motionEvent, action)) {
            return false;
        }
        if (this.S == null) {
            this.S = VelocityTracker.obtain();
        }
        this.S.addMovement(motionEvent);
        return this.H;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        this.V0 = getPaddingLeft();
        this.W0 = getPaddingTop();
        this.X0 = getPaddingRight();
        this.Y0 = getPaddingBottom();
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                a(i6, i7, scrollX, scrollY, childAt);
                i8++;
            }
        }
        int i10 = this.V0;
        int i11 = this.X0;
        int i12 = this.W0;
        int i13 = this.Y0;
        Rect rect = new Rect(this.V0, this.W0, this.X0, this.Y0);
        for (int i14 = 0; i14 < childCount; i14++) {
            if (isPageScrollHorizontal()) {
                a(i14, (i6 - i10) - i11, i7, rect);
            } else {
                b(i14, (i7 - i12) - i13, i6, rect);
            }
        }
        if (isPageScrollHorizontal()) {
            this.s = this.W0;
            this.t = i7 - this.Y0;
        } else {
            this.u = this.V0;
            this.v = i6 - this.X0;
        }
        this.h0 = i8;
        if (this.e0) {
            a(this.b, false, 0, false);
        }
        this.e0 = false;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(View.getDefaultSize(0, i2), View.getDefaultSize(0, i3));
        int measuredWidth = getMeasuredWidth();
        this.K = Math.min(measuredWidth / 10, this.J);
        this.T0 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        this.U0 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                a(childAt, (LayoutParams) childAt.getLayoutParams());
            }
        }
        this.A = View.MeasureSpec.makeMeasureSpec(this.T0, 1073741824);
        this.B = View.MeasureSpec.makeMeasureSpec(this.U0, 1073741824);
        this.C = true;
        o();
        this.C = false;
        int childCount2 = getChildCount();
        for (int i5 = 0; i5 < childCount2; i5++) {
            a(this.T0, this.U0, i5);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
    
        r3 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onPageScrolled(int r5, float r6, int r7) {
        /*
            r4 = this;
            int r0 = r4.h0
            if (r0 <= 0) goto L11
            boolean r0 = r4.isPageScrollHorizontal()
            if (r0 == 0) goto Le
            r4.e()
            goto L11
        Le:
            r4.f()
        L11:
            r4.a(r5, r6, r7)
            com.huawei.uikit.hwviewpager.widget.HwViewPager$PageTransformer r5 = r4.m0
            r6 = 1
            if (r5 == 0) goto L5b
            boolean r5 = r4.isPageScrollHorizontal()
            if (r5 == 0) goto L24
            int r5 = r4.getScrollX()
            goto L28
        L24:
            int r5 = r4.getScrollY()
        L28:
            int r7 = r4.getChildCount()
            r0 = 0
            r1 = r0
        L2e:
            if (r1 >= r7) goto L5b
            android.view.View r2 = r4.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r3 = r2.getLayoutParams()
            com.huawei.uikit.hwviewpager.widget.HwViewPager$LayoutParams r3 = (com.huawei.uikit.hwviewpager.widget.HwViewPager.LayoutParams) r3
            boolean r3 = r3.f
            if (r3 == 0) goto L3f
            goto L58
        L3f:
            boolean r3 = r4.isPageScrollHorizontal()
            if (r3 == 0) goto L4c
            int r3 = r4.getClientWidth()
            if (r3 <= 0) goto L54
            goto L52
        L4c:
            int r3 = r4.getClientHeight()
            if (r3 <= 0) goto L54
        L52:
            r3 = r6
            goto L55
        L54:
            r3 = r0
        L55:
            r4.a(r5, r2, r3)
        L58:
            int r1 = r1 + 1
            goto L2e
        L5b:
            r4.g0 = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.onPageScrolled(int, float, int):void");
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            if (a(i2, rect, i3)) {
                return true;
            }
            i3 += i5;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof RtlSavedState) {
            RtlSavedState rtlSavedState = (RtlSavedState) parcelable;
            Parcelable parcelable2 = rtlSavedState.f10779a;
            if (!(parcelable2 instanceof SavedState)) {
                super.onRestoreInstanceState(parcelable2);
                return;
            }
            SavedState savedState = (SavedState) parcelable2;
            super.onRestoreInstanceState(savedState.getSuperState());
            HwPagerAdapter hwPagerAdapter = this.f10777a;
            if (hwPagerAdapter != null) {
                hwPagerAdapter.restoreState(savedState.b, savedState.c);
                setCurrentItemInternal(savedState.f10780a, false, true);
            } else {
                this.k = savedState.f10780a;
                this.l = savedState.b;
                this.m = savedState.c;
            }
            if (rtlSavedState.c != isRtlLayout()) {
                this.N0 = false;
                setCurrentItem(rtlSavedState.b, false);
                this.N0 = true;
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f10780a = this.b;
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        if (hwPagerAdapter != null) {
            savedState.b = hwPagerAdapter.saveState();
        }
        return new RtlSavedState(savedState, this.b, isRtlLayout());
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        Drawable drawable = this.Q0;
        if (drawable != null && i3 != i5) {
            drawable.setBounds(0, 0, i2, i3);
        }
        if (isPageScrollHorizontal()) {
            if (i2 != i4) {
                c(i2, i4);
            }
        } else if (i3 != i5) {
            c(i3, i5);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        HwPagerAdapter hwPagerAdapter;
        if (motionEvent == null) {
            return false;
        }
        if (this.a0) {
            return true;
        }
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (hwPagerAdapter = this.f10777a) == null || hwPagerAdapter.getCount() == 0) {
            return false;
        }
        if (this.S == null) {
            this.S = VelocityTracker.obtain();
        }
        this.S.addMovement(motionEvent);
        int action = motionEvent.getAction();
        this.Z0 = false;
        int i2 = action & 255;
        if (i2 == 0) {
            f(motionEvent);
        } else if (i2 == 1) {
            e(motionEvent);
        } else if (i2 != 2) {
            if (i2 == 3) {
                a(motionEvent);
            } else if (i2 == 5) {
                c(motionEvent);
            } else if (i2 == 6) {
                d(motionEvent);
            }
        } else {
            if (this.A0) {
                setScrollState(1);
                return true;
            }
            b(motionEvent);
        }
        if (this.Z0) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    protected boolean pageScrolled(int i2) {
        if (this.f.size() == 0) {
            if (this.e0) {
                return false;
            }
            this.g0 = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.g0) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        if (infoForCurrentScrollPosition == null) {
            Log.e(g1, "pageScrolled: ItemInfo is null!");
            return false;
        }
        int clientWidth = isPageScrollHorizontal() ? getClientWidth() : getClientHeight();
        float f2 = clientWidth <= 0 ? 0.0f : this.q / clientWidth;
        int i3 = infoForCurrentScrollPosition.b;
        boolean z = clientWidth > 0 && infoForCurrentScrollPosition.c + f2 > 0.0f;
        if (isPageScrollHorizontal() && isRtlLayout()) {
            i2 = -i2;
        }
        float f3 = z ? ((i2 / clientWidth) - infoForCurrentScrollPosition.f10781a) / (infoForCurrentScrollPosition.c + f2) : 0.0f;
        this.g0 = false;
        onPageScrolled(i3, f3, (int) ((clientWidth + r5) * f3));
        if (this.g0) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected boolean performDrag(float f2) {
        return isPageScrollHorizontal() ? d(f2) : e(f2);
    }

    protected void populate(int i2) {
        e eVar;
        int i3 = this.b;
        if (i3 != i2) {
            eVar = e(i3);
            this.b = i2;
        } else {
            eVar = null;
        }
        if (this.f10777a == null) {
            w();
            return;
        }
        if (this.F) {
            w();
            return;
        }
        if (getWindowToken() == null) {
            return;
        }
        this.f10777a.startUpdate(this);
        a(eVar);
        this.f10777a.finishUpdate(this);
        c();
        w();
        if (hasFocus()) {
            View findFocus = findFocus();
            e b3 = findFocus != null ? b(findFocus) : null;
            if (b3 == null || b3.b != this.b) {
                d();
            }
        }
    }

    public void prePage() {
        prePage(true);
    }

    public void removeOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        List<OnAdapterChangeListener> list = this.l0;
        if (list != null) {
            list.remove(onAdapterChangeListener);
        }
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (isRtlLayout()) {
            onPageChangeListener = this.i.remove(onPageChangeListener);
        }
        List<OnPageChangeListener> list = this.i0;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.C) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void setAdapter(HwPagerAdapter hwPagerAdapter) {
        this.D0 = hwPagerAdapter;
        if (hwPagerAdapter != null) {
            boolean z = this.s0;
            if (z) {
                hwPagerAdapter = new snh(hwPagerAdapter, z);
            }
            if (hwPagerAdapter instanceof snh) {
                ((snh) hwPagerAdapter).e(this.t0);
            }
        }
        setAdapterInner(hwPagerAdapter);
        if (this.s0) {
            a(0, false);
        }
    }

    public void setAutoRtlLayoutEnabled(boolean z) {
        this.y0 = z;
    }

    public void setCurrentItem(int i2) {
        if (getCurrentItem() != i2) {
            setCurrentItem(i2, true);
        }
    }

    void setCurrentItemInternal(int i2, boolean z, boolean z2) {
        setCurrentItemInternal(i2, z, z2, 0);
    }

    public void setDynamicSpringAnimaitionEnabled(boolean z) {
        this.u0 = z;
    }

    public void setEndShadowAlpha(float f2) {
        this.x = f2;
    }

    public void setExtendedChangePageEnabled(boolean z) {
        this.M0 = z;
    }

    public void setHwSpringDamping(float f2) {
        this.v0 = f2;
    }

    public void setHwSpringStiffness(float f2) {
        this.w0 = f2;
    }

    public void setLoopEndCache(int i2) {
        HwPagerAdapter hwPagerAdapter;
        if (!this.s0 || (hwPagerAdapter = this.f10777a) == null || i2 <= 2) {
            return;
        }
        this.d = i2;
        if (hwPagerAdapter instanceof snh) {
            snh snhVar = (snh) hwPagerAdapter;
            snhVar.d(i2);
            snhVar.notifyDataSetChanged();
        }
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < this.e) {
            Log.w(g1, "Requested offscreen page limit " + i2 + " too small; defaulting to " + this.e);
            i2 = this.e;
        }
        if (i2 != this.G) {
            this.G = i2;
            o();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.s0) {
            onPageChangeListener = new i(this, onPageChangeListener, null);
        }
        this.j0 = onPageChangeListener;
    }

    public void setPageMargin(int i2) {
        this.q = i2;
        int width = isPageScrollHorizontal() ? getWidth() : getHeight();
        c(width, width);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.r = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageScrollDirection(int i2) {
        if ((i2 == 1 || i2 == 0) && this.O0 != i2) {
            this.O0 = i2;
            requestLayout();
        }
    }

    public void setPageTransformer(boolean z, PageTransformer pageTransformer) {
        setPageTransformer(z, pageTransformer, 2);
    }

    public void setPageTurningEnabled(boolean z) {
        this.A0 = z;
        if (z && this.E0 == null) {
            this.E0 = new smz(this);
        }
    }

    public void setPageTurningThresholdRatioHorizontal(float f2) {
        if (Float.compare(f2, 0.0f) < 0 || Float.compare(f2, 1.0f) > 0) {
            Log.e(g1, "Please ensure that the value of ratio is greater than 0.0f and less than 1.0f.");
            return;
        }
        smz smzVar = this.E0;
        if (smzVar != null) {
            smzVar.b(f2);
        }
    }

    public void setPageTurningThresholdRatioVertical(float f2) {
        if (Float.compare(f2, 0.0f) < 0 || Float.compare(f2, 1.0f) > 0) {
            Log.e(g1, "Please ensure that the value of ratio is greater than 0.0f and less than 1.0f.");
            return;
        }
        smz smzVar = this.E0;
        if (smzVar != null) {
            smzVar.e(f2);
        }
    }

    public void setReverseDrawingOrder(boolean z) {
        if (this.q0 != 0) {
            return;
        }
        this.D = z;
        if (z) {
            this.o0 = 1;
        } else {
            this.o0 = 2;
        }
    }

    void setScrollState(int i2) {
        if (this.q0 == i2) {
            return;
        }
        this.q0 = i2;
        if (this.m0 != null) {
            b(i2 != 0);
        }
        b(i2);
    }

    public void setScroller(Scroller scroller) {
        if (scroller != null) {
            this.n = scroller;
        }
    }

    public void setSensitivity(float f2) {
        HwGenericEventDetector hwGenericEventDetector = this.L0;
        if (hwGenericEventDetector != null) {
            hwGenericEventDetector.e(f2);
        }
    }

    public void setShadowColor(int i2) {
        this.R0 = i2;
    }

    public void setShadowEnable(boolean z) {
        this.F0 = z;
    }

    public void setSpringInterpolatorEnable(boolean z) {
        this.x0 = z;
        if (z && isDynamicSpringAnimaitionEnabled()) {
            Log.w(g1, "Please call setDynamicSpringAnimaitionEnabled and set false.");
        }
        if (z) {
            return;
        }
        this.n = new Scroller(getContext(), b2);
    }

    public void setSpringInterpolatorEndPos(float f2) {
        this.H0 = f2;
    }

    public void setStartShadowAlpha(float f2) {
        this.w = f2;
    }

    public void setSupportLoop(boolean z) {
        if (this.s0 == z) {
            return;
        }
        this.s0 = z;
        HwPagerAdapter hwPagerAdapter = this.D0;
        if (hwPagerAdapter != null) {
            setAdapter(hwPagerAdapter);
        }
    }

    public void setVelocityRatio(float f2) {
        this.G0 = f2;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.r;
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {

        /* renamed from: a, reason: collision with root package name */
        boolean f10778a;
        public int b;
        float c;
        int d;
        int e;
        public boolean f;

        public LayoutParams() {
            super(-1, -1);
            this.c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, HwViewPager.f1);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public HwViewPager(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.huawei.health.R.attr._2131100604_res_0x7f0603bc);
    }

    private void b(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.orientation, com.huawei.health.R.attr._2131100520_res_0x7f060368, com.huawei.health.R.attr._2131100521_res_0x7f060369, com.huawei.health.R.attr._2131100601_res_0x7f0603b9, com.huawei.health.R.attr._2131100602_res_0x7f0603ba, com.huawei.health.R.attr._2131100603_res_0x7f0603bb, com.huawei.health.R.attr._2131100605_res_0x7f0603bd}, i2, 0);
        this.s0 = obtainStyledAttributes.getBoolean(6, false);
        this.B0 = obtainStyledAttributes.getInt(1, 1);
        this.R0 = obtainStyledAttributes.getColor(2, U1);
        this.O0 = obtainStyledAttributes.getInt(0, 0);
        this.A0 = obtainStyledAttributes.getBoolean(3, false);
        float f2 = obtainStyledAttributes.getFloat(5, 0.125f);
        float f3 = obtainStyledAttributes.getFloat(4, 0.125f);
        obtainStyledAttributes.recycle();
        if (this.A0) {
            smz smzVar = new smz(this);
            this.E0 = smzVar;
            smzVar.e(f2);
            this.E0.b(f3);
        }
    }

    private boolean c(int i2, float f2, int i3) {
        float f3 = this.e1;
        if (f3 >= f2 && i3 < i2) {
            e eVar = this.b1;
            if (eVar == null) {
                return true;
            }
            if (i3 != eVar.b || eVar.e) {
                return false;
            }
            this.f.remove(this.a1);
            this.f10777a.destroyItem(this, i3, this.b1.d);
            int i4 = this.a1 - 1;
            this.a1 = i4;
            this.d1--;
            this.b1 = i4 >= 0 ? this.f.get(i4) : null;
            return false;
        }
        e eVar2 = this.b1;
        if (eVar2 != null && i3 == eVar2.b) {
            this.e1 = f3 + eVar2.c;
            int i5 = this.a1 - 1;
            this.a1 = i5;
            this.b1 = i5 >= 0 ? this.f.get(i5) : null;
            return false;
        }
        e a3 = a(i3, this.a1 + 1);
        this.b1 = a3;
        this.e1 += a3.c;
        this.d1++;
        int i6 = this.a1;
        this.b1 = i6 >= 0 ? this.f.get(i6) : null;
        return false;
    }

    private void d() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            e c2 = c(childAt);
            if (c2 != null && c2.b == this.b && childAt.requestFocus(2)) {
                return;
            }
        }
    }

    private void f(int i2) {
        try {
            Class<?> cls = Class.forName("android.view.SceneUtil");
            Class<?> cls2 = Integer.TYPE;
            cls.getMethod("notifyAnimationState", String.class, cls2, cls2, cls2).invoke(null, "HWVIEWPAGER_SLIDE", Integer.valueOf(i2), -1, Integer.valueOf(hashCode()));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            Log.w(g1, "notifyPageScrollToUifwk class or method not found");
        } catch (IllegalAccessException unused2) {
            Log.w(g1, "notifyPageScrollToUifwk illegal access");
        } catch (IllegalArgumentException unused3) {
            Log.w(g1, "notifyPageScrollToUifwk illegal argument");
        } catch (SecurityException unused4) {
            Log.w(g1, "notifyPageScrollToUifwk not secure");
        } catch (InvocationTargetException unused5) {
            Log.w(g1, "notifyPageScrollToUifwk target error");
        }
        Log.d(g1, "notifyPageScrollToUifwk " + i2 + " done");
    }

    void e(int i2, int i3) {
        b(i2, i3, 0);
    }

    void g() {
        int count = this.f10777a.getCount();
        this.c = count;
        boolean z = this.f.size() < (this.G * 2) + 1 && this.f.size() < count;
        int i2 = this.b;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.f.size()) {
            e eVar = this.f.get(i3);
            int itemPosition = this.f10777a.getItemPosition(eVar.d);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.f.remove(i3);
                    i3--;
                    if (!z2) {
                        this.f10777a.startUpdate(this);
                        z2 = true;
                    }
                    this.f10777a.destroyItem(this, eVar.b, eVar.d);
                    int i4 = this.b;
                    if (i4 == eVar.b) {
                        i2 = Math.max(0, Math.min(i4, count - 1));
                    }
                } else {
                    int i5 = eVar.b;
                    if (i5 != itemPosition) {
                        if (i5 == this.b) {
                            i2 = itemPosition;
                        }
                        eVar.b = itemPosition;
                    }
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.f10777a.finishUpdate(this);
        }
        Collections.sort(this.f, a2);
        a(z, i2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void nextPage(boolean z) {
        nextPage(z, true);
    }

    public void prePage(boolean z) {
        prePage(z, false);
    }

    protected void setCurrentItemInternal(int i2, boolean z, boolean z2, int i3) {
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        if (hwPagerAdapter == null || hwPagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.b == i2 && this.f.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.f10777a.getCount()) {
            i2 = this.f10777a.getCount() - 1;
        }
        int i4 = this.G;
        int i5 = this.b;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.f.size(); i6++) {
                this.f.get(i6).e = true;
            }
        }
        boolean z3 = this.b != i2;
        if (!this.e0) {
            populate(i2);
            a(i2, z, i3, z3);
        } else {
            this.b = i2;
            if (z3) {
                a(i2);
            }
            requestLayout();
        }
    }

    public void setPageTransformer(boolean z, PageTransformer pageTransformer, int i2) {
        boolean z2 = pageTransformer != null;
        boolean z3 = z2 != (this.m0 != null);
        this.m0 = pageTransformer;
        setChildrenDrawingOrderEnabled(z2);
        if (z2) {
            this.o0 = z ? 2 : 1;
            this.D = !z;
            this.n0 = i2;
        } else {
            this.o0 = 0;
        }
        if (z3) {
            o();
        }
    }

    public HwViewPager(Context context, AttributeSet attributeSet, int i2) {
        super(a(context, i2), attributeSet, i2);
        this.d = 2;
        this.e = 1;
        this.f = new ArrayList<>();
        this.g = new e();
        this.h = new Rect();
        this.i = new ArrayMap();
        this.j = new g();
        this.k = -1;
        this.l = null;
        this.m = null;
        this.w = 0.0f;
        this.x = 0.3f;
        this.y = -3.4028235E38f;
        this.z = Float.MAX_VALUE;
        this.G = 2;
        this.Q = 1.4f;
        this.R = -1;
        this.e0 = true;
        this.f0 = false;
        this.q0 = 0;
        this.s0 = false;
        this.t0 = false;
        this.u0 = true;
        this.v0 = 30.0f;
        this.w0 = m1;
        this.x0 = false;
        this.y0 = true;
        this.z0 = true;
        this.A0 = false;
        this.F0 = false;
        this.G0 = 1.0f;
        this.H0 = -1.0f;
        this.I0 = new bng(this, DynamicAnimation.SCROLL_X, new bnf(this.w0, this.v0));
        this.J0 = new DynamicAnimation.OnAnimationEndListener() { // from class: snb
            @Override // com.huawei.dynamicanimation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3) {
                HwViewPager.this.a(dynamicAnimation, z, f2, f3);
            }
        };
        this.K0 = new DynamicAnimation.OnAnimationUpdateListener() { // from class: sni
            @Override // com.huawei.dynamicanimation.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                HwViewPager.this.a(dynamicAnimation, f2, f3);
            }
        };
        this.M0 = true;
        this.N0 = true;
        this.O0 = 0;
        this.S0 = PathInterpolatorCompat.create(0.4f, 0.0f, 0.2f, 1.0f);
        a(getContext(), attributeSet, i2);
    }

    e e(int i2) {
        for (int i3 = 0; i3 < this.f.size(); i3++) {
            e eVar = this.f.get(i3);
            if (eVar.b == i2) {
                return eVar;
            }
        }
        return null;
    }

    public void nextPage(boolean z, boolean z2) {
        int count;
        if (getAdapter() != null && (count = getAdapter().getCount()) >= 2) {
            int currentItem = getCurrentItem();
            if (currentItem != count - 1) {
                setCurrentItem(currentItem + 1, z);
            } else if (z2) {
                setCurrentItem(0, false);
            }
        }
    }

    public void prePage(boolean z, boolean z2) {
        int count;
        if (getAdapter() != null && (count = getAdapter().getCount()) >= 2) {
            int currentItem = getCurrentItem();
            if (currentItem > 0) {
                setCurrentItem(currentItem - 1, z);
            } else if (z2) {
                setCurrentItem(count - 1, false);
            }
        }
    }

    public void setCurrentItem(int i2, boolean z) {
        if (this.N0) {
            i2 = a(i2, this.f10777a);
        }
        this.F = false;
        setCurrentItemInternal(i2, z, false);
    }

    public static class RtlSavedState implements Parcelable {
        public static final Parcelable.ClassLoaderCreator<RtlSavedState> CREATOR = new e();

        /* renamed from: a, reason: collision with root package name */
        Parcelable f10779a;
        int b;
        boolean c;

        class e implements Parcelable.ClassLoaderCreator<RtlSavedState> {
            e() {
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: eiF_, reason: merged with bridge method [inline-methods] */
            public RtlSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new RtlSavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: eiE_, reason: merged with bridge method [inline-methods] */
            public RtlSavedState createFromParcel(Parcel parcel) {
                return new RtlSavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public RtlSavedState[] newArray(int i) {
                return new RtlSavedState[i];
            }
        }

        public RtlSavedState(Parcelable parcelable, int i, boolean z) {
            this.f10779a = parcelable;
            this.b = i;
            this.c = z;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel == null) {
                return;
            }
            parcel.writeParcelable(this.f10779a, i);
            parcel.writeInt(this.b);
            parcel.writeByte(this.c ? (byte) 1 : (byte) 0);
        }

        RtlSavedState(Parcel parcel, ClassLoader classLoader) {
            this.f10779a = parcel.readParcelable(classLoader == null ? getClass().getClassLoader() : classLoader);
            this.b = parcel.readInt();
            this.c = parcel.readByte() != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3) {
        if (!z) {
            boolean isPageScrollHorizontal = isPageScrollHorizontal();
            if (Float.compare(isPageScrollHorizontal ? getScrollX() : getScrollY(), f2) != 0) {
                scrollTo(isPageScrollHorizontal ? (int) f2 : 0, isPageScrollHorizontal ? 0 : (int) f2);
            }
        }
        if (this.q0 != 0) {
            a(false);
        }
        f(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            r10 = this;
            int r0 = r10.getScrollX()
            int r1 = r10.getPaddingLeft()
            int r2 = r10.getPaddingRight()
            int r3 = r10.getWidth()
            int r4 = r10.getChildCount()
            r5 = 0
        L15:
            if (r5 >= r4) goto L72
            android.view.View r6 = r10.getChildAt(r5)
            if (r6 != 0) goto L1e
            goto L6f
        L1e:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            boolean r7 = r7 instanceof com.huawei.uikit.hwviewpager.widget.HwViewPager.LayoutParams
            if (r7 != 0) goto L27
            goto L6f
        L27:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            com.huawei.uikit.hwviewpager.widget.HwViewPager$LayoutParams r7 = (com.huawei.uikit.hwviewpager.widget.HwViewPager.LayoutParams) r7
            boolean r8 = r7.f
            if (r8 != 0) goto L32
            goto L6f
        L32:
            int r7 = r7.b
            r7 = r7 & 7
            r8 = 1
            if (r7 == r8) goto L54
            r8 = 3
            if (r7 == r8) goto L4e
            r8 = 5
            if (r7 == r8) goto L41
            r7 = r1
            goto L63
        L41:
            int r7 = r3 - r2
            int r8 = r6.getMeasuredWidth()
            int r7 = r7 - r8
            int r8 = r6.getMeasuredWidth()
            int r2 = r2 + r8
            goto L60
        L4e:
            int r7 = r6.getWidth()
            int r7 = r7 + r1
            goto L63
        L54:
            int r7 = r6.getMeasuredWidth()
            int r7 = r3 - r7
            int r7 = r7 / 2
            int r7 = java.lang.Math.max(r7, r1)
        L60:
            r9 = r7
            r7 = r1
            r1 = r9
        L63:
            int r1 = r1 + r0
            int r8 = r6.getLeft()
            int r1 = r1 - r8
            if (r1 == 0) goto L6e
            r6.offsetLeftAndRight(r1)
        L6e:
            r1 = r7
        L6f:
            int r5 = r5 + 1
            goto L15
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.e():void");
    }

    private boolean h(MotionEvent motionEvent) {
        if (this.R == -1) {
            Log.w(g1, "onTouchEvent: something wrong! we get invalid pointer!");
            return false;
        }
        VelocityTracker velocityTracker = this.S;
        velocityTracker.computeCurrentVelocity(1000, this.U);
        int yVelocity = (int) velocityTracker.getYVelocity(this.R);
        this.F = true;
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        if (infoForCurrentScrollPosition == null) {
            return false;
        }
        float f2 = 0.0f;
        float f3 = clientHeight <= 0 ? 0.0f : this.q / clientHeight;
        int i2 = infoForCurrentScrollPosition.b;
        if (clientHeight > 0 && infoForCurrentScrollPosition.c + f3 > 0.0f) {
            f2 = ((scrollY / clientHeight) - infoForCurrentScrollPosition.f10781a) / (infoForCurrentScrollPosition.c + f3);
        }
        setCurrentItemInternal(determineTargetPage(i2, f2, yVelocity, (int) (motionEvent.getY(motionEvent.findPointerIndex(this.R)) - this.P)), true, true, yVelocity);
        return true;
    }

    private static boolean d(View view) {
        return view.getClass().getAnnotation(DecorView.class) != null;
    }

    private void i(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.R) {
            int i2 = actionIndex == 0 ? 1 : 0;
            f(motionEvent, i2);
            this.R = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.S;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(ContextCompat.getDrawable(getContext(), i2));
    }

    private void d(int i2, int i3) {
        if (isPageScrollHorizontal()) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int paddingLeft2 = (i3 - getPaddingLeft()) - getPaddingRight();
            scrollTo((int) ((paddingLeft2 > 0 ? getScrollX() / paddingLeft2 : 0.0f) * ((i2 - paddingLeft) - paddingRight)), getScrollY());
            return;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingTop2 = (i3 - getPaddingTop()) - getPaddingBottom();
        scrollTo(getScrollX(), (int) ((paddingTop2 > 0 ? getScrollY() / paddingTop2 : 0.0f) * ((i2 - paddingTop) - paddingBottom)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DynamicAnimation dynamicAnimation, float f2, float f3) {
        if (this.q0 == 2) {
            pageScrolled((int) f2);
        }
    }

    private static Context a(Context context, int i2) {
        return smr.b(context, i2, com.huawei.health.R.style.Theme_Emui_HwViewPager);
    }

    private void a(Context context, AttributeSet attributeSet, int i2) {
        b(context, attributeSet, i2);
        j();
        HwGenericEventDetector createGenericEventDetector = createGenericEventDetector();
        this.L0 = createGenericEventDetector;
        if (createGenericEventDetector != null) {
            setSensitivityMode(this.B0);
            this.L0.e(this.Q);
            this.L0.eit_(this, createOnScrollListener());
            this.L0.b(createOnChangePageListener());
        }
        setValueFromPlume(context);
    }

    void b(int i2, int i3, int i4) {
        int abs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int newAnimationScrollX = getNewAnimationScrollX();
        int newAnimationScrollY = getNewAnimationScrollY();
        int i5 = i2 - newAnimationScrollX;
        int i6 = i3 - newAnimationScrollY;
        if (i5 == 0 && i6 == 0) {
            a(false);
            o();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = isPageScrollHorizontal() ? getClientWidth() : getClientHeight();
        float f2 = clientWidth / 2;
        float a3 = a(clientWidth <= 0 ? 0.0f : Math.min(1.0f, (Math.abs(i5) * 1.0f) / clientWidth));
        if (this.u0) {
            if (!isPageScrollHorizontal()) {
                i2 = i3;
            }
            f(i2, -i4);
            f(1);
            return;
        }
        if (this.x0) {
            a(-i4, new Pair<>(Integer.valueOf(newAnimationScrollX), Integer.valueOf(newAnimationScrollY)), new Pair<>(Integer.valueOf(i5), Integer.valueOf(i6)));
            return;
        }
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            abs = Math.round(Math.abs((f2 + (a3 * f2)) / abs2) * 1000.0f) * 4;
        } else {
            float pageWidth = clientWidth * (isPageScrollHorizontal() ? this.f10777a.getPageWidth(this.b) : this.f10777a.getPageHeight(this.b));
            abs = (int) (((Float.compare(((float) this.q) + pageWidth, 0.0f) != 0 ? Math.abs(i5) / (pageWidth + this.q) : 0.0f) + 1.0f) * 100.0f);
        }
        int min = Math.min(abs, 600);
        this.o = false;
        this.n.startScroll(newAnimationScrollX, newAnimationScrollY, i5, i6, min);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean d(MotionEvent motionEvent, int i2) {
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float x = motionEvent.getX(findPointerIndex);
        float f2 = x - this.M;
        float abs = Math.abs(f2);
        float y = motionEvent.getY(findPointerIndex);
        float abs2 = Math.abs(y - this.P);
        if (f2 != 0.0f && !b(this.M, f2) && canScroll(this, false, (int) f2, (int) x, (int) y)) {
            this.M = x;
            this.N = y;
            this.I = true;
            return false;
        }
        float f3 = this.L;
        if (abs > f3 && abs > abs2) {
            this.H = true;
            c(true);
            setScrollState(1);
            this.M = f2 > 0.0f ? this.O + this.L : this.O - this.L;
            this.N = y;
            setScrollingCacheEnabled(true);
        } else if (abs2 > f3) {
            this.I = true;
        }
        if (this.H && !this.A0 && performDrag(this.M - x)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    private void c() {
        e c2;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? (LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.d = i2;
                if (!layoutParams2.f && layoutParams2.c == 0.0f && (c2 = c(childAt)) != null) {
                    layoutParams2.c = c2.c;
                    layoutParams2.e = c2.b;
                }
            }
        }
    }

    private void h() {
        this.H = false;
        this.I = false;
        VelocityTracker velocityTracker = this.S;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.S = null;
        }
    }

    private void f(int i2, int i3) {
        this.I0.e().e(this, isPageScrollHorizontal() ? DynamicAnimation.SCROLL_X : DynamicAnimation.SCROLL_Y, this.w0, this.v0, i2, i3 * this.G0);
        t();
        this.o = false;
        this.I0.startImmediately();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean a(float f2, MotionEvent motionEvent) {
        if (a(f2, 0.0f) || this.q0 != 0) {
            return false;
        }
        if (f2 > 0.0f) {
            n();
            return true;
        }
        m();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f() {
        /*
            r10 = this;
            int r0 = r10.getScrollY()
            int r1 = r10.getPaddingTop()
            int r2 = r10.getPaddingBottom()
            int r3 = r10.getHeight()
            int r4 = r10.getChildCount()
            r5 = 0
        L15:
            if (r5 >= r4) goto L75
            android.view.View r6 = r10.getChildAt(r5)
            if (r6 != 0) goto L1e
            goto L72
        L1e:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            boolean r7 = r7 instanceof com.huawei.uikit.hwviewpager.widget.HwViewPager.LayoutParams
            if (r7 != 0) goto L27
            goto L72
        L27:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            com.huawei.uikit.hwviewpager.widget.HwViewPager$LayoutParams r7 = (com.huawei.uikit.hwviewpager.widget.HwViewPager.LayoutParams) r7
            boolean r8 = r7.f
            if (r8 != 0) goto L32
            goto L72
        L32:
            int r7 = r7.b
            r7 = r7 & 112(0x70, float:1.57E-43)
            r8 = 16
            if (r7 == r8) goto L57
            r8 = 48
            if (r7 == r8) goto L51
            r8 = 80
            if (r7 == r8) goto L44
            r7 = r1
            goto L66
        L44:
            int r7 = r3 - r2
            int r8 = r6.getMeasuredHeight()
            int r7 = r7 - r8
            int r8 = r6.getMeasuredHeight()
            int r2 = r2 + r8
            goto L63
        L51:
            int r7 = r6.getHeight()
            int r7 = r7 + r1
            goto L66
        L57:
            int r7 = r6.getMeasuredHeight()
            int r7 = r3 - r7
            int r7 = r7 / 2
            int r7 = java.lang.Math.max(r7, r1)
        L63:
            r9 = r7
            r7 = r1
            r1 = r9
        L66:
            int r1 = r1 + r0
            int r8 = r6.getTop()
            int r1 = r1 - r8
            if (r1 == 0) goto L71
            r6.offsetTopAndBottom(r1)
        L71:
            r1 = r7
        L72:
            int r5 = r5 + 1
            goto L15
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.f():void");
    }

    private boolean a(float f2, float f3) {
        return Math.abs(f2 - f3) < B1;
    }

    private void a(int i2, boolean z, int i3, boolean z2) {
        int h2 = h(i2);
        if (!this.A0 && z) {
            if (isPageScrollHorizontal()) {
                if (isRtlLayout()) {
                    h2 = -h2;
                }
                b(h2, 0, i3);
            } else {
                b(0, h2, i3);
            }
            if (z2) {
                a(i2);
                return;
            }
            return;
        }
        if (z2) {
            a(i2);
        }
        a(false);
        if (isPageScrollHorizontal()) {
            if (isRtlLayout()) {
                h2 = -h2;
            }
            scrollTo(h2, 0);
        } else {
            scrollTo(0, h2);
        }
        pageScrolled(h2);
    }

    e c(View view) {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            e eVar = this.f.get(i2);
            if (this.f10777a.isViewFromObject(view, eVar.d)) {
                return eVar;
            }
        }
        return null;
    }

    private void c(int i2, int i3) {
        bng bngVar;
        if (i3 > 0 && !this.f.isEmpty()) {
            if (this.u0 && (bngVar = this.I0) != null && bngVar.isRunning()) {
                q();
                return;
            } else if (!this.n.isFinished()) {
                p();
                return;
            } else {
                d(i2, i3);
                return;
            }
        }
        g(i2);
    }

    private void g(int i2) {
        int paddingTop;
        int paddingBottom;
        e e2 = e(this.b);
        float min = e2 != null ? Math.min(e2.f10781a, this.z) : 0.0f;
        if (isPageScrollHorizontal() && isRtlLayout()) {
            min = -min;
        }
        if (isPageScrollHorizontal()) {
            paddingTop = i2 - getPaddingLeft();
            paddingBottom = getPaddingRight();
        } else {
            paddingTop = i2 - getPaddingTop();
            paddingBottom = getPaddingBottom();
        }
        int i3 = (int) (min * (paddingTop - paddingBottom));
        if (isPageScrollHorizontal()) {
            if (i3 == getScrollX()) {
                return;
            }
        } else if (i3 == getScrollY()) {
            return;
        }
        a(false);
        if (isPageScrollHorizontal()) {
            scrollTo(i3, getScrollY());
        } else {
            scrollTo(getScrollX(), i3);
        }
    }

    private boolean e(MotionEvent motionEvent, int i2) {
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float y = motionEvent.getY(findPointerIndex);
        float f2 = y - this.N;
        float abs = Math.abs(f2);
        float x = motionEvent.getX(findPointerIndex);
        float abs2 = Math.abs(x - this.O);
        if (f2 != 0.0f && !b(this.N, f2) && canScroll(this, false, (int) f2, (int) x, (int) y)) {
            this.M = x;
            this.N = y;
            this.I = true;
            return false;
        }
        float f3 = this.L;
        if (abs > f3 && abs > abs2) {
            this.H = true;
            c(true);
            setScrollState(1);
            this.M = x;
            this.N = f2 > 0.0f ? this.P + this.L : this.P - this.L;
            setScrollingCacheEnabled(true);
        } else if (abs2 > f3) {
            this.I = true;
        }
        if (this.H && !this.A0 && performDrag(this.N - y)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    private boolean c(MotionEvent motionEvent, int i2) {
        if (i2 == 0) {
            if (this.A0) {
                this.E0.a(motionEvent);
            }
            if (this.F0) {
                y();
            }
            float x = motionEvent.getX();
            this.O = x;
            this.M = x;
            float y = motionEvent.getY();
            this.P = y;
            this.N = y;
            this.R = motionEvent.getPointerId(0);
            this.I = false;
            this.o = true;
            this.n.computeScrollOffset();
            v();
        } else if (i2 == 2) {
            int i3 = this.R;
            if (i3 != -1) {
                if (isPageScrollHorizontal()) {
                    if (!d(motionEvent, i3)) {
                        return true;
                    }
                } else if (!e(motionEvent, i3)) {
                    return true;
                }
            }
        } else if (i2 == 6) {
            i(motionEvent);
        }
        return false;
    }

    OnPageChangeListener a(OnPageChangeListener onPageChangeListener) {
        OnPageChangeListener onPageChangeListener2 = this.k0;
        this.k0 = onPageChangeListener;
        return onPageChangeListener2;
    }

    private boolean g(MotionEvent motionEvent) {
        if (this.R == -1) {
            Log.w(g1, "onTouchEvent: something wrong! we get invalid pointer!");
            return false;
        }
        VelocityTracker velocityTracker = this.S;
        velocityTracker.computeCurrentVelocity(1000, this.U);
        int xVelocity = (int) velocityTracker.getXVelocity(this.R);
        this.F = true;
        int clientWidth = getClientWidth();
        int scrollX = isRtlLayout() ? -getScrollX() : getScrollX();
        e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        if (infoForCurrentScrollPosition == null) {
            return false;
        }
        float f2 = 0.0f;
        float f3 = clientWidth <= 0 ? 0.0f : this.q / clientWidth;
        int i2 = infoForCurrentScrollPosition.b;
        if (clientWidth > 0 && infoForCurrentScrollPosition.c + f3 > 0.0f) {
            f2 = ((scrollX / clientWidth) - infoForCurrentScrollPosition.f10781a) / (infoForCurrentScrollPosition.c + f3);
        }
        setCurrentItemInternal(determineTargetPage(i2, f2, xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.R)) - this.O)), true, true, xVelocity);
        return true;
    }

    float a(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    private void a(int i2, Pair<Integer, Integer> pair, Pair<Integer, Integer> pair2) {
        float intValue = ((Integer) (isPageScrollHorizontal() ? pair2.first : pair2.second)).intValue();
        if (Float.compare(this.H0, 0.0f) > 0) {
            Log.d(g1, "startDynamicAnimationWithSpringInterpolator: use fix endPos " + this.H0);
            if (Math.abs(intValue) < this.H0) {
                intValue = Math.signum(intValue) * this.H0;
            }
        }
        bno bnoVar = new bno(this.w0, this.v0, intValue, i2 * this.G0);
        int duration = (int) bnoVar.getDuration();
        Scroller scroller = new Scroller(getContext(), bnoVar);
        this.n = scroller;
        this.o = false;
        scroller.startScroll(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue(), ((Integer) pair2.first).intValue(), ((Integer) pair2.second).intValue(), duration);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void d(MotionEvent motionEvent) {
        i(motionEvent);
        if (this.R == -1) {
            Log.w(g1, "onTouchEvent: something wrong! we get invalid pointer!");
            return;
        }
        try {
            if (isPageScrollHorizontal()) {
                this.M = motionEvent.getX(motionEvent.findPointerIndex(this.R));
            } else {
                this.N = motionEvent.getY(motionEvent.findPointerIndex(this.R));
            }
        } catch (IllegalArgumentException unused) {
            Log.e(g1, "onTouchEvent: pointer index out of range");
        }
    }

    private boolean b(int i2, float f2, int i3) {
        float f3 = this.c1;
        if (f3 >= f2 && i3 > i2) {
            e eVar = this.b1;
            if (eVar == null) {
                return true;
            }
            if (i3 != eVar.b || eVar.e) {
                return false;
            }
            this.f.remove(this.a1);
            this.f10777a.destroyItem(this, i3, this.b1.d);
            this.b1 = this.a1 < this.f.size() ? this.f.get(this.a1) : null;
            return false;
        }
        e eVar2 = this.b1;
        if (eVar2 != null && i3 == eVar2.b) {
            this.c1 = f3 + eVar2.c;
            int i4 = this.a1 + 1;
            this.a1 = i4;
            this.b1 = i4 < this.f.size() ? this.f.get(this.a1) : null;
            return false;
        }
        e a3 = a(i3, this.a1);
        this.b1 = a3;
        int i5 = this.a1 + 1;
        this.a1 = i5;
        this.c1 += a3.c;
        this.b1 = i5 < this.f.size() ? this.f.get(this.a1) : null;
        return false;
    }

    e a(int i2, int i3) {
        e eVar = new e();
        eVar.b = i2;
        eVar.d = this.f10777a.instantiateItem(this, i2);
        eVar.c = isPageScrollHorizontal() ? this.f10777a.getPageWidth(i2) : this.f10777a.getPageHeight(i2);
        if (i3 >= 0 && i3 < this.f.size()) {
            this.f.add(i3, eVar);
        } else {
            this.f.add(eVar);
        }
        return eVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(float r14) {
        /*
            Method dump skipped, instructions count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.d(float):boolean");
    }

    private boolean f(MotionEvent motionEvent) {
        this.n.abortAnimation();
        cancelAnimation();
        this.F = false;
        o();
        try {
            float x = motionEvent.getX();
            this.O = x;
            this.M = x;
            float y = motionEvent.getY();
            this.P = y;
            this.N = y;
            this.R = motionEvent.getPointerId(0);
            return false;
        } catch (IllegalArgumentException unused) {
            Log.e(g1, "onTouchEvent: pointer index out of range");
            return true;
        }
    }

    private void a(boolean z, int i2) {
        if (z) {
            setChildCount(getChildCount());
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    private boolean c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        try {
            if (isPageScrollHorizontal()) {
                this.M = motionEvent.getX(actionIndex);
            } else {
                this.N = motionEvent.getY(actionIndex);
            }
            this.R = motionEvent.getPointerId(actionIndex);
            return false;
        } catch (IllegalArgumentException unused) {
            Log.e(g1, "onTouchEvent: pointer index out of range");
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
    
        if (r2 == r3) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.uikit.hwviewpager.widget.HwViewPager.e r8) {
        /*
            r7 = this;
            com.huawei.uikit.hwviewpager.widget.HwPagerAdapter r0 = r7.f10777a
            int r4 = r0.getCount()
            int r0 = r7.c
            if (r4 != r0) goto L59
            r0 = 0
            r7.d1 = r0
        Ld:
            int r1 = r7.d1
            java.util.ArrayList<com.huawei.uikit.hwviewpager.widget.HwViewPager$e> r2 = r7.f
            int r2 = r2.size()
            if (r1 >= r2) goto L31
            java.util.ArrayList<com.huawei.uikit.hwviewpager.widget.HwViewPager$e> r1 = r7.f
            int r2 = r7.d1
            java.lang.Object r1 = r1.get(r2)
            com.huawei.uikit.hwviewpager.widget.HwViewPager$e r1 = (com.huawei.uikit.hwviewpager.widget.HwViewPager.e) r1
            int r2 = r1.b
            int r3 = r7.b
            if (r2 < r3) goto L2a
            if (r2 != r3) goto L31
            goto L32
        L2a:
            int r1 = r7.d1
            int r1 = r1 + 1
            r7.d1 = r1
            goto Ld
        L31:
            r1 = 0
        L32:
            if (r1 != 0) goto L3e
            if (r4 <= 0) goto L3e
            int r1 = r7.b
            int r2 = r7.d1
            com.huawei.uikit.hwviewpager.widget.HwViewPager$e r1 = r7.a(r1, r2)
        L3e:
            r6 = r1
            int r1 = r7.G
            int r2 = r7.b
            int r2 = r2 - r1
            int r3 = java.lang.Math.max(r0, r2)
            int r0 = r4 + (-1)
            int r2 = r7.b
            int r2 = r2 + r1
            int r5 = java.lang.Math.min(r0, r2)
            if (r6 == 0) goto L58
            r1 = r7
            r2 = r8
            r1.a(r2, r3, r4, r5, r6)
        L58:
            return
        L59:
            android.content.res.Resources r8 = r7.getResources()     // Catch: android.content.res.Resources.NotFoundException -> L66
            int r0 = r7.getId()     // Catch: android.content.res.Resources.NotFoundException -> L66
            java.lang.String r8 = r8.getResourceName(r0)     // Catch: android.content.res.Resources.NotFoundException -> L66
            goto L6e
        L66:
            int r8 = r7.getId()
            java.lang.String r8 = java.lang.Integer.toHexString(r8)
        L6e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The application's HwPagerAdapter changed the adapter's contents without calling HwPagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            r0.<init>(r1)
            int r1 = r7.c
            r0.append(r1)
            java.lang.String r1 = ", found: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = " Pager id: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = " Pager class: "
            r0.append(r8)
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.Class r1 = r7.getClass()
            r0.append(r1)
            java.lang.String r1 = " Problematic adapter: "
            r0.append(r1)
            com.huawei.uikit.hwviewpager.widget.HwPagerAdapter r1 = r7.f10777a
            java.lang.Class r1 = r1.getClass()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.a(com.huawei.uikit.hwviewpager.widget.HwViewPager$e):void");
    }

    private void f(MotionEvent motionEvent, int i2) {
        if (isPageScrollHorizontal()) {
            this.M = motionEvent.getX(i2);
        } else {
            this.N = motionEvent.getY(i2);
        }
    }

    private boolean e(MotionEvent motionEvent) {
        if (this.A0) {
            this.E0.eiC_(motionEvent);
            setScrollState(0);
            return true;
        }
        if (this.F0 && this.C0 != null) {
            z();
        }
        this.Z0 = a(motionEvent, this.Z0);
        return false;
    }

    private void b(e eVar, e eVar2, float f2, int i2) {
        e eVar3;
        float f3 = eVar2.f10781a + eVar2.c + f2;
        int i3 = i2 + 1;
        int i4 = 0;
        while (i3 <= eVar.b && i4 < this.f.size()) {
            e eVar4 = this.f.get(i4);
            while (true) {
                eVar3 = eVar4;
                if (i3 <= eVar3.b || i4 >= this.f.size() - 1) {
                    break;
                }
                i4++;
                eVar4 = this.f.get(i4);
            }
            while (i3 < eVar3.b) {
                f3 += (isPageScrollHorizontal() ? this.f10777a.getPageWidth(i3) : this.f10777a.getPageHeight(i3)) + f2;
                i3++;
            }
            eVar3.f10781a = f3;
            f3 += eVar3.c + f2;
            i3++;
        }
    }

    private boolean e(float f2) {
        boolean z;
        boolean z2;
        float a3;
        this.N -= f2;
        float scrollY = getScrollY();
        float f3 = scrollY + f2;
        int clientHeight = getClientHeight();
        float f4 = clientHeight;
        float f5 = this.y * f4;
        float f6 = this.z * f4;
        boolean z3 = false;
        if (this.f.size() <= 0) {
            return false;
        }
        e eVar = this.f.get(0);
        ArrayList<e> arrayList = this.f;
        e eVar2 = arrayList.get(arrayList.size() - 1);
        if (eVar.b != 0) {
            f5 = eVar.f10781a * f4;
            z = false;
        } else {
            z = true;
        }
        if (eVar2.b != this.f10777a.getCount() - 1) {
            f6 = eVar2.f10781a * f4;
            z2 = false;
        } else {
            z2 = true;
        }
        if (f3 < f5) {
            if (z) {
                this.c0.onPull(clientHeight > 0 ? Math.abs(f5 - f3) / f4 : 0.0f);
                z3 = true;
            }
            if ((this.u0 || this.x0) && z) {
                a3 = a(clientHeight, f2, f3 - f5);
                f3 = scrollY + a3;
            } else {
                f3 = f5;
            }
        } else if (f3 > f6) {
            if (z2) {
                this.d0.onPull(clientHeight > 0 ? Math.abs(f3 - f6) / f4 : 0.0f);
                z3 = true;
            }
            if ((this.u0 || this.x0) && z2) {
                a3 = a(clientHeight, f2, f3 - f6);
                f3 = scrollY + a3;
            } else {
                f3 = f6;
            }
        }
        c(f3);
        return z3;
    }

    private void c(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void c(float f2) {
        int i2 = (int) f2;
        this.N += f2 - i2;
        scrollTo(getScrollX(), i2);
        pageScrolled(i2);
    }

    private void c(int i2) {
        int clientWidth = getClientWidth();
        int scrollX = isRtlLayout() ? -getScrollX() : getScrollX();
        e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        if (infoForCurrentScrollPosition != null) {
            int i3 = infoForCurrentScrollPosition.b;
            float f2 = 0.0f;
            if (clientWidth > 0 && infoForCurrentScrollPosition.c > 0.0f) {
                f2 = ((scrollX / clientWidth) - infoForCurrentScrollPosition.f10781a) / infoForCurrentScrollPosition.c;
            }
            setCurrentItemInternal(determineTargetPage(i3, f2, i2, (int) (this.M - this.O)), true, true, i2);
        }
    }

    e b(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent != this) {
                if (parent == null || !(parent instanceof View)) {
                    return null;
                }
                view = (View) parent;
            } else {
                return c(view);
            }
        }
    }

    private void b(int i2, int i3, int i4, Rect rect) {
        View childAt = getChildAt(i2);
        if (childAt == null) {
            return;
        }
        int i5 = rect.left;
        int i6 = rect.right;
        int i7 = rect.top;
        if (childAt.getVisibility() == 8 || !(childAt.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
        e c2 = c(childAt);
        if (layoutParams.f || c2 == null) {
            return;
        }
        float f2 = i3;
        int i8 = i7 + ((int) (c2.f10781a * f2));
        if (layoutParams.f10778a) {
            layoutParams.f10778a = false;
            childAt.measure(View.MeasureSpec.makeMeasureSpec((i4 - i5) - i6, 1073741824), View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams.c), 1073741824));
        }
        childAt.layout(i5, i8, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight() + i8);
    }

    private void d(int i2) {
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        e infoForCurrentScrollPosition = infoForCurrentScrollPosition();
        if (infoForCurrentScrollPosition != null) {
            int i3 = infoForCurrentScrollPosition.b;
            float f2 = 0.0f;
            if (clientHeight > 0 && infoForCurrentScrollPosition.c > 0.0f) {
                f2 = ((scrollY / clientHeight) - infoForCurrentScrollPosition.f10781a) / infoForCurrentScrollPosition.c;
            }
            setCurrentItemInternal(determineTargetPage(i3, f2, i2, (int) (this.N - this.P)), true, true, i2);
        }
    }

    private void a(e eVar, int i2, int i3, int i4, e eVar2) {
        float f2;
        int paddingTop;
        float f3;
        int paddingBottom;
        float f4 = 0.0f;
        this.e1 = 0.0f;
        int i5 = this.d1 - 1;
        this.a1 = i5;
        this.b1 = i5 >= 0 ? this.f.get(i5) : null;
        int clientWidth = isPageScrollHorizontal() ? getClientWidth() : getClientHeight();
        if (clientWidth <= 0) {
            f3 = 0.0f;
        } else {
            if (isPageScrollHorizontal()) {
                f2 = 2.0f - eVar2.c;
                paddingTop = getPaddingLeft();
            } else {
                f2 = 2.0f - eVar2.c;
                paddingTop = getPaddingTop();
            }
            f3 = (f2 + paddingTop) / clientWidth;
        }
        for (int i6 = this.b - 1; i6 >= 0 && !c(i2, f3, i6); i6--) {
        }
        float f5 = eVar2.c;
        this.c1 = f5;
        int i7 = this.d1 + 1;
        this.a1 = i7;
        if (f5 < 2.0f) {
            this.b1 = i7 < this.f.size() ? this.f.get(this.a1) : null;
            if (clientWidth > 0) {
                if (isPageScrollHorizontal()) {
                    paddingBottom = getPaddingRight();
                } else {
                    paddingBottom = getPaddingBottom();
                }
                f4 = (paddingBottom / clientWidth) + 2.0f;
            }
            int i8 = this.b;
            do {
                i8++;
                if (i8 >= i3) {
                    break;
                }
            } while (!b(i4, f4, i8));
        }
        a(eVar2, this.d1, eVar);
        this.f10777a.setPrimaryItem(this, this.b, eVar2.d);
    }

    private void b(int i2, int i3) {
        if (this.Q0 == null) {
            ViewGroupOverlay a3 = a((View) this);
            this.P0 = a3;
            if (a3 == null) {
                return;
            }
            this.Q0 = new snl(this.R0, this);
            setLayerType(1, null);
            this.P0.add(this.Q0);
        }
        Drawable drawable = this.Q0;
        if (drawable instanceof snl) {
            ((snl) drawable).c(i2, i3);
        }
    }

    private void b(int i2) {
        OnPageChangeListener onPageChangeListener = this.j0;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i2);
        }
        List<OnPageChangeListener> list = this.i0;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener2 = this.i0.get(i3);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageScrollStateChanged(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.k0;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrollStateChanged(i2);
        }
    }

    private void a(e eVar, int i2, e eVar2) {
        int i3;
        int i4;
        float pageHeight;
        int i5;
        int i6;
        float pageHeight2;
        int count = this.f10777a.getCount();
        int clientWidth = isPageScrollHorizontal() ? getClientWidth() : getClientHeight();
        float f2 = clientWidth > 0 ? this.q / clientWidth : 0.0f;
        if (eVar2 != null) {
            int i7 = eVar2.b;
            int i8 = eVar.b;
            if (i7 < i8) {
                b(eVar, eVar2, f2, i7);
            } else if (i7 > i8) {
                a(eVar, eVar2, f2, i7);
            }
        }
        int size = this.f.size();
        float f3 = eVar.f10781a;
        int i9 = eVar.b;
        int i10 = i9 - 1;
        this.y = i9 == 0 ? f3 : -3.4028235E38f;
        int i11 = count - 1;
        this.z = i9 == i11 ? (eVar.c + f3) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            e eVar3 = this.f.get(i12);
            while (true) {
                i5 = eVar3.b;
                if (i10 <= i5) {
                    break;
                }
                if (isPageScrollHorizontal()) {
                    i6 = i10 - 1;
                    pageHeight2 = this.f10777a.getPageWidth(i10);
                } else {
                    i6 = i10 - 1;
                    pageHeight2 = this.f10777a.getPageHeight(i10);
                }
                f3 -= pageHeight2 + f2;
                i10 = i6;
            }
            f3 -= eVar3.c + f2;
            eVar3.f10781a = f3;
            if (i5 == 0) {
                this.y = f3;
            }
            i12--;
            i10--;
        }
        float f4 = eVar.f10781a + eVar.c + f2;
        int i13 = eVar.b + 1;
        int i14 = i2 + 1;
        while (i14 < size) {
            e eVar4 = this.f.get(i14);
            while (true) {
                i3 = eVar4.b;
                if (i13 >= i3) {
                    break;
                }
                if (isPageScrollHorizontal()) {
                    i4 = i13 + 1;
                    pageHeight = this.f10777a.getPageWidth(i13);
                } else {
                    i4 = i13 + 1;
                    pageHeight = this.f10777a.getPageHeight(i13);
                }
                f4 += pageHeight + f2;
                i13 = i4;
            }
            if (i3 == i11) {
                this.z = (eVar4.c + f4) - 1.0f;
            }
            eVar4.f10781a = f4;
            f4 += eVar4.c + f2;
            i14++;
            i13++;
        }
        this.f0 = false;
    }

    private boolean b(float f2, float f3) {
        return isPageScrollHorizontal() ? (f2 < ((float) this.K) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.K)) && f3 < 0.0f) : (f2 < ((float) this.K) && f3 > 0.0f) || (f2 > ((float) (getHeight() - this.K)) && f3 < 0.0f);
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).setLayerType(z ? this.n0 : 0, null);
        }
    }

    private void b(MotionEvent motionEvent) {
        if (!this.H) {
            int i2 = this.R;
            if (i2 == -1) {
                Log.w(g1, "onTouchEvent: something wrong! we get invalid pointer!");
                this.Z0 = u();
                return;
            }
            int findPointerIndex = motionEvent.findPointerIndex(i2);
            if (findPointerIndex == -1) {
                this.Z0 = u();
                return;
            } else if (isPageScrollHorizontal()) {
                a(motionEvent, findPointerIndex);
            } else {
                b(motionEvent, findPointerIndex);
            }
        }
        if (this.H) {
            int i3 = this.R;
            if (i3 == -1) {
                Log.w(g1, "onTouchEvent: something wrong! we get invalid pointer!");
                return;
            }
            try {
                int findPointerIndex2 = motionEvent.findPointerIndex(i3);
                float x = isPageScrollHorizontal() ? motionEvent.getX(findPointerIndex2) : motionEvent.getY(findPointerIndex2);
                this.Z0 = performDrag((isPageScrollHorizontal() ? this.M : this.N) - x) | this.Z0;
            } catch (IllegalArgumentException unused) {
                Log.e(g1, "onTouchEvent: pointer index out of range");
            }
        }
    }

    private void b(MotionEvent motionEvent, int i2) {
        float x = motionEvent.getX(i2);
        float abs = Math.abs(x - this.M);
        float y = motionEvent.getY(i2);
        float abs2 = Math.abs(y - this.N);
        if (abs2 <= this.L || abs2 <= abs) {
            return;
        }
        this.H = true;
        c(true);
        this.M = x;
        float f2 = this.P;
        this.N = y - f2 > 0.0f ? f2 + this.L : f2 - this.L;
        setScrollState(1);
        setScrollingCacheEnabled(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void a(e eVar, e eVar2, float f2, int i2) {
        e eVar3;
        int size = this.f.size() - 1;
        float f3 = eVar2.f10781a;
        while (true) {
            i2--;
            if (i2 < eVar.b || size < 0) {
                return;
            }
            e eVar4 = this.f.get(size);
            while (true) {
                eVar3 = eVar4;
                if (i2 >= eVar3.b || size <= 0) {
                    break;
                }
                size--;
                eVar4 = this.f.get(size);
            }
            while (i2 > eVar3.b) {
                f3 -= (isPageScrollHorizontal() ? this.f10777a.getPageWidth(i2) : this.f10777a.getPageHeight(i2)) + f2;
                i2--;
            }
            f3 -= eVar3.c + f2;
            eVar3.f10781a = f3;
        }
    }

    private void a(View view, LayoutParams layoutParams) {
        int i2;
        if (layoutParams == null || !layoutParams.f) {
            return;
        }
        int i3 = layoutParams.b;
        int i4 = i3 & 7;
        int i5 = i3 & 112;
        boolean z = true;
        boolean z2 = i5 == 48 || i5 == 80;
        if (i4 != 3 && i4 != 5) {
            z = false;
        }
        int i6 = Integer.MIN_VALUE;
        int i7 = 1073741824;
        if (z2) {
            i2 = 1073741824;
        } else if (z) {
            i2 = Integer.MIN_VALUE;
            i6 = 1073741824;
        } else {
            Log.d(g1, "do nothing.");
            i2 = Integer.MIN_VALUE;
        }
        int i8 = this.T0;
        int i9 = this.U0;
        int i10 = ((ViewGroup.LayoutParams) layoutParams).width;
        if (i10 != -2) {
            i2 = 1073741824;
            if (i10 != -1) {
                i8 = i10;
            }
        }
        int i11 = ((ViewGroup.LayoutParams) layoutParams).height;
        if (i11 == -2) {
            i7 = i6;
        } else if (i11 != -1) {
            i9 = i11;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(i8, i2), View.MeasureSpec.makeMeasureSpec(i9, i7));
        if (z2) {
            this.U0 -= view.getMeasuredHeight();
        } else if (z) {
            this.T0 -= view.getMeasuredWidth();
        }
    }

    private boolean b(boolean z, float f2, int i2, float f3, boolean z2) {
        if (!z2) {
            return z;
        }
        this.d0.onPull(i2 <= 0 ? 0.0f : Math.abs(f2 - f3) / i2);
        return true;
    }

    private void b(float f2) {
        int i2 = (int) f2;
        this.M += f2 - i2;
        scrollTo(i2, getScrollY());
        pageScrolled(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        r4 = 0.39999998f;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int b(int r2, float r3, int r4, int r5) {
        /*
            r1 = this;
            int r5 = java.lang.Math.abs(r5)
            int r0 = r1.V
            if (r5 <= r0) goto L16
            int r5 = java.lang.Math.abs(r4)
            int r0 = r1.T
            if (r5 <= r0) goto L16
            if (r4 <= 0) goto L13
            goto L2d
        L13:
            int r2 = r2 + 1
            goto L2d
        L16:
            boolean r4 = r1.u0
            if (r4 == 0) goto L1f
            int r4 = r1.b
            if (r2 < r4) goto L23
            goto L27
        L1f:
            int r4 = r1.b
            if (r2 < r4) goto L27
        L23:
            r4 = 1053609164(0x3ecccccc, float:0.39999998)
            goto L2a
        L27:
            r4 = 1058642330(0x3f19999a, float:0.6)
        L2a:
            float r3 = r3 + r4
            int r3 = (int) r3
            int r2 = r2 + r3
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwviewpager.widget.HwViewPager.b(int, float, int, int):int");
    }

    private boolean b(int i2, View view) {
        boolean m2;
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i2);
        if (findNextFocus == null || findNextFocus == view) {
            if (i2 == 17 || i2 == 1) {
                m2 = m();
            } else {
                if (i2 == 66 || i2 == 2) {
                    m2 = n();
                }
                m2 = false;
            }
        } else if (i2 == 17) {
            int i3 = a(this.h, findNextFocus).left;
            int i4 = a(this.h, view).left;
            if (view != null && i3 >= i4) {
                m2 = m();
            } else {
                m2 = findNextFocus.requestFocus();
            }
        } else if (i2 == 66) {
            int i5 = a(this.h, findNextFocus).left;
            int i6 = a(this.h, view).left;
            if (view != null && i5 <= i6) {
                m2 = n();
            } else {
                m2 = findNextFocus.requestFocus();
            }
        } else {
            Log.d(g1, "do nothing.");
            m2 = false;
        }
        if (m2) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i2));
        }
        return m2;
    }

    private void a(int i2, int i3, int i4) {
        View childAt = getChildAt(i4);
        if (childAt.getVisibility() != 8) {
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? (LayoutParams) layoutParams : null;
            if (layoutParams2 == null || layoutParams2.f) {
                return;
            }
            if (isPageScrollHorizontal()) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec((int) (i2 * layoutParams2.c), 1073741824), this.B);
            } else {
                childAt.measure(this.A, View.MeasureSpec.makeMeasureSpec((int) (i3 * layoutParams2.c), 1073741824));
            }
        }
    }

    private void b() {
        HwPagerAdapter hwPagerAdapter;
        if (this.s0 && (hwPagerAdapter = this.f10777a) != null) {
            if (hwPagerAdapter instanceof snh) {
                int count = hwPagerAdapter.getCount() - ((snh) hwPagerAdapter).a();
                int i2 = this.b;
                if (i2 > count + 1 || i2 < count) {
                    return;
                }
                postDelayed(new Runnable() { // from class: sne
                    @Override // java.lang.Runnable
                    public final void run() {
                        HwViewPager.this.l();
                    }
                }, isDynamicSpringAnimaitionEnabled() ? X1 : 300L);
                this.z0 = false;
                return;
            }
            return;
        }
        this.z0 = true;
    }

    private void a(int i2, int i3, int i4, int i5, View view) {
        int max;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? (LayoutParams) layoutParams : null;
        if (layoutParams2 == null || !layoutParams2.f) {
            return;
        }
        int i6 = layoutParams2.b & 7;
        if (i6 == 1) {
            max = Math.max((i2 - view.getMeasuredWidth()) / 2, this.V0);
        } else if (i6 == 3) {
            max = isRtlLayout() ? (i2 - this.X0) - view.getMeasuredWidth() : this.V0;
            this.X0 = isRtlLayout() ? this.X0 + view.getMeasuredWidth() : this.X0;
            this.V0 = isRtlLayout() ? this.V0 : this.V0 + view.getMeasuredWidth();
        } else if (i6 != 5) {
            max = this.V0;
        } else {
            max = isRtlLayout() ? this.V0 : (i2 - this.X0) - view.getMeasuredWidth();
            this.X0 = isRtlLayout() ? this.X0 : this.X0 + view.getMeasuredWidth();
            this.V0 = isRtlLayout() ? this.V0 + view.getMeasuredWidth() : this.V0;
        }
        int a3 = a(i3, view, layoutParams2);
        if (isPageScrollHorizontal()) {
            max += i4;
        } else {
            a3 += i5;
        }
        view.layout(max, a3, view.getMeasuredWidth() + max, view.getMeasuredHeight() + a3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, boolean z) {
        int a3 = a(i2, this.f10777a);
        this.N0 = false;
        setCurrentItem(a3, z);
        this.N0 = true;
    }

    private int a(int i2, View view, LayoutParams layoutParams) {
        int i3 = layoutParams.b & 112;
        if (i3 == 16) {
            return Math.max((i2 - view.getMeasuredHeight()) / 2, this.W0);
        }
        if (i3 == 48) {
            int i4 = this.W0;
            this.W0 = view.getMeasuredHeight() + i4;
            return i4;
        }
        if (i3 != 80) {
            return this.W0;
        }
        int measuredHeight = (i2 - this.Y0) - view.getMeasuredHeight();
        this.Y0 += view.getMeasuredHeight();
        return measuredHeight;
    }

    private void a(int i2, int i3, int i4, Rect rect) {
        View childAt = getChildAt(i2);
        if (childAt == null) {
            return;
        }
        int i5 = rect.top;
        int i6 = rect.bottom;
        int i7 = rect.left;
        if (childAt.getVisibility() == 8 || !(childAt.getLayoutParams() instanceof LayoutParams)) {
            return;
        }
        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
        e c2 = c(childAt);
        if (layoutParams.f || c2 == null) {
            return;
        }
        float f2 = i3;
        int i8 = (int) (c2.f10781a * f2);
        int i9 = isRtlLayout() ? i7 - i8 : i7 + i8;
        if (layoutParams.f10778a) {
            layoutParams.f10778a = false;
            childAt.measure(View.MeasureSpec.makeMeasureSpec((int) (f2 * layoutParams.c), 1073741824), View.MeasureSpec.makeMeasureSpec((i4 - i5) - i6, 1073741824));
        }
        childAt.layout(i9, i5, childAt.getMeasuredWidth() + i9, childAt.getMeasuredHeight() + i5);
    }

    private void a(int i2, View view, boolean z) {
        HwPagerAdapter adapter;
        if (!z || (adapter = getAdapter()) == null) {
            return;
        }
        int count = adapter.getCount() - 1;
        if (isSupportLoop() && (!isSupportLoop() || i2 == getClientWidth() * count || i2 == (-(count * getClientWidth())))) {
            return;
        }
        a(i2, view);
    }

    private void a(int i2, View view) {
        this.m0.transformPage(view, isPageScrollHorizontal() ? (isRtlLayout() ? i2 - view.getLeft() : view.getLeft() - i2) / getClientWidth() : (view.getTop() - i2) / getClientHeight());
    }

    private void a(int i2, float f2, int i3) {
        if (this.F0) {
            b(i2, i3);
        }
        OnPageChangeListener onPageChangeListener = this.j0;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i2, f2, i3);
        }
        List<OnPageChangeListener> list = this.i0;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                OnPageChangeListener onPageChangeListener2 = this.i0.get(i4);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageScrolled(i2, f2, i3);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.k0;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrolled(i2, f2, i3);
        }
    }

    private void a(int i2) {
        OnPageChangeListener onPageChangeListener = this.j0;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i2);
        }
        List<OnPageChangeListener> list = this.i0;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                OnPageChangeListener onPageChangeListener2 = this.i0.get(i3);
                if (onPageChangeListener2 != null) {
                    onPageChangeListener2.onPageSelected(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.k0;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageSelected(i2);
        }
    }

    private void a(boolean z) {
        boolean z2 = this.q0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            if (this.u0) {
                i();
            } else if (!this.n.isFinished()) {
                this.n.abortAnimation();
                a(getScrollX(), getScrollY(), this.n.getCurrX(), this.n.getCurrY());
            }
        }
        this.F = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            e eVar = this.f.get(i2);
            if (eVar.e) {
                eVar.e = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                ViewCompat.postOnAnimation(this, this.j);
            } else {
                this.j.run();
            }
        }
    }

    private void a(int i2, int i3, int i4, int i5) {
        if (i2 == i4 && i3 == i5) {
            return;
        }
        scrollTo(i4, i5);
        if (i4 != i2) {
            pageScrolled(i4);
        }
    }

    private boolean a(MotionEvent motionEvent) {
        if (this.A0) {
            this.E0.eiC_(motionEvent);
            setScrollState(0);
            return true;
        }
        if (this.H) {
            a(this.b, true, 0, false);
            this.Z0 = u();
        }
        return false;
    }

    private boolean a(MotionEvent motionEvent, boolean z) {
        return this.H ? isPageScrollHorizontal() ? g(motionEvent) ? u() : z : h(motionEvent) ? u() : z : z;
    }

    private void a(MotionEvent motionEvent, int i2) {
        float x = motionEvent.getX(i2);
        float abs = Math.abs(x - this.M);
        float y = motionEvent.getY(i2);
        float abs2 = Math.abs(y - this.N);
        if (abs <= this.L || abs <= abs2) {
            return;
        }
        this.H = true;
        c(true);
        float f2 = this.O;
        this.M = x - f2 > 0.0f ? f2 + this.L : f2 - this.L;
        this.N = y;
        setScrollState(1);
        setScrollingCacheEnabled(true);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private boolean a(boolean z, float f2, int i2, float f3, boolean z2) {
        if (!z2) {
            return z;
        }
        this.c0.onPull(i2 <= 0 ? 0.0f : Math.abs(f3 - f2) / i2);
        return true;
    }

    private float a(int i2, float f2, float f3) {
        return f2 * new bnm(i2 * 0.5f).getRate(Math.abs(f3));
    }

    private int a(int i2, float f2, int i3, int i4) {
        float f3;
        if (Math.abs(i4) > this.V && Math.abs(i3) > this.T) {
            if (isRtlLayout()) {
                if (i3 < 0) {
                    return i2;
                }
            } else if (i3 > 0) {
                return i2;
            }
            return i2 + 1;
        }
        if (this.u0) {
            f3 = 0.5f;
        } else {
            f3 = i2 >= this.b ? 0.39999998f : p1;
        }
        return i2 + ((int) (f2 + f3));
    }

    private boolean a(Canvas canvas, int i2, int i3, float f2, boolean z) {
        if (z) {
            float f3 = f2 - this.q;
            if (f3 < i2) {
                float f4 = i3;
                this.r.setBounds(Math.round(f3 + f4), this.s, Math.round(f4 + f2), this.t);
                this.r.draw(canvas);
            }
            return f2 < ((float) (i2 - i3));
        }
        if (this.q + f2 > i2) {
            if (isPageScrollHorizontal()) {
                this.r.setBounds(Math.round(f2), this.s, Math.round(this.q + f2), this.t);
            } else {
                this.r.setBounds(this.u, Math.round(f2), this.v, Math.round(this.q + f2));
            }
            this.r.draw(canvas);
        }
        return f2 > ((float) (i2 + i3));
    }

    private float a(float f2, int i2) {
        float f3;
        float f4;
        float f5;
        float f6;
        if (isRtlLayout()) {
            f3 = -i2;
            f4 = this.z;
        } else {
            f3 = i2;
            f4 = this.y;
        }
        float f7 = f3 * f4;
        if (isRtlLayout()) {
            f5 = -i2;
            f6 = this.y;
        } else {
            f5 = i2;
            f6 = this.z;
        }
        float f8 = f5 * f6;
        e eVar = this.f.get(0);
        e eVar2 = this.f.get(r3.size() - 1);
        if (eVar.b != 0) {
            if (isRtlLayout()) {
                f8 = (-eVar.f10781a) * i2;
            } else {
                f7 = eVar.f10781a * i2;
            }
        }
        if (eVar2.b != this.f10777a.getCount() - 1) {
            if (isRtlLayout()) {
                f7 = (-eVar2.f10781a) * i2;
            } else {
                f8 = eVar2.f10781a * i2;
            }
        }
        return f2 < f7 ? f7 : f2 > f8 ? f8 : f2;
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private void a() {
        if (!this.s0) {
            this.z0 = true;
            return;
        }
        HwPagerAdapter hwPagerAdapter = this.f10777a;
        if ((hwPagerAdapter instanceof snh) && this.b < ((snh) hwPagerAdapter).a()) {
            postDelayed(new Runnable() { // from class: snf
                @Override // java.lang.Runnable
                public final void run() {
                    HwViewPager.this.k();
                }
            }, isDynamicSpringAnimaitionEnabled() ? X1 : 300L);
            this.z0 = false;
        }
    }

    private ViewGroupOverlay a(View view) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            Log.e(g1, "getParentViewOverlay: viewParent is null");
            return null;
        }
        if (parent instanceof ViewGroup) {
            return ((ViewGroup) parent).getOverlay();
        }
        Log.e(g1, "getParentViewOverlay: viewParent is not instance of ViewGroup");
        return null;
    }

    private boolean a(int i2, Rect rect, int i3) {
        e c2;
        View childAt = getChildAt(i3);
        return childAt.getVisibility() == 0 && (c2 = c(childAt)) != null && c2.b == this.b && childAt.requestFocus(i2, rect);
    }

    private void a(int i2, boolean z) {
        int a3 = a(i2, this.f10777a);
        this.N0 = false;
        setCurrentItem(a3, z);
        this.N0 = true;
    }

    private int a(int i2, HwPagerAdapter hwPagerAdapter) {
        return ((hwPagerAdapter instanceof snh) && this.s0) ? ((snh) hwPagerAdapter).e(i2) : i2;
    }
}
