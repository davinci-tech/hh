package com.huawei.devicesdk.connect.physical;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.CharacterOperationInterface;
import com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase;
import com.huawei.devicesdk.connect.retry.ExecuteActionInterface;
import com.huawei.devicesdk.connect.retry.RetryCallbackInterface;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import defpackage.bia;
import defpackage.bij;
import defpackage.bil;
import defpackage.bim;
import defpackage.bip;
import defpackage.biu;
import defpackage.bkz;
import defpackage.bln;
import defpackage.blt;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes3.dex */
public abstract class OpenBlePhysicalServiceBase extends PhysicalLayerBase implements CharacterOperationInterface {
    private static final int LIST_DEFAULT_SIZE = 16;
    private static final int MAX_REDISCOVER_TIMES = 10;
    private static final int MAX_SET_CHARACTER = 3;
    private static final String TAG = "OpenBlePhysicalServiceBase";
    private static final String TAG_RELEASE = "DEVMGR_OpenBlePhysicalServiceBase";
    private static final int TIMEOUT_LOCK = 3000;
    protected static final int WAIT_MS_200 = 200;
    protected static final int WAIT_SERVICE_INIT = 100;
    private int mBluetoothConnectState;
    public BluetoothGatt mBluetoothGatt;
    private d mBluetoothGattCallback;
    private Condition mCharacterCondition;
    private bij mCharacterExecutor;
    private final ReentrantLock mCharacterLock;
    public bia mConnectHandler;
    private ConsumerHandler<Message> mConnectHandlerMessage;
    protected bij mServiceDiscoveryExecutor;
    protected AtomicBoolean mIsLocked = new AtomicBoolean(false);
    private final Object mLock = new Object();
    private final Object mStatusLock = new Object();

    protected abstract void characteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    protected abstract void descriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    protected abstract void onCharacteristicChange(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    protected abstract void onServiceDiscovery(BluetoothGatt bluetoothGatt);

    protected abstract void setDescriptorValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattDescriptor bluetoothGattDescriptor, List<byte[]> list);

