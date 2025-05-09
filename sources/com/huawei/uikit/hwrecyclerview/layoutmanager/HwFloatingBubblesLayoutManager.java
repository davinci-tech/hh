package com.huawei.uikit.hwrecyclerview.layoutmanager;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;
import com.huawei.uikit.hwrecyclerview.layoutmanager.HwFloatingBubblesLayoutManager;
import defpackage.sma;
import defpackage.smd;
import defpackage.sme;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class HwFloatingBubblesLayoutManager extends LinearLayoutManager {
    private static final int[] d = {1, 2, 0, 1, 0, 2};

    /* renamed from: a, reason: collision with root package name */
    private final int f10690a;
    private int b;
    private final HwFloatingBubblesScrollFinishedCallback c;
    private int e;
    private SparseArray<Rect> f;
    private int g;
    private int h;
    private sme i;
    private int j;
    private int k;
    private int l;
    private int m;
    private HwLayoutFinishedCallback n;
    private int o;
    private ValueAnimator p;
    private SparseArray<a> q;
    private int r;
    private int s;
    private int t;

    public static abstract class HwFloatingBubblesScrollFinishedCallback {
        public abstract void onScrollByFinished(View view, RecyclerView recyclerView);
    }

    public static abstract class HwLayoutFinishedCallback {
        public abstract void onLayoutFinished(boolean z);
    }

    class a {

        /* renamed from: a, reason: collision with root package name */
        int f10691a;
        int b;
        int c;
        int d;
        float e;

        a(int i, int i2, int i3, int i4, float f) {
            this.d = i;
            this.f10691a = i2;
            this.c = i3;
            this.b = i4;
            this.e = f;
        }
    }

    static class c extends LinearSmoothScroller {
        private HwCubicBezierInterpolator b;

        c(Context context) {
            super(context);
            this.b = new HwCubicBezierInterpolator(0.4f, 0.0f, 0.4f, 1.0f);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return (int) ((i3 + ((i4 - i3) / 2.0f)) - (i + ((i2 - i) / 2.0f)));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateTimeForDeceleration(int i) {
            return 1000;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateTimeForScrolling(int i) {
            return 1000;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int calculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
            int calculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
            int calculateTimeForDeceleration = calculateTimeForDeceleration((int) Math.sqrt((calculateDxToMakeVisible * calculateDxToMakeVisible) + (calculateDyToMakeVisible * calculateDyToMakeVisible)));
            if (calculateTimeForDeceleration > 0) {
                action.update(-calculateDxToMakeVisible, -calculateDyToMakeVisible, calculateTimeForDeceleration, this.b);
            }
        }
    }

    private void a(RecyclerView.Recycler recycler, int i) {
        int position;
        ArrayList arrayList = new ArrayList();
        int itemCount = getItemCount();
        boolean g = g();
        boolean f = f();
        int i2 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt != null) {
                float translationY = childAt.getTranslationY();
                if (i <= 0 || (getDecoratedBottom(childAt) - i) + (translationY * 0.5f) >= 0.0f) {
                    if (i >= 0 || (getDecoratedTop(childAt) - i) + (translationY * 0.5f) <= a()) {
                        int position2 = getPosition(childAt);
                        if (position2 > i2) {
                            i2 = position2;
                        }
                        if (position2 < itemCount) {
                            itemCount = position2;
                        }
                    } else if (!g) {
                        arrayList.add(childAt);
                    }
                } else if (!f) {
                    arrayList.add(childAt);
                }
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            View view = (View) arrayList.get(i3);
            if (view != null && ((position = getPosition(view)) < itemCount || position > i2)) {
                removeAndRecycleView(view, recycler);
            }
        }
    }

    private void b(RecyclerView.Recycler recycler, int i) {
        for (int i2 = this.j; i2 < getItemCount(); i2++) {
            Rect rect = this.f.get(i2);
            if (rect != null) {
                Rect rect2 = new Rect(rect.left, rect.top + i, rect.right, rect.bottom + i);
                if (Rect.intersects(eeC_(0), rect2)) {
                    eeH_(false, rect2, recycler.getViewForPosition(i2), i2);
                }
            }
        }
        b(false, 0, true);
        e(true);
    }

    private void b(boolean z, int i, boolean z2) {
        if (getChildCount() == 0 || getHeight() == 0) {
            return;
        }
        boolean z3 = true;
        for (int i2 = 0; z3 && i2 < 10; i2++) {
            z3 = d(z2, smd.a(z, i, this, this.i));
        }
    }

    private void e(RecyclerView.Recycler recycler, int i) {
        int itemCount = getItemCount();
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt != null) {
                int position = getPosition(childAt);
                if (position > i2) {
                    i2 = position;
                }
                if (position < itemCount) {
                    itemCount = position;
                }
            }
        }
        a(recycler, i);
        Rect eeC_ = eeC_(i);
        if (i > 0) {
            for (int i4 = i2 + 1; i4 < getItemCount(); i4++) {
                a(recycler, eeC_, i4, false);
            }
            return;
        }
        for (int i5 = itemCount - 1; i5 >= 0; i5--) {
            a(recycler, eeC_, i5, true);
        }
    }

    private boolean f() {
        Pair<Boolean, Integer> eeD_ = eeD_();
        if (((Boolean) eeD_.first).booleanValue()) {
            if (((Integer) eeD_.second).intValue() <= ((int) (getHeight() * 0.5f))) {
                return true;
            }
        }
        return false;
    }

    private boolean g() {
        Pair<Boolean, Integer> c2 = c();
        if (((Boolean) c2.first).booleanValue()) {
            if (((Integer) c2.second).intValue() >= ((int) (getHeight() * 0.5f))) {
                return true;
            }
        }
        return false;
    }

    private boolean h() {
        ValueAnimator valueAnimator = this.p;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    void b() {
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt != null) {
                if (childAt.getTop() + this.g < i) {
                    i = childAt.getTop() + this.g;
                }
                if (childAt.getBottom() + this.g > i2) {
                    i2 = childAt.getBottom() + this.g;
                }
            }
        }
        if (i == Integer.MAX_VALUE || i2 == Integer.MIN_VALUE) {
            return;
        }
        if (this.g + i < getPaddingTop()) {
            this.g = getPaddingTop() - i;
        }
        if (getPaddingBottom() + i2 > this.e) {
            this.b = i2 + getPaddingBottom();
        } else {
            if (getPaddingBottom() + i2 >= this.e || !((Boolean) eeD_().first).booleanValue()) {
                return;
            }
            this.b = i2 + getPaddingBottom();
        }
    }

    public boolean c(RecyclerView recyclerView, int i) {
        if (getChildCount() == 0 || recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent() == 0) {
            return false;
        }
        return i > 0 ? this.g + getHeight() < this.b : this.g > 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return true;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        int i = this.b;
        if (getChildCount() == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt != null) {
                if (childAt.getTop() < i) {
                    i = childAt.getTop();
                }
                if (childAt.getBottom() > i2) {
                    i2 = childAt.getBottom();
                }
            }
        }
        return i2 - i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return this.g;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return (this.b - getPaddingTop()) - getPaddingBottom();
    }

    public void d(boolean z, int i) {
        b(z, i, false);
    }

    public void e(RecyclerView recyclerView) {
        b(recyclerView);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getPaddingBottom() {
        return super.getPaddingBottom() + d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getPaddingTop() {
        return super.getPaddingTop() + d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
        this.f.clear();
        this.g = 0;
        this.b = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        b(recyclerView);
        super.onItemsChanged(recyclerView);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (state.getItemCount() == 0 || state.isPreLayout()) {
            return;
        }
        if (this.s != 0 && getItemCount() != 1) {
            Log.w("HwFloatingBubblesLayoutManager", "Scroll state illegal.");
            return;
        }
        this.t = getItemCount();
        detachAndScrapAttachedViews(recycler);
        int i = 0;
        View viewForPosition = recycler.getViewForPosition(0);
        measureChildWithMargins(viewForPosition, 0, 0);
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        this.r = getDecoratedMeasuredHeight(viewForPosition);
        d(decoratedMeasuredWidth);
        if (this.f.size() > 0) {
            Rect rect = this.f.get(0);
            SparseArray<Rect> sparseArray = this.f;
            Rect rect2 = sparseArray.get(sparseArray.size() - 1);
            if (rect == null || rect2 == null) {
                return;
            }
            int i2 = rect2.bottom;
            int i3 = rect.top;
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            eeE_(rect);
            if (this.q.size() != 0) {
                c(recycler, paddingTop);
            } else {
                b(recycler, paddingTop);
            }
            i = paddingBottom + paddingTop + (i2 - i3);
        }
        int max = Math.max(a(), i);
        this.e = max;
        this.b = Math.max(max, this.b);
        if (this.q.size() != 0) {
            this.q.clear();
        }
        b();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        HwLayoutFinishedCallback hwLayoutFinishedCallback = this.n;
        if (hwLayoutFinishedCallback != null) {
            hwLayoutFinishedCallback.onLayoutFinished(true);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        this.s = i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() != 0 && recycler != null) {
            recycler.clear();
            int i2 = this.g;
            int i3 = i2 + i;
            int i4 = this.h;
            if (i3 < i4) {
                i = i4 - i2;
            } else if (i3 > this.b - a()) {
                i = (this.b - a()) - this.g;
            }
            e(recycler, i);
            this.g += i;
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        c cVar = new c(recyclerView.getContext());
        cVar.setTargetPosition(i);
        startSmoothScroll(cVar);
    }

    private boolean d(boolean z, int[][] iArr) {
        boolean z2 = false;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                if (z && (iArr[0][i] != 0 || iArr[1][i] != 0)) {
                    z2 = true;
                }
                childAt.offsetLeftAndRight(iArr[0][i]);
                childAt.offsetTopAndBottom(iArr[1][i]);
            }
        }
        return z2;
    }

    private void d(int i) {
        boolean z = (this.k == getWidth() && this.o == getHeight()) ? false : true;
        boolean z2 = (this.l == getPaddingLeft() && this.m == getPaddingRight()) ? false : true;
        if (this.f.size() == 0 || z || z2) {
            this.o = getHeight();
            this.k = getWidth();
            this.l = getPaddingLeft();
            this.m = getPaddingRight();
            this.f.clear();
            List<sma> a2 = smd.a(getItemCount() - 1, (getWidth() - getPaddingLeft()) - getPaddingRight(), getHeight(), i, this.f10690a);
            for (int i2 = 0; i2 < a2.size(); i2++) {
                sma smaVar = a2.get(i2);
                this.f.put(i2, new Rect((smaVar.c() - smaVar.a()) + getPaddingLeft(), smaVar.e() - smaVar.a(), smaVar.c() + smaVar.a() + getPaddingLeft(), smaVar.e() + smaVar.a()));
            }
        }
    }

    private void a(RecyclerView.Recycler recycler, Rect rect, int i, boolean z) {
        Rect rect2 = this.f.get(i);
        if (rect2 == null) {
            return;
        }
        int paddingTop = getPaddingTop();
        Rect rect3 = new Rect(rect2.left, rect2.top + paddingTop, rect2.right, rect2.bottom + paddingTop);
        if (Rect.intersects(rect, rect3)) {
            eeH_(z, rect3, recycler.getViewForPosition(i), -1);
        }
    }

    private Pair<Boolean, Integer> c() {
        int i;
        int childCount = getChildCount();
        if (childCount == 0) {
            return new Pair<>(Boolean.TRUE, Integer.valueOf(getHeight()));
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                i = 0;
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt != null && getPosition(childAt) == 0) {
                z = true;
                i = getDecoratedBottom(childAt);
                break;
            }
            i2++;
        }
        return new Pair<>(Boolean.valueOf(z), Integer.valueOf(i));
    }

    private int d() {
        return (int) ((getHeight() * 0.5f) - (this.r * 0.5f));
    }

    private void eeH_(boolean z, Rect rect, View view, int i) {
        SparseArray<a> sparseArray;
        int i2 = rect.bottom - rect.top;
        if (view instanceof ViewGroup) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams.width != i2 || layoutParams.height != i2) {
                layoutParams.width = i2;
                layoutParams.height = i2;
                view.setLayoutParams(layoutParams);
            }
        }
        if (z) {
            addView(view, 0);
        } else {
            addView(view);
        }
        int i3 = rect.top;
        int a2 = a(view, getWidth(), i2);
        measureChildWithMargins(view, 0, 0);
        if (i != -1 && (sparseArray = this.q) != null && sparseArray.get(i) != null) {
            a aVar = this.q.get(i);
            layoutDecorated(view, aVar.d, aVar.f10691a, aVar.c, aVar.b);
        } else {
            int i4 = this.g;
            layoutDecorated(view, a2, i3 - i4, a2 + i2, (i3 + i2) - i4);
        }
    }

    private int a() {
        return getHeight();
    }

    private int a(View view, int i, int i2) {
        float nextFloat;
        if ((view == null ? 0 : getPosition(view)) == this.i.c()) {
            nextFloat = i * 0.5f;
        } else {
            int[] iArr = d;
            nextFloat = (((iArr[r4 % iArr.length] + 0.5f) + new SecureRandom().nextFloat()) * i) / 4.0f;
        }
        return (int) (nextFloat - (i2 * 0.5f));
    }

    private Rect eeC_(int i) {
        return new Rect(getPaddingLeft(), this.g + i, getWidth() - getPaddingRight(), a() + this.g + i);
    }

    private void c(RecyclerView.Recycler recycler, int i) {
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            int keyAt = this.q.keyAt(i2);
            Rect rect = this.f.get(keyAt);
            if (rect != null) {
                Rect rect2 = new Rect(rect.left, rect.top + i, rect.right, rect.bottom + i);
                View viewForPosition = recycler.getViewForPosition(keyAt);
                eeH_(false, rect2, viewForPosition, keyAt);
                a aVar = this.q.get(keyAt);
                if (aVar != null) {
                    float f = aVar.e;
                    viewForPosition.setScaleX(f);
                    viewForPosition.setScaleY(f);
                    smd.eeK_(viewForPosition, getWidth());
                }
            }
        }
    }

    private void b(RecyclerView recyclerView) {
        this.q.clear();
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() == this.t) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt != null) {
                    this.q.put(getPosition(childAt), new a(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom(), childAt.getScaleX()));
                }
            }
            return;
        }
        removeAllViews();
        this.f.clear();
        this.g = 0;
        this.b = 0;
    }

    private void eeE_(Rect rect) {
        Rect rect2;
        int c2 = this.i.c();
        if (c2 >= getItemCount() || c2 < 0 || (rect2 = this.f.get(c2)) == null) {
            return;
        }
        this.g = rect2.top - rect.top;
    }

    private Pair<Boolean, Integer> eeD_() {
        int i;
        int childCount = getChildCount();
        boolean z = false;
        if (childCount == 0) {
            return new Pair<>(Boolean.TRUE, 0);
        }
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                i = 0;
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt != null && getPosition(childAt) == getItemCount() - 1) {
                i = getDecoratedTop(childAt);
                z = true;
                break;
            }
            i2++;
        }
        return new Pair<>(Boolean.valueOf(z), Integer.valueOf(i));
    }

    void e(boolean z) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        View childAt = getChildAt(0);
        if (childAt != null && (childAt.getParent() instanceof RecyclerView)) {
            RecyclerView recyclerView = (RecyclerView) childAt.getParent();
            float height = getHeight() / 2.0f;
            for (int i = 0; i < childCount; i++) {
                View childAt2 = getChildAt(i);
                if (childAt2 != null) {
                    if (!a(z, childAt2, height)) {
                        smd.eeL_(this, childAt2, height, this.i);
                    }
                    HwFloatingBubblesScrollFinishedCallback hwFloatingBubblesScrollFinishedCallback = this.c;
                    if (hwFloatingBubblesScrollFinishedCallback != null) {
                        hwFloatingBubblesScrollFinishedCallback.onScrollByFinished(childAt2, recyclerView);
                    }
                }
            }
        }
    }

    private boolean a(boolean z, View view, float f) {
        float translationY = view.getTranslationY() + (view.getHeight() * 0.5f) + view.getTop();
        if (z || !view.isSelected() || Float.compare(translationY, getHeight()) >= 0 || Float.compare(translationY, 0.0f) <= 0) {
            return false;
        }
        float a2 = smd.a(this.i, view, Math.abs(translationY - f) / f);
        if (b((0.005f + a2) - view.getScaleX(), (this.i.e() - 1.0f) * this.i.a())) {
            eeF_(view, a2, getWidth());
            return true;
        }
        return h();
    }

    private boolean b(float f, float f2) {
        return SystemClock.uptimeMillis() - this.i.d() < 500 && Float.compare(f, f2) >= 0 && !h();
    }

    private void eeF_(final View view, float f, final int i) {
        ValueAnimator valueAnimator = this.p;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(view.getScaleX(), f);
            this.p = ofFloat;
            ofFloat.setDuration(200L);
            this.p.setInterpolator(new LinearInterpolator());
            this.p.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: smb
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    HwFloatingBubblesLayoutManager.eeG_(view, i, valueAnimator2);
                }
            });
            this.p.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void eeG_(View view, int i, ValueAnimator valueAnimator) {
        if (valueAnimator == null) {
            return;
        }
        Object animatedValue = valueAnimator.getAnimatedValue();
        if (animatedValue instanceof Float) {
            float floatValue = ((Float) animatedValue).floatValue();
            view.setScaleX(floatValue);
            view.setScaleY(floatValue);
            smd.eeK_(view, i);
        }
    }
}
