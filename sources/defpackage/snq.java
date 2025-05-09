package defpackage;

import android.content.Context;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.unitedevice.api.EngineChannelInterface;
import com.huawei.unitedevice.api.UniteChannelInterface;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.config.IwearLinkConfig;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import com.huawei.unitedevice.p2p.P2pReceiver;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class snq implements EngineChannelInterface, UniteChannelInterface {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, DeviceStatusChangeCallback> f17142a;
    private Map<WearEngineModule, EngineLogicBaseManager> b;
    private boolean c;
    private MessageReceiveCallback d;
    private boolean e;
    private final sno f;
    private Map<String, MessageReceiveCallback> i;

    public boolean e() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    private snq() {
        this.f = sno.b();
        this.b = new ConcurrentHashMap();
        this.f17142a = new ConcurrentHashMap();
        this.i = new ConcurrentHashMap();
        this.e = false;
        this.c = false;
        this.d = new MessageReceiveCallback() { // from class: snq.1
            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
                snq.this.a(i, deviceInfo, biuVar);
            }

            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onChannelEnable(final DeviceInfo deviceInfo, final String str, final int i) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: snq.1.5
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it = snq.this.i.values().iterator();
                        while (it.hasNext()) {
                            ((MessageReceiveCallback) it.next()).onChannelEnable(deviceInfo, str, i);
                        }
                    }
                });
            }
        };
        LogUtil.c("UniteDeviceService", "enter UniteDeviceService");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, DeviceInfo deviceInfo, biu biuVar) {
        if (deviceInfo == null || biuVar == null) {
            LogUtil.a("UniteDeviceService", "data is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<MessageReceiveCallback> it = this.i.values().iterator();
        while (it.hasNext()) {
            it.next().onDataReceived(deviceInfo, biuVar, i);
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 5) {
            LogUtil.c("UniteDeviceService", "repo data suspected block msg:", Long.valueOf(currentTimeMillis2), ",command:", bly.e(biuVar.a()));
        }
    }

    public static void d(IwearLinkConfig iwearLinkConfig) {
        snu.d(iwearLinkConfig);
    }

    public static snq c() {
        return a.f17144a;
    }

    public void a(Context context) {
        if (this.e) {
            return;
        }
        this.e = true;
        this.f.b(context);
        this.f.registerDeviceStateListener(UUID.randomUUID().toString(), new DeviceStatusChangeCallback() { // from class: snq.2
            @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
            public void onConnectStatusChanged(final DeviceInfo deviceInfo, final int i, final int i2) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: snq.2.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ConnectState connectState = ConnectState.getConnectState(i);
                        Iterator it = snq.this.f17142a.values().iterator();
                        while (it.hasNext()) {
                            ((DeviceStatusChangeCallback) it.next()).onConnectStatusChanged(deviceInfo, i, i2);
                        }
                        Iterator it2 = snq.this.b.values().iterator();
                        while (it2.hasNext()) {
                            ((EngineLogicBaseManager) it2.next()).onConnectionChange(connectState, blc.d(deviceInfo));
                        }
                    }
                });
            }
        });
        this.f.registerDeviceMessageListener(UUID.randomUUID().toString(), this.d);
    }

    public void e(WearEngineModule wearEngineModule, EngineLogicBaseManager engineLogicBaseManager) {
        if (wearEngineModule == null) {
            LogUtil.e("UniteDeviceService", "WearEngineModule is null");
        } else if (engineLogicBaseManager == null) {
            LogUtil.e("UniteDeviceService", "EngineLogicBaseManager is null");
        } else {
            this.b.put(wearEngineModule, engineLogicBaseManager);
        }
    }

    public void e(WearEngineModule wearEngineModule) {
        if (wearEngineModule == null) {
            LogUtil.e("UniteDeviceService", "WearEngineModule is null");
        } else if (this.b.containsKey(wearEngineModule)) {
            this.b.remove(wearEngineModule);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void p2pSendForWearEngine(Context context, UniteDevice uniteDevice, snt sntVar, SendCallback sendCallback) {
        snp.c().p2pSendForWearEngine(context, uniteDevice, sntVar, sendCallback);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startTransferFileToWear(sol solVar, IResultAIDLCallback iResultAIDLCallback) {
        snp.c().startTransferFileToWear(solVar, iResultAIDLCallback);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void registerReceiver(Context context, snt sntVar, P2pReceiver p2pReceiver, SendCallback sendCallback) {
        snp.c().registerReceiver(context, sntVar, p2pReceiver, sendCallback);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void unregisterReceiver(Context context, P2pReceiver p2pReceiver, snt sntVar) {
        snp.c().unregisterReceiver(context, p2pReceiver, sntVar);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void pingSendForWearEngine(Context context, UniteDevice uniteDevice, String str, PingCallback pingCallback, int i) {
        snp.c().pingSendForWearEngine(context, uniteDevice, str, pingCallback, i);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        snp.c().startReceiveFileFromWear(requestFileInfo, iTransferFileCallback);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        snp.c().stopReceiveFileFromWear(requestFileInfo, iTransferFileCallback);
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopTransferFileToWear(sol solVar, ITransferFileCallback iTransferFileCallback) {
        snp.c().stopTransferFileToWear(solVar, iTransferFileCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void scanDevice(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        this.c = true;
        this.f.scanDevice(scanMode, list, deviceScanCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void stopScanDevice() {
        this.f.stopScanDevice();
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode) {
        this.f.connectDevice(uniteDevice, z, connectMode);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode, ConnectFilter connectFilter) {
        this.f.connectDevice(uniteDevice, z, connectMode, connectFilter);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void createSystemBond(UniteDevice uniteDevice) {
        this.f.createSystemBond(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void sendCommand(UniteDevice uniteDevice, bir birVar) {
        this.f.sendCommand(uniteDevice, birVar);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public Map<String, UniteDevice> getDeviceList() {
        return this.f.getDeviceList();
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unPairDevice(UniteDevice uniteDevice) {
        this.f.unPairDevice(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void disconnect(UniteDevice uniteDevice) {
        this.f.disconnect(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportService(UniteDevice uniteDevice, String str) {
        return this.f.isSupportService(uniteDevice, str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportCharactor(UniteDevice uniteDevice, String str, String str2) {
        return this.f.isSupportCharactor(uniteDevice, str, str2);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceStateListener(String str, DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.f17142a.put(str, deviceStatusChangeCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceMessageListener(String str, MessageReceiveCallback messageReceiveCallback) {
        this.i.put(str, messageReceiveCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceStateListener(String str) {
        this.f17142a.remove(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceMessageListener(String str) {
        this.i.remove(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerHandshakeFilter(ConnectFilter connectFilter) {
        this.f.registerHandshakeFilter(connectFilter);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, SendMode sendMode, boolean z) {
        this.f.setCharacteristicNotify(uniteDevice, str, str2, sendMode, z);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceCompatibleListener(String str, DeviceCompatibleCallback deviceCompatibleCallback) {
        this.f.registerDeviceCompatibleListener(str, deviceCompatibleCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceCompatibleListener(String str) {
        this.f.unregisterDeviceCompatibleListener(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerListener(String str, String str2, DataReceiveCallback dataReceiveCallback) {
        this.f.registerListener(str, str2, dataReceiveCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterListener(String str, String str2) {
        this.f.unregisterListener(str, str2);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void updateDeviceAfterSimulatConnected(String str) {
        this.f.updateDeviceAfterSimulatConnected(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public List<com.huawei.health.devicemgr.business.entity.DeviceInfo> getDeviceMgrList(int i, String str) {
        return this.f.getDeviceMgrList(i, str);
    }

    /* loaded from: classes7.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static snq f17144a = new snq();
    }
}
