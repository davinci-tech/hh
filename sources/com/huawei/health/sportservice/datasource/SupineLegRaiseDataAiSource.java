package com.huawei.health.sportservice.datasource;

import android.util.Pair;
import androidx.collection.SimpleArrayMap;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper;
import defpackage.kxa;

@SportComponentType(classify = 3, name = ComponentName.SUPINE_LEG_RAISE_DATA_SOURCE)
/* loaded from: classes8.dex */
public class SupineLegRaiseDataAiSource extends BaseSource implements SportLifecycle, OnSportCodeListenerWrapper {
    private static final String TAG = "SportService_SupineLegRaiseDataAiSource";
    private int mContinuousTimes;
    private int mDataTimes;
    private int mInterruptTimes;
    private final kxa mPoint = new kxa();

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().setParas(SportParamsType.AI_SPORT_EXAM_SOURCE_LISTENER, this);
    }

    @Override // com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper
    public void getData(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap == null) {
            LogUtil.h(TAG, "getData map == null");
        } else {
            getStatus(simpleArrayMap);
            getPoint(simpleArrayMap);
        }
    }

    private void successCount(int i) {
        if (i == -2000) {
            this.mDataTimes++;
            this.mContinuousTimes++;
            BaseSportManager.getInstance().stagingAndNotification("SPORT_EXAM_SCORE", Integer.valueOf(this.mDataTimes));
            BaseSportManager.getInstance().stagingAndNotification("CONTINUOUS_DATA", Integer.valueOf(this.mContinuousTimes));
        }
    }

    private void interruptTimesCount(int i) {
        if (i == -2008) {
            this.mContinuousTimes = 0;
            BaseSportManager baseSportManager = BaseSportManager.getInstance();
            int i2 = this.mInterruptTimes + 1;
            this.mInterruptTimes = i2;
            baseSportManager.stagingAndNotification("INTERRUPT_DATA", Integer.valueOf(i2));
            BaseSportManager.getInstance().stagingAndNotification("CONTINUOUS_DATA", Integer.valueOf(this.mContinuousTimes));
        }
    }

    private void getPoint(SimpleArrayMap simpleArrayMap) {
        boolean z;
        Pair<Float, Float> groupPoint = getGroupPoint(simpleArrayMap, "pointX", "pointY");
        boolean z2 = true;
        if (groupPoint != null) {
            this.mPoint.n(((Float) groupPoint.first).floatValue());
            this.mPoint.m(((Float) groupPoint.second).floatValue());
            z = true;
        } else {
            z = false;
        }
        if (getGroupPoint(simpleArrayMap, "ankleX", "ankleY") != null) {
            this.mPoint.d(((Float) r2.first).intValue());
            this.mPoint.c(((Float) r2.second).intValue());
            z = true;
        }
        if (getGroupPoint(simpleArrayMap, "hipX", "hipY") != null) {
            this.mPoint.h(((Float) r2.first).intValue());
            this.mPoint.f(((Float) r2.second).intValue());
        } else {
            z2 = z;
        }
        if (isChangeGroupDirectionPoint(simpleArrayMap) || z2) {
            BaseSportManager.getInstance().stagingAndNotification("BODY_POINT_DATA", this.mPoint);
        }
    }

    private Pair<Float, Float> getGroupPoint(SimpleArrayMap simpleArrayMap, String str, String str2) {
        if (!simpleArrayMap.containsKey(str) || !simpleArrayMap.containsKey(str2)) {
            return null;
        }
        Object obj = simpleArrayMap.get(str);
        Object obj2 = simpleArrayMap.get(str2);
        if ((obj instanceof Float) && (obj2 instanceof Float)) {
            return new Pair<>(Float.valueOf(((Float) obj).floatValue()), Float.valueOf(((Float) obj2).floatValue()));
        }
        return null;
    }

    private boolean isChangeGroupDirectionPoint(SimpleArrayMap simpleArrayMap) {
        if (!simpleArrayMap.containsKey(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION)) {
            return false;
        }
        Object obj = simpleArrayMap.get(HiAnalyticsConstant.HaKey.BI_KEY_DIRECTION);
        if (!(obj instanceof Integer)) {
            return false;
        }
        this.mPoint.e(((Integer) obj).intValue());
        return true;
    }

    private void getStatus(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("errCode")) {
            Object obj = simpleArrayMap.get("errCode");
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                BaseSportManager.getInstance().stagingAndNotification("STATUS_CODE_DATA", Integer.valueOf(intValue));
                successCount(intValue);
                interruptTimesCount(intValue);
            }
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
