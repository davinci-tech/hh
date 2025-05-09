package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.DeviceExtra;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.device.open.DeviceProvider;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealth.device.open.IDeviceEventHandler;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class ceo {
    private static final String b = SecurityConstant.d;
    private static ceo d;
    private DeviceProvider c;
    private boolean e = false;

    private boolean a(int i) {
        return i == 2 || i == 1;
    }

    private ceo() {
    }

    public static ceo d() {
        ceo ceoVar;
        synchronized (ceo.class) {
            if (d == null) {
                d = new ceo();
            }
            ceoVar = d;
        }
        return ceoVar;
    }

    public void c(String str, String str2) {
        MeasurableDevice c = d().c(str);
        if (c == null) {
            return;
        }
        LogUtil.a("DeviceManager", "sendBroadcastToBluetoothMonitor", " current device is ", c.getDeviceName());
        MeasureKit e = cfl.a().e(c.getMeasureKitUuid());
        if (e == null || e.getBackgroundController() == null || !c.isAutoDevice()) {
            return;
        }
        LogUtil.a("DeviceManager", "sendBroadcastToBluetoothMonitor", " action ", str2);
        Intent intent = new Intent(str2);
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("productId", str);
        cpp.a().sendBroadcast(intent, b);
    }

    public void c(String str, String str2, String str3) {
        MeasurableDevice d2 = d().d(str2, false);
        if (d2 == null) {
            return;
        }
        LogUtil.a("DeviceManager", "sendBroadcastToBluetoothMonitor device is ", d2.getDeviceName());
        MeasureKit e = cfl.a().e(d2.getMeasureKitUuid());
        if (e == null || e.getBackgroundController() == null || !d2.isAutoDevice()) {
            return;
        }
        LogUtil.a("DeviceManager", "sendBroadCastToBluetoothMonitor action ", str3);
        Intent intent = new Intent(str3);
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("productId", str);
        intent.putExtra("uniqueId", str2);
        cpp.a().sendBroadcast(intent, b);
    }

    public void c() {
        LogUtil.a("DeviceManager", "enter deleteCloudFromDeviceDB");
        LogUtil.a("DeviceManager", "deleteCloudFromDeviceDB affectedRows:", Integer.valueOf(cen.b().e("device", "addTime = ?", new String[]{"0"})));
    }

    public ArrayList<String> i() {
        return ctq.a();
    }

    public ArrayList<ctk> a() {
        return ctq.e();
    }

    public String d(String str, String str2) {
        MeasurableDevice c = c(str);
        if (c == null) {
            LogUtil.h("DeviceManager", "scaleUpdateJump bondedDevice is null");
            return null;
        }
        if (!(c instanceof ctk)) {
            return str2;
        }
        ctk ctkVar = (ctk) c;
        LogUtil.a("DeviceManager", "is device");
        return TextUtils.isEmpty(str2) ? ctkVar.getUniqueId() : str2;
    }

    public boolean b(String str, boolean z) {
        ctk ctkVar;
        MeasurableDevice e = e(str, z);
        if (e instanceof ctk) {
            LogUtil.a("DeviceManager", "is device");
            ctkVar = (ctk) e;
        } else {
            ctkVar = null;
        }
        return ctkVar != null;
    }

    public MeasurableDevice c(String str) {
        return e(str, false);
    }

    public MeasurableDevice e(String str, boolean z) {
        ArrayList<ContentValues> c = c("productId = ?", new String[]{str}, null, null, "addTime DESC", "1");
        if (c.size() == 0) {
            return null;
        }
        LogUtil.a("DeviceManager", "get bonded device by product id , device record num ", Integer.valueOf(c.size()));
        return DW_(c.get(c.size() - 1), z);
    }

    public ArrayList<MeasurableDevice> a(String str, boolean z) {
        ArrayList<ContentValues> c = c("productId = ?", new String[]{str}, null, null, null, null);
        LogUtil.a("DeviceManager", "get bonded devices by productId , device record num = ", Integer.valueOf(c.size()));
        ArrayList<MeasurableDevice> arrayList = new ArrayList<>();
        Iterator<ContentValues> it = c.iterator();
        while (it.hasNext()) {
            arrayList.add(DW_(it.next(), z));
        }
        return arrayList;
    }

    public MeasurableDevice e(String str, String str2) {
        ArrayList<ContentValues> c = c("productId = ?", new String[]{str}, null, null, "addTime DESC", "1");
        if (c.size() == 0) {
            return null;
        }
        LogUtil.a("DeviceManager", "get bonded device by product id , device record num ", Integer.valueOf(c.size()));
        ContentValues DY_ = DY_(c, str2);
        if (DY_ == null) {
            LogUtil.a("DeviceManager", "no this deviceName MeasurableDevice");
            return null;
        }
        return DW_(DY_, false);
    }

    private ContentValues DY_(ArrayList<ContentValues> arrayList, String str) {
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (TextUtils.equals(str, next.getAsString("name"))) {
                return next;
            }
        }
        return null;
    }

    private String g(String str, String str2) {
        return str + "#" + str2.substring(str2.length() - 3, str2.length());
    }

    private MeasurableDevice DX_(ContentValues contentValues) {
        MeasurableDevice ctkVar;
        if (contentValues == null) {
            LogUtil.h("DeviceManager", "createMeasureDevice device info is null");
            return null;
        }
        LogUtil.c("DeviceManager", "createMeasureDevice device info", contentValues.toString());
        if (contentValues.getAsInteger(Wpt.MODE) == null) {
            LogUtil.h("DeviceManager", "createMeasureDevice unknow device connect mode");
            return null;
        }
        try {
            int intValue = contentValues.getAsInteger(Wpt.MODE).intValue();
            String asString = contentValues.getAsString("uniqueId");
            String asString2 = contentValues.getAsString("kitUuid");
            String asString3 = contentValues.getAsString("productId");
            if (intValue == 1) {
                ctkVar = h(asString, asString2);
            } else if (intValue == 2) {
                ctkVar = ceu.Ej_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(asString));
            } else if (intValue == 4) {
                ctkVar = new cfa();
            } else {
                if (intValue == 16) {
                    return new cfb(contentValues.getAsString("name"), asString, 1);
                }
                if (intValue == 8) {
                    ctkVar = new ctk(asString3);
                } else {
                    if (i(asString3)) {
                        return new cex(contentValues.getAsString("name"), contentValues.getAsString("productId"), contentValues.getAsString("uniqueId"), contentValues.getAsString("sn"));
                    }
                    LogUtil.c("DeviceManager", "createMeasureDevice other connect mode = ", Integer.valueOf(intValue));
                    return null;
                }
            }
            return ctkVar;
        } catch (SecurityException unused) {
            LogUtil.b("DeviceManager", "createBlueToothDevice securityException");
            return null;
        }
    }

    private MeasurableDevice h(String str, String str2) {
        MeasurableDevice Ra_;
        MeasurableDevice measurableDevice = null;
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (str2 != null && str2.equals("6d783cfa-eec8-4a62-a6fa-b686fdf02d24")) {
                Ra_ = cey.Eq_(remoteDevice);
            } else {
                Ra_ = cxh.Ra_(remoteDevice);
            }
            measurableDevice = Ra_;
            if (measurableDevice != null) {
                LogUtil.c("DeviceManager", "createMeasureDevice device:", measurableDevice.getAddress(), "; deviceName:", measurableDevice.getDeviceName());
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceManager", "can not create bluetooth device, illegal mac address");
        }
        return measurableDevice;
    }

    private MeasurableDevice DV_(MeasurableDevice measurableDevice, ContentValues contentValues) {
        try {
            String asString = contentValues.getAsString("uniqueId");
            LogUtil.a("DeviceManager", "convertWifiDeviceToBleDevice id:", cpw.d(asString), ", length:", Integer.valueOf(asString.length()));
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(asString);
            if (remoteDevice == null) {
                return measurableDevice;
            }
            LogUtil.a("DeviceManager", "convertWifiDeviceToBleDevice bluetoothDevice id:", cpw.d(remoteDevice.getAddress()), ",length:", Integer.valueOf(remoteDevice.getAddress().length()));
            cxh Ra_ = cxh.Ra_(remoteDevice);
            LogUtil.c("DeviceManager", "setDeviceOtherInfo bleDevice:", cpw.d(Ra_.getAddress()), "; deviceName:", Ra_.getDeviceName());
            Ra_.setAutoDevice(measurableDevice.isAutoDevice());
            Ra_.setDeviceName(measurableDevice.getDeviceName());
            Ra_.setMeasureKitUuid(measurableDevice.getMeasureKitUuid());
            Ra_.setKind(measurableDevice.getKind());
            Ra_.setProductId(measurableDevice.getProductId());
            Ra_.setSerialNumber(contentValues.getAsString("sn"));
            return Ra_;
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceManager", "convertWifiDeviceToBleDevice illegal mac address");
            return measurableDevice;
        }
    }

    private void Eb_(ctk ctkVar, ContentValues contentValues) {
        ctk h = ctq.h(ctkVar.getSerialNumber());
        if (h != null) {
            LogUtil.a("DeviceManager", "setDeviceOtherInfo mDeviceID", cpw.d(h.b().a()));
            ctkVar.a(h.c());
            ctkVar.b(h.b());
        }
        ctkVar.a(contentValues.getAsString("uniqueId"));
    }

    public void b(String str, String str2, String str3) {
        HealthDevice b2 = d().b(str, str2);
        Intent intent = new Intent(str3);
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("productId", str);
        intent.putExtra("uniqueId", str2);
        Ea_(intent, b2, "sendBroadcastToBluetoothMonitorUniversal", str, str3);
    }

    private void Ea_(Intent intent, HealthDevice healthDevice, String str, String str2, String str3) {
        com.huawei.hihealth.device.open.MeasureKit c;
        if (healthDevice == null) {
            return;
        }
        LogUtil.a("DeviceManager", str, "DeviceManager current device is ", healthDevice.getDeviceName());
        cfl a2 = cfl.a();
        LogUtil.a("DeviceManager", str, "DeviceManager kitUuid:", healthDevice.getUniqueId());
        dcz d2 = ResourceManager.e().d(str2);
        if (d2 == null || (c = a2.c(d2.s())) == null || c.getBackgroundController() == null || cpa.z(str2)) {
            return;
        }
        LogUtil.a("DeviceManager", str, "DeviceManager sendBroadCastToBluetoothMonitor action ", str3);
        cpp.a().sendBroadcast(intent, b);
    }

    public HealthDevice d(String str) {
        ArrayList<ContentValues> c = c("productId = ?", new String[]{str}, null, null, "addTime DESC", "1");
        if (c.size() == 0) {
            return null;
        }
        ContentValues contentValues = c.get(c.size() - 1);
        String asString = contentValues.getAsString("name");
        String asString2 = contentValues.getAsString("uniqueId");
        if (TextUtils.isEmpty(contentValues.getAsString("kitUuid"))) {
            return null;
        }
        return i(asString, asString2);
    }

    public HealthDevice b(String str, String str2) {
        ArrayList<ContentValues> c = c("uniqueId = ?", new String[]{str2}, null, null, "addTime DESC", "1");
        if (c.size() == 0) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() < 3) {
                return null;
            }
            return d(g(str, str2));
        }
        ContentValues contentValues = c.get(c.size() - 1);
        String asString = contentValues.getAsString("name");
        if (TextUtils.isEmpty(contentValues.getAsString("kitUuid"))) {
            return null;
        }
        return i(asString, str2);
    }

    private HealthDevice i(final String str, final String str2) {
        return new HealthDevice() { // from class: ceo.4
            @Override // com.huawei.hihealth.device.open.HealthDevice
            public boolean doCreatePair(IDeviceEventHandler iDeviceEventHandler) {
                return false;
            }

            @Override // com.huawei.hihealth.device.open.HealthDevice
            public boolean doRemovePair() {
                return false;
            }

            @Override // com.huawei.hihealth.device.open.HealthDevice
            public String getDeviceName() {
                return str;
            }

            @Override // com.huawei.hihealth.device.open.HealthDevice
            public String getAddress() {
                return str2;
            }

            @Override // com.huawei.hihealth.device.open.HealthDevice
            public String getUniqueId() {
                return str2;
            }
        };
    }

    public ArrayList<String> c(HealthDevice.HealthDeviceKind healthDeviceKind) {
        ArrayList<String> arrayList = new ArrayList<>(10);
        if (healthDeviceKind == null) {
            LogUtil.h("DeviceManager", "getBondedProducts device kind is null");
            return arrayList;
        }
        String name = healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_UNKNOWN ? null : healthDeviceKind.name();
        String[] strArr = name == null ? null : new String[]{name};
        Iterator<ContentValues> it = c(strArr != null ? "kind = ?" : null, strArr, null, null, EventMonitorRecord.ADD_TIME, null).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAsString("productId"));
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a0, code lost:
    
        if (r6 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ba, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b7, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b5, code lost:
    
        if (0 == 0) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<java.lang.String> a(com.huawei.health.device.model.HealthDevice.HealthDeviceKind r17) {
        /*
            r16 = this;
            java.lang.String r1 = "DeviceManager"
            java.util.ArrayList r2 = new java.util.ArrayList
            r0 = 10
            r2.<init>(r0)
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = 0
            cen r7 = defpackage.cen.b()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String[] r9 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r0 = "productId"
            r9[r5] = r0     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r0 = "kitUuid"
            r9[r4] = r0     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            com.huawei.health.device.model.HealthDevice$HealthDeviceKind r0 = com.huawei.health.device.model.HealthDevice.HealthDeviceKind.HDK_UNKNOWN     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r8 = r17
            if (r8 != r0) goto L23
            r10 = r6
            goto L3b
        L23:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r10 = "kind = '"
            r0.<init>(r10)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r8 = r17.name()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r0.append(r8)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r8 = "'"
            r0.append(r8)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r10 = r0
        L3b:
            java.lang.String r8 = "device"
            r11 = 0
            r12 = 0
            r13 = 0
            java.lang.String r14 = "addTime"
            r15 = 0
            android.database.Cursor r6 = r7.DT_(r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            if (r6 != 0) goto L4f
            if (r6 == 0) goto L4e
            r6.close()
        L4e:
            return r2
        L4f:
            boolean r0 = r6.moveToNext()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            if (r0 == 0) goto La0
            java.lang.String r0 = r6.getString(r5)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r7 = r6.getString(r4)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r8 = 4
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r9 = "getBondedProductsForDeviceOnly() productId"
            r8[r5] = r9     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r8[r4] = r0     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r9 = ", kitUid="
            r8[r3] = r9     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r9 = 3
            r8[r9] = r7     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            if (r8 == 0) goto L77
            goto L4f
        L77:
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.NumberFormatException -> L7e java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            if (r7 != 0) goto L87
            goto L4f
        L7e:
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r8 = "getBondedProductsForDeviceOnly() Exception "
            r7[r5] = r8     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
        L87:
            if (r0 == 0) goto L4f
            java.lang.String r7 = r0.trim()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            int r7 = r7.length()     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            if (r7 <= 0) goto L4f
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            java.lang.String r8 = "getBondedProductsForDeviceOnly() add "
            r7[r5] = r8     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            com.huawei.hwlogsmodel.LogUtil.a(r1, r7)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            r2.add(r0)     // Catch: java.lang.Throwable -> La3 android.database.sqlite.SQLiteException -> La5
            goto L4f
        La0:
            if (r6 == 0) goto Lba
            goto Lb7
        La3:
            r0 = move-exception
            goto Lbb
        La5:
            r0 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La3
            java.lang.String r7 = "getBondedProductsForDeviceOnly"
            r3[r5] = r7     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> La3
            r3[r4] = r0     // Catch: java.lang.Throwable -> La3
            com.huawei.hwlogsmodel.LogUtil.c(r1, r3)     // Catch: java.lang.Throwable -> La3
            if (r6 == 0) goto Lba
        Lb7:
            r6.close()
        Lba:
            return r2
        Lbb:
            if (r6 == 0) goto Lc0
            r6.close()
        Lc0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ceo.a(com.huawei.health.device.model.HealthDevice$HealthDeviceKind):java.util.ArrayList");
    }

    public void b() {
        String[] strArr = (String[]) msr.c.keySet().toArray(new String[0]);
        StringBuffer stringBuffer = new StringBuffer(10);
        for (int i = 0; i < strArr.length; i++) {
            if (i == strArr.length - 1) {
                stringBuffer.append("kind = ?");
            } else {
                stringBuffer.append("kind = ? OR ");
            }
        }
        int e = cen.b().e("device", stringBuffer.toString(), strArr);
        LogUtil.c("DeviceManager", "deleteBondedProductsForDeviceOnly deleteNum:", Integer.valueOf(e));
        LogUtil.a("DeviceManager", "deleteBondedProductsForDeviceOnly deleteNum:", Integer.valueOf(cen.b().e("device", "kind = ? and kitUuid != ? ", new String[]{"HDK_HEART_RATE", "0"})));
        if (e > 0) {
            ctq.b();
        }
    }

    public boolean e(String str, String str2, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        if (measurableDevice == null || TextUtils.isEmpty(measurableDevice.getUniqueId()) || measurableDevice.getUniqueId().trim().length() <= 0) {
            LogUtil.h("DeviceManager", "bindDevice device is null or device uniqueId is empty");
            if (iDeviceEventHandler == null) {
                return false;
            }
            iDeviceEventHandler.onStateChanged(-1);
            return false;
        }
        ReleaseLogUtil.e("R_DeviceManager", "DeviceManager bindDevice");
        if (cfl.a().e(str2) == null) {
            return a(str, measurableDevice, iDeviceEventHandler);
        }
        if (!cpa.ab(str)) {
            return c(str, str2, measurableDevice, iDeviceEventHandler);
        }
        LogUtil.c("DeviceManager", "bindDevice doCreateBond result :", Boolean.valueOf(measurableDevice.doCreateBond(iDeviceEventHandler)));
        return true;
    }

    private boolean a(String str, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 != null && d2.r() != null && "H5".equals(d2.r())) {
            a(str, measurableDevice, "db_device");
            return b(str, c(d2, measurableDevice), measurableDevice, iDeviceEventHandler, measurableDevice.getSerialNumber());
        }
        LogUtil.h("DeviceManager", "deviceKit is null, and is not measure on h5, do nothing");
        if (iDeviceEventHandler == null) {
            return false;
        }
        iDeviceEventHandler.onStateChanged(-1);
        return false;
    }

    public void a(String str, MeasurableDevice measurableDevice, String str2) {
        String c;
        if (TextUtils.isEmpty(measurableDevice.getDeviceName())) {
            if (str2.equals("db_device")) {
                c = dks.a(str);
            } else {
                c = dks.c(str, "en");
            }
            measurableDevice.setDeviceName(c + Constants.LINK + measurableDevice.getUniqueId().replace(":", "").substring(r0.length() - 3));
            LogUtil.c("DeviceManager", "dbType:", str2, " , ", "name", ":", measurableDevice.getDeviceName());
        }
    }

    private String c(dcz dczVar, MeasurableDevice measurableDevice) {
        if (TextUtils.isEmpty(measurableDevice.getMeasureKitUuid())) {
            String s = dczVar.s();
            measurableDevice.setMeasureKitUuid(s);
            LogUtil.c("DeviceManager", "资源包 kitUuid:", s);
        }
        return measurableDevice.getMeasureKitUuid();
    }

    public boolean c(String str, String str2, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        return b(str, str2, measurableDevice, iDeviceEventHandler, "");
    }

    public boolean b(String str, String str2, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler, String str3) {
        if (!b(str, measurableDevice)) {
            d(iDeviceEventHandler);
            return false;
        }
        return Ee_(str, measurableDevice, iDeviceEventHandler, Ec_(str, str2, measurableDevice, str3), str3);
    }

    public boolean Ee_(String str, MeasurableDevice measurableDevice, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler, ContentValues contentValues, String str2) {
        if (cen.b().DQ_("device", contentValues) == -1) {
            LogUtil.h("DeviceManager", "fail to insert device data base");
            d(iDeviceEventHandler);
            return false;
        }
        a(str, measurableDevice, "db_hihealth_device");
        d(str, measurableDevice, str2);
        a(str, measurableDevice.getUniqueId(), measurableDevice);
        measurableDevice.doCreateBond(iDeviceEventHandler);
        if (this.e && did.c().d(str)) {
            LogUtil.a("DeviceManager", "SupportAutoConnect");
            if (did.c().c(measurableDevice.getUniqueId())) {
                DZ_(contentValues);
            }
            return true;
        }
        if (!dfe.a().e()) {
            g();
            if (this.e) {
                DZ_(contentValues);
            }
        }
        return true;
    }

    public void a(String str, String str2, MeasurableDevice measurableDevice) {
        a(str, str2, "", measurableDevice);
    }

    public void a(final String str, String str2, String str3, final MeasurableDevice measurableDevice) {
        LogUtil.a("DeviceManager", "enter uploadDeviceToCloud");
        ProductMapParseUtil.b(cpp.a());
        final cpo cpoVar = new cpo();
        cpoVar.e(str, str2, str3);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ceo.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DeviceManager", "enter get wiseDeviceId");
                ceo.this.c(str, measurableDevice, cpoVar);
            }
        });
    }

    public ContentValues Ec_(String str, String str2, MeasurableDevice measurableDevice, String str3) {
        dcz d2 = ResourceManager.e().d(str);
        MeasureKit e = cfl.a().e(str2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("kind", b(d2, e));
        contentValues.put(Wpt.MODE, Integer.valueOf(e(d2, e)));
        boolean c = c(d2, e);
        this.e = c;
        contentValues.put("auto", Integer.valueOf(c ? 1 : 0));
        contentValues.put("uniqueId", measurableDevice.getUniqueId());
        contentValues.put("sn", str3);
        String deviceName = measurableDevice.getDeviceName();
        if (TextUtils.isEmpty(deviceName) && d2 != null && d2.n() != null) {
            deviceName = dks.e(str, d2.n().b());
            LogUtil.h("DeviceManager", "get deviceName from ResourceManager deviceName = ", deviceName);
        }
        String str4 = deviceName;
        contentValues.put("name", str4);
        contentValues.put("productId", str);
        contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(System.currentTimeMillis()));
        contentValues.put("kitUuid", str2);
        LogUtil.a("DeviceManager", "saveDevice deviceInfo : deviceKind is ", b(d2, e), ", deviceMode is ", Integer.valueOf(e(d2, e)), ", auto is ", Integer.valueOf(this.e ? 1 : 0), ", uniqueId is ", CommonUtil.l(measurableDevice.getUniqueId()), ", serialNumber is ", CommonUtil.l(str3), ", productId is", CommonUtil.l(str), ", kitUuid is ", str2, ", deviceName is ", str4);
        return contentValues;
    }

    private void d(com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        if (iDeviceEventHandler != null) {
            iDeviceEventHandler.onStateChanged(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, MeasurableDevice measurableDevice, cpo cpoVar) {
        String c = cpoVar.c(str, measurableDevice.getUniqueId());
        ContentValues contentValues = new ContentValues();
        contentValues.put("mDeviceId", c);
        if (cen.b().DU_("device", contentValues, "uniqueId= ?", new String[]{measurableDevice.getUniqueId()}) == -1) {
            LogUtil.h("DeviceManager", "fail to udpate wisedeviceid");
        }
    }

    public void j(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KnitFragment.KEY_EXTRA, str2);
        if (cen.b().DU_("device", contentValues, "uniqueId= ?", new String[]{str}) == -1) {
            LogUtil.h("DeviceManager", "fail to update extra");
        }
    }

    public String a(String str, String str2, String str3, String str4) {
        MeasurableDevice d2 = d().d(str4, false);
        DeviceExtra deviceExtra = (d2 == null || TextUtils.isEmpty(d2.getExtra())) ? null : (DeviceExtra) HiJsonUtil.e(d2.getExtra(), DeviceExtra.class);
        if (deviceExtra == null) {
            deviceExtra = new DeviceExtra();
        }
        if (TextUtils.isEmpty(str)) {
            deviceExtra.getFittings().add(new DeviceExtra.DeviceData(null, str2, str3));
        } else {
            deviceExtra.getAttached().add(new DeviceExtra.DeviceData(str, str2, str3));
        }
        return HiJsonUtil.e(deviceExtra);
    }

    public boolean e(String str, String str2, com.huawei.hihealth.device.open.HealthDevice healthDevice) {
        String uniqueId = healthDevice.getUniqueId();
        com.huawei.hihealth.device.open.MeasureKit c = cfl.a().c(str2);
        dcz d2 = ResourceManager.e().d(str);
        boolean z = d2 != null && "10".equals(d2.p());
        if (c != null && uniqueId != null && uniqueId.trim().length() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", str);
            contentValues.put("name", healthDevice.getDeviceName());
            contentValues.put("uniqueId", uniqueId);
            contentValues.put(Wpt.MODE, HealthZonePushReceiver.PRESSURE_NOTIFY);
            contentValues.put("kind", c.getHealthDataKind().name());
            contentValues.put("kitUuid", str2);
            contentValues.put("auto", Integer.valueOf((c.getBackgroundController() == null || z) ? 0 : 1));
            contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(new Date().getTime()));
            long DQ_ = cen.b().DQ_("device", contentValues);
            c(str, healthDevice);
            if (!dfe.a().e() && DQ_ != -1) {
                if (c.getBackgroundController() != null && !z) {
                    LogUtil.c("DeviceManager", "saveDeviceInfo auto test background measure");
                    Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
                    intent.setPackage(cpp.a().getPackageName());
                    intent.putExtra("operation", 1);
                    intent.putExtra("uniqueId", uniqueId);
                    intent.putExtra("productId", str);
                    intent.putExtra(Wpt.MODE, 32);
                    intent.putExtra("kind", c.getHealthDataKind().name());
                    intent.putExtra("name", healthDevice.getDeviceName());
                    LogUtil.a("DeviceManager", "saveDeviceInfo deviceName = ", healthDevice.getDeviceName());
                    cpp.a().sendBroadcast(intent, LocalBroadcast.c);
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<ContentValues> f() {
        return c(null, null, null, null, null, null);
    }

    public MeasurableDevice c(String str, boolean z) {
        LogUtil.a("DeviceManager", "getBondedDeviceBySerialNumber ", cpw.d(str));
        ArrayList<ContentValues> c = c("sn = ?", new String[]{str}, null, null, null, null);
        if (koq.b(c)) {
            LogUtil.h("DeviceManager", "getBondedDeviceByUniqueId not device for uniqueId ", cpw.d(str));
            return null;
        }
        return DW_(c.get(c.size() - 1), z);
    }

    public MeasurableDevice d(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "getBondedDeviceByUniqueId uniqueId is null ");
            return null;
        }
        ArrayList<ContentValues> c = c("uniqueId = ?", new String[]{str}, null, null, null, null);
        if (koq.b(c)) {
            LogUtil.h("DeviceManager", "getBondedDeviceByUniqueId no device for uniqueId ", cpw.d(str));
            return null;
        }
        LogUtil.a("DeviceManager", "get bonded device by unique id:", cpw.d(str));
        return DW_(c.get(c.size() - 1), z);
    }

    public boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "isBondedDevice uniqueId is null ");
            return false;
        }
        if (!koq.b(c("uniqueId = ?", new String[]{str}, null, null, null, null))) {
            return true;
        }
        LogUtil.h("DeviceManager", "isBondedDevice no device for uniqueId ", cpw.d(str));
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0099, code lost:
    
        if (r6 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00bb, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b8, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009e, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b6, code lost:
    
        if (r6 == null) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.ArrayList<android.content.ContentValues> c(java.lang.String r17, java.lang.String[] r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22) {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r0 = 10
            r1.<init>(r0)
            r2 = 2
            r3 = 13
            r4 = 1
            r5 = 0
            r6 = 0
            java.lang.String[] r15 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "productId"
            r15[r5] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "name"
            r15[r4] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "uniqueId"
            r15[r2] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "mode"
            r8 = 3
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "kind"
            r8 = 4
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "kitUuid"
            r8 = 5
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "auto"
            r8 = 6
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "sn"
            r8 = 7
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "displayName"
            r8 = 8
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "addTime"
            r8 = 9
            r15[r8] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r7 = "mDeviceId"
            r15[r0] = r7     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r0 = "showInList"
            r7 = 11
            r15[r7] = r0     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r0 = "extra"
            r7 = 12
            r15[r7] = r0     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            cen r7 = defpackage.cen.b()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            java.lang.String r8 = "device"
            r9 = r15
            r10 = r17
            r11 = r18
            r12 = r19
            r13 = r20
            r14 = r21
            r0 = r15
            r15 = r22
            android.database.Cursor r6 = r7.DT_(r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            if (r6 == 0) goto L9c
            boolean r7 = r6.isClosed()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            if (r7 == 0) goto L74
            goto L9c
        L74:
            boolean r7 = r6.moveToNext()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            if (r7 == 0) goto L99
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            r7.<init>()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            r8 = r5
        L80:
            if (r8 >= r3) goto L95
            r9 = r0[r8]     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            int r10 = r6.getColumnIndex(r9)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            r11 = -1
            if (r10 == r11) goto L92
            java.lang.String r10 = r6.getString(r10)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            r7.put(r9, r10)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
        L92:
            int r8 = r8 + 1
            goto L80
        L95:
            r1.add(r7)     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La4
            goto L74
        L99:
            if (r6 == 0) goto Lbb
            goto Lb8
        L9c:
            if (r6 == 0) goto La1
            r6.close()
        La1:
            return r1
        La2:
            r0 = move-exception
            goto Lbc
        La4:
            r0 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La2
            java.lang.String r3 = "getBondedDevice"
            r2[r5] = r3     // Catch: java.lang.Throwable -> La2
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> La2
            r2[r4] = r0     // Catch: java.lang.Throwable -> La2
            java.lang.String r0 = "DeviceManager"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)     // Catch: java.lang.Throwable -> La2
            if (r6 == 0) goto Lbb
        Lb8:
            r6.close()
        Lbb:
            return r1
        Lbc:
            if (r6 == 0) goto Lc1
            r6.close()
        Lc1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ceo.c(java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.util.ArrayList");
    }

    public ContentValues Ed_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "getDeviceInfoByUniqueId uniqueId is null ");
            return null;
        }
        ArrayList<ContentValues> c = c("uniqueId = ?", new String[]{str}, null, null, null, null);
        if (koq.b(c)) {
            LogUtil.h("DeviceManager", "getDeviceInfoByUniqueId no device for uniqueId ", cpw.d(str));
            return null;
        }
        return c.get(0);
    }

    public ArrayList<ContentValues> d(HealthDevice.HealthDeviceKind healthDeviceKind) {
        ArrayList<ContentValues> arrayList = new ArrayList<>(10);
        if (healthDeviceKind == null) {
            LogUtil.h("DeviceManager", "getBondedProducts device kind is null");
            return arrayList;
        }
        return c("kind= ?", new String[]{healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_UNKNOWN ? null : healthDeviceKind.name()}, null, null, "addTime DESC", null);
    }

    public ArrayList<ContentValues> a(String str) {
        ArrayList<ContentValues> arrayList = new ArrayList<>(10);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "getBondedDeviceByProductId device productId is null");
            return arrayList;
        }
        return c("productId= ?", new String[]{str}, null, null, "addTime DESC", null);
    }

    public boolean a(String str, String str2, ctk ctkVar, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        if (ctkVar == null) {
            LogUtil.h("DeviceManager", "bindWiFiDevice device is null");
            return false;
        }
        LogUtil.a("DeviceManager", "bindWiFiDevice:", cpw.d(ctkVar.b().a()));
        String uniqueId = ctkVar.getUniqueId();
        if (!TextUtils.isEmpty(uniqueId) && uniqueId.trim().length() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", str);
            contentValues.put("name", ctkVar.getDeviceName());
            contentValues.put("uniqueId", uniqueId);
            contentValues.put(Wpt.MODE, "8");
            contentValues.put("kind", String.valueOf(ctkVar.getKind()));
            contentValues.put("kitUuid", str2);
            contentValues.put("auto", (Integer) 1);
            contentValues.put("sn", ctkVar.getSerialNumber());
            contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(new Date().getTime()));
            long DQ_ = cen.b().DQ_("device", contentValues);
            LogUtil.a("DeviceManager", "bindWiFiDevice rowId:", Long.valueOf(DQ_));
            if (DQ_ != -1) {
                ctkVar.doCreateBond(iDeviceEventHandler);
                g();
                return true;
            }
        }
        return false;
    }

    public boolean b(String str, String str2, ctk ctkVar, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler) {
        return e(str, str2, ctkVar, iDeviceEventHandler, 8);
    }

    public boolean e(String str, String str2, ctk ctkVar, com.huawei.health.device.callback.IDeviceEventHandler iDeviceEventHandler, int i) {
        long DU_;
        if (ctkVar == null) {
            LogUtil.h("DeviceManager", "bindWiFiUpdateDevice device is null");
            return false;
        }
        LogUtil.a("DeviceManager", "bindWiFiUpdateDevice ", cpw.d(ctkVar.b().a()));
        LogUtil.c("DeviceManager", "bindWiFiUpdateDevice ", ctkVar.toString());
        String m = !TextUtils.isEmpty(ctkVar.b().m()) ? ctkVar.b().m() : ctkVar.getSerialNumber();
        String e = !TextUtils.isEmpty(ctkVar.e()) ? ctkVar.e() : m;
        if (TextUtils.isEmpty(m) || TextUtils.isEmpty(e)) {
            LogUtil.h("DeviceManager", "can not update wifi device in table device, serial number or uniqueID is null");
            return false;
        }
        LogUtil.a("DeviceManager", "bindWiFiUpdateDevice uniqueId = ", cpw.d(e), ",productId = ", str, ", deviceName = ", ctkVar.getDeviceName(), ", sn = ", cpw.d(m));
        ContentValues contentValues = new ContentValues();
        contentValues.put(Wpt.MODE, "" + i);
        if (d().c(m, false) == null) {
            LogUtil.a("DeviceManager", "bindWiFiUpdateDevice insert table device");
            contentValues.put("productId", str);
            contentValues.put("name", ctkVar.getDeviceName());
            contentValues.put("uniqueId", e);
            contentValues.put("kind", String.valueOf(ctkVar.getKind()));
            contentValues.put("kitUuid", str2);
            contentValues.put("auto", (Integer) 1);
            contentValues.put("sn", m);
            contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(new Date().getTime()));
            DU_ = cen.b().DQ_("device", contentValues);
        } else {
            DU_ = cen.b().DU_("device", contentValues, "sn = ?", new String[]{m});
        }
        LogUtil.a("DeviceManager", "bindWiFiUpdateDevice rowId:", Long.valueOf(DU_));
        if (DU_ != -1) {
            ctkVar.doCreateBond(iDeviceEventHandler);
            return true;
        }
        if (iDeviceEventHandler != null) {
            LogUtil.a("DeviceManager", "callback PAIRING_FAILED");
            iDeviceEventHandler.onStateChanged(8);
        }
        return false;
    }

    public boolean a(String str, String str2, com.huawei.hihealth.device.open.HealthDevice healthDevice, IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("DeviceManager", "DeviceManager bindDeviceUniversal");
        String uniqueId = healthDevice.getUniqueId();
        if (cfl.a().c(str2) == null || uniqueId == null || uniqueId.trim().length() <= 0) {
            return false;
        }
        LogUtil.c("DeviceManager", "bindDeviceUniversal doCreatePair result :", Boolean.valueOf(healthDevice.doCreatePair(iDeviceEventHandler)));
        if (cpa.ac(str)) {
            return true;
        }
        return e(str, str2, healthDevice);
    }

    public boolean k(String str) {
        LogUtil.a("DeviceManager", "DeviceManager unbindWiFiDevice");
        if (str == null) {
            return false;
        }
        ctk c = ctq.c(str);
        if (c != null) {
            LogUtil.a("DeviceManager", "unbindWiFiDevice productId ", c.getProductId());
            int e = cen.b().e("device", "sn = ?", new String[]{c.getSerialNumber()});
            LogUtil.a("DeviceManager", "delete device in table device, serial number ", cpw.d(c.getSerialNumber()));
            LogUtil.a("DeviceManager", "unbindWiFiDevice isRemoveSuccess:", Boolean.valueOf(c.doRemoveBond()), " affectedRows:", Integer.valueOf(e));
            return true;
        }
        LogUtil.h("DeviceManager", "unbindWiFiDevice device is null!");
        return false;
    }

    public boolean g(String str) {
        LogUtil.a("DeviceManager", "DeviceManager unbindDevice");
        if (!TextUtils.isEmpty(str)) {
            MeasurableDevice c = d().c(str);
            if (cen.b().e("device", "productId = ?", new String[]{str}) <= -1) {
                LogUtil.h("DeviceManager", "unbindDevice delete fail");
                return false;
            }
            if (c == null) {
                LogUtil.h("DeviceManager", "unbindDevice not find device");
                return false;
            }
            new cpo().e(c.getUniqueId());
            c.doRemoveBond();
            if (c instanceof ctk) {
                return true;
            }
            e(c);
            return true;
        }
        LogUtil.h("DeviceManager", "unbind devcie uniqueId is null");
        return false;
    }

    public boolean f(String str) {
        LogUtil.a("DeviceManager", "DeviceManager unbindDevice by SerialNumber ", cpw.d(str));
        if (!TextUtils.isEmpty(str)) {
            MeasurableDevice c = d().c(str, false);
            if (cen.b().e("device", "sn = ?", new String[]{str}) <= -1) {
                LogUtil.h("DeviceManager", "unbindDevice delete fail");
                return false;
            }
            if (c == null) {
                LogUtil.h("DeviceManager", "unbindDevice not find device");
                return false;
            }
            new cpo().e(c.getUniqueId());
            c.doRemoveBond();
            if (c instanceof ctk) {
                return true;
            }
            e(c);
            return true;
        }
        LogUtil.h("DeviceManager", "unbind devcie uniqueId is null");
        return false;
    }

    private void e(MeasurableDevice measurableDevice) {
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("operation", -1);
        intent.putExtra("productId", measurableDevice.getProductId());
        intent.putExtra("uniqueId", measurableDevice.getUniqueId());
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    public boolean a(String str, String str2) {
        DeviceExtra deviceExtra;
        LogUtil.a("DeviceManager", "DeviceManager unbindDevice fittings by attachUniqueId ", cpw.d(str), ", uniqueId ", cpw.d(str2));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("DeviceManager", "unbind devcie uniqueId is null");
            return false;
        }
        MeasurableDevice d2 = d().d(str2, false);
        if (d2 != null && !TextUtils.isEmpty(d2.getExtra()) && (deviceExtra = (DeviceExtra) HiJsonUtil.e(d2.getExtra(), DeviceExtra.class)) != null && koq.c(deviceExtra.getAttached())) {
            a(str, str2, deviceExtra);
            if (d(str, str2, deviceExtra)) {
                return true;
            }
        }
        return c(str2, d2);
    }

    public boolean n(String str) {
        DeviceExtra deviceExtra;
        LogUtil.a("DeviceManager", "DeviceManager unbindDevice by uniqueId, ", cpw.d(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "unbind devcie uniqueId is null");
            return false;
        }
        MeasurableDevice d2 = d().d(str, false);
        if (d2 != null && !TextUtils.isEmpty(d2.getExtra()) && (deviceExtra = (DeviceExtra) HiJsonUtil.e(d2.getExtra(), DeviceExtra.class)) != null && koq.c(deviceExtra.getFittings())) {
            a(str, deviceExtra);
        }
        return c(str, d2);
    }

    private boolean c(String str, MeasurableDevice measurableDevice) {
        if (measurableDevice != null) {
            dew.e(measurableDevice.getAddress(), "local");
        }
        if (cen.b().e("device", "uniqueId = ?", new String[]{str}) <= -1) {
            LogUtil.h("DeviceManager", "unbindDeviceByUniqueId delete fail");
            return false;
        }
        if (measurableDevice == null) {
            LogUtil.h("DeviceManager", "unbindDeviceByUniqueId not find device");
            return false;
        }
        measurableDevice.doRemoveBond();
        if (measurableDevice instanceof ctk) {
            return true;
        }
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("operation", -1);
        intent.putExtra("productId", measurableDevice.getProductId());
        intent.putExtra("uniqueId", measurableDevice.getUniqueId());
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
        return true;
    }

    private void a(String str, DeviceExtra deviceExtra) {
        for (DeviceExtra.DeviceData deviceData : deviceExtra.getFittings()) {
            if (TextUtils.isEmpty(deviceData.getUniqueId())) {
                LogUtil.h("DeviceManager", "fitting uniqueId is null");
            } else {
                MeasurableDevice d2 = d().d(deviceData.getUniqueId(), false);
                if (d2 == null || TextUtils.isEmpty(d2.getExtra())) {
                    LogUtil.h("DeviceManager", "deviceFitting or deviceFitting.getExtra is null");
                } else {
                    DeviceExtra deviceExtra2 = (DeviceExtra) HiJsonUtil.e(d2.getExtra(), DeviceExtra.class);
                    if (deviceExtra2 == null || koq.b(deviceExtra2.getAttached())) {
                        LogUtil.h("DeviceManager", "attachExtra or attachExtra.getFittings is null");
                    } else {
                        List<DeviceExtra.DeviceData> attached = deviceExtra2.getAttached();
                        Iterator<DeviceExtra.DeviceData> it = attached.iterator();
                        while (it.hasNext()) {
                            if (str.equals(it.next().getUniqueId())) {
                                it.remove();
                            }
                        }
                        if (attached.size() == 0) {
                            LogUtil.a("DeviceManager", "unbindDeviceByUniqueId deviceFitting by uniqueId, ", cpw.d(deviceData.getUniqueId()));
                            c(deviceData.getUniqueId(), d2);
                        } else {
                            j(deviceData.getUniqueId(), HiJsonUtil.e(deviceExtra2));
                        }
                    }
                }
            }
        }
    }

    private void a(String str, String str2, DeviceExtra deviceExtra) {
        for (DeviceExtra.DeviceData deviceData : deviceExtra.getAttached()) {
            if (TextUtils.isEmpty(deviceData.getUniqueId())) {
                LogUtil.h("DeviceManager", "attached uniqueId is null");
            } else if (deviceData.getUniqueId().equals(str)) {
                MeasurableDevice d2 = d().d(str, false);
                if (d2 == null || TextUtils.isEmpty(d2.getExtra())) {
                    LogUtil.h("DeviceManager", "attachDevice or attachDevice.getExtra is null");
                } else {
                    DeviceExtra deviceExtra2 = (DeviceExtra) HiJsonUtil.e(d2.getExtra(), DeviceExtra.class);
                    if (deviceExtra2 == null || koq.b(deviceExtra2.getFittings())) {
                        LogUtil.h("DeviceManager", "attachExtra or attachExtra.getFittings is null");
                    } else {
                        List<DeviceExtra.DeviceData> fittings = deviceExtra2.getFittings();
                        Iterator<DeviceExtra.DeviceData> it = fittings.iterator();
                        while (it.hasNext()) {
                            if (str2.equals(it.next().getUniqueId())) {
                                it.remove();
                            }
                        }
                        j(str, fittings.size() > 0 ? HiJsonUtil.e(deviceExtra2) : null);
                        return;
                    }
                }
            } else {
                continue;
            }
        }
    }

    private boolean d(String str, String str2, DeviceExtra deviceExtra) {
        Iterator<DeviceExtra.DeviceData> it = deviceExtra.getAttached().iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getUniqueId())) {
                it.remove();
            }
        }
        if (deviceExtra.getAttached().size() <= 0) {
            return false;
        }
        j(str2, HiJsonUtil.e(deviceExtra));
        return true;
    }

    public boolean f(String str, String str2) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
            com.huawei.hihealth.device.open.HealthDevice b2 = d().b(str, str2);
            if (cen.b().e("device", "uniqueId = ?", new String[]{str2}) <= -1) {
                LogUtil.h("DeviceManager", "unbindDeviceUniversal delete fail");
                return false;
            }
            if (b2 != null) {
                LogUtil.c("DeviceManager", "unbindDeviceUniversal DeviceManager isRemovePair : ", Boolean.valueOf(b2.doRemovePair()));
                Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
                intent.setPackage(cpp.a().getPackageName());
                intent.putExtra("operation", -1);
                intent.putExtra("uniqueId", str2);
                intent.putExtra("productId", str);
                cpp.a().sendBroadcast(intent, LocalBroadcast.c);
                return true;
            }
        }
        return false;
    }

    public boolean e(String str, com.huawei.health.device.model.HealthDevice healthDevice) {
        if (healthDevice == null || TextUtils.isEmpty(healthDevice.getUniqueId()) || healthDevice.getUniqueId().trim().length() <= 0) {
            LogUtil.a("DeviceManager", "Enter bindWearDevice illegal uniqueId");
            return false;
        }
        String uniqueId = healthDevice.getUniqueId();
        LogUtil.a("DeviceManager", "DeviceManager bindWearDevice ", cpw.d(uniqueId));
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("name", healthDevice.getDeviceName());
        contentValues.put("uniqueId", uniqueId);
        contentValues.put(Wpt.MODE, "16");
        contentValues.put("kind", String.valueOf(HealthDevice.HealthDeviceKind.HDK_HEART_RATE));
        contentValues.put("kitUuid", (Integer) 0);
        contentValues.put("auto", (Integer) 1);
        contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(new Date().getTime()));
        long DQ_ = cen.b().DQ_("device", contentValues);
        d(str, healthDevice, "");
        if (DQ_ >= 0) {
            return true;
        }
        LogUtil.h("DeviceManager", "bindWearDevice insert into data base fail");
        return false;
    }

    public int e() {
        LogUtil.a("DeviceManager", "DeviceManager deleteWearDevice");
        LogUtil.a("DeviceManager", "Enter deleteWearDevice");
        int e = cen.b().e("device", "mode = ? and kind = ? and kitUuid = ? ", new String[]{"16", "HDK_HEART_RATE", "0"});
        LogUtil.a("DeviceManager", "deleteWearDevice num:", Integer.valueOf(e));
        return e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
    
        if (r6 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        return r2.size();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0089, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0087, code lost:
    
        if (0 == 0) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int j() {
        /*
            r16 = this;
            java.lang.String r0 = "DeviceManager getWearDeviceSize"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "DeviceManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.util.ArrayList r2 = new java.util.ArrayList
            r0 = 10
            r2.<init>(r0)
            r0 = 4
            r3 = 1
            r4 = 2
            r5 = 0
            r6 = 0
            java.lang.String[] r9 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r0 = "productId"
            r9[r5] = r0     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r0 = "mode"
            r9[r3] = r0     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r0 = "kind"
            r9[r4] = r0     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r0 = "kitUuid"
            r7 = 3
            r9[r7] = r0     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            cen r0 = defpackage.cen.b()     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String[] r11 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r7 = "16"
            r11[r5] = r7     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r7 = "HDK_HEART_RATE"
            r11[r3] = r7     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r7 = "0"
            r11[r4] = r7     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r8 = "device"
            java.lang.String r10 = "mode = ? and kind = ? and kitUuid = ? "
            r12 = 0
            r13 = 0
            java.lang.String r14 = "addTime"
            r15 = 0
            r7 = r0
            android.database.Cursor r6 = r7.DT_(r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            if (r6 != 0) goto L52
            if (r6 == 0) goto L51
            r6.close()
        L51:
            return r5
        L52:
            boolean r0 = r6.moveToNext()     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            if (r0 == 0) goto L70
            java.lang.String r0 = r6.getString(r5)     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            boolean r7 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            if (r7 != 0) goto L52
            java.lang.String r7 = r0.trim()     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            int r7 = r7.length()     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            if (r7 <= 0) goto L52
            r2.add(r0)     // Catch: java.lang.Throwable -> L73 java.lang.NumberFormatException -> L75 android.database.sqlite.SQLiteException -> L77
            goto L52
        L70:
            if (r6 == 0) goto L8c
            goto L89
        L73:
            r0 = move-exception
            goto L91
        L75:
            r0 = move-exception
            goto L78
        L77:
            r0 = move-exception
        L78:
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = "getWearDeviceSize e = "
            r4[r5] = r7     // Catch: java.lang.Throwable -> L73
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L73
            r4[r3] = r0     // Catch: java.lang.Throwable -> L73
            com.huawei.hwlogsmodel.LogUtil.c(r1, r4)     // Catch: java.lang.Throwable -> L73
            if (r6 == 0) goto L8c
        L89:
            r6.close()
        L8c:
            int r0 = r2.size()
            return r0
        L91:
            if (r6 == 0) goto L96
            r6.close()
        L96:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ceo.j():int");
    }

    public boolean b(MeasurableDevice measurableDevice) {
        LogUtil.a("DeviceManager", "DeviceManager cancelBinding");
        if (measurableDevice == null) {
            return false;
        }
        measurableDevice.cancelBinding(measurableDevice);
        return true;
    }

    public void a(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback) {
        cjg.d().c(scanMode, list, deviceScanCallback);
    }

    public boolean d(String str, IDeviceEventHandler iDeviceEventHandler) {
        LogUtil.a("DeviceManager", "DeviceManager scanDeviceUniversal current device is ", str);
        com.huawei.hihealth.device.open.MeasureKit c = cfl.a().c(str);
        if (c == null) {
            LogUtil.h("DeviceManager", "DeviceManager scanDeviceUniversal fail,deviceKit is null");
            return false;
        }
        DeviceProvider deviceProvider = c.getDeviceProvider();
        this.c = deviceProvider;
        if (deviceProvider != null) {
            return deviceProvider.scanDevice(iDeviceEventHandler);
        }
        return false;
    }

    public boolean j(String str) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            return false;
        }
        String k = d2.k();
        LogUtil.a("DeviceManager", "isDeviceKitUniversal deviceInfo entry:", k);
        if (k == null) {
            return false;
        }
        return !"NULL".equals(k);
    }

    public void h() {
        LogUtil.a("DeviceManager", "DeviceManager stopScanUniversal:");
        DeviceProvider deviceProvider = this.c;
        if (deviceProvider != null) {
            deviceProvider.stopScanDevice();
        }
    }

    public int Eg_(ContentValues contentValues, String str, String[] strArr) {
        return (int) cen.b().DU_("device", contentValues, str, strArr);
    }

    public void d(String str, com.huawei.health.device.model.HealthDevice healthDevice, String str2) {
        LogUtil.a("DeviceManager", "registerDeviceInfo param is HealthDevice ", str2);
        HiDeviceInfo a2 = ces.a().a(str, str2, healthDevice);
        dcz d2 = ResourceManager.e().d(str);
        try {
            if (d2 != null) {
                if (d2.g() != null && !d2.g().trim().isEmpty()) {
                    int h = CommonUtil.h(d2.g());
                    if (CommonUtil.cg() && h < 2) {
                        h = 300;
                    }
                    a2.setDeviceType(h);
                    LogUtil.c("DeviceManager", "registerDeviceInfo deviceId is ", Integer.valueOf(h), " productId is ", str);
                }
                if (CommonUtil.as()) {
                    ReleaseLogUtil.d("R_DeviceManager", "registerDeviceInfo type 1 deviceName is ", healthDevice.getDeviceName());
                    return;
                } else {
                    a2.setDeviceType(1);
                    LogUtil.h("DeviceManager", "registerDeviceInfo type 1 deviceName is ", healthDevice.getDeviceName());
                }
            } else if (healthDevice instanceof cfb) {
                a2.setDeviceType(((cfb) healthDevice).d());
                LogUtil.a("DeviceManager", "registerDeviceInfo deviceName is ", healthDevice.getDeviceName());
            } else {
                LogUtil.h("DeviceManager", "registerDeviceInfo know device type");
            }
            String e = dij.e(str);
            a2.setProdId(e);
            LogUtil.a("DeviceManager", "registerDeviceInfo prodId is：", e);
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(0);
            HiHealthManager.d(cpp.a()).registerDataClient(a2, arrayList, null);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DeviceManager", "registerDeviceInfo set hiDevcieId error, save info fail, please check, originalType");
        }
    }

    private void c(String str, com.huawei.hihealth.device.open.HealthDevice healthDevice) {
        LogUtil.a("DeviceManager", "registerDeviceInfo param is open.HealthDevice");
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(1);
        hiDeviceInfo.setDeviceUniqueCode(healthDevice.getUniqueId());
        hiDeviceInfo.setDeviceName(healthDevice.getDeviceName());
        dcz d2 = ResourceManager.e().d(str);
        if (d2 != null) {
            if (d2.g() == null || d2.g().trim().isEmpty()) {
                hiDeviceInfo.setDeviceType(1);
                LogUtil.h("DeviceManager", "registerDeviceInfo type 1 deviceName is ", healthDevice.getDeviceName());
            } else {
                try {
                    hiDeviceInfo.setDeviceType(CommonUtil.h(d2.g()));
                    LogUtil.c("DeviceManager", "registerDeviceInfo deviceId is ", d2.g(), " productId is ", str);
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("DeviceManager", "registerDeviceInfo set error, about open device.");
                    return;
                }
            }
        }
        String e = dij.e(str);
        hiDeviceInfo.setProdId(e);
        LogUtil.a("DeviceManager", "registerDeviceInfo prodId is：", e);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(0);
        HiHealthManager.d(cpp.a()).registerDataClient(hiDeviceInfo, arrayList, null);
    }

    private void g() {
        if (TextUtils.isEmpty(SharedPreferenceManager.b(cpp.a(), "BIND_WEIGHT", "bind_weight_time"))) {
            Context a2 = cpp.a();
            long currentTimeMillis = System.currentTimeMillis();
            SharedPreferenceManager.e(a2, "BIND_WEIGHT", "bind_weight_time", String.valueOf(currentTimeMillis), new StorageParams());
        }
    }

    private MeasurableDevice DW_(ContentValues contentValues, boolean z) {
        MeasurableDevice DX_;
        String asString = contentValues.getAsString("name");
        String asString2 = contentValues.getAsString("uniqueId");
        Integer asInteger = contentValues.getAsInteger("auto");
        if (asInteger == null) {
            LogUtil.h("DeviceManager", "can't not get device measure mode ");
            return null;
        }
        if (dfe.a().e()) {
            DX_ = new cet(asString, asString2, asString2);
        } else {
            DX_ = DX_(contentValues);
        }
        if (DX_ == null) {
            return null;
        }
        HealthDevice.HealthDeviceKind c = dks.c(contentValues.getAsString("kind"));
        String asString3 = contentValues.getAsString("productId");
        String asString4 = contentValues.getAsString("kitUuid");
        DX_.setAutoDevice(asInteger.intValue() == 1);
        DX_.setDeviceName(asString);
        DX_.setMeasureKitUuid(asString4);
        DX_.setKind(c);
        DX_.setProductId(asString3);
        DX_.setSerialNumber(contentValues.getAsString("sn"));
        DX_.setShowInList(contentValues.getAsInteger("showInList").intValue());
        DX_.setExtra(contentValues.getAsString(KnitFragment.KEY_EXTRA));
        if (DX_ instanceof ctk) {
            if (z && cpa.x(asString3)) {
                LogUtil.a("DeviceManager", "this device is BleDevice ");
                return DV_(DX_, contentValues);
            }
            ctk ctkVar = (ctk) DX_;
            Eb_(ctkVar, contentValues);
            LogUtil.a("DeviceManager", "this device is WiFiDevice and getDeviceInfo().getSource() ==  ", Integer.valueOf(ctkVar.b().k()));
            return DX_;
        }
        LogUtil.h("DeviceManager", "this device is not wifiDevice ");
        return DX_;
    }

    private void DZ_(ContentValues contentValues) {
        if (contentValues == null) {
            LogUtil.h("DeviceManager", "sendBondNewDeviceBroadcast device info is null");
            return;
        }
        Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
        intent.setPackage(cpp.a().getPackageName());
        intent.putExtra("operation", 1);
        String[] strArr = {"productId", "uniqueId", Wpt.MODE, "name", "kind"};
        for (int i = 0; i < 5; i++) {
            String str = strArr[i];
            if (contentValues.containsKey(str)) {
                if (Wpt.MODE.equals(str) || "auto".equals(str)) {
                    intent.putExtra(str, contentValues.getAsInteger(str));
                } else if (EventMonitorRecord.ADD_TIME.equals(str)) {
                    intent.putExtra(str, contentValues.getAsLong(str));
                } else {
                    intent.putExtra(str, contentValues.getAsString(str));
                }
            } else {
                LogUtil.h("DeviceManager", "sendBondNewDeviceBroadcast device info missing: ", str);
            }
        }
        LogUtil.a("DeviceManager", "sendBroadCast deviceName = ", contentValues.getAsString("name"));
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
    }

    private int e(dcz dczVar, MeasureKit measureKit) {
        if (measureKit != null) {
            return ces.a().b(measureKit);
        }
        if (dczVar != null) {
            return dczVar.x().c();
        }
        LogUtil.h("DeviceManager", "can not determine connect mode, set default connect mode");
        return 0;
    }

    private boolean b(String str, MeasurableDevice measurableDevice) {
        if (measurableDevice != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(measurableDevice.getUniqueId())) {
            return true;
        }
        LogUtil.h("DeviceManager", "save device fail, illegal device");
        return false;
    }

    private boolean c(dcz dczVar, MeasureKit measureKit) {
        if (dczVar != null && did.c().d(dczVar.t()) && ("12".equals(dczVar.p()) || a.t.equals(dczVar.p()))) {
            return true;
        }
        return ((dczVar != null && "10".equals(dczVar.p())) || measureKit == null || measureKit.getBackgroundController() == null) ? false : true;
    }

    private String b(dcz dczVar, MeasureKit measureKit) {
        String name = HealthDevice.HealthDeviceKind.HDK_UNKNOWN.name();
        if (measureKit != null && measureKit.getHealthDataKind() != null) {
            return measureKit.getHealthDataKind().name();
        }
        if (dczVar != null && dczVar.l() != null) {
            return dczVar.l().name();
        }
        LogUtil.h("DeviceManager", "can not determine device kind, set defualt");
        return name;
    }

    public boolean Ef_(Context context, ContentValues contentValues, String str) {
        if (context == null || contentValues == null || contentValues.size() == 0 || TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "unbindHaveBindingDevice context or deviceInfo or productId is null");
            return false;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, contentValues.getAsString("name"));
        if (!a(contentValues.getAsInteger(com.huawei.operation.utils.Constants.KEY_BLE_SCAN_MODE).intValue()) && !i(str)) {
            LogUtil.a("DeviceManager", "unbindHaveBindingDevice mAdapter.isBluetooth(mMode) = false");
            return false;
        }
        String asString = contentValues.getAsString("uniqueId");
        boolean n = n(asString, str);
        if (n) {
            LogUtil.a("DeviceManager", "unbindHaveBindingDevice isUnbind is true");
            cjx.e().d(str, asString);
            ixx.d().d(context, AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
        }
        return n;
    }

    private boolean n(String str, String str2) {
        if (dks.j(str2)) {
            LogUtil.a("DeviceManager", " unbindHaveBindingDevice fitting device unbind");
            return a(dds.c().d(), str);
        }
        if (j(str2)) {
            boolean f = f(str2, str);
            cun.c().executeDeviceRelatedLogic(ExecuteMode.CLEAR_AM_16_CACHE, null, "DeviceManager");
            return f;
        }
        if (TextUtils.isEmpty(str)) {
            return g(str2);
        }
        return n(str);
    }

    public boolean i(String str) {
        return "PRO_GPRS".equals(e(str));
    }

    public String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceManager", "getDeviceProtocol productId is null");
            return "";
        }
        dcz b2 = ResourceManager.e().b(str);
        if (b2 == null) {
            LogUtil.h("DeviceManager", "getDeviceProtocol productInfo is null");
            return "";
        }
        return b2.y();
    }
}
