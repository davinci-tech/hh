package com.huawei.health.suggestion.ui.voice;

import com.huawei.pluginfitnessadvice.TargetConfig;

/* loaded from: classes4.dex */
public interface IFitRunVoiceContentConstructor {
    Object getAbsoluteLowerLimit();

    Object getAbsoluteUpperLimit();

    Object getChangeFirstAction(String str, int i, int i2, TargetConfig targetConfig);

    Object getChangeLastAction(String str, int i, int i2, TargetConfig targetConfig);

    Object getChangeNextAction(String str, int i, int i2, TargetConfig targetConfig);

    Object getHeartRateDeviceConnected();

    Object getHeartRateDeviceError();

    Object getMAF180LowerLimit();

    Object getMAF180UpperLimit();

    Object getNormalHeartRate();

    Object getNormalPace();

    Object getNormalSpeed();

    Object getNormalStepRate();

    Object getPaceZoneLowerLimit();

    Object getPaceZoneUpperLimit();

    Object getRelativeLowerLimit();

    Object getRelativeUpperLimit();

    Object getReserveHRIntervalLowerLimit();

    Object getReserveHRIntervalUpperLimit();

    Object getReserveHRLowerLimit();

    Object getReserveHRUpperLimit();

    Object getSpeedLowerLimit();

    Object getSpeedUpperLimit();

    Object getStepRateLowerLimit();

    Object getStepRateUpperLimit();
}
