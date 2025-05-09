package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import defpackage.slc;
import defpackage.smn;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes9.dex */
public class HwLinearLayoutManagerEx extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {

    /* renamed from: a, reason: collision with root package name */
    boolean f10714a;
    OrientationHelper b;
    int c;
    int d;
    int e;
    private OnReferenceItemListener f;
    private boolean g;
    SavedState h;
    private e i;
    final a j;
    private boolean k;
    private boolean l;
    private final d m;
    private boolean n;
    private boolean o;
    private Method p;
    private int q;
    private int[] r;
    private Object s;
    private Object t;
    private int u;
    private int w;

    public interface OnReferenceItemListener {
        boolean isNeedRefreshReferenceLayout(View view);
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new e();

        /* renamed from: a, reason: collision with root package name */
        int f10715a;
        int b;
        boolean c;

        class e implements Parcelable.Creator<SavedState> {
            e() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        }

        public SavedState() {
        }

        boolean a() {
            return this.f10715a >= 0;
        }

        void b() {
            this.f10715a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f10715a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }

        SavedState(Parcel parcel) {
            this.f10715a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f10715a = savedState.f10715a;
            this.b = savedState.b;
            this.c = savedState.c;
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f10716a;
        boolean b;
        int c;
        boolean d;
        OrientationHelper e;

        a() {
            a();
        }

        private void a() {
            this.c = -1;
            this.f10716a = Integer.MIN_VALUE;
            this.d = false;
            this.b = false;
        }

        void c() {
            this.f10716a = this.d ? this.e.getEndAfterPadding() : this.e.getStartAfterPadding();
        }

        void d() {
            a();
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.c + ", mCoordinate=" + this.f10716a + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.b + '}';
        }

        public void efA_(View view, int i) {
            int totalSpaceChange = this.e.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                efy_(view, i);
                return;
            }
            this.c = i;
            if (this.d) {
                int endAfterPadding = (this.e.getEndAfterPadding() - totalSpaceChange) - this.e.getDecoratedEnd(view);
                this.f10716a = this.e.getEndAfterPadding() - endAfterPadding;
                if (endAfterPadding > 0) {
                    int decoratedMeasurement = this.e.getDecoratedMeasurement(view);
                    int i2 = this.f10716a;
                    int startAfterPadding = this.e.getStartAfterPadding();
                    int min = (i2 - decoratedMeasurement) - (startAfterPadding + Math.min(this.e.getDecoratedStart(view) - startAfterPadding, 0));
                    if (min < 0) {
                        this.f10716a += Math.min(endAfterPadding, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int decoratedStart = this.e.getDecoratedStart(view);
            int startAfterPadding2 = decoratedStart - this.e.getStartAfterPadding();
            this.f10716a = decoratedStart;
            if (startAfterPadding2 > 0) {
                int decoratedMeasurement2 = this.e.getDecoratedMeasurement(view);
                int endAfterPadding2 = (this.e.getEndAfterPadding() - Math.min(0, (this.e.getEndAfterPadding() - totalSpaceChange) - this.e.getDecoratedEnd(view))) - (decoratedStart + decoratedMeasurement2);
                if (endAfterPadding2 < 0) {
                    this.f10716a -= Math.min(startAfterPadding2, -endAfterPadding2);
                }
            }
        }

        boolean efz_(View view, RecyclerView.State state) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount();
        }

        public void efy_(View view, int i) {
            if (this.d) {
                this.f10716a = this.e.getDecoratedEnd(view) + this.e.getTotalSpaceChange();
            } else {
                this.f10716a = this.e.getDecoratedStart(view);
            }
            this.c = i;
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public View f10717a;
        public View c;
        public boolean d;

        b() {
        }
    }

    protected static class d {
        public boolean b;
        public boolean c;
        public int d;
        public boolean e;

        protected d() {
        }

        void e() {
            this.d = 0;
            this.e = false;
            this.b = false;
            this.c = false;
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        int f10718a;
        int b;
        int c;
        int d;
        int i;
        int j;
        int k;
        boolean o;
        boolean e = true;
        int h = 0;
        int f = 0;
        boolean g = false;
        List<RecyclerView.ViewHolder> n = null;

        e() {
        }

        private View efw_() {
            int size = this.n.size();
            for (int i = 0; i < size; i++) {
                View view = this.n.get(i).itemView;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams instanceof RecyclerView.LayoutParams) {
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    if (!layoutParams2.isItemRemoved() && this.d == layoutParams2.getViewLayoutPosition()) {
                        a(view);
                        return view;
                    }
                }
            }
            return null;
        }

        public View b(View view) {
            int viewLayoutPosition;
            int size = this.n.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.n.get(i2).itemView;
                ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
                if (layoutParams instanceof RecyclerView.LayoutParams) {
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                    if (view3 != view && !layoutParams2.isItemRemoved() && (viewLayoutPosition = (layoutParams2.getViewLayoutPosition() - this.d) * this.c) >= 0 && viewLayoutPosition < i) {
                        view2 = view3;
                        if (viewLayoutPosition == 0) {
                            break;
                        }
                        i = viewLayoutPosition;
                    }
                }
            }
            return view2;
        }

        boolean b(RecyclerView.State state) {
            int i = this.d;
            return i >= 0 && i < state.getItemCount();
        }

        View efx_(RecyclerView.Recycler recycler) {
            if (this.n != null) {
                return efw_();
            }
            View viewForPosition = recycler.getViewForPosition(this.d);
            this.d += this.c;
            return viewForPosition;
        }

        public void d() {
            a(null);
        }

        public void a(View view) {
            View b = b(view);
            if (b == null) {
                this.d = -1;
                return;
            }
            ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                this.d = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            }
        }
    }

    public HwLinearLayoutManagerEx(Context context) {
        this(context, 1, false);
    }

    private void b(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.o == z) {
            return;
        }
        this.o = z;
        requestLayout();
    }

