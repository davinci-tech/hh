package com.huawei.uikit.hwrecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes9.dex */
public class HwLinearLayoutManager extends HwLinearLayoutManagerEx {
    private LayoutCallback f;

    public static abstract class LayoutCallback {
        public abstract void onLayoutFinished(View view, RecyclerView recyclerView);
    }

    public HwLinearLayoutManager(Context context, LayoutCallback layoutCallback) {
        super(context, 1, false);
        this.f = layoutCallback;
    }

    private void j() {
        if (this.f == null) {
            return;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt.getParent() instanceof RecyclerView)) {
                this.f.onLayoutFinished(childAt, (RecyclerView) childAt.getParent());
            }
        }
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManagerEx
    protected void c(RecyclerView.State state, int[] iArr) {
        super.c(state, iArr);
        iArr[0] = iArr[0] + a()[0];
        iArr[1] = iArr[1] + a()[1];
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManagerEx, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getChildCount() == 0) {
            return;
        }
        j();
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManagerEx, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollVerticallyBy = super.scrollVerticallyBy(i, recycler, state);
        j();
        return scrollVerticallyBy;
    }

    public HwLinearLayoutManager(Context context) {
        this(context, HwDeviceAdapter.b(context).c(context));
    }

    public HwLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
