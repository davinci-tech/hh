package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.Services;
import java.util.UUID;

/* loaded from: classes3.dex */
public class dak extends GattMeasureController implements ConnectStatusCallback, DataChangedCallback {
    private static final Object d = new Object();
    private static dak e;
    private String b;
    private IHealthDeviceCallback c;
    private BluetoothGatt i;
    private boolean l;
    private int m;
    private String n;
    private c o;
    private final String k = HealthDevice.HealthDeviceKind.HDK_HEART_RATE.name();
    private final HandlerThread h = new HandlerThread("HeartRateMeasureController");

    /* renamed from: a, reason: collision with root package name */
    private int f11570a = 0;
    private boolean g = true;
    private BluetoothGattCallback f = new BluetoothGattCallback() { // from class: dak.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            dak.this.ST_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (dak.this.o != null) {
                dak.this.o.removeCallbacksAndMessages(null);
            }
            LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController onServicesDiscovered statusInt = ", Integer.valueOf(i));
            if (dak.this.m != 1) {
                LogUtil.a("HeartRateMeasureController", "HRPController onServicesDiscovered mState is disconnected");
            } else if (i == 0) {
                dak.this.SU_(bluetoothGatt, true);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (dak.this.m != 1) {
                LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController onCharacteristicChanged mState is disconnected");
                return;
            }
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            LogUtil.a("HeartRateMeasureController", "onCharacteristicChanged characteristicId: ", uuid, ", characteristic.getValue: ", dis.d(bluetoothGattCharacteristic.getValue(), ""), ", characteristic.getProperties: ", Integer.valueOf(bluetoothGattCharacteristic.getProperties()));
            if (uuid.equalsIgnoreCase(cez.j.toString())) {
                if (dak.this.j == null) {
                    dak.this.j = new dal();
                }
                dak.this.j.e(bluetoothGattCharacteristic.getProperties());
                HealthData parseData = dak.this.j.parseData(bluetoothGattCharacteristic.getValue());
                if (!(parseData instanceof der)) {
                    LogUtil.a("HeartRateMeasureController", "onCharacteristicChanged healthData healthData instanceof HeartRate is false");
                    return;
                }
                der derVar = (der) parseData;
                if (dak.this.c != null) {
                    dak.this.c.onDataChanged(dak.this.mDevice, derVar);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (dak.this.m != 1) {
                LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController onDescriptorWrite mState is disconnected");
            }
        }
    };
    private dal j = new dal();

    static /* synthetic */ int m(dak dakVar) {
        int i = dakVar.f11570a;
        dakVar.f11570a = i + 1;
        return i;
    }

    private dak() {
        a();
    }

    public static dak d() {
        dak dakVar;
        synchronized (d) {
            if (e == null) {
                e = new dak();
            }
            dakVar = e;
        }
        return dakVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ST_(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 2) {
            LogUtil.a("HeartRateMeasureController", "doConnectionStateChange Connected to GATT server.");
            this.i = bluetoothGatt;
            this.m = 1;
            c cVar = this.o;
            if (cVar != null) {
                cVar.sendEmptyMessageDelayed(1, 1000L);
            }
            IHealthDeviceCallback iHealthDeviceCallback = this.c;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
                return;
            }
            return;
        }
        if (i2 == 0) {
            LogUtil.a("HeartRateMeasureController", "doConnectionStateChange Disconnected from GATT server.");
            this.m = 0;
            if (this.g) {
                LogUtil.a("HeartRateMeasureController", "doConnectionStateChange isFirstConnect enter");
                SS_(this.i, this.o);
                c cVar2 = this.o;
                if (cVar2 != null) {
                    cVar2.sendEmptyMessageDelayed(4, 1000L);
                }
            }
            IHealthDeviceCallback iHealthDeviceCallback2 = this.c;
            if (iHealthDeviceCallback2 != null) {
                iHealthDeviceCallback2.onStatusChanged(this.mDevice, 3);
                return;
            }
            return;
        }
        LogUtil.a("HeartRateMeasureController", "doConnectionStateChange other state ", Integer.valueOf(i2));
    }

    public void b(IHealthDeviceCallback iHealthDeviceCallback) {
        this.c = iHealthDeviceCallback;
    }

