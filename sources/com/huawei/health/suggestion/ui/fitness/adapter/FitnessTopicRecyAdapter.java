package com.huawei.health.suggestion.ui.fitness.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessTopicViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.LoadMoreViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.WarmUpAndStretchViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessTopicRecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3150a;
    private List<FitWorkout> b;
    private Bundle c;
    private FitnessTopicDeleteModel d;
    private boolean e;
    private HealthRecycleView f;
    private int g;
    private boolean h;
    private LoadMoreListener i;
    private String j;

    public interface LoadMoreListener {
        void loadMore();
    }

    public FitnessTopicRecyAdapter(HealthRecycleView healthRecycleView) {
        this.b = new ArrayList(10);
        this.e = true;
        this.f = healthRecycleView;
        this.f3150a = healthRecycleView.getContext();
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitnessTopicRecyAdapter.3
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ViewCompat.canScrollVertically(recyclerView, 1) || !FitnessTopicRecyAdapter.this.e || FitnessTopicRecyAdapter.this.i == null) {
                    return;
                }
                LogUtil.a("Suggestion_FitnessTopicRecyAdapter", "method:loadMore()");
                FitnessTopicRecyAdapter.this.i.loadMore();
            }
        });
    }

    public FitnessTopicRecyAdapter(HealthRecycleView healthRecycleView, int i, Bundle bundle) {
        this(healthRecycleView);
        this.g = i;
        this.c = bundle;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("Suggestion_FitnessTopicRecyAdapter", "onCreateViewHolder(ViewGroup parent, int viewType)");
        if (i == 1) {
            return new LoadMoreViewHolder(LayoutInflater.from(this.f.getContext()).inflate(R.layout.sug_his_loading_more, viewGroup, false));
        }
        if (this.g == 8) {
            return new WarmUpAndStretchViewHolder(LayoutInflater.from(this.f.getContext()).inflate(R.layout.sug_warm_up_and_stretch_item, viewGroup, false), this.c);
        }
        return new FitnessTopicViewHolder(LayoutInflater.from(this.f.getContext()).inflate(R.layout.sug_fitness_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("Suggestion_FitnessTopicRecyAdapter", "onBindViewHolder(HealthRecycleView.ViewHolder holder, int position)");
        if (getItemViewType(i) == 0) {
            if (this.g == 8) {
                if (koq.b(this.b, i)) {
                    return;
                }
                FitWorkout fitWorkout = this.b.size() != 0 ? this.b.get(i) : null;
                if (viewHolder instanceof WarmUpAndStretchViewHolder) {
                    ((WarmUpAndStretchViewHolder) viewHolder).e(fitWorkout, this.j, this.f3150a);
                    return;
                }
                return;
            }
            FitnessTopicDeleteModel fitnessTopicDeleteModel = this.d;
            if (fitnessTopicDeleteModel != null) {
                fitnessTopicDeleteModel.savePosition(i);
            }
            if (viewHolder instanceof FitnessTopicViewHolder) {
                if (i == 0) {
                    ((FitnessTopicViewHolder) viewHolder).b(0);
                } else if (nsn.ag(this.f3150a) && i == 1) {
                    ((FitnessTopicViewHolder) viewHolder).b(0);
                } else {
                    ((FitnessTopicViewHolder) viewHolder).b(8);
                }
                ((FitnessTopicViewHolder) viewHolder).e(this.d, this.j, this.h, this.b.size() != 0 ? this.b.get(i) : null);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.e && this.b.size() >= 10) {
            return this.b.size() + 1;
        }
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.e && this.b.size() >= 10 && i == getItemCount() - 1) ? 1 : 0;
    }

    public void b(boolean z) {
        this.e = z;
        notifyDataSetChanged();
    }

    public void d(LoadMoreListener loadMoreListener) {
        this.i = loadMoreListener;
    }

    public void d(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z, List<FitWorkout> list) {
        this.b.clear();
        a(fitnessTopicDeleteModel, z, list);
    }

    public void a(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z, List<FitWorkout> list) {
        if (list == null) {
            LogUtil.h("Suggestion_FitnessTopicRecyAdapter", "fitWorkouts null or size =0");
            return;
        }
        if (list.size() < 10) {
            this.e = false;
        }
        if (fitnessTopicDeleteModel != null) {
            this.d = fitnessTopicDeleteModel;
        }
        int size = this.b.size();
        this.h = z;
        this.b.addAll(list);
        int max = Math.max(1, getItemCount() - size);
        if (size == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeChanged(size, max);
        }
    }

    public void d(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z) {
        if (fitnessTopicDeleteModel != null) {
            this.d = fitnessTopicDeleteModel;
        }
        this.h = z;
        notifyItemRangeChanged(0, getItemCount(), true);
    }

    public List<FitWorkout> a() {
        return this.b;
    }

    public void c(String str) {
        this.j = str;
    }
}
