package com.careforeyou.library.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.bt.bean.TaskData;
import com.careforeyou.library.enums.EnumProcessResult;
import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.intface.AsynBLETaskCallback;
import com.careforeyou.library.intface.OnBluetoothListener;
import com.careforeyou.library.intface.OnWeightScalesListener;
import com.careforeyou.library.intface.SeachDeviceCallback;
import com.careforeyou.library.protocal.iStraightFrame;
import com.huawei.operation.ble.BleConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import defpackage.nl;
import defpackage.nm;
import defpackage.nn;
import defpackage.nq;
import defpackage.ns;
import defpackage.nt;
import defpackage.nx;
import defpackage.oa;
import defpackage.oc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/* loaded from: classes2.dex */
public class CsBtUtil_v11 {
    private static volatile CsBtUtil_v11 b;
    public BluetoothAdapter e;
    private nm g;
    private BluetoothGatt i;
    private Context j;
    private BluetoothGattCharacteristic o;
    private Handler q;
    private BluetoothGattCharacteristic t;
    private BluetoothDevice f = null;
    private OnBluetoothListener h = null;
    private OnWeightScalesListener p = null;
    private final int s = 1500;
    private int l = 11;
    public String c = "";
    private Protocal_Type k = Protocal_Type.OKOK;
    private boolean m = false;
    private AsynBLETaskCallback r = null;
    private BluetoothGattCallback n = new BluetoothGattCallback() { // from class: com.careforeyou.library.utils.CsBtUtil_v11.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            oa.e("CsBtUtil_v11", "status" + i + "++newState++" + i2);
            if (i == 133) {
                oa.e("CsBtUtil_v11", "status == 133");
                CsBtUtil_v11.this.l = 11;
                bluetoothGatt.close();
                if (CsBtUtil_v11.this.h != null) {
                    CsBtUtil_v11.this.h.bluetoothStateChange(CsBtUtil_v11.this.l);
                    return;
                }
                return;
            }
            if (i2 == 2 && i == 0) {
                oa.e("CsBtUtil_v11", "STATE_CONNECTED++++GATT_SUCCESS");
                CsBtUtil_v11.this.l = 12;
                CsBtUtil_v11.this.q.removeCallbacks(CsBtUtil_v11.this.f1659a);
                CsBtUtil_v11.this.i.discoverServices();
                if (CsBtUtil_v11.this.h != null) {
                    CsBtUtil_v11.this.h.bluetoothStateChange(CsBtUtil_v11.this.l);
                    return;
                }
                return;
            }
            if (i2 == 0) {
                oa.e("CsBtUtil_v11", " BluetoothProfile.STATE_DISCONNECTED");
                bluetoothGatt.close();
                CsBtUtil_v11.this.l = 11;
                if (CsBtUtil_v11.this.h != null) {
                    CsBtUtil_v11.this.h.bluetoothStateChange(CsBtUtil_v11.this.l);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (i == 0) {
                oa.e("CsBtUtil_v11", "GATT_SUCCESS");
                List<BluetoothGattService> services = CsBtUtil_v11.this.i.getServices();
                CsBtUtil_v11.this.l = 10;
                CsBtUtil_v11.this.a(services);
                return;
            }
            oa.e("CsBtUtil_v11", "服务发现失败");
            CsBtUtil_v11.this.l = 11;
            bluetoothGatt.disconnect();
            if (CsBtUtil_v11.this.h != null) {
                CsBtUtil_v11.this.h.bluetoothStateChange(CsBtUtil_v11.this.l);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (CsBtUtil_v11.this.h != null) {
                CsBtUtil_v11.this.h.bluetoothStateChange(2);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (CsBtUtil_v11.this.h != null) {
                CsBtUtil_v11.this.h.bluetoothStateChange(3);
            }
            if (CsBtUtil_v11.this.r == null) {
                return;
            }
            if (i == 0) {
                CsBtUtil_v11.this.r.success("");
            } else {
                CsBtUtil_v11.this.r.failed();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            CsBtUtil_v11.this.bz_(bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (CsBtUtil_v11.this.r == null) {
                return;
            }
            if (i == 0) {
                CsBtUtil_v11.this.r.success("");
            } else {
                CsBtUtil_v11.this.r.failed();
            }
        }
    };
    BroadcastReceiver d = new BroadcastReceiver() { // from class: com.careforeyou.library.utils.CsBtUtil_v11.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.location.MODE_CHANGED")) {
                if (CsBtUtil_v11.this.h != null) {
                    CsBtUtil_v11.this.h.bluetoothStateChange(17);
                    return;
                }
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
            if (intExtra != 10) {
                if (intExtra == 12 && CsBtUtil_v11.this.h != null) {
                    CsBtUtil_v11.this.h.bluetoothTurnOn();
                    CsBtUtil_v11.this.h.bluetoothStateChange(8);
                    return;
                }
                return;
            }
            if (CsBtUtil_v11.this.h != null) {
                CsBtUtil_v11.this.h.bluetoothTurnOff();
                CsBtUtil_v11.this.h.bluetoothStateChange(7);
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    Runnable f1659a = new Runnable() { // from class: com.careforeyou.library.utils.CsBtUtil_v11.1
        @Override // java.lang.Runnable
        public void run() {
            if (CsBtUtil_v11.this.d()) {
                return;
            }
            oa.e("CsBtUtil_v11", "runnableReConnect:  !isConnected()");
            CsBtUtil_v11.this.g();
            CsBtUtil_v11.this.q.postDelayed(this, ProfileExtendConstants.TIME_OUT);
        }
    };

    public enum CONNECT_MODE {
        FSAC,
        Alway_Conn
    }

    public enum Down_Instruction_Type {
        Sync_UserInfo
    }

    public enum Synchronization_Task_Key {
        Time,
        CSDownData,
        BodyMeasurement,
        BodyHistory,
        CSNotify,
        LXUPI,
        LXUPN,
        LXDOWNN,
        Unknown
    }

    public static CsBtUtil_v11 e() {
        if (b == null) {
            synchronized (CsBtUtil_v11.class) {
                if (b == null) {
                    b = new CsBtUtil_v11();
                }
            }
        }
        return b;
    }

    private CsBtUtil_v11() {
        this.e = null;
        this.g = null;
        this.e = BluetoothAdapter.getDefaultAdapter();
        this.g = new nm(this);
    }

    public void c(Context context) {
        this.m = false;
        this.j = context;
        if (nt.e(Build.VERSION.RELEASE, "4.4") < 0) {
            this.q = new Handler(context.getMainLooper());
        } else {
            this.q = new Handler();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.location.MODE_CHANGED");
        context.registerReceiver(this.d, intentFilter);
    }

    public void c() {
        BluetoothGatt bluetoothGatt = this.i;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
        oa.e("CsBtUtil_v11", "disconnectGATT");
        this.l = 11;
    }

    public void d(Context context) {
        try {
            context.unregisterReceiver(this.d);
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        boolean z = this.e == null;
        boolean z2 = this.f == null;
        boolean z3 = this.i == null;
        if (z || this.c == null || !a()) {
            return false;
        }
        if (z2) {
            this.f = this.e.getRemoteDevice(this.c);
        } else if (this.f.getAddress() != null && !this.f.getAddress().equalsIgnoreCase(this.c)) {
            this.f = this.e.getRemoteDevice(this.c);
        }
        if (this.f == null) {
            oa.e("CsBtUtil_v11", "mBtDevice == null");
            return false;
        }
        if (!z3) {
            try {
                oa.e("CsBtUtil_v11", "mBtGatt.close()");
                this.i.close();
            } catch (Exception e) {
                if (TextUtils.isEmpty(e.getMessage())) {
                    oa.c("CsBtUtil_v11", e.getMessage());
                }
            }
            this.i = null;
        }
        this.i = this.f.connectGatt(this.j, false, this.n);
        this.l = 14;
        OnBluetoothListener onBluetoothListener = this.h;
        if (onBluetoothListener != null) {
            onBluetoothListener.bluetoothStateChange(14);
        }
        if (this.i != null) {
            oa.e("CsBtUtil_v11", "mBtGatt != null");
            return true;
        }
        oa.e("CsBtUtil_v11", "mBtGatt == null");
        return false;
    }

    public boolean d() {
        int i = this.l;
        return i == 13 || i == 12 || i == 16;
    }

    public void c(String str, Protocal_Type protocal_Type, boolean z) {
        if (this.e == null || str == null || this.m || protocal_Type == Protocal_Type.UNKNOWN || !a() || d()) {
            return;
        }
        oa.e("CsBtUtil_v11", "autoConnect(begin)");
        this.c = str;
        this.k = protocal_Type;
        nm nmVar = this.g;
        if (nmVar != null) {
            nmVar.a();
        }
        if (nn.a() == CONNECT_MODE.Alway_Conn) {
            oa.e("CsBtUtil_v11", "Alway_Conn: " + this.c);
            this.q.removeCallbacks(this.f1659a);
            this.q.postDelayed(this.f1659a, ProfileExtendConstants.TIME_OUT);
            return;
        }
        oa.e("CsBtUtil_v11", "starSeachBindDevice: " + this.c);
        if (z) {
            oa.e("CsBtUtil_v11", "starSeachBindDevice: isScan" + z);
            nl.e().starSeachBindDevice(this.c, new SeachDeviceCallback() { // from class: com.careforeyou.library.utils.CsBtUtil_v11.3
                @Override // com.careforeyou.library.intface.SeachDeviceCallback
                public void success() {
                    oa.e("CsBtUtil_v11", "SeachDeviceCallback::success");
                    nl.e().stopSeachBindDevice();
                    CsBtUtil_v11.this.g();
                }
            });
            return;
        }
        oa.e("CsBtUtil_v11", "starSeachBindDevice: isScan" + z);
        g();
    }

    public void h() {
        Handler handler = this.q;
        if (handler != null) {
            handler.removeCallbacks(this.f1659a);
        }
        if (nn.a() == CONNECT_MODE.FSAC) {
            nl.e().stopSeachBindDevice();
        }
    }

    public void a(OnBluetoothListener onBluetoothListener) {
        this.h = onBluetoothListener;
    }

    public void d(OnWeightScalesListener onWeightScalesListener) {
        this.p = onWeightScalesListener;
        nl.e().setWeightScalesListener(onWeightScalesListener);
    }

    public boolean a() {
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter != null) {
            int state = bluetoothAdapter.getState();
            if (this.e.isEnabled() && state == 12) {
                return true;
            }
        }
        return false;
    }

    public boolean b() {
        OnBluetoothListener onBluetoothListener = this.h;
        if (onBluetoothListener != null) {
            onBluetoothListener.bluetoothStateChange(9);
        }
        nl.e().startSearching();
        return true;
    }

    public void j() {
        nl.e().stopSearching();
    }

    public void c(Synchronization_Task_Key synchronization_Task_Key, AsynBLETaskCallback asynBLETaskCallback) {
        String str;
        BluetoothGattCharacteristic characteristic;
        String str2 = "0000181b-0000-1000-8000-00805f9b34fb";
        boolean z = true;
        if (synchronization_Task_Key == Synchronization_Task_Key.BodyMeasurement) {
            str = "00002a9c-0000-1000-8000-00805f9b34fb";
        } else if (synchronization_Task_Key == Synchronization_Task_Key.BodyHistory) {
            str = "0000fa9c-0000-1000-8000-00805f9b34fb";
        } else if (synchronization_Task_Key == Synchronization_Task_Key.CSNotify) {
            str2 = "0000fff0-0000-1000-8000-00805f9b34fb";
            str = "0000fff1-0000-1000-8000-00805f9b34fb";
            z = false;
        } else {
            if (synchronization_Task_Key == Synchronization_Task_Key.LXUPI) {
                str = "0000a620-0000-1000-8000-00805f9b34fb";
            } else {
                if (synchronization_Task_Key == Synchronization_Task_Key.LXUPN) {
                    str = "0000a621-0000-1000-8000-00805f9b34fb";
                } else if (synchronization_Task_Key == Synchronization_Task_Key.LXDOWNN) {
                    str = "0000a625-0000-1000-8000-00805f9b34fb";
                } else {
                    str = "";
                }
                z = false;
            }
            str2 = "0000a602-0000-1000-8000-00805f9b34fb";
        }
        BluetoothGattService service = this.i.getService(UUID.fromString(str2));
        if (service != null && (characteristic = service.getCharacteristic(UUID.fromString(str))) != null) {
            this.r = asynBLETaskCallback;
            if (by_(characteristic, z)) {
                return;
            }
        }
        AsynBLETaskCallback asynBLETaskCallback2 = this.r;
        if (asynBLETaskCallback2 != null) {
            asynBLETaskCallback2.failed();
        }
    }

    public void c(TaskData taskData, AsynBLETaskCallback asynBLETaskCallback) {
        String str;
        BluetoothGattCharacteristic characteristic;
        String str2 = "00001805-0000-1000-8000-00805f9b34fb";
        if (taskData.e == Synchronization_Task_Key.Time) {
            str = "00002a08-0000-1000-8000-00805f9b34fb";
        } else if (taskData.e == Synchronization_Task_Key.CSDownData) {
            str2 = "0000fff0-0000-1000-8000-00805f9b34fb";
            str = "0000fff2-0000-1000-8000-00805f9b34fb";
        } else {
            str = "";
        }
        BluetoothGattService service = this.i.getService(UUID.fromString(str2));
        if (service != null && (characteristic = service.getCharacteristic(UUID.fromString(str))) != null) {
            this.r = asynBLETaskCallback;
            if (bB_(characteristic, taskData.a(), Down_Instruction_Type.Sync_UserInfo)) {
                return;
            }
        }
        AsynBLETaskCallback asynBLETaskCallback2 = this.r;
        if (asynBLETaskCallback2 != null) {
            asynBLETaskCallback2.failed();
        }
    }

    public boolean bB_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, Down_Instruction_Type down_Instruction_Type) {
        if (this.i == null || this.e == null || bluetoothGattCharacteristic == null) {
            return false;
        }
        bluetoothGattCharacteristic.setValue(bArr);
        bluetoothGattCharacteristic.setWriteType(1);
        return this.i.writeCharacteristic(bluetoothGattCharacteristic);
    }

    /* renamed from: com.careforeyou.library.utils.CsBtUtil_v11$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[Protocal_Type.values().length];
            e = iArr;
            try {
                iArr[Protocal_Type.OKOK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private String a(boolean z) {
        return AnonymousClass2.e[this.k.ordinal()] != 1 ? "" : z ? "0000fff1-0000-1000-8000-00805f9b34fb" : "0000fff2-0000-1000-8000-00805f9b34fb";
    }

    private String n() {
        return AnonymousClass2.e[this.k.ordinal()] != 1 ? "" : "0000fff0-0000-1000-8000-00805f9b34fb";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<BluetoothGattService> list) {
        if (list == null) {
            return;
        }
        if (this.k == Protocal_Type.OKOKCloud || this.k == Protocal_Type.OKOKCloudV3 || this.k == Protocal_Type.OKOKCloudV4) {
            this.g.b(new TaskData(Synchronization_Task_Key.CSNotify, true));
            this.g.b(new TaskData(Synchronization_Task_Key.BodyMeasurement, true));
            this.g.d();
            this.l = 13;
            OnBluetoothListener onBluetoothListener = this.h;
            if (onBluetoothListener != null) {
                onBluetoothListener.bluetoothStateChange(13);
            }
            OnWeightScalesListener onWeightScalesListener = this.p;
            if (onWeightScalesListener != null) {
                onWeightScalesListener.bluetoothWriteChannelDone(null);
            }
            OnBluetoothListener onBluetoothListener2 = this.h;
            if (onBluetoothListener2 != null) {
                onBluetoothListener2.bluetoothStateChange(16);
                return;
            }
            return;
        }
        String n = n();
        for (BluetoothGattService bluetoothGattService : list) {
            if (bluetoothGattService.getUuid().toString().compareToIgnoreCase(n) == 0) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                    int properties = bluetoothGattCharacteristic.getProperties();
                    String uuid = bluetoothGattCharacteristic.getUuid().toString();
                    if (properties > 0 && uuid.compareToIgnoreCase(a(true)) == 0) {
                        this.o = bluetoothGattCharacteristic;
                        bA_(bluetoothGattCharacteristic, true);
                    }
                    if (properties > 0 && uuid.compareToIgnoreCase(a(false)) == 0) {
                        this.t = bluetoothGattCharacteristic;
                        this.l = 13;
                        OnBluetoothListener onBluetoothListener3 = this.h;
                        if (onBluetoothListener3 != null) {
                            onBluetoothListener3.bluetoothStateChange(13);
                        }
                        OnWeightScalesListener onWeightScalesListener2 = this.p;
                        if (onWeightScalesListener2 != null) {
                            onWeightScalesListener2.bluetoothWriteChannelDone(this.t);
                        }
                        OnBluetoothListener onBluetoothListener4 = this.h;
                        if (onBluetoothListener4 != null) {
                            onBluetoothListener4.bluetoothStateChange(16);
                        }
                    }
                }
                return;
            }
        }
    }

    private void bA_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        BluetoothGatt bluetoothGatt;
        if (this.e == null || (bluetoothGatt = this.i) == null) {
            return;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            this.i.writeDescriptor(descriptor);
        }
    }

    private boolean by_(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        BluetoothGatt bluetoothGatt;
        if (this.e != null && (bluetoothGatt = this.i) != null) {
            bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
            if (descriptor != null) {
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                if (z) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                }
                return this.i.writeDescriptor(descriptor);
            }
        }
        return false;
    }

    public void e(byte[] bArr) {
        this.g.b(new TaskData(Synchronization_Task_Key.CSDownData, bArr));
        this.g.d();
    }

    public void i() {
        Calendar calendar = Calendar.getInstance();
        byte[] d = oc.d((short) calendar.get(1));
        this.g.b(new TaskData(Synchronization_Task_Key.Time, new byte[]{d[1], d[0], (byte) (calendar.get(2) + 1), (byte) calendar.get(5), (byte) calendar.get(11), (byte) calendar.get(12), (byte) calendar.get(13)}));
        this.g.d();
    }

    public void f() {
        byte[] bArr = new byte[4];
        oc.b(bArr, (int) (System.currentTimeMillis() / 1000), 0);
        this.g.b(new TaskData(Synchronization_Task_Key.Time, bArr));
        this.g.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bz_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        OnWeightScalesListener onWeightScalesListener;
        byte[] value = bluetoothGattCharacteristic.getValue();
        oa.e("CsBtUtil_v11", "++handleConnectedInfo++" + oc.e(value));
        if (value == null || value.length <= 0) {
            return;
        }
        try {
            iStraightFrame d = ns.d(this.k);
            EnumProcessResult process = d.process(value, bluetoothGattCharacteristic.getUuid().toString());
            if (process == EnumProcessResult.Received_Scale_Data) {
                oa.e("CsBtUtil_v11", "Received_Scale_Data");
                if (this.h != null) {
                    CsFatScale fatScale = ns.d(this.k).getFatScale();
                    if (fatScale.getLockFlag() == 1) {
                        oa.e("CsBtUtil_v11", "fatScale.getLockFlag() == 1");
                        if (fatScale.getWeight() >= 2.0d && fatScale.getWeight() <= 2000.0d) {
                            if (fatScale.getAxunge() > 0.0d) {
                                if (fatScale.ori_visceral_fat == 25600.0d) {
                                    d(2);
                                } else {
                                    d(1);
                                }
                            }
                            if (fatScale.getHeartRateMeasuringType() == 1) {
                                this.l = 6;
                            }
                        }
                        return;
                    }
                    oa.e("CsBtUtil_v11", "fatScale.getLockFlag() == 0");
                    if (fatScale.getWeight() == 0.0d && fatScale.getAxunge() == 0.0d) {
                        this.l = 4;
                    } else if (fatScale.getWeight() > 0.0d && fatScale.getAxunge() == 0.0d) {
                        this.l = 5;
                    }
                    OnBluetoothListener onBluetoothListener = this.h;
                    if (onBluetoothListener != null) {
                        onBluetoothListener.bluetoothStateChange(this.l);
                    }
                    if (fatScale.isHistoryData()) {
                        oa.e("CsBtUtil_v11", "历史数据");
                        try {
                            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-01 00:00:00");
                            if (fatScale.weighingDate != null) {
                                if ((fatScale.weighingDate.getTime() >= parse.getTime() || fatScale.weighingDate.getTime() <= System.currentTimeMillis()) && (onWeightScalesListener = this.p) != null) {
                                    onWeightScalesListener.specialFatScaleInfo(fatScale);
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    if (this.p != null) {
                        oa.e("CsBtUtil_v11", "specialFatScaleInfo：" + fatScale.toString());
                        this.p.specialFatScaleInfo(fatScale);
                        return;
                    }
                    return;
                }
                return;
            }
            if (process == EnumProcessResult.Match_User_Msg && (d instanceof nx)) {
                nx nxVar = (nx) d;
                OnWeightScalesListener onWeightScalesListener2 = this.p;
                if (onWeightScalesListener2 != null) {
                    onWeightScalesListener2.matchUserMsg(nxVar.e());
                }
            }
        } catch (nq e2) {
            e2.printStackTrace();
        }
    }

    public int c(String str) {
        return this.j.getSharedPreferences("chipsea_btLib", 0).getInt("device:" + str, 0);
    }

    private void d(int i) {
        String str = this.c;
        if (str == null || str.length() == 0) {
            return;
        }
        SharedPreferences.Editor edit = this.j.getSharedPreferences("chipsea_btLib", 0).edit();
        edit.putInt("device:" + this.c, i);
        edit.commit();
    }
}
