package com.huawei.health.suggestion.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.run.adapter.TrainReportAdapter;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.model.UserInfomation;
import defpackage.ffy;
import defpackage.gdr;
import defpackage.gge;
import defpackage.moi;
import defpackage.nsf;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class TrainReportActivity extends BaseCloudActivity {

    /* renamed from: a, reason: collision with root package name */
    private Plan f3062a;
    private PlanRecord b;
    private CustomTitleBar c;
    private LinearLayout d;
    private boolean e;
    private HealthRecycleView f;
    private PlanStat h;
    private List<WorkoutRecord> j;

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    protected void bindView() {
        setContentView(R.layout.sug_train_report);
        this.d = (LinearLayout) findViewById(R.id.sug_ll_load);
        this.f = (HealthRecycleView) findViewById(R.id.sug_rcv_report);
        this.c = (CustomTitleBar) findViewById(R.id.sug_titleBar);
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    protected void preInitCloudData() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                if (intent.getParcelableExtra("plan") != null) {
                    this.f3062a = (Plan) intent.getParcelableExtra("plan");
                }
            } catch (Exception e2) {
                LogUtil.b("Suggestion_TrainReportActivity", LogAnonymous.b((Throwable) e2));
            }
        }
        if (this.f3062a == null) {
            this.f3062a = c(this);
        }
    }

    public static Plan c(Context context) {
        Plan e2 = gdr.e();
        if (e2 != null) {
            return e2;
        }
        Plan plan = new Plan();
        plan.putName(moi.e(context, R.string._2130848381_res_0x7f022a7d, new Object[0]));
        return plan;
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    protected void registerUiCallback() {
        registerUiCallback(1, new e(this));
    }

    static class e extends UiCallback<List<WorkoutRecord>> {
        private WeakReference<TrainReportActivity> c;

        e(TrainReportActivity trainReportActivity) {
            this.c = null;
            this.c = new WeakReference<>(trainReportActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_TrainReportActivity", Integer.valueOf(i), str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<WorkoutRecord> list) {
            TrainReportActivity trainReportActivity = this.c.get();
            if (trainReportActivity != null) {
                trainReportActivity.j = list;
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    protected void initCloudData() {
        try {
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi != null) {
                this.j = courseApi.getWorkoutRecords(this.f3062a.acquireId(), getUiCallback(1));
            }
        } catch (Exception unused) {
            LogUtil.b("Suggestion_TrainReportActivity", "initCloudData failed");
            finish();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    protected void initLocalData() {
        LogUtil.a("Suggestion_TrainReportActivity", "initLocalData enter");
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getBooleanExtra("finish_plan", false);
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_TrainReportActivity", "initLocalData : userProfileMgrApi is null.");
            return;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo == null) {
            LogUtil.h("Suggestion_TrainReportActivity", "initLocalData null of userInfo");
            finish();
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_TrainReportActivity", "initLocalData : planApi is null.");
            return;
        }
        if (this.f3062a.acquireType() == 3) {
            planApi.setPlanType(3);
            this.h = planApi.getPlanBestRecord(this.f3062a.acquireId(), userInfo.getWeightOrDefaultValue());
            this.b = planApi.getPlanProgress(this.f3062a.acquireId(), false);
        } else {
            planApi.setPlanType(0);
            this.h = planApi.getPlanBestRecord(this.f3062a.acquireId(), 0.0f);
            this.b = planApi.getPlanProgress(this.f3062a.acquireId(), true);
        }
        c();
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    public void initLayout() {
        this.d.setVisibility(8);
        this.f.setLayoutManager(new LinearLayoutManager(this));
        this.c.setTitleText(ffy.d(this, R.string._2130848450_res_0x7f022ac2, this.f3062a.acquireName()));
        this.c.setRightButtonDrawable(getResources().getDrawable(R$drawable.ic_health_nav_share_black), nsf.h(R$string.accessibility_share));
    }

    @Override // com.huawei.health.suggestion.ui.BaseCloudActivity
    public void initViewController() {
        if (isFinishing()) {
            return;
        }
        if (this.h == null) {
            finish();
        } else {
            this.f.setAdapter(new TrainReportAdapter(this, this.f3062a, this.h, this.b, this.j));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        initViewController();
    }

    private void c() {
        if (this.f3062a.acquireType() == 0) {
            a();
        } else {
            b();
        }
    }

    private void b() {
        PlanStat planStat;
        HashMap hashMap = new HashMap(10);
        JSONObject jSONObject = new JSONObject();
        try {
            PlanRecord planRecord = this.b;
            if (planRecord == null) {
                planRecord = new PlanRecord();
            }
            jSONObject.put("type", this.f3062a.acquireType());
            if (gge.c()) {
                jSONObject.put("finish_rate", gge.c(planRecord.acquireFinishRate()));
            }
            if (gge.c() && (planStat = this.h) != null) {
                jSONObject.put("highest_complete_rate", gge.c(planStat.getHighestCompleteRate()));
            }
            hashMap.put("data", jSONObject.toString());
            gge.e("1120010", hashMap);
        } catch (JSONException e2) {
            LogUtil.b("Suggestion_TrainReportActivity", "fitnessTrainReportBi exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void a() {
        HashMap hashMap = new HashMap(10);
        JSONObject jSONObject = new JSONObject();
        try {
            PlanRecord planRecord = this.b;
            if (planRecord == null) {
                planRecord = new PlanRecord();
            }
            if (gge.c()) {
                jSONObject.put("finish_rate", gge.c(planRecord.acquireFinishRate()));
            }
            jSONObject.put("type", this.f3062a.acquireType());
            hashMap.put("data", jSONObject.toString());
            gge.e("1120010", hashMap);
        } catch (JSONException e2) {
            LogUtil.b("Suggestion_TrainReportActivity", "runTrainReportBi exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        boolean z = this.e;
        if (z) {
            LogUtil.a("Suggestion_TrainReportActivity", "finish", Boolean.valueOf(z));
        }
    }
}
