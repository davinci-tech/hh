package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.api.DevicesManagementInterface;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bgl implements DevicesManagementInterface {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicBoolean f360a = new AtomicBoolean(false);
    private static final Object d = new Object();

    private bgl() {
    }

    public static bgl c() {
        return d.b;
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void initUniteService(final DeviceCompatibleCallback deviceCompatibleCallback) {
        ThreadPoolManager.d().d("DevicesManagement", new Runnable() { // from class: bgl.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (bgl.d) {
                    LogUtil.c("DevicesManagement", "DevicesManagement initUniteService initUniteService()-tagExecute()");
                    bjx.a().c();
                    List<String> a2 = bgl.this.a();
                    DeviceCompatibleCallback deviceCompatibleCallback2 = deviceCompatibleCallback;
                    if (deviceCompatibleCallback2 != null) {
                        deviceCompatibleCallback2.adapterOperate(a2);
                    }
                }
                if (bjv.e().b() != null) {
                    bjy.d().c(1);
                }
            }
        });
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void scanDevice(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        LogUtil.c("DevicesManagement", "DevicesManagement scanDevice ");
        if (scanMode == null || deviceScanCallback == null || list == null) {
            LogUtil.c("DevicesManagement", "DevicesManagement scanDevice fail");
        } else {
            bjw.a().c(scanMode, list, deviceScanCallback);
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void stopScanDevice() {
        bjw.a().b();
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void connectDevice(DeviceInfo deviceInfo, boolean z, ConnectMode connectMode) {
        if (connectMode == null || deviceInfo == null) {
            LogUtil.e("DevicesManagement", "device or connectMode is invalid.");
            return;
        }
        LogUtil.c("DevicesManagement", "connectDevice start.", blt.a(deviceInfo), " reconnect:", Boolean.valueOf(z));
        if (TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        DeviceInfo e = bjx.a().e(deviceMac);
        if (e != null && e.getDeviceConnectState() == 1) {
            LogUtil.c("DevicesManagement", "currentDevice is already connecting.");
        } else if (!bml.e(deviceInfo)) {
            LogUtil.c("DevicesManagement", "isReconnectionDevice false");
        } else {
            bjx.a().d(deviceMac, connectMode);
            bjv.e().e(connectMode, deviceInfo, z);
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void createSystemBond(DeviceInfo deviceInfo, ConnectMode connectMode) {
        if (deviceInfo == null || connectMode == null) {
            LogUtil.e("DevicesManagement", "createSystemBond error. device or connect mode is invalid");
            return;
        }
        bjx.a().b(deviceInfo);
        LogUtil.c("DevicesManagement", "createSystemBond", blt.a(deviceInfo.getDeviceMac()));
        bjv.e().e(deviceInfo, connectMode);
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void sendCommand(DeviceInfo deviceInfo, bir birVar) {
        byte b;
        if (deviceInfo == null || birVar == null) {
            LogUtil.a("DevicesManagement", "sendCommand error. device or command is null");
            return;
        }
        if (birVar.b() == null) {
            birVar.b("");
        }
        byte[] e = birVar.e();
        byte b2 = 0;
        if (e == null || e.length <= 1) {
            b = 0;
        } else {
            b2 = e[0];
            b = e[1];
            if (bme.c(b2, b, deviceInfo.getDeviceMac(), "send to device")) {
                return;
            }
            if (b2 == 52 || b2 == 55) {
                birVar.c(blk.e().c(deviceInfo, b2, e));
            } else {
                birVar.c(blk.e().b(deviceInfo, birVar, b2, b));
            }
        }
        DeviceInfo j = bjx.a().j(deviceInfo.getDeviceMac());
        if (b2 != 26 || b != 6) {
            deviceInfo = j;
        }
        ReleaseLogUtil.b("DEVMGR_DevicesManagement", "sendCommand command socketChannel: ", Integer.valueOf(birVar.i()), " serviceId: ", Integer.valueOf(b2), " commandId: ", Integer.valueOf(b));
        bjz.b().a(deviceInfo, birVar);
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public Map<String, UniteDevice> getDeviceList() {
        Map<String, UniteDevice> b;
        synchronized (d) {
            b = blo.b(bjx.a().b(), bjx.a().e());
        }
        return b;
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void unPairDevice(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("DevicesManagement", "unPairDevice device null");
        } else {
            ReleaseLogUtil.b("DEVMGR_DevicesManagement", "unPairDevice", blt.a(deviceInfo.getDeviceMac()));
            bjv.e().a(deviceInfo, true);
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void disconnect(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.e("DevicesManagement", "device is invalid.");
            return;
        }
        DeviceInfo j = bjx.a().j(deviceInfo.getDeviceMac());
        if (j != null) {
            if (j.getDeviceConnectState() == 3) {
                bjx.a().e(deviceInfo.getDeviceMac(), false);
                LogUtil.c("DevicesManagement", "currentDevice is already disconnected.");
            } else {
                LogUtil.c("DevicesManagement", "disconnect device start.", blt.a(j));
                bjv.e().e(j);
            }
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public boolean isSupportService(DeviceInfo deviceInfo, String str) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac()) || str == null) {
            LogUtil.e("DevicesManagement", "query device is SupportService error");
            return false;
        }
        return bib.a().b(deviceInfo.getDeviceMac(), str);
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public boolean isSupportCharacter(DeviceInfo deviceInfo, String str, String str2) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac()) || str == null || str2 == null) {
            LogUtil.e("DevicesManagement", "query device is SupportCharacter error");
            return false;
        }
        return bib.a().d(deviceInfo.getDeviceMac(), str, str2);
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void registerDeviceStateListener(DeviceStatusChangeCallback deviceStatusChangeCallback) {
        LogUtil.c("DevicesManagement", "registerDeviceStateListener");
        if (deviceStatusChangeCallback != null) {
            bjv.e().b(deviceStatusChangeCallback);
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void unregisterDeviceStateListener() {
        LogUtil.c("DevicesManagement", "unregisterDeviceStateListener");
        bjv.e().d();
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void registerDeviceMessageListener(MessageReceiveCallback messageReceiveCallback) {
        LogUtil.c("DevicesManagement", "registerDeviceMessageListener");
        if (messageReceiveCallback != null) {
            bjz.b().b(messageReceiveCallback);
        }
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void unregisterDeviceMessageListener() {
        LogUtil.c("DevicesManagement", "unregisterDeviceMessageListener");
        bjz.b().d();
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void registerHandshakeFilter(ConnectFilter connectFilter) {
        if (connectFilter == null) {
            LogUtil.e("DevicesManagement", "registerHandshakeFilter error, input param is invalid. device or filter is null");
        } else {
            LogUtil.c("DevicesManagement", "registerHandshakeFilter");
            bjv.e().b(connectFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> a() {
        String deviceMac;
        ExternalDeviceCapability a2;
        ArrayList arrayList = new ArrayList(16);
        if (f360a.get()) {
            return arrayList;
        }
        String d2 = blz.d("fix_multi_status", "");
        LogUtil.c("DevicesManagement", "fixMultiDeviceStatus isUpdated is ", d2);
        if (!TextUtils.isEmpty(d2)) {
            f360a.set(true);
            return arrayList;
        }
        blz.a("fix_multi_status", "true");
        for (DeviceInfo deviceInfo : bjx.a().b().values()) {
            if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceMac()) && (a2 = bjx.a().a((deviceMac = deviceInfo.getDeviceMac()))) != null) {
                com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo2 = new com.huawei.health.devicemgr.business.entity.DeviceInfo();
                deviceInfo2.setExpandCapability(a2.getCapacity());
                if (bmm.a(deviceInfo2, 109) && !deviceInfo.isUsing()) {
                    LogUtil.a("DevicesManagement", "isSupportMultiConnect but not is using", blt.a(deviceInfo));
                    deviceInfo.setUsing(true);
                    bjx.a().d(deviceMac, deviceInfo);
                    arrayList.add(deviceMac);
                    bjy.d().c(deviceInfo);
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.devicesdk.api.DevicesManagementInterface
    public void destory() {
        bjy.d().b();
    }

    static class d {
        private static bgl b = new bgl();
    }
}
