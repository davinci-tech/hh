package com.huawei.health.suggestion.ui.fitness.callback;

import android.os.RemoteException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.fnh;
import defpackage.gge;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class TrainDetailConnectCallback extends IConnectCallback.Stub {
    public static int b = -1;

    /* renamed from: a, reason: collision with root package name */
    private FitWorkout f3152a;
    private int c;
    private final long d;
    private String e;
    private UiCallback<Integer> f;

    public TrainDetailConnectCallback(FitWorkout fitWorkout, String str, long j, UiCallback<Integer> uiCallback) {
        this.f3152a = fitWorkout;
        this.d = j;
        this.f = uiCallback;
        this.e = str;
    }

    public void setToken(int i) {
        this.c = i;
    }

    @Override // com.huawei.controlcenter.featureability.sdk.IConnectCallback
    public void connect(String str, String str2, String str3) throws RemoteException {
        LogUtil.a("TrainDetailConnectCallback", "executor IConnectCallback connect,the deviceId: " + CommonUtil.l(str), " deviceType: ", str2);
        if (this.f3152a == null) {
            LogUtil.h("TrainDetailConnectCallback", "connect mFitWorkout is null");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", "1");
        hashMap.put("workout_id", this.f3152a.acquireId());
        hashMap.put("workout_name", this.f3152a.acquireName());
        gge.e(AnalyticsValue.FA_HOP_START.value(), hashMap);
        DistributedApi distributedApi = (DistributedApi) Services.c("DistributedService", DistributedApi.class);
        fnh fnhVar = new fnh(this.f3152a, this.e, this.d, this.f);
        fnhVar.b(this.c);
        distributedApi.initDistributedEnvironment(str, fnhVar);
        LogUtil.a("TrainDetailConnectCallback", "connect mToken:", Integer.valueOf(this.c));
        if (distributedApi.updateConnectStatus(this.c, str, DeviceConnectState.CONNECTING)) {
            return;
        }
        ReleaseLogUtil.d("TrainDetailConnectCallback", "updateConnectStatus to CONNECTING failed.");
    }

    @Override // com.huawei.controlcenter.featureability.sdk.IConnectCallback
    public void disconnect(String str) throws RemoteException {
        if (this.f3152a == null) {
            LogUtil.h("TrainDetailConnectCallback", "disconnect mFitWorkout is null");
            return;
        }
        LogUtil.h("TrainDetailConnectCallback", "executor IConnectCallback disconnect the deviceId: ", CommonUtil.l(str));
        DistributedApi distributedApi = (DistributedApi) Services.c("DistributedService", DistributedApi.class);
        LogUtil.a("TrainDetailConnectCallback", "disconnect mToken:", Integer.valueOf(this.c));
        distributedApi.updateConnectStatus(this.c, str, DeviceConnectState.IDLE);
        fnh fnhVar = new fnh(this.f3152a, this.e, this.d, this.f);
        fnhVar.b(this.c);
        distributedApi.unInitDistributedEnvironment(str, fnhVar);
    }
}
