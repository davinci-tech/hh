package com.huawei.health.suggestion.ui.plan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.plan.fragment.WorkoutDetailsFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.AiPlanWeekDetailViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fys;
import defpackage.fzr;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mnr;
import defpackage.moc;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class WorkoutDetailsFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private Context f3274a;
    private HealthTextView b;
    private AiPlanWeekDetailViewHolder e;
    private LinearLayout g;
    private HealthTextView h;
    private fzr i;
    private IntPlan j;
    private List<Integer> d = new ArrayList();
    private int f = 1;
    private int c = 0;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("WorkoutDetailsFragment", "WorkoutDetailsFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.sug_layout_week_fitnessplan_detail, viewGroup, false);
        inflate.measure(0, 0);
        this.c = inflate.getMeasuredHeight();
        this.g = (LinearLayout) inflate.findViewById(R.id.view_pager_layout);
        this.b = (HealthTextView) inflate.findViewById(R.id.add_course_button);
        this.h = (HealthTextView) inflate.findViewById(R.id.remind_follow_the_plan);
        this.i = new fzr(this.f3274a, this.g);
        b();
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d("WorkoutDetailsFragment", "WorkoutDetailsFragment onResume");
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        ixx.d().d(this.f3274a, AnalyticsValue.INT_PLAN_1120028.value(), hashMap, 0);
        this.i.e();
    }

    private void b() {
        LogUtil.a("WorkoutDetailsFragment", "update");
        this.i.e(this.j);
        a();
    }

    public void a() {
        if (this.j == null || this.d.isEmpty() || this.e == null) {
            Log.d("WorkoutDetailsFragment", "updateCourseDataView failed");
            return;
        }
        final ArrayList<String> a2 = this.i.a();
        this.b.setOnClickListener(new View.OnClickListener() { // from class: fxg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WorkoutDetailsFragment.this.aHV_(a2, view);
            }
        });
        if (!fys.a(this.j, this.d.get(1).intValue(), this.d.get(3).intValue())) {
            this.i.g();
            return;
        }
        this.h.setVisibility(0);
        if (this.d.get(1).intValue() < this.d.get(0).intValue()) {
            this.f = 0;
            this.h.setVisibility(8);
        } else if (this.d.get(1).intValue() > this.d.get(0).intValue()) {
            this.f = 3;
            this.h.setText(R.string._2130848695_res_0x7f022bb7);
        } else {
            this.f = 1;
            this.h.setText(R.string._2130848704_res_0x7f022bc0);
        }
        this.i.e(this.f);
        this.i.e(this.d.get(1).intValue(), this.d.get(3).intValue(), this.e, this.c);
        if (this.d.get(1).intValue() < this.d.get(0).intValue()) {
            this.b.setVisibility(8);
            return;
        }
        if (this.d.get(1).intValue() > this.d.get(0).intValue()) {
            this.b.setVisibility(8);
        } else if (this.i.d() >= 3) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            LogUtil.c("WorkoutDetailsFragment", "mAddCourseButton visible");
        }
    }

    public /* synthetic */ void aHV_(ArrayList arrayList, View view) {
        Intent intent = new Intent(getContext(), (Class<?>) SportAllCourseActivity.class);
        intent.putExtra("KEY_SUG_COURSE_ADD_STATUS", true);
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        intent.putStringArrayListExtra("KEY_SUG_COURSE_ADD_IDS", arrayList);
        boolean h = this.i.h();
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("isOffDay", Boolean.valueOf(h));
        ixx.d().d(this.f3274a, AnalyticsValue.INT_PLAN_1120035.value(), hashMap, 0);
        startActivityForResult(intent, 11);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(Context context, AiPlanWeekDetailViewHolder aiPlanWeekDetailViewHolder, IntPlan intPlan, List<Integer> list) {
        this.e = aiPlanWeekDetailViewHolder;
        this.j = intPlan;
        this.f3274a = context;
        this.d = list;
        if (getView() != null) {
            b();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        List<Integer> list;
        super.onActivityResult(i, i2, intent);
        if (i == 11 && i2 == -1 && intent != null) {
            try {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("KEY_SUG_COURSE_IDS_RESULT");
                ArrayList<String> stringArrayListExtra2 = intent.getStringArrayListExtra("KEY_SUG_COURSE_NAMES_RESULT");
                if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty()) {
                    LogUtil.a("WorkoutDetailsFragment", "couseIdList size is " + stringArrayListExtra.size());
                    if (this.i.d() + stringArrayListExtra.size() > 3) {
                        Toast.makeText(this.f3274a, R.string._2130848643_res_0x7f022b83, 0).show();
                        return;
                    }
                    List<Integer> list2 = this.d;
                    if (list2 == null || 3 >= list2.size() || (list = this.d) == null || 1 >= list.size()) {
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    e(a(stringArrayListExtra, stringArrayListExtra2, hashMap), hashMap);
                    return;
                }
                LogUtil.h("WorkoutDetailsFragment", "onActivityResult: courseIdList is empty. ");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.h("WorkoutDetailsFragment", "getStringArrayListExtra fail");
            }
        }
    }

    private moc a(ArrayList<String> arrayList, ArrayList<String> arrayList2, Map<String, String> map) {
        moc mocVar = new moc();
        mocVar.b(this.j.getPlanId());
        mocVar.a(this.d.get(3).intValue());
        mocVar.b(this.d.get(1).intValue());
        mocVar.d(0);
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            mnr mnrVar = new mnr();
            mnrVar.a(arrayList.get(i));
            mnrVar.b(IntAction.ActionType.WORKOUT.getType());
            arrayList3.add(mnrVar);
            String str = "";
            String str2 = (arrayList2 == null || !koq.d(arrayList2, i)) ? "" : arrayList2.get(i);
            if (str2 != null) {
                str = str2;
            }
            map.put(arrayList.get(i), str);
        }
        mocVar.d(arrayList3);
        return mocVar;
    }

    private void e(moc mocVar, final Map<String, String> map) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return;
        }
        planApi.updateAction(mocVar, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.plan.fragment.WorkoutDetailsFragment.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("WorkoutDetailsFragment", "updateAction fail");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                String value = AnalyticsValue.INT_PLAN_1120036.value();
                for (Map.Entry entry : map.entrySet()) {
                    HashMap hashMap = new HashMap(3);
                    hashMap.put("click", 1);
                    hashMap.put("courseId", entry.getKey());
                    hashMap.put("courseName", entry.getValue());
                    ixx.d().d(WorkoutDetailsFragment.this.f3274a, value, hashMap, 0);
                }
                LogUtil.h("WorkoutDetailsFragment", "updateAction success");
            }
        });
        planApi.setNeedUpdateCurrentPlan();
    }
}
