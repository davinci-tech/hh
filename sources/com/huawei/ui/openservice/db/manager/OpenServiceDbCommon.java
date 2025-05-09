package com.huawei.ui.openservice.db.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.ui.openservice.db.OpenServiceDbHelper;

/* loaded from: classes7.dex */
public class OpenServiceDbCommon {
    private String[] columns;
    private String tableName;
    private SQLiteDatabase writableDatabase;

    public OpenServiceDbCommon(Context context, String str, String[] strArr) {
        this.tableName = str;
        this.columns = (String[]) strArr.clone();
        this.writableDatabase = OpenServiceDbHelper.getInstance(context).getWritableDatabase();
    }

    public long insert(ContentValues contentValues) {
        return this.writableDatabase.insert(this.tableName, null, contentValues);
    }

    public int delete(String str, String[] strArr) {
        return this.writableDatabase.delete(this.tableName, str, strArr);
    }

    public int update(ContentValues contentValues, String str, String[] strArr) {
        return this.writableDatabase.update(this.tableName, contentValues, str, strArr);
    }

    public Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        return this.writableDatabase.query(this.tableName, this.columns, str, strArr, str2, str3, str4);
    }
}
