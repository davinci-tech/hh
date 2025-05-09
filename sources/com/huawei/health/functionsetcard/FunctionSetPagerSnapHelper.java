package com.huawei.health.functionsetcard;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class FunctionSetPagerSnapHelper extends LinearSnapHelper {

    /* renamed from: a, reason: collision with root package name */
    private OrientationHelper f2354a;
    private int b = 0;
    private int c = 0;
    private int d;
    private OrientationHelper e;

    public FunctionSetPagerSnapHelper(int i) {
        this.d = i;
    }

    public void a(int i) {
        this.b = i;
    }

    public void e(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager == null) {
            LogUtil.h("FunctionSetPagerSnapHelper", "layoutManager is null");
            return iArr;
        }
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = WT_(layoutManager, view, c(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = WT_(layoutManager, view, d(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        View findSnapView;
        int position;
        int i3;
        PointF computeScrollVectorForPosition;
        int i4;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return -1;
        }
        LogUtil.a("FunctionSetPagerSnapHelper", "findTargetSnapPosition() : velocityX: ", Integer.valueOf(i));
        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0 || (findSnapView = findSnapView(layoutManager)) == null || (position = layoutManager.getPosition(findSnapView)) == -1 || (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        int c = c(i, position);
        int WS_ = WS_(layoutManager, i, computeScrollVectorForPosition, findSnapView);
        if (layoutManager.canScrollVertically()) {
            i4 = b(layoutManager, d(layoutManager), 0, i2);
            if (computeScrollVectorForPosition.y < 0.0f) {
                i4 = -i4;
            }
        } else {
            i4 = 0;
        }
        if (layoutManager.canScrollVertically()) {
            WS_ = i4;
        }
        if (WS_ == 0) {
            return -1;
        }
        int i5 = c + WS_;
        int i6 = i5 >= 0 ? i5 : 0;
        return i6 >= itemCount ? i3 : i6;
    }

    @Override // androidx.recyclerview.widget.LinearSnapHelper, androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return null;
        }
        if (layoutManager.canScrollVertically()) {
            return WU_(layoutManager, d(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return WU_(layoutManager, c(layoutManager));
        }
        LogUtil.h("FunctionSetPagerSnapHelper", "no filter");
        return null;
    }

    private int WT_(RecyclerView.LayoutManager layoutManager, View view, OrientationHelper orientationHelper) {
        int end;
        int decoratedStart = orientationHelper.getDecoratedStart(view);
        int decoratedMeasurement = orientationHelper.getDecoratedMeasurement(view) / 2;
        if (layoutManager.getClipToPadding()) {
            end = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            end = orientationHelper.getEnd() / 2;
        }
        return (decoratedStart + decoratedMeasurement) - end;
    }

    private int b(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper, int i, int i2) {
        double ceil;
        int[] calculateScrollDistance = calculateScrollDistance(i, i2);
        if (c(layoutManager, orientationHelper) <= 0.0f) {
            return 0;
        }
        if ((Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1]) ? calculateScrollDistance[0] : calculateScrollDistance[1]) > 0) {
            ceil = Math.floor(r4 / r3);
        } else {
            ceil = Math.ceil(r4 / r3);
        }
        return (int) ceil;
    }

    private View WU_(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int end;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        if (layoutManager.getClipToPadding()) {
            end = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        } else {
            end = orientationHelper.getEnd() / 2;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - end);
            if (abs < i) {
                view = childAt;
                i = abs;
            }
        }
        return view;
    }

    private float c(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
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
            int position = layoutManager.getPosition(childAt);
            if (position != -1) {
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

    private OrientationHelper d(RecyclerView.LayoutManager layoutManager) {
        if (this.e == null) {
            this.e = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.e;
    }

    private OrientationHelper c(RecyclerView.LayoutManager layoutManager) {
        if (this.f2354a == null) {
            this.f2354a = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.f2354a;
    }

    private int c(int i, int i2) {
        if (i < 0) {
            int i3 = this.c;
            i2 = (i3 + 1 == this.d && this.b == i3 + (-3)) ? i3 - 1 : i3 - 2;
            this.b = 0;
            this.c = 0;
        }
        if (i <= 0) {
            return i2;
        }
        int i4 = this.b;
        int i5 = (i4 == 0 && this.c == i4 + 3) ? i4 + 1 : i4 + 2;
        this.b = 0;
        this.c = 0;
        return i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int WS_(androidx.recyclerview.widget.RecyclerView.LayoutManager r4, int r5, android.graphics.PointF r6, android.view.View r7) {
        /*
            r3 = this;
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r0 = health.compact.a.LanguageUtil.bc(r0)
            androidx.recyclerview.widget.OrientationHelper r1 = r3.c(r4)
            int r7 = r1.getDecoratedMeasurement(r7)
            r1 = 0
            if (r7 <= 0) goto L19
            int r2 = r4.getWidth()
            int r2 = r2 / r7
            goto L1a
        L19:
            r2 = r1
        L1a:
            boolean r7 = r4.canScrollHorizontally()
            if (r7 == 0) goto L3f
            androidx.recyclerview.widget.OrientationHelper r7 = r3.c(r4)
            int r4 = r3.b(r4, r7, r5, r1)
            float r6 = r6.x
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L30
            int r4 = -r4
        L30:
            if (r5 >= 0) goto L37
            if (r0 == 0) goto L36
            r1 = r2
            goto L38
        L36:
            int r4 = -r2
        L37:
            r1 = r4
        L38:
            if (r5 <= 0) goto L3f
            if (r0 == 0) goto L3e
            int r1 = -r2
            goto L3f
        L3e:
            r1 = r2
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.functionsetcard.FunctionSetPagerSnapHelper.WS_(androidx.recyclerview.widget.RecyclerView$LayoutManager, int, android.graphics.PointF, android.view.View):int");
    }
}
