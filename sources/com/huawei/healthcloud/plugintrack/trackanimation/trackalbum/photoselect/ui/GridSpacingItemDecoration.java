package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ui;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int b;
    private boolean c;
    private int d;

    public GridSpacingItemDecoration(int i, int i2, boolean z) {
        this.d = i;
        this.b = i2;
        this.c = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.d;
        int i2 = childAdapterPosition % i;
        if (i == 0) {
            return;
        }
        if (this.c) {
            int i3 = this.b;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * this.b) / this.d;
            if (childAdapterPosition < this.d) {
                rect.top = this.b;
            }
            rect.bottom = this.b;
            return;
        }
        rect.left = (this.b * i2) / i;
        int i4 = this.b;
        rect.right = i4 - (((i2 + 1) * i4) / this.d);
        if (childAdapterPosition < this.d) {
            rect.top = this.b;
        }
        rect.bottom = this.b;
    }
}
