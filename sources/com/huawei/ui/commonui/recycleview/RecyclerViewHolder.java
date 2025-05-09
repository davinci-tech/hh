package com.huawei.ui.commonui.recycleview;

import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes6.dex */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> b;

    public RecyclerViewHolder(View view) {
        super(view);
        this.b = new SparseArray<>();
    }

    public <T extends View> T cEO_(int i) {
        T t = (T) this.b.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) this.itemView.findViewById(i);
        this.b.put(i, t2);
        return t2;
    }
}
