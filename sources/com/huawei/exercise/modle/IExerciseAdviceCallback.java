package com.huawei.exercise.modle;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface IExerciseAdviceCallback {
    DeviceInfo getCurrentDeviceInfo();

    JSONObject getDeviceCapability();

    void getDeviceETEState(IBaseResponseCallback iBaseResponseCallback);

    void getDeviceFitnessPlanParamter(IBaseResponseCallback iBaseResponseCallback);

    void pushFitnessPlan(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback);

    void registerConnectionStatusChangeNotification(IBaseResponseCallback iBaseResponseCallback);

    void registerServiceConnectedListener(IBaseResponseCallback iBaseResponseCallback);

    void setMetricUnit(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback);

    void setRunPlanReminderSwitch(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback);
}
