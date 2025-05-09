package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.arx;
import defpackage.fot;
import defpackage.gge;
import defpackage.nqx;
import defpackage.smy;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class FitnessTopicActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private long f3092a;
    private CustomTitleBar b;
    private HealthViewPager c;
    private nqx d;
    private HealthSubTabWidget e;
    private String h;
    private int i;

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
        LogUtil.a("Suggestion_FitnessTopicActivity", "Lifecycle: onCreate()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        LogUtil.a("Suggestion_FitnessTopicActivity", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_fit_topic);
        this.b = (CustomTitleBar) findViewById(R.id.custom_titlebar);
        this.e = (HealthSubTabWidget) findViewById(R.id.sug_detail_tab);
        this.c = (HealthViewPager) findViewById(R.id.sug_detail_vp);
        c();
        b();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        LogUtil.a("Suggestion_FitnessTopicActivity", "initData()");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("Suggestion_FitnessTopicActivity", "Lifecycle: onDestroy()");
        fot.a().c();
        d();
        super.onDestroy();
    }

    public CustomTitleBar a() {
        return this.b;
    }

    public HealthViewPager e() {
        return this.c;
    }

    private void b() {
        this.d = new nqx(this, this.c, this.e);
        this.e.setVisibility(8);
        smy f = this.e.f();
        Intent intent = getIntent();
        if (intent != null && "warm_up_and_stretch".equals(intent.getStringExtra("entrance"))) {
            this.d.c(f, FitnessTopicFragment.b(8), false);
        } else {
            this.d.c(f, FitnessTopicFragment.b(0), false);
        }
    }

    private void d() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("topic_name", this.h);
        hashMap.put("stay_time", Long.valueOf(System.currentTimeMillis() - this.f3092a));
        gge.e("1130016", hashMap);
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.f3092a = System.currentTimeMillis();
        this.i = intent.getIntExtra("intent_key_topicid", -1);
        if (intent.getStringExtra("intent_key_topicname") != null) {
            this.h = intent.getStringExtra("intent_key_topicname");
        } else {
            this.h = "";
        }
        LogUtil.a("Suggestion_FitnessTopicActivity", "mTopicId:", Integer.valueOf(this.i), "-mTopicName:", this.h);
        if (this.i != -1) {
            i();
        } else {
            this.b.setTitleText(arx.b().getString(R.string._2130848460_res_0x7f022acc));
        }
        LogUtil.a("Suggestion_FitnessTopicActivity", "mTopicName = ", this.h);
        LogUtil.a("Suggestion_FitnessTopicActivity", "mTopicId = ", Integer.valueOf(this.i));
    }

    private void i() {
        String str = this.h;
        if (str == null || str.isEmpty()) {
            e eVar = new e(this);
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi != null) {
                courseApi.getCourseTopicList(0, eVar);
                return;
            }
            return;
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.b.setTitleText(this.h);
    }

    /* loaded from: classes7.dex */
    static class e extends UiCallback<List<Topic>> {
        WeakReference<FitnessTopicActivity> d;

        e(FitnessTopicActivity fitnessTopicActivity) {
            this.d = new WeakReference<>(fitnessTopicActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_FitnessTopicActivity", "GetTopicNameCallBack on failure ", "errorCode:", Integer.valueOf(i), MyLocationStyle.ERROR_INFO, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Topic> list) {
            if (list == null) {
                LogUtil.h("Suggestion_FitnessTopicActivity", "GetTopicNameCallBack onSuccess topic data null");
                return;
            }
            WeakReference<FitnessTopicActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.h("Suggestion_FitnessTopicActivity", "mWeakReference is null");
                return;
            }
            final FitnessTopicActivity fitnessTopicActivity = this.d.get();
            for (final Topic topic : list) {
                if (topic != null && topic.acquireId() == fitnessTopicActivity.i) {
                    fitnessTopicActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessTopicActivity.e.1
                        @Override // java.lang.Runnable
                        public void run() {
                            fitnessTopicActivity.h = topic.acquireName();
                            fitnessTopicActivity.h();
                        }
                    });
                    return;
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
