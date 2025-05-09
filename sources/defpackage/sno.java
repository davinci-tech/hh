package defpackage;

import android.content.Context;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.api.UniteChannelInterface;
import com.huawei.unitedevice.entity.UniteDevice;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class sno implements UniteChannelInterface {

    /* renamed from: a, reason: collision with root package name */
    private final snr f17140a;

    private sno() {
        this.f17140a = snr.a();
    }

    public void b(Context context) {
        this.f17140a.e(context);
    }

    public static sno b() {
        return d.f17141a;
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void scanDevice(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        this.f17140a.scanDevice(scanMode, list, deviceScanCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void stopScanDevice() {
        this.f17140a.stopScanDevice();
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode) {
        this.f17140a.connectDevice(uniteDevice, z, connectMode);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode, ConnectFilter connectFilter) {
        this.f17140a.connectDevice(uniteDevice, z, connectMode, connectFilter);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void createSystemBond(UniteDevice uniteDevice) {
        this.f17140a.createSystemBond(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void sendCommand(UniteDevice uniteDevice, bir birVar) {
        this.f17140a.sendCommand(uniteDevice, birVar);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public Map<String, UniteDevice> getDeviceList() {
        return this.f17140a.getDeviceList();
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unPairDevice(UniteDevice uniteDevice) {
        this.f17140a.unPairDevice(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void disconnect(UniteDevice uniteDevice) {
        this.f17140a.disconnect(uniteDevice);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportService(UniteDevice uniteDevice, String str) {
        return this.f17140a.isSupportService(uniteDevice, str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public boolean isSupportCharactor(UniteDevice uniteDevice, String str, String str2) {
        return this.f17140a.isSupportCharactor(uniteDevice, str, str2);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceStateListener(String str, DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.f17140a.registerDeviceStateListener(str, deviceStatusChangeCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceMessageListener(String str, MessageReceiveCallback messageReceiveCallback) {
        this.f17140a.registerDeviceMessageListener(str, messageReceiveCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceStateListener(String str) {
        this.f17140a.unregisterDeviceStateListener(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceMessageListener(String str) {
        this.f17140a.unregisterDeviceMessageListener(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerHandshakeFilter(ConnectFilter connectFilter) {
        this.f17140a.registerHandshakeFilter(connectFilter);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, SendMode sendMode, boolean z) {
        this.f17140a.setCharacteristicNotify(uniteDevice, str, str2, sendMode, z);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerDeviceCompatibleListener(String str, DeviceCompatibleCallback deviceCompatibleCallback) {
        this.f17140a.registerDeviceCompatibleListener(str, deviceCompatibleCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterDeviceCompatibleListener(String str) {
        this.f17140a.unregisterDeviceCompatibleListener(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void registerListener(String str, String str2, DataReceiveCallback dataReceiveCallback) {
        this.f17140a.registerListener(str, str2, dataReceiveCallback);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void unregisterListener(String str, String str2) {
        this.f17140a.unregisterListener(str, str2);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public void updateDeviceAfterSimulatConnected(String str) {
        this.f17140a.updateDeviceAfterSimulatConnected(str);
    }

    @Override // com.huawei.unitedevice.api.UniteChannelInterface
    public List<DeviceInfo> getDeviceMgrList(int i, String str) {
        return this.f17140a.getDeviceMgrList(i, str);
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static sno f17141a = new sno();
    }
}
