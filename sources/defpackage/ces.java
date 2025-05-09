package defpackage;

import android.content.ContentValues;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.device.open.BleMeasureController;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.open.HdpMeasureController;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.device.open.HealthDevice;
import com.huawei.hihealth.device.open.MeasureKit;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes3.dex */
public class ces {
    private static final Object b = new Object();
    private static ces c;

    private ces() {
    }

    public static ces a() {
        ces cesVar;
        synchronized (b) {
            if (c == null) {
                LogUtil.h("DeviceManagerHelper", "Enter getInstance");
                c = new ces();
            }
            cesVar = c;
        }
        return cesVar;
    }

    public boolean e(String str, String str2, HealthDevice healthDevice) {
        String uniqueId = healthDevice.getUniqueId();
        MeasureKit c2 = cfl.a().c(str2);
        dcz d = ResourceManager.e().d(str);
        boolean z = d != null && "10".equals(d.p());
        if (c2 != null && uniqueId != null && uniqueId.trim().length() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("productId", str);
            contentValues.put("name", healthDevice.getDeviceName());
            contentValues.put("uniqueId", uniqueId);
            contentValues.put(Wpt.MODE, HealthZonePushReceiver.PRESSURE_NOTIFY);
            contentValues.put("kind", c2.getHealthDataKind().name());
            contentValues.put("kitUuid", str2);
            contentValues.put("auto", Integer.valueOf((c2.getBackgroundController() == null || z) ? 0 : 1));
            contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(new Date().getTime()));
            long Eh_ = Eh_(contentValues);
            c(str, healthDevice);
            if (!dfe.a().e() && Eh_ != -1) {
                if (c2.getBackgroundController() != null && !z) {
                    LogUtil.c("DeviceManagerHelper", "saveDeviceInfo auto test background measure");
                    Intent intent = new Intent("com.huawei.health.action.DEVICE_CHANGED");
                    intent.setPackage(cpp.a().getPackageName());
                    intent.putExtra("operation", 1);
                    intent.putExtra("uniqueId", uniqueId);
                    intent.putExtra("productId", str);
                    intent.putExtra(Wpt.MODE, 32);
                    intent.putExtra("kind", c2.getHealthDataKind().name());
                    intent.putExtra("name", healthDevice.getDeviceName());
                    LogUtil.a("DeviceManagerHelper", "saveDeviceInfo deviceName = ", healthDevice.getDeviceName());
                    cpp.a().sendBroadcast(intent, LocalBroadcast.c);
                }
                return true;
            }
        }
        return false;
    }

    public int b(com.huawei.health.device.open.MeasureKit measureKit) {
        MeasureController backgroundController = measureKit.getBackgroundController();
        if (backgroundController == null) {
            backgroundController = measureKit.getMeasureController();
        }
        if (backgroundController == null) {
            return 0;
        }
        if (backgroundController instanceof BleMeasureController) {
            return 1;
        }
        return backgroundController instanceof HdpMeasureController ? 2 : 4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r3 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long Eh_(android.content.ContentValues r7) {
        /*
            r6 = this;
            java.lang.String r0 = "DeviceManagerHelper"
            r1 = 1
            r2 = 0
            r3 = 0
            android.content.Context r4 = defpackage.cpp.a()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 android.database.SQLException -> L34
            java.lang.String r5 = "device.db"
            android.database.sqlite.SQLiteDatabase r4 = r4.openOrCreateDatabase(r5, r2, r3)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28 android.database.SQLException -> L34
            java.lang.String r5 = "create table if not exists device (productId VARCHAR(40) UNIQUE not null, name VARCHAR(64) not null, uniqueId VARCHAR(32) not null, mode VARCHAR(16) not null,kind VARCHAR(16) not null, kitUuid VARCHAR(40) not null,auto integer not null, addTime integer not null)"
            r4.execSQL(r5)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22 android.database.SQLException -> L24
            java.lang.String r5 = "device"
            long r0 = r4.insert(r5, r3, r7)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22 android.database.SQLException -> L24
            if (r4 == 0) goto L44
            r4.close()
            goto L44
        L20:
            r7 = move-exception
            goto L46
        L22:
            r3 = r4
            goto L28
        L24:
            r3 = r4
            goto L34
        L26:
            r7 = move-exception
            goto L45
        L28:
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L26
            java.lang.String r1 = "getRowId Exception!"
            r7[r2] = r1     // Catch: java.lang.Throwable -> L26
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)     // Catch: java.lang.Throwable -> L26
            if (r3 == 0) goto L42
            goto L3f
        L34:
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L26
            java.lang.String r1 = "getRowId SQLException!"
            r7[r2] = r1     // Catch: java.lang.Throwable -> L26
            com.huawei.hwlogsmodel.LogUtil.c(r0, r7)     // Catch: java.lang.Throwable -> L26
            if (r3 == 0) goto L42
        L3f:
            r3.close()
        L42:
            r0 = 0
        L44:
            return r0
        L45:
            r4 = r3
        L46:
            if (r4 == 0) goto L4b
            r4.close()
        L4b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ces.Eh_(android.content.ContentValues):long");
    }

    public HiDeviceInfo a(String str, String str2, com.huawei.health.device.model.HealthDevice healthDevice) {
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(1);
        if (!TextUtils.isEmpty(str2) && cpa.x(str)) {
            hiDeviceInfo.setDeviceUniqueCode(str2);
        } else {
            hiDeviceInfo.setDeviceUniqueCode(healthDevice.getUniqueId());
        }
        hiDeviceInfo.setDeviceName(healthDevice.getDeviceName());
        return hiDeviceInfo;
    }

    private void c(String str, HealthDevice healthDevice) {
        LogUtil.a("DeviceManagerHelper", "registerDeviceInfo param is open.HealthDevice");
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(1);
        hiDeviceInfo.setDeviceUniqueCode(healthDevice.getUniqueId());
        hiDeviceInfo.setDeviceName(healthDevice.getDeviceName());
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            if (d.g() == null || d.g().trim().isEmpty()) {
                hiDeviceInfo.setDeviceType(1);
                LogUtil.h("DeviceManagerHelper", "registerDeviceInfo type 1 deviceName is ", healthDevice.getDeviceName());
            } else {
                try {
                    hiDeviceInfo.setDeviceType(CommonUtil.h(d.g()));
                    LogUtil.c("DeviceManagerHelper", "registerDeviceInfo deviceId is ", d.g(), " productId is ", str);
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("DeviceManagerHelper", "registerDeviceInfo set error, about open device.");
                    return;
                }
            }
        }
        String e = dij.e(str);
        hiDeviceInfo.setProdId(e);
        LogUtil.a("DeviceManagerHelper", "registerDeviceInfo prodId is：", e);
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        HiHealthManager.d(cpp.a()).registerDataClient(hiDeviceInfo, arrayList, null);
    }
}
