package com.huawei.health.suggestion.ui.fitness.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder;
import defpackage.arx;
import defpackage.nsn;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessSeriesCourseViewAdapter extends RecyclerView.Adapter {
    private List<Topic> c;

    public FitnessSeriesCourseViewAdapter(List<Topic> list) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.addAll(list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FitSeriesCourseViewHolder(LayoutInflater.from(arx.b()).inflate(R.layout.sug_item_series_course, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if ((viewHolder instanceof FitSeriesCourseViewHolder) && i >= 0 && this.c.size() > i) {
            ((FitSeriesCourseViewHolder) viewHolder).e(this.c.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.c.size() <= 0) {
            return 0;
        }
        return nsn.ag(arx.b()) ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public void c(List<Topic> list) {
        List<Topic> list2 = this.c;
        if (list2 == null) {
            LogUtil.c("Suggestion_FitnessSeriesCourseViewAdapter", "notifyClearAndAddALl is null");
            return;
        }
        list2.clear();
        this.c.addAll(list);
        notifyDataSetChanged();
    }
}
