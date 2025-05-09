package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.os.Bundle;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.HeartRateDeviceSelectedCallback;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.model.MeasureResult;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

@ApiDefine(uri = HealthDeviceEntryApi.class)
/* loaded from: classes3.dex */
public class dyo implements HealthDeviceEntryApi {
    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public HealthDevice getBondedDevice(String str) {
        LogUtil.d("HealthDeviceEntryImpl", "getBondedDevice");
        return cjx.e().a(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean isDeviceKitUniversal(String str) {
        LogUtil.d("HealthDeviceEntryImpl", "isDeviceKitUniversal");
        return cjx.e().h(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str) {
        LogUtil.d("HealthDeviceEntryImpl", "getBondedDeviceUniversal");
        return cjx.e().d(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public com.huawei.hihealth.device.open.HealthDevice getBondedDeviceUniversal(String str, String str2) {
        LogUtil.d("HealthDeviceEntryImpl", "getBondedDeviceUniversal");
        return cjx.e().c(str, str2);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public HealthDevice getBondedDeviceByUniqueId(String str) {
        LogUtil.d("HealthDeviceEntryImpl", "getBondedDeviceByUniqueId");
        return cjx.e().c(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean unbindDeviceByUniqueId(String str) {
        LogUtil.d("HealthDeviceEntryImpl", "unbindDeviceByUniqueId");
        return cjx.e().o(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean unbindDeviceUniversalByUniqueId(String str, String str2) {
        return cjx.e().h(str, str2);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean bindDevice(String str, HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler) {
        return cjx.e().a(str, healthDevice, iDeviceEventHandler);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean bindDevice(String str, String str2, HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler) {
        return cjx.e().b(str, str2, healthDevice, iDeviceEventHandler);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public ArrayList<ContentValues> getBondedDeviceByDeviceKind(HealthDevice.HealthDeviceKind healthDeviceKind) {
        return cjx.e().d(healthDeviceKind);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void stopMeasureUniversal(String str, String str2) {
        cjx.e().e(str, str2);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void cleanupMeasureUniversal(String str, String str2) {
        cjx.e().b(str, str2);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void stopMeasure(String str, String str2) {
        cjx.e().d(str, str2);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public String startMeasureHeartRate(HealthDevice.HealthDeviceKind healthDeviceKind, IHealthDeviceCallback iHealthDeviceCallback, HeartRateDeviceSelectedCallback heartRateDeviceSelectedCallback, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback2, MeasureResult.MeasureResultListener measureResultListener) {
        return cjx.e().b(healthDeviceKind, iHealthDeviceCallback, heartRateDeviceSelectedCallback, iHealthDeviceCallback2, measureResultListener);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean unbindDevice(String str) {
        return cjx.e().m(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public MeasureKit getMeasureKit(String str) {
        return cjx.e().g(str);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void sendLocalBroadcastUniversal(String str, String str2, String str3) {
        cjx.e().c(str, str2, str3);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void sendLocalBroadcast(String str, String str2, String str3) {
        cjx.e().a(str, str2, str3);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void stopMeasureBleScale(String str, String str2, int i) {
        cjx.e().e(str, str2, i);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean startMeasureUniversal(String str, String str2, com.huawei.hihealth.device.open.IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        return cjx.e().Gu_(str, str2, iHealthDeviceCallback, bundle);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean startMeasureReconnect(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle, boolean z) {
        return cjx.e().Gt_(str, str2, iHealthDeviceCallback, bundle, z);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public boolean startMeasure(String str, String str2, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        return cjx.e().Gr_(str, str2, iHealthDeviceCallback, bundle);
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public ArrayList<String> getBondedWiFiDevices() {
        return cjx.e().c();
    }

    @Override // com.huawei.health.device.api.HealthDeviceEntryApi
    public void startConnectLatestScale() {
        if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
            LogUtil.c("HealthDeviceEntryImpl", "bluetooth is not open");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dys
                @Override // java.lang.Runnable
                public final void run() {
                    cjx.e().g();
                }
            });
        }
    }
}
