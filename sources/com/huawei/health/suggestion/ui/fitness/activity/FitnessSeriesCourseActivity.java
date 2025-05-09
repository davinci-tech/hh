package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.asc;
import defpackage.ggs;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessSeriesCourseActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3090a;
    private Context b;
    private int c = 0;
    private HealthRecycleView d;
    private FitSeriesCourseAdapter e;

    static /* synthetic */ int c(FitnessSeriesCourseActivity fitnessSeriesCourseActivity) {
        int i = fitnessSeriesCourseActivity.c;
        fitnessSeriesCourseActivity.c = i + 1;
        return i;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        FitSeriesCourseAdapter fitSeriesCourseAdapter = new FitSeriesCourseAdapter(this.d);
        this.e = fitSeriesCourseAdapter;
        this.d.setAdapter(fitSeriesCourseAdapter);
        ggs.a(this.b, this.d);
        this.e.e(new FitSeriesCourseAdapter.LoadMoreCourseListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseActivity.1
            @Override // com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseAdapter.LoadMoreCourseListener
            public void loadMore() {
                FitnessSeriesCourseActivity.this.b();
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_series_course);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        this.d = (HealthRecycleView) findViewById(R.id.recyclerview_series_course);
        this.f3090a = (LinearLayout) findViewById(R.id.series_course);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c() {
        LogUtil.a("FitnessSeriesCourseActivity", "getSeriesCourseDateAndRefresh(), mPager=", Integer.valueOf(this.c));
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("FitnessSeriesCourseActivity", "getSeriesCourseDateAndRefresh : courseApi is null.");
        } else {
            courseApi.getCourseTopicList(this.c, new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        asc.e().b(new Runnable() { // from class: fky
            @Override // java.lang.Runnable
            public final void run() {
                FitnessSeriesCourseActivity.this.c();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        HealthRecycleView healthRecycleView = this.d;
        if (healthRecycleView == null || this.e == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.d.setLayoutManager(null);
        this.d.setAdapter(this.e);
        ggs.a(this.b, this.d);
        this.e.notifyDataSetChanged();
    }

    static class c extends UiCallback<List<Topic>> {
        WeakReference<FitnessSeriesCourseActivity> e;

        c(FitnessSeriesCourseActivity fitnessSeriesCourseActivity) {
            this.e = new WeakReference<>(fitnessSeriesCourseActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("FitnessSeriesCourseActivity", "GetTopicCallback, load data failed");
            FitnessSeriesCourseActivity fitnessSeriesCourseActivity = this.e.get();
            if (fitnessSeriesCourseActivity != null) {
                fitnessSeriesCourseActivity.f3090a.setVisibility(0);
                fitnessSeriesCourseActivity.e.d(null);
            } else {
                LogUtil.h("FitnessSeriesCourseActivity", "FitnessSeriesCourseActivity is null");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Topic> list) {
            LogUtil.c("FitnessSeriesCourseActivity", "GetTopicCallback,load data success");
            FitnessSeriesCourseActivity fitnessSeriesCourseActivity = this.e.get();
            if (fitnessSeriesCourseActivity == null) {
                LogUtil.h("FitnessSeriesCourseActivity", "FitnessSeriesCourseActivity is null");
            } else {
                FitnessSeriesCourseActivity.c(fitnessSeriesCourseActivity);
                fitnessSeriesCourseActivity.e.d(list);
            }
        }
    }
}
