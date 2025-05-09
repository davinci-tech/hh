package com.huawei.health.suggestion.ui.run.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessInnerViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FitnessCourseHorizontalAdapter extends RecyclerView.Adapter<FitnessInnerViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private ArrayMap<String, String> f3350a;
    private List<FitWorkout> e = new ArrayList();

    public void d(List<FitWorkout> list) {
        if (list == null) {
            return;
        }
        this.e.clear();
        this.e.addAll(list);
        notifyDataSetChanged();
        LogUtil.a("Suggestion_FitnessCourseHorizontalAdapter", "clearAndAddAll()");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aKU_, reason: merged with bridge method [inline-methods] */
    public FitnessInnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_item_fitness_topic_inner, viewGroup, false);
        if (inflate.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) inflate.getLayoutParams();
            if (i == 1) {
                layoutParams.setMarginStart(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e));
                layoutParams.setMarginEnd(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d));
            } else if (i == 2) {
                layoutParams.setMarginStart(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e));
                layoutParams.setMarginEnd(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8));
            } else if (i == 3) {
                layoutParams.setMarginEnd(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d));
            } else {
                layoutParams.setMarginEnd(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8));
            }
            inflate.setLayoutParams(layoutParams);
        }
        return new FitnessInnerViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FitnessInnerViewHolder fitnessInnerViewHolder, int i) {
        if (i == 0) {
            fitnessInnerViewHolder.b(this.e.get(i), true, false);
        } else if (i == this.e.size() - 1) {
            fitnessInnerViewHolder.b(this.e.get(i), false, true);
        } else {
            fitnessInnerViewHolder.b(this.e.get(i), false, false);
        }
        if (this.f3350a == null || this.e.get(i) == null) {
            return;
        }
        fitnessInnerViewHolder.e(this.f3350a.get(this.e.get(i).acquireId()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (getItemCount() == 1) {
            return 1;
        }
        if (i == 0) {
            return 2;
        }
        if (i + 1 == getItemCount()) {
            return 3;
        }
        return super.getItemViewType(i);
    }

    public void d(ArrayMap<String, String> arrayMap) {
        this.f3350a = arrayMap;
    }
}
