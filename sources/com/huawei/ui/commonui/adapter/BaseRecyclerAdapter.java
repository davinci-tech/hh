package com.huawei.ui.commonui.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class BaseRecyclerAdapter<E> extends RecyclerView.Adapter<RecyclerHolder> {
    private static final String TAG = "Health_BaseRecyclerAdapter";
    private OnItemClickListener mClickListener;
    private List<E> mDataList;
    private SparseArray<Integer> mItemLayoutIds = new SparseArray<>(10);

    public interface OnItemClickListener<T> {
        void onItemClicked(RecyclerHolder recyclerHolder, int i, T t);
    }

    public abstract void convert(RecyclerHolder recyclerHolder, int i, E e);

    public BaseRecyclerAdapter(List<E> list, int... iArr) {
        this.mDataList = new ArrayList();
        if (iArr != null) {
            for (int i = 0; i < iArr.length; i++) {
                this.mItemLayoutIds.append(i, Integer.valueOf(iArr[i]));
            }
        }
        if (list != null) {
            this.mDataList = list;
        }
    }

    public BaseRecyclerAdapter(List<E> list) {
        this.mDataList = new ArrayList();
        if (list != null) {
            this.mDataList = list;
        }
        setItemLayouts(this.mItemLayoutIds);
    }

    private void setItemLayouts(SparseArray<Integer> sparseArray) {
        this.mItemLayoutIds = sparseArray;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    private int getItemTypeLayout(int i) {
        return this.mItemLayoutIds.get(i, 0).intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemTypeLayout(i), viewGroup, false));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerHolder recyclerHolder, final int i) {
        convert(recyclerHolder, i, get(i));
        if (recyclerHolder.itemView != null) {
            recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BaseRecyclerAdapter.this.mClickListener != null) {
                        OnItemClickListener onItemClickListener = BaseRecyclerAdapter.this.mClickListener;
                        RecyclerHolder recyclerHolder2 = recyclerHolder;
                        int i2 = i;
                        onItemClickListener.onItemClicked(recyclerHolder2, i2, BaseRecyclerAdapter.this.get(i2));
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataList.size();
    }

    public E get(int i) {
        if (koq.b(this.mDataList, i)) {
            LogUtil.h(TAG, "position out of the data range");
            return null;
        }
        return this.mDataList.get(i);
    }

    public void refreshDataChange(List<E> list) {
        if (list != null) {
            this.mDataList = list;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }
}
