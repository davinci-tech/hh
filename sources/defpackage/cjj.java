package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import android.os.Handler;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.picooc.health.formula.Formula;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class cjj extends GattMeasureController {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothGatt f744a;
    private IHealthDeviceCallback b;
    private HealthDevice e;
    private BluetoothGattCharacteristic f;
    private List<HealthData> i;
    private BluetoothGattService k;
    private BluetoothGattCharacteristic m;
    private int n;
    private float j = 173.0f;
    private int c = 29;
    private int l = 1;
    private boolean h = true;
    private boolean g = true;
    private BluetoothGattCallback d = new BluetoothGattCallback() { // from class: cjj.2
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            cjj.this.FC_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            ReleaseLogUtil.e("R_PluginDevice_PicoocMeasureController", "onDescriptorWrite status = ", Integer.valueOf(i));
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (cjj.this.n != 2) {
                LogUtil.h("PluginDevice_PicoocMeasureController", "onDescriptorWrite state is not connected");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (cjj.this.n == 2) {
                cjj.this.FB_(bluetoothGattCharacteristic);
            } else {
                LogUtil.h("PluginDevice_PicoocMeasureController", "onDescriptorWrite state is not connected");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            LogUtil.a("PluginDevice_PicoocMeasureController", "Enter onServicesDiscovered, status = ", Integer.valueOf(i));
            super.onServicesDiscovered(bluetoothGatt, i);
            if (i == 0) {
                LogUtil.a("PluginDevice_PicoocMeasureController", "onServicesDiscovered GATT_SUCCESS");
                cjj.this.FD_(bluetoothGatt);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(BleConstants.MEASUREKIT_ID, cjj.this.mDevice.getMeasureKitUuid());
            LogUtil.bRh_(907127003, "PluginDevice_PicoocMeasureController", bundle, false, "No GATT service found.");
            if (cjj.this.b != null) {
                cjj.this.b.onStatusChanged(cjj.this.e, 8);
            }
        }
    };

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FB_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic2;
        try {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            if ("0000fff2-0000-1000-8000-00805f9b34fb".equalsIgnoreCase(uuid)) {
                LogUtil.c("PluginDevice_PicoocMeasureController", "doCharacteristicChange write data:", Arrays.toString(bluetoothGattCharacteristic.getValue()));
                return;
            }
            if ("0000fff1-0000-1000-8000-00805f9b34fb".equalsIgnoreCase(uuid)) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value == null || value.length <= 0) {
                    return;
                }
                byte[] a2 = Formula.a(value);
                if (a2.length > 0 && (bluetoothGattCharacteristic2 = this.m) != null && this.f744a != null) {
                    bluetoothGattCharacteristic2.setValue(a2);
                    this.f744a.writeCharacteristic(this.m);
                }
                c(value);
                return;
            }
            LogUtil.h("PluginDevice_PicoocMeasureController", "doCharacteristicChange other characteristicId = ", uuid);
        } catch (SecurityException e) {
            LogUtil.b("PluginDevice_PicoocMeasureController", "doCharacteristicChange SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void c(byte[] bArr) {
        txy a2;
        int i = this.l;
        if (i == 1 || i == 0) {
            a2 = Formula.a(bArr, this.j, i, this.c);
        } else {
            a2 = Formula.a(bArr, this.j, 1, this.c);
        }
        int c = a2.c();
        if (c == 1) {
            if (this.h) {
                this.h = false;
                IHealthDeviceCallback iHealthDeviceCallback = this.b;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onDataChanged(this.e, c(a2));
                }
                LogUtil.c("PluginDevice_PicoocMeasureController", "parserData 发送本次测量数据");
                return;
            }
            return;
        }
        if (c == 2) {
            d(a2);
            LogUtil.c("PluginDevice_PicoocMeasureController", "parserData 收到一条历史数据");
        } else if (c == 3 && !koq.b(this.i) && this.g) {
            this.g = false;
            IHealthDeviceCallback iHealthDeviceCallback2 = this.b;
            if (iHealthDeviceCallback2 != null) {
                iHealthDeviceCallback2.onDataChanged(this.e, this.i);
            }
            LogUtil.c("PluginDevice_PicoocMeasureController", "parserData 批量发送历史数据", Integer.valueOf(this.i.size()), "条");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void FC_(BluetoothGatt bluetoothGatt, int i, int i2) {
        ReleaseLogUtil.e("R_PluginDevice_PicoocMeasureController", "doConnectionStateChange：", Integer.valueOf(i), " newState = ", Integer.valueOf(i2));
        if (i != 0) {
            this.n = 0;
            LogUtil.h("PluginDevice_PicoocMeasureController", "doConnectionStateChange STATUS_DISCONNECTED 2");
            IHealthDeviceCallback iHealthDeviceCallback = this.b;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onStatusChanged(this.e, 3);
                return;
            }
            return;
        }
        if (i2 == 2) {
            this.f744a = bluetoothGatt;
            this.n = 2;
            d();
        } else {
            if (i2 == 0) {
                this.n = 0;
                LogUtil.h("PluginDevice_PicoocMeasureController", "doConnectionStateChange STATUS_DISCONNECTED 1");
                IHealthDeviceCallback iHealthDeviceCallback2 = this.b;
                if (iHealthDeviceCallback2 != null) {
                    iHealthDeviceCallback2.onStatusChanged(this.e, 3);
                }
                cleanup();
                return;
            }
            LogUtil.a("PluginDevice_PicoocMeasureController", "doConnectionStateChange other newState = ", Integer.valueOf(i2));
        }
    }

    private void d() {
        new Handler().postDelayed(new Runnable() { // from class: cjj.1
            @Override // java.lang.Runnable
            public void run() {
                if (cjj.this.f744a != null) {
                    cjj.this.f744a.discoverServices();
                    return;
                }
                LogUtil.h("PluginDevice_PicoocMeasureController", "startDiscoverService mBluetoothGatt is null");
                if (cjj.this.b != null) {
                    cjj.this.b.onStatusChanged(cjj.this.e, 9);
                }
            }
        }, 500L);
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.d;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        try {
            LogUtil.a("PluginDevice_PicoocMeasureController", "Enter cleanup");
            BluetoothGatt bluetoothGatt = this.f744a;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
            }
            this.f744a = null;
            this.k = null;
            this.m = null;
            this.f = null;
            this.b = null;
        } catch (SecurityException e) {
            LogUtil.b("PluginDevice_PicoocMeasureController", "cleanup SecurityException:", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        LogUtil.a("PluginDevice_PicoocMeasureController", "Enter ending");
        BluetoothGatt bluetoothGatt = this.f744a;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("PluginDevice_PicoocMeasureController", "Enter prepare");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        b(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("PluginDevice_PicoocMeasureController", "PicoocMeasureController prepare:", e.getMessage());
        }
        this.e = healthDevice;
        this.i = new ArrayList();
        this.h = true;
        this.g = true;
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        this.b = iHealthDeviceCallback;
        if (bundle != null) {
            this.j = bundle.getInt("height");
            this.l = bundle.getInt("sex");
            this.c = bundle.getInt("age");
        } else {
            LogUtil.h("PluginDevice_PicoocMeasureController", "prepare bundle is null");
        }
        this.j = dks.d((int) this.j);
        this.l = dks.b(this.l);
        this.c = dks.a(this.c);
        Object[] objArr = new Object[4];
        objArr[0] = "PicoocMeasureController prepare info:";
        objArr[1] = Boolean.valueOf(this.j > 0.0f);
        objArr[2] = Boolean.valueOf(this.l == 1);
        objArr[3] = Boolean.valueOf(this.c == 29);
        LogUtil.a("PluginDevice_PicoocMeasureController", objArr);
        return true;
    }

    private void b(final CountDownLatch countDownLatch) {
        HiHealthManager.d(cpp.a()).fetchUserData(new HiCommonListener() { // from class: cjj.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null) {
                    countDownLatch.countDown();
                    return;
                }
                cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
                if (currentUser != null) {
                    cjj.this.j = currentUser.d();
                    cjj.this.l = currentUser.c();
                    cjj.this.c = currentUser.a();
                } else if (obj instanceof List) {
                    Iterator it = ((List) obj).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Object next = it.next();
                        if (next instanceof HiUserInfo) {
                            HiUserInfo hiUserInfo = (HiUserInfo) next;
                            if (hiUserInfo.getRelateType() == 0) {
                                cjj.this.j = hiUserInfo.getHeight();
                                cjj.this.l = hiUserInfo.getGender();
                                cjj.this.c = hiUserInfo.getAge();
                                break;
                            }
                        }
                    }
                } else {
                    LogUtil.h("PluginDevice_PicoocMeasureController", "fetchUserData other data");
                }
                Object[] objArr = new Object[4];
                objArr[0] = "PicoocMeasureController fetchUserData info:";
                objArr[1] = Boolean.valueOf(cjj.this.j > 0.0f);
                objArr[2] = Boolean.valueOf(cjj.this.l == 1);
                objArr[3] = Boolean.valueOf(cjj.this.c == 29);
                LogUtil.a("PluginDevice_PicoocMeasureController", objArr);
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("PluginDevice_PicoocMeasureController", "PicoocMeasureController onFailure");
            }
        });
    }

    private void d(txy txyVar) {
        if (koq.b(this.i)) {
            return;
        }
        Iterator<HealthData> it = this.i.iterator();
        while (it.hasNext()) {
            if (txyVar.a() == it.next().getStartTime()) {
                return;
            }
        }
        this.i.add(c(txyVar));
    }

    private ckm c(txy txyVar) {
        ckm ckmVar = new ckm();
        ckmVar.setWeight(txyVar.e());
        ckmVar.setBodyFatRat(txyVar.d());
        ckmVar.setStartTime(txyVar.a());
        ckmVar.setEndTime(txyVar.a());
        return ckmVar;
    }

    public void FD_(BluetoothGatt bluetoothGatt) {
        LogUtil.a("PluginDevice_PicoocMeasureController", "initService service = ", this.k);
        this.f744a = bluetoothGatt;
        if (this.k != null) {
            LogUtil.a("PluginDevice_PicoocMeasureController", "initService service is exist");
            return;
        }
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb"));
        this.k = service;
        if (service != null) {
            this.f = service.getCharacteristic(UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb"));
            this.m = this.k.getCharacteristic(UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb"));
            this.f744a.setCharacteristicNotification(this.f, true);
            BluetoothGattDescriptor descriptor = this.f.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
            if (descriptor != null) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                this.f744a.writeDescriptor(descriptor);
            }
        }
    }
}
