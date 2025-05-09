package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.motiontrack.api.ViewHolderBase;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.CardGuideViewModel;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sport.view.AiFitnessPlanEnergyReplacementCard;
import com.huawei.health.sport.view.CalorieConsumptionGuideCard;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessResultInteractor;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.ui.fitness.module.HeartRateLineChartHolderImpl;
import com.huawei.health.suggestion.ui.run.adapter.FitnessCourseHorizontalAdapter;
import com.huawei.health.suggestion.ui.view.ActionRadarView;
import com.huawei.health.suggestion.ui.view.ActionRingChartView;
import com.huawei.health.suggestion.ui.view.AutoFillColorView;
import com.huawei.health.suggestion.ui.view.ScorePersentView;
import com.huawei.health.suggestion.ui.view.StrechMuscleView;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutAction;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import defpackage.arx;
import defpackage.asc;
import defpackage.ase;
import defpackage.bzw;
import defpackage.caj;
import defpackage.ehq;
import defpackage.eme;
import defpackage.ffw;
import defpackage.ffy;
import defpackage.fgd;
import defpackage.fhp;
import defpackage.fhu;
import defpackage.fis;
import defpackage.fnt;
import defpackage.fqc;
import defpackage.fqq;
import defpackage.fqt;
import defpackage.gge;
import defpackage.ggm;
import defpackage.ggr;
import defpackage.ggt;
import defpackage.gic;
import defpackage.gnp;
import defpackage.grz;
import defpackage.jdx;
import defpackage.koq;
import defpackage.kwm;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.mof;
import defpackage.moj;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FitnessResultActivity extends BaseStateActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static int f3086a;
    private static final Map<Integer, Integer> c = new HashMap<Integer, Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.4
        {
            put(0, 33);
            put(137, 53);
            put(283, 54);
        }
    };
    private String aa;
    private HealthRecycleView ab;
    private CustomTitleBar ac;
    private CustomAlertDialog ad;
    private int ae;
    private HealthTextView af;
    private HealthTextView ag;
    private LinearLayout ah;
    private LinearLayout aj;
    private List<HeartRateData> am;
    private View an;
    private boolean ao;
    private LinearLayout au;
    private boolean aw;
    private int ax;
    private List<RecordAction> ay;
    private List<FitWorkout> az;
    private View b;
    private int ba;
    private WorkoutRecord bb;
    private long bc;
    private LinearLayout bd;
    private LinearLayout be;
    private LinearLayout bf;
    private LinearLayout bg;
    private int bj;
    private HealthTextView bk;
    private RelativeLayout bm;
    private float bn;
    private float bo;
    private HealthTextView bp;
    private float bq;
    private SharedPreferences br;
    private HealthTextView bs;
    private String bt;
    private HealthTextView bu;
    private long bv;
    private AiFitnessPlanEnergyReplacementCard d;
    private LinearLayout e;
    private AutoFillColorView f;
    private CalorieConsumptionGuideCard g;
    private Bundle h;
    private CardGuideViewModel i;
    private Observer j;
    private HealthTextView k;
    private LinearLayout l;
    private HealthRecycleView m;
    private HealthTextView n;
    private View o;
    private HealthTextView p;
    private ImageView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private LinearLayout v;
    private HealthTextView w;
    private Context x;
    private HealthTextView y;
    private int z;
    private long bi = 0;
    private int bh = 0;
    private boolean at = false;
    private boolean aq = true;
    private volatile boolean av = true;
    private FitnessResultInteractor ar = new FitnessResultInteractor(BaseApplication.getContext());
    private FitnessCourseHorizontalAdapter ap = new FitnessCourseHorizontalAdapter();
    private ViewHolderBase bw = null;
    private int bl = 100;
    private int ak = 0;
    private int al = 1;
    private boolean as = false;
    private e ai = new e(this);

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public int getLoadingLayoutId() {
        return R.layout.sug_loading_layout;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_fitness_coach_trainfinish);
    }

    /* loaded from: classes4.dex */
    static class e extends BaseHandler<FitnessResultActivity> {
        e(FitnessResultActivity fitnessResultActivity) {
            super(fitnessResultActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: azl_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(FitnessResultActivity fitnessResultActivity, Message message) {
            if (message == null) {
                LogUtil.a("Suggestion_FitnessResultActivity", "message is null");
                return;
            }
            int i = message.what;
            if (i == -1) {
                bzw.e().showMedalDialog(fitnessResultActivity, 0);
            } else {
                if (i == 106) {
                    if (message.obj instanceof int[]) {
                        fitnessResultActivity.b((int[]) message.obj);
                        return;
                    }
                    return;
                }
                LogUtil.h("Suggestion_FitnessResultActivity", "FitnessResultHandler is wrong");
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.5
            @Override // java.lang.Runnable
            public void run() {
                FitnessResultActivity.this.updateViewController();
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e("R_Suggestion_FitnessResultActivity", "onCreate");
        this.x = this;
        p();
        this.ac.setRightButtonOnClickListener(new View.OnClickListener() { // from class: fku
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessResultActivity.this.azi_(view);
            }
        });
        g();
        l();
        grz.c("Suggestion_FitnessResultActivity");
        this.i = (CardGuideViewModel) new ViewModelProvider(this).get(CardGuideViewModel.class);
    }

    public /* synthetic */ void azi_(View view) {
        if (PermissionUtil.c()) {
            m();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.x, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new f());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void l() {
        Observer observer = new Observer() { // from class: fkz
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                FitnessResultActivity.this.c(str, objArr);
            }
        };
        this.j = observer;
        ObserverManagerUtil.d(observer, "UPDATE_AI_PLAN_COURSE_RECORD");
    }

    public /* synthetic */ void c(String str, Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            LogUtil.b("Suggestion_FitnessResultActivity", "args is empty");
            return;
        }
        Object obj = objArr[0];
        if (!(obj instanceof Long)) {
            LogUtil.b("Suggestion_FitnessResultActivity", "args is not long");
            return;
        }
        if (this.bb == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "record is null");
            return;
        }
        long longValue = ((Long) obj).longValue();
        ReleaseLogUtil.e("Suggestion_FitnessResultActivity", "endTime is ", Long.valueOf(longValue), " & exerciseEndTime is ", Long.valueOf(this.bb.acquireExerciseTime()));
        if (longValue / 1000 == this.bb.acquireExerciseTime() / 1000) {
            i(this.bb);
        }
    }

    private void g() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        azc_(intent);
        WorkoutRecord workoutRecord = this.bb;
        if (workoutRecord != null) {
            this.as = workoutRecord.isFitnessRecordFromDevice() || this.bb.isFitnessRecordFromTv();
            this.bv = this.bb.startTime();
            this.ay = e(this.bb.acquireActionSummary());
            this.am = this.bb.acquireHeartRateDataList();
            this.z = this.bb.acquireWearType();
            this.bt = this.bb.acquireWorkoutId();
            if (this.bb.isFitnessRecordFromTv()) {
                this.ba = this.bb.acquireProductId();
            }
            n();
            j(this.bb);
        }
        o();
        azh_(intent);
        s();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        WorkoutRecord workoutRecord;
        super.onWindowFocusChanged(z);
        ReleaseLogUtil.e("R_Suggestion_FitnessResultActivity", "onWindowFocusChanged isHasFocus:", Boolean.valueOf(z));
        if (z && this.aq && (workoutRecord = this.bb) != null) {
            if (this.at) {
                ehq.b().showMarketCommentDialog(this.x);
                l(this.bb);
                fhp.c().onChange(this.bb.acquireWorkoutId(), 1, null);
                e eVar = this.ai;
                if (eVar != null) {
                    eVar.sendEmptyMessageDelayed(-1, 300L);
                }
            } else {
                a(workoutRecord);
            }
            this.aq = false;
        }
    }

    private void o() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.getCourseTrainStatistics(1, new d(this));
        }
    }

    private void n() {
        if (this.ay == null) {
            this.ay = Collections.EMPTY_LIST;
        }
        int i2 = 0;
        for (RecordAction recordAction : this.ay) {
            if (recordAction != null) {
                int finishedAction = recordAction.getFinishedAction();
                if (CommonUtil.c(finishedAction - recordAction.getActionTargetValue())) {
                    i2++;
                }
                LogUtil.a("Suggestion_FitnessResultActivity", "finishAction:", Integer.valueOf(finishedAction), " TargetType:", Integer.valueOf(recordAction.getActionTargetType()), " TargetValue:", Float.valueOf(recordAction.getActionTargetValue()));
            }
        }
        this.ae = i2;
        if (this.ay.size() == 1 && i2 == 0) {
            this.ae = 1;
        }
    }

    private void azh_(Intent intent) {
        if (intent.getIntExtra("entrance", 0) == 0) {
            MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
            if (marketingApi == null) {
                LogUtil.a("Suggestion_FitnessResultActivity", "marketingApi is null");
                return;
            }
            e(marketingApi);
            a(marketingApi, i());
            a(marketingApi, 34);
            a(marketingApi, 61);
        }
        e();
    }

    private void azc_(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("bundlekey");
            this.h = bundleExtra;
            if (bundleExtra != null) {
                this.aw = bundleExtra.getBoolean("isshowbutton");
                this.bj = this.h.getInt("track_type");
            }
            Bundle aPD_ = gnp.aPD_(intent.getStringExtra("fitness"));
            if (aPD_ != null) {
                try {
                    this.bb = (WorkoutRecord) HiJsonUtil.e(aPD_.getString("workout_record"), WorkoutRecord.class);
                    gnp.d("fitness", this.x, -1L);
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("Suggestion_FitnessResultActivity", "parse WorkoutRecord error");
                }
            } else {
                this.bb = (WorkoutRecord) intent.getParcelableExtra("workout_record");
            }
            this.bc = intent.getLongExtra("plan_execute_time", 0L);
            this.at = intent.getBooleanExtra("is_show_rpe", false);
        } catch (Exception e2) {
            LogUtil.h("Suggestion_FitnessResultActivity", LogAnonymous.b((Throwable) e2));
        }
    }

    private void a(MarketingApi marketingApi, int i2) {
        LogUtil.a("Suggestion_FitnessResultActivity", "triggerMarketingEvent: ", Integer.valueOf(i2));
        MarketingOption.Builder builder = new MarketingOption.Builder();
        builder.setContext(this.x);
        if (i2 == 34 || i2 == 61) {
            HashMap hashMap = new HashMap();
            hashMap.put("fitness_finish_training", this.bt);
            builder.setTriggerEventParams(hashMap);
            LogUtil.a("Suggestion_FitnessResultActivity", "marketingApi mWorkoutId: ", this.bt);
        }
        builder.setPageId(260);
        builder.setTypeId(i2);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    private int i() {
        WorkoutRecord workoutRecord = this.bb;
        Integer num = c.get(Integer.valueOf(workoutRecord != null ? workoutRecord.acquireCategory() : 0));
        if (num != null) {
            return num.intValue();
        }
        return 33;
    }

    private void e(MarketingApi marketingApi) {
        marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this.x).setPageId(260).build());
    }

    protected void e() {
        MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4158);
        resourceResultInfo.addOnSuccessListener(new a(this, marketingApi));
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: fks
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FitnessResultActivity.b(exc);
            }
        });
    }

    public static /* synthetic */ void b(Exception exc) {
        Object[] objArr = new Object[2];
        objArr[0] = "initMarketing onFailure ";
        objArr[1] = exc == null ? "" : exc.getMessage();
        LogUtil.b("Suggestion_FitnessResultActivity", objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MarketingApi marketingApi, Map<Integer, ResourceResultInfo> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("fitness_finish_training", this.bt);
        LogUtil.a("Suggestion_FitnessResultActivity", "marketingApi mWorkoutId: ", this.bt);
        MarketingOption build = new MarketingOption.Builder().setContext(com.huawei.haf.application.BaseApplication.e()).setTypeId(400).setResultInfoMap(map).setTriggerEventParams(hashMap).build();
        if (marketingApi == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "resourceInfo marketingApi == null");
            this.av = false;
            aa();
            return;
        }
        List<View> marketingViewList = marketingApi.getMarketingViewList(this, marketingApi.filterMarketingRules(build));
        if (koq.b(marketingViewList)) {
            LogUtil.a("Suggestion_FitnessResultActivity", "market list is null");
            this.av = false;
            aa();
            return;
        }
        this.av = true;
        LinearLayout linearLayout = this.v;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        for (View view : marketingViewList) {
            LinearLayout linearLayout2 = this.au;
            if (linearLayout2 != null) {
                linearLayout2.addView(view);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = "mMarketingLayout == null";
        objArr[1] = Boolean.valueOf(this.au == null);
        LogUtil.a("Suggestion_FitnessResultActivity", objArr);
    }

    /* loaded from: classes4.dex */
    static class a implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<FitnessResultActivity> f3087a;
        private final MarketingApi b;

        a(FitnessResultActivity fitnessResultActivity, MarketingApi marketingApi) {
            this.f3087a = new WeakReference<>(fitnessResultActivity);
            this.b = marketingApi;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            FitnessResultActivity fitnessResultActivity = this.f3087a.get();
            if (fitnessResultActivity != null) {
                fitnessResultActivity.e(this.b, map);
            } else {
                LogUtil.h("Suggestion_FitnessResultActivity", "TackOnSuccessListener activity == null");
            }
        }
    }

    private void c(Map<String, String> map) {
        WorkoutRecord workoutRecord = this.bb;
        if (workoutRecord == null) {
            return;
        }
        List<RecordAction> e2 = e(workoutRecord.acquireActionSummary());
        this.ay = e2;
        for (RecordAction recordAction : e2) {
            if (recordAction != null && StringUtils.i(recordAction.getActionId()) && recordAction.getActionId().equals(recordAction.getActionName())) {
                recordAction.setActionName(map.get(recordAction.getActionId()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<ChoreographedMultiActions> list) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        HashMap hashMap = new HashMap();
        ArrayList<ChoreographedSingleAction> arrayList = new ArrayList();
        for (ChoreographedMultiActions choreographedMultiActions : list) {
            if (choreographedMultiActions != null && !koq.b(choreographedMultiActions.getActionList())) {
                arrayList.addAll(choreographedMultiActions.getActionList());
            }
        }
        for (ChoreographedSingleAction choreographedSingleAction : arrayList) {
            if (choreographedSingleAction != null && choreographedSingleAction.getAction() != null) {
                hashMap.put(choreographedSingleAction.getAction().getId(), choreographedSingleAction.getAction().getName());
            }
        }
        c(hashMap);
        ac();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<WorkoutAction> list) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (WorkoutAction workoutAction : list) {
            if (workoutAction != null && workoutAction.getAction() != null) {
                hashMap.put(workoutAction.getAction().getId(), workoutAction.getAction().getName());
            }
        }
        c(hashMap);
        n();
        ac();
    }

    private void y() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(arx.b());
        linearLayoutManager.setOrientation(0);
        this.ab.setLayoutManager(linearLayoutManager);
        this.ab.setAdapter(this.ap);
        setViewSafeRegion(false, this.ab);
    }

    private void p() {
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.sug_fitness_scroll_view);
        cancelLayoutById(healthScrollView);
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        this.bs = (HealthTextView) findViewById(R.id.sug_coachf_tv_congra);
        this.q = (ImageView) findViewById(R.id.sug_coachf_iv_close);
        this.w = (HealthTextView) findViewById(R.id.sug_coachf_tv_rate);
        this.u = (HealthTextView) findViewById(R.id.sug_coachf_tv_rate_title);
        this.r = (HealthTextView) findViewById(R.id.sug_coachf_tv_duration);
        this.p = (HealthTextView) findViewById(R.id.sug_coachf_tv_calorie);
        this.t = (HealthTextView) findViewById(R.id.sug_coach_calorie_unit);
        this.s = (HealthTextView) findViewById(R.id.sug_coach_calorie_unit2);
        this.n = (HealthTextView) findViewById(R.id.sug_coachf_tv_active_calorie);
        this.k = (HealthTextView) findViewById(R.id.sug_coach_active_calorie_unit);
        this.af = (HealthTextView) findViewById(R.id.sug_coachf_tv_level);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.sug_coachf_rcv_actions);
        this.m = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        this.m.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.q.setOnClickListener(this);
        this.bm = (RelativeLayout) findViewById(R.id.sug_rl_startrunaftwarmup);
        this.bk = (HealthTextView) findViewById(R.id.sug_startrunaftwarmup);
        this.ac = (CustomTitleBar) findViewById(R.id.sug_fitness_title);
        this.f = (AutoFillColorView) findViewById(R.id.auto_fill_color_view);
        this.y = (HealthTextView) findViewById(R.id.sug_course_tv_duration);
        this.bu = (HealthTextView) findViewById(R.id.sug_coachf_train_time);
        this.l = (LinearLayout) findViewById(R.id.sug_coachf_calories_ll);
        this.o = findViewById(R.id.sug_coachf_space);
        this.e = (LinearLayout) findViewById(R.id.sug_coachf_active_calories_ll);
        this.b = findViewById(R.id.sug_active_coachf_space);
        this.v = (LinearLayout) findViewById(R.id.sug_detail_info_re_course_layout);
        this.ab = (HealthRecycleView) findViewById(R.id.sug_recycleview_relative_course);
        this.bp = (HealthTextView) findViewById(R.id.sug_coach_train_times);
        this.aj = (LinearLayout) findViewById(R.id.sug_coachf_rate_ll);
        this.ah = (LinearLayout) findViewById(R.id.sug_fitness_tv_result_display);
        this.ag = (HealthTextView) findViewById(R.id.guide_user_share_content);
        this.au = (LinearLayout) findViewById(R.id.my_course_marketing_layout);
        this.ag.setOnClickListener(this);
        if (LanguageUtil.bc(this)) {
            this.ac.setRightButtonDrawable(nrz.cKn_(this, R$drawable.ic_health_nav_share_black), nsf.h(R$string.accessibility_share));
        }
        setViewSafeRegion(true, this.af, this.bs, this.bp);
        setViewSafeRegion(true, findViewById(R.id.sug_coach_train_result_layout));
        setViewSafeRegion(true, findViewById(R.id.sug_fitness_result_action_completed_tv));
        setViewSafeRegion(false, this.aj);
        setViewSafeRegion(true, findViewById(R.id.sug_fitness_result_relative_course_header_tv));
        e(this.r, this.p, this.w, this.n);
        y();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        FitnessResultInteractor fitnessResultInteractor = this.ar;
        if (fitnessResultInteractor != null) {
            fitnessResultInteractor.b();
            this.ar = null;
        }
        CardGuideViewModel cardGuideViewModel = this.i;
        if (cardGuideViewModel != null) {
            cardGuideViewModel.a(this);
        }
        ObserverManagerUtil.e(this.j, "UPDATE_AI_PLAN_COURSE_RECORD");
    }

    /* loaded from: classes4.dex */
    static class d extends UiCallback<TrainStatistics> {
        private WeakReference<FitnessResultActivity> b;

        d(FitnessResultActivity fitnessResultActivity) {
            this.b = null;
            this.b = new WeakReference<>(fitnessResultActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_FitnessResultActivity", "getTotalTimeAndCalorie:", "errorCode=", Integer.valueOf(i), "   errorInfo=", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(TrainStatistics trainStatistics) {
            FitnessResultActivity fitnessResultActivity = this.b.get();
            if (fitnessResultActivity == null || trainStatistics == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "TrainStatisticsUiCallback onSuccess mTrainDetail is null");
                return;
            }
            LogUtil.c("Suggestion_FitnessResultActivity", "getTotalTimeAndCalorie success data = ", trainStatistics);
            fitnessResultActivity.bi = trainStatistics.acquireDuration();
            LogUtil.a("Suggestion_FitnessResultActivity", "getTotalTimeAndCalorie mTotalTime = ", Long.valueOf(fitnessResultActivity.bi));
        }
    }

    private void a(final WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: fkr
            @Override // java.lang.Runnable
            public final void run() {
                FitnessResultActivity.this.b(workoutRecord);
            }
        });
    }

    public /* synthetic */ void b(WorkoutRecord workoutRecord) {
        HashMap hashMap = new HashMap(10);
        if (gge.c()) {
            hashMap.put("date", Long.valueOf(workoutRecord.acquireExerciseTime()));
        }
        hashMap.put("workout_name", workoutRecord.acquireWorkoutName());
        hashMap.put("workout_id", workoutRecord.acquireWorkoutId());
        hashMap.put("isAICourse", ggr.d(workoutRecord));
        c((Map<String, Object>) hashMap, true);
        gge.e("1130013", hashMap);
        LogUtil.c("Suggestion_FitnessResultActivity", "Course history:", hashMap.toString());
    }

    private void l(WorkoutRecord workoutRecord) {
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("Suggestion_FitnessResultActivity", "this is Destroyed");
            return;
        }
        View inflate = View.inflate(getApplicationContext(), R.layout.sug_fitness_rpe_dialog, null);
        this.bd = (LinearLayout) inflate.findViewById(R.id.sug_rpe_notgood);
        this.d = (AiFitnessPlanEnergyReplacementCard) inflate.findViewById(R.id.ai_fitness_plan_card_layout);
        this.g = (CalorieConsumptionGuideCard) inflate.findViewById(R.id.calorie_consumption_dialog);
        this.bg = (LinearLayout) inflate.findViewById(R.id.sug_rpe_ok);
        this.bf = (LinearLayout) inflate.findViewById(R.id.sug_rpe_good);
        this.be = (LinearLayout) inflate.findViewById(R.id.sug_rpe_great);
        this.bd.setOnClickListener(this);
        this.bg.setOnClickListener(this);
        this.bf.setOnClickListener(this);
        this.be.setOnClickListener(this);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.x);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        builder.e(0, 0);
        builder.cyp_(inflate);
        CustomAlertDialog c2 = builder.c();
        this.ad = c2;
        c2.show();
        this.ad.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                FitnessResultActivity fitnessResultActivity = FitnessResultActivity.this;
                fitnessResultActivity.e(fitnessResultActivity.bb);
            }
        });
        o(workoutRecord);
        this.i.d(workoutRecord);
        this.g.setCustomViewDialog(this.ad);
        this.g.e(this, this.i);
    }

    private void o(WorkoutRecord workoutRecord) {
        if (!ase.f()) {
            ReleaseLogUtil.e("Suggestion_FitnessResultActivity", "not support new ai fitness plan");
            return;
        }
        if (workoutRecord == null) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "record is null, return");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "getCoursePlanId, getCurrentPlan : planApi is null.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new kwm(workoutRecord.startTime(), workoutRecord.acquireExerciseTime()));
        planApi.checkSportRecordOutPlanPunch(new c(), arrayList);
    }

    /* loaded from: classes4.dex */
    static final class c extends UiCallback<List<kwm>> {
        private final WeakReference<FitnessResultActivity> e;

        private c(FitnessResultActivity fitnessResultActivity) {
            this.e = new WeakReference<>(fitnessResultActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "errorCode, ", Integer.valueOf(i), " errorInfo, ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<kwm> list) {
            ReleaseLogUtil.e("Suggestion_FitnessResultActivity", "timeRanges is ", list);
            FitnessResultActivity fitnessResultActivity = this.e.get();
            if (fitnessResultActivity == null) {
                ReleaseLogUtil.e("Suggestion_FitnessResultActivity", "activity is null, return");
            } else if (!koq.b(list)) {
                fitnessResultActivity.i(fitnessResultActivity.bb);
            } else {
                ReleaseLogUtil.e("Suggestion_FitnessResultActivity", "data is empty, return");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "record is null");
        } else if (this.d == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "mAiFitnessPlanEnergyReplacementCard is null");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: fkp
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessResultActivity.this.d(workoutRecord);
                }
            });
        }
    }

    public /* synthetic */ void d(WorkoutRecord workoutRecord) {
        WorkoutRecord workoutRecord2 = this.bb;
        if (workoutRecord2 != null) {
            ggr.d(workoutRecord2.acquireCategory(), 0);
        }
        this.d.setVisibility(0);
        this.d.setCalorieConsumption(workoutRecord.acquireActualCalorie());
        this.d.setJumpBtnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FitnessResultActivity.this.bb != null) {
                    ggr.d(FitnessResultActivity.this.bb.acquireCategory(), 1);
                    ase.c(8, 1, 1, 1);
                    fhu.e().d("SP" + FitnessResultActivity.this.bb.acquireCategory());
                }
                JumpUtil.c(FitnessResultActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new kwm(workoutRecord.startTime(), workoutRecord.acquireExerciseTime()));
        ase.d(BaseApplication.getContext(), arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("workout_id", workoutRecord.acquireWorkoutId());
        hashMap.put("workout_name", workoutRecord.acquireWorkoutName());
        hashMap.put("version", workoutRecord.acquireVersion());
        if (gge.c()) {
            hashMap.put("finishRate", gge.c(workoutRecord.acquireFinishRate()));
        }
        hashMap.put("rpe", Integer.valueOf(this.bh));
        hashMap.put("calories", Integer.valueOf((int) workoutRecord.acquireActualCalorie()));
        hashMap.put("isAICourse", ggr.d(workoutRecord));
        c((Map<String, Object>) hashMap, false);
        gge.e("1130010", hashMap);
        LogUtil.c("Suggestion_FitnessResultActivity", "BI_rpe：", hashMap.toString());
    }

    private void c(Map<String, Object> map, boolean z) {
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi == null) {
            return;
        }
        FitWorkout workout = sportServiceApi.getWorkout(this.bb.acquireWorkoutId());
        if (workout == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "getWorkout null.", this.bb.acquireWorkoutId());
            return;
        }
        String a2 = ffy.a(workout.acquireClasses());
        if (TextUtils.isEmpty(a2)) {
            a2 = ffy.c(workout.getPrimaryClassify());
        }
        map.put("type", a2);
        map.put("train_points", c(workout));
        map.put("difficulty", Integer.valueOf(workout.acquireDifficulty()));
        if (z) {
            map.put("duration", Integer.valueOf(workout.acquireDuration()));
        }
    }

    private String c(FitWorkout fitWorkout) {
        StringBuilder sb = new StringBuilder();
        if (koq.b(fitWorkout.acquireTrainingpoints())) {
            return sb.toString();
        }
        for (Attribute attribute : fitWorkout.acquireTrainingpoints()) {
            if (attribute != null) {
                sb.append(attribute.getName());
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.sug_coachf_iv_close) {
            finish();
        } else if (view.getId() == R.id.sug_startrunaftwarmup) {
            if (this.aw) {
                ab();
            } else if (this.bb.isRunWorkout()) {
                z();
            }
        } else if (view.getId() == R.id.sug_rpe_notgood) {
            this.bh = 1;
            t();
            this.ad.dismiss();
        } else if (view.getId() == R.id.sug_rpe_ok) {
            this.bh = 2;
            t();
            this.ad.dismiss();
        } else if (view.getId() == R.id.sug_rpe_good) {
            this.bh = 3;
            t();
            this.ad.dismiss();
        } else if (view.getId() == R.id.sug_rpe_great) {
            this.bh = 4;
            t();
            this.ad.dismiss();
        } else if (view.getId() == R.id.guide_user_share_content) {
            k();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        WorkoutRecord workoutRecord = this.bb;
        if (workoutRecord == null || TextUtils.isEmpty(workoutRecord.acquirePlanId()) || TextUtils.isEmpty(this.bb.acquireWorkoutId()) || !FitnessExternalUtils.a()) {
            LogUtil.b("Suggestion_FitnessResultActivity", "postFeedback planId or courseId or mRecord null");
            return;
        }
        if (((PlanApi) Services.a("CoursePlanService", PlanApi.class)) == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "postFeedback : planApi is null.");
            return;
        }
        mof mofVar = new mof();
        mofVar.a(this.bb.acquirePlanId());
        mofVar.c(this.bb.acquireWorkoutId());
        mofVar.e(this.bb.acquireExerciseTime());
        mofVar.e(String.valueOf(this.bh));
        mofVar.b(this.bc);
        LogUtil.a("Suggestion_FitnessResultActivity", "postFeedback planTime ", Long.valueOf(this.bc), " exTime ", Long.valueOf(this.bb.acquireExerciseTime()));
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "postFeedback() getAccountInfo == null");
            return;
        }
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi != null) {
            sportServiceApi.addUpdateFeedback(loginInit.getUsetId(), mofVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        k();
        Bundle bundle = new Bundle();
        bundle.putLong("exercise_current_time", this.bv);
        bundle.putInt("levels_count", this.ax);
        bundle.putFloat("train_duration", this.bq);
        bundle.putFloat("calorie", this.bo);
        bundle.putFloat("percent", this.bn);
        bundle.putCharSequence("train_name", this.aa);
        bundle.putLong("exercise_total_time", this.bi);
        bundle.putInt("action_count", this.ae);
        bundle.putInt("exercise_count", f3086a);
        bundle.putInt(DeviceCategoryFragment.DEVICE_TYPE, this.z);
        if (getIntent() != null) {
            bundle.putInt("entrance", getIntent().getIntExtra("entrance", 0));
        }
        WorkoutRecord workoutRecord = this.bb;
        if (workoutRecord != null) {
            bundle.putString("workoutid", workoutRecord.acquireWorkoutId());
            if (this.bb.isFitnessRecordFromTv()) {
                bundle.putInt("product_id", this.ba);
            }
            bundle.putString("isAICourse", ggr.d(this.bb));
        }
        this.ar.aAN_(this, bundle);
    }

    private void z() {
        String acquireRunWorkoutTrajectoryId = this.bb.acquireRunWorkoutTrajectoryId();
        LogUtil.a("Suggestion_FitnessResultActivity", "showTrainDetails :", acquireRunWorkoutTrajectoryId);
        if (acquireRunWorkoutTrajectoryId == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "button_show trajectoryId == null ");
        } else {
            fis.d().a(acquireRunWorkoutTrajectoryId);
        }
    }

    private void ab() {
        Bundle bundle = this.h;
        if (bundle == null) {
            return;
        }
        int i2 = bundle.getInt("track_type");
        int i3 = this.h.getInt("track_target");
        float f2 = this.h.getFloat("track_targetvalue");
        if (i2 == 283) {
            fis.d().a(this.x, i2, i3, f2);
            finish();
        } else {
            PermissionUtil.b(this.x, PermissionUtil.e(i2), new g(this.x, i2, i3, f2));
        }
    }

    private void j(WorkoutRecord workoutRecord) {
        this.bo = gic.b(Float.valueOf(workoutRecord.acquireActualCalorie())).floatValue();
        this.bn = gic.b(Float.valueOf(workoutRecord.acquireFinishRate())).floatValue();
        this.bq = gic.b(Integer.valueOf(workoutRecord.getDuration())).floatValue();
        String c2 = StringUtils.c((Object) workoutRecord.acquireWorkoutName());
        this.aa = c2;
        this.bs.setText(c2);
        if (!TextUtils.isEmpty(this.aa) && !this.as && !SportSupportUtil.f()) {
            h(workoutRecord);
        }
        f(workoutRecord);
        g(workoutRecord);
        w();
        if (this.as) {
            int acquireDifficulty = workoutRecord.acquireDifficulty();
            this.ax = acquireDifficulty;
            this.af.setText(ggm.d(acquireDifficulty));
            this.y.setText(ffy.b(R.plurals._2130903305_res_0x7f030109, (int) (workoutRecord.acquireCourseDuration() / 60.0f), UnitUtil.e(workoutRecord.acquireCourseDuration() / 60.0f, 1, 0)));
        }
        x();
        q();
        if (this.bb.isSpecialAiWorkout()) {
            f();
        }
        c(workoutRecord);
    }

    private void c(WorkoutRecord workoutRecord) {
        if (!this.bb.isBeitiOrYogaAiWorkout() || !this.as) {
            ac();
        }
        boolean isSingle = workoutRecord.isSingle();
        String acquireWorkoutId = workoutRecord.acquireWorkoutId();
        if (isSingle) {
            showLoading();
            d(acquireWorkoutId);
        } else if (this.as) {
            d(workoutRecord.acquireWorkoutId());
            LogUtil.h("Suggestion_FitnessResultActivity", "getWorkoutCount CountInfoByID");
        } else {
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi != null) {
                courseApi.getWorkoutCount(acquireWorkoutId, workoutRecord.acquireVersion(), new b(this));
            }
        }
    }

    private void f(WorkoutRecord workoutRecord) {
        long j = this.bv;
        if (j > 0) {
            this.bu.setText(DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YMD_HM));
        }
        this.r.setText(UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(workoutRecord.getDuration())));
        LogUtil.a("Suggestion_FitnessResultActivity", "is link wear:", Boolean.valueOf(workoutRecord.isLinkWear()));
        if (workoutRecord.isLinkWear()) {
            Drawable azb_ = azb_();
            if (azb_ != null) {
                azb_.setBounds(0, 0, nsn.c(this.x, 12.0f), nsn.c(this.x, 12.0f));
                this.t.setCompoundDrawables(null, null, azb_, null);
                this.k.setCompoundDrawables(null, null, azb_, null);
            }
            this.l.setOnClickListener(new View.OnClickListener() { // from class: fkn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessResultActivity.this.azj_(view);
                }
            });
            this.e.setOnClickListener(new View.OnClickListener() { // from class: fkv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessResultActivity.this.azk_(view);
                }
            });
        }
        if (this.bb.isShowScores()) {
            this.u.setText(com.huawei.health.servicesui.R$string.IDS_fitness_score);
            this.w.setText(UnitUtil.e(workoutRecord.acquireFinishRate(), 1, 0));
        } else {
            this.u.setText(R.string._2130848395_res_0x7f022a8b);
            this.w.setText(UnitUtil.e(workoutRecord.acquireFinishRate() <= 100.0f ? workoutRecord.acquireFinishRate() : 100.0f, 2, 0));
        }
    }

    public /* synthetic */ void azj_(View view) {
        nrh.d(this.x, nsf.h(R.string._2130848767_res_0x7f022bff));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azk_(View view) {
        nrh.d(this.x, nsf.h(R.string._2130848767_res_0x7f022bff));
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g(WorkoutRecord workoutRecord) {
        String h;
        String d2;
        float acquireActualCalorie = workoutRecord.acquireActualCalorie();
        this.p.setText(acquireActualCalorie < 0.5f ? nsf.h(R.string._2130851561_res_0x7f0236e9) : moe.d(acquireActualCalorie));
        float activeCalorie = workoutRecord.getActiveCalorie();
        boolean z = ((double) activeCalorie) > 1.0E-6d && activeCalorie < acquireActualCalorie;
        if (z) {
            HealthTextView healthTextView = this.n;
            if (activeCalorie < 0.5f) {
                d2 = nsf.h(R.string._2130851561_res_0x7f0236e9);
            } else {
                d2 = moe.d(activeCalorie);
            }
            healthTextView.setText(d2);
        }
        HealthTextView healthTextView2 = this.t;
        if (z) {
            h = nsf.h(R.string._2130847440_res_0x7f0226d0);
        } else {
            h = nsf.h(R.string._2130847441_res_0x7f0226d1);
        }
        healthTextView2.setText(h);
        nsy.cMA_(this.s, z ? 0 : 8);
        nsy.cMA_(this.e, z ? 0 : 8);
        nsy.cMA_(this.b, z ? 0 : 8);
        b(z ? 20 : 24, this.r, this.p, this.w, this.n);
    }

    private Drawable azb_() {
        Drawable drawable = ContextCompat.getDrawable(this.x, R.drawable._2131430490_res_0x7f0b0c5a);
        return nrt.a(this.x) ? nrf.cJH_(drawable, ContextCompat.getColor(this.x, R.color._2131296685_res_0x7f0901ad)) : drawable;
    }

    private void ac() {
        List<RecordAction> list = this.ay;
        if (list == null || list.size() <= 0) {
            return;
        }
        findViewById(R.id.sug_coach_action_layout).setVisibility(0);
        d(this.ay);
        this.m.getAdapter().notifyDataSetChanged();
    }

    private void f() {
        this.ah.setVisibility(0);
        if (this.bb.acquireCourseType() == 222) {
            h();
            return;
        }
        if (this.bb.acquireCourseType() == 333) {
            d();
        } else if (this.bb.acquireCourseType() == 444) {
            a();
            d();
        } else {
            b();
        }
    }

    private void h() {
        try {
            fqt fqtVar = (fqt) moj.e(this.bb.acquireExtendString(WorkoutRecord.Extend.FIT_EXTEND_STRETCH_RESULT), fqt.class);
            if (fqtVar != null) {
                StrechMuscleView strechMuscleView = new StrechMuscleView(this.x);
                strechMuscleView.setData(fqtVar.aDG_(), fqtVar.aDF_(), (int) this.bb.acquireFinishRate());
                this.ah.addView(strechMuscleView);
            }
        } catch (JsonSyntaxException unused) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "displayStretchMuscleView JsonSyntaxException.");
        }
    }

    private void a() {
        try {
            if (((fqc) moj.e(this.bb.acquireExtendString(WorkoutRecord.Extend.FIT_EXTEND_SCORE_RADAR), fqc.class)) != null) {
                ActionRadarView actionRadarView = new ActionRadarView(this.x);
                actionRadarView.setData(new double[]{r0.a(), r0.h(), r0.e(), r0.b(), r0.d(), r0.c()}, this.bb.acquireFinishRate());
                this.ah.addView(actionRadarView);
            }
        } catch (JsonSyntaxException unused) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "displayCompletedActionScoreView JsonSyntaxException.");
        }
    }

    private void d() {
        try {
            List<fqq> list = (List) moj.b(this.bb.acquireActionSummary(), new TypeToken<List<fqq>>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.2
            }.getType());
            if (koq.c(list)) {
                ScorePersentView scorePersentView = new ScorePersentView(this.x);
                scorePersentView.setActionScoreAdapter(list);
                this.ah.addView(scorePersentView);
            }
        } catch (JsonSyntaxException unused) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "displayActionSummaryListView JsonSyntaxException.");
        }
    }

    private void b() {
        try {
            if (((fqq) moj.e(this.bb.acquireExtendString(WorkoutRecord.Extend.FIT_EXTEND_ACTION_COMPLETION_STATUS), fqq.class)) != null) {
                ActionRingChartView actionRingChartView = new ActionRingChartView(this.x);
                actionRingChartView.setData(new double[]{r0.i(), r0.b(), r0.a(), r0.e(), r0.g()});
                this.ah.addView(actionRingChartView);
            }
        } catch (JsonSyntaxException unused) {
            ReleaseLogUtil.c("Suggestion_FitnessResultActivity", "displayActionSummaryView JsonSyntaxException.");
        }
    }

    private void h(final WorkoutRecord workoutRecord) {
        Drawable drawable;
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "setWorkoutNameClickListener, the workout is null, return");
            return;
        }
        if (!TextUtils.isEmpty(workoutRecord.acquirePlanId()) || workoutRecord.getCourseDefineType() == 1 || CommonUtil.bu()) {
            LogUtil.a("Suggestion_FitnessResultActivity", "workout.", workoutRecord.acquirePlanId(), " ", Integer.valueOf(workoutRecord.getCourseDefineType()));
            return;
        }
        if (LanguageUtil.bc(this)) {
            drawable = nrz.cKn_(this, R.drawable._2131430214_res_0x7f0b0b46);
        } else {
            drawable = getResources().getDrawable(R.drawable._2131430214_res_0x7f0b0b46);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.bs.setCompoundDrawablesRelative(null, null, drawable, null);
        this.bs.setOnClickListener(new View.OnClickListener() { // from class: fko
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessResultActivity.azd_(WorkoutRecord.this, view);
            }
        });
    }

    public static /* synthetic */ void azd_(WorkoutRecord workoutRecord, View view) {
        WorkoutRecord workoutRecord2 = new WorkoutRecord();
        workoutRecord2.saveVersion(workoutRecord.acquireVersion());
        workoutRecord2.saveExerciseTime(workoutRecord.acquireExerciseTime());
        workoutRecord2.saveWorkoutId(workoutRecord.acquireWorkoutId());
        workoutRecord2.savePlanId("");
        workoutRecord2.saveWorkoutName(workoutRecord.acquireWorkoutName());
        workoutRecord2.saveCalorie(workoutRecord.acquireCalorie());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(workoutRecord2);
        mmp mmpVar = new mmp(workoutRecord.acquireWorkoutId());
        mmpVar.d("FitnessResult");
        mod.c(arx.b(), mmpVar, arrayList);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(List<RecordAction> list) {
        this.m.setAdapter(new BaseRecyclerViewAdapter<RecordAction>(list, R.layout.sug_coach_item_fitness_finish_rcv) { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.10
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i2, RecordAction recordAction) {
                fnt.a(recyclerHolder, recordAction);
            }
        });
    }

    private void x() {
        List<HeartRateData> list = this.am;
        if (list == null || list.size() <= 0) {
            LogUtil.h("Suggestion_FitnessResultActivity", "setRateInfoData: The mHeartRateDataList is null or empty, return.");
            return;
        }
        this.ak = this.bb.acquireHeartRateZoneType();
        int acquireHeartRatePostureType = this.bb.acquireHeartRatePostureType();
        this.al = acquireHeartRatePostureType;
        LogUtil.a("Suggestion_FitnessResultActivity", " mHeartRatePostureType", Integer.valueOf(acquireHeartRatePostureType), " mHeartRateZoneType= ", Integer.valueOf(this.ak));
        int b2 = ffw.b(e(this.am));
        int acquireAvgHeartRate = this.bb.acquireAvgHeartRate();
        if (this.bw == null) {
            this.bl = nrt.a(this.x) ? 101 : 100;
            ViewHolderBase chartViewHolder = eme.b().getChartViewHolder(this.x, this.bl);
            this.bw = chartViewHolder;
            if (chartViewHolder == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "setRateInfoData failed, mViewHolderBase is null");
                return;
            }
        }
        if (this.an == null) {
            View buildView = this.bw.buildView(this.al, this.ak);
            this.an = buildView;
            a((HealthTextView) buildView.findViewById(R.id.track_share_heart_title), getResources().getDimensionPixelSize(R.dimen._2131365080_res_0x7f0a0cd8));
            aza_((HealthTextView) this.an.findViewById(R.id.track_share_heart_unit), Typeface.create(getResources().getString(R$string.textFontFamilyRegular), 0));
        }
        aze_(this.an, acquireAvgHeartRate);
        azg_(this.an, b2);
        azf_((ImageView) this.an.findViewById(R.id.heart_rate_information_icon));
        v();
        ffw.d();
        HwHealthBaseCombinedChart acquireHeartRateChart = this.bw.acquireHeartRateChart();
        if (acquireHeartRateChart != null) {
            HeartRateLineChartHolderImpl heartRateLineChartHolderImpl = new HeartRateLineChartHolderImpl(this);
            new ggt(heartRateLineChartHolderImpl).a(this.bb);
            heartRateLineChartHolderImpl.addHeartRateDataLayer(acquireHeartRateChart, new TrackLineChartHolder.b().c(this.bl == 100).b(true));
            acquireHeartRateChart.setTouchEnabled(false);
            acquireHeartRateChart.setTimeValueMode(HwHealthBaseCombinedChart.TimeValueMode.MINUTES);
            acquireHeartRateChart.refresh();
        } else {
            LogUtil.h("Suggestion_FitnessResultActivity", "drawHeartRateDataView chart is null");
        }
        findViewById(R.id.sug_coach_heart_rate_layout).setVisibility(0);
        this.aj.addView(this.an);
    }

    private void azf_(ImageView imageView) {
        if (imageView == null) {
            LogUtil.b("Suggestion_FitnessResultActivity", "explainIcon is null");
            return;
        }
        imageView.setVisibility(0);
        imageView.setBackground(BaseApplication.getContext().getDrawable(R.drawable._2131430493_res_0x7f0b0c5d));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.h("Suggestion_FitnessResultActivity", "downRemindClick is fast click");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    caj.a().a(ObserveLabels.HEART_RATE);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void aze_(View view, int i2) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.track_share_device_heart_avg_num);
        b(healthTextView, UnitUtil.e(i2, 1, 0));
        e(healthTextView);
    }

    private void azg_(View view, int i2) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.track_share_device_heart_max_num);
        b(healthTextView, UnitUtil.e(i2, 1, 0));
        e(healthTextView);
    }

    private void b(HealthTextView healthTextView, String str) {
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    private void a(HealthTextView healthTextView, float f2) {
        if (healthTextView != null) {
            healthTextView.setTextSize(0, f2);
        }
    }

    private void aza_(HealthTextView healthTextView, Typeface typeface) {
        if (healthTextView != null) {
            healthTextView.setTypeface(typeface);
        }
    }

    private void v() {
        final UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "getLocalUserInfo : userProfileMgrApi is null.");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    FitnessResultActivity fitnessResultActivity = FitnessResultActivity.this;
                    int[] c2 = ffw.c(fitnessResultActivity.e((List<HeartRateData>) fitnessResultActivity.am), FitnessResultActivity.this.ak, FitnessResultActivity.this.bb.getDuration(), FitnessResultActivity.this.bb.acquireHeartRateConfig(), userProfileMgrApi.getLocalUserInfo(), FitnessResultActivity.this.al);
                    Message obtainMessage = FitnessResultActivity.this.ai.obtainMessage();
                    obtainMessage.what = 106;
                    obtainMessage.obj = c2;
                    FitnessResultActivity.this.ai.sendMessage(obtainMessage);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int[] iArr) {
        int i2 = iArr[4];
        int i3 = iArr[3];
        int i4 = iArr[2];
        int i5 = iArr[1];
        int i6 = iArr[0];
        if (i6 + i5 + i4 + i2 + i3 == 0) {
            this.an.findViewById(R.id.heart_rate_chart_show_layout).setVisibility(8);
            return;
        }
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Integer.valueOf(i6));
        arrayList.add(Integer.valueOf(i5));
        arrayList.add(Integer.valueOf(i4));
        arrayList.add(Integer.valueOf(i3));
        arrayList.add(Integer.valueOf(i2));
        List<nkz> a2 = a(arrayList);
        HealthRingChart healthRingChart = (HealthRingChart) this.an.findViewById(R.id.sug_sc_j_heart_pie);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.x, new nld().c(false).b(true), a2);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: fkt
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return FitnessResultActivity.this.e(nkzVar);
            }
        });
        healthRingChart.setAdapter(healthRingChartAdapter);
        if (LanguageUtil.j(this.x)) {
            healthRingChart.setDesc(this.x.getResources().getString(com.huawei.health.servicesui.R$string.IDS_main_watch_heart_rate_string), this.x.getResources().getString(com.huawei.health.servicesui.R$string.IDS_hwh_motiontrack_heart_rate_zone));
        }
    }

    public /* synthetic */ String e(nkz nkzVar) {
        return c((int) nkzVar.i());
    }

    private List<nkz> a(List<Integer> list) {
        int i2 = this.ak;
        if (i2 < 0 || i2 > 3) {
            i2 = ffw.a();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298933_res_0x7f090a75)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298931_res_0x7f090a73)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298925_res_0x7f090a6d)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298927_res_0x7f090a6f)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298929_res_0x7f090a71)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298932_res_0x7f090a74)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298930_res_0x7f090a72)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298924_res_0x7f090a6c)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298926_res_0x7f090a6e)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(this.x, R.color._2131298928_res_0x7f090a70)));
        List<String> c2 = fgd.c(i2);
        int size = c2.size();
        ArrayList arrayList3 = new ArrayList(size);
        while (true) {
            size--;
            if (size < 0) {
                return arrayList3;
            }
            arrayList3.add(new nkz(size < c2.size() ? c2.get(size) : "", list.get(size).intValue(), size < arrayList.size() ? ((Integer) arrayList.get(size)).intValue() : 0, size < arrayList2.size() ? ((Integer) arrayList2.get(size)).intValue() : 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HeartRateData> e(List<HeartRateData> list) {
        ArrayList arrayList = new ArrayList(list != null ? list.size() : 0);
        if (list != null) {
            for (HeartRateData heartRateData : list) {
                if (heartRateData != null) {
                    arrayList.add(new HeartRateData(heartRateData.acquireTime(), heartRateData.acquireHeartRate()));
                }
            }
        }
        return arrayList;
    }

    private String c(int i2) {
        Context context = this.x;
        if (context == null) {
            return null;
        }
        if (i2 > 0 && i2 < 60) {
            return context.getString(com.huawei.health.servicesui.R$string.IDS_motiontrack_show_chart_less_than_one_minute, 1);
        }
        return nsn.e((int) TimeUnit.SECONDS.toMinutes(i2), this.x);
    }

    private void w() {
        if (this.aw) {
            int i2 = this.bj;
            if (i2 != 283) {
                switch (i2) {
                    case 257:
                        this.bk.setText(R$string.IDS_main_time_line_start_walking);
                        break;
                    case 258:
                        this.bk.setText(R$string.IDS_main_time_line_start_running);
                        break;
                    case 259:
                        this.bk.setText(R$string.IDS_main_time_line_start_cycling);
                        break;
                }
            } else {
                this.bk.setText(R$string.IDS_indoor_start_skipping);
            }
            this.bm.setVisibility(0);
            this.bk.setOnClickListener(this);
            return;
        }
        if (this.bb.isRunWorkout()) {
            u();
        }
    }

    private void q() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "refreshDifficulty : courseApi is null.");
        } else {
            courseApi.getCourseById(this.bb.acquireWorkoutId(), this.bb.acquireVersion(), null, new i(this));
        }
    }

    private void u() {
        LogUtil.c("Suggestion_FitnessResultActivity", "showTrackButton:");
        this.bk.setText(R.string._2130849772_res_0x7f022fec);
        this.bm.setVisibility(0);
        this.bk.setOnClickListener(this);
    }

    /* loaded from: classes4.dex */
    static class b extends UiCallback<Integer> {
        WeakReference<FitnessResultActivity> e;

        b(FitnessResultActivity fitnessResultActivity) {
            this.e = new WeakReference<>(fitnessResultActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_FitnessResultActivity", "errorCode = ", Integer.valueOf(i), ".errorInfo = ", str);
            FitnessResultActivity fitnessResultActivity = this.e.get();
            if (fitnessResultActivity != null) {
                if (fitnessResultActivity.mLoadingView != null) {
                    fitnessResultActivity.mLoadingView.setVisibility(8);
                }
                fitnessResultActivity.d(0);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Integer num) {
            FitnessResultActivity fitnessResultActivity = this.e.get();
            if (fitnessResultActivity != null) {
                if (fitnessResultActivity.mLoadingView != null) {
                    fitnessResultActivity.mLoadingView.setVisibility(8);
                }
                if (num == null) {
                    int unused = FitnessResultActivity.f3086a = 0;
                    fitnessResultActivity.d(0);
                } else {
                    int unused2 = FitnessResultActivity.f3086a = num.intValue();
                    fitnessResultActivity.d(num.intValue());
                }
            }
        }
    }

    private void d(String str) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.e("R_Suggestion_FitnessResultActivity", "courseApi is null");
            d(0);
            f3086a = 0;
            finishLoading();
            return;
        }
        courseApi.queryTrainCountByCourseId(str, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.8
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                FitnessResultActivity.this.d(0);
                int unused = FitnessResultActivity.f3086a = 0;
                FitnessResultActivity.this.finishLoading();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(final Integer num) {
                FitnessResultActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.8.3
                    @Override // java.lang.Runnable
                    public void run() {
                        int unused = FitnessResultActivity.f3086a = num.intValue();
                        FitnessResultActivity.this.d(num.intValue());
                        FitnessResultActivity.this.finishLoading();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        LogUtil.a("Suggestion_FitnessResultActivity", "Get times of train from db, trainTimes = ", Integer.valueOf(i2));
        if (i2 == 0) {
            this.bp.setVisibility(8);
        } else {
            this.bp.setVisibility(0);
            this.bp.setText(ffy.b(R.plurals._2130903477_res_0x7f0301b5, i2, Integer.valueOf(i2)));
        }
    }

    private void e(HealthTextView... healthTextViewArr) {
        if (healthTextViewArr == null) {
            return;
        }
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        for (HealthTextView healthTextView : healthTextViewArr) {
            if (healthTextView != null) {
                aza_(healthTextView, createFromAsset);
            }
        }
    }

    private void b(int i2, HealthTextView... healthTextViewArr) {
        if (healthTextViewArr == null) {
            return;
        }
        for (HealthTextView healthTextView : healthTextViewArr) {
            nsy.cMw_(healthTextView, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.l.setVisibility(z ? 0 : 8);
        this.o.setVisibility(z ? 0 : 8);
    }

    private List<RecordAction> e(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        return moj.b(str, RecordAction[].class);
    }

    /* loaded from: classes4.dex */
    public static class i extends UiCallback<Workout> {
        private WeakReference<FitnessResultActivity> d;

        i(FitnessResultActivity fitnessResultActivity) {
            this.d = new WeakReference<>(fitnessResultActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_FitnessResultActivity", "WorkoutDifficultyUiCallback errorCode:", Integer.valueOf(i), "errorInfo:", str);
            d();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            final FitnessResultActivity fitnessResultActivity = this.d.get();
            if (fitnessResultActivity == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "WorkoutDifficultyUiCallback failed with activity is null");
                return;
            }
            final FitWorkout a2 = mod.a(workout);
            if (a2 != null) {
                b(fitnessResultActivity, a2);
                if (a2.getCourseDefineType() == 1) {
                    fitnessResultActivity.af.setText(fitnessResultActivity.x.getString(R.string._2130848684_res_0x7f022bac));
                    fitnessResultActivity.y.setVisibility(8);
                }
                String acquireMidPicture = a2.acquireMidPicture();
                String acquirePicture = a2.acquirePicture();
                if (!TextUtils.isEmpty(StringUtils.c((Object) acquireMidPicture)) || !TextUtils.isEmpty(StringUtils.c((Object) acquirePicture))) {
                    d(acquireMidPicture, acquirePicture, fitnessResultActivity.f);
                } else {
                    fitnessResultActivity.f.aMj_(azn_(fitnessResultActivity, a2), true);
                }
                asc.e().b(new Runnable() { // from class: fkw
                    @Override // java.lang.Runnable
                    public final void run() {
                        FitnessResultActivity.i.this.d(a2, fitnessResultActivity);
                    }
                });
                b(a2, fitnessResultActivity);
                return;
            }
            LogUtil.h("Suggestion_FitnessResultActivity", "request workout result data is null");
            d();
        }

        private void b(FitWorkout fitWorkout, FitnessResultActivity fitnessResultActivity) {
            if (fitWorkout.getVideoProperty() != 3) {
                fitnessResultActivity.b(true);
                fitnessResultActivity.ac.setRightButtonVisibility(0);
            } else {
                fitnessResultActivity.ac.setRightButtonVisibility(8);
                fitnessResultActivity.b(false);
            }
        }

        private void b(FitnessResultActivity fitnessResultActivity, FitWorkout fitWorkout) {
            if (fitnessResultActivity.as) {
                return;
            }
            fitnessResultActivity.ax = fitWorkout.acquireDifficulty();
            if (fitWorkout.isNewRunCourse()) {
                fitnessResultActivity.c(fitWorkout.getCourseActions());
            } else if (!fitWorkout.isEquipmentCourse()) {
                fitnessResultActivity.b(fitWorkout.acquireWorkoutActions());
            } else {
                LogUtil.a("Suggestion_FitnessResultActivity", "isEquipmentCourse");
            }
            fitnessResultActivity.af.setText(ggm.d(fitnessResultActivity.ax));
            if (fitWorkout.acquireMeasurementType() != 0) {
                if (fitWorkout.acquireDuration() / 60.0f < 1.0f) {
                    fitnessResultActivity.y.setVisibility(8);
                    return;
                } else {
                    LogUtil.c("Suggestion_FitnessResultActivity", "WorkoutAction.MEASUREMENTTYPE_TIME:", Integer.valueOf(fitWorkout.acquireMeasurementType()));
                    fitnessResultActivity.y.setText(ffy.b(R.plurals._2130903305_res_0x7f030109, (int) (fitWorkout.acquireDuration() / 60.0f), UnitUtil.e(fitWorkout.acquireDuration() / 60.0f, 1, 0)));
                    return;
                }
            }
            boolean h = UnitUtil.h();
            double acquireDistance = fitWorkout.acquireDistance() / 1000.0d;
            double e = UnitUtil.e(acquireDistance, 3);
            if (h) {
                acquireDistance = e;
            }
            fitnessResultActivity.y.setText(ffy.b(R.plurals._2130903108_res_0x7f030044, (int) acquireDistance, UnitUtil.e(acquireDistance, 1, 0)));
        }

        private void d() {
            FitnessResultActivity fitnessResultActivity = this.d.get();
            int i = nsn.v(fitnessResultActivity) ? R.color._2131299386_res_0x7f090c3a : R.color._2131296499_res_0x7f0900f3;
            if (fitnessResultActivity != null) {
                e(fitnessResultActivity, i);
                if (fitnessResultActivity.f != null) {
                    fitnessResultActivity.f.aMj_(azn_(fitnessResultActivity, null), true);
                }
                d(fitnessResultActivity, i);
                if (fitnessResultActivity.bp != null) {
                    fitnessResultActivity.bp.setTextColor(fitnessResultActivity.getResources().getColor(i));
                    fitnessResultActivity.bp.setAlpha(0.6f);
                }
            }
        }

        private void d(FitnessResultActivity fitnessResultActivity, int i) {
            if (fitnessResultActivity.bs != null) {
                int color = fitnessResultActivity.getResources().getColor(i);
                fitnessResultActivity.bs.setTextColor(color);
                Drawable[] compoundDrawablesRelative = fitnessResultActivity.bs.getCompoundDrawablesRelative();
                Drawable drawable = compoundDrawablesRelative.length > 2 ? compoundDrawablesRelative[2] : null;
                Drawable cJH_ = drawable != null ? nrf.cJH_(drawable, color) : null;
                if (cJH_ != null) {
                    cJH_.setBounds(0, 0, cJH_.getMinimumWidth(), cJH_.getMinimumHeight());
                    fitnessResultActivity.bs.setCompoundDrawablesRelative(null, null, cJH_, null);
                }
                fitnessResultActivity.bs.setAlpha(0.6f);
            }
        }

        private void e(FitnessResultActivity fitnessResultActivity, int i) {
            if (!fitnessResultActivity.as) {
                fitnessResultActivity.ax = -1;
                if (fitnessResultActivity.af != null) {
                    fitnessResultActivity.af.setVisibility(4);
                }
                if (fitnessResultActivity.y != null) {
                    fitnessResultActivity.y.setVisibility(4);
                    return;
                }
                return;
            }
            fitnessResultActivity.c((List<ChoreographedMultiActions>) Collections.EMPTY_LIST);
            if (fitnessResultActivity.af != null) {
                fitnessResultActivity.af.setTextColor(fitnessResultActivity.getResources().getColor(i));
                fitnessResultActivity.af.setAlpha(0.38f);
            }
            if (fitnessResultActivity.y != null) {
                fitnessResultActivity.y.setTextColor(fitnessResultActivity.getResources().getColor(i));
                fitnessResultActivity.y.setAlpha(0.38f);
            }
        }

        private Drawable azn_(FitnessResultActivity fitnessResultActivity, FitWorkout fitWorkout) {
            WorkoutRecord workoutRecord = fitnessResultActivity.bb;
            if (workoutRecord != null && workoutRecord.isFitnessRecordFromTv()) {
                return azm_(fitnessResultActivity, R.drawable.pic_fitness_result_header_tv_common);
            }
            if (fitWorkout != null && fitWorkout.getCourseDefineType() == 1) {
                long g = CommonUtils.g(fitWorkout.acquireId()) % 3;
                return azm_(fitnessResultActivity, g == 0 ? R.drawable.pic_custom_yellow : g == 1 ? R.drawable.pic_custom_red : R.drawable.pic_custom_blue);
            }
            return azm_(fitnessResultActivity, R.drawable.pic_fitness_result_header_default);
        }

        private Drawable azm_(Context context, int i) {
            if (LanguageUtil.bc(context)) {
                return nrz.cKn_(context, i);
            }
            return ContextCompat.getDrawable(context, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void d(FitWorkout fitWorkout, FitnessResultActivity fitnessResultActivity) {
            if (fitnessResultActivity == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "getRelativeCourses activity null");
                return;
            }
            List<String> acquireListRelativeWorkouts = fitWorkout.acquireListRelativeWorkouts();
            if (acquireListRelativeWorkouts == null || acquireListRelativeWorkouts.size() == 0) {
                acquireListRelativeWorkouts = new ArrayList<>();
                d(acquireListRelativeWorkouts, fitWorkout.acquireNarrowDesc());
            }
            if (acquireListRelativeWorkouts.size() != 0) {
                fitnessResultActivity.az = new ArrayList(10);
                d(0, acquireListRelativeWorkouts);
            } else {
                LogUtil.h("Suggestion_FitnessResultActivity", "no relative courses");
            }
        }

        private void d(List<String> list, String str) {
            if (list == null || str == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "fillRelativeCourses, the relativeCourse or detail is null");
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("recommendCourses")) {
                    JSONArray jSONArray = new JSONArray(jSONObject.optString("recommendCourses"));
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String optString = jSONArray.optString(i);
                        if (optString != null && optString.length() > 0) {
                            list.add(optString);
                        }
                    }
                }
            } catch (JSONException unused) {
                LogUtil.h("Suggestion_FitnessResultActivity", "fillRelativeCourses json has error");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(final int i, final List<String> list) {
            final FitnessResultActivity fitnessResultActivity = this.d.get();
            if (fitnessResultActivity == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "resetRelativeCourseAdapter, the activity is null");
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("Suggestion_FitnessResultActivity", "requestRelativeCourse relativeCourses is null");
                return;
            }
            if (i >= list.size()) {
                c();
                return;
            }
            String str = list.get(i);
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h("Suggestion_FitnessResultActivity", "requestRelativeCourse : courseApi is null.");
            } else {
                courseApi.getCourseById(str, null, null, new UiCallback<Workout>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity.i.3
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i2, String str2) {
                        LogUtil.h("Suggestion_FitnessResultActivity", "getworkoutlist onFailure ", Integer.valueOf(i2));
                        i.this.d(i + 1, (List<String>) list);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Workout workout) {
                        LogUtil.a("Suggestion_FitnessResultActivity", "getworkoutlist onSuccess");
                        FitWorkout a2 = mod.a(workout);
                        if (a2 != null) {
                            fitnessResultActivity.az.add(a2);
                        }
                        i.this.d(i + 1, (List<String>) list);
                    }
                });
            }
        }

        private void c() {
            FitnessResultActivity fitnessResultActivity = this.d.get();
            if (fitnessResultActivity != null) {
                fitnessResultActivity.aa();
            } else {
                LogUtil.h("Suggestion_FitnessResultActivity", "resetRelativeCourseAdapter, the activity is null");
            }
        }

        private void d(String str, String str2, AutoFillColorView autoFillColorView) {
            if (TextUtils.isEmpty(str)) {
                str = str2;
            }
            String c = StringUtils.c((Object) str);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            if (c.startsWith("http")) {
                autoFillColorView.b(c, true);
                return;
            }
            if (CommonUtil.bu()) {
                autoFillColorView.b("file:///android_asset/suggestion/img/" + c, true);
            } else {
                autoFillColorView.b("file:///android_asset/img/" + c, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        HandlerExecutor.e(new Runnable() { // from class: fkx
            @Override // java.lang.Runnable
            public final void run() {
                FitnessResultActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        if (this.av) {
            LogUtil.a("Suggestion_FitnessResultActivity", "show marketing ,do not show relative course");
            return;
        }
        if (this.v == null || this.ap == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "updateUICourseView mCourseLayout == null || mHorizontalAdapter == null");
        } else if (koq.b(this.az)) {
            LogUtil.a("Suggestion_FitnessResultActivity", "updateUICourseView mRelativeCourseList is null");
        } else {
            this.v.setVisibility(0);
            this.ap.d(this.az);
        }
    }

    private void s() {
        if (!r()) {
            LogUtil.h("Suggestion_FitnessResultActivity", "The account language is not supported.");
            return;
        }
        SharedPreferences sharedPreferences = this.x.getSharedPreferences(Integer.toString(20002), 0);
        this.br = sharedPreferences;
        if (sharedPreferences == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "Show bubble sharedPreferences is null.");
            return;
        }
        boolean z = sharedPreferences.getBoolean("is_first_time_guide_user_share", true);
        this.ao = z;
        if (z) {
            this.ag.setVisibility(0);
        } else {
            this.ag.setVisibility(8);
        }
    }

    private void k() {
        if (!r()) {
            LogUtil.h("Suggestion_FitnessResultActivity", "The account language is not supported.");
            return;
        }
        this.ag.setVisibility(8);
        if (this.br == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "Hide bubble sharedPreferences is null.");
            this.br = this.x.getSharedPreferences(Integer.toString(20002), 0);
        }
        SharedPreferences.Editor edit = this.br.edit();
        if (edit == null) {
            LogUtil.h("Suggestion_FitnessResultActivity", "Hide bubble editor is null.");
        } else {
            edit.putBoolean("is_first_time_guide_user_share", false);
            edit.commit();
        }
    }

    private boolean r() {
        return LanguageUtil.m(BaseApplication.getContext()) && !Utils.o();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReleaseLogUtil.e("R_Suggestion_FitnessResultActivity", "onResume");
    }

    /* loaded from: classes4.dex */
    static class f extends CustomPermissionAction {
        private final WeakReference<FitnessResultActivity> d;

        private f(FitnessResultActivity fitnessResultActivity) {
            super(fitnessResultActivity);
            this.d = new WeakReference<>(fitnessResultActivity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            FitnessResultActivity fitnessResultActivity = this.d.get();
            if (fitnessResultActivity != null && !fitnessResultActivity.isDestroyed() && !fitnessResultActivity.isFinishing()) {
                fitnessResultActivity.m();
            } else {
                ReleaseLogUtil.d("R_Suggestion_FitnessResultActivity", "WeakShareAction onGranted activity ", fitnessResultActivity);
            }
        }
    }

    /* loaded from: classes4.dex */
    static class g extends CustomPermissionAction {

        /* renamed from: a, reason: collision with root package name */
        private final float f3088a;
        private final int b;
        private final WeakReference<FitnessResultActivity> c;
        private final int d;
        private final Context e;

        private g(FitnessResultActivity fitnessResultActivity, Context context, int i, int i2, float f) {
            super(fitnessResultActivity);
            this.c = new WeakReference<>(fitnessResultActivity);
            this.e = context;
            this.d = i;
            this.b = i2;
            this.f3088a = f;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            FitnessResultActivity fitnessResultActivity = this.c.get();
            if (fitnessResultActivity == null) {
                ReleaseLogUtil.d("Suggestion_FitnessResultActivity", "activity is null in WeakCustomPermissionAction");
            } else {
                fis.d().a(this.e, this.d, this.b, this.f3088a);
                fitnessResultActivity.finish();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
