package com.huawei.health.sportservice.manager;

import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportCourseParams;
import com.huawei.health.sportservice.constants.SportObserverType;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.ffd;
import defpackage.ggs;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SportComponentType(classify = 1, name = ComponentName.COURSE_TARGET_MANAGER)
/* loaded from: classes8.dex */
public class CourseTargetManager extends BaseTargetManager {
    private static final String TAG = "SportService_CourseTargetManager";
    private HashMap<String, ITargetUpdateListener> mCallbackMap = new HashMap<>(1);
    private FitWorkout mFitWorkout;

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager, com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        LogUtil.d(TAG, "enter setParas");
        if (SportParamsType.COURSE_INFO.equals(sportParamsType) && (obj instanceof SportCourseParams)) {
            FitWorkout fitWorkout = (FitWorkout) ((SportCourseParams) obj).getExtra("COURSE_DATA", FitWorkout.class);
            this.mFitWorkout = fitWorkout;
            LogUtil.d(TAG, "mFitWorkout = ", fitWorkout);
            if (this.mFitWorkout != null) {
                initTargetList(getSegmentedTargetList());
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager, com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.COURSE_INFO.equals(sportParamsType);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.d(TAG, "enter onPreSport");
        setTag(TAG);
        this.mTargetType = 0;
        if (this.mTargetType == 0) {
            registerTargetSportData("TIME_ONE_SECOND_DURATION");
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean addObserver(SportObserverType sportObserverType, String str, Object obj) {
        LogUtil.d(TAG, "addObserver, observerType is ", sportObserverType, " tag is ", str);
        this.mCallbackMap.put(str, (ITargetUpdateListener) obj);
        return true;
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportObserver(SportObserverType sportObserverType) {
        return SportObserverType.COURSE_TARGET_OBSERVE.equals(sportObserverType);
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager
    public void notifyTargetStateUpdate(int i, String str) {
        LogUtil.d(TAG, "notifyTargetStateUpdate mCallbackMap ===== ", this.mCallbackMap);
        for (ITargetUpdateListener iTargetUpdateListener : this.mCallbackMap.values()) {
            if (iTargetUpdateListener != null) {
                iTargetUpdateListener.onStateUpdate(i, str);
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.BaseTargetManager
    protected void updateTargetValue(ffd ffdVar) {
        for (ITargetUpdateListener iTargetUpdateListener : this.mCallbackMap.values()) {
            if (iTargetUpdateListener != null) {
                iTargetUpdateListener.onTargetDataUpdate(ffdVar);
            }
        }
    }

    private List<ffd> getSegmentedTargetList() {
        List<ChoreographedSingleAction> c = ggs.c(this.mFitWorkout);
        LogUtil.d(TAG, "singleActionList = ", c);
        if (koq.b(c)) {
            LogUtil.d(TAG, "singleActionList is empty");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c.size(); i++) {
            ChoreographedSingleAction choreographedSingleAction = c.get(i);
            if (choreographedSingleAction != null && choreographedSingleAction.getTargetConfig() != null) {
                arrayList.add(new ffd("", 0, (int) choreographedSingleAction.getTargetConfig().getValueL(), i + 1, c.size() + 1));
            }
        }
        LogUtil.d(TAG, "targetList = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }
}
