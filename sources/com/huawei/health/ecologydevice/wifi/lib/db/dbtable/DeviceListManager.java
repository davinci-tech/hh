package com.huawei.health.ecologydevice.wifi.lib.db.dbtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.hwbasemgr.HwBaseManager;
import defpackage.dku;
import defpackage.dkz;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceListManager extends HwBaseManager {
    private static final Object b = new Object();
    private static volatile DeviceListManager e;

    public DeviceListManager(Context context) {
        super(context);
        a();
        if (e()) {
            dku.d(false, "DeviceListManager", "check DB is Null");
        }
    }

    private void a() {
        dku.d(false, "DeviceListManager", "createTable | create new table: ", getTableFullName("WiFiDeviceList"));
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,productId text not null,deviceId text not null,deviceModel text,deviceTypeId text");
        dku.a(false, "DeviceListManager", "createTable | create new table sql = ", sb.toString());
        createStorageDataTable("WiFiDeviceList", 1, sb.toString());
        dku.d(false, "DeviceListManager", "createTable | create table end");
    }

    public static DeviceListManager c(Context context) {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new DeviceListManager(context);
                }
            }
        }
        return e;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10034;
    }

    private boolean e() {
        dku.d(false, "DeviceListManager", "checkDBisNull");
        Cursor queryStorageData = queryStorageData("WiFiDeviceList", 1, null);
        if (queryStorageData == null) {
            return true;
        }
        queryStorageData.close();
        return false;
    }

    public dkz e(String str) {
        dkz dkzVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Cursor rawQueryStorageData = rawQueryStorageData(1, "select * from " + getTableFullName("WiFiDeviceList") + " where deviceId = ?", new String[]{str});
        if (rawQueryStorageData != null && rawQueryStorageData.moveToNext()) {
            dkzVar = WF_(rawQueryStorageData);
            dku.d(true, "DeviceListManager", dkzVar.toString());
        }
        if (rawQueryStorageData != null) {
            rawQueryStorageData.close();
        }
        return dkzVar;
    }

    private dkz WF_(Cursor cursor) {
        dkz dkzVar = new dkz();
        dkzVar.d = cursor.getString(cursor.getColumnIndex("productId"));
        dkzVar.c = cursor.getString(cursor.getColumnIndex("deviceModel"));
        dkzVar.b = cursor.getString(cursor.getColumnIndex("deviceTypeId"));
        dkzVar.f11701a = cursor.getString(cursor.getColumnIndex("deviceId"));
        return dkzVar;
    }

    public long c(List<dkz> list) {
        long j = 0;
        if (list != null && !list.isEmpty()) {
            dku.d(true, "DeviceListManager", "tables Size :", Integer.valueOf(list.size()));
            for (dkz dkzVar : list) {
                if (e(dkzVar.f11701a) == null) {
                    j = insertStorageData("WiFiDeviceList", 1, WE_(dkzVar));
                } else {
                    dku.d(true, "DeviceListManager", "not insert proId:", dkzVar.f11701a);
                }
            }
            dku.d(true, "DeviceListManager", "insert count:", Long.valueOf(j));
        }
        return j;
    }

    private ContentValues WE_(dkz dkzVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", dkzVar.d);
        contentValues.put("deviceId", dkzVar.f11701a);
        contentValues.put("deviceModel", dkzVar.c);
        contentValues.put("deviceTypeId", dkzVar.b);
        return contentValues;
    }
}
