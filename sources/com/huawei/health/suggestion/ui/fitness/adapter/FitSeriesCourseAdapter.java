package com.huawei.health.suggestion.ui.fitness.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class FitSeriesCourseAdapter extends RecyclerView.Adapter<FitSeriesCourseViewHolder> {
    private LoadMoreCourseListener b;

    /* renamed from: a, reason: collision with root package name */
    private List<Topic> f3147a = new ArrayList(10);
    private boolean e = true;

    public interface LoadMoreCourseListener {
        void loadMore();
    }

    public FitSeriesCourseAdapter(HealthRecycleView healthRecycleView) {
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseAdapter.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ViewCompat.canScrollVertically(recyclerView, 1) || !FitSeriesCourseAdapter.this.e || FitSeriesCourseAdapter.this.b == null) {
                    return;
                }
                FitSeriesCourseAdapter.this.b.loadMore();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aBj_, reason: merged with bridge method [inline-methods] */
    public FitSeriesCourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FitSeriesCourseViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_item_series_course, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FitSeriesCourseViewHolder fitSeriesCourseViewHolder, int i) {
        if (i < 0 || this.f3147a.size() <= i) {
            return;
        }
        fitSeriesCourseViewHolder.e(this.f3147a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Topic> list = this.f3147a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void e(LoadMoreCourseListener loadMoreCourseListener) {
        this.b = loadMoreCourseListener;
    }

    private void c(List<Topic> list) {
        for (Topic topic : list) {
            if (!this.f3147a.contains(topic)) {
                this.f3147a.add(topic);
            }
        }
    }

    public void d(List<Topic> list) {
        if (koq.b(list)) {
            this.e = false;
            LogUtil.h("Sug_FitSeriesCourseAdapter", "notifyAddAllList, topics is null");
            return;
        }
        if (list.size() < 10) {
            this.f3147a.addAll(list);
            notifyDataSetChanged();
            this.e = false;
        }
        int itemCount = getItemCount();
        c(list);
        notifyItemRangeInserted(itemCount, (getItemCount() - itemCount) - 1);
    }
}
