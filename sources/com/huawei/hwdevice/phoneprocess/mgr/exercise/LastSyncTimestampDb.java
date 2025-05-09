package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;

/* loaded from: classes5.dex */
public class LastSyncTimestampDb {
    private static final String COLUMN_DEVICE_ID = "Device_ID";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TIMESTAMP = "Time_Stamp";
    private static final String END_SQL = "'";
    private static final String EQUALS_SQL = "='";
    public static final String TABLE_ID = "HWExerciseAdviceManagerDB";
    private static final String TAG = "HWExerciseAdviceManagerDB";
    private static final String USER_ID = "user_id";

    private String getCreateSqlCmd() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("_id integer primary key autoincrement,Device_ID NVARCHAR(300) not null,Time_Stamp integer not null");
        return String.valueOf(stringBuffer);
    }

    public void createDbTable(HwExerciseAdviceManager hwExerciseAdviceManager) {
        if (hwExerciseAdviceManager == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "createDbTable manager is null");
            return;
        }
        String currentDataTableId = getCurrentDataTableId();
        if (hwExerciseAdviceManager.createStorageDataTable(currentDataTableId, 1, getCreateSqlCmd()) != 0) {
            LogUtil.h("HWExerciseAdviceManagerDB", "database is bad.");
            if (!hwExerciseAdviceManager.deleteDatabase()) {
                LogUtil.h("HWExerciseAdviceManagerDB", "data base error.");
            } else {
                hwExerciseAdviceManager.createStorageDataTable(currentDataTableId, 1, getCreateSqlCmd());
            }
        }
    }

    public ContentValues getContentValues(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DEVICE_ID, str);
        contentValues.put(COLUMN_TIMESTAMP, Long.valueOf(j));
        return contentValues;
    }

    private long getAndFillDataTable(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex(COLUMN_TIMESTAMP));
    }

    public long getLastTimestamp(HwExerciseAdviceManager hwExerciseAdviceManager) {
        long j = 0;
        if (hwExerciseAdviceManager == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "getLastTimestamp manager is null");
            return 0L;
        }
        Cursor queryStorageData = hwExerciseAdviceManager.queryStorageData(getCurrentDataTableId(), 1, "Device_ID='" + KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + hwExerciseAdviceManager.getCurrentDeviceId() + "' OR Device_ID='" + hwExerciseAdviceManager.getCurrentDeviceId() + END_SQL);
        if (queryStorageData == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "get lastTimeStamp query error");
            return 0L;
        }
        if (queryStorageData.moveToNext()) {
            LogUtil.c("HWExerciseAdviceManagerDB", "getLastTimeStamp moveToNext() is not null");
            j = getAndFillDataTable(queryStorageData);
        }
        queryStorageData.close();
        LogUtil.c("HWExerciseAdviceManagerDB", "getLastTimestamp: ", Long.valueOf(j));
        return j;
    }

    public long getLastTimestamp(HwExerciseAdviceManager hwExerciseAdviceManager, String str) {
        long j = 0;
        if (hwExerciseAdviceManager == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "getLastTimestamp manager is null");
            return 0L;
        }
        Cursor queryStorageData = hwExerciseAdviceManager.queryStorageData(getCurrentDataTableId(), 1, "Device_ID='" + KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + str + "' OR Device_ID='" + str + END_SQL);
        if (queryStorageData == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "getLastTimestamp query error");
            return 0L;
        }
        if (queryStorageData.moveToNext()) {
            LogUtil.c("HWExerciseAdviceManagerDB", "getLastTimestamp moveToNext() is not null");
            j = getAndFillDataTable(queryStorageData);
        }
        queryStorageData.close();
        LogUtil.c("HWExerciseAdviceManagerDB", "getLastTimestamp: ", Long.valueOf(j));
        return j;
    }

    public void setLastTimestamp(HwExerciseAdviceManager hwExerciseAdviceManager, long j) {
        if (hwExerciseAdviceManager == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "setLastTimestamp manager is null");
            return;
        }
        String currentDataTableId = getCurrentDataTableId();
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Cursor queryStorageData = hwExerciseAdviceManager.queryStorageData(currentDataTableId, 1, "Device_ID='" + e + hwExerciseAdviceManager.getCurrentDeviceId() + END_SQL);
        if (queryStorageData != null) {
            if (queryStorageData.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_TIMESTAMP, Long.valueOf(j));
                hwExerciseAdviceManager.updateStorageData(currentDataTableId, 1, contentValues, "Device_ID='" + e + hwExerciseAdviceManager.getCurrentDeviceId() + END_SQL);
                LogUtil.c("HWExerciseAdviceManagerDB", "setLastTimestamp update");
            } else {
                hwExerciseAdviceManager.insertStorageData(currentDataTableId, 1, getContentValues(j, e + hwExerciseAdviceManager.getCurrentDeviceId()));
                LogUtil.c("HWExerciseAdviceManagerDB", "setLastTimestamp new");
            }
            queryStorageData.close();
        }
        hwExerciseAdviceManager.deleteStorageData(currentDataTableId, 1, "Device_ID='" + hwExerciseAdviceManager.getCurrentDeviceId() + END_SQL);
    }

    public void setLastTimestamp(HwExerciseAdviceManager hwExerciseAdviceManager, String str, long j) {
        if (hwExerciseAdviceManager == null) {
            LogUtil.h("HWExerciseAdviceManagerDB", "setLastTimestamp manager is null");
            return;
        }
        String currentDataTableId = getCurrentDataTableId();
        String e = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Cursor queryStorageData = hwExerciseAdviceManager.queryStorageData(currentDataTableId, 1, "Device_ID='" + e + str + END_SQL);
        if (queryStorageData != null) {
            if (queryStorageData.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_TIMESTAMP, Long.valueOf(j));
                hwExerciseAdviceManager.updateStorageData(currentDataTableId, 1, contentValues, "Device_ID='" + e + str + END_SQL);
                LogUtil.c("HWExerciseAdviceManagerDB", "setLastTimestamp update");
            } else {
                hwExerciseAdviceManager.insertStorageData(currentDataTableId, 1, getContentValues(j, e + str));
                LogUtil.c("HWExerciseAdviceManagerDB", "setLastTimestamp new");
            }
            queryStorageData.close();
        }
        hwExerciseAdviceManager.deleteStorageData(currentDataTableId, 1, "Device_ID='" + str + END_SQL);
    }

    private String getCurrentDataTableId() {
        return "HWExerciseAdviceManagerDB";
    }
}
