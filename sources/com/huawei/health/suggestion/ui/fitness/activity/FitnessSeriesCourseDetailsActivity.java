package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseDetialsAdapter;
import com.huawei.health.suggestion.ui.view.FitnessEmptyView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.asc;
import defpackage.ggs;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessSeriesCourseDetailsActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f3091a = 0;
    private Context b;
    private FitSeriesCourseDetialsAdapter c;
    private FitnessEmptyView d;
    private HealthRecycleView e;
    private String f;
    private String g;
    private HealthTextView h;
    private CustomTitleBar i;
    private int j;

    static /* synthetic */ int j(FitnessSeriesCourseDetailsActivity fitnessSeriesCourseDetailsActivity) {
        int i = fitnessSeriesCourseDetailsActivity.f3091a;
        fitnessSeriesCourseDetailsActivity.f3091a = i + 1;
        return i;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        FitSeriesCourseDetialsAdapter fitSeriesCourseDetialsAdapter = new FitSeriesCourseDetialsAdapter(this.e);
        this.c = fitSeriesCourseDetialsAdapter;
        this.e.setAdapter(fitSeriesCourseDetialsAdapter);
        ggs.a(this.b, this.e);
        this.c.a(new FitSeriesCourseDetialsAdapter.LoadMoreCourseDetialsListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity.5
            @Override // com.huawei.health.suggestion.ui.fitness.adapter.FitSeriesCourseDetialsAdapter.LoadMoreCourseDetialsListener
            public void loadMore() {
                FitnessSeriesCourseDetailsActivity.this.c();
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_series_course_details);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        this.e = (HealthRecycleView) findViewById(R.id.recyclerview_series_course_details);
        this.i = (CustomTitleBar) findViewById(R.id.tv_title_train);
        this.h = (HealthTextView) findViewById(R.id.tv_description);
        this.d = (FitnessEmptyView) findViewById(R.id.sug_series_course_empty_view);
        setViewSafeRegion(true, this.e);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Sug_FitnessSeriesCourseDetailsActivity", "initData(),intent is null");
            return;
        }
        this.j = intent.getIntExtra("intent_key_topicid", -1);
        this.g = intent.getStringExtra("intent_key_topicname");
        this.f = intent.getStringExtra("intent_key_description");
        FitSeriesCourseDetialsAdapter fitSeriesCourseDetialsAdapter = this.c;
        if (fitSeriesCourseDetialsAdapter != null) {
            fitSeriesCourseDetialsAdapter.c(this.g);
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity.4
            @Override // java.lang.Runnable
            public void run() {
                FitnessSeriesCourseDetailsActivity.this.i.setTitleText(TextUtils.isEmpty(FitnessSeriesCourseDetailsActivity.this.g) ? "" : FitnessSeriesCourseDetailsActivity.this.g);
                if (TextUtils.isEmpty(FitnessSeriesCourseDetailsActivity.this.f)) {
                    FitnessSeriesCourseDetailsActivity.this.h.setVisibility(8);
                } else {
                    FitnessSeriesCourseDetailsActivity.this.h.setText(FitnessSeriesCourseDetailsActivity.this.f);
                }
            }
        });
        LogUtil.a("Sug_FitnessSeriesCourseDetailsActivity", "mTopicId:", Integer.valueOf(this.j), "-mTopicName:", this.g);
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        asc.e().b(new Runnable() { // from class: fla
            @Override // java.lang.Runnable
            public final void run() {
                FitnessSeriesCourseDetailsActivity.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("Sug_FitnessSeriesCourseDetailsActivity", "getDetailsWorkoutsByTopicIdAndRefresh(), mPage=", Integer.valueOf(this.f3091a), "mTopicId=", Integer.valueOf(this.j));
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Sug_FitnessSeriesCourseDetailsActivity", "getDetailsWorkoutsByTopicIdAndRefresh : courseApi is null.");
        } else {
            courseApi.getCoursesByTopicId(this.f3091a, this.j, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Sug_FitnessSeriesCourseDetailsActivity", "getDetailsWorkoutsByTopicIdAndRefresh(), load data failed");
                    FitnessSeriesCourseDetailsActivity.this.c.b(null);
                    FitnessSeriesCourseDetailsActivity.this.d.e();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    LogUtil.c("Sug_FitnessSeriesCourseDetailsActivity", "getDetailsWorkoutsByTopicIdAndRefresh(),load data success");
                    FitnessSeriesCourseDetailsActivity.j(FitnessSeriesCourseDetailsActivity.this);
                    FitnessSeriesCourseDetailsActivity.this.c.b(mod.a(list));
                    FitnessSeriesCourseDetailsActivity.this.d.a();
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        b();
        HealthRecycleView healthRecycleView = this.e;
        if (healthRecycleView == null || this.c == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.e.setLayoutManager(null);
        this.e.setAdapter(this.c);
        ggs.a(this.b, this.e);
        this.c.notifyDataSetChanged();
    }

    private void b() {
        if (!nsn.l()) {
            LogUtil.c("Sug_FitnessSeriesCourseDetailsActivity", "adaptMateX(),is not adaptMateX");
            return;
        }
        nsn.cLF_(getBaseContext(), true, true, (CustomTitleBar) findViewById(R.id.tv_title), (HealthTextView) findViewById(R.id.tv_description));
    }
}
