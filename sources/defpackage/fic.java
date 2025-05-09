package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.FitnessCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Summary;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.DataPlatformApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.CourseUpdateListener;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.health.suggestion.config.MoveService;
import com.huawei.health.suggestion.data.DataDownloadService;
import com.huawei.health.suggestion.service.PlanSendService;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.api.SyncApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HealthSyncUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.up.model.UserInfomation;
import defpackage.fev;
import defpackage.fic;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = PluginSuggestion.class)
@Singleton
/* loaded from: classes4.dex */
public class fic extends PluginSuggestion {
    private BroadcastReceiver b;
    private H5ProWebView c;
    private BroadcastReceiver d;
    private BroadcastReceiver e;
    private BroadcastReceiver g;
    private boolean h;
    private List<Integer> i;
    private List<Integer> j = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private FitnessCallback f12522a = new FitnessCallback() { // from class: fii
        @Override // com.huawei.basefitnessadvice.callback.FitnessCallback
        public final void onChange(String str, int i, Bundle bundle) {
            fic.axO_(str, i, bundle);
        }
    };
    private final HiSubscribeListener f = new HiSubscribeListener() { // from class: fic.6
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a("Suggestion_PluginSuggestion", "mMergeDataSubscribeListener onResult successList");
                fic.this.h = true;
                fic.this.i = list;
            } else {
                LogUtil.b("Suggestion_PluginSuggestion", "onResult null.");
                fic.this.h = false;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("Suggestion_PluginSuggestion", "type:", Integer.valueOf(i), " changeKey:", str, " time:", Long.valueOf(j));
            kor.a().c(jdl.t(j), jdl.e(j), 0, new AnonymousClass4());
        }

