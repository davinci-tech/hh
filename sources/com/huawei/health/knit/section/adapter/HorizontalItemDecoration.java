package com.huawei.health.knit.section.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes8.dex */
public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {
    private int c;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemCount = recyclerView.getAdapter().getItemCount();
        if (childAdapterPosition == 0) {
            rect.left = 0;
            rect.right = this.c / 2;
        } else if (childAdapterPosition == itemCount - 1) {
            rect.left = this.c / 2;
            rect.right = 0;
        } else {
            rect.left = this.c / 2;
            rect.right = this.c / 2;
        }
    }
}
