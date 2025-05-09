package com.huawei.health.suggestion.ui.run.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.RunRecommendFragment;
import com.huawei.health.suggestion.ui.run.adapter.FitnessCourseHorizontalAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.arx;
import defpackage.ggs;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class RunRecommendFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3329a;
    private Context b;
    private FitnessCourseHorizontalAdapter c;
    private HealthTextView d;
    private ArrayList<FitWorkout> e = new ArrayList<>();
    private HealthSubHeader g;

    public static RunRecommendFragment a() {
        return new RunRecommendFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Suggestion_RunCourseRecommend", "onCreateView");
        View inflate = View.inflate(BaseApplication.e(), R.layout.sug_fragment_recomend_run_course, null);
        BaseActivity.setViewSafeRegion(false, inflate);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.hw_health_recommend_run_course_title);
        this.g = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(4);
        this.g.setMoreLayoutVisibility(4);
        this.g.setSubHeaderBackgroundColor(ContextCompat.getColor(inflate.getContext(), R.color._2131296971_res_0x7f0902cb));
        this.d = (HealthTextView) inflate.findViewById(R.id.hw_show_health_data_inputweight_top_date);
        this.g.setMoreLayoutVisibility(8);
        this.b = getContext();
        aKp_(inflate);
        Bundle arguments = getArguments();
        if (arguments != null && koq.b(this.e)) {
            try {
                this.e = arguments.getParcelableArrayList("recommendCourse");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("Suggestion_RunCourseRecommend", "onCreateView", LogAnonymous.b((Throwable) e));
            }
        }
        if (koq.c(this.e)) {
            LogUtil.a("Suggestion_RunCourseRecommend", " mRecommendWorkoutList", Integer.valueOf(this.e.size()));
            a(this.e);
        }
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
    }

    private void a(final List<FitWorkout> list) {
        FragmentActivity activity = getActivity();
        if (activity == null || !isAdded()) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: gct
            @Override // java.lang.Runnable
            public final void run() {
                RunRecommendFragment.this.c(list);
            }
        });
    }

    public /* synthetic */ void c(List list) {
        this.f3329a.setVisibility(0);
        this.c.d((List<FitWorkout>) list);
        if (ggs.e(this.b)) {
            return;
        }
        this.g.setHeadTitleText(this.b.getResources().getText(R.string._2130848518_res_0x7f022b06).toString());
        this.d.setVisibility(8);
        this.g.setMoreLayoutVisibility(0);
    }

    private void aKp_(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.head_recommend_run_course);
        this.f3329a = relativeLayout;
        relativeLayout.setVisibility(8);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.head_recommend_run_course_recycler);
        FitnessCourseHorizontalAdapter fitnessCourseHorizontalAdapter = new FitnessCourseHorizontalAdapter();
        this.c = fitnessCourseHorizontalAdapter;
        healthRecycleView.setAdapter(fitnessCourseHorizontalAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(arx.b());
        linearLayoutManager.setOrientation(0);
        healthRecycleView.setLayoutManager(linearLayoutManager);
        healthRecycleView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunRecommendFragment.1
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
        this.f3329a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunRecommendFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (nsn.o()) {
                    LogUtil.a("Suggestion_RunCourseRecommend", "onClick, isFastClick");
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                Intent intent = new Intent(RunRecommendFragment.this.b, (Class<?>) SportAllCourseActivity.class);
                intent.putExtra("SKIP_ALL_COURSE_KEY", "RUNNING_COURSE");
                gnm.aPB_(RunRecommendFragment.this.b, intent);
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(RunRecommendFragment.this.getContext(), AnalyticsValue.HEALTH_SPORT_TAP_ALL_RUNNING_COURSE_1120018.value(), hashMap, 0);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        LogUtil.a("Suggestion_RunCourseRecommend", "onStop() enter");
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.a("Suggestion_RunCourseRecommend", "onDestroyView() enter!");
        super.onDestroyView();
    }
}
