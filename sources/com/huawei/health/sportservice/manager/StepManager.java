package com.huawei.health.sportservice.manager;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.model.IStepRateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.treadmill.CallBackToReportStepsOrEvent;
import com.huawei.up.model.UserInfomation;
import defpackage.fgm;
import defpackage.fhs;
import defpackage.gso;
import defpackage.gwg;
import defpackage.njl;
import defpackage.sqr;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.STEP_MANAGER)
/* loaded from: classes8.dex */
public class StepManager implements ManagerComponent {
    private static final int DEFAULT_HEIGHT = 170;
    private static final float DEFAULT_WEIGHT = 60.0f;
    private static final int GENDER_FEMALE = 0;
    private static final int GENDER_MALE = 1;
    private static final int GENDER_UNKNOWN = 2;
    private static final int MAX_HEIGHT = 300;
    private static final float MAX_WEIGHT = 200.0f;
    private static final int MIN_HEIGHT = 100;
    private static final float MIN_WEIGHT = 30.0f;
    private static final String TAG = "SportService_StepManager";
    private sqr mVibraStepCounterHelper;
    private IStepRateCallback mStepCallback = null;
    private fgm mSportCallbackOption = new fgm();
    private final CallBackToReportStepsOrEvent mCallbackToReportStepsOrEvent = new CallBackToReportStepsOrEvent() { // from class: com.huawei.health.sportservice.manager.StepManager$$ExternalSyntheticLambda1
        @Override // com.huawei.treadmill.CallBackToReportStepsOrEvent
        public final void onUpdateStepsOrEvent(int i, int i2) {
            StepManager.this.m476lambda$new$0$comhuaweihealthsportservicemanagerStepManager(i, i2);
        }
    };
    private PluginSportTrackAdapter mPluginTrackAdapter = gso.e().c();

    /* renamed from: lambda$new$0$com-huawei-health-sportservice-manager-StepManager, reason: not valid java name */
    /* synthetic */ void m476lambda$new$0$comhuaweihealthsportservicemanagerStepManager(int i, int i2) {
        IStepRateCallback iStepRateCallback = this.mStepCallback;
        if (iStepRateCallback != null) {
            iStepRateCallback.report(i, i2);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        if (this.mPluginTrackAdapter == null) {
            return;
        }
        if (fhs.d(BaseSportManager.getInstance().getSportType())) {
            if (CommonUtil.bd()) {
                initVibrationHelper();
            } else {
                initPhoneStep();
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("SPEED_DATA");
        arrayList.add("DISTANCE_DATA");
        arrayList.add("TIME_ONE_SECOND_DURATION");
        this.mSportCallbackOption.a(arrayList);
        this.mSportCallbackOption.c(TAG);
        BaseSportManager.getInstance().subscribeNotify(this.mSportCallbackOption, new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.StepManager$$ExternalSyntheticLambda3
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                StepManager.this.m477xfb74bc36(list, map);
            }
        });
    }

    /* renamed from: lambda$onPreSport$1$com-huawei-health-sportservice-manager-StepManager, reason: not valid java name */
    /* synthetic */ void m477xfb74bc36(List list, Map map) {
        if (list.contains("TIME_ONE_SECOND_DURATION")) {
            BaseSportManager baseSportManager = BaseSportManager.getInstance();
            int intValue = ((Integer) baseSportManager.getData("SPEED_DATA")).intValue();
            int intValue2 = ((Integer) baseSportManager.getData("DISTANCE_DATA")).intValue();
            sqr sqrVar = this.mVibraStepCounterHelper;
            if (sqrVar != null) {
                sqrVar.e(intValue * 10, intValue2, 0);
            }
        }
    }

    private void initPhoneStep() {
        if (gwg.i()) {
            this.mPluginTrackAdapter.regStepRateListener(new IStepRateCallback() { // from class: com.huawei.health.sportservice.manager.StepManager$$ExternalSyntheticLambda2
                @Override // com.huawei.healthcloud.plugintrack.model.IStepRateCallback
                public final void report(int i, int i2) {
                    StepManager.this.m474xea52d515(i, i2);
                }
            }, 5000);
        }
    }

    /* renamed from: lambda$initPhoneStep$2$com-huawei-health-sportservice-manager-StepManager, reason: not valid java name */
    /* synthetic */ void m474xea52d515(int i, int i2) {
        IStepRateCallback iStepRateCallback = this.mStepCallback;
        if (iStepRateCallback != null) {
            iStepRateCallback.report(i, i2);
        }
    }

    private void initVibrationHelper() {
        sqr d = sqr.d(BaseApplication.e());
        this.mVibraStepCounterHelper = d;
        if (d == null) {
            LogUtil.a(TAG, "mVibraStepCounterHelper getInstance failed");
            return;
        }
        final njl njlVar = new njl();
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null) {
            int gender = userInfo.getGender();
            int i = 1;
            if (gender == 1) {
                gender = 0;
            } else if (gender == 0) {
                gender = 1;
            } else {
                LogUtil.h(TAG, "Unknown state in startVibrateStepCount");
            }
            if (gender >= 0 && gender <= 2) {
                i = gender;
            }
            int height = userInfo.getHeight();
            if (height < 100 || height > 300) {
                height = 170;
            }
            float weight = userInfo.getWeight();
            if (weight < 30.0f || weight > 200.0f) {
                weight = 60.0f;
            }
            njlVar.d(i, (int) weight, height);
        }
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.sportservice.manager.StepManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                StepManager.this.m475xda2959ee(njlVar);
            }
        });
    }

    /* renamed from: lambda$initVibrationHelper$3$com-huawei-health-sportservice-manager-StepManager, reason: not valid java name */
    /* synthetic */ void m475xda2959ee(njl njlVar) {
        LogUtil.a(TAG, "VibrateSteps: startVibrateStepCount, isResult:", Boolean.valueOf(this.mVibraStepCounterHelper.c(njlVar, this.mCallbackToReportStepsOrEvent, 1000)));
    }

    public void initPhoneStep(IStepRateCallback iStepRateCallback) {
        this.mStepCallback = iStepRateCallback;
    }

    public void unregisterVibrateStepsCount() {
        sqr sqrVar = this.mVibraStepCounterHelper;
        if (sqrVar != null) {
            sqrVar.b();
            LogUtil.a(TAG, "VibrateSteps: stopVibrateStepsCount");
        }
        this.mVibraStepCounterHelper = null;
    }

    public void setAdapter(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.mPluginTrackAdapter = pluginSportTrackAdapter;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        unregisterVibrateStepsCount();
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        BaseSportManager.getInstance().cancelSubscribeNotify(this.mSportCallbackOption);
    }
}
