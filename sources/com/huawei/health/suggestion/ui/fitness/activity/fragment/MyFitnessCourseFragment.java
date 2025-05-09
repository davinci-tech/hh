package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.adapter.MyCourseAdapter;
import com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.arx;
import defpackage.ary;
import defpackage.asc;
import defpackage.fot;
import defpackage.ggs;
import defpackage.koq;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class MyFitnessCourseFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f3126a;
    private HealthButton b;
    private boolean c = true;
    private MyCourseAdapter d;
    private RelativeLayout e;
    private HealthRecycleView f;
    private a h;
    private LinearLayout i;
    private OnFitnessStatusChangeCallback j;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_my_fitness_course, viewGroup, false);
        aAW_(inflate);
        this.h = new a(this);
        this.j = new OnFitnessStatusChangeCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.MyFitnessCourseFragment.1
            @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
            public void onUpdate() {
                MyFitnessCourseFragment.this.c = true;
            }
        };
        ary.a().e(this.j, "WORKOUT_FINISHED");
        ary.a().e(this.j, "WORKOUT_DELETE");
        return inflate;
    }

    private void aAW_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_MyFitnessCourseFragment", "initLayout(), view == null");
            return;
        }
        this.f3126a = (HealthSubHeader) view.findViewById(R.id.head_my_fitness_course);
        if (getContext() != null && (LanguageUtil.w(getContext()) || LanguageUtil.bb(getContext()))) {
            this.f3126a.setSubHeaderTitleScaleTextSize(0.75f);
        }
        this.e = (RelativeLayout) view.findViewById(R.id.my_fitness_course_recycler_layout);
        this.f = (HealthRecycleView) view.findViewById(R.id.my_fitness_course_recycler_view);
        this.i = (LinearLayout) view.findViewById(R.id.my_fitness_no_course_layout);
        this.b = (HealthButton) view.findViewById(R.id.choose_fitness_course_button);
        this.f3126a.setMoreTextVisibility(4);
        c();
        MyCourseAdapter myCourseAdapter = new MyCourseAdapter(this.f);
        this.d = myCourseAdapter;
        this.f.setAdapter(myCourseAdapter);
        ggs.a(getContext(), this.f);
        this.f.setHasFixedSize(true);
        this.f.setNestedScrollingEnabled(false);
        this.h = new a(this);
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.MyFitnessCourseFragment.5
            @Override // java.lang.Runnable
            public void run() {
                MyFitnessCourseFragment myFitnessCourseFragment = MyFitnessCourseFragment.this;
                myFitnessCourseFragment.e(myFitnessCourseFragment.h);
            }
        });
    }

    private void c() {
        this.f3126a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.MyFitnessCourseFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.a("Suggestion_MyFitnessCourseFragment", "mChooseButton onClick, isFastClick");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    MyFitnessCourseFragment.this.startActivity(new Intent(arx.b(), (Class<?>) MyFitnessCourseActivity.class));
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.MyFitnessCourseFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.a("Suggestion_MyFitnessCourseFragment", "mChooseButton onClick, isFastClick");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    Intent intent = new Intent(arx.b(), (Class<?>) SportAllCourseActivity.class);
                    intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
                    MyFitnessCourseFragment.this.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        HealthRecycleView healthRecycleView = this.f;
        if (healthRecycleView == null || this.d == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.f.setLayoutManager(null);
        this.f.setAdapter(this.d);
        ggs.a(getContext(), this.f);
        this.d.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(a aVar) {
        if (aVar == null) {
            LogUtil.b("Suggestion_MyFitnessCourseFragment", "getDataAndRefreshWorkoutData", "uiCallback is null");
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_MyFitnessCourseFragment", "getDataAndRefreshWorkoutData : courseApi is null.");
        } else {
            courseApi.getUserCourseList(0, 50, 1, "FITNESS_COURSE", aVar);
        }
    }

    static class a extends UiCallback<List<Workout>> {
        private boolean b = true;
        MyFitnessCourseFragment c;
        WeakReference<MyFitnessCourseFragment> d;

        a(MyFitnessCourseFragment myFitnessCourseFragment) {
            WeakReference<MyFitnessCourseFragment> weakReference = new WeakReference<>(myFitnessCourseFragment);
            this.d = weakReference;
            this.c = weakReference.get();
        }

        public void a(boolean z) {
            this.b = z;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            if (!this.b) {
                LogUtil.b("Suggestion_MyFitnessCourseFragment", "MyUiCallbackFitnessCourse() mEnable=false ");
                return;
            }
            MyFitnessCourseFragment myFitnessCourseFragment = this.c;
            if (myFitnessCourseFragment == null || myFitnessCourseFragment.d == null) {
                return;
            }
            this.c.d.notifyDataSetChanged();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            LogUtil.a("Suggestion_MyFitnessCourseFragment", "MyUiCallbackFitnessCourse(", Integer.valueOf(hashCode()), ") onSuccess()");
            if (this.c == null) {
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("Suggestion_MyFitnessCourseFragment", "my running Workout data == null");
                return;
            }
            List<FitWorkout> c = fot.a().c(mod.a(list));
            if (c.isEmpty()) {
                LogUtil.h("Suggestion_MyFitnessCourseFragment", "reorderList is empty!");
                return;
            }
            ArrayList arrayList = new ArrayList(2);
            int i = 1;
            for (FitWorkout fitWorkout : c) {
                if (fitWorkout != null && fitWorkout.getWorkoutType() == 1) {
                    if (i > 2) {
                        break;
                    }
                    arrayList.add(fitWorkout);
                    i++;
                }
            }
            if (koq.b(arrayList)) {
                this.c.e.setVisibility(8);
                this.c.i.setVisibility(0);
            } else {
                this.c.e.setVisibility(0);
                this.c.i.setVisibility(8);
            }
            if (this.c.d != null) {
                this.c.d.b(true, (List<FitWorkout>) arrayList);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.a("Suggestion_MyFitnessCourseFragment", "onStart");
        if (this.c) {
            asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.MyFitnessCourseFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    MyFitnessCourseFragment myFitnessCourseFragment = MyFitnessCourseFragment.this;
                    myFitnessCourseFragment.e(myFitnessCourseFragment.h);
                }
            });
            this.c = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.d == null && this.j == null) {
            return;
        }
        this.d = null;
        ary.a().c(this.j, "WORKOUT_FINISHED");
        ary.a().c(this.j, "WORKOUT_DELETE");
        this.j = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.h.a(false);
        super.onDestroyView();
    }
}
