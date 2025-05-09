package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kdt {
    private String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("_id integer primary key autoincrement,Device_ID NVARCHAR(300) not null,TimeStamp integer not null");
        return String.valueOf(sb);
    }

    public void e(kdq kdqVar) {
        if (kdqVar != null) {
            String b = b();
            if (kdqVar.createStorageDataTable(b, 1, e()) != 0) {
                if (!kdqVar.deleteDatabase()) {
                    LogUtil.h("StressLastSyncTimeStoreDb", "data base error.");
                } else {
                    kdqVar.createStorageDataTable(b, 1, e());
                }
            }
        }
    }

    private ContentValues bNi_(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Device_ID", str);
        contentValues.put("TimeStamp", Long.valueOf(j));
        return contentValues;
    }

    private long bNh_(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("TimeStamp"));
    }

    public long b(kdq kdqVar) {
        Cursor cursor;
        if (kdqVar != null) {
            cursor = kdqVar.queryStorageData(b(), 1, "Device_ID='" + kdqVar.b() + "'");
        } else {
            cursor = null;
        }
        long j = 0;
        if (cursor == null) {
            LogUtil.h("StressLastSyncTimeStoreDb", "getLastTimestamp query error");
            return 0L;
        }
        if (cursor.moveToNext()) {
            LogUtil.c("StressLastSyncTimeStoreDb", "getLastTimestamp cursor.moveToNext() is not null");
            j = bNh_(cursor);
        }
        cursor.close();
        LogUtil.c("StressLastSyncTimeStoreDb", "getLastTimestamp = ", Long.valueOf(j));
        return j;
    }

    public void d(kdq kdqVar, long j) {
        if (kdqVar == null) {
            LogUtil.h("StressLastSyncTimeStoreDb", "hwStressManager is null");
            return;
        }
        String b = b();
        Cursor queryStorageData = kdqVar.queryStorageData(b, 1, "Device_ID='" + kdqVar.b() + "'");
        if (queryStorageData == null) {
            LogUtil.h("StressLastSyncTimeStoreDb", "setLastTimestamp query error");
            return;
        }
        if (queryStorageData.moveToNext()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("TimeStamp", Long.valueOf(j));
            kdqVar.updateStorageData(b, 1, contentValues, "Device_ID='" + kdqVar.b() + "'");
            LogUtil.c("StressLastSyncTimeStoreDb", "setLastTimestamp update");
        } else {
            kdqVar.insertStorageData(b, 1, bNi_(j, kdqVar.b()));
            LogUtil.c("StressLastSyncTimeStoreDb", "setLastTimestamp new");
        }
        queryStorageData.close();
    }

    private String b() {
        return "HWStressManagerDB";
    }
}
