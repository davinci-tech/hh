package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.UUID;

/* loaded from: classes3.dex */
public class czv extends GattMeasureController implements ConnectStatusCallback, DataChangedCallback {
    private static czv d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private IHealthDeviceCallback f11563a;
    private BluetoothGatt b;
    private BluetoothGattCharacteristic c;
    private BluetoothGattCharacteristic f;
    private BluetoothGattCharacteristic g;
    private volatile boolean j;
    private BluetoothGattCharacteristic m;
    private String n;
    private String o;
    private HealthDevice.HealthDeviceKind h = HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE;
    private BluetoothGattCallback i = new BluetoothGattCallback() { // from class: czv.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            czv.this.Sw_(bluetoothGatt, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            czv.this.Sx_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            LogUtil.a("BloodPressureMeasureController", "onCharacteristicChanged mBaseResponseCallback:", czv.this.f11563a, ",parser:", czv.this.l, ",data = ", dis.d(bluetoothGattCharacteristic.getValue(), ""));
            if (cez.e.equals(uuid)) {
                if (czv.this.l == null) {
                    czv.this.l = new czt();
                }
                HealthData parseData = czv.this.l.parseData(bluetoothGattCharacteristic.getValue());
                if (!(parseData instanceof deo) || czv.this.f11563a == null) {
                    return;
                }
                czv.this.f11563a.onDataChanged(czv.this.mDevice, parseData);
                return;
            }
            if (cez.p.equals(uuid)) {
                dks.WA_(bluetoothGatt, czv.this.g, czv.this.f);
            } else {
                if (cez.ah.equals(uuid)) {
                    LogUtil.a("BloodPressureMeasureController", "Click Start on the device.");
                    czv.this.a();
                    czv.this.d(bluetoothGattCharacteristic.getValue());
                    return;
                }
                LogUtil.a("BloodPressureMeasureController", "onCharacteristicChanged other uuid ");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LogUtil.a("BloodPressureMeasureController", "Enter onDescriptorWrite status:", Integer.valueOf(i));
            if (i != 0) {
                return;
            }
            if (bluetoothGattDescriptor == null || bluetoothGattDescriptor.getCharacteristic() == null) {
                LogUtil.h("BloodPressureMeasureController", "descriptor == null || descriptor.getCharacteristic == null");
            } else if (cez.e.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                if (czv.this.f == null && czv.this.g == null) {
                    return;
                }
                dks.WA_(bluetoothGatt, czv.this.g, czv.this.f);
            }
        }
    };
    private czt l = new czt();

    public static czv c() {
        czv czvVar;
        synchronized (e) {
            if (d == null) {
                d = new czv();
            }
            czvVar = d;
        }
        return czvVar;
    }

