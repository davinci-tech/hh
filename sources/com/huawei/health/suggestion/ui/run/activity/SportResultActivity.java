package com.huawei.health.suggestion.ui.run.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.view.AiFitnessPlanEnergyReplacementCard;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessTopicRecyAdapter;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.health.suggestion.ui.run.activity.SportResultActivity;
import com.huawei.health.suggestion.ui.run.adapter.FitnessCourseHorizontalAdapter;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.asc;
import defpackage.ase;
import defpackage.bzw;
import defpackage.ffy;
import defpackage.fhu;
import defpackage.gge;
import defpackage.ggl;
import defpackage.ggr;
import defpackage.koq;
import defpackage.kwm;
import defpackage.mod;
import defpackage.mof;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class SportResultActivity extends BaseStateActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3323a;
    private Observer b;
    private int c;
    private AiFitnessPlanEnergyReplacementCard d;
    private View e;
    private long f;
    private String j;
    private String l;
    private RecyclerView.Adapter m;
    private HealthSubHeader n;
    private int p;
    private long r;
    private HealthRecycleView t;
    private HealthTextView w;
    private HealthTextView x;
    private LinearLayout y;
    private int u = 1;
    private int i = 0;
    private int s = 0;
    private int q = 0;
    private Handler k = new b();
    private FitnessTopicDeleteModel g = new FitnessTopicDeleteModel();
    private List<String> h = new ArrayList();
    private List<FitWorkout> v = new ArrayList();
    private boolean o = false;

    static /* synthetic */ int b(SportResultActivity sportResultActivity) {
        int i = sportResultActivity.i;
        sportResultActivity.i = i + 1;
        return i;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.activity_sport_result);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sport_result_title_bar);
        customTitleBar.setRightButtonVisibility(8);
        customTitleBar.setTitleBarBackgroundColor(0);
        this.f3323a = (HealthTextView) findViewById(R.id.completed_sport_times);
        this.y = (LinearLayout) findViewById(R.id.stretch_after_run_layout);
        this.w = (HealthTextView) findViewById(R.id.stretch_suggestion);
        this.x = (HealthTextView) findViewById(R.id.stretch_title);
        findViewById(R.id.sug_rpe_great).setOnClickListener(this);
        findViewById(R.id.sug_rpe_good).setOnClickListener(this);
        findViewById(R.id.sug_rpe_ok).setOnClickListener(this);
        findViewById(R.id.sug_rpe_notgood).setOnClickListener(this);
        findViewById(R.id.sug_rpe_great).setSelected(true);
        findViewById(R.id.sug_rpe_notgood).setSelected(true);
        findViewById(R.id.sug_rpe_good).setSelected(true);
        findViewById(R.id.sug_rpe_ok).setSelected(true);
        findViewById(R.id.action_bar_title).setOnClickListener(this);
        this.t = (HealthRecycleView) findViewById(R.id.recyclerView_topic);
        e();
        j();
        if (this.q == 258 && Utils.j()) {
            this.y.setVisibility(0);
        } else if (this.q == 283 && Utils.j()) {
            this.y.setVisibility(0);
            this.x.setText(R.string._2130844500_res_0x7f021b54);
            this.w.setText(R.string._2130844501_res_0x7f021b55);
        }
        d();
        this.e = findViewById(R.id.ai_fitness_plan_card_layout);
        this.d = (AiFitnessPlanEnergyReplacementCard) findViewById(R.id.ai_fitness_plan_energy_replacement_card);
        f();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        g();
    }

    private void d() {
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.SportResultActivity.4
            @Override // java.lang.Runnable
            public void run() {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.b("Suggestion_SportResultActivity", "getCoursePlanId, getCurrentPlan : planApi is null.");
                } else {
                    planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.ui.run.activity.SportResultActivity.4.3
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str) {
                            LogUtil.h("Suggestion_SportResultActivity", "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(IntPlan intPlan) {
                            if (intPlan != null) {
                                SportResultActivity.this.l = intPlan.getPlanId();
                            }
                        }
                    });
                }
            }
        });
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.q = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
            this.q = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
            this.j = intent.getStringExtra("runningCourseId");
            this.f = intent.getLongExtra("endTime", System.currentTimeMillis());
            this.r = intent.getLongExtra("startTime", System.currentTimeMillis());
            this.c = intent.getIntExtra("calories", 0);
            this.p = intent.getIntExtra("plan_execute_time", 0);
            if (this.q == 264) {
                this.q = 258;
            }
        }
        if (intent != null) {
            Bundle bundleExtra = intent.getBundleExtra("bundlekey");
            HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(this);
            this.t.setNestedScrollingEnabled(false);
            int i = this.q;
            if (i == 283) {
                healthLinearLayoutManager.setOrientation(0);
                this.m = new FitnessCourseHorizontalAdapter();
                this.t.a(true);
            } else if (i == 258) {
                this.m = new FitnessTopicRecyAdapter(this.t, 8, bundleExtra);
                this.t.a(false);
                this.t.setPadding((int) getResources().getDimension(R.dimen._2131362625_res_0x7f0a0341), 0, (int) getResources().getDimension(R.dimen._2131362624_res_0x7f0a0340), 0);
            }
            this.t.setLayoutManager(healthLinearLayoutManager);
            this.t.setAdapter(this.m);
        }
    }

    private void j() {
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.sport_result_rpe_sub_header);
        this.n = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        int i = this.q;
        if (i != 283) {
            switch (i) {
                case 257:
                    this.n.setHeadTitleText(getResources().getString(R$string.IDS_hw_sport_result_walk_rpe_name));
                    break;
                case 258:
                    this.n.setHeadTitleText(getResources().getString(R$string.IDS_hw_sport_result_run_rpe_name));
                    break;
                case 259:
                    this.n.setHeadTitleText(getResources().getString(R$string.IDS_hw_sport_result_bike_rpe_name));
                    break;
            }
        }
        this.n.setHeadTitleText(getResources().getString(R$string.IDS_track_skip_rope_feel));
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        b();
        h();
        this.u = this.h.size() + 1;
        i();
        Handler handler = this.k;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = -1;
            obtainMessage.arg1 = this.q;
            this.k.sendMessageDelayed(obtainMessage, 300L);
        }
    }

    private void h() {
        int i = this.q;
        if (i == 258) {
            this.h.add("R002");
        } else if (i == 283) {
            this.h.add("Y045");
            this.h.add("Y047");
            this.h.add("Y049");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.o) {
            LogUtil.a("Suggestion_SportResultActivity", "sportResultActivity already postFeedBack");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.sug_rpe_notgood) {
            this.s = 1;
            a(view.getId());
        } else if (view.getId() == R.id.sug_rpe_ok) {
            this.s = 2;
            a(view.getId());
        } else if (view.getId() == R.id.sug_rpe_good) {
            this.s = 3;
            a(view.getId());
        } else if (view.getId() == R.id.sug_rpe_great) {
            this.s = 4;
            a(view.getId());
        } else {
            this.s = 0;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        Observer observer = new Observer() { // from class: gcm
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                SportResultActivity.this.d(str, objArr);
            }
        };
        this.b = observer;
        ObserverManagerUtil.d(observer, "UPDATE_AI_PLAN_COURSE_RECORD");
    }

    public /* synthetic */ void d(String str, Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            LogUtil.b("Suggestion_SportResultActivity", "args is empty");
            return;
        }
        Object obj = objArr[0];
        if (!(obj instanceof Long)) {
            LogUtil.b("Suggestion_SportResultActivity", "args is not long");
            return;
        }
        long longValue = ((Long) obj).longValue();
        ReleaseLogUtil.e("Suggestion_SportResultActivity", "endTime is ", Long.valueOf(longValue), " & exerciseEndTime is ", Long.valueOf(this.f));
        if (longValue / 1000 == this.f / 1000) {
            c(this.c);
        }
    }

    private void f() {
        if (!ase.f()) {
            LogUtil.a("Suggestion_SportResultActivity", "not support new ai fitness plan");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_SportResultActivity", "getCoursePlanId, getCurrentPlan : planApi is null.");
            return;
        }
        LogUtil.a("Suggestion_SportResultActivity", "endTime is ", Long.valueOf(this.f));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new kwm(this.r, this.f));
        planApi.checkSportRecordOutPlanPunch(new a(), arrayList);
    }

    static final class a extends UiCallback<List<kwm>> {
        private final WeakReference<SportResultActivity> c;

        private a(SportResultActivity sportResultActivity) {
            this.c = new WeakReference<>(sportResultActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Suggestion_SportResultActivity", "errorCode, ", Integer.valueOf(i), " errorInfo, ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<kwm> list) {
            ReleaseLogUtil.e("Suggestion_SportResultActivity", "timeRanges is ", list);
            SportResultActivity sportResultActivity = this.c.get();
            if (sportResultActivity == null) {
                LogUtil.a("Suggestion_SportResultActivity", "activity is null, return");
            } else {
                if (koq.b(list)) {
                    return;
                }
                sportResultActivity.c(sportResultActivity.c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        if (this.d == null || this.e == null) {
            ReleaseLogUtil.c("Suggestion_SportResultActivity", "view is null");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: gcn
                @Override // java.lang.Runnable
                public final void run() {
                    SportResultActivity.this.d(i);
                }
            });
        }
    }

    public /* synthetic */ void d(int i) {
        ggr.d(this.q, 0);
        this.e.setVisibility(0);
        this.d.setCalorieConsumption(i);
        this.d.setJumpBtnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.SportResultActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ggr.d(SportResultActivity.this.q, 1);
                ase.c(8, 1, 1, 1);
                fhu.e().d("SP" + SportResultActivity.this.q);
                JumpUtil.c(SportResultActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new kwm(this.r, this.f));
        ase.d(BaseApplication.getContext(), arrayList);
    }

    private void n() {
        if (TextUtils.isEmpty(this.l) || TextUtils.isEmpty(this.j) || FitnessExternalUtils.e()) {
            LogUtil.b("Suggestion_SportResultActivity", "postFeedback planId or courseId null open：", Boolean.valueOf(FitnessExternalUtils.e()));
            return;
        }
        ReleaseLogUtil.e("Suggestion_SportResultActivity", "postFeedback mStartPlanCourseTime: ", Integer.valueOf(this.p));
        if (((PlanApi) Services.a("CoursePlanService", PlanApi.class)) == null) {
            LogUtil.b("Suggestion_SportResultActivity", "postFeedback : planApi is null.");
            return;
        }
        mof mofVar = new mof();
        mofVar.a(this.l);
        mofVar.c(this.j);
        mofVar.e(this.f);
        mofVar.e(String.valueOf(this.s));
        mofVar.b(ggl.e(this.p));
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit == null) {
            LogUtil.b("Suggestion_SportResultActivity", "postFeedback() getAccountInfo == null");
            return;
        }
        LogUtil.a("Suggestion_SportResultActivity", "postFeedback mSportType:", Integer.valueOf(this.s));
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi != null) {
            sportServiceApi.addUpdateFeedback(loginInit.getUsetId(), mofVar);
        }
    }

    private void a(int i) {
        n();
        if (i == R.id.sug_rpe_notgood) {
            findViewById(R.id.sug_rpe_great).setSelected(false);
            findViewById(R.id.sug_rpe_notgood).setSelected(true);
            findViewById(R.id.sug_rpe_good).setSelected(false);
            findViewById(R.id.sug_rpe_ok).setSelected(false);
        } else if (i == R.id.sug_rpe_ok) {
            findViewById(R.id.sug_rpe_great).setSelected(false);
            findViewById(R.id.sug_rpe_notgood).setSelected(false);
            findViewById(R.id.sug_rpe_good).setSelected(false);
            findViewById(R.id.sug_rpe_ok).setSelected(true);
        } else if (i == R.id.sug_rpe_good) {
            findViewById(R.id.sug_rpe_great).setSelected(false);
            findViewById(R.id.sug_rpe_notgood).setSelected(false);
            findViewById(R.id.sug_rpe_good).setSelected(true);
            findViewById(R.id.sug_rpe_ok).setSelected(false);
        } else if (i == R.id.sug_rpe_great) {
            findViewById(R.id.sug_rpe_great).setSelected(true);
            findViewById(R.id.sug_rpe_notgood).setSelected(false);
            findViewById(R.id.sug_rpe_good).setSelected(false);
            findViewById(R.id.sug_rpe_ok).setSelected(false);
        }
        this.o = true;
    }

    private void b() {
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("Suggestion_SportResultActivity", "requestTotalSportTimes : healthDataMgrApi is null.");
        } else {
            healthDataMgrApi.requestTotalSportTimes(0L, System.currentTimeMillis(), this.q, new IBaseResponseCallback() { // from class: com.huawei.health.suggestion.ui.run.activity.SportResultActivity.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!SportResultActivity.this.isFinishing()) {
                        SportResultActivity.this.c();
                        if (obj == null) {
                            LogUtil.h("Suggestion_SportResultActivity", "getTotalSportTimes onResponse objectData is null");
                            return;
                        }
                        if (koq.e(obj, HiHealthData.class)) {
                            Message obtainMessage = SportResultActivity.this.k.obtainMessage();
                            obtainMessage.what = 0;
                            obtainMessage.obj = obj;
                            SportResultActivity.this.k.sendMessage(obtainMessage);
                            return;
                        }
                        LogUtil.h("Suggestion_SportResultActivity", "getTotalSportTimes onResponse objectData not instanceof List");
                        return;
                    }
                    LogUtil.h("Suggestion_SportResultActivity", "getTotalSportTimes onResponse activity is finishing");
                }
            });
        }
    }

    private void i() {
        if (Utils.j()) {
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h("Suggestion_SportResultActivity", "getWorkoutByWorkoutId : courseApi is null.");
                return;
            }
            for (int i = 0; i < this.h.size(); i++) {
                courseApi.getCourseById(this.h.get(i), null, null, new UiCallback<Workout>() { // from class: com.huawei.health.suggestion.ui.run.activity.SportResultActivity.5
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i2, String str) {
                        if (!SportResultActivity.this.isFinishing()) {
                            SportResultActivity.this.c();
                            LogUtil.h("Suggestion_SportResultActivity", "getCourseById onFailure.");
                            SportResultActivity.b(SportResultActivity.this);
                            return;
                        }
                        LogUtil.h("Suggestion_SportResultActivity", "getCourseById activity is finishing.");
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Workout workout) {
                        if (!SportResultActivity.this.isFinishing()) {
                            SportResultActivity.this.v.add(mod.a(workout));
                            LogUtil.h("Suggestion_SportResultActivity", "getCourseById onSuccess.");
                            SportResultActivity.this.c();
                            return;
                        }
                        LogUtil.h("Suggestion_SportResultActivity", "getCourseById activity is finishing.");
                    }
                });
            }
        }
    }

    static class b extends Handler {
        WeakReference<SportResultActivity> c;

        private b(SportResultActivity sportResultActivity) {
            super(Looper.getMainLooper());
            this.c = new WeakReference<>(sportResultActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("Suggestion_SportResultActivity", "RequestHandler handleMessage msg is null || msg.obj is null.");
                return;
            }
            super.handleMessage(message);
            SportResultActivity sportResultActivity = this.c.get();
            if (sportResultActivity == null) {
                LogUtil.h("Suggestion_SportResultActivity", "RequestHandler handleMessage activity is finishing.");
                return;
            }
            if (message.what == 0 && koq.e(message.obj, HiHealthData.class)) {
                List list = (List) message.obj;
                if (koq.b(list)) {
                    return;
                }
                sportResultActivity.b((HiHealthData) list.get(0));
                return;
            }
            if (message.what == 1) {
                sportResultActivity.m();
            } else if (message.what == -1) {
                bzw.e().showMedalDialog(sportResultActivity, message.arg1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.i == this.h.size()) {
            RecyclerView.Adapter adapter = this.m;
            if (adapter instanceof FitnessTopicRecyAdapter) {
                ((FitnessTopicRecyAdapter) adapter).b(false);
                return;
            }
        }
        RecyclerView.Adapter adapter2 = this.m;
        if (adapter2 instanceof FitnessTopicRecyAdapter) {
            ((FitnessTopicRecyAdapter) adapter2).a(this.g, false, this.v);
        } else if (adapter2 instanceof FitnessCourseHorizontalAdapter) {
            ((FitnessCourseHorizontalAdapter) adapter2).d(this.v);
        }
    }

    public void b(HiHealthData hiHealthData) {
        String b2;
        int i;
        int i2 = this.q;
        if (i2 != 283) {
            switch (i2) {
                case 257:
                    int i3 = hiHealthData.getInt("Track_Walk_Count_Sum");
                    int i4 = hiHealthData.getInt("Track_Walk_Abnormal_Count_Sum");
                    i = i3 > i4 ? i3 - i4 : 0;
                    b2 = ffy.b(R.plurals._2130903276_res_0x7f0300ec, i, Integer.valueOf(i));
                    break;
                case 258:
                    int i5 = hiHealthData.getInt("Track_Run_Count_Sum");
                    int i6 = hiHealthData.getInt("Track_Run_Abnormal_Count_Sum");
                    i = i5 > i6 ? i5 - i6 : 0;
                    b2 = ffy.b(R.plurals._2130903275_res_0x7f0300eb, i, Integer.valueOf(i));
                    break;
                case 259:
                    int i7 = hiHealthData.getInt("Track_Ride_Count_Sum");
                    int i8 = hiHealthData.getInt("Track_Ride_Abnormal_Count_Sum");
                    i = i7 > i8 ? i7 - i8 : 0;
                    b2 = ffy.b(R.plurals._2130903277_res_0x7f0300ed, i, Integer.valueOf(i));
                    break;
                default:
                    b2 = "";
                    break;
            }
        } else {
            int i9 = hiHealthData.getInt("TIME");
            b2 = ffy.b(R.plurals._2130903310_res_0x7f03010e, i9, Integer.valueOf(i9));
        }
        e(b2);
    }

    private void e(String str) {
        SpannableString awT_ = ffy.awT_(getApplicationContext(), "\\d|[/]", str, R.style.completed_sport_times_number_style, R.style.completed_sport_times_content_style);
        if (awT_ != null) {
            this.f3323a.setText(awT_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        synchronized (this) {
            int i = this.u - 1;
            this.u = i;
            LogUtil.a("Suggestion_SportResultActivity", "mWaitCallbackCount", Integer.valueOf(i));
            if (this.u == 0) {
                finishLoading();
                if (this.h.size() == 0) {
                    LogUtil.b("Suggestion_SportResultActivity", "course size is 0");
                } else {
                    Handler handler = this.k;
                    if (handler != null) {
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_SportResultActivity", "doReportBi intent is null.");
            return;
        }
        HashMap hashMap = new HashMap(6);
        hashMap.put("rpe", Integer.valueOf(this.s));
        hashMap.put(BleConstants.SPORT_TYPE, intent.getStringExtra(BleConstants.SPORT_TYPE));
        hashMap.put("avgHeartRate", intent.getStringExtra("avgHeartRate"));
        hashMap.put("avgPace", intent.getStringExtra("avgPace"));
        hashMap.put("distances", intent.getStringExtra("distances"));
        hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, intent.getStringExtra(ParsedFieldTag.NPES_SPORT_TIME));
        hashMap.put("calories", Integer.valueOf(intent.getIntExtra("calories", 0)));
        hashMap.put("isAICourse", Constants.NULL);
        gge.e("1130010", hashMap);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        a();
        ObserverManagerUtil.e(this.b, "UPDATE_AI_PLAN_COURSE_RECORD");
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
