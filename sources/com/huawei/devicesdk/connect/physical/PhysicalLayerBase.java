package com.huawei.devicesdk.connect.physical;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.BtDevicePairCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import defpackage.bia;
import defpackage.bim;
import defpackage.bjx;
import defpackage.bkw;
import defpackage.bln;
import defpackage.blt;
import defpackage.bmh;
import defpackage.bmw;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public abstract class PhysicalLayerBase {
    private static final String TAG = "PhysicalLayerBase";
    protected BluetoothDevice mBluetoothDevice;
    public DeviceInfo mDeviceInfo;
    public MessageReceiveCallback mMessageReceiveCallback;
    protected DeviceStatusChangeCallback mStatusChangeCallback;
    protected bkw mCommonAdapterUtil = bkw.d();
    private BtDevicePairCallback mDevicePairCallback = new BtDevicePairCallback() { // from class: com.huawei.devicesdk.connect.physical.PhysicalLayerBase.2
        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairing(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                LogUtil.c(PhysicalLayerBase.TAG, "onDevicePairing device is null");
                return;
            }
            LogUtil.c(PhysicalLayerBase.TAG, "onDevicePairing", blt.a(bluetoothDevice.getAddress()));
            PhysicalLayerBase physicalLayerBase = PhysicalLayerBase.this;
            physicalLayerBase.reportStatusChange(physicalLayerBase.mDeviceInfo, 31, 100000);
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePaired(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                LogUtil.c(PhysicalLayerBase.TAG, "onDevicePaired device is null");
                return;
            }
            LogUtil.c(PhysicalLayerBase.TAG, "onDevicePaired", blt.a(bluetoothDevice.getAddress()));
            PhysicalLayerBase physicalLayerBase = PhysicalLayerBase.this;
            physicalLayerBase.reportStatusChange(physicalLayerBase.mDeviceInfo, 30, 100000);
        }

        @Override // com.huawei.devicesdk.callback.BtDevicePairCallback
        public void onDevicePairNone(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                LogUtil.c(PhysicalLayerBase.TAG, "onDevicePairNone device is null");
                return;
            }
            LogUtil.c(PhysicalLayerBase.TAG, "onDevicePairNone", blt.a(bluetoothDevice.getAddress()));
            PhysicalLayerBase physicalLayerBase = PhysicalLayerBase.this;
            physicalLayerBase.reportStatusChange(physicalLayerBase.mDeviceInfo, 32, 100000);
        }
    };

    public abstract void connectDevice(DeviceInfo deviceInfo);

    public abstract void destroy();

    public abstract void disconnectDevice();

    public boolean isSupportCharactor(String str, String str2) {
        return false;
    }

    public boolean isSupportService(String str) {
        return false;
    }

    public abstract boolean sendData(bim bimVar, String str);

    protected void sendTimeoutMessage(bia biaVar, int i) {
        sendTimeoutMessage(biaVar, i, 25000L);
    }

    protected void sendTimeoutMessage(bia biaVar, int i, long j) {
        if (biaVar == null) {
            LogUtil.e(TAG, "mConnectHandler is null, sendTimeoutMessage fail.");
            return;
        }
        Message rP_ = biaVar.rP_(4);
        rP_.arg1 = bln.e(i, 304);
        rP_.obj = Long.valueOf(System.currentTimeMillis() + j);
        biaVar.rR_(rP_, j);
    }

    protected void sendTimeoutMessageForService(bia biaVar) {
        if (biaVar == null) {
            LogUtil.a(TAG, "sendTimeoutMessageForService mConnectHandler is null, sendTimeoutMessage fail.");
            return;
        }
        Message rP_ = biaVar.rP_(4);
        rP_.arg1 = bln.e(2, 304);
        biaVar.rR_(rP_, PreConnectManager.CONNECT_INTERNAL);
    }

    protected void reportMonitorResult(Message message, DeviceInfo deviceInfo, int i) {
        if (message == null || deviceInfo == null) {
            LogUtil.e(TAG, "message or deviceInfo is null");
            return;
        }
        Object obj = message.obj;
        if (obj instanceof Long) {
            if (System.currentTimeMillis() - ((Long) obj).longValue() > 2000) {
                bmw.e(100071, bmh.b(deviceInfo.getDeviceName()), bmh.b(Integer.valueOf(message.what)), Byte.toString(deviceInfo.getTriggerType()));
                LogUtil.c(TAG, "freezing connect timeout,", blt.a(this.mDeviceInfo));
                return;
            }
        }
        bmw.e(i, bmh.b(deviceInfo.getDeviceName()), bmh.b(Integer.valueOf(message.what)), "");
    }

    protected void setTriggerType(int i, DeviceInfo deviceInfo) {
        if (i == 1 || deviceInfo == null || deviceInfo.getTriggerType() == 0) {
            return;
        }
        LogUtil.c(TAG, "deviceInfo triggerType is ", Byte.valueOf(deviceInfo.getTriggerType()));
        deviceInfo.setTriggerType((byte) 0);
    }

    public void init(DeviceInfo deviceInfo, DeviceStatusChangeCallback deviceStatusChangeCallback, MessageReceiveCallback messageReceiveCallback) {
        this.mDeviceInfo = deviceInfo;
        addDeviceStatusChangeListener(deviceStatusChangeCallback);
        addMessageReceiveListener(messageReceiveCallback);
    }

    public void addDeviceStatusChangeListener(DeviceStatusChangeCallback deviceStatusChangeCallback) {
        this.mStatusChangeCallback = deviceStatusChangeCallback;
    }

    public void addMessageReceiveListener(MessageReceiveCallback messageReceiveCallback) {
        this.mMessageReceiveCallback = messageReceiveCallback;
    }

    public DeviceInfo getCurrentDeviceInfo() {
        return this.mDeviceInfo;
    }

    public void pairDevice(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.c(TAG, "pair device error. device is invalid.");
            return;
        }
        LogUtil.c(TAG, "pair device start.", blt.a(deviceInfo));
        this.mDeviceInfo = deviceInfo;
        String deviceMac = deviceInfo.getDeviceMac();
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        if (bluetoothDevice == null || !bluetoothDevice.getAddress().equals(deviceMac)) {
            this.mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceMac);
        }
        BluetoothDevice bluetoothDevice2 = this.mBluetoothDevice;
        if (bluetoothDevice2 == null) {
            LogUtil.c(TAG, "pair device error. mBluetoothDevice is null.");
            return;
        }
        bkw bkwVar = this.mCommonAdapterUtil;
        if (bkwVar == null) {
            LogUtil.c(TAG, "pair device error. mCommonAdapterUtil is null.");
            return;
        }
        boolean sJ_ = bkwVar.sJ_(bluetoothDevice2, this.mDevicePairCallback);
        LogUtil.c(TAG, "pair device. ", blt.a(deviceInfo), " result:", Boolean.valueOf(sJ_));
        if (sJ_) {
            return;
        }
        reportStatusChange(deviceInfo, 32, 60032);
    }

    public boolean unPairDevice(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.c(TAG, "unPair device error. device is invalid.");
            return false;
        }
        LogUtil.c(TAG, "unPair device start.", blt.a(deviceInfo));
        this.mDeviceInfo = deviceInfo;
        String deviceMac = deviceInfo.getDeviceMac();
        BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
        if (bluetoothDevice == null || !bluetoothDevice.getAddress().equals(deviceMac)) {
            this.mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceMac);
        }
        BluetoothDevice bluetoothDevice2 = this.mBluetoothDevice;
        if (bluetoothDevice2 == null) {
            LogUtil.c(TAG, "unPair device error. mBluetoothDevice is null.");
            return false;
        }
        bkw bkwVar = this.mCommonAdapterUtil;
        if (bkwVar == null) {
            LogUtil.c(TAG, "unPair device error. mCommonAdapterUtil is null");
            return false;
        }
        boolean sK_ = bkwVar.sK_(bluetoothDevice2);
        LogUtil.c(TAG, "unPair device.", blt.a(deviceInfo), " result:", Boolean.valueOf(sK_));
        bmw.e(100027, bmh.b(deviceInfo.getDeviceName()), bmh.b(Boolean.valueOf(sK_)), "");
        if (sK_) {
            reportStatusChange(deviceInfo, 33, 100000);
        } else {
            reportStatusChange(deviceInfo, 34, 70034);
        }
        disconnectDevice();
        clearDataCache();
        return sK_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportStatusChange(DeviceInfo deviceInfo, int i, int i2) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.e(TAG, "deviceInfo is invalid, reportStatusChange fail.");
            return;
        }
        if (this.mStatusChangeCallback == null) {
            LogUtil.e(TAG, "mStatusChangeCallback is null, reportStatusChange fail.");
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        if (deviceInfo.getDeviceType() <= 0) {
            DeviceInfo j = bjx.a().j(deviceMac);
            if (j == null) {
                j = bjx.a().e(deviceMac);
            }
            if (j == null) {
                LogUtil.e(TAG, "unknown device, reportStatusChange fail.");
                return;
            }
            deviceInfo.setDeviceType(j.getDeviceType());
        }
        this.mStatusChangeCallback.onConnectStatusChanged(deviceInfo, i, i2);
    }

    private void clearDataCache() {
        LogUtil.c(TAG, "clearDataCache");
        bjx.a().d(this.mDeviceInfo.getDeviceMac());
    }
}