    public czv() {
        dkq.c().b(this.h.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.l == null) {
            this.l = new czt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte[] bArr) {
        IHealthDeviceCallback iHealthDeviceCallback;
        czt cztVar = this.l;
        if (cztVar == null) {
            LogUtil.a("BloodPressureMeasureController", "bloodPressureDataParser is null");
            return;
        }
        int b = cztVar.b(bArr);
        LogUtil.a("BloodPressureMeasureController", "Click Start on the device.controlType is ", Integer.valueOf(b));
        if ((b == 0 || b == 1) && (iHealthDeviceCallback = this.f11563a) != null) {
            iHealthDeviceCallback.onMeasureChanged(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Sx_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.c("BloodPressureMeasureController", "Enter doServiceDiscover, status:", Integer.valueOf(i));
        if (i == 0) {
            try {
                BluetoothGattService service = bluetoothGatt.getService(cez.aa);
                if (service == null) {
                    return;
                }
                b();
                this.c = service.getCharacteristic(cez.e);
                this.g = service.getCharacteristic(cez.s);
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(cez.ah);
                this.m = characteristic;
                if (characteristic != null) {
                    LogUtil.a("BloodPressureMeasureController", " doServiceDiscover setCharacteristicNotification isEnable:", Boolean.valueOf(bluetoothGatt.setCharacteristicNotification(characteristic, true)));
                }
                if (this.c != null) {
                    Thread.sleep(500L);
                    LogUtil.a("BloodPressureMeasureController", "doServiceDiscover enableIndication isEnable:", Boolean.valueOf(czu.e().Sr_(bluetoothGatt, this.c)));
                }
            } catch (InterruptedException | SecurityException e2) {
                LogUtil.b("BloodPressureMeasureController", "doServiceDiscover Exception:", ExceptionUtils.d(e2));
                return;
            }
        }
        Thread.sleep(600L);
        BluetoothGattService service2 = bluetoothGatt.getService(cez.r);
        if (service2 != null) {
            BluetoothGattCharacteristic characteristic2 = service2.getCharacteristic(cez.p);
            this.f = characteristic2;
            if (characteristic2 == null) {
                return;
            }
            bluetoothGatt.setCharacteristicNotification(characteristic2, true);
            BluetoothGattDescriptor descriptor = this.f.getDescriptor(cez.n);
            if (descriptor != null) {
                LogUtil.a("BloodPressureMeasureController", "doServiceDiscover ENABLE_NOTIFICATION_VALUE");
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                bluetoothGatt.writeDescriptor(descriptor);
            }
        }
    }

    private void b() {
        if (BloodPressureStartFromDeviceHelper.c()) {
            LogUtil.a("BloodPressureMeasureController", "doServiceDiscover NewMeasurementProcedure");
            IHealthDeviceCallback iHealthDeviceCallback = this.f11563a;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Sw_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.c("BloodPressureMeasureController", "Enter doConnectionStateChange, newState:", Integer.valueOf(i));
        try {
            if (i != 2) {
                if (i != 0) {
                    LogUtil.h("BloodPressureMeasureController", "doConnectionStateChange other State = ", Integer.valueOf(i));
                    return;
                }
                if (this.f11563a != null && BloodPressureStartFromDeviceHelper.c()) {
                    LogUtil.h("BloodPressureMeasureController", "doConnectionStateChange disconnected");
                    this.f11563a.onStatusChanged(this.mDevice, 3);
                }
                cleanup();
                return;
            }
            this.b = bluetoothGatt;
            bluetoothGatt.discoverServices();
            if (!BloodPressureStartFromDeviceHelper.c()) {
                LogUtil.a("BloodPressureMeasureController", "OldMeasurementProcedure BluetoothProfile.STATE_CONNECTED");
                IHealthDeviceCallback iHealthDeviceCallback = this.f11563a;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
                    return;
                }
                return;
            }
            LogUtil.a("BloodPressureMeasureController", "NewMeasurementProcedure BluetoothProfile.STATE_CONNECTED");
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureMeasureController", "doConnectionStateChange SecurityException:", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.i;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("BloodPressureMeasureController", "Enter prepare");
        if (dkq.c().d()) {
            if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
                return false;
            }
            String name = czv.class.getName();
            this.f11563a = iHealthDeviceCallback;
            this.o = UUID.randomUUID().toString();
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).putUdsClassMap(name, c());
            ddw.c().a(this.o, new cwq(name));
            ddw.c().e(this.o, new cwm(name));
            LogUtil.a("BloodPressureMeasureController", "BloodPressureMeasureController prepare start, isConnect:", Boolean.valueOf(this.j));
            return true;
        }
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        this.f11563a = iHealthDeviceCallback;
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        LogUtil.a("BloodPressureMeasureController", "Enter start");
        return true;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("BloodPressureMeasureController", "Enter ending");
        try {
            if (this.b != null) {
                LogUtil.a("BloodPressureMeasureController", "ending disconnect");
                this.b.disconnect();
            }
            if (dkq.c().d()) {
                ddw.c().a();
            }
            super.ending();
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureMeasureController", "ending SecurityException:", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("BloodPressureMeasureController", "Enter cleanup");
        try {
            if (this.b != null) {
                LogUtil.a("BloodPressureMeasureController", "cleanup close");
                this.b.close();
            }
            this.c = null;
            this.g = null;
            this.f11563a = null;
            this.l = null;
            if (dkq.c().d()) {
                ddw.c().a();
                ddw.c().a(this.o);
                ddw.c().d(this.o);
                ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).clearUdsClassMap();
            }
            e();
            this.o = null;
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureMeasureController", "cleanup SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private static void e() {
        synchronized (e) {
            d = null;
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        IHealthDeviceCallback iHealthDeviceCallback = this.f11563a;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
        }
        this.n = deviceInfo.getDeviceMac();
        this.j = true;
        DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
        deviceInfoUtilsApi.setCharacteristicNotifyByUds(this.n, cez.aa.toString(), cez.e.toString(), true);
        try {
            Thread.sleep(600L);
        } catch (InterruptedException unused) {
            LogUtil.b("BloodPressureMeasureController", "doDeviceConnected sleep error");
        }
        deviceInfoUtilsApi.setCharacteristicNotifyByUds(this.n, cez.r.toString(), cez.p.toString(), true);
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        if (this.f11563a != null && BloodPressureStartFromDeviceHelper.c()) {
            LogUtil.h("BloodPressureMeasureController", "doConnectionStateChange disconnected");
            this.f11563a.onStatusChanged(this.mDevice, 3);
        }
        cleanup();
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.a("BloodPressureMeasureController", "device is connecting");
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        IHealthDeviceCallback iHealthDeviceCallback;
        LogUtil.a("BloodPressureMeasureController", "onCharacterChanged enter");
        if (biuVar == null) {
            return;
        }
        String b = biuVar.b();
        if (cez.e.toString().equals(b)) {
            if (this.l == null) {
                this.l = new czt();
            }
            HealthData parseData = this.l.parseData(biuVar.a());
            if ((parseData instanceof deo) && (iHealthDeviceCallback = this.f11563a) != null) {
                iHealthDeviceCallback.onDataChanged(this.mDevice, parseData);
            }
            dkq.c().e(cez.aa.toString(), cez.e.toString(), this.n);
            return;
        }
        if (cez.p.toString().equals(b)) {
            dkq.c().e(cez.r.toString(), cez.s.toString(), this.n);
        } else {
            LogUtil.a("BloodPressureMeasureController", "onCharacterChanged other uuid ");
        }
    }
}
