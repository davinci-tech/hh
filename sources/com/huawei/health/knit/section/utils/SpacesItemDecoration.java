package com.huawei.health.knit.section.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes3.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f2613a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    SpacesItemDecoration(int i, int i2) {
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.c = 0;
        this.b = i;
        this.d = i2;
    }

    public SpacesItemDecoration(int i, int i2, int i3, int[] iArr) {
        this(i, i3);
        this.h = i2;
        if (iArr != null && iArr.length >= 4) {
            this.e = iArr[0];
            this.f = iArr[1];
            this.g = iArr[2];
            this.c = iArr[3];
        }
        int i4 = this.b;
        int i5 = this.d;
        this.f2613a = (((i4 * (i5 - 1)) + this.e) + this.g) / i5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView == null) {
            LogUtil.h("SpacesItemDecoration", "getItemOffsets parent is null.");
            return;
        }
        if (this.d <= 0) {
            LogUtil.h("SpacesItemDecoration", "getItemOffsets mGridNumber is zero");
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            LogUtil.h("SpacesItemDecoration", "getItemOffsets adapter is null");
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int orientation = gridLayoutManager.getOrientation();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = this.d;
            if (orientation == 0) {
                ahg_(rect, recyclerView, childAdapterPosition);
            } else {
                ahh_(rect, childAdapterPosition % i, childAdapterPosition, adapter.getItemCount(), gridLayoutManager.getSpanCount());
            }
        }
    }

    private void ahg_(Rect rect, RecyclerView recyclerView, int i) {
        int dimensionPixelSize;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (i == 0) {
            dimensionPixelSize = this.e;
        } else {
            dimensionPixelSize = this.g - BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        }
        int i2 = i == adapter.getItemCount() + (-1) ? this.g : 0;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i2;
            rect.right = dimensionPixelSize;
        } else {
            rect.left = dimensionPixelSize;
            rect.right = i2;
        }
        rect.top = this.f;
        rect.bottom = this.c;
    }

    private void ahh_(Rect rect, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = this.d;
        if (i6 == 1) {
            i5 = this.e;
        } else if (i6 > 1) {
            int i7 = this.f2613a;
            int i8 = this.e;
            i5 = ((i * ((i7 - i8) - this.g)) / (i6 - 1)) + i8;
        } else {
            i5 = 0;
        }
        int i9 = this.f2613a - i5;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i9;
            rect.right = i5;
        } else {
            rect.left = i5;
            rect.right = i9;
        }
        if (i2 < i4) {
            rect.top = this.f;
        } else {
            rect.top = 0;
        }
        if (((int) Math.ceil((i3 * 1.0f) / i4)) - 1 == i2 / i4) {
            rect.bottom = this.c;
        } else {
            rect.bottom = this.h;
        }
    }
}
