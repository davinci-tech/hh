package com.huawei.health.sportservice.manager;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.health.hearratecontrol.HeartRateControlConfigApi;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportCourseParams;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.dsy;
import defpackage.ffd;
import defpackage.ggs;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SportComponentType(classify = 1, name = ComponentName.COURSE_MANAGER)
/* loaded from: classes8.dex */
public class CourseManager implements ITargetUpdateListener, ManagerComponent {
    private static final String TAG = "SportService_CourseManager";
    private String mCourseId;
    private int mCurrentStageIndex = -1;
    private FitWorkout mFitWorkout;
    private List<ChoreographedSingleAction> mSingleActionList;
    private int mSportType;

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        LogUtil.d(TAG, "enter setParas");
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mSportType = sportLaunchParams.getSportType();
            String str = (String) sportLaunchParams.getExtra("HEART_RATE_CONTROL_COURSE_ID", String.class);
            this.mCourseId = str;
            LogUtil.d(TAG, "mCourseId = ", str);
            if (TextUtils.isEmpty(this.mCourseId)) {
                return;
            }
            updateCourseData();
            getCourseConfigInfo();
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.d(TAG, "enter onPreSport");
        BaseSportManager.getInstance().addObserver(SportObserverType.COURSE_TARGET_OBSERVE, TAG, this);
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void init() {
        LogUtil.d(TAG, "enter init");
    }

    private void initCourse() {
        LogUtil.d(TAG, "initCourse");
        courseInitDataChange();
        this.mCurrentStageIndex = -1;
        courseActionChange();
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        LogUtil.d(TAG, "onTargetDataUpdate");
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        LogUtil.d(TAG, "onStateUpdate");
        if (i == 201) {
            courseActionChange();
            return;
        }
        if (i == 200) {
            SportCourseParams sportCourseParams = new SportCourseParams();
            sportCourseParams.setCourseFinished(true);
            BaseSportManager.getInstance().setParas(SportParamsType.COURSE_INFO, sportCourseParams);
            LogUtil.d(TAG, "course finished");
            return;
        }
        LogUtil.d(TAG, "error type");
    }

    private void courseActionChange() {
        LogUtil.d(TAG, "enter courseActionChange");
        if (koq.b(this.mSingleActionList)) {
            LogUtil.d(TAG, "courseActionChange: mSingleActionList is empty");
        }
        int i = this.mCurrentStageIndex + 1;
        this.mCurrentStageIndex = i;
        if (koq.d(this.mSingleActionList, i)) {
            SportCourseParams sportCourseParams = new SportCourseParams();
            sportCourseParams.addExtra("COURSE_ACTION_INDEX", Integer.valueOf(this.mCurrentStageIndex));
            sportCourseParams.addExtra("COURSE_ACTION_DATA", this.mSingleActionList.get(this.mCurrentStageIndex));
            BaseSportManager.getInstance().setParas(SportParamsType.COURSE_INFO, sportCourseParams);
            LogUtil.d(TAG, "mCurrentStageIndex = ", Integer.valueOf(this.mCurrentStageIndex));
        }
    }

    private void setSingleActionList() {
        List<ChoreographedSingleAction> c = ggs.c(this.mFitWorkout);
        this.mSingleActionList = c;
        LogUtil.d(TAG, " mSingleActionList = ", c);
    }

