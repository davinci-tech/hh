package com.huawei.health.ecologydevice.callback;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    /* renamed from: a, reason: collision with root package name */
    private ItemTouchHelperAdapter f2278a;
    private boolean e = false;

    public interface ItemTouchHelperAdapter {
        void onItemMove(int i, int i2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.f2278a = itemTouchHelperAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (this.e) {
            return makeMovementFlags(3, 4);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        this.f2278a.onItemMove(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        this.e = false;
    }

    public void b(boolean z) {
        this.e = z;
    }
}
