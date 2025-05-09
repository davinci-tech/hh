package com.huawei.health.sleep;

import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwbasemgr.SleepMonthlyProcessResultCallback;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import defpackage.fcw;
import defpackage.fcz;
import defpackage.fda;
import defpackage.fdh;
import java.util.Date;

/* loaded from: classes.dex */
public interface SleepApi {
    void getPlanState(IBaseResponseCallback iBaseResponseCallback);

    void getSleepData(long j, IBaseResponseCallback iBaseResponseCallback);

    Class<? extends JsBaseModule> getSleepManagementH5Bridge();

    Class<?> getSleepManagementJsApi();

    void requestAndUpdateDailyResult(long j, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback);

    void requestDailyProcessResult(long j, SleepDailyProcessResultCallback<fda> sleepDailyProcessResultCallback);

    void requestMonthlyProcessResult(fcz fczVar, fcw fcwVar, long j, long j2, SleepMonthlyProcessResultCallback<fdh> sleepMonthlyProcessResultCallback);

    void requestQuestionnaireProcessResult(long j, long j2, IBaseResponseCallback iBaseResponseCallback);

    void requestSleepDevice(Date date, CommonUiBaseResponse commonUiBaseResponse);
}
