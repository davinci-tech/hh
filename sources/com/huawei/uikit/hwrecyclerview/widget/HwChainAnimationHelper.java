package com.huawei.uikit.hwrecyclerview.widget;

import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.animation.physical2.SimpleSpringChain;
import com.huawei.animation.physical2.SimpleSpringNodeEx;
import com.huawei.animation.physical2.SpringNode;
import defpackage.abm;
import defpackage.abn;
import defpackage.abq;

/* loaded from: classes9.dex */
public class HwChainAnimationHelper extends HwChainAnimationListener implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10692a;
    private HwRecyclerView b;
    private int c;
    private int d;
    private RecyclerView.LayoutManager e;
    private abm f;
    private int g;
    private boolean h;
    private float i;
    private float j;
    private abn k;
    private float l;
    private float m;
    private SimpleSpringChain n;
    private float o;
    private SparseArray<Float> p;
    private int q;
    private SparseArray<Float> r;
    private float s;
    private int t;
    private int w;
    private boolean y;

    class a extends SimpleSpringNodeEx {
        a(int i) {
            super(i);
        }

        @Override // com.huawei.animation.physical2.SpringNode
        public void onUpdate(float f, float f2) {
            if (HwChainAnimationHelper.this.h) {
                HwChainAnimationHelper.this.b.invalidate();
                HwChainAnimationHelper.this.h = false;
            }
            HwChainAnimationHelper.this.r.put(getIndex() - HwChainAnimationHelper.this.f.a(), Float.valueOf(f));
        }
    }

    private boolean k() {
        return !this.f10692a ? this.b.getTranslationX() <= 0.0f : this.b.getTranslationY() <= 0.0f;
    }

    private void l() {
        this.p.clear();
        this.s = 0.0f;
    }

    private void m() {
        if (e()) {
            this.d = this.c;
            boolean k = k();
            View eeM_ = eeM_(k);
            int position = eeM_ == null ? this.c : this.e.getPosition(eeM_);
            this.c = position;
            if (this.w != 2) {
                if (this.d != position) {
                    b();
                }
            } else {
                e(this.p.get(0, Float.valueOf(n())).floatValue());
                a(k);
                l();
                this.w = 1;
            }
        }
    }

    private float n() {
        return this.f10692a ? this.b.getTranslationY() : this.b.getTranslationX();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0023, code lost:
    
        if (r0 == 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void o() {
        /*
            r4 = this;
            int r0 = r4.h()
            if (r0 > 0) goto L7
            goto L25
        L7:
            boolean r1 = r4.f10692a
            if (r1 == 0) goto L12
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r4.e
            int r1 = r1.getHeight()
            goto L18
        L12:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r1 = r4.e
            int r1 = r1.getWidth()
        L18:
            float r1 = (float) r1
            float r0 = (float) r0
            float r1 = r1 / r0
            double r0 = (double) r1
            double r0 = java.lang.Math.ceil(r0)
            int r0 = (int) r0
            int r0 = r0 * 2
            if (r0 != 0) goto L27
        L25:
            r0 = 20
        L27:
            abq r1 = new abq
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r1.<init>(r2)
            r2 = 1800(0x708, float:2.522E-42)
            r3 = 1072466756(0x3fec8b44, float:1.848)
            abn r0 = defpackage.abn.c(r0, r2, r3, r1)
            r4.k = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationHelper.o():void");
    }

    private void q() {
        int childCount = this.e.getChildCount();
        if (this.r.size() <= 0) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = this.e.getChildAt(i);
            if (childAt != null) {
                int position = this.e.getPosition(childAt) - this.c;
                eeO_(childAt, (this.r.indexOfKey(position) < 0 ? -this.g : this.r.get(position).floatValue()) + this.g);
            }
        }
    }

    protected boolean a() {
        return true;
    }

    public void b() {
        this.r.clear();
        this.h = true;
        this.n.d(this.c - this.d);
        if (this.r.indexOfKey(0) < 0) {
            Log.w("HwChainHelper", "relocate: control node hasn't data.");
            return;
        }
        int round = Math.round(this.r.get(0).floatValue());
        int i = this.g + round;
        if (i != 0) {
            if (!e()) {
                if (this.f10692a) {
                    this.b.scrollBy(0, -i);
                    return;
                } else {
                    this.b.scrollBy(-i, 0);
                    return;
                }
            }
            float n = i + n();
            this.g = -round;
            if (c((int) n)) {
                n = 0.0f;
            }
            e(n);
        }
    }

    public void c() {
        if (this.n == null) {
            this.r.clear();
            int j = j();
            Log.i("HwChainHelper", "init SpringChain: nodes:" + j);
            d(j);
        }
        if (this.k == null) {
            this.p.clear();
            o();
        }
    }

    public boolean d() {
        HwRecyclerView hwRecyclerView = this.b;
        return hwRecyclerView != null && hwRecyclerView.n();
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationListener
    public void dispatchGenericMotionEvent(MotionEvent motionEvent) {
        this.y = motionEvent != null && motionEvent.getAction() == 8;
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationListener
    public void dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            Log.w("HwChainHelper", "dispatchTouchEvent: event is null.");
            return;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            eeN_(motionEvent);
        } else if (action == 1 || action == 3) {
            m();
        }
    }

    public boolean e() {
        return !this.f10692a ? this.b.getTranslationX() == 0.0f : this.b.getTranslationY() == 0.0f;
    }

    protected View eeQ_(float f, float f2) {
        int childCount = this.e.getChildCount();
        float height = this.e.getHeight();
        int i = 0;
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            View childAt = this.e.getChildAt(i2);
            if (childAt != null) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof RecyclerView.LayoutParams) {
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    float translationX = childAt.getTranslationX();
                    float translationY = childAt.getTranslationY();
                    float decoratedLeft = (this.e.getDecoratedLeft(childAt) + translationX) - layoutParams2.leftMargin;
                    float decoratedRight = this.e.getDecoratedRight(childAt) + translationX + layoutParams2.rightMargin;
                    float decoratedTop = (this.e.getDecoratedTop(childAt) + translationY) - layoutParams2.topMargin;
                    float decoratedBottom = this.e.getDecoratedBottom(childAt) + translationY + layoutParams2.bottomMargin;
                    if (f >= decoratedLeft && f <= decoratedRight && f2 >= decoratedTop && f2 <= decoratedBottom) {
                        return childAt;
                    }
                    if (this.f10692a) {
                        float f3 = (((int) (decoratedBottom + decoratedTop)) / 2) - f2;
                        if (height > Math.abs(f3)) {
                            height = Math.abs(f3);
                            i = i2;
                        }
                    } else {
                        float f4 = (((int) (decoratedLeft + decoratedRight)) / 2) - f;
                        if (height > Math.abs(f4)) {
                            height = Math.abs(f4);
                            i = i2;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return this.e.getChildAt(i);
    }

    public void i() {
        if (this.w != 1) {
            return;
        }
        q();
        this.h = true;
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationListener
    public void onAttachedToWindow() {
        this.b.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationListener
    public void onDetachedFromWindow() {
        this.b.getViewTreeObserver().removeOnPreDrawListener(this);
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwChainAnimationListener
    public void onOverScroll(float f, float f2) {
        HwRecyclerView hwRecyclerView = this.b;
        if (hwRecyclerView == null || hwRecyclerView.f()) {
            if (this.y) {
                this.y = false;
                return;
            }
            if (this.n == null || this.k == null) {
                Log.w("HwChainHelper", "onOverScroll: mSpringChain or mCurveChain is null");
                c();
            }
            int i = this.w;
            if (i != 2) {
                if (i == 1) {
                    f();
                }
                l();
            }
            this.w = 2;
            a(f, f2);
        }
    }

    public boolean onPreDraw() {
        c();
        if ((e() && this.b.n()) || !this.b.f()) {
            return true;
        }
        i();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.y) {
            this.y = false;
            return;
        }
        if (i == 0 && i2 == 0) {
            return;
        }
        if (this.n == null) {
            Log.w("HwChainHelper", "onScrolled: spring chain is null.");
            c();
        }
        int i3 = this.g;
        if (this.f10692a) {
            i = i2;
        }
        this.g = i3 + i;
        this.n.b(-r2);
        this.w = 1;
    }

    private void a(int i) {
        this.f = new abm();
        for (int i2 = 0; i2 < i; i2++) {
            this.f.e(e(i2));
        }
    }

    private void d(int i) {
        a(i);
        this.n = new SimpleSpringChain(this.f).b(new abq(this.j)).e(new abq(this.i)).d(this.m).a(this.l).e(this.o).b(this.t, this.q).a();
    }

    private int j() {
        int finalX;
        int width;
        OverScroller overScroller = new OverScroller(this.b.getContext());
        if (this.f10692a) {
            overScroller.fling(0, 0, 0, this.b.getMaxFlingVelocity(), Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            overScroller.fling(0, 0, this.b.getMaxFlingVelocity(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        overScroller.forceFinished(true);
        int childCount = this.e.getChildCount();
        if (childCount == 0) {
            Log.w("HwChainHelper", "getNodesNum: child count is 0.");
            return 101;
        }
        View childAt = this.e.getChildAt(0);
        View childAt2 = this.e.getChildAt(childCount - 1);
        if (childAt == null || childAt2 == null) {
            Log.w("HwChainHelper", "getNodesNum: firstView or lastView is null.");
            return 101;
        }
        int h = h();
        if (h <= 0 || h == this.e.getHeight()) {
            return 101;
        }
        if (this.f10692a) {
            finalX = overScroller.getFinalY();
            width = this.e.getHeight();
        } else {
            finalX = overScroller.getFinalX();
            width = this.e.getWidth();
        }
        return ((((int) Math.ceil((finalX + width) / h)) + 1 + childCount) * 2) + 1;
    }

    private SimpleSpringNodeEx e(int i) {
        a aVar = new a(i);
        aVar.setFixMode(0);
        aVar.setValueAccuracy(0.2f);
        return aVar;
    }

    private void a(boolean z) {
        int size = this.p.size();
        int a2 = this.f.a();
        for (int i = 0; i < size; i++) {
            int i2 = z ? i : (this.c - 1) - i;
            int i3 = (a2 + i2) - this.c;
            SpringNode node = this.f.getNode(i3);
            if (node instanceof SimpleSpringNodeEx) {
                SimpleSpringNodeEx simpleSpringNodeEx = (SimpleSpringNodeEx) node;
                simpleSpringNodeEx.resetNode((simpleSpringNodeEx.getValue() + this.p.valueAt(i).floatValue()) - n(), 0.0f);
            } else {
                Log.w("HwChainHelper", "transferData: index = " + i2 + ", nodeIndex = " + i3 + ", isOverStartEdge = " + z);
            }
        }
    }

    private int h() {
        int height = this.e.getHeight();
        for (int i = 0; i < this.e.getChildCount(); i++) {
            View childAt = this.e.getChildAt(i);
            if (childAt != null) {
                if (this.f10692a && childAt.getHeight() < height) {
                    height = childAt.getHeight();
                } else if (!this.f10692a && childAt.getWidth() < height) {
                    height = childAt.getWidth();
                }
            }
        }
        return height;
    }

    private void eeN_(MotionEvent motionEvent) {
        this.w = 0;
        View eeQ_ = eeQ_(motionEvent.getX(), motionEvent.getY());
        if (eeQ_ == null) {
            Log.w("HwChainHelper", "onTouchDown: control item is null.");
            this.c = -1;
            return;
        }
        this.d = this.c;
        this.c = this.b.getChildLayoutPosition(eeQ_);
        if (this.n == null || this.k == null) {
            Log.w("HwChainHelper", "onTouchDown: mSpringChain or mCurveChain is null:");
            c();
        }
        int i = this.d;
        if (i != -1 && i != this.c && this.n.c() && a()) {
            b();
        }
        SpringNode b = this.n.b();
        if (b != null) {
            b.cancel();
        }
    }

    private void f() {
        this.n.e();
        this.g = 0;
        int size = this.f.getSize();
        for (int i = 0; i < size; i++) {
            SpringNode node = this.f.getNode(i);
            if (node != null) {
                node.resetValue(0.0f);
            }
        }
        this.r.clear();
    }

    private boolean c(int i) {
        if (!this.f10692a) {
            return g();
        }
        if (!this.b.canScrollVertically(1) || i >= 0) {
            return this.b.canScrollVertically(-1) && i > 0;
        }
        return true;
    }

    private View eeM_(boolean z) {
        if (z) {
            return this.e.getChildAt(0);
        }
        return this.e.getChildAt(r2.getChildCount() - 1);
    }

    private boolean g() {
        if (this.b.getLayoutDirection() == 1) {
            if (this.b.canScrollHorizontally(-1) && this.b.getTranslationX() > 0.0f) {
                return true;
            }
        } else if (this.b.canScrollHorizontally(1) && this.b.getTranslationX() < 0.0f) {
            return true;
        }
        return false;
    }

    private void a(float f, float f2) {
        boolean k = k();
        int childCount = this.e.getChildCount();
        int a2 = a(k, childCount, this.c);
        this.k.a(a2);
        float n = n();
        b(a2, n, f, f2, 0);
        for (int i = a2 - 1; i >= 0; i--) {
            b(i, n, f, f2, 1);
        }
        while (true) {
            a2++;
            if (a2 < childCount) {
                b(a2, n, f, f2, -1);
            } else {
                this.s = n;
                return;
            }
        }
    }

    private int a(boolean z, int i, int i2) {
        View childAt;
        return (z || (childAt = this.e.getChildAt(i + (-1))) == null) ? i2 : this.b.getChildLayoutPosition(childAt) - i2;
    }

    private void b(int i, float f, float f2, float f3, int i2) {
        View childAt = this.e.getChildAt(k() ? i : (this.e.getChildCount() - 1) - i);
        if (childAt == null) {
            return;
        }
        float d = d(i, f3, i2);
        float translationY = this.f10692a ? childAt.getTranslationY() : childAt.getTranslationX();
        float c = c(i, d(f, translationY, this.p.get(i, Float.valueOf(f + translationY)).floatValue() + (d * f2)), i2, k());
        this.p.put(i, Float.valueOf(c));
        eeO_(childAt, c - f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private float d(int i, float f, int i2) {
        try {
            return this.k.e(i, f);
        } catch (IllegalArgumentException unused) {
            try {
            } catch (IllegalArgumentException unused2) {
                Log.e("HwChainHelper", "getCurrentRate: get rate from curve chain failed. index = " + i + ", rate = 1.0");
            }
            if (i2 != 1) {
                if (i2 == -1) {
                    i = this.k.e(i - 1, f);
                }
                return 1.0f;
            }
            i = this.k.e(i + 1, f);
            return i;
        }
    }

    private float d(float f, float f2, float f3) {
        return (Float.compare(Math.abs(f), 200.0f) >= 0 || Math.abs(this.s) <= Math.abs(f)) ? f3 : ((f2 * f) / this.s) + f;
    }

    private float c(int i, float f, int i2, boolean z) {
        if (i2 == 1) {
            float floatValue = this.p.get(i + 1, Float.valueOf(f)).floatValue();
            if (z) {
                return Math.min(floatValue + this.t, f);
            }
            return Math.max(floatValue - this.t, f);
        }
        if (i2 != -1) {
            return f;
        }
        float floatValue2 = this.p.get(i - 1, Float.valueOf(f)).floatValue();
        if (z) {
            return Math.max(floatValue2 - this.t, f);
        }
        return Math.min(floatValue2 + this.t, f);
    }

    private void eeO_(View view, float f) {
        if (this.f10692a) {
            view.setTranslationY(f);
        } else {
            view.setTranslationX(f);
        }
    }

    private void e(float f) {
        if (this.f10692a) {
            this.b.setTranslationY(f);
        } else {
            this.b.setTranslationX(f);
        }
    }
}
