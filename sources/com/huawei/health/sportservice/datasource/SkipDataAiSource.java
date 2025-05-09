package com.huawei.health.sportservice.datasource;

import androidx.collection.SimpleArrayMap;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.pluginsport.airopeskipping.OnJumpRopeListenerWrapper;
import defpackage.kxd;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@SportComponentType(classify = 3, name = ComponentName.SKIP_DATA_AI_SOURCE)
/* loaded from: classes8.dex */
public class SkipDataAiSource extends BaseSource implements SportLifecycle, OnJumpRopeListenerWrapper {
    private static final String R_TAG = "R_SportService_SkipDataAiSource";
    private static final String TAG = "SportService_SkipDataAiSource";
    private int mContinuousJumpTimes;
    private int mInterruptTimes;
    private kxd mPoint;
    private float mSkipSpeed;
    private int mSkippingTimes;

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void generateData(Object obj) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().setParas(SportParamsType.AI_ROPE_SOURCE_LISTENER, this);
    }

    @Override // com.huawei.pluginsport.airopeskipping.OnJumpRopeListenerWrapper
    public void getData(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap != null) {
            try {
                if (!simpleArrayMap.isEmpty()) {
                    getStatus(simpleArrayMap);
                    if (BaseSportManager.getInstance().getStatus() != 1) {
                        return;
                    }
                    getFootPoint(simpleArrayMap);
                    isSuccess(simpleArrayMap);
                    getSpeed(simpleArrayMap);
                    isBreak(simpleArrayMap);
                    return;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                ReleaseLogUtil.c(R_TAG, "SimpleArrayMap ArrayIndexOutOfBoundsException");
                return;
            }
        }
        ReleaseLogUtil.d(R_TAG, "map is null or empty");
    }

    private void isSuccess(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("rope_success")) {
            Object obj = simpleArrayMap.get("rope_success");
            if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                this.mSkippingTimes++;
                this.mContinuousJumpTimes++;
                BaseSportManager.getInstance().stagingAndNotification("CONTINUOUS_SKIPPING_JUMP_DATA", Integer.valueOf(this.mContinuousJumpTimes));
                BaseSportManager.getInstance().stagingAndNotification("SKIP_NUM_DATA", Integer.valueOf(this.mSkippingTimes));
            }
        }
    }

    private void isBreak(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("rope_break")) {
            Object obj = simpleArrayMap.get("rope_break");
            if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                this.mContinuousJumpTimes = 0;
                BaseSportManager baseSportManager = BaseSportManager.getInstance();
                int i = this.mInterruptTimes + 1;
                this.mInterruptTimes = i;
                baseSportManager.stagingAndNotification("STUMBLING_ROPE_DATA", Integer.valueOf(i));
                BaseSportManager.getInstance().stagingAndNotification("CONTINUOUS_SKIPPING_JUMP_DATA", Integer.valueOf(this.mContinuousJumpTimes));
            }
        }
    }

    private void getSpeed(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("rope_speed")) {
            Object obj = simpleArrayMap.get("rope_speed");
            if (obj instanceof Float) {
                this.mSkipSpeed = ((Float) obj).floatValue();
                BaseSportManager.getInstance().stagingAndNotification("SKIP_SPEED_DATA", Float.valueOf(this.mSkipSpeed));
            }
        }
    }

    private void getFootPoint(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("foot_x") && simpleArrayMap.containsKey("foot_y")) {
            Object obj = simpleArrayMap.get("foot_x");
            Object obj2 = simpleArrayMap.get("foot_y");
            if ((obj instanceof Float) && (obj2 instanceof Float)) {
                float floatValue = ((Float) obj).floatValue();
                float floatValue2 = ((Float) obj2).floatValue();
                if (floatValue <= 0.0f || floatValue2 <= 0.0f) {
                    return;
                }
                this.mPoint = new kxd(floatValue, floatValue2);
                BaseSportManager.getInstance().stagingAndNotification("FOOT_POINT_DATA", this.mPoint);
            }
        }
    }

    private void getStatus(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap.containsKey("status_code")) {
            Object obj = simpleArrayMap.get("status_code");
            if (obj instanceof Integer) {
                BaseSportManager.getInstance().stagingAndNotification("STATUS_CODE_DATA", Integer.valueOf(((Integer) obj).intValue()));
            }
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
