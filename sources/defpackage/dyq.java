package defpackage;

import android.content.Context;
import android.os.IBinder;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.controlcenter.featureability.sdk.IConnectCallback;
import com.huawei.controlcenter.featureability.sdk.model.ExtraParams;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.health.airsharing.WirelessProjectionManager;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import java.util.List;

@ApiDefine(uri = DistributedApi.class)
@Singleton
/* loaded from: classes3.dex */
public class dyq implements DistributedApi {
    private WirelessProjectionManager e;

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void init(Context context) {
        this.e = new WirelessProjectionManager(context);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public int startWirelessProjection() {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            return wirelessProjectionManager.f();
        }
        LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        return 4;
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void destroyWirelessProjection() {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            wirelessProjectionManager.e();
            this.e = null;
        } else {
            LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        }
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void detectLastWirelessDevice() {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            wirelessProjectionManager.b();
        } else {
            LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        }
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void setIsInit(boolean z) {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            wirelessProjectionManager.a(z);
        } else {
            LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        }
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void setIsCanPreSearch(boolean z) {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            wirelessProjectionManager.d(z);
        } else {
            LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        }
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void setHideNavigationBar(boolean z) {
        WirelessProjectionManager wirelessProjectionManager = this.e;
        if (wirelessProjectionManager != null) {
            wirelessProjectionManager.b(z);
        } else {
            LogUtil.h("DistributedImpl", "mProjectionManager.is not init().");
        }
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean isHopEnabled(Context context) {
        return dll.c().d(context);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public int register(String str, IBinder iBinder, ExtraParams extraParams, IConnectCallback iConnectCallback) {
        return dll.c().WI_(str, iBinder, extraParams, iConnectCallback);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean unregister(int i) {
        return dll.c().a(i);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public String createExFilterParamsWithSameAccount(cwn cwnVar) {
        return dll.c().d(cwnVar);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public ExtraParams createExtraParams(String[] strArr, String str, String str2, String str3) {
        return dll.c().d(strArr, str, str2, str3);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public ExtraParams getDefaultExtraParams(String str, String str2) {
        return dll.c().a(str, str2);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean showDeviceList(int i, ExtraParams extraParams) {
        return dll.c().c(i, extraParams);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void initDistributedEnvironment(String str, IInitCallBack iInitCallBack) {
        dll.c().d(str, iInitCallBack);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public int startRemoteAbility(Context context, String str, Intent intent) {
        return dll.c().a(context, str, intent);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void unInitDistributedEnvironment(String str, IInitCallBack iInitCallBack) {
        dll.c().b(str, iInitCallBack);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean updateConnectStatus(int i, String str, DeviceConnectState deviceConnectState) {
        return dll.c().a(i, str, deviceConnectState);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean isTargetDeviceInstalledHealth(String str) {
        return dll.c().a(str);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public boolean isSupportCenterController(Context context) {
        return dll.c().a(context);
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void releaseDeviceManager() {
        dll.c().d();
    }

    @Override // com.huawei.health.distributedservice.api.DistributedApi
    public void getAvailableFaDevice(UiCallback<List<?>> uiCallback) {
        dll.c().a(uiCallback);
    }
}
