package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public abstract class CustomCourseDragViewHolder<T> extends RecyclerView.ViewHolder {
    protected boolean mIsCanDrag;

    public abstract void init(T t, int i);

    public abstract void setDragState(boolean z);

    public CustomCourseDragViewHolder(View view) {
        super(view);
        this.mIsCanDrag = true;
    }

    public boolean isIsCanDrag() {
        return this.mIsCanDrag;
    }

    public void setIsCanDrag(boolean z) {
        this.mIsCanDrag = z;
    }
}
