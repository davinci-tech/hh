package com.huawei.ui.main.stories.history.model;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int c;

    public SpacesItemDecoration(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.c;
        rect.right = this.c;
        rect.bottom = this.c;
        if (recyclerView.getChildPosition(view) == 0) {
            rect.top = 0;
        }
    }
}
