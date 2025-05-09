package com.huawei.health.suggestion.ui.fitness.helper;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    private static final String TAG = "Suggestion_BaseRecyclerViewAdapter";
    private OnItemClickListener mClickListener;
    private List<T> mData;
    private SparseArray<Integer> mItemLayoutIds = new SparseArray<>(10);

    public interface OnItemClickListener<T> {
        void onItemClicked(RecyclerHolder recyclerHolder, int i, T t);
    }

    public abstract void convert(RecyclerHolder recyclerHolder, int i, T t);

    protected void setItemLayout() {
    }

    public BaseRecyclerViewAdapter(List<T> list, int... iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i = 0; i < iArr.length; i++) {
                this.mItemLayoutIds.append(i, Integer.valueOf(iArr[i]));
            }
        }
        this.mData = list;
    }

    public BaseRecyclerViewAdapter(List<T> list) {
        this.mData = list;
        setItemLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    private int getItemTypeLayout(int i) {
        return this.mItemLayoutIds.get(i).intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            LogUtil.h(TAG, "parent is null");
            return null;
        }
        return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemTypeLayout(i), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerHolder recyclerHolder, final int i) {
        if (recyclerHolder == null) {
            LogUtil.h(TAG, "holder is null ");
            return;
        }
        if (!koq.b(this.mData, i)) {
            convert(recyclerHolder, i, this.mData.get(i));
        }
        recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BaseRecyclerViewAdapter.this.mClickListener != null && !koq.b(BaseRecyclerViewAdapter.this.mData, i)) {
                    BaseRecyclerViewAdapter.this.mClickListener.onItemClicked(recyclerHolder, i, BaseRecyclerViewAdapter.this.mData.get(i));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public T get(int i) {
        if (koq.d(this.mData, i)) {
            return this.mData.get(i);
        }
        return null;
    }

    public void refreshDataChange(List<T> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }
}