    private void c(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.n) {
            return;
        }
        this.n = z;
        requestLayout();
    }

    private void e(int i, int i2) {
        this.i.f10718a = i2 - this.b.getStartAfterPadding();
        e eVar = this.i;
        eVar.d = i;
        eVar.c = this.f10714a ? 1 : -1;
        eVar.j = -1;
        eVar.b = i2;
        eVar.i = Integer.MIN_VALUE;
    }

    private View efo_(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.f10714a ? efk_(recycler, state) : a(recycler, state);
    }

    private View efp_() {
        return this.f10714a ? efl_() : efn_();
    }

    private View efq_() {
        return this.f10714a ? efn_() : efl_();
    }

    private View efr_() {
        return getChildAt(this.f10714a ? 0 : getChildCount() - 1);
    }

    private View efs_() {
        return getChildAt(this.f10714a ? getChildCount() - 1 : 0);
    }

    private void o() {
        if (this.c == 1 || !g()) {
            this.f10714a = this.n;
        } else {
            this.f10714a = !this.n;
        }
    }

    public boolean a(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            return ((RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams()).isItemRemoved();
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.h == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Deprecated
    protected int b(RecyclerView.State state) {
        if (state.hasTargetScrollPosition()) {
            return this.b.getTotalSpace();
        }
        return 0;
    }

    public int c() {
        View efu_ = efu_(getChildCount() - 1, -1, false, true);
        if (efu_ == null) {
            return -1;
        }
        return getPosition(efu_);
    }

    public void c(int i, int i2) {
        this.d = i;
        this.e = i2;
        SavedState savedState = this.h;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    protected void c(RecyclerView.State state, int[] iArr) {
        int i;
        int b2 = b(state);
        if (this.i.j == -1) {
            i = 0;
        } else {
            i = b2;
            b2 = 0;
        }
        iArr[0] = b2;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.c == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.c == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        if (this.c != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        e();
        e(i > 0 ? 1 : -1, Math.abs(i), true, state);
        e(state, this.i, layoutPrefetchRegistry);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        boolean z;
        int i2;
        SavedState savedState = this.h;
        if (savedState == null || !savedState.a()) {
            o();
            z = this.f10714a;
            i2 = this.d;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.h;
            z = savedState2.c;
            i2 = savedState2.f10715a;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.q && i2 >= 0 && i2 < i; i4++) {
            layoutPrefetchRegistry.addPosition(i2, 0);
            i2 += i3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return c(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return e(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return a(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = (i < getPosition(getChildAt(0))) != this.f10714a ? -1 : 1;
        return this.c == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return c(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return e(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return a(state);
    }

    public int d() {
        View efu_ = efu_(0, getChildCount(), false, true);
        if (efu_ == null) {
            return -1;
        }
        return getPosition(efu_);
    }

    void d(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar, int i) {
    }

    boolean f() {
        return this.b.getMode() == 0 && this.b.getEnd() == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    protected boolean g() {
        return getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean hasFlexibleChildInBothOrientations() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.l) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int e2;
        o();
        if (getChildCount() == 0 || (e2 = e(i)) == Integer.MIN_VALUE) {
            return null;
        }
        e();
        e(e2, (int) (this.b.getTotalSpace() * 0.33333334f), false, state);
        e eVar = this.i;
        eVar.i = Integer.MIN_VALUE;
        eVar.e = false;
        d(recycler, eVar, state, true);
        View efq_ = e2 == -1 ? efq_() : efp_();
        View efs_ = e2 == -1 ? efs_() : efr_();
        if (!efs_.hasFocusable()) {
            return efq_;
        }
        if (efq_ == null) {
            return null;
        }
        return efs_;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(d());
            accessibilityEvent.setToIndex(c());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        int i3;
        int i4;
        int a2;
        int i5;
        View findViewByPosition;
        int decoratedStart;
        int i6;
        int i7 = -1;
        if (!(this.h == null && this.d == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        SavedState savedState = this.h;
        if (savedState != null && savedState.a()) {
            this.d = this.h.f10715a;
        }
        e();
        this.i.e = false;
        o();
        View focusedChild = getFocusedChild();
        a aVar = this.j;
        if (!aVar.b || this.d != -1 || this.h != null) {
            aVar.d();
            a aVar2 = this.j;
            aVar2.d = this.f10714a ^ this.o;
            d(recycler, state, aVar2);
            this.j.b = true;
        } else if (focusedChild != null && (this.b.getDecoratedStart(focusedChild) >= this.b.getEndAfterPadding() || this.b.getDecoratedEnd(focusedChild) <= this.b.getStartAfterPadding())) {
            this.j.efA_(focusedChild, getPosition(focusedChild));
        }
        e eVar = this.i;
        eVar.j = eVar.k >= 0 ? 1 : -1;
        int[] iArr = this.r;
        iArr[0] = 0;
        iArr[1] = 0;
        c(state, iArr);
        int max = Math.max(0, this.r[0]) + this.b.getStartAfterPadding();
        int max2 = Math.max(0, this.r[1]) + this.b.getEndPadding();
        if (state.isPreLayout() && (i5 = this.d) != -1 && this.e != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i5)) != null) {
            if (this.f10714a) {
                i6 = this.b.getEndAfterPadding() - this.b.getDecoratedEnd(findViewByPosition);
                decoratedStart = this.e;
            } else {
                decoratedStart = this.b.getDecoratedStart(findViewByPosition) - this.b.getStartAfterPadding();
                i6 = this.e;
            }
            int i8 = i6 - decoratedStart;
            if (i8 > 0) {
                max += i8;
            } else {
                max2 -= i8;
            }
        }
        a aVar3 = this.j;
        if (!aVar3.d ? !this.f10714a : this.f10714a) {
            i7 = 1;
        }
        d(recycler, state, aVar3, i7);
        detachAndScrapAttachedViews(recycler);
        this.i.o = f();
        this.i.g = state.isPreLayout();
        this.i.f = 0;
        a aVar4 = this.j;
        if (aVar4.d) {
            e(aVar4);
            e eVar2 = this.i;
            eVar2.h = max;
            d(recycler, eVar2, state, false);
            e eVar3 = this.i;
            i2 = eVar3.b;
            int i9 = eVar3.d;
            int i10 = eVar3.f10718a;
            if (i10 > 0) {
                max2 += i10;
            }
            b(this.j);
            e eVar4 = this.i;
            eVar4.h = max2;
            eVar4.d += eVar4.c;
            d(recycler, eVar4, state, false);
            e eVar5 = this.i;
            i = eVar5.b;
            int i11 = eVar5.f10718a;
            if (i11 > 0) {
                e(i9, i2);
                e eVar6 = this.i;
                eVar6.h = i11;
                d(recycler, eVar6, state, false);
                i2 = this.i.b;
            }
        } else {
            b(aVar4);
            e eVar7 = this.i;
            eVar7.h = max2;
            d(recycler, eVar7, state, false);
            e eVar8 = this.i;
            i = eVar8.b;
            int i12 = eVar8.d;
            int i13 = eVar8.f10718a;
            if (i13 > 0) {
                max += i13;
            }
            e(this.j);
            e eVar9 = this.i;
            eVar9.h = max;
            eVar9.d += eVar9.c;
            d(recycler, eVar9, state, false);
            e eVar10 = this.i;
            i2 = eVar10.b;
            int i14 = eVar10.f10718a;
            if (i14 > 0) {
                a(i12, i);
                e eVar11 = this.i;
                eVar11.h = i14;
                d(recycler, eVar11, state, false);
                i = this.i.b;
            }
        }
        if (getChildCount() > 0) {
            if (this.f10714a ^ this.o) {
                int a3 = a(i, recycler, state, true);
                i3 = i2 + a3;
                i4 = i + a3;
                a2 = b(i3, recycler, state, false);
            } else {
                int b2 = b(i2, recycler, state, true);
                i3 = i2 + b2;
                i4 = i + b2;
                a2 = a(i4, recycler, state, false);
            }
            i2 = i3 + a2;
            i = i4 + a2;
        }
        c(recycler, state, i2, i);
        if (state.isPreLayout()) {
            this.j.d();
        } else {
            this.b.onLayoutComplete();
        }
        this.g = this.o;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.h = null;
        this.d = -1;
        this.e = Integer.MIN_VALUE;
        this.j.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.h = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.h != null) {
            return new SavedState(this.h);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            e();
            boolean z = this.g ^ this.f10714a;
            savedState.c = z;
            if (z) {
                View efr_ = efr_();
                savedState.b = this.b.getEndAfterPadding() - this.b.getDecoratedEnd(efr_);
                savedState.f10715a = getPosition(efr_);
            } else {
                View efs_ = efs_();
                savedState.f10715a = getPosition(efs_);
                savedState.b = this.b.getDecoratedStart(efs_) - this.b.getStartAfterPadding();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
    public void prepareForDrop(View view, View view2, int i, int i2) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        e();
        o();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        char c = position < position2 ? (char) 1 : (char) 65535;
        if (this.f10714a) {
            if (c == 1) {
                c(position2, this.b.getEndAfterPadding() - (this.b.getDecoratedStart(view2) + this.b.getDecoratedMeasurement(view)));
                return;
            } else {
                c(position2, this.b.getEndAfterPadding() - this.b.getDecoratedEnd(view2));
                return;
            }
        }
        if (c == 65535) {
            c(position2, this.b.getDecoratedStart(view2) - this.b.getStartAfterPadding());
        } else {
            c(position2, (this.b.getDecoratedEnd(view2) - this.b.getDecoratedMeasurement(view)) - this.b.getStartAfterPadding());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.c == 1) {
            return 0;
        }
        return d(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.d = i;
        this.e = Integer.MIN_VALUE;
        SavedState savedState = this.h;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.c == 0) {
            return 0;
        }
        return d(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.h == null && this.g == this.o;
    }

    public HwLinearLayoutManagerEx(Context context, int i, boolean z) {
        this.c = 1;
        this.f10714a = false;
        this.d = -1;
        this.e = Integer.MIN_VALUE;
        this.h = null;
        this.j = new a();
        this.n = false;
        this.o = false;
        this.k = true;
        this.m = new d();
        this.q = 2;
        this.r = new int[2];
        this.p = null;
        this.s = null;
        this.t = null;
        this.u = 0;
        this.w = 0;
        a(i);
        c(z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    private View efn_() {
        return eft_(getChildCount() - 1, -1);
    }

    protected int[] a() {
        return new int[]{this.u, this.w};
    }

    private void a(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        assertNotInLayoutOrScroll(null);
        if (i != this.c || this.b == null) {
            OrientationHelper createOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
            this.b = createOrientationHelper;
            this.j.e = createOrientationHelper;
            this.c = i;
            requestLayout();
        }
    }

    private void c(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        if (!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.ViewHolder> scrapList = recycler.getScrapList();
        int size = scrapList.size();
        int position = getPosition(getChildAt(0));
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView.ViewHolder viewHolder = scrapList.get(i5);
            if (!a(viewHolder)) {
                if ((viewHolder.getLayoutPosition() < position) != this.f10714a) {
                    i3 += this.b.getDecoratedMeasurement(viewHolder.itemView);
                } else {
                    i4 += this.b.getDecoratedMeasurement(viewHolder.itemView);
                }
            }
        }
        this.i.n = scrapList;
        if (i3 > 0) {
            e(getPosition(efs_()), i);
            e eVar = this.i;
            eVar.h = i3;
            eVar.f10718a = 0;
            eVar.d();
            d(recycler, this.i, state, false);
        }
        if (i4 > 0) {
            a(getPosition(efr_()), i2);
            e eVar2 = this.i;
            eVar2.h = i4;
            eVar2.f10718a = 0;
            eVar2.d();
            d(recycler, this.i, state, false);
        }
        this.i.n = null;
    }

    private int a(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        e();
        b bVar = new b();
        bVar.c = b(!this.k, true);
        bVar.f10717a = a(!this.k, true);
        bVar.d = this.k;
        return smn.e(state, this.b, bVar, this);
    }

    private void c(RecyclerView.Recycler recycler, int i, int i2) {
        if (i < 0) {
            return;
        }
        int i3 = i - i2;
        int childCount = getChildCount();
        if (!this.f10714a) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (this.b.getDecoratedEnd(childAt) > i3 || this.b.getTransformedEndWithDecoration(childAt) > i3) {
                    d(recycler, 0, i4);
                    return;
                }
            }
            return;
        }
        int i5 = childCount - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            View childAt2 = getChildAt(i6);
            if (this.b.getDecoratedEnd(childAt2) > i3 || this.b.getTransformedEndWithDecoration(childAt2) > i3) {
                d(recycler, i5, i6);
                return;
            }
        }
    }

    private void d(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar) {
        if (b(state, aVar) || b(recycler, state, aVar)) {
            return;
        }
        aVar.c();
        aVar.c = this.o ? state.getItemCount() - 1 : 0;
    }

    private View efm_(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.f10714a ? a(recycler, state) : efk_(recycler, state);
    }

    private int b(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.b.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -d(startAfterPadding2, recycler, state);
        if (!z || (startAfterPadding = (i + i2) - this.b.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.b.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    private View efl_() {
        return eft_(0, getChildCount());
    }

    private void a(int i, int i2) {
        this.i.f10718a = this.b.getEndAfterPadding() - i2;
        e eVar = this.i;
        eVar.c = this.f10714a ? -1 : 1;
        eVar.d = i;
        eVar.j = 1;
        eVar.b = i2;
        eVar.i = Integer.MIN_VALUE;
    }

    private boolean b(RecyclerView.Recycler recycler, RecyclerView.State state, a aVar) {
        View efo_;
        int startAfterPadding;
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (this.f == null && focusedChild != null && aVar.efz_(focusedChild, state)) {
            aVar.efA_(focusedChild, getPosition(focusedChild));
            return true;
        }
        if (this.g != this.o) {
            return false;
        }
        if (aVar.d) {
            efo_ = efm_(recycler, state);
        } else {
            efo_ = efo_(recycler, state);
        }
        if (efo_ == null) {
            return false;
        }
        aVar.efy_(efo_, getPosition(efo_));
        if (!state.isPreLayout() && supportsPredictiveItemAnimations() && (this.b.getDecoratedStart(efo_) >= this.b.getEndAfterPadding() || this.b.getDecoratedEnd(efo_) < this.b.getStartAfterPadding())) {
            if (aVar.d) {
                startAfterPadding = this.b.getEndAfterPadding();
            } else {
                startAfterPadding = this.b.getStartAfterPadding();
            }
            aVar.f10716a = startAfterPadding;
        }
        return true;
    }

    private void e(a aVar) {
        e(aVar.c, aVar.f10716a);
    }

    void e() {
        if (this.i == null) {
            this.i = b();
        }
    }

    private int e(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        e();
        b bVar = new b();
        bVar.c = b(!this.k, true);
        bVar.f10717a = a(!this.k, true);
        bVar.d = this.k;
        return smn.d(state, this.b, bVar, this, this.f10714a);
    }

    private void a(RecyclerView.Recycler recycler, int i, int i2) {
        int childCount = getChildCount();
        if (i < 0) {
            return;
        }
        int end = (this.b.getEnd() - i) + i2;
        if (this.f10714a) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (this.b.getDecoratedStart(childAt) < end || this.b.getTransformedStartWithDecoration(childAt) < end) {
                    d(recycler, 0, i3);
                    return;
                }
            }
            return;
        }
        int i4 = childCount - 1;
        for (int i5 = i4; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            if (this.b.getDecoratedStart(childAt2) < end || this.b.getTransformedStartWithDecoration(childAt2) < end) {
                d(recycler, i4, i5);
                return;
            }
        }
    }

    private boolean b(RecyclerView.State state, a aVar) {
        int i;
        if (state.isPreLayout() || (i = this.d) == -1) {
            return false;
        }
        if (i >= 0 && i < state.getItemCount()) {
            aVar.c = this.d;
            SavedState savedState = this.h;
            if (savedState != null && savedState.a()) {
                boolean z = this.h.c;
                aVar.d = z;
                if (z) {
                    aVar.f10716a = this.b.getEndAfterPadding() - this.h.b;
                } else {
                    aVar.f10716a = this.b.getStartAfterPadding() + this.h.b;
                }
                return true;
            }
            if (this.e == Integer.MIN_VALUE) {
                return efi_(aVar, findViewByPosition(this.d));
            }
            boolean z2 = this.f10714a;
            aVar.d = z2;
            if (z2) {
                aVar.f10716a = this.b.getEndAfterPadding() - this.e;
            } else {
                aVar.f10716a = this.b.getStartAfterPadding() + this.e;
            }
            return true;
        }
        this.d = -1;
        this.e = Integer.MIN_VALUE;
        return false;
    }

    View b(boolean z, boolean z2) {
        if (this.f10714a) {
            return efu_(getChildCount() - 1, -1, z, z2);
        }
        return efu_(0, getChildCount(), z, z2);
    }

    private View efk_(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return efv_(recycler, state, getChildCount() - 1, -1, state.getItemCount());
    }

    private View efj_(int i, int i2, int i3, int i4) {
        try {
            if (this.t == null) {
                Field declaredField = RecyclerView.LayoutManager.class.getDeclaredField("mVerticalBoundCheck");
                declaredField.setAccessible(true);
                this.t = declaredField.get(this);
            }
            if (this.p == null) {
                Class cls = Integer.TYPE;
                this.p = slc.b("findOneViewWithinBoundFlags", new Class[]{cls, cls, cls, cls}, "androidx.recyclerview.widget.ViewBoundsCheck");
            }
            Object c = slc.c(this.t, this.p, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
            if (c instanceof View) {
                return (View) c;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            Log.e("LinearLayoutManager", "Reflect findVerticalOneViewWithinBoundFlags failed!");
            return null;
        }
    }

    public HwLinearLayoutManagerEx(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = 1;
        this.f10714a = false;
        this.d = -1;
        this.e = Integer.MIN_VALUE;
        this.h = null;
        this.j = new a();
        this.n = false;
        this.o = false;
        this.k = true;
        this.m = new d();
        this.q = 2;
        this.r = new int[2];
        this.p = null;
        this.s = null;
        this.t = null;
        this.u = 0;
        this.w = 0;
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        a(properties.orientation);
        c(properties.reverseLayout);
        b(properties.stackFromEnd);
    }

    private boolean efi_(a aVar, View view) {
        int decoratedStart;
        if (aVar == null) {
            return false;
        }
        if (view != null) {
            if (this.b.getDecoratedMeasurement(view) > this.b.getTotalSpace()) {
                aVar.c();
                return true;
            }
            if (this.b.getDecoratedStart(view) - this.b.getStartAfterPadding() < 0) {
                aVar.f10716a = this.b.getStartAfterPadding();
                aVar.d = false;
                return true;
            }
            if (this.b.getEndAfterPadding() - this.b.getDecoratedEnd(view) < 0) {
                aVar.f10716a = this.b.getEndAfterPadding();
                aVar.d = true;
                return true;
            }
            if (aVar.d) {
                decoratedStart = this.b.getDecoratedEnd(view) + this.b.getTotalSpaceChange();
            } else {
                decoratedStart = this.b.getDecoratedStart(view);
            }
            aVar.f10716a = decoratedStart;
        } else {
            if (getChildCount() > 0) {
                aVar.d = (this.d < getPosition(getChildAt(0))) == this.f10714a;
            }
            aVar.c();
        }
        return true;
    }

    private int a(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.b.getEndAfterPadding() - i;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -d(-endAfterPadding2, recycler, state);
        if (!z || (endAfterPadding = this.b.getEndAfterPadding() - (i + i2)) <= 0) {
            return i2;
        }
        this.b.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    private void b(a aVar) {
        a(aVar.c, aVar.f10716a);
    }

    e b() {
        return new e();
    }

    private int c(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        e();
        b bVar = new b();
        bVar.c = b(!this.k, true);
        bVar.f10717a = a(!this.k, true);
        bVar.d = this.k;
        return smn.b(state, this.b, bVar, this);
    }

    private void e(int i, int i2, boolean z, RecyclerView.State state) {
        int startAfterPadding;
        this.i.o = f();
        this.i.j = i;
        int[] iArr = this.r;
        iArr[0] = 0;
        iArr[1] = 0;
        c(state, iArr);
        int max = Math.max(0, this.r[0]);
        int max2 = Math.max(0, this.r[1]);
        boolean z2 = i == 1;
        e eVar = this.i;
        int i3 = z2 ? max2 : max;
        eVar.h = i3;
        if (!z2) {
            max = max2;
        }
        eVar.f = max;
        if (z2) {
            eVar.h = i3 + this.b.getEndPadding();
            View efr_ = efr_();
            e eVar2 = this.i;
            eVar2.c = this.f10714a ? -1 : 1;
            int position = getPosition(efr_);
            e eVar3 = this.i;
            eVar2.d = position + eVar3.c;
            eVar3.b = this.b.getDecoratedEnd(efr_);
            startAfterPadding = this.b.getDecoratedEnd(efr_) - this.b.getEndAfterPadding();
        } else {
            View efs_ = efs_();
            this.i.h += this.b.getStartAfterPadding();
            e eVar4 = this.i;
            eVar4.c = this.f10714a ? 1 : -1;
            int position2 = getPosition(efs_);
            e eVar5 = this.i;
            eVar4.d = position2 + eVar5.c;
            eVar5.b = this.b.getDecoratedStart(efs_);
            startAfterPadding = (-this.b.getDecoratedStart(efs_)) + this.b.getStartAfterPadding();
        }
        e eVar6 = this.i;
        eVar6.f10718a = i2;
        if (z) {
            eVar6.f10718a = i2 - startAfterPadding;
        }
        eVar6.i = startAfterPadding;
    }

    void e(RecyclerView.State state, e eVar, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i = eVar.d;
        if (i < 0 || i >= state.getItemCount()) {
            return;
        }
        layoutPrefetchRegistry.addPosition(i, Math.max(0, eVar.i));
    }

    int d(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        e();
        this.i.e = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        e(i2, abs, true, state);
        e eVar = this.i;
        int d2 = eVar.i + d(recycler, eVar, state, false);
        if (d2 < 0) {
            return 0;
        }
        if (abs > d2) {
            i = i2 * d2;
        }
        this.b.offsetChildren(-i);
        this.i.k = i;
        return i;
    }

    private void d(RecyclerView.Recycler recycler, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
        } else {
            while (true) {
                i2--;
                if (i2 < i) {
                    return;
                } else {
                    removeAndRecycleViewAt(i2, recycler);
                }
            }
        }
    }

    private void d(RecyclerView.Recycler recycler, e eVar) {
        if (!eVar.e || eVar.o) {
            return;
        }
        int i = eVar.i;
        int i2 = eVar.f;
        if (eVar.j == -1) {
            a(recycler, i, i2);
        } else {
            c(recycler, i, i2);
        }
    }

    int d(RecyclerView.Recycler recycler, e eVar, RecyclerView.State state, boolean z) {
        int i = eVar.f10718a;
        int i2 = eVar.i;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                eVar.i = i2 + i;
            }
            d(recycler, eVar);
        }
        int i3 = eVar.f10718a + eVar.h;
        d dVar = this.m;
        while (true) {
            if ((!eVar.o && i3 <= 0) || !eVar.b(state)) {
                break;
            }
            dVar.e();
            c(recycler, state, eVar, dVar);
            if (!dVar.e) {
                eVar.b += dVar.d * eVar.j;
                if (!dVar.b || eVar.n != null || !state.isPreLayout()) {
                    int i4 = eVar.f10718a;
                    int i5 = dVar.d;
                    eVar.f10718a = i4 - i5;
                    i3 -= i5;
                }
                int i6 = eVar.i;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + dVar.d;
                    eVar.i = i7;
                    int i8 = eVar.f10718a;
                    if (i8 < 0) {
                        eVar.i = i7 + i8;
                    }
                    d(recycler, eVar);
                }
                if (z && dVar.c) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - eVar.f10718a;
    }

    void c(RecyclerView.Recycler recycler, RecyclerView.State state, e eVar, d dVar) {
        View efx_ = eVar.efx_(recycler);
        if (efx_ == null) {
            dVar.e = true;
            return;
        }
        if (eVar.n == null) {
            if (this.f10714a == (eVar.j == -1)) {
                addView(efx_);
            } else {
                addView(efx_, 0);
            }
        } else {
            if (this.f10714a == (eVar.j == -1)) {
                addDisappearingView(efx_);
            } else {
                addDisappearingView(efx_, 0);
            }
        }
        measureChildWithMargins(efx_, 0, 0);
        dVar.d = this.b.getDecoratedMeasurement(efx_);
        efh_(eVar, dVar, efx_);
        dVar.c = efx_.hasFocusable();
    }

    private void efh_(e eVar, d dVar, View view) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int paddingLeft;
        int decoratedMeasurementInOther;
        int i7;
        int i8;
        if (eVar == null || dVar == null || view == null) {
            return;
        }
        if (this.c == 1) {
            if (g()) {
                decoratedMeasurementInOther = getWidth() - getPaddingRight();
                paddingLeft = decoratedMeasurementInOther - this.b.getDecoratedMeasurementInOther(view);
            } else {
                paddingLeft = getPaddingLeft();
                decoratedMeasurementInOther = this.b.getDecoratedMeasurementInOther(view) + paddingLeft;
            }
            if (eVar.j == -1) {
                i8 = eVar.b;
                i7 = i8 - dVar.d;
            } else {
                i7 = eVar.b;
                i8 = dVar.d + i7;
            }
            i6 = i8;
            i3 = decoratedMeasurementInOther;
            i4 = i7;
            i5 = paddingLeft;
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther2 = this.b.getDecoratedMeasurementInOther(view);
            if (eVar.j == -1) {
                i2 = eVar.b;
                i = i2 - dVar.d;
            } else {
                int i9 = eVar.b;
                i = i9;
                i2 = dVar.d + i9;
            }
            i3 = i2;
            i4 = paddingTop;
            i5 = i;
            i6 = decoratedMeasurementInOther2 + paddingTop;
        }
        layoutDecoratedWithMargins(view, i5, i4, i3, i6);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            if (layoutParams2.isItemRemoved() || layoutParams2.isItemChanged()) {
                dVar.b = true;
            }
        }
    }

    int e(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.c == 1) ? 1 : Integer.MIN_VALUE : this.c == 0 ? 1 : Integer.MIN_VALUE : this.c == 1 ? -1 : Integer.MIN_VALUE : this.c == 0 ? -1 : Integer.MIN_VALUE : (this.c != 1 && g()) ? -1 : 1 : (this.c != 1 && g()) ? 1 : -1;
    }

    View a(boolean z, boolean z2) {
        if (this.f10714a) {
            return efu_(0, getChildCount(), z, z2);
        }
        return efu_(getChildCount() - 1, -1, z, z2);
    }

    private View a(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return efv_(recycler, state, 0, getChildCount(), state.getItemCount());
    }

    View efv_(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2, int i3) {
        OnReferenceItemListener onReferenceItemListener;
        e();
        int startAfterPadding = this.b.getStartAfterPadding();
        int endAfterPadding = this.b.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof RecyclerView.LayoutParams) && position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) layoutParams).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.b.getDecoratedStart(childAt) < endAfterPadding && this.b.getDecoratedEnd(childAt) >= startAfterPadding) {
                    OnReferenceItemListener onReferenceItemListener2 = this.f;
                    if (onReferenceItemListener2 == null || onReferenceItemListener2.isNeedRefreshReferenceLayout(childAt)) {
                        return childAt;
                    }
                } else if (view == null && ((onReferenceItemListener = this.f) == null || onReferenceItemListener.isNeedRefreshReferenceLayout(childAt))) {
                    view = childAt;
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    View efu_(int i, int i2, boolean z, boolean z2) {
        e();
        int i3 = GlMapUtil.DEVICE_DISPLAY_DPI_HIGH;
        int i4 = z ? 24579 : 320;
        if (!z2) {
            i3 = 0;
        }
        if (this.c == 0) {
            return efg_(i, i2, i4, i3);
        }
        return efj_(i, i2, i4, i3);
    }

    View eft_(int i, int i2) {
        int i3;
        int i4;
        e();
        if (i2 <= i && i2 >= i) {
            return getChildAt(i);
        }
        if (this.b.getDecoratedStart(getChildAt(i)) < this.b.getStartAfterPadding()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        if (this.c == 0) {
            return efg_(i, i2, i3, i4);
        }
        return efj_(i, i2, i3, i4);
    }

    private View efg_(int i, int i2, int i3, int i4) {
        try {
            if (this.s == null) {
                Field declaredField = RecyclerView.LayoutManager.class.getDeclaredField("mHorizontalBoundCheck");
                declaredField.setAccessible(true);
                this.s = declaredField.get(this);
            }
            if (this.p == null) {
                Class cls = Integer.TYPE;
                this.p = slc.b("findOneViewWithinBoundFlags", new Class[]{cls, cls, cls, cls}, "androidx.recyclerview.widget.ViewBoundsCheck");
            }
            Object c = slc.c(this.s, this.p, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
            if (c instanceof View) {
                return (View) c;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            Log.e("LinearLayoutManager", "Reflect findHorizontalOneViewWithinBoundFlags failed!");
            return null;
        }
    }
}
