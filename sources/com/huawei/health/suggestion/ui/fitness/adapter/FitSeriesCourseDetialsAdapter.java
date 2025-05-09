package com.huawei.health.suggestion.ui.fitness.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseDetailsViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class FitSeriesCourseDetialsAdapter extends RecyclerView.Adapter<FitSeriesCourseDetailsViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private LoadMoreCourseDetialsListener f3148a;
    private boolean e = true;
    private List<FitWorkout> c = new ArrayList(10);
    private String d = "";

    public interface LoadMoreCourseDetialsListener {
        void loadMore();
    }

    public FitSeriesCourseDetialsAdapter(HealthRecycleView healthRecycleView) {
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseDetialsAdapter.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ViewCompat.canScrollVertically(recyclerView, 1) || !FitSeriesCourseDetialsAdapter.this.e || FitSeriesCourseDetialsAdapter.this.f3148a == null) {
                    return;
                }
                FitSeriesCourseDetialsAdapter.this.f3148a.loadMore();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aBk_, reason: merged with bridge method [inline-methods] */
    public FitSeriesCourseDetailsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FitSeriesCourseDetailsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_item_series_course_details, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FitSeriesCourseDetailsViewHolder fitSeriesCourseDetailsViewHolder, int i) {
        if (i < 0 || this.c.size() <= i) {
            return;
        }
        fitSeriesCourseDetailsViewHolder.e(this.c.get(i), this.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FitWorkout> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void a(LoadMoreCourseDetialsListener loadMoreCourseDetialsListener) {
        this.f3148a = loadMoreCourseDetialsListener;
    }

    public void b(List<FitWorkout> list) {
        if (koq.b(list)) {
            LogUtil.h("FitSeriesCourseDetialsAdapter", "notifyAddAllDetailsList(),fitWorkouts is null");
            this.e = false;
            return;
        }
        if (list.size() < 10) {
            this.c.addAll(list);
            notifyDataSetChanged();
            this.e = false;
        }
        int itemCount = getItemCount();
        d(list);
        notifyItemRangeInserted(itemCount, (getItemCount() - itemCount) - 1);
    }

    private void d(List<FitWorkout> list) {
        for (FitWorkout fitWorkout : list) {
            if (!this.c.contains(fitWorkout)) {
                this.c.add(fitWorkout);
            }
        }
    }

    public void c(String str) {
        this.d = str;
    }
}
