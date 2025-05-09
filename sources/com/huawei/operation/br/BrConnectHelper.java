package com.huawei.operation.br;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.br.BrBroadcastReceiver;
import com.huawei.operation.br.BrClient;

/* loaded from: classes5.dex */
public class BrConnectHelper implements BrBroadcastReceiver.OnBluetoothReceiverListener, BrClient.BtListener {
    private static final String TAG = "BrConnectHelper";
    private BluetoothAdapter mBluetoothAdapter;
    private BrBroadcastReceiver mBrBroadcastReceiver;
    private BrClient mBrClient;
    private BtListener mBtListener;
    private boolean mIsFound;
    private boolean mIsStartDiscoveryAfterOpen;
    private boolean mIsStartScan;
    private String mMac;

    public interface BtListener {
        void socketNotify(int i, String str);
    }

    private BrConnectHelper() {
    }

    @Override // com.huawei.operation.br.BrClient.BtListener
    public void socketNotify(int i, String str) {
        LogUtil.a(TAG, "socketNotify state is ", Integer.valueOf(i));
        BtListener btListener = this.mBtListener;
        if (btListener != null) {
            btListener.socketNotify(i, str);
        }
    }

    static class Instance {
        private static final BrConnectHelper INSTANCE = new BrConnectHelper();

        private Instance() {
        }
    }

    public static BrConnectHelper getInstance() {
        return Instance.INSTANCE;
    }

    public void setFound(boolean z) {
        this.mIsFound = z;
    }

    @Override // com.huawei.operation.br.BrBroadcastReceiver.OnBluetoothReceiverListener
    public void onDeviceFound(BluetoothDevice bluetoothDevice) {
        if (TextUtils.isEmpty(this.mMac) || bluetoothDevice == null) {
            return;
        }
        try {
            if (!this.mMac.equals(bluetoothDevice.getAddress()) || this.mIsFound) {
                return;
            }
            this.mIsFound = true;
            LogUtil.a(TAG, "find connect device name:", bluetoothDevice.getName());
            this.mIsStartScan = false;
            this.mBluetoothAdapter.cancelDiscovery();
            getInstance().unregisterReceiver();
            this.mBrClient.connect(bluetoothDevice);
        } catch (SecurityException e) {
            LogUtil.b(TAG, "onDeviceFound SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.operation.br.BrBroadcastReceiver.OnBluetoothReceiverListener
    public void onScanFinish() {
        if (this.mIsStartScan) {
            this.mIsStartScan = false;
            BtListener btListener = this.mBtListener;
            if (btListener != null) {
                btListener.socketNotify(0, "");
            }
        }
        LogUtil.a(TAG, "onScanFinish");
    }

    @Override // com.huawei.operation.br.BrBroadcastReceiver.OnBluetoothReceiverListener
    public void onStateChanged(int i) {
        try {
            if (i == 10) {
                BtListener btListener = this.mBtListener;
                if (btListener != null) {
                    btListener.socketNotify(0, "");
                    return;
                }
                return;
            }
            if (i != 12) {
                return;
            }
            if (this.mIsStartDiscoveryAfterOpen) {
                this.mBluetoothAdapter.startDiscovery();
            }
            this.mIsStartDiscoveryAfterOpen = false;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "onStateChanged SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void connectDevice(String str, String str2) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            LogUtil.a(TAG, "mBluetoothAdapter is null");
            BtListener btListener = this.mBtListener;
            if (btListener != null) {
                btListener.socketNotify(0, "");
                return;
            }
            return;
        }
        if (this.mBrClient == null) {
            this.mBrClient = new BrClient(this);
        }
        this.mBrClient.setSppUuid(str2);
        this.mMac = str;
        BluetoothDevice bondedDevice = getBondedDevice(str);
        if (bondedDevice == null) {
            LogUtil.a(TAG, "connectDevice getBondedDevice is null");
            bondedDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        }
        if (bondedDevice != null) {
            this.mBrClient.connect(bondedDevice);
        } else {
            LogUtil.a(TAG, "connectDevice getRemoteDevice is null");
            scanDevice();
        }
    }

    private BluetoothDevice getBondedDevice(String str) {
        try {
            for (BluetoothDevice bluetoothDevice : this.mBluetoothAdapter.getBondedDevices()) {
                if (str.equals(bluetoothDevice.getAddress())) {
                    LogUtil.a(TAG, "getConnectDevice name is ", bluetoothDevice.getName());
                    return bluetoothDevice;
                }
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "getBondedDevice SecurityException:", ExceptionUtils.d(e));
        }
        return null;
    }

    public void scanDevice() {
        if (this.mBrBroadcastReceiver == null) {
            this.mBrBroadcastReceiver = new BrBroadcastReceiver(BaseApplication.getContext(), this);
        }
        try {
            if (!this.mBluetoothAdapter.isEnabled()) {
                this.mIsStartDiscoveryAfterOpen = true;
                this.mBluetoothAdapter.enable();
            } else {
                if (this.mBluetoothAdapter.isDiscovering()) {
                    this.mBluetoothAdapter.cancelDiscovery();
                }
                this.mIsStartScan = true;
                this.mBluetoothAdapter.startDiscovery();
            }
        } catch (SecurityException e) {
            LogUtil.b(TAG, "scanDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void releaseResource() {
        LogUtil.a(TAG, "releaseResource");
        try {
            BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
            if (bluetoothAdapter != null && bluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
            BrClient brClient = this.mBrClient;
            if (brClient != null) {
                brClient.releaseResource();
            }
            unregisterReceiver();
        } catch (SecurityException e) {
            LogUtil.b(TAG, "releaseResource SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void unregisterReceiver() {
        if (this.mBrBroadcastReceiver != null) {
            BaseApplication.getContext().unregisterReceiver(this.mBrBroadcastReceiver);
            this.mBrBroadcastReceiver = null;
        }
    }

    public void sendMsg(String str) {
        this.mBrClient.sendMsg(str);
    }

    public void onBtListener(BtListener btListener) {
        this.mBtListener = btListener;
    }
}
