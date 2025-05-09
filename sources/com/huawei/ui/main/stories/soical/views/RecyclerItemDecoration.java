package com.huawei.ui.main.stories.soical.views;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f10504a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int j;

    public RecyclerItemDecoration(int i, int i2, int[] iArr) {
        this.d = 0;
        this.e = 0;
        this.b = 0;
        this.f10504a = 0;
        this.c = i;
        this.j = i2;
        if (iArr == null || iArr.length < 4) {
            return;
        }
        this.d = iArr[0];
        this.b = iArr[1];
        this.e = iArr[2];
        this.f10504a = iArr[3];
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            LogUtil.h("RecyclerItemDecoration", "getItemOffsets adapter is null");
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = adapter.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            dTP_(rect, childAdapterPosition, itemCount, ((LinearLayoutManager) layoutManager).getOrientation());
        }
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            dTM_(rect, childAdapterPosition, itemCount, gridLayoutManager.getOrientation(), gridLayoutManager.getSpanCount());
        }
    }

    private void dTP_(Rect rect, int i, int i2, int i3) {
        int i4 = i2 - 1;
        if (i3 == 0) {
            if (i == 0) {
                rect.set(this.d, this.b, this.c, this.f10504a);
                return;
            } else if (i == i4) {
                rect.set(0, this.b, this.e, this.f10504a);
                return;
            } else {
                rect.set(0, this.b, this.c, this.f10504a);
                return;
            }
        }
        if (i == 0) {
            rect.set(this.d, this.b, this.e, this.j);
        } else if (i == i4) {
            rect.set(this.d, 0, this.e, this.f10504a);
        } else {
            rect.set(this.d, 0, this.e, this.j);
        }
    }

    private void dTM_(Rect rect, int i, int i2, int i3, int i4) {
        if (i3 == 0) {
            dTN_(rect, i, i2, i4);
        } else {
            dTO_(rect, i, i2, i4);
        }
    }

    private void dTN_(Rect rect, int i, int i2, int i3) {
        float f;
        float f2;
        int i4 = this.c;
        int i5 = i3 - 1;
        int i6 = this.d;
        int i7 = this.e;
        float f3 = (((i4 * i5) + i6) + i7) / i3;
        int i8 = i % i3;
        int i9 = i / i3;
        int i10 = (i2 / i3) - 1;
        float f4 = i4;
        float f5 = 0.0f;
        if (i6 == 0 || i7 == 0) {
            f = i8 * f3;
            if (i3 > 1) {
                f /= i5;
            }
            f2 = f3 - f;
            if (i10 == i9) {
                f4 = 0.0f;
            }
        } else {
            if (i < i3) {
                f5 = i6;
            } else if (i10 == i9) {
                f4 = i7;
            }
            float f6 = this.b;
            float f7 = i8 * ((f3 - f6) - this.f10504a);
            if (i3 > 1) {
                f7 /= i5;
            }
            f = f7 + f6;
            f2 = f3 - f;
        }
        rect.set((int) f5, (int) f, (int) f4, (int) f2);
    }

    private void dTO_(Rect rect, int i, int i2, int i3) {
        float f;
        float f2;
        int i4;
        int i5 = this.c;
        int i6 = i3 - 1;
        int i7 = this.d;
        int i8 = this.e;
        float f3 = (((i5 * i6) + i7) + i8) / i3;
        int i9 = i % i3;
        int i10 = i / i3;
        int i11 = (i2 / i3) - 1;
        float f4 = this.j;
        int i12 = this.b;
        float f5 = 0.0f;
        if (i12 == 0 || (i4 = this.f10504a) == 0) {
            f = i9 * f3;
            if (i3 > 1) {
                f /= i6;
            }
            f2 = f3 - f;
            if (i11 == i10) {
                f4 = 0.0f;
            }
        } else {
            if (i < i3) {
                f5 = i12;
            } else if (i11 == i10) {
                f4 = i4;
            }
            float f6 = i7;
            float f7 = i9 * ((f3 - f6) - i8);
            if (i3 > 1) {
                f7 /= i6;
            }
            f = f7 + f6;
            f2 = f3 - f;
        }
        rect.set((int) f, (int) f5, (int) f2, (int) f4);
    }
}
