package com.huawei.health.suggestion.ui.run.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.fitness.adapter.FitSearchRecyAdapter;
import com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import defpackage.arx;
import defpackage.ary;
import defpackage.asc;
import defpackage.ffq;
import defpackage.fot;
import defpackage.gds;
import defpackage.gge;
import defpackage.ggs;
import defpackage.koq;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes8.dex */
public class RunTopicFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f3331a;
    private View b;
    private HealthButton e;
    private View f;
    private int g;
    private HealthTextView h;
    private FitSearchRecyAdapter m;
    private HealthRecycleView n;
    private boolean c = false;
    private Map<String, View> j = new HashMap();
    private Handler i = new a(Looper.getMainLooper(), this);
    private OnFitnessStatusChangeCallback d = new OnFitnessStatusChangeCallback() { // from class: gcw
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            RunTopicFragment.this.a();
        }
    };

    static /* synthetic */ int b(RunTopicFragment runTopicFragment) {
        int i = runTopicFragment.f3331a;
        runTopicFragment.f3331a = i + 1;
        return i;
    }

    public /* synthetic */ void a() {
        LogUtil.a("Suggestion_RunTopicFragment", "mWorkoutStatusChangeCallback is refreshView");
        this.f3331a = 0;
        c();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.g = arguments.getInt("classifyId");
        }
        View inflate = layoutInflater.inflate(R.layout.sug_trainevent_recofm, viewGroup, false);
        this.b = inflate.findViewById(R.id.sug_content);
        this.j.put("showLoading", inflate.findViewById(R.id.loading_layout));
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.net_error_layout);
        this.e = (HealthButton) linearLayout.findViewById(R.id.btn_no_net_work);
        this.j.put("showNetError", linearLayout);
        aKr_(inflate);
        View findViewById = inflate.findViewById(R.id.no_record_layout);
        this.f = findViewById;
        this.j.put("showNoData", findViewById);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.sug_train_rcv_events);
        this.n = healthRecycleView;
        healthRecycleView.setItemAnimator(new DefaultItemAnimator());
        ggs.a(arx.b(), this.n);
        this.j.put("showData", this.n);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.sug_fitnes_nodata);
        this.h = healthTextView;
        if (this.g == 1) {
            healthTextView.setText(R.string._2130848671_res_0x7f022b9f);
        } else {
            healthTextView.setText(R.string._2130848453_res_0x7f022ac5);
        }
        FitSearchRecyAdapter fitSearchRecyAdapter = new FitSearchRecyAdapter((Context) Objects.requireNonNull(getActivity()));
        this.m = fitSearchRecyAdapter;
        this.n.setAdapter(fitSearchRecyAdapter);
        this.f3331a = 0;
        o();
        j();
        asc.e().b(new Runnable() { // from class: gcu
            @Override // java.lang.Runnable
            public final void run() {
                RunTopicFragment.this.e();
            }
        });
        ary.a().e(this.d, "CUSTOM_COURSE_UPDATE");
        return inflate;
    }

    private void o() {
        if (this.g == 1) {
            LogUtil.a("Suggestion_RunTopicFragment", "setRecyclerViewListener current fragment is custom course fragment.");
            this.n.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (RunTopicFragment.this.n.canScrollVertically(1) || !RunTopicFragment.this.c) {
                        return;
                    }
                    LogUtil.a("Suggestion_RunTopicFragment", "method:loadMore()");
                    RunTopicFragment.b(RunTopicFragment.this);
                    RunTopicFragment.this.c();
                }
            });
        }
    }

    private String f() {
        return getResources().getString(R.string._2130848664_res_0x7f022b98).toUpperCase(Locale.ENGLISH);
    }

    private void aKr_(View view) {
        final HealthToolBar healthToolBar = (HealthToolBar) view.findViewById(R.id.toolbar_new);
        this.j.put("showToolBar", healthToolBar);
        healthToolBar.cHc_(View.inflate(getContext(), R.layout.hw_toolbar_bottomview, null));
        healthToolBar.setIcon(2, R.drawable._2131430269_res_0x7f0b0b7d);
        healthToolBar.setIconTitle(2, f());
        healthToolBar.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: gcv
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                RunTopicFragment.this.c(i);
            }
        });
        healthToolBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (healthToolBar.getHeight() > 0) {
                    if (RunTopicFragment.this.n.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) RunTopicFragment.this.n.getLayoutParams();
                        marginLayoutParams.bottomMargin = healthToolBar.getHeight();
                        RunTopicFragment.this.n.setLayoutParams(marginLayoutParams);
                        healthToolBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        return;
                    }
                    LogUtil.b("Suggestion_RunTopicFragment", "setRecyclerMarginBottom mRecycleViewFitWorks.getLayoutParams() is invalid.");
                    return;
                }
                LogUtil.h("Suggestion_RunTopicFragment", "onGlobalLayout toolbar.getHeight() is invalid.");
            }
        });
        healthToolBar.setVisibility(8);
    }

    public /* synthetic */ void c(int i) {
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_RunTopicFragment", "setOnSingleTapListener click button too fast.");
            return;
        }
        FragmentActivity activity = getActivity();
        if (i == 2 && activity != null && isAdded()) {
            Intent intent = new Intent();
            intent.setClass(activity, CustomCourseActivity.class);
            startActivity(intent);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HealthRecycleView healthRecycleView = this.n;
        if (healthRecycleView == null || this.m == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.n.setLayoutManager(null);
        this.n.setAdapter(this.m);
        ggs.a(arx.b(), this.n);
        this.m.notifyDataSetChanged();
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e() {
        int i = this.g;
        if (i == 0) {
            g();
        } else if (i == 1) {
            this.m.a(i);
            c();
        } else {
            h();
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(this.g));
        gge.e("1130053", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.g != 1) {
            return;
        }
        LogUtil.a("Suggestion_RunTopicFragment", "getCustomWorks enter...");
        if (this.f3331a == 0) {
            this.i.sendMessage(Message.obtain(this.i, 3, "showLoading"));
        }
        if (!NetworkUtil.j()) {
            LogUtil.b("Suggestion_RunTopicFragment", "getCustomWorks Network isNetworkAvailable.");
            this.i.sendMessage(Message.obtain(this.i, 3, "showNetError"));
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.b("Suggestion_RunTopicFragment", "getCustomWorks courseApi is null.");
            this.i.sendMessage(Message.obtain(this.i, 3, "showNoData"));
            return;
        }
        courseApi.getCoursesBySecondClassifyId(this.f3331a * 30, 30, 0, 1, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_RunTopicFragment", "getCoursesBySecondClassifyId failed. ", Integer.valueOf(i), ", ", str);
                if (RunTopicFragment.this.f3331a == 0) {
                    RunTopicFragment.this.i.sendMessage(Message.obtain(RunTopicFragment.this.i, 3, "showNoData"));
                }
                RunTopicFragment.this.i.sendEmptyMessage(4);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                RunTopicFragment.this.i.sendMessage(Message.obtain(RunTopicFragment.this.i, 2, list));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Workout> list) {
        LogUtil.a("Suggestion_RunTopicFragment", "courseApi get data success.");
        if (koq.b(list)) {
            b("showNoData");
            return;
        }
        this.c = list.size() >= 30;
        List<FitWorkout> a2 = mod.a(list);
        fot.a().a(a2);
        if (koq.b(a2)) {
            LogUtil.b("Suggestion_RunTopicFragment", "getCoursesBySecondClassifyId customWorkouts is null.");
            return;
        }
        Iterator<FitWorkout> it = a2.iterator();
        while (it.hasNext()) {
            it.next().setCourseDefineType(1);
        }
        b(a2);
        b("showData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        for (Map.Entry<String, View> entry : this.j.entrySet()) {
            if (str.equals("showData") || str.equals("showNoData")) {
                View value = entry.getValue();
                if (!entry.getKey().equals(str) && !entry.getKey().equals("showToolBar")) {
                    r3 = 8;
                }
                value.setVisibility(r3);
            } else {
                entry.getValue().setVisibility(entry.getKey().equals(str) ? 0 : 8);
            }
        }
    }

    private void g() {
        if (ffq.c() == 204) {
            LogUtil.a("Suggestion_RunTopicFragment", "isOversea");
            a(ffq.c());
        } else {
            i();
        }
    }

    private void i() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RunTopicFragment", "getTopicId : courseApi is null.");
        } else {
            courseApi.getCourseTopicList(0, new UiCallback<List<Topic>>() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_RunTopicFragment", "getCourseTopicList()  errorCode:", Integer.valueOf(i), " error info ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Topic> list) {
                    if (koq.b(list)) {
                        LogUtil.h("Suggestion_RunTopicFragment", "initRunCourseTopic data null");
                        return;
                    }
                    for (Topic topic : list) {
                        if (topic != null && "SF006".equals(topic.acquireSerialNum())) {
                            RunTopicFragment.this.a(topic.acquireId());
                            return;
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("Suggestion_RunTopicFragment", "----getInitWorks()--");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RunTopicFragment", "getInitWorks : courseApi is null.");
        } else {
            courseApi.getCoursesByTopicId(0, i, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    LogUtil.h("Suggestion_RunTopicFragment", str, "getInitWorks ==Failed--errorCode:", Integer.valueOf(i2));
                    RunTopicFragment.this.i.sendEmptyMessage(0);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    RunTopicFragment.this.i.sendMessage(Message.obtain(RunTopicFragment.this.i, 1, gds.b(mod.a(list))));
                }
            });
        }
    }

    private void h() {
        LogUtil.a("Suggestion_RunTopicFragment", "Suggestion_RunTopicFragment", "----getRecommendWorks()--");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RunTopicFragment", "getRecommendWorks : courseApi is null.");
        } else {
            courseApi.getCoursesBySecondClassifyId(this.f3331a * 30, 30, this.g, 0, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.10
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_RunTopicFragment", str, "getCoursesBySecondClassifyId == Failed--errorCode:", Integer.valueOf(i));
                    RunTopicFragment.this.i.sendEmptyMessage(0);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    RunTopicFragment.this.i.sendMessage(Message.obtain(RunTopicFragment.this.i, 1, mod.a(list)));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<FitWorkout> list) {
        if (this.f3331a == 0) {
            this.m.c();
        }
        if (koq.c(list)) {
            LogUtil.a("Suggestion_RunTopicFragment", "getData Size: ", Integer.valueOf(list.size()));
            this.m.b(list);
        }
        if (this.c) {
            this.m.e();
        } else {
            this.m.a();
        }
    }

    private void m() {
        this.f.setVisibility(0);
        if (LanguageUtil.bc(getContext())) {
            ((ImageView) this.f.findViewById(R.id.iv_sug_fitness_no_data)).setScaleX(-1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.b.setVisibility(8);
        m();
        this.m.a();
    }

    private void j() {
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.RunTopicFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == null) {
                    LogUtil.b("Suggestion_RunTopicFragment", "mBtnSetNetWork onClick view is null.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    CommonUtil.q(RunTopicFragment.this.getContext());
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        ary.a().c(this.d, "CUSTOM_COURSE_UPDATE");
        this.d = null;
        super.onDestroy();
    }

    static class a extends BaseHandler<RunTopicFragment> {
        public a(Looper looper, RunTopicFragment runTopicFragment) {
            super(looper, runTopicFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aKs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(RunTopicFragment runTopicFragment, Message message) {
            LogUtil.a("Suggestion_RunTopicFragment", "handleMessageWhenReferenceNotNull, message.what = ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                runTopicFragment.b();
                return;
            }
            if (i == 1) {
                if (message.obj instanceof List) {
                    runTopicFragment.b((List<FitWorkout>) message.obj);
                }
            } else if (i == 2) {
                if (message.obj instanceof List) {
                    runTopicFragment.e((List<Workout>) message.obj);
                }
            } else if (i == 3) {
                if (message.obj instanceof String) {
                    runTopicFragment.b((String) message.obj);
                }
            } else if (i == 4) {
                runTopicFragment.m.a();
            } else {
                LogUtil.h("Suggestion_RunTopicFragment", "handleMessageWhenReferenceNotNull, message.what = ", Integer.valueOf(message.what));
            }
        }
    }
}
