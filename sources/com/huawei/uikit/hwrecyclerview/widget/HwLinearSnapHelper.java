package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.OverScroller;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/* loaded from: classes9.dex */
public class HwLinearSnapHelper extends SnapHelper {
    private OrientationHelper b;
    protected RecyclerView c;
    protected int d;
    protected int[] e;
    private View g;
    private OrientationHelper i;
    private SnapListener j;

    /* renamed from: a, reason: collision with root package name */
    protected int f10719a = 10;
    private boolean h = false;
    private final RecyclerView.OnScrollListener f = new b();

    public interface SnapListener {
        boolean isTargetNoSnap(int i);
    }

    class a extends LinearSmoothScroller {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            int i = displayMetrics.densityDpi;
            if (i > 0) {
                return 300.0f / i;
            }
            return 0.625f;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            HwLinearSnapHelper hwLinearSnapHelper = HwLinearSnapHelper.this;
            RecyclerView recyclerView = hwLinearSnapHelper.c;
            if (recyclerView == null) {
                return;
            }
            int[] calculateDistanceToFinalSnap = hwLinearSnapHelper.calculateDistanceToFinalSnap(recyclerView.getLayoutManager(), view);
            int i = calculateDistanceToFinalSnap[0];
            int i2 = calculateDistanceToFinalSnap[1];
            int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
            if (calculateTimeForDeceleration > 0) {
                action.update(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }
    }

    class b extends RecyclerView.OnScrollListener {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 1) {
                HwLinearSnapHelper.this.e = null;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            HwLinearSnapHelper hwLinearSnapHelper = HwLinearSnapHelper.this;
            hwLinearSnapHelper.d = i + i2 > 0 ? 1 : -1;
            hwLinearSnapHelper.b();
        }
    }

