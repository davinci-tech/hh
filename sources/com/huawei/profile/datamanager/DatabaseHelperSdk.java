package com.huawei.profile.datamanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.profile.utils.logger.DsLog;
import java.util.Locale;

/* loaded from: classes9.dex */
public class DatabaseHelperSdk extends SQLiteOpenHelper {
    protected static final String PUBLIC_CLOUD_TABLE = "PUBLIC_CLOUD_TABLE";
    private static final String TAG = "DatabaseHelperSdk";

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DatabaseHelperSdk(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        addTable(sQLiteDatabase, PUBLIC_CLOUD_TABLE);
    }

    private void addTable(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null) {
            DsLog.et(TAG, "db is null", new Object[0]);
            return;
        }
        sQLiteDatabase.beginTransaction();
        sQLiteDatabase.execSQL(String.format(Locale.ROOT, "CREATE TABLE IF NOT EXISTS %s(key varchar(255),value varchar(255));", str));
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private void dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase == null) {
            DsLog.et(TAG, "db is null", new Object[0]);
            return;
        }
        sQLiteDatabase.beginTransaction();
        sQLiteDatabase.execSQL(String.format(Locale.ROOT, "DROP TABLE IF EXISTS %s;", str));
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }
}
