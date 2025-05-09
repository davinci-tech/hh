package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.healthcloud.plugintrack.ui.view.TrainingResultsView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ffu;
import defpackage.ffy;
import defpackage.ggm;
import defpackage.koq;
import defpackage.moe;
import defpackage.moj;
import health.compact.a.Services;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseActionViewHolder {
    private LinearLayout b;
    private WorkoutRecord c;
    private final Context d;
    private HealthRecycleView e;

    public CourseActionViewHolder(Context context) {
        this.d = context;
    }

    public View bkB_(boolean z) {
        View inflate = View.inflate(this.d, R.layout.track_course_action, null);
        HealthDivider healthDivider = (HealthDivider) inflate.findViewById(R.id.action_top_divider);
        if (z) {
            healthDivider.setVisibility(8);
        }
        this.b = (LinearLayout) inflate.findViewById(R.id.course_action_layout);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.sug_coach_rcv_actions);
        this.e = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.e.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
        return inflate;
    }

    public boolean e(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.b("Track_CourseActionViewHolder", " motionPathSimplify == null");
            return false;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_CourseActionViewHolder", "refreshDifficulty : courseApi is null.");
            return false;
        }
        WorkoutRecord acquireWorkoutRecordByExerciseTime = courseApi.acquireWorkoutRecordByExerciseTime(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), motionPathSimplify.requestEndTime(), motionPathSimplify.requestRunCourseId());
        if (acquireWorkoutRecordByExerciseTime == null) {
            LogUtil.b("Track_CourseActionViewHolder", "refreshCourseLayout mRecord == null");
            return false;
        }
        this.c = acquireWorkoutRecordByExerciseTime;
        if (acquireWorkoutRecordByExerciseTime.isFitnessRecordFromTv()) {
            LogUtil.a("Track_CourseActionViewHolder", "refreshCourseLayout isFitnessRecordFromTv");
            return false;
        }
        if (acquireWorkoutRecordByExerciseTime.acquireWorkoutId() == null || !acquireWorkoutRecordByExerciseTime.acquireWorkoutId().equals(motionPathSimplify.requestRunCourseId())) {
            return false;
        }
        return b(acquireWorkoutRecordByExerciseTime);
    }

    private boolean b(WorkoutRecord workoutRecord) {
        List<RecordAction> c = c(workoutRecord.acquireActionSummary());
        if (koq.b(c)) {
            LogUtil.b("Track_CourseActionViewHolder", "updateActionsName mRecordActions is empty");
            return false;
        }
        HashMap hashMap = new HashMap();
        for (RecordAction recordAction : c) {
            if (recordAction != null && recordAction.getActionId() != null) {
                int e = new ffu().e(recordAction.getActionId().trim());
                if (e != 0) {
                    hashMap.put(recordAction.getActionId(), this.d.getString(e));
                } else {
                    hashMap.put(recordAction.getActionId(), recordAction.getActionName());
                }
                recordAction.setActionName((String) hashMap.get(recordAction.getActionId()));
            }
        }
        return c(c);
    }

    private boolean c(List<RecordAction> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        this.b.setVisibility(0);
        a(list);
        this.e.getAdapter().notifyDataSetChanged();
        return true;
    }

    private void a(List<RecordAction> list) {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null) {
            this.e.setAdapter(pluginSuggestion.getBaseRecyclerViewAdapter(list));
        }
    }

    private List<RecordAction> c(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        return moj.b(str, RecordAction[].class);
    }

    public TrainingResultsView b(boolean z) {
        TrainingResultsView trainingResultsView = new TrainingResultsView(this.d);
        WorkoutRecord workoutRecord = this.c;
        if (workoutRecord == null) {
            trainingResultsView.setVisibility(8);
            return trainingResultsView;
        }
        trainingResultsView.setCourseName(workoutRecord.acquireWorkoutName());
        trainingResultsView.setCourseDifficulty(ggm.d(this.c.acquireDifficulty()));
        trainingResultsView.setCourseDuration(ffy.d(this.d, R.string._2130837534_res_0x7f02001e, moe.k(this.c.acquireCourseDuration())));
        trainingResultsView.setCourseFinish(this.c.acquireFinishRate());
        trainingResultsView.setHealthDivider(z);
        return trainingResultsView;
    }
}
