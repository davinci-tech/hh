package com.huawei.ui.main.stories.health.basehealth;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes6.dex */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f10146a;
    private int d;

    public SpaceItemDecoration(int i, int i2) {
        this.f10146a = i;
        this.d = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDraw(canvas, recyclerView, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager.getOrientation() == 1) {
                if (recyclerView.getChildAdapterPosition(view) == linearLayoutManager.getItemCount() - 1) {
                    rect.bottom = this.d;
                }
                rect.top = this.d;
                rect.left = this.f10146a;
                rect.right = this.f10146a;
                return;
            }
            if (recyclerView.getChildAdapterPosition(view) == linearLayoutManager.getItemCount() - 1) {
                rect.right = this.f10146a;
            }
            rect.top = this.d;
            rect.left = this.f10146a;
            rect.bottom = this.d;
        }
    }
}
