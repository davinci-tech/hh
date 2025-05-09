package com.huawei.health.ecologydevice.kit.blp;

import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import defpackage.cwm;
import defpackage.cwq;
import defpackage.czu;
import defpackage.ddw;
import defpackage.dkq;
import health.compact.a.Services;
import java.util.UUID;

/* loaded from: classes3.dex */
public abstract class BaseBloodPressureManager implements ConnectStatusCallback, DataChangedCallback {
    private String mMessageReceiveCallbackId;

    public boolean init(NfcMeasureCallback nfcMeasureCallback) {
        if (!dkq.c().d()) {
            return false;
        }
        this.mMessageReceiveCallbackId = UUID.randomUUID().toString();
        String name = BaseBloodPressureManager.class.getName();
        ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).putUdsClassMap(name, czu.e());
        ddw.c().a(this.mMessageReceiveCallbackId, new cwq(name));
        ddw.c().e(this.mMessageReceiveCallbackId, new cwm(name));
        return false;
    }

    public void releaseResource() {
        if (dkq.c().d()) {
            ddw.c().a(this.mMessageReceiveCallbackId);
            ddw.c().d(this.mMessageReceiveCallbackId);
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).clearUdsClassMap();
        }
    }
}
