package com.huawei.health.suggestion.ui.fitness.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.google.gson.Gson;
import com.huawei.android.fsm.HwFoldScreenManagerEx;
import com.huawei.android.os.SystemPropertiesEx;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.breathe.bean.BreatheBean;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.SuggestionBaseCallback;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity;
import com.huawei.health.suggestion.ui.fitness.helper.HwScreenFoldDisplayMode;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.ash;
import defpackage.cbl;
import defpackage.fib;
import defpackage.fjg;
import defpackage.ggx;
import defpackage.gia;
import defpackage.kvq;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SportIntensityUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class BaseCoachActivity extends BaseStateActivity {
    private static final int SCHEDULE_TIME = 5000;
    private static final String TAG = "Suggestion_BaseCoachActivity";
    protected CaloriesConsumeHandler mCaloriesConsumeHandler;
    private long mCurrentHeartRateReportTime;
    private ScheduledExecutorService mExecutor;
    private HwScreenFoldDisplayMode.FoldableStateListener mFoldableStateListener;
    private HeartRateConfigHelper mHeartRateConfigHelper;
    protected fjg mHeartRateHandler;
    protected int mIntensityHeartRateThreshold;
    private NoTitleCustomAlertDialog mLanguageChangeHintDialog;
    protected long mPauseDuration;
    protected long mPauseTrainTime;
    private kvq mReportData;
    private volatile HwScreenFoldDisplayMode mScreenFoldDisplayMode;
    public long mStartTrainTime;
    protected Handler mStatusHandler;
    protected gia mVolumeInteractive;
    public long mLoadingDuration = 0;
    public long mLoadingStartTime = 0;
    public long mLoadingEndTime = 0;
    protected int mClassifyMethod = 1;
    protected int mHeartRatePosture = 1;
    protected long mPlayStartTime = 0;
    protected long mPlayEndTime = 0;
    protected boolean mIsPauseStatus = false;
    private boolean mIsFirst = true;
    private String mOriginalLanguage = MLAsrConstants.LAN_ZH;
    private int mHeartRate = -1;
    private IReportDataCallback mReportDataCallback = new IReportDataCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity.2
        @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
        public void onChange(kvq kvqVar) {
            BaseCoachActivity.this.mReportData = kvqVar;
            if (kvqVar != null) {
                kvqVar.j(BaseCoachActivity.this.mHeartRate);
                CoachController.d().c(kvqVar);
                if (BaseCoachActivity.this.mCaloriesConsumeHandler != null) {
                    BaseCoachActivity.this.mCaloriesConsumeHandler.c(kvqVar.c(), kvqVar.a(), CaloriesConsumeHandler.CaloriesSource.LINK_WEAR);
                }
                if (BaseCoachActivity.this.mHeartRateHandler != null) {
                    BaseCoachActivity.this.mHeartRateHandler.pushHeartRate(kvqVar.j(), kvqVar.m());
                    BaseCoachActivity.this.mCurrentHeartRateReportTime = kvqVar.m();
                    return;
                }
                return;
            }
            LogUtil.b(BaseCoachActivity.TAG, "report data null.");
        }

        @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
        public void onResult() {
            LogUtil.a(BaseCoachActivity.TAG, "report data onResult.");
        }
    };
    private SuggestionBaseCallback mHeartRateReportCallback = new AnonymousClass3();

    public abstract CoachViewInterface getCoachView();

    protected WorkoutRecord getCurrentWorkout() {
        return null;
    }

    protected abstract void initStatusHandler();

    protected boolean isNotSupportFitnessLinkage() {
        return false;
    }

    protected abstract void refreshHeart();

    public abstract void reloadView(String str);

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements SuggestionBaseCallback {
        AnonymousClass3() {
        }

        @Override // com.huawei.health.suggestion.SuggestionBaseCallback
        public void onResponse(int i, Object obj) {
            if (obj == null) {
                LogUtil.h(BaseCoachActivity.TAG, "onResponse data == null");
                return;
            }
            if (obj instanceof HeartRateData) {
                HeartRateData heartRateData = (HeartRateData) obj;
                final int acquireHeartRate = heartRateData.acquireHeartRate();
                BaseCoachActivity.this.runOnUiThread(new Runnable() { // from class: fjy
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseCoachActivity.AnonymousClass3.this.b(acquireHeartRate);
                    }
                });
                BaseCoachActivity.this.mCurrentHeartRateReportTime = System.currentTimeMillis();
                BaseCoachActivity.this.mHeartRate = acquireHeartRate;
                if (BaseCoachActivity.this.mReportData == null && CoachController.d().i()) {
                    CoachController.d().d(acquireHeartRate);
                    if (BaseCoachActivity.this.mHeartRateHandler != null) {
                        BaseCoachActivity.this.mHeartRateHandler.pushHeartRate(acquireHeartRate, heartRateData.acquireTime());
                    }
                }
            }
        }

        public /* synthetic */ void b(int i) {
            if (BaseCoachActivity.this.getCoachView() != null) {
                BaseCoachActivity.this.getCoachView().updateHeartRate(i);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e(TAG, "onCreate");
        if (isSupportFoldingModel() && this.mFoldableStateListener == null) {
            LogUtil.h(TAG, "registerFoldableState");
            this.mFoldableStateListener = new HwScreenFoldDisplayMode.FoldableStateListener(this);
            this.mScreenFoldDisplayMode = new HwScreenFoldDisplayMode(this.mFoldableStateListener);
            HwFoldScreenManagerEx.registerFoldableState(this.mScreenFoldDisplayMode, 1);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public boolean isSupportFoldingModel() {
        if (!EnvironmentInfo.j()) {
            return false;
        }
        try {
            return SystemPropertiesEx.getBoolean("hw_mc.multiwindow.fat_hover", false);
        } catch (Exception e) {
            LogUtil.b(TAG, "isSupportFoldingModel Exception:", ExceptionUtils.d(e));
            return false;
        }
    }

    protected void startPlay() {
        ReleaseLogUtil.e(TAG, "startPlay");
        if (isNotSupportFitnessLinkage()) {
            LogUtil.a(TAG, "startPlay is explain Course Or Device Linkage Course");
            CoachController.d().e(false);
            return;
        }
        if (CoachController.d().i()) {
            CoachController.d().c();
            this.mHeartRateHandler = new fjg();
            setMaxHeartRate();
            fib.e().c(this.mReportDataCallback);
            CoachController.d().a(0L);
            initInteractorSchedule();
            initStatusHandler();
            initCoachStatusCallback();
            CoachController.d().a(CoachController.StatusSource.APP, 1);
            refreshHeart();
            gia giaVar = new gia();
            this.mVolumeInteractive = giaVar;
            giaVar.c();
        }
        fib.e().a(this.mHeartRateReportCallback, true ^ CoachController.d().i());
        startCheckHeartRateReport();
        setTrainingCourse();
    }

    protected void stopPlay() {
        ReleaseLogUtil.e(TAG, "stopPlay");
        if (isNotSupportFitnessLinkage()) {
            LogUtil.a(TAG, "stopPlay ", "is explainCourse Or Device Linkage Course");
            return;
        }
        if (CoachController.d().i()) {
            fib.e().d();
            Handler handler = this.mStatusHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
        }
        fib.e().e(this.mHeartRateReportCallback, !CoachController.d().i());
        resetTimer();
        removeTrainingCourse();
    }

    protected void initCoachStatusCallback() {
        CoachController.d().d(CoachController.StatusSource.NEW_LINK_WEAR, new CourseLinkageLifecycle() { // from class: com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity.1
            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void start(int i, Bundle bundle) {
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void resume(int i) {
                ReleaseLogUtil.e(BaseCoachActivity.TAG, "sendMessage, wear command = resume");
                if (BaseCoachActivity.this.mStatusHandler != null) {
                    BaseCoachActivity.this.mStatusHandler.sendEmptyMessage(1);
                }
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void pause(int i) {
                ReleaseLogUtil.e(BaseCoachActivity.TAG, "sendMessage, wear command = pause");
                if (BaseCoachActivity.this.mStatusHandler != null) {
                    BaseCoachActivity.this.mStatusHandler.sendEmptyMessage(2);
                }
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void stop(int i) {
                ReleaseLogUtil.e(BaseCoachActivity.TAG, "sendMessage, wear command = stop");
                if (BaseCoachActivity.this.mStatusHandler != null) {
                    BaseCoachActivity.this.mStatusHandler.sendEmptyMessage(3);
                }
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void chapterForward(CourseStateProto.CourseState courseState) {
                BaseCoachActivity.this.sendMessage(5, courseState);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void chapterBackward(CourseStateProto.CourseState courseState) {
                BaseCoachActivity.this.sendMessage(6, courseState);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void volumeAdjust(CourseStateProto.CourseState courseState) {
                BaseCoachActivity.this.sendMessage(8, courseState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i, CourseStateProto.CourseState courseState) {
        ReleaseLogUtil.e(TAG, "sendMessage, command = ", Integer.valueOf(i));
        if (this.mStatusHandler != null) {
            Message message = new Message();
            message.what = i;
            message.obj = courseState;
            this.mStatusHandler.sendMessage(message);
        }
    }

    protected void initInteractorSchedule() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        this.mExecutor.scheduleAtFixedRate(new Runnable() { // from class: fka
            @Override // java.lang.Runnable
            public final void run() {
                BaseCoachActivity.this.m506x31080461();
            }
        }, 500L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: lambda$initInteractorSchedule$0$com-huawei-health-suggestion-ui-fitness-activity-BaseCoachActivity, reason: not valid java name */
    public /* synthetic */ void m506x31080461() {
        int a2 = CoachController.d().a();
        if (a2 == 1) {
            CoachController.d().a(CoachController.d().e() + 1);
            fjg fjgVar = this.mHeartRateHandler;
            if (fjgVar != null) {
                fjgVar.saveHeartRate();
            }
            CoachController.d().axL_(CoachController.d().axM_(a2));
        }
        if (a2 == 2) {
            this.mHeartRateHandler.c(true);
        }
    }

    protected void resetTimer() {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mExecutor = null;
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ReleaseLogUtil.e(TAG, "onResume");
        if (this.mIsFirst) {
            this.mIsFirst = false;
            String u = CommonUtil.u();
            this.mOriginalLanguage = u;
            LogUtil.a(TAG, "onResume, mOriginalLanguage = ", u);
        }
        super.onResume();
    }

    protected boolean isLanguageChange() {
        if (this.mIsFirst) {
            return false;
        }
        if (!this.mOriginalLanguage.equals(CommonUtil.u())) {
            showChangeHintDialog();
            return true;
        }
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.mLanguageChangeHintDialog;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.mLanguageChangeHintDialog.dismiss();
        }
        return false;
    }

    private void showChangeHintDialog() {
        LogUtil.a(TAG, "showChangeHintDialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130848740_res_0x7f022be4)).czE_(getString(R.string._2130839504_res_0x7f0207d0).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: fjx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseCoachActivity.this.m508xdd2638e4(view);
            }
        }).e();
        this.mLanguageChangeHintDialog = e;
        e.setCancelable(false);
        this.mLanguageChangeHintDialog.show();
    }

    /* renamed from: lambda$showChangeHintDialog$1$com-huawei-health-suggestion-ui-fitness-activity-BaseCoachActivity, reason: not valid java name */
    public /* synthetic */ void m508xdd2638e4(View view) {
        if (getCoachView() != null) {
            LogUtil.a(TAG, "languageChange, coachView finishTrain.");
            getCoachView().finishTrain();
        } else {
            LogUtil.a(TAG, "languageChange, activity finish");
            finish();
        }
        this.mLanguageChangeHintDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity
    public void finish() {
        ReleaseLogUtil.e(TAG, "finish()");
        if (getIntent() != null && getIntent().getBooleanExtra("moveTaskToBack", false)) {
            moveTaskToBack(true);
        }
        super.finish();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ReleaseLogUtil.e(TAG, "BaseCoachActivity onDestroy");
        stopPlay();
        super.onDestroy();
        if (isNotSupportFitnessLinkage()) {
            LogUtil.a(TAG, "onDestroy ", "is explainCourse Or Device Linkage Course");
        } else {
            CoachController.d().b(CoachController.StatusSource.NEW_LINK_WEAR);
            gia giaVar = this.mVolumeInteractive;
            if (giaVar != null) {
                giaVar.d();
            }
        }
        if (!isSupportFoldingModel() || this.mScreenFoldDisplayMode == null) {
            return;
        }
        HwFoldScreenManagerEx.unregisterFoldableState(this.mScreenFoldDisplayMode);
    }

    private void startCheckHeartRateReport() {
        boolean a2 = ggx.a(this, ggx.a());
        boolean isDeviceConnected = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).isDeviceConnected("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c");
        ReleaseLogUtil.e(TAG, "isConnectedHeartRateDeviceWear:", Boolean.valueOf(a2), "isNemo:", Boolean.valueOf(isDeviceConnected));
        if (a2 || isDeviceConnected) {
            if (this.mExecutor == null) {
                this.mExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            this.mExecutor.scheduleAtFixedRate(new AnonymousClass5(), 5000L, 5000L, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (System.currentTimeMillis() - BaseCoachActivity.this.mCurrentHeartRateReportTime > TimeUnit.SECONDS.toMillis(30L)) {
                BaseCoachActivity.this.runOnUiThread(new Runnable() { // from class: fjz
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseCoachActivity.AnonymousClass5.this.c();
                    }
                });
            }
        }

        public /* synthetic */ void c() {
            BaseCoachActivity.this.getCoachView().updateHeartRate(-1);
        }
    }

    private void setTrainingCourse() {
        WorkoutRecord currentWorkout = getCurrentWorkout();
        if (currentWorkout == null || StringUtils.g(currentWorkout.acquirePlanId())) {
            return;
        }
        LogUtil.a(TAG, "setTrainingCourse, record = ", currentWorkout);
        ash.a("intPlanTrainingCourse", new Gson().toJson(currentWorkout));
    }

    private void removeTrainingCourse() {
        LogUtil.a(TAG, "removeTrainingCourse");
        ash.d("intPlanTrainingCourse");
    }

    private void setMaxHeartRate() {
        this.mHeartRateConfigHelper = new HeartRateConfigHelper(10001, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: fkb
            @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
            public final void onInitComplete() {
                BaseCoachActivity.this.m507x681ee275();
            }
        });
    }

    /* renamed from: lambda$setMaxHeartRate$2$com-huawei-health-suggestion-ui-fitness-activity-BaseCoachActivity, reason: not valid java name */
    public /* synthetic */ void m507x681ee275() {
        HeartZoneConf a2 = this.mHeartRateConfigHelper.a();
        if (a2 != null) {
            this.mIntensityHeartRateThreshold = SportIntensityUtil.b().d(a2);
        }
    }

    protected void checkBreachStatus(CoachData coachData) {
        if (coachData == null) {
            LogUtil.h(TAG, "checkBreachStatus, coachData is empty.");
        } else if (coachData.isBreathCourse()) {
            cbl.a(new BreatheBean());
        }
    }

    protected void dealLastPauseTime() {
        long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.e(TAG, "dealLastPauseTime mPauseDuration:", Long.valueOf(this.mPauseDuration), ":mPauseTrainTime", Long.valueOf(this.mPauseTrainTime), "mStartTrainTime:", Long.valueOf(this.mStartTrainTime), "now:", Long.valueOf(currentTimeMillis), "mIsPauseStatus:", Boolean.valueOf(this.mIsPauseStatus));
        if (this.mIsPauseStatus) {
            long j = this.mPauseTrainTime;
            if (j != 0) {
                this.mPauseDuration = (this.mPauseDuration + currentTimeMillis) - j;
            }
        }
    }
}
