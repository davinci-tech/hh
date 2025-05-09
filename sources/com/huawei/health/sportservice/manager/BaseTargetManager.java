package com.huawei.health.sportservice.manager;

import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import defpackage.ffd;
import defpackage.fgm;
import defpackage.koq;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.TARGET_MANAGER)
/* loaded from: classes4.dex */
public class BaseTargetManager implements ManagerComponent {
    private static final String TARGET_STATUS = "TARGET_STATUS";
    protected ffd mCurrentTarget;
    protected boolean mHasTarget;
    protected ffd mSegmentedTarget;
    protected SportDataNotify mSportDataNotify;
    protected List<ffd> mTargetList;
    protected float mTargetValue;
    protected int mTargetType = -1;
    protected LinkedHashMap<String, ITargetUpdateListener> mTargetUpdateListenerMap = new LinkedHashMap<>(2);
    protected double mPercentage = -1.0d;
    protected fgm mCallbackOption = new fgm();
    private float mLastAccumulateTargetValue = 0.0f;
    private String mTag = "";
    private int mTargetStatus = 0;
    private boolean mIsNeedCheck = true;

    protected void updateTargetValue(ffd ffdVar) {
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void setParas(SportParamsType sportParamsType, Object obj) {
        if (SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType) && (obj instanceof SportLaunchParams)) {
            SportLaunchParams sportLaunchParams = (SportLaunchParams) obj;
            this.mTargetType = sportLaunchParams.getSportTarget();
            this.mTargetValue = sportLaunchParams.getTargetValue();
            this.mHasTarget = this.mTargetType != -1;
            if (sportLaunchParams.isRestart()) {
                int intValue = ((Integer) sportLaunchParams.getExtra(TARGET_STATUS, Integer.class, 0)).intValue();
                this.mTargetStatus = intValue;
                LogUtil.d("BaseTargetManager", "setParas, mTargetStatus  ", Integer.valueOf(intValue));
            }
        }
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public boolean supportParas(SportParamsType sportParamsType) {
        return SportParamsType.SPORT_LAUNCH_PARAS.equals(sportParamsType);
    }

    public float getTargetValue() {
        return this.mTargetValue;
    }

    public double getPercentage() {
        return this.mPercentage;
    }

    public void subscribeTargetStatus(String str, ITargetUpdateListener iTargetUpdateListener) {
        this.mTargetUpdateListenerMap.put(str, iTargetUpdateListener);
    }

    public int getTargetSportStatus() {
        return this.mTargetStatus;
    }

    public void registerTargetSportData(final String str, final float f) {
        LogUtil.d(this.mTag, "registerTargetSportData");
        this.mSegmentedTarget = new ffd(this.mTag, this.mTargetType, f);
        this.mSportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.BaseTargetManager$$ExternalSyntheticLambda1
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                BaseTargetManager.this.m461xf0a07281(str, f, list, map);
            }
        };
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        if (this.mTargetType == 1) {
            arrayList.add("CALORIES_DATA");
        }
        this.mCallbackOption.a(arrayList);
        this.mCallbackOption.c(this.mTag);
        BaseSportManager.getInstance().subscribeNotify(this.mCallbackOption, this.mSportDataNotify);
    }

    /* renamed from: lambda$registerTargetSportData$0$com-huawei-health-sportservice-manager-BaseTargetManager, reason: not valid java name */
    /* synthetic */ void m461xf0a07281(String str, float f, List list, Map map) {
        long j;
        if (isTargetUpdate(list, map)) {
            if (!map.containsKey(str) || map.get(str) == null) {
                LogUtil.c(this.mTag, "no such data or is null");
                return;
            }
            if (map.get(str) instanceof Long) {
                j = ((Long) map.get(str)).longValue();
            } else if (map.get(str) instanceof Integer) {
                j = ((Integer) map.get(str)).intValue();
            } else {
                LogUtil.c(this.mTag, "error type");
                j = 0;
            }
            updateSingleTargetData(j, f);
        }
    }

    public void registerTargetSportData(final String str) {
        LogUtil.d(this.mTag, "registerTargetSportData");
        this.mSportDataNotify = new SportDataNotify() { // from class: com.huawei.health.sportservice.manager.BaseTargetManager$$ExternalSyntheticLambda0
            @Override // com.huawei.health.sportservice.SportDataNotify
            public final void onChange(List list, Map map) {
                BaseTargetManager.this.m462x7d8d89a0(str, list, map);
            }
        };
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        this.mCallbackOption.a(arrayList);
        this.mCallbackOption.c(this.mTag);
        BaseSportManager.getInstance().subscribeNotify(this.mCallbackOption, this.mSportDataNotify);
    }

    /* renamed from: lambda$registerTargetSportData$1$com-huawei-health-sportservice-manager-BaseTargetManager, reason: not valid java name */
    /* synthetic */ void m462x7d8d89a0(String str, List list, Map map) {
        int intValue;
        if (!list.contains(str) || map.get(str) == null) {
            LogUtil.c(this.mTag, "no such data or is null");
            return;
        }
        if (this.mTargetType == 0) {
            intValue = Math.round(((Long) map.get(str)).longValue() / 1000);
        } else {
            intValue = ((Integer) map.get(str)).intValue();
        }
        updateData(intValue);
    }

    protected void initTarget(float f) {
        this.mSegmentedTarget = new ffd(this.mTag, this.mTargetType, f);
    }

    private boolean isTargetUpdate(List<String> list, Map<String, Object> map) {
        if (this.mTargetType == 1 && !list.contains("CALORIES_DATA")) {
            return (map.get("CALORIES_DATA") instanceof Integer) && ((Integer) map.get("CALORIES_DATA")).intValue() == 0;
        }
        return true;
    }

    private void updateSingleTargetData(long j, float f) {
        LogUtil.d(this.mTag, "currentValue ", Long.valueOf(j), " mTargetType ", Integer.valueOf(this.mTargetType), " targetValue ", Float.valueOf(f));
        if (Float.compare(f, 0.0f) != 0) {
            this.mPercentage = (j * 1.0d) / f;
            this.mSegmentedTarget.d(j);
            Iterator<String> it = this.mTargetUpdateListenerMap.keySet().iterator();
            while (it.hasNext()) {
                this.mTargetUpdateListenerMap.get(it.next()).onTargetDataUpdate(this.mSegmentedTarget);
            }
            LogUtil.d(this.mTag, "mPercentage ", Double.valueOf(this.mPercentage));
            checkOverTargetBeforeStart(j);
            targetFinishedDetect();
        }
    }

    protected void initTargetList(List<ffd> list) {
        LogUtil.d(this.mTag, "initTargetList, list = ", list);
        if (koq.b(list)) {
            LogUtil.c(this.mTag, "list is empty");
            return;
        }
        this.mTargetList = list;
        if (list.size() > 0) {
            ffd ffdVar = this.mTargetList.get(0);
            this.mCurrentTarget = ffdVar;
            ffdVar.a(0.0f);
        }
    }

    private void updateData(float f) {
        float updateGoalProgress = updateGoalProgress(f);
        LogUtil.b(this.mTag, "currentValue = ", Float.valueOf(f), " progress = ", Float.valueOf(updateGoalProgress));
        updateTargetValue(this.mCurrentTarget);
        if (Float.compare(updateGoalProgress, 1.0f) >= 0) {
            updateNewTargetValue();
        }
    }

    protected float updateGoalProgress(float f) {
        ffd ffdVar = this.mCurrentTarget;
        if (ffdVar == null) {
            return 0.0f;
        }
        ffdVar.d(f);
        return this.mCurrentTarget.a();
    }

    protected void updateNewTargetValue() {
        List<ffd> list = this.mTargetList;
        if (list == null || list.size() <= 0) {
            return;
        }
        LogUtil.d(this.mTag, "updateNewTargetValue");
        setAccumulateTargetValue();
        this.mTargetList.remove(0);
        if (this.mTargetList.size() > 0) {
            ffd ffdVar = this.mTargetList.get(0);
            this.mCurrentTarget = ffdVar;
            ffdVar.a(this.mLastAccumulateTargetValue);
            LogUtil.d(this.mTag, "mCurrentTarget = ", this.mCurrentTarget);
            notifyTargetStateUpdate(201, "");
            return;
        }
        notifyTargetStateUpdate(200, "");
    }

    public void notifyTargetStateUpdate(int i, String str) {
        for (ITargetUpdateListener iTargetUpdateListener : this.mTargetUpdateListenerMap.values()) {
            if (iTargetUpdateListener != null) {
                iTargetUpdateListener.onStateUpdate(i, str);
            }
        }
    }

    private void targetFinishedDetect() {
        if (this.mPercentage < 1.0d || this.mTargetUpdateListenerMap == null) {
            return;
        }
        LogUtil.d(this.mTag, "targetFinishedDetect");
        notifyTargetStateUpdate(200, "");
        if (this.mIsNeedCheck || this.mTargetStatus == 2) {
            return;
        }
        this.mTargetStatus = 1;
        LogUtil.d(this.mTag, "targetFinishedDetect， mTargetStatus is ", 1);
    }

    private void checkOverTargetBeforeStart(long j) {
        if (this.mIsNeedCheck) {
            if (this.mTargetType == 0 && j == 0) {
                return;
            }
            if (this.mPercentage > 1.0d && this.mTargetStatus != 1) {
                this.mTargetStatus = 2;
                LogUtil.d(this.mTag, "OverTargetBeforeStart, mTargetStatus is ", 2);
            }
            this.mIsNeedCheck = false;
        }
    }

    private void setAccumulateTargetValue() {
        float i = this.mLastAccumulateTargetValue + this.mTargetList.get(0).i();
        this.mLastAccumulateTargetValue = i;
        LogUtil.d(this.mTag, "mLastAccumulateTargetValue is ", Float.valueOf(i));
    }

    @Override // com.huawei.health.sportservice.manager.ManagerComponent
    public void destroy() {
        this.mTargetList = null;
    }
}
