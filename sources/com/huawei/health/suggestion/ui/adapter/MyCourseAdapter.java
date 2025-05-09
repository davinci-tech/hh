package com.huawei.health.suggestion.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.health.suggestion.ui.fitness.viewholder.LoadMoreViewHolder;
import com.huawei.health.suggestion.ui.viewholder.CourseItemHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.fot;
import defpackage.koq;
import defpackage.mod;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class MyCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context c;
    private FitnessTopicDeleteModel e;
    private String f;
    private OnItemClickListener g;
    private boolean i;
    private List<Object> d = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private boolean f3063a = true;
    private int b = 1;
    private CourseItemHolder.ClickItemCount j = new CourseItemHolder.ClickItemCount() { // from class: com.huawei.health.suggestion.ui.adapter.MyCourseAdapter.3
        @Override // com.huawei.health.suggestion.ui.viewholder.CourseItemHolder.ClickItemCount
        public void onClickCheckBoxItem() {
            LogUtil.a("Suggestion_MyFitnessCourseAdapter", "onClickCheckBoxItem item ");
            if (MyCourseAdapter.this.g != null) {
                MyCourseAdapter.this.g.onItemClick();
            }
        }
    };

    public interface OnItemClickListener {
        void onItemClick();
    }

    public MyCourseAdapter(HealthRecycleView healthRecycleView) {
        this.c = healthRecycleView.getContext();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("Suggestion_MyFitnessCourseAdapter", "onCreateViewHolder(ViewGroup parent, int viewType)");
        if (i == 1) {
            return new LoadMoreViewHolder(LayoutInflater.from(this.c).inflate(R.layout.sug_his_loading_more, viewGroup, false));
        }
        return new CourseItemHolder(LayoutInflater.from(this.c).inflate(R.layout.sug_my_course_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("Suggestion_MyFitnessCourseAdapter", "onBindViewHolder(HealthRecycleView.ViewHolder holder, int position)");
        if (!(viewHolder instanceof CourseItemHolder) || koq.b(this.d, i)) {
            LogUtil.b("Suggestion_MyFitnessCourseAdapter", "onBindViewHolder error!");
            return;
        }
        CourseItemHolder courseItemHolder = (CourseItemHolder) viewHolder;
        if (getItemViewType(i) == 0) {
            FitnessTopicDeleteModel fitnessTopicDeleteModel = this.e;
            if (fitnessTopicDeleteModel != null) {
                fitnessTopicDeleteModel.savePosition(i);
            }
            if (i == 0) {
                courseItemHolder.e(0);
            } else {
                Context context = this.c;
                if (context != null && nsn.ag(context) && i == 1) {
                    courseItemHolder.e(0);
                } else {
                    courseItemHolder.e(8);
                }
            }
            if (i == this.d.size() - 1) {
                courseItemHolder.a(8);
            } else {
                Context context2 = this.c;
                if (context2 != null && nsn.ag(context2) && i == this.d.size() - 2) {
                    courseItemHolder.a(8);
                } else {
                    courseItemHolder.a(0);
                }
            }
            if (this.d.get(i) instanceof Workout) {
                courseItemHolder.e(this.e, this.f, this.i, this.d.get(i));
            } else {
                courseItemHolder.e(this.e, this.d.get(i));
            }
            courseItemHolder.e(this.j);
            courseItemHolder.c(this.b);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.f3063a && this.d.size() >= 50) {
            return this.d.size() + 1;
        }
        return this.d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.f3063a && this.d.size() >= 50 && i == getItemCount() - 1) ? 1 : 0;
    }

    public void e(boolean z) {
        this.f3063a = z;
        notifyDataSetChanged();
    }

    public boolean c() {
        return this.f3063a;
    }

    public void d(int i) {
        this.b = i;
    }

    public void e(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z, List<Object> list) {
        this.d.clear();
        d(fitnessTopicDeleteModel, z, list);
    }

    public void b(boolean z, List<FitWorkout> list) {
        this.d.clear();
        if (list == null) {
            LogUtil.h("Suggestion_MyFitnessCourseAdapter", "fitWorkouts null or size =0");
            return;
        }
        if (list.size() < 50) {
            this.f3063a = false;
        }
        int size = this.d.size();
        this.i = z;
        this.d.addAll(list);
        int max = Math.max(1, getItemCount() - size);
        if (size == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeChanged(size, max);
        }
    }

    public void d(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z, List<Object> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_MyFitnessCourseAdapter", "fitWorkouts null or size =0");
            return;
        }
        LogUtil.h("Suggestion_MyFitnessCourseAdapter", Integer.valueOf(list.hashCode()));
        if (fitnessTopicDeleteModel != null) {
            this.e = fitnessTopicDeleteModel;
        }
        this.i = z;
        this.d.addAll(list);
        this.d = a(this.d);
        d();
        notifyDataSetChanged();
    }

    private void d() {
        if (this.b == 3 && koq.e((Object) this.d, Workout.class)) {
            List<FitWorkout> a2 = mod.a((List<Workout>) koq.e((List) this.d, Workout.class));
            fot.a().a(a2);
            this.d = koq.e((List) mod.j(a2), Object.class);
        }
    }

    private List<Object> a(List<Object> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_MyFitnessCourseAdapter", "removeWorkout() workouts is null");
            return arrayList;
        }
        for (Object obj : list) {
            if (!arrayList.contains(obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public List<Object> b() {
        return this.d;
    }

    public void b(FitnessTopicDeleteModel fitnessTopicDeleteModel, boolean z) {
        if (fitnessTopicDeleteModel != null) {
            this.e = fitnessTopicDeleteModel;
        }
        this.i = z;
        notifyItemRangeChanged(0, getItemCount(), true);
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.g = onItemClickListener;
    }

    public void d(String str) {
        this.f = str;
    }
}
