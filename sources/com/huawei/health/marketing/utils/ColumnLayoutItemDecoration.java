package com.huawei.health.marketing.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import health.compact.a.LanguageUtil;

/* loaded from: classes3.dex */
public class ColumnLayoutItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f2815a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int j;

    public ColumnLayoutItemDecoration(int i, int i2, int i3, int[] iArr) {
        this.f = 0;
        this.h = 0;
        this.j = 0;
        this.e = 0;
        if (i3 == 0) {
            LogUtil.h("ColumnLayoutItemDecoration", "gridNumber is zero.");
            return;
        }
        this.c = i;
        this.f2815a = i3;
        this.g = i2;
        if (iArr != null && iArr.length >= 4) {
            this.f = iArr[0];
            this.h = iArr[1];
            this.j = iArr[2];
            this.e = iArr[3];
        }
        this.b = (((i * (i3 - 1)) + this.f) + this.j) / i3;
    }

    public ColumnLayoutItemDecoration(int i, int i2, int i3, int[] iArr, int i4) {
        this(i, i2, i3, iArr);
        this.d = i4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView == null) {
            LogUtil.h("ColumnLayoutItemDecoration", "getItemOffsets parent is null.");
            return;
        }
        if (this.f2815a <= 0) {
            LogUtil.h("ColumnLayoutItemDecoration", "getItemOffsets mGridNumber is zero");
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            LogUtil.h("ColumnLayoutItemDecoration", "getItemOffsets adapter is null");
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            int spanCount = layoutManager instanceof GridLayoutManager ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = this.f2815a;
            if (orientation == 0) {
                alA_(rect, recyclerView, childAdapterPosition);
                return;
            }
            alB_(rect, childAdapterPosition % i, childAdapterPosition, adapter.getItemCount(), spanCount);
            if (alz_(view)) {
                rect.top = 0;
            }
        }
    }

    private boolean c(int i, int i2, int i3) {
        return (i3 - 1) / i2 == i / i2;
    }

    private void alB_(Rect rect, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = this.f2815a;
        if (i6 == 1) {
            i5 = this.f;
        } else if (i6 > 1) {
            int i7 = this.b;
            int i8 = this.f;
            i5 = ((i * ((i7 - i8) - this.j)) / (i6 - 1)) + i8;
        } else {
            i5 = 0;
        }
        int i9 = this.b - i5;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i9;
            rect.right = i5;
        } else {
            rect.left = i5;
            rect.right = i9;
        }
        if (i2 < i4) {
            rect.top = this.h;
        } else {
            rect.top = this.g;
        }
        if (c(i2, i4, i3)) {
            rect.bottom = this.e;
        }
    }

    private void alA_(Rect rect, RecyclerView recyclerView, int i) {
        int i2;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (i == 0 || (i == 1 && this.d == 59)) {
            i2 = this.f;
        } else {
            i2 = this.c;
        }
        int i3 = (i == adapter.getItemCount() - 1 || (i == adapter.getItemCount() + (-2) && this.d == 59)) ? this.j : 0;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i3;
            rect.right = i2;
        } else {
            rect.left = i2;
            rect.right = i3;
        }
        if (this.d == 116) {
            rect.left = 0;
            rect.right = 0;
        }
        rect.top = this.h;
        if (this.d == 59 && i % 2 != 0) {
            rect.top = this.g;
        }
        rect.bottom = this.e;
    }

    private boolean alz_(View view) {
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        if (view instanceof HealthSubHeader) {
            return true;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof HealthSubHeader) {
                return ((HealthSubHeader) childAt).getVisibility() != 8;
            }
            z = z || alz_(childAt);
            if (z) {
                break;
            }
        }
        return z;
    }
}
