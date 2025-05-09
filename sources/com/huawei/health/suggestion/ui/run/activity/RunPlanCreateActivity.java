package com.huawei.health.suggestion.ui.run.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.service.PlanReportNotificationService;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.ConfirmDistanceFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.PlanDurationFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.QuerySetCompeteDayFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetBestRecordFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetCompeteDateFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.StartTrainDateFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TargetGoalFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TrainDateFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.UserInfoFragment;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.PlanInfoBean;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ary;
import defpackage.asc;
import defpackage.fyc;
import defpackage.gdr;
import defpackage.gge;
import defpackage.ggl;
import defpackage.ggx;
import defpackage.gib;
import defpackage.gij;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.mog;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrt;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class RunPlanCreateActivity extends BaseStateActivity {
    private static String c = c(Locale.getDefault());
    private int aa;
    private String ab;
    private CustomTitleBar ac;
    private TrainDateFragment ad;
    private UserInfoFragment ai;
    private ConfirmDistanceFragment b;
    private Context d;
    private long e;
    private int f;
    private int g;
    private BaseFragment h;
    private List<Integer> j;
    private QuerySetCompeteDayFragment k;
    private ImageView l;
    private ImageView m;
    private HealthTextView n;
    private BroadcastReceiver o;
    private PlanDurationFragment q;
    private String r;
    private StartTrainDateFragment u;
    private SetBestRecordFragment v;
    private TargetGoalFragment w;
    private List<Integer> x;
    private SetCompeteDateFragment y;

    /* renamed from: a, reason: collision with root package name */
    private CreateRunPlanParams f3321a = new CreateRunPlanParams();
    private PlanInfoBean s = new PlanInfoBean();
    private UserInfoBean af = new UserInfoBean();
    private mog z = new mog();
    private boolean i = false;
    private Handler t = new d();
    private OnNextPageListener p = new OnNextPageListener() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.1
        @Override // com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.OnNextPageListener
        public void backLock() {
            RunPlanCreateActivity.this.i = true;
        }

        @Override // com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.OnNextPageListener
        public void nextPage(boolean z) {
            RunPlanCreateActivity.this.b(1, z);
            RunPlanCreateActivity.this.i = false;
        }
    };

    public interface OnNextPageListener {
        void backLock();

        void nextPage(boolean z);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String string;
        String string2;
        super.onCreate(bundle);
        this.d = this;
        this.e = System.currentTimeMillis();
        Resources resources = this.d.getResources();
        if (nrt.a(this.d)) {
            string = resources.getString(R.string._2130851475_res_0x7f023693);
        } else {
            string = resources.getString(R.string._2130851476_res_0x7f023694);
        }
        nrf.cIw_(string, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), this.m);
        if (LanguageUtil.bc(this.d)) {
            this.m.setScaleX(-1.0f);
        }
        if (nrt.a(this.d)) {
            string2 = resources.getString(R.string._2130851471_res_0x7f02368f);
        } else {
            string2 = resources.getString(R.string._2130851472_res_0x7f023690);
        }
        nrf.cIu_(string2, this.l);
        this.o = new BroadcastReceiver() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent != null && "android.intent.action.LOCALE_CHANGED".equals(intent.getAction()) && (context instanceof Activity) && RunPlanCreateActivity.h()) {
                    ((Activity) context).finish();
                    LogUtil.h("Suggestion_RunPlanCreateActivity", "onReceive ACTION_LOCALE_CHANGED finish");
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        registerReceiver(this.o, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h() {
        String c2 = c(Locale.getDefault());
        LogUtil.a("Suggestion_RunPlanCreateActivity", "isLanguageChanged lastLocalStr = ", c, ", currentLocale = ", c2);
        if (c2.equals(c)) {
            return false;
        }
        c = c2;
        return true;
    }

    private static String c(Locale locale) {
        return locale == null ? "" : locale.toString();
    }

    private void i() {
        int intExtra;
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_RunPlanCreateActivity", "intent is null");
            intExtra = -1;
        } else {
            this.ab = intent.getStringExtra("runPlanTitle");
            intExtra = intent.getIntExtra("runPlanType", -1);
            int intExtra2 = intent.getIntExtra("type", 0);
            this.g = intExtra2;
            if (intExtra2 == 1) {
                try {
                    this.r = intent.getStringExtra("planId");
                    this.ab = getResources().getString(R.string._2130848577_res_0x7f022b41);
                    this.aa = intent.getIntExtra("trainLevel", 1);
                    this.x = intent.getIntegerArrayListExtra("runDate");
                    this.j = intent.getIntegerArrayListExtra("exeDate");
                } catch (ArrayIndexOutOfBoundsException e2) {
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "initIntentData:", LogAnonymous.b((Throwable) e2));
                }
            }
        }
        if (intExtra == -1 && this.g != 1) {
            LogUtil.h("Suggestion_RunPlanCreateActivity", "PlanType or mPlanPbType is invalid.");
            finish();
        } else {
            this.s.setPlanType(intExtra);
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        if (isDestroyed()) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "initData parameter has invalid.");
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (this.g == 1) {
            this.f = 8;
            TrainDateFragment trainDateFragment = this.ad;
            this.h = trainDateFragment;
            trainDateFragment.e(this.x, this.j, this.aa);
        } else {
            this.f = 0;
            this.h = this.ai;
        }
        beginTransaction.add(R.id.frag_create_plan_page, this.h).commit();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        i();
        setContentView(R.layout.sug_activity_run_plan_create);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sug_run_plan_title);
        this.ac = customTitleBar;
        customTitleBar.setTitleText(this.ab);
        this.ai = UserInfoFragment.d();
        this.b = ConfirmDistanceFragment.c();
        this.v = SetBestRecordFragment.d(this.s.getPlanType());
        this.u = StartTrainDateFragment.d();
        this.q = PlanDurationFragment.b();
        this.k = QuerySetCompeteDayFragment.b();
        this.y = SetCompeteDateFragment.d();
        this.w = TargetGoalFragment.e(this.s.getPlanType());
        this.ad = TrainDateFragment.a(this.g);
        this.mLoadingView = findViewById(R.id.layout_make_plan);
        this.l = (ImageView) findViewById(R.id.make_plan_back);
        this.n = (HealthTextView) findViewById(R.id.make_plan_text);
        this.m = (ImageView) findViewById(R.id.make_plan_image);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        this.ac.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == null) {
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "mTitleBar LeftButton view is invalid.");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    RunPlanCreateActivity.this.onBackPressed();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.ai.b(this.p);
        this.b.e(this.p);
        this.v.d(this.p);
        this.u.c(this.p);
        this.q.d(this.p);
        this.k.d(this.p);
        this.y.d(this.p);
        this.w.a(this.p);
        this.ad.c(this.p);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.i) {
            LogUtil.a("Suggestion_RunPlanCreateActivity", "onBackPressed playing motion effects.");
        } else {
            b(-1, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, boolean z) {
        if (!CommonUtil.aa(this.d) && i == 1) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "Network is not Connected ");
            nrh.e(this.d, R.string._2130841588_res_0x7f020ff4);
            this.h.onHiddenChanged(false);
            return;
        }
        if (i == 0) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "updateDataAndResetCurrentPage arrow is invalid.");
            return;
        }
        int i2 = this.f;
        if ((i2 == 0 || this.g == 1) && i == -1) {
            finish();
            return;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                this.af.setMonthVolume(this.b.a());
            } else if (i2 != 2) {
                e(z, i);
            } else if (z) {
                this.af.setPbTime(this.v.a());
                this.af.setPbType(this.v.b());
                this.s.setWeekCount(0);
            } else {
                this.af.setPbTime(0);
                this.af.setPbType(0);
            }
        } else {
            if (this.ai.a() == null) {
                LogUtil.b("Suggestion_RunPlanCreateActivity", "updateDataAndResetCurrentPage mUserInfoFragment.getUserInfo() == null");
                return;
            }
            this.af.setSex(gdr.c(this.ai.a().getGender()));
            this.af.setAge(this.ai.a().getAge());
            this.af.setHeight(this.ai.a().getHeight());
            this.af.setWeight((int) this.ai.a().getWeight());
        }
        c(i, z);
    }

    private void e(boolean z, int i) {
        int i2 = this.f;
        if (i2 == 3) {
            this.s.setStartTime(this.u.b() / 1000);
            this.y.c(this.u.b());
            return;
        }
        if (i2 == 4) {
            this.s.setWeekCount(this.q.d());
            this.s.setEndTime(jdl.c(this.u.b(), this.s.getWeekCount() * 7) / 1000);
        } else {
            if (i2 == 5) {
                if (!z) {
                    this.s.setEndTime(0L);
                    e();
                }
                this.s.setIsForRace(z);
                return;
            }
            d(i);
        }
    }

    private void d(int i) {
        int i2 = this.f;
        if (i2 == 6) {
            if (i == 1) {
                this.s.setEndTime(this.y.c() / 1000);
                long i3 = gib.i(this.u.b());
                this.s.setWeekCount((int) Math.ceil((gib.i(this.y.c()) - i3) / 7.0f));
                e();
                return;
            }
            return;
        }
        if (i2 != 7) {
            if (i2 != 8) {
                return;
            }
            this.s.setRunDate(this.ad.d());
            this.s.setExeDate(this.ad.c());
            return;
        }
        if (this.w.b() == 0 && i == 1) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "updateDataAndResetCurrentPage getTargetTime() == 0");
        } else {
            this.s.setTrainLevel(this.w.e());
            this.s.setTargetTime(this.w.b());
        }
    }

    private void e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "getGoalForecast : planApi is null.");
        } else {
            LogUtil.a("Suggestion_RunPlanCreateActivity", "getGoalForecast pbType:", Integer.valueOf(this.af.getPbType()), "pbTime:", Integer.valueOf(this.af.getPbTime()), "planType:", Integer.valueOf(this.s.getPlanType()), "weekCount:", Integer.valueOf(this.s.getWeekCount()));
            planApi.getAchievementForecast(this.af.getPbType(), this.af.getPbTime(), this.s.getPlanType(), this.s.getWeekCount(), new UiCallback<mog>() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "getGoalForecast: ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mog mogVar) {
                    if (mogVar != null) {
                        RunPlanCreateActivity.this.z = mogVar;
                        RunPlanCreateActivity.this.af.setPbBeforePlan(mogVar.b());
                        if (RunPlanCreateActivity.this.h == RunPlanCreateActivity.this.w) {
                            RunPlanCreateActivity.this.w.e(RunPlanCreateActivity.this.z);
                            return;
                        }
                        return;
                    }
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "getGoalForecast onSuccess data is null.");
                }
            });
        }
    }

    private void c(int i, boolean z) {
        int e2 = e(i, z);
        if (e2 == 0) {
            if (this.g == 1) {
                o();
                return;
            } else {
                d();
                return;
            }
        }
        int i2 = this.f + e2;
        this.f = i2;
        if (i2 == 0) {
            d((BaseFragment) this.ai);
            return;
        }
        if (i2 == 1) {
            d((BaseFragment) this.b);
            return;
        }
        if (i2 == 2) {
            d((BaseFragment) this.v);
        } else if (i2 == 3) {
            d((BaseFragment) this.u);
        } else {
            c();
        }
    }

    private void c() {
        switch (this.f) {
            case 4:
                d((BaseFragment) this.q);
                break;
            case 5:
                d((BaseFragment) this.k);
                break;
            case 6:
                d((BaseFragment) this.y);
                this.y.a();
                break;
            case 7:
                d((BaseFragment) this.w);
                this.w.e(this.z);
                break;
            case 8:
                d((BaseFragment) this.ad);
                this.ad.d(this.w.e());
                break;
        }
    }

    private void d() {
        this.s.setTimeZone(ggl.b());
        this.f3321a.setPlanInfoBean(this.s);
        this.f3321a.setUserInfoBean(this.af);
        c(this.f3321a);
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.e;
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("useTime", Long.valueOf(currentTimeMillis - j));
        gge.e("1120022", hashMap);
    }

    private void c(final CreateRunPlanParams createRunPlanParams) {
        showLoading();
        this.ac.setBackgroundColor(this.d.getColor(R.color._2131297873_res_0x7f090651));
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.3
            @Override // java.lang.Runnable
            public void run() {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "updatePlanDate : planApi is null.");
                } else {
                    planApi.createIntelligentRunPlan(createRunPlanParams, new e(RunPlanCreateActivity.this));
                }
            }
        });
    }

    static class d extends Handler {
        WeakReference<RunPlanCreateActivity> d;

        private d(RunPlanCreateActivity runPlanCreateActivity) {
            this.d = new WeakReference<>(runPlanCreateActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String string;
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            RunPlanCreateActivity runPlanCreateActivity = this.d.get();
            if (runPlanCreateActivity == null) {
                return;
            }
            Resources resources = runPlanCreateActivity.d.getResources();
            switch (message.what) {
                case 100:
                    if (nrt.a(runPlanCreateActivity.d)) {
                        string = resources.getString(R.string._2130851477_res_0x7f023695);
                        break;
                    } else {
                        string = resources.getString(R.string._2130851478_res_0x7f023696);
                        break;
                    }
                case 101:
                    if (nrt.a(runPlanCreateActivity.d)) {
                        string = resources.getString(R.string._2130851473_res_0x7f023691);
                        break;
                    } else {
                        string = resources.getString(R.string._2130851474_res_0x7f023692);
                        break;
                    }
                case 102:
                default:
                    LogUtil.b("Suggestion_RunPlanCreateActivity", "msg.what wrong");
                    return;
                case 103:
                    runPlanCreateActivity.j();
                    runPlanCreateActivity.d(message.obj);
                    return;
                case 104:
                    runPlanCreateActivity.mLoadingView.setVisibility(8);
                    runPlanCreateActivity.getSupportFragmentManager().beginTransaction().hide(runPlanCreateActivity.h).show(runPlanCreateActivity.h).commitAllowingStateLoss();
                    return;
            }
            if (!(message.obj instanceof String)) {
                LogUtil.a("Suggestion_RunPlanCreateActivity", "msg.obj null or not List");
                return;
            }
            LogUtil.a("Suggestion_RunPlanCreateActivity", "update ui");
            if (LanguageUtil.bc(runPlanCreateActivity.d)) {
                runPlanCreateActivity.m.setScaleX(1.0f);
            }
            nrf.cIw_(string, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), runPlanCreateActivity.m);
            runPlanCreateActivity.n.setText((String) message.obj);
        }
    }

    static class e extends UiCallback<Plan> {
        WeakReference<RunPlanCreateActivity> b;

        e(RunPlanCreateActivity runPlanCreateActivity) {
            this.b = new WeakReference<>(runPlanCreateActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_RunPlanCreateActivity", "gotoCreatePlan onFailure errorCode:", Integer.valueOf(i), "errorInfo:", str);
            RunPlanCreateActivity runPlanCreateActivity = this.b.get();
            if (runPlanCreateActivity != null && runPlanCreateActivity.t != null) {
                Message obtainMessage = runPlanCreateActivity.t.obtainMessage();
                obtainMessage.what = 101;
                if (i == -404) {
                    obtainMessage.obj = runPlanCreateActivity.d.getString(R.string._2130848486_res_0x7f022ae6);
                } else {
                    if (i == 200010) {
                        obtainMessage.obj = runPlanCreateActivity.d.getString(R.string._2130851553_res_0x7f0236e1);
                        runPlanCreateActivity.t.sendMessage(obtainMessage);
                        runPlanCreateActivity.j();
                        return;
                    }
                    obtainMessage.obj = runPlanCreateActivity.d.getString(R.string._2130839511_res_0x7f0207d7);
                }
                runPlanCreateActivity.t.sendMessage(obtainMessage);
                runPlanCreateActivity.t.sendEmptyMessageDelayed(104, 1000L);
                return;
            }
            LogUtil.h("Suggestion_RunPlanCreateActivity", "CreateUiCallBack onFailure activity or mRequestHandler is null.");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Plan plan) {
            RunPlanCreateActivity runPlanCreateActivity = this.b.get();
            if (runPlanCreateActivity != null && runPlanCreateActivity.t != null) {
                Message obtainMessage = runPlanCreateActivity.t.obtainMessage();
                obtainMessage.what = 100;
                obtainMessage.obj = runPlanCreateActivity.d.getString(R.string._2130848556_res_0x7f022b2c);
                runPlanCreateActivity.t.sendMessage(obtainMessage);
                Message obtainMessage2 = runPlanCreateActivity.t.obtainMessage();
                obtainMessage2.what = 103;
                obtainMessage2.obj = plan;
                runPlanCreateActivity.t.sendMessageDelayed(obtainMessage2, 1000L);
                return;
            }
            LogUtil.b("Suggestion_RunPlanCreateActivity", "CreateUiCallBack onSuccess activity or mRequestHandler is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        g();
        k();
        f();
        finishLoading();
        a();
    }

    private void a() {
        HashMap hashMap = new HashMap(14);
        hashMap.put("click", "1");
        hashMap.put("plantype", 0);
        hashMap.put("planName", this.ab);
        hashMap.put("planUserCheck", true);
        hashMap.put("recentPlanCheck", Integer.valueOf(this.af.getMonthVolume()));
        hashMap.put("bestRecordCheck", Integer.valueOf(this.af.getPbType()));
        hashMap.put("startPlanDate", Long.valueOf(this.s.getStartTime()));
        hashMap.put("checkPrepareCheck", Boolean.valueOf(this.s.getIsForRace()));
        hashMap.put("checkCompetitionDate", Long.valueOf(this.y.c()));
        hashMap.put("checkPredictLevel", Integer.valueOf(this.af.getPbBeforePlan()));
        hashMap.put("checkContinueWeek", Integer.valueOf(this.s.getWeekCount()));
        hashMap.put("checkRunningDay", this.s.getRunDate());
        hashMap.put("checkTrainingDay", this.s.getExeDate());
        hashMap.put("createPlanType", Integer.valueOf(this.s.getPlanType()));
        gge.e("1120021", hashMap);
    }

    private void g() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "createPlanSuccess, updateRemindTime : planApi is null.");
        } else {
            planApi.setPlanType(0);
            planApi.updateRemindTime(true, 1080);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        if (!(obj instanceof Plan)) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "objPlan pushDataToDevice is wrong");
        } else if (ggx.a() && fyc.d() && gij.d()) {
            LogUtil.h("Suggestion_RunPlanCreateActivity", "initPushToWatchView bluetooth is disable");
            fyc.b(((PlanApi) Services.c("CoursePlanService", PlanApi.class)).convertRunPlan((Plan) obj), BaseApplication.getContext());
        }
    }

    private void k() {
        LogUtil.a("Suggestion_RunPlanCreateActivity", "startPlanReportRemindService.");
        this.d.startService(new Intent(this.d, (Class<?>) PlanReportNotificationService.class));
    }

    private void f() {
        ary.a().e("PLAN_UPDATE");
        JumpUtil.c(this);
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", "1");
        hashMap.put("planName", this.ab);
        hashMap.put("planType", 0);
        hashMap.put("enter", "5");
        gge.e("1120024", hashMap);
        finish();
    }

    private void o() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "updatePlanDate : planApi is null.");
            return;
        }
        planApi.updatePlanDate(this.r, this.s.getRunDate(), this.s.getExeDate(), new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_RunPlanCreateActivity", "updatePlanDate errorInfo:", str);
                nrh.b(RunPlanCreateActivity.this.d, R.string._2130844736_res_0x7f021c40);
                RunPlanCreateActivity.this.finish();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_RunPlanCreateActivity", "updatePlanDate onSuccess:", str);
                if ("0".equals(str)) {
                    nrh.b(RunPlanCreateActivity.this.d, R.string._2130844735_res_0x7f021c3f);
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("click", 1);
                    hashMap.put("type", 2);
                    ixx.d().d(RunPlanCreateActivity.this.d, AnalyticsValue.INT_PLAN_1120045.value(), hashMap, 0);
                } else {
                    nrh.b(RunPlanCreateActivity.this.d, R.string._2130844736_res_0x7f021c40);
                }
                RunPlanCreateActivity.this.finish();
            }
        });
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", "1");
        hashMap.put("planType", 0);
        hashMap.put("createPlanType", Integer.valueOf(this.s.getPlanType()));
        hashMap.put("checkRunningDay", this.s.getRunDate());
        hashMap.put("checkTrainingDay", this.s.getExeDate());
        gge.e("1120026", hashMap);
    }

    private void d(BaseFragment baseFragment) {
        if (baseFragment == null) {
            LogUtil.b("Suggestion_RunPlanCreateActivity", "switchFragment fragment is null.");
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (this.h != baseFragment) {
            if (!baseFragment.isAdded()) {
                beginTransaction.hide(this.h).add(R.id.frag_create_plan_page, baseFragment).commit();
            } else {
                beginTransaction.hide(this.h).show(baseFragment).commit();
            }
        }
        this.h = baseFragment;
    }

    private int e(int i, boolean z) {
        int i2 = this.f;
        if (i2 == 3 && i == 1) {
            int planType = this.s.getPlanType();
            if (planType == 2 || planType == 11) {
                return 5;
            }
            if (planType != 12) {
                return 2;
            }
            return i;
        }
        if (i2 == 4 && i == 1 && this.s.getPlanType() == 12) {
            return 4;
        }
        return a(i, z);
    }

    private int a(int i, boolean z) {
        int i2 = this.f;
        if (i2 == 5 && i == 1 && !z) {
            return 2;
        }
        if (i2 == 5 && i == -1) {
            return -2;
        }
        if (i2 == 7 && i == -1) {
            return -2;
        }
        return c(i);
    }

    private int c(int i) {
        int i2 = this.f;
        if (i2 == 8 && i == 1) {
            return 0;
        }
        if (i2 != 8 || i != -1) {
            return i;
        }
        int planType = this.s.getPlanType();
        if (planType == 2 || planType == 11) {
            return -5;
        }
        if (planType != 12) {
            return i;
        }
        return -4;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Suggestion_RunPlanCreateActivity", "onDestroy");
        BroadcastReceiver broadcastReceiver = this.o;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
            this.o = null;
        }
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.t = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
