package com.huawei.health.ecologydevice.open;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothHealthAppConfiguration;
import android.bluetooth.BluetoothHealthCallback;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ceu;
import defpackage.cpp;
import defpackage.dew;

/* loaded from: classes3.dex */
public abstract class HdpMeasureController extends MeasureController {
    private static final String TAG = "HdpMeasureController";
    private static final int WAIT_SERVICE_CONNECTION_TIME = 500;
    protected IHealthDeviceCallback mBaseResponseCallback;
    private BluetoothHealth mBluetoothHealth;
    protected ceu mDevice;
    private BluetoothHealthAppConfiguration mHwHealthAppConfig;
    private final BluetoothHealthCallback mHwHealthCallback = new BluetoothHealthCallback() { // from class: com.huawei.health.ecologydevice.open.HdpMeasureController.5
        @Override // android.bluetooth.BluetoothHealthCallback
        public void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration bluetoothHealthAppConfiguration, int i) {
            LogUtil.a(HdpMeasureController.TAG, "onHealthAppConfigurationStatusChange status is ", Integer.valueOf(i));
            if (i == 1) {
                HdpMeasureController.this.mHwHealthAppConfig = null;
            } else if (i == 0) {
                HdpMeasureController.this.mHwHealthAppConfig = bluetoothHealthAppConfiguration;
            } else {
                LogUtil.h(HdpMeasureController.TAG, "onHealthAppConfigurationStatusChange: status irregular : ", Integer.valueOf(i));
            }
        }

        @Override // android.bluetooth.BluetoothHealthCallback
        public void onHealthChannelStateChange(BluetoothHealthAppConfiguration bluetoothHealthAppConfiguration, BluetoothDevice bluetoothDevice, int i, int i2, ParcelFileDescriptor parcelFileDescriptor, int i3) {
            if (bluetoothHealthAppConfiguration != null) {
                if (i == 0 && i2 == 2 && bluetoothHealthAppConfiguration.equals(HdpMeasureController.this.mHwHealthAppConfig)) {
                    HdpMeasureController.this.mChannelId = i3;
                    cpp.a(HdpMeasureController.this.new d(i, i2, parcelFileDescriptor, i3));
                    return;
                }
                return;
            }
            LogUtil.h(HdpMeasureController.TAG, "onHealthChannelStateChange config is null");
        }
    };
    private final BluetoothProfile.ServiceListener mHwServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.huawei.health.ecologydevice.open.HdpMeasureController.4
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 3) {
                try {
                    if (bluetoothProfile instanceof BluetoothHealth) {
                        HdpMeasureController.this.mBluetoothHealth = (BluetoothHealth) bluetoothProfile;
                        HdpMeasureController.this.mBluetoothHealth.registerSinkAppConfiguration("HDP", HdpMeasureController.this.getDataType(), HdpMeasureController.this.mHwHealthCallback);
                        dew.b(HdpMeasureController.this.mDevice.getAddress(), 1);
                    }
                } catch (SecurityException e) {
                    LogUtil.b(HdpMeasureController.TAG, "onServiceConnected SecurityException:", ExceptionUtils.d(e));
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 3) {
                try {
                    HdpMeasureController.this.mBluetoothHealth = null;
                    dew.b(HdpMeasureController.this.mDevice.getAddress(), 0);
                } catch (SecurityException e) {
                    LogUtil.b(HdpMeasureController.TAG, "onServiceDisconnected SecurityException:", ExceptionUtils.d(e));
                }
            }
        }
    };
    private int mChannelId = 0;

    protected abstract int getDataType();

    protected abstract void handleHealthChannelStateChange(int i, int i2, ParcelFileDescriptor parcelFileDescriptor, int i3);

    @Override // com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        if (!(healthDevice instanceof ceu) || iHealthDeviceCallback == null) {
            return false;
        }
        this.mDevice = (ceu) healthDevice;
        this.mBaseResponseCallback = iHealthDeviceCallback;
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(cpp.a(), this.mHwServiceListener, 3);
        try {
            Thread.sleep(500L);
            return true;
        } catch (InterruptedException e) {
            LogUtil.b(TAG, e.getMessage());
            return true;
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        try {
            BluetoothHealth bluetoothHealth = this.mBluetoothHealth;
            if (bluetoothHealth == null || this.mHwHealthAppConfig == null) {
                return false;
            }
            return bluetoothHealth.connectChannelToSource(this.mDevice.Ek_(), this.mHwHealthAppConfig);
        } catch (SecurityException e) {
            LogUtil.b(TAG, "start SecurityException:", ExceptionUtils.d(e));
            return false;
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void ending() {
        BluetoothHealth bluetoothHealth;
        try {
            ceu ceuVar = this.mDevice;
            if (ceuVar == null || (bluetoothHealth = this.mBluetoothHealth) == null) {
                return;
            }
            bluetoothHealth.disconnectChannel(ceuVar.Ek_(), this.mHwHealthAppConfig, this.mChannelId);
            this.mBluetoothHealth.unregisterAppConfiguration(this.mHwHealthAppConfig);
            this.mBluetoothHealth = null;
        } catch (SecurityException e) {
            LogUtil.b(TAG, "ending SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        ending();
        this.mDevice = null;
        this.mBluetoothHealth = null;
        this.mHwHealthAppConfig = null;
    }

    final class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f2311a;
        private int b;
        private int c;
        private ParcelFileDescriptor d;

        d(int i, int i2, ParcelFileDescriptor parcelFileDescriptor, int i3) {
            this.c = i;
            this.b = i2;
            this.d = parcelFileDescriptor;
            this.f2311a = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            HdpMeasureController.this.handleHealthChannelStateChange(this.c, this.b, this.d, this.f2311a);
        }
    }
}
