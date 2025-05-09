package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;
import com.huawei.health.device.kit.wsp.task.ITaskService;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes3.dex */
public class czz extends GattMeasureController implements ITaskService, ConnectStatusCallback, DataChangedCallback {
    private static czz b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private IHealthDeviceCallback f11564a;
    private BluetoothGattCharacteristic c;
    private cke d;
    private BluetoothGattCharacteristic f;
    private String g;
    private Map<String, HealthData> h;
    private dac i;
    private boolean l;
    private BluetoothGatt n;
    private String p;
    private int q;
    private int r;
    private int s;
    private IAsynBleTaskCallback u;
    private BleTaskQueueUtil v;
    private int w;
    private BluetoothGattService x;
    private BluetoothGattCharacteristic y;
    private HealthDevice.HealthDeviceKind t = HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR;
    private boolean o = true;
    private boolean m = true;
    private char[] k = "0123456789ABCDEF".toCharArray();
    private BluetoothGattCallback j = new BluetoothGattCallback() { // from class: czz.2
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            czz.this.SF_(bluetoothGatt, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            czz.this.SJ_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            czz.this.SE_(bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            LogUtil.a("GlucoseMeasureController", "onCharacteristicChanged()");
            czz.this.SD_(bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            czz.this.SH_(bluetoothGatt, bluetoothGattDescriptor, i);
        }
    };

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void disable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        return true;
    }

    czz() {
        LogUtil.a("GlucoseMeasureController", "constructed");
        this.i = new dac();
        this.d = new cke("bloodSugar");
        this.h = new HashMap(16);
        this.v = new BleTaskQueueUtil(this);
        dkq.c().b(this.t.name());
    }

    public static czz c() {
        czz czzVar;
        synchronized (e) {
            if (b == null) {
                b = new czz();
            }
            czzVar = b;
        }
        return czzVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SH_(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (this.w != 1) {
            LogUtil.h("GlucoseMeasureController", "doDescriptorWrite mState is disconnected");
            return;
        }
        if (bluetoothGattDescriptor == null) {
            LogUtil.h("GlucoseMeasureController", "doDescriptorWrite bluetoothGattDescriptor is null");
            return;
        }
        String uuid = bluetoothGattDescriptor.getCharacteristic().getUuid().toString();
        if (i == 0) {
            SI_(uuid, bluetoothGatt);
        }
    }

    private void SI_(String str, BluetoothGatt bluetoothGatt) {
        LogUtil.a("GlucoseMeasureController", "doGattSuccess charUuid:", str);
        if (cez.g.toString().equalsIgnoreCase(str)) {
            this.u.success(null);
            SK_(this.n, this.f, true);
            return;
        }
        if (cez.m.toString().equalsIgnoreCase(str)) {
            try {
                BluetoothGattService service = bluetoothGatt.getService(cez.r);
                if (service != null) {
                    SG_(service, bluetoothGatt);
                } else {
                    this.f.setValue(new byte[2]);
                    this.f.setValue(1, 17, 0);
                    this.f.setValue(1, 17, 1);
                    this.n.writeCharacteristic(this.f);
                    LogUtil.a("GlucoseMeasureController", "doGattSuccess NO_SERVICE_CURRENT_TIME");
                }
                return;
            } catch (SecurityException e2) {
                LogUtil.b("GlucoseMeasureController", "doGattSuccess SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        if (cez.p.toString().equalsIgnoreCase(str)) {
            e();
        } else {
            LogUtil.a("GlucoseMeasureController", "doGattSuccess other charUuid:", str);
        }
    }

    private void SG_(BluetoothGattService bluetoothGattService, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(cez.p);
        this.c = characteristic;
        if (characteristic != null) {
            try {
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                BluetoothGattDescriptor descriptor = this.c.getDescriptor(cez.n);
                if (descriptor != null) {
                    LogUtil.a("GlucoseMeasureController", "doCurrentTimeService GlucoseMeasureController ENABLE_TIME_NOTIFICATION_VALUE");
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.writeDescriptor(descriptor);
                }
            } catch (SecurityException e2) {
                LogUtil.b("GlucoseMeasureController", "doCurrentTimeService SecurityException:", ExceptionUtils.d(e2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SE_(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        try {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            LogUtil.a("GlucoseMeasureController", " doCharacteristicWrite statusInt:", Integer.valueOf(i), " uuid:", uuid);
            if (this.u == null) {
                LogUtil.h("GlucoseMeasureController", " doCharacteristicWrite mTaskCallback is null");
                return;
            }
            if (i == 0 && cez.p.toString().equalsIgnoreCase(uuid)) {
                this.f.setValue(new byte[2]);
                this.f.setValue(1, 17, 0);
                this.f.setValue(1, 17, 1);
                this.n.writeCharacteristic(this.f);
            }
        } catch (SecurityException e2) {
            LogUtil.b("GlucoseMeasureController", "doCharacteristicWrite SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SJ_(BluetoothGatt bluetoothGatt, int i) {
        if (this.w != 1) {
            LogUtil.a("GlucoseMeasureController", "doServiceDiscover mState is disconnected");
            return;
        }
        LogUtil.a("GlucoseMeasureController", "doServiceDiscover status = ", Integer.valueOf(i));
        if (i == 0) {
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(cez.z.toString()));
            this.x = service;
            if (service == null) {
                LogUtil.h("GlucoseMeasureController", "doServiceDiscover failed to get service");
                Bundle bundle = new Bundle();
                bundle.putString(BleConstants.MEASUREKIT_ID, this.mDevice.getMeasureKitUuid());
                LogUtil.bRh_(907127003, "GlucoseMeasureController", bundle, false, "doServiceDiscover No GATT service found");
                return;
            }
            this.v.b(new cjq(BleTaskQueueUtil.TaskType.ENABLE_GLUCOSE_MEASUREMENT, null));
            this.v.e();
            return;
        }
        LogUtil.h("GlucoseMeasureController", "doServiceDiscover status: ", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SF_(BluetoothGatt bluetoothGatt, int i) {
        Map<String, HealthData> map;
        if (i == 2) {
            this.l = false;
            LogUtil.a("GlucoseMeasureController", "doConnectionStateChange Connected to GATT server.");
            int c = this.d.c(cpp.a(), this.g);
            this.r = c;
            LogUtil.c("GlucoseMeasureController", "doConnectionStateChange newestSequenceNumberInDB is ", Integer.valueOf(c));
            this.h.clear();
            this.n = bluetoothGatt;
            this.w = 1;
            try {
                bluetoothGatt.discoverServices();
                IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
                    return;
                }
                return;
            } catch (SecurityException e2) {
                LogUtil.b("GlucoseMeasureController", "doConnectionStateChange SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        if (i == 0) {
            LogUtil.a("GlucoseMeasureController", "doConnectionStateChange Disconnected from GATT server.");
            if (!this.l && (map = this.h) != null && !map.isEmpty()) {
                LogUtil.c("GlucoseMeasureController", "doConnectionStateChange data has been transferred ", Integer.valueOf(this.h.size()));
                IHealthDeviceCallback iHealthDeviceCallback2 = this.f11564a;
                if (iHealthDeviceCallback2 != null) {
                    iHealthDeviceCallback2.onDataChanged(this.mDevice, b());
                }
                int i2 = this.s;
                if (i2 != 0) {
                    LogUtil.a("GlucoseMeasureController", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(i2));
                    this.d.e(cpp.a(), this.g, this.s);
                }
            }
            this.w = 0;
            cleanup();
            IHealthDeviceCallback iHealthDeviceCallback3 = this.f11564a;
            if (iHealthDeviceCallback3 != null) {
                iHealthDeviceCallback3.onStatusChanged(this.mDevice, 3);
                return;
            }
            return;
        }
        LogUtil.a("GlucoseMeasureController", "doConnectionStateChange other state:", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SD_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.w != 1) {
            LogUtil.h("GlucoseMeasureController", "doCharacteristicChange mState is disconnected");
            return;
        }
        String uuid = bluetoothGattCharacteristic.getUuid().toString();
        String a2 = a(bluetoothGattCharacteristic.getValue());
        if (cez.g.toString().equalsIgnoreCase(uuid)) {
            LogUtil.c("GlucoseMeasureController", "doCharacteristicChange CHARACTERISTIC_GLUCOSE_MEASUREMENT");
            dac dacVar = this.i;
            if (dacVar == null) {
                return;
            }
            HealthData parseData = dacVar.parseData(bluetoothGattCharacteristic.getValue());
            if (parseData instanceof deb) {
                deb debVar = (deb) parseData;
                int sequenceNumber = debVar.getSequenceNumber();
                LogUtil.a("GlucoseMeasureController", "doCharacteristicChange current sequenceNumber is ", Integer.valueOf(sequenceNumber));
                int i = this.r;
                if (i == 0 || sequenceNumber > i) {
                    e(sequenceNumber, debVar);
                }
                this.q = sequenceNumber;
                return;
            }
            return;
        }
        if (cez.m.toString().equalsIgnoreCase(uuid)) {
            LogUtil.a("GlucoseMeasureController", "doCharacteristicChange CHARACTERISTIC_RECORD_ACCESS_CONTROL_POINT");
            if (a2.endsWith("06000101")) {
                this.l = true;
                LogUtil.a("GlucoseMeasureController", "doCharacteristicChange data has been completely transferred ", Integer.valueOf(this.h.size()));
                IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onDataChanged(this.mDevice, b());
                }
                int i2 = this.s;
                if (i2 != 0) {
                    LogUtil.a("GlucoseMeasureController", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(i2));
                    this.d.e(cpp.a(), this.g, this.s);
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.a("GlucoseMeasureController", "doCharacteristicChange other characteristicId", uuid);
    }

    private void e(int i, deb debVar) {
        if (this.l) {
            LogUtil.a("GlucoseMeasureController", "doParser current sequenceNumber is ", Integer.valueOf(i), ",本次测量");
            this.h.clear();
            this.s = i;
            boolean a2 = a(debVar, i);
            int i2 = this.s;
            if (i2 != 0) {
                LogUtil.a("GlucoseMeasureController", "doParser ", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(i2));
                this.d.e(cpp.a(), this.g, this.s);
            }
            IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
            if (iHealthDeviceCallback == null || !a2) {
                return;
            }
            iHealthDeviceCallback.onDataChanged(this.mDevice, b());
            return;
        }
        int i3 = this.q;
        if (i3 != 0 && i != i3 + 1) {
            LogUtil.a("GlucoseMeasureController", "doParser ", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(this.s), ",lastNumber:", Integer.valueOf(this.q), ",sequenceNumber:", Integer.valueOf(i));
            boolean a3 = a(debVar, i);
            if (this.s != 0) {
                this.d.e(cpp.a(), this.g, this.s);
            }
            IHealthDeviceCallback iHealthDeviceCallback2 = this.f11564a;
            if (iHealthDeviceCallback2 == null || !a3) {
                return;
            }
            iHealthDeviceCallback2.onDataChanged(this.mDevice, b());
            return;
        }
        this.s = i;
        a(debVar, i);
    }

    private boolean a(deb debVar, int i) {
        if (!d(debVar)) {
            return false;
        }
        if (this.h == null) {
            this.h = new HashMap(16);
        }
        String valueOf = String.valueOf(debVar.getStartTime());
        if (this.h.containsKey(valueOf)) {
            if (!(this.h.get(valueOf) instanceof deb)) {
                LogUtil.b("GlucoseMeasureController", "Object invalid type");
                return false;
            }
            if (i > ((deb) this.h.get(valueOf)).getSequenceNumber()) {
                this.h.put(valueOf, debVar);
                return true;
            }
            LogUtil.h("GlucoseMeasureController", "Blood sugar data timestamp exists");
            return false;
        }
        this.h.put(valueOf, debVar);
        return true;
    }

    private ArrayList<HealthData> b() {
        return new ArrayList<>(this.h.values());
    }

    private boolean d(deb debVar) {
        if (debVar.getBloodSugar() - 33.0f > 1.0E-6d || 1.0f - debVar.getBloodSugar() > 1.0E-6d) {
            LogUtil.b("GlucoseMeasureController", "Blood sugar value invalid");
            return false;
        }
        if (debVar.getStartTime() <= System.currentTimeMillis()) {
            return true;
        }
        LogUtil.b("GlucoseMeasureController", "Blood sugar time invalid");
        return false;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.j;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.c("GlucoseMeasureController", ParamConstants.CallbackMethod.ON_PREPARE);
        if (healthDevice == null) {
            return false;
        }
        this.g = healthDevice.getAddress();
        if (dkq.c().d()) {
            if (super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
                String name = czz.class.getName();
                this.f11564a = iHealthDeviceCallback;
                this.p = UUID.randomUUID().toString();
                ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).putUdsClassMap(name, c());
                ddw.c().a(this.p, new cwq(name));
                ddw.c().e(this.p, new cwm(name, this.v));
                LogUtil.a("GlucoseMeasureController", "GlucoseMeasureController prepare start");
                return true;
            }
        } else if (super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            this.f11564a = iHealthDeviceCallback;
            return true;
        }
        return false;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("GlucoseMeasureController", "ending");
        BluetoothGatt bluetoothGatt = this.n;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.disconnect();
            } catch (SecurityException e2) {
                LogUtil.b("GlucoseMeasureController", "ending SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        this.o = true;
        this.m = true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("GlucoseMeasureController", "cleanup");
        BluetoothGatt bluetoothGatt = this.n;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.close();
            } catch (SecurityException e2) {
                LogUtil.b("GlucoseMeasureController", "cleanup SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        this.n = null;
        this.f = null;
        this.x = null;
        this.y = null;
        this.i = null;
        this.f11564a = null;
        this.w = 0;
        if (dkq.c().d()) {
            ddw.c().a();
            ddw.c().a(this.p);
            ddw.c().d(this.p);
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).clearUdsClassMap();
        }
        d();
        this.p = null;
    }

    private static void d() {
        synchronized (e) {
            b = null;
        }
    }

    private void SL_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        LogUtil.a("GlucoseMeasureController", "setCharNotification() enabled:", Boolean.valueOf(z));
        try {
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(cez.n);
            if (descriptor == null) {
                LogUtil.h("GlucoseMeasureController", "setCharNotification descriptor == null");
                return;
            }
            if (z) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            bluetoothGatt.writeDescriptor(descriptor);
        } catch (SecurityException e2) {
            LogUtil.b("GlucoseMeasureController", "setCharNotification SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private void SK_(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        LogUtil.a("GlucoseMeasureController", "setCharIndication() enabled:", Boolean.valueOf(z));
        try {
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(cez.n);
            if (descriptor == null) {
                LogUtil.h("GlucoseMeasureController", "setCharIndication descriptor == null");
                return;
            }
            if (z) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            } else {
                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            this.n.writeDescriptor(descriptor);
        } catch (SecurityException e2) {
            LogUtil.b("GlucoseMeasureController", "setCharIndication SecurityException:", ExceptionUtils.d(e2));
        }
    }

    private String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = this.k;
            cArr[i2] = cArr2[(b2 & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b2 & BaseType.Obj];
        }
        return new String(cArr);
    }

    public void e() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        boolean z = calendar.getFirstDayOfWeek() == 1;
        int i7 = calendar.get(7);
        LogUtil.c("GlucoseMeasureController", "GlucoseMeasureController syncCurrentTime data：", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        if (z && i7 - 1 == 0) {
            i7 = 7;
        }
        LogUtil.c("GlucoseMeasureController", "GlucoseMeasureController syncCurrentTime time：", Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
        this.v.b(new cjq(BleTaskQueueUtil.TaskType.SET_TIME, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) (i3 & 255), (byte) (i4 & 255), (byte) (i5 & 255), (byte) (i6 & 255), (byte) (i7 & 255), 0, 0}));
        this.v.e();
    }

    private boolean SM_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (this.n == null || bluetoothGattCharacteristic == null) {
            return false;
        }
        try {
            bluetoothGattCharacteristic.setValue(bArr);
            bluetoothGattCharacteristic.setWriteType(1);
            boolean writeCharacteristic = this.n.writeCharacteristic(bluetoothGattCharacteristic);
            LogUtil.a("GlucoseMeasureController", "writeCharacteristic --> ", e(bArr));
            return writeCharacteristic;
        } catch (SecurityException e2) {
            LogUtil.b("GlucoseMeasureController", "writeCharacteristic SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    private String e(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            LogUtil.h("GlucoseMeasureController", "GlucoseMeasureController bytesToHexString content is null ");
            return null;
        }
        LogUtil.c("GlucoseMeasureController", "GlucoseMeasureController bytesToHexString start to Hex String");
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + " ");
        }
        return sb.toString();
    }

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void enable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
        LogUtil.a("GlucoseMeasureController", " enter enable");
        if (dkq.c().d()) {
            this.u = iAsynBleTaskCallback;
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.g, cez.z.toString(), cez.g.toString(), true);
            return;
        }
        this.y = this.x.getCharacteristic(cez.g);
        BluetoothGattCharacteristic characteristic = this.x.getCharacteristic(cez.m);
        this.f = characteristic;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.y;
        if (bluetoothGattCharacteristic != null && characteristic != null) {
            this.u = iAsynBleTaskCallback;
            SL_(this.n, bluetoothGattCharacteristic, true);
        } else {
            LogUtil.a("GlucoseMeasureController", "enable mTargetMeasurementChar and mGattChar is null");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0061  */
    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(defpackage.cjq r5, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r6) {
        /*
            r4 = this;
            java.lang.String r0 = " enter write"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "GlucoseMeasureController"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            dkq r0 = defpackage.dkq.c()
            boolean r0 = r0.d()
            if (r0 == 0) goto L1c
            r4.u = r6
            boolean r5 = r4.b(r5)
            goto L50
        L1c:
            android.bluetooth.BluetoothGatt r0 = r4.n
            java.util.UUID r2 = defpackage.cez.r
            android.bluetooth.BluetoothGattService r0 = r0.getService(r2)
            if (r0 == 0) goto L53
            java.util.UUID r2 = defpackage.cez.p
            android.bluetooth.BluetoothGattCharacteristic r0 = r0.getCharacteristic(r2)
            if (r0 == 0) goto L5d
            r4.u = r6
            byte[] r6 = r5.c()
            boolean r6 = r4.SM_(r0, r6)
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil$TaskType r5 = r5.f()
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = " isSuccess:"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r6)
            java.lang.String r3 = "Write key:"
            java.lang.Object[] r5 = new java.lang.Object[]{r3, r5, r0, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
            r5 = r6
        L50:
            if (r5 != 0) goto L64
            goto L5d
        L53:
            java.lang.String r5 = "write BluetoothGattService is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
        L5d:
            com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback r5 = r4.u
            if (r5 == 0) goto L64
            r5.failed()
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.czz.write(cjq, com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback):void");
    }

    private boolean b(cjq cjqVar) {
        if (cjqVar == null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.g)) {
            biu biuVar = new biu();
            biuVar.d(cjqVar.c());
            a(biuVar, cez.r.toString(), cez.p.toString(), this.g, CharacterOperationType.WRITE);
            return true;
        }
        LogUtil.h("GlucoseMeasureController", "writeDataUds : macAddress is null");
        return false;
    }

    private void a(biu biuVar, String str, String str2, String str3, CharacterOperationType characterOperationType) {
        if (biuVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h("GlucoseMeasureController", "data is null || serviceUuid == null || characterUuid == null || mac == null");
            return;
        }
        synchronized (e) {
            UniteDevice d = dkq.c().d(str3, 2);
            if (d != null) {
                ddw.c().e(d, ddw.c().c(biuVar, str, str2, characterOperationType));
                LogUtil.a("GlucoseMeasureController", "setDateTime : data frames:", cvx.d(biuVar.a()));
            } else {
                LogUtil.h("GlucoseMeasureController", "uniteDevice is null");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        this.l = false;
        LogUtil.a("GlucoseMeasureController", "doDeviceConnected Connected.");
        int c = this.d.c(cpp.a(), this.g);
        this.r = c;
        LogUtil.c("GlucoseMeasureController", "doConnectionStateChange newestSequenceNumberInDB is ", Integer.valueOf(c));
        this.h.clear();
        this.w = 1;
        this.v.b(new cjq(BleTaskQueueUtil.TaskType.ENABLE_GLUCOSE_MEASUREMENT, null));
        this.v.e();
        IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
        if (iHealthDeviceCallback != null) {
            iHealthDeviceCallback.onStatusChanged(this.mDevice, 2);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        Map<String, HealthData> map;
        LogUtil.a("GlucoseMeasureController", "doDeviceDisconnect enter");
        if (!this.l && (map = this.h) != null && !map.isEmpty()) {
            LogUtil.c("GlucoseMeasureController", "doConnectionStateChange data has been transferred ", Integer.valueOf(this.h.size()));
            IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onDataChanged(this.mDevice, b());
            }
            int i2 = this.s;
            if (i2 != 0) {
                LogUtil.a("GlucoseMeasureController", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(i2));
                this.d.e(cpp.a(), this.g, this.s);
            }
        }
        this.w = 0;
        cleanup();
        IHealthDeviceCallback iHealthDeviceCallback2 = this.f11564a;
        if (iHealthDeviceCallback2 != null) {
            iHealthDeviceCallback2.onStatusChanged(this.mDevice, 3);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.a("GlucoseMeasureController", "doDeviceConnecting enter");
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        LogUtil.a("GlucoseMeasureController", "onDataChanged enter");
        if (biuVar == null) {
            LogUtil.h("GlucoseMeasureController", "onDataChanged dataFrame is null");
            return;
        }
        if (biuVar.a() != null && biuVar.a().length > 0 && biuVar.a()[0] == 0) {
            LogUtil.a("GlucoseMeasureController", "frames[0] is 0");
            c(biuVar);
        }
        String b2 = biuVar.b();
        if (cez.g.toString().equalsIgnoreCase(b2)) {
            a(biuVar);
            return;
        }
        if (cez.m.toString().equalsIgnoreCase(b2)) {
            e(biuVar);
        } else if (cez.p.toString().equals(b2)) {
            e();
            c(biuVar);
        } else {
            LogUtil.h("GlucoseMeasureController", "onDataChanged other uuid");
        }
    }

    private void c(biu biuVar) {
        biuVar.d(new byte[]{1, 1});
        a(biuVar, cez.z.toString(), cez.m.toString(), this.g, CharacterOperationType.WRITE);
    }

    private void a(biu biuVar) {
        LogUtil.a("GlucoseMeasureController", "measurementCallback mIsFirstMeasurement is ", Boolean.valueOf(this.o));
        if (this.o) {
            this.u.success(null);
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.g, cez.z.toString(), cez.m.toString(), true);
            this.o = false;
            return;
        }
        dac dacVar = this.i;
        if (dacVar == null) {
            return;
        }
        HealthData parseData = dacVar.parseData(biuVar.a());
        if (parseData instanceof deb) {
            deb debVar = (deb) parseData;
            int sequenceNumber = debVar.getSequenceNumber();
            LogUtil.a("GlucoseMeasureController", "onDataChanged current sequenceNumber is ", Integer.valueOf(sequenceNumber));
            int i = this.r;
            if (i == 0 || sequenceNumber > i) {
                e(sequenceNumber, debVar);
            }
            this.q = sequenceNumber;
        }
    }

    private void e(biu biuVar) {
        if (this.m) {
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.g, cez.r.toString(), cez.p.toString(), true);
            this.m = false;
            return;
        }
        if (a(biuVar.a()).endsWith("06000101")) {
            this.l = true;
            IHealthDeviceCallback iHealthDeviceCallback = this.f11564a;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onDataChanged(this.mDevice, b());
            }
            int i = this.s;
            if (i != 0) {
                LogUtil.a("GlucoseMeasureController", "GlucoseMeasureController refreshedSequenceNumber is ", Integer.valueOf(i));
                this.d.e(cpp.a(), this.g, this.s);
            }
        }
    }
}
