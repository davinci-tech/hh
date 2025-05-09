package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Intent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessMyCourseWithDataAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.gge;
import defpackage.koq;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessMyCourseViewHolder extends AbsFitnessViewHolder<List<FitWorkout>> implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final View f3211a;
    private FitnessMyCourseWithDataAdapter b;
    private final View c;
    private final HealthButton d;
    private final HealthSubHeader e;
    private final HealthRecycleView i;

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void init(List<FitWorkout> list) {
        if (list == null) {
            LogUtil.h("Suggestion_FitnessMyCourseViewHolder", "FitnessMyCourseViewHolder init fitWorkouts null");
            return;
        }
        b();
        c();
        e(list);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_FitnessMyCourseViewHolder", "onClick view null");
            ViewClickInstrumentation.clickOnView(view);
        } else if (isFastClick()) {
            LogUtil.h("Suggestion_FitnessMyCourseViewHolder", "FitnessMyCourseViewHolder is fast click");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.tv_fitness_add_course) {
                e();
            } else {
                a();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b() {
        setRecyclerViewLayout(this.itemView.getContext(), this.i);
        this.i.setAdapter(this.b);
    }

    private void e() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130848515_res_0x7f022b03));
        hashMap.put("position", 3);
        gge.e("1130015", hashMap);
        Intent intent = new Intent(this.itemView.getContext(), (Class<?>) SportAllCourseActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        this.itemView.getContext().startActivity(intent);
    }

    private void a() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130848532_res_0x7f022b14));
        hashMap.put("position", 2);
        gge.e("1130015", hashMap);
        Intent intent = new Intent(this.itemView.getContext(), (Class<?>) MyFitnessCourseActivity.class);
        intent.setFlags(268435456);
        this.itemView.getContext().startActivity(intent);
    }

    private void c() {
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    private void e(List<FitWorkout> list) {
        a(list);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessMyCourseViewHolder", "workouts is empty");
        } else {
            this.b.c(list);
        }
    }

    private void a(List<FitWorkout> list) {
        if (koq.b(list)) {
            this.c.setVisibility(8);
            this.f3211a.setVisibility(0);
        } else {
            this.c.setVisibility(0);
            this.f3211a.setVisibility(8);
        }
    }
}