    private void updateCourseData() {
        ((HeartRateControlConfigApi) Services.c("EcologyDevice", HeartRateControlConfigApi.class)).getCourseInfoByCourseId(this.mSportType, this.mCourseId, new ConfigCallBack() { // from class: com.huawei.health.sportservice.manager.CourseManager$$ExternalSyntheticLambda1
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                CourseManager.this.m463x24dd1aa((FitWorkout) obj);
            }
        });
    }

    /* renamed from: lambda$updateCourseData$0$com-huawei-health-sportservice-manager-CourseManager, reason: not valid java name */
    /* synthetic */ void m463x24dd1aa(FitWorkout fitWorkout) {
        this.mFitWorkout = fitWorkout;
        LogUtil.d(TAG, " mFitWorkout = ", fitWorkout);
        BaseSportManager.getInstance().stagingData("COURSE_DATA", this.mFitWorkout);
        SportCourseParams sportCourseParams = new SportCourseParams();
        sportCourseParams.addExtra("COURSE_DATA", this.mFitWorkout);
        BaseSportManager.getInstance().setParas(SportParamsType.COURSE_INFO, sportCourseParams);
        setSingleActionList();
        initCourse();
    }

    private void getCourseConfigInfo() {
        ((HeartRateControlConfigApi) Services.c("EcologyDevice", HeartRateControlConfigApi.class)).requestGlobalConfigByType(this.mSportType, new ConfigCallBack() { // from class: com.huawei.health.sportservice.manager.CourseManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                CourseManager.lambda$getCourseConfigInfo$1((dsy) obj);
            }
        });
    }

    static /* synthetic */ void lambda$getCourseConfigInfo$1(dsy dsyVar) {
        LogUtil.d(TAG, "getCourseConfigInfo courseConfigInfo = ", dsyVar);
        SportCourseParams sportCourseParams = new SportCourseParams();
        sportCourseParams.addExtra("COURSE_CONFIG_DATA", dsyVar);
        BaseSportManager.getInstance().setParas(SportParamsType.COURSE_INFO, sportCourseParams);
    }

    private void courseInitDataChange() {
        LogUtil.d(TAG, "enter courseDataUpdate");
        HashMap hashMap = new HashMap();
        if (this.mFitWorkout == null || this.mSingleActionList == null) {
            LogUtil.c(TAG, "mFitWorkout or singleActionList is null");
            return;
        }
        SportCourseParams sportCourseParams = new SportCourseParams();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (ChoreographedSingleAction choreographedSingleAction : this.mSingleActionList) {
            arrayList.add(choreographedSingleAction.getAction() != null ? choreographedSingleAction.getAction().getName() : "");
            arrayList2.add(Integer.valueOf((int) (choreographedSingleAction.getTargetConfig() != null ? choreographedSingleAction.getTargetConfig().getValueL() : 0.0d)));
            arrayList3.add(new Pair(Integer.valueOf(choreographedSingleAction.getIntensityConfig() != null ? (int) choreographedSingleAction.getIntensityConfig().getValueL() : 0), Integer.valueOf(choreographedSingleAction.getIntensityConfig() != null ? (int) choreographedSingleAction.getIntensityConfig().getValueH() : 0)));
            try {
                arrayList4.add(new Pair(Integer.valueOf(Integer.parseInt(choreographedSingleAction.getExtendProperty("rpm_min"))), Integer.valueOf(Integer.parseInt(choreographedSingleAction.getExtendProperty("rpm_max")))));
            } catch (NumberFormatException unused) {
                LogUtil.e(TAG, "NumberFormatException, get cadence ");
            }
        }
        hashMap.put("COURSE_NAME_LIST", arrayList);
        hashMap.put("COURSE_DURATION_LIST", arrayList2);
        hashMap.put("COURSE_HEART_RATE_LIST", arrayList3);
        hashMap.put("COURSE_CADENCE_LIST", arrayList4);
        hashMap.put("COURSE_DURATION", Integer.valueOf(this.mFitWorkout.acquireDuration()));
        LogUtil.d(TAG, "outputMap ", hashMap);
        sportCourseParams.setCourseNameList(arrayList);
        sportCourseParams.setCourseDurationList(arrayList2);
        sportCourseParams.setCourseHeartRateList(arrayList3);
        sportCourseParams.setCourseTotalTime(this.mFitWorkout.acquireDuration());
        sportCourseParams.addExtra("COURSE_CADENCE_LIST", arrayList4);
        sportCourseParams.addExtra("COURSE_MAX_HEART_RATE", Integer.valueOf(this.mFitWorkout.getUpperHeartRateAdjust()));
        sportCourseParams.addExtra("COURSE_MIN_HEART_RATE", Integer.valueOf(this.mFitWorkout.getLowerHeartRateAdjust()));
        BaseSportManager.getInstance().setParas(SportParamsType.COURSE_INFO, sportCourseParams);
    }
}
