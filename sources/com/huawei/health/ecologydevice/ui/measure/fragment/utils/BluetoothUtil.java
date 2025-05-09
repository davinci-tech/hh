package com.huawei.health.ecologydevice.ui.measure.fragment.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.cxh;
import defpackage.ddw;
import defpackage.dew;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class BluetoothUtil extends BaseBluetoothManager {
    private static final Object LOCK = new Object();
    private static final int RECONNECT_COUNT_NUM = 1;
    private static final String TAG = "BluetoothUtil";
    private static BluetoothUtil sInstance;
    private boolean isStopScan;
    private cxh mBleDevice;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothManager mBluetoothManager;
    private String mDeviceStateListenId;
    private UniteDevice mUniteDevice;
    private final ExtendHandlerCallback mExtendHandlerCallback = new ExtendHandlerCallback();
    private boolean mIsScanning = false;
    private boolean isBinding = false;
    private final ScanCallback mScanCallback = new ScanCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.utils.BluetoothUtil.1
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            BluetoothUtil.this.stopScanDevice();
            if (scanResult != null) {
                try {
                    if (scanResult.getDevice() != null) {
                        LogUtil.a(BluetoothUtil.TAG, "get device in scanning: ", scanResult.getDevice().getName());
                        BluetoothUtil.this.distinguishDevice(scanResult.getDevice());
                    }
                } catch (SecurityException e) {
                    LogUtil.b(BluetoothUtil.TAG, "onScanResult SecurityException:", ExceptionUtils.d(e));
                    return;
                }
            }
            LogUtil.h(BluetoothUtil.TAG, "result is null or result.getDevice is null");
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            LogUtil.c(BluetoothUtil.TAG, "onBatchScanResults : results=" + list.size());
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            LogUtil.h(BluetoothUtil.TAG, "onScanFailed : errorCode=" + i);
        }
    };
    private final DeviceScanCallback mDeviceScanCallback = new DeviceScanCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.utils.BluetoothUtil.2
        @Override // com.huawei.devicesdk.callback.DeviceScanCallback
        public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str, int i) {
            LogUtil.a(BluetoothUtil.TAG, "device: ", uniteDevice, "status: ", Integer.valueOf(i));
            if (i == 20 && uniteDevice != null) {
                String deviceName = uniteDevice.getDeviceInfo().getDeviceName();
                LogUtil.a(BluetoothUtil.TAG, "scanResult deviceName: ", deviceName);
                if (!TextUtils.isEmpty(deviceName)) {
                    if (BluetoothUtil.this.isStopScan) {
                        return;
                    }
                    BluetoothUtil.this.deviceScanSucByUds(uniteDevice);
                    return;
                }
                LogUtil.h(BluetoothUtil.TAG, "deviceName is empty");
                return;
            }
            LogUtil.h(BluetoothUtil.TAG, "scanResult: " + i);
        }
    };
    private final DeviceStatusChangeCallback mDeviceStatusChangeCallback = new DeviceStatusChangeCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.utils.BluetoothUtil.3
        @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
        public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
            LogUtil.a(BluetoothUtil.TAG, "device: ", deviceInfo.toString(), "status: ", Integer.valueOf(i), "errCode: ", Integer.valueOf(i2));
            BluetoothUtil.this.mCurrentStatus = 16;
            String deviceName = deviceInfo.getDeviceName();
            String deviceMac = deviceInfo.getDeviceMac();
            if (deviceName == null) {
                if (deviceMac == null || !deviceMac.equals(BluetoothUtil.this.mDeviceMacAddress)) {
                    LogUtil.h(BluetoothUtil.TAG, "not this bond device");
                    return;
                }
            } else if (!deviceName.equals(BluetoothUtil.this.mDeviceName) && !deviceMac.equals(BluetoothUtil.this.mDeviceMacAddress)) {
                LogUtil.a(BluetoothUtil.TAG, "Ble pair failed: name and address are not equal");
                return;
            }
            switch (i) {
                case 30:
                    LogUtil.a(BluetoothUtil.TAG, "bonded_device_success");
                    BluetoothUtil.this.mCurrentStatus = 16;
                    ddw.c().d(BluetoothUtil.this.mDeviceStateListenId);
                    BluetoothUtil.this.mNewState = 12;
                    dew.b(deviceMac, 1);
                    BluetoothUtil.this.sendEmptyMessageToHandler(119);
                    return;
                case 31:
                    LogUtil.a(BluetoothUtil.TAG, "bonded_device_ing");
                    BluetoothUtil.this.mCurrentStatus = 15;
                    BluetoothUtil.this.mNewState = 11;
                    break;
                case 32:
                    LogUtil.a(BluetoothUtil.TAG, "bonded_device_failed");
                    BluetoothUtil.this.mCurrentStatus = 14;
                    BluetoothUtil.this.mNewState = 10;
                    ddw.c().d(BluetoothUtil.this.mDeviceStateListenId);
                    break;
                case 33:
                    dew.b(deviceMac, 0);
                    break;
                default:
                    LogUtil.h(BluetoothUtil.TAG, "unknown status", Integer.valueOf(i));
                    break;
            }
            BluetoothUtil.this.sendEmptyMessageToHandler(106);
        }
    };

    public static BluetoothUtil getInstance() {
        BluetoothUtil bluetoothUtil;
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new BluetoothUtil();
            }
            bluetoothUtil = sInstance;
        }
        return bluetoothUtil;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.utils.BaseBluetoothManager
    public boolean init(DeviceStateCallback deviceStateCallback) {
        if (deviceStateCallback == null) {
            LogUtil.h(TAG, "DeviceStateCallback == null");
            return false;
        }
        super.init(deviceStateCallback);
        initHandler();
        if (this.mBluetoothAdapter != null) {
            LogUtil.a(TAG, "Init already.");
            return true;
        }
        if (this.mBluetoothManager == null) {
            Object systemService = BaseApplication.getContext().getSystemService("bluetooth");
            if (systemService instanceof BluetoothManager) {
                this.mBluetoothManager = (BluetoothManager) systemService;
            }
        }
        BluetoothManager bluetoothManager = this.mBluetoothManager;
        if (bluetoothManager == null) {
            LogUtil.b(TAG, "Unable to initAdapter BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.mBluetoothAdapter = adapter;
        if (adapter != null) {
            return true;
        }
        LogUtil.b(TAG, "Unable to obtain BluetoothAdapter.");
        return false;
    }

    private void initHandler() {
        if (this.mExtendHandler == null) {
            this.mExtendHandler = HandlerCenter.yt_(this.mExtendHandlerCallback, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanDeviceAssertUds() {
        if (this.mIsUniformDeviceManagementFlag) {
            scanLeDeviceByUds();
        } else {
            scanLeDevice();
        }
    }

    private void scanLeDeviceByUds() {
        sendMessageToHandlerWithDelay(103, 5000L);
        if (this.mReconnectCount < 1) {
            sendMessageToHandlerWithDelay(104, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.isStopScan = false;
        ddw.c().b(ScanMode.BLE, ddw.c().b(this.mDeviceName, this.mDeviceMacAddress), this.mDeviceScanCallback);
    }

    private void scanLeDevice() {
        sendMessageToHandlerWithDelay(103, 5000L);
        if (this.mReconnectCount < 1) {
            sendMessageToHandlerWithDelay(104, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (this.mBluetoothAdapter != null && (!TextUtils.isEmpty(this.mDeviceName) || !TextUtils.isEmpty(this.mDeviceMacAddress))) {
            LogUtil.a(TAG, "SDK is higher than LOLLIPOP");
            scanEnableBle();
        } else {
            LogUtil.h(TAG, "scanBleDevice stop scan");
        }
    }

    public void stopScanDevice() {
        try {
            if (this.mIsScanning) {
                LogUtil.a(TAG, "stopScanDevice ");
                this.mIsScanning = false;
                BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(this.mScanCallback);
                }
            }
            this.isStopScan = true;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "stopScanDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void scanEnableBle() {
        ScanFilter build;
        this.mIsScanning = true;
        ArrayList arrayList = new ArrayList(10);
        try {
            if (!TextUtils.isEmpty(this.mDeviceName)) {
                build = new ScanFilter.Builder().setDeviceName(this.mDeviceName).build();
            } else {
                build = new ScanFilter.Builder().setDeviceAddress(this.mDeviceMacAddress).build();
            }
            arrayList.add(build);
            LogUtil.a(TAG, "now scanner start scan");
            BluetoothLeScanner bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(1).build(), this.mScanCallback);
            } else {
                LogUtil.b(TAG, "scanner = null");
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "scanEnableBle SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void getConnectedDeviceList() {
        try {
            Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                Iterator<BluetoothDevice> it = bondedDevices.iterator();
                while (it.hasNext()) {
                    checkDevice(it.next());
                }
                if (this.isBinding) {
                    distinguishDevice(this.mBluetoothDevice);
                    return;
                } else {
                    scanDeviceAssertUds();
                    return;
                }
            }
            scanDeviceAssertUds();
        } catch (SecurityException e) {
            LogUtil.b(TAG, "getConnectedDeviceList SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void checkDevice(BluetoothDevice bluetoothDevice) {
        try {
            if ((TextUtils.isEmpty(this.mDeviceName) || !this.mDeviceName.equals(bluetoothDevice.getName())) && (TextUtils.isEmpty(this.mDeviceMacAddress) || !this.mDeviceMacAddress.equals(bluetoothDevice.getAddress()))) {
                return;
            }
            this.isBinding = true;
            this.mBluetoothDevice = bluetoothDevice;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "checkDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void distinguishDevice(BluetoothDevice bluetoothDevice) {
        LogUtil.c(TAG, "now will distinguish device.");
        if (bluetoothDevice == null) {
            return;
        }
        try {
            if ((TextUtils.isEmpty(this.mDeviceName) || !this.mDeviceName.equals(bluetoothDevice.getName())) && (TextUtils.isEmpty(this.mDeviceMacAddress) || !this.mDeviceMacAddress.equals(bluetoothDevice.getAddress()))) {
                return;
            }
            LogUtil.a(TAG, "found the device we need successfully");
            removeMessage(103);
            removeMessage(104);
            this.mBluetoothDevice = bluetoothDevice;
            cxh Ra_ = cxh.Ra_(bluetoothDevice);
            stopScanDevice();
            if (this.mCallback != null) {
                this.mCallback.onNotify(17, this.mNewState, this.mReconnectCount, Ra_);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "distinguishDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deviceScanSucByUds(UniteDevice uniteDevice) {
        LogUtil.c(TAG, "now will deviceScanSucByUds");
        if (uniteDevice == null) {
            return;
        }
        if ((TextUtils.isEmpty(this.mDeviceName) || !this.mDeviceName.equals(uniteDevice.getDeviceInfo().getDeviceName())) && (TextUtils.isEmpty(this.mDeviceMacAddress) || !this.mDeviceMacAddress.equals(uniteDevice.getDeviceInfo().getDeviceMac()))) {
            return;
        }
        LogUtil.a(TAG, "found the device we need successfully by uds");
        removeMessage(103);
        removeMessage(104);
        this.mUniteDevice = uniteDevice;
        cxh d = cxh.d(uniteDevice);
        stopScanDevice();
        if (this.mCallback != null) {
            this.mCallback.onNotify(17, this.mNewState, this.mReconnectCount, d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recreateBondDevice() {
        if (this.mIsScanning) {
            stopScanDevice();
        }
        this.mReconnectCount++;
        if (this.mReconnectCount == 1) {
            this.mCallback.onNotify(10, this.mNewState, this.mReconnectCount, this.mBleDevice);
        }
        sendMessageToHandlerWithDelay(102, 2000L);
    }

    private void clearInstance() {
        synchronized (LOCK) {
            sInstance = null;
        }
    }

    public void createBond() {
        sendEmptyMessageToHandler(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createBondDevice() {
        try {
            BluetoothDevice bluetoothDevice = this.mBluetoothDevice;
            if (bluetoothDevice != null && bluetoothDevice.getBondState() != 12) {
                sendMessageToHandlerWithDelay(120, OpAnalyticsConstants.H5_LOADING_DELAY);
                this.mBluetoothDevice.createBond();
            }
            if (this.mIsUniformDeviceManagementFlag) {
                this.mDeviceStateListenId = UUID.randomUUID().toString();
                ddw.c().a(this.mDeviceStateListenId, this.mDeviceStatusChangeCallback);
                ddw.c().d(this.mUniteDevice);
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "createBondDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void scanDevice() {
        getConnectedDeviceList();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.utils.BaseBluetoothManager
    public void releaseSource() {
        super.releaseSource();
        clearInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDevice(Class cls, BluetoothDevice bluetoothDevice) {
        if (cls == null || bluetoothDevice == null) {
            LogUtil.h(TAG, "clazz == null || bleDevice == null");
            return;
        }
        try {
            cls.getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.b(TAG, "removeDevice exception");
        }
    }

    class ExtendHandlerCallback implements Handler.Callback {
        private ExtendHandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a(BluetoothUtil.TAG, "msg what is ：", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 119) {
                if (i != 120) {
                    switch (i) {
                        case 101:
                            BluetoothUtil.this.createBondDevice();
                            return true;
                        case 102:
                            BluetoothUtil.this.scanDeviceAssertUds();
                            return true;
                        case 103:
                            BluetoothUtil.this.recreateBondDevice();
                            return true;
                        case 104:
                            break;
                        case 105:
                            BluetoothUtil.this.mCallback.onNotify(12, BluetoothUtil.this.mNewState, BluetoothUtil.this.mReconnectCount, BluetoothUtil.this.mBleDevice);
                            return true;
                        case 106:
                            BluetoothUtil.this.mCallback.onNotify(BluetoothUtil.this.mCurrentStatus, BluetoothUtil.this.mNewState, BluetoothUtil.this.mReconnectCount, BluetoothUtil.this.mBleDevice);
                            return true;
                        case 107:
                            BluetoothUtil bluetoothUtil = BluetoothUtil.this;
                            bluetoothUtil.removeDevice(bluetoothUtil.mBluetoothDevice.getClass(), BluetoothUtil.this.mBluetoothDevice);
                            return true;
                        case 108:
                            BluetoothUtil.this.stopScanDevice();
                            BluetoothUtil.this.mCallback.onNotify(17, BluetoothUtil.this.mNewState, BluetoothUtil.this.mReconnectCount, BluetoothUtil.this.mBleDevice);
                            return true;
                        default:
                            LogUtil.h(BluetoothUtil.TAG, "default case");
                            return false;
                    }
                }
                BluetoothUtil.this.mCallback.onNotify(13, BluetoothUtil.this.mNewState, BluetoothUtil.this.mReconnectCount, BluetoothUtil.this.mBleDevice);
                BluetoothUtil.this.stopScanDevice();
                BluetoothUtil.this.releaseSource();
                return true;
            }
            if (BluetoothUtil.this.mIsUniformDeviceManagementFlag) {
                BluetoothUtil bluetoothUtil2 = BluetoothUtil.this;
                bluetoothUtil2.mBleDevice = cxh.d(bluetoothUtil2.mUniteDevice);
            } else {
                BluetoothUtil bluetoothUtil3 = BluetoothUtil.this;
                bluetoothUtil3.mBleDevice = cxh.Ra_(bluetoothUtil3.mBluetoothDevice);
            }
            BluetoothUtil.this.mCallback.onNotify(18, BluetoothUtil.this.mNewState, BluetoothUtil.this.mReconnectCount, BluetoothUtil.this.mBleDevice);
            BluetoothUtil.this.stopScanDevice();
            return true;
        }
    }
}