    private void a() {
        this.h.start();
        this.o = new c(this.h.getLooper());
        dkq.c().b(this.k);
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("HeartRateMeasureController", ParamConstants.CallbackMethod.ON_PREPARE);
        DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
        if (dkq.c().d()) {
            String name = dak.class.getName();
            this.c = iHealthDeviceCallback;
            this.n = UUID.randomUUID().toString();
            deviceInfoUtilsApi.putUdsClassMap(name, d());
            ddw.c().a(this.n, new cwq(name));
            ddw.c().e(this.n, new cwm(name));
            return super.prepare(healthDevice, iHealthDeviceCallback, bundle);
        }
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        b(iHealthDeviceCallback);
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        LogUtil.a("HeartRateMeasureController", "start");
        return true;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("HeartRateMeasureController", "ending");
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt != null) {
            SU_(bluetoothGatt, false);
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e2) {
                LogUtil.b("HeartRateMeasureController", "ending", e2.getMessage());
            }
        }
        super.ending();
        c cVar = this.o;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.o = null;
        }
        HandlerThread handlerThread = this.h;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        if (dkq.c().d()) {
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.b, cez.q.toString(), cez.j.toString(), false);
            b();
        }
        c();
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("HeartRateMeasureController", "cleanup");
        this.g = false;
        this.f11570a = 0;
        this.i = null;
        this.j = null;
        this.c = null;
        if (dkq.c().d()) {
            b();
        }
    }

    private static void c() {
        synchronized (d) {
            e = null;
        }
    }

    private void b() {
        UniteDevice d2 = dkq.c().d(this.b, 2);
        if (d2 == null) {
            LogUtil.h("HeartRateMeasureController", "uniteDevice is null");
        } else {
            ddw.c().b(d2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SU_(BluetoothGatt bluetoothGatt, boolean z) {
        BluetoothGattCharacteristic characteristic;
        LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController setCharNotification() enabled:", Boolean.valueOf(z));
        try {
            BluetoothGattService service = bluetoothGatt.getService(cez.q);
            if (service == null || (characteristic = service.getCharacteristic(UUID.fromString(cez.j.toString()))) == null) {
                return;
            }
            bluetoothGatt.setCharacteristicNotification(characteristic, z);
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(cez.n.toString()));
            if (descriptor == null) {
                LogUtil.h("HeartRateMeasureController", "HeartRateMeasureController setCharNotification descriptor == null");
                return;
            }
            LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController notification isEnabled:", Boolean.valueOf(z));
            if (z) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            bluetoothGatt.writeDescriptor(descriptor);
        } catch (SecurityException e2) {
            LogUtil.b("HeartRateMeasureController", "setCharNotification SecurityException:", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HeartRateMeasureController", "doDeviceConnected Connected to GATT server. by uds");
        if (deviceInfo == null) {
            LogUtil.h("HeartRateMeasureController", "deviceInfo == null");
            return;
        }
        this.b = deviceInfo.getDeviceMac();
        this.m = 1;
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
        }
        c cVar = this.o;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        if (this.m != 1) {
            LogUtil.a("HeartRateMeasureController", "HRPController onServicesDiscovered mState is disconnected");
        } else {
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.b, cez.q.toString(), cez.j.toString(), true);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        LogUtil.a("HeartRateMeasureController", "doDeviceDisconnect Disconnected from GATT server. by uds");
        this.m = 0;
        if (this.g) {
            LogUtil.a("HeartRateMeasureController", "doConnectionStateChange isFirstConnect enter");
            SS_(this.i, this.o);
            c cVar = this.o;
            if (cVar != null) {
                cVar.sendEmptyMessageDelayed(4, 1000L);
            }
        }
        IHealthDeviceCallback iHealthDeviceCallback = this.c;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 3);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.a("HeartRateMeasureController", "doDeviceConnecting connecting by uds");
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        if (biuVar == null) {
            LogUtil.h("HeartRateMeasureController", "dataFrame == null");
            return;
        }
        if (this.m != 1) {
            LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController onCharacteristicChanged mState is disconnected");
            return;
        }
        if (cez.j.toString().equalsIgnoreCase(biuVar.b())) {
            if (this.j == null) {
                this.j = new dal();
            }
            this.j.e(biuVar.e());
            HealthData parseData = this.j.parseData(biuVar.a());
            if (parseData instanceof der) {
                LogUtil.a("HeartRateMeasureController", " mBaseResponseCallback ", this.c);
                der derVar = (der) parseData;
                IHealthDeviceCallback iHealthDeviceCallback = this.c;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onDataChanged(this.mDevice, derVar);
                }
            }
        }
    }

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("HeartRateMeasureController", "HeartRateMeasureController receive msg : ", Integer.valueOf(message.what));
            try {
                int i = message.what;
                if (i != 1) {
                    if (i == 2) {
                        removeMessages(2);
                        if (dak.this.f11570a < 2) {
                            dak.m(dak.this);
                            dak dakVar = dak.this;
                            dakVar.SS_(dakVar.i, dak.this.o);
                            if (dak.this.o != null) {
                                dak.this.o.sendEmptyMessageDelayed(3, 1000L);
                            }
                        } else {
                            LogUtil.h("HeartRateMeasureController", "Times IS OUT! The mConnectTryNum = ", Integer.valueOf(dak.this.f11570a));
                        }
                    } else if (i == 3) {
                        dak.this.mDevice.connectAsync(null);
                    } else if (i == 4) {
                        dak.this.mDevice.d();
                    }
                } else if (dak.this.i != null) {
                    sendEmptyMessageDelayed(2, PreConnectManager.CONNECT_INTERNAL);
                    dak dakVar2 = dak.this;
                    dakVar2.l = dakVar2.i.discoverServices();
                    LogUtil.a("HeartRateMeasureController", "start service discovery:", Boolean.valueOf(dak.this.l));
                }
            } catch (SecurityException e) {
                LogUtil.b("HeartRateMeasureController", "handleMessage SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SS_(BluetoothGatt bluetoothGatt, c cVar) {
        if (bluetoothGatt != null) {
            LogUtil.a("HeartRateMeasureController", "start to close gatt...");
            try {
                bluetoothGatt.close();
            } catch (SecurityException e2) {
                LogUtil.b("HeartRateMeasureController", "closeBluetoothGattAndRemoveMsg SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        if (dkq.c().d()) {
            b();
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.f;
    }
}
