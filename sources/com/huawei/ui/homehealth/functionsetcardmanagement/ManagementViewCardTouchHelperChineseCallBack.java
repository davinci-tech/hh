package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class ManagementViewCardTouchHelperChineseCallBack extends ItemTouchHelper.Callback {
    private Context b;
    private final FunctionSetCardManagementViewChineseAdapter d;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public ManagementViewCardTouchHelperChineseCallBack(FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter, Context context) {
        this.b = context;
        this.d = functionSetCardManagementViewChineseAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "onSwiped");
        FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter = this.d;
        if (functionSetCardManagementViewChineseAdapter == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "mAdapter is null");
        } else {
            functionSetCardManagementViewChineseAdapter.b(viewHolder.getAdapterPosition());
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(15, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder == null || viewHolder2 == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "source or target is null");
            return false;
        }
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "onMove source", Integer.valueOf(viewHolder.getAdapterPosition()), ", target", Integer.valueOf(viewHolder2.getAdapterPosition()));
        FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter = this.d;
        if (functionSetCardManagementViewChineseAdapter == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "mAdapter is null");
            return false;
        }
        functionSetCardManagementViewChineseAdapter.e(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "onSelectedChanged");
        if (viewHolder == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "viewHolder is null");
            return;
        }
        if (i != 0) {
            LogUtil.a("ManagementViewCardTouchHelperCallBack", "ctionState != ItemTouchHelper.ACTION_STATE_IDLE");
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "viewHolder is null");
        } else {
            super.clearView(recyclerView, viewHolder);
            LogUtil.a("ManagementViewCardTouchHelperCallBack", "clearView");
        }
    }
}
