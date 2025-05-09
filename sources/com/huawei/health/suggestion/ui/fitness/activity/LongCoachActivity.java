package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
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
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.helper.HwScreenFoldDisplayMode;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.CoachWifiBroadcastReceiver;
import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.OnLongVideoChangeListener;
import com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.health.suggestion.ui.fitness.viewmodel.CourseEquipmentViewModel;
import com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.tencent.connect.share.QzonePublish;
import defpackage.arx;
import defpackage.ary;
import defpackage.ash;
import defpackage.ffw;
import defpackage.ffy;
import defpackage.fhp;
import defpackage.fis;
import defpackage.fno;
import defpackage.fnt;
import defpackage.fnu;
import defpackage.fnz;
import defpackage.foq;
import defpackage.fpa;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.gnb;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.squ;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class LongCoachActivity extends BaseCoachActivity implements SportLifecycle, SportBleStatus {

    /* renamed from: a, reason: collision with root package name */
    private String f3095a;
    private CustomAlertDialog ab;
    private int ac;
    private String ad;
    private foq ae;
    private float af;
    private WorkoutRecordViewModel ag;
    private BroadcastReceiver ah;
    private String ai;
    private int c;
    private CoachData d;
    private LongCoachView e;
    private int f;
    private int g;
    private CourseEquipmentViewModel h;
    private Context i;
    private int j;
    private Map<Long, Integer> k;
    private boolean l;
    private foq n;
    private int o;
    private boolean p;
    private List<Motion> u;
    private boolean v;
    private long w;
    private boolean s = false;
    private boolean t = false;
    private boolean y = false;
    private boolean q = false;
    private boolean r = false;
    private Observer<Bundle> z = new Observer() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity$$ExternalSyntheticLambda6
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            LongCoachActivity.this.azw_((Bundle) obj);
        }
    };
    private fnu.e b = new fnu.e();
    private String m = "NORMAL_MODE";
    private boolean x = false;
    private BroadcastReceiver aa = new BroadcastReceiver() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("Suggestion_LongCoachActivity", "intent is null");
                return;
            }
            ReleaseLogUtil.e("Suggestion_LongCoachActivity", "action=", intent.getAction(), " type=", intent.getStringExtra("sync_data_result_type"), " sync_id=", Long.valueOf(intent.getLongExtra("sync_data_result_id", 0L)), " result=", Boolean.valueOf(intent.getBooleanExtra("sync_data_result_success", true)));
        }
    };

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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        LogUtil.a("Suggestion_LongCoachActivity", "onMultiWindowModeChanged isInMultiWindowMode: ", Boolean.valueOf(z));
        aj();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        LongCoachView longCoachView = this.e;
        if (longCoachView == null || longCoachView.g() == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "changeVideoChanged mCoachView == null || mCoachView.acquireMediaHelper() == null");
        } else {
            this.e.post(new Runnable() { // from class: flh
                @Override // java.lang.Runnable
                public final void run() {
                    LongCoachActivity.this.j();
                }
            });
        }
    }

    public /* synthetic */ void j() {
        MediaPlayer aCq_ = this.e.g().aCq_();
        if (aCq_ == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "changeVideoChanged mediaPlayer == null");
        } else {
            this.e.g().onVideoSizeChanged(aCq_, aCq_.getVideoWidth(), aCq_.getVideoHeight());
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    public void reloadView(final String str) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.2
            @Override // java.lang.Runnable
            public void run() {
                LongCoachActivity.this.m = str;
                LongCoachActivity.this.e.d(LongCoachActivity.this.m);
                LongCoachActivity.this.aj();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public String getCurrFoldModel() {
        return this.m;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.markFitnessCourseStatusOccupied();
        }
        super.onCreate(bundle);
        clearBackgroundDrawable();
        getWindow().addFlags(1152);
        this.i = this;
        this.mStartTrainTime = System.currentTimeMillis();
        this.k = new HashMap(10);
        setVolumeControlStream(3);
        this.g = getWindow().getDecorView().getSystemUiVisibility();
        u();
        w();
        if (!nsn.ag(this.i)) {
            setRequestedOrientation(0);
        }
        p();
        startPlay();
        this.e.setValidListener(new Consumer() { // from class: fli
            @Override // com.huawei.framework.servicemgr.Consumer
            public final void accept(Object obj) {
                LongCoachActivity.this.d((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void d(Boolean bool) {
        if (bool.booleanValue()) {
            LogUtil.a("Suggestion_LongCoachActivity", "save video");
            ac();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        this.e.as();
        this.e.ao();
        if (nsn.ag(this.i)) {
            return;
        }
        setRequestedOrientation(0);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_fitness_activity_coach_long_video);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        this.e = (LongCoachView) findViewById(R.id.sug_fitness_long_coach_view);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected boolean isNotSupportFitnessLinkage() {
        return this.r || this.p;
    }

    private void p() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                intent.getIntExtra("coachstartposition", 0);
                this.l = intent.getBooleanExtra("havenexttrain", false);
                boolean booleanExtra = intent.getBooleanExtra("isafterrun", false);
                this.s = booleanExtra;
                LogUtil.a("Suggestion_LongCoachActivity", "initCoachData mIsAfterRun = ", Boolean.valueOf(booleanExtra));
                this.w = intent.getLongExtra("plan_execute_time", 0L);
                if (intent.getParcelableExtra(ContentTemplateRecord.MOTIONS) != null) {
                    CoachData coachData = (CoachData) intent.getParcelableExtra(ContentTemplateRecord.MOTIONS);
                    this.d = coachData;
                    checkBreachStatus(coachData);
                }
                this.c = intent.getIntExtra("commodityFlag", 0);
                this.v = intent.getBooleanExtra("ISPLANFIT", false);
                this.ai = intent.getStringExtra("workout_package_id");
                this.ac = intent.getIntExtra(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, 0);
                this.ad = intent.getStringExtra("resource_type");
                this.f = intent.getIntExtra("course_belong_type", 0);
                if (azr_(intent)) {
                    return;
                }
                if (intent.getBooleanExtra("islinkedfitness", false)) {
                    this.e.setDeviceConnected();
                }
                azy_(intent);
                azx_(intent);
                azt_(intent);
                azv_(intent);
            } catch (Exception e2) {
                LogUtil.b("Suggestion_LongCoachActivity", LogAnonymous.b((Throwable) e2));
            }
        }
        a(this.j);
        af();
        this.af = fnz.e();
        this.mCaloriesConsumeHandler = new CaloriesConsumeHandler(CoachController.d().i(), this.p, new e(this.e));
        this.e.setCaloriesConsumeHandler(this.mCaloriesConsumeHandler);
        azu_(intent);
    }

    public static class e implements CaloriesConsumeHandler.CaloriesRefreshCallback {
        private WeakReference<LongCoachView> b;

        e(LongCoachView longCoachView) {
            this.b = new WeakReference<>(longCoachView);
        }

        @Override // com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler.CaloriesRefreshCallback
        public void onRefresh(final float f) {
            final LongCoachView longCoachView;
            WeakReference<LongCoachView> weakReference = this.b;
            if (weakReference == null || (longCoachView = weakReference.get()) == null) {
                return;
            }
            if (HandlerExecutor.c()) {
                longCoachView.e(f);
            } else {
                HandlerExecutor.e(new Runnable() { // from class: fll
                    @Override // java.lang.Runnable
                    public final void run() {
                        LongCoachView.this.e(f);
                    }
                });
            }
        }
    }

    private void af() {
        CoachData coachData = this.d;
        if (coachData != null) {
            this.e.e(FitWorkout.acquireComeFrom(coachData.acquireWorkId()));
            this.e.setCalorieStartTime(this.d.getCalorieStartTime());
            ReleaseLogUtil.e("Suggestion_LongCoachActivity", "settingCoachView calorieStartTime:", Integer.valueOf(this.d.getCalorieStartTime()));
        }
        fhp.c().onChange(this.ag.a().acquireWorkoutId(), 5, null);
        this.e.setMotions(this.u);
        this.e.r();
        final String b = ash.b("is_first_to_coachacitivyt");
        runOnUiThread(new Runnable() { // from class: flj
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachActivity.this.e(b);
            }
        });
    }

    public /* synthetic */ void e(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e.c(true);
            ash.a("is_first_to_coachacitivyt", "notfirst");
        }
        updateViewController();
    }

    private void azx_(Intent intent) {
        CoachData coachData = this.d;
        fnu.e aBy_ = fnu.aBy_(intent, this.f3095a, coachData != null ? coachData.acquireWorkId() : "", ggr.d(this.ag.a()));
        this.b = aBy_;
        if (this.e != null) {
            aBy_.d(this.c);
            this.e.e(this.b);
        }
    }

    private void azv_(Intent intent) {
        if (!intent.hasExtra("IS_SUPPORT_RECORD") || intent.getIntExtra("IS_SUPPORT_RECORD", 0) == 0) {
            return;
        }
        getWindow().addFlags(8192);
        this.e.setScreenCastVisibility(8);
    }

    private void azt_(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("equipment_course", false);
        this.j = intent.getIntExtra("KEY_INTENT_COURSE_ENTRANCE", 0);
        this.o = intent.getIntExtra("KEY_INTENT_EQUIPMENT_TYPE", 0);
        LogUtil.a("Suggestion_LongCoachActivity", "initCoachData isEquipmentCourse = ", Boolean.valueOf(booleanExtra), " mCourseEntrance = ", Integer.valueOf(this.j), " mEquipmentType = ", Integer.valueOf(this.o));
        boolean z = booleanExtra || this.j == 1;
        this.e.setEquipmentCourseAndType(z, this.o);
        if (this.j == 1 && !this.r) {
            this.p = true;
            t();
        }
        if (!z || this.j == 1) {
            return;
        }
        JumpConnectHelper.c().c(true);
        LogUtil.a("Suggestion_LongCoachActivity", "initEquipmentData setLongCoachActivityRunning true");
    }

    private void t() {
        this.h = (CourseEquipmentViewModel) new ViewModelProvider(this).get(CourseEquipmentViewModel.class);
        WorkoutRecordViewModel workoutRecordViewModel = this.ag;
        if (workoutRecordViewModel == null || workoutRecordViewModel.a() == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "initEquipmentViewModel mWorkoutRecordViewModel null");
            this.h.d(this.o, "", this.f3095a);
        } else {
            this.h.d(this.o, this.ag.a().acquireWorkoutId(), this.f3095a);
        }
        this.h.c(this.z);
        this.e.b(this.o);
        this.h.observeSportLifeCycle("Suggestion_LongCoachActivity", this);
        this.h.e(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void azw_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "notifyUpdateItemData bundle == null");
            return;
        }
        int i = bundle.getInt("fitnessCalories");
        LogUtil.a("Suggestion_LongCoachActivity", "notifyUpdateItemData kiloCalories: ", Integer.valueOf(i));
        float f = i * 1000.0f;
        this.mCaloriesConsumeHandler.c(f, f, CaloriesConsumeHandler.CaloriesSource.EQUIPMENT);
        this.e.aDC_(bundle);
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onConnected() {
        nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R.string._2130845061_res_0x7f021d85));
        LongCoachView longCoachView = this.e;
        if (longCoachView != null) {
            longCoachView.setBlutoothConnected(true);
        }
    }

    @Override // com.huawei.health.sportservice.SportBleStatus
    public void onInterrupted() {
        nrh.c(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R.string._2130845060_res_0x7f021d84));
        LongCoachView longCoachView = this.e;
        if (longCoachView != null) {
            longCoachView.setBlutoothConnected(false);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        this.e.aq();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        this.e.ap();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        this.e.ar();
    }

    private void azy_(Intent intent) {
        String stringExtra = intent.getStringExtra("coach_detail_picture");
        this.f3095a = intent.getStringExtra("coach_detail_name");
        if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(this.f3095a)) {
            this.e.setCoachPictureAndName(stringExtra, this.f3095a);
        }
        int intExtra = intent.getIntExtra("long_coach_type", 3);
        this.r = FitWorkout.b.e(intExtra);
        this.e.setVideoType(intExtra);
        this.e.setVideoUrl(intent.getStringExtra("long_coach_video_url"));
        String stringExtra2 = intent.getStringExtra("long_coach_subtitle_url");
        LogUtil.a("Suggestion_LongCoachActivity", "subtitlesUrl null:", Boolean.valueOf(TextUtils.isEmpty(stringExtra2)));
        if (!TextUtils.isEmpty(stringExtra2)) {
            this.e.setSrtFile(new File(squ.k(stringExtra2)));
        }
        String backgroundMusicUrl = this.d.getBackgroundMusicUrl();
        if (TextUtils.isEmpty(backgroundMusicUrl)) {
            LogUtil.a("Suggestion_LongCoachActivity", "backgroundUrl isEmpty");
        } else {
            this.e.a(backgroundMusicUrl);
        }
    }

    private void azu_(Intent intent) {
        if (intent == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "initShowCalorieOrHearRateView intent null");
            return;
        }
        if (this.r) {
            this.e.c(0);
            this.e.e(0);
            this.e.setHeartRateAndCalorieVisibility(4);
            return;
        }
        this.e.c(intent.getIntExtra("long_coach_need_calories", 0));
        boolean aj = this.e.aj();
        this.e.e(intent.getIntExtra("long_coach_need_heart_rate", 0));
        if (aj) {
            this.e.setHeartRateAndCalorieVisibility(0);
        } else {
            this.e.setHeartRateAndCalorieVisibility(4);
        }
    }

    private void a(int i) {
        foq aBU_ = foq.aBU_(this);
        this.n = aBU_;
        aBU_.a(i == 1 ? R.layout.sug_fitness_coach_dialog_equipment_exit : R.layout.sug_fitness_coach_dialog_exit).aBW_(R.id.sug_coach_dialog_No, new View.OnClickListener() { // from class: flf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachActivity.this.azz_(view);
            }
        }).aBW_(R.id.sug_coach_dialog_yse, new View.OnClickListener() { // from class: flk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachActivity.this.azA_(view);
            }
        });
        this.n.aBV_().setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: flg
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                LongCoachActivity.this.azB_(dialogInterface);
            }
        });
        q();
    }

    public /* synthetic */ void azz_(View view) {
        getWindow().getDecorView().setSystemUiVisibility(fpa.c());
        fnu.a(this.b, 7);
        this.e.setClickStop(false);
        this.n.a();
        this.e.au();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azA_(View view) {
        this.n.a();
        fnu.a(this.b, 6);
        this.t = true;
        LongCoachView longCoachView = this.e;
        if (longCoachView == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        longCoachView.ax();
        if (this.t) {
            this.t = false;
            if (this.s) {
                fis.d().e();
                CoachController.d().a(CoachController.StatusSource.APP, 3);
                finish();
            } else {
                this.e.finishTrain();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azB_(DialogInterface dialogInterface) {
        getWindow().getDecorView().setSystemUiVisibility(fpa.c());
        this.n.a();
        this.e.au();
    }

    private void q() {
        foq aBU_ = foq.aBU_(this);
        this.ae = aBU_;
        aBU_.d(R.layout.sug_fitness_coach_dialog_exit).e(R.id.sug_dialog_tv_content, R.string._2130848734_res_0x7f022bde).d(R.id.sug_coach_dialog_yse, R$string.IDS_apphelp_pwindows_continue_button).aBW_(R.id.sug_coach_dialog_No, new View.OnClickListener() { // from class: flo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachActivity.this.azC_(view);
            }
        }).aBW_(R.id.sug_coach_dialog_yse, new View.OnClickListener() { // from class: fln
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachActivity.this.azD_(view);
            }
        });
        this.ae.aBV_().setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: flb
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                LongCoachActivity.this.azE_(dialogInterface);
            }
        });
    }

    public /* synthetic */ void azC_(View view) {
        getWindow().getDecorView().setSystemUiVisibility(fpa.c());
        this.ae.a();
        CoachController.d().a(CoachController.StatusSource.APP, 3);
        this.e.finishTrain();
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azD_(View view) {
        getWindow().getDecorView().setSystemUiVisibility(fpa.c());
        this.ae.a();
        this.t = true;
        this.e.setHasRemindMobileConnected(true);
        this.e.ao();
        LogUtil.a("Suggestion_LongCoachActivity", "initWifiDialog isHasRemindMobileConnected true");
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azE_(DialogInterface dialogInterface) {
        getWindow().getDecorView().setSystemUiVisibility(fpa.c());
        CoachController.d().a(CoachController.StatusSource.APP, 3);
        finish();
    }

    private boolean azr_(Intent intent) {
        WorkoutRecord workoutRecord = (WorkoutRecord) intent.getParcelableExtra("workoutrecord");
        WorkoutRecordViewModel workoutRecordViewModel = (WorkoutRecordViewModel) new ViewModelProvider(this).get(WorkoutRecordViewModel.class);
        this.ag = workoutRecordViewModel;
        workoutRecordViewModel.c(workoutRecord);
        CoachData coachData = this.d;
        if (coachData == null || workoutRecord == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "checkCoachData() mCoachData,mRecord is null");
            finish();
            return true;
        }
        List<Motion> acquireMotions = coachData.acquireMotions();
        this.u = acquireMotions;
        if (acquireMotions != null) {
            return false;
        }
        LogUtil.h("Suggestion_LongCoachActivity", "checkCoachData() mMotions is null");
        finish();
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void updateViewController() {
        this.e.e(new OnMotionChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.5
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionOver(Motion motion, int i) {
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionReady(Motion motion, int i) {
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionChanged(Motion motion, int i) {
                LogUtil.c("Suggestion_LongCoachActivity", "motionChanged()", Integer.valueOf(i));
                LongCoachActivity.this.mPlayEndTime = System.currentTimeMillis();
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionStart(Motion motion, int i) {
                LongCoachActivity.this.mPlayStartTime = System.currentTimeMillis();
                ReleaseLogUtil.e("Suggestion_LongCoachActivity", "onMotionStart()", Integer.valueOf(i));
                LongCoachActivity.this.mIsPauseStatus = false;
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionPause(Motion motion, int i) {
                LongCoachActivity.this.mIsPauseStatus = true;
                LongCoachActivity.this.mPauseTrainTime = System.currentTimeMillis();
                LongCoachActivity.this.z();
                ReleaseLogUtil.e("Suggestion_LongCoachActivity", "onMotionPause()", Integer.valueOf(i), "mPauseTrainTime:", Long.valueOf(LongCoachActivity.this.mPauseTrainTime));
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onMotionContinue(Motion motion, int i) {
                ReleaseLogUtil.e("Suggestion_LongCoachActivity", "onMotionContinue:", motion.acquireName(), "position:", Integer.valueOf(i));
                LongCoachActivity.this.mPlayStartTime = System.currentTimeMillis();
                LongCoachActivity.this.dealLastPauseTime();
                LongCoachActivity.this.mIsPauseStatus = false;
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onGroupFinish(Motion motion, int i, int i2) {
                LogUtil.c("Suggestion_LongCoachActivity", "onGroupFinish() A group of action completed, Total: ", Integer.valueOf(i2));
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onTrainOver(boolean z) {
                if (z && LongCoachActivity.this.e != null && !LongCoachActivity.this.e.z()) {
                    fnu.a(LongCoachActivity.this.b, 4);
                }
                LongCoachActivity.this.b(z);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnMotionChangeListener
            public void onCustomBeave() {
                LongCoachActivity.this.l();
            }
        });
        ab();
        finishLoading();
    }

    private void ab() {
        this.e.setLongVideoChangeListener(new OnLongVideoChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.4
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnLongVideoChangeListener
            public void onLongExplanationVideoComplete(int i, int i2) {
                if (i2 > 0) {
                    LongCoachActivity.this.ag.aFL_(Math.min(i, i2), i2, LongCoachActivity.this.getIntent(), LongCoachActivity.this.mStartTrainTime);
                    LongCoachActivity.this.finish();
                } else {
                    LongCoachActivity.this.finish();
                }
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnLongVideoChangeListener
            public void onLongExerciseVideoComplete(long j, int i) {
                if (i > 0) {
                    LongCoachActivity.this.ag.aFL_(j, i, LongCoachActivity.this.getIntent(), LongCoachActivity.this.mStartTrainTime);
                } else {
                    LongCoachActivity.this.finish();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!this.r && this.j == 1 && r()) {
            LogUtil.a("Suggestion_LongCoachActivity", "setTrainOver onStopSport");
            this.h.m134x32b3e3a1();
        }
        LogUtil.a("Suggestion_LongCoachActivity", "setTrainOver isValid: ", Boolean.valueOf(z));
        if (this.r) {
            LogUtil.a("Suggestion_LongCoachActivity", "setTrainOver isExplainCourse");
            finish();
        } else {
            if (!z) {
                if (y()) {
                    return;
                }
                LogUtil.a("Suggestion_LongCoachActivity", "setTrainOver finish()");
                finish();
                return;
            }
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        this.mPlayEndTime = System.currentTimeMillis();
        if (this.mPlayStartTime == 0 || this.mHeartRateHandler != null) {
            return;
        }
        LogUtil.c("Suggestion_LongCoachActivity", "saveFitnessIntensityToMap() mPlayStartTime != 0");
        fnt.d(this.k, this.mPlayStartTime, this.mPlayEndTime);
        x();
    }

    private void x() {
        this.mPlayStartTime = 0L;
        this.mPlayEndTime = 0L;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.e == null) {
            LogUtil.b("Suggestion_LongCoachActivity", "onBackPressed mCoachView null.");
            return;
        }
        if (s()) {
            return;
        }
        if (m()) {
            if (this.e.an()) {
                fnu.a(this.b, 3);
            }
            this.e.setClickStop(true);
            l();
            return;
        }
        f();
    }

    private boolean s() {
        LongCoachView longCoachView = this.e;
        if (longCoachView == null || !longCoachView.ae()) {
            return false;
        }
        LogUtil.a("Suggestion_LongCoachActivity", "onBackPressed isLocked true");
        return true;
    }

    private void f() {
        LongCoachView longCoachView = this.e;
        if (longCoachView != null) {
            longCoachView.ab();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (!this.e.an() && !this.r) {
            n();
        } else {
            this.e.finishTrain();
        }
    }

    private boolean r() {
        CourseEquipmentViewModel courseEquipmentViewModel = this.h;
        return (courseEquipmentViewModel == null || courseEquipmentViewModel.getSportStatus() == 3) ? false : true;
    }

    private boolean m() {
        return this.e.getTrainStation() == 191 || this.e.getTrainStation() == 190;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, android.app.Activity
    public void finish() {
        super.finish();
        if (((this.s && this.e.an()) || v()) && !this.x) {
            this.x = true;
            fis.d().e();
        } else if (this.l) {
            Intent intent = new Intent(this, (Class<?>) TrainDetail.class);
            intent.putExtra("finish", false);
            startActivity(intent);
        } else {
            LogUtil.c("Suggestion_LongCoachActivity", "finish() is not after run or have next train");
        }
        setRequestedOrientation(1);
    }

    private boolean v() {
        return this.e.an() && this.p;
    }

    private void n() {
        LogUtil.a("Suggestion_LongCoachActivity", "ifExits");
        fnu.a(this.b, 5);
        this.n.a(this.m);
        this.e.av();
        this.e.at();
    }

    private void i() {
        dealLastPauseTime();
        this.ag.a(this.u, this.d, this.mStartTrainTime, this.mPauseDuration, this.mLoadingDuration);
        if (!this.p && !this.r) {
            LogUtil.c("Suggestion_LongCoachActivity", "calculateTrain() saveFitnessIntensityToMap");
            ad();
        }
        getWindow().clearFlags(1024);
        getWindow().getDecorView().setSystemUiVisibility(this.g);
        h();
    }

    private void ad() {
        this.mPlayEndTime = System.currentTimeMillis();
        if (this.mHeartRateHandler != null && koq.c(this.mHeartRateHandler.getHeartRateList())) {
            fnt.d(this.k, this.mHeartRateHandler.getHeartRateList(), this.mIntensityHeartRateThreshold);
        } else if (this.mPlayStartTime != 0) {
            fnt.d(this.k, this.mPlayStartTime, this.mPlayEndTime);
        } else {
            LogUtil.h("Suggestion_LongCoachActivity", "saveIntensityToMap() no saveFitnessIntensityToMap");
        }
        fnt.a(this.k);
    }

    private void h() {
        this.ag.e(this.mCaloriesConsumeHandler.b(), this.mCaloriesConsumeHandler.d(), fno.a(this.u, this.af), this.p);
        if (y()) {
            LogUtil.a("Suggestion_LongCoachActivity", "analyzeData showPrivacyDialog");
            runOnUiThread(new Runnable() { // from class: flc
                @Override // java.lang.Runnable
                public final void run() {
                    LongCoachActivity.this.c();
                }
            });
        } else {
            LogUtil.a("Suggestion_LongCoachActivity", "analyzeData saveAndFinish");
            aa();
        }
    }

    private boolean y() {
        this.y = ggs.b(3);
        String b = SharedPreferenceManager.b(arx.b(), String.valueOf(101010), "privacynotremind");
        LogUtil.a("Suggestion_LongCoachActivity", "isNeedDialog() dialog not remind:", b, "mValid:", Boolean.valueOf(this.e.an()));
        return (!this.e.an() || this.y || "true".equals(b)) ? false : true;
    }

    private void aa() {
        this.ag.d(this.ai, this.f);
        WorkoutRecord e2 = this.ag.e(this.mHeartRateHandler, this.mStartTrainTime, this.mClassifyMethod, this.w, this.mHeartRatePosture);
        e2.setLinkWear(this.mCaloriesConsumeHandler.a());
        this.ag.e(e2, this.mCachedThreadPool);
        this.ag.c(this.i, this.mCachedThreadPool);
        this.ag.e(this.mCaloriesConsumeHandler.c(), this.mCachedThreadPool);
        k();
        ary.a().e("WORKOUT_FINISHED");
        ac();
    }

    private void ac() {
        if (TextUtils.isEmpty(this.ad)) {
            LogUtil.a("Suggestion_LongCoachActivity", "resource type is empty, return");
        } else {
            this.ag.a(this.ad, this.ac);
        }
    }

    private void k() {
        if (!this.s) {
            if (this.v) {
                finish();
                return;
            } else {
                LogUtil.a("Suggestion_LongCoachActivity", "executeThread : startFitnessResultActivity");
                if (!v()) {
                    ag();
                }
            }
        } else {
            LogUtil.a("Suggestion_LongCoachActivity", "executeThread : showTrackAfterSketch");
            fis.d().e();
            this.s = false;
        }
        if (this.e.b().c() == -100) {
            LogUtil.a("Suggestion_LongCoachActivity", "executeThread : TRAIN_OVER");
            finish();
        }
        LogUtil.a("Suggestion_LongCoachActivity", "executeThread() save record and updateprocess mRecord:", this.ag.a(), "is plan: ", Boolean.valueOf(!TextUtils.isEmpty(this.ag.a().acquirePlanId())));
        this.ag.aFJ_(getIntent(), this.mStartTrainTime);
    }

    private void ag() {
        try {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, (Class<?>) FitnessResultActivity.class);
            if (getIntent() != null && getIntent().getBundleExtra("bundlekey") != null) {
                intent.putExtra("bundlekey", getIntent().getBundleExtra("bundlekey"));
            }
            bundle.putParcelable("workout_record", this.ag.a());
            bundle.putBoolean("is_show_rpe", true);
            bundle.putLong("plan_execute_time", this.w);
            intent.putExtras(bundle);
            intent.putExtra("entrance", 0);
            startActivity(intent);
        } catch (BadParcelableException | ConcurrentModificationException e2) {
            LogUtil.b("Suggestion_LongCoachActivity", "bundle putParcelable failed with BadParcelableException or ConcurrentModification", LogAnonymous.b(e2));
        }
    }

    public void g() {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130848552_res_0x7f022b28)).czE_(getString(com.huawei.health.servicesui.R$string.IDS_user_permission_know), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LongCoachActivity.this.s) {
                    LongCoachActivity.this.e.finishTrain();
                } else {
                    fis.d().e();
                    CoachController.d().a(CoachController.StatusSource.APP, 3);
                    LongCoachActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
        LogUtil.a("Suggestion_LongCoachActivity", "showNoRecordDialog.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ah, reason: merged with bridge method [inline-methods] */
    public void c() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        gnb popOutWindowInfo = userProfileMgrApi.getPopOutWindowInfo(this.i, "privacy_sport_data_");
        int a2 = popOutWindowInfo.a();
        long c = popOutWindowInfo.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 >= 3 || currentTimeMillis - c <= Constants.ANALYSIS_EVENT_KEEP_TIME) {
            LongCoachView longCoachView = this.e;
            if (longCoachView != null && longCoachView.b() != null) {
                this.e.b().c(-100);
            }
            aa();
            return;
        }
        View inflate = View.inflate(this.i, R.layout.sug_fitness_coach_dialog_post_data, null);
        userProfileMgrApi.setPopOutWindowInfo(this.i, "privacy_sport_data_");
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(this.i).a(this.i.getString(R.string._2130848400_res_0x7f022a90)).cyp_(inflate).cyn_(R$string.IDS_common_disagree, new DialogInterface.OnClickListener() { // from class: fle
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LongCoachActivity.this.azF_(dialogInterface, i);
            }
        }).cyo_(R$string.IDS_user_permission_ok, new DialogInterface.OnClickListener() { // from class: fld
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LongCoachActivity.this.azG_(dialogInterface, i);
            }
        }).c();
        this.ab = c2;
        c2.setCancelable(false);
        this.ab.show();
    }

    public /* synthetic */ void azF_(DialogInterface dialogInterface, int i) {
        aa();
        this.ab.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void azG_(DialogInterface dialogInterface, int i) {
        this.y = true;
        ggs.b(3, true);
        aa();
        this.ab.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("Suggestion_LongCoachActivity", "onResume");
        if (isSupportFoldingModel()) {
            String foldableState = HwScreenFoldDisplayMode.getFoldableState();
            this.m = foldableState;
            if ("HORIZONTAL_FOLDING_MODE".equals(foldableState)) {
                reloadView(this.m);
            }
        }
        if (ffy.c(this.e)) {
            if (this.e.getVisibility() == 0) {
                getWindow().getDecorView().setSystemUiVisibility(fpa.c());
            }
            if (!isLanguageChange() && this.q) {
                if (this.e.b().c() == 192) {
                    LogUtil.a("Suggestion_LongCoachActivity", "onResume() play again");
                    this.e.b().c(this.e.t());
                    this.e.d().setVisibility(4);
                }
                this.e.ao();
            }
        }
        super.onResume();
        fpa.c(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.a("Suggestion_LongCoachActivity", "onPause");
        if (ffy.c(this.e)) {
            if (this.e.af()) {
                this.q = true;
            } else {
                this.q = false;
            }
            this.e.as();
        }
        super.onPause();
        fpa.c(false);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity, com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("Suggestion_LongCoachActivity", "onDestroy");
        JumpConnectHelper.c().c(false);
        setRequestedOrientation(1);
        WorkoutRecordViewModel workoutRecordViewModel = this.ag;
        if (workoutRecordViewModel != null) {
            workoutRecordViewModel.d();
            this.ag.d(this);
        }
        CourseEquipmentViewModel courseEquipmentViewModel = this.h;
        if (courseEquipmentViewModel != null) {
            courseEquipmentViewModel.a(this);
        }
        this.e.aw();
        super.onDestroy();
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "onDestroy recordApi is null.");
            return;
        }
        recordApi.unregisterResultCallback();
        ae();
        ai();
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_LongCoachActivity", "onDestroy : courseApi is null.");
        } else {
            courseApi.markFitnessCourseStatusAvailable();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Suggestion_LongCoachActivity", "onActivityResult ", Integer.valueOf(i), " ", Integer.valueOf(i2), " ", intent);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected void refreshHeart() {
        WorkoutRecordViewModel workoutRecordViewModel = this.ag;
        if (workoutRecordViewModel == null || workoutRecordViewModel.a() == null) {
            return;
        }
        int acquireCategory = this.ag.a().acquireCategory();
        LogUtil.a("Suggestion_LongCoachActivity", "refreshHeart sportType = ", Integer.valueOf(acquireCategory));
        this.e.setSportType(acquireCategory == 137 ? 137 : 10001);
        this.mHeartRatePosture = acquireCategory == 137 ? 4 : 1;
        ThreadPoolManager.d().execute(new Runnable() { // from class: flm
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        this.mClassifyMethod = ffw.a(this.mHeartRatePosture);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected void initStatusHandler() {
        this.mStatusHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null || LongCoachActivity.this.e == null) {
                    LogUtil.b("Suggestion_LongCoachActivity", "message null.or coach view null.");
                    return;
                }
                super.handleMessage(message);
                int i = message.what;
                if (i == 1) {
                    if (LongCoachActivity.this.n != null && LongCoachActivity.this.n.c()) {
                        LongCoachActivity.this.n.a();
                    }
                    LongCoachActivity.this.e.au();
                    fnu.b(LongCoachActivity.this.b, 2, CoachController.StatusSource.NEW_LINK_WEAR);
                    return;
                }
                if (i == 2) {
                    LongCoachActivity.this.e.ba();
                    fnu.b(LongCoachActivity.this.b, 1, CoachController.StatusSource.NEW_LINK_WEAR);
                } else if (i != 3) {
                    LongCoachActivity.this.azs_(message);
                } else {
                    LongCoachActivity.this.o();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void azs_(Message message) {
        if (!(message.obj instanceof CourseStateProto.CourseState)) {
            LogUtil.h("Suggestion_LongCoachActivity", "handleMessageOne !instanceof CourseStateProto.CourseState");
            return;
        }
        CourseStateProto.CourseState courseState = (CourseStateProto.CourseState) message.obj;
        int i = message.what;
        if (i == 5) {
            this.e.y();
            return;
        }
        if (i == 6) {
            this.e.w();
            return;
        }
        if (i == 8 && this.mVolumeInteractive != null) {
            this.mVolumeInteractive.d(courseState.getCurrentVolume());
            if (this.mVolumeInteractive.e()) {
                CoachController.d().d(CoachController.StatusSource.APP, courseState.toBuilder());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (CoachController.d().f() && !this.e.an()) {
            LogUtil.a("Suggestion_LongCoachActivity", "showNoRecordDialog.");
            if (this.q) {
                this.q = false;
                this.e.ba();
            }
            g();
            fnu.b(this.b, 5, CoachController.StatusSource.NEW_LINK_WEAR);
            return;
        }
        this.e.finishTrain();
        fnu.b(this.b, 3, CoachController.StatusSource.NEW_LINK_WEAR);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    private void u() {
        if (this.ah == null) {
            this.ah = new CoachWifiBroadcastReceiver(this);
        }
        LogUtil.a("Suggestion_LongCoachActivity", "Enter registerWifiBroadcast()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.ah, intentFilter);
    }

    private void ae() {
        if (this.ah != null) {
            LogUtil.a("Suggestion_LongCoachActivity", "Enter unregisterWifiBroadcast()");
            try {
                unregisterReceiver(this.ah);
            } catch (IllegalArgumentException e2) {
                LogUtil.h("Suggestion_LongCoachActivity", LogAnonymous.b((Throwable) e2));
            } catch (RuntimeException e3) {
                LogUtil.h("Suggestion_LongCoachActivity", LogAnonymous.b((Throwable) e3));
            }
            this.ah = null;
        }
    }

    private void w() {
        LogUtil.a("Suggestion_LongCoachActivity", "Enter registerSyncBroadcast()");
        BroadcastManagerUtil.bFA_(this.i, this.aa, new IntentFilter("com.huawei.hihealth.action_sync_data_result"), "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    private void ai() {
        if (this.aa != null) {
            LogUtil.a("Suggestion_LongCoachActivity", "Enter unregisterWifiBroadcast()");
            try {
                unregisterReceiver(this.aa);
            } catch (IllegalArgumentException e2) {
                LogUtil.h("Suggestion_LongCoachActivity", LogAnonymous.b((Throwable) e2));
            } catch (RuntimeException e3) {
                LogUtil.h("Suggestion_LongCoachActivity", LogAnonymous.b((Throwable) e3));
            }
            this.aa = null;
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public LongCoachView getCoachView() {
        return this.e;
    }

    public foq a() {
        return this.ae;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        LogUtil.a("Suggestion_LongCoachActivity", "onConfigurationChanged, orientation = ", Integer.valueOf(i));
        this.e.d(this.m);
        if (nsn.ac(this.i) || (!nsn.ag(this.i) && i == 1)) {
            setRequestedOrientation(0);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity
    protected WorkoutRecord getCurrentWorkout() {
        WorkoutRecordViewModel workoutRecordViewModel = this.ag;
        if (workoutRecordViewModel != null) {
            return workoutRecordViewModel.a();
        }
        return null;
    }

    public boolean d() {
        foq foqVar = this.ae;
        return foqVar != null && foqVar.c();
    }
}
