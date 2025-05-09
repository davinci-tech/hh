package com.huawei.health.suggestion.customizecourse.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseDetailActionViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseMultiBottomViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseMultiViewHolder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.ffg;
import defpackage.ffu;
import defpackage.fhq;
import defpackage.fhx;
import defpackage.fjd;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CustomCourseDetailActionAdapter extends RecyclerView.Adapter<CustomCourseDragViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private HeartRateConfigHelper f3034a;
    private final Context b;
    private fhx c;
    private ArrayList<fjd> d;
    private int e;
    private ffg f;
    private fhx h;

    public CustomCourseDetailActionAdapter(Context context) {
        this.b = context;
    }

    public void e(HeartRateConfigHelper heartRateConfigHelper, ffg ffgVar) {
        this.f3034a = heartRateConfigHelper;
        this.f = ffgVar;
    }

    public void d(FitWorkout fitWorkout) {
        if (fitWorkout == null || koq.b(fitWorkout.getCourseActions())) {
            LogUtil.h("Suggestion_CustomCourseDetailActionAdapter", "setFitWorkout, fitWorkout == null or CollectionUtils.isEmpty(fitWorkout.getCourseActions())");
            return;
        }
        ArrayList<fjd> arrayList = this.d;
        if (arrayList == null) {
            this.d = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        for (ChoreographedMultiActions choreographedMultiActions : fitWorkout.getCourseActions()) {
            if (choreographedMultiActions != null && koq.c(choreographedMultiActions.getActionList())) {
                b(choreographedMultiActions);
            } else {
                LogUtil.h("Suggestion_CustomCourseDetailActionAdapter", "setFitWorkout, multiActions == null or multiActions.getActionList() isEmpty");
            }
        }
        notifyDataSetChanged();
    }

    private void b(ChoreographedMultiActions choreographedMultiActions) {
        int i = 0;
        if (choreographedMultiActions.getRepeatTimes() <= 1 && choreographedMultiActions.getActionList().size() == 1) {
            ChoreographedSingleAction choreographedSingleAction = choreographedMultiActions.getActionList().get(0);
            if (choreographedSingleAction == null || choreographedSingleAction.getAction() == null) {
                return;
            }
            this.d.add(b(choreographedSingleAction, 0));
            return;
        }
        this.d.add(a(choreographedMultiActions));
        int size = choreographedMultiActions.getActionList().size();
        while (i < size) {
            ChoreographedSingleAction choreographedSingleAction2 = choreographedMultiActions.getActionList().get(i);
            if (choreographedSingleAction2 == null || choreographedSingleAction2.getAction() == null) {
                return;
            }
            i++;
            if (i < size) {
                this.d.add(b(choreographedSingleAction2, 1));
            } else {
                this.d.add(b(choreographedSingleAction2, 2));
            }
        }
        fjd fjdVar = new fjd();
        fjdVar.e(4);
        this.d.add(fjdVar);
    }

    private fjd a(ChoreographedMultiActions choreographedMultiActions) {
        fjd fjdVar = new fjd();
        fjdVar.e(3);
        fjdVar.b(choreographedMultiActions.getRepeatTimes());
        return fjdVar;
    }

    private fjd b(ChoreographedSingleAction choreographedSingleAction, int i) {
        fjd fjdVar = new fjd();
        fjdVar.e(i);
        fjdVar.b(0);
        AtomicAction action = choreographedSingleAction.getAction();
        if (action == null) {
            LogUtil.b("Suggestion_CustomCourseDetailActionAdapter", "getSingleActionItemConfig action is null.");
            return fjdVar;
        }
        int e = new ffu().e(action.getId().trim());
        if (e != 0) {
            fjdVar.e(BaseApplication.getContext().getResources().getString(e));
        } else {
            fjdVar.e(action.getName());
        }
        fjdVar.a(action.getId());
        if (this.c == null || this.h == null) {
            c();
        }
        if (choreographedSingleAction.getTargetConfig() != null) {
            fjdVar.e(this.b, choreographedSingleAction.getTargetConfig(), this.c);
        }
        if (choreographedSingleAction.getIntensityConfig() != null) {
            fjdVar.c(this.b, choreographedSingleAction.getIntensityConfig(), this.c, this.h, this.e, this.f);
        }
        return fjdVar;
    }

    private void c() {
        HeartRateConfigHelper heartRateConfigHelper = this.f3034a;
        if (heartRateConfigHelper == null) {
            LogUtil.h("Suggestion_CustomCourseDetailActionAdapter", "initHeartRateZoneConfig, mHeartRateConfigHelper is null");
            return;
        }
        HeartZoneConf a2 = heartRateConfigHelper.a();
        if (a2 != null) {
            this.c = fhq.e(a2);
            this.h = fhq.d(a2);
            this.e = a2.getClassifyMethod();
            LogUtil.a("Suggestion_CustomCourseDetailActionAdapter", "initHeartRateZoneConfig:", a2.toString());
            return;
        }
        LogUtil.h("Suggestion_CustomCourseDetailActionAdapter", "initHeartRateZoneConfig, heartZoneConf == null");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i < this.d.size()) {
            return this.d.get(i).i();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ayc_, reason: merged with bridge method [inline-methods] */
    public CustomCourseDragViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.b);
        if (i == 0) {
            return new CourseDetailActionViewHolder(from.inflate(R.layout.sug_course_detail_item_action_card, viewGroup, false), i);
        }
        if (i == 3) {
            return new CourseMultiViewHolder(from.inflate(R.layout.sug_custom_course_item_multi, viewGroup, false), this.b);
        }
        if (i == 4) {
            return new CourseMultiBottomViewHolder(from.inflate(R.layout.sug_custom_course_item_multi_space, viewGroup, false));
        }
        return new CourseDetailActionViewHolder(from.inflate(R.layout.sug_course_detail_item_action_multi, viewGroup, false), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CustomCourseDragViewHolder customCourseDragViewHolder, int i) {
        if (i < this.d.size()) {
            customCourseDragViewHolder.init(this.d.get(i), i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CustomCourseDragViewHolder customCourseDragViewHolder, int i, List<Object> list) {
        super.onBindViewHolder(customCourseDragViewHolder, i, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<fjd> arrayList = this.d;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }
}
