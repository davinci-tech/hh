package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.sport.view.FitnessCardView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.gge;
import defpackage.ggs;
import defpackage.koq;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessCourseHorizontalHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private View f3208a;
    private HealthSubHeader b;
    private Topic c;
    private FitnessCourseHorizontalAdapter d;
    private HealthRecycleView e;

    public FitnessCourseHorizontalHolder(View view, Context context) {
        super(view);
        this.d = new FitnessCourseHorizontalAdapter();
        if (CommonUtil.bu()) {
            HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.fitness_course_topic_sub_title_without_more);
            this.b = healthSubHeader;
            healthSubHeader.setVisibility(0);
            ((HealthSubHeader) view.findViewById(R.id.fitness_course_topic_sub_title)).setVisibility(8);
            LogUtil.a("Suggestion_FitnessCourseHorizontalHolder", "subHeaderDebug, isStoreDemoVersion");
        } else {
            HealthSubHeader healthSubHeader2 = (HealthSubHeader) view.findViewById(R.id.fitness_course_topic_sub_title);
            this.b = healthSubHeader2;
            healthSubHeader2.setVisibility(0);
            this.b.setMoreTextVisibility(4);
            LogUtil.a("Suggestion_FitnessCourseHorizontalHolder", "subHeaderDebug, not isStoreDemoVersion");
        }
        this.e = (HealthRecycleView) view.findViewById(R.id.sug_recycleview_topic);
        this.f3208a = view.findViewById(R.id.fitness_bg_view_head);
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(context) { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessCourseHorizontalHolder.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        healthLinearLayoutManager.setOrientation(0);
        this.e.setLayoutManager(healthLinearLayoutManager);
        this.e.setAdapter(this.d);
    }

    public void a(int i) {
        HealthSubHeader healthSubHeader = this.b;
        if (healthSubHeader != null) {
            healthSubHeader.setSubHeaderBackgroundColor(i);
        }
        View view = this.f3208a;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void d(final Topic topic, final String str) {
        if (topic == null) {
            return;
        }
        this.c = topic;
        this.b.setHeadTitleText(topic.acquireName());
        List<FitWorkout> acquireWorkoutList = topic.acquireWorkoutList();
        if (koq.b(acquireWorkoutList)) {
            LogUtil.h("Suggestion_FitnessCourseHorizontalHolder", "workouts is empty");
            return;
        }
        this.d.d(acquireWorkoutList);
        if (CommonUtil.bu()) {
            return;
        }
        this.b.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessCourseHorizontalHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_FitnessCourseHorizontalHolder", "view == mRlName", "topic.acquireId():", Integer.valueOf(topic.acquireId()), "--", topic.acquireName());
                if (str != null) {
                    FitnessCourseHorizontalHolder.this.d();
                } else {
                    FitnessCourseHorizontalHolder.this.b(topic);
                }
                if (FitnessCourseHorizontalHolder.this.itemView.getParent() instanceof RecyclerView) {
                    Context context = ((RecyclerView) FitnessCourseHorizontalHolder.this.itemView.getParent()).getContext();
                    Bundle bundle = new Bundle();
                    bundle.putInt("intent_key_topicid", topic.acquireId());
                    bundle.putString("intent_key_topicname", topic.acquireName());
                    AppRouter.b("/PluginFitnessAdvice/FitnessTopicActivity").zF_(bundle).a(268435456).c(context);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Topic topic) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", topic.acquireName());
        hashMap.put("position", 1);
        gge.e("1130015", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_WEIGHT_RECOMMENDED_TOPIC_2030066.value(), hashMap);
        LogUtil.a("Suggestion_FitnessCourseHorizontalHolder", "doCurrentPageBi", AnalyticsValue.HEALTH_WEIGHT_RECOMMENDED_TOPIC_2030066.value());
    }

    public class FitnessCourseHorizontalAdapter extends RecyclerView.Adapter<FitnessInnerViewHolder> {
        private List<FitWorkout> d = new ArrayList(10);

        public FitnessCourseHorizontalAdapter() {
        }

        public void d(List<FitWorkout> list) {
            if (koq.b(list)) {
                LogUtil.h("Suggestion_FitnessCourseHorizontalHolder", "workout list is empty, pls check");
            }
            LogUtil.a("Suggestion_FitnessCourseHorizontalHolder", "clearAndAddAll()");
            this.d.clear();
            this.d.addAll(list);
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aFy_, reason: merged with bridge method [inline-methods] */
        public FitnessInnerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_item_fitness_topic_inner, viewGroup, false);
            FitnessCardView fitnessCardView = (FitnessCardView) inflate.findViewById(R.id.recycle_item);
            fitnessCardView.setWidthWeight(21.0f);
            fitnessCardView.setHeightWeight(9.0f);
            return new FitnessInnerViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(FitnessInnerViewHolder fitnessInnerViewHolder, int i) {
            if (koq.b(this.d, i)) {
                LogUtil.h("Suggestion_FitnessCourseHorizontalHolder", "position out of bounds, position is ", Integer.valueOf(i), " , fit workouts size is ", Integer.valueOf(this.d.size()));
                return;
            }
            if (i == 0) {
                fitnessInnerViewHolder.c(this.d.get(i), true, false, FitnessCourseHorizontalHolder.this.c);
            } else if (i == getItemCount() - 1) {
                fitnessInnerViewHolder.c(this.d.get(i), false, true, FitnessCourseHorizontalHolder.this.c);
            } else {
                fitnessInnerViewHolder.c(this.d.get(i), false, false, FitnessCourseHorizontalHolder.this.c);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.d == null) {
                return 0;
            }
            int c = ggs.c();
            return this.d.size() > c ? c : this.d.size();
        }
    }
}
