package com.huawei.health.suggestion.ui.fitness.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessMyCourseWithDataViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.arx;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessMyCourseWithDataAdapter extends RecyclerView.Adapter {
    private List<FitWorkout> d = new ArrayList(10);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FitnessMyCourseWithDataViewHolder(LayoutInflater.from(arx.b()).inflate(R.layout.sug_item_fitness_each_course, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if ((viewHolder instanceof FitnessMyCourseWithDataViewHolder) && koq.c(this.d) && !koq.b(this.d, i)) {
            FitnessMyCourseWithDataViewHolder fitnessMyCourseWithDataViewHolder = (FitnessMyCourseWithDataViewHolder) viewHolder;
            fitnessMyCourseWithDataViewHolder.init(this.d.get(i));
            if (!nsn.ag(arx.b()) && this.d.size() >= 2 && i == 0) {
                fitnessMyCourseWithDataViewHolder.d().setVisibility(0);
            } else {
                fitnessMyCourseWithDataViewHolder.d().setVisibility(8);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.d.size() < 2) {
            return this.d.size();
        }
        return 2;
    }

    public void c(List<FitWorkout> list) {
        if (list == null) {
            LogUtil.h("Suggestion_FitnessMyCourseWithDataAdapter", "notifyClearAndAddAll is null");
            return;
        }
        this.d.clear();
        this.d.addAll(list);
        notifyDataSetChanged();
        LogUtil.a("Suggestion_FitnessMyCourseWithDataAdapter", "notifyClearAndAddAll List<FitWorkout>", Integer.valueOf(list.size()));
    }
}
