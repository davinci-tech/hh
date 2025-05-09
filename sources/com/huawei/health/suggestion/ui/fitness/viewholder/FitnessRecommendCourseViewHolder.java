package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.adapter.FitnessCourseHorizontalAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class FitnessRecommendCourseViewHolder extends AbsFitnessViewHolder<List<FitWorkout>> {

    /* renamed from: a, reason: collision with root package name */
    private ArrayMap<String, String> f3216a;
    private final HealthRecycleView b;
    private final HealthSubHeader c;
    private FitnessCourseHorizontalAdapter d;

    public FitnessRecommendCourseViewHolder(ViewGroup viewGroup) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_item_fitness_recommand_course, viewGroup, false));
    }

    public FitnessRecommendCourseViewHolder(View view) {
        super(view);
        this.d = new FitnessCourseHorizontalAdapter();
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.head_my_fitness);
        this.c = healthSubHeader;
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.my_fitness_recycle_view);
        this.b = healthRecycleView;
        healthSubHeader.setMoreText("");
        healthSubHeader.setMoreLayoutVisibility(4);
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(view.getContext(), R.color._2131296971_res_0x7f0902cb));
        healthRecycleView.setAdapter(this.d);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(0);
        healthRecycleView.setLayoutManager(linearLayoutManager);
        healthRecycleView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessRecommendCourseViewHolder.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void init(List<FitWorkout> list) {
        if (koq.c(list)) {
            d(list);
            return;
        }
        LogUtil.h("Suggestion_FitnessRecommandCourseViewHolder", "FitnessMyPlanViewHolder init FitnessMyPlanUseCase null");
        this.c.setVisibility(8);
        this.b.setVisibility(8);
    }

    private void d(List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList(6);
        int i = 0;
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                if (i >= 6) {
                    break;
                }
                arrayList.add(fitWorkout);
                i++;
            }
        }
        LogUtil.a("Suggestion_FitnessRecommandCourseViewHolder", Integer.valueOf(this.c.getVisibility()), Integer.valueOf(this.b.getVisibility()), Integer.valueOf(arrayList.size()));
        if (koq.c(arrayList)) {
            this.c.setVisibility(0);
            this.b.setVisibility(0);
        } else {
            this.c.setVisibility(8);
            this.b.setVisibility(8);
        }
        this.d.d(this.f3216a);
        this.d.d(arrayList);
    }

    public void d(List<FitWorkout> list, ArrayMap<String, String> arrayMap) {
        this.f3216a = arrayMap;
        if (koq.c(list)) {
            LogUtil.a("Suggestion_FitnessRecommandCourseViewHolder", "bindData: recommend fitWorkouts size is ", Integer.valueOf(list.size()));
            this.c.setVisibility(0);
            this.b.setVisibility(0);
            this.d.d(this.f3216a);
            this.d.d(list);
            return;
        }
        LogUtil.a("Suggestion_FitnessRecommandCourseViewHolder", "bindData: recommend fitWorkouts is empty");
        this.c.setVisibility(8);
        this.b.setVisibility(8);
    }
}
