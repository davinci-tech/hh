package com.huawei.health.bloodpressure;

import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.ResponseCallback;
import defpackage.cbk;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public interface BloodPressureApi {
    int getBloodPressureGradeType(int i, int i2);

    Class<? extends JsBaseModule> getBloodPressureJsApi();

    List<cbk> getBloodPressureMeasurePlan();

    void getBloodPressureMeasurePlan(ResponseCallback<List<cbk>> responseCallback);

    JSONObject queryBloodPressureDeviceStatus();

    void updateAllAlarm(List<cbk> list, boolean z, ResponseCallback<List<cbk>> responseCallback);
}
