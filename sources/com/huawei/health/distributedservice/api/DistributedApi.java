package com.huawei.health.distributedservice.api;

import android.content.Context;
import android.os.IBinder;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import defpackage.cwn;
import java.util.List;

/* loaded from: classes.dex */
public interface DistributedApi {
    String createExFilterParamsWithSameAccount(cwn cwnVar);

    ExtraParams createExtraParams(String[] strArr, String str, String str2, String str3);

    void destroyWirelessProjection();

    void detectLastWirelessDevice();

    void getAvailableFaDevice(UiCallback<List<?>> uiCallback);

    ExtraParams getDefaultExtraParams(String str, String str2);

    void init(Context context);

    void initDistributedEnvironment(String str, IInitCallBack iInitCallBack);

    boolean isHopEnabled(Context context);

    boolean isSupportCenterController(Context context);

    boolean isTargetDeviceInstalledHealth(String str);

    int register(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback);

    void releaseDeviceManager();

    void setHideNavigationBar(boolean z);

    void setIsCanPreSearch(boolean z);

    void setIsInit(boolean z);

    boolean showDeviceList(int i, ExtraParams extraParams);

    int startRemoteAbility(Context context, String str, Intent intent);

    int startWirelessProjection();

    void unInitDistributedEnvironment(String str, IInitCallBack iInitCallBack);

    boolean unregister(int i);

    boolean updateConnectStatus(int i, String str, DeviceConnectState deviceConnectState);
}