    private boolean c() {
        int[] iArr = this.e;
        if (iArr == null || iArr.length != 2) {
            return false;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    protected int a(int i, int i2) {
        RecyclerView.Adapter adapter = this.c.getAdapter();
        SnapListener snapListener = this.j;
        if (snapListener != null) {
            if (!snapListener.isTargetNoSnap(i2)) {
                return i2;
            }
        } else if (adapter == null || (adapter.getItemViewType(i2) & 268435456) == 0) {
            return i2;
        }
        int i3 = this.d;
        if (i3 == -1) {
            return i2 > 0 ? i2 - 1 : i > 1 ? 0 : -1;
        }
        if (i3 != 1) {
            return i2;
        }
        int i4 = i - 1;
        return i2 < i4 ? i2 + 1 : i4;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        RecyclerView recyclerView2 = this.c;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.f);
        }
        this.c = recyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(this.f);
        }
        super.attachToRecyclerView(recyclerView);
    }

    protected float c(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int position;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        View view2 = null;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            if (childAt != null && (position = layoutManager.getPosition(childAt)) != -1) {
                if (position < i) {
                    view = childAt;
                    i = position;
                }
                if (position > i2) {
                    view2 = childAt;
                    i2 = position;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int max = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2));
        if (max == 0) {
            return 1.0f;
        }
        return (max * 1.0f) / ((i2 - i) + 1);
    }

    protected int c(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper, int i, int i2) {
        int[] calculateScrollDistance = calculateScrollDistance(i, i2);
        float c = c(layoutManager, orientationHelper);
        int i3 = 0;
        if (Float.compare(c, 0.0f) <= 0) {
            return 0;
        }
        int i4 = Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1]) ? calculateScrollDistance[0] : calculateScrollDistance[1];
        int round = Math.round(i4 / c);
        if (i4 < 0) {
            i3 = -1;
        } else if (i4 > 0) {
            i3 = 1;
        }
        this.d = i3;
        int i5 = this.f10719a;
        return MathUtils.clamp(round, -i5, i5);
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return new a(this.c.getContext());
        }
        return null;
    }

    protected int efC_(RecyclerView.LayoutManager layoutManager, int i, int i2, PointF pointF) {
        int i3;
        int i4 = 0;
        if (layoutManager.canScrollHorizontally()) {
            i3 = c(layoutManager, b(layoutManager), i, 0);
            if (pointF.x < 0.0f) {
                i3 = -i3;
            }
        } else {
            i3 = 0;
        }
        if (layoutManager.canScrollVertically()) {
            i4 = c(layoutManager, d(layoutManager), 0, i2);
            if (pointF.y < 0.0f) {
                i4 = -i4;
            }
        }
        return layoutManager.canScrollVertically() ? i4 : i3;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return null;
        }
        if (layoutManager.canScrollHorizontally()) {
            return a(layoutManager, b(layoutManager));
        }
        if (layoutManager.canScrollVertically() && this.c.canScrollVertically(1) && this.c.canScrollVertically(-1)) {
            return a(layoutManager, d(layoutManager));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        int itemCount;
        View findSnapView;
        int position;
        int i3;
        PointF computeScrollVectorForPosition;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (itemCount = layoutManager.getItemCount()) == 0 || (findSnapView = findSnapView(layoutManager)) == null || (position = layoutManager.getPosition(findSnapView)) == -1 || (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        int efC_ = efC_(layoutManager, i, i2, computeScrollVectorForPosition);
        if (efC_ == 0) {
            this.e = calculateDistanceToFinalSnap(layoutManager, findSnapView);
            return -1;
        }
        int i4 = position + efC_;
        if (i4 < 0) {
            i4 = 0;
        }
        if (i4 < itemCount) {
            i3 = i4;
        }
        return a(itemCount, i3);
    }

    @Override // androidx.recyclerview.widget.SnapHelper, androidx.recyclerview.widget.RecyclerView.OnFlingListener
    public boolean onFling(int i, int i2) {
        OverScroller overScroller;
        boolean onFling = super.onFling(i, i2);
        if (!onFling) {
            RecyclerView recyclerView = this.c;
            if ((recyclerView instanceof HwRecyclerView) && (overScroller = ((HwRecyclerView) recyclerView).getOverScroller()) != null) {
                overScroller.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
                return false;
            }
        }
        if (!onFling && c()) {
            RecyclerView recyclerView2 = this.c;
            int[] iArr = this.e;
            recyclerView2.smoothScrollBy(iArr[0], iArr[1]);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!this.h) {
            this.g = null;
            return;
        }
        RecyclerView recyclerView = this.c;
        if (recyclerView == null) {
            return;
        }
        View findSnapView = findSnapView(recyclerView.getLayoutManager());
        View view = this.g;
        if (view != null && findSnapView != view) {
            RecyclerView recyclerView2 = this.c;
            if (recyclerView2 instanceof HwRecyclerView) {
                ((HwRecyclerView) recyclerView2).t();
            }
        }
        this.g = findSnapView;
    }

    private OrientationHelper d(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.b;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.b = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.b;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = efB_(layoutManager, view, b(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = efB_(layoutManager, view, d(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    private int efB_(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        return (orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2)) - (orientationHelper.getEnd() / 2);
    }

    private View a(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        RecyclerView.Adapter adapter;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }
        int end = orientationHelper.getEnd();
        View view = null;
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = layoutManager.getChildAt(i3);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) >> 1)) - (end >> 1));
            if (abs < i) {
                i2 = i3;
                i = abs;
                view = childAt;
            }
        }
        int childAdapterPosition = this.c.getChildAdapterPosition(view);
        if (childAdapterPosition == -1 || (adapter = this.c.getAdapter()) == null) {
            return null;
        }
        SnapListener snapListener = this.j;
        if (snapListener != null) {
            if (!snapListener.isTargetNoSnap(childAdapterPosition)) {
                return view;
            }
        } else if ((adapter.getItemViewType(childAdapterPosition) & 268435456) == 0) {
            return view;
        }
        int i4 = this.d;
        if (i4 == -1) {
            if (i2 > 0) {
                return layoutManager.getChildAt(i2 - 1);
            }
            return null;
        }
        if (i4 != 1 || i2 >= childCount - 1) {
            return null;
        }
        return layoutManager.getChildAt(i2 + 1);
    }

    private OrientationHelper b(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.i;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.i = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.i;
    }
}
