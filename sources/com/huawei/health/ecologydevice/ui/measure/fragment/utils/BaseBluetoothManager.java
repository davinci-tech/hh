package com.huawei.health.ecologydevice.ui.measure.fragment.utils;

import android.bluetooth.BluetoothDevice;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class BaseBluetoothManager {
    protected static final int DELAY_2000MS = 2000;
    protected static final int DELAY_30000MS = 30000;
    protected static final int DELAY_5000MS = 5000;
    protected static final int MSG_BLUETOOTH_OFF = 105;
    protected static final int MSG_BOND_SUCCESS = 119;
    protected static final int MSG_BOND_TIMEOUT = 120;
    protected static final int MSG_CREATE_BOND = 101;
    protected static final int MSG_DEVICE_FOUND = 108;
    protected static final int MSG_REBIND = 102;
    protected static final int MSG_REMOVE_BOND = 107;
    protected static final int MSG_SCAN_FIRST_FAILED = 103;
    protected static final int MSG_SCAN_TIMEOUT = 104;
    protected static final int MSG_STATE_CHANGED = 106;
    protected static final String TAG = "BaseBluetoothManager";
    private RopeStateMonitor mBleDeviceStateMonitor;
    protected DeviceStateCallback mCallback;
    protected int mCurrentStatus;
    protected String mDeviceMacAddress;
    protected String mDeviceName;
    protected ExtendHandler mExtendHandler;
    protected boolean mIsUniformDeviceManagementFlag;
    protected int mNewState;
    protected int mReconnectCount = 0;
    private final RopeStateMonitor.StateChangeListener mStateCallback = new RopeStateMonitor.StateChangeListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.utils.BaseBluetoothManager.1
        @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
        public void onBondStateChanged(int i, BluetoothDevice bluetoothDevice) {
            try {
                String name = bluetoothDevice.getName();
                String address = bluetoothDevice.getAddress();
                if (name == null) {
                    if (address == null || !address.equals(BaseBluetoothManager.this.mDeviceMacAddress)) {
                        LogUtil.h(BaseBluetoothManager.TAG, "not this bond device");
                        return;
                    }
                } else if (!name.equals(BaseBluetoothManager.this.mDeviceName) && !address.equals(BaseBluetoothManager.this.mDeviceMacAddress)) {
                    LogUtil.a(BaseBluetoothManager.TAG, "Ble pair failed: name and address are not equal");
                    return;
                }
                BaseBluetoothManager.this.mNewState = i;
                if (i == 0) {
                    BaseBluetoothManager.this.mCurrentStatus = 14;
                } else if (i == 1) {
                    BaseBluetoothManager.this.mCurrentStatus = 15;
                } else if (i == 2) {
                    BaseBluetoothManager.this.mCurrentStatus = 16;
                    BaseBluetoothManager.this.sendEmptyMessageToHandler(119);
                    return;
                } else if (i != 9) {
                    LogUtil.h(BaseBluetoothManager.TAG, "not have this status");
                }
                BaseBluetoothManager.this.removeMessage(120);
                BaseBluetoothManager.this.sendEmptyMessageToHandler(106);
            } catch (SecurityException e) {
                LogUtil.b(BaseBluetoothManager.TAG, "onBondStateChanged SecurityException:", ExceptionUtils.d(e));
            }
        }

        @Override // com.huawei.health.ecologydevice.fitness.util.RopeStateMonitor.StateChangeListener
        public void onSwitchStateChanged(int i) {
            BaseBluetoothManager.this.mNewState = i;
            if (i == 3) {
                LogUtil.a(BaseBluetoothManager.TAG, "BLUETOOTH_SWITCH_OFF");
                BaseBluetoothManager.this.sendEmptyMessageToHandler(105);
            } else if (i == 4) {
                LogUtil.a(BaseBluetoothManager.TAG, "BLUETOOTH_SWITCH_ON");
            } else {
                LogUtil.a(BaseBluetoothManager.TAG, "BluetoothSwitchMonitorReceiver other blueState: ", Integer.valueOf(i));
            }
        }
    };

    public boolean init(DeviceStateCallback deviceStateCallback) {
        registerDeviceCallback(deviceStateCallback);
        initBleDeviceStateMonitor();
        return false;
    }

    public void releaseSource() {
        releaseHandler();
        unRegisterDeviceCallback();
        releaseDeviceStateMonitor();
    }

    private void registerDeviceCallback(DeviceStateCallback deviceStateCallback) {
        this.mCallback = deviceStateCallback;
    }

    private void unRegisterDeviceCallback() {
        this.mCallback = null;
    }

    private void initBleDeviceStateMonitor() {
        RopeStateMonitor ropeStateMonitor = this.mBleDeviceStateMonitor;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
        }
        RopeStateMonitor ropeStateMonitor2 = new RopeStateMonitor(BaseApplication.getContext(), this.mStateCallback);
        this.mBleDeviceStateMonitor = ropeStateMonitor2;
        ropeStateMonitor2.c();
    }

    private void releaseHandler() {
        if (this.mExtendHandler != null) {
            LogUtil.a(TAG, "releaseHandler msgHandler will removeCallbacksAndMessages");
            this.mExtendHandler.removeTasksAndMessages();
            this.mExtendHandler.quit(false);
            this.mExtendHandler = null;
        }
    }

    private void releaseDeviceStateMonitor() {
        RopeStateMonitor ropeStateMonitor = this.mBleDeviceStateMonitor;
        if (ropeStateMonitor != null) {
            ropeStateMonitor.d();
            this.mBleDeviceStateMonitor = null;
        }
    }

    protected void sendMessageToHandlerWithDelay(int i, long j) {
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i, j);
        }
    }

    protected void sendEmptyMessageToHandler(int i) {
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.sendEmptyMessage(i);
        }
    }

    protected void removeMessage(int i) {
        ExtendHandler extendHandler = this.mExtendHandler;
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    public void setDeviceNameAndMac(String str, String str2) {
        this.mDeviceName = str;
        this.mDeviceMacAddress = str2;
    }

    public void setIsUniformDeviceManagementFlag(boolean z) {
        this.mIsUniformDeviceManagementFlag = z;
        LogUtil.a(TAG, "mIsUniformDeviceManagementFlag = ", Boolean.valueOf(z));
    }
}
