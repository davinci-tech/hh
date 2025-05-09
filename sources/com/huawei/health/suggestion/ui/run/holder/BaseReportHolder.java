package com.huawei.health.suggestion.ui.run.holder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public abstract class BaseReportHolder<T> extends RecyclerView.ViewHolder {
    public abstract void bindDataToView(T t);

    public BaseReportHolder(View view) {
        super(view);
    }
}
