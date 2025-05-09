package com.huawei.health.manager.hdp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothHealthAppConfiguration;
import android.bluetooth.BluetoothHealthCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.common.Constant;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.manager.util.Consts;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.IoUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes.dex */
public class BloodPressureController {
    private BluetoothHealthAppConfiguration h;
    private BluetoothDevice i;
    private BluetoothHealth j;
    private String k;
    private Context l;
    private String n;
    private String o;
    private static final byte[] c = {-29, 0, 0, 44, 0, 3, 80, 121, 0, 38, Byte.MIN_VALUE, 0, 0, 0, Byte.MIN_VALUE, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 8, 0, 34, 9, 34, 88, 54, 122, -82, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f2788a = {-25, 0, 0, BaseType.Vector64, 0, BaseType.Union, 0, 1, 2, 1, 0, 10, 0, 0, 0, 0, 0, 0, 13, 28, 0, 4, 64, 0, 0, 0};
    private static final byte[] e = {-25, 0, 0, BaseType.Vector64, 0, BaseType.Union, 0, 2, 2, 1, 0, 10, 0, 0, 0, 0, 0, 0, 13, 31, 0, 0};
    private static final byte[] d = {-27, 0, 0, 2, 0, 0};
    private int f = 0;
    private ArrayList<Bundle> b = new ArrayList<>(10);
    private final BluetoothProfile.ServiceListener m = new BluetoothProfile.ServiceListener() { // from class: com.huawei.health.manager.hdp.BloodPressureController.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i != 3 || bluetoothProfile == null) {
                return;
            }
            try {
                LogUtil.c("BloodPressureController", Constant.SERVICE_CONNECT_MESSAGE);
                if (bluetoothProfile instanceof BluetoothHealth) {
                    BloodPressureController.this.j = (BluetoothHealth) bluetoothProfile;
                    BloodPressureController.this.j.registerSinkAppConfiguration("HDP", 4103, BloodPressureController.this.g);
                } else {
                    LogUtil.h("BloodPressureController", "bluetoothProfile is not instanceof BluetoothHealth");
                }
            } catch (SecurityException e2) {
                LogUtil.b("BloodPressureController", "onServiceConnected SecurityException:", ExceptionUtils.d(e2));
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 3) {
                BloodPressureController.this.j = null;
            }
        }
    };
    private final BluetoothHealthCallback g = new BluetoothHealthCallback() { // from class: com.huawei.health.manager.hdp.BloodPressureController.2
        @Override // android.bluetooth.BluetoothHealthCallback
        public void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration bluetoothHealthAppConfiguration, int i) {
            if (i == 1) {
                BloodPressureController.this.h = null;
            } else if (i == 0) {
                LogUtil.c("BloodPressureController", "onHealthAppConfigurationStatusChange");
                BloodPressureController.this.h = bluetoothHealthAppConfiguration;
            } else {
                LogUtil.a("BloodPressureController", "status != APP_CONFIG_REGISTRATION_FAILURE && status != APP_CONFIG_REGISTRATION_SUCCESS");
            }
        }

        @Override // android.bluetooth.BluetoothHealthCallback
        public void onHealthChannelStateChange(BluetoothHealthAppConfiguration bluetoothHealthAppConfiguration, BluetoothDevice bluetoothDevice, int i, int i2, ParcelFileDescriptor parcelFileDescriptor, int i3) {
            if (bluetoothHealthAppConfiguration != null && parcelFileDescriptor != null) {
                if (i == 0 && i2 == 2 && bluetoothHealthAppConfiguration.equals(BloodPressureController.this.h)) {
                    BloodPressureController.this.f = i3;
                    LogUtil.a("BloodPressureController", "onHealthChannelStateChange");
                    BloodPressureController.this.akX_(parcelFileDescriptor);
                    return;
                }
                return;
            }
            LogUtil.h("BloodPressureController", "onHealthChannelStateChange configuration is null");
        }
    };

    public BloodPressureController(Context context) {
        this.l = context;
    }

    public boolean akY_(BluetoothDevice bluetoothDevice, String str, String str2, String str3) {
        if (bluetoothDevice == null && str == null && str3 == null) {
            LogUtil.h("BloodPressureController", "(device == null) && (productId == null) && (kind == null)");
            return false;
        }
        LogUtil.c("BloodPressureController", "isPrepare");
        this.i = bluetoothDevice;
        this.o = str;
        this.n = str2;
        this.k = str3;
        this.b.clear();
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(this.l, this.m, 3);
        try {
            Thread.sleep(500L);
            return true;
        } catch (InterruptedException e2) {
            LogUtil.b("BloodPressureController", "prepare InterruptedException ", e2.getMessage());
            return true;
        }
    }

    public boolean b() {
        BluetoothHealthAppConfiguration bluetoothHealthAppConfiguration;
        LogUtil.c("BloodPressureController", "start");
        BluetoothHealth bluetoothHealth = this.j;
        if (bluetoothHealth == null || (bluetoothHealthAppConfiguration = this.h) == null) {
            return false;
        }
        try {
            return bluetoothHealth.connectChannelToSource(this.i, bluetoothHealthAppConfiguration);
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureController", "start SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    public void e() {
        BluetoothHealth bluetoothHealth;
        LogUtil.c("BloodPressureController", "ending");
        BluetoothDevice bluetoothDevice = this.i;
        if (bluetoothDevice == null || (bluetoothHealth = this.j) == null) {
            return;
        }
        try {
            bluetoothHealth.disconnectChannel(bluetoothDevice, this.h, this.f);
            this.j.unregisterAppConfiguration(this.h);
            this.j = null;
        } catch (SecurityException e2) {
            LogUtil.b("BloodPressureController", "ending SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void akX_(ParcelFileDescriptor parcelFileDescriptor) {
        FileOutputStream fileOutputStream;
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                int i = 1;
                while (fileInputStream2.read(bArr) > -1) {
                    try {
                        byte b = bArr[0];
                        if (b == -30) {
                            LogUtil.c("BloodPressureController", "fileOutputStream.write FIRST_CONFIGURATION_ARRAY");
                            fileOutputStream2.write(c);
                        } else if (b == -25 && bArr[18] == 13 && bArr[19] == 28) {
                            LogUtil.c("BloodPressureController", "fileOutputStream.write(SECOND_CONFIGURATION_ARRAY)");
                            fileOutputStream2.write(f2788a);
                        } else if (b == -25 && bArr[18] == 13 && bArr[19] == 31) {
                            LogUtil.c("BloodPressureController", "receive data");
                            this.b.add(akW_(bArr));
                            i++;
                            byte[] bArr2 = e;
                            bArr2[7] = (byte) i;
                            fileOutputStream2.write(bArr2);
                        } else {
                            if (b == -26) {
                                LogUtil.c("BloodPressureController", "ABORT_APDU");
                                d();
                            } else if (b == -28) {
                                LogUtil.c("BloodPressureController", "fileOutputStream.write(FOURTH_CONFIGURATION_ARRAY)");
                                fileOutputStream2.write(d);
                                d();
                            } else {
                                LogUtil.c("BloodPressureController", "handleHealthChannelStateChange other type");
                            }
                            i = 1;
                        }
                        bArr = new byte[1024];
                        fileOutputStream2.flush();
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileOutputStream2;
                        fileOutputStream = fileInputStream;
                        fileInputStream = fileInputStream2;
                        try {
                            LogUtil.b("BloodPressureController", "HandleHealthChannelStateChange error", e.getMessage());
                            akV_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                            return;
                        } catch (Throwable th) {
                            th = th;
                            akV_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileOutputStream2;
                        fileOutputStream = fileInputStream;
                        fileInputStream = fileInputStream2;
                        akV_(parcelFileDescriptor, fileInputStream, fileOutputStream);
                        throw th;
                    }
                }
                akV_(parcelFileDescriptor, fileInputStream2, fileOutputStream2);
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e4) {
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private void akV_(ParcelFileDescriptor parcelFileDescriptor, FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        if (parcelFileDescriptor != null) {
            IoUtils.e(parcelFileDescriptor);
        }
        if (fileInputStream != null) {
            IoUtils.e(fileInputStream);
        }
        if (fileOutputStream != null) {
            IoUtils.e(fileOutputStream);
        }
    }

    private void d() {
        if (this.l == null || this.b.size() <= 0) {
            return;
        }
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CONNECTED");
        intent.setPackage(this.l.getPackageName());
        intent.putExtra("productId", this.o);
        intent.putExtra("uniqueId", this.n);
        intent.putExtra("kind", this.k);
        intent.putExtra("address", this.i.getAddress());
        intent.putParcelableArrayListExtra("bloodPressureDataList", this.b);
        LogUtil.a("BloodPressureController", "sendBroadcastToConnectDevice productId is ", this.o);
        this.l.sendBroadcast(intent, Consts.f2803a);
    }

    private Bundle akW_(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("BloodPressureController", "getBloodPressureData data is null");
            return null;
        }
        LogUtil.a("BloodPressureController", "getBloodPressureData()");
        short s = (short) (bArr[45] & 255);
        short s2 = (short) (bArr[47] & 255);
        short s3 = (short) (bArr[63] & 255);
        if (s >= Short.MAX_VALUE || s <= 0) {
            LogUtil.h("BloodPressureController", "getBloodPressureData() systolic invalid");
            s = 0;
        }
        if (s2 >= Short.MAX_VALUE || s2 <= 0) {
            LogUtil.h("BloodPressureController", "getBloodPressureData() diastolic invalid");
            s2 = 0;
        }
        if (s3 >= Short.MAX_VALUE || s3 <= 0) {
            LogUtil.h("BloodPressureController", "getBloodPressureData() heartRate invalid");
            s3 = 0;
        }
        Bundle bundle = new Bundle();
        bundle.putShort(BleConstants.BLOODPRESSURE_SYSTOLIC, s);
        bundle.putShort(BleConstants.BLOODPRESSURE_DIASTOLIC, s2);
        bundle.putShort(IndoorEquipManagerApi.KEY_HEART_RATE, s3);
        bundle.putLong("time", e(bArr));
        return bundle;
    }

    private long e(byte[] bArr) {
        Calendar calendar = Calendar.getInstance();
        int d2 = d(bArr[50]);
        calendar.set(d(bArr[51]) + (d2 * 100), d(bArr[52]) - 1, d(bArr[53]), d(bArr[54]), d(bArr[55]), d(bArr[56]));
        long timeInMillis = calendar.getTimeInMillis();
        long j = timeInMillis - 9249352595000L;
        if (j < 1000 && j > -1000) {
            timeInMillis = System.currentTimeMillis();
        }
        LogUtil.c("BloodPressureController", "getTime() time = ", Long.valueOf(timeInMillis));
        return timeInMillis;
    }

    private int d(byte b) {
        try {
            return Integer.parseInt(Integer.toString(b & 255, 16));
        } catch (NumberFormatException e2) {
            LogUtil.b("BloodPressureController", "convertHexadecimalToDecimal exception = ", e2.getMessage());
            return 0;
        }
    }
}
