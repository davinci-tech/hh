package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ggs;
import defpackage.nsn;

/* loaded from: classes4.dex */
public abstract class AbsFitnessViewHolder<T> extends RecyclerView.ViewHolder {
    public abstract void init(T t);

    public AbsFitnessViewHolder(View view) {
        super(view);
    }

    public boolean isTahiti(Context context) {
        return nsn.ag(context);
    }

    public static void setRecyclerViewLayout(Context context, HealthRecycleView healthRecycleView) {
        ggs.b(context, healthRecycleView, new HealthLinearLayoutManager(context), false, 0);
    }

    public boolean isFastClick() {
        return nsn.a(500);
    }
}
