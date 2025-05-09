package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class ManagementViewCardTouchHelperCallBack extends ItemTouchHelper.Callback {

    /* renamed from: a, reason: collision with root package name */
    private Context f9453a;
    private final FunctionSetCardManagementViewAdapter d;

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return false;
    }

    public ManagementViewCardTouchHelperCallBack(FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter, Context context) {
        this.f9453a = context;
        this.d = functionSetCardManagementViewAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "onSwiped");
        FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter = this.d;
        if (functionSetCardManagementViewAdapter == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "mAdapter is null");
        } else {
            functionSetCardManagementViewAdapter.b(viewHolder.getAdapterPosition());
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder == null || viewHolder2 == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "source or target is null");
            return false;
        }
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "onMove source", Integer.valueOf(viewHolder.getAdapterPosition()), ", target", Integer.valueOf(viewHolder2.getAdapterPosition()));
        FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter = this.d;
        if (functionSetCardManagementViewAdapter == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "mAdapter is null");
            return false;
        }
        functionSetCardManagementViewAdapter.b(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
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
            FunctionSetCardManagementViewHolder functionSetCardManagementViewHolder = (FunctionSetCardManagementViewHolder) viewHolder;
            ViewGroup.LayoutParams layoutParams = functionSetCardManagementViewHolder.itemView.getLayoutParams();
            layoutParams.height = (int) TypedValue.applyDimension(1, 72.0f, this.f9453a.getResources().getDisplayMetrics());
            functionSetCardManagementViewHolder.itemView.setLayoutParams(layoutParams);
            functionSetCardManagementViewHolder.itemView.setBackgroundResource(R.drawable._2131427759_res_0x7f0b01af);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            LogUtil.h("ManagementViewCardTouchHelperCallBack", "viewHolder is null");
            return;
        }
        super.clearView(recyclerView, viewHolder);
        LogUtil.a("ManagementViewCardTouchHelperCallBack", "clearView");
        FunctionSetCardManagementViewHolder functionSetCardManagementViewHolder = (FunctionSetCardManagementViewHolder) viewHolder;
        functionSetCardManagementViewHolder.itemView.setBackgroundResource(0);
        ViewGroup.LayoutParams layoutParams = functionSetCardManagementViewHolder.itemView.getLayoutParams();
        layoutParams.height = (int) TypedValue.applyDimension(1, 60.0f, this.f9453a.getResources().getDisplayMetrics());
        functionSetCardManagementViewHolder.itemView.setLayoutParams(layoutParams);
    }
}
