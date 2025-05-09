package com.huawei.ui.commonui.recycleview;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private final int f8925a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    public RecyclerItemDecoration(int i, int i2, ArrayList<Integer> arrayList) {
        this.d = i;
        this.f = i2;
        if (koq.b(arrayList, 3)) {
            LogUtil.h("RecyclerItemDecoration", "RecyclerItemDecoration edgeSpacesList is out of bounds");
            this.c = 0;
            this.b = 0;
            this.e = 0;
            this.f8925a = 0;
            return;
        }
        this.c = arrayList.get(0).intValue();
        this.b = arrayList.get(1).intValue();
        this.e = arrayList.get(2).intValue();
        this.f8925a = arrayList.get(3).intValue();
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
            cEN_(rect, childAdapterPosition, itemCount, ((LinearLayoutManager) layoutManager).getOrientation());
        }
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            cEK_(rect, childAdapterPosition, itemCount, gridLayoutManager.getOrientation(), gridLayoutManager.getSpanCount());
        }
    }

    private void cEN_(Rect rect, int i, int i2, int i3) {
        int i4 = i2 - 1;
        if (i3 != 0) {
            if (i == 0) {
                rect.set(0, this.e, 0, this.f);
                return;
            } else if (i == i4) {
                rect.set(0, 0, 0, this.f8925a);
                return;
            } else {
                rect.set(0, 0, 0, this.f);
                return;
            }
        }
        if (LanguageUtil.bc(BaseApplication.e())) {
            if (i == 0) {
                rect.set(this.d, 0, this.c, 0);
                return;
            } else if (i == i4) {
                rect.set(this.b, 0, 0, 0);
                return;
            } else {
                rect.set(this.d, 0, 0, 0);
                return;
            }
        }
        if (i == 0) {
            rect.set(this.c, 0, this.d, 0);
        } else if (i == i4) {
            rect.set(0, 0, this.b, 0);
        } else {
            rect.set(0, 0, this.d, 0);
        }
    }

    private void cEK_(Rect rect, int i, int i2, int i3, int i4) {
        if (i3 == 0) {
            cEL_(rect, i, i2, i4);
        } else {
            cEM_(rect, i, i2, i4);
        }
    }

    private void cEL_(Rect rect, int i, int i2, int i3) {
        float f;
        float f2;
        int i4 = this.d;
        int i5 = i3 - 1;
        int i6 = this.c;
        int i7 = this.b;
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
            float f6 = this.e;
            float f7 = i8 * ((f3 - f6) - this.f8925a);
            if (i3 > 1) {
                f7 /= i5;
            }
            f = f7 + f6;
            f2 = f3 - f;
        }
        rect.set((int) f5, (int) f, (int) f4, (int) f2);
    }

    private void cEM_(Rect rect, int i, int i2, int i3) {
        float f;
        float f2;
        int i4;
        int i5 = this.d;
        int i6 = i3 - 1;
        int i7 = this.c;
        int i8 = this.b;
        float f3 = (((i5 * i6) + i7) + i8) / i3;
        int i9 = i % i3;
        int i10 = i / i3;
        int i11 = (i2 / i3) - 1;
        float f4 = this.f;
        int i12 = this.e;
        float f5 = 0.0f;
        if (i12 == 0 || (i4 = this.f8925a) == 0) {
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
        if (LanguageUtil.bc(BaseApplication.e())) {
            rect.set((int) f2, (int) f5, (int) f, (int) f4);
        } else {
            rect.set((int) f, (int) f5, (int) f2, (int) f4);
        }
    }
}
