package com.huawei.health.sport.configuredpage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class ConfiguredPageItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f2988a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int g;
    private int h;
    private int i;

    public ConfiguredPageItemDecoration(int i, int i2) {
        this.c = 0;
        this.g = 0;
        this.h = 0;
        this.f2988a = 0;
        this.d = i;
        this.b = i2;
    }

    public ConfiguredPageItemDecoration(int i, int i2, int i3, int[] iArr) {
        this(i, i3);
        this.i = i2;
        if (iArr != null && iArr.length >= 4) {
            this.c = iArr[0];
            this.g = iArr[1];
            this.h = iArr[2];
            this.f2988a = iArr[3];
        }
        int i4 = this.d;
        int i5 = this.b;
        this.e = (((i4 * (i5 - 1)) + this.c) + this.h) / i5;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageItemDecoration", "getItemOffsets parent is null.");
            return;
        }
        if (this.b <= 0) {
            LogUtil.h("ConfiguredPage_ConfiguredPageItemDecoration", "getItemOffsets mGridNumber is zero");
            return;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageItemDecoration", "getItemOffsets adapter is null");
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int orientation = gridLayoutManager.getOrientation();
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = this.b;
            if (orientation == 0) {
                awK_(rect, recyclerView, childAdapterPosition);
            } else {
                awL_(rect, childAdapterPosition % i, childAdapterPosition, adapter.getItemCount(), gridLayoutManager.getSpanCount());
            }
        }
    }

    private void awL_(Rect rect, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = this.b;
        if (i6 == 1) {
            i5 = this.c;
        } else if (i6 > 1) {
            int i7 = this.e;
            int i8 = this.c;
            i5 = ((i * ((i7 - i8) - this.h)) / (i6 - 1)) + i8;
        } else {
            i5 = 0;
        }
        int i9 = this.e - i5;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i9;
            rect.right = i5;
        } else {
            rect.left = i5;
            rect.right = i9;
        }
        if (i2 < i4) {
            rect.top = this.g;
        } else {
            rect.top = 0;
        }
        if (((int) ((((i3 * 1.0f) / i4) + 0.5f) - 1.0f)) == i2 / i4) {
            rect.bottom = this.f2988a;
        } else {
            rect.bottom = this.i;
        }
    }

    private void awK_(Rect rect, RecyclerView recyclerView, int i) {
        int i2;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (i == 0) {
            i2 = this.c;
        } else {
            i2 = this.d;
        }
        int i3 = i == adapter.getItemCount() + (-1) ? this.h : 0;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.left = i3;
            rect.right = i2;
        } else {
            rect.left = i2;
            rect.right = i3;
        }
        rect.top = this.g;
        rect.bottom = this.f2988a;
    }
}
