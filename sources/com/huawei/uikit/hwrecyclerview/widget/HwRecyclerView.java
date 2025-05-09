package com.huawei.uikit.hwrecyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.OverScroller;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.health.R;
import com.huawei.uikit.hwrecyclerview.layoutmanager.HwFloatingBubblesLayoutManager;
import com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwRollbackRuleDetector;
import com.huawei.uikit.hwunifiedinteract.widget.HwCompoundEventDetector;
import com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector;
import com.huawei.uikit.hwunifiedinteract.widget.HwKeyEventDetector;
import com.huawei.uikit.hwunifiedinteract.widget.HwRotaryConverter;
import defpackage.bnm;
import defpackage.bno;
import defpackage.slc;
import defpackage.smg;
import defpackage.smm;
import defpackage.smo;
import defpackage.smr;
import defpackage.smt;
import defpackage.snj;
import defpackage.snm;
import huawei.android.widget.HwSafeInsetsShareable;
import huawei.android.widget.ScrollCallback;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HwRecyclerView extends RecyclerView implements ScrollCallback, HwSafeInsetsShareable {
    private static final Interpolator c = new h();

    /* renamed from: a, reason: collision with root package name */
    private int f10722a;
    private int aa;
    private DeleteAnimatorCallback ab;
    private ContextMenu.ContextMenuInfo ac;
    private long ad;
    private int ae;
    private boolean af;
    private List<c> ag;
    private HwGenericEventDetector ah;
    private boolean ai;
    private HwKeyEventDetector aj;
    private OrientationHelper ak;
    private HwOnOverScrollListener al;
    private Runnable am;
    private int an;
    private List<HwOnOverScrollListener> ao;
    private float ap;
    private boolean aq;
    private int ar;
    private int as;
    private boolean at;
    private boolean au;
    private boolean av;
    private Method aw;
    private boolean ax;
    private float ay;
    private HwCompoundEventDetector az;
    private int b;
    private boolean ba;
    private boolean bb;
    private bqmxo bc;
    private boolean bd;
    private VelocityTracker be;
    private int bf;
    private float bg;
    private boolean bh;
    private boolean bi;
    private boolean bj;
    private boolean bk;
    private boolean bl;
    private boolean bm;
    private e bn;
    private int bo;
    private snj bp;
    private int bq;
    private snm br;
    private final ViewTreeObserver.OnPreDrawListener bs;
    private Rect bt;
    private Rect bu;
    private HwChainAnimationListener bv;
    private boolean bw;
    private boolean bx;
    private ValueAnimator by;
    private Interpolator bz;
    private Map<Integer, Rect> ca;
    public smm d;
    private smg e;
    private int f;
    private boolean g;
    private int h;
    private boolean i;
    private RecyclerView.OnScrollListener j;
    private int k;
    private b l;
    private HwLinkedViewCallBack m;
    private i n;
    private Field o;
    private int p;
    private OverScroller q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private final int[] v;
    private int w;
    private boolean x;
    private int y;
    private GestureDetector z;

    public interface DeleteAnimatorCallback {
        int getItemPosition(Object obj);

        int getPositionByView(View view);

        void notifyResult(boolean z);

        void remove(Object obj);
    }

    public interface MultiChoiceModeListener extends ActionMode.Callback {
        void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z);
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public interface OnItemClickListener {
        boolean onItemClick(View view, int i, long j);
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int i, long j);
    }

    class a extends RecyclerView.OnScrollListener {
        private int d = 0;

        /* renamed from: a, reason: collision with root package name */
        private int f10723a = 0;
        private int b = 0;

        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            OverScroller overScroller;
            if (!HwRecyclerView.this.canScrollVertically(-1)) {
                HwRecyclerView.this.as = 0;
            }
            int i2 = this.d;
            this.d = i;
            if (i2 == 2 && i == 0) {
                if (HwRecyclerView.this.by == null || !HwRecyclerView.this.by.isRunning()) {
                    RecyclerView.LayoutManager layoutManager = HwRecyclerView.this.getLayoutManager();
                    if (layoutManager == null) {
                        Log.e("HwRecyclerView", "onScrollStateChanged: call getLayoutManager failed");
                        return;
                    }
                    if (!layoutManager.canScrollVertically() || HwRecyclerView.this.aj()) {
                        if ((layoutManager.canScrollHorizontally() && !HwRecyclerView.this.ae()) || (overScroller = HwRecyclerView.this.getOverScroller()) == null || HwRecyclerView.this.x) {
                            return;
                        }
                        HwRecyclerView.this.efR_(overScroller, this.f10723a, this.b, 0L);
                    }
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            this.f10723a = i;
            this.b = i2;
            HwRecyclerView.a(HwRecyclerView.this, i2);
            HwRecyclerView.this.x();
        }
    }

    class b implements Runnable {
        private int b;
        private int c;
        private int d;
        private final int[] e;
        private long f;
        private boolean i;

        private b() {
            this.e = new int[2];
            this.d = 0;
            this.i = true;
            this.f = 0L;
        }

        private void d() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            if (HwRecyclerView.this.ad == 0) {
                long j = this.f;
                if (currentAnimationTimeMillis != j) {
                    HwRecyclerView.this.ad = currentAnimationTimeMillis - j;
                }
            }
            int[] iArr = this.e;
            int currY = HwRecyclerView.this.q.getCurrY();
            int i = currY - HwRecyclerView.this.k;
            HwRecyclerView.this.k = currY;
            if (i == 0 && HwRecyclerView.this.k == 0) {
                HwRecyclerView.this.postOnAnimation(this);
                return;
            }
            int linkedViewHeight = HwRecyclerView.this.m.linkedViewHeight();
            HwRecyclerView.this.startNestedScroll(2, 0);
            if (HwRecyclerView.this.dispatchNestedPreScroll(0, i, iArr, null, 0)) {
                i -= iArr[1];
            }
            if (i != 0 && HwRecyclerView.this.dispatchNestedScroll(0, 0, 0, i, null, 0)) {
                int currVelocity = (int) HwRecyclerView.this.q.getCurrVelocity();
                int linkedViewState = HwRecyclerView.this.m.linkedViewState();
                boolean z = currVelocity > 0;
                boolean z2 = (linkedViewState == 0 && this.b < 0) || (linkedViewState == 2 && this.b > 0);
                if (z && z2) {
                    int linkedViewHeight2 = (HwRecyclerView.this.m.linkedViewHeight() - linkedViewHeight) + i;
                    this.c = linkedViewHeight2;
                    if (linkedViewHeight2 < 0) {
                        this.d = 2;
                        run();
                    } else {
                        HwRecyclerView hwRecyclerView = HwRecyclerView.this;
                        hwRecyclerView.efR_(hwRecyclerView.q, 0, this.b, HwRecyclerView.this.ad);
                        c();
                    }
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.i) {
                return;
            }
            if (HwRecyclerView.this.m == null) {
                c();
                return;
            }
            if (this.c < 0 && this.d == 2) {
                HwRecyclerView.this.e(Math.abs(r0));
                HwRecyclerView hwRecyclerView = HwRecyclerView.this;
                hwRecyclerView.efR_(hwRecyclerView.q, 0, this.b, HwRecyclerView.this.ad);
                c();
                this.c = 0;
                return;
            }
            if (!HwRecyclerView.this.q.computeScrollOffset()) {
                c();
                return;
            }
            d();
            if (HwRecyclerView.this.q.isFinished()) {
                c();
            } else {
                HwRecyclerView.this.postOnAnimation(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            return this.i;
        }

        void egm_(OverScroller overScroller, int i) {
            this.b = i;
            this.d = 1;
            this.i = false;
            this.f = AnimationUtils.currentAnimationTimeMillis();
            run();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            HwRecyclerView.this.stopNestedScroll();
            HwRecyclerView.this.s = false;
            this.d = 0;
            HwRecyclerView.this.k = 0;
            this.i = true;
        }

        /* synthetic */ b(HwRecyclerView hwRecyclerView, h hVar) {
            this();
        }
    }

    class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwRecyclerView.this.k();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            HwRecyclerView.this.o();
        }
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f10727a;

        private e() {
        }

        protected void b(int i) {
            d();
            this.f10727a = i;
            HwRecyclerView.this.postOnAnimation(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            HwRecyclerView.this.smoothScrollBy(0, this.f10727a, new LinearInterpolator());
            HwRecyclerView.this.postOnAnimation(this);
        }

        /* synthetic */ e(HwRecyclerView hwRecyclerView, h hVar) {
            this();
        }

        protected void d() {
            HwRecyclerView.this.removeCallbacks(this);
        }
    }

    class f implements HwGenericEventDetector.OnScrollListener {
        f() {
        }

        @Override // com.huawei.uikit.hwunifiedinteract.widget.HwGenericEventDetector.OnScrollListener
        public boolean onScrollBy(float f, float f2, MotionEvent motionEvent) {
            return HwRecyclerView.this.e(f, f2);
        }
    }

    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HwRecyclerView.this.aq();
            HwRecyclerView.this.smoothScrollToPosition(0);
            if (HwRecyclerView.this.bh) {
                return;
            }
            HwRecyclerView.this.d.c();
            HwRecyclerView.this.bh = true;
        }
    }

    class h implements Interpolator {
        h() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    class i implements Runnable {
        private smo b;
        private int c;
        private boolean d;

        private i() {
            this.d = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.d || this.b == null || HwRecyclerView.this.ah()) {
                return;
            }
            this.d = this.b.c();
            float e = this.b.e();
            if (HwRecyclerView.this.f()) {
                HwRecyclerView.this.i((int) ((this.c == 1 ? HwRecyclerView.this.getTranslationY() : HwRecyclerView.this.getTranslationX()) - e));
            }
            HwRecyclerView.this.e(this.c, e);
            HwRecyclerView.this.invalidate();
            if (this.d) {
                HwRecyclerView.this.k();
            } else {
                HwRecyclerView.this.e(e);
                HwRecyclerView.this.postOnAnimation(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return this.d;
        }

        protected void c(int i, float f, int i2, int i3, long j) {
            if (Float.compare(f, 0.0f) == 0) {
                this.d = true;
                return;
            }
            smo smoVar = new smo(!HwRecyclerView.this.f() ? 228.0f : 200.0f, !HwRecyclerView.this.f() ? 30.0f : 28.0f, i2, i3, f);
            this.b = smoVar;
            smoVar.a(j);
            this.d = false;
            this.c = i;
            HwRecyclerView.this.o();
            run();
        }

        /* synthetic */ i(HwRecyclerView hwRecyclerView, h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            this.d = true;
        }
    }

    class j implements ValueAnimator.AnimatorUpdateListener {
        int c;

        j() {
            this.c = (int) (HwRecyclerView.this.m() ? HwRecyclerView.this.getTranslationY() : HwRecyclerView.this.getTranslationX());
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (valueAnimator == null) {
                Log.e("HwRecyclerView", "mSpringBackAnimator: onAnimationUpdate: animation is null");
                return;
            }
            if (HwRecyclerView.this.f()) {
                int floatValue = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                HwRecyclerView.this.i(this.c - floatValue);
                this.c = floatValue;
            }
            HwRecyclerView.this.e(((Float) valueAnimator.getAnimatedValue()).floatValue());
            HwRecyclerView.this.invalidate();
        }
    }

    class l extends RecyclerView.OnScrollListener {
        l() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int childCount;
            View findViewByPosition;
            RecyclerView.LayoutManager layoutManager = HwRecyclerView.this.getLayoutManager();
            if (layoutManager == null || (childCount = layoutManager.getChildCount()) < 1 || (findViewByPosition = layoutManager.findViewByPosition(childCount - 1)) == null) {
                return;
            }
            int height = (int) ((HwRecyclerView.this.getHeight() * 0.5f) - (findViewByPosition.getHeight() * 0.5f));
            if (HwRecyclerView.this.getPaddingBottom() == height || height <= 0) {
                return;
            }
            HwRecyclerView hwRecyclerView = HwRecyclerView.this;
            hwRecyclerView.bq = hwRecyclerView.getPaddingBottom();
            HwRecyclerView hwRecyclerView2 = HwRecyclerView.this;
            hwRecyclerView2.setPadding(hwRecyclerView2.getPaddingLeft(), HwRecyclerView.this.getPaddingTop(), HwRecyclerView.this.getPaddingRight(), height);
        }
    }

    class m implements ViewTreeObserver.OnPreDrawListener {
        m() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (!HwRecyclerView.this.bj || HwRecyclerView.this.getChildCount() <= 0) {
                return true;
            }
            HwRecyclerView.this.q();
            HwRecyclerView.this.bj = false;
            return true;
        }
    }

    class n implements HwRollbackRuleDetector.RollBackScrollListener {
        n() {
        }

        @Override // com.huawei.uikit.hwrecyclerview.widget.HwRollbackRuleDetector.RollBackScrollListener
        public int getScrollYDistance() {
            return HwRecyclerView.this.computeVerticalScrollOffset();
        }
    }

    class o extends AnimatorListenerAdapter {
        o() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            HwRecyclerView.this.ak();
        }
    }

    public HwRecyclerView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ae() {
        return (canScrollHorizontally(1) && canScrollHorizontally(-1)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ah() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            return true;
        }
        if (!layoutManager.canScrollVertically() || computeVerticalScrollRange() == 0) {
            return !layoutManager.canScrollHorizontally() || computeHorizontalScrollRange() == 0;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aj() {
        return (canScrollVertically(1) && canScrollVertically(-1)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        smt smtVar;
        List<c> list = this.ag;
        if (list == null) {
            Log.e("HwRecyclerView", "getDisappearAnimatorListener: onAnimationEnd: mVisibleItemInfos is null");
            ad();
            this.ab.notifyResult(false);
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = this.ag.get(i2);
            if (cVar.m) {
                ViewGroupOverlay viewGroupOverlay = cVar.j;
                if (viewGroupOverlay == null || (smtVar = cVar.i) == null) {
                    Log.w("HwRecyclerView", "getDisappearAnimatorListener: onAnimationEnd: mViewOverlay/mAnimDrawable is null.");
                } else {
                    viewGroupOverlay.remove(smtVar);
                }
                cVar.m = false;
            }
        }
        ad();
        this.ab.notifyResult(true);
    }

    private boolean al() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return !this.bp.b() && (layoutManager != null && layoutManager.getClass().isAssignableFrom(LinearLayoutManager.class));
    }

    private void am() {
        this.b = 0;
        this.h = 0;
    }

    private void an() {
        e eVar = this.bn;
        if (eVar != null) {
            eVar.d();
            stopScroll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aq() {
        int i2 = ((int) ((this.ar * this.ap) + 0.5f)) - this.as;
        if (g(i2)) {
            scrollBy(0, i2);
        }
    }

    private Class<?> ar() {
        Class<?> cls = getClass();
        String name = RecyclerView.class.getName();
        for (int i2 = 0; i2 < 5; i2++) {
            if (name.equals(cls.getName())) {
                return cls;
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private void as() {
        OverScroller overScroller = this.q;
        if (overScroller != null) {
            overScroller.abortAnimation();
        }
        if (!this.n.a()) {
            this.n.d();
        }
        if (this.l.e()) {
            return;
        }
        this.l.c();
    }

    private void d() {
        HwLinkedViewCallBack hwLinkedViewCallBack = this.m;
        if (hwLinkedViewCallBack == null || this.p != Integer.MIN_VALUE) {
            return;
        }
        this.p = hwLinkedViewCallBack.linkedViewHeight();
    }

    private void e() {
        addOnScrollListener(new a());
    }

    private void egl_(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.y) {
            try {
                this.y = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
            } catch (IllegalArgumentException unused) {
                Log.e("HwRecyclerView", "HwRecyclerView get pointerId error!");
            }
        }
    }

    private ValueAnimator.AnimatorUpdateListener getAlphaListener() {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: smf
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                HwRecyclerView.this.efY_(valueAnimator);
            }
        };
    }

    private Animator.AnimatorListener getDisappearAnimatorListener() {
        return new o();
    }

    private View getFirstVisibleView() {
        int firstVisibleViewIndex = getFirstVisibleViewIndex();
        if (firstVisibleViewIndex < 0) {
            return null;
        }
        return getChildAt(firstVisibleViewIndex);
    }

    private Field getFlingerField() {
        try {
            Field declaredField = RecyclerView.class.getDeclaredField("mViewFlinger");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (ClassNotFoundException unused) {
            Log.e("HwRecyclerView", "getFlingerField: class not found.");
            return null;
        } catch (NoSuchFieldException unused2) {
            Log.e("HwRecyclerView", "getFlingerField: no such field.");
            return null;
        }
    }

    private ValueAnimator.AnimatorUpdateListener getHeightListener() {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: sml
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                HwRecyclerView.this.ege_(valueAnimator);
            }
        };
    }

    private void p() {
        if (this.br == null || !al()) {
            return;
        }
        this.br.d(this.bp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (getChildCount() < 1 || !this.bm) {
            return;
        }
        if (getChildAt(0) == null) {
            return;
        }
        int height = (int) ((getHeight() * 0.5f) - (r1.getHeight() * 0.5f));
        if (getPaddingTop() == height || height <= 0) {
            return;
        }
        this.bo = getPaddingTop();
        setPadding(getPaddingLeft(), height, getPaddingRight(), getPaddingBottom());
        View focusedChild = getFocusedChild();
        getLayoutManager().scrollToPosition(focusedChild != null ? getLayoutManager().getPosition(focusedChild) : 0);
    }

    private void s() {
        if (this.bo != Integer.MIN_VALUE) {
            setPadding(getPaddingLeft(), this.bo, getPaddingRight(), this.bq);
        }
    }

    private void setFirstItemCenteringEnabledInternal(boolean z) {
        this.bm = z;
        if (!z) {
            s();
            this.bj = false;
        } else if (getChildCount() > 0) {
            q();
        } else {
            this.bj = true;
        }
    }

    private void setIntegerTranslationX(float f2) {
        setTranslationX((int) f2);
    }

    private void setIntegerTranslationY(float f2) {
        setTranslationY((int) f2);
    }

    private void setSensitivityMode(int i2) {
        if (i2 == 0) {
            this.bg = 1.0f;
        } else if (i2 == 2) {
            this.bg = 0.6f;
        } else {
            this.bg = 0.6f;
        }
    }

    private void setValueFromPlume(Context context) {
        Method b2 = slc.b("getBoolean", new Class[]{Context.class, View.class, String.class, Boolean.TYPE}, "huawei.android.widget.HwPlume");
        if (b2 == null) {
            return;
        }
        Object c2 = slc.c((Object) null, b2, new Object[]{context, this, "listScrollEnabled", Boolean.TRUE});
        if (c2 instanceof Boolean) {
            setExtendScrollEnabled(((Boolean) c2).booleanValue());
        }
    }

    protected HwGenericEventDetector a() {
        return new HwGenericEventDetector(getContext());
    }

    public void a(boolean z) {
        this.aq = z;
    }

    public boolean a(int i2) {
        HwLinkedViewCallBack hwLinkedViewCallBack;
        int linkedViewState;
        return !this.x || (hwLinkedViewCallBack = this.m) == null || (((linkedViewState = hwLinkedViewCallBack.linkedViewState()) == 0 || ai()) && getTranslationY() >= 0.0f) || ((linkedViewState == 2 && getTranslationY() <= 0.0f) || (linkedViewState == 0 && getTranslationY() <= 0.0f && i2 > 0));
    }

    @Override // huawei.android.widget.HwSafeInsetsShareable
    public void addSharedView(View view, int i2) {
        if (view == null || this.bp == null || !al()) {
            return;
        }
        ac();
        this.br.addSharedView(view, i2);
        if (isAttachedToWindow()) {
            this.br.eiM_(view, this.bp);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        boolean z = layoutParams instanceof RecyclerView.LayoutParams;
        if (al() && z) {
            Object b2 = slc.b(layoutParams, "mViewHolder", (Class<?>) RecyclerView.LayoutParams.class);
            if (b2 instanceof RecyclerView.ViewHolder) {
                efP_(view, ((RecyclerView.ViewHolder) b2).getItemViewType());
            }
        }
        super.addView(view, i2, layoutParams);
    }

    public void b() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            bqmxoVar.b();
        }
    }

    public void b(HwOnOverScrollListener hwOnOverScrollListener) {
        if (this.ao == null) {
            this.ao = new ArrayList();
        }
        if (hwOnOverScrollListener != null) {
            this.ao.add(hwOnOverScrollListener);
        }
    }

    public void b(boolean z) {
        this.ax = z;
    }

    protected HwCompoundEventDetector c() {
        return new HwCompoundEventDetector(getContext());
    }

    public void c(boolean z) {
        this.av = z;
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        RecyclerView.Adapter adapter = getAdapter();
        if (this.ak == null || adapter == null) {
            return super.canScrollHorizontally(i2);
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return (layoutManager == null || !layoutManager.canScrollHorizontally()) ? super.canScrollHorizontally(i2) : i2 > 0 ? d(adapter) : w();
    }

    @Override // android.view.View
    public boolean canScrollVertically(int i2) {
        RecyclerView.Adapter adapter = getAdapter();
        if (getLayoutManager() instanceof HwFloatingBubblesLayoutManager) {
            return ((HwFloatingBubblesLayoutManager) getLayoutManager()).c(this, i2);
        }
        if (this.ak == null || adapter == null) {
            return super.canScrollVertically(i2);
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return (layoutManager == null || !layoutManager.canScrollVertically()) ? super.canScrollVertically(i2) : i2 > 0 ? d(adapter) : w();
    }

    protected float d(int i2, boolean z) {
        float translationY = z ? getTranslationY() : getTranslationX();
        return translationY + c(i2, Math.abs(translationY), (int) ((z ? getHeight() : getWidth()) * this.ay));
    }

    public void d(boolean z) {
        this.ba = z;
    }

    protected boolean d(float f2, float f3) {
        return ((f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1)) < 0 && (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1)) >= 0) || ((f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1)) > 0 && (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1)) <= 0);
    }

    protected boolean d(int i2) {
        if (this.aq && !this.i) {
            if (i2 < 0 && !canScrollHorizontally(1) && this.ax) {
                o();
            } else if (i2 <= 0 || canScrollHorizontally(-1) || !this.av) {
                Log.e("HwRecyclerView", "invalid scroll, do not onOverScrollStart");
            } else {
                o();
            }
        }
        return this.i;
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (!this.ai) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        HwChainAnimationListener hwChainAnimationListener = this.bv;
        if (hwChainAnimationListener != null) {
            hwChainAnimationListener.dispatchGenericMotionEvent(motionEvent);
        }
        HwGenericEventDetector hwGenericEventDetector = this.ah;
        if (hwGenericEventDetector == null || !hwGenericEventDetector.eir_(motionEvent)) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        HwKeyEventDetector hwKeyEventDetector;
        List<c> list = this.ag;
        if (list != null && list.size() != 0) {
            return true;
        }
        if (keyEvent == null) {
            return false;
        }
        boolean dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        return (dispatchKeyEvent || (hwKeyEventDetector = this.aj) == null) ? dispatchKeyEvent : hwKeyEventDetector.eix_(keyEvent.getKeyCode(), keyEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        boolean dispatchNestedPreScroll = super.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
        if (this.x && dispatchNestedPreScroll && iArr2 != null) {
            int[] iArr3 = this.v;
            iArr3[0] = iArr3[0] + iArr2[0];
            iArr3[1] = iArr3[1] + iArr2[1];
        }
        return dispatchNestedPreScroll;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr, int i6) {
        boolean dispatchNestedScroll = super.dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
        if (this.x && dispatchNestedScroll && iArr != null) {
            int[] iArr2 = this.v;
            iArr2[0] = iArr2[0] + iArr[0];
            iArr2[1] = iArr2[1] + iArr[1];
        }
        return dispatchNestedScroll;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        List<c> list = this.ag;
        if (list != null && list.size() != 0) {
            return true;
        }
        HwChainAnimationListener hwChainAnimationListener = this.bv;
        if (hwChainAnimationListener != null) {
            hwChainAnimationListener.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        efL_(canvas);
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            bqmxoVar.n();
        }
        super.draw(canvas);
    }

    protected void e(float f2) {
        if (this.i) {
            HwOnOverScrollListener hwOnOverScrollListener = this.al;
            if (hwOnOverScrollListener != null) {
                hwOnOverScrollListener.onOverScrolled(f2);
            }
            List<HwOnOverScrollListener> list = this.ao;
            if (list != null) {
                Iterator<HwOnOverScrollListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onOverScrolled(f2);
                }
            }
        }
    }

    protected boolean e(float f2, float f3) {
        if (m()) {
            scrollBy(0, (int) f3);
            return true;
        }
        if (Float.compare(f2, 0.0f) == 0) {
            f2 = f3;
        }
        scrollBy((int) f2, 0);
        return true;
    }

    protected boolean e(int i2, int i3) {
        if (this.aq && !this.i) {
            HwLinkedViewCallBack hwLinkedViewCallBack = this.m;
            if (hwLinkedViewCallBack != null) {
                if (hwLinkedViewCallBack.linkedViewState() != 2 && i2 < 0) {
                    this.h = i3;
                    return false;
                }
                if (this.m.linkedViewState() != 0 && i2 > 0 && getTranslationY() >= 0.0f) {
                    this.h = i3;
                    return false;
                }
            }
            b(i2);
        }
        return this.i;
    }

    public boolean f() {
        return this.bv != null && this.bw;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int i2, int i3) {
        if (this.x && this.q.isFinished()) {
            VelocityTracker velocityTracker = this.be;
            if (velocityTracker == null) {
                Log.w("HwRecyclerView", "fling without velocityTracker");
                return super.fling(i2, i3);
            }
            velocityTracker.computeCurrentVelocity(1000, this.w);
            int i4 = (int) (-this.be.getYVelocity(this.y));
            this.aa = i4;
            this.q.fling(0, 0, 0, i4, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            if (layoutManager == null) {
                return super.fling(i2, i3);
            }
            if (layoutManager.canScrollVertically()) {
                if (((!canScrollVertically(-1) && this.aa < 0) || (!canScrollVertically(-1) && !canScrollVertically(1) && this.aa > 0)) && !this.s && Math.abs(this.aa) >= this.u && getTranslationY() == 0.0f) {
                    int i5 = this.aa > 0 ? 1 : -1;
                    this.s = true;
                    this.k = 0;
                    efQ_(this.q, i5);
                }
            }
        }
        if (!this.bl) {
            return super.fling(i2, i3);
        }
        this.bl = false;
        return super.fling(0, 0);
    }

    protected HwGenericEventDetector.OnScrollListener g() {
        return new f();
    }

    public HwChainAnimationListener getChainAnimationListener() {
        return this.bv;
    }

    public int getCheckedItemCount() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar == null) {
            return 0;
        }
        return bqmxoVar.a();
    }

    public long[] getCheckedItemIds() {
        bqmxo bqmxoVar = this.bc;
        return bqmxoVar == null ? new long[0] : bqmxoVar.d();
    }

    public SparseBooleanArray getCheckedItemPositions() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            return bqmxoVar.egA_();
        }
        Log.e("HwRecyclerView", "mHwMultipleChoiceModeHelper: is null");
        return null;
    }

    public ActionMode getChoiceActionMode() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            return bqmxoVar.i();
        }
        Log.e("HwRecyclerView", "mHwMultipleChoiceModeHelper: is null");
        return null;
    }

    public int getChoiceMode() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar == null) {
            return 0;
        }
        return bqmxoVar.g();
    }

    @Override // android.view.View
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return this.ac;
    }

    public int getFirstVisibleViewIndex() {
        int childCount = getChildCount();
        if (childCount == 0 || this.ag == null) {
            return -1;
        }
        int paddingTop = getPaddingTop();
        int size = this.ag.size();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && !childAt.isDirty() && childAt.getBottom() >= paddingTop) {
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        i3 = 0;
                        break;
                    }
                    if (this.ag.get(i3).e == childAt) {
                        break;
                    }
                    i3++;
                }
                if (i3 == size || !this.ag.get(i3).f10725a) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public HwLinkedViewCallBack getLinkedViewCallBack() {
        return this.m;
    }

    public MultiChoiceModeListener getMultiChoiceModeListener() {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            return bqmxoVar.j();
        }
        Log.e("HwRecyclerView", "mHwMultipleChoiceModeHelper: is null");
        return null;
    }

    public HwKeyEventDetector.OnEditEventListener getOnEditEventListener() {
        HwKeyEventDetector hwKeyEventDetector = this.aj;
        if (hwKeyEventDetector != null) {
            return hwKeyEventDetector.b();
        }
        return null;
    }

    @Deprecated
    public OnItemClickListener getOnItemClickListener() {
        return null;
    }

    @Deprecated
    public OnItemLongClickListener getOnItemLongClickListener() {
        return null;
    }

    public HwKeyEventDetector.OnSearchEventListener getOnSearchEventListener() {
        HwKeyEventDetector hwKeyEventDetector = this.aj;
        if (hwKeyEventDetector != null) {
            return hwKeyEventDetector.d();
        }
        return null;
    }

    public float getOverScrollFactor() {
        return this.ay;
    }

    @Deprecated
    public HwOnOverScrollListener getOverScrollListener() {
        return this.al;
    }

    OverScroller getOverScroller() {
        Field field = this.o;
        if (field == null) {
            return null;
        }
        try {
            Object obj = field.get(this);
            Object b2 = slc.b(obj, "mScroller", this.o.getType());
            if (b2 == null) {
                b2 = slc.b(obj, "mOverScroller", this.o.getType());
            }
            if (b2 instanceof OverScroller) {
                return (OverScroller) b2;
            }
        } catch (IllegalAccessException unused) {
            Log.e("HwRecyclerView", "getOverScroller: illegal access.");
        }
        return null;
    }

    public float getScrollTopFactor() {
        return this.ap;
    }

    public float getSensitivity() {
        HwGenericEventDetector hwGenericEventDetector = this.ah;
        if (hwGenericEventDetector != null) {
            return hwGenericEventDetector.b();
        }
        return 0.6f;
    }

    protected HwRotaryConverter h() {
        Log.i("HwRecyclerView", "Overwrite createRotaryConverter in the watch package.");
        return null;
    }

    protected HwKeyEventDetector i() {
        return new HwKeyEventDetector(getContext());
    }

    public void j() {
        ValueAnimator valueAnimator = this.by;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && m() && canScrollVertically(-1) && getWindowVisibility() == 0 && hasWindowFocus()) {
            post(new g());
        }
    }

    protected void k() {
        if (getTranslationY() == 0.0f && getTranslationX() == 0.0f && this.i) {
            this.i = false;
            HwOnOverScrollListener hwOnOverScrollListener = this.al;
            if (hwOnOverScrollListener != null) {
                hwOnOverScrollListener.onOverScrollEnd();
            }
            List<HwOnOverScrollListener> list = this.ao;
            if (list != null) {
                Iterator<HwOnOverScrollListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onOverScrollEnd();
                }
            }
            setScrollStateExtend(0);
        }
    }

    protected boolean l() {
        return true;
    }

    protected boolean m() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        return (layoutManager == null || !layoutManager.canScrollVertically() || layoutManager.canScrollHorizontally()) ? false : true;
    }

    protected void o() {
        if (this.i) {
            return;
        }
        this.i = true;
        HwOnOverScrollListener hwOnOverScrollListener = this.al;
        if (hwOnOverScrollListener != null) {
            hwOnOverScrollListener.onOverScrollStart();
        }
        List<HwOnOverScrollListener> list = this.ao;
        if (list != null) {
            Iterator<HwOnOverScrollListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onOverScrollStart();
            }
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (al()) {
            this.bp.ejb_(windowInsets);
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        HwCompoundEventDetector hwCompoundEventDetector;
        super.onAttachedToWindow();
        this.bp.d(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        this.bu.set(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        u();
        if (this.az == null) {
            this.az = c();
        }
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null && (hwCompoundEventDetector = this.az) != null) {
            hwCompoundEventDetector.eio_(this, bqmxoVar.f());
        }
        if (this.bm) {
            getViewTreeObserver().addOnPreDrawListener(this.bs);
        }
        HwChainAnimationListener hwChainAnimationListener = this.bv;
        if (hwChainAnimationListener != null) {
            hwChainAnimationListener.onAttachedToWindow();
            addOnScrollListener(this.bv);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        if (getLayoutManager() instanceof HwFloatingBubblesLayoutManager) {
            ((HwFloatingBubblesLayoutManager) getLayoutManager()).e(this);
        }
        snm snmVar = this.br;
        if (snmVar != null) {
            snmVar.eiL_(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d.d();
        v();
        HwCompoundEventDetector hwCompoundEventDetector = this.az;
        if (hwCompoundEventDetector != null) {
            hwCompoundEventDetector.b();
        }
        if (this.bm) {
            getViewTreeObserver().removeOnPreDrawListener(this.bs);
        }
        HwChainAnimationListener hwChainAnimationListener = this.bv;
        if (hwChainAnimationListener != null) {
            hwChainAnimationListener.onDetachedFromWindow();
            removeOnScrollListener(this.bv);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        HwGenericEventDetector hwGenericEventDetector;
        if (motionEvent == null) {
            return false;
        }
        HwCompoundEventDetector hwCompoundEventDetector = this.az;
        if (hwCompoundEventDetector != null && hwCompoundEventDetector.eim_(motionEvent)) {
            return true;
        }
        if (this.ai && (hwGenericEventDetector = this.ah) != null && hwGenericEventDetector.eis_(motionEvent)) {
            return this.at;
        }
        if (motionEvent.getAction() == 11 && motionEvent.isFromSource(2)) {
            int buttonState = motionEvent.getButtonState();
            bqmxo bqmxoVar = this.bc;
            if (bqmxoVar != null && (buttonState == 32 || buttonState == 2)) {
                bqmxoVar.c(motionEvent);
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.e("HwRecyclerView", "onInterceptTouchEvent: motionEvent is null");
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        this.t = actionMasked;
        if (actionMasked == 0) {
            if (this.bx) {
                this.e.efE_(motionEvent);
            }
            as();
        }
        if ((actionMasked == 2 && this.g) || super.onInterceptTouchEvent(motionEvent) || this.i) {
            return true;
        }
        if (!this.aq) {
            return false;
        }
        int actionIndex = motionEvent.getActionIndex();
        if (!aj() && !ae()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        efN_(motionEvent, actionMasked, actionIndex);
        return this.g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        RecyclerView.Adapter adapter;
        super.onLayout(z, i2, i3, i4, i5);
        if (al() && (adapter = getAdapter()) != null) {
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                View childAt = getChildAt(i6);
                if (childAt != null) {
                    int childAdapterPosition = getChildAdapterPosition(childAt);
                    if (childAdapterPosition == -1 || childAdapterPosition >= adapter.getItemCount()) {
                        Log.w("HwRecyclerView", "the position is " + childAdapterPosition);
                        break;
                    }
                    efP_(childAt, adapter.getItemViewType(childAdapterPosition));
                    bqmxo bqmxoVar = this.bc;
                    if (bqmxoVar != null) {
                        bqmxoVar.a(childAt, childAdapterPosition);
                    }
                }
            }
            Rect eiW_ = this.bp.eiW_(this);
            if (eiW_ != null) {
                this.bt.set(eiW_);
            }
            d();
            p();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        bqmxo bqmxoVar = this.bc;
        Parcelable egy_ = bqmxoVar != null ? bqmxoVar.egy_(parcelable) : null;
        if (egy_ != null) {
            super.onRestoreInstanceState(egy_);
        } else {
            super.onRestoreInstanceState(parcelable);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        bqmxo bqmxoVar = this.bc;
        return bqmxoVar == null ? onSaveInstanceState : bqmxoVar.egz_(onSaveInstanceState);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w("HwRecyclerView", "onTouchEvent(): motionEvent can not be null!");
            return false;
        }
        this.d.egq_(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        this.t = actionMasked;
        if (efV_(motionEvent, actionMasked)) {
            return true;
        }
        int actionIndex = motionEvent.getActionIndex();
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        e(actionMasked);
        int[] iArr = this.v;
        obtain.offsetLocation(iArr[0], iArr[1]);
        return a(motionEvent, actionMasked, actionIndex, obtain);
    }

    protected boolean r() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            return false;
        }
        return layoutManager.canScrollVertically() ? b("translationY", 0.0f, 0.0f) : b("translationX", 0.0f, 0.0f);
    }

    @Override // huawei.android.widget.HwSafeInsetsShareable
    public void removeSharedView(View view) {
        snm snmVar = this.br;
        if (snmVar != null) {
            snmVar.removeSharedView(view);
        }
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i2) {
        View childAt;
        if (f() && (childAt = getChildAt(i2)) != null) {
            childAt.setTranslationY(0.0f);
            childAt.setTranslationX(0.0f);
        }
        super.removeViewAt(i2);
    }

    @Override // huawei.android.widget.ScrollCallback
    public void scrollToTop() {
        j();
    }

    public void setAdaptOverScrollEnabled(boolean z) {
        this.au = z;
        if (z) {
            ViewParent parent = getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).setClipChildren(false);
            } else {
                Log.w("HwRecyclerView", "setAdaptScrollBarEnabled: parent is invalid.");
                this.au = false;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            bqmxoVar.d(adapter);
        }
        super.setAdapter(adapter);
    }

    public void setAutoScrollEnable(boolean z) {
        this.bd = z;
    }

    public void setChainAnimationEnabled(boolean z) {
        if (this.bw == z) {
            return;
        }
        this.bw = z;
        if (z) {
            return;
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                childAt.setTranslationY(0.0f);
                childAt.setTranslationX(0.0f);
            }
        }
    }

    public void setChainAnimationListener(HwChainAnimationListener hwChainAnimationListener) {
        this.bv = hwChainAnimationListener;
    }

    public void setChoiceMode(int i2) {
        if (this.bc == null) {
            this.bc = new bqmxo(this);
        }
        this.bc.c(i2);
    }

    void setDetectoredLongpressEnabled(boolean z) {
        GestureDetector gestureDetector = this.z;
        if (gestureDetector != null) {
            gestureDetector.setIsLongpressEnabled(z);
        }
    }

    public void setExtendScrollConsumedEvent(boolean z) {
        this.at = z;
    }

    public void setExtendScrollEnabled(boolean z) {
        this.ai = z;
    }

    public void setExtendedMultiChoiceEnabled(boolean z, boolean z2) {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar == null) {
            Log.e("HwRecyclerView", "mHwMultipleChoiceModeHelper: is null");
        } else {
            bqmxoVar.e(z, z2);
        }
    }

    public void setFirstItemCenteringEnabled(boolean z) {
        setFirstItemCenteringEnabledInternal(z);
    }

    public void setItemChecked(int i2, boolean z) {
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar == null) {
            Log.e("HwRecyclerView", "mHwMultipleChoiceModeHelper: is null");
        } else {
            bqmxoVar.c(i2, z);
        }
    }

    public void setLastItemCenteringEnabled(boolean z) {
        this.bk = z;
        removeOnScrollListener(this.j);
        if (this.bk) {
            addOnScrollListener(this.j);
        } else if (this.bq != Integer.MIN_VALUE) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), this.bq);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            this.ak = OrientationHelper.createOrientationHelper(layoutManager, ((StaggeredGridLayoutManager) layoutManager).getOrientation());
        } else {
            this.ak = null;
        }
        super.setLayoutManager(layoutManager);
    }

    public void setLinkedViewCallBack(HwLinkedViewCallBack hwLinkedViewCallBack) {
        this.m = hwLinkedViewCallBack;
        u();
    }

    public void setMaxFlingVelocity(int i2) {
        Class<?> ar;
        if (this.w == i2 || (ar = ar()) == null) {
            return;
        }
        try {
            Field declaredField = ar.getDeclaredField("mMaxFlingVelocity");
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(i2));
            this.w = getMaxFlingVelocity();
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            Log.e("HwRecyclerView", "set max fling velocity failed.");
        }
    }

    public void setMinFlingVelocity(int i2) {
        Class<?> ar;
        if (this.u == i2 || (ar = ar()) == null) {
            return;
        }
        try {
            Field declaredField = ar.getDeclaredField("mMinFlingVelocity");
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(i2));
            this.u = getMinFlingVelocity();
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            Log.e("HwRecyclerView", "set min fling velocity failed.");
        }
    }

    public void setMultiChoiceModeListener(MultiChoiceModeListener multiChoiceModeListener) {
        if (this.bc == null) {
            this.bc = new bqmxo(this);
        }
        this.bc.d(multiChoiceModeListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        if (this.x && isAttachedToWindow() && !this.r) {
            return;
        }
        super.setNestedScrollingEnabled(z);
    }

    public void setOnEditEventListener(HwKeyEventDetector.OnEditEventListener onEditEventListener) {
        aa();
        HwKeyEventDetector hwKeyEventDetector = this.aj;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.c(onEditEventListener);
        }
    }

    @Deprecated
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    }

    @Deprecated
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
    }

    public void setOnSearchEventListener(HwKeyEventDetector.OnSearchEventListener onSearchEventListener) {
        aa();
        HwKeyEventDetector hwKeyEventDetector = this.aj;
        if (hwKeyEventDetector != null) {
            hwKeyEventDetector.a(onSearchEventListener);
        }
    }

    public void setOverScrollFactor(float f2) {
        if (Float.compare(f2, 0.0f) <= 0 || Float.compare(f2, 1.0f) > 0) {
            Log.w("HwRecyclerView", "setOverScrollFactor: input is invalid.");
        } else {
            this.ay = f2;
        }
    }

    @Deprecated
    public void setOverScrollListener(HwOnOverScrollListener hwOnOverScrollListener) {
        this.al = hwOnOverScrollListener;
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        this.bp.d(i2, i3, i4, i5);
    }

    public void setPageTurningScrollEnabled(boolean z) {
        this.bx = z;
        if (z && this.e == null) {
            this.e = new smg(this);
        }
    }

    public void setPageTurningThresholdRatioHorizontal(float f2) {
        if (Float.compare(f2, 0.0f) < 0 || Float.compare(f2, 1.0f) > 0) {
            Log.e("HwRecyclerView", "Please ensure that the value of ratio is greater than 0.0f and less than 1.0f.");
            return;
        }
        smg smgVar = this.e;
        if (smgVar != null) {
            smgVar.a(f2);
        }
    }

    public void setPageTurningThresholdRatioVertical(float f2) {
        if (Float.compare(f2, 0.0f) < 0 || Float.compare(f2, 1.0f) > 0) {
            Log.e("HwRecyclerView", "Please ensure that the value of ratio is greater than 0.0f and less than 1.0f.");
            return;
        }
        smg smgVar = this.e;
        if (smgVar != null) {
            smgVar.e(f2);
        }
    }

    protected void setScrollStateExtend(int i2) {
        if (this.aw == null) {
            try {
                Method declaredMethod = RecyclerView.class.getDeclaredMethod("setScrollState", Integer.TYPE);
                this.aw = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (ClassNotFoundException unused) {
                Log.w("HwRecyclerView", "setScrollStateExtend not found method");
            } catch (NoSuchMethodException unused2) {
                Log.w("HwRecyclerView", "setScrollStateExtend no such method");
            }
        }
        Method method = this.aw;
        if (method == null) {
            return;
        }
        try {
            method.invoke(this, Integer.valueOf(i2));
        } catch (IllegalAccessException unused3) {
            Log.w("HwRecyclerView", "setScrollStateExtend illegal access");
        } catch (InvocationTargetException unused4) {
            Log.w("HwRecyclerView", "setScrollStateExtend invocation target");
        }
    }

    public void setScrollTopEnable(boolean z) {
    }

    public void setScrollTopFactor(float f2) {
        this.ap = f2;
    }

    public void setScroller(OverScroller overScroller) {
        if (overScroller == null) {
            return;
        }
        this.q = overScroller;
    }

    public void setSensitivity(float f2) {
        HwGenericEventDetector hwGenericEventDetector = this.ah;
        if (hwGenericEventDetector != null) {
            hwGenericEventDetector.e(f2);
        }
    }

    public void setSmoothScrollInterpolator(Interpolator interpolator) {
        this.bz = interpolator;
    }

    public void setSubHeaderDeleteUpdate(Runnable runnable) {
        this.am = runnable;
    }

    @Override // android.view.View
    public boolean showContextMenu() {
        return d(0.0f, 0.0f, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view) {
        if (this.af) {
            return false;
        }
        return efX_(view, 0.0f, 0.0f, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void smoothScrollBy(int i2, int i3) {
        OverScroller overScroller;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            super.smoothScrollBy(i2, i3);
            return;
        }
        Interpolator interpolator = this.bz;
        if (interpolator != null) {
            if (interpolator instanceof bno) {
                super.smoothScrollBy(i2, i3, interpolator, (int) ((bno) interpolator).getDuration());
                return;
            } else {
                super.smoothScrollBy(i2, i3, interpolator);
                return;
            }
        }
        if ((!layoutManager.canScrollHorizontally() || i2 == 0) && (!layoutManager.canScrollVertically() || i3 == 0)) {
            super.smoothScrollBy(i2, i3);
            return;
        }
        if (l() && (overScroller = getOverScroller()) != null) {
            overScroller.fling(0, 0, 0, 0, 0, 0, 0, 0);
            overScroller.abortAnimation();
        }
        super.smoothScrollBy(i2, i3);
    }

    protected void t() {
    }

    public HwRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr._2131100481_res_0x7f060341);
    }

    static /* synthetic */ int a(HwRecyclerView hwRecyclerView, int i2) {
        int i3 = hwRecyclerView.as + i2;
        hwRecyclerView.as = i3;
        return i3;
    }

    private void aa() {
        if (this.aj == null) {
            this.aj = i();
        }
    }

    private void ab() {
        Field flingerField = getFlingerField();
        this.o = flingerField;
        if (flingerField == null) {
            Log.e("HwRecyclerView", "mFlingerField: getFlingerField failed!");
        } else {
            e();
        }
    }

    private void ac() {
        if (this.br == null) {
            this.br = new snm();
        }
    }

    private boolean af() {
        return getLayoutDirection() == 1;
    }

    private boolean ag() {
        return this.l.e() && this.n.a();
    }

    private boolean ai() {
        HwLinkedViewCallBack hwLinkedViewCallBack = this.m;
        return hwLinkedViewCallBack != null && hwLinkedViewCallBack.linkedViewHeight() > this.p;
    }

    private boolean egj_(MotionEvent motionEvent) {
        if (!this.bx) {
            return false;
        }
        setScrollStateExtend(1);
        motionEvent.recycle();
        return true;
    }

    private void z() {
        this.d = new smm(new n());
    }

    boolean n() {
        return this.g;
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        float[] fArr = {0.0f, 0.0f};
        if (!c(fArr)) {
            super.onDrawForeground(canvas);
            return;
        }
        float scrollX = getScrollX() - fArr[0];
        float scrollY = getScrollY() - fArr[1];
        canvas.translate(scrollX, scrollY);
        super.onDrawForeground(canvas);
        canvas.translate(-scrollX, -scrollY);
    }

    @Override // android.view.View
    public boolean showContextMenu(float f2, float f3) {
        return d(f2, f3, true);
    }

    /* loaded from: classes9.dex */
    class c {

        /* renamed from: a, reason: collision with root package name */
        boolean f10725a;
        int b;
        int c;
        int d;
        View e;
        int f;
        int g;
        float h;
        smt i;
        ViewGroupOverlay j;
        final /* synthetic */ HwRecyclerView l;
        boolean m;
        boolean n;

        /* JADX INFO: Access modifiers changed from: private */
        public void b(float f) {
            this.h = f;
            if (this.g == 0) {
                this.h = 0.0f;
            }
            smt smtVar = this.i;
            if (smtVar != null) {
                smtVar.setAlpha(((int) this.h) * 255);
            }
            View view = this.e;
            if (view != null) {
                if (this.m) {
                    view.setAlpha(0.0f);
                } else {
                    view.setAlpha(this.h);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int b(int i, int i2) {
            smt smtVar;
            int i3 = this.g;
            this.g = i;
            View view = this.e;
            if (view == null) {
                return 0;
            }
            int top = view.getTop();
            ViewGroupOverlay viewGroupOverlay = this.j;
            if (viewGroupOverlay != null && (smtVar = this.i) != null) {
                int i4 = this.g;
                if (i4 > 0) {
                    if (!this.m) {
                        viewGroupOverlay.add(smtVar);
                        this.m = true;
                        this.e.setAlpha(0.0f);
                    }
                    int i5 = this.f;
                    if (i5 > top) {
                        this.i.d(this.e.getLeft(), top - i2);
                    } else if (i5 < top) {
                        this.i.d(this.e.getLeft(), (i3 - this.g) + top);
                    } else {
                        this.i.d(this.e.getLeft(), top);
                    }
                    this.i.b(0, this.g - this.b);
                    i3 -= this.g;
                } else if (i4 == 0 && this.m) {
                    viewGroupOverlay.remove(smtVar);
                    this.i = null;
                } else {
                    Log.e("HwRecyclerView", "invalid height");
                }
                i2 += i3;
            }
            if (this.g == 0) {
                RecyclerView.ViewHolder childViewHolder = this.l.getChildViewHolder(this.e);
                this.n = childViewHolder.isRecyclable();
                childViewHolder.setIsRecyclable(false);
            }
            this.f = top;
            this.e.getLayoutParams().height = this.g;
            this.e.requestLayout();
            return i2;
        }
    }

    public HwRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(c(context, i2), attributeSet, i2);
        h hVar = null;
        this.ab = null;
        this.ag = null;
        this.ae = -1;
        this.an = -1;
        this.am = null;
        this.aq = true;
        this.av = true;
        this.ax = true;
        this.au = false;
        this.ba = true;
        this.bp = new snj(this);
        this.bu = new Rect();
        this.bt = new Rect();
        this.ca = new HashMap(0);
        this.f10722a = -1;
        this.i = false;
        this.g = false;
        this.n = new i(this, hVar);
        this.l = new b(this, hVar);
        this.q = new OverScroller(getContext(), c);
        this.s = false;
        this.p = Integer.MIN_VALUE;
        this.r = false;
        this.x = false;
        this.v = new int[2];
        this.y = -1;
        this.aa = Integer.MIN_VALUE;
        this.ad = 0L;
        this.ac = null;
        this.af = false;
        this.ai = true;
        this.aj = null;
        this.ak = null;
        this.as = 0;
        this.ap = 8.0f;
        this.at = false;
        this.aw = null;
        this.ay = 0.5f;
        this.bg = 0.6f;
        this.bo = Integer.MIN_VALUE;
        this.bq = Integer.MIN_VALUE;
        this.bw = false;
        this.bs = new m();
        this.j = new l();
        efK_(super.getContext(), attributeSet, i2);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.w = viewConfiguration.getScaledMaximumFlingVelocity();
        this.u = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    private boolean g(int i2) {
        return this.ap >= 0.0f && this.as > 0 && i2 < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        boolean z = !this.s && this.q.getCurrVelocity() > 0.0f;
        boolean z2 = this.t == 1 && Math.abs(this.aa) >= this.u;
        boolean z3 = getTranslationY() == 0.0f;
        boolean z4 = !canScrollVertically(-1) && this.aa < 0;
        boolean z5 = !canScrollVertically(1) && this.aa > 0;
        boolean z6 = z && z2 && z3;
        boolean z7 = z4 || z5;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && this.x && layoutManager.canScrollVertically()) {
            if (!aj()) {
                this.q.computeScrollOffset();
                this.k = this.q.getCurrY();
            } else if (z6 && z7) {
                this.s = true;
                efQ_(this.q, this.aa <= 0 ? -1 : 1);
            }
        }
    }

    private void efZ_(Context context, AttributeSet attributeSet, int i2) {
        if (attributeSet == null) {
            Log.w("HwRecyclerView", "Attribute set is null");
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.choiceMode, R.attr._2131100422_res_0x7f060306, R.attr._2131100423_res_0x7f060307, R.attr._2131100424_res_0x7f060308, R.attr._2131100478_res_0x7f06033e, R.attr._2131100479_res_0x7f06033f, R.attr._2131100480_res_0x7f060340, R.attr._2131100485_res_0x7f060345, R.attr._2131100520_res_0x7f060368}, i2, 0);
        this.bf = obtainStyledAttributes.getInt(8, 1);
        int i3 = obtainStyledAttributes.getInt(0, 0);
        if (i3 != 0) {
            setChoiceMode(i3);
        }
        obtainStyledAttributes.recycle();
        HwGenericEventDetector a2 = a();
        this.ah = a2;
        if (a2 != null) {
            setSensitivityMode(this.bf);
            this.ah.e(this.bg);
            this.ah.eit_(this, g());
            this.ah.b(h());
        }
    }

    private void egi_(MotionEvent motionEvent) {
        if (this.i || !this.bd || motionEvent == null) {
            return;
        }
        int y = (int) motionEvent.getY(motionEvent.getActionIndex());
        double applyDimension = TypedValue.applyDimension(1, 90.0f, getContext().getResources().getDisplayMetrics());
        double d2 = y;
        if (getHeight() - applyDimension < d2) {
            this.bi = true;
            this.bl = true;
            f(c(false, y));
        } else if (d2 < applyDimension) {
            this.bi = true;
            this.bl = true;
            f(c(true, y));
        } else if (this.bi) {
            an();
        }
    }

    private void v() {
        ValueAnimator valueAnimator = this.by;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.by.cancel();
        }
        i iVar = this.n;
        if (iVar != null) {
            iVar.d();
        }
    }

    private void ad() {
        List<c> list = this.ag;
        if (list != null) {
            list.clear();
        }
        bqmxo bqmxoVar = this.bc;
        if (bqmxoVar != null) {
            bqmxoVar.e();
        }
        this.ae = -1;
        this.an = -1;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view, float f2, float f3) {
        return efX_(view, f2, f3, true);
    }

    private static Context c(Context context, int i2) {
        return smr.b(context, i2, R.style.Theme_Emui_HwRecyclerView);
    }

    private void egf_(MotionEvent motionEvent) {
        egl_(motionEvent);
        am();
    }

    private void efK_(Context context, AttributeSet attributeSet, int i2) {
        efZ_(context, attributeSet, i2);
        if (isInEditMode() && super.getLayoutManager() == null) {
            super.setLayoutManager(new LinearLayoutManager(context, 1, false));
        }
        this.bb = true;
        this.ar = context.getResources().getDisplayMetrics().densityDpi;
        this.f = ViewConfiguration.get(context).getScaledTouchSlop();
        this.bp.eiZ_(context, attributeSet);
        z();
        ab();
        setValueFromPlume(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.choiceMode, R.attr._2131100422_res_0x7f060306, R.attr._2131100423_res_0x7f060307, R.attr._2131100424_res_0x7f060308, R.attr._2131100478_res_0x7f06033e, R.attr._2131100479_res_0x7f06033f, R.attr._2131100480_res_0x7f060340, R.attr._2131100485_res_0x7f060345, R.attr._2131100520_res_0x7f060368}, i2, R.style.Widget_Emui_HwRecyclerView);
        this.bx = obtainStyledAttributes.getBoolean(1, false);
        float f2 = obtainStyledAttributes.getFloat(3, 0.125f);
        float f3 = obtainStyledAttributes.getFloat(2, 0.125f);
        obtainStyledAttributes.recycle();
        if (this.bx) {
            smg smgVar = new smg(this);
            this.e = smgVar;
            smgVar.e(f2);
            this.e.a(f3);
        }
    }

    private void egd_(int i2, MotionEvent motionEvent) {
        this.f10722a = motionEvent.getPointerId(i2);
        this.b = (int) motionEvent.getRawX();
        this.h = (int) motionEvent.getRawY();
        int i3 = getTranslationY() > 0.0f ? 1 : -1;
        if ((getTranslationY() > 0.0f && a(i3)) || getTranslationX() > 0.0f) {
            this.g = true;
            this.i = true;
        } else if ((getTranslationY() < 0.0f && a(i3)) || getTranslationX() < 0.0f) {
            this.g = true;
            this.i = true;
        } else {
            this.g = false;
            this.i = false;
        }
        if (this.be == null) {
            this.be = VelocityTracker.obtain();
        }
        v();
        this.be.clear();
        this.be.addMovement(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        if (m()) {
            this.bv.onScrolled(this, 0, i2);
        } else {
            this.bv.onScrolled(this, i2, 0);
        }
    }

    private boolean c(int i2) {
        e eVar;
        if (this.bi && i2 == 1) {
            this.bi = false;
            an();
        }
        if ((!this.bi || i2 == 1) && (eVar = this.bn) != null) {
            eVar.d();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: egh_, reason: merged with bridge method [inline-methods] */
    public void ege_(ValueAnimator valueAnimator) {
        int i2;
        if (valueAnimator == null) {
            Log.e("HwRecyclerView", "getHeightListener: onAnimationUpdate: animation is null");
            return;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int size = this.ag.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            c cVar = this.ag.get(i4);
            if (cVar.f10725a) {
                int i5 = cVar.d;
                if (i5 > 0 && (i2 = cVar.b) > 0) {
                    int i6 = (int) (i5 * floatValue);
                    int i7 = cVar.c;
                    if (i6 > i7) {
                        if (cVar.e == null) {
                            Log.e("HwRecyclerView", "getHeightListener: view is null.");
                        } else {
                            int i8 = (i7 + i2) - i6;
                            if (i8 > 0) {
                                i3 = cVar.b(i8, i3);
                            } else if (cVar.g > 0) {
                                i3 = cVar.b(0, i3);
                            }
                        }
                    }
                } else {
                    Log.e("HwRecyclerView", "getHeightListener: mHeightTotal or mHeightOriginal is invalid.");
                }
            }
        }
        Runnable runnable = this.am;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void egg_(MotionEvent motionEvent) {
        int rawY = (int) motionEvent.getRawY();
        int rawX = (int) motionEvent.getRawX();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = layoutManager.canScrollVertically();
        if (Math.abs(rawY - this.h) > this.f && canScrollVertically) {
            this.g = true;
        }
        if (Math.abs(rawX - this.b) <= this.f || !canScrollHorizontally) {
            return;
        }
        this.g = true;
    }

    private void ega_(MotionEvent motionEvent, int i2, MotionEvent motionEvent2) {
        this.y = motionEvent.getPointerId(0);
        v();
        egd_(i2, motionEvent2);
        if (!motionEvent.isFromSource(8194) || (motionEvent.getButtonState() & 2) == 0) {
            return;
        }
        showContextMenu(motionEvent.getX(), motionEvent.getY());
    }

    private void b(int i2) {
        if (i2 < 0 && !canScrollVertically(1) && this.ax) {
            o();
        } else if (i2 > 0 && !canScrollVertically(-1) && this.av) {
            o();
        } else {
            Log.d("HwRecyclerView", "do nothing.");
        }
    }

    private boolean egb_(int i2, MotionEvent motionEvent) {
        int b2 = b(i2, this.h);
        if (this.g && this.aq) {
            if (egc_(motionEvent)) {
                return true;
            }
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (computeVerticalScrollRange() != 0 && e(b2, i2)) {
                float translationY = getTranslationY();
                float d2 = d(b2, true);
                int i3 = (int) d2;
                if (d(translationY, i3)) {
                    setIntegerTranslationY(0.0f);
                    if (this.bv != null) {
                        i((int) translationY);
                    }
                    scrollBy(0, -i3);
                    k();
                    setScrollStateExtend(1);
                    invalidate();
                    this.v[1] = (int) (r8[1] - translationY);
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(0);
                    return super.onTouchEvent(obtain);
                }
                this.h = i2;
                float abs = Math.abs(getTranslationY());
                setIntegerTranslationY(d2);
                HwChainAnimationListener hwChainAnimationListener = this.bv;
                if (hwChainAnimationListener != null) {
                    hwChainAnimationListener.onOverScroll(b2, (int) abs);
                }
                this.v[1] = (int) (r8[1] - (translationY - d2));
                e(d2);
                invalidate();
                return true;
            }
            this.h = i2;
        }
        return false;
    }

    private void d(int i2, MotionEvent motionEvent) {
        e eVar;
        VelocityTracker velocityTracker = this.be;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
            this.be.computeCurrentVelocity(1000, this.w);
        }
        if (i2 < 0) {
            return;
        }
        if (!this.bi && (eVar = this.bn) != null) {
            eVar.d();
        }
        if (getLayoutManager() == null) {
            return;
        }
        r();
        this.g = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void efR_(OverScroller overScroller, int i2, int i3, long j2) {
        if (this.aq && this.ba) {
            if (i3 >= 0 || this.av) {
                if (i3 <= 0 || this.ax) {
                    if (i2 >= 0 || this.av) {
                        if (i2 <= 0 || this.ax) {
                            float currVelocity = overScroller.getCurrVelocity();
                            if (Float.isNaN(currVelocity)) {
                                return;
                            }
                            RecyclerView.LayoutManager layoutManager = getLayoutManager();
                            if (layoutManager == null) {
                                Log.e("HwRecyclerView", "startOverFling: call getLayoutManager failed");
                                return;
                            }
                            if (layoutManager.canScrollHorizontally()) {
                                if (i2 < 0) {
                                    currVelocity = -currVelocity;
                                }
                                this.n.c(0, -currVelocity, 0, 0, 0L);
                                overScroller.abortAnimation();
                            }
                            if (layoutManager.canScrollVertically()) {
                                if (i3 < 0) {
                                    currVelocity = -currVelocity;
                                }
                                this.n.c(1, -currVelocity, 0, 0, j2);
                                overScroller.abortAnimation();
                            }
                        }
                    }
                }
            }
        }
    }

    private void f(int i2) {
        if (this.bn == null) {
            this.bn = new e(this, null);
        }
        this.bn.b(i2);
    }

    private boolean egc_(MotionEvent motionEvent) {
        if (canScrollVertically(1) && getTranslationY() < 0.0f && this.i) {
            efM_(motionEvent);
            return true;
        }
        if (!(getLayoutManager() instanceof HwFloatingBubblesLayoutManager) || !canScrollVertically(-1) || getTranslationY() <= 0.0f || !this.i) {
            return false;
        }
        efM_(motionEvent);
        return true;
    }

    private void efQ_(OverScroller overScroller, int i2) {
        if (!ag()) {
            as();
        }
        OverScroller overScroller2 = getOverScroller();
        if (overScroller2 != null) {
            overScroller2.abortAnimation();
        }
        startNestedScroll(2, 0);
        this.l.egm_(overScroller, i2);
    }

    private void efP_(View view, int i2) {
        Rect rect;
        if (view == null) {
            return;
        }
        int layoutDirection = getLayoutDirection();
        if (view.getLayoutDirection() != layoutDirection) {
            view.setLayoutDirection(layoutDirection);
        }
        Rect rect2 = this.ca.get(Integer.valueOf(i2));
        if (rect2 == null) {
            rect = new Rect(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            this.ca.put(Integer.valueOf(i2), rect);
            rect2 = rect;
        } else {
            rect = new Rect(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        Rect eiX_ = this.bp.eiX_(this, rect2);
        if (eiX_ == null) {
            eiX_ = new Rect(rect2);
            Log.w("HwRecyclerView", "HwWidgetSafeInsets.getDisplaySafeInsets() is null!");
        }
        Rect rect3 = new Rect(eiX_.left, view.getPaddingTop(), eiX_.right, view.getPaddingBottom());
        if (rect.equals(rect3)) {
            return;
        }
        this.bp.eiV_(view, rect3, false);
    }

    private void u() {
        HwLinkedViewCallBack hwLinkedViewCallBack = this.m;
        if (hwLinkedViewCallBack == null) {
            this.x = false;
        } else if (hwLinkedViewCallBack.linkedViewState() != -1 && getOverScrollMode() != 2 && isNestedScrollingEnabled()) {
            this.x = true;
        } else {
            this.x = false;
        }
    }

    private void efN_(MotionEvent motionEvent, int i2, int i3) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    if (getLayoutManager() == null) {
                        return;
                    }
                    egg_(motionEvent);
                    return;
                } else if (i2 != 3) {
                    if (i2 == 5) {
                        this.y = motionEvent.getPointerId(i3);
                        return;
                    } else {
                        if (i2 != 6) {
                            return;
                        }
                        egl_(motionEvent);
                        return;
                    }
                }
            }
            this.f10722a = -1;
            if (this.g) {
                return;
            }
            r();
            return;
        }
        this.y = motionEvent.getPointerId(0);
        egd_(i3, motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void efY_(ValueAnimator valueAnimator) {
        if (valueAnimator == null) {
            Log.e("HwRecyclerView", "getAlphaListener: onAnimationUpdate: animation is null");
            return;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int size = this.ag.size();
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = this.ag.get(i2);
            if (cVar.f10725a) {
                View view = cVar.e;
                if (view != null) {
                    view.setAlpha(floatValue);
                }
                cVar.b(floatValue);
            }
        }
    }

    private boolean efV_(MotionEvent motionEvent, int i2) {
        if (c(i2)) {
            return true;
        }
        if (motionEvent.getActionMasked() != 2) {
            return false;
        }
        egi_(motionEvent);
        return false;
    }

    private boolean a(MotionEvent motionEvent, int i2, int i3, MotionEvent motionEvent2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 5) {
                            efO_(motionEvent, i3, motionEvent2);
                        } else if (i2 == 6) {
                            egf_(motionEvent2);
                        }
                    }
                } else {
                    if (egj_(motionEvent2)) {
                        return false;
                    }
                    if (efU_(i3, motionEvent, motionEvent2)) {
                        motionEvent2.recycle();
                        return true;
                    }
                }
            } else if (efW_(motionEvent, motionEvent2)) {
                return true;
            }
            d(i3, motionEvent2);
        } else {
            ega_(motionEvent, i3, motionEvent2);
            if (this.bx) {
                this.e.efE_(motionEvent);
            }
        }
        motionEvent2.recycle();
        GestureDetector gestureDetector = this.z;
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private boolean efW_(MotionEvent motionEvent, MotionEvent motionEvent2) {
        if (!this.bx) {
            return false;
        }
        this.e.efF_(motionEvent);
        setScrollStateExtend(0);
        motionEvent2.recycle();
        return true;
    }

    private void e(int i2) {
        if (i2 == 0) {
            int[] iArr = this.v;
            iArr[0] = 0;
            iArr[1] = 0;
        }
    }

    private void efO_(MotionEvent motionEvent, int i2, MotionEvent motionEvent2) {
        this.y = motionEvent.getPointerId(i2);
        v();
        egd_(i2, motionEvent2);
    }

    private boolean efU_(int i2, MotionEvent motionEvent, MotionEvent motionEvent2) {
        if (motionEvent.getButtonState() == 2 || i2 < 0 || getLayoutManager() == null) {
            return false;
        }
        VelocityTracker velocityTracker = this.be;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent2);
        }
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        if (this.h == 0 && this.b == 0) {
            this.h = rawY;
            this.b = rawX;
        }
        boolean canScrollHorizontally = getLayoutManager().canScrollHorizontally();
        if (getLayoutManager().canScrollVertically()) {
            return egb_(rawY, motionEvent);
        }
        if (canScrollHorizontally) {
            return a(rawX, motionEvent);
        }
        return false;
    }

    private boolean a(int i2, MotionEvent motionEvent) {
        if (this.g && this.aq) {
            if (y() && this.i) {
                int i3 = -((int) getTranslationX());
                setTranslationX(0.0f);
                scrollBy(i3, 0);
                k();
                MotionEvent obtain = MotionEvent.obtain(motionEvent.getEventTime(), motionEvent.getEventTime(), 0, motionEvent.getX() - i3, motionEvent.getY(), 0);
                obtain.setAction(0);
                super.onTouchEvent(obtain);
                return true;
            }
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            int b2 = b(i2, this.b);
            if (computeHorizontalScrollRange() != 0 && d(b2)) {
                float translationX = getTranslationX();
                float d2 = d(b2, false);
                int i4 = (int) d2;
                if (d(translationX, i4)) {
                    setIntegerTranslationX(0.0f);
                    if (this.bv != null) {
                        i((int) translationX);
                    }
                    scrollBy(-i4, 0);
                    k();
                    setScrollStateExtend(1);
                    invalidate();
                    MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                    obtain2.setAction(0);
                    return super.onTouchEvent(obtain2);
                }
                this.b = i2;
                float abs = Math.abs(getTranslationX());
                setIntegerTranslationX(d2);
                HwChainAnimationListener hwChainAnimationListener = this.bv;
                if (hwChainAnimationListener != null) {
                    hwChainAnimationListener.onOverScroll(b2, (int) abs);
                }
                e(d2);
                invalidate();
                return true;
            }
            this.b = i2;
        }
        return false;
    }

    private boolean w() {
        int startAfterPadding = this.ak.getStartAfterPadding();
        int childCount = getChildCount();
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt != null) {
                if (this.ak.getDecoratedStart(childAt) < startAfterPadding) {
                    return true;
                }
                int childAdapterPosition = getChildAdapterPosition(childAt);
                if (i2 > childAdapterPosition) {
                    i2 = childAdapterPosition;
                }
            }
        }
        return i2 > 0;
    }

    private boolean y() {
        if (af()) {
            if (canScrollHorizontally(-1) && getTranslationX() > 0.0f) {
                return true;
            }
        } else if (canScrollHorizontally(1) && getTranslationX() < 0.0f) {
            return true;
        }
        return false;
    }

    private void efM_(MotionEvent motionEvent) {
        int i2 = -((int) getTranslationY());
        setTranslationY(0.0f);
        scrollBy(0, i2);
        k();
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getEventTime(), motionEvent.getEventTime(), 0, motionEvent.getX(), motionEvent.getY() - i2, 0);
        obtain.setAction(0);
        super.onTouchEvent(obtain);
    }

    private int b(int i2, int i3) {
        int i4 = i2 - i3;
        if (this.g) {
            return i4;
        }
        int abs = Math.abs(i4);
        int i5 = this.f;
        if (abs <= i5) {
            return i4;
        }
        this.g = true;
        return i4 > 0 ? i4 - i5 : i4 + i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2, float f2) {
        if (i2 == 1) {
            setIntegerTranslationY(f2);
        } else {
            setIntegerTranslationX(f2);
        }
    }

    private int c(boolean z, int i2) {
        int height = getHeight();
        int applyDimension = (int) TypedValue.applyDimension(1, 90.0f, getContext().getResources().getDisplayMetrics());
        double applyDimension2 = TypedValue.applyDimension(1, 4.0f, getContext().getResources().getDisplayMetrics());
        double applyDimension3 = TypedValue.applyDimension(1, 0.0f, getContext().getResources().getDisplayMetrics());
        if (z) {
            double d2 = i2 + applyDimension3;
            double doubleValue = BigDecimal.valueOf(height).divide(BigDecimal.valueOf((applyDimension2 / (((d2 > 0.0d ? d2 / applyDimension : 0.0d) * 38.0d) + 10.0d)) * 300.0d), 6, 4).doubleValue();
            if (doubleValue != 1.0d) {
                return -((int) (height / (doubleValue - 1.0d)));
            }
            return 0;
        }
        double doubleValue2 = BigDecimal.valueOf(height).divide(BigDecimal.valueOf((applyDimension2 / (((((double) (getHeight() - i2)) + applyDimension3 > 0.0d ? ((getHeight() - i2) + applyDimension3) / applyDimension : 0.0d) * 38.0d) + 10.0d)) * 300.0d), 6, 4).doubleValue();
        if (doubleValue2 != 1.0d) {
            return (int) (height / (doubleValue2 - 1.0d));
        }
        return 0;
    }

    private float c(int i2, float f2, int i3) {
        return i2 * new bnm(i3).getRate(f2);
    }

    private boolean b(final String str, float f2, float f3) {
        float abs;
        DynamicAnimation.ViewProperty viewProperty;
        if ("translationY".equals(str)) {
            abs = Math.abs(getTranslationY());
            viewProperty = DynamicAnimation.TRANSLATION_Y;
        } else {
            abs = Math.abs(getTranslationX());
            viewProperty = DynamicAnimation.TRANSLATION_X;
        }
        DynamicAnimation.ViewProperty viewProperty2 = viewProperty;
        if (Float.compare(abs, 0.0f) == 0) {
            return false;
        }
        ValueAnimator valueAnimator = this.by;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if ("translationY".equals(str)) {
            this.by = ValueAnimator.ofFloat(getTranslationY(), f3);
        } else {
            this.by = ValueAnimator.ofFloat(getTranslationX(), f3);
        }
        this.by.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: smi
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                HwRecyclerView.this.efT_(str, valueAnimator2);
            }
        });
        bno bnoVar = new bno(viewProperty2, !f() ? 228.0f : 200.0f, !f() ? 30.0f : 28.0f, Math.abs(abs), f2);
        this.by.setInterpolator(bnoVar);
        this.by.setDuration((long) bnoVar.getDuration());
        this.by.addListener(new d());
        a(this.by);
        this.by.start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void efT_(String str, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if ("translationY".equals(str)) {
            setIntegerTranslationY(floatValue);
        } else {
            setIntegerTranslationX(floatValue);
        }
        invalidate();
    }

    private void a(ValueAnimator valueAnimator) {
        valueAnimator.addUpdateListener(new j());
    }

    private ContextMenu.ContextMenuInfo a(View view, int i2, long j2) {
        return new AdapterView.AdapterContextMenuInfo(view, i2, j2);
    }

    private boolean d(float f2, float f3, boolean z) {
        View findChildViewUnder = findChildViewUnder(f2, f3);
        int childAdapterPosition = findChildViewUnder != null ? getChildAdapterPosition(findChildViewUnder) : -1;
        this.ac = null;
        if (childAdapterPosition != -1 && findChildViewUnder != null) {
            this.ac = a(findChildViewUnder, childAdapterPosition, getChildItemId(findChildViewUnder));
            if (z && this.bb) {
                this.af = true;
                return super.showContextMenuForChild(this, f2, f3);
            }
            return super.showContextMenuForChild(this);
        }
        Log.e("HwRecyclerView", "position: invalid position");
        if (z && this.bb) {
            return super.showContextMenu(f2, f3);
        }
        return super.showContextMenu();
    }

    private boolean efX_(View view, float f2, float f3, boolean z) {
        View efJ_ = efJ_(view);
        int childAdapterPosition = efJ_ == null ? -1 : getChildAdapterPosition(efJ_);
        this.ac = null;
        if (childAdapterPosition >= 0) {
            this.ac = a(efJ_, childAdapterPosition, getChildItemId(efJ_));
        } else {
            Log.e("HwRecyclerView", "longPressPosition: invalid longPressPosition");
        }
        if (z && this.bb) {
            this.af = true;
            return super.showContextMenuForChild(view, f2, f3);
        }
        return super.showContextMenuForChild(view);
    }

    private View efJ_(View view) {
        if (view == null || !(view.getParent() instanceof View)) {
            return null;
        }
        View view2 = (View) view.getParent();
        while (view2 != null && !view2.equals(this)) {
            if (!(view2.getParent() instanceof View)) {
                return null;
            }
            View view3 = view2;
            view2 = (View) view2.getParent();
            view = view3;
        }
        return view;
    }

    private boolean d(RecyclerView.Adapter adapter) {
        int endAfterPadding = this.ak.getEndAfterPadding();
        int i2 = Integer.MIN_VALUE;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null) {
                if (this.ak.getDecoratedEnd(childAt) > endAfterPadding) {
                    return true;
                }
                int childAdapterPosition = getChildAdapterPosition(childAt);
                if (i2 < childAdapterPosition) {
                    i2 = childAdapterPosition;
                }
            }
        }
        return i2 < adapter.getItemCount() - 1;
    }

    private boolean c(float[] fArr) {
        float translationX;
        float f2;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (!this.aq || !this.au || layoutManager == null) {
            return false;
        }
        if (layoutManager.canScrollVertically()) {
            f2 = getTranslationY();
            translationX = 0.0f;
        } else {
            translationX = getTranslationX();
            f2 = 0.0f;
        }
        if (Float.compare(translationX, 0.0f) == 0 && Float.compare(f2, 0.0f) == 0) {
            return false;
        }
        fArr[0] = translationX;
        fArr[1] = f2;
        return true;
    }

    private void efL_(Canvas canvas) {
        Drawable background;
        float[] fArr = {0.0f, 0.0f};
        if (!c(fArr) || (background = getBackground()) == null) {
            return;
        }
        float scrollX = getScrollX() - fArr[0];
        float scrollY = getScrollY() - fArr[1];
        canvas.translate(scrollX, scrollY);
        background.draw(canvas);
        canvas.translate(-scrollX, -scrollY);
    }
}
