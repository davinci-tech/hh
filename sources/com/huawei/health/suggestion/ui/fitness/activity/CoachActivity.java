package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.DataPlatformApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.health.suggestion.ui.fitness.activity.CoachActivity;
import com.huawei.health.suggestion.ui.fitness.helper.HwScreenFoldDisplayMode;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.CoachView;
import com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.health.suggestion.ui.fitness.viewmodel.CourseEquipmentViewModel;
import com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.arx;
import defpackage.ary;
import defpackage.ash;
import defpackage.dds;
import defpackage.ffw;
import defpackage.ffy;
import defpackage.fhp;
import defpackage.fis;
import defpackage.fno;
import defpackage.fnt;
import defpackage.fnu;
import defpackage.fnz;
import defpackage.foa;
import defpackage.foq;
import defpackage.fpq;
import defpackage.fyr;
import defpackage.gge;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.gnb;
import defpackage.koq;
import defpackage.mmt;
import defpackage.nsn;
import defpackage.squ;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CoachActivity extends BaseCoachActivity implements SportLifecycle {

    /* renamed from: a, reason: collision with root package name */
    private String f3073a;
    private float aa;
    private ArrayList<RecordAction> ab;
    private float ac;
    private CoachView b;
    private CoachData c;
    private Context d;
    private foq f;
    private int g;
    private int h;
    private Map<Long, Integer> i;
    private CourseEquipmentViewModel j;
    private int l;
    private boolean o;
    private boolean p;
    private boolean s;
    private WorkoutRecord u;
    private CustomAlertDialog v;
    private long w;
    private int x;
    private List<Motion> y;
    private String z;
    private boolean n = false;
    private boolean q = false;
    private boolean r = false;
    private boolean m = false;
    private Observer<Bundle> ad = new Observer() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity$$ExternalSyntheticLambda1
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            CoachActivity.this.ayT_((Bundle) obj);
        }
    };
    private fnu.e e = new fnu.e();
    private volatile boolean t = false;
    private String k = "NORMAL_MODE";

    /* JADX INFO: Access modifiers changed from: private */
    public int q() {
        return 5894;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public int getLoadingLayoutId() {
        return R.layout.sug_loading_layout;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public int setLoadingBackgroundColor() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    public void reloadView(final String str) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.4
            @Override // java.lang.Runnable
            public void run() {
                CoachActivity.this.k = str;
                CoachActivity.this.b.c(CoachActivity.this.k);
                CoachActivity.this.ah();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public String getCurrFoldModel() {
        return this.k;
    }

    private static boolean e(Context context, boolean z) {
        boolean z2 = false;
        if (context == null) {
            LogUtil.b("Suggestion_CoachActivity", "context is null.");
            return false;
        }
        if (context.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager) {
            AudioManager audioManager = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
            if (!z ? audioManager.abandonAudioFocus(null) == 1 : audioManager.requestAudioFocus(null, 3, 1) == 1) {
                z2 = true;
            }
        }
        LogUtil.a("Suggestion_CoachActivity", "pauseMusic bMute=", Boolean.valueOf(z), " result=", Boolean.valueOf(z2));
        return z2;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Suggestion_CoachActivity", "onCreate");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.markFitnessCourseStatusOccupied();
        }
        super.onCreate(bundle);
        clearBackgroundDrawable();
        getWindow().addFlags(1152);
        this.d = this;
        this.mStartTrainTime = System.currentTimeMillis();
        this.i = new HashMap(10);
        this.g = getWindow().getDecorView().getSystemUiVisibility();
        if (!nsn.ag(this.d)) {
            setRequestedOrientation(0);
        }
        s();
        startPlay();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        this.b.ap();
        this.b.ar();
        if (nsn.ag(this.d)) {
            return;
        }
        setRequestedOrientation(0);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_fitness_activity_coach);
        p();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        this.b = (CoachView) findViewById(R.id.cv_coua);
    }

    private void s() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.x = intent.getIntExtra("coachstartposition", 0);
                this.o = intent.getBooleanExtra("havenexttrain", false);
                this.n = intent.getBooleanExtra("isafterrun", false);
                this.w = intent.getLongExtra("plan_execute_time", 0L);
                String stringExtra = intent.getStringExtra("BASE_AUDIO_TIMBRE");
                this.f3073a = stringExtra;
                squ.q(stringExtra);
                if (intent.getParcelableExtra(ContentTemplateRecord.MOTIONS) != null) {
                    CoachData coachData = (CoachData) intent.getParcelableExtra(ContentTemplateRecord.MOTIONS);
                    this.c = coachData;
                    checkBreachStatus(coachData);
                }
                this.s = intent.getBooleanExtra("ISPLANFIT", false);
                this.z = intent.getStringExtra("workout_package_id");
                this.h = intent.getIntExtra("course_belong_type", 0);
                if (ayQ_(intent)) {
                    return;
                } else {
                    ayS_(intent);
                }
            } catch (Exception e) {
                LogUtil.h("Suggestion_CoachActivity", LogAnonymous.b((Throwable) e));
            }
        }
        x();
        ayU_(intent);
        fhp.c().onChange(this.u.acquireWorkoutId(), 2, null);
        this.b.setGender(1);
        this.b.setMotions(this.y, this.x);
        final String b = ash.b("is_first_to_coachacitivyt");
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(b)) {
                    CoachActivity.this.b.d(true);
                    ash.a("is_first_to_coachacitivyt", "notfirst");
                }
                CoachActivity.this.updateViewController();
            }
        });
        this.ac = fnz.e();
        this.mCaloriesConsumeHandler = new CaloriesConsumeHandler(CoachController.d().i(), this.p, new a(this.b));
        this.b.setCaloriesConsumeHandler(this.mCaloriesConsumeHandler);
    }

    /* loaded from: classes4.dex */
    public static class a implements CaloriesConsumeHandler.CaloriesRefreshCallback {
        private WeakReference<CoachView> e;

        a(CoachView coachView) {
            this.e = new WeakReference<>(coachView);
        }

        @Override // com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler.CaloriesRefreshCallback
        public void onRefresh(final float f) {
            final CoachView coachView;
            WeakReference<CoachView> weakReference = this.e;
            if (weakReference == null || (coachView = weakReference.get()) == null) {
                return;
            }
            if (HandlerExecutor.c()) {
                coachView.b(f);
            } else {
                HandlerExecutor.e(new Runnable() { // from class: fkk
                    @Override // java.lang.Runnable
                    public final void run() {
                        CoachView.this.b(f);
                    }
                });
            }
        }
    }

    private void ayU_(Intent intent) {
        String str;
        String str2;
        WorkoutRecord workoutRecord = this.u;
        String str3 = "";
        if (workoutRecord != null) {
            str2 = workoutRecord.acquireWorkoutName();
            str = ggr.d(this.u);
        } else {
            str = Constants.NULL;
            str2 = "";
        }
        CoachData coachData = this.c;
        if (coachData != null) {
            str3 = coachData.acquireWorkId();
            this.b.a(FitWorkout.acquireComeFrom(this.c.acquireWorkId()));
        }
        fnu.e aBy_ = fnu.aBy_(intent, str2, str3, str);
        this.e = aBy_;
        CoachView coachView = this.b;
        if (coachView != null) {
            coachView.e(aBy_);
        }
    }

    private void ayS_(Intent intent) {
        if (!intent.hasExtra("IS_SUPPORT_RECORD") || intent.getIntExtra("IS_SUPPORT_RECORD", 0) == 0) {
            return;
        }
        getWindow().addFlags(8192);
        this.b.setScreenCastVisibility(8);
    }

    private void p() {
        foq aBU_ = foq.aBU_(this);
        this.f = aBU_;
        aBU_.a(R.layout.sug_fitness_coach_dialog_exit).aBW_(R.id.sug_coach_dialog_No, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachActivity.this.b.setClickStop(false);
                CoachActivity.this.getWindow().getDecorView().setSystemUiVisibility(CoachActivity.this.q());
                CoachActivity.this.f.a();
                fnu.a(CoachActivity.this.e, 7);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).aBW_(R.id.sug_coach_dialog_yse, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachActivity.this.f.a();
                fnu.a(CoachActivity.this.e, 6);
                CoachActivity.this.q = true;
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f.aBV_().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.10
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (CoachActivity.this.b == null) {
                    return;
                }
                CoachActivity.this.b.ao();
                if (CoachActivity.this.q) {
                    CoachActivity.this.q = false;
                    CoachActivity.this.ae();
                }
            }
        });
    }

    private boolean ayQ_(Intent intent) {
        WorkoutRecord workoutRecord = (WorkoutRecord) intent.getParcelableExtra("workoutrecord");
        this.u = workoutRecord;
        CoachData coachData = this.c;
        if (coachData == null || workoutRecord == null) {
            LogUtil.b("Suggestion_CoachActivity", "mCoachData,mRecord is null");
            finish();
            return true;
        }
        List<Motion> acquireMotions = coachData.acquireMotions();
        this.y = acquireMotions;
        if (acquireMotions != null) {
            return false;
        }
        LogUtil.b("Suggestion_CoachActivity", "mMotions is null");
        finish();
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void updateViewController() {
        this.b.b(new OnMotionChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.7
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionOver(Motion motion, int i) {
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionReady(Motion motion, int i) {
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionChanged(Motion motion, int i) {
                CoachActivity.this.d(motion, i);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionStart(Motion motion, int i) {
                LogUtil.a("Suggestion_CoachActivity", "onMotionStart:", motion.acquireName(), Integer.valueOf(i));
                CoachActivity.this.mPlayStartTime = System.currentTimeMillis();
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionPause(Motion motion, int i) {
                CoachActivity.this.e(motion, i);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionContinue(Motion motion, int i) {
                CoachActivity.this.c(motion, i);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onGroupFinish(Motion motion, int i, int i2) {
                LogUtil.c("Suggestion_CoachActivity", "A group of action completed, Total: ", Integer.valueOf(i2));
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onTrainOver(boolean z) {
                if (z && CoachActivity.this.b != null && !CoachActivity.this.b.am()) {
                    fnu.a(CoachActivity.this.e, 4);
                }
                CoachActivity.this.d(z);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onCustomBeave() {
                CoachActivity.this.l();
            }
        });
        this.b.d(AudioConstant.BACKGROUND_MUSIC_CODE);
        finishLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Motion motion, int i) {
        LogUtil.a("Suggestion_CoachActivity", "onMotionChanged:", motion.acquireName(), Integer.valueOf(i));
        ac();
        if (this.p) {
            return;
        }
        this.b.setCaloreHeartViewsVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Motion motion, int i) {
        this.mIsPauseStatus = true;
        this.mPauseTrainTime = System.currentTimeMillis();
        ReleaseLogUtil.e("Suggestion_CoachActivity", "onMotionPause:", motion.acquireName(), Integer.valueOf(i), " mPauseTrainTime:", Long.valueOf(this.mPauseTrainTime));
        ac();
        CourseEquipmentViewModel courseEquipmentViewModel = this.j;
        if (courseEquipmentViewModel == null || courseEquipmentViewModel.getSportStatus() == 2) {
            return;
        }
        this.j.onPauseSport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Motion motion, int i) {
        ReleaseLogUtil.e("Suggestion_CoachActivity", "onMotionContinue:", motion.acquireName(), "position:", Integer.valueOf(i));
        this.mPlayStartTime = System.currentTimeMillis();
        dealLastPauseTime();
        this.mIsPauseStatus = false;
        CourseEquipmentViewModel courseEquipmentViewModel = this.j;
        if (courseEquipmentViewModel == null || courseEquipmentViewModel.getSportStatus() == 1 || this.q) {
            return;
        }
        this.j.onResumeSport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.c("Suggestion_CoachActivity", "Training Completed");
        this.t = true;
        CourseEquipmentViewModel courseEquipmentViewModel = this.j;
        if (courseEquipmentViewModel != null) {
            courseEquipmentViewModel.m134x32b3e3a1();
        }
        if (!z) {
            if (w()) {
                return;
            }
            finish();
        } else {
            if (this.mHeartRateHandler != null && koq.c(this.mHeartRateHandler.getHeartRateList())) {
                fnt.d(this.i, this.mHeartRateHandler.getHeartRateList(), this.mIntensityHeartRateThreshold);
            }
            h();
        }
    }

    private void ac() {
        this.mPlayEndTime = System.currentTimeMillis();
        if (this.mPlayStartTime == 0 || this.mHeartRateHandler != null) {
            return;
        }
        fnt.d(this.i, this.mPlayStartTime, this.mPlayEndTime);
        u();
    }

    private void u() {
        this.mPlayStartTime = 0L;
        this.mPlayEndTime = 0L;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        CoachView coachView = this.b;
        if (coachView != null && coachView.an()) {
            LogUtil.a("Suggestion_CoachActivity", "onBackPressed isLocked true");
            return;
        }
        if (r()) {
            if (this.b.ak()) {
                fnu.a(this.e, 3);
            }
            this.b.setClickStop(true);
            l();
            return;
        }
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        CoachView coachView = this.b;
        if (coachView != null) {
            coachView.ae();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!this.b.ak()) {
            t();
        } else {
            this.b.finishTrain();
        }
    }

    private boolean r() {
        return this.b.getTrainStation() == 191 || this.b.getTrainStation() == 190;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, android.app.Activity
    public void finish() {
        LogUtil.a("Suggestion_CoachActivity", "finish()");
        super.finish();
        if ((this.n && this.b.ak()) || y()) {
            fis.d().e();
        } else {
            if (this.o) {
                Intent intent = new Intent(this, (Class<?>) TrainDetail.class);
                intent.putExtra("finish", false);
                startActivity(intent);
                return;
            }
            LogUtil.c("Suggestion_CoachActivity", "Is not after run or have next train");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y() {
        return this.b.ak() && this.p;
    }

    private void t() {
        fnu.a(this.e, 5);
        this.f.a(this.k);
        this.b.aa();
        this.b.aw();
    }

    private void h() {
        this.aa = 0.0f;
        this.ab = new ArrayList<>(this.y.size());
        for (Motion motion : this.y) {
            if (motion != null) {
                int acquireProgress = motion.acquireProgress();
                this.ab.add(motion.getRecordAction());
                float c = foa.c(motion, acquireProgress);
                this.aa += c;
                LogUtil.c("Suggestion_CoachActivity", "rate：", Float.valueOf(c), " duration：", Float.valueOf((motion.acquireDuration() * motion.acquireGroups()) / 1000.0f), " kcal：", Float.valueOf(((((c * motion.acquireCalorie()) * motion.acquireDuration()) * motion.acquireGroups()) * this.ac) / 1000.0f), "--stand kcal2 ", Float.valueOf(motion.acquireCalorie()), "==times: ", Float.valueOf(motion.acquireDuration()), "  weight: ", Float.valueOf(this.ac));
            }
        }
        this.aa = (this.aa * 100.0f) / this.y.size();
        fnt.a(this.i);
        getWindow().clearFlags(1024);
        getWindow().getDecorView().setSystemUiVisibility(this.g);
        g();
    }

    private void g() {
        LogUtil.a("Suggestion_CoachActivity", "save record: name:", this.u.acquireWorkoutName(), "--saveFinishRate:", Integer.valueOf((int) this.aa), "--saveActualCalorie:", Integer.valueOf((int) this.mCaloriesConsumeHandler.b()), "--saveDuring:", Long.valueOf(this.b.u()), "--saveExerciseTime:", Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        this.u.saveCalorie(fno.a(this.y, this.ac));
        this.u.saveExerciseTime(Calendar.getInstance().getTimeInMillis());
        dealLastPauseTime();
        this.u.saveRecordType(!TextUtils.isEmpty(this.u.acquirePlanId()) ? 1 : 0);
        this.u.saveFinishRate(this.aa);
        this.u.saveActualCalorie(this.mCaloriesConsumeHandler.b());
        this.u.setActiveCalorie(this.mCaloriesConsumeHandler.d());
        if (CoachController.d().i()) {
            this.u.setDuration((int) (CoachController.d().e() * 1000.0f));
        } else {
            this.u.setDuration((int) ((System.currentTimeMillis() - this.mStartTrainTime) - this.mPauseDuration));
        }
        this.u.saveActionSummary(new Gson().toJson(this.ab));
        if (w()) {
            runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    CoachActivity.this.z();
                }
            });
        } else {
            aa();
        }
    }

    private boolean w() {
        this.r = ggs.b(3);
        String b = SharedPreferenceManager.b(arx.b(), String.valueOf(101010), "privacynotremind");
        LogUtil.a("Suggestion_CoachActivity", "dialog not remind:", b);
        LogUtil.a("Suggestion_CoachActivity", "mValid:", Boolean.valueOf(this.b.ak()));
        return (!this.b.ak() || this.r || "true".equals(b)) ? false : true;
    }

    private void aa() {
        mmt mmtVar = new mmt();
        if (CoachController.d().i()) {
            if (this.mHeartRateHandler != null) {
                this.u.saveHeartRateDataList(this.mHeartRateHandler.getHeartRateList());
                this.u.saveInvalidHeartRateList(this.mHeartRateHandler.getInvalidHeartRateList());
                mmtVar.a(this.mHeartRateHandler.getAverageHeartRate());
            }
            mmtVar.c(this.mStartTrainTime);
            mmtVar.j(this.mClassifyMethod);
            mmtVar.g(this.mHeartRatePosture);
        }
        if (!TextUtils.isEmpty(this.u.acquirePlanId())) {
            this.u.setPlanTrainDate(DateFormatUtil.b(this.w));
        }
        mmtVar.b(this.u.acquireCategory());
        if (this.p) {
            this.u.setSportRecordType(1);
        }
        this.u.setLinkWear(this.mCaloriesConsumeHandler.a());
        this.u.saveExtend(mmtVar, false);
        this.u.saveWorkoutPackageId(this.z);
        this.u.saveCourseBelongType(this.h);
        this.u.setStartTime(this.mStartTrainTime);
        ag();
        if (!TextUtils.isEmpty(this.u.acquireWorkoutId())) {
            LogUtil.a("Suggestion_CoachActivity", "workoutId:", this.u.acquireWorkoutId());
            this.mCachedThreadPool.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.12
                @Override // java.lang.Runnable
                public void run() {
                    CoachActivity coachActivity = CoachActivity.this;
                    coachActivity.c(coachActivity.u);
                }
            });
        }
        m();
        ary.a().e("WORKOUT_FINISHED");
    }

    private void ag() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fkl
            @Override // java.lang.Runnable
            public final void run() {
                CoachActivity.this.f();
            }
        });
    }

    public /* synthetic */ void f() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_CoachActivity", "saveAndFinish, updatePlanProgress : planApi is null.");
            return;
        }
        planApi.setPlanType(0);
        planApi.updatePlanProgress(this.u);
        if (!TextUtils.isEmpty(this.u.acquirePlanId()) && !FitnessExternalUtils.a()) {
            LogUtil.a("Suggestion_CoachActivity", "planId:", this.u.acquirePlanId());
            planApi.setPlanType(3);
            planApi.updatePlanProgress(this.u);
        }
        fyr.d(0, fyr.b(this.u.startTime()));
    }

    private void m() {
        this.mCachedThreadPool.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.11
            @Override // java.lang.Runnable
            public void run() {
                DataPlatformApi dataPlatformApi = (DataPlatformApi) Services.a("CoursePlanService", DataPlatformApi.class);
                if (dataPlatformApi == null) {
                    LogUtil.h("Suggestion_CoachActivity", "executeThread dataPlatformApi is null.");
                    return;
                }
                boolean a2 = CoachActivity.this.mCaloriesConsumeHandler != null ? CoachActivity.this.mCaloriesConsumeHandler.a() : false;
                if (!a2) {
                    dataPlatformApi.insertCalorieData(CoachActivity.this.d, CoachActivity.this.u);
                    dataPlatformApi.insertCaloriePoints(CoachActivity.this.d, CoachActivity.this.mCaloriesConsumeHandler.c());
                }
                ReleaseLogUtil.e("Suggestion_CoachActivity", "executeThread isFormWear:", Boolean.valueOf(a2));
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi != null) {
                    courseApi.saveCourseTrainstatis(CoachActivity.this.u);
                }
                LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                if (loginInit.getUsetId() != null) {
                    RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
                    if (recordApi != null) {
                        recordApi.addRecordFor(loginInit.getUsetId(), CoachActivity.this.u, true);
                    } else {
                        LogUtil.h("Suggestion_CoachActivity", "executeThread recordApi is null.");
                    }
                }
            }
        });
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (!CoachActivity.this.n) {
                    if (!CoachActivity.this.s) {
                        if (!CoachActivity.this.y()) {
                            CoachActivity.this.ab();
                        }
                    } else {
                        CoachActivity.this.finish();
                        return;
                    }
                } else {
                    fis.d().e();
                    CoachActivity.this.n = false;
                }
                if (CoachActivity.this.b.f().c() == -100) {
                    CoachActivity.this.finish();
                }
            }
        });
        LogUtil.a("Suggestion_CoachActivity", "save record and updateprocess mRecord:", this.u, "  is plan: ", Boolean.valueOf(!TextUtils.isEmpty(r0.acquirePlanId())));
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        try {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, (Class<?>) FitnessResultActivity.class);
            if (getIntent() != null && getIntent().getBundleExtra("bundlekey") != null) {
                intent.putExtra("bundlekey", getIntent().getBundleExtra("bundlekey"));
            }
            bundle.putParcelable("workout_record", this.u);
            bundle.putBoolean("is_show_rpe", true);
            bundle.putLong("plan_execute_time", this.w);
            intent.putExtras(bundle);
            intent.putExtra("entrance", 0);
            startActivity(intent);
            overridePendingTransition(R.anim._2130772059_res_0x7f01005b, R.anim._2130771981_res_0x7f01000d);
        } catch (BadParcelableException | ConcurrentModificationException e) {
            LogUtil.b("Suggestion_CoachActivity", "bundle putParcelable failed with BadParcelableException or ConcurrentModification", LogAnonymous.b(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(WorkoutRecord workoutRecord) {
        FitWorkout workout;
        LogUtil.a("Suggestion_CoachActivity", "saveFitnessDataToOdmf");
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi == null || (workout = sportServiceApi.getWorkout(workoutRecord.acquireWorkoutId())) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        Iterator<Attribute> it = workout.acquireClasses().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            sb.append(",");
        }
        if (sb.length() >= 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        StringBuilder sb2 = new StringBuilder(16);
        Iterator<Attribute> it2 = workout.acquireTrainingpoints().iterator();
        while (it2.hasNext()) {
            sb2.append(it2.next().getName());
            sb2.append(",");
        }
        if (sb2.length() >= 1) {
            sb2.deleteCharAt(sb2.length() - 1);
        }
        int duration = workoutRecord.getDuration() / 1000;
        String b = DateFormatUtil.b(workoutRecord.acquireExerciseTime() - (duration * 1000), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT);
        int ceil = (int) Math.ceil(workoutRecord.acquireActualCalorie() / 1000.0d);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", 1001);
            jSONObject.put("SportStartTime", b);
            jSONObject.put("SportDuration", duration);
            jSONObject.put("HeatQuantity", ceil);
            jSONObject.put("BodyBuildingType", sb.toString());
            jSONObject.put("BodyBuildingPart", sb2.toString());
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).saveTrackDataToOdmf(jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b("Suggestion_CoachActivity", "Jsons parse error:", LogAnonymous.b((Throwable) e));
        }
    }

    public void j() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130848552_res_0x7f022b28)).czE_(getString(R$string.IDS_user_permission_know), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachActivity.this.ae();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
        LogUtil.a("Suggestion_CoachActivity", "showNoRecordDialog.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        if (this.n) {
            fis.d().e();
            CoachController.d().a(CoachController.StatusSource.APP, 3);
            finish();
            return;
        }
        this.b.finishTrain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        gnb popOutWindowInfo = userProfileMgrApi.getPopOutWindowInfo(this.d, "privacy_sport_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 >= 3 || currentTimeMillis - c <= com.huawei.openalliance.ad.constant.Constants.ANALYSIS_EVENT_KEEP_TIME) {
            CoachView coachView = this.b;
            if (coachView != null && coachView.f() != null) {
                this.b.f().a(-100);
            }
            aa();
            return;
        }
        View inflate = View.inflate(this.d, R.layout.sug_fitness_coach_dialog_post_data, null);
        userProfileMgrApi.setPopOutWindowInfo(this.d, "privacy_sport_data_");
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this.d).a(this.d.getString(R.string._2130848400_res_0x7f022a90)).cyp_(inflate).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: fke
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CoachActivity.this.ayV_(dialogInterface, i);
            }
        }).cyo_(R$string.IDS_user_permission_ok, new DialogInterface.OnClickListener() { // from class: fkg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CoachActivity.this.ayW_(dialogInterface, i);
            }
        }).c();
        this.v = c2;
        c2.setCancelable(false);
        this.v.show();
    }

    public /* synthetic */ void ayV_(DialogInterface dialogInterface, int i) {
        aa();
        this.v.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void ayW_(DialogInterface dialogInterface, int i) {
        this.r = true;
        ggs.b(3, true);
        aa();
        this.v.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void k() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fki
            @Override // java.lang.Runnable
            public final void run() {
                CoachActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        OpAnalyticsUtil.getInstance().setEventWithActionType(5, OperationKey.HEALTH_APP_RUN_END_2050006.value());
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("actiontype", String.valueOf(5));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_PVUV_85050001.value(), linkedHashMap);
        JSONObject jSONObject = new JSONObject();
        if (this.u == null) {
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        IntPlan currentIntPlan = planApi != null ? planApi.getCurrentIntPlan() : null;
        try {
            if (fpq.b(currentIntPlan, this.u.acquirePlanId())) {
                jSONObject.put("plan_name", currentIntPlan.getMetaInfo().getName());
                jSONObject.put("type", ggr.e(currentIntPlan));
            }
            if (gge.c()) {
                jSONObject.put("start_time", this.mStartTrainTime);
            }
            jSONObject.put("course_version", this.u.acquireVersion());
            jSONObject.put("calories", (int) this.u.acquireActualCalorie());
            jSONObject.put("duration", this.u.getDuration());
            jSONObject.put("isAICourse", ggr.d(this.u));
            WorkoutRecordViewModel.aFI_(getIntent(), jSONObject);
            this.u.getBiJson(jSONObject);
            HashMap hashMap = new HashMap(1);
            hashMap.put("data", jSONObject.toString());
            gge.e("1120005", hashMap);
        } catch (JSONException e) {
            LogUtil.b("Suggestion_CoachActivity", "e = ", LogAnonymous.b((Throwable) e));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("Suggestion_CoachActivity", "onResume");
        if (isSupportFoldingModel()) {
            String foldableState = HwScreenFoldDisplayMode.getFoldableState();
            this.k = foldableState;
            if ("HORIZONTAL_FOLDING_MODE".equals(foldableState)) {
                reloadView(this.k);
            }
        }
        if (ffy.c(this.b)) {
            if (this.b.getVisibility() == 0) {
                getWindow().getDecorView().setSystemUiVisibility(q());
            }
            if (!isLanguageChange() && this.m) {
                if (this.b.f().c() == 192) {
                    LogUtil.a("Suggestion_CoachActivity", "onResume() play again");
                    this.b.f().a(this.b.w());
                    this.b.g().setVisibility(4);
                }
                this.b.ar();
            }
        }
        super.onResume();
        e(this.d, true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("Suggestion_CoachActivity", "onPause");
        if (ffy.c(this.b)) {
            if (this.b.aj()) {
                this.m = true;
            } else {
                this.m = false;
            }
            this.b.ap();
        }
        super.onPause();
        e(this.d, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("Suggestion_CoachActivity", "onDestroy");
        setRequestedOrientation(1);
        if (this.u != null) {
            fhp.c().onChange(this.u.acquireWorkoutId(), 3, null);
        }
        this.b.as();
        super.onDestroy();
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Suggestion_CoachActivity", "onDestroy recordApi is null.");
            return;
        }
        recordApi.unregisterResultCallback();
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.markFitnessCourseStatusAvailable();
        }
        CourseEquipmentViewModel courseEquipmentViewModel = this.j;
        if (courseEquipmentViewModel != null) {
            courseEquipmentViewModel.a(this);
            this.j.unregisterAll();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Suggestion_CoachActivity", "onActivityResult ", Integer.valueOf(i), " ", Integer.valueOf(i2), " ", intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        if (this.b == null) {
            LogUtil.b("Suggestion_CoachActivity", "mCoachView is null.");
            return;
        }
        if (CoachController.d().f() && !this.b.ak()) {
            LogUtil.a("Suggestion_CoachActivity", "showNoRecordDialog.");
            if (this.m) {
                this.m = false;
                this.b.av();
            }
            j();
            fnu.b(this.e, 5, CoachController.StatusSource.NEW_LINK_WEAR);
            return;
        }
        this.b.finishTrain();
        fnu.b(this.e, 3, CoachController.StatusSource.NEW_LINK_WEAR);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected void refreshHeart() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fkf
            @Override // java.lang.Runnable
            public final void run() {
                CoachActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        this.mClassifyMethod = ffw.a(this.mHeartRatePosture);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected void initStatusHandler() {
        this.mStatusHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.fitness.activity.CoachActivity.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null || CoachActivity.this.b == null) {
                    LogUtil.b("Suggestion_CoachActivity", "message null.or mCoachView null");
                    return;
                }
                super.handleMessage(message);
                int c = CoachActivity.this.b.f().c();
                int i = message.what;
                if (i == 1) {
                    if (c == 194 || c == 195 || (c == 193 && !CoachController.d().g())) {
                        CoachActivity.this.n();
                    } else {
                        if (CoachActivity.this.f.c()) {
                            CoachActivity.this.f.a();
                        }
                        CoachActivity.this.b.au();
                    }
                    fnu.b(CoachActivity.this.e, 2, CoachController.StatusSource.NEW_LINK_WEAR);
                    return;
                }
                if (i == 2) {
                    CoachActivity.this.b.av();
                    fnu.b(CoachActivity.this.e, 1, CoachController.StatusSource.NEW_LINK_WEAR);
                } else {
                    if (i != 3) {
                        CoachActivity.this.ayR_(message);
                        return;
                    }
                    if (c == 194 || c == 195 || c == 193) {
                        CoachActivity.this.n();
                        CoachActivity.this.b.aa();
                    }
                    CoachActivity.this.ad();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ayR_(Message message) {
        if (!(message.obj instanceof CourseStateProto.CourseState)) {
            LogUtil.h("Suggestion_CoachActivity", "handleMessageOne msg.obj !instanceof CourseStateProto.CourseState");
            return;
        }
        CourseStateProto.CourseState courseState = (CourseStateProto.CourseState) message.obj;
        int i = message.what;
        if (i == 5) {
            o();
            return;
        }
        if (i == 6) {
            CoachView coachView = this.b;
            coachView.aDc_(coachView.aDa_(), true, CoachController.StatusSource.NEW_LINK_WEAR);
        } else if (i == 8 && this.mVolumeInteractive != null) {
            this.mVolumeInteractive.d(courseState.getCurrentVolume());
            if (this.mVolumeInteractive.e()) {
                CoachController.d().d(CoachController.StatusSource.APP, courseState.toBuilder());
            }
        }
    }

    private void o() {
        CoachView coachView = this.b;
        if (coachView == null) {
            LogUtil.h("Suggestion_CoachActivity", "changeToNextAction: mCoachView == null");
            return;
        }
        LogUtil.a("Suggestion_CoachActivity", "changeToNextAction getTrainStation:", Integer.valueOf(coachView.getTrainStation()), "mTempStation:", Integer.valueOf(this.b.w()));
        if (this.b.getTrainStation() == 193) {
            this.b.al();
        } else if (this.b.w() == 193 && this.b.getTrainStation() == 192) {
            this.b.al();
        } else {
            CoachView coachView2 = this.b;
            coachView2.aDc_(coachView2.aCZ_(), false, CoachController.StatusSource.NEW_LINK_WEAR);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    public CoachViewInterface getCoachView() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        LogUtil.a("Suggestion_CoachActivity", "onConfigurationChanged, orientation = ", Integer.valueOf(i));
        if (isSupportFoldingModel()) {
            this.b.c(this.k);
        }
        if (nsn.ag(this.d) || i != 1) {
            return;
        }
        setRequestedOrientation(0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        LogUtil.a("Suggestion_CoachActivity", "onMultiWindowModeChanged isInMultiWindowMode: ", Boolean.valueOf(z));
        ah();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        CoachView coachView = this.b;
        if (coachView == null || coachView.s() == null) {
            LogUtil.h("Suggestion_CoachActivity", "changeVideoChanged mCoachView == null || mCoachView.acquireMediaHelper() == null");
        } else {
            this.b.post(new Runnable() { // from class: fkh
                @Override // java.lang.Runnable
                public final void run() {
                    CoachActivity.this.i();
                }
            });
        }
    }

    public /* synthetic */ void i() {
        MediaPlayer aCq_ = this.b.s().aCq_();
        if (aCq_ == null) {
            LogUtil.h("Suggestion_CoachActivity", "changeVideoChanged mediaPlayer == null");
        } else {
            this.b.s().onVideoSizeChanged(aCq_, aCq_.getVideoWidth(), aCq_.getVideoHeight());
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected WorkoutRecord getCurrentWorkout() {
        return this.u;
    }

    private void x() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("KEY_INTENT_EQUIPMENT_TYPE", 0);
        this.l = intExtra;
        if (intExtra != 3) {
            return;
        }
        this.p = true;
        this.b.setCaloreHeartViewsVisibility(0);
        v();
    }

    private void v() {
        CourseEquipmentViewModel courseEquipmentViewModel = (CourseEquipmentViewModel) new ViewModelProvider(this).get(CourseEquipmentViewModel.class);
        this.j = courseEquipmentViewModel;
        WorkoutRecord workoutRecord = this.u;
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_CoachActivity", "initEquipmentViewModel mRecord == null");
            this.j.d(this.l, "", "");
        } else {
            courseEquipmentViewModel.d(this.l, workoutRecord.acquireWorkoutId(), this.u.acquireWorkoutName());
        }
        this.j.c(this.ad);
        this.j.observeSportLifeCycle("Suggestion_CoachActivity", this);
        dds.c().c(new SkippingTargetMode(9, 0, null));
        this.j.onStartSport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ayT_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("Suggestion_CoachActivity", "notifyUpdateItemData bundle == null");
            return;
        }
        int i = bundle.getInt("fitnessRopeSpeed");
        int i2 = bundle.getInt("fitnessTime");
        int i3 = bundle.getInt("fitnessStumbling");
        int i4 = bundle.getInt("fitnessContinuousSkippingJump");
        int i5 = bundle.getInt("fitnessRopeTotalNumber");
        LogUtil.a("Suggestion_CoachActivity", "notifyUpdateItemData speed: ", Integer.valueOf(i), " time:", Integer.valueOf(i2), " stumbling:", Integer.valueOf(i3), " num:", Integer.valueOf(i4), " totalNumber: ", Integer.valueOf(i5));
        this.b.d(i5, i);
        int i6 = bundle.getInt("fitnessCalories");
        LogUtil.a("Suggestion_CoachActivity", "notifyUpdateItemData kiloCalories: ", Integer.valueOf(i6));
        float f = i6 * 1000.0f;
        this.mCaloriesConsumeHandler.c(f, f, CaloriesConsumeHandler.CaloriesSource.EQUIPMENT);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a("Suggestion_CoachActivity", "onStopSport mIsFinishPageHasBeenShowed:", Boolean.valueOf(this.t));
        if (this.t) {
            return;
        }
        this.t = true;
        this.b.post(new Runnable() { // from class: fkj
            @Override // java.lang.Runnable
            public final void run() {
                CoachActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        this.b.finishTrain();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        LogUtil.a("Suggestion_CoachActivity", "onResumeSport SportStatus=", Integer.valueOf(this.j.getSportStatus()), " isPlaying:", Boolean.valueOf(this.b.s().o()));
        this.j.onResumeSport();
        if (this.b.getTrainStation() == 194) {
            n();
        } else {
            if (this.b.s().o()) {
                return;
            }
            this.b.post(new Runnable() { // from class: fkd
                @Override // java.lang.Runnable
                public final void run() {
                    CoachActivity.this.e();
                }
            });
        }
    }

    public /* synthetic */ void e() {
        this.b.au();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        LogUtil.a("Suggestion_CoachActivity", "onPauseSport SportStatus=", Integer.valueOf(this.j.getSportStatus()), " isPlaying:", Boolean.valueOf(this.b.s().o()));
        this.j.onPauseSport();
        if (this.b.s().o()) {
            this.b.post(new Runnable() { // from class: fkc
                @Override // java.lang.Runnable
                public final void run() {
                    CoachActivity.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        this.b.av();
    }
}
