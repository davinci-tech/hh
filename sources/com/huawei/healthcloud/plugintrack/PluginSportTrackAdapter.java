package com.huawei.healthcloud.plugintrack;

import android.os.Handler;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITreadmillStyleCallback;
import com.huawei.healthcloud.plugintrack.manager.inteface.LocalPressureCallback;
import com.huawei.healthcloud.plugintrack.model.IHeartRateCallback;
import com.huawei.healthcloud.plugintrack.model.IRealStepCallback;
import com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback;
import com.huawei.healthcloud.plugintrack.model.IRunningPostureCallback;
import com.huawei.healthcloud.plugintrack.model.IStepRateCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.gxn;
import defpackage.gyc;
import defpackage.kvq;
import java.util.List;

/* loaded from: classes4.dex */
public interface PluginSportTrackAdapter extends PluginBaseAdapter {
    public static final int MODE_TYPE_TRACK_ALTITUDE = 0;

    DeviceInfo getCurrentAw70DeviceInfo();

    int getCurrentSteps();

    int getDefaultDelayInterval();

    void getStepRateList(long j, long j2, Handler handler);

    int getWarningLimitHeartRate();

    void init();

    void insertIntensityData(List<gxn> list);

    boolean isFitnessCourseDisplay();

    boolean isPrivacyOfSportDataSwitchOn();

    boolean isWarningEnable();

    boolean isWearDeviceConnected();

    void pauseOrResumeStepRateRecord(boolean z);

    void readLastVo2max(IBaseResponseCallback iBaseResponseCallback);

    void recoveryStep(int i, double d, int i2);

    boolean regHeartRateListener(IHeartRateCallback iHeartRateCallback, int i);

    boolean regRunningPostureListener(IRunningPostureCallback iRunningPostureCallback);

    void regStepRateListener(IStepRateCallback iStepRateCallback, int i, int i2);

    boolean regStepRateListener(IStepRateCallback iStepRateCallback, int i);

    void registerFreeIndoorRunningStyle(ITreadmillStyleCallback iTreadmillStyleCallback);

    void registerRealStepListener(IRealStepCallback iRealStepCallback, long j);

    void registerRealStepListener(IRealStepCallback iRealStepCallback, long j, int i);

    boolean registerReportDataListener(IReportDataCallback iReportDataCallback);

    boolean registerRidePosture(IRidePostureDataCallback iRidePostureDataCallback);

    void reportCurrentStepCallback(IRealStepCallback iRealStepCallback);

    void requestLocalPressure(LocalPressureCallback localPressureCallback, int i);

    int saveTrackData(MotionPathSimplify motionPathSimplify, MotionPath motionPath);

    long saveTrackData(MotionPathSimplify motionPathSimplify, String str);

    long saveTrackData(MotionPathSimplify motionPathSimplify, String str, kvq kvqVar);

    void saveTrackDataWhenProcess(MotionPathSimplify motionPathSimplify, String str);

    void saveTrackPointData(List<gyc> list, int i);

    void setPrivacyOfSportDataSwitch(boolean z);

    void setStepInterval(boolean z);

    void setStepType(int i);

    void startHeartDeviceMeasure();

    void startStepPoint();

    void startTickTrackDog();

    void startTreadmillStep();

    void stopHeartDeviceMeasure();

    void stopStepPoint();

    void stopTickTrackDog();

    void stopTreadmillStep();

    void tickTrackDog();

    void unRegisterRealStepCallback();

    void unRegisterReportDataListener();

    void unregHeartRateListener();

    void unregRunningPostureListener();

    void unregStepRateListener();

    void unregStepRateListener(int i);

    void unregisterFreeIndoorRunningStyle();

    void unregisterRidePosture();

    void updateStepPoint(int i, long j);
}
