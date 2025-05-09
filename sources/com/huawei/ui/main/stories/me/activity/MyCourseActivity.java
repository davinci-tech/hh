package com.huawei.ui.main.stories.me.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.marketing.views.MarketingView;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.suggestion.util.FitnessItemDecoration;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.me.activity.MyCourseActivity;
import com.huawei.ui.main.stories.me.adapter.MyCourseAdapter;
import defpackage.fff;
import defpackage.koq;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes9.dex */
public class MyCourseActivity extends BaseActivity {
    private HealthRecycleView c;
    private MyCourseAdapter d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_show_my_course);
        this.c = (HealthRecycleView) findViewById(R.id.my_course);
        this.d = new MyCourseAdapter(this);
        b();
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(this) { // from class: com.huawei.ui.main.stories.me.activity.MyCourseActivity.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        healthLinearLayoutManager.setOrientation(1);
        this.c.setLayoutManager(healthLinearLayoutManager);
        this.c.addItemDecoration(new FitnessItemDecoration(this, 0, nsn.a(this, getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8))));
        this.c.setAdapter(this.d);
        a();
    }

    private void b() {
        MarketingView marketingView = new MarketingView(this);
        marketingView.setInvalidateCallback(new IBaseResponseCallback() { // from class: rfu
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MyCourseActivity.this.c(i, obj);
            }
        });
        marketingView.d(4159);
        this.d.addBottomView(marketingView);
    }

    public /* synthetic */ void c(int i, Object obj) {
        this.d.notifyDataSetChanged();
    }

    private void a() {
        if (LoginInit.getInstance(this).isBrowseMode()) {
            LogUtil.h("Suggestion_MyCourseActivity", "isBrowseMode");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rfw
                @Override // java.lang.Runnable
                public final void run() {
                    MyCourseActivity.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_MyCourseActivity", "getCourseData : courseApi is null");
            return;
        }
        c();
        e(courseApi, 0);
        d(courseApi, 1, "");
    }

    private void c() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_MyCourseActivity", "initCurrentPlanData, PlanApi is null.");
            this.d.b("invalidUrl", 0);
        } else {
            planApi.b(new UiPagingCallback<IntPlan>() { // from class: com.huawei.ui.main.stories.me.activity.MyCourseActivity.5
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccessFirst(IntPlan intPlan) {
                    if (intPlan == null) {
                        LogUtil.h("Suggestion_MyCourseActivity", "getCurrentIntPlan, data is null.");
                        MyCourseActivity.this.d.b("invalidUrl", 0);
                    } else if (intPlan.getMetaInfo() != null) {
                        MyCourseActivity.this.d.b(intPlan.getMetaInfo().getPicture(), 0);
                    } else {
                        LogUtil.h("Suggestion_MyCourseActivity", "getCurrentIntPlan, data.getMetaInfo() is null.");
                        MyCourseActivity.this.d.b("invalidUrl", 0);
                    }
                }

                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                public void onFailureFirst(int i, String str) {
                    LogUtil.b("Suggestion_MyCourseActivity", "getCurrentIntPlan onFailure: ", Integer.valueOf(i), " : ", str);
                    MyCourseActivity.this.d.b("invalidUrl", 0);
                }
            });
        }
    }

    private void d(CourseApi courseApi, int i, String str) {
        courseApi.getUserCourseList(0, 1, i, str, new UiCallback<List<Workout>>() { // from class: com.huawei.ui.main.stories.me.activity.MyCourseActivity.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.b("Suggestion_MyCourseActivity", "getUsedCourseData onFailure: ", Integer.valueOf(i2), " : ", str2);
                MyCourseActivity.this.d.b("invalidUrl", 1);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                String topicPreviewPicUrl;
                if (koq.b(list)) {
                    LogUtil.a("Suggestion_MyCourseActivity", "CollectionUtils.isEmpty(workouts)");
                    MyCourseActivity.this.d.b("invalidUrl", 1);
                    return;
                }
                Workout workout = list.get(0);
                if (workout == null) {
                    LogUtil.h("Suggestion_MyCourseActivity", "showCourseWorkout workout is null");
                    MyCourseActivity.this.d.b("invalidUrl", 1);
                    return;
                }
                FitWorkout a2 = mod.a(workout);
                if (a2 != null) {
                    if (a2.getCourseDefineType() == 1) {
                        MyCourseActivity.this.d.b(MyCourseAdapter.a(a2.acquireId()), 1);
                        return;
                    }
                    topicPreviewPicUrl = a2.getTopicPreviewPicUrl();
                } else {
                    WorkoutPackageInfo e = mod.e(workout);
                    if (e == null) {
                        LogUtil.h("Suggestion_MyCourseActivity", "setWorkoutPackageInfo workout is null");
                        MyCourseActivity.this.d.b("invalidUrl", 1);
                        return;
                    } else {
                        String squarePicture = e.getSquarePicture();
                        topicPreviewPicUrl = TextUtils.isEmpty(squarePicture) ? e.getTopicPreviewPicUrl() : squarePicture;
                    }
                }
                if (!TextUtils.isEmpty(topicPreviewPicUrl)) {
                    MyCourseActivity.this.d.b(topicPreviewPicUrl, 1);
                } else {
                    LogUtil.h("Suggestion_MyCourseActivity", "SportCourse workout is null");
                    MyCourseActivity.this.d.b("invalidUrl", 1);
                }
            }
        });
    }

    private void e(CourseApi courseApi, int i) {
        courseApi.getAudioBehaviorList(new fff.e().a(1).b(2).c(i).e(1).d(1).a(), new UiCallback<List<SleepAudioSeries>>() { // from class: com.huawei.ui.main.stories.me.activity.MyCourseActivity.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.b("Suggestion_MyCourseActivity", "initSleepCourseData onFailure: ", Integer.valueOf(i2), " : ", str);
                MyCourseActivity.this.d.b("invalidUrl", 2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<SleepAudioSeries> list) {
                if (list != null && list.size() > 0) {
                    if (list.get(0) == null) {
                        MyCourseActivity.this.d.b("invalidUrl", 2);
                        return;
                    }
                    String smallImage = list.get(0).getSmallImage();
                    boolean isEmpty = TextUtils.isEmpty(smallImage);
                    LogUtil.a("Suggestion_MyCourseActivity", "isEmptyUrl = ", Boolean.valueOf(isEmpty));
                    MyCourseActivity.this.d.b(isEmpty ? "invalidUrl" : smallImage, 2);
                    return;
                }
                LogUtil.h("Suggestion_MyCourseActivity", "initSleepCourseData sleepAudioSeriesList == null");
                MyCourseActivity.this.d.b("invalidUrl", 2);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Suggestion_MyCourseActivity", " onResume");
        MyCourseAdapter myCourseAdapter = this.d;
        if (myCourseAdapter == null || !myCourseAdapter.c()) {
            return;
        }
        a();
        this.d.b(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
