package com.huawei.hianalytics.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.CommonHeaderEx;
import com.huawei.hianalytics.core.storage.DBUtil;

/* loaded from: classes4.dex */
public class b extends a<CommonHeaderEx> {
    public static final String e = "CREATE TABLE COMMON_HEADER_EX (" + CommonHeaderEx.COLUMN_HASHCODE.columnName + " TEXT PRIMARY KEY NOT NULL," + CommonHeaderEx.COLUMN_HEADER.columnName + " TEXT);";

    @Override // com.huawei.hianalytics.core.a
    public CommonHeaderEx a(Cursor cursor) {
        return new CommonHeaderEx(cursor.getColumnIndex(CommonHeaderEx.COLUMN_HASHCODE.columnName) == -1 ? "" : cursor.getString(cursor.getColumnIndex(CommonHeaderEx.COLUMN_HASHCODE.columnName)), cursor.isNull(cursor.getColumnIndex(CommonHeaderEx.COLUMN_HEADER.columnName)) ? "" : cursor.getString(cursor.getColumnIndex(CommonHeaderEx.COLUMN_HEADER.columnName)));
    }

    @Override // com.huawei.hianalytics.core.a
    public void a(ContentValues contentValues, CommonHeaderEx commonHeaderEx) {
        CommonHeaderEx commonHeaderEx2 = commonHeaderEx;
        contentValues.clear();
        contentValues.put(CommonHeaderEx.COLUMN_HASHCODE.columnName, commonHeaderEx2.getEvtExHashCode());
        contentValues.put(CommonHeaderEx.COLUMN_HEADER.columnName, commonHeaderEx2.getCommonHeaderEx());
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        DBUtil.dropTable(sQLiteDatabase, CommonHeaderEx.TABLE_NAME);
        HiLog.w("CommonHeaderExDao", "dropTable");
    }

    public b(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, CommonHeaderEx.TABLE_NAME);
    }
}
