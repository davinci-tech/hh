package com.huawei.openalliance.ad.views.viewpager;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class e extends ViewGroup {
    private float A;
    private float B;
    private int C;
    private boolean D;
    private boolean E;
    private int F;
    private int G;
    private int H;
    private VelocityTracker I;
    private int J;
    private int K;
    private int L;
    private boolean M;
    private boolean N;
    private boolean O;
    private int P;
    private int Q;
    private List<d> R;
    private h S;
    private int T;
    private int U;
    private EdgeEffect V;
    private EdgeEffect W;

    /* renamed from: a, reason: collision with root package name */
    int f8169a;
    private int aa;
    private ArrayList<View> ab;
    private final Runnable ad;
    private int ae;
    f b;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private int k;
    private Scroller l;
    private final ArrayList<a> m;
    private final a n;
    private ClassLoader o;
    private final Rect p;
    private Drawable q;
    private Parcelable r;
    private float s;
    private float t;
    private g u;
    private int v;
    private boolean w;
    private boolean x;
    private float y;
    private float z;
    private static final Comparator<a> c = new Comparator<a>() { // from class: com.huawei.openalliance.ad.views.viewpager.e.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(a aVar, a aVar2) {
            return aVar.b - aVar2.b;
        }
    };
    private static final Interpolator d = new Interpolator() { // from class: com.huawei.openalliance.ad.views.viewpager.e.2
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private static final j ac = new j();

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.q;
    }

    void setScrollState(int i) {
        if (this.ae == i) {
            return;
        }
        this.ae = i;
        if (this.S != null) {
            b(i != 0);
        }
        g(i);
    }

    public void setCurrentItem(int i) {
        this.x = false;
        a(i, !this.M, false);
    }

    public void setAdapter(f fVar) {
        f fVar2 = this.b;
        if (fVar2 != null) {
            fVar2.a((DataSetObserver) null);
            this.b.a((ViewGroup) this);
            for (int i = 0; i < this.m.size(); i++) {
                a aVar = this.m.get(i);
                this.b.a(this, aVar.b, aVar.f8166a);
            }
            this.b.b(this);
            this.m.clear();
            i();
            this.f8169a = 0;
            scrollTo(0, 0);
        }
        this.b = fVar;
        this.g = 0;
        if (fVar != null) {
            if (this.u == null) {
                this.u = new g(this);
            }
            this.b.a((DataSetObserver) this.u);
            this.x = false;
            boolean z = this.M;
            this.M = true;
            this.g = this.b.a();
            if (this.h < 0) {
                if (z) {
                    requestLayout();
                    return;
                } else {
                    b();
                    return;
                }
            }
            this.b.a(this.r, this.o);
            a(this.h, false, true);
            this.h = -1;
            this.r = null;
            this.o = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.w) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        f fVar;
        boolean a2;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (fVar = this.b) == null || fVar.a() == 0) {
            return false;
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action == 1) {
                a2 = a(motionEvent, false);
            } else if (action == 2) {
                a2 = b(motionEvent, false);
            } else if (action == 3) {
                a2 = c(false);
            } else if (action == 5) {
                c(motionEvent);
            } else if (action == 6) {
                b(motionEvent);
            }
            if (a2) {
                postInvalidateOnAnimation();
            }
        } else {
            d(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            int i5 = this.v;
            a(i, i3, i5, i5);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedStatePPS savedStatePPS = new SavedStatePPS(super.onSaveInstanceState());
        savedStatePPS.b = this.f8169a;
        f fVar = this.b;
        if (fVar != null) {
            savedStatePPS.c = fVar.b();
        }
        return savedStatePPS;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedStatePPS)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedStatePPS savedStatePPS = (SavedStatePPS) parcelable;
        super.onRestoreInstanceState(savedStatePPS.a());
        f fVar = this.b;
        if (fVar != null) {
            fVar.a(savedStatePPS.c, savedStatePPS.d);
            a(savedStatePPS.b, false, true);
        } else {
            this.h = savedStatePPS.b;
            this.r = savedStatePPS.c;
            this.o = savedStatePPS.d;
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3;
        int i4;
        a a2;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = childCount;
            i2 = 0;
            i4 = 1;
        } else {
            i2 = childCount - 1;
            i3 = -1;
            i4 = -1;
        }
        while (i2 != i3) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.f8169a && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i4;
        }
        return false;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        i iVar;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z = false;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.F = Math.min(measuredWidth / 10, this.P);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i7 = 0;
        while (true) {
            int i8 = 1073741824;
            if (i7 >= childCount) {
                this.e = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
                this.f = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
                e(paddingLeft);
                return;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8 && (iVar = (i) childAt.getLayoutParams()) != null && iVar.b) {
                int i9 = iVar.c & 7;
                int i10 = iVar.c & 112;
                boolean z2 = true;
                boolean z3 = (i10 == 48 || i10 == 80) ? true : z;
                if (i9 != 3 && i9 != 5) {
                    z2 = z;
                }
                int i11 = Integer.MIN_VALUE;
                if (z3) {
                    i3 = Integer.MIN_VALUE;
                    i11 = 1073741824;
                } else {
                    i3 = z2 ? 1073741824 : Integer.MIN_VALUE;
                }
                if (iVar.width != -2) {
                    i5 = iVar.width != -1 ? iVar.width : paddingLeft;
                    i4 = 1073741824;
                } else {
                    i4 = i11;
                    i5 = paddingLeft;
                }
                if (iVar.height != -2) {
                    i6 = iVar.height != -1 ? iVar.height : measuredHeight;
                } else {
                    i6 = measuredHeight;
                    i8 = i3;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i5, i4), View.MeasureSpec.makeMeasureSpec(i6, i8));
                if (z3) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z2) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i7++;
            z = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.viewpager.e.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            l();
            return false;
        }
        if (action != 0) {
            if (this.D) {
                return true;
            }
            if (this.O) {
                return false;
            }
        }
        if (action == 0) {
            a(motionEvent);
        } else if (action == 2) {
            int i = this.H;
            if (i != -1 && a(motionEvent, i)) {
                return false;
            }
        } else if (action == 6) {
            e(motionEvent);
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        return this.D;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.v <= 0 || this.q == null || this.m.size() <= 0 || this.b == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.v / width;
        int i = 0;
        a aVar = this.m.get(0);
        float f5 = aVar.e;
        int size = this.m.size();
        int i2 = aVar.b;
        int i3 = this.m.get(size - 1).b;
        while (i2 < i3) {
            while (i2 > aVar.b && i < size) {
                i++;
                aVar = this.m.get(i);
            }
            if (i2 == aVar.b) {
                f = (aVar.e + aVar.d) * width;
                f2 = aVar.e + aVar.d + f4;
            } else {
                float a2 = this.b.a(i2);
                float f6 = a2 + f4 + f5;
                f = (f5 + a2) * width;
                f2 = f6;
            }
            if (this.v + f > scrollX) {
                f3 = f4;
                this.q.setBounds(Math.round(f), this.j, Math.round(this.v + f), this.k);
                this.q.draw(canvas);
            } else {
                f3 = f4;
            }
            if (f > scrollX + r2) {
                return;
            }
            i2++;
            f5 = f2;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.ad);
        Scroller scroller = this.l;
        if (scroller != null && !scroller.isFinished()) {
            this.l.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.M = true;
    }

    public int getCurrentItem() {
        return this.f8169a;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        if (this.aa == 2) {
            i2 = (i - 1) - i2;
        }
        return ((i) this.ab.get(i2).getLayoutParams()).g;
    }

    public f getAdapter() {
        return this.b;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new i(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new i();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.q;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        f fVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        if (overScrollMode != 0 && (overScrollMode != 1 || (fVar = this.b) == null || fVar.a() <= 1)) {
            this.V.finish();
            this.W.finish();
            return;
        }
        if (this.V.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int width = getWidth();
            canvas.rotate(270.0f);
            canvas.translate((-height) + getPaddingTop(), this.s * width);
            this.V.setSize(height, width);
            z = this.V.draw(canvas);
            canvas.restoreToCount(save);
        }
        if (!this.W.isFinished()) {
            int save2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate(-getPaddingTop(), (-(this.t + 1.0f)) * width2);
            this.W.setSize((height2 - paddingTop) - paddingBottom, width2);
            z |= this.W.draw(canvas);
            canvas.restoreToCount(save2);
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        a a2;
        if (4096 == accessibilityEvent.getEventType()) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.f8169a && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    boolean d() {
        int i = this.f8169a;
        if (i <= 0) {
            return false;
        }
        a(i - 1, true);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        this.i = true;
        if (this.l.isFinished() || !this.l.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.l.getCurrX();
        int currY = this.l.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!f(currX)) {
                this.l.abortAnimation();
                scrollTo(0, currY);
            }
        }
        postInvalidateOnAnimation();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof i) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i) {
        if (this.b == null) {
            return false;
        }
        int clientViewWidth = getClientViewWidth();
        int scrollX = getScrollX();
        return i < 0 ? scrollX > ((int) (((float) clientViewWidth) * this.s)) : i > 0 && scrollX < ((int) (((float) clientViewWidth) * this.t));
    }

    public boolean c(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        } else if (findFocus != null) {
            findFocus = c(findFocus);
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        boolean z = false;
        if (findNextFocus != null && findNextFocus != findFocus) {
            z = a(i, findFocus, false, findNextFocus);
        } else if (i == 17 || i == 1) {
            z = d();
        } else if (i == 66 || i == 2) {
            z = c();
        }
        if (z) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return z;
    }

    boolean c() {
        f fVar = this.b;
        if (fVar == null || this.f8169a >= fVar.a() - 1) {
            return false;
        }
        a(this.f8169a + 1, true);
        return true;
    }

    void b() {
        a(this.f8169a);
    }

    a b(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    a b(int i) {
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            a aVar = this.m.get(i2);
            if (aVar.b == i) {
                return aVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        i iVar = (i) layoutParams;
        if (!this.w) {
            super.addView(view, i, layoutParams);
        } else {
            if (iVar != null && iVar.b) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            iVar.e = true;
            addViewInLayout(view, i, layoutParams);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        a a2;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.f8169a) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        a a2;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (a2 = a(childAt)) != null && a2.b == this.f8169a) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i2 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    protected boolean a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (a(i2, i3, scrollX, scrollY, childAt) && a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && view.canScrollHorizontally(-i);
    }

    public boolean a(KeyEvent keyEvent) {
        int i;
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 21) {
                if (keyCode != 22) {
                    if (keyCode == 61) {
                        if (keyEvent.hasNoModifiers()) {
                            return c(2);
                        }
                        if (keyEvent.hasModifiers(1)) {
                            return c(1);
                        }
                    }
                } else {
                    if (keyEvent.hasModifiers(2)) {
                        return c();
                    }
                    i = 66;
                }
            } else {
                if (keyEvent.hasModifiers(2)) {
                    return d();
                }
                i = 17;
            }
            return c(i);
        }
        return false;
    }

    public void a(d dVar) {
        if (this.R == null) {
            this.R = new ArrayList();
        }
        this.R.add(dVar);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        f fVar = this.b;
        if (fVar == null || fVar.a() <= 0) {
            setScrollingCacheEnabledStatus(false);
            return;
        }
        if (!z2 && this.f8169a == i && this.m.size() != 0) {
            setScrollingCacheEnabledStatus(false);
            return;
        }
        if (i < 0) {
            i = 0;
        } else if (i >= this.b.a()) {
            i = this.b.a() - 1;
        }
        int i3 = this.C;
        int i4 = this.f8169a;
        if (i > i4 + i3 || i < i4 - i3) {
            for (int i5 = 0; i5 < this.m.size(); i5++) {
                this.m.get(i5).c = true;
            }
        }
        boolean z3 = this.f8169a != i;
        if (!this.M) {
            a(i);
            a(i, z, i2, z3);
        } else {
            this.f8169a = i;
            if (z3) {
                h(i);
            }
            requestLayout();
        }
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    public void a(int i, boolean z) {
        this.x = false;
        a(i, z, false);
    }

    void a(int i, int i2, int i3) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabledStatus(false);
            return;
        }
        Scroller scroller = this.l;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            scrollX = this.i ? this.l.getCurrX() : this.l.getStartX();
            this.l.abortAnimation();
            setScrollingCacheEnabledStatus(false);
        }
        int i4 = scrollX;
        int scrollY = getScrollY();
        int i5 = i - i4;
        int i6 = i2 - scrollY;
        if (i5 == 0 && i6 == 0) {
            a(false);
            b();
            setScrollState(0);
        } else {
            setScrollingCacheEnabledStatus(true);
            setScrollState(2);
            a(i3, i4, scrollY, i5, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.Q
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L6b
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r2
        L1b:
            if (r7 >= r6) goto L6b
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            com.huawei.openalliance.ad.views.viewpager.i r9 = (com.huawei.openalliance.ad.views.viewpager.i) r9
            boolean r10 = r9.b
            if (r10 != 0) goto L2c
            goto L68
        L2c:
            int r9 = r9.c
            r9 = r9 & 7
            if (r9 == r1) goto L4d
            r10 = 3
            if (r9 == r10) goto L47
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5c
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
            goto L59
        L47:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5c
        L4d:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
        L59:
            r11 = r9
            r9 = r3
            r3 = r11
        L5c:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L67
            r8.offsetLeftAndRight(r3)
        L67:
            r3 = r9
        L68:
            int r7 = r7 + 1
            goto L1b
        L6b:
            r12.b(r13, r14, r15)
            com.huawei.openalliance.ad.views.viewpager.h r13 = r12.S
            if (r13 == 0) goto L9f
            int r13 = r12.getScrollX()
            int r14 = r12.getChildCount()
        L7a:
            if (r2 >= r14) goto L9f
            android.view.View r15 = r12.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r0 = r15.getLayoutParams()
            com.huawei.openalliance.ad.views.viewpager.i r0 = (com.huawei.openalliance.ad.views.viewpager.i) r0
            boolean r0 = r0.b
            if (r0 == 0) goto L8b
            goto L9c
        L8b:
            int r0 = r15.getLeft()
            int r0 = r0 - r13
            float r0 = (float) r0
            int r3 = r12.getClientViewWidth()
            float r3 = (float) r3
            float r0 = r0 / r3
            com.huawei.openalliance.ad.views.viewpager.h r3 = r12.S
            r3.a(r15, r0)
        L9c:
            int r2 = r2 + 1
            goto L7a
        L9f:
            r12.N = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.viewpager.e.a(int, float, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0061, code lost:
    
        if (r3.b == r12.f8169a) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(int r13) {
        /*
            r12 = this;
            int r0 = r12.f8169a
            r1 = 0
            if (r0 == r13) goto Lc
            com.huawei.openalliance.ad.views.viewpager.a r0 = r12.b(r0)
            r12.f8169a = r13
            goto Ld
        Lc:
            r0 = r1
        Ld:
            com.huawei.openalliance.ad.views.viewpager.f r13 = r12.b
            if (r13 != 0) goto L15
            r12.k()
            return
        L15:
            boolean r13 = r12.x
            if (r13 == 0) goto L1d
            r12.k()
            return
        L1d:
            android.os.IBinder r13 = r12.getWindowToken()
            if (r13 != 0) goto L24
            return
        L24:
            com.huawei.openalliance.ad.views.viewpager.f r13 = r12.b
            r13.a(r12)
            int r13 = r12.C
            int r2 = r12.f8169a
            int r2 = r2 - r13
            r3 = 0
            int r5 = java.lang.Math.max(r3, r2)
            com.huawei.openalliance.ad.views.viewpager.f r2 = r12.b
            int r2 = r2.a()
            int r4 = r2 + (-1)
            int r6 = r12.f8169a
            int r6 = r6 + r13
            int r13 = java.lang.Math.min(r4, r6)
            int r4 = r12.g
            if (r2 != r4) goto Lbe
            r6 = r3
        L47:
            java.util.ArrayList<com.huawei.openalliance.ad.views.viewpager.a> r3 = r12.m
            int r3 = r3.size()
            if (r6 >= r3) goto L67
            java.util.ArrayList<com.huawei.openalliance.ad.views.viewpager.a> r3 = r12.m
            java.lang.Object r3 = r3.get(r6)
            com.huawei.openalliance.ad.views.viewpager.a r3 = (com.huawei.openalliance.ad.views.viewpager.a) r3
            int r4 = r3.b
            int r7 = r12.f8169a
            if (r4 < r7) goto L64
            int r4 = r3.b
            int r7 = r12.f8169a
            if (r4 != r7) goto L67
            goto L68
        L64:
            int r6 = r6 + 1
            goto L47
        L67:
            r3 = r1
        L68:
            if (r3 != 0) goto L72
            if (r2 <= 0) goto L72
            int r3 = r12.f8169a
            com.huawei.openalliance.ad.views.viewpager.a r3 = r12.a(r3, r6)
        L72:
            if (r3 == 0) goto Lba
            int r8 = r6 + (-1)
            if (r8 < 0) goto L80
            java.util.ArrayList<com.huawei.openalliance.ad.views.viewpager.a> r1 = r12.m
            java.lang.Object r1 = r1.get(r8)
            com.huawei.openalliance.ad.views.viewpager.a r1 = (com.huawei.openalliance.ad.views.viewpager.a) r1
        L80:
            r9 = r1
            int r1 = r12.getClientViewWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            if (r1 > 0) goto L8b
            r4 = 0
            goto L97
        L8b:
            float r4 = r3.d
            float r4 = r11 - r4
            int r7 = r12.getPaddingLeft()
            float r7 = (float) r7
            float r10 = (float) r1
            float r7 = r7 / r10
            float r4 = r4 + r7
        L97:
            r10 = r4
            r7 = 0
            r4 = r12
            int r4 = r4.a(r5, r6, r7, r8, r9, r10)
            float r5 = r3.d
            int r6 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r6 >= 0) goto Lae
            int r9 = r4 + 1
            r6 = r12
            r7 = r2
            r8 = r13
            r10 = r1
            r11 = r5
            r6.a(r7, r8, r9, r10, r11)
        Lae:
            r12.a(r3, r4, r0)
            com.huawei.openalliance.ad.views.viewpager.f r13 = r12.b
            int r0 = r12.f8169a
            java.lang.Object r1 = r3.f8166a
            r13.b(r12, r0, r1)
        Lba:
            r12.j()
            return
        Lbe:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "populate exception"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.viewpager.e.a(int):void");
    }

    void a() {
        int a2 = this.b.a();
        this.g = a2;
        boolean z = this.m.size() < (this.C * 2) + 1 && this.m.size() < a2;
        int i = this.f8169a;
        int i2 = 0;
        boolean z2 = false;
        while (i2 < this.m.size()) {
            a aVar = this.m.get(i2);
            int a3 = this.b.a(aVar.f8166a);
            if (a3 != -1) {
                if (a3 == -2) {
                    this.m.remove(i2);
                    i2--;
                    if (!z2) {
                        this.b.a((ViewGroup) this);
                        z2 = true;
                    }
                    this.b.a(this, aVar.b, aVar.f8166a);
                    if (this.f8169a == aVar.b) {
                        i = Math.max(0, Math.min(this.f8169a, a2 - 1));
                    }
                } else if (aVar.b != a3) {
                    if (aVar.b == this.f8169a) {
                        i = a3;
                    }
                    aVar.b = a3;
                }
                z = true;
            }
            i2++;
        }
        if (z2) {
            this.b.b(this);
        }
        Collections.sort(this.m, c);
        if (z) {
            d(i);
        }
    }

    a a(View view) {
        for (int i = 0; i < this.m.size(); i++) {
            a aVar = this.m.get(i);
            if (this.b.a(view, aVar.f8166a)) {
                return aVar;
            }
        }
        return null;
    }

    a a(int i, int i2) {
        a aVar = new a();
        aVar.b = i;
        aVar.f8166a = this.b.a(this, i);
        aVar.d = this.b.a(i);
        if (i2 < 0 || i2 >= this.m.size()) {
            this.m.add(aVar);
        } else {
            this.m.add(i2, aVar);
        }
        return aVar;
    }

    float a(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    private void setScrollingCacheEnabledStatus(boolean z) {
        if (this.E != z) {
            this.E = z;
        }
    }

    private void n() {
        this.D = false;
        this.O = false;
        VelocityTracker velocityTracker = this.I;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.I = null;
        }
    }

    private a m() {
        int i;
        int clientViewWidth = getClientViewWidth();
        float f = 0.0f;
        float scrollX = clientViewWidth > 0 ? getScrollX() / clientViewWidth : 0.0f;
        float f2 = clientViewWidth > 0 ? this.v / clientViewWidth : 0.0f;
        int i2 = 0;
        boolean z = true;
        int i3 = -1;
        a aVar = null;
        float f3 = 0.0f;
        while (i2 < this.m.size()) {
            a aVar2 = this.m.get(i2);
            if (!z && aVar2.b != (i = i3 + 1)) {
                aVar2 = this.n;
                aVar2.e = f + f3 + f2;
                aVar2.b = i;
                aVar2.d = this.b.a(aVar2.b);
                i2--;
            }
            a aVar3 = aVar2;
            f = aVar3.e;
            float f4 = aVar3.d;
            if (!z && scrollX < f) {
                return aVar;
            }
            if (scrollX < f4 + f + f2 || i2 == this.m.size() - 1) {
                return aVar3;
            }
            i3 = aVar3.b;
            i2++;
            z = false;
            aVar = aVar3;
            f3 = aVar3.d;
        }
        return aVar;
    }

    private boolean l() {
        this.H = -1;
        n();
        this.V.onRelease();
        this.W.onRelease();
        return this.V.isFinished() || this.W.isFinished();
    }

    private void k() {
        if (this.aa != 0) {
            ArrayList<View> arrayList = this.ab;
            if (arrayList == null) {
                this.ab = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ab.add(getChildAt(i));
            }
            Collections.sort(this.ab, ac);
        }
    }

    private void j() {
        a a2;
        this.b.b(this);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            i iVar = (i) childAt.getLayoutParams();
            iVar.g = i;
            if (!iVar.b && iVar.d == 0.0f && (a2 = a(childAt)) != null) {
                iVar.d = a2.d;
                iVar.f = a2.b;
            }
        }
        k();
        if (hasFocus()) {
            View findFocus = findFocus();
            a b = findFocus != null ? b(findFocus) : null;
            if (b == null || b.b != this.f8169a) {
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    View childAt2 = getChildAt(i2);
                    a a3 = a(childAt2);
                    if (a3 != null && a3.b == this.f8169a && childAt2.requestFocus(2)) {
                        return;
                    }
                }
            }
        }
    }

    private void i() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((i) getChildAt(i).getLayoutParams()).b) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    private void h(int i) {
        List<d> list = this.R;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = this.R.get(i2);
                if (dVar != null) {
                    dVar.a(i);
                }
            }
        }
    }

    private void h() {
        e();
        g();
        f();
    }

    private int getClientViewWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void g(int i) {
        List<d> list = this.R;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                d dVar = this.R.get(i2);
                if (dVar != null) {
                    dVar.b(i);
                }
            }
        }
    }

    private void g() {
        Context context = getContext();
        this.l = new Scroller(context, d);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.G = viewConfiguration.getScaledPagingTouchSlop();
        this.J = (int) (400.0f * f);
        this.K = viewConfiguration.getScaledMaximumFlingVelocity();
        this.V = new EdgeEffect(context);
        this.W = new EdgeEffect(context);
        this.L = (int) (25.0f * f);
        this.U = (int) (2.0f * f);
        this.P = (int) (f * 16.0f);
        setAccessibilityDelegate(new b(this));
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private boolean f(int i) {
        if (this.m.size() == 0) {
            if (this.M) {
                return false;
            }
            this.N = false;
            a(0, 0.0f, 0);
            if (this.N) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        a m = m();
        if (m == null) {
            return false;
        }
        int clientViewWidth = getClientViewWidth();
        int i2 = this.v;
        float f = clientViewWidth;
        int i3 = m.b;
        float f2 = ((i / f) - m.e) / (m.d + (i2 / f));
        this.N = false;
        a(i3, f2, (int) ((clientViewWidth + i2) * f2));
        if (this.N) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void f() {
        setOnApplyWindowInsetsListener(new c(this));
    }

    private void e(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.H) {
            int i = actionIndex == 0 ? 1 : 0;
            this.y = motionEvent.getX(i);
            this.H = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.I;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void e(int i) {
        int makeMeasureSpec;
        this.w = true;
        b();
        this.w = false;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                i iVar = (i) childAt.getLayoutParams();
                if (iVar == null) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
                } else if (!iVar.b) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (i * iVar.d), 1073741824);
                }
                childAt.measure(makeMeasureSpec, this.f);
            }
        }
    }

    private void e() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
    }

    private void d(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void d(MotionEvent motionEvent) {
        this.l.abortAnimation();
        this.x = false;
        b();
        float x = motionEvent.getX();
        this.A = x;
        this.y = x;
        float y = motionEvent.getY();
        this.B = y;
        this.z = y;
        this.H = motionEvent.getPointerId(0);
    }

    private void d(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            i iVar = (i) getChildAt(i2).getLayoutParams();
            if (!iVar.b) {
                iVar.d = 0.0f;
            }
        }
        a(i, false, true);
        requestLayout();
    }

    private boolean c(boolean z) {
        if (!this.D) {
            return z;
        }
        a(this.f8169a, true, 0, false);
        return l();
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        this.y = motionEvent.getX(actionIndex);
        this.H = motionEvent.getPointerId(actionIndex);
    }

    private View c(View view) {
        for (ViewParent parent = view.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            if (parent == this) {
                return view;
            }
        }
        view.getClass().getSimpleName();
        for (ViewParent parent2 = view.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
            parent2.getClass().getSimpleName();
        }
        return null;
    }

    private boolean b(MotionEvent motionEvent, boolean z) {
        if (!this.D) {
            int findPointerIndex = motionEvent.findPointerIndex(this.H);
            if (findPointerIndex == -1) {
                return l();
            }
            float x = motionEvent.getX(findPointerIndex);
            float abs = Math.abs(x - this.y);
            float y = motionEvent.getY(findPointerIndex);
            float abs2 = Math.abs(y - this.z);
            if (abs > this.G && abs > abs2) {
                this.D = true;
                d(true);
                float f = this.A;
                this.y = x - f > 0.0f ? f + this.G : f - this.G;
                this.z = y;
                setScrollState(1);
                setScrollingCacheEnabledStatus(true);
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            }
        }
        return this.D ? z | b(motionEvent.getX(motionEvent.findPointerIndex(this.H))) : z;
    }

    private boolean b(float f) {
        boolean z;
        boolean z2;
        float f2 = this.y;
        this.y = f;
        float scrollX = getScrollX() + (f2 - f);
        float clientViewWidth = getClientViewWidth();
        float f3 = this.s * clientViewWidth;
        float f4 = this.t * clientViewWidth;
        boolean z3 = false;
        a aVar = this.m.get(0);
        ArrayList<a> arrayList = this.m;
        a aVar2 = arrayList.get(arrayList.size() - 1);
        if (aVar.b != 0) {
            f3 = aVar.e * clientViewWidth;
            z = false;
        } else {
            z = true;
        }
        if (aVar2.b != this.b.a() - 1) {
            f4 = aVar2.e * clientViewWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (scrollX < f3) {
            if (z) {
                this.V.onPull(Math.abs(f3 - scrollX) / clientViewWidth);
                z3 = true;
            }
            scrollX = f3;
        } else if (scrollX > f4) {
            if (z2) {
                this.W.onPull(Math.abs(scrollX - f4) / clientViewWidth);
                z3 = true;
            }
            scrollX = f4;
        }
        int i = (int) scrollX;
        this.y += scrollX - i;
        scrollTo(i, getScrollY());
        f(i);
        return z3;
    }

    private void b(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setLayerType(z ? this.T : 0, null);
        }
    }

    private void b(MotionEvent motionEvent) {
        e(motionEvent);
        this.y = motionEvent.getX(motionEvent.findPointerIndex(this.H));
    }

    private void b(int i, float f, int i2) {
        List<d> list = this.R;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                d dVar = this.R.get(i3);
                if (dVar != null) {
                    dVar.a(i, f, i2);
                }
            }
        }
    }

    private boolean a(MotionEvent motionEvent, boolean z) {
        if (!this.D) {
            return z;
        }
        VelocityTracker velocityTracker = this.I;
        velocityTracker.computeCurrentVelocity(1000, this.K);
        int xVelocity = (int) velocityTracker.getXVelocity(this.H);
        this.x = true;
        int clientViewWidth = getClientViewWidth();
        int scrollX = getScrollX();
        a m = m();
        if (m == null) {
            return l();
        }
        float f = clientViewWidth;
        a(a(m.b, ((scrollX / f) - m.e) / (m.d + (this.v / f)), xVelocity, (int) (motionEvent.getX(motionEvent.findPointerIndex(this.H)) - this.A)), true, true, xVelocity);
        return l();
    }

    private boolean a(MotionEvent motionEvent, int i) {
        int findPointerIndex = motionEvent.findPointerIndex(i);
        float x = motionEvent.getX(findPointerIndex);
        float f = x - this.y;
        float abs = Math.abs(f);
        float y = motionEvent.getY(findPointerIndex);
        float abs2 = Math.abs(y - this.B);
        if (f != 0.0f && !a(this.y, f) && a((View) this, false, (int) f, (int) x, (int) y)) {
            this.y = x;
            this.z = y;
            this.O = true;
            return true;
        }
        float f2 = this.G;
        if (abs > f2 && abs * 0.5f > abs2) {
            this.D = true;
            d(true);
            setScrollState(1);
            float f3 = this.A;
            float f4 = this.G;
            this.y = f > 0.0f ? f3 + f4 : f3 - f4;
            this.z = y;
            setScrollingCacheEnabledStatus(true);
        } else if (abs2 > f2) {
            this.O = true;
        }
        if (!this.D || !b(x)) {
            return false;
        }
        postInvalidateOnAnimation();
        return false;
    }

    private boolean a(int i, View view, boolean z, View view2) {
        if (i == 17) {
            return (view == null || a(this.p, view2).left < a(this.p, view).left) ? view2.requestFocus() : d();
        }
        if (i == 66) {
            return (view == null || a(this.p, view2).left > a(this.p, view).left) ? view2.requestFocus() : c();
        }
        return z;
    }

    private boolean a(int i, int i2, int i3, int i4, View view) {
        int i5;
        int i6 = i + i3;
        return i6 >= view.getLeft() && i6 < view.getRight() && (i5 = i2 + i4) >= view.getTop() && i5 < view.getBottom();
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.F) && f2 > 0.0f) || (f > ((float) (getWidth() - this.F)) && f2 < 0.0f);
    }

    private void a(boolean z) {
        boolean z2 = this.ae == 2;
        if (z2) {
            setScrollingCacheEnabledStatus(false);
            if (!this.l.isFinished()) {
                this.l.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.l.getCurrX();
                int currY = this.l.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        f(currX);
                    }
                }
            }
        }
        this.x = false;
        for (int i = 0; i < this.m.size(); i++) {
            a aVar = this.m.get(i);
            if (aVar.c) {
                aVar.c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                postOnAnimation(this.ad);
            } else {
                this.ad.run();
            }
        }
    }

    private void a(a aVar, int i, a aVar2) {
        a aVar3;
        a aVar4;
        int a2 = this.b.a();
        int clientViewWidth = getClientViewWidth();
        float f = clientViewWidth > 0 ? this.v / clientViewWidth : 0.0f;
        if (aVar2 != null) {
            int i2 = aVar2.b;
            if (i2 < aVar.b) {
                float f2 = aVar2.e + aVar2.d + f;
                int i3 = i2 + 1;
                int i4 = 0;
                while (i3 <= aVar.b && i4 < this.m.size()) {
                    while (true) {
                        aVar4 = this.m.get(i4);
                        if (i3 <= aVar4.b || i4 >= this.m.size() - 1) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                    while (i3 < aVar4.b) {
                        f2 += this.b.a(i3) + f;
                        i3++;
                    }
                    aVar4.e = f2;
                    f2 += aVar4.d + f;
                    i3++;
                }
            } else if (i2 > aVar.b) {
                int size = this.m.size() - 1;
                float f3 = aVar2.e;
                while (true) {
                    i2--;
                    if (i2 < aVar.b || size < 0) {
                        break;
                    }
                    while (true) {
                        aVar3 = this.m.get(size);
                        if (i2 >= aVar3.b || size <= 0) {
                            break;
                        } else {
                            size--;
                        }
                    }
                    while (i2 > aVar3.b) {
                        f3 -= this.b.a(i2) + f;
                        i2--;
                    }
                    f3 -= aVar3.d + f;
                    aVar3.e = f3;
                }
            }
        }
        a(aVar, i, a2, f);
    }

    private void a(a aVar, int i, int i2, float f) {
        int size = this.m.size();
        float f2 = aVar.e;
        int i3 = aVar.b - 1;
        this.s = aVar.b == 0 ? aVar.e : -3.4028235E38f;
        int i4 = i2 - 1;
        this.t = aVar.b == i4 ? (aVar.e + aVar.d) - 1.0f : Float.MAX_VALUE;
        int i5 = i - 1;
        while (i5 >= 0) {
            a aVar2 = this.m.get(i5);
            while (i3 > aVar2.b) {
                f2 -= this.b.a(i3) + f;
                i3--;
            }
            f2 -= aVar2.d + f;
            aVar2.e = f2;
            if (aVar2.b == 0) {
                this.s = f2;
            }
            i5--;
            i3--;
        }
        float f3 = aVar.e + aVar.d + f;
        int i6 = aVar.b + 1;
        int i7 = i + 1;
        while (i7 < size) {
            a aVar3 = this.m.get(i7);
            while (i6 < aVar3.b) {
                f3 += this.b.a(i6) + f;
                i6++;
            }
            if (aVar3.b == i4) {
                this.t = (aVar3.d + f3) - 1.0f;
            }
            aVar3.e = f3;
            f3 += aVar3.d + f;
            i7++;
            i6++;
        }
    }

    private void a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        this.A = x;
        this.y = x;
        float y = motionEvent.getY();
        this.B = y;
        this.z = y;
        this.H = motionEvent.getPointerId(0);
        this.O = false;
        this.i = true;
        this.l.computeScrollOffset();
        if (this.ae != 2 || Math.abs(this.l.getFinalX() - this.l.getCurrX()) <= this.U) {
            a(false);
            this.D = false;
            return;
        }
        this.l.abortAnimation();
        this.x = false;
        b();
        this.D = true;
        d(true);
        setScrollState(1);
    }

    private void a(int i, boolean z, int i2, boolean z2) {
        a b = b(i);
        int clientViewWidth = b != null ? (int) (getClientViewWidth() * Math.max(this.s, Math.min(b.e, this.t))) : 0;
        if (z) {
            a(clientViewWidth, 0, i2);
            if (z2) {
                h(i);
                return;
            }
            return;
        }
        if (z2) {
            h(i);
        }
        a(false);
        scrollTo(clientViewWidth, 0);
        f(clientViewWidth);
    }

    private void a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        a a2;
        for (int i8 = 0; i8 < i; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                i iVar = (i) childAt.getLayoutParams();
                if (!iVar.b && (a2 = a(childAt)) != null) {
                    float f = (i6 - i2) - i4;
                    int i9 = ((int) (a2.e * f)) + i2;
                    if (iVar.e) {
                        iVar.e = false;
                        childAt.measure(View.MeasureSpec.makeMeasureSpec((int) (f * iVar.d), 1073741824), View.MeasureSpec.makeMeasureSpec((i7 - i3) - i5, 1073741824));
                    }
                    childAt.layout(i9, i3, childAt.getMeasuredWidth() + i9, childAt.getMeasuredHeight() + i3);
                }
            }
        }
    }

    private void a(int i, int i2, int i3, int i4, int i5) {
        int abs;
        int clientViewWidth = getClientViewWidth();
        int i6 = clientViewWidth / 2;
        float f = clientViewWidth;
        float f2 = i6;
        float a2 = a(Math.min(1.0f, (Math.abs(i4) * 1.0f) / f));
        int abs2 = Math.abs(i);
        if (abs2 > 0) {
            abs = Math.round(Math.abs((f2 + (a2 * f2)) / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i4) / ((f * this.b.a(this.f8169a)) + this.v)) + 1.0f) * 100.0f);
        }
        int min = Math.min(abs, 600);
        this.i = false;
        this.l.startScroll(i2, i3, i4, i5, min);
        postInvalidateOnAnimation();
    }

    private void a(int i, int i2, int i3, int i4, float f) {
        a aVar = i3 < this.m.size() ? this.m.get(i3) : null;
        float paddingRight = i4 <= 0 ? 0.0f : 2.0f + (getPaddingRight() / i4);
        int i5 = this.f8169a;
        while (true) {
            i5++;
            if (i5 >= i) {
                return;
            }
            if (f < paddingRight || i5 <= i2) {
                if (aVar == null || i5 != aVar.b) {
                    a a2 = a(i5, i3);
                    i3++;
                    f += a2.d;
                    aVar = i3 < this.m.size() ? this.m.get(i3) : null;
                } else {
                    f += aVar.d;
                    i3++;
                    if (i3 < this.m.size()) {
                    }
                }
            } else {
                if (aVar == null) {
                    return;
                }
                if (i5 == aVar.b && !aVar.c) {
                    this.m.remove(i3);
                    this.b.a(this, i5, aVar.f8166a);
                    if (i3 < this.m.size()) {
                    }
                }
            }
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        int min;
        if (i2 <= 0 || this.m.isEmpty()) {
            a b = b(this.f8169a);
            min = (int) ((b != null ? Math.min(b.e, this.t) : 0.0f) * ((i - getPaddingLeft()) - getPaddingRight()));
            if (min == getScrollX()) {
                return;
            } else {
                a(false);
            }
        } else {
            if (!this.l.isFinished()) {
                this.l.setFinalX(getCurrentItem() * getClientViewWidth());
                return;
            }
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            min = (int) ((getScrollX() / (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)) * (((i - paddingLeft) - paddingRight) + i3));
        }
        scrollTo(min, getScrollY());
    }

    private Rect a(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        rect.left = view.getLeft();
        rect.right = view.getRight();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
    
        if (r7 >= 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004f, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0046, code lost:
    
        r2 = r3.m.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0036, code lost:
    
        if (r7 >= 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0044, code lost:
    
        if (r7 >= 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(int r4, int r5, float r6, int r7, com.huawei.openalliance.ad.views.viewpager.a r8, float r9) {
        /*
            r3 = this;
            int r0 = r3.f8169a
            int r0 = r0 + (-1)
        L4:
            if (r0 < 0) goto L53
            int r1 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            r2 = 0
            if (r1 < 0) goto L2b
            if (r0 >= r4) goto L2b
            if (r8 != 0) goto L10
            goto L53
        L10:
            int r1 = r8.b
            if (r0 != r1) goto L50
            boolean r1 = r8.c
            if (r1 != 0) goto L50
            java.util.ArrayList<com.huawei.openalliance.ad.views.viewpager.a> r1 = r3.m
            r1.remove(r7)
            com.huawei.openalliance.ad.views.viewpager.f r1 = r3.b
            java.lang.Object r8 = r8.f8166a
            r1.a(r3, r0, r8)
            int r7 = r7 + (-1)
            int r5 = r5 + (-1)
            if (r7 < 0) goto L4f
            goto L46
        L2b:
            if (r8 == 0) goto L39
            int r1 = r8.b
            if (r0 != r1) goto L39
            float r8 = r8.d
            float r6 = r6 + r8
            int r7 = r7 + (-1)
            if (r7 < 0) goto L4f
            goto L46
        L39:
            int r8 = r7 + 1
            com.huawei.openalliance.ad.views.viewpager.a r8 = r3.a(r0, r8)
            float r8 = r8.d
            float r6 = r6 + r8
            int r5 = r5 + 1
            if (r7 < 0) goto L4f
        L46:
            java.util.ArrayList<com.huawei.openalliance.ad.views.viewpager.a> r8 = r3.m
            java.lang.Object r8 = r8.get(r7)
            r2 = r8
            com.huawei.openalliance.ad.views.viewpager.a r2 = (com.huawei.openalliance.ad.views.viewpager.a) r2
        L4f:
            r8 = r2
        L50:
            int r0 = r0 + (-1)
            goto L4
        L53:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.viewpager.e.a(int, int, float, int, com.huawei.openalliance.ad.views.viewpager.a, float):int");
    }

    private int a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.L || Math.abs(i2) <= this.J) {
            i += (int) (f + (i >= this.f8169a ? 0.4f : 0.6f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.m.size() <= 0) {
            return i;
        }
        return Math.max(this.m.get(0).b, Math.min(i, this.m.get(r4.size() - 1).b));
    }

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
        this.m = new ArrayList<>();
        this.n = new a();
        this.o = null;
        this.p = new Rect();
        this.r = null;
        this.s = -3.4028235E38f;
        this.t = Float.MAX_VALUE;
        this.C = 1;
        this.H = -1;
        this.M = true;
        this.ad = new Runnable() { // from class: com.huawei.openalliance.ad.views.viewpager.e.3
            @Override // java.lang.Runnable
            public void run() {
                e.this.setScrollState(0);
                e.this.b();
            }
        };
        this.ae = 0;
        h();
    }

    public e(Context context) {
        super(context);
        this.h = -1;
        this.m = new ArrayList<>();
        this.n = new a();
        this.o = null;
        this.p = new Rect();
        this.r = null;
        this.s = -3.4028235E38f;
        this.t = Float.MAX_VALUE;
        this.C = 1;
        this.H = -1;
        this.M = true;
        this.ad = new Runnable() { // from class: com.huawei.openalliance.ad.views.viewpager.e.3
            @Override // java.lang.Runnable
            public void run() {
                e.this.setScrollState(0);
                e.this.b();
            }
        };
        this.ae = 0;
        h();
    }
}
