package health.compact.a;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.BadParcelableException;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.manager.BluetoothMonitor$BluetoothDeviceInfo;
import com.huawei.health.manager.hdp.BloodPressureController;
import com.huawei.health.manager.reconnect.ReconnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cez;
import health.compact.a.BluetoothMonitor;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class BluetoothMonitor {
    private static BluetoothMonitor d;
    private volatile BloodPressureController c;
    private final Object e = new Object();
    private HashMap<String, BluetoothMonitor$BluetoothDeviceInfo> b = new HashMap<>();
    private List<BluetoothMonitor$BluetoothDeviceInfo> f = new ArrayList();
    private String h = null;

    /* renamed from: a, reason: collision with root package name */
    private BluetoothGattCallback f13105a = new BluetoothGattCallback() { // from class: com.huawei.health.manager.BluetoothMonitor$1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            Object obj;
            if (bluetoothGatt == null) {
                LogUtil.a("BluetoothMonitor", "onConnectionStateChange ", "bluetoothGatt is null");
                return;
            }
            String address = bluetoothGatt.getDevice().getAddress();
            LogUtil.c("BluetoothMonitor", "ConnectionStateChange: status =", Integer.valueOf(i), ", newState = ", Integer.valueOf(i2));
            if (i == 0) {
                obj = BluetoothMonitor.this.e;
                synchronized (obj) {
                    BluetoothMonitor.this.akb_(bluetoothGatt, i2, address);
                }
            }
        }
    };
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.health.manager.BluetoothMonitor$2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("BluetoothMonitor", "Received action: ", action);
            if (action == null) {
                return;
            }
            action.hashCode();
            if (action.equals("com.huawei.health.action.DEVICE_CHANGED")) {
                try {
                    int intExtra = intent.getIntExtra("operation", 0);
                    String stringExtra = intent.getStringExtra("productId");
                    String stringExtra2 = intent.getStringExtra("uniqueId");
                    if (intExtra == 1) {
                        BluetoothMonitor.this.a(stringExtra, stringExtra2, intent.getIntExtra(Wpt.MODE, 0), intent.getStringExtra("kind"), intent.getStringExtra("name"));
                    } else if (intExtra == -1) {
                        BluetoothMonitor.this.c(stringExtra, stringExtra2);
                    } else {
                        LogUtil.a("BluetoothMonitor", "unexpected operation");
                    }
                    return;
                } catch (BadParcelableException e) {
                    LogUtil.b("BluetoothMonitor", "BadParcelableException: ", LogAnonymous.b((Throwable) e));
                    return;
                }
            }
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (intExtra2 == 12) {
                    BluetoothMonitor.this.b();
                    ReconnectManager.c().a();
                } else if (intExtra2 == 10) {
                    BluetoothMonitor.this.c();
                    ReconnectManager.c().d();
                } else {
                    LogUtil.a("BluetoothMonitor", "wrong action state");
                }
            }
        }
    };

    private BluetoothMonitor() {
        d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.DEVICE_CHANGED");
        BroadcastManagerUtil.bFA_(BaseApplication.e(), this.j, intentFilter, LocalBroadcast.c, null);
    }

    public static BluetoothMonitor a() {
        BluetoothMonitor bluetoothMonitor;
        synchronized (BluetoothMonitor.class) {
            if (d == null) {
                d = new BluetoothMonitor();
            }
            bluetoothMonitor = d;
        }
        return bluetoothMonitor;
    }

    public boolean b() {
        BluetoothAdapter defaultAdapter;
        BluetoothProfile bluetoothProfile;
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "isStartMonitor");
        try {
            defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        } catch (NullPointerException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "IBluetooth.getState() nullPointerException");
            return false;
        } catch (NoSuchElementException e) {
            com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "isStartMonitor fail ", e.getMessage());
        }
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            synchronized (this.e) {
                com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "", Integer.valueOf(this.b.size()), " device(s) to be monitored");
                for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.b.values()) {
                    if (bluetoothMonitor$BluetoothDeviceInfo != null) {
                        bluetoothProfile = bluetoothMonitor$BluetoothDeviceInfo.h;
                        if (bluetoothProfile == null) {
                            aka_(defaultAdapter, bluetoothMonitor$BluetoothDeviceInfo);
                        }
                    }
                }
            }
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.h("BluetoothMonitor", "bluetoothAdapter is null or is not enable");
        return false;
    }

    public boolean a(String str) {
        BluetoothProfile bluetoothProfile;
        String str2;
        String str3;
        if (str != null) {
            com.huawei.hwlogsmodel.LogUtil.c("BluetoothMonitor", "isStartMonitorByDeviceInfo device info = ", str);
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!defaultAdapter.isEnabled()) {
                return false;
            }
            synchronized (this.e) {
                com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "", Integer.valueOf(this.b.size()), " device(s) to be monitored");
                for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.b.values()) {
                    if (bluetoothMonitor$BluetoothDeviceInfo != null) {
                        bluetoothProfile = bluetoothMonitor$BluetoothDeviceInfo.h;
                        if (bluetoothProfile == null) {
                            str2 = bluetoothMonitor$BluetoothDeviceInfo.c;
                            if (!str.equals(str2)) {
                                str3 = bluetoothMonitor$BluetoothDeviceInfo.f2764a;
                                if (str.equals(str3)) {
                                }
                            }
                            aka_(defaultAdapter, bluetoothMonitor$BluetoothDeviceInfo);
                        }
                    }
                }
            }
            return true;
        }
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "isStartMonitorByDeviceInfo device info = null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void akb_(BluetoothGatt bluetoothGatt, int i, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        Context e = BaseApplication.e();
        if (i == 2) {
            for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.b.values()) {
                str9 = bluetoothMonitor$BluetoothDeviceInfo.c;
                if (str9.equals(str)) {
                    Intent intent = new Intent("com.huawei.health.action.DEVICE_CONNECTED");
                    intent.setPackage(e.getPackageName());
                    str10 = bluetoothMonitor$BluetoothDeviceInfo.e;
                    intent.putExtra("productId", str10);
                    str11 = bluetoothMonitor$BluetoothDeviceInfo.c;
                    intent.putExtra("uniqueId", str11);
                    str12 = bluetoothMonitor$BluetoothDeviceInfo.b;
                    intent.putExtra("kind", str12);
                    str13 = bluetoothMonitor$BluetoothDeviceInfo.e;
                    str14 = bluetoothMonitor$BluetoothDeviceInfo.b;
                    com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "sendBroadcast for ", str13, ", kind = ", str14);
                    e.sendBroadcast(intent, LocalBroadcast.c);
                    return;
                }
            }
            return;
        }
        if (i == 0) {
            String str15 = this.h;
            if (str15 != null && !this.b.containsKey(str15)) {
                this.h = null;
                try {
                    bluetoothGatt.close();
                } catch (SecurityException unused) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("BluetoothMonitor", "handleBluetoothState SecurityException");
                }
            }
            for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo2 : this.b.values()) {
                str2 = bluetoothMonitor$BluetoothDeviceInfo2.c;
                if (str2.equals(str)) {
                    str3 = bluetoothMonitor$BluetoothDeviceInfo2.f2764a;
                    if (str3.equals("AH100")) {
                        Intent intent2 = new Intent("com.huawei.health.action.DEVICE_DISCONNECTED");
                        intent2.setPackage(e.getPackageName());
                        str4 = bluetoothMonitor$BluetoothDeviceInfo2.e;
                        intent2.putExtra("productId", str4);
                        str5 = bluetoothMonitor$BluetoothDeviceInfo2.c;
                        intent2.putExtra("uniqueId", str5);
                        str6 = bluetoothMonitor$BluetoothDeviceInfo2.b;
                        intent2.putExtra("kind", str6);
                        str7 = bluetoothMonitor$BluetoothDeviceInfo2.e;
                        str8 = bluetoothMonitor$BluetoothDeviceInfo2.b;
                        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "sendBroadcast for ", str7, ", kind = ", str8);
                        e.sendBroadcast(intent2, LocalBroadcast.c);
                        return;
                    }
                }
            }
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "onConnectionStateChange ", "invalid status");
    }

    private void aka_(BluetoothAdapter bluetoothAdapter, BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo) {
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        try {
            i = bluetoothMonitor$BluetoothDeviceInfo.d;
            if (i == 1) {
                str6 = bluetoothMonitor$BluetoothDeviceInfo.c;
                if (!BluetoothAdapter.checkBluetoothAddress(str6)) {
                    com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "connectDevice invalid Bluetooth address");
                } else {
                    str7 = bluetoothMonitor$BluetoothDeviceInfo.c;
                    BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(str7);
                    if (remoteDevice != null) {
                        str8 = bluetoothMonitor$BluetoothDeviceInfo.e;
                        com.huawei.hwlogsmodel.LogUtil.c("BluetoothMonitor", "connGatt to #### for ####", str8);
                        bluetoothMonitor$BluetoothDeviceInfo.h = remoteDevice.connectGatt(BaseApplication.e(), true, this.f13105a);
                    }
                }
            } else {
                i2 = bluetoothMonitor$BluetoothDeviceInfo.d;
                if (i2 == 2) {
                    com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "isStartMonitor for classic bluetooth");
                    str = bluetoothMonitor$BluetoothDeviceInfo.c;
                    if (!BluetoothAdapter.checkBluetoothAddress(str)) {
                        com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "connectDevice invalid Bluetooth address");
                    } else {
                        str2 = bluetoothMonitor$BluetoothDeviceInfo.c;
                        BluetoothDevice remoteDevice2 = bluetoothAdapter.getRemoteDevice(str2);
                        str3 = bluetoothMonitor$BluetoothDeviceInfo.e;
                        str4 = bluetoothMonitor$BluetoothDeviceInfo.c;
                        str5 = bluetoothMonitor$BluetoothDeviceInfo.b;
                        akd_(remoteDevice2, str3, str4, str5);
                    }
                } else {
                    com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "connectDevice ", "wrong connectMode");
                }
            }
        } catch (IllegalArgumentException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "connectDevice illegalArgumentException");
        } catch (IllegalStateException unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("BluetoothMonitor", "connectDevice illegalStateException");
        } catch (SecurityException e) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("BluetoothMonitor", "connectDevice SecurityException", LogAnonymous.b((Throwable) e));
        }
    }

    private void akd_(BluetoothDevice bluetoothDevice, String str, String str2, String str3) {
        BloodPressureController bloodPressureController = this.c;
        if (bloodPressureController == null) {
            bloodPressureController = new BloodPressureController(BaseApplication.e());
            this.c = bloodPressureController;
        }
        bloodPressureController.akY_(bluetoothDevice, str, str2, str3);
        bloodPressureController.b();
    }

    private void i() {
        BloodPressureController bloodPressureController = this.c;
        this.c = null;
        if (bloodPressureController != null) {
            bloodPressureController.e();
        }
    }

    public void e(String str) {
        BluetoothProfile bluetoothProfile;
        String str2;
        String str3;
        if (str != null) {
            com.huawei.hwlogsmodel.LogUtil.c("BluetoothMonitor", "stopMonitorByDeviceInfo deviceInfo = ", str);
            synchronized (this.e) {
                for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.b.values()) {
                    if (bluetoothMonitor$BluetoothDeviceInfo != null) {
                        bluetoothProfile = bluetoothMonitor$BluetoothDeviceInfo.h;
                        if (bluetoothProfile != null) {
                            str2 = bluetoothMonitor$BluetoothDeviceInfo.f2764a;
                            if (!str.equals(str2)) {
                                str3 = bluetoothMonitor$BluetoothDeviceInfo.c;
                                if (str.equals(str3)) {
                                }
                            }
                            d(bluetoothMonitor$BluetoothDeviceInfo);
                        }
                    }
                }
            }
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "stopMonitorByDeviceInfo deviceInfo = n");
    }

    public void c() {
        BluetoothProfile bluetoothProfile;
        synchronized (this.e) {
            for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.b.values()) {
                if (bluetoothMonitor$BluetoothDeviceInfo != null) {
                    bluetoothProfile = bluetoothMonitor$BluetoothDeviceInfo.h;
                    if (bluetoothProfile != null) {
                        d(bluetoothMonitor$BluetoothDeviceInfo);
                    }
                }
            }
        }
    }

    private void d(BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo) {
        int i;
        int i2;
        BluetoothProfile bluetoothProfile;
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "disconnectDevice enter");
        i = bluetoothMonitor$BluetoothDeviceInfo.d;
        if (i == 1) {
            bluetoothProfile = bluetoothMonitor$BluetoothDeviceInfo.h;
            BluetoothGatt bluetoothGatt = (BluetoothGatt) bluetoothProfile;
            if (bluetoothGatt != null) {
                com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "BluetoothGatt disconnect and close");
                try {
                    bluetoothGatt.disconnect();
                } catch (SecurityException unused) {
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.c("BluetoothMonitor", "disconnectDevice SecurityException");
                }
            }
            bluetoothMonitor$BluetoothDeviceInfo.h = null;
            return;
        }
        i2 = bluetoothMonitor$BluetoothDeviceInfo.d;
        if (i2 == 2) {
            com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "stopMonitor for classic bluetooth");
            i();
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "disconnectDevice ", "wrong connect mode");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:
    
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x007a, code lost:
    
        r1 = r15.b.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0077, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0060, code lost:
    
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0075, code lost:
    
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0085 A[Catch: all -> 0x0089, TryCatch #3 {all -> 0x0089, blocks: (B:11:0x0045, B:13:0x007a, B:14:0x0080, B:18:0x0077, B:23:0x005d, B:28:0x0072, B:33:0x0085, B:35:0x008d, B:36:0x0090), top: B:4:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d A[Catch: all -> 0x0089, TryCatch #3 {all -> 0x0089, blocks: (B:11:0x0045, B:13:0x007a, B:14:0x0080, B:18:0x0077, B:23:0x005d, B:28:0x0072, B:33:0x0085, B:35:0x008d, B:36:0x0090), top: B:4:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int d() {
        /*
            r15 = this;
            java.lang.Object r0 = r15.e
            monitor-enter(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            android.content.Context r4 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4e java.lang.NumberFormatException -> L63
            java.lang.String r5 = "device.db"
            android.database.sqlite.SQLiteDatabase r4 = r4.openOrCreateDatabase(r5, r2, r3)     // Catch: java.lang.Throwable -> L4b android.database.sqlite.SQLiteException -> L4e java.lang.NumberFormatException -> L63
            r5 = 5
            java.lang.String[] r8 = new java.lang.String[r5]     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r5 = "productId"
            r8[r2] = r5     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r5 = "uniqueId"
            r8[r1] = r5     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r5 = "mode"
            r6 = 2
            r8[r6] = r5     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r5 = "kind"
            r6 = 3
            r8[r6] = r5     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r5 = "name"
            r6 = 4
            r8[r6] = r5     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            java.lang.String r7 = "device"
            java.lang.String r9 = "auto=1"
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r6 = r4
            android.database.Cursor r3 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14)     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            r15.akc_(r3)     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            r3.close()     // Catch: android.database.sqlite.SQLiteException -> L4f java.lang.NumberFormatException -> L64 java.lang.Throwable -> L82
            if (r3 == 0) goto L48
            r3.close()     // Catch: java.lang.Throwable -> L89
        L48:
            if (r4 == 0) goto L7a
            goto L77
        L4b:
            r1 = move-exception
            r4 = r3
            goto L83
        L4e:
            r4 = r3
        L4f:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = "loadDeviceList SQLiteException"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = "BluetoothMonitor"
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)     // Catch: java.lang.Throwable -> L82
            if (r3 == 0) goto L60
            r3.close()     // Catch: java.lang.Throwable -> L89
        L60:
            if (r4 == 0) goto L7a
            goto L77
        L63:
            r4 = r3
        L64:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L82
            java.lang.String r5 = "loadDeviceList NumberFormatException"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L82
            java.lang.String r2 = "BluetoothMonitor"
            com.huawei.hwlogsmodel.LogUtil.h(r2, r1)     // Catch: java.lang.Throwable -> L82
            if (r3 == 0) goto L75
            r3.close()     // Catch: java.lang.Throwable -> L89
        L75:
            if (r4 == 0) goto L7a
        L77:
            r4.close()     // Catch: java.lang.Throwable -> L89
        L7a:
            java.util.HashMap<java.lang.String, com.huawei.health.manager.BluetoothMonitor$BluetoothDeviceInfo> r1 = r15.b     // Catch: java.lang.Throwable -> L89
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L89
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            return r1
        L82:
            r1 = move-exception
        L83:
            if (r3 == 0) goto L8b
            r3.close()     // Catch: java.lang.Throwable -> L89
            goto L8b
        L89:
            r1 = move-exception
            goto L91
        L8b:
            if (r4 == 0) goto L90
            r4.close()     // Catch: java.lang.Throwable -> L89
        L90:
            throw r1     // Catch: java.lang.Throwable -> L89
        L91:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L89
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.BluetoothMonitor.d():int");
    }

    private void akc_(Cursor cursor) {
        String str;
        String str2;
        int i = 0;
        int i2 = 0;
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            String prodId = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(string);
            int a2 = SharedPreferenceManager.a(String.valueOf(10000), cursor.getString(1), 2);
            if (a(prodId, a2, i, i2) && !this.b.containsKey(cursor.getString(1))) {
                i = c(i, prodId, a2);
                i2 = c(i2, prodId, a2);
                BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo = new BluetoothMonitor$BluetoothDeviceInfo();
                bluetoothMonitor$BluetoothDeviceInfo.e = string;
                bluetoothMonitor$BluetoothDeviceInfo.c = cursor.getString(1);
                bluetoothMonitor$BluetoothDeviceInfo.d = Integer.parseInt(cursor.getString(2), 10);
                bluetoothMonitor$BluetoothDeviceInfo.b = cursor.getString(3);
                bluetoothMonitor$BluetoothDeviceInfo.f2764a = cursor.getString(4);
                str = bluetoothMonitor$BluetoothDeviceInfo.f2764a;
                com.huawei.hwlogsmodel.LogUtil.c("BluetoothMonitor", "loadDeviceList() mdi.name = ", str);
                bluetoothMonitor$BluetoothDeviceInfo.h = null;
                HashMap<String, BluetoothMonitor$BluetoothDeviceInfo> hashMap = this.b;
                str2 = bluetoothMonitor$BluetoothDeviceInfo.c;
                hashMap.put(str2, bluetoothMonitor$BluetoothDeviceInfo);
                this.f.add(bluetoothMonitor$BluetoothDeviceInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i, String str3, String str4) {
        if (str == null || str2 == null || str4 == null) {
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("BluetoothMonitor", "Device for ", str, " was added mode = ", Integer.valueOf(i), ",name = ", str4);
        synchronized (this.e) {
            if (!this.b.containsKey(str2)) {
                e(str, ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(str));
                BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo = new BluetoothMonitor$BluetoothDeviceInfo();
                bluetoothMonitor$BluetoothDeviceInfo.e = str;
                bluetoothMonitor$BluetoothDeviceInfo.c = str2;
                bluetoothMonitor$BluetoothDeviceInfo.d = i;
                bluetoothMonitor$BluetoothDeviceInfo.b = str3;
                bluetoothMonitor$BluetoothDeviceInfo.f2764a = str4;
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter.isEnabled()) {
                    aka_(defaultAdapter, bluetoothMonitor$BluetoothDeviceInfo);
                }
                this.b.put(str2, bluetoothMonitor$BluetoothDeviceInfo);
                this.f.add(bluetoothMonitor$BluetoothDeviceInfo);
            }
        }
    }

    private void e(String str, String str2) {
        String str3;
        String str4;
        if (this.b.size() < 2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!cez.ac.contains(str2)) {
            com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "Not second-generation jump rope.");
            return;
        }
        for (BluetoothMonitor$BluetoothDeviceInfo bluetoothMonitor$BluetoothDeviceInfo : this.f) {
            str3 = bluetoothMonitor$BluetoothDeviceInfo.e;
            if (str3.equals(str)) {
                str4 = bluetoothMonitor$BluetoothDeviceInfo.c;
                arrayList.add(str4);
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "device size is ", Integer.valueOf(arrayList.size()));
        if (arrayList.size() < 2) {
            return;
        }
        String str5 = (String) arrayList.get(0);
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "remove device ", CommonUtil.l(str5));
        c(str, str5);
    }

    private boolean a(String str, int i, int i2, int i3) {
        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "isNeedAddSecondRopeMonitorProfile connectMode = ", Integer.valueOf(i));
        if (cez.ac.contains(str) && i == 2) {
            return false;
        }
        return "2G97".equals(str) ? i2 < 2 : !"2G98".equals(str) || i3 < 2;
    }

    private int c(int i, String str, int i2) {
        return (!cez.ac.contains(str) || i2 == 2) ? i : i + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        int i;
        int i2;
        BluetoothProfile bluetoothProfile;
        if (str2 != null) {
            com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "Device for ", str, " was removed");
            synchronized (this.e) {
                this.h = str2;
                BluetoothMonitor$BluetoothDeviceInfo remove = this.b.remove(str2);
                this.f.remove(remove);
                if (remove != null) {
                    i2 = remove.d;
                    if (i2 == 1) {
                        bluetoothProfile = remove.h;
                        if (!(bluetoothProfile instanceof BluetoothGatt)) {
                            com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "removeDevice ", "wrong object type");
                            return;
                        }
                        try {
                            ((BluetoothGatt) bluetoothProfile).disconnect();
                        } catch (SecurityException unused) {
                            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("BluetoothMonitor", "removeDevice SecurityException");
                        }
                        SharedPreferenceManager.d(String.valueOf(10000), str2);
                    }
                }
                if (remove != null) {
                    i = remove.d;
                    if (i == 2) {
                        com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "removeDevice");
                        i();
                    }
                }
                com.huawei.hwlogsmodel.LogUtil.a("BluetoothMonitor", "removeDevice ", "wrong connectMode");
            }
        }
    }

    public void e() {
        BaseApplication.e().unregisterReceiver(this.j);
    }
}
