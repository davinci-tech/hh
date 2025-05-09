package com.huawei.health.suggestion.ui.fitness.adapter;

import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer;
import com.huawei.health.suggestion.ui.fitness.viewholder.CoachesIntroducesViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessNewDetailImageViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessNewDetailVideoViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessRecommendCourseViewHolder;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSubTitleViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LayoutTemplateInfo;
import com.huawei.pluginfitnessadvice.ShowLayout;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class NewDetailInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FitWorkout> b;
    private LinearLayoutManager d;
    private LayoutTemplateInfo e;
    private VideoPlayer f;
    private ArrayMap<String, String> g;
    private RecyclerView i;
    private List<ShowLayout> j;

    /* renamed from: a, reason: collision with root package name */
    private int f3151a = -1;
    private ArrayList<Integer> c = new ArrayList<>();

    public NewDetailInfoAdapter(VideoPlayer videoPlayer) {
        this.f = videoPlayer;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new FitnessNewDetailImageViewHolder(viewGroup);
        }
        if (i == 1) {
            return new FitnessNewDetailVideoViewHolder(viewGroup, this.f);
        }
        if (i == 2) {
            return new FitnessSubTitleViewHolder(viewGroup);
        }
        if (i == 3) {
            return new CoachesIntroducesViewHolder(viewGroup);
        }
        return new FitnessRecommendCourseViewHolder(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int size = i - this.c.size();
        if (viewHolder instanceof FitnessSubTitleViewHolder) {
            if (this.e.getWorkoutIntroductionBar() == null) {
                ((FitnessSubTitleViewHolder) viewHolder).e("");
                return;
            } else {
                ((FitnessSubTitleViewHolder) viewHolder).e(koq.b(this.j) ? "" : this.e.getWorkoutIntroductionBar().getName());
                return;
            }
        }
        if (viewHolder instanceof CoachesIntroducesViewHolder) {
            ((CoachesIntroducesViewHolder) viewHolder).d(this.e.getCoachBar());
            return;
        }
        if (viewHolder instanceof FitnessRecommendCourseViewHolder) {
            ((FitnessRecommendCourseViewHolder) viewHolder).d(this.b, this.g);
            return;
        }
        if ((viewHolder instanceof FitnessNewDetailImageViewHolder) && koq.d(this.j, size)) {
            ((FitnessNewDetailImageViewHolder) viewHolder).a(this.j.get(size));
        } else if ((viewHolder instanceof FitnessNewDetailVideoViewHolder) && koq.d(this.j, size)) {
            ((FitnessNewDetailVideoViewHolder) viewHolder).a(this.j.get(size), this.f3151a);
        } else {
            LogUtil.a("Suggestion_NewDetailInfoAdapter", "onBindViewHolder else");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        LayoutTemplateInfo layoutTemplateInfo = this.e;
        if (layoutTemplateInfo == null) {
            return 0;
        }
        if (layoutTemplateInfo.getWorkoutIntroductionBar() == null || koq.b(this.e.getWorkoutIntroductionBar().getLayoutList())) {
            return this.c.size();
        }
        LogUtil.a("Suggestion_NewDetailInfoAdapter", "getItemCount = ", Integer.valueOf(this.j.size()));
        return this.j.size() + this.c.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i < this.c.size()) {
            return this.c.get(i).intValue();
        }
        int size = i - this.c.size();
        if (koq.b(this.j, size)) {
            LogUtil.b("Suggestion_NewDetailInfoAdapter", "getItemViewType curPosition = ", Integer.valueOf(size));
            return 0;
        }
        int type = this.j.get(size).getType();
        LogUtil.a("Suggestion_NewDetailInfoAdapter", "getItemViewType mType = ", Integer.valueOf(type));
        return type;
    }

    public void b(LayoutTemplateInfo layoutTemplateInfo) {
        if (layoutTemplateInfo == null) {
            LogUtil.b("Suggestion_NewDetailInfoAdapter", "data is null");
            return;
        }
        this.e = layoutTemplateInfo;
        if (layoutTemplateInfo.getWorkoutIntroductionBar() != null && koq.c(layoutTemplateInfo.getWorkoutIntroductionBar().getLayoutList())) {
            this.j = layoutTemplateInfo.getWorkoutIntroductionBar().getLayoutList();
        } else {
            this.j = new ArrayList();
        }
        if (koq.c(this.c)) {
            this.c.clear();
        }
        if (this.e.getCoachBar() != null && !TextUtils.isEmpty(this.e.getCoachBar().getName())) {
            this.c.add(3);
        }
        if (koq.c(this.b)) {
            this.c.add(4);
        }
        if (koq.c(this.j) && !TextUtils.isEmpty(this.e.getWorkoutIntroductionBar().getName())) {
            this.c.add(2);
        }
        notifyDataSetChanged();
    }

    public void a(List<FitWorkout> list, ArrayMap<String, String> arrayMap) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_NewDetailInfoAdapter", "fitWorkoutList is empty");
            return;
        }
        this.b = list;
        this.g = arrayMap;
        if (koq.c(list) && !this.c.contains(4)) {
            if (this.c.contains(2)) {
                this.c.add(this.c.indexOf(2), 4);
            } else {
                this.c.add(4);
            }
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.i = recyclerView;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            this.d = (LinearLayoutManager) this.i.getLayoutManager();
            this.i.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.NewDetailInfoAdapter.3
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                    super.onScrolled(recyclerView2, i, i2);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                    super.onScrollStateChanged(recyclerView2, i);
                    if (NewDetailInfoAdapter.this.f == null || NewDetailInfoAdapter.this.d == null || 1 != i) {
                        return;
                    }
                    int findFirstVisibleItemPosition = NewDetailInfoAdapter.this.d.findFirstVisibleItemPosition();
                    int findLastVisibleItemPosition = NewDetailInfoAdapter.this.d.findLastVisibleItemPosition();
                    if ((NewDetailInfoAdapter.this.f3151a < findFirstVisibleItemPosition || NewDetailInfoAdapter.this.f3151a > findLastVisibleItemPosition) && NewDetailInfoAdapter.this.f.b()) {
                        NewDetailInfoAdapter.this.f.f();
                    }
                }
            });
        }
    }

    public void e(int i, int i2) {
        this.f3151a = -1;
        notifyItemChanged(i);
        this.f3151a = i2;
        if (i2 == -1) {
            notifyDataSetChanged();
        } else {
            this.i.getLayoutManager().scrollToPosition(this.f3151a);
        }
    }
}
