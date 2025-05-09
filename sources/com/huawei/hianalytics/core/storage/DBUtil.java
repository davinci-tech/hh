package com.huawei.hianalytics.core.storage;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;

/* loaded from: classes4.dex */
public class DBUtil {
    public static final String TAG = "DBUtil";

    public static void rename(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        exec(sQLiteDatabase, "ALTER TABLE " + str + " RENAME TO " + str2);
    }

    public static void exec(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            sQLiteDatabase.execSQL(str);
        } catch (Exception e) {
            HiLog.e(TAG, e.getMessage());
        }
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str)) {
            return;
        }
        exec(sQLiteDatabase, "DROP TABLE IF EXISTS " + str);
    }

    public static void deleteAll(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null || TextUtils.isEmpty(str)) {
            return;
        }
        exec(sQLiteDatabase, "DELETE FROM '" + str + "'");
    }
}