        /* renamed from: fic$6$4, reason: invalid class name */
        class AnonymousClass4 implements IBaseResponseCallback {
            AnonymousClass4() {
            }

            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(final int i, final Object obj) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: fie
                    @Override // java.lang.Runnable
                    public final void run() {
                        fic.AnonymousClass6.AnonymousClass4.this.a(i, obj);
                    }
                });
            }

            /* synthetic */ void a(int i, Object obj) {
                fic.this.c(i, obj);
            }
        }
    };

    @Override // defpackage.mml
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    @Deprecated
    public void setTargetList(List<ffd> list) {
    }

    static /* synthetic */ void axO_(String str, int i, Bundle bundle) {
        if (i == 4) {
            final HashMap hashMap = new HashMap();
            if (bundle != null) {
                long j = bundle.getLong(FitnessCallback.BUNDLE_FITNESS_DURATION);
                hashMap.put(KakaConstants.FITNESS_DURATION, Long.valueOf(j));
                LogUtil.a("Suggestion_PluginSuggestion", "FitnessAdapter onChange workoutId = ", str, "  minutes=", Long.valueOf(j));
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: fif
                @Override // java.lang.Runnable
                public final void run() {
                    fis.d().b(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_FITNESS_BEHAVIOR), hashMap);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() != 2) {
            return;
        }
        startSendIntelligentPlan();
    }

    public fic() {
        LogUtil.a("Suggestion_PluginSuggestion", "PluginSuggestion enter");
        d dVar = new d(this);
        try {
            HealthSyncUtil.a(dVar);
        } catch (IllegalArgumentException unused) {
            LogUtil.h("Suggestion_PluginSuggestion", "add api is null");
        }
        try {
            HealthSyncUtil.b(dVar);
        } catch (IllegalArgumentException unused2) {
            LogUtil.h("Suggestion_PluginSuggestion", "add api is null");
        }
        cun.c().registerDataCallback(null, DataCallbackType.SUGGESTION_AIDL, "Suggestion_PluginSuggestion");
        gdq.b().c();
        f();
        g();
        h();
        d();
        j();
        n();
        e();
    }

    private void e() {
        MoveService.b(arx.b(), 1);
        asc.e().b(new Runnable() { // from class: fih
            @Override // java.lang.Runnable
            public final void run() {
                fic.this.b();
            }
        });
    }

    private void j() {
        LogUtil.a("Suggestion_PluginSuggestion", "registerAchieveModelListener");
        fhp.c().d("achievemodel", this.f12522a);
    }

    private void k() {
        LogUtil.a("Suggestion_PluginSuggestion", "unregisterAchieveModelListener");
        fhp.c().a("achievemodel");
    }

    private void f() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        this.b = new BroadcastReceiver() { // from class: fic.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("Suggestion_PluginSuggestion", "mDataSyncReceiver onReceive");
                if (intent == null || !"com.huawei.hihealth.action_data_refresh".equals(intent.getAction())) {
                    return;
                }
                LogUtil.a("Suggestion_PluginSuggestion", "onReceive HiBroadcastAction.ACTION_DATA_REFRESH");
                if (intent.getIntExtra("refresh_type", -1) != 2 || CommonUtil.x(context)) {
                    return;
                }
                LogUtil.a("Suggestion_PluginSuggestion", "onReceive HiBroadcastAction.ACTION_DATA_REFRESH TRACK_DATA");
                arx.b().startService(new Intent(arx.b(), (Class<?>) DataDownloadService.class));
            }
        };
        BroadcastManagerUtil.bFA_(arx.b(), this.b, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    private void g() {
        LogUtil.a("Suggestion_PluginSuggestion", "registerPhoneServiceBindedReceiver");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        this.g = new BroadcastReceiver() { // from class: fic.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("Suggestion_PluginSuggestion", "mPhoneServiceBindedReceiver onReceive");
                if (intent == null) {
                    LogUtil.b("Suggestion_PluginSuggestion", "mPhoneServiceBindedReceiver intent is null");
                } else if ("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS".equals(intent.getAction())) {
                    cun.c().registerDataCallback(null, DataCallbackType.SUGGESTION_AIDL, "Suggestion_PluginSuggestion");
                    fic.this.startSendIntelligentPlan();
                }
            }
        };
        BroadcastManagerUtil.bFC_(arx.b(), this.g, intentFilter, LocalBroadcast.c, null);
    }

    private void h() {
        LogUtil.a("Suggestion_PluginSuggestion", "registerDeviceStatusReceiver");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        this.d = new BroadcastReceiver() { // from class: fic.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DeviceInfo deviceInfo;
                LogUtil.a("Suggestion_PluginSuggestion", "mDeviceConnectStatusReceiver onReceive");
                if (intent == null) {
                    LogUtil.b("Suggestion_PluginSuggestion", "mDeviceConnectStatusReceiver intent is null");
                } else {
                    if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo")) == null) {
                        return;
                    }
                    LogUtil.a("Suggestion_PluginSuggestion", "mConnectStateChangedReceiver() status: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    fic.this.a(deviceInfo);
                }
            }
        };
        BroadcastManagerUtil.bFC_(arx.b(), this.d, intentFilter, LocalBroadcast.c, null);
    }

    private void d() {
        LogUtil.a("Suggestion_PluginSuggestion", "registerAccountDataSwitchReceiver");
        IntentFilter intentFilter = new IntentFilter("com.huawei.hihealth.action_account_login_datas_switch_finish");
        intentFilter.addAction("com.huawei.hihealth.action_account_login_datas_switch_finish");
        this.e = new BroadcastReceiver() { // from class: fic.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.b("Suggestion_PluginSuggestion", "mAccountDataSwitchReceiver intent is null");
                    return;
                }
                if ("com.huawei.hihealth.action_account_login_datas_switch_finish".equals(intent.getAction())) {
                    int intExtra = intent.getIntExtra("account_switch_status", 0);
                    LogUtil.a("Suggestion_PluginSuggestion", "mAccountDataSwitchReceiver onReceive,", Integer.valueOf(intExtra), Boolean.valueOf(fic.this.h));
                    if (intExtra != 0 || fic.this.h) {
                        return;
                    }
                    fic.this.n();
                }
            }
        };
        BroadcastManagerUtil.bFA_(arx.b(), this.e, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
    }

    @Override // defpackage.mml
    public void init(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        if (context == null) {
            LogUtil.h("Suggestion_PluginSuggestion", "the context is null");
            return;
        }
        if (FitnessExternalUtils.a()) {
            return;
        }
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_PluginSuggestion", "init : planApi is null.");
        } else {
            asc.e().b(new Runnable() { // from class: fic.1
                @Override // java.lang.Runnable
                public void run() {
                    fev.a.a("sportSuggestUrl", new GrsQueryCallback() { // from class: fic.1.3
                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackSuccess(String str) {
                            LogUtil.a("Suggestion_PluginSuggestion", "onCallBackSuccess url = ", str);
                            planApi.queryAllCompletedFitnessPlanFromCloud(new ResultCallback() { // from class: fic.1.3.2
                                @Override // com.huawei.health.suggestion.ResultCallback
                                public void onResult(int i, Object obj) {
                                    LogUtil.a("Suggestion_PluginSuggestion", "onResult resultCode = ", Integer.valueOf(i));
                                    LogUtil.a("Suggestion_PluginSuggestion", "onResult object = ", obj);
                                }
                            });
                        }

                        @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                        public void onCallBackFail(int i) {
                            LogUtil.h("Suggestion_PluginSuggestion", "onCallBackFail position = ", Integer.valueOf(i));
                        }
                    });
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public boolean isInitComplete() {
        return fib.e().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void b() {
        if (fib.e().c()) {
            SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
            if (sportServiceApi == null) {
                LogUtil.h("Suggestion_PluginSuggestion", "initModule sportServiceApi is null.");
                return;
            }
            sportServiceApi.syncData();
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Suggestion_PluginSuggestion", "planApi == null.");
            } else if (!AuthorizationUtils.a(BaseApplication.getContext())) {
                LogUtil.a("Suggestion_PluginSuggestion", "initModule getAuthorizationStatus: false");
            } else {
                planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: fic.7
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Suggestion_PluginSuggestion", "errorCode:", Integer.valueOf(i), " errorInfo:", str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(IntPlan intPlan) {
                        Object[] objArr = new Object[2];
                        objArr[0] = "onSuccess.";
                        objArr[1] = Boolean.valueOf(intPlan == null);
                        LogUtil.a("Suggestion_PluginSuggestion", objArr);
                        if (intPlan == null || !IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
                            return;
                        }
                        fic.this.startSendIntelligentPlan();
                    }
                });
            }
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void getFitWorkout(String str, String str2, UiCallback<FitWorkout> uiCallback) {
        fib.e().a(str, str2, uiCallback);
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void jumpToStartTrain(Context context, FitWorkout fitWorkout, PluginSuggestion pluginSuggestion) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null || fitWorkout == null || pluginSuggestion == null) {
            LogUtil.b("Suggestion_PluginSuggestion", "params invalid");
            return;
        }
        if (fitWorkout.isLongVideoCourse()) {
            LogUtil.a("Suggestion_PluginSuggestion", "jump to long coach video");
            JumpUtil.c(context, courseApi, fitWorkout);
        } else if (!JumpUtil.a(courseApi, fitWorkout)) {
            LogUtil.a("Suggestion_PluginSuggestion", "jump to course detail, coach video not download");
            JumpUtil.e(context, fitWorkout);
        } else if (fitWorkout.isRunModelCourse()) {
            LogUtil.a("Suggestion_PluginSuggestion", "jump to start train");
            JumpUtil.d(context, fitWorkout);
        } else {
            LogUtil.a("Suggestion_PluginSuggestion", "jump to short coach video");
            JumpUtil.c(context, fitWorkout);
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void updateRunSummary(Summary summary) {
        if (fib.e().c()) {
            ftk.e().a(summary, false);
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void realTimeGuidance(int i, List<Integer> list) {
        ftk.e().realTimeGuidance(i, list);
    }

    @Override // defpackage.mml
    public void finish() {
        LogUtil.a("Suggestion_PluginSuggestion", "finish.");
        if (!koq.b(this.i)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.i, new HiUnSubscribeListener() { // from class: fhz
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("Suggestion_PluginSuggestion", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("Suggestion_PluginSuggestion", "syncData()");
        asc.e().b(new Runnable() { // from class: fic.10
            @Override // java.lang.Runnable
            public void run() {
                SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
                if (sportServiceApi != null) {
                    sportServiceApi.updateAll();
                    sportServiceApi.syncData();
                }
                arx.b().startService(new Intent(arx.b(), (Class<?>) DataDownloadService.class));
                cei.b().syncDevice();
            }
        });
        LogUtil.a("Suggestion_PluginSuggestion", "syncData() end");
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public List<Map<String, Object>> getRecordsByDateRange(Date date, Date date2) {
        LogUtil.a("Suggestion_PluginSuggestion", "getRecordsByDateRange");
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi != null) {
            return recordApi.getRecordsByDateRange(date, date2);
        }
        return null;
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void deleteFitnessDataForStoreDemo() {
        if (Utils.g()) {
            LogUtil.a("Suggestion_PluginSuggestion", "deleteFitnessDataForStoreDemo isNoCloudVersion");
            return;
        }
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi != null) {
            recordApi.deleteAllWorkoutRecords();
            recordApi.deleteAllPlanRecords();
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void restoreUserDataForDemoVersion() {
        i();
    }

    private void i() {
        if (Utils.g()) {
            LogUtil.a("Suggestion_PluginSuggestion", "restoreFitnessRecord isNoCloudVersion");
            return;
        }
        String e = moh.e(moh.e("suggestion", "TrainList.txt"));
        LogUtil.c("Suggestion_PluginSuggestion", "workouStr = ", e);
        for (WorkoutRecord workoutRecord : ghb.c(e, WorkoutRecord[].class)) {
            if (workoutRecord == null) {
                LogUtil.b("Suggestion_PluginSuggestion", "record is null");
            } else {
                LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                if (loginInit.getUsetId() != null) {
                    LogUtil.b("Suggestion_PluginSuggestion", "accountInfo is not null");
                    workoutRecord.saveWorkoutDate(DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
                    workoutRecord.saveExerciseTime(System.currentTimeMillis());
                    UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
                    if (userProfileMgrApi == null) {
                        LogUtil.h("Suggestion_PluginSuggestion", "getUserInfo : userProfileMgrApi is null.");
                        return;
                    }
                    UserInfomation userInfo = userProfileMgrApi.getUserInfo();
                    if (userInfo == null) {
                        LogUtil.h("Suggestion_PluginSuggestion", "userInfo == null");
                        return;
                    }
                    e(userInfo, workoutRecord, loginInit);
                    DataPlatformApi dataPlatformApi = (DataPlatformApi) Services.a("CoursePlanService", DataPlatformApi.class);
                    if (dataPlatformApi == null) {
                        LogUtil.h("Suggestion_PluginSuggestion", "restoreFitnessRecord dataPlatformApi is null.");
                        return;
                    }
                    dataPlatformApi.insertCalorieData(arx.b(), workoutRecord);
                } else {
                    continue;
                }
            }
        }
    }

    private void e(UserInfomation userInfomation, WorkoutRecord workoutRecord, LoginInit loginInit) {
        if (!userInfomation.isWeightValid()) {
            LogUtil.h("Suggestion_PluginSuggestion", "restoreFitnessRecord !userInfo.isWeightValid()");
        }
        float weightOrDefaultValue = userInfomation.getWeightOrDefaultValue();
        LogUtil.c("Suggestion_PluginSuggestion", "userWeight = ", Float.valueOf(weightOrDefaultValue));
        workoutRecord.saveActualCalorie(workoutRecord.acquireActualCalorie() * weightOrDefaultValue);
        workoutRecord.saveCalorie(workoutRecord.acquireCalorie() * weightOrDefaultValue);
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi != null) {
            recordApi.insertWorkoutRecord(loginInit.getUsetId(), workoutRecord);
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void startSendIntelligentPlan() {
        LogUtil.h("Suggestion_PluginSuggestion", "startSendIntelligentRunPlan");
        ThreadPoolManager.d().d("Suggestion_PluginSuggestion", new Runnable() { // from class: fia
            @Override // java.lang.Runnable
            public final void run() {
                fic.this.a();
            }
        });
    }

    /* synthetic */ void a() {
        if (gij.c()) {
            LogUtil.h("Suggestion_PluginSuggestion", "start PlanSendService");
            m();
        }
    }

    private void m() {
        Intent intent = new Intent(arx.b(), (Class<?>) PlanSendService.class);
        if (!CommonUtil.x(arx.b())) {
            arx.b().startService(intent);
            ReleaseLogUtil.e("Suggestion_PluginSuggestion", "startService SDK < 8.0 || isForeground");
        } else {
            ReleaseLogUtil.e("Suggestion_PluginSuggestion", "startService SDK >= 8.0 and isBackground");
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public RecyclerView.Adapter getBaseRecyclerViewAdapter(List<RecordAction> list) {
        return new BaseRecyclerViewAdapter<RecordAction>(list, R.layout.sug_coach_item_fitness_finish_rcv) { // from class: fic.8
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, RecordAction recordAction) {
                fnt.a(recyclerHolder, recordAction);
            }
        };
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void jumpToLongCoach(Context context, int i) {
        if (context == null || i == 0) {
            LogUtil.h("Suggestion_PluginSuggestion", "jumpToStartTrain params invalid");
        } else if (i == 1) {
            JumpConnectHelper.c().d(context, i);
        } else {
            LogUtil.a("Suggestion_PluginSuggestion", "jumpToStartTrain courseEntrance", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public CourseUpdateListener getRunCourseVoiceManager(List<ChoreographedSingleAction> list, FitWorkout fitWorkout, boolean z, CourseEnvParams courseEnvParams) {
        ftd.e().b(list, fitWorkout, z, courseEnvParams);
        return ftd.e();
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void switchToPlanReport(String str) {
        gdq.b().c(str);
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void jumpToPlanTab() {
        JumpUtil.c(arx.b());
    }

    static class d implements SyncApi<Void> {
        WeakReference<fic> c;

        @Override // com.huawei.hihealth.api.SyncApi
        public void cleanCloud(boolean z, Object obj) {
        }

        public d(fic ficVar) {
            this.c = new WeakReference<>(ficVar);
        }

        @Override // com.huawei.hihealth.api.SyncApi
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Void syncCloud() {
            fic ficVar;
            WeakReference<fic> weakReference = this.c;
            if (weakReference == null || (ficVar = weakReference.get()) == null) {
                return null;
            }
            ficVar.l();
            return null;
        }

        @Override // com.huawei.hihealth.api.SyncApi
        public String getLabel() {
            return SyncApi.FITNESS;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        if (i != 0) {
            LogUtil.h("Suggestion_PluginSuggestion", "requestTrackSimplifyData not success");
            return;
        }
        if (obj == null) {
            LogUtil.h("Suggestion_PluginSuggestion", "requestTrackSimplifyData objData null");
            return;
        }
        List<HiHealthData> list = (List) obj;
        if (list.isEmpty() || list.get(0) == null) {
            LogUtil.h("Suggestion_PluginSuggestion", "requestTrackSimplifyData size 0");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                try {
                    MotionPathSimplify e = kwz.e(hiHealthData);
                    PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                    if (planApi == null) {
                        LogUtil.h("Suggestion_PluginSuggestion", "intPlanApi == null");
                        return;
                    } else if (e == null) {
                        LogUtil.h("Suggestion_PluginSuggestion", "motionPathSimplify == null");
                    } else if (e.requestSportDataSource() == 2) {
                        ReleaseLogUtil.d("Suggestion_PluginSuggestion", "motionPathSimplify is user input.");
                    } else if (e.requestAbnormalTrack() != 0) {
                        ReleaseLogUtil.d("Suggestion_PluginSuggestion", "motionPathSimplify is abnormal.");
                    } else {
                        b(e, planApi);
                    }
                } catch (NumberFormatException e2) {
                    ReleaseLogUtil.c("Suggestion_PluginSuggestion", "onDataResponse exception:", ExceptionUtils.d(e2));
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r14, com.huawei.health.plan.api.PlanApi r15) {
        /*
            r13 = this;
            java.lang.String r0 = "planInfo"
            java.lang.String r1 = ""
            java.lang.String r0 = r14.getExtendDataString(r0, r1)
            java.lang.String r2 = "plan_id"
            java.lang.String r2 = r14.getExtendDataString(r2, r1)
            boolean r3 = r1.equals(r0)
            java.lang.String r4 = "Suggestion_PluginSuggestion"
            if (r3 != 0) goto L2b
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L22
            r3.<init>(r0)     // Catch: org.json.JSONException -> L22
            java.lang.String r0 = "planId"
            java.lang.String r0 = r3.optString(r0)     // Catch: org.json.JSONException -> L22
            goto L2c
        L22:
            java.lang.String r0 = "toJson error"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r4, r0)
        L2b:
            r0 = r1
        L2c:
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L33
            goto L34
        L33:
            r2 = r0
        L34:
            mob r0 = defpackage.ase.e(r14)
            java.lang.String r5 = "updateDayRecord planId:"
            java.lang.String r7 = " workoutId:"
            java.lang.String r8 = r0.n()
            java.lang.String r9 = " startTime:"
            long r10 = r0.i()
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.String r11 = " planCourseTime:"
            java.lang.String r1 = "plan_course_time"
            int r14 = r14.getExtendDataInt(r1)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r14)
            r6 = r2
            java.lang.Object[] r14 = new java.lang.Object[]{r5, r6, r7, r8, r9, r10, r11, r12}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r4, r14)
            float r14 = defpackage.grz.a()
            double r3 = (double) r14
            r0.e(r3)
            r15.updateDayRecord(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fic.b(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify, com.huawei.health.plan.api.PlanApi):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.a("Suggestion_PluginSuggestion", "subscribeSportData");
        if (koq.b(this.j)) {
            ArrayList arrayList = new ArrayList();
            this.j = arrayList;
            arrayList.add(4);
        }
        HiHealthNativeApi.a(arx.b()).subscribeHiHealthData(this.j, this.f);
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void setH5ProWebView(H5ProWebView h5ProWebView) {
        this.c = h5ProWebView;
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public H5ProWebView getH5ProWebView() {
        return this.c;
    }

    @Override // com.huawei.health.suggestion.PluginSuggestion
    public void notifyActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("Suggestion_PluginSuggestion", "notifyActivityResult requestCode:", Integer.valueOf(i), " resultCode:", Integer.valueOf(i2), " mH5ProWebView:", this.c);
        if (this.c == null) {
            return;
        }
        H5ProBridgeManager.getInstance().notifyActivityResult(this.c.getInstance(), i, i2, intent);
    }
}