    protected abstract boolean writeCharacteristicValue(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    class d extends BluetoothGattCallback {
        private d() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            ReleaseLogUtil.b(OpenBlePhysicalServiceBase.TAG_RELEASE, "onConnectionStateChange status: ", Integer.valueOf(i), ", newState: ", Integer.valueOf(i2), blt.b(OpenBlePhysicalServiceBase.this.mDeviceInfo));
            if (bluetoothGatt == null) {
                LogUtil.e(OpenBlePhysicalServiceBase.TAG, "onConnectionStateChange gatt is null", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(3, bln.e(1, i));
            } else {
                if (OpenBlePhysicalServiceBase.this.mBluetoothGatt == null) {
                    LogUtil.a(OpenBlePhysicalServiceBase.TAG, "onConnectionStateChange set mBluetoothGatt", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                    OpenBlePhysicalServiceBase.this.mBluetoothGatt = bluetoothGatt;
                }
                OpenBlePhysicalServiceBase.this.onDeviceStateChange(i, i2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            OpenBlePhysicalServiceBase.this.mConnectHandler.c((Object) null);
            if (bluetoothGatt == null) {
                LogUtil.e(OpenBlePhysicalServiceBase.TAG, "onServicesDiscovered gatt is null", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(3, bln.e(2, i));
                return;
            }
            ReleaseLogUtil.b(OpenBlePhysicalServiceBase.TAG_RELEASE, "onServicesDiscovered status:", Integer.valueOf(i), blt.b(OpenBlePhysicalServiceBase.this.mDeviceInfo));
            if (i != 0) {
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(3, bln.e(2, i));
            } else {
                OpenBlePhysicalServiceBase.this.onServiceDiscovery(bluetoothGatt);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            OpenBlePhysicalServiceBase.this.onCharacteristicChange(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.e(OpenBlePhysicalServiceBase.TAG, "onCharacteristicRead characteristic is null");
                return;
            }
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            blt.d(OpenBlePhysicalServiceBase.TAG, bluetoothGattCharacteristic.getValue(), "onCharacteristicRead uuid:", bluetoothGattCharacteristic.getUuid(), blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo), " data:");
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (i != 0) {
                OpenBlePhysicalServiceBase.this.onMessageReceived(uuid, value, 1);
            } else {
                OpenBlePhysicalServiceBase.this.onMessageReceived(uuid, value, 0);
            }
            OpenBlePhysicalServiceBase.this.unlock(uuid);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.e(OpenBlePhysicalServiceBase.TAG, "onCharacteristicWrite characteristic is null");
                return;
            }
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            OpenBlePhysicalServiceBase.this.characteristicWrite(bluetoothGattCharacteristic, i);
            OpenBlePhysicalServiceBase.this.unlock(bluetoothGattCharacteristic.getUuid().toString());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (bluetoothGattDescriptor == null) {
                LogUtil.e(OpenBlePhysicalServiceBase.TAG, "descriptor is null");
                return;
            }
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            OpenBlePhysicalServiceBase.this.descriptorWrite(bluetoothGattDescriptor, i);
            OpenBlePhysicalServiceBase.this.unlock(bluetoothGattDescriptor.getUuid().toString());
        }
    }

    /* renamed from: lambda$new$0$com-huawei-devicesdk-connect-physical-OpenBlePhysicalServiceBase, reason: not valid java name */
    public /* synthetic */ void m128x59aaf103(Message message) {
        if (message == null) {
            LogUtil.e(TAG, "message is null");
        }
        switch (message.what) {
            case 2:
                checkActionExecuteState(startServiceDiscovery(), 2);
                break;
            case 3:
                release();
                break;
            case 4:
                this.mStatusChangeCallback.onConnectStatusChanged(this.mDeviceInfo, 3, message.arg1);
                break;
            case 5:
                checkActionExecuteState(initGattService(), 1);
                break;
            case 6:
                checkActionExecuteState(reInitGattService(), 1);
                break;
            case 7:
                checkActionExecuteState(checkInitCharacteristic(), 2);
                break;
            case 8:
                if (message.obj instanceof BluetoothGattCharacteristic) {
                    checkActionExecuteState(enableInitCharacteristic((BluetoothGattCharacteristic) message.obj), 2);
                    break;
                }
                break;
            case 9:
                int i = message.arg1;
                Object obj = message.obj;
                if (obj instanceof biu) {
                    notifyMessage((biu) obj, i);
                    break;
                }
                break;
            case 10:
                notifyDeviceState(message.arg1, message.arg2);
                break;
            case 11:
                enableDeviceCapability();
                break;
        }
    }

    public OpenBlePhysicalServiceBase() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mCharacterLock = reentrantLock;
        this.mCharacterCondition = reentrantLock.newCondition();
        this.mBluetoothGattCallback = new d();
        this.mConnectHandlerMessage = new ConsumerHandler() { // from class: bie
            @Override // com.huawei.devicesdk.connect.physical.ConsumerHandler
            public final void accept(Object obj) {
                OpenBlePhysicalServiceBase.this.m128x59aaf103((Message) obj);
            }
        };
        this.mConnectHandler = new bia(this.mConnectHandlerMessage);
        this.mServiceDiscoveryExecutor = new bij(10);
        this.mCharacterExecutor = new bij(3);
    }

    private void notifyDeviceState(int i, int i2) {
        LogUtil.c(TAG, "notifyDeviceStatusToApp status ", Integer.valueOf(i), blt.a(this.mDeviceInfo));
        synchronized (this.mStatusLock) {
            if (i == this.mBluetoothConnectState) {
                LogUtil.c(TAG, "connectState no change");
                return;
            }
            this.mBluetoothConnectState = i;
            if (this.mStatusChangeCallback != null) {
                this.mStatusChangeCallback.onConnectStatusChanged(this.mDeviceInfo, i, i2);
            } else {
                LogUtil.e(TAG, "device status call back is null", blt.a(this.mDeviceInfo));
            }
        }
    }

    @Override // com.huawei.devicesdk.callback.CharacterOperationInterface
    public void write(bim bimVar) {
        if (bimVar == null) {
            LogUtil.e(TAG, "write frameData is null");
            return;
        }
        LogUtil.c(TAG, "write characteristic. characterId: ", bimVar.d(), blt.a(this.mDeviceInfo));
        BluetoothGattCharacteristic characteristic = getCharacteristic(bimVar.c(), bimVar.d());
        if (characteristic == null) {
            onMessageReceived(bimVar.d(), new byte[0], 1);
        }
        List<bil> e = bimVar.e();
        if (bkz.e(e)) {
            LogUtil.e(TAG, "the packages getted is empty", blt.a(this.mDeviceInfo));
            return;
        }
        Iterator<bil> it = e.iterator();
        while (it.hasNext()) {
            if (!executeCharacteristicValue(characteristic, it.next(), bimVar.a())) {
                onMessageReceived(bimVar.d(), new byte[0], 1);
                return;
            }
        }
    }

    @Override // com.huawei.devicesdk.callback.CharacterOperationInterface
    public void enable(bim bimVar) {
        if (bimVar == null) {
            LogUtil.e(TAG, "enable frameData is null");
            return;
        }
        LogUtil.c(TAG, "enable characteristic. characterId:", bimVar.d(), blt.a(this.mDeviceInfo));
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        arrayList.add(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        enableOrDisableCharacteristic(bimVar, true, arrayList);
    }

    @Override // com.huawei.devicesdk.callback.CharacterOperationInterface
    public void read(bim bimVar) {
        if (bimVar == null) {
            LogUtil.e(TAG, "read frameData is null");
            return;
        }
        LogUtil.c(TAG, "read characteristic. characterId: ", bimVar.d(), blt.a(this.mDeviceInfo));
        BluetoothGattCharacteristic characteristic = getCharacteristic(bimVar.c(), bimVar.d());
        if (characteristic == null) {
            onMessageReceived(bimVar.d(), new byte[0], 1);
        }
        executeReadCharacteristic(characteristic, bimVar.a());
    }

    @Override // com.huawei.devicesdk.callback.CharacterOperationInterface
    public void disable(bim bimVar) {
        if (bimVar == null) {
            LogUtil.e(TAG, "disable frameData is null");
            return;
        }
        LogUtil.c(TAG, "disable characteristic. characterId:", bimVar.d(), blt.a(this.mDeviceInfo));
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        enableOrDisableCharacteristic(bimVar, false, arrayList);
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void connectDevice(DeviceInfo deviceInfo) {
        if (this.mDeviceInfo == null || deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.e(TAG, "connect device failed. device is invalid.");
            updateDeviceConnectState(3, bln.e(7, 303));
            return;
        }
        LogUtil.c(TAG, "connectDevice start. ", blt.a(this.mDeviceInfo));
        if (this.mBluetoothGatt != null) {
            LogUtil.c(TAG, "mBluetooth exit, start release gatt.", blt.a(this.mDeviceInfo));
            try {
                this.mBluetoothGatt.disconnect();
            } catch (SecurityException e) {
                LogUtil.e(TAG, "connectDevice SecurityException ", ExceptionUtils.d(e));
            }
            this.mConnectHandler.d(6, 5000L);
            return;
        }
        this.mConnectHandler.b(5);
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void disconnectDevice() {
        LogUtil.c(TAG, "disconnectDevice start");
        synchronized (this.mLock) {
            BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
            if (bluetoothGatt != null) {
                try {
                    bluetoothGatt.disconnect();
                } catch (SecurityException e) {
                    LogUtil.e(TAG, "disconnectDevice SecurityException ", ExceptionUtils.d(e));
                }
                LogUtil.c(TAG, "disconnectGatt success", blt.a(this.mDeviceInfo));
                updateDeviceConnectState(0, bln.b(6));
            } else {
                LogUtil.a(TAG, "disconnectGatt fail because mBluetoothGatt is invalid.", blt.a(this.mDeviceInfo));
                updateDeviceConnectState(0, bln.e(6, 303));
            }
        }
        this.mConnectHandler.d(3, 200L);
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean sendData(bim bimVar, String str) {
        if (bimVar == null || bimVar.b() == null) {
            LogUtil.e(TAG, "sendData error. bluetooth frame data is null");
            return false;
        }
        CharacterOperationType b = bimVar.b();
        LogUtil.c(TAG, "sendData start. characterId:", bimVar.d(), " type:", b);
        int i = AnonymousClass4.c[b.ordinal()];
        if (i == 1) {
            enable(bimVar);
        } else if (i == 2) {
            write(bimVar);
        } else if (i == 3) {
            disable(bimVar);
        } else if (i == 4) {
            read(bimVar);
        }
        LogUtil.c(TAG, "sendData finish. characterId:", bimVar.d(), " type:", b);
        return true;
    }

    /* renamed from: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[CharacterOperationType.values().length];
            c = iArr;
            try {
                iArr[CharacterOperationType.ENABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[CharacterOperationType.WRITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[CharacterOperationType.DISABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[CharacterOperationType.READ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public void destroy() {
        release();
        this.mConnectHandler.a();
        this.mConnectHandlerMessage = null;
        this.mBluetoothDevice = null;
        this.mServiceDiscoveryExecutor = null;
        this.mCharacterExecutor = null;
    }

    protected void unlock(String str) {
        LogUtil.c(TAG, "unlock character. characterUuid:", str);
        this.mCharacterLock.lock();
        if (this.mIsLocked.get()) {
            LogUtil.c(TAG, "release mCharacterLock. characterUuid:", str);
            this.mIsLocked.set(false);
            this.mCharacterCondition.signalAll();
        } else {
            LogUtil.c(TAG, "release mCharacterLock skip. lock is false.");
        }
        this.mCharacterLock.unlock();
    }

    protected void lock(String str) {
        LogUtil.c(TAG, "lock character. characterUuid:", str);
        try {
            try {
                this.mCharacterLock.lock();
                LogUtil.c(TAG, "get mCharacterLock wait. characterUuid:", str);
                if (this.mIsLocked.get()) {
                    LogUtil.c(TAG, "get mCharacterLock lock ", Boolean.valueOf(this.mCharacterCondition.await(3000L, TimeUnit.MILLISECONDS)), ". characterUuid:", str);
                }
            } catch (InterruptedException unused) {
                LogUtil.e(TAG, "InterruptedException. characterUuid:", str);
                this.mIsLocked.set(false);
            }
        } finally {
            this.mCharacterLock.unlock();
        }
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean isSupportService(String str) {
        BluetoothGattService bluetoothGattService;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null && str != null) {
            try {
                bluetoothGattService = bluetoothGatt.getService(UUID.fromString(str));
            } catch (SecurityException e) {
                LogUtil.e(TAG, "isSupportService SecurityException", ExceptionUtils.d(e));
                bluetoothGattService = null;
            }
            if (bluetoothGattService != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.devicesdk.connect.physical.PhysicalLayerBase
    public boolean isSupportCharactor(String str, String str2) {
        BluetoothGattService bluetoothGattService;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null && str != null && str2 != null) {
            try {
                bluetoothGattService = bluetoothGatt.getService(UUID.fromString(str));
            } catch (SecurityException e) {
                LogUtil.e(TAG, "isSupportCharactor SecurityException", ExceptionUtils.d(e));
                bluetoothGattService = null;
            }
            if (bluetoothGattService != null && bluetoothGattService.getCharacteristic(UUID.fromString(str2)) != null) {
                return true;
            }
        }
        return false;
    }

    private void enableOrDisableCharacteristic(bim bimVar, boolean z, List<byte[]> list) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(bimVar.c(), bimVar.d());
        if (characteristic == null) {
            doCharacteristicNotificationResponse(bimVar.d(), false);
        } else {
            doCharacteristicNotificationResponse(bimVar.d(), executeCharacteristicNotification(characteristic, z, list, bimVar.a()));
        }
    }

    private void doCharacteristicNotificationResponse(final String str, final boolean z) {
        if (!this.mConnectHandler.d()) {
            LogUtil.c(TAG, "mConnectHandler is null");
        } else {
            this.mConnectHandler.c(new Runnable() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.5
                @Override // java.lang.Runnable
                public void run() {
                    if (OpenBlePhysicalServiceBase.this.mMessageReceiveCallback != null) {
                        OpenBlePhysicalServiceBase.this.mMessageReceiveCallback.onChannelEnable(OpenBlePhysicalServiceBase.this.mDeviceInfo, str, !z ? 1 : 0);
                    }
                }
            });
        }
    }

    private boolean executeCharacteristicNotification(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final boolean z, final List<byte[]> list, int i) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new bij(i).d(new ExecuteActionInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.3
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "EnableOrDisableCharacteristic{" + bluetoothGattCharacteristic.getUuid() + "}";
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                return OpenBlePhysicalServiceBase.this.setCharacteristicNotification(bluetoothGattCharacteristic, z, list);
            }
        }, new RetryCallbackInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.9
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i2) {
                OpenBlePhysicalServiceBase.this.sleepDelay(100L);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                atomicBoolean.set(true);
                OpenBlePhysicalServiceBase.this.sleepDelay(100L);
                LogUtil.c(OpenBlePhysicalServiceBase.TAG, "EnableOrDisableCharacteristic success, character: ", bluetoothGattCharacteristic.getUuid(), blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
            }
        }, false);
        return atomicBoolean.get();
    }

    private boolean executeCharacteristicValue(final BluetoothGattCharacteristic bluetoothGattCharacteristic, bil bilVar, int i) {
        final byte[] d2 = bilVar.d();
        final int a2 = bilVar.a();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new bij(i).d(new ExecuteActionInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.8
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                StringBuilder sb = new StringBuilder("WriteCharacteristic{");
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
                sb.append(bluetoothGattCharacteristic2 == null ? "" : bluetoothGattCharacteristic2.getUuid());
                sb.append("} ");
                return sb.toString();
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                return OpenBlePhysicalServiceBase.this.writeCharacteristicValue(bluetoothGattCharacteristic, d2);
            }
        }, new RetryCallbackInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.7
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i2) {
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                atomicBoolean.set(true);
                OpenBlePhysicalServiceBase.this.sleepDelay(a2);
            }
        }, false);
        return atomicBoolean.get();
    }

    private boolean executeReadCharacteristic(final BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        new bij(i).d(new ExecuteActionInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.10
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                StringBuilder sb = new StringBuilder("ReadCharacteristic{");
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
                sb.append(bluetoothGattCharacteristic2 == null ? "" : bluetoothGattCharacteristic2.getUuid());
                sb.append("} ");
                return sb.toString();
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                return OpenBlePhysicalServiceBase.this.readCharacteristic(bluetoothGattCharacteristic);
            }
        }, new RetryCallbackInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.6
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i2) {
                OpenBlePhysicalServiceBase.this.sleepDelay(100L);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                atomicBoolean.set(true);
                OpenBlePhysicalServiceBase.this.sleepDelay(100L);
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
                if (bluetoothGattCharacteristic2 != null) {
                    LogUtil.c(OpenBlePhysicalServiceBase.TAG, "readCharacteristic success, character: ", bluetoothGattCharacteristic2.getUuid(), "mDeviceInfo: ", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                } else {
                    LogUtil.c(OpenBlePhysicalServiceBase.TAG, "readCharacteristic success, character: ", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                }
            }
        }, false);
        return atomicBoolean.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        boolean z;
        synchronized (this.mLock) {
            if (bluetoothGattCharacteristic == null) {
                LogUtil.e(TAG, "characteristic is invalid.");
                return false;
            }
            if (this.mBluetoothGatt == null) {
                return false;
            }
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            this.mIsLocked.set(true);
            try {
                z = this.mBluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
            } catch (SecurityException e) {
                LogUtil.e(TAG, "readCharacteristic SecurityException:", ExceptionUtils.d(e));
                z = false;
            }
            LogUtil.c(TAG, "readCharacteristic status:", Boolean.valueOf(z));
            if (z) {
                lock(uuid);
            } else {
                this.mIsLocked.set(false);
            }
            return z;
        }
    }

    private void checkActionExecuteState(boolean z, int i) {
        if (z) {
            return;
        }
        updateDeviceConnectState(3, bln.e(i, 301));
    }

    private boolean reInitGattService() {
        LogUtil.c(TAG, "reInitGatt start.", blt.a(this.mDeviceInfo));
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.close();
                this.mConnectHandler.d(5, 1000L);
                return true;
            } catch (SecurityException e) {
                LogUtil.e(TAG, "reInitGattService SecurityException ", ExceptionUtils.d(e));
                return false;
            }
        }
        LogUtil.e(TAG, "reInitGatt error. mBluetoothGatt is null.", blt.a(this.mDeviceInfo));
        return false;
    }

    private boolean initGattService() {
        LogUtil.c(TAG, "initGattService start", blt.a(this.mDeviceInfo));
        sendTimeoutMessage(this.mConnectHandler, 1);
        try {
            this.mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(this.mDeviceInfo.getDeviceMac());
        } catch (Exception e) {
            LogUtil.e(TAG, "initGattService occur exception ", ExceptionUtils.d(e));
        }
        if (this.mBluetoothDevice == null) {
            LogUtil.a(TAG, "mBluetoothDevice is null");
            return false;
        }
        String name = this.mBluetoothDevice.getName();
        LogUtil.c(TAG, "BluetoothDevice deviceName: ", name);
        if (!TextUtils.isEmpty(name)) {
            this.mDeviceInfo.setDeviceName(name);
        }
        this.mBluetoothGatt = this.mBluetoothDevice.connectGatt(BaseApplication.e(), false, this.mBluetoothGattCallback, 2);
        synchronized (this.mStatusLock) {
            this.mBluetoothConnectState = 1;
        }
        return this.mBluetoothGatt != null;
    }

    private void release() {
        this.mConnectHandler.c(4);
        synchronized (this.mStatusLock) {
            LogUtil.c(TAG, "release gatt start. current bluetooth state:", Integer.valueOf(this.mBluetoothConnectState), blt.a(this.mDeviceInfo));
        }
        synchronized (this.mLock) {
            if (this.mBluetoothGatt != null) {
                LogUtil.c(TAG, "start close gatt.", blt.a(this.mDeviceInfo));
                try {
                    this.mBluetoothGatt.close();
                } catch (SecurityException e) {
                    LogUtil.e(TAG, "release SecurityException ", ExceptionUtils.d(e));
                }
                this.mBluetoothGatt = null;
            }
        }
        this.mConnectHandler.c((Object) null);
        bij bijVar = this.mServiceDiscoveryExecutor;
        if (bijVar != null) {
            bijVar.d();
        }
        bij bijVar2 = this.mCharacterExecutor;
        if (bijVar2 != null) {
            bijVar2.d();
        }
    }

    private boolean startServiceDiscovery() {
        LogUtil.c(TAG, "startServiceDiscovery start", blt.a(this.mDeviceInfo));
        this.mConnectHandler.c(2);
        sendTimeoutMessage(this.mConnectHandler, 2);
        if (this.mBluetoothGatt != null) {
            LogUtil.c(TAG, "start service discovery", blt.a(this.mDeviceInfo));
            try {
                this.mBluetoothGatt.discoverServices();
                return true;
            } catch (SecurityException e) {
                LogUtil.e(TAG, "startServiceDiscovery SecurityException ", ExceptionUtils.d(e));
                return false;
            }
        }
        LogUtil.e(TAG, "startServiceDiscovery error. mBluetoothGatt is null", blt.a(this.mDeviceInfo));
        return false;
    }

    private boolean checkInitCharacteristic() {
        LogUtil.c(TAG, "checkScaleStatusCharacteristic", blt.a(this.mDeviceInfo));
        BluetoothGattCharacteristic characteristic = getCharacteristic(bip.c.toString(), bip.b.toString());
        if (characteristic == null) {
            LogUtil.a(TAG, "characteristic is null", blt.a(this.mDeviceInfo));
            return false;
        }
        this.mCharacterExecutor.d();
        Message rP_ = this.mConnectHandler.rP_(8);
        rP_.obj = characteristic;
        this.mConnectHandler.rQ_(rP_);
        return true;
    }

    private BluetoothGattCharacteristic getCharacteristic(String str, String str2) {
        BluetoothGattService bluetoothGattService;
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            LogUtil.e(TAG, "mBluetoothGatt is null", blt.a(this.mDeviceInfo));
            return null;
        }
        if (str == null || str2 == null) {
            LogUtil.e(TAG, "invalid parameter");
            return null;
        }
        try {
            bluetoothGattService = bluetoothGatt.getService(UUID.fromString(str));
        } catch (SecurityException e) {
            LogUtil.e(TAG, "getCharacteristic SecurityException", ExceptionUtils.d(e));
            bluetoothGattService = null;
        }
        if (bluetoothGattService == null) {
            LogUtil.e(TAG, "can not get service. serviceUuid: ", str, blt.a(this.mDeviceInfo));
            return null;
        }
        BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(UUID.fromString(str2));
        if (characteristic == null) {
            LogUtil.e(TAG, "can not get characteristic. : characteristicUuid", str2, blt.a(this.mDeviceInfo));
        }
        return characteristic;
    }

    private boolean enableInitCharacteristic(final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        LogUtil.c(TAG, "enableInitCharacteristic", blt.a(this.mDeviceInfo));
        if (this.mBluetoothGatt == null) {
            return false;
        }
        this.mCharacterExecutor.d(new ExecuteActionInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.11
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "InitStatusCharacteristic" + blt.b(OpenBlePhysicalServiceBase.this.mDeviceInfo);
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                arrayList.add(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                return OpenBlePhysicalServiceBase.this.setCharacteristicNotification(bluetoothGattCharacteristic, true, arrayList);
            }
        }, new RetryCallbackInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.13
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i) {
                Message rP_ = OpenBlePhysicalServiceBase.this.mConnectHandler.rP_(8);
                rP_.obj = bluetoothGattCharacteristic;
                OpenBlePhysicalServiceBase.this.mConnectHandler.rR_(rP_, 200L);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                LogUtil.c(OpenBlePhysicalServiceBase.TAG, "setCharacteristicNotification success", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                OpenBlePhysicalServiceBase.this.mConnectHandler.c(8);
                OpenBlePhysicalServiceBase.this.mConnectHandler.rQ_(OpenBlePhysicalServiceBase.this.mConnectHandler.rP_(11));
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
                OpenBlePhysicalServiceBase.this.mConnectHandler.c(8);
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(3, bln.e(2, 301));
            }
        }, true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, List<byte[]> list) {
        synchronized (this.mLock) {
            BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
            if (bluetoothGatt == null) {
                LogUtil.a(TAG, "setCharacteristicNotification mBluetoothGatt is null.");
                return false;
            }
            try {
                boolean characteristicNotification = bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
                BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(bip.e);
                if (descriptor != null) {
                    setDescriptorValue(bluetoothGattCharacteristic, descriptor, list);
                    String uuid = bluetoothGattCharacteristic.getUuid().toString();
                    this.mIsLocked.set(true);
                    characteristicNotification = this.mBluetoothGatt.writeDescriptor(descriptor);
                    LogUtil.c(TAG, "setCharacteristicNotification status:", Boolean.valueOf(characteristicNotification));
                    if (characteristicNotification) {
                        lock(uuid);
                    } else {
                        this.mIsLocked.set(false);
                    }
                }
                LogUtil.c(TAG, "setCharacteristicNotification", blt.a(this.mDeviceInfo), " isEnable:", Boolean.valueOf(z), " character:", bluetoothGattCharacteristic.getUuid().toString(), " result:", Boolean.valueOf(characteristicNotification));
                return characteristicNotification;
            } catch (SecurityException e) {
                LogUtil.e(TAG, "setCharacteristicNotification SecurityException ", ExceptionUtils.d(e));
                return false;
            }
        }
    }

    private void enableDeviceCapability() {
        LogUtil.c(TAG, "enableDeviceCapability", blt.a(this.mDeviceInfo));
        if (this.mBluetoothGatt == null) {
            return;
        }
        this.mCharacterExecutor.d();
        final List<BluetoothGattCharacteristic> deviceCapabilityCharacteristic = getDeviceCapabilityCharacteristic();
        this.mCharacterExecutor.d(new ExecuteActionInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.2
            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public String getActionName() {
                return "DeviceCapabilityCharacteristic" + blt.b(OpenBlePhysicalServiceBase.this.mDeviceInfo);
            }

            @Override // com.huawei.devicesdk.connect.retry.ExecuteActionInterface
            public boolean execute() {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                Iterator it = deviceCapabilityCharacteristic.iterator();
                while (it.hasNext()) {
                    if (!OpenBlePhysicalServiceBase.this.setCharacteristicNotification((BluetoothGattCharacteristic) it.next(), true, arrayList)) {
                        return false;
                    }
                }
                return true;
            }
        }, new RetryCallbackInterface() { // from class: com.huawei.devicesdk.connect.physical.OpenBlePhysicalServiceBase.1
            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doRetryAction(int i) {
                Message rP_ = OpenBlePhysicalServiceBase.this.mConnectHandler.rP_(11);
                rP_.obj = deviceCapabilityCharacteristic;
                OpenBlePhysicalServiceBase.this.mConnectHandler.rR_(rP_, 200L);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doSuccessAction() {
                LogUtil.c(OpenBlePhysicalServiceBase.TAG, "setCharacteristicNotification success", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                OpenBlePhysicalServiceBase.this.mConnectHandler.c(11);
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(2, 100000);
            }

            @Override // com.huawei.devicesdk.connect.retry.RetryCallbackInterface
            public void doFailureAction() {
                LogUtil.c(OpenBlePhysicalServiceBase.TAG, "setCharacteristicNotification fail", blt.a(OpenBlePhysicalServiceBase.this.mDeviceInfo));
                OpenBlePhysicalServiceBase.this.mConnectHandler.c(11);
                OpenBlePhysicalServiceBase.this.updateDeviceConnectState(3, bln.e(2, 301));
            }
        }, true);
    }

    private List<BluetoothGattCharacteristic> getDeviceCapabilityCharacteristic() {
        String uuid = bip.f389a.toString();
        ArrayList<String> arrayList = new ArrayList(16);
        arrayList.add(bip.g.toString());
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            BluetoothGattCharacteristic characteristic = getCharacteristic(uuid, str);
            if (characteristic == null) {
                LogUtil.a(TAG, "can not find characteristic. service:", uuid, " characteristic:", str);
            } else {
                arrayList2.add(characteristic);
            }
        }
        return arrayList2;
    }

    private void notifyMessage(biu biuVar, int i) {
        if (this.mMessageReceiveCallback != null) {
            this.mMessageReceiveCallback.onDataReceived(this.mDeviceInfo, biuVar, i);
        } else {
            LogUtil.e(TAG, "device message call back is null", blt.a(this.mDeviceInfo));
        }
    }

    public void updateDeviceConnectState(int i, int i2) {
        if (i == 2 || i == 3) {
            this.mConnectHandler.c((Object) null);
        }
        Message rP_ = this.mConnectHandler.rP_(10);
        rP_.arg1 = i;
        rP_.arg2 = i2;
        this.mConnectHandler.rQ_(rP_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceStateChange(int i, int i2) {
        if (i == 133) {
            LogUtil.e(TAG, "onConnectionStateChange bluetooth 133 error", blt.a(this.mDeviceInfo));
            updateDeviceConnectState(3, bln.e(6, i));
        }
        if (i2 == 2) {
            LogUtil.c(TAG, "device connect success. ", blt.a(this.mDeviceInfo));
            this.mConnectHandler.c(4);
            this.mConnectHandler.d(2, 1000L);
        } else {
            if (i2 == 0) {
                LogUtil.c(TAG, "device disconnect success.", blt.a(this.mDeviceInfo));
                updateDeviceConnectState(0, bln.e(6, i));
                disconnectDevice();
                return;
            }
            LogUtil.a(TAG, "onStateChange unknown", blt.a(this.mDeviceInfo));
        }
    }

    protected void onMessageReceived(String str, byte[] bArr, int i) {
        biu biuVar = new biu();
        biuVar.a(str);
        biuVar.d(bArr);
        Message rP_ = this.mConnectHandler.rP_(9);
        rP_.arg1 = i;
        rP_.obj = biuVar;
        this.mConnectHandler.rQ_(rP_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sleepDelay(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            LogUtil.e(TAG, "sleep delay interrupted");
        }
    }
}
