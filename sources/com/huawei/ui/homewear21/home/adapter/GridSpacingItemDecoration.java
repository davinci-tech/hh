package com.huawei.ui.homewear21.home.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int c;
    private boolean e;

    public GridSpacingItemDecoration(boolean z, int i) {
        this.c = i;
        this.e = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = nsn.ag(BaseApplication.getContext()) ? 3 : 2;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i2 = childAdapterPosition % i;
        int itemCount = recyclerView.getAdapter().getItemCount();
        boolean z = this.e;
        if (z) {
            rect.top = this.c;
            return;
        }
        if (!z && childAdapterPosition >= i) {
            rect.top = this.c;
        }
        if (i == 2 && itemCount >= 0 && itemCount % 2 == 1 && childAdapterPosition == itemCount - 1) {
            return;
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            rect.right = (this.c * i2) / i;
            int i3 = this.c;
            rect.left = i3 - (((i2 + 1) * i3) / i);
        } else {
            rect.left = (this.c * i2) / i;
            int i4 = this.c;
            rect.right = i4 - (((i2 + 1) * i4) / i);
        }
    }
}
