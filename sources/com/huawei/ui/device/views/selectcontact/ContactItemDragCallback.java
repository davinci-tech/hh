package com.huawei.ui.device.views.selectcontact;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.views.selectcontact.ContactItemDragAdapter;

/* loaded from: classes6.dex */
public class ContactItemDragCallback extends ItemTouchHelper.Callback {
    private ContactItemDragListener e;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public ContactItemDragCallback(ContactItemDragListener contactItemDragListener) {
        this.e = contactItemDragListener;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        this.e.onItemDrag(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            LogUtil.h("ContactItemDragCallback", "onSelectedChanged viewHolder is null");
            return;
        }
        if (i != 0 && (viewHolder instanceof ContactItemDragAdapter.SlideViewHolder)) {
            ((ContactItemDragAdapter.SlideViewHolder) viewHolder).itemView.setBackgroundColor(BaseApplication.e().getColor(R.color._2131299296_res_0x7f090be0));
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            LogUtil.h("ContactItemDragCallback", "clearView viewHolder is null");
            return;
        }
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof ContactItemDragAdapter.SlideViewHolder) {
            ((ContactItemDragAdapter.SlideViewHolder) viewHolder).itemView.setBackgroundColor(BaseApplication.e().getColor(R.color._2131299296_res_0x7f090be0));
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.c("ContactItemDragCallback", "onSwiped");
    }
}
