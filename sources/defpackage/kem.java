package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWindowAllocationException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;

/* loaded from: classes5.dex */
public class kem {

    /* renamed from: a, reason: collision with root package name */
    private static kem f14319a;
    private HwBaseManager b;

    private kem(HwBaseManager hwBaseManager) {
        this.b = hwBaseManager;
        b();
    }

    public static kem c(HwBaseManager hwBaseManager) {
        kem kemVar;
        synchronized (kem.class) {
            if (f14319a == null) {
                f14319a = new kem(hwBaseManager);
            }
            kemVar = f14319a;
        }
        return kemVar;
    }

    public long c() {
        Cursor queryStorageData;
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        long j = 0;
        try {
            queryStorageData = this.b.queryStorageData("heartRateRaiseRemindAlarmTime", 1, "Device_ID='" + e + keg.e() + "' OR Device_ID='" + keg.e() + "'");
        } catch (CursorWindowAllocationException e2) {
            LogUtil.b("HeartRateRemindAlarmTimeDb", "getLastTimeStamp CursorWindowAllocationException:", ExceptionUtils.d(e2));
        }
        if (queryStorageData == null) {
            LogUtil.h("HeartRateRemindAlarmTimeDb", "get lastTimeStamp query error");
            return 0L;
        }
        if (queryStorageData.moveToNext()) {
            LogUtil.c("HeartRateRemindAlarmTimeDb", "getLastTimeStamp moveToNext() is not null");
            j = bNj_(queryStorageData);
        }
        queryStorageData.close();
        LogUtil.c("HeartRateRemindAlarmTimeDb", "getLastTimeStamp is ", Long.valueOf(j));
        return j;
    }

    public void b(long j) {
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        try {
            Cursor queryStorageData = this.b.queryStorageData("heartRateRaiseRemindAlarmTime", 1, "Device_ID='" + e + keg.e() + "'");
            if (queryStorageData != null) {
                if (queryStorageData.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("Time_Stamp", Long.valueOf(j));
                    this.b.updateStorageData("heartRateRaiseRemindAlarmTime", 1, contentValues, "Device_ID='" + e + keg.e() + "'");
                    LogUtil.a("HeartRateRemindAlarmTimeDb", "setLastTimeStamp update ", Long.valueOf(j));
                } else {
                    this.b.insertStorageData("heartRateRaiseRemindAlarmTime", 1, bNk_(j, e + keg.e()));
                    LogUtil.a("HeartRateRemindAlarmTimeDb", "setLastTimeStamp new ", Long.valueOf(j));
                }
                queryStorageData.close();
            }
            this.b.deleteStorageData("heartRateRaiseRemindAlarmTime", 1, "Device_ID='" + keg.e() + "'");
        } catch (CursorWindowAllocationException e2) {
            LogUtil.b("HeartRateRemindAlarmTimeDb", "setLastTimeStamp CursorWindowAllocationException:", ExceptionUtils.d(e2));
        }
    }

    private long bNj_(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("Time_Stamp"));
    }

    private ContentValues bNk_(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Device_ID", str);
        contentValues.put("Time_Stamp", Long.valueOf(j));
        return contentValues;
    }

    private void b() {
        this.b.createStorageDataTable("heartRateRaiseRemindAlarmTime", 1, d());
    }

    private String d() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("_id integer primary key autoincrement,Device_ID NVARCHAR(300) not null,Time_Stamp integer not null");
        return String.valueOf(stringBuffer);
    }
}
